package com.motoband.reportlogmanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motoband.admin.admin.Admin;
import com.motoband.util.Constants;
import com.motoband.util.PageBean;

@Controller
@RequestMapping(value = "/reportlogmanage")
public class ReportlogController {
	@Autowired
	private ReportlogService reportlogService;
	@RequestMapping(value = "/reportloglist", method = RequestMethod.GET)
	public void reportloglist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid, 
			int page,int limit,int order,String orderConditions) {
		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		
		PageBean<ReportlogModel> pageBean=new PageBean<ReportlogModel>();
		if(page==0){
			page=1;
		}
	    if(limit==0){
	    	limit=20;
	    }
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int totalCount= reportlogService.getReportloglistCount();
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
		ArrayList<ReportlogModel> reportloglist = reportlogService.getReportloglist(
				start,limit,order,orderConditions);
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(reportloglist!=null && reportloglist.size()>0){
			for(ReportlogModel rm :reportloglist){
				rm.setInformtimeString(format.format(new Date(rm.getInformtime())));
			}
		}
		model.addAttribute("reportloglist", reportloglist);
		
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
}
