package com.motoband.secondcar;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.businessmanage.BusinessService;
import com.motoband.businessmanage.UsecarmainModel;
import com.motoband.businessmanage.UsecarmaingroupModel;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.DateFormat;
import com.motoband.util.ESManager;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

@Controller
@RequestMapping(value = "/secondcar")
public class SecondCarController {
@Autowired
private SecondCarService secondCarService;
@Autowired
private boxService boxService;
@Autowired
private BusinessService businessService;


public static final String USERKEY_SECONDCARFAVORITES= "_secondcar_favorites";
public static final String USERKEY_SECONDCARPUBLISH= "_secondcar_publish";
public static final String USERKEY_SECONDCARMAP= "_secondcar_map";
public static final String USERKEY_SECONDCARCOMMENT= "_secondcar_comment";
public static final String USERKEY_SECONDCARCOMMENTMAP= "_secondcar_commentmap";

@RequestMapping(value = "/secondcarlist", method = RequestMethod.GET)
public void secondcarlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,String createuser) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<SecondCarModel> pageBean=new PageBean<SecondCarModel>();
	if(page==0){
		page=1;
	}
    if(limit==0){
    	limit=20;
    }
    if(createuser==null || "".equals(createuser)){
    	createuser="";
    }
    if(orderConditions==null || orderConditions==""){
		orderConditions="";
	}
	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=secondCarService.getSecondCarListCount(createuser);
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	List<SecondCarModel> secondcars=secondCarService.getSecondCarList(start,limit,order,orderConditions,createuser);
	for(SecondCarModel secondCarModel : secondcars){
		secondCarModel.setFirstonthecardtimeString(formatter.format(new Date(secondCarModel.getFirstonthecardtime())));
		secondCarModel.setLastrefreshtimeString(formatter.format(new Date(secondCarModel.getLastrefreshtime())));
		secondCarModel.setCreatetimeString(formatter.format(new Date(secondCarModel.getCreatetime())));
	}
	
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	List<SecondcarMainZoneModel> secondcarMainZoneList=secondCarService.getSecondcarMainZoneNameList();
	model.addAttribute("secondcarMainZoneList", secondcarMainZoneList);
	
	List<UsecarmaingroupModel> usecarmaingroupModels=businessService.getUsecarmaingroupList(2);
	model.addAttribute("usecarmaingroupModels", usecarmaingroupModels);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("secondcars", secondcars);
	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("limit", limit);
	model.addAttribute("createuser",createuser);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/secondcarToUsecarmain", method = RequestMethod.POST)
public void productToUsecarmain(Model model, HttpSession session, HttpServletRequest request, String id,
		String groupid,String orderindex, PrintWriter out) throws Exception {
	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, id + USERKEY_SECONDCARMAP)){
		Map<String, String> scmap= RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, id + USERKEY_SECONDCARMAP);
		SecondCarModel secondCarModel = SecondCarModel.convertToModel(scmap);
	
	if(secondCarModel!=null){
		String contentstr=JSON.toJSONString(secondCarModel);
		UsecarmainModel usecarmainModel = new UsecarmainModel();
		usecarmainModel.setGroupid(Integer.parseInt(groupid));
		usecarmainModel.setContentstr(contentstr);
		usecarmainModel.setOrderindex(Long.valueOf(orderindex));
		usecarmainModel.setType(2);
		businessService.addtoUsecarmain(usecarmainModel);
		out.print("success");
	
	}else{
		out.print("fail");
	}
	
	}else{
		out.print("fail");
	}
	
}

