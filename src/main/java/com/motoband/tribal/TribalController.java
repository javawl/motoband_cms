package com.motoband.tribal;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.alibaba.fastjson.JSON;
import com.motoband.admin.admin.Admin;
import com.motoband.boxmanage.boxService;
import com.motoband.boxmanage.imggroup;
import com.motoband.boxmanage.motoimg;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;
import com.motoband.util.RedisManager;

@Controller
@RequestMapping(value = "/tribal")
public class TribalController {
@Autowired
private TribalService tribalService;
@Autowired
private boxService boxService;


public static final String RUNKEY_TRIBALMAP = "_tribalmap";
public static final String RUNKEY_NIDLIST = "_nidlist";
public static final String RUNKEY_FOLLOWERUSERLIST = "_followeruserlist";
public static final String RUNKEY_USERTRIBALLIST = "_usertriballist";
//public static final String RUNKEY_TRIBALLIST_REGION = "triballist_region";//地域部落列表
//public static final String RUNKEY_TRIBALLIST_BRAND = "triballist_brand";//品牌部落列表
//public static final String RUNKEY_TRIBALLIST_THEME = "triballist_theme";//主题部落列表
public static final String RUNKEY_TRIBALLIST_HOT = "triballist_hot";//热门部落列表
public static final String RUNKEY_TRIBALLIST_PREFIX = "triballist_";//部落列表key前缀

@RequestMapping(value = "/triballist", method = RequestMethod.GET)
public void triballist(Model model, HttpSession session, HttpServletRequest request, String userGuid, 
		int page,int limit,int order,String orderConditions,int tribaltype) {

	if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}
	PageBean<TribalModel> pageBean=new PageBean<TribalModel>();
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
	String poolname="";
	int totalCount=0;
	if(tribaltype==0){
	//	poolname=RUNKEY_TRIBALLIST_REGION;
		totalCount=tribalService.getTribalCountByType(tribaltype);
	}else if(tribaltype==1){
	//	poolname=RUNKEY_TRIBALLIST_BRAND;
		totalCount=tribalService.getTribalCountByType(tribaltype);
	}else if(tribaltype==2){
	//	poolname=RUNKEY_TRIBALLIST_THEME;
		totalCount=tribalService.getTribalCountByType(tribaltype);
	}else if(tribaltype==100){
		poolname=RUNKEY_TRIBALLIST_HOT;
	}else{
	//	poolname=RUNKEY_TRIBALLIST_REGION;
		totalCount=tribalService.getTribalCountByType(tribaltype);
	}
	
