package com.motoband.admanage;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONObject;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.Boxlist;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.news.topic;
import com.motoband.util.BeanUtils;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

@Controller
@RequestMapping(value = "/admanage")
public class AdControll {
	@Autowired
	private AdService adService;
	@Autowired
	private boxService boxService;
	
	public static final String MAIN_ADPOOL = "main_adpool";
	public static final String FOCUS_ADPOOL = "focus_adpool";
	public static final String MAINSEARCH_ADPOOL = "mainsearch_adpool";
	public static final String USECARMAINSEARCH_ADPOOL = "usecarmainsearch_adpool";
	public static final String TOOL_ADPOOL = "tool_adpool";
	public static final String ADPOOLIMGMAP = "_adpoolimgmap";
	
	
	public static final String RUNKEY_ADTYPELIST_UP = "_adtypelist_up";
	public static final String RUNKEY_ADTYPELIST_DOWN = "_adtypelist_down";
	public static final String RUNKEY_ADMAP = "_admap";
	public static final String RUNKEY_ADIMGLIST = "_adimglist";
	public static final String RUNKEY_ADIMGMAP = "_adimgmap";
	
	@RequestMapping(value = "/adlist", method = RequestMethod.GET)
	public void adlist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<AdModel> pageBean=new PageBean<AdModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount=adService.getAdListCount();

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<AdModel> adlist =adService.getAdList(userGuid,start,limit,order);
		
