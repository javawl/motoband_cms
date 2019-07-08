package com.motoband.giftexchangemanage;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.businessmanage.BusinessService;
import com.motoband.businessmanage.BusinessUserModel;
import com.motoband.motouser.MBUserlevelModel;
import com.motoband.motouser.motouserService;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

@Controller
@RequestMapping(value = "/giftexchangemanage")
public class GiftexchangeController {
@Autowired
private GiftexchangeService giftexchangeService;
@Autowired
private boxService boxService;
@Autowired
private BusinessService businessService;
@Autowired
private motouserService motouserService;

public static final String USERKEY_USERMAP = "_usermap";

@RequestMapping(value = "/giftexchangelist", method = RequestMethod.GET)
public void giftexchangelist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<GiftexchangeModel> pageBean=new PageBean<GiftexchangeModel>();
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
	int totalCount=giftexchangeService.getGiftexchangeCount();
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	List<GiftexchangeModel> giftexchangelist=giftexchangeService.getGiftexchangelist(start,limit,order,orderConditions);
//	List<MBUserlevelModel> buserlevels=motouserService.getUserLevels();
	List<GiftModel> gifts=giftexchangeService.getGiftList();
//	Map<String,String> buserlevelsmap = new HashMap<String,String>();
	Map<String,String> giftsmap = new HashMap<String,String>();
//	for(MBUserlevelModel ml :buserlevels){
//		buserlevelsmap.put(String.valueOf(ml.getLevel()), ml.getName());
//	}
	for(GiftModel g : gifts){
		giftsmap.put(String.valueOf(g.getGiftid()), g.getName());
	}
	for(GiftexchangeModel giftexchangeModel :giftexchangelist){
		giftexchangeModel.setValiditystarttimeString(formatter.format(new Date(giftexchangeModel.getValiditystarttime())));
		giftexchangeModel.setValidityendtimeString(formatter.format(new Date(giftexchangeModel.getValidityendtime())));
		giftexchangeModel.setGiftName(giftsmap.get(String.valueOf(giftexchangeModel.getGiftid())));
		//giftexchangeModel.setConditionName(buserlevelsmap.get(String.valueOf(giftexchangeModel.getCondition())));
	}
	
	
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	ArrayList<Integer> typeList =new ArrayList<Integer>();
	typeList.add(0);
	typeList.add(1);

	model.addAttribute("pageBean", pageBean);