	if(tribaltype==100){
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
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	List<TribalModel> tribalModels1=tribalService.getTribalListAll(-1);
	Map<String,TribalModel> tribalMap=new HashMap<String,TribalModel>();
	if(tribalModels1!=null && tribalModels1.size()>0){
		for(TribalModel tb :tribalModels1){
			tribalMap.put(tb.tribalid, tb);
		}
	}
	
	List<TribalModel> tribalModels=new ArrayList<TribalModel>();
	if (tribaltype==100) {
		long end = start+limit-1;
		Set<String> set = RedisManager.getInstance().zrevrangebyindex(Consts.REDIS_SCHEME_RUN, poolname, start, end);
		if(set!=null && set.size()>0){
			for(String tribalid : set){
				TribalModel tribalModel=tribalMap.get(tribalid);
				double score=RedisManager.getInstance().zscore(Consts.REDIS_SCHEME_RUN, poolname,tribalid);
				tribalModel.setOrderindex(Math.round(score));
//				if(tribalModel!=null){
//					if(tribalModel.mainadminsstr!=null){
//						tribalModel.mainadmins = JSON.parseArray(tribalModel.mainadminsstr, String.class);
//					}
//					if(tribalModel.viceadminsstr!=null){
//						tribalModel.viceadmins = JSON.parseArray(tribalModel.viceadminsstr, String.class);
//					}
//					
//				}
				if(tribalModel!=null){
					tribalModels.add(tribalModel);
				}
				
			}
		}

	}else{
		tribalModels=tribalService.getTribalListByType(tribaltype,start,limit);
//		if(tribalModels.size()>0){
//			for(TribalModel tribalModel: tribalModels){
//				if(tribalModel.mainadminsstr!=null){
//					tribalModel.mainadmins = JSON.parseArray(tribalModel.mainadminsstr, String.class);
//				}
//				if(tribalModel.viceadminsstr!=null){
//					tribalModel.viceadmins = JSON.parseArray(tribalModel.viceadminsstr, String.class);
//				}
//			}
//		}
	} 
	
	ArrayList<motoimg> motoimgs = boxService.getMotoImgListByGroupGuid("0");
	ArrayList<imggroup> imggroups = boxService.getImgGroupList();
	model.addAttribute("imggroups", imggroups);
	model.addAttribute("motoimgs", motoimgs);
	
	ArrayList<TribalTypeModel> tribaltypelist = tribalService.getTribalTypeList();
	model.addAttribute("tribaltypelist", tribaltypelist);
	
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	model.addAttribute("triballists", tribalModels);
	model.addAttribute("pageBean", pageBean);

	
	model.addAttribute("limit", limit);
	model.addAttribute("order", order);
	model.addAttribute("orderConditions", orderConditions);
	model.addAttribute("tribaltype", tribaltype);
}
@RequestMapping(value = "/getTribalById", method = RequestMethod.POST)
public void getTribalById(Model model, HttpSession session, HttpServletRequest request, String tribalid,PrintWriter out) {
			String jsonString= "";
			TribalModel tribalModel=tribalService.getTribalById(tribalid);
			if(tribalModel!=null){
//			if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, tribalid+RUNKEY_TRIBALMAP)){
//				Map<String, String> map = RedisManager.getInstance().hgetAll(Consts.REDIS_SCHEME_RUN, tribalid+RUNKEY_TRIBALMAP);
//				tribalModel=TribalModel.convertToModel(map);
				if(RedisManager.getInstance().checkkey(Consts.REDIS_SCHEME_RUN, tribalid+RUNKEY_FOLLOWERUSERLIST)){
					long count=RedisManager.getInstance().zcard(Consts.REDIS_SCHEME_RUN, tribalid+RUNKEY_FOLLOWERUSERLIST);
					tribalModel.followcount=count;
				}else{
					tribalModel.followcount=0;
				}
			}
			if(tribalModel!=null){
				
				if(tribalModel.mainadminsstr!=null){
					tribalModel.mainadmins = JSON.parseArray(tribalModel.mainadminsstr, String.class);
				}
				if(tribalModel.viceadminsstr!=null){
					tribalModel.viceadmins = JSON.parseArray(tribalModel.viceadminsstr, String.class);
				}
					
				if(tribalModel.mainadmins!=null && tribalModel.mainadmins.size()>0){
				StringBuffer sbmain = new StringBuffer();
				for(String s: tribalModel.mainadmins){
					sbmain.append(s);
					sbmain.append(",");
				}
				tribalModel.mainadminsstr=sbmain.substring(0,sbmain.length()-1);
				}
				if(tribalModel.viceadmins!=null && tribalModel.viceadmins.size()>0){
					StringBuffer sbvice = new StringBuffer();
					for(String s: tribalModel.viceadmins){
						sbvice.append(s);
						sbvice.append(",");
					}
					tribalModel.viceadminsstr=sbvice.substring(0,sbvice.length()-1);
				}
				jsonString=JSON.toJSONString(tribalModel);
			}
			out.print(jsonString);
}
@RequestMapping(value = "/insertOrUpdateTribal", method = RequestMethod.POST)
public void insertOrUpdateTribal(Model model, HttpSession session, HttpServletRequest request, String tribalid,String addtime,String oldtype,String name,String logopic,
		String bannerpic,String mainadminsstr,String viceadminsstr,String des,String type,PrintWriter out) {
			    String jsonString= "";
			    TribalModel tb =new TribalModel();
			    long curtime=System.currentTimeMillis();
			    if(addtime!=null&& !"".equals(addtime)){
			    	long atime=Long.valueOf(addtime);
			    	if(atime>0){
			    		tb.addtime=atime;
			    	}
			    }else{
			    	tb.addtime=curtime;
			    	tb.orderindex=curtime;
			    }
			    tb.lastupdatetime=curtime;
			    if(tribalid!=null && !"".equals(tribalid)){
			    	tb.tribalid=tribalid;
			    }else{
			    	tb.tribalid=MbUtil.getUUID();
			    }
			    tb.name=name;
			    tb.logopic=logopic;
			    tb.bannerpic=bannerpic;
			    
			    if (mainadminsstr != null && !"".equals(mainadminsstr)) {
			    String[] mainids=mainadminsstr.split(",");
			    List<String> mainlist=new ArrayList<String>();
			    for(String s : mainids){
			    	mainlist.add(s);
			    }
			    String temp = JSON.toJSONString(mainlist);
			    tb.mainadminsstr=temp;
			    }
			    
				if (viceadminsstr != null && !"".equals(viceadminsstr)) {
					 String[] viceids=viceadminsstr.split(",");
					    List<String> vicelist=new ArrayList<String>();
					    for(String s : viceids){
					    	vicelist.add(s);
					    }
					String temp1 = JSON.toJSONString(vicelist);
					tb.viceadminsstr=temp1;
				}
				tb.des=des;
				tb.type=Integer.parseInt(type);
				
				boolean b=tribalService.addOrUpdateTribal(tb);
				if(b){
//					Map<String, String> map = TribalModel.convertToMap(tb);
//					 if (mainadminsstr != null && !"".equals(mainadminsstr)) {
//					     map.put("mainadmins", tb.mainadminsstr);
//					 }
//					if (viceadminsstr != null && !"".equals(viceadminsstr)) {
//						map.put("viceadmins", tb.viceadminsstr);
//					}
//					
//					String typestr=RedisManager.getInstance().hget(Consts.REDIS_SCHEME_RUN, tb.tribalid+RUNKEY_TRIBALMAP, "type");
//					RedisManager.getInstance().hmset(Consts.REDIS_SCHEME_RUN, tb.tribalid+RUNKEY_TRIBALMAP, map);
//					if(typestr!=null && !"".equals(typestr)){
//						int oldtype1=Integer.parseInt(typestr);
//						if(oldtype1!=tb.type){
//							if(oldtype1==0){
//								RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_REGION, tb.tribalid);
//							}else if(oldtype1==1){
//								RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_BRAND, tb.tribalid);
//							}else if(oldtype1==2){
//								RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_THEME, tb.tribalid);
//							}
//						}
//					}
//					if(tb.type==0){
//						boolean flag=RedisManager.getInstance().zexist(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_REGION, tb.tribalid);
//						if(!flag){
//							RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_REGION, curtime, tb.tribalid);
//						}
//					}else if(tb.type==1){
//						boolean flag=RedisManager.getInstance().zexist(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_BRAND, tb.tribalid);
//						if(!flag){
//							RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_BRAND, curtime, tb.tribalid);
//						}
//					}else if(tb.type==2){
//						boolean flag=RedisManager.getInstance().zexist(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_THEME, tb.tribalid);
//						if(!flag){
//							RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_THEME, curtime, tb.tribalid);
//						}
//					}
					
					jsonString="success";
				}else{
					jsonString="fail";
				}
			
			out.print(jsonString);
}
@RequestMapping(value = "/deltribalfromhot", method = RequestMethod.POST)
public void deltribalfromhot(Model model, HttpSession session, HttpServletRequest request, String tribalid,PrintWriter out) {
			
			RedisManager.getInstance().zrem(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_HOT, tribalid);
			String jsonString= "success";
			out.print(jsonString);
}
@RequestMapping(value = "/addhottribal", method = RequestMethod.POST)
public void addhottribal(Model model, HttpSession session, HttpServletRequest request, String tribalid,String score,PrintWriter out) {
			
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_HOT, Long.valueOf(score), tribalid);
			String jsonString= "success";
			out.print(jsonString);
}
@RequestMapping(value = "/changgeOrderindex", method = RequestMethod.POST)
public void changgeOrderindex(Model model, HttpSession session, HttpServletRequest request, String tribalid,String orderindex,String type,PrintWriter out) {
	if(Integer.parseInt(type)==100){
		RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, RUNKEY_TRIBALLIST_HOT, Long.valueOf(orderindex), tribalid);
	}else{
		tribalService.updateTribalOrderindex(tribalid,Long.valueOf(orderindex));
	}
	String jsonString= "success";
	out.print(jsonString);
}
@RequestMapping(value = "/sysTribalRedis", method = RequestMethod.POST)
public void sysTribalRedis(Model model, HttpSession session, HttpServletRequest request, PrintWriter out) {
	List<TribalModel> tribalModels=tribalService.getTribalListAll(-1);
	ArrayList<TribalTypeModel> tribaltypelist = tribalService.getTribalTypeList();
	Map<String,String> map = new HashMap<String,String>();
	for(TribalTypeModel tribaltype :tribaltypelist){
		map.put(String.valueOf(tribaltype.tribaltypeid), tribaltype.rediskeyname);
	}
	String rediskey="";
	if(tribalModels!=null && tribalModels.size()>0){
		for(Map.Entry<String, String> entry :map.entrySet()){
			String delrediskey=RUNKEY_TRIBALLIST_PREFIX+entry.getValue();
			RedisManager.getInstance().delbykey(Consts.REDIS_SCHEME_RUN, delrediskey);
		}
		
		for(TribalModel tb :tribalModels){
			if(tb.mainadminsstr!=null &&!"".equals(tb.mainadminsstr)){
				tb.mainadmins = JSON.parseArray(tb.mainadminsstr, String.class);
				if(tb.mainadmins.size()>0){
					for(String s :tb.mainadmins){
						RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, tb.tribalid+RUNKEY_FOLLOWERUSERLIST, tb.addtime, s);
						RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, s+RUNKEY_USERTRIBALLIST, tb.addtime, tb.tribalid);
					}
				}
			}
			if(tb.viceadminsstr!=null &&!"".equals(tb.viceadminsstr)){
				tb.viceadmins = JSON.parseArray(tb.viceadminsstr, String.class);
				if(tb.viceadmins.size()>0){
					for(String s :tb.viceadmins){
						RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, tb.tribalid+RUNKEY_FOLLOWERUSERLIST, tb.addtime, s);
						RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, s+RUNKEY_USERTRIBALLIST, tb.addtime, tb.tribalid);
					}
				}
			}
			String rediskeyindb=map.get(String.valueOf(tb.type));
			rediskey=RUNKEY_TRIBALLIST_PREFIX+rediskeyindb;
			RedisManager.getInstance().zadd(Consts.REDIS_SCHEME_RUN, rediskey, tb.orderindex, tb.tribalid);
		
		}
	}
	String jsonString= "success";
	out.print(jsonString);
}
}
