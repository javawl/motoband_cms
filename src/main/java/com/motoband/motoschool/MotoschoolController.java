package com.motoband.motoschool;

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
@RequestMapping(value = "/motoschoolmanage")
public class MotoschoolController {
@Autowired
private MotoschoolService motoschoolService;
@Autowired
private boxService boxService;
@RequestMapping(value = "/packagelist", method = RequestMethod.GET)
public void packagelist(Model model, HttpSession session,
		HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {
	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<MotoschoolPackageModel> pageBean=new PageBean<MotoschoolPackageModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount= motoschoolService.getMotoschoolPackageListCount();
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;

	if(orderConditions==null || orderConditions==""){
		orderConditions="";
	}
	ArrayList<MotoschoolPackageModel> packagelist = motoschoolService.getMotoschoolPackageList(
			start,limit,order,orderConditions);
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	model.addAttribute("packagelist", packagelist);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/getPackageBypid", method = RequestMethod.POST)
public void getPackageBypid(Model model, HttpSession session,
		HttpServletRequest request, String pid,PrintWriter out) {
	String jsonStr="";
    if(pid!=null && !"".equals(pid)){
    	MotoschoolPackageModel motoschoolPackageModel = motoschoolService.getPackageBypid(pid);
    	if(motoschoolPackageModel !=null){
    		jsonStr=JSONObject.toJSONString(motoschoolPackageModel);
    	}
    }
	out.write(jsonStr);
}
@RequestMapping(value = "/updatePackage", method = RequestMethod.POST)
public void updatePackage(Model model, HttpSession session,
		HttpServletRequest request, String pid,String title,String subtitle,String desc,String picurl,String orderindex,String state,PrintWriter out) {
	MotoschoolPackageModel motoschoolPackageModel = new MotoschoolPackageModel();
	if(pid!=null && !"".equals(pid)){
	motoschoolPackageModel.setPid(Integer.parseInt(pid));
	}
	motoschoolPackageModel.setTitle(title);
	motoschoolPackageModel.setSubtitle(subtitle);
	motoschoolPackageModel.setDesc(desc);
	motoschoolPackageModel.setPicurl(picurl);
	if(orderindex!=null && !"".equals(orderindex)){
	motoschoolPackageModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
	motoschoolPackageModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.updatePackage(motoschoolPackageModel);
    	
	out.write("success");
}
@RequestMapping(value = "/insPackage", method = RequestMethod.POST)
public void insPackage(Model model, HttpSession session,
		HttpServletRequest request,String title,String subtitle,String desc,String picurl,String orderindex,String state,PrintWriter out) {
	MotoschoolPackageModel motoschoolPackageModel = new MotoschoolPackageModel();
	
	motoschoolPackageModel.setTitle(title);
	motoschoolPackageModel.setSubtitle(subtitle);
	motoschoolPackageModel.setDesc(desc);
	motoschoolPackageModel.setPicurl(picurl);
	if(orderindex!=null && !"".equals(orderindex)){
	motoschoolPackageModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
	motoschoolPackageModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.insPackage(motoschoolPackageModel);
    	
	out.write("success");
}
@RequestMapping(value = "/getPackageTitleList", method = RequestMethod.POST)
public void getPackageTitleList(Model model, HttpSession session,
		HttpServletRequest request,PrintWriter out) {
	    String jsonStr="";
    	ArrayList<MotoschoolPackageModel> motoschoolPackageModels = motoschoolService.getPackageTitleList();
    	if(motoschoolPackageModels !=null && motoschoolPackageModels.size()>0){
    		jsonStr=JSONObject.toJSONString(motoschoolPackageModels);
    	}
    
	out.write(jsonStr);
}
@RequestMapping(value = "/videolist", method = RequestMethod.GET)
public void videolist(Model model, HttpSession session,
		HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {
	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<MotoschoolVideoModel> pageBean=new PageBean<MotoschoolVideoModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount= motoschoolService.getMotoschoolVideoListCount();
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;

	if(orderConditions==null || orderConditions==""){
		orderConditions="";
	}
	ArrayList<MotoschoolVideoModel> videolist = motoschoolService.getMotoschoolVideoList(
			start,limit,order,orderConditions);
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	model.addAttribute("videolist", videolist);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}

@RequestMapping(value = "/getVideoBysid", method = RequestMethod.POST)
public void getVideoBysid(Model model, HttpSession session,
		HttpServletRequest request, String sid,PrintWriter out) {
	String jsonStr="";
    if(sid!=null && !"".equals(sid)){
    	MotoschoolVideoModel motoschoolVideoModel = motoschoolService.getVideoBysid(sid);
    	if(motoschoolVideoModel !=null){
    		jsonStr=JSONObject.toJSONString(motoschoolVideoModel);
    	}
    }
	out.write(jsonStr);
}
@RequestMapping(value = "/updateVideo", method = RequestMethod.POST)
public void updateVideo(Model model, HttpSession session,
		HttpServletRequest request, String sid,String title,String pid,String vu,String videourl,String desc,String picurl,String orderindex,String state,PrintWriter out) {
	MotoschoolVideoModel motoschoolVideoModel = new MotoschoolVideoModel();
	if(sid!=null && !"".equals(sid)){
	motoschoolVideoModel.setSid(Integer.parseInt(sid));
	}
	motoschoolVideoModel.setTitle(title);
	motoschoolVideoModel.setPid(Integer.parseInt(pid));
	motoschoolVideoModel.setVideourl(videourl);
	motoschoolVideoModel.setVu(vu);
	motoschoolVideoModel.setDesc(desc);
	motoschoolVideoModel.setPicurl(picurl);
	if(orderindex!=null && !"".equals(orderindex)){
		motoschoolVideoModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
		motoschoolVideoModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.updateVideo(motoschoolVideoModel);
    	
	out.write("success");
}
@RequestMapping(value = "/insVideo", method = RequestMethod.POST)
public void insVideo(Model model, HttpSession session,
		HttpServletRequest request,String title,String pid,String vu,String videourl,String desc,String picurl,String orderindex,String state,PrintWriter out) {
	MotoschoolVideoModel motoschoolVideoModel = new MotoschoolVideoModel();
	motoschoolVideoModel.setTitle(title);
	motoschoolVideoModel.setPid(Integer.parseInt(pid));
	motoschoolVideoModel.setVideourl(videourl);
	motoschoolVideoModel.setVu(vu);
	motoschoolVideoModel.setDesc(desc);
	motoschoolVideoModel.setPicurl(picurl);
	if(orderindex!=null && !"".equals(orderindex)){
		motoschoolVideoModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
		motoschoolVideoModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.insVideo(motoschoolVideoModel);
    	
	out.write("success");
}

@RequestMapping(value = "/boxlist", method = RequestMethod.GET)
public void boxlist(Model model, HttpSession session,
		HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {
	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<MotoschoolBoxModel> pageBean=new PageBean<MotoschoolBoxModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount= motoschoolService.getMotoschoolBoxListCount();
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;

	if(orderConditions==null || orderConditions==""){
		orderConditions="";
	}
	ArrayList<MotoschoolBoxModel> boxlist = motoschoolService.getMotoschoolBoxList(
			start,limit,order,orderConditions);

	model.addAttribute("boxlist", boxlist);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}

@RequestMapping(value = "/getBoxBybid", method = RequestMethod.POST)
public void getBoxBybid(Model model, HttpSession session,
		HttpServletRequest request, String bid,PrintWriter out) {
	String jsonStr="";
    if(bid!=null && !"".equals(bid)){
    	MotoschoolBoxModel motoschoolBoxModel = motoschoolService.getBoxBybid(bid);
    	if( motoschoolBoxModel !=null){
    		jsonStr=JSONObject.toJSONString( motoschoolBoxModel);
    	}
    }
	out.write(jsonStr);
}

@RequestMapping(value = "/updateBox", method = RequestMethod.POST)
public void updateBox(Model model, HttpSession session,
		HttpServletRequest request, String bid,String boxid,String pid,String orderindex,String state,PrintWriter out) {
	MotoschoolBoxModel motoschoolBoxModel = new MotoschoolBoxModel();
	if(bid!=null && !"".equals(bid)){
		motoschoolBoxModel.setBid(Integer.parseInt(bid));
	}
	motoschoolBoxModel.setBoxid(Integer.parseInt(boxid));
	motoschoolBoxModel.setPid(Integer.parseInt(pid));
	if(orderindex!=null && !"".equals(orderindex)){
		motoschoolBoxModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
		motoschoolBoxModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.updateBox(motoschoolBoxModel);
    	
	out.write("success");
}
@RequestMapping(value = "/insBox", method = RequestMethod.POST)
public void insBox(Model model, HttpSession session,
		HttpServletRequest request,String boxid,String pid,String orderindex,String state,PrintWriter out) {
	MotoschoolBoxModel motoschoolBoxModel = new MotoschoolBoxModel();
	motoschoolBoxModel.setBoxid(Integer.parseInt(boxid));
	motoschoolBoxModel.setPid(Integer.parseInt(pid));
	if(orderindex!=null && !"".equals(orderindex)){
		motoschoolBoxModel.setOrderindex(Integer.parseInt(orderindex));
	}
	if(state!=null && !"".equals(state)){
		motoschoolBoxModel.setState(Integer.parseInt(state));
	}
	
    motoschoolService.insBox(motoschoolBoxModel);
    	
	out.write("success");
}
}