		ArrayList<Integer> limitList =new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		if(adlist !=null && adlist.size()>0){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(AdModel adModel:adlist){
				String starttimeString = formatter.format(adModel.getStarttime());
				String endtimeString = formatter.format(adModel.getEndtime());
				adModel.setStarttimeString(starttimeString);
				adModel.setEndtimeString(endtimeString);
			}
		}
		ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
		ArrayList<imggroup> imggroups = boxService.getImgGroupList();
		model.addAttribute("imggroups", imggroups);
		model.addAttribute("motoimgs", motoimgs);
		model.addAttribute("adlist", adlist);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
	}
	@RequestMapping(value = "/editAd", method = RequestMethod.POST)
	public void editAd(Model model, HttpSession session, HttpServletRequest request, int adid,PrintWriter out) {
		AdModel ad =null;
		if(adid>0){
        	ad =adService.editAd(adid);	
        }
		String jsonObjectStr =null;
		if(ad !=null){
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String starttimeString = formatter.format(ad.getStarttime());
			String endtimeString = formatter.format(ad.getEndtime());
			ad.setStarttimeString(starttimeString);
			ad.setEndtimeString(endtimeString);
			jsonObjectStr = JSONObject.toJSONString(ad);
		}
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/delAd", method = RequestMethod.POST)
	public void delAd(Model model, HttpSession session, HttpServletRequest request, int adid,PrintWriter out) {
		
		if(adid>0){
        	adService.deleteAdById(adid);
        	out.write("success");
        }
		
	}
	@RequestMapping(value = "/updateAd", method = RequestMethod.POST)
	public void updateAd(Model model, HttpSession session, HttpServletRequest request,
			String adid,String adtitle,String adtype,String starttime,String endtime,String state,PrintWriter out) {
	      AdModel adModel = new AdModel();
	      adModel.setAdid(Integer.parseInt(adid));
	      adModel.setAdtitle(adtitle);
	      adModel.setAdtype(Integer.parseInt(adtype));
	      adModel.setState(Integer.parseInt(state));
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	      
	      long startTime=0l;
	      long endTime=0l;
	      try {
	    	  startTime = formatter.parse(starttime).getTime();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	      try {
	    	  endTime = formatter.parse(endtime).getTime();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	      adModel.setStarttime(startTime);
	      adModel.setEndtime(endTime);
	      
	      adService.updateAd(adModel);
	      out.write("success");
        }
		
	@RequestMapping(value = "/insertAdImg", method = RequestMethod.POST)
	public void insertAdImg(Model model, HttpSession session, HttpServletRequest request,
			String adid,String linktype,String imgurl,String linkurl,String gpid,String keyword,String mallurl,String boxid,String orderindex, String miniprogramid,PrintWriter out) {
	      AdimgModel adimgModel = new AdimgModel();
	      adimgModel.setAdid(Integer.parseInt(adid));
	      adimgModel.setLinktype(Integer.parseInt(linktype));
	      adimgModel.setImgurl(imgurl);
	      adimgModel.setLinkurl(linkurl);
	      adimgModel.setMiniprogramid(miniprogramid);
	      if(gpid !=null && gpid !=""){
	    	  adimgModel.setGpid(Integer.parseInt(gpid)); 
	      }
	      
	      adimgModel.setKeyword(keyword);
	      adimgModel.setMallurl(mallurl);
	      if(boxid !=null && boxid !=""){
	    	  adimgModel.setBoxid(boxid); 
	      }
	    
	      adimgModel.setOrderindex(Integer.parseInt(orderindex));
	     
	      adService.insertAdImg(adimgModel);
	      out.write("success");
        }
	@RequestMapping(value = "/addAd", method = RequestMethod.POST)
	public void addAd(Model model, HttpSession session, HttpServletRequest request,
			String adtitle,String adtype,String starttime,String endtime,String state,PrintWriter out) {
	      AdModel adModel = new AdModel();
	      adModel.setAdtitle(adtitle);
	      adModel.setAdtype(Integer.parseInt(adtype));
	      adModel.setState(Integer.parseInt(state));
	      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	      
	      long startTime=0l;
	      long endTime=0l;
	      try {
	    	  startTime = formatter.parse(starttime).getTime();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	      try {
	    	  endTime = formatter.parse(endtime).getTime();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	      adModel.setStarttime(startTime);
	      adModel.setEndtime(endTime);
	      
	      adService.addAd(adModel);
	      out.write("success");
        }
	@RequestMapping(value = "/getAdimgByadid", method = RequestMethod.POST)
	public void getAdimgByadid(Model model, HttpSession session, HttpServletRequest request, int adid,PrintWriter out) {
		List<AdimgModel>  list = new ArrayList<AdimgModel>();
		if(adid>0){
        	list =adService.getAdimgByadid(adid);	
        }
		
		 String	jsonObjectStr = JSONObject.toJSONString(list);
		
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/delAdimg", method = RequestMethod.POST)
	public void delAdimg(Model model, HttpSession session, HttpServletRequest request, int adimgid,PrintWriter out) {
		
		if(adimgid>0){
        	adService.deleteAdimgById(adimgid);
        	out.write("success");
        }
		
	}
	@RequestMapping(value = "/getAdimgByadImgid", method = RequestMethod.POST)
	public void getAdimgByadImgid(Model model, HttpSession session, HttpServletRequest request, int adimgid,PrintWriter out) {
		AdimgModel adimgModel   = new AdimgModel();
		if(adimgid>0){
			adimgModel =adService.getAdimgByadImgid(adimgid);	
        }
		
		 String	jsonObjectStr = JSONObject.toJSONString(adimgModel);
		
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/updateAdImg", method = RequestMethod.POST)
	public void updateAdImg(Model model, HttpSession session, HttpServletRequest request,
			String adimgid,String adid,String linktype,String imgurl,String linkurl,String gpid,String keyword,String mallurl,String boxid,String orderindex,String miniprogramid, PrintWriter out) {
	      AdimgModel adimgModel = new AdimgModel();
	      adimgModel.setAdimgid(Integer.parseInt(adimgid));
	      adimgModel.setAdid(Integer.parseInt(adid));
	      adimgModel.setLinktype(Integer.parseInt(linktype));
	      adimgModel.setImgurl(imgurl);
	      adimgModel.setLinkurl(linkurl);
	      adimgModel.setMiniprogramid(miniprogramid);
	      if(gpid !=null && gpid !=""){
	    	  adimgModel.setGpid(Integer.parseInt(gpid)); 
	      }
	      
	      adimgModel.setKeyword(keyword);
	      adimgModel.setMallurl(mallurl);
	      if(boxid !=null && boxid !=""){
	    	  adimgModel.setBoxid(boxid); 
	      }
	    
	      adimgModel.setOrderindex(Integer.parseInt(orderindex));
	     
	      adService.updateAdImg(adimgModel);
	      out.write("success");
        }
	
	@RequestMapping(value = "/getmotoGp", method = RequestMethod.POST)
	public void getmotoGp(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {
		List<MotobandGPModel>  list = new ArrayList<MotobandGPModel>();
		
        	list =adService.getmotoGp();	
        
		
		 String	jsonObjectStr = JSONObject.toJSONString(list);
		
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/getmotoKeywords", method = RequestMethod.POST)
	public void getmotoKeywords(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {
		List<topic>  list = new ArrayList<topic>();
		
        	list =adService.getmotoKeywords();	
        
		
		 String	jsonObjectStr = JSONObject.toJSONString(list);
		
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/getmotoBoxid", method = RequestMethod.POST)
	public void getmotoBoxid(Model model, HttpSession session, HttpServletRequest request,PrintWriter out) {
		List<Boxlist>  list = new ArrayList<Boxlist>();
		
        	list =adService.getmotoBoxid();	
        
		
		 String	jsonObjectStr = JSONObject.toJSONString(list);
		
		out.write(jsonObjectStr);
	}
	
	
	@RequestMapping(value = "/adpoollist", method = RequestMethod.GET)
	public void adpoollist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,int adtype) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<AdPoolImgModel> pageBean=new PageBean<AdPoolImgModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
   
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		String poolname="";
		if(adtype==0){
			poolname=MAIN_ADPOOL;
		}else if(adtype==1){
			poolname=FOCUS_ADPOOL;
		}else if(adtype==2){
			poolname=MAINSEARCH_ADPOOL;
		}else if(adtype==3){
			poolname=USECARMAINSEARCH_ADPOOL;
		}else if(adtype==4){
			poolname=TOOL_ADPOOL;
		}else{
			poolname=MAIN_ADPOOL;
		}
		
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, poolname)){
			totalCount=(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, poolname);
		}

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<AdPoolImgModel> adlist= new ArrayList<AdPoolImgModel>();
		Set<String> set=null;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, poolname)){
			if(totalCount>0){
				set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_RUN, poolname, start, start+limit-1);
				for(String s :set){
					Map<String,String>  temp= RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, s+ADPOOLIMGMAP);
					AdPoolImgModel adPoolImgModel = AdPoolImgModel.convertToModel(temp);
					adlist.add(adPoolImgModel);
				}
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
		model.addAttribute("adPoolImgModels", adlist);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("adtype", adtype);
	}
	
	@RequestMapping(value = "/getAdpoolimgidById", method = RequestMethod.POST)
	public void getAdpoolimgidById(Model model, HttpSession session, HttpServletRequest request,PrintWriter out,String adpoolimgid) {
		String	jsonObjectStr = ""; 
		if(adpoolimgid!=null&& !"".equals(adpoolimgid)){
	    	 if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, adpoolimgid+ADPOOLIMGMAP)){
	    		 Map<String, String> tempmap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, adpoolimgid+ADPOOLIMGMAP);
	    		 AdPoolImgModel adPoolImgModel = AdPoolImgModel.convertToModel(tempmap);
	    		 if(adPoolImgModel!=null){
	    			 jsonObjectStr =  JSONObject.toJSONString(adPoolImgModel);
	    		 }
	    	 }
	     }
		 
		
		out.write(jsonObjectStr);
	}
	@RequestMapping(value = "/insertOrUpdateAdpoolImg", method = RequestMethod.POST)
	public void insertOrUpdateAdpoolImg(Model model, HttpSession session, HttpServletRequest request,PrintWriter out,String adpoolimgid,
			String linktype,String imgurl,String linkurl,String gpid,String keyword,String mallurl,String boxid,String secondcarid,
			String orderindex,String state,String adtype,String oldadtype,String price,String adtitle,String addes) {
		String	jsonObjectStr = ""; 
		String poolname="";
		if(Integer.parseInt(adtype)==0){
			poolname=MAIN_ADPOOL;
		}else if(Integer.parseInt(adtype)==1){
			poolname=FOCUS_ADPOOL;
		}else if(Integer.parseInt(adtype)==2){
			poolname=MAINSEARCH_ADPOOL;
		}else if(Integer.parseInt(adtype)==3){
			poolname=USECARMAINSEARCH_ADPOOL;
		}else if(Integer.parseInt(adtype)==4){
		    poolname=TOOL_ADPOOL;
	    }
		 AdPoolImgModel adPoolImgModel=new AdPoolImgModel();
		if(adpoolimgid==null||"".equals(adpoolimgid)){
			adPoolImgModel.setAdpoolimgid(MbUtil.getUUID());		
	     }else{
	    	adPoolImgModel.setAdpoolimgid(adpoolimgid);
	     }
		adPoolImgModel.setLinktype(Integer.parseInt(linktype));
		if(imgurl!=null && !"".equals(imgurl)){
			adPoolImgModel.setImgurl(imgurl);
		}
		if(linkurl!=null && !"".equals(linkurl)){
			adPoolImgModel.setLinkurl(linkurl);
		}
		if(gpid!=null && !"".equals(gpid)){
			adPoolImgModel.setGpid(Integer.parseInt(gpid));
		}
		if(keyword!=null && !"".equals(keyword)){
			adPoolImgModel.setKeyword(keyword);
		}
		if(mallurl!=null && !"".equals(mallurl)){
			adPoolImgModel.setMallurl(mallurl);
		}
		if(boxid!=null && !"".equals(boxid)){
			adPoolImgModel.setBoxid(boxid);
		}
		if(secondcarid!=null && !"".equals(secondcarid)){
			adPoolImgModel.setSecondcarid(secondcarid);
		}
		if(adtitle!=null && !"".equals(adtitle)){
			adPoolImgModel.setAdtitle(adtitle);
		}
		if(price!=null && !"".equals(price)){
			adPoolImgModel.setPrice(price);
		}
		if(addes!=null && !"".equals(addes)){
			adPoolImgModel.setAddes(addes);
		}
		adPoolImgModel.setOrderindex(Integer.parseInt(orderindex));
		adPoolImgModel.setState(Integer.parseInt(state));
		adPoolImgModel.setAdtype(Integer.parseInt(adtype));
		try {
			if(oldadtype!=null && !"".equals(oldadtype)){
				String oldpoolname="";
				if(Integer.parseInt(oldadtype)==0){
					oldpoolname=MAIN_ADPOOL;
				}else if(Integer.parseInt(oldadtype)==1){
					oldpoolname=FOCUS_ADPOOL;
				}else if(Integer.parseInt(oldadtype)==2){
					oldpoolname=MAINSEARCH_ADPOOL;
				}else if(Integer.parseInt(oldadtype)==3){
					oldpoolname=USECARMAINSEARCH_ADPOOL;
				}else if(Integer.parseInt(oldadtype)==4){
				    oldpoolname=TOOL_ADPOOL;
			}
				RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, oldpoolname, adPoolImgModel.adpoolimgid);
				RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN, adPoolImgModel.adpoolimgid+ADPOOLIMGMAP);
			}
//			if(adPoolImgModel.getState()==1){
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, poolname, System.currentTimeMillis(), adPoolImgModel.adpoolimgid);
				Map<String, String> map = AdPoolImgModel.convertToMap(adPoolImgModel);
				RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, adPoolImgModel.adpoolimgid+ADPOOLIMGMAP, map);
