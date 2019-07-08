package com.motoband.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motobox;
import com.motoband.boxmanage.motoimg;
import com.motoband.credit.CreditService;
import com.motoband.motouser.MBUserModel;
import com.motoband.motouser.motouserService;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.ESManager;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Controller
@RequestMapping(value = "/news")
public class newsController {
	@Autowired
	private newsService newsService;
	@Autowired
	private boxService boxService;
	@Autowired
	private motouserService motouserService;
	@Autowired
	private CreditService creditService;
	
	// 日志记录
	public	static Logger logger = LoggerFactory.getLogger(newsController.class);
	
	private static final String NEWSKEY_INFO="_ninfo";
	private static final String NEWSKEY_USER="_user";
	private static final String NEWSKEY_RECOMMENDUSERID="admin";
	
	private static final String NEWSKEY_LABEL = "_label";
	public static final String NEWSKEY_LABELRECOMMEND = "_labelrecommend";
	private static final String NEWSKEY_NEWSHOT = "_hot";
	private static final String NEWSKEY_DISCUSSHOT = "_discusshot";
	//boxnews
	public static final String NEWSKEY_BOXNEWS = "news_boxnews";//外链动态存放池
	//2.1   积分类型
	public static final byte CreditActionType_Add = 0;// 增加
	public static final byte CreditActionType_Subtract = 1;// 减少
	
	public static final int CreditType_BreakNews = 200;
	public static final int CreditType_BreakStory = 201;
	public static final int CreditType_BreakComment = 202;
	public static final int CreditType_NewsRecommend = 15;
	public static final int CreditType_StoryRecommend = 16;
	public static final int CreditType_NewsRecommendMain = 17;
	public static final int CreditType_StoryRecommendMain = 18;
	
	public static final String RUNKEY_NEWMOTOMODELASSESSMENTLIST = "_newmotomodelassessmentlist";
	
	public static final MediaType MediaType_JSON = MediaType.parse("application/json; charset=utf-8");

