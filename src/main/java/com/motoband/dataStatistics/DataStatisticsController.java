package com.motoband.dataStatistics;

import java.text.ParseException;
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

import com.motoband.businessmanage.BusinessService;
import com.motoband.businessmanage.BusinessUserModel;
import com.motoband.util.DateFormat;
import com.motoband.util.PageBean;

@Controller
@RequestMapping(value = "/dataStatisticsmanage")
public class DataStatisticsController {
@Autowired
private DataStatisticsService dataStatisticsService;
@Autowired
private BusinessService businessService;

@RequestMapping(value = "/dataStatisticslist", method = RequestMethod.GET)
public void getDataStatisticslist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,int type,String startTime,String endTime) {

	/*if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}*/

	if(type==0){
	    type=1;	
	}

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	
	int year_start =0;
	int month_start = 0;
	int week_start =0;
	int year_end = 0;
	int month_end = 0;
	int week_end =0;
   if(startTime!=null &&!"".equals(startTime)){
		year_start = DateFormat.getYearInTime(startTime);
		month_start = DateFormat.getMonthInYear(startTime);
		week_start =DateFormat.getWeekInYear(startTime);
	}
   if(endTime!=null && !"".equals(endTime)){
		year_end = DateFormat.getYearInTime(endTime);
		month_end = DateFormat.getMonthInYear(endTime);
		week_end =DateFormat.getWeekInYear(endTime);
   }
   
	if(startTime==null ||"".equals(startTime)){
		long time=System.currentTimeMillis()/1000-20*24*60*60;
		startTime =  sdf.format(new Date(time*1000));
	}else{
		try {
			if(type==1){
				 startTime =  sdf.format(new Date(sdf1.parse(startTime).getTime()));
			}else if(type==2){
				Date d= DateFormat.getFirstDayOfWeek(year_start, week_start);
				startTime =  sdf.format(d);
			}else if(type==3){
				Date d= DateFormat.getFirstDayOfMonth(year_start, month_start);
				startTime =  sdf.format(d);
			}
			
		} catch (Exception e) {
			
		}
	}
	if(endTime==null || "".equals(endTime)){
		long time=System.currentTimeMillis();
		endTime = sdf.format(new Date(time));	
	}else{
		try {
			if(type==1){
				endTime =  sdf.format(new Date(sdf1.parse(endTime).getTime()));
			}else if(type==2){
				Date d= DateFormat.getLastDayOfWeek(year_end, week_end);
				endTime =  sdf.format(d);
			}else if(type==3){
				Date d= DateFormat.getLastDayOfMonth(year_end, month_end);
				endTime =  sdf.format(d);
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	PageBean<DataStatisticsModel> pageBean=new PageBean<DataStatisticsModel>();
	if(page==0){
		page=1;
	}
  if(limit==0){
 	limit=20;
    }

	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=dataStatisticsService.getDataStatisticsCount(startTime,endTime,String.valueOf(type));

	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	ArrayList<DataStatisticsModel> dataStatisticslist =dataStatisticsService.getDataStatisticslist(startTime,endTime,String.valueOf(type));
	long mbusercount =dataStatisticsService.getSummbuserCount();
	long secondcarcount =dataStatisticsService.getSummSecondCarCount();
	ArrayList<DataStatisticsModel> tempdataStatisticslist = new ArrayList<DataStatisticsModel>();
	int forcount=0;
	int temp=start+limit;
	if(temp>totalCount){
		forcount=totalCount;
	}else{
		forcount=temp;
	}
    for(int i=start;i<forcount;i++){
    	int appcount=dataStatisticslist.get(i).getOpenAppCount();
    	int distinctUserCount=dataStatisticslist.get(i).getDistinctUserCount();
    	int newAddUserCount=dataStatisticslist.get(i).getNewAddUserCount();
    	dataStatisticslist.get(i).setOpenAppCount(changeCount(appcount));
    	dataStatisticslist.get(i).setDistinctUserCount(changeCount(distinctUserCount));
    	dataStatisticslist.get(i).setNewAddUserCount(changeCount(newAddUserCount));
    	tempdataStatisticslist.add(dataStatisticslist.get(i));
    }
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	
	
	if(type==3){
		List<MonthActiveModel> monthActiveModels = dataStatisticsService.getMonthActiveModels();
		Map<String,Integer> mamap= new HashMap<String,Integer>();
		if(monthActiveModels!=null && monthActiveModels.size()>0){
			for(MonthActiveModel ma :monthActiveModels ){
				mamap.put(ma.getDatetime(),ma.getNum());
			}
		}
		
		if(tempdataStatisticslist!=null && tempdataStatisticslist.size()>0){
			for(DataStatisticsModel ds : tempdataStatisticslist){
				Integer numLong=mamap.get(ds.getTimeformat().substring(0, 6));
				int num=0;
				if(numLong!=null ){
					num=numLong;
				}
				ds.setDistinctUserCount(num);
			}
		}
		
	}
	

	model.addAttribute("dataStatisticslist", tempdataStatisticslist);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("type", type);
	model.addAttribute("mbusercount", mbusercount-840000);
	model.addAttribute("secondcarcount", secondcarcount);
	try {
		model.addAttribute("startTime", sdf1.format(new Date(sdf.parse(startTime).getTime())));
	} catch (ParseException e) {
		
	}
	try {
		model.addAttribute("endTime", sdf1.format(new Date(sdf.parse(endTime).getTime())));
	} catch (ParseException e) {
		
	}
}

private static int changeCount(int appcount) {
	String countstr=String.valueOf(appcount);
	char lastnum=countstr.charAt(countstr.length()-1);
	appcount=(int) (appcount*1.2/10);
	appcount=appcount*10+Integer.parseInt(String.valueOf(lastnum));
	return appcount;
}


@RequestMapping(value = "/businessdataStatisticslist", method = RequestMethod.GET)
public void getBusinessDataStatisticslist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,int limit,String  buserid,String startTime,String endTime) throws Exception {

	/*if (userGuid == null) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		userGuid = admin.getUser_guid();
	}*/


	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
   
	if(startTime==null ||"".equals(startTime)){
		long time=System.currentTimeMillis()/1000-20*24*60*60;
		startTime =  sdf.format(new Date(time*1000));
	}else{
	   startTime =  sdf.format(new Date(sdf1.parse(startTime).getTime()));
	}
	if(endTime==null || "".equals(endTime)){
		long time=System.currentTimeMillis();
		endTime = sdf.format(new Date(time));	
	}else{
		endTime =  sdf.format(new Date(sdf1.parse(endTime).getTime()));
	}
	
	PageBean<BusinessDataStatisticsModel> pageBean=new PageBean<BusinessDataStatisticsModel>();
	if(page==0){
		page=1;
	}
  if(limit==0){
 	limit=20;
    }

	pageBean.setPage(page);
	pageBean.setLimit(limit);
	int totalCount=dataStatisticsService.getBusinessDataStatisticsCount(startTime,endTime,buserid);

	int totalPage=0;
	if(totalCount % limit == 0){
		totalPage = totalCount / limit;
	}else{
		totalPage = totalCount / limit + 1;
	}
	pageBean.setTotalPage(totalPage); 
	int start= (page-1)*limit;
	ArrayList<BusinessDataStatisticsModel> dataStatisticslist =dataStatisticsService.getBusinessDataStatisticslist(startTime,endTime,buserid);
	ArrayList<BusinessDataStatisticsModel> tempdataStatisticslist = new ArrayList<BusinessDataStatisticsModel>();
	int forcount=0;
	int temp=start+limit;
	if(temp>totalCount){
		forcount=totalCount;
	}else{
		forcount=temp;
	}
    for(int i=start;i<forcount;i++){
    	tempdataStatisticslist.add(dataStatisticslist.get(i));
    }
	
	ArrayList<Integer> limitList =new ArrayList<Integer>();
	limitList.add(20);
	limitList.add(50);
	limitList.add(100);
	model.addAttribute("limitList", limitList);
	
	List<BusinessUserModel> businessUserModels = businessService.getBusersBystate(1);
	model.addAttribute("businessUserModels", businessUserModels);

	model.addAttribute("businessdataStatisticslist", tempdataStatisticslist);
	model.addAttribute("pageBean", pageBean);
	model.addAttribute("limit", limit);
	model.addAttribute("buserid", buserid);
	try {
		model.addAttribute("startTime", sdf1.format(new Date(sdf.parse(startTime).getTime())));
	} catch (ParseException e) {
		
	}
	try {
		model.addAttribute("endTime", sdf1.format(new Date(sdf.parse(endTime).getTime())));
	} catch (ParseException e) {
		
	}
}
}