//			}
			
			jsonObjectStr="success";
		} catch (Exception e) {
			e.printStackTrace();
			jsonObjectStr="fail";
		}
		
		out.write(jsonObjectStr);
	}
//	public static void main(String[] args) {
//		Map<String, String> tempmap = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, "702849B54EFB4254949CFE3D0F6E8BD2"+ADPOOLIMGMAP);
//		for(int i=0;i<10;i++){
//			tempmap.put("adtitle", "工具广告呀"+i+i+i+i);
//			tempmap.put("price", "800"+i+i);
//			tempmap.put("orderindex", "5"+i);
//			tempmap.put("adpoolimgid", MbUtil.getUUID());
//			
//			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, TOOL_ADPOOL, System.currentTimeMillis(), tempmap.get("adpoolimgid"));
//			RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, tempmap.get("adpoolimgid")+ADPOOLIMGMAP, tempmap);
//		}
//	}
	
	
	@RequestMapping(value = "/advertisinglist", method = RequestMethod.GET)
	public void advertisinglist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int order,int adtype) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<AdvertisingModel> pageBean=new PageBean<AdvertisingModel>();
		if(page==0){
			page=1;
		}
      if(limit==0){
     	limit=20;
       }
      
      String poolname="";
      if(order==0){
    	  poolname=adtype+RUNKEY_ADTYPELIST_DOWN;
      }else{
    	  poolname=adtype+RUNKEY_ADTYPELIST_UP;
      }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
	
		
		int totalCount=0;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, poolname)){
			totalCount=(int) RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, poolname);
		}

		int totalPage=0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage); 
		int start= (page-1)*limit;
		ArrayList<AdvertisingModel> adlist= new ArrayList<AdvertisingModel>();
		Set<String> set=null;
		if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, poolname)){
			if(totalCount>0){
				set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_RUN, poolname, start, start+limit-1);
				for(String s :set){
					Map<String,String>  temp= RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, s+RUNKEY_ADMAP);
					AdvertisingModel advertisingModel = null;
					try {
						advertisingModel = BeanUtils.mapToObject(temp, AdvertisingModel.class);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if(advertisingModel!=null){
						adlist.add(advertisingModel);
					}
					
				}
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
		model.addAttribute("adlist", adlist);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("adtype", adtype);
	}
	
	@RequestMapping(value = "/addAdvertising", method = RequestMethod.POST)
	public void addAdvertising(Model model, HttpSession session, HttpServletRequest request,
			String title,String adtype,String subtitle,String des,String state, String adheadtype ,PrintWriter out) {
		  AdvertisingModel adModel = new AdvertisingModel();
		  String adid=MbUtil.getUUID();
		  adModel.setAdid(adid);
	      adModel.setTitle(title);
	      adModel.setSubtitle(subtitle);
	      adModel.setDes(des);
	      adModel.setAdtype(Integer.parseInt(adtype));
	      adModel.setAdheadtype(Integer.parseInt(adheadtype));
	      adModel.setState(Integer.parseInt(state));
	      String poolname="";
	      if(Integer.parseInt(state)==1){
	    	  poolname=adtype+RUNKEY_ADTYPELIST_UP;
	      }else{
	    	  poolname=adtype+RUNKEY_ADTYPELIST_DOWN;
	      }
	      String print="fail";
	      try {
			Map<String, String> map = BeanUtils.objectToMap(adModel);
			if(map!=null){
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, poolname, System.currentTimeMillis(), adid);
				RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP, map);
				print="success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	     
	      out.write(print);
        }
	
	
	@RequestMapping(value = "/editAdvertising", method = RequestMethod.POST)
	public void editAdvertising(Model model, HttpSession session, HttpServletRequest request, String adid,PrintWriter out) {
		AdvertisingModel ad =null;
		if(StringUtils.isNotBlank(adid)){
        	if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP)){
        		Map<String, String> hgetAll = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP);
        		try {
					ad =BeanUtils.mapToObject(hgetAll, AdvertisingModel.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}	
        }
		String jsonObjectStr =null;
		if(ad !=null){
			jsonObjectStr = JSONObject.toJSONString(ad);
		}
		out.write(jsonObjectStr);
	}
	
	
	@RequestMapping(value = "/updateAdvertising", method = RequestMethod.POST)
	public void updateAdvertising(Model model, HttpSession session, HttpServletRequest request,
			String adid,String title,String adtype,String subtitle,String des,String state,String order,String oldadtype,String adheadtype,PrintWriter out) {
		 AdvertisingModel adModel = new AdvertisingModel();
	      adModel.setAdid(adid);
	      adModel.setTitle(title);
	      adModel.setAdtype(Integer.parseInt(adtype));
	      adModel.setAdheadtype(Integer.parseInt(adheadtype));
	      adModel.setState(Integer.parseInt(state));
	      adModel.setSubtitle(subtitle);
	      adModel.setDes(des);
	      
	      String poolname="";
	      if(Integer.parseInt(state)==1){
	    	  poolname=adtype+RUNKEY_ADTYPELIST_UP;
	      }else{
	    	  poolname=adtype+RUNKEY_ADTYPELIST_DOWN;
	      }
	       
	        String print = "fail";
	        Map<String, String> objectToMap;
			try {
				if(Integer.parseInt(order)==1){
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, oldadtype+RUNKEY_ADTYPELIST_UP, adid);
				}else{
					RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, oldadtype+RUNKEY_ADTYPELIST_DOWN, adid);
				}
				RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, poolname, System.currentTimeMillis(), adid);
				objectToMap = BeanUtils.objectToMap(adModel);
				RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP, objectToMap);
				print = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	     
	      out.write(print);
        }
	
	@RequestMapping(value = "/insertAdvertisingImg", method = RequestMethod.POST)
	public void insertAdvertisingImg(Model model, HttpSession session, HttpServletRequest request,
			String adid,String linktype,String imgurl,String linkurl,String gpid,String keyword,
			String secondcarid,String buserid,String orderindex, String miniprogramid,String nid,PrintWriter out) throws Exception {
		  AdvertisingImgModel adimgModel = new AdvertisingImgModel();
	      adimgModel.setAdid(adid);
	      adimgModel.setLinktype(Integer.parseInt(linktype));
	      adimgModel.setImgurl(imgurl);
	      adimgModel.setLinkurl(linkurl);
	      adimgModel.setMiniprogramid(miniprogramid);
	      if(gpid !=null && gpid !=""){
	    	  adimgModel.setGpid(Integer.parseInt(gpid)); 
	      }
	      adimgModel.setKeyword(keyword);
	      adimgModel.setNid(nid);
	      adimgModel.setSecondcarid(secondcarid);
	      adimgModel.setBuserid(buserid);
	      adimgModel.setOrderindex(Integer.parseInt(orderindex));
	      String adimgid=MbUtil.getUUID();
	      adimgModel.setAdimgid(adimgid);
	      String print="fail";
	      if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP)){
	    	  RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADIMGLIST, Integer.parseInt(orderindex), adimgid);
	    	  Map<String, String> objectToMap = BeanUtils.objectToMap(adimgModel);
	    	  RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, adimgid+RUNKEY_ADIMGMAP, objectToMap);
	    	  print="success";
	      }
	     
	      out.write(print);
        }
	
	@RequestMapping(value = "/getAdvertisingimgByadid", method = RequestMethod.POST)
	public void getAdvertisingimgByadid(Model model, HttpSession session, HttpServletRequest request, String adid,PrintWriter out) {
		List<AdvertisingImgModel>  list = new ArrayList<AdvertisingImgModel>();
        if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADIMGLIST)){
        	Set<String> tempset = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADIMGLIST, 0, -1);
        	if(tempset!=null && tempset.size()>0){
        		for(String s : tempset){
        			Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, s+RUNKEY_ADIMGMAP);
        			AdvertisingImgModel adimg = null;
        			if(map!=null){
        				try {
        					adimg=BeanUtils.mapToObject(map, AdvertisingImgModel.class);
						} catch (Exception e) {
							e.printStackTrace();
							continue;
						}
        			}
        			if(adimg!=null){
        				list.add(adimg);
        			}
        		}
        	}
        }
        
	    String	jsonObjectStr = JSONObject.toJSONString(list);
		out.write(jsonObjectStr);
	}
	
	@RequestMapping(value = "/getAdvertisingimgByadImgid", method = RequestMethod.POST)
	public void getAdvertisingimgByadImgid(Model model, HttpSession session, HttpServletRequest request, String adimgid,PrintWriter out) {
		AdvertisingImgModel adimgModel   = new AdvertisingImgModel();
		Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, adimgid+RUNKEY_ADIMGMAP);
		if(map!=null){
			try {
				adimgModel=BeanUtils.mapToObject(map, AdvertisingImgModel.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 String	jsonObjectStr = JSONObject.toJSONString(adimgModel);
		
		out.write(jsonObjectStr);
	}
	
	@RequestMapping(value = "/updateAdvertisingImg", method = RequestMethod.POST)
	public void updateAdvertisingImg(Model model, HttpSession session, HttpServletRequest request,
			String adimgid,String adid,String linktype,String imgurl,String linkurl,String gpid,String keyword,String nid,
			String secondcarid,String buserid,String orderindex,String miniprogramid, PrintWriter out) throws Exception {
	      AdvertisingImgModel adimgModel = new AdvertisingImgModel();
	      adimgModel.setAdid(adid);
	      adimgModel.setLinktype(Integer.parseInt(linktype));
	      adimgModel.setImgurl(imgurl);
	      adimgModel.setLinkurl(linkurl);
	      adimgModel.setMiniprogramid(miniprogramid);
	      if(gpid !=null && gpid !=""){
	    	  adimgModel.setGpid(Integer.parseInt(gpid)); 
	      }
	      adimgModel.setKeyword(keyword);
	      adimgModel.setNid(nid);
	      adimgModel.setSecondcarid(secondcarid);
	      adimgModel.setBuserid(buserid);
	      adimgModel.setOrderindex(Integer.parseInt(orderindex));
	      adimgModel.setAdimgid(adimgid);
	      String print="fail";
	      if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADMAP)){
	    	  RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADIMGLIST, Integer.parseInt(orderindex), adimgid);
	    	  Map<String, String> objectToMap = BeanUtils.objectToMap(adimgModel);
	    	  RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, adimgid+RUNKEY_ADIMGMAP, objectToMap);
	    	  print="success";
	      }
	      
	      out.write(print);
        }
	
	@RequestMapping(value = "/delAdvertisingimg", method = RequestMethod.POST)
	public void delAdvertisingimg(Model model, HttpSession session, HttpServletRequest request, String adimgid,String adid,PrintWriter out) {
		RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, adid+RUNKEY_ADIMGLIST, adimgid);
		RedisManager.getInstance().hdel(Consts.REDIS_SCHEME_RUN, adimgid+RUNKEY_ADIMGMAP);
        out.write("success");
        
	}
	
}

