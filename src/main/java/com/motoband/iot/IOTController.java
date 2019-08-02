//package com.motoband.iot;
//
//import java.io.PrintWriter;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.alibaba.fastjson.JSON;
//import com.motoband.admin.admin.Admin;
//import com.motoband.boxmanage.boxService;
//import com.motoband.boxmanage.imggroup;
//import com.motoband.boxmanage.motoimg;
//import com.motoband.businessmanage.BusinessService;
//import com.motoband.businessmanage.BusinessUserModel;
//import com.motoband.giftexchangemanage.GiftexchangeService;
//import com.motoband.motouser.MBUserlevelModel;
//import com.motoband.motouser.motouserService;
//import com.motoband.util.Constants;
//import com.motoband.util.Consts;
//import com.motoband.util.MbUtil;
//import com.motoband.util.PageBean;
//import com.motoband.util.RedisManager;
//
//@Controller
//@RequestMapping(value = "/iot")
//public class IOTController {
//
//@Autowired
//private IOTService iotservice;
//
//@RequestMapping(value = "/devicelog", method = RequestMethod.GET)
//public void giftexchangelist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
//		int page,int limit,int order,String orderConditions) {
//
//	if (userGuid == null) {
//		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
//		userGuid = admin.getUser_guid();
//	}
//	PageBean<GPSDeviceLog> pageBean=new PageBean<GPSDeviceLog>();
//	if(page==0){
//		page=1;
//	}
//    if(limit==0){
//    	limit=20;
//    }
//  
//    if(orderConditions==null || orderConditions==""){
//		orderConditions="";
//	}
//	pageBean.setPage(page);
//	pageBean.setLimit(limit);
//	int totalCount=iotservice.getDeviceLogCount();
//	
//	int totalPage=0;
//	if(totalCount % limit == 0){
//		totalPage = totalCount / limit;
//	}else{
//		totalPage = totalCount / limit + 1;
//	
//	}
//	pageBean.setTotalPage(totalPage); 
//	int start= (page-1)*limit;
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
////	List<GiftexchangeModel> giftexchangelist=giftexchangeService.getGiftexchangelist(start,limit,order,orderConditions);
//////	List<MBUserlevelModel> buserlevels=motouserService.getUserLevels();
////	List<GiftModel> gifts=giftexchangeService.getGiftList();
//////	Map<String,String> buserlevelsmap = new HashMap<String,String>();
////	Map<String,String> giftsmap = new HashMap<String,String>();
//////	for(MBUserlevelModel ml :buserlevels){
//////		buserlevelsmap.put(String.valueOf(ml.getLevel()), ml.getName());
//////	}
////	for(GiftModel g : gifts){
////		giftsmap.put(String.valueOf(g.getGiftid()), g.getName());
////	}
////	for(GiftexchangeModel giftexchangeModel :giftexchangelist){
////		giftexchangeModel.setValiditystarttimeString(formatter.format(new Date(giftexchangeModel.getValiditystarttime())));
////		giftexchangeModel.setValidityendtimeString(formatter.format(new Date(giftexchangeModel.getValidityendtime())));
////		giftexchangeModel.setGiftName(giftsmap.get(String.valueOf(giftexchangeModel.getGiftid())));
////		//giftexchangeModel.setConditionName(buserlevelsmap.get(String.valueOf(giftexchangeModel.getCondition())));
////	}
////	
////	
////	
////	ArrayList<Integer> limitList =new ArrayList<Integer>();
////	limitList.add(20);
////	limitList.add(50);
////	limitList.add(100);
////	model.addAttribute("limitList", limitList);
////	ArrayList<Integer> typeList =new ArrayList<Integer>();
////	typeList.add(0);
////	typeList.add(1);
////
////	model.addAttribute("pageBean", pageBean);
////
////	model.addAttribute("giftexchangelist", giftexchangelist);
////	model.addAttribute("limit", limit);
////	model.addAttribute("order", order);
////	model.addAttribute("orderConditions", orderConditions);
//}
//}