	private static OkHttpClient httpClient = new OkHttpClient();
	
	
	public List<newsModel> getNewInfoListFromIdSet(Set<String> set) {
		if (set.isEmpty()) {
			return null;
		}
		List<newsModel> list = new ArrayList<newsModel>();
		for (String nid : set) {

			newsModel model = this.getNewsInfo(nid);

			if (model != null) {
				list.add(model);
			}
		}

		return list;
	}
	public newsModel getNewsInfo(String nid) {
		newsModel model = null;
		Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo");

		if (map != null && !map.isEmpty()) {
			model = newsModel.convertToModel(map);
		} 
		return model;
	}
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public void news(Model model, HttpSession session, HttpServletRequest request, String userGuid) {
		int updateCheck = newsService.getUserUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = newsService.getUserInsCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("insCheck", "lock");
		} else {
			model.addAttribute("insCheck", "unlock");
		}


	}

	@RequestMapping(value = "/topicnews", method = RequestMethod.GET)
	public void topicnews(Model model, HttpSession session, HttpServletRequest request, String label,String topictype) throws IOException {
		label=MbUtil.sChangeBytes(label);
		model.addAttribute("topictype", topictype);
		model.addAttribute("label", label);
		model.addAttribute("keyword", label);
		JSONObject obj = new JSONObject();
		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("label", label);
		obj.put("topictype", topictype);
		obj.put("pagetype", 0);
		obj.put("pagesize", 1);
//		String data = JSON.toJSONString(obj);
//
//		String urlString = Consts.ServiceUrl + "news/refreshnewsbylabel";
//		logger.info("topicnews  request data:[url:"+urlString+"  param:"+data+"]");
//		
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("topicnews  response data:[result:"+result+"]");
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/news/gettopicnewslist", data);
		String resultstr = MbUtil.handleResp(responstr);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		JSONObject json = JSON.parseObject(resultstr);
		JSONArray jsonArray = json.getJSONArray("data");
		if(jsonArray !=null && jsonArray.size()>0){
			JSONObject jsonObject=jsonArray.getJSONObject(0);
			String ptime=jsonObject.getString("ptime");
			String ptimeString=formatter.format(new Date(Long.valueOf(ptime)));
			jsonObject.put("ptimeString", ptimeString);
			newsModel news=JSONObject.parseObject(jsonObject.toJSONString(),newsModel.class);
			
			String temptopicnews = jsonObject.getString("nid");
			model.addAttribute("temptopicnews", temptopicnews);
			model.addAttribute("tempnews",news);
		}
		

//		urlString = Consts.ServiceUrl + "news/refreshlabelhotnews";
//		logger.info("topicnews request data:[url:"+urlString+"  param:"+data+"]");
//		body = RequestBody.create(MediaType_JSON, data);
//		req = new Request.Builder().url(urlString).post(body).build();
//		resp=httpClient.newCall(req).execute();
//		result =new String(resp.body().bytes(), "utf-8");
//		logger.info("topicnews response data:[result:"+result+"]");
		
		 responstr = MbUtil.handleReqAndSendReq("/news/getlabelhotnewslist", data);
		 resultstr = MbUtil.handleResp(responstr);
		json = JSON.parseObject(resultstr);
		jsonArray = json.getJSONArray("data");
		if(jsonArray !=null && jsonArray.size()>0){
		JSONObject jsonObject=jsonArray.getJSONObject(0);
		String ptime=jsonObject.getString("ptime");
		String ptimeString=formatter.format(new Date(Long.valueOf(ptime)));
		jsonObject.put("ptimeString", ptimeString);
		newsModel news1=JSONObject.parseObject(jsonObject.toJSONString(), newsModel.class);
		String temptopichot = jsonObject.getString("nid");
		model.addAttribute("temptopichot", temptopichot);
		model.addAttribute("temphots",news1);
		}
		

		JSONObject object = new JSONObject();
		object.put("userid", Consts.TIM_AdminUserID);
//		object.put("sign", Consts.TIM_AdminSig);
//		String datajson = JSON.toJSONString(object);
//		String url = Consts.ServiceUrl + "gettopic";
//		logger.info("topicnews request data:[url:"+urlString+"  param:"+data+"]");
//		body = RequestBody.create(MediaType_JSON, data);
//		req = new Request.Builder().url(urlString).post(body).build();
//		resp=httpClient.newCall(req).execute();
//		result =new String(resp.body().bytes(), "utf-8");
//		logger.info("topicnews response data:[result"+result+"]");
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		obj.put("pagenum", 0);
		obj.put("pagesize",1000);
		obj.put("topictype", 0);
		 data = JSON.toJSONString(obj);
		 responstr = MbUtil.handleReqAndSendReq("/dataversion/gettopiclist", data);
		 resultstr = MbUtil.handleResp(responstr);
		
		JSONObject topicjson = JSON.parseObject(resultstr);
		JSONArray topicjsonArray = topicjson.getJSONArray("data");
		List<topic> topics = new ArrayList<topic>();
		if(topicjsonArray !=null && topicjsonArray.size()>0){
		for (int i = 0; i < topicjsonArray.size(); i++) {

			topics.add(JSON.parseObject(topicjsonArray.getJSONObject(i).toJSONString(), topic.class));
		}
		model.addAttribute("topics", topics);
		}
	}
	
	
	
	@RequestMapping(value = "/recommendnewslist", method = RequestMethod.GET)
	public void recommendnewslist(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order) throws UnsupportedEncodingException {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		PageBean<RecommendNewsModel> pageBean=new PageBean<RecommendNewsModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_recommendnews")){
			totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_recommendnews");
		}
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 

		List<RecommendNewsModel> recommendNewsModels = new ArrayList<RecommendNewsModel>();
		
		Set<String> temprecommendSet= new HashSet<String>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_recommendnews")){
			long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_recommendnews");
			if(order==1){
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_recommendnews",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_recommendnews",(page-1)*limit, page*limit-1);
				}
				
			}else{
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_recommendnews",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_recommendnews",(page-1)*limit, page*limit-1);
				}
				
			}
			for(String s: temprecommendSet){
				if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, s+"_ninfo")){
					Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, s+"_ninfo");
					if(map!=null && map.size()>0){
						RecommendNewsModel recommendNewsModel=RecommendNewsModel.convertToCommentModel(map);
						double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, "news_recommendnews", s);
						recommendNewsModel.setScore(Math.round(score));
						recommendNewsModel.ptimeString=formatter.format(new Date(recommendNewsModel.ptime));
	    				if (recommendNewsModel.updatetime!=0) {
	    					recommendNewsModel.updatetimeString=formatter.format(new Date(recommendNewsModel.updatetime));
	    				}
	    				recommendNewsModels.add(recommendNewsModel);
					}
				}
			}
		}
	
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		
		model.addAttribute("recommendNewsModels", recommendNewsModels);

		int updateCheck = newsService.getRecommendUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = newsService.getRecommendDelCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}

	}

	@RequestMapping(value = "/bannerlist", method = RequestMethod.GET)
	public void bannerlist(Model model, HttpSession session, HttpServletRequest request, String userGuid) throws UnsupportedEncodingException {
		/*JSONObject obj = new JSONObject();
		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("sign", Consts.TIM_AdminSig);
		String data = JSON.toJSONString(obj);
		String urlString = "";
		urlString = Consts.ServiceUrl + "getttempbanner";
		String ret = HttpClient.post(urlString, data);
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		System.out.println("[" + dateString + "]--ret=" + ret);
		JSONObject jsonObject = JSONObject.parseObject(ret);
		JSONArray jsonArray = jsonObject.getJSONArray("data");*/

		List<banner> banners =newsService.getBannerlist();
		/*if (jsonArray != null) {

			for (int i = 0; i < jsonArray.size(); i++) {
				String text = jsonArray.getJSONObject(i).toJSONString();
				banner banner = JSON.parseObject(text, banner.class);

				banners.add(banner);
			}
		}*/
		
		Collections.sort(banners,new Comparator<banner>() {

			@Override
			public int compare(banner o1, banner o2) {
				if (o1.getScore()>o2.getScore()) {
					return 1;
				}
				if (o1.getScore()==o2.getScore()) {
					return 0;
				}
				return -1;
			}
				
		});
		
		model.addAttribute("banners", banners);

		int updateCheck = newsService.getBannerUpdateCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = newsService.getBannerDelCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
	}

	@RequestMapping(value = "/addbanner", method = RequestMethod.POST)
	public void addbanner(Model model, HttpSession session, HttpServletRequest request,String boxid, String boxurl, String keywords, String type, PrintWriter out) throws UnsupportedEncodingException {
		banner banner = new banner();
		if (boxid!=null) {
			
			banner.setBoxid(boxid);
		}
		banner.setUrl(boxurl);
		banner.setKeywords(keywords);
		banner.setType(Integer.parseInt(type));
		newsService.addBanner(banner);
		out.print("success");
	}
	@RequestMapping(value = "/addbannerall", method = RequestMethod.POST)
	public void addbannerall(Model model, HttpSession session, HttpServletRequest request,
			String score, String title, String subtitle, String url,
			String boxid, String nid, String label, String keywords,
			String runid, String pid, String type, String imgurl,
			String state, String gpid,String bannertype, String secondcarid,String yzid, String buserid,String bannerid,String miniprogramid,
			PrintWriter out) throws UnsupportedEncodingException {
		
		banner banner = new banner();
		if(bannerid!=null && !"".equals(bannerid.trim())){
			banner.setBannerid(Long.valueOf(bannerid));
		}
		banner.setScore(Long.valueOf(score));
		banner.setTitle(title);
		banner.setSubtitle(subtitle);
		if(url!=null && !"".equals(url)){
			banner.setUrl(url);
		}
		if(boxid!=null && !"".equals(boxid)){
			banner.setBoxid(boxid);
		}
		if(nid!=null && !"".equals(nid)){
			banner.setNid(nid);
		}
		if(label!=null && !"".equals(label)){
			banner.setLabel(label);
		}
		if(keywords!=null && !"".equals(keywords)){
			banner.setKeywords(keywords);
		}
		if(runid!=null && !"".equals(runid)){
			banner.setRunid(runid);
		}
		if(pid!=null && !"".equals(pid)){
			banner.setPid(Integer.parseInt(pid));
		}
		if(gpid!=null && !"".equals(gpid)){
			banner.setGpid(Integer.parseInt(gpid));
		}
		banner.setImgurl(imgurl);
		banner.setState(Integer.parseInt(state));
		banner.setType(Integer.parseInt(type));
		
		
		banner.setBannertype(Integer.parseInt(bannertype));
		if(secondcarid!=null && !"".equals(secondcarid)){
			banner.setSecondcarid(secondcarid);
		}
		if(yzid!=null && !"".equals(yzid)){
			banner.setYzid(yzid);
		}
		if(buserid!=null && !"".equals(buserid)){
			banner.setBuserid(buserid);
		}
		if(miniprogramid!=null && !"".equals(miniprogramid)){
			banner.setMiniprogramid(miniprogramid);
		}
		newsService.addBannerall(banner);
		out.print("success");
	}

	@RequestMapping(value = "/delbanner", method = RequestMethod.POST)
	public void delBanner(Model model, HttpSession session, HttpServletRequest request, String bannerid, PrintWriter out) throws UnsupportedEncodingException {

		newsService.delBanner(bannerid);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

		out.print(admin.getUser_guid());
	}
	@RequestMapping(value = "/getbannerbyid", method = RequestMethod.POST)
	public void getbannerbyid(Model model, HttpSession session, HttpServletRequest request, String bannerid, PrintWriter out) throws UnsupportedEncodingException {
		
		banner banner =newsService.getbannerbyid(bannerid);
		String jsonString="";
		if(banner!=null){
			jsonString=JSON.toJSONString(banner);
		}
		
		out.print(jsonString);
	}

	@RequestMapping(value = "/updateTopicIsMain", method = RequestMethod.POST)
	@ResponseBody
	public String updateTopicIsMain(String topicid,int ismain) throws UnsupportedEncodingException {
		newsService.updateTopicIsMain(topicid,ismain==0?1:0);
		return "sucess";

	}

	@RequestMapping(value = "/addrecommendnews", method = RequestMethod.POST)
	public void addrecommendnews(Model model, HttpSession session, HttpServletRequest request,String userid, String nid, String score,String addtype,String styletype,String stylepic, PrintWriter out) throws IOException {

		
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		int styletypeint=0;
        if(styletype!=null && !"".equals(styletype)){
        	styletypeint=Integer.parseInt(styletype);
        }
        if(stylepic!=null && !"".equals(stylepic.trim())){
        	stylepic=stylepic.trim();
        }
		JSONObject obj = new JSONObject();
		obj.put("addtype",Integer.parseInt(addtype));
		obj.put("nid", nid);
		obj.put("score",score);
		obj.put("styletype", styletypeint);
		obj.put("stylepic",stylepic);
		obj.put("userid", Consts.TIM_AdminUserID);
//		String data = JSON.toJSONString(obj);
//		String urlString = Consts.ServiceUrl + "news/addrecommendnews";
//		
//		logger.info("addrecommendnews request data:[url:"+urlString+"  param:"+data+"]");
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("addrecommendnews response data:[result:"+result+"]");
//		JSONObject json = JSON.parseObject(result);
//		String str =(String) json.get("code");
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/operate/news/addrecommendnews", data);
		String resultstr = MbUtil.handleResp(responstr);
		String str ="1";
		if(StringUtils.isNotBlank(resultstr)){
			JSONObject json = JSON.parseObject(resultstr);
			str =(String) json.get("code");
		}
		if(Integer.parseInt(str)==0){
			out.write("success");
		}else{
			out.write("fail");
		}
	
	}

