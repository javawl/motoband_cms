package com.motoband.businessmanage;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.brandparentmanage.BrandparentModel;
import com.motoband.brandparentmanage.BrandparentService;
import com.motoband.carmanage.carModel;
import com.motoband.carmanage.carService;
import com.motoband.mallmanage.MallProductModel;
import com.motoband.secondcar.SecondCarModel;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;
import com.motoband.util.Utilities.MD5;

@Controller
@RequestMapping(value = "/businessmanage")
public class BusinessController {
@Autowired  private BusinessService businessService;
@Autowired
private BrandparentService brandparentService;
@Autowired 
private boxService boxService;
@Autowired
private carService carService;

public static final String USERID_PREFIX = "business_";
public static final String DEFAULTHEADURL = "http://libres-10013836.cos.myqcloud.com/defaultheadimg.png";

public static final String USERKEY_USERMAP = "_usermap";
public static final String USERKEY_USER = "_user";
public static final String USERKEY_COMMENT = "_comment";
public static final String USERKEY_GOODCOMMENT = "_goodcomment";
public static final String USERKEY_BADCOMMENT = "_badcomment";
public static final String USERKEY_PICCOMMENT = "_piccomment";
public static final String USERKEY_COMMENTMAP = "_commentmap";
public static final String USERKEY_ACTIVITY = "_activity";
public static final String USERKEY_ACTIVITYMAP = "_activitymap";
public static final String MAPKEY_BUSINESSACTIVITYLIST = "businessactivitylist";

public static final String USERKEY_BUSINESSUSERLIST = "_businessuserlist";// 客服用户下的本地商家列表


//日志记录
//public	static Logger logger = LoggerFactory.getLogger(BusinessController.class);

@RequestMapping(value = "/changeApplyState", method = RequestMethod.POST)
public void changeApplyState(Model model, HttpSession session, HttpServletRequest request, String reason, String userid,String password,String aid,String state,PrintWriter out) throws Exception {
	    if(state==null ||"".equals(state)){
	    	out.print("fail");
	    }else{
	    	if(Integer.parseInt(state)==1){
	    		if(userid==null ||"".equals(userid.trim())||password==null ||"".equals(password.trim())){
	    			out.print("fail");
	    		}else{
	    			long currenttime=System.currentTimeMillis();
	    			businessService.changeApplyState(state,null,aid);
	    			BusinessApplyModel businessApplyModel=businessService.getApplyByAid(aid);
	    			BusinessUserModel businessUserModel = new BusinessUserModel();
	    			businessUserModel.setName(businessApplyModel.getName());
	    			businessUserModel.setContactname(businessApplyModel.getContactname());
	    			businessUserModel.setContactphone(businessApplyModel.getContactphone());
	    			businessUserModel.setType(businessApplyModel.getType());
	    			businessUserModel.setMainbrand(businessApplyModel.getMainbrand());
	    			businessUserModel.setAddress(businessApplyModel.getAddress());
	    			businessUserModel.setBusinessarea(businessApplyModel.getBusinessarea());
	    			businessUserModel.setStaffcount(businessApplyModel.getStaffcount());
	    			businessUserModel.setAge(businessApplyModel.getAge());
	    			businessUserModel.setRecommendbrand(businessApplyModel.getRecommendbrand());
	    			businessUserModel.setLicense(businessApplyModel.getLicense());
	    			businessUserModel.setOther(businessApplyModel.getOther());
	    			businessUserModel.setState(0);
	    			businessUserModel.setReason(businessApplyModel.getReason());
	    			businessUserModel.setProvince(businessApplyModel.getProvince());
	    			businessUserModel.setCity(businessApplyModel.getCity());
	    			businessUserModel.setAddtime(currenttime);
	    			businessUserModel.setBusinessserviceliststr(businessApplyModel.getBusinessserviceliststr());
	    			businessUserModel.setLongitude(businessApplyModel.getLongitude());
	    			businessUserModel.setLatitude(businessApplyModel.getLatitude());
	    			
	    			businessUserModel.setAid(aid);
	    			businessUserModel.setUserid(USERID_PREFIX+MbUtil.getUUID());
	    			businessUserModel.setMobileno(userid);
	    			businessUserModel.setPassword(MD5.stringToMD5(MD5.stringToMD5(password)));
	    			businessUserModel.setHeadurl(DEFAULTHEADURL);
	    			
	    			businessService.insertBusinessUser(businessUserModel);
	    			long buid=businessUserModel.getBuid();
	    			//高德
	    			String lbsid=LBSManager.getInstance().synLBSDataToGD(businessUserModel);
	    			if(lbsid!=null&& !"".equals(lbsid.trim())){
		    			//businesscode
		    			Map<String,String> updatemap = new HashMap<String, String>();
		    			updatemap.put("businesscode", String.valueOf(buid+1000));
		    			updatemap.put("userid", businessUserModel.getUserid());
		    			updatemap.put("lbsid", lbsid);
		    			businessService.updateBusinessUser(updatemap);
		    			
		    			
		    			//redis
		    			businessUserModel.setBuid(buid);
		    			businessUserModel.setBusinesscode(String.valueOf(buid+1000));
		    			businessUserModel.setLbsid(lbsid);
		    			Map<String,String> umap=BusinessUserModel.convertToMap(businessUserModel);
		    			umap.put("businessservicelist", businessApplyModel.getBusinessserviceliststr());
		    			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, businessUserModel.getUserid()+USERKEY_USERMAP, umap);
		    			//userinfoapply
//		    			BusinessBaseinfoModel businessBaseinfoModel = new BusinessBaseinfoModel();
//		    			businessBaseinfoModel.setName(businessApplyModel.getName());
//		    			businessBaseinfoModel.setContactphone(businessApplyModel.getContactphone());
//		    			businessBaseinfoModel.setAddress(businessApplyModel.getAddress());
//		    			businessBaseinfoModel.setProvince(businessApplyModel.getProvince());
//		    			businessBaseinfoModel.setCity(businessApplyModel.getCity());
//		    			businessBaseinfoModel.setLongitude(businessApplyModel.getLongitude());
//		    			businessBaseinfoModel.setLatitude(businessApplyModel.getLatitude());
//		    			businessBaseinfoModel.setHeadurl(DEFAULTHEADURL);
//		    			businessBaseinfoModel.setBusinesscode(String.valueOf(buid+1000));
//		    			
//		    			Map<String,Object>  bitempMap =new HashMap<String, Object>();
//		    			bitempMap.put("baseinfo", businessBaseinfoModel);
//		    			bitempMap.put("state", 1);
//		    			Map<String,Object>  betempMap =new HashMap<String, Object>();
//		    			betempMap.put("state", 1);
//		    			Map<String,Object>  bstempMap =new HashMap<String, Object>();
//		    			bstempMap.put("businessservicelist",JSON.parseArray(businessUserModel.getBusinessserviceliststr(), BusinessServiceModel.class));
//		    			bstempMap.put("state", 1);
//		    			Map<String,Object>  batempMap =new HashMap<String, Object>();
//		    			batempMap.put("state", 1);
//		    			
//		    			BusinessUserinfoApplyModel businessUserinfoApplyModel = new BusinessUserinfoApplyModel();
//		    		//	System.out.println(JSON.toJSONString(bitempMap));
//		    			businessUserinfoApplyModel.setBaseinfostr(JSON.toJSONString(bitempMap));
//		    			businessUserinfoApplyModel.setEnvironmentalstr(JSON.toJSONString(betempMap));
//		    			businessUserinfoApplyModel.setBusinessserviceliststr(JSON.toJSONString(bstempMap));
//		    			businessUserinfoApplyModel.setBusinessactivityliststr(JSON.toJSONString(batempMap));
//		    			businessUserinfoApplyModel.setUserid(businessUserModel.getUserid());
//		    			
//		    			businessService.insertUserinfoApply(businessUserinfoApplyModel);
		    			
		    			out.print("success");
	    			}else{
	    				out.print("fail");
	    			}
	    			
	    		}
	    	}else if(Integer.parseInt(state)==2){
               if(reason==null ||"".equals(reason.trim())){
            	   out.print("fail");
	    		}else{
	    			businessService.changeApplyState(state,reason,aid);
	    			out.print("success");
	    		}
	    	}
	    	
	    	
	    }
		
	}

