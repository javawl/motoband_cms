package com.motoband.activitymanage;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.util.Constants;
import com.motoband.util.PageBean;

@Controller
@RequestMapping(value = "/activitymanage")
public class ActivityControll {
@Autowired
private ActivityService activityService;
@Autowired
private boxService boxService;
@RequestMapping(value = "/activitylist", method = RequestMethod.GET)
public void adlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<ActivityModel> pageBean=new PageBean<ActivityModel>();
	if(page==0){
		page=1;
	}
  if(limit==0){
 	limit=20;
   }

	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=activityService.getActivityListCount();

	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	ArrayList<ActivityModel> activitylist =activityService.getActivityList(userGuid,start,limit,order);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	model.addAttribute("activitylist", activitylist);
	model.addAttribute("pageBean", pageBean);

	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
}
@RequestMapping(value = "/getActivityByaid", method = RequestMethod.POST)
public void getActivityByaid(Model model, HttpSession session, HttpServletRequest request,String aid,PrintWriter out) {
    String jsonStr="";
	if(aid !=null && !"".equals(aid)){
		ActivityModel activityModel =activityService.getActivityByaid(aid);
		if(activityModel !=null){
			jsonStr=JSONObject.toJSONString(activityModel);
		}
	}
	out.write(jsonStr);
	
}
@RequestMapping(value = "/updateActivity", method = RequestMethod.POST)
public void updateActivity(Model model, HttpSession session, HttpServletRequest request,String aid,
		String title,String subtitle,String picurl,String type,String orderindex,String keyword,String gpid,String runid,String boxid,
		String mallurl,String url, String pid,String state, PrintWriter out) {
	ActivityModel activityModel = new ActivityModel();
	activityModel.setAid(Integer.parseInt(aid));
	activityModel.setTitle(title);
	activityModel.setSubtitle(subtitle);
	activityModel.setPicurl(picurl);
	activityModel.setType(Integer.parseInt(type));
	activityModel.setOrderindex(Integer.parseInt(orderindex));
	activityModel.setKeyword(keyword);
	if(gpid !=null && !"".equals(gpid) ){
		activityModel.setGpid(Integer.parseInt(gpid));
	}
	
	activityModel.setRunid(runid);
	if(boxid !=null && !"".equals(boxid) ){
		activityModel.setBoxid(boxid);
	}
	if(pid!=null && !"".equals(pid)){
		activityModel.setPid(Integer.parseInt(pid));
	}
	activityModel.setMallurl(mallurl);
	activityModel.setUrl(url);
	activityModel.setState(Integer.parseInt(state));
    
	activityService.updateActivity(activityModel);	
	
	out.write("success");
	
}
@RequestMapping(value = "/insActivity", method = RequestMethod.POST)
public void insActivity(Model model, HttpSession session, HttpServletRequest request,
		String title,String subtitle,String picurl,String type,String orderindex,String keyword,String gpid,String runid,String boxid,
		String mallurl,String url,String pid, String state, PrintWriter out) {
	ActivityModel activityModel = new ActivityModel();
	activityModel.setTitle(title);
	activityModel.setSubtitle(subtitle);
	activityModel.setPicurl(picurl);
	activityModel.setType(Integer.parseInt(type));
	activityModel.setOrderindex(Integer.parseInt(orderindex));
	activityModel.setKeyword(keyword);
	if(gpid !=null && !"".equals(gpid) ){
		activityModel.setGpid(Integer.parseInt(gpid));
	}
	
	activityModel.setRunid(runid);
	if(boxid !=null && !"".equals(boxid) ){
		activityModel.setBoxid(boxid);
	}
	
	if(pid!=null && !"".equals(pid)){
		activityModel.setPid(Integer.parseInt(pid));
	}
	activityModel.setMallurl(mallurl);
	activityModel.setUrl(url);
	activityModel.setState(Integer.parseInt(state));
    
	activityService.insActivity(activityModel);	
	
	out.write("success");
	
}
}