//	@RequestMapping(value = "/delrecommendnews", method = RequestMethod.POST)
//	public void delrecommendnews(Model model, HttpSession session, HttpServletRequest request, String nid,String score, PrintWriter out) throws UnsupportedEncodingException {
//		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, "news_temprecommendnews", nid);
//		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, "stroy_temprecommendnews", nid);
//		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
//		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "news_standby", Long.valueOf(score), nid);
//		out.print(admin.getUser_guid());
//
//	}

	@RequestMapping(value = "/delrecommendnews_new", method = RequestMethod.POST)
	public void delrecommendnews_new(Model model, HttpSession session, HttpServletRequest request, String nid,String score, PrintWriter out) throws IOException {
		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news", nid);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommend", "0");
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommendtime","0");
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommenduserid","");
		newsModel newsModel =new newsModel();
		newsModel.setRecommend(0);
		newsModel.setRecommendtime(0l);
		newsModel.setRecommenduserid("");
		newsModel.setNid(nid);
		newsService.updatenewsByNid(newsModel);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommenduserid","");
		
		
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());

	}
	

	@RequestMapping(value = "/addTopic", method = RequestMethod.POST)
	public void addTopic(Model model, HttpSession session, HttpServletRequest request, String id, String title, String pic, String keyword, String content, String status, String orderindex,String topictype,String userid,String pubtime,String picwidth,String picheight,String pid,String businessuserid,  PrintWriter out) throws  Exception {
		topic topic = new topic();
		if (!id.equals("")) {
			topic.setTopicid(Integer.parseInt(id));
		}
		topic.setTitle(title);
		topic.setPic(pic);
		topic.setKeyword(keyword);
		topic.setContent(content);
		topic.setStatus(Integer.parseInt(status));
		topic.setOrderindex(Long.valueOf(orderindex));
		topic.setTopictype(Integer.parseInt(topictype));
		if(keyword==null||keyword==""){
			topic.setKeyword(MbUtil.getUUID());
		}
		if(Integer.parseInt(topictype)==1){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			topic.setUserid(userid);
			topic.setPubtime(formatter.parse(pubtime).getTime());
			topic.setPicheight(Integer.parseInt(picheight));
			topic.setPicwidth(Integer.parseInt(picwidth));
			
			
		}else{
			topic.setUserid(null);
			topic.setPubtime(0);
			topic.setPicheight(0);
			topic.setPicwidth(0);
		}
		if(pid!=null&& !"".equals(pid)){
			topic.setPid(pid);
		}else{
			topic.setPid(null);
		}
		if(businessuserid!=null&& !"".equals(businessuserid)){
			topic.setBusinessuserid(businessuserid);
		}else{
			topic.setBusinessuserid(null);
		}
		newsService.insertOrUpdateTopic(topic);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

		out.print(admin.getUser_guid());

	}
	
	@RequestMapping(value = "/changekeywords", method = RequestMethod.POST)
	public void changekeywords(Model model, HttpSession session, HttpServletRequest request, String nid, String keyword1, String keyword2,String keywords, PrintWriter out) throws UnsupportedEncodingException {
		double newsscore=0;
		double newshot=0;
		if (keywords != null && !keywords.equals("")) {
			String[] keywordStrings = keywords.split(",");
			for (int i = 0; i < keywordStrings.length; i++) {
				String usertopicname = keywordStrings[i];
			    newsscore=	RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, usertopicname + "_topic", nid);
				newshot=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, usertopicname + "_hot", nid);
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, usertopicname+"_topic", nid);
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, usertopicname+"_hot", nid);
			}
		}
		String newkeywordString="";
		if (keyword1!=null&&!keyword1.equals("")) {
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, keyword1+"_topic", Math.round(newsscore), nid);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, keyword1+"_hot", Math.round(newshot), nid);
			newkeywordString+=keyword1;
		}
		if (keyword2!=null&&!keyword2.equals("")) {
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, keyword2+"_topic", Math.round(newsscore), nid);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, keyword2+"_hot", Math.round(newshot), nid);
			newkeywordString+=","+keyword2;
		}
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo", "keywords", newkeywordString);
		out.print("success");
		
	}
	
	@RequestMapping(value = "/moment", method = RequestMethod.GET)
	public void moment(Model model, HttpSession session, HttpServletRequest request) throws IOException {
		
		JSONObject obj = new JSONObject();
		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("pagetype", 0);
		obj.put("pagesize", 1);
		
//		String data = JSON.toJSONString(obj);
//
//		String urlString = Consts.ServiceUrl + "news/refreshmomentnews";
//		logger.info("moment request data:[url:"+urlString+"  param:"+data+"]");
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("moment response data:[result:"+result+"]");
		
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/news/getmomentnewslist", data);
		String resultstr = MbUtil.handleResp(responstr);
	
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		JSONObject json = JSON.parseObject(resultstr);
		JSONArray jsonArray = json.getJSONArray("data");
		if(jsonArray !=null && jsonArray.size()>0){
		//System.out.println("-----------" + jsonArray.getJSONObject(0).getString("nid"));
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		String ptime=jsonObject.getString("ptime");
		String ptimeString=formatter.format(new Date(Long.valueOf(ptime)));
		jsonObject.put("ptimeString", ptimeString);
		newsModel news=JSONObject.parseObject(jsonObject.toJSONString(),newsModel.class);
		
		String temptopicnews = jsonObject.getString("nid");
		
		/*AchievementModel achievementModel=motouserService.getachievement(Integer.toString(news.getAchievementmodel().getAchid()));
		news.getAchievementmodel().setAchname(achievementModel.getAch_name());
		news.setRecommendtimeString(formatter.format(new Date(news.getRecommendtime())));*/
		
		model.addAttribute("temptopicnews", temptopicnews);
		model.addAttribute("tempnews",news);
		}
//		urlString = Consts.ServiceUrl + "news/refreshmomentnews";
//		logger.info("moment request data:[url:"+urlString+"  param:"+data+"]");
//		body = RequestBody.create(MediaType_JSON, data);
//		req = new Request.Builder().url(urlString).post(body).build();
//		resp=httpClient.newCall(req).execute();
//		result =new String(resp.body().bytes(), "utf-8");
//		logger.info("moment response data:[result:"+result+"]");
//		//System.out.println("[" + dateString + "]--ret=" + ret);
		 responstr = MbUtil.handleReqAndSendReq("/news/getmomentnewslist", data);
		 resultstr = MbUtil.handleResp(responstr);
		
		
		json = JSON.parseObject(resultstr);
		jsonArray = json.getJSONArray("data");
		if(jsonArray !=null && jsonArray.size()>0){
		//System.out.println("-----------" + jsonArray.getJSONObject(0).getString("nid"));
			JSONObject jsonObject = jsonArray.getJSONObject(0);
		String ptime=jsonObject.getString("ptime");
		String ptimeString=formatter.format(new Date(Long.valueOf(ptime)));
		jsonObject.put("ptimeString", ptimeString);
		newsModel news1=JSONObject.parseObject(jsonObject.toJSONString(), newsModel.class);
		String temptopichot = jsonObject.getString("nid");
		
	/*	AchievementModel achievementModel=motouserService.getachievement(Integer.toString(news1.getAchievementmodel().getAchid()));
		news1.getAchievementmodel().setAchname(achievementModel.getAch_name());
		news1.setRecommendtimeString(formatter.format(new Date(news1.getRecommendtime())));*/
		
		model.addAttribute("temptopichot", temptopichot);
		model.addAttribute("temphots",news1);
		}

	}
	
	@RequestMapping(value = "/storyrecommend", method = RequestMethod.GET)
	public void storyrecommend(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order) throws UnsupportedEncodingException {
	
		List<newsModel> newsModels_new=new ArrayList<newsModel>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		PageBean<newsModel> pageBean=new PageBean<newsModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "story_recommend")){
			totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "story_recommend");
		}
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		/*int start=0;
		int forSize=0;
		if(order==1){
			start= totalCount-1-(page-1)*limit;
			if(start>=limit){
			   forSize=start-limit+1;
			}else{
			  forSize=0;
			}
		}else{
			start= (page-1)*limit;
			int lastPageLength=0;
			if(totalCount-start<limit){
				lastPageLength=totalCount-start;
			}else{
				lastPageLength=limit;
			}
			forSize=start+lastPageLength;
		}*/
		
		Set<String> temprecommendSet= new HashSet<String>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "story_recommend")){
			long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "story_recommend");
			if(order==1){
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "story_recommend",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "story_recommend",(page-1)*limit, page*limit-1);
				}
				
			}else{
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "story_recommend",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "story_recommend",(page-1)*limit, page*limit-1);
				}
				
			}
			List<newsModel> newsModels=getNewInfoListFromIdSet(temprecommendSet);
			for(newsModel newsModel : newsModels){
				String nid=newsModel.getNid();
				double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, "story_recommend", nid);
				newsModel.setScore(Math.round(score));
				newsModel.setPtimeString(formatter.format(new Date(newsModel.getPtime())));
				if(newsModel.getUpdatetime()!=null){
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getUpdatetime())));
				}else{
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getPtime())));
				}
				
				newsModels_new.add(newsModel);
			}
			
		}

	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("newsModels", newsModels_new);

		int updateCheck = newsService.getStoryRecommendUpdCheck(userGuid);

		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		int insCheck = newsService.getStoryRecommendDelCheck(userGuid);
		if (insCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}
	}
//	@RequestMapping(value = "/addstoryrecommendnews", method = RequestMethod.POST)
//	public void addstoryrecommendnews(Model model, HttpSession session, HttpServletRequest request, String nid, String score, PrintWriter out) throws UnsupportedEncodingException {
//		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
//			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "stroy_temprecommendnews",Long.valueOf(score), nid);
//			
//			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
//
//			out.print(admin.getUser_guid());
//		}
//		
//
//	}
	
	@RequestMapping(value = "/addstoryrecommendnews_new", method = RequestMethod.POST)
	public void addstoryrecommendnews_new(Model model, HttpSession session, HttpServletRequest request, String nid, String score, PrintWriter out) throws UnsupportedEncodingException {
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory",Long.valueOf(score), nid);
			
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

			out.print(admin.getUser_guid());
		}
		

	}
	
