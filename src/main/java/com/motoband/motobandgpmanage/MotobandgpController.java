package com.motoband.motobandgpmanage;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.motoband.admanage.MotobandGPModel;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.motouser.AchievementModel;
import com.motoband.motouser.MBUserModel;
import com.motoband.motouser.motouserService;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

@Controller
@RequestMapping(value = "/motobandgpmanage")
public class MotobandgpController {
@Autowired
private MotobandgpService motobandgpService;
@Autowired
private boxService boxService;
@Autowired
private motouserService motouserService;
@RequestMapping(value = "/motobandgplist", method = RequestMethod.GET)
public void getMotobandgplist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,String orderConditions) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<MotobandGPModel> pageBean=new PageBean<MotobandGPModel>();
	if(page==0){
		page=1;
	}
  if(limit==0){
 	limit=20;
   }

	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=motobandgpService.getMotobandgpCount();

	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	ArrayList<MotobandGPModel> motobandgplist =motobandgpService.getMotobandgplist(start,limit,order,orderConditions);
	if(motobandgplist !=null && motobandgplist.size()>0){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(MotobandGPModel motobandGPModel :motobandgplist){
			String starttimeString=formatter.format(motobandGPModel.getStarttime());
			String endtimeString = formatter.format(motobandGPModel.getEndtime());
			motobandGPModel.setStarttimeString(starttimeString);
			motobandGPModel.setEndtimeString(endtimeString);
		}
	}
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);


	model.addAttribute("motobandgplist", motobandgplist);
	model.addAttribute("pageBean", pageBean);

	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/getMotobandgpByid", method = RequestMethod.POST)
