package com.motoband.admin.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.motoband.admin.admin.Admin;
import com.motoband.admin.admin.AdminService;
import com.motoband.admin.admin.Privilege;
import com.motoband.util.Constants;
import com.motoband.util.Consts;
import com.motoband.util.DESedeCoder;

@Controller
@RequestMapping(value = "/admin")
public class LoginAdminController {

	@Autowired
	private AdminService adminservice;
	
	@PostConstruct

	public  void initData(){
		Consts consts =new  Consts();
	}


	// 自动跳转到登录页面
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login2() {
		
	}

	// 登录首页
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public void login1(Login login, HttpSession session, Model model,HttpServletRequest req,HttpServletResponse resp,PrintWriter out) {
		Assert.notNull(login.getLoginname());
		Assert.notNull(login.getPassword());
		String loginname = login.getLoginname();
		String password = login.getPassword();
		
			password = Base64.encodeBase64String(
					DESedeCoder.encrypt(password.getBytes())).replaceAll(
					"\r\n", ""); // 密码加密
			String username = loginname;
			String pwd = password;
			Admin admin = adminservice.getAdminMessageByLoginnameAndPassword(
					username, pwd); // 获取登录用户信息
			if (admin == null) {
			
				/*RequestDispatcher rDispatcher=req.getRequestDispatcher("/admin/login");
				try {
					rDispatcher.forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			out.print("false");
			} else {

				session.setMaxInactiveInterval(144000); // Session的有效时间
				session.setAttribute(Constants.SESSION_USER, admin);
				session.setAttribute(Constants.SESSION_LOGINED, "logined");
				
                model.addAttribute("admin", admin);
               
            /*	RequestDispatcher rDispatcher=req.getRequestDispatcher("/admin/index");
    			try {
					rDispatcher.forward(req, resp);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
    			out.print("true");
			}
		
	}

	// 登录后首页
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public void functionIndex(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		model.addAttribute("admin", admin);
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void functionIndex1(Model model, HttpSession session) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		model.addAttribute("admin", admin);
	}

	// 登录后ajax首页
	@RequestMapping(value = "/privilege", method = RequestMethod.POST)
	public void functionIndexPrivilege(Model model, HttpSession session,PrintWriter out) {
		Admin admin = (Admin) session.getAttribute(Constants.SESSION_USER);
		String loginname = admin.getUser_loginname();
		ArrayList<Privilege> privilege = adminservice.getPrivilegeByloginname(loginname);
	    String jsonstr = JSON.toJSONString(privilege, true);
		model.addAttribute("privilege", privilege);
		out.print(jsonstr);
	}


	// 退出登录
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpSession session,HttpServletRequest req,HttpServletResponse resp) {
		session.invalidate(); // 清空session值
		RequestDispatcher rDispatcher=req.getRequestDispatcher("/admin/login");
		try {
			rDispatcher.forward(req, resp);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
