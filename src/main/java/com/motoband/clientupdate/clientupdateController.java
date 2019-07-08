package com.motoband.clientupdate;



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


@Controller
@RequestMapping(value = "/clientupdate")
public class clientupdateController {

	@Autowired
	private clientupdateService clientupdateService;

	@RequestMapping(value = "/clientupdatelist", method = RequestMethod.GET)
	public void clientupdatelist(Model model, HttpSession session,
			HttpServletRequest request,String userGuid,String ctype) {
	   ArrayList<updateconf> updateconflist=clientupdateService.getClientUpdateList(ctype);
	   int updateCheck = clientupdateService.getUserUpdateCheck(userGuid);
		int delCheck = clientupdateService.getUserDelCheck(userGuid);
		int insCheck = clientupdateService.getUserInsCheck(userGuid);
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
	   model.addAttribute("updateconflist", updateconflist);
	   model.addAttribute("ctype", ctype);
	}
	@RequestMapping(value = "/insertclientupdate", method = RequestMethod.POST)
	public void insertclientupdate(Model model, HttpSession session,
		HttpServletRequest request,String cversion,String tcversion,int ctype,String content,String downloadurl,int state,int ishighversion,PrintWriter out) {
		updateconf updateconf=new updateconf();
		updateconf.setCversion(cversion);
		if(tcversion==null ||"".equals(tcversion)){
			updateconf.setTcversion(null);
		}else{
			updateconf.setTcversion(tcversion);
		}
		updateconf.setCtype(ctype);
		updateconf.setContent(content);
		updateconf.setDownloadurl(downloadurl);
		updateconf.setState(state);
		updateconf.setIshighversion(ishighversion);
		clientupdateService.insertclientupdate(updateconf);
		out.write("success");
		
	}
	@RequestMapping(value = "/updateUpdateConf", method = RequestMethod.POST)
	public void updateUpdateConf(Model model, HttpSession session,
		HttpServletRequest request,int id,String content,String downloadurl,int state,
		String cversion,String tcversion,int ctype,int ishighversion,PrintWriter out) {
		updateconf updateconf=new updateconf();
//		updateconf.setOld_ctype(old_ctype);
//		updateconf.setOld_cversion(old_cversion);
//		updateconf.setOld_tcversion(old_tcversion);
		updateconf.setId(id);
		updateconf.setCversion(cversion);
		if(tcversion==null ||"".equals(tcversion)){
			updateconf.setTcversion(null);
		}else{
			updateconf.setTcversion(tcversion);
		}
		updateconf.setCtype(ctype);
		updateconf.setContent(content);
		updateconf.setDownloadurl(downloadurl);
		updateconf.setState(state);
		updateconf.setIshighversion(ishighversion);
		clientupdateService.updateUpdateConf(updateconf);
		out.write("success");
		
	}
	@RequestMapping(value = "/getUpdateConf", method = RequestMethod.POST)
	public void getUpdateConf(Model model, HttpSession session,
			HttpServletRequest request,int id,PrintWriter out) {
	        
		updateconf uf= clientupdateService.getUpdateConf(id);
		String jsonString="";
		
		if(uf!=null){
			JSONObject jsonObject = new JSONObject();
			jsonString=jsonObject.toJSONString(uf);
		}
		out.write(jsonString);
		
	}
	@RequestMapping(value = "/deleteclientupdate", method = RequestMethod.POST)
	public void deleteclientupdate(Model model, HttpSession session,
		HttpServletRequest request,int id,PrintWriter out) {
		clientupdateService.deleteclientupdate(id);
		out.write("success");
		
	}
//	@RequestMapping(value = "/insertConfigdata", method = RequestMethod.POST)
//	public void insertConfigdata(Model model, HttpSession session,
//			HttpServletRequest request,String servicename,String configkey,String configvalue,String comments,PrintWriter out) {
//		configdata configdata=new configdata();
//		configdata.setServicename(servicename);
//		configdata.setConfigkey(configkey);
//		configdata.setConfigvalue(configvalue);
//		configdata.setComments(comments);
//		clientupdateService.insertConfigdata(configdata);
//		out.print("success");
//	}
//	
//	@RequestMapping(value = "/selconfigdataMessage", method = RequestMethod.POST)
//	public void selconfigdataMessage(Model model, HttpSession session,
//			HttpServletRequest request,String configdata_id,PrintWriter out) {
//		
//		configdata configdata=clientupdateService.getConfigDataByID(Integer.parseInt(configdata_id));
//	String a=JSONObject.toJSON(configdata).toString();
//	System.out.println(a);
//	out.print(a);
//	}
//	
//	@RequestMapping(value = "/saveConfigdataMessage", method = RequestMethod.POST)
//	public void saveConfigdataMessage(Model model, HttpSession session,
//			HttpServletRequest request,String id,String servicename,String configkey,String configvalue,String comments,PrintWriter out) {
//		configdata configdata=new configdata();
//		configdata.setId(Integer.parseInt(id));
//		configdata.setServicename(servicename);
//		configdata.setConfigkey(configkey);
//		configdata.setConfigvalue(configvalue);
//		configdata.setComments(comments);
//		clientupdateService.saveConfigdataMessage(configdata);
//		Admin admin=(Admin)session.getAttribute(Constants.SESSION_USER);
//		out.print(admin.getUser_guid());
//	}
	
//	@RequestMapping(value = "/delConfigdataMessage", method = RequestMethod.POST)
//	public void delConfigdataMessage(Model model, HttpSession session,
//			HttpServletRequest request,String id,PrintWriter out) {
//		clientupdateService.delConfigdataMessage(id);
//	}

}