public void getMotobandgpByid(Model model, HttpSession session, HttpServletRequest request, String gpid,PrintWriter out) {

	MotobandGPModel motobandgp =null ;
	String jsonString="";
	if(gpid !=null && gpid!=""){
		motobandgp =motobandgpService.getMotobandgpByid(gpid);
		if(motobandgp !=null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String starttimeString=formatter.format(motobandgp.getStarttime());
			String endtimeString = formatter.format(motobandgp.getEndtime());
			motobandgp.setStarttimeString(starttimeString);
			motobandgp.setEndtimeString(endtimeString);
			jsonString=JSONObject.toJSONString(motobandgp);
		}
	}
	out.write(jsonString);
}
@RequestMapping(value = "/updatemotobandgpByid", method = RequestMethod.POST)
public void updatemotobandgpByid(Model model, HttpSession session, HttpServletRequest request,
		String gpid,String title,String subtitle,String content, String picurl,String starttime,
		String endtime,String mileage,String lap,String achid,String type, String status,  PrintWriter out) {
	MotobandGPModel motobandgp =new  MotobandGPModel();
   motobandgp.setGpid(Integer.parseInt(gpid));
   motobandgp.setTitle(title);
   motobandgp.setSubtitle(subtitle);
   motobandgp.setContent(content);
   motobandgp.setPicurl(picurl);
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   long st=0;
   long et=0;
   try {
	st=formatter.parse(starttime).getTime();
	et=formatter.parse(endtime).getTime();
} catch (ParseException e) {
	e.printStackTrace();
}
   
   motobandgp.setStarttime(st);
   motobandgp.setEndtime(et);
   
   motobandgp.setMileage(Integer.parseInt(mileage));
   motobandgp.setLap(Integer.parseInt(lap));
   motobandgp.setAchid(Integer.parseInt(achid));
   motobandgp.setType(Integer.parseInt(type));
   motobandgp.setStatus(Integer.parseInt(status));
 
   motobandgpService.updatemotobandgpByid(motobandgp);	
   out.write("success");
}
@RequestMapping(value = "/getachievementlist", method = RequestMethod.POST)
public void getachievementlist(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) throws UnsupportedEncodingException {
	List<AchievementModel> achievementModellist=motobandgpService.getachievementlist();
	String jsonString="";
	if(achievementModellist !=null &&achievementModellist.size()>0 ){
		jsonString= JSONObject.toJSONString(achievementModellist);
	}
	out.write(jsonString);
}
@RequestMapping(value = "/addmotobandgp", method = RequestMethod.POST)
public void addmotobandgp(Model model, HttpSession session, HttpServletRequest request,
		String title,String subtitle,String content, String picurl,String starttime,
		String endtime,String mileage,String lap,String achid,String type, String status,  PrintWriter out) {
	MotobandGPModel motobandgp =new  MotobandGPModel();
   motobandgp.setTitle(title);
   motobandgp.setSubtitle(subtitle);
   motobandgp.setContent(content);
   motobandgp.setPicurl(picurl);
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
   long st=0;
   long et=0;
   try {
	st=formatter.parse(starttime).getTime();
	et=formatter.parse(endtime).getTime();
} catch (ParseException e) {
	e.printStackTrace();
}
   
   motobandgp.setStarttime(st);
   motobandgp.setEndtime(et);
   
   motobandgp.setMileage(Integer.parseInt(mileage));
   motobandgp.setLap(Integer.parseInt(lap));
   motobandgp.setAchid(Integer.parseInt(achid));
   motobandgp.setType(Integer.parseInt(type));
   motobandgp.setStatus(Integer.parseInt(status));
 
   motobandgpService.addmotobandgp(motobandgp);	
   out.write("success");
}
@RequestMapping(value = "/gpuserRankinglist", method = RequestMethod.POST)
public void gpuserRankinglist(Model model, HttpSession session, HttpServletRequest request,String gpid,String type,String page,PrintWriter out) throws UnsupportedEncodingException {
	List<GpuserRankingModel>  gpuserlist = new ArrayList<GpuserRankingModel>();
	Set<String> rankSet = new HashSet<String>();
	if(gpid!="" && gpid!=null && type !="" && type !=null){
		String gpidTrim= gpid.trim();
		int typeTrim = Integer.parseInt(type.trim());
		int pageTrim= Integer.parseInt(page.trim());
		if(typeTrim==1){
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank")) {
				long count= RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank");

				 if(count<100){
					 rankSet = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank", 0, count);
				 }else{
					 rankSet = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank", (pageTrim-1)*100, pageTrim*100-1);
				 }
				 for(String s :rankSet){
					 double zscore=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank",s);
					 GpuserRankingModel gpuserRankingModel = new GpuserRankingModel();
					 gpuserRankingModel.setUserid(s);
					 gpuserRankingModel.setRedisCount(count);
					 MBUserModel mbUserModel=motouserService.getUserByID(s);
					 gpuserRankingModel.setNickname(mbUserModel.getNickname());
					 gpuserRankingModel.setMileageScore(zscore);
					 gpuserlist.add(gpuserRankingModel);
				 }
				
			}
		}
		if(typeTrim==2){
			if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank")) {
				long count= RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank");
				 if(count<100){
					 rankSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank", 0, count);
				 }else{
					 rankSet = RedisManager.getInstance().zrangebyindex(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank",  (pageTrim-1)*100, pageTrim*100-1);
				 }
				 for(String s :rankSet){
					 double zscore=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank",s);
					 GpuserRankingModel gpuserRankingModel = new GpuserRankingModel();
					 gpuserRankingModel.setUserid(s);
					 String ridetimeScore="";
					 String hourStr="00";
					 String minuteStr="00";
					 String seconedStr="00";
					 double seconed =Math.floor(zscore/1000);
					 if(seconed>=60){
						 double minute=Math.floor(seconed/60);
						 int seconedInt =(int) seconed;
						 int seconedMod =seconedInt % 60;
						 if(minute>=60){
							 double hour=Math.floor(minute/60);
							 int minuteInt =(int) minute;
							 int minuteMod =minuteInt % 60;
							 if(hour>=10){
								 hourStr=""+(int)hour+""; 
							 }else{
								 hourStr="0"+(int)hour+""; 
							 }
							 if(minuteMod>=10){
								 minuteStr=""+minuteMod+""; 
							 }else{
								 minuteStr="0"+minuteMod+""; 
							 }
							 if(seconedMod>=10){
								 seconedStr=""+seconedMod+""; 
							 }else{
								 seconedStr="0"+seconedMod+""; 
							 }
						 
						 }else{
							 if(minute>=10){
								 minuteStr=""+(int)minute+""; 
							 }else{
								 minuteStr="0"+(int)minute+""; 
							 }
							 if(seconedMod>=10){
								 seconedStr=""+seconedMod+""; 
							 }else{
								 seconedStr="0"+seconedMod+""; 
							 }
							 
						 }
					 }else{
						 if(seconed>=10){
							 seconedStr=""+(int)seconed+""; 
						 }else{
							 seconedStr="0"+(int)seconed+""; 
						 }
						 
					 }
					 ridetimeScore=hourStr+":"+minuteStr+":"+seconedStr;
					 MBUserModel mbUserModel=motouserService.getUserByID(s);
					 gpuserRankingModel.setRedisCount(count);
					 gpuserRankingModel.setNickname(mbUserModel.getNickname());
					 gpuserRankingModel.setRidetimeScore(ridetimeScore);;
					 gpuserlist.add(gpuserRankingModel);
				 }
			}
		}
	}
	
	
	String jsonString="";
	if(gpuserlist  !=null &&gpuserlist .size()>0 ){
		jsonString= JSONObject.toJSONString(gpuserlist);
	}
	out.write(jsonString);
}
@RequestMapping(value = "/gpUserRemove", method = RequestMethod.POST)
public void gpUserRemove(Model model, HttpSession session, HttpServletRequest request,String userid,String gpid,String type, PrintWriter out) throws UnsupportedEncodingException {
	if(userid!=null && userid !=""){
		if(gpid!="" && gpid!=null && type !="" && type !=null){
			String gpidTrim= gpid.trim();
			int typeTrim = Integer.parseInt(type.trim());
			if(typeTrim==1){
				if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank")) {
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpmileagerank", userid);
			  }
			}
			if(typeTrim==2){
				if (RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank")) {
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, gpidTrim+"_motogpridetimerank", userid);
			  }
			}
	
		}
	}

	out.write("success");
}

@RequestMapping(value = "/lookJoinGPCount", method = RequestMethod.POST)
public void lookJoinGPCount(Model model, HttpSession session, HttpServletRequest request, String gpid,PrintWriter out) {

	String jsonString="";
	if(gpid !=null && gpid!=""){
		int joingpCount=motobandgpService.getMgpJoingpCount(gpid);
		int completedgpCount=motobandgpService.getMgpCompletedgpCount(gpid);
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("joingpCount", joingpCount);
		map.put("completedgpCount", completedgpCount);
		jsonString=JSONObject.toJSONString(map);
		
	}
	out.write(jsonString);
}
}