@RequestMapping(value = "/businessapplylist", method = RequestMethod.GET)
public void businessapplylist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,int state) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<BusinessApplyModel> pageBean=new PageBean<BusinessApplyModel>();
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
	int totalCount=businessService.getApplyCount(state);
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<BusinessApplyModel> applys=businessService.getApplys(start,limit,order,orderConditions,state);
	for(BusinessApplyModel businessApplyModel :applys){
		businessApplyModel.setAddtimeString(formatter.format(new Date(businessApplyModel.getAddtime())));
	}
	
//	List<BrandparentModel>  brandparentModels = brandparentService.getBrandparentlistSimple();
//	List<BusinessTypeModel> businessTypeModels = businessService.getBusinessTypeList();
//	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
//	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
//	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
//	model.addAttribute("imggroups", imggroups);
//	model.addAttribute("motoimgs", motoimgs);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	ArrayList<Integer> typeList =new ArrayList<Integer>();
	typeList.add(0);
	typeList.add(1);

	model.addAttribute("pageBean", pageBean);

	
//	model.addAttribute("businessTypeModels", businessTypeModels);
//	model.addAttribute("businessServiceModels", businessServiceModels);
//	model.addAttribute("brandparentModels", brandparentModels);
	model.addAttribute("businessapplys", applys);
	model.addAttribute("limit", limit);
	model.addAttribute("state",state);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/changeUserinfoApplyState", method = RequestMethod.POST)
public void changeUserinfoApplyState(Model model, HttpSession session, HttpServletRequest request, String reason, String buserid,String type,String state,PrintWriter out) throws UnsupportedEncodingException {
	    if(state==null ||"".equals(state)||buserid==null||"".equals(buserid)||type==null ||"".equals(type)){
	    	out.print("fail");
	    }else{
	    	BusinessUserinfoApplyModel businessUserinfoApplyModel=businessService.getBuserinfoApplyBybuserid(buserid);
	    	if(Integer.parseInt(type)==1){
	    	    Map<String,Object> baseinfostrMap=JSON.parseObject(businessUserinfoApplyModel.getBaseinfostr(), Map.class);
	    		
	    	  	if(Integer.parseInt(state)==1){
	    			//int baseinfoState=(Integer) baseinfostrMap.get("state");
	    			Map<String,Object> baseinfoMap=JSON.parseObject(JSON.toJSONString(baseinfostrMap.get("baseinfo")), Map.class);
	    			Map<String,String>  map =new HashMap<String,String>();
	    			for(Map.Entry<String, Object> entry : baseinfoMap.entrySet()){
	    				map.put(entry.getKey(), String.valueOf(entry.getValue()));
	    			}
	    			map.put("userid", buserid);
	    			businessService.updateBusinessUser(map);
	    			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, buserid+USERKEY_USERMAP, map);
	    			baseinfostrMap.put("state", 1);
	    			String baseinfostr=JSON.toJSONString(baseinfostrMap);
	    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
	    			businessUserinfoApplyModel1.setBaseinfostr(baseinfostr);
	    			businessUserinfoApplyModel1.setUserid(buserid);
	    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
	    			//检验状态
	    			boolean checkstate=checkStateOfUserinfoApply(buserid);
	    			if(checkstate){
	    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
	    			}else{
	    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
	    			}
	    			out.print("success");	
		    	}else if(Integer.parseInt(state)==2){
	               if(reason==null ||"".equals(reason.trim())){
	            	   out.print("fail");
		    		}else{
		    			baseinfostrMap.put("state", 2);
		    			baseinfostrMap.put("reason", reason);
		    			String baseinfostr=JSON.toJSONString(baseinfostrMap);
		    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
		    			businessUserinfoApplyModel1.setBaseinfostr(baseinfostr);
		    			businessUserinfoApplyModel1.setUserid(buserid);
		    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
		    			//检验状态
		    			boolean checkstate=checkStateOfUserinfoApply(buserid);
		    			if(checkstate){
		    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
		    			}else{
		    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
		    			}
		    			out.print("success");
		    		}
		    	}
	    	}else if(Integer.parseInt(type)==2){
	    		Map<String,Object> environmentalstrMap=JSON.parseObject(businessUserinfoApplyModel.getEnvironmentalstr(), Map.class);
	    	  	if(Integer.parseInt(state)==1){
	    			//int environmentalState=(Integer) environmentalstrMap.get("state");
	    			Map<String,Object> environmental=JSON.parseObject(JSON.toJSONString(environmentalstrMap.get("environmental")), Map.class);
	    			Map<String,String>  map =new HashMap<String,String>();
	    			for(Map.Entry<String, Object> entry : environmental.entrySet()){
	    				map.put(entry.getKey(), String.valueOf(entry.getValue()));
	    			}
	    			map.put("userid", buserid);
	    			businessService.updateBusinessUser(map);
	    			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, buserid+USERKEY_USERMAP, map);
	    			environmentalstrMap.put("state", 1);
	    			String environmentalstr=JSON.toJSONString(environmentalstrMap);
	    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
	    			businessUserinfoApplyModel1.setEnvironmentalstr(environmentalstr);;
	    			businessUserinfoApplyModel1.setUserid(buserid);
	    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
	    			//检验状态
	    			boolean checkstate=checkStateOfUserinfoApply(buserid);
	    			if(checkstate){
	    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
	    			}else{
	    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
	    			}
	    			out.print("success");	
		    	}else if(Integer.parseInt(state)==2){
	               if(reason==null ||"".equals(reason.trim())){
	            	   out.print("fail");
		    		}else{
		    			environmentalstrMap.put("state", 2);
		    			environmentalstrMap.put("reason", reason);
		    			String environmentalstr=JSON.toJSONString(environmentalstrMap);
		    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
		    			businessUserinfoApplyModel1.setEnvironmentalstr(environmentalstr);;
		    			businessUserinfoApplyModel1.setUserid(buserid);
		    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
		    			//检验状态
		    			boolean checkstate=checkStateOfUserinfoApply(buserid);
		    			if(checkstate){
		    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
		    			}else{
		    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
		    			}
		    			out.print("success");
		    		}
		    	}
	    	}else if(Integer.parseInt(type)==3){
	    		Map<String,Object> serviceliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessserviceliststr(), Map.class);
	    	  	if(Integer.parseInt(state)==1){
	    			//int servicelistState=(Integer) serviceliststrMap.get("state");
	    			Map<String,String>  map =new HashMap<String,String>();
	    			map.put("businessserviceliststr", JSON.toJSONString(serviceliststrMap.get("businessservicelist")));
	    			map.put("userid", buserid);
	    			businessService.updateBusinessUser(map);
	    			map.remove("businessserviceliststr");
	    			map.put("businessservicelist", JSON.toJSONString(serviceliststrMap.get("businessservicelist")));
	    			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, buserid+USERKEY_USERMAP, map);
	    			serviceliststrMap.put("state", 1);
	    			String businessserviceliststr=JSON.toJSONString(serviceliststrMap);
	    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
	    			businessUserinfoApplyModel1.setBusinessserviceliststr(businessserviceliststr);
	    			businessUserinfoApplyModel1.setUserid(buserid);
	    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
	    			//检验状态
	    			boolean checkstate=checkStateOfUserinfoApply(buserid);
	    			if(checkstate){
	    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
	    			}else{
	    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
	    			}
	    			out.print("success");	
		    	}else if(Integer.parseInt(state)==2){
	               if(reason==null ||"".equals(reason.trim())){
	            	   out.print("fail");
		    		}else{
		    			serviceliststrMap.put("state", 2);
		    			serviceliststrMap.put("reason", reason);
		    			String businessserviceliststr=JSON.toJSONString(serviceliststrMap);
		    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
		    			businessUserinfoApplyModel1.setBusinessserviceliststr(businessserviceliststr);;
		    			businessUserinfoApplyModel1.setUserid(buserid);
		    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
		    			//检验状态
		    			boolean checkstate=checkStateOfUserinfoApply(buserid);
		    			if(checkstate){
		    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
		    			}else{
		    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
		    			}
		    			out.print("success");
		    		}
		    	}
	    	}else if(Integer.parseInt(type)==4){
	    		Map<String,Object> activityliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessactivityliststr(), Map.class);
	    	  	if(Integer.parseInt(state)==1){
	    			//int baseinfoState=(Integer) activityliststrMap.get("state");
	    			Map<String,String>  map =new HashMap<String,String>();
	    			map.put("businessactivityliststr", JSON.toJSONString(activityliststrMap.get("businessactivitylist")));
	    			map.put("userid", buserid);
	    			businessService.updateBusinessUser(map);
	    			map.remove("businessactivityliststr");
	    			map.put("businessactivitylist", JSON.toJSONString(activityliststrMap.get("businessactivitylist")));
	    			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, buserid+USERKEY_USERMAP, map);
	    			activityliststrMap.put("state", 1);
	    			String businessactivityliststr=JSON.toJSONString(activityliststrMap);
	    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
	    			businessUserinfoApplyModel1.setBusinessactivityliststr(businessactivityliststr);
	    			businessUserinfoApplyModel1.setUserid(buserid);
	    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
	    			//检验状态
	    			boolean checkstate=checkStateOfUserinfoApply(buserid);
	    			if(checkstate){
	    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
	    			}else{
	    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
	    			}
	    			out.print("success");	
		    	}else if(Integer.parseInt(state)==2){
	               if(reason==null ||"".equals(reason.trim())){
	            	   out.print("fail");
		    		}else{
		    			activityliststrMap.put("state", 2);
		    			activityliststrMap.put("reason", reason);
		    			String businessactivityliststr=JSON.toJSONString(activityliststrMap);
		    			BusinessUserinfoApplyModel businessUserinfoApplyModel1 = new BusinessUserinfoApplyModel();
		    			businessUserinfoApplyModel1.setBusinessactivityliststr(businessactivityliststr);
		    			businessUserinfoApplyModel1.setUserid(buserid);
		    			businessService.changeUserinfoApplyState(businessUserinfoApplyModel1);
		    			//检验状态
		    			boolean checkstate=checkStateOfUserinfoApply(buserid);
		    			if(checkstate){
		    				businessService.updateUsesrinfoApplyBigState(buserid,0,System.currentTimeMillis());
		    			}else{
		    				businessService.updateUsesrinfoApplyBigState(buserid,1,System.currentTimeMillis());
		    			}
		    			out.print("success");
		    		}
		    	}
	    	}
	
	    	
	    }
		
	}
