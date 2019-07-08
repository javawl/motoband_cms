package com.motoband.brandparentmanage;

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
@RequestMapping(value = "/brandparentmanage")
public class BrandparentController {
@Autowired
private BrandparentService brandparentService;
@Autowired boxService boxService;

@RequestMapping(value = "/brandparentlist", method = RequestMethod.GET)
public void getBrandparentlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	PageBean<BrandparentModel> pageBean=new PageBean<BrandparentModel>();
	if(page==0){
		page=1;
	}
  if(limit==0){
 	limit=20;
   }

	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=brandparentService.getBrandparentlistCount();

	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	ArrayList<BrandparentModel> brandparentlist =brandparentService.getBrandparentlist(start,limit,order);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);

	model.addAttribute("brandparentlist", brandparentlist);
	model.addAttribute("pageBean", pageBean);

	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
}
@RequestMapping(value = "/getBrandparentByid", method = RequestMethod.POST)
public void getBrandparentByid(Model model, HttpSession session, HttpServletRequest request, String  bpid,PrintWriter out) {
	BrandparentModel brandparentModel =null ;
	String jsonString="";
	if(bpid !=null && bpid !=""){
		brandparentModel= brandparentService.getBrandparentByid(bpid);
		if(brandparentModel !=null){
			jsonString=JSONObject.toJSONString(brandparentModel);
		}
	}
	out.write(jsonString);
}
@RequestMapping(value = "/updatebrandparentByid", method = RequestMethod.POST)
public void updatebrandparentByid(Model model, HttpSession session, HttpServletRequest request,
		String bpid,String name,String imgurl,String ishot, String orderindex,  PrintWriter out) {
	BrandparentModel brandparentModel =new  BrandparentModel();
	if(bpid !=null && bpid!=""){
		brandparentModel.setBpid(Integer.parseInt(bpid));
	}
	brandparentModel.setName(name);
	brandparentModel.setImgurl(imgurl);
	if(ishot !=null && ishot!=""){
		brandparentModel.setIshot(Integer.parseInt(ishot));
	}
	if(orderindex !=null && orderindex!=""){
		brandparentModel.setOrderindex(Integer.parseInt(orderindex));
	}
	
		 brandparentService.updatebrandparentByid(brandparentModel);
		
	     out.write("success");
}
@RequestMapping(value = "/insbrandparent", method = RequestMethod.POST)
public void insbrandparent(Model model, HttpSession session, HttpServletRequest request,
		String name,String imgurl,String ishot, String orderindex,  PrintWriter out) {
	BrandparentModel brandparentModel =new  BrandparentModel();
	
	brandparentModel.setName(name);
	brandparentModel.setImgurl(imgurl);
	if(ishot !=null && ishot!=""){
		brandparentModel.setIshot(Integer.parseInt(ishot));
	}
	if(orderindex !=null && orderindex!=""){
		brandparentModel.setOrderindex(Integer.parseInt(orderindex));
	}
	
		 brandparentService.insbrandparent(brandparentModel);
		
	     out.write("success");
}
}