//	@RequestMapping(value = "/delStoryrecommendnews", method = RequestMethod.POST)
//	public void delStoryrecommendnews(Model model, HttpSession session, HttpServletRequest request, String nid,String score, PrintWriter out) throws UnsupportedEncodingException {
//		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, "news_temprecommendnews", nid);
//		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, "stroy_temprecommendnews", nid);	
//		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
//		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "news_standby", Long.valueOf(score), nid);
//		out.print(admin.getUser_guid());
//
//	}
	
	@RequestMapping(value = "/delStoryrecommendnews_new", method = RequestMethod.POST)
	public void delStoryrecommendnews_new(Model model, HttpSession session, HttpServletRequest request, String nid,String score, PrintWriter out) throws IOException {
		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory", nid);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommend", "0");
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommendtime","0");
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommenduserid","");
		newsModel newsModel =new newsModel();
		newsModel.setRecommend(0);
		newsModel.setRecommendtime(0l);
		newsModel.setRecommenduserid("");
		newsModel.setNid(nid);
		newsService.updatenewsByNid(newsModel);
		
		
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());

	}

	
	
//	@RequestMapping(value = "/saverecommendnews", method = RequestMethod.POST)
//	public void saverecommendnews(Model model, HttpSession session, HttpServletRequest request, String nid, String score, PrintWriter out) throws UnsupportedEncodingException {
//		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
//			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "news_temprecommendnews", Long.valueOf(score), nid);
//			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommend", "1");
//			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
//
//			out.print(admin.getUser_guid());
//		}
//	}
	
	@RequestMapping(value = "/saverecommendnews_new", method = RequestMethod.POST)
	public void saverecommendnews_new(Model model, HttpSession session, HttpServletRequest request, String nid, String score, PrintWriter out) throws UnsupportedEncodingException {
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news", Long.valueOf(score), nid);
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","recommend", "1");
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

			out.print(admin.getUser_guid());
		}
	}
	
	@RequestMapping(value = "/motouserIndividualNews", method = RequestMethod.GET)
	public void motouserIndividualNews(Model model, HttpSession session, HttpServletRequest request, String targetid) throws UnsupportedEncodingException {
		Set<String> tempset = new HashSet<String>();
		List<newsModel> newsModels = new ArrayList<newsModel>();
		
		if(targetid !=null && ! "".equals(targetid)){
		    long count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,targetid+"_unlist");
		    if(count>0){
			   tempset=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, targetid+"_unlist", 0, 0);
			 //  newsModels=getNewInfoListFromIdSet(tempset);
			   motobox motobox1 = null;
				for (String nid : tempset) {
                    if(nid.startsWith("box")){
                    	Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo");
                    	if (map != null && !map.isEmpty()) {
                			motobox box = motobox.convertToMotobox(map);
                			motobox1= boxService.getMotoBoxMessageByBoxid(String.valueOf(box.getBoxid()));
                			newsModel newsModel = new newsModel();
                			if(motobox1!=null){
                				newsModel.setMotoboxmodel(motobox1);
                			}else{
                				newsModel.setMotoboxmodel(new motobox());
                			}
                			
                			newsModel.setNid(nid);
                			newsModel.setPtime(box.getPtime());
                			newsModel.setUpdatetime(box.getUpdatetime());
                			newsModel.setCategoryid(box.getCategoryid());
                			newsModel.setType(8);
                			newsModel.setUserid(targetid);
                			newsModels.add(newsModel);
                		} 
                    }else{
                    	newsModel newsModel = this.getNewsInfo(nid);

    					if (newsModel != null) {
    						if(newsModel.getType()==10){
    							if(newsModel.getTopicmodel()!=null){
    								newsModel.setContent(newsModel.getContent()+": "+newsModel.getTopicmodel().getTitle()+"==>"+newsModel.getTopicmodel().getContent());
    							}
    						}
    						newsModels.add(newsModel);
    					}
                    }
					
				}
		    }
	     }
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		newsModel news=null;
		newsModel news1=null;
		String temptopicnews="";
		String temptopichot="";
	if(newsModels!=null && newsModels.size()>0){
		        newsModel  newsModel=newsModels.get(0);
				String nid=newsModel.getNid();
				double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS,targetid+"_unlist", nid);
				newsModel.setScore(Math.round(score));
				newsModel.setPtimeString(formatter.format(new Date(newsModel.getPtime())));
				if(newsModel.getUpdatetime()!=null){
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getUpdatetime())));
				}else{
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getPtime())));
				}
				
				news=newsModel;
				temptopicnews = news.getNid();
				news1=newsModel;
				temptopichot= news1.getNid();
					
	}
	model.addAttribute("targetid",targetid);
	model.addAttribute("temptopicnews", temptopicnews);
	model.addAttribute("tempnews",news);
	
	model.addAttribute("temptopichot", temptopichot);
	model.addAttribute("temphots",news1);

	}
	@RequestMapping(value = "/getmoreMotouserIndividualNews", method = RequestMethod.POST)
	public void getmoreMotouserIndividualNews(Model model, HttpSession session, HttpServletRequest request, String targetid,String param_nid,PrintWriter out) throws UnsupportedEncodingException {
		Set<String> tempset = new HashSet<String>();
		List<newsModel> newsModels = new ArrayList<newsModel>();
		if(targetid !=null && ! "".equals(targetid)){
			long count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,targetid+"_unlist");
			if(count>0 && param_nid!=null && !"".equals(param_nid) ){
				long index=RedisManager.getInstance().zrevrank(Consts.REDIS_SCHEME_NEWS,targetid+"_unlist",param_nid);
				if(index+10>count){
					tempset=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, targetid+"_unlist", index+1, count);
				}else{
					tempset=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, targetid+"_unlist", index+1, index+10);
				}
				//newsModels=getNewInfoListFromIdSet(tempset);
				 motobox motobox1 = null;
				for (String nid : tempset) {
                    if(nid.startsWith("box")){
                    	Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo");
                    	if (map != null && !map.isEmpty()) {
                			motobox box = motobox.convertToMotobox(map);
                		    motobox1= boxService.getMotoBoxMessageByBoxid(String.valueOf(box.getBoxid()));
                			newsModel newsModel = new newsModel();
                			
                			if(motobox1!=null){
                				newsModel.setMotoboxmodel(motobox1);
                			}else{
                				newsModel.setMotoboxmodel(new motobox());
                			}
                			newsModel.setNid(nid);
                			newsModel.setPtime(box.getPtime());
                			newsModel.setUpdatetime(box.getUpdatetime());
                			newsModel.setCategoryid(box.getCategoryid());
                			newsModel.setType(8);
                			newsModel.setUserid(targetid);
                			newsModels.add(newsModel);
                		} 
                    }else{
                    	newsModel newsModel = this.getNewsInfo(nid);

    					if (newsModel != null) {
    						newsModels.add(newsModel);
    					}
                    }
					
				}
			}
		
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String jsonString = "";
	if(newsModels!=null && newsModels.size()>0){
		for(int i=0 ; i<newsModels.size();i++){
			    newsModel newsModel =newsModels.get(i);
				String nid=newsModel.getNid();
				double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS,targetid+"_unlist", nid);
				newsModel.setScore(Math.round(score));
				newsModel.setPtimeString(formatter.format(new Date(newsModel.getPtime())));
				if(newsModel.getUpdatetime()==null){
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getPtime())));		
				}else{
					newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getUpdatetime())));		
				}
						
				
		}
		jsonString =JSONObject.toJSONString(newsModels);
	}

	   out.write(jsonString);

	}
	
	
	/**
	 * 查看动态详情
	* <p>Method:newsDynamic </p>
	* <p>Description: </p>
	* <p>Return Type: void</p>
	* @author fanghebin
	* @date 2016年12月30日 下午12:24:41
	 */
	@RequestMapping(value = "/newsDynamic", method = RequestMethod.GET)
	public void newsDynamic(Model model, HttpSession session, HttpServletRequest request, String userGuid,String nid) {
		Map<String, String> ninfomap=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, nid+NEWSKEY_INFO);
		newsModel news=new newsModel();
		news.setContent(ninfomap.get("content"));
		news.setLocation(ninfomap.get("location"));
		news.setNid(nid);
		news.setPicurl(ninfomap.get("picurl"));
		Date d = new Date(Long.valueOf(ninfomap.get("ptime")));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String ptimeString=sdf.format(d);
		//news.setPtime(Long.valueOf(ninfomap.get("ptime")));
		String userid=ninfomap.get("userid");
		news.setUserid(userid);
		boolean islocation=true;
		if (news.getLocation()==null||news.getLocation().equals("")||news.getLocation().isEmpty()) {
			islocation=false;
		}

		String headurl="";
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid+NEWSKEY_USER)) {
			headurl=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid+NEWSKEY_USER, "headurl");
		}
		if ("".equals(headurl)||headurl==null) {
			headurl="http://libres-10013836.file.myqcloud.com/head_icon%402x.png";//默认头像
		}
		news.setHeadurl(headurl);
		String nickname="";
		if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid+NEWSKEY_USER)) {
			
			nickname=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid+NEWSKEY_USER, "nickname");
		}
		news.setNickname(nickname);
		if (nickname.equals("")) {
			Random rand = new Random();
			nickname="MotoBand+" + (rand.nextInt(5000000) + 1000000);
		}
		int type=Integer.valueOf(ninfomap.get("type"));
		List<newsModel> newsList=new ArrayList<newsModel>();
		if (type!=4) {
			newsModel newsModel1=new newsModel();
			newsModel1.setPicurl(ninfomap.get("picurl"));
			if(type==10){
				if (!ninfomap.get("topicmodel").isEmpty()) {
					newsModel1.setTopicmodel(JSON.parseObject(ninfomap.get("topicmodel"), topic.class));
					newsModel1.setContent(ninfomap.get("content")+": "+newsModel1.getTopicmodel().getTitle()+"==>"+newsModel1.getTopicmodel().getContent());
				}
				
			}else{
				newsModel1.setContent(ninfomap.get("content"));
			}
			newsModel1.setType(Integer.valueOf(ninfomap.get("type")));
			JSONObject jsonObject=JSON.parseObject(ninfomap.get("videodatamodel"));
			if (Integer.valueOf(ninfomap.get("type"))==1) {
				
				newsModel1.setVideourl(jsonObject.getString("videoUrl"));
			}
			newsList.add(newsModel1);
		}else {
			news.title=ninfomap.get("title");
			Date d1 = new Date(Long.valueOf(ninfomap.get("updatetime")));
			news.newsupdatetime="更新     "+sdf.format(d1);
			JSONArray jsonArray=JSONArray.parseArray(ninfomap.get("storylist"));
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject=jsonArray.getJSONObject(i);
				newsModel newsModel=new newsModel();
				newsModel.setPicurl(jsonObject.getString("picurl"));
				newsModel.setContent(jsonObject.getString("content"));
				newsModel.setType(Integer.valueOf(jsonObject.getString("type")));
				if (Integer.valueOf(jsonObject.getString("type"))==1) {
					
					JSONObject videoJsonObject=jsonObject.getJSONObject("videodatamodel");
					newsModel.setVideourl(videoJsonObject.getString("videoUrl"));
				}
				newsList.add(newsModel);
			}
		}
		List<CommentModel> commentlist = new ArrayList<CommentModel>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist")){
			long clistCount = RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist");
			if(clistCount>0){
				Set<String> clist=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist", 0, clistCount);
				if(clist!=null && clist.size()>0){
					for(String s:clist){
						if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, s+"_ncommentmap")){
							Map<String,String> cmap=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, s+"_ncommentmap");
							if(cmap!=null && cmap.size()>0){
								CommentModel commentModel = CommentModel.convertToCommentModel(cmap);
								commentModel.setCtimeString(sdf.format(new Date(Long.parseLong(commentModel.getCtime()))));
								if(commentModel.getTuserid()!=null && !"".equals(commentModel.getTuserid())){
									MBUserModel tmb= motouserService.getUserByID(commentModel.getTuserid());
									if (tmb != null && (tmb.nickname == null || tmb.nickname.isEmpty())) {

										Random rand = new Random();
										tmb.nickname = "MotoBand+" + (rand.nextInt(5000000) + 1000000);
										
									}
									commentModel.setTusernickname(tmb.getNickname());
									if(tmb.getHeadurl()==null || tmb.getHeadurl().equals("")){
										commentModel.setTuserHeadurl("http://libres-10013836.file.myqcloud.com/head_icon%402x.png");
									}else{
										commentModel.setTuserHeadurl(tmb.getHeadurl());
									}
									
								}
								MBUserModel mb=motouserService.getUserByID(commentModel.getUserid());
								if (mb != null && (mb.nickname == null || mb.nickname.isEmpty())) {

									Random rand = new Random();
									mb.nickname = "MotoBand+" + (rand.nextInt(5000000) + 1000000);
									
								}
								commentModel.setUsernickname(mb.getNickname());
								if(mb.getHeadurl()==null || mb.getHeadurl().equals("")){
									commentModel.setUserHeadurl("http://libres-10013836.file.myqcloud.com/head_icon%402x.png");
								}else{
									commentModel.setUserHeadurl(mb.getHeadurl());
								}
								
								commentlist.add(commentModel);
							}
						}
					}
				}
			}
			
		}
		
		
		news.lcount=ninfomap.get("lcount")==null? 0:Long.valueOf(ninfomap.get("lcount"));
		news.ccount=ninfomap.get("ccount")==null? 0:Long.valueOf(ninfomap.get("ccount"));
		String labelString=ninfomap.get("labels")==null? "":ninfomap.get("labels");
		labelString="#"+labelString.replace(",", "#&nbsp;&nbsp#")+"#";
		news.setLabels(labelString);
		model.addAttribute("stroylist",newsList);
		model.addAttribute("islocation", islocation);
		model.addAttribute("news", news);
		model.addAttribute("ptimeString",ptimeString);
		model.addAttribute("commentlist",commentlist);
	}
   /**
    * 删除动态评论
   * <p>Method:deletenewscomment </p>
   * <p>Description: </p>
   * <p>Return Type: void</p>
   * @author fanghebin
 * @throws IOException 
   * @date 2016年12月30日 下午12:29:02
    */
	@RequestMapping(value = "/deletenewscomment", method = RequestMethod.POST)
	public void deletenewscomment(Model model, HttpSession session, HttpServletRequest request,String cid,String nid,PrintWriter out) throws IOException {
//		long i=0;
//		long j=0;
//		double score=0;
//		Map<String,String> map =new HashMap<String, String>();
//		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, cid+"_ncommentmap")){
//			map =RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, cid+"_ncommentmap");
//			i=RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_NEWS, cid+"_ncommentmap");
//			
//		}
//		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist")){
//			long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist");
//			if(count>0){
//				Set<String> setStr =RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist", 0, count);
//				for (String s :setStr){
//					if(s.equals(cid)){
//						score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist", cid);
//						j=RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist", s);
//					}
//				}
//			}
//			
//		}
//		
//		if(i!=0 && j!=0){
//			newsService.deleteNewsCommentByCid(cid);
//			out.write("success");
//		}else if(i!=0 && j==0){
//			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_NEWS, cid+"_ncommentmap", map);
//		}else if(i==0 && j!=0){
//			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, nid+"_ncommentlist", Double.doubleToLongBits(score),cid);
//		}
		String userid="";
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,nid+"_ninfo")){
			userid=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS,nid+"_ninfo", "userid");
			  if(userid!=null && !"".equals(userid)){
			    JSONObject obj = new JSONObject();
				obj.put("actiontype", 1);//0 普通用户 1 管理员
				obj.put("userid", userid);
				obj.put("nid",nid);
				obj.put("cid", cid);
//				String data = JSON.toJSONString(obj);
//				String urlString = Consts.ServiceUrl + "news/delnewscomments";
//				logger.info("deletenewscomment  request data:[url:"+urlString+"  param:"+data+"]");
//				
//				RequestBody body = RequestBody.create(MediaType_JSON, data);
//				Request req = new Request.Builder().url(urlString).post(body).build();
//				Response resp=httpClient.newCall(req).execute();
//				String result =new String(resp.body().bytes(), "utf-8");
//				logger.info("deletenewscomment  response data:[result:"+result+"]");
//				JSONObject json = JSON.parseObject(result);
//				String str =(String) json.get("code");
				obj.put("token", "token");
				obj.put("requestid", "12345678");
				obj.put("cversion", "1");
				obj.put("ctype", "1");
				String data = JSON.toJSONString(obj);
				String responstr = MbUtil.handleReqAndSendReq("/operate/news/delnews", data);
				String resultstr = MbUtil.handleResp(responstr);
				String str ="1";
				if(StringUtils.isNotBlank(resultstr)){
					JSONObject json = JSON.parseObject(resultstr);
					str =(String) json.get("code");
				}
				if(Integer.parseInt(str)==0){
					out.write("success");
				}else{
					out.write("fail");
				}
		     }
		}else{
			out.write("fail");
		}
	}
	/**
	 *  新精选列表
	* <p>Method:recommendnewslist_new </p>
	* <p>Description: </p>
	* <p>Return Type: void</p>
	* @author fanghebin
	* @date 2016年12月13日 下午8:55:17
	 */
	@RequestMapping(value = "/recommendnewslist_new", method = RequestMethod.GET)
	public void recommendnewslist_new(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order) throws UnsupportedEncodingException {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		PageBean<RecommendNewsModel> pageBean=new PageBean<RecommendNewsModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news")){
			totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news");
		}
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 

		List<RecommendNewsModel> recommendNewsModels = new ArrayList<RecommendNewsModel>();
		
		Set<String> temprecommendSet= new HashSet<String>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news")){
			long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news");
			if(order==1){
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news",(page-1)*limit, page*limit-1);
				}
				
			}else{
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news",(page-1)*limit, page*limit-1);
				}
				
			}
			for(String s: temprecommendSet){
				if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, s+"_ninfo")){
					Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, s+"_ninfo");
					if(map!=null && map.size()>0){
						RecommendNewsModel recommendNewsModel=RecommendNewsModel.convertToCommentModel(map);
						double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_news", s);
						recommendNewsModel.setScore(Math.round(score));
						recommendNewsModel.ptimeString=formatter.format(new Date(recommendNewsModel.ptime));
	    				if (recommendNewsModel.updatetime!=0) {
	    					recommendNewsModel.updatetimeString=formatter.format(new Date(recommendNewsModel.updatetime));
	    				}
	    				recommendNewsModels.add(recommendNewsModel);
					}
				}
			}
		}
	
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		
		model.addAttribute("recommendNewsModels", recommendNewsModels);

	
	}
   /**
    * 重内容列表
   * <p>Method:recommendnewslist_heavy </p>
   * <p>Description: </p>
   * <p>Return Type: void</p>
   * @author fanghebin
   * @date 2016年12月13日 下午8:56:09
    */
	@RequestMapping(value = "/recommendnewslist_heavy", method = RequestMethod.GET)
	public void recommendnewslist_heavy(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order) throws UnsupportedEncodingException {
	
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		PageBean<RecommendNewsModel> pageBean=new PageBean<RecommendNewsModel>();
		if(page==0){
			page=1;
		}
        if(limit==0){
        	limit=20;
        }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory")){
			totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory");
		}
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 

		List<RecommendNewsModel> recommendNewsModels = new ArrayList<RecommendNewsModel>();
		
		Set<String> temprecommendSet= new HashSet<String>();
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory")){
			long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory");
			if(order==1){
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory",(page-1)*limit, page*limit-1);
				}
				
			}else{
				if(limit>count){
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory",0, count);
				}else{
					temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory",(page-1)*limit, page*limit-1);
				}
				
			}
			for(String s: temprecommendSet){
				if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, s+"_ninfo")){
					Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, s+"_ninfo");
					if(map!=null && map.size()>0){
						RecommendNewsModel recommendNewsModel=RecommendNewsModel.convertToCommentModel(map);
						double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, "news_motobandpage_boxandstory", s);
						recommendNewsModel.setScore(Math.round(score));
						recommendNewsModel.ptimeString=formatter.format(new Date(recommendNewsModel.ptime));
	    				if (recommendNewsModel.updatetime!=0) {
	    					recommendNewsModel.updatetimeString=formatter.format(new Date(recommendNewsModel.updatetime));
	    				}
	    				recommendNewsModels.add(recommendNewsModel);
					}
				}
			}
		}
	
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		
		model.addAttribute("newsModels", recommendNewsModels);

		

	}
	@RequestMapping(value = "/newscategorylist", method = RequestMethod.GET)
	public void newscategorylist(Model model, HttpSession session, HttpServletRequest request, String userGuid) throws UnsupportedEncodingException {

		List<NewscategoryModel> newscategoryModels = newsService.getNewscategory();
		
		model.addAttribute("newscategorylist", newscategoryModels);

	}
	/**
	 * 单个动态分类列表加载
	* <p>Method:newscategoryInfo </p>
	* <p>Description: </p>
	* <p>Return Type: void</p>
	* @author fanghebin
	 * @throws IOException 
	* @date 2016年12月30日 下午12:22:18
	 */
	@RequestMapping(value = "/newscategoryInfo", method = RequestMethod.GET)
	public void newscategoryInfo(Model model, HttpSession session, HttpServletRequest request, String categoryid) throws IOException {

		JSONObject obj = new JSONObject();
		obj.put("userid", Consts.TIM_AdminUserID);
		obj.put("categoryid", categoryid);
//		String data = JSON.toJSONString(obj);
//
//		String urlString = Consts.ServiceUrl + "news/refreshnewsbycategory";
//		logger.info("newscategoryInfo request data:[url:"+urlString+"  param:"+data+"]");
//		RequestBody body = RequestBody.create(MediaType_JSON, data);
//		Request req = new Request.Builder().url(urlString).post(body).build();
//		Response resp=httpClient.newCall(req).execute();
//		String result =new String(resp.body().bytes(), "utf-8");
//		logger.info("newscategoryInfo  response data:[result:"+result+"]");
		obj.put("pagetype", 0);
		obj.put("pagesize", 1);
		obj.put("token", "token");
		obj.put("requestid", "12345678");
		obj.put("cversion", "1");
		obj.put("ctype", "1");
		String data = JSON.toJSONString(obj);
		String responstr = MbUtil.handleReqAndSendReq("/news/getcategoryrecommendlist", data);
		String resultstr = MbUtil.handleResp(responstr);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		JSONObject json = JSON.parseObject(resultstr);
		JSONArray jsonArray = json.getJSONArray("data");
		if(jsonArray !=null && jsonArray.size()>0){
		JSONObject jsonObject =jsonArray.getJSONObject(0);
		newsModel news=JSONObject.parseObject(jsonObject.toJSONString(),newsModel.class);
		if(news.getType()==8 && news.getMotoboxmodel()==null ){
			news.setMotoboxmodel(new motobox());
		}
		
		String temptopicnews = jsonObject.getString("nid");
		model.addAttribute("temptopicnews",temptopicnews);
		//model.addAttribute("ptimeString",formatter.format(new Date(news.getPtime())));
		String ptimeString =formatter.format(new Date(news.getPtime()));
		news.setPtimeString(ptimeString);
		model.addAttribute("tempnews",news);
		}
//		urlString = Consts.ServiceUrl + "news/refreshcategoryrecommend";
//		logger.info("newscategoryInfo  request data:[url:"+urlString+"  param:"+data+"]");
//		body = RequestBody.create(MediaType_JSON, data);
//		req = new Request.Builder().url(urlString).post(body).build();
//		resp=httpClient.newCall(req).execute();
//		result =new String(resp.body().bytes(), "utf-8");
//		logger.info("newscategoryInfo  response data:[result:"+result+"]");
		 responstr = MbUtil.handleReqAndSendReq("/news/getcategoryrecommendlist", data);
		 resultstr = MbUtil.handleResp(responstr);
		
		json = JSON.parseObject(resultstr);
		jsonArray = json.getJSONArray("data");
		String temptopichot=null;
		newsModel news1=null;
		if (jsonArray!=null) {
		//	System.out.println("-----------" + jsonArray.getJSONObject(0).getString("nid"));
			JSONObject jsonObject =jsonArray.getJSONObject(0);
			news1=JSONObject.parseObject(jsonObject.toJSONString(), newsModel.class);
			temptopichot= jsonObject.getString("nid");
			
		}
		model.addAttribute("temptopichot", temptopichot);
		if (news1!=null) {
			
			//model.addAttribute("ptimeString",formatter.format(new Date(news1.getPtime())));
			String ptimeString =formatter.format(new Date(news1.getPtime()));
			news1.setPtimeString(ptimeString);
		}else {
			//model.addAttribute("ptimeString",formatter.format(0));
			
		}
		if(news1!=null && news1.getType()==8 && news1.getMotoboxmodel()==null ){
			news1.setMotoboxmodel(new motobox());
		}
		model.addAttribute("temphots",news1);
		
		model.addAttribute("categoryid",categoryid);
		
	}
	