@RequestMapping(value = "/lookapply", method = RequestMethod.POST)
public void lookapply(Model model, HttpSession session, HttpServletRequest request,String aid,PrintWriter out) throws UnsupportedEncodingException {
	String jsonString = "";
	BusinessApplyModel businessApplyModel=businessService.getApplyByAid(aid);
	String[] mainbrands= businessApplyModel.getMainbrand().split(",");
	String[] rebrands= businessApplyModel.getRecommendbrand().split(",");
	StringBuffer s1= new StringBuffer();
	StringBuffer s2= new StringBuffer();
	StringBuffer s3= new StringBuffer();
	StringBuffer s4= new StringBuffer();
	for(int i=0;i<mainbrands.length;i++){
		BrandparentModel brandparentModel=brandparentService.getBrandparentByid(mainbrands[i]);
		if(i==mainbrands.length-1){
			s1.append(brandparentModel.getName());
		}else{
			s1.append(brandparentModel.getName());
			s1.append(",");
		}
	}
	for(int i=0;i<rebrands.length;i++){
		BrandparentModel brandparentModel=brandparentService.getBrandparentByid(rebrands[i]);
		if(i==mainbrands.length-1){
			s2.append(brandparentModel.getName());
		}else{
			s2.append(brandparentModel.getName());
			s2.append(",");
		}
	}
	List<BusinessServiceModel>  businessServiceModels= JSON.parseArray(businessApplyModel.getBusinessserviceliststr(), BusinessServiceModel.class);
	for(int i=0;i<businessServiceModels.size();i++){
		if(i==businessServiceModels.size()-1){
			s3.append(businessServiceModels.get(i).getName());
		}else{
			s3.append(businessServiceModels.get(i).getName());
			s3.append(",");
		}
	}
	businessApplyModel.setMainbrandNames(s1.toString());
	businessApplyModel.setRecommendbrandNames(s2.toString());
//	businessApplyModel.setBusinessserviceliststr(s3.toString());
	for(BusinessServiceModel bmodel :businessServiceModels ){
		s4.append(String.valueOf(bmodel.getBsid())).append(",");
	}
	businessApplyModel.setServiceids(s4.toString().substring(0, s4.toString().length()-1));
	jsonString=JSON.toJSONString(businessApplyModel);
    out.write(jsonString);
  }
@RequestMapping(value = "/businessuserinfoapplylist", method = RequestMethod.GET)
public void businessuserinfoapplylist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<BusinessUserinfoApplyModel> pageBean=new PageBean<BusinessUserinfoApplyModel>();
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
	int totalCount=businessService.getUserinfoApplyCount();
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<BusinessUserinfoApplyModel> userinfoapplys=businessService.getUserinfoApplys(start,limit,order,orderConditions);
//	for(BusinessApplyModel businessApplyModel :applys){
//		businessApplyModel.setAddtimeString(formatter.format(new Date(businessApplyModel.getAddtime())));
//	}
	for(BusinessUserinfoApplyModel businessUserinfoApplyModel :userinfoapplys){
		businessUserinfoApplyModel.setUpdatetimeString(formatter.format(new Date(businessUserinfoApplyModel.getUpdatetime())));
	}
//	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
//	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
//	model.addAttribute("imggroups", imggroups);
//	model.addAttribute("motoimgs", motoimgs);
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	ArrayList<Integer> typeList =new ArrayList<Integer>();
	typeList.add(0);
	typeList.add(1);

	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("userinfoapplys", userinfoapplys);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/businessuserinfoapplydetail", method = RequestMethod.GET)
