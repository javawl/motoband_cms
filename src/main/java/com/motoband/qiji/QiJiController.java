package com.motoband.qiji;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.motoband.admin.admin.Admin;
import com.motoband.util.Constants;
import com.motoband.util.MbUtil;
import com.motoband.util.PageBean;

@Controller
@RequestMapping(value = "/qiji")
public class QiJiController {

	@Autowired
	private QiJiService qijiService;

	@RequestMapping(value = "/openqijilist", method = RequestMethod.GET)
	public void openqijilist(Model model, HttpSession session, HttpServletRequest request, String userGuid, int page,
			int limit, int order, String orderConditions,String province,String city) {

		if (userGuid == null) {
			Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
			userGuid = admin.getUser_guid();
		}
		PageBean<QiJiInfoModel> pageBean = new PageBean<QiJiInfoModel>();
		if (page == 0) {
			page = 1;
		}
		if (limit == 0) {
			limit = 20;
		}
		if (orderConditions == null || orderConditions == "") {
			orderConditions = "";
		}
		if(StringUtils.isBlank(province)) {
			province=null;
		}
		if(StringUtils.isBlank(city)) {
			city=null;
		}
		
		if(StringUtils.isNotBlank(province)&&StringUtils.isNotBlank(city)) {
			if(qijiService.getCount(province,city)==0) {
				city=null;
			}
		}
		pageBean.setPage(page);
		pageBean.setLimit(limit);
		int start = (page - 1) * limit;
		List<QiJiInfoModel> qijiModels = qijiService.getList(start, limit, order, orderConditions,province,city);
		int totalCount = qijiService.getCount(province,city);

		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		ArrayList<Integer> limitList = new ArrayList<Integer>();
		limitList.add(20);
		limitList.add(50);
		limitList.add(100);
		model.addAttribute("limitList", limitList);
		model.addAttribute("openqijilists", qijiModels);
		model.addAttribute("pageBean", pageBean);

		model.addAttribute("limit", limit);
		model.addAttribute("order", order);
		model.addAttribute("orderConditions", orderConditions);
		List<String> provinceList=qijiService.getProvinceList();
		model.addAttribute("provincelist",provinceList);
		List<String> cityList=Lists.newArrayList();
		if(StringUtils.isNotBlank(province)) {
			model.addAttribute("province",province);
			cityList=qijiService.getCityBypProvince(province);
		}else {
			model.addAttribute("province",null);
		}
		if(StringUtils.isNotBlank(city)) {
			model.addAttribute("city",city);
		}else {
			model.addAttribute("city",null);
		}
		model.addAttribute("citylist",cityList);
	}

	@RequestMapping(value = "/getQijiModelById", method = RequestMethod.POST)
	@ResponseBody
	public QiJiInfoModel getQijiModelById(String sid) {
		if(StringUtils.isNotBlank(sid)) {
			QiJiInfoModel model=qijiService.getQijiModelById(sid);
			return model;
		}else {
			return null;
		}
	}
	
	
	
	@RequestMapping(value = "/insertOrUpdateQijiModel", method = RequestMethod.POST)
	@ResponseBody
	public String insertOrUpdateQijiModel(QiJiInfoModel model) {
		String jsonString= "fail";
			if(StringUtils.isBlank(model.sid)){
				model.sid=MbUtil.getUUID().toLowerCase();
			}
			model._name=model.name;
			boolean flag=qijiService.insertOrUpdateQijiModel(model);
			if(flag) {
				jsonString= "success";
			}
			
		return jsonString;
	}
	
	@RequestMapping(value = "/delopenqiji", method = RequestMethod.POST)
	@ResponseBody
	public String delopenqiji(String sid) {
		String jsonString= "fail";
			if(StringUtils.isNotBlank(sid)){
				boolean flag=qijiService.delopenqiji(sid);
				if(flag) {
					jsonString= "success";
				}			}
		return jsonString;
	}
	
	@RequestMapping(value = "/getprovincelist", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getprovincelist(Model model){
		List<String> provinceList=qijiService.getProvinceList();
		model.addAttribute("provincelist",provinceList);
		return provinceList;
	}
	
	
}