/**
 * 修改动态分类
* <p>Method:updateNewsCategory </p>
* <p>Description: </p>
* <p>Return Type: void</p>
* @author fanghebin
 * @throws IOException 
* @date 2016年12月30日 下午12:21:28
 */
@RequestMapping(value = "/updateNewsCategory", method = RequestMethod.POST)
public void updateNewsCategory(Model model, HttpSession session, HttpServletRequest request, String nid, String categoryid, String score,PrintWriter out) throws IOException {
		String old_categoryid = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", "categoryid");
		String updatetime = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", "updatetime");
		String ptime = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", "ptime");
		if(updatetime==null || updatetime==""){
			updatetime=ptime;
		}
		String recommend=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", "recommend");
		if (recommend!=null && recommend!="" && "1".equals(recommend)) {
			if (old_categoryid != null && old_categoryid != "") {
				if (Integer.parseInt(old_categoryid) > 0) {
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,old_categoryid + "_category", nid);
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,old_categoryid + "_categoryrecommend", nid);
				}
			}
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS,nid + "_ninfo", "categoryid", categoryid);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS,categoryid + "_category", Long.valueOf(updatetime), nid);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS,categoryid + "_categoryrecommend", Long.valueOf(updatetime), nid);
		} else {
			if (old_categoryid != null && old_categoryid != "") {
				if (Integer.parseInt(old_categoryid) > 0) {
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,old_categoryid + "_category", nid);
				}
			}
			RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS,nid + "_ninfo", "categoryid", categoryid);
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS,categoryid + "_category", Long.valueOf(updatetime), nid);
		}
		//修改数据库
		newsModel newsModel =new newsModel();
		newsModel.setCategoryid(Integer.parseInt(categoryid));
		newsModel.setNid(nid);
		newsService.updatenewsByNid(newsModel);
		
		
	    out.write("success");
}
/**
 * 从动态分类里移除
* <p>Method:deleteFromCategory </p>
* <p>Description: </p>
* <p>Return Type: void</p>
* @author fanghebin
 * @throws IOException 
* @date 2016年12月30日 下午12:20:20
 */