public void businessuserinfoapplydetail(Model model, HttpSession session, HttpServletRequest request, String buserid) {

	
	BusinessUserinfoApplyModel businessUserinfoApplyModel=businessService.getBuserinfoApplyBybuserid(buserid);
	Map<String,Object> baseinfostrMap=JSON.parseObject(businessUserinfoApplyModel.getBaseinfostr(), Map.class);
	Map<String,Object> environmentalstrMap=JSON.parseObject(businessUserinfoApplyModel.getEnvironmentalstr(), Map.class);
	Map<String,Object> serviceliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessserviceliststr(), Map.class);
	Map<String,Object> activityliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessactivityliststr(), Map.class);
	int baseinfoState=(Integer) baseinfostrMap.get("state");
	BusinessBaseinfoModel businessBaseinfoModel=JSON.parseObject(JSON.toJSONString(baseinfostrMap.get("baseinfo")), BusinessBaseinfoModel.class);
	int environmentalState=(Integer) environmentalstrMap.get("state");
	BusinessEnvironmentalModel businessEnvironmentalModel=JSON.parseObject(JSON.toJSONString(environmentalstrMap.get("environmental")), BusinessEnvironmentalModel.class);
	int servicelistState=(Integer) serviceliststrMap.get("state");
	List<BusinessServiceModel> businessServiceModellist =JSON.parseArray(JSON.toJSONString(serviceliststrMap.get("businessservicelist")), BusinessServiceModel.class);
	int activitylistState=(Integer) activityliststrMap.get("state");
	List<BusinessActivityModel> businessActivityModellist =JSON.parseArray(JSON.toJSONString(activityliststrMap.get("businessactivitylist")), BusinessActivityModel.class);
	
	//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    List<String>  doorpicslist = new ArrayList<String>();
    List<String>  shoppicslist = new ArrayList<String>();
    if(businessEnvironmentalModel!=null && !"".equals(businessEnvironmentalModel)){
    	if(businessEnvironmentalModel.getDoorpics()!=null && !"".equals(businessEnvironmentalModel.getDoorpics())){
       	 String[] doorpics=businessEnvironmentalModel.getDoorpics().split(",");
       	    for(String s:doorpics){
       	    	doorpicslist.add(s);
       	    }
       }
      
       if(businessEnvironmentalModel.getShoppics()!=null && !"".equals(businessEnvironmentalModel.getShoppics())){
       	 String[] shoppics=businessEnvironmentalModel.getShoppics().split(",");
       	    for(String s:shoppics){
       	    	shoppicslist.add(s);
       	    }
       }
    }
    
   
	model.addAttribute("baseinfoState", baseinfoState);
	model.addAttribute("environmentalState", environmentalState);
	model.addAttribute("servicelistState", servicelistState);
	model.addAttribute("activitylistState", activitylistState);
	model.addAttribute("businessBaseinfoModel", businessBaseinfoModel);
	//model.addAttribute("businessEnvironmentalModel", businessEnvironmentalModel);
	model.addAttribute("businessServiceModellist", businessServiceModellist);
	model.addAttribute("businessActivityModellist", businessActivityModellist);
	model.addAttribute("buserid", buserid);
	model.addAttribute("shoppicslist", shoppicslist);
	model.addAttribute("doorpicslist", doorpicslist);
}
/**
 * 
* <p>Method:checkStateOfUserinfoApply </p>
* <p>Description: 检验userinfoapply的状态</p>
* <p>Return Type: boolean</p>
* @author fanghebin
* @date 2017年4月14日 下午7:51:28
 */
public boolean checkStateOfUserinfoApply(String buserid) {

	
	BusinessUserinfoApplyModel businessUserinfoApplyModel=businessService.getBuserinfoApplyBybuserid(buserid);
	Map<String,Object> baseinfostrMap=JSON.parseObject(businessUserinfoApplyModel.getBaseinfostr(), Map.class);
	Map<String,Object> environmentalstrMap=JSON.parseObject(businessUserinfoApplyModel.getEnvironmentalstr(), Map.class);
	Map<String,Object> serviceliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessserviceliststr(), Map.class);
	Map<String,Object> activityliststrMap=JSON.parseObject(businessUserinfoApplyModel.getBusinessactivityliststr(), Map.class);
	int baseinfoState=(Integer) baseinfostrMap.get("state");
	int environmentalState=(Integer) environmentalstrMap.get("state");
	int servicelistState=(Integer) serviceliststrMap.get("state");
	int activitylistState=(Integer) activityliststrMap.get("state");
	if(baseinfoState !=0 && environmentalState !=0 && servicelistState !=0  && activitylistState !=0 ){
		return true;
	}
	
	return false;
 }
@RequestMapping(value = "/lookApplyNewPage", method = RequestMethod.GET)
public void lookApplyNewPage(Model model, HttpSession session, HttpServletRequest request, String aid) {


	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	model.addAttribute("aid", aid);
	List<BrandparentModel>  brandparentModels = brandparentService.getBrandparentlistSimple();
	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
	model.addAttribute("businessServiceModels", businessServiceModels);
	model.addAttribute("brandparentModels", brandparentModels);

}
@RequestMapping(value = "/updateApply", method = RequestMethod.POST)
@ResponseBody
public void updateApply(Model model, HttpSession session, HttpServletRequest request,String aid,String name,String contactname,String contactphone,String type,String address,String businessarea
		,String staffcount,String age,String license,String other,String province,String city,String longitude,String latitude
		,String mainbrand,String recommendbrand,
		@RequestParam(value = "businessservice_arr[]",required = false)Integer[] businessservice_arr,
		PrintWriter out) throws UnsupportedEncodingException {
	String jsonString = "";
	BusinessApplyModel businessApplyModel = new BusinessApplyModel();
	businessApplyModel.setAid(aid);
	businessApplyModel.setName(name);
	businessApplyModel.setContactname(contactname);
	businessApplyModel.setContactphone(contactphone);
	businessApplyModel.setType(Integer.parseInt(type));
	if(mainbrand!=null && !"".equals(mainbrand)){
		businessApplyModel.setMainbrand(mainbrand);
	}
	businessApplyModel.setAddress(address);
	businessApplyModel.setBusinessarea(Integer.parseInt(businessarea));
	businessApplyModel.setStaffcount(Integer.parseInt(staffcount));
	businessApplyModel.setAge(Integer.parseInt(age));
	if(recommendbrand!=null && !"".equals(recommendbrand)){
		businessApplyModel.setRecommendbrand(recommendbrand);
	}
	businessApplyModel.setLicense(license);
	businessApplyModel.setOther(other);
	businessApplyModel.setProvince(province);
	businessApplyModel.setCity(city);
	businessApplyModel.setLongitude(Double.valueOf(longitude));
	businessApplyModel.setLatitude(Double.valueOf(latitude));
	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
	Map<String,String>  tempmap =new HashMap<String, String>();
	for(BusinessServiceModel m :businessServiceModels){
		tempmap.put(String.valueOf(m.getBsid()), m.getName());
	}
	List<BusinessServiceModel>  list = new ArrayList<BusinessServiceModel>();
	for(int i=0;i<businessservice_arr.length;i++){
		BusinessServiceModel businessServiceModel = new BusinessServiceModel();
		businessServiceModel.setBsid(businessservice_arr[i]);
		businessServiceModel.setName(tempmap.get(String.valueOf(businessservice_arr[i])));
		list.add(businessServiceModel);
	}
	String businessserviceliststr=JSON.toJSONString(list);
	businessApplyModel.setBusinessserviceliststr(businessserviceliststr);
	try {
		businessService.updateApply(businessApplyModel);
		jsonString="success";
	} catch (Exception e) {
	//	System.out.println(e);
		jsonString="fail";
	}

    out.write(jsonString);
  }
