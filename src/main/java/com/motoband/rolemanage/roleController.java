package com.motoband.rolemanage;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.motoband.util.Constants;

@Controller
@RequestMapping(value = "/rolemanage")
public class roleController {
	@Autowired
	private roleService roleService;

	@RequestMapping(value = "/rolelist", method = RequestMethod.GET)
	public void rolelist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid) {

		ArrayList<rolelist> rolelist = roleService
				.getRolelistByuserGuid(userGuid);

		int updateCheck = roleService.getRoleUpdateCheck(userGuid);
		int delCheck = roleService.getRoleDelCheck(userGuid);
		int insCheck = roleService.getRoleInsCheck(userGuid);
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
		model.addAttribute("rolelist", rolelist);

	}

	@RequestMapping(value = "/rolesel", method = RequestMethod.GET)
	public void rolesel(Model model, HttpSession session,
			HttpServletRequest request, String role_guid, String role_name,String role_des) {
		ArrayList<parentPrivilegelist> parentPrivilegelist = roleService
				.getParentPrivilegelist(role_guid);
		ArrayList<RolePrivilegeListByroleguid> rolePrivilegeListByroleguids = roleService
				.getRolePrivilegeListByroleguid(role_guid);
		ArrayList<Privilegelist> childPrivilegelist = roleService
				.getChildPrivilegelist();

		for (parentPrivilegelist parentPrivilegelist2 : parentPrivilegelist) {
			for (RolePrivilegeListByroleguid rolePrivilegeListByroleguid : rolePrivilegeListByroleguids) {
				if (parentPrivilegelist2.getPrivilege_guid().equals(
						rolePrivilegeListByroleguid.getPrivilege_guid())) {
					parentPrivilegelist2.setPrivilege_checked("checked");
					for (Privilegelist privilegelist : childPrivilegelist) {
						if (parentPrivilegelist2.getPrivilege_guid().equals(
								privilegelist.getPrivilege_parentguid())) {
							privilegelist.setPrivilege_checked("checked");
						}
					}
				}
			}
		}
		// ArrayList<Privilege>
		// parentPrivilegesNoHaveChildlist=roleService.getParentPriviegesNoHaveChild();
		for (Privilegelist privilegelist : childPrivilegelist) {
			for (RolePrivilegeListByroleguid rolePrivilegeListByroleguid : rolePrivilegeListByroleguids) {
				if (privilegelist.getPrivilege_guid().equals(
						rolePrivilegeListByroleguid.getPrivilege_guid())) {
					privilegelist.setPrivilege_checked("checked");
				}
			}
		}
		model.addAttribute("parentPrivilegelist", parentPrivilegelist);
		// model.addAttribute("parentPrivilegesNoHaveChildlist",
		// parentPrivilegesNoHaveChildlist);
		model.addAttribute("childPrivilegelist", childPrivilegelist);
		model.addAttribute("role_guid", role_guid);
		role_name = roleService.getRolenameByRoleguid(role_guid);
		model.addAttribute("role_name", role_name);
		try {
			role_des=new String(role_des.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("role_des", role_des);
	}

	@RequestMapping(value = "/saveRoleConf", method = RequestMethod.POST)
	public void saveRoleConf(Model model, HttpSession session,
			HttpServletRequest request, String role_guid, String role_name,
			String role_conf, PrintWriter out) {
		String[] roleconfStrings = role_conf.split(",");
		ArrayList<String> roleconflist = new ArrayList<String>();
		String parentguid = "";
		int k = 0;
		for (String rolestring : roleconfStrings) {
			k = 0;
			parentguid = roleService.getParentGuidByChildGuid(rolestring);
			for (String str : roleconfStrings) {
				if (parentguid.equals(str)) {
					k = 1;
				}
			}
			if (k == 0) {
				roleconflist.add(rolestring);
			}
		}
		// 删除该角色权限
		roleService.delRoleConf(role_guid);
		for (String stri : roleconflist) {
			roleService.insRoleConf(role_guid, stri);
		}
		out.print("success");
	}

	@RequestMapping(value = "/insNewRole", method = RequestMethod.POST)
	public void insNewRole(Model model, HttpSession session,
			HttpServletRequest request, String role_name, String role_des,
			PrintWriter out) {
		String role_guid = UUID.randomUUID().toString();
		roleService.insNewRole(role_guid, role_name, role_des);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}

	@RequestMapping(value = "/delRoleMessage", method = RequestMethod.POST)
	public void delRoleMessage(Model model, HttpSession session,
			HttpServletRequest request, String role_guid, PrintWriter out) {
		roleService.delRoleMessage(role_guid);
		/*
		 * roleService.delRoleMessage(role_guid);
		 * roleService.delRolePrivilege(role_guid);
		 * roleService.updateUserRole(role_guid);
		 */
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}

	@RequestMapping(value = "/checkNewRoleName", method = RequestMethod.POST)
	public void checkNewRoleName(Model model, HttpSession session,
			HttpServletRequest request, String newrolename, PrintWriter out) {
		int i = roleService.checkNewRoleName(newrolename);
		if (i == 0) {
			out.print("ok");
		} else {
			out.print("no");
		}

	}

	@RequestMapping(value = "/saveNewRoleMessage", method = RequestMethod.POST)
	public void saveNewRoleMessage(Model model, HttpSession session,
			HttpServletRequest request, String role_guid, String newrole_name,
			String newrole_des, PrintWriter out) {
		roleService.saveNewRoleMessage(role_guid, newrole_name, newrole_des);
		out.print("success");

	}
	
	@RequestMapping(value = "/checkRoleName", method = RequestMethod.POST)
	public void checkRoleName(Model model, HttpSession session,
			HttpServletRequest request, String newrole_name,String role_guid, PrintWriter out) {
		int i = roleService.checkRoleName(role_guid,newrole_name);
		if (i == 0) {
			out.print("ok");
		} else {
			out.print("no");
		}

	}

}