@RequestMapping(value = "/deleteFromCategory", method = RequestMethod.POST)
public void deleteFromCategory(Model model, HttpSession session, HttpServletRequest request, String nid, String categoryid, String score,PrintWriter out) throws IOException {
	String  old_categoryid=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS,nid+"_ninfo",  "categoryid");
	String recommend=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid + "_ninfo", "recommend");
	if (recommend!=null && recommend!="" && "1".equals(recommend)) {
		if(old_categoryid!=null && old_categoryid!=""){
			if(Integer.parseInt(old_categoryid)>0){
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, old_categoryid+"_category", nid);
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, old_categoryid+"_categoryrecommend", nid);
			}
		}
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS,nid+"_ninfo", "categoryid", "0");
	}else{
		if(old_categoryid!=null && old_categoryid!=""){
			if(Integer.parseInt(old_categoryid)>0){
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS, old_categoryid+"_category", nid);
			}
		}
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS,nid+"_ninfo", "categoryid", "0");
	}
	
	//修改数据库
	newsModel newsModel =new newsModel();
	newsModel.setCategoryid(0);
	newsModel.setNid(nid);
	newsService.updatenewsByNid(newsModel);
	
	
	out.write("success");
}


@RequestMapping(value = "/newsrecommendcategorylist", method = RequestMethod.GET)
public void newsrecommendcategorylist(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order,String categoryid) throws UnsupportedEncodingException {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	PageBean<RecommendNewsModel> pageBean=new PageBean<RecommendNewsModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=0;
	if(categoryid==null || "".equals(categoryid)){
		categoryid="1";
	}
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend")){
		totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend");
	}
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 

	List<RecommendNewsModel> recommendNewsModels = new ArrayList<RecommendNewsModel>();
	
	Set<String> temprecommendSet= new HashSet<String>();
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend")){
		long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend");
		if(order==1){
			if(limit>count){
				temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend",0, count);
			}else{
				temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend",(page-1)*limit, page*limit-1);
			}
			
		}else{
			if(limit>count){
				temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend",0, count);
			}else{
				temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend",(page-1)*limit, page*limit-1);
			}
			
		}
		for(String s: temprecommendSet){
			if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, s+"_ninfo")){
				Map<String,String> map=RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, s+"_ninfo");
				if(map!=null && map.size()>0){
					RecommendNewsModel recommendNewsModel=RecommendNewsModel.convertToCommentModel(map);
					double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend", s);
					recommendNewsModel.setScore(Math.round(score));
					recommendNewsModel.ptimeString=formatter.format(new Date(recommendNewsModel.ptime));
    				if (recommendNewsModel.updatetime!=0) {
    					recommendNewsModel.updatetimeString=formatter.format(new Date(recommendNewsModel.updatetime));
    				}
    				recommendNewsModels.add(recommendNewsModel);
				}
			}
		}
	}

	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	
	model.addAttribute("categoryid", categoryid);
	model.addAttribute("recommendNewsModels", recommendNewsModels);


}