@RequestMapping(value = "/businessuserlist", method = RequestMethod.GET)
public void businessuserlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<BusinessUserModel> pageBean=new PageBean<BusinessUserModel>();
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
	int totalCount=businessService.getBuserCount();
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<BusinessUserModel> businessusers=businessService.getBusers(start,limit,order,orderConditions);
	
//	List<BrandparentModel>  brandparentModels = brandparentService.getBrandparentlistSimple();
//	List<BusinessTypeModel> businessTypeModels = businessService.getBusinessTypeList();
//	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
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

	model.addAttribute("pageBean", pageBean);

	
//	model.addAttribute("businessTypeModels", businessTypeModels);
//	model.addAttribute("businessServiceModels", businessServiceModels);
//	model.addAttribute("brandparentModels", brandparentModels);
	model.addAttribute("businessusers", businessusers);
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}
@RequestMapping(value = "/resetBuserPassword", method = RequestMethod.POST)
public void resetBuserPassword(Model model, HttpSession session, HttpServletRequest request, String userid,String password,PrintWriter out) {
    password=MD5.stringToMD5(MD5.stringToMD5(password));
	businessService.resetBuserPassword(userid,password);
	RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "password",password);
   out.print("success");
}
@RequestMapping(value = "/changeToApprove", method = RequestMethod.POST)
public void changeToApprove(Model model, HttpSession session, HttpServletRequest request, String userid,PrintWriter out) throws Exception {
	BusinessUserModel businessUserModel=businessService.getBuserByUsesrid(userid);
	businessUserModel.setIsapprove(1);
	String lbsid=LBSManager.getInstance().synLBSDataToGD(businessUserModel);
	businessService.changeToApprove(userid,lbsid);
	RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "isapprove","1");
	RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "lbsid",lbsid);
	out.print("success");
}

@RequestMapping(value = "/lookUserNewPage", method = RequestMethod.GET)
public void lookUserNewPage(Model model, HttpSession session, HttpServletRequest request, String userid) {

	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	model.addAttribute("userid", userid);
	List<BrandparentModel>  brandparentModels = brandparentService.getBrandparentlistSimple();
	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
	model.addAttribute("businessServiceModels", businessServiceModels);
	model.addAttribute("brandparentModels", brandparentModels);

}

@RequestMapping(value = "/lookuser", method = RequestMethod.POST)
public void lookuser(Model model, HttpSession session, HttpServletRequest request,String userid,PrintWriter out) throws UnsupportedEncodingException {
	String jsonString = "";
	BusinessUserModel businessUserModel=businessService.getBuserByUsesrid(userid);
	String[] mainbrands= businessUserModel.getMainbrand().split(",");
	String[] rebrands= businessUserModel.getRecommendbrand().split(",");
	StringBuffer s1= new StringBuffer();
	StringBuffer s2= new StringBuffer();
	StringBuffer s3= new StringBuffer();
	StringBuffer s4= new StringBuffer();
	for(int i=0;i<mainbrands.length;i++){
		BrandparentModel brandparentModel=brandparentService.getBrandparentByid(mainbrands[i]);
		if(i==mainbrands.length-1){
			s1.append(brandparentModel.getName());
		}else{
			s1.append(brandparentModel.getName());
			s1.append(",");
		}
	}
	for(int i=0;i<rebrands.length;i++){
		BrandparentModel brandparentModel=brandparentService.getBrandparentByid(rebrands[i]);
		if(i==rebrands.length-1){
			s2.append(brandparentModel.getName());
		}else{
			s2.append(brandparentModel.getName());
			s2.append(",");
		}
	}
	List<BusinessServiceModel>  businessServiceModels= JSON.parseArray(businessUserModel.getBusinessserviceliststr(), BusinessServiceModel.class);
	for(int i=0;i<businessServiceModels.size();i++){
		if(i==businessServiceModels.size()-1){
			s3.append(businessServiceModels.get(i).getName());
		}else{
			s3.append(businessServiceModels.get(i).getName());
			s3.append(",");
		}
	}
	businessUserModel.setMainbrandNames(s1.toString());
	businessUserModel.setRecommendbrandNames(s2.toString());
	for(BusinessServiceModel bmodel :businessServiceModels ){
		s4.append(String.valueOf(bmodel.getBsid())).append(",");
	}
	businessUserModel.setServiceids(s4.toString().substring(0, s4.toString().length()-1));
	List<BusinessServiceModel> businessservicelist=JSON.parseArray(businessUserModel.getBusinessserviceliststr(), BusinessServiceModel.class);
	businessUserModel.setBusinessservicelist(businessservicelist);
	String businessactivityliststr = businessUserModel.getBusinessactivityliststr();
	if(businessactivityliststr!=null && !"".equals(businessactivityliststr.trim())){
		List<BusinessActivityModel> businessactivitylist= JSON.parseArray(businessactivityliststr, BusinessActivityModel.class);
		businessUserModel.setBusinessactivitylist(businessactivitylist);
	}
	List<UsecarmaingroupModel> usecarmaingroupModels=businessService.getUsecarmaingroupList(1);
	businessUserModel.setUsecarmaingroupModels(usecarmaingroupModels);
	
	//获取客服id
	String hget = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "kfuseridlist");
	businessUserModel.setKfuseridlist(hget);
	
	jsonString=JSON.toJSONString(businessUserModel);
    out.write(jsonString);
  }