@RequestMapping(value = "/addkeyword", method = RequestMethod.POST)
public void addkeyword(Model model, HttpSession session, HttpServletRequest request, String secondcarid,
		@RequestParam(value = "checkbox_scmzname_arr[]",required = false)String[] checkbox_scmzname_arr,
		PrintWriter out) throws Exception {
	try {
		   StringBuffer sb = new StringBuffer();
		   String keyword="";
		   if(checkbox_scmzname_arr!=null && checkbox_scmzname_arr.length>0){
			   for(int i=0;i<checkbox_scmzname_arr.length;i++){
				   if(i!=checkbox_scmzname_arr.length-1){
					   sb.append(checkbox_scmzname_arr[i]);
					   sb.append(",");
				   }else{
					   sb.append(checkbox_scmzname_arr[i]);  
				   }
			   }
		   }
		   keyword=sb.toString();
		   secondCarService.addkeyword(secondcarid,keyword);
		   RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, secondcarid + USERKEY_SECONDCARMAP, "keyword", keyword);
		   Map<String, String> scmap= RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, secondcarid + USERKEY_SECONDCARMAP);
		   SecondCarModel secondCarModel = SecondCarModel.convertToModel(scmap);
		   ESManager.syncSecondcarEs(secondCarModel);
		   
			out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
	   
}
@RequestMapping(value = "/changeState", method = RequestMethod.POST)
public void changeState(Model model, HttpSession session, HttpServletRequest request, String secondcarid,String state,
		PrintWriter out) throws Exception {
	try {
		secondCarService.changeState(secondcarid,state);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, secondcarid + USERKEY_SECONDCARMAP, "state", state);
		Map<String, String> scmap= RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, secondcarid + USERKEY_SECONDCARMAP);
		SecondCarModel secondCarModel = SecondCarModel.convertToModel(scmap);
		ESManager.syncSecondcarEs(secondCarModel);
		
		out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
	
}

@RequestMapping(value = "/secondcarmainzonelist", method = RequestMethod.GET)
public void secondcarmainzonelist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<SecondcarMainZoneModel> pageBean=new PageBean<SecondcarMainZoneModel>();
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
	int totalCount=secondCarService.getSecondcarMainZoneListCount();
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	List<SecondcarMainZoneModel> secondcarmainzonelist=secondCarService.getSecondcarMainZoneList(start,limit,order,orderConditions);
	
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("secondcarmainzonelist", secondcarmainzonelist);
	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}

@RequestMapping(value = "/getSecondcarMainZoneById", method = RequestMethod.POST)
public void getSecondcarMainZoneById(Model model, HttpSession session, HttpServletRequest request, String scmzid,
		 PrintWriter out) throws Exception {
	       String jsonString="";
	       SecondcarMainZoneModel secondcarMainZoneModel=secondCarService.getSecondcarMainZoneById(scmzid);
		  if(secondcarMainZoneModel!=null){
			  secondcarMainZoneModel.searchsecondcar =JSON.parseObject(secondcarMainZoneModel.searchsecondcarstr, SearchSecondCarModel.class);
			  jsonString=JSON.toJSONString(secondcarMainZoneModel);
		  }
		 
		  out.print(jsonString);
	   
}