@RequestMapping(value = "/saverecommendnewscategory", method = RequestMethod.POST)
public void saverecommendnewscategory(Model model, HttpSession session, HttpServletRequest request, String nid, String score,String categoryid, PrintWriter out) throws UnsupportedEncodingException {
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, categoryid+"_categoryrecommend", Long.valueOf(score), nid);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}
}
@RequestMapping(value = "/delFromLabel", method = RequestMethod.POST)
public void delFromLabel(Model model, HttpSession session, HttpServletRequest request, String nid, String label, PrintWriter out) throws UnsupportedEncodingException {
//	String changelabel=MbUtil.sChangeBytes(label);
	String changelabel=label;
	Set<String>  set= new HashSet<String>();
	long count=0;
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABEL)) {
		  count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABEL);
		  set =RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABEL, 0,count);
		  for(String s: set){
			  if(s.equals(nid)){
				  RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABEL, s);
			  }
		  }
	}
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABELRECOMMEND)) {
		  count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABELRECOMMEND);
		  set =RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABELRECOMMEND, 0,count);
		  for(String s: set){
			  if(s.equals(nid)){
				  RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_LABELRECOMMEND, s);
			  }
		  }
	}
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_NEWSHOT)) {
		  count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_NEWSHOT);
		  set =RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_NEWSHOT, 0,count);
		  for(String s: set){
			  if(s.equals(nid)){
				  RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_NEWS,changelabel+NEWSKEY_NEWSHOT, s);
			  }
		  }
	}
	out.write("success");
}

@RequestMapping(value = "/updateNewsPid", method = RequestMethod.POST)
public void updateNewsPid(Model model, HttpSession session, HttpServletRequest request, String nid, String pid,PrintWriter out) throws IOException {
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo", "pid", pid);
		newsModel newsModel =new newsModel();
		newsModel.setNid(nid);
		newsModel.setPid(pid);
		newsService.updatenewsByNid(newsModel);
		
		
		out.print("success");
	}
}
@RequestMapping(value = "/updateNewsBusinessuserid", method = RequestMethod.POST)
public void updateNewsBusinessuserid(Model model, HttpSession session, HttpServletRequest request, String nid, String businessuserid,PrintWriter out) throws IOException {
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")) {
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo", "businessuserid", businessuserid);
		newsModel newsModel =new newsModel();
		newsModel.setNid(nid);
		newsModel.setBusinessuserid(businessuserid);
		newsService.updatenewsByNid(newsModel);
		
		
		out.print("success");
	}
}
@RequestMapping(value = "/topicAndDiscusslist", method = RequestMethod.GET)
public void topicAndDiscusslist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,int topictype) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<topic> pageBean=new PageBean<topic>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
  
    if(orderConditions==null || orderConditions==""){
		orderConditions="";
	}
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=newsService.getTopicsCount(topictype);
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	List<topic> topics=newsService.getTopics(start,limit,order,orderConditions,topictype);
	if(topictype==1){
		for(topic topic : topics){
			topic.setPubtimeString(formatter.format(new Date(topic.getPubtime())));
		}
	}
	model.addAttribute("topics", topics);
	
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	ArrayList<Integer> typeList =new ArrayList<Integer>();
	typeList.add(0);
	typeList.add(1);
	model.addAttribute("typeList", typeList);
	model.addAttribute("topics", topics);
	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("limit", limit);
	model.addAttribute("topictype",topictype);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/changeDiscussOrderindex", method = RequestMethod.POST)
public void changeDiscussOrderindex(Model model, HttpSession session, HttpServletRequest request, String nid, String keyword,String orderindex,PrintWriter out) throws UnsupportedEncodingException {
	if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, keyword + NEWSKEY_DISCUSSHOT)) {
		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_NEWS, keyword + NEWSKEY_DISCUSSHOT, Long.valueOf(orderindex), nid);
		out.print("success");
	}
}
@RequestMapping(value = "/changeNewmotomodelCePing", method = RequestMethod.POST)
public void changeNewmotomodelCePing(Model model, HttpSession session, HttpServletRequest request, String nid, String modelid,PrintWriter out) throws UnsupportedEncodingException {
		if(modelid!=null && !"".equals(modelid.trim())){
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, modelid +RUNKEY_NEWMOTOMODELASSESSMENTLIST, System.currentTimeMillis(),nid);
			out.print("success");
		}else{
			out.print("fail");
		}
}

