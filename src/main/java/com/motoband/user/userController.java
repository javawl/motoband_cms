package com.motoband.user;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.motoband.admin.admin.Admin;
import com.motoband.rolemanage.rolelist;
import com.motoband.util.Constants;
import com.motoband.util.DESedeCoder;

@Controller
@RequestMapping(value = "/usermanage")
public class userController {
	@Autowired
	private userService userService;

	@RequestMapping(value = "/userlist", method = RequestMethod.GET)
	public void userlist(Model model, HttpSession session,
			HttpServletRequest request, String userGuid) {

		ArrayList<userlist> userlist = userService
				.getUserlistByuserGuid(userGuid);
		int updateCheck = userService.getUserUpdateCheck(userGuid);
		int delCheck = userService.getUserDelCheck(userGuid);
		int insCheck = userService.getUserInsCheck(userGuid);
		if (updateCheck > 0) {
			//System.out.println(updateCheck);
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
		model.addAttribute("userlist", userlist);

	}

	@RequestMapping(value = "/insNewUser", method = RequestMethod.POST)
	public void insNewUser(Model model, HttpSession session,
			HttpServletRequest request, String user_name,
			String user_loginname, String user_sex, String user_tel,
			PrintWriter out) {
		String user_guid = UUID.randomUUID().toString();
		String user_password = "123";
		user_password = Base64.encodeBase64String(
				DESedeCoder.encrypt(user_password.getBytes())).replaceAll(
				"\r\n", ""); // 密码加密
		userModel userModel = new userModel();
		userModel.setUser_guid(user_guid);
		userModel.setUser_name(user_name);
		userModel.setUser_loginname(user_loginname);
		userModel.setUser_password(user_password);
		userModel.setUser_sex(user_sex);
		userModel.setUser_tel(user_tel);
		userModel.setRole_guid("10000");
		userService.insNewUser(userModel);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}

	@RequestMapping(value = "/usersel", method = RequestMethod.GET)
	public void usersel(Model model, HttpSession session,
			HttpServletRequest request, String user_guid) {
		userModel userModel = userService.getUserMessageByGuid(user_guid);
		ArrayList<rolelist> rolelist = userService.getRoleList();
		model.addAttribute("rolelist", rolelist);
		model.addAttribute("userMessage", userModel);
	}

	@RequestMapping(value = "/saveUserMessage", method = RequestMethod.POST)
	public void saveUserMessage(Model model, HttpSession session,
			HttpServletRequest request, String user_guid, String user_name,
			String user_loginname, String user_sex, String user_tel,
			String role_guid, PrintWriter out) {
		userModel userModel = new userModel();
		userModel.setUser_guid(user_guid);
		userModel.setUser_name(user_name);
		userModel.setUser_loginname(user_loginname);
		userModel.setUser_sex(user_sex);
		userModel.setUser_tel(user_tel);
		userModel.setRole_guid(role_guid);
		userService.updateUserMessageByGuid(userModel);
		out.print("success");
	}

	@RequestMapping(value = "/checkNewLoginUser", method = RequestMethod.POST)
	public void checkNewLoginUser(Model model, HttpSession session,
			HttpServletRequest request, String user_loginname, PrintWriter out) {
		userModel userModel = userService.checkLoginName(user_loginname);
		if (userModel == null) {
			out.print("ok");
		} else {
			out.print("no");
		}
	}
	@RequestMapping(value = "/selOwnMessage", method = RequestMethod.GET)
	public void selOwnMessage(Model model, HttpSession session,
			HttpServletRequest request, String userGuid) {
		userModel userModel=userService.getUserMessageByGuid(userGuid);
		model.addAttribute("userMessage", userModel);
	}
	
	@RequestMapping(value = "/saveOwnMessage", method = RequestMethod.POST)
	public void saveOwnMessage(Model model, HttpSession session,
			HttpServletRequest request, String user_guid,String user_sex,String user_tel,String user_name,PrintWriter out) {
		userService.updateOwnMessage(user_guid,user_name,user_sex,user_tel);
		out.print("success");
	}
	
	@RequestMapping(value = "/updateOwnPassword", method = RequestMethod.POST)
	public void updateOwnPassword(Model model, HttpSession session,
			HttpServletRequest request, String user_guid,String oldpassword,String newpassword,PrintWriter out) {
		//检验旧密码
		oldpassword = Base64.encodeBase64String(
				DESedeCoder.encrypt(oldpassword.getBytes())).replaceAll(
				"\r\n", ""); // 密码加密
		userModel userModel=userService.checkPasswordByGuid(user_guid,oldpassword);
		if (userModel==null) {
			out.print("false");
		}else {
			newpassword=Base64.encodeBase64String(
					DESedeCoder.encrypt(newpassword.getBytes())).replaceAll(
							"\r\n", ""); // 密码加密
			userService.updatePasswordByGuid(user_guid,newpassword);
			out.print("success");
		}

	}
	
	@RequestMapping(value = "/delUserMessage", method = RequestMethod.POST)
	public void delUserMessage(Model model, HttpSession session,
			HttpServletRequest request, String user_guid,PrintWriter out) {
		userService.delUserMessage(user_guid);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}
	@RequestMapping(value = "/resetUserPsw", method = RequestMethod.POST)
	public void resetUserPsw(Model model, HttpSession session,
			HttpServletRequest request, String user_guid,PrintWriter out) {
		String user_password = "123";
		user_password = Base64.encodeBase64String(
				DESedeCoder.encrypt(user_password.getBytes())).replaceAll(
				"\r\n", ""); // 密码加密
		userService.resetUserPsw(user_guid,user_password);
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		out.print(admin.getUser_guid());
	}
	
}