@RequestMapping(value = "/updateBUser", method = RequestMethod.POST)
@ResponseBody
public void updateBUser(Model model, HttpSession session, HttpServletRequest request,String userid,String name,String contactname,String contactphone,String type,String address,String businessarea
		,String staffcount,String age,String license,String other,String province,String city,String longitude,String latitude
		,String mainbrand,String recommendbrand,String officestarttime,String officeendtime,
		String des,String desdetail,String isapprove,String headurl,String lbsid,String state,String buid,
		String doorpics1,String doorpics2,String doorpics3,String doorpics4,String busikeyword, String kfuseridlist,
		String shoppics1,String shoppics2,String shoppics3,String shoppics4,
		String activitytitle1,String activitycontent1,String activitypics11,String activitypics12,String activitypics13,String activitypics14,
		String activitytitle2,String activitycontent2,String activitypics21,String activitypics22,String activitypics23,String activitypics24,
		String activitytitle3,String activitycontent3,String activitypics31,String activitypics32,String activitypics33,String activitypics34,
		String activitytitle4,String activitycontent4,String activitypics41,String activitypics42,String activitypics43,String activitypics44,
		String activitybaid1,String activitybaid2,String activitybaid3,String activitybaid4,
		@RequestParam(value = "businessservice_arr[]",required = false)Integer[] businessservice_arr,
		@RequestParam(value = "businessservice_descarr[]",required = false)String[] businessservice_descarr,
		PrintWriter out) throws UnsupportedEncodingException {
	String jsonString = "";
	BusinessUserModel businessUserModel =new BusinessUserModel();
	businessUserModel.setUserid(userid);
	businessUserModel.setName(name);
	businessUserModel.setContactname(contactname);
	businessUserModel.setContactphone(contactphone);
	businessUserModel.setType(Integer.parseInt(type));
	if(mainbrand!=null && !"".equals(mainbrand)){
		businessUserModel.setMainbrand(mainbrand);
	}
	if(busikeyword!=null && !"".equals(busikeyword)){
		businessUserModel.setBusikeyword(busikeyword);
	}
	if(kfuseridlist!=null && !"".equals(kfuseridlist)){
		businessUserModel.setKfuseridlist(kfuseridlist);
		
	}
	businessUserModel.setAddress(address);
	businessUserModel.setBusinessarea(Integer.parseInt(businessarea));
	businessUserModel.setStaffcount(Integer.parseInt(staffcount));
	businessUserModel.setAge(Integer.parseInt(age));
	if(recommendbrand!=null && !"".equals(recommendbrand)){
		businessUserModel.setRecommendbrand(recommendbrand);
	}
	businessUserModel.setLicense(license);
	businessUserModel.setOther(other);
	businessUserModel.setProvince(province);
	businessUserModel.setCity(city);
	businessUserModel.setLongitude(Double.valueOf(longitude));
	businessUserModel.setLatitude(Double.valueOf(latitude));
	businessUserModel.setLbsid(lbsid);
	List<BusinessServiceModel> businessServiceModels = businessService.getBusinessServiceList();
	Map<String,String>  tempmap =new HashMap<String, String>();
	for(BusinessServiceModel m :businessServiceModels){
		tempmap.put(String.valueOf(m.getBsid()), m.getName());
	}
	List<BusinessServiceModel>  list = new ArrayList<BusinessServiceModel>();
	for(int i=0;i<businessservice_arr.length;i++){
		BusinessServiceModel businessServiceModel = new BusinessServiceModel();
		businessServiceModel.setBsid(businessservice_arr[i]);
		businessServiceModel.setName(tempmap.get(String.valueOf(businessservice_arr[i])));
		businessServiceModel.setContent(String.valueOf(businessservice_descarr[i].trim()));
		list.add(businessServiceModel);
	}
	String businessserviceliststr=JSON.toJSONString(list);
	List<BusinessServiceModel>  businessservicelist =JSON.parseArray(businessserviceliststr, BusinessServiceModel.class);
	businessUserModel.setBusinessservicelist(businessservicelist);
	businessUserModel.setBusinessserviceliststr(businessserviceliststr);
    if(officestarttime!=null && !"".equals(officestarttime.trim())){
    	businessUserModel.setOfficestarttime(officestarttime);
    }
    if(officeendtime!=null && !"".equals(officeendtime.trim())){
    	businessUserModel.setOfficeendtime(officeendtime);
    }
    if(des!=null && !"".equals(des.trim())){
    	businessUserModel.setDes(des);
    }
    if(desdetail!=null && !"".equals(desdetail.trim())){
    	businessUserModel.setDesdetail(desdetail);
    }
    if(headurl!=null && !"".equals(headurl.trim())){
    	businessUserModel.setHeadurl(headurl);
    }
    businessUserModel.setIsapprove(Integer.parseInt(isapprove));
    StringBuffer doorpicsSB = new StringBuffer();
    StringBuffer shoppicsSB = new StringBuffer();
    if(doorpics1!=null && !"".equals(doorpics1.trim())){
    	doorpicsSB.append(doorpics1).append(",");
    }
    if(doorpics2!=null && !"".equals(doorpics2.trim())){
    	doorpicsSB.append(doorpics2).append(",");
    }
    if(doorpics3!=null && !"".equals(doorpics3.trim())){
    	doorpicsSB.append(doorpics3).append(",");
    }
    if(doorpics4!=null && !"".equals(doorpics4.trim())){
    	doorpicsSB.append(doorpics4).append(",");
    }
    if(doorpicsSB!=null && !"".equals(doorpicsSB.toString())){
    	businessUserModel.setDoorpics(doorpicsSB.substring(0,doorpicsSB.length()-1));
    }
    if(shoppics1!=null && !"".equals(shoppics1.trim())){
    	shoppicsSB.append(shoppics1).append(",");
    }
    if(shoppics2!=null && !"".equals(shoppics2.trim())){
    	shoppicsSB.append(shoppics2).append(",");
    }
    if(shoppics3!=null && !"".equals(shoppics3.trim())){
    	shoppicsSB.append(shoppics3).append(",");
    }
    if(shoppics4!=null && !"".equals(shoppics4.trim())){
    	shoppicsSB.append(shoppics4).append(",");
    }
    if(shoppicsSB!=null && !"".equals(shoppicsSB.toString())){
    	businessUserModel.setShoppics(shoppicsSB.substring(0,shoppicsSB.length()-1));
    }
    
    StringBuffer activitypicsSB1 = new StringBuffer();
    StringBuffer activitypicsSB2 = new StringBuffer();
    StringBuffer activitypicsSB3 = new StringBuffer();
    StringBuffer activitypicsSB4 = new StringBuffer();
    if(activitypics11!=null && !"".equals(activitypics11.trim())){
    	activitypicsSB1.append(activitypics11).append(",");
    }
    if(activitypics12!=null && !"".equals(activitypics12.trim())){
    	activitypicsSB1.append(activitypics12).append(",");
    }
    if(activitypics13!=null && !"".equals(activitypics13.trim())){
    	activitypicsSB1.append(activitypics13).append(",");
    }
    if(activitypics14!=null && !"".equals(activitypics14.trim())){
    	activitypicsSB1.append(activitypics14).append(",");
    }
    if(activitypics21!=null && !"".equals(activitypics21.trim())){
    	activitypicsSB2.append(activitypics21).append(",");
    }
    if(activitypics22!=null && !"".equals(activitypics22.trim())){
    	activitypicsSB2.append(activitypics22).append(",");
    }
    if(activitypics23!=null && !"".equals(activitypics23.trim())){
    	activitypicsSB2.append(activitypics23).append(",");
    }
    if(activitypics24!=null && !"".equals(activitypics24.trim())){
    	activitypicsSB2.append(activitypics24).append(",");
    }
    if(activitypics31!=null && !"".equals(activitypics31.trim())){
    	activitypicsSB3.append(activitypics31).append(",");
    }
    if(activitypics32!=null && !"".equals(activitypics32.trim())){
    	activitypicsSB3.append(activitypics32).append(",");
    }
    if(activitypics33!=null && !"".equals(activitypics33.trim())){
    	activitypicsSB3.append(activitypics33).append(",");
    }
    if(activitypics34!=null && !"".equals(activitypics34.trim())){
    	activitypicsSB3.append(activitypics34).append(",");
    }
    if(activitypics41!=null && !"".equals(activitypics41.trim())){
    	activitypicsSB4.append(activitypics41).append(",");
    }
    if(activitypics42!=null && !"".equals(activitypics42.trim())){
    	activitypicsSB4.append(activitypics42).append(",");
    }
    if(activitypics43!=null && !"".equals(activitypics43.trim())){
    	activitypicsSB4.append(activitypics43).append(",");
    }
    if(activitypics44!=null && !"".equals(activitypics44.trim())){
    	activitypicsSB4.append(activitypics44).append(",");
    }
    List<BusinessActivityModel>  activitylist = new ArrayList<BusinessActivityModel>();
    if(activitybaid1!=null && !"".equals(activitybaid1.trim())){
    	BusinessActivityModel businessActivityModel = new BusinessActivityModel();
    	businessActivityModel.setBaid(activitybaid1);
    	businessActivityModel.setTitle(activitytitle1);
    	businessActivityModel.setContent(activitycontent1);
    	businessActivityModel.setBuserid(userid);
    	businessActivityModel.setPics(activitypicsSB1.substring(0,activitypicsSB1.length()-1));
    	activitylist.add(businessActivityModel);
    }
    if(activitybaid2!=null && !"".equals(activitybaid2.trim())){
    	BusinessActivityModel businessActivityModel = new BusinessActivityModel();
    	businessActivityModel.setBaid(activitybaid2);
    	businessActivityModel.setTitle(activitytitle2);
    	businessActivityModel.setContent(activitycontent2);
    	businessActivityModel.setBuserid(userid);
    	businessActivityModel.setPics(activitypicsSB2.substring(0,activitypicsSB2.length()-1));
    	activitylist.add(businessActivityModel);
    }
    if(activitybaid3!=null && !"".equals(activitybaid3.trim())){
    	BusinessActivityModel businessActivityModel = new BusinessActivityModel();
    	businessActivityModel.setBaid(activitybaid3);
    	businessActivityModel.setTitle(activitytitle3);
    	businessActivityModel.setContent(activitycontent3);
    	businessActivityModel.setBuserid(userid);
    	businessActivityModel.setPics(activitypicsSB3.substring(0,activitypicsSB3.length()-1));
    	activitylist.add(businessActivityModel);
    }
    if(activitybaid4!=null && !"".equals(activitybaid4.trim())){
    	BusinessActivityModel businessActivityModel = new BusinessActivityModel();
    	businessActivityModel.setBaid(activitybaid4);
    	businessActivityModel.setTitle(activitytitle4);
    	businessActivityModel.setContent(activitycontent4);
    	businessActivityModel.setBuserid(userid);
    	businessActivityModel.setPics(activitypicsSB4.substring(0,activitypicsSB4.length()-1));
    	activitylist.add(businessActivityModel);
    }
    if(activitylist!=null && activitylist.size()>0){
    	String businessactivityliststr=JSON.toJSONString(activitylist);
    	List<BusinessActivityModel> businessactivitylist = JSON.parseArray(businessactivityliststr, BusinessActivityModel.class);
    	businessUserModel.setBusinessactivitylist(businessactivitylist);
    	businessUserModel.setBusinessactivityliststr(businessactivityliststr);
    }
    
    businessUserModel.setState(Integer.parseInt(state));
    businessUserModel.setBuid(Integer.parseInt(buid));
	try {
		//先拿到客服
		String hget = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "kfuseridlist");
		
		Map<String,String> updatemap=BusinessUserModel.convertToMap(businessUserModel);
		updatemap.put("businessactivityliststr", updatemap.get("businessactivitylist"));
		updatemap.put("businessserviceliststr", updatemap.get("businessservicelist"));
		//高德
	//	logger.info("request data:[businessUserModel:"+businessUserModel.toString()+"]");
		String newlbsid=LBSManager.getInstance().synLBSDataToGD(businessUserModel);
	//	logger.info("response data:[lbsid:"+newlbsid+"]");
		updatemap.put("lbsid", newlbsid);
		businessService.updateBusinessUser(updatemap);
		updatemap.remove("businessactivityliststr");
		updatemap.remove("businessserviceliststr");
		updatemap.remove("channel");
		updatemap.remove("buid");
		updatemap.remove("businesscode");
		updatemap.remove("password");
		updatemap.remove("usercount");
		updatemap.remove("mobileno");
		updatemap.remove("addtime");
		updatemap.remove("aid");
		updatemap.remove("reason");
		updatemap.remove("scorecount");
		updatemap.remove("token");
		updatemap.remove("approvepic");
		if(updatemap.get("businessactivitylist")==null ){
			updatemap.put("businessactivitylist", "");
		}
		RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP,updatemap);
		
		//删除商家下所有活动重新添加
		boolean flag = businessService.deleteAllBusinessActivity(userid);
		if(flag){
			if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, userid+USERKEY_ACTIVITY)){
				long count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, userid+USERKEY_ACTIVITY);
				Set<String> set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, userid+USERKEY_ACTIVITY, 0, count);
				for(String s:set){
					RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_USER, s+USERKEY_ACTIVITYMAP);
				}
				RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_USER, userid+USERKEY_ACTIVITY);
			}
			
			 if(activitylist!=null && activitylist.size()>0){
			    	String businessactivityliststr=JSON.toJSONString(activitylist);
			    	List<BusinessActivityModel> businessActivityModels = JSON.parseArray(businessactivityliststr, BusinessActivityModel.class);
			    	for(BusinessActivityModel businessActivityModel :businessActivityModels){
						businessActivityModel.buserid=userid;
						boolean b = businessService.insertOrUpdateBusinessActivity(businessActivityModel);
						if(b){
							RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, userid+USERKEY_ACTIVITY, System.currentTimeMillis(), businessActivityModel.baid);
							Map<String,String> modelmap = BusinessActivityModel.convertToMap(businessActivityModel);
							RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_USER, businessActivityModel.baid+USERKEY_ACTIVITYMAP, modelmap);
							
						}
					}
			    }
		
		}
		
		
		//删除客服时     暂不对删除商家下已有客服的动态进行处理
		if(StringUtils.isNotBlank(kfuseridlist)){
			
			if(StringUtils.isNotBlank(hget)){
				String[] hkfusers = hget.split(",");
				if(hkfusers!=null && hkfusers.length>0){
					for(int i=0;i<hkfusers.length;i++){
						RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, hkfusers[i]+USERKEY_BUSINESSUSERLIST, userid);
					}
				}
			}
			
			String[] kfusers = kfuseridlist.split(",");
			if(kfusers!=null && kfusers.length>0){
				for(int i=0;i<kfusers.length;i++){
					RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_USER, kfusers[i]+USERKEY_BUSINESSUSERLIST, System.currentTimeMillis(), userid);
				}
			}
		}
		
		
		
		
		jsonString="success";
	} catch (Exception e) {
	//	System.out.println(e);
		jsonString="fail";
	}
    out.write(jsonString);
  }