@RequestMapping(value = "/motoboxnews", method = RequestMethod.GET)
public void motoboxnews(Model model, HttpSession session, HttpServletRequest request, String userGuid,int page,int limit,int order) throws UnsupportedEncodingException {

	List<newsModel> newsModels_new=new ArrayList<newsModel>();
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	PageBean<newsModel> pageBean=new PageBean<newsModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=0;
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS)){
		totalCount =(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS);
	}
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	
	
	Set<String> temprecommendSet= new HashSet<String>();
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS)){
		long count =RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS);
		if(order==1){
			if(limit>count){
				temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS,0, count);
			}else{
				temprecommendSet=RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS,(page-1)*limit, page*limit-1);
			}
			
		}else{
			if(limit>count){
				temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS,0, count);
			}else{
				temprecommendSet=RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS,(page-1)*limit, page*limit-1);
			}
			
		}
		List<newsModel> newsModels=getNewInfoListFromIdSet(temprecommendSet);
		for(newsModel newsModel : newsModels){
			String nid=newsModel.getNid();
			double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_NEWS, NEWSKEY_BOXNEWS, nid);
			newsModel.setScore(Math.round(score));
			newsModel.setPtimeString(formatter.format(new Date(newsModel.getPtime())));
			if(newsModel.getUpdatetime()!=null){
				newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getUpdatetime())));
			}else{
				newsModel.setUpdatetimeString(formatter.format(new Date(newsModel.getPtime())));
			}
			
			newsModels_new.add(newsModel);
		}
		
	}


	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("newsModels", newsModels_new);
}

public void syncStoryAndBoxToES(String nid){
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo")){
		String type=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo","type");
		if("4".equals(type)||"8".equals(type)){
			Map<String, String> newsmap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_NEWS, nid+"_ninfo");
			newsModel news = newsModel.convertToModel(newsmap);
			newsModel newsModel= new newsModel();
			newsModel.setId(news.getId());
			newsModel.setNid(news.getNid());
			newsModel.setTitle(news.getTitle());
			newsModel.setPtime(news.getPtime());
			newsModel.setContent(news.getContent());
			if("8".equals(type)){
				newsModel.setMotoboxmodel(news.getMotoboxmodel());
			}
			//同步ES
			ESManager.syncNewsEs(newsModel);
		}
	}
	
}



@RequestMapping(value = "/newbannerlist", method = RequestMethod.GET)
public void newbannerlist(Model model, HttpSession session, HttpServletRequest request, String userGuid) throws UnsupportedEncodingException {
	

	List<BannerModel> banners =newsService.getNewBannerlist();
	
	model.addAttribute("banners", banners);

	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
}

@RequestMapping(value = "/getnewbannerbyid", method = RequestMethod.POST)
public void getnewbannerbyid(Model model, HttpSession session, HttpServletRequest request, String bannerid, PrintWriter out) throws UnsupportedEncodingException {
	
	
	BannerModel banner =newsService.getnewbannerbyid(bannerid);
	String jsonString="";
	if(banner!=null){
		jsonString=JSON.toJSONString(banner);
	}
	
	out.print(jsonString);
}

@RequestMapping(value = "/delnewbanner", method = RequestMethod.POST)
public void delnewBanner(Model model, HttpSession session, HttpServletRequest request, String bannerid, PrintWriter out) throws UnsupportedEncodingException {

	newsService.delnewBanner(bannerid);
	Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);

	out.print(admin.getUser_guid());
}

@RequestMapping(value = "/addnewbannerall", method = RequestMethod.POST)
public void addnewbannerall(Model model, HttpSession session, HttpServletRequest request,
		String bannerid, String title, String subtitle, String des,
		String bannertype, String linktype, String imgurl, String linkurl,
		String gpid, String nid, String keyword, String secondcarid,
		String state, String buserid,String miniprogramid,String orderindex,
		PrintWriter out) throws UnsupportedEncodingException {
	
	BannerModel banner = new BannerModel();
	if(bannerid!=null && !"".equals(bannerid.trim())){
		banner.setBannerid(Long.valueOf(bannerid));
	}
	banner.setBannertype(Integer.parseInt(bannertype));
	banner.setTitle(title);
	banner.setSubtitle(subtitle);
	banner.setDes(des);
	banner.setState(Integer.parseInt(state));
	banner.setLinktype(Integer.parseInt(linktype));
	banner.setImgurl(imgurl);
	if(linkurl!=null && !"".equals(linkurl)){
		banner.setLinkurl(linkurl);
	}
	if(gpid!=null && !"".equals(gpid)){
		banner.setGpid(Integer.parseInt(gpid));
	}
	if(nid!=null && !"".equals(nid)){
		banner.setNid(nid);
	}
	if(keyword!=null && !"".equals(keyword)){
		banner.setKeyword(keyword);
	}
	if(secondcarid!=null && !"".equals(secondcarid)){
		banner.setSecondcarid(secondcarid);
	}
	if(miniprogramid!=null && !"".equals(miniprogramid)){
		banner.setMiniprogramid(miniprogramid);
	}
	if(buserid!=null && !"".equals(buserid)){
		banner.setBuserid(buserid);
	}
	banner.setOrderindex(Long.valueOf(orderindex));
	
	newsService.addnewBannerall(banner);
	out.print("success");
}


@RequestMapping(value = "/getmoremomentnews", method = RequestMethod.POST)
public void getmoremomentnews(Model model, HttpSession session, HttpServletRequest request, String nid, String userid,PrintWriter out) throws UnsupportedEncodingException {
	JSONObject obj = new JSONObject();
	obj.put("token", "token");
	obj.put("requestid", "12345678");
	obj.put("cversion", "1");
	obj.put("ctype", "1");
	obj.put("userid", userid);
	obj.put("nid", nid);
	obj.put("pagetype", 1);
	obj.put("pagesize", 20);
	
	String data = JSON.toJSONString(obj);
	String responstr = MbUtil.handleReqAndSendReq("/news/getmomentnewslist", data);
	String resultstr = MbUtil.handleResp(responstr);
	String str ="1";
	if(StringUtils.isNotBlank(resultstr)){
		JSONObject json = JSON.parseObject(resultstr);
		str =(String) json.get("code");
	}
	String jsonString="";
	if(Integer.parseInt(str)==0){
		 jsonString=resultstr;
	}
	
	out.print(jsonString);
}
@RequestMapping(value = "/getmorenewsbycategory", method = RequestMethod.POST)
public void getmorenewsbycategory(Model model, HttpSession session, HttpServletRequest request, String nid, String userid,String categoryid,PrintWriter out) throws UnsupportedEncodingException {
	JSONObject obj = new JSONObject();
	obj.put("token", "token");
	obj.put("requestid", "12345678");
	obj.put("cversion", "1");
	obj.put("ctype", "1");
	obj.put("userid", userid);
	obj.put("nid", nid);
	obj.put("categoryid", categoryid);
	obj.put("pagetype", 1);
	obj.put("pagesize", 20);
	
	String data = JSON.toJSONString(obj);
	String responstr = MbUtil.handleReqAndSendReq("/news/getcategoryrecommendlist", data);
	String resultstr = MbUtil.handleResp(responstr);
	String str ="1";
	if(StringUtils.isNotBlank(resultstr)){
		JSONObject json = JSON.parseObject(resultstr);
		str =(String) json.get("code");
	}
	String jsonString="";
	if(Integer.parseInt(str)==0){
		jsonString=resultstr;
	}
	
	out.print(jsonString);
}
@RequestMapping(value = "/getmorenewsbylabel", method = RequestMethod.POST)
public void getmorenewsbylabel(Model model, HttpSession session, HttpServletRequest request, String nid, String userid,String label,String topictype, PrintWriter out) throws UnsupportedEncodingException {
	JSONObject obj = new JSONObject();
	obj.put("token", "token");
	obj.put("requestid", "12345678");
	obj.put("cversion", "1");
	obj.put("ctype", "1");
	obj.put("userid", userid);
	obj.put("nid", nid);
	obj.put("label", label);
	obj.put("topictype", topictype);
	obj.put("pagetype", 1);
	obj.put("pagesize", 20);
	
	String data = JSON.toJSONString(obj);
	String responstr = MbUtil.handleReqAndSendReq("/news/getlabelhotnewslist", data);
	String resultstr = MbUtil.handleResp(responstr);
	String str ="1";
	if(StringUtils.isNotBlank(resultstr)){
		JSONObject json = JSON.parseObject(resultstr);
		str =(String) json.get("code");
	}
	String jsonString="";
	if(Integer.parseInt(str)==0){
		jsonString=resultstr;
	}
	
	out.print(jsonString);
}


}