	model.addAttribute("giftexchangelist", giftexchangelist);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/addgiftexchangeNewPage", method = RequestMethod.GET)
public void addgiftexchangeNewPage(Model model, HttpSession session, HttpServletRequest request, String userGuid) {

	model.addAttribute("userGuid", userGuid);
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
}
@RequestMapping(value = "/updategiftexchangeNewPage", method = RequestMethod.GET)
public void updategiftexchangeNewPage(Model model, HttpSession session, HttpServletRequest request, String userGuid,String exchangeid ) {
	
	model.addAttribute("userGuid", userGuid);
	model.addAttribute("exchangeid", exchangeid);
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
}

@RequestMapping(value = "/getBusersBystate", method = RequestMethod.POST)
public void getBusersBystate(Model model, HttpSession session, HttpServletRequest request, String userGuid,PrintWriter out) {
	List<BusinessUserModel> busers=businessService.getBusersBystate(1);
	String jsonstring=JSON.toJSONString(busers);
	out.write(jsonstring);
}
@RequestMapping(value = "/getUserLevels", method = RequestMethod.POST)
public void getUserLevels(Model model, HttpSession session, HttpServletRequest request, String userGuid,PrintWriter out) {
	List<MBUserlevelModel> buserlevels=motouserService.getUserLevels();
	String jsonstring=JSON.toJSONString(buserlevels);
	out.write(jsonstring);
}
@RequestMapping(value = "/getGiftList", method = RequestMethod.POST)
public void getGiftList(Model model, HttpSession session, HttpServletRequest request, String userGuid,PrintWriter out) {
	List<GiftModel> gifts=giftexchangeService.getGiftList();
	String jsonstring=JSON.toJSONString(gifts);
	out.write(jsonstring);
}
@RequestMapping(value = "/addGiftexchange", method = RequestMethod.POST)
public void addGiftexchange(Model model, HttpSession session, HttpServletRequest request, String name,
		String des,String condition,String scope,String sumcount,String validitystarttime,String validityendtime,String buserids,String giftid,String giftcount,String remind,
		String pic,String state,PrintWriter out) throws Exception {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	GiftexchangeModel giftexchangeModel = new GiftexchangeModel();
	giftexchangeModel.setExchangeid(MbUtil.getUUID());
	giftexchangeModel.setName(name);
	giftexchangeModel.setDes(des);
	giftexchangeModel.setCondition(Integer.parseInt(condition));
	MBUserlevelModel mbUserlevelModel=motouserService.getUserLevelByLevel(Integer.parseInt(condition));
	giftexchangeModel.setConditiondes(mbUserlevelModel.getName()+"及以上级别");
	giftexchangeModel.setScope(scope);
	giftexchangeModel.setSumcount(Integer.parseInt(sumcount));
	giftexchangeModel.setValiditystarttime(formatter.parse(validitystarttime).getTime());
	giftexchangeModel.setValidityendtime(formatter.parse(validityendtime).getTime()+86399999);
//	giftexchangeModel.setBuserids(buserids);
	giftexchangeModel.setGiftid(Integer.parseInt(giftid));
	giftexchangeModel.setGiftcount(Integer.parseInt(giftcount));
	giftexchangeModel.setRemind(remind);
	giftexchangeModel.setPic(pic);
	giftexchangeModel.setState(Integer.parseInt(state));
	String jsonstring="";
	try {
		giftexchangeService.addGiftexchange(giftexchangeModel);
	    giftexchangeService.insertBusinessGiftexchange(buserids,giftexchangeModel.getExchangeid());
			jsonstring="success";
	} catch (Exception e) {
		jsonstring="fail";
	}
	out.write(jsonstring);
}

@RequestMapping(value = "/getGiftexchangeByid", method = RequestMethod.POST)
public void getGiftexchangeByid(Model model, HttpSession session, HttpServletRequest request, String exchangeid,PrintWriter out) {
	GiftexchangeModel giftexchangeModel=giftexchangeService.getGiftexchangeByid(exchangeid);
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	String validitystarttimeString=formatter.format(new Date(giftexchangeModel.getValiditystarttime()));
	giftexchangeModel.setValiditystarttimeString(validitystarttimeString);
	String validityendtimeString=formatter.format(new Date(giftexchangeModel.getValidityendtime()));
	giftexchangeModel.setValidityendtimeString(validityendtimeString);
	List<BusinessGiftexchangeModel> businessGiftexchangeModels=giftexchangeService.getBusergiftexchangeByid(exchangeid);
	StringBuilder sbids= new StringBuilder();
	StringBuilder sbnames= new StringBuilder();
	if(businessGiftexchangeModels!=null && businessGiftexchangeModels.size()>0){
		for(BusinessGiftexchangeModel businessGiftexchangeModel :businessGiftexchangeModels){
			String buserid=businessGiftexchangeModel.getBuserid();
			String bname=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, buserid+USERKEY_USERMAP, "name");
			sbids.append(buserid).append(",");
			sbnames.append(bname).append(",");
		}
	}
	giftexchangeModel.setBuserids(sbids.substring(0, sbids.length()-1));
	giftexchangeModel.setBusernames(sbnames.substring(0, sbnames.length()-1));
	String jsonstring=JSON.toJSONString(giftexchangeModel);
	out.write(jsonstring);
}

@RequestMapping(value = "/updateGiftexchange", method = RequestMethod.POST)
public void updateGiftexchange(Model model, HttpSession session, HttpServletRequest request, String name,
		String des,String condition,String scope,String sumcount,String validitystarttime,String validityendtime,String buserids,String giftid,String giftcount,String remind,
		String pic,String state,String exchangeid,PrintWriter out) throws Exception {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	GiftexchangeModel giftexchangeModel = new GiftexchangeModel();
	giftexchangeModel.setName(name);
	giftexchangeModel.setDes(des);
	giftexchangeModel.setCondition(Integer.parseInt(condition));
	MBUserlevelModel mbUserlevelModel=motouserService.getUserLevelByLevel(Integer.parseInt(condition));
	giftexchangeModel.setConditiondes(mbUserlevelModel.getName()+"及以上级别");
	giftexchangeModel.setScope(scope);
	giftexchangeModel.setSumcount(Integer.parseInt(sumcount));
	giftexchangeModel.setValiditystarttime(formatter.parse(validitystarttime).getTime());
	giftexchangeModel.setValidityendtime(formatter.parse(validityendtime).getTime()+86399999);
	giftexchangeModel.setGiftid(Integer.parseInt(giftid));
	giftexchangeModel.setGiftcount(Integer.parseInt(giftcount));
	giftexchangeModel.setRemind(remind);
	giftexchangeModel.setPic(pic);
	giftexchangeModel.setExchangeid(exchangeid);
	giftexchangeModel.setState(Integer.parseInt(state));
	String jsonstring="";
	try {
		giftexchangeService.updateGiftexchange(giftexchangeModel);
		giftexchangeService.deleteBusinessGiftexchange(exchangeid);
	    giftexchangeService.insertBusinessGiftexchange(buserids,exchangeid);
		jsonstring="success";
	} catch (Exception e) {
		jsonstring="fail";
	}
	out.write(jsonstring);
}
}
