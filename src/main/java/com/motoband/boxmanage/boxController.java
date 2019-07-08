package com.motoband.boxmanage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.motouser.MBUserModel;
import com.motoband.motouser.motouserService;
import com.motoband.news.newsModel;
import com.motoband.news.newsService;
import com.motoband.user.userService;
import com.motoband.util.CollectionUtil;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.ESManager;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.PinyinComparator;
import com.motoband.util.RedisManager;
import com.qcloud.PicCloud;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/boxmanage")
public class boxController {

	@Autowired
	private boxService boxService;
	@Autowired
	private userService userService;
	@Autowired
	private motouserService motouserService;
	@Autowired
	private newsService newsService;

	// 日志记录
	public static Logger logger = LoggerFactory.getLogger(boxController.class);

	private static final String NEWS_SORRY = "http://libres-10013836.cos.myqcloud.com/newssorry.jpg";
	public static final String NEWSKEY_BOXNEWS = "news_boxnews";// 外链动态存放池
	public static final String BOXKEY_BOXINFO = "_binfo";
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();
	
	@RequestMapping(value = "/boxlist", method = RequestMethod.GET)
	public void boxlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, String nowtypeid, int page, int limit, int order, String title, String orderConditions) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<Boxlist> pageBean = new PageBean<Boxlist>();
		if (page == 0) {
			page = 1;
		}
		if (limit == 0) {
			limit = 20;
		}

		if (title != null && !"".equals(title)) {
			try {
				title = MbUtil.sChangeBytes(title);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		if (orderConditions == null || orderConditions == "") {
			orderConditions = "";
		}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount = 0;
		if (nowtypeid == null || nowtypeid.equals("0") || nowtypeid == "") {
			totalCount = boxService.getBoxListCount(title);
		} else {
			totalCount = boxService.getBoxListBytypidCount(nowtypeid, title);
		}

		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		int start = (page - 1) * limit;
		ArrayList<Boxlist> boxlist = null;
		if (nowtypeid == null || nowtypeid.equals("0") || nowtypeid == "") {
			boxlist = boxService.getBoxList(userGuid, start, limit, order, title, orderConditions);
		} else {
			boxlist = boxService.getBoxList(userGuid, nowtypeid, start, limit, order, title, orderConditions);
		}

		ArrayList<Boxtype> boxtypelist = boxService.getBoxTypeList();
		Boxtype boxtype = new Boxtype();
		boxtype.setTypeid(0);
		boxtype.setDescription("全部");
		boxtypelist.add(boxtype);

		int updateCheck = boxService.getUserUpdateCheck(userGuid);
		int delCheck = boxService.getUserDelCheck(userGuid);
		int insCheck = boxService.getUserInsCheck(userGuid);
		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		if (delCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}
		if (insCheck > 0) {
			model.addAttribute("insCheck", "lock");
		} else {
			model.addAttribute("insCheck", "unlock");
		}
		for (Boxlist boxlist2 : boxlist) {
			Date dat = new Date(boxlist2.getBoxtime());
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(dat);
			java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			boxlist2.setTimeString(format.format(gc.getTime()));

		}

		ArrayList<Integer> limitList = new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("boxlist", boxlist);
		model.addAttribute("boxtypelist", boxtypelist);
		model.addAttribute("pageBean", pageBean);
		if (nowtypeid == null) {
			nowtypeid = "0";
		}
		model.addAttribute("nowtypeid", nowtypeid);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		if (title != null && !"".equals(title)) {
			model.addAttribute("title", title);
		} else {
			model.addAttribute("title", "");
		}

		model.addAttribute("orderConditions", orderConditions);
	}

	@RequestMapping(value = "/addNewBoxPage", method = RequestMethod.GET)
	public void addNewBoxPage(Model model, HttpSession session, HttpServletRequest request, String recommendBoxID) {
		ArrayList<Boxtype> boxtypelist = boxService.getBoxTypeList();
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		model.addAttribute("boxtypelist", boxtypelist);
		if (recommendBoxID != null && !recommendBoxID.equals("")) {

			BoxRecommendModel boxRecommendModel = boxService.getRecommend(Long.valueOf(recommendBoxID));
			if (boxRecommendModel != null) {
				if (checkUserApprove(boxRecommendModel.getUserid())) {
					model.addAttribute("approve", 1);
				} else {
					model.addAttribute("approve", 0);
				}
				model.addAttribute("box_submitter", boxRecommendModel.getUserid());
				model.addAttribute("source", boxRecommendModel.getSource());
				model.addAttribute("box_url", boxRecommendModel.getUrl());
				model.addAttribute("title", boxRecommendModel.getTitle());
				model.addAttribute("ismotoband", 1);
				model.addAttribute("checked", "checked");
			} else {
				model.addAttribute("approve", 0);
				model.addAttribute("box_submitter", "");
				model.addAttribute("source", "");
				model.addAttribute("box_url", "");
				model.addAttribute("title", "");
				model.addAttribute("ismotoband", 0);
				model.addAttribute("checked", "");
			}
		} else {
			model.addAttribute("approve", 0);
			model.addAttribute("box_submitter", "");
			model.addAttribute("source", "");
			model.addAttribute("box_url", "");
			model.addAttribute("title", "");
			model.addAttribute("ismotoband", 0);
			model.addAttribute("checked", "");
		}
		model.addAttribute("recommendBoxID", recommendBoxID);

	}

	@RequestMapping(value = "/addNewBoxPage1", method = RequestMethod.GET)
	public void addNewBoxPage1(Model model, HttpSession session, HttpServletRequest request) {
		ArrayList<Boxtype> boxtypelist = boxService.getBoxTypeList();
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		model.addAttribute("boxtypelist", boxtypelist);
	}

	@RequestMapping(value = "/addNewBoxPage", method = RequestMethod.POST)
	public void addNewBoxPagePost(Model model, HttpSession session, HttpServletRequest request, String recommendBoxID, String box_title, String box_subtitle, String box_writer, String box_type, String box_time, String box_content, String imglist, String box_boxkind, String box_approve, String box_source, String box_submitter, String box_keyword, String box_boxurl, PrintWriter out) {
		int typeid = Integer.parseInt(box_type);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long boxtime = 0;
		try {
			boxtime = formatter.parse(box_time).getTime();
		} catch (ParseException e) {

			e.printStackTrace();
		}

		motobox motobox = new motobox();
		motobox.setTypeid(typeid);
		motobox.setSubtitle(box_subtitle);
		motobox.setTitle(box_title);
		motobox.setBoxtime(boxtime);
		motobox.setBoxkind(Integer.parseInt(box_boxkind));
		motobox.setStatus(0);
		motobox.setKeyword(box_keyword);
		motobox.setApprove(Integer.parseInt(box_approve));
		motobox.setSubmitter(box_submitter);
		motobox.setSource(box_source);
		motobox.setLocalpath(box_boxurl);
		if (box_submitter != null && !box_submitter.equals("")) {
			motobox.setIsmotoband(0);
		} else {
			motobox.setIsmotoband(1);
		}

		int boxid = boxService.insMotoBox(motobox);
		Boxtype boxtype = boxService.getBoxTypeByTypeid(String.valueOf(motobox.getTypeid()));
		Map<String, String> map = new HashMap<String, String>();
		// try {
		// motobox.setBoxid(boxid);
		// map =BeanUtils.describe(motobox);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		map.put("boxid", motobox.getBoxid());
		map.put("typeid", String.valueOf(motobox.getTypeid()));
		map.put("subtitle", motobox.getSubtitle());
		map.put("title", motobox.getTitle());
		map.put("boxtime", String.valueOf(motobox.getBoxtime()));
		map.put("boxkind", String.valueOf(motobox.getBoxkind()));
		map.put("status", String.valueOf(motobox.getStatus()));
		map.put("keyword", motobox.getKeyword());
		map.put("approve", String.valueOf(motobox.getApprove()));
		map.put("submitter", motobox.getSubmitter());
		map.put("source", motobox.getSource());
		map.put("localpath", motobox.getLocalpath());
		map.put("ismotoband", String.valueOf(motobox.getIsmotoband()));

		map.put("description", "");
		map.put("boxurl", "");
		map.put("ishot", "0");
		map.put("news", "0");
		map.put("boxtype", boxtype.getDescription());
		map.put("titleimage", "");

		RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, boxid + "_binfo", map);

		String note_guid = UUID.randomUUID().toString();
		noteModel noteModel = new noteModel();
		noteModel.setNote_guid(note_guid);
		noteModel.setBoxid(String.valueOf(boxid));
		noteModel.setNote_writer(box_writer);
		noteModel.setNote_content(box_content);
		boxService.insMotoNote(noteModel);

		JSONArray jsonArray = JSONArray.parseArray(imglist);
		Object[] listArrayList = jsonArray.toArray();

		for (int i = 0; i < listArrayList.length; i++) {
			boxService.addImgBox(listArrayList[i].toString(), boxid);
		}
		if (recommendBoxID != null && !recommendBoxID.equals("")) {
			boxService.updateBoxRecommdState(recommendBoxID);
		}
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}

	@RequestMapping(value = "/uploadBoxImg", method = RequestMethod.GET)
	public void uploadBoxImg(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) {
		int APP_ID_V2 = 10013836; // 项目ID
		String SECRET_ID_V2 = "AKID8TCGA2cH6hkJQ3NriIAkzN0bvIHd9r1w"; // 项目SecretID
		String SECRET_KEY_V2 = "NZoF8svEjxv9d1ThKdalZsmCoAuOBjX5"; // 项目SecretKey
		String BUCKET = "motobox"; // 空间名称bucket
		PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
		String sign = "";
		String signdel = "";
		long expired = System.currentTimeMillis() / 1000 + 3600 * 24 * 30 * 2;
		String type = request.getParameter("type");
		String fileid = request.getParameter("fileid");
		String url = "";
		if (null == type || "".equals(type)) {
			sign = pc.getSign(expired);
			fileid = UUID.randomUUID().toString();
			url = pc.getUrl("0", fileid);
		} else if ("upload".equals(type)) {
			fileid = UUID.randomUUID().toString();
			url = pc.getUrl("0", fileid);
			sign = pc.getSign(expired);
		} else if ("delete".equals(type) || "download".equals(type)) {
			signdel = pc.getSignOnce(fileid);
		} else {
			sign = "";
		}

		url = url.replace("http", "https");

		sign = pc.getSign(expired);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("signdel", signdel.toString());
		jsonObject.put("sign", sign.toString());
		jsonObject.put("url", url);
		out.print(jsonObject.toString());
	}

	@RequestMapping(value = "/getSignUrl", method = RequestMethod.POST)
	public void getSignUrl(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) {
		int APP_ID_V2 = 10013836; // 项目ID
		String SECRET_ID_V2 = "AKID8TCGA2cH6hkJQ3NriIAkzN0bvIHd9r1w"; // 项目SecretID
		String SECRET_KEY_V2 = "NZoF8svEjxv9d1ThKdalZsmCoAuOBjX5"; // 项目SecretKey
		String BUCKET = "motobox"; // 空间名称bucket
		PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
		String sign = "";
		long expired = System.currentTimeMillis() / 1000 + 3600 * 24 * 30 * 2;
		String url = "https://web.image.myqcloud.com/photos/v2/10013836/motobox/0/";
		sign = pc.getSign(expired);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("sign", sign.toString());
		jsonObject.put("url", url);
		out.print(jsonObject.toString());

	}

	@RequestMapping(value = "/boxsel", method = RequestMethod.GET)
	public void boxsel(Model model, HttpSession session, HttpServletRequest request, String box_id) {
		motobox motobox = boxService.getMotoBoxMessageByBoxid(box_id);
		noteModel noteModel = boxService.getNoteModelMessageByBoxid(box_id);
		ArrayList<Boxtype> boxtypelist = boxService.getBoxTypeList();
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		if (motobox.getIsmotoband() == 0) {
			model.addAttribute("notmotopush", "block");
			model.addAttribute("motopush", "block");
			model.addAttribute("motoselfpush", "none");
		} else if (motobox.getLocalpath() != null && !motobox.getLocalpath().equals("")) {
			model.addAttribute("notmotopush", "none");
			model.addAttribute("motopush", "block");
			model.addAttribute("motoselfpush", "none");
		} else {
			model.addAttribute("notmotopush", "none");
			model.addAttribute("motopush", "none");
			model.addAttribute("motoselfpush", "block");
		}
		model.addAttribute("imggroups", imggroups);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(motobox.getBoxtime());
		String puttimeString = sdf.format(date);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

		int pushCheck = userService.getUserPushCheck(admin.getUser_guid());
		if (pushCheck > 0) {
			model.addAttribute("pushCheck", "lock");
		} else {
			model.addAttribute("pushCheck", "unlock");
		}
		noteModel.setNote_content(noteModel.getNote_content().replace('\n', ' '));
		noteModel.setNote_content(noteModel.getNote_content().replace("'Helvetica Neue', Helvetica, 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif;", ""));
		noteModel.setNote_content(noteModel.getNote_content().replace("'Microsoft YaHei'", ""));
		noteModel.setNote_content(noteModel.getNote_content().replace("'Helvetica Neue'", ""));

		/*
		 * noteModel.setNote_content(noteModel.getNote_content().replace("'",
		 * "&apos;"));
		 */
		String box_titleimage = boxService.getTitleImage(box_id);
		model.addAttribute("box_titleimage", box_titleimage);
		model.addAttribute("puttime", puttimeString);
		model.addAttribute("motoimgs", motoimgs);
		model.addAttribute("boxtypelist", boxtypelist);

		if (motobox.getBoxurl() == null) {
			motobox.setBoxurl("");
		}
		model.addAttribute("motobox", motobox);
		model.addAttribute("noteModel", noteModel);
	}

	@RequestMapping(value = "/imgSourceManage", method = RequestMethod.GET)
	public void imgSourceManage(Model model, HttpSession session, HttpServletRequest request) {
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		Collections.sort(imggroups, new PinyinComparator());
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
	}

	@RequestMapping(value = "/delBoxMessage", method = RequestMethod.POST)
	public void delBoxMessage(Model model, HttpSession session, HttpServletRequest request, String box_id, PrintWriter out) {
		boxService.delBoxMessage(box_id);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
//		BoxYunSouModel boxYunSouModel = new BoxYunSouModel();
//		boxYunSouModel.setBoxid(Integer.valueOf(box_id));
//		YunSouManager.delYuSou(BoxYunSouModel.convertToMap(boxYunSouModel));
	}

	@RequestMapping(value = "/addNewBoxImg", method = RequestMethod.POST)
	public void addNewBoxImg(Model model, HttpSession session, HttpServletRequest request, String img_guid, String img_url, String img_opurl, String img_name, String img_groupid, PrintWriter out) {
		long img_time = System.currentTimeMillis();
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		String img_writerguid = admin.getUser_guid();
		motoimg motoimg = new motoimg();
		motoimg.setImg_guid(img_guid);
		motoimg.setImg_url(img_url);
		motoimg.setImg_opurl(img_opurl);
		motoimg.setImg_time(img_time);
		motoimg.setImg_writerguid(img_writerguid);
		motoimg.setImg_name(img_name);
		motoimg.setImg_groupid(img_groupid);

		boxService.insMotoImg(motoimg);
		String str = JSON.toJSONString(motoimg);
		out.print(str);

	}

	@RequestMapping(value = "/addBoxPage", method = RequestMethod.POST)
	public void addBoxPage(Model model, HttpSession session, HttpServletRequest request, String box_title, String box_subtitle, String box_writer, String box_type, String box_time, String box_content, String box_status, String box_id, String preview, String imglist, String box_titleimg, String box_boxkind, String box_boxurl, String box_approve, String box_source, String box_submitter, String box_keyword, PrintWriter out) {
		motobox motobox = new motobox();
		motobox.setBoxid(box_id);
		motobox.setTypeid(Integer.parseInt(box_type));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		// try {
		// Date datetime = formatter.parse(box_time);
		// } catch (ParseException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		long boxtime = 0;
		try {
			boxtime = formatter.parse(box_time).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		motobox.setBoxtime(boxtime);
		motobox.setApprove(Integer.parseInt(box_approve));
		motobox.setSource(box_source);
		motobox.setSubmitter(box_submitter);
		motobox.setKeyword(box_keyword);
		motobox.setStatus(Integer.parseInt(box_status));
		motobox.setTitle(box_title);
		motobox.setSubtitle(box_subtitle);
		motobox.setBoxkind(Integer.parseInt(box_boxkind));
		noteModel noteModel = new noteModel();
		noteModel.setBoxid(box_id);
		noteModel.setNote_writer(box_writer);
		noteModel.setNote_content(box_content);

		Boxtype boxtype = boxService.getBoxTypeByTypeid(String.valueOf(motobox.getTypeid()));

		Map<String, String> map = new HashMap<String, String>();
		map.put("boxid", String.valueOf(motobox.getBoxid()));
		map.put("typeid", String.valueOf(motobox.getTypeid()));
		map.put("subtitle", motobox.getSubtitle());
		map.put("title", motobox.getTitle());
		map.put("boxtime", String.valueOf(motobox.getBoxtime()));
		map.put("boxkind", String.valueOf(motobox.getBoxkind()));
		map.put("status", String.valueOf(motobox.getStatus()));
		map.put("keyword", motobox.getKeyword());
		map.put("approve", String.valueOf(motobox.getApprove()));
		map.put("submitter", motobox.getSubmitter());
		map.put("source", motobox.getSource());
		map.put("boxtype", boxtype.getDescription());
		// map.put("localpath",motobox.getLocalpath());
		// map.put("ismotoband",String.valueOf(motobox.getIsmotoband()));

		// try {
		// map =BeanUtils.describe(motobox);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", map);

		boxService.updateBoxMessage(motobox);
		boxService.updateNoteMessage(noteModel);
		box_time = box_time.substring(0, box_time.indexOf(' '));

		if (box_status.equals("1") || preview.equals("1")) {
			motobox = boxService.getMotoBoxMessageByBoxid(box_id);
			if (motobox.getBoxurl() == null) {
				String fileid = UUID.randomUUID().toString() + ".html";
				String localpath = boxService.getPageLocalPath() + fileid;
				if (box_boxurl != null && !box_boxurl.equals("")) {

					tohtmlfile("/Model/Model.html", localpath, box_title, box_time, box_writer, box_content, box_boxurl, box_id, "none");
				} else {
					tohtmlfile("/Model/Modelx.html", localpath, box_title, box_time, box_writer, box_content, box_boxurl, box_id, "block");
				}
				String showurl = "http://" + boxService.getBoxUrlIP() + "/" + fileid;
				// showurl=box_boxurl;
			//	System.out.println("url===========" + showurl);
				localpath = box_boxurl;
				boxService.updatePath(box_id, localpath, showurl);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", "localpath", localpath);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", "boxurl", showurl);
				Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
				String dataString = "{ \"root\": [ {\"userid\":\"" + admin.getUser_guid() + "\",\"showurl\":\"" + showurl + "\"}]}";

				JSONObject jsonObject = JSONObject.parseObject(dataString);
				out.print(jsonObject);

			} else {
				File file = new File(motobox.getBoxurl());
				if (file.isFile() && file.exists()) {
					file.delete();
				}
				String fileid = file.getName();
				String localpath = boxService.getPageLocalPath() + fileid;
				if (box_boxurl != null && !box_boxurl.equals("")) {

					tohtmlfile("/Model/Model.html", localpath, box_title, box_time, box_writer, box_content, box_boxurl, box_id, "none");
				} else {
					tohtmlfile("/Model/Modelx.html", localpath, box_title, box_time, box_writer, box_content, box_boxurl, box_id, "block");
				}
				String showurl = "http://" + boxService.getBoxUrlIP() + "/" + fileid;
				// showurl=box_boxurl;
				localpath = box_boxurl;
			//	System.out.println("url===========" + showurl);

				boxService.updatePath(box_id, localpath, showurl);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", "localpath", localpath);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", "boxurl", showurl);
				Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
				String dataString = "{ \"root\": [ {\"userid\":\"" + admin.getUser_guid() + "\",\"showurl\":\"" + showurl + "\"}]}";

				JSONObject jsonObject = JSONObject.parseObject(dataString);
				out.print(jsonObject);
			}
//			BoxYunSouModel boxYunSouModel = new BoxYunSouModel();
//			boxYunSouModel.setBoxid(Integer.valueOf(box_id));
//			boxYunSouModel.setTitle(box_title);
//			boxYunSouModel.setKeyword(box_keyword);
//			boxYunSouModel.setNA(Integer.valueOf(box_id));
//			YunSouManager.addYuSou(BoxYunSouModel.convertToMap(boxYunSouModel));
		} else {
//			BoxYunSouModel boxYunSouModel = new BoxYunSouModel();
//			boxYunSouModel.setBoxid(Integer.valueOf(box_id));
//			YunSouManager.delYuSou(BoxYunSouModel.convertToMap(boxYunSouModel));
		}
		JSONArray jsonArray = JSONArray.parseArray(imglist);
		Object[] listArrayList = jsonArray.toArray();
		boxService.delImgBox(box_id);
		for (int i = 0; i < listArrayList.length; i++) {
			boxService.addImgBox(listArrayList[i].toString(), Integer.parseInt(box_id));
		}
		if (box_titleimg != null) {
			boxService.addTitleImg(box_id, box_titleimg);
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, box_id + "_binfo", "titleimage", box_titleimg);
		}

	}

	public boolean tohtmlfile(String filePath, String HtmlFile, String title, String time, String writer, String context, String box_boxurl, String box_id, String display) {
		time = time.replace("-", "/");
		String str = "";
		try {
			String tempStr = "";

			FileInputStream is = new FileInputStream(filePath);// 读取模块文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while ((tempStr = br.readLine()) != null)
				str = str + tempStr;
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		try {
			str = str.replaceAll("###display###", display);
			str = str.replaceAll("###title###", title);
			str = str.replaceAll("###time###", time);
			str = str.replaceAll("###writer###", writer);
			str = str.replaceAll("###content###", context);
			str = str.replaceAll("###box_boxurl###", box_boxurl);
			str = str.replaceAll("###box_id###", box_id);
			File f = new File(HtmlFile);
			BufferedWriter o = new BufferedWriter(new FileWriter(f));
			o.write(str);
			o.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/delBoxImg", method = RequestMethod.POST)
	public void delBoxImg(Model model, HttpSession session, HttpServletRequest request, String img_guid, PrintWriter out) {
		String img_boxid = boxService.selectBoxIdByGuid(img_guid);
		if (img_boxid == null || img_boxid == "") {
			boxService.delMotoImg(img_guid);
			out.print("ok");
		} else {
			out.print("no");
		}

	}

	@RequestMapping(value = "/showgroupimg", method = RequestMethod.POST)
	public void showgroupimg(Model model, HttpSession session, HttpServletRequest request, String group_guid, PrintWriter out) {
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid(group_guid);
		String str = JSON.toJSONString(motoimgs);
		out.print(str);
	}

	@RequestMapping(value = "/updateImgName", method = RequestMethod.POST)
	public void updateImgName(Model model, HttpSession session, HttpServletRequest request, String img_guid, String newname, PrintWriter out) {
		boxService.updateImgName(img_guid, newname);
		out.print("success");
	}

	@RequestMapping(value = "/saveimgNewGroup", method = RequestMethod.POST)
	public void saveimgNewGroup(Model model, HttpSession session, HttpServletRequest request, String img_guid, String imgnewgroup, PrintWriter out) {
		boxService.saveimgNewGroup(img_guid, imgnewgroup);
		out.print("success");
	}

	@RequestMapping(value = "/delImgList", method = RequestMethod.POST)
	public void delImgList(Model model, HttpSession session, HttpServletRequest request, String imgguidlist, PrintWriter out) {
		JSONArray jsonArray = JSONArray.parseArray(imgguidlist);
		Object[] listArrayList = jsonArray.toArray();
		Boolean t = true;
		if (listArrayList.length > 0) {
			for (int i = 0; i < listArrayList.length; i++) {
				String img_boxid = boxService.selectBoxIdByGuid(listArrayList[i].toString());
				if (img_boxid != null && img_boxid != "") {
					t = false;

				}
			}
			if (t) {
				for (Object json : listArrayList) {
					boxService.delMotoImg(json.toString());
				}
				out.print("删除成功");
			} else {
				out.print("存在已被占用的图片！");
			}

		} else {
			out.print("没有选中图片");
		}

	}

	@RequestMapping(value = "/saveimgListNewGroup", method = RequestMethod.POST)
	public void saveimgListNewGroup(Model model, HttpSession session, HttpServletRequest request, String imglistnewgroup, String imgguidlist, PrintWriter out) {
		JSONArray jsonArray = JSONArray.parseArray(imgguidlist);
		Object[] listArrayList = jsonArray.toArray();
		if (listArrayList.length > 0) {
			for (int i = 0; i < listArrayList.length; i++) {
				boxService.saveimgNewGroup(listArrayList[i].toString(), imglistnewgroup);
			}
			out.print("保存图片分组成功");

		} else {
			out.print("没有选中图片");
		}
	}

	@RequestMapping(value = "/checkGroupName", method = RequestMethod.POST)
	public void checkGroupName(Model model, HttpSession session, HttpServletRequest request, String groupname, PrintWriter out) {
		String groupnewname = boxService.checkGroupName(groupname);
		if (groupnewname == null) {
			out.print("ok");
		} else {
			out.print("no");
		}
	}

	@RequestMapping(value = "/addImgGroup", method = RequestMethod.POST)
	public void addImgGroup(Model model, HttpSession session, HttpServletRequest request, String groupname, PrintWriter out) {
		String groupid = UUID.randomUUID().toString();

		boxService.addImgGroup(groupid, groupname);
		out.print("success");
	}

	@RequestMapping(value = "/sellook", method = RequestMethod.POST)
	public void sellook(Model model, HttpSession session, HttpServletRequest request, String boxid, PrintWriter out) {
		long count = boxService.getBoxLook(boxid);
		//System.out.println(count);
		out.print(count);
	}

	@RequestMapping(value = "/boxRecommendList", method = RequestMethod.GET)
	public void boxRecommendList(Model model, HttpSession session, HttpServletRequest request, String userGuid) {
		List<BoxRecommendModel> boxRecommendModels = new ArrayList<BoxRecommendModel>();
		boxRecommendModels = boxService.getBoxRecommendList();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (int i = 0; i < boxRecommendModels.size(); i++) {
			BoxRecommendModel boxRecommendModel = boxRecommendModels.get(i);
			boxRecommendModel.addtimestr = formatter.format(new Date(boxRecommendModel.getAddtime()));
			boxRecommendModel.nickname = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, boxRecommendModel.userid + "_user", "nickname");
			boxRecommendModels.set(i, boxRecommendModel);
			if (checkUserApprove(boxRecommendModel.getUserid())) {
				boxRecommendModel.setApprove(1);
			}
		}
		model.addAttribute("boxrecommendlist", boxRecommendModels);

	}

	public boolean checkUserApprove(String userid) {
		int count = boxService.checkUserApprove(userid);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@RequestMapping(value = "/delFromBoxRecommend", method = RequestMethod.POST)
	public void delFromBoxRecommend(Model model, HttpSession session, HttpServletRequest request, PrintWriter out, String userGuid, String recommendBoxID) {
		boxService.delFromBoxRecommend(recommendBoxID);
		out.print("success");
	}

	@RequestMapping(value = "/boxhotmanage", method = RequestMethod.POST)
	public void boxhotmanage(Model model, HttpSession session, HttpServletRequest request, PrintWriter out, String state, String boxid) {
		if (state.equals("0")) {

			boxService.delHotBox(boxid);
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, boxid + "_binfo", "ishot", "0");
		} else if (state.equals("1")) {
			boxService.addHotBox(boxid);
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, boxid + "_binfo", "ishot", "1");
		}
		out.print("success");
	}

	@RequestMapping(value = "/addBoxBanner", method = RequestMethod.POST)
	public void addBoxBanner(Model model, HttpSession session, HttpServletRequest request, String boxid, PrintWriter out) throws UnsupportedEncodingException {
		BoxBannerModel banner = new BoxBannerModel();
		if (boxid != null) {

			banner.setBoxid(Integer.parseInt(boxid));
		}
		motobox box = new motobox();
		box = boxService.getBoxByBoxid(boxid);
		banner.setScore(0);
		banner.setTitle(box.getTitle());
		banner.setType(0);
		banner.setSubtitle(box.getSubtitle());
		banner.setUrl(box.getBoxurl());
		banner.setState(0);
		boxService.addBoxBanner(banner);
		out.print("success");
	}

	@RequestMapping(value = "/boxcomments", method = RequestMethod.GET)
	public void boxcomments(Model model, HttpSession session, HttpServletRequest request, String boxid) {
		List<BoxCommentModel> commentlist = new ArrayList<BoxCommentModel>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist")) {
			long clistCount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist");
			if (clistCount > 0) {
				Set<String> clist = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist", 0, clistCount);
				if (clist != null && clist.size() > 0) {
					for (String s : clist) {
						if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, s + "_bcommentmap")) {
							Map<String, String> cmap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, s + "_bcommentmap");
							if (cmap != null && cmap.size() > 0) {
								BoxCommentModel commentModel = BoxCommentModel.convertToCommentModel(cmap);
								commentModel.setCtimeString(sdf.format(new Date(Long.parseLong(commentModel.getCtime()))));
								if (commentModel.getTuserid() != null && !"".equals(commentModel.getTuserid())) {
									MBUserModel tmb = motouserService.getUserByID(commentModel.getTuserid());
									if (tmb != null && (tmb.nickname == null || tmb.nickname.isEmpty())) {

										Random rand = new Random();
										tmb.nickname = "MotoBand+" + (rand.nextInt(5000000) + 1000000);

									}

									commentModel.setTusernickname(tmb.getNickname());
									commentModel.setTuserHeadurl(tmb.getHeadurl());
								}
								MBUserModel mb = motouserService.getUserByID(commentModel.getUserid());
								if (mb != null && (mb.nickname == null || mb.nickname.isEmpty())) {

									Random rand = new Random();
									mb.nickname = "MotoBand+" + (rand.nextInt(5000000) + 1000000);

								}
								commentModel.setUsernickname(mb.getNickname());
								commentModel.setUserHeadurl(mb.getHeadurl());
								commentlist.add(commentModel);
							}
						}
					}
				}
			}

		}
		if (commentlist != null && commentlist.size() > 0) {
			model.addAttribute("commentlist", commentlist);
		} else {
			model.addAttribute("commentlist", "");
		}

	}

	@RequestMapping(value = "/deleteboxcomment", method = RequestMethod.POST)
	public void deleteboxcomment(Model model, HttpSession session, HttpServletRequest request, String cid, String boxid, PrintWriter out) {
		long i = 0;
		long j = 0;
		double score = 0;
		Map<String, String> map = new HashMap<String, String>();
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, cid + "_bcommentmap")) {
			map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, cid + "_bcommentmap");
			i = RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN, cid + "_bcommentmap");

		}
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist")) {
			long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist");
			if (count > 0) {
				Set<String> setStr = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist", 0, count);
				for (String s : setStr) {
					if (s.equals(cid)) {
						score = RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist", cid);
						j = RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist", s);
					}
				}
			}

		}
		if (i != 0 && j != 0) {
			out.write("success");
		} else if (i != 0 && j == 0) {
			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, cid + "_bcommentmap", map);
		} else if (i == 0 && j != 0) {
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, boxid + "_bcommentlist", Double.doubleToLongBits(score), cid);
		}

	}

	@RequestMapping(value = "/releaseNews", method = RequestMethod.POST)
	public void releaseNews(Model model, HttpSession session, HttpServletRequest request, String typeid, String boxid, PrintWriter out)  {

		if (typeid != null && !"".equals(typeid)) {
			Boxtype boxtype = boxService.getBoxTypeByTypeid(typeid);
			String userid = boxtype.getUserid();

			long time = System.currentTimeMillis();
			Map<String, String> map = new HashMap<String, String>();
			String nid = "box_" + userid + "_" + boxid;
			map.put("userid", userid);
			map.put("updatetime", Long.toString(time));
			map.put("ptime", Long.toString(time));
			map.put("nid", nid);
			map.put("boxid", boxid);
			map.put("type", "8");
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, boxid + BOXKEY_BOXINFO, "nid", nid);
			Map<String, String> boxMap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, boxid + BOXKEY_BOXINFO);
			motobox mbox = motobox.convertToMotobox(boxMap);
			map.put("motoboxmodel", JSON.toJSONString(mbox));
			map.put("picurl", mbox.getTitleimage());
			BufferedImage image=null;
			if(mbox.getTitleimage()!=null && !"".equals(mbox.getTitleimage())){
				  String imageUrl=mbox.getTitleimage();  
			        image=getBufferedImage(imageUrl);  
			        if (image!=null)  
			        {  
			        	map.put("picheight", String.valueOf(image.getHeight()));
			        	map.put("picwidth", String.valueOf(image.getWidth()));  
			        }else{
			        	map.put("picheight", "350");
			        	map.put("picwidth", "750"); 
			        }  
			}
		  
			
			if (!RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo")) {
				RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", map);
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, userid + "_unlist", time, nid);
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, userid + "_anlist", time, nid);
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS, time, nid);

				if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_FOLLOW, userid + "_followed")) {
					long count = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_FOLLOW, userid + "_followed");
					if (count > 0) {
						Set<String> set = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_FOLLOW, userid + "_followed", 0, count);
						for (String s : set) {
							RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, s + "_anlist", time, nid);

						}
					}
				}
				newsModel newsModel = new newsModel();
				newsModel.setUserid(userid);
				newsModel.setUpdatetime(time);
				newsModel.setPtime(time);
				newsModel.setNid(nid);
				newsModel.setBoxid(Integer.parseInt(boxid));
				newsModel.setType(8);
				newsModel.setPicurl(mbox.getTitleimage());
				if(mbox.getTitleimage()!=null && !"".equals(mbox.getTitleimage())){
					 if (image!=null){  
						 newsModel.setPicheight(image.getHeight());
						 newsModel.setPicwidth(image.getWidth());
				        } 
				}
				 
				newsService.insertNews(newsModel);
				boxService.updateNewsStatus(1, boxid);
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_RUN, boxid + "_binfo", "news", "1");
				long userNewsCount = 0l;
				if (!RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, userid + "_unlist")) {
					userNewsCount = 0l;
				} else {
					userNewsCount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, userid + "_unlist");
				}
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "newscount", String.valueOf(userNewsCount));
				RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid + "_user", "updatetime", String.valueOf(System.currentTimeMillis()));
				
				//同步ES
				newsModel newsModel1= new newsModel();
				newsModel1.setNid(nid);
				newsModel1.setPid(Long.toString(time));
				newsModel1.setMotoboxmodel(mbox);
				ESManager.syncNewsEs(newsModel1);
				
				out.write("success");
			} else {
				out.write("fail");
			}

		}

	}

	@RequestMapping(value = "/boxAddToRecommendnews", method = RequestMethod.POST)
	public void boxAddToRecommendnews(Model model, HttpSession session, HttpServletRequest request, String typeid, String boxid, String addtype, PrintWriter out) throws IOException {
		if (boxid != null && !"".equals(boxid) && typeid != null && !"".equals(typeid)) {
			Boxtype boxtype = boxService.getBoxTypeByTypeid(typeid);
			String userid = boxtype.getUserid();
			Date myDate = new Date();
			SimpleDateFormat daysdf = new SimpleDateFormat("yyyyMMddHHmm");
			String score = daysdf.format(myDate);
			String nid = "box_" + userid + "_" + boxid;
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo")) {
				// RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS,"news_motobandpage_boxandstory",
				// Long.valueOf(score), "box_"+userid+"_"+boxid);
				JSONObject obj = new JSONObject();
				obj.put("addtype", Integer.parseInt(addtype));
				obj.put("nid", nid);
				obj.put("score", score);
				obj.put("userid", Consts.TIM_AdminUserID);
				obj.put("sign", Consts.TIM_AdminSig);
				String data = JSON.toJSONString(obj);
				String urlString = Consts.ServiceUrl + "news/addrecommendnews";

				logger.info("boxAddToRecommendnews request data:[url:" + urlString + "  param:" + data + "]");

				RequestBody body = RequestBody.create(MediaType_JSON, data);
				Request req = new Request.Builder().url(urlString).post(body).build();
				Response resp=httpClient.newCall(req).execute();
			
				String result = new String(resp.body().bytes(), "utf-8");

				logger.info("boxAddToRecommendnews response data:[result" + result + "]");

				JSONObject json = JSON.parseObject(result);
				String str = (String) json.get("code");
				if (Integer.parseInt(str) == 0) {
					out.write("success");
				}

			} else {
				out.write("fail");
			}

		}

	}

	@RequestMapping(value = "/getBoxTitleList", method = RequestMethod.POST)
	public void getBoxTitleList(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) {
		String jsonStr = "";
		ArrayList<Boxlist> boxlist = boxService.getBoxTitleList();
		if (boxlist != null && boxlist.size() > 0) {
			jsonStr = JSONObject.toJSONString(boxlist);
		}

		out.write(jsonStr);
	}

	@RequestMapping(value = "/getBoxByBoxid", method = RequestMethod.POST)
	public void getBoxByBoxid(Model model, HttpSession session, HttpServletRequest request, String boxid, PrintWriter out) {
		String jsonStr = "";
		if (boxid != null && !"".equals(boxid)) {

			motobox box = boxService.getBoxByBoxid(boxid);
			if (box != null) {
				jsonStr = JSONObject.toJSONString(box);
			}
		}

		out.write(jsonStr);
	}
	
	
    public static BufferedImage getBufferedImage(String imgUrl) {  
        URL url = null;  
        InputStream is = null;  
        BufferedImage img = null;  
        try {  
            url = new URL(imgUrl);  
            is = url.openStream();  
            img = ImageIO.read(is);  
        } catch (MalformedURLException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
              
            try {  
            	if(is!=null){
            		is.close(); 
            	}
                 
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return img;  
    }  
  
 
}