@RequestMapping(value = "/activitytoUsecarmain", method = RequestMethod.POST)
public void activitytoUsecarmain(Model model, HttpSession session, HttpServletRequest request, String baid,
		String groupid,String orderindex, String content,String title,String pics1,String pics2,String pics3,String pics4,String buserid, PrintWriter out) throws Exception {
	UsecarmaingroupModel usecarmaingroupModel=businessService.getUsecarmaingroupByid(groupid);
	    String contentstr="";
	    StringBuffer picsSB = new StringBuffer();
	    if(pics1!=null && !"".equals(pics1.trim())){
	    	picsSB.append(pics1).append(",");
	    }
	    if(pics2!=null && !"".equals(pics2.trim())){
	    	picsSB.append(pics2).append(",");
	    }
	    if(pics3!=null && !"".equals(pics3.trim())){
	    	picsSB.append(pics3).append(",");
	    }
	    if(pics4!=null && !"".equals(pics4.trim())){
	    	picsSB.append(pics4).append(",");
	    }
	if(usecarmaingroupModel.getType()==2){//横幅活动
		BusinessActivityModel businessActivityModel = new BusinessActivityModel();
		businessActivityModel.setBaid(baid);
		businessActivityModel.setTitle(title);
		businessActivityModel.setContent(content);
		businessActivityModel.setPics(picsSB.substring(0, picsSB.length()-1));
		businessActivityModel.setBuserid(buserid);
		contentstr=JSON.toJSONString(businessActivityModel);
	}else{
		MallProductModel mallProductModel = new MallProductModel();
		mallProductModel.setId(MbUtil.getUUID());
		mallProductModel.setTitle(title);
		mallProductModel.setDes(content);
		String pic =picsSB.substring(0, picsSB.toString().indexOf(","));
		mallProductModel.setPicurl(pic);
		mallProductModel.setSource(4);
		mallProductModel.setBuserid(buserid);
		mallProductModel.setBaid(baid);
		contentstr=JSON.toJSONString(mallProductModel);
	}

	UsecarmainModel usecarmainModel = new UsecarmainModel();
	usecarmainModel.setGroupid(Integer.parseInt(groupid));
	usecarmainModel.setContentstr(contentstr);
	usecarmainModel.setOrderindex(Long.valueOf(orderindex));
	usecarmainModel.setType(1);
	businessService.addtoUsecarmain(usecarmainModel);
	out.print("success");
}
@RequestMapping(value = "/usecarmainlist", method = RequestMethod.GET)
public void usecarmainlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,int groupid) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<UsecarmainModel> pageBean=new PageBean<UsecarmainModel>();
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
	int totalCount=businessService.getUsecarmainCount(groupid);
	
	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	List<UsecarmainModel> usecarmainModels=businessService.getUsecarmainlist(start,limit,order,orderConditions,groupid);
	for(UsecarmainModel usecarmainModel :usecarmainModels){
		if(usecarmainModel.getGrouptype()==2){
			BusinessActivityModel businessActivityModel=JSON.parseObject(usecarmainModel.getContentstr(), BusinessActivityModel.class);
			usecarmainModel.setBusinessActivityModel(businessActivityModel);
		}else if(usecarmainModel.getGrouptype()==3){
			SecondCarModel secondCarModel=JSON.parseObject(usecarmainModel.getContentstr(), SecondCarModel.class);
			usecarmainModel.setSecondCarModel(secondCarModel);
		}else{
			MallProductModel mallProductModel=JSON.parseObject(usecarmainModel.getContentstr(), MallProductModel.class);
			usecarmainModel.setMallProductModel(mallProductModel);
		}
		
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
	
	List<UsecarmaingroupModel> usecarmaingroupModels=businessService.getUsecarmaingroupList(4);
	model.addAttribute("usecarmaingroupModels", usecarmaingroupModels);
	
	model.addAttribute("usecarmainModels", usecarmainModels);
	model.addAttribute("limit", limit);
	model.addAttribute("groupid",groupid);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
}

