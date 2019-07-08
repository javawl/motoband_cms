package com.motoband.configdata;



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
import com.motoband.util.Constants;


@Controller
@RequestMapping(value = "/configdata")
public class configdataController {

	@Autowired
	private configdataService configdataService;

	@RequestMapping(value = "/configdatalist", method = RequestMethod.GET)
	public void carMessage(Model model, HttpSession session,
			HttpServletRequest request,String userGuid) {
	   ArrayList<configdata> configdatas=configdataService.getConfigList();
	   int updateCheck = configdataService.getUserUpdateCheck(userGuid);
		int delCheck = configdataService.getUserDelCheck(userGuid);
		int insCheck = configdataService.getUserInsCheck(userGuid);
		if (updateCheck > 0) {
			model.addAttribute("updateCheck", "lock");
		} else {
			model.addAttribute("updateCheck", "unlock");
		}
		if (delCheck > 0) {
			model.addAttribute("delCheck", "lock");
		} else {
			model.addAttribute("delCheck", "unlock");
		}
		if (insCheck > 0) {
			model.addAttribute("insCheck", "lock");
		} else {
			model.addAttribute("insCheck", "unlock");
		}
	   model.addAttribute("configdatas", configdatas);
	}
	
	@RequestMapping(value = "/insertConfigdata", method = RequestMethod.POST)
	public void insertConfigdata(Model model, HttpSession session,
			HttpServletRequest request,String servicename,String configkey,String configvalue,String comments,PrintWriter out) {
		configdata configdata=new configdata();
		configdata.setServicename(servicename);
		configdata.setConfigkey(configkey);
		configdata.setConfigvalue(configvalue);
		configdata.setComments(comments);
		configdataService.insertConfigdata(configdata);
		out.print("success");
	}
	
	@RequestMapping(value = "/selconfigdataMessage", method = RequestMethod.POST)
	public void selconfigdataMessage(Model model, HttpSession session,
			HttpServletRequest request,String configdata_id,PrintWriter out) {
		
		configdata configdata=configdataService.getConfigDataByID(Integer.parseInt(configdata_id));
	String a=JSONObject.toJSON(configdata).toString();
	//System.out.println(a);
	out.print(a);
	}
	
	@RequestMapping(value = "/saveConfigdataMessage", method = RequestMethod.POST)
	public void saveConfigdataMessage(Model model, HttpSession session,
			HttpServletRequest request,String id,String servicename,String configkey,String configvalue,String comments,PrintWriter out) {
		configdata configdata=new configdata();
		configdata.setId(Integer.parseInt(id));
		configdata.setServicename(servicename);
		configdata.setConfigkey(configkey);
		configdata.setConfigvalue(configvalue);
		configdata.setComments(comments);
		configdataService.saveConfigdataMessage(configdata);
		Admin admin=(Admin)session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}
	
	@RequestMapping(value = "/delConfigdataMessage", method = RequestMethod.POST)
	public void delConfigdataMessage(Model model, HttpSession session,
			HttpServletRequest request,String id,PrintWriter out) {
		configdataService.delConfigdataMessage(id);
	}

}