@RequestMapping(value = "/insertOrUpdateSecondcarMainZone", method = RequestMethod.POST)
public void insertOrUpdateSecondcarMainZone(Model model, HttpSession session, HttpServletRequest request, String scmzid,
		String title,String city,String store,String bpid,String brandid,String modelid,String minprice,String maxprice,String style,String state,String picurl,
		String minage,String maxage,String minkilometer,String maxkilometer,String procedure,String hasolddrivercomment,String hasofficial,String createusertype,String keyword,
		String orderindex,String scmztitle, PrintWriter out) throws Exception {
	       String jsonString="";
	       SecondcarMainZoneModel scmz = new SecondcarMainZoneModel();
	       if(scmzid!=null && !"".equals(scmzid)){
	    	   scmz.scmzid=scmzid;
	       }else{
	    	   scmz.scmzid=MbUtil.getUUID();
	       }
	       if(picurl!=null && !"".equals(picurl)){
	    	   scmz.picurl=picurl;
	       }
	       if(scmztitle!=null && !"".equals(scmztitle)){
	    	   scmz.title=scmztitle;
	       }
	       if(state!=null && !"".equals(state)){
	    	   scmz.state=Integer.parseInt(state);
	       }
	       if(orderindex!=null && !"".equals(orderindex)){
	    	   scmz.orderindex=Long.valueOf(orderindex);
	       }
	       SearchSecondCarModel ssc = new SearchSecondCarModel();
	       if(title!=null && !"".equals(title)){
	    	   ssc.title=title;
	       }
	       if(city!=null && !"".equals(city)){
	    	   ssc.city=city;
	       }
	       if(store!=null && !"".equals(store)){
	    	   ssc.store=Integer.parseInt(store);
	       }else{
	    	   ssc.store=1; 
	       }
	       if(bpid!=null && !"".equals(bpid)){
	    	   ssc.bpid=Integer.parseInt(bpid);
	       }
	       if(brandid!=null && !"".equals(brandid)){
	    	   ssc.brandid=Integer.parseInt(brandid);
	       }
	       if(modelid!=null && !"".equals(modelid)){
	    	   ssc.modelid=Integer.parseInt(modelid);
	       }
	       if(minprice!=null && !"".equals(minprice)){
	    	   ssc.minprice=Integer.parseInt(minprice);
	       }
	       if(maxprice!=null && !"".equals(maxprice)){
	    	   ssc.maxprice=Integer.parseInt(maxprice);
	       }
	       if(minage!=null && !"".equals(minage)){
	    	   ssc.minage=Integer.parseInt(minage);
	       }
	       if(maxage!=null && !"".equals(maxage)){
	    	   ssc.maxage=Integer.parseInt(maxage);
	       }
	       if(minkilometer!=null && !"".equals(minkilometer)){
	    	   ssc.minkilometer=Double.parseDouble(minkilometer);
	       }
	       if(maxkilometer!=null && !"".equals(maxkilometer)){
	    	   ssc.maxkilometer=Double.parseDouble(maxkilometer);
	       }
	       if(procedure!=null && !"".equals(procedure)){
	    	   ssc.procedure=Integer.parseInt(procedure);
	       }
	       if(hasolddrivercomment!=null && !"".equals(hasolddrivercomment)){
	    	   ssc.hasolddrivercomment=Integer.parseInt(hasolddrivercomment);
	       }
	       if(hasofficial!=null && !"".equals(hasofficial)){
	    	   ssc.hasofficial=Integer.parseInt(hasofficial);
	       }
	       if(createusertype!=null && !"".equals(createusertype)){
	    	   ssc.createusertype=Integer.parseInt(createusertype);
	       }
	       if(keyword!=null && !"".equals(keyword)){
	    	   ssc.keyword=keyword;
	       }
	       if(style!=null && !"".equals(style)){
	    	   ssc.style=style;
	       }
	       ssc.pageNo=0;
	       ssc.pageSize=10;
	       scmz.searchsecondcarstr = JSON.toJSONString(ssc);
	       
	     
	       try {
	    	   secondCarService.insertOrUpdateSecondcarMainZone(scmz);
	    	   jsonString="success";
		} catch (Exception e) {
			 jsonString="fail";
		}
	      
		  out.print(jsonString);
	   
}

@RequestMapping(value = "/consignmentlist", method = RequestMethod.GET)
public void consignmentlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,String startTime,String endTime) throws Exception {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	long starttimelong=0;
	long endtimelong=0;
	if(startTime!=null && !"".equals(startTime)){
		starttimelong=DateFormat.getDayStart(sdf.parse(startTime).getTime()/1000)*1000;
	}
	if(endTime!=null && !"".equals(endTime)){
		endtimelong=DateFormat.getDayEnd(sdf.parse(endTime).getTime()/1000)*1000;
	}
	
	PageBean<ConsignmentModel> pageBean=new PageBean<ConsignmentModel>();
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
	int totalCount=secondCarService.getConsignmentListCount(starttimelong,endtimelong);
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	List<ConsignmentModel> consignmentlist=secondCarService.getConsignmentList(start,limit,order,orderConditions,starttimelong,endtimelong);
	if(consignmentlist!=null && consignmentlist.size()>0){
		for(ConsignmentModel consignmentModel :consignmentlist){
			consignmentModel.setAddtimeString(formatter.format(new Date(consignmentModel.getAddtime())));
		}
	}
	
	
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("consignmentlist", consignmentlist);
	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
	if(startTime!=null && !"".equals(startTime)){
		model.addAttribute("startTime", startTime);
	}else{
		model.addAttribute("startTime", "");
	}
	if(endTime!=null && !"".equals(endTime)){
		model.addAttribute("endTime", endTime);
	}else{
		model.addAttribute("endTime", "");
	}
}
@RequestMapping(value = "/addRemarkandState", method = RequestMethod.POST)
public void addRemarkandState(Model model, HttpSession session, HttpServletRequest request, String consignmentid,String state,String remark,
		PrintWriter out) throws Exception {
	try {
		secondCarService.addRemarkandState(consignmentid,state,remark);
		out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
	
}
}