@RequestMapping(value = "/deleteusecarmain", method = RequestMethod.POST)
public void deleteusecarmain(Model model, HttpSession session, HttpServletRequest request, String id,PrintWriter out) throws Exception {
	try {
		businessService.deleteUsecarmainByid(id);
		out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
}

@RequestMapping(value = "/changeorderindex", method = RequestMethod.POST)
public void changeorderindex(Model model, HttpSession session, HttpServletRequest request, String id,String orderindex,PrintWriter out) throws Exception {
	try {
		businessService.updateUsecarmainOrderindex(id,orderindex);
		out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
}
@RequestMapping(value = "/uploadapprovepic", method = RequestMethod.POST)
public void uploadapprovepic(Model model, HttpSession session, HttpServletRequest request, String userid,String approvepic,PrintWriter out) throws Exception {
	try {
		businessService.uploadapprovepic(userid,approvepic);
		RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, userid+USERKEY_USERMAP, "approvepic",approvepic);
		out.print("success");
	} catch (Exception e) {
		out.print("fail");
	}
}

@RequestMapping(value = "/businesscommentlist", method = RequestMethod.GET)
public void businesscommentlist(Model model, HttpSession session, HttpServletRequest request, String userGuid) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	List<BusinessUserModel> list = businessService.getBusersBystate(1);
	model.addAttribute("buserlist", list);
	model.addAttribute("userGuid", userGuid);
}
@RequestMapping(value = "/businesscommentlistinfo", method = RequestMethod.GET)
public ModelAndView businesscommentlistinfo(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,String buserid) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<BusinessCommentModel> pageBean=new PageBean<BusinessCommentModel>();
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
	
	if(!RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_USER, buserid + USERKEY_COMMENT)){
		
	}else{
		int totalCount=(int)RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_USER, buserid + USERKEY_COMMENT);
		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		
		}
		pageBean.setTotalPage(totalPage); 
		
		int start= (page-1)*limit;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		
		List<BusinessCommentModel> commentlsit = new ArrayList<BusinessCommentModel>();
		Set<String> tempSet = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_USER, buserid + USERKEY_COMMENT, start, start+limit);
		for(String s: tempSet){
			Map<String,String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, s + USERKEY_COMMENTMAP);
			BusinessCommentModel businessCommentModel=BusinessCommentModel.convertToModel(map);
			String userName=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, businessCommentModel.getUserid()+USERKEY_USER, "nickname");
			String tuserName=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, businessCommentModel.getTuserid()+USERKEY_USERMAP, "name");
			carModel carmodel= carService.getCarModelById(String.valueOf(businessCommentModel.getModelid()));
			businessCommentModel.setTuserName(tuserName);
			businessCommentModel.setUserName(userName);
			businessCommentModel.setModelName(carmodel.getName());
			commentlsit.add(businessCommentModel);
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
		
		model.addAttribute("commentlsit", commentlsit);
		model.addAttribute("limit", limit);
		model.addAttribute("buserid",buserid);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
	}
	ModelAndView modelAndView = new ModelAndView();
	modelAndView.setViewName("/businessmanage/businesscommentlistinfo");
	return modelAndView;
}
@RequestMapping(value = "/deletbusercomment", method = RequestMethod.POST)
public void deletbusercomment(Model model, HttpSession session, HttpServletRequest request, String cid,String buserid,PrintWriter out) {

	Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_USER, cid+ USERKEY_COMMENTMAP);
	String score= map.get("score")==null ? "" :map.get("score");
	String pics= map.get("pics")==null ? "" :map.get("pics");
	if(!"".equals(score)){
		if(Integer.parseInt(score)<= 3){
			RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, buserid + USERKEY_BADCOMMENT, cid);
		}else if(Integer.parseInt(score)== 5){
			RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, buserid + USERKEY_GOODCOMMENT, cid);
		}
	}
	if(!"".equals(pics)){
		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, buserid + USERKEY_PICCOMMENT, cid);
	}
	RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_USER, buserid + USERKEY_COMMENT, cid);
	RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_USER, cid+ USERKEY_COMMENTMAP);
	
	String usercount = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, buserid + USERKEY_USERMAP, "usercount");
	String scorecount = RedisManager.getInstance().hget(Consts.REDIS_SCHEME_USER, buserid + USERKEY_USERMAP, "scorecount");
	RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, buserid + USERKEY_USERMAP, "usercount", String.valueOf(Integer.parseInt(usercount)-1));
	RedisManager.getInstance().hset(Consts.REDIS_SCHEME_USER, buserid + USERKEY_USERMAP, "scorecount", String.valueOf(Integer.parseInt(scorecount)-Integer.parseInt(score)));
	businessService.updateBuserCommentScore(buserid,score);
	businessService.deletbusercomment(cid);
	out.write("success");
}
}
