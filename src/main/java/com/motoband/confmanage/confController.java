package com.motoband.confmanage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motoband.admin.admin.Admin;
import com.motoband.admin.admin.Privilege;
import com.motoband.util.Constants;

@Controller
@RequestMapping(value = "/confmanage")
public class confController {
	@Autowired
	private confService confService;

	@RequestMapping(value = "/conflist", method = RequestMethod.GET)
	public void conflist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid) {

		ArrayList<conflist> conflist = confService
				.getConflistByuserGuid(userGuid);

		int updateCheck = confService.getConfUpdateCheck(userGuid);
		int delCheck = confService.getConfDelCheck(userGuid);
		int insCheck = confService.getConfInsCheck(userGuid);
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
		model.addAttribute("conflist", conflist);
	}

	@RequestMapping(value = "/insertConf", method = RequestMethod.POST)
	public void insertConf(Model model, HttpSession session,
			HttpServletRequest request, PrintWriter out, String conf_name,
			String conf_key, String conf_des) {
	//	System.out.println("conf_name+" + conf_name);
	//	System.out.println("conf_key+" + conf_key);
	//	System.out.println("conf_des+" + conf_des);
		String conf_guid = UUID.randomUUID().toString();
	//	System.out.println("conf_guid+" + conf_guid);
		String conf_keymain = conf_key + "_manage";
		conflist conflistmain1 = new conflist();
		conflistmain1.setPrivilege_guid(conf_guid);
		conflistmain1.setPrivilege_name(conf_name);
		conflistmain1.setPrivilege_key(conf_keymain);
		conflistmain1.setPrivilege_parentguid("0");
		conflistmain1.setPrivilege_url(null);
		conflistmain1.setPrivilege_des(conf_des);


		conflist conflistmain2=new conflist();
		String conf_guidnew = UUID.randomUUID().toString();
		conflistmain2.setPrivilege_guid(conf_guidnew);
		conflistmain2.setPrivilege_name(conf_name + "查询");
		conflistmain2.setPrivilege_key(conf_key + "_sel");
		conflistmain2.setPrivilege_parentguid(conf_guid);
		conflistmain2.setPrivilege_url(null);
		conflistmain2.setPrivilege_des(null);


		conflist conflistmain3=new conflist();
		conf_guidnew = UUID.randomUUID().toString();
		conflistmain3.setPrivilege_guid(conf_guidnew);
		conflistmain3.setPrivilege_name(conf_name + "修改");
		conflistmain3.setPrivilege_key(conf_key + "_upd");
		conflistmain3.setPrivilege_parentguid(conf_guid);
		conflistmain3.setPrivilege_url(null);
		conflistmain3.setPrivilege_des(null);


		conflist conflistmain4=new conflist();
		conf_guidnew = UUID.randomUUID().toString();
		conflistmain4.setPrivilege_guid(conf_guidnew);
		conflistmain4.setPrivilege_name(conf_name + "删除");
		conflistmain4.setPrivilege_key(conf_key + "_del");
		conflistmain4.setPrivilege_parentguid(conf_guid);
		conflistmain4.setPrivilege_url(null);
		conflistmain4.setPrivilege_des(null);

		
		conflist conflistmain5=new conflist();
		conf_guidnew = UUID.randomUUID().toString();
		conflistmain5.setPrivilege_guid(conf_guidnew);
		conflistmain5.setPrivilege_name(conf_name + "添加");
		conflistmain5.setPrivilege_key(conf_key + "_ins");
		conflistmain5.setPrivilege_parentguid(conf_guid);
		conflistmain5.setPrivilege_url(null);
		conflistmain5.setPrivilege_des(null);
		confService.insertPrivilege(conflistmain1,conflistmain2,conflistmain3,conflistmain4,conflistmain5);
		
		

		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}

	@RequestMapping(value = "/confsel", method = RequestMethod.GET)
	public void csel(Model model, HttpSession session,
			HttpServletRequest request, String conf_guid) {
		Privilege privilege = confService.selPrivilegeByGuid(conf_guid);
		privilege.setPrivilege_key(privilege.getPrivilege_key().substring(0, privilege.getPrivilege_key().indexOf("_")));
		
		ArrayList<Privilege> privilegelist = confService
				.selchildPrivilegesByGuid(conf_guid);
		for (Privilege prl : privilegelist) {
			prl.setPrivilege_key(prl.getPrivilege_key().substring(prl.getPrivilege_key().indexOf("_")+1));
		}
		model.addAttribute("parentConf", privilege);
		model.addAttribute("childConf", privilegelist);
	
	}
	@RequestMapping(value = "/saveConf", method = RequestMethod.POST)
	public void saveConf(Model model, HttpSession session,
			HttpServletRequest request, String conf_guid,String conf_name,String conf_key,String conf_des,PrintWriter out) {
		confService.saveChildConfByPartentGuid(conf_guid,conf_key);
		conf_key=conf_key+"_manage";	
		confService.saveConfByGuid(conf_guid,conf_name,conf_key,conf_des);
		out.print(conf_guid);
	}
	@RequestMapping(value = "/saveChildConf", method = RequestMethod.POST)
	public void saveChildConf(Model model, HttpSession session,
			HttpServletRequest request, String conf_guid,String conf_name,String conf_key,String conf_des,PrintWriter out) {
		confService.saveChildConfByChild(conf_guid,conf_name,conf_key,conf_des);
		out.print(conf_guid);
	}
	
	@RequestMapping(value = "/insertChildConf", method = RequestMethod.POST)
	public void insertChildConf(Model model, HttpSession session,
			HttpServletRequest request, String partentconf_guid,String conf_name,String conf_key,String conf_des,PrintWriter out) {
		String conf_guid=UUID.randomUUID().toString();
		String conf_parentguid=partentconf_guid;
		confService.insertChildConf(conf_guid,conf_name,conf_key,conf_parentguid,conf_des);
		out.print(partentconf_guid);
	}
	
	@RequestMapping(value = "/delChildConf", method = RequestMethod.POST)
	public void delChildConf(Model model, HttpSession session,
			HttpServletRequest request, String conf_guid,String conf_parentguid,PrintWriter out) {
		
		confService.delChildConf(conf_guid);
		out.print(conf_parentguid);
	}
	@RequestMapping(value = "/checkNewConfKey", method = RequestMethod.POST)
	public void checkNewConfKey(Model model, HttpSession session,
			HttpServletRequest request, String conf_key,PrintWriter out) {
		
		int i=confService.checkNewConfKey(conf_key);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	@RequestMapping(value = "/checkNewConfName", method = RequestMethod.POST)
	public void checkNewConfName(Model model, HttpSession session,
			HttpServletRequest request, String conf_name,PrintWriter out) {
		
		int i=confService.checkNewConfName(conf_name);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkNewParentConfKey", method = RequestMethod.POST)
	public void checkNewParentConfKey(Model model, HttpSession session,
			HttpServletRequest request, String parentconf_key,String parentconf_guid,PrintWriter out) {	
		int i=confService.checkNewParentConfKey(parentconf_key,parentconf_guid);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkNewParentConfName", method = RequestMethod.POST)
	public void checkNewParentConfName(Model model, HttpSession session,
			HttpServletRequest request, String parentconf_name,String parentconf_guid,PrintWriter out) {	
		int i=confService.checkNewParentConfName(parentconf_name,parentconf_guid);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkNewChildConfKey", method = RequestMethod.POST)
	public void checkNewChildConfKey(Model model, HttpSession session,
			HttpServletRequest request, String childconf_key,String childconf_guid,PrintWriter out) {	
		int i=confService.checkNewChildConfKey(childconf_key,childconf_guid);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkNewChildConfName", method = RequestMethod.POST)
	public void checkNewChildConfName(Model model, HttpSession session,
			HttpServletRequest request, String childconf_name,String childconf_guid,PrintWriter out) {	
		int i=confService.checkNewChildConfName(childconf_name,childconf_guid);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkchildname", method = RequestMethod.POST)
	public void checkchildname(Model model, HttpSession session,
			HttpServletRequest request, String parent_guid,String childname,PrintWriter out) {	
		int i=confService.checkchildname(parent_guid,childname);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
	
	@RequestMapping(value = "/checkchildkey", method = RequestMethod.POST)
	public void checkchildkey(Model model, HttpSession session,
			HttpServletRequest request, String parent_guid,String childconf_key,PrintWriter out) {	
		int i=confService.checkchildkey(parent_guid,childconf_key);
		if (i==0) {
		   out.print("ok");
		} else {
            out.print("no");
		}
		
	}
}
