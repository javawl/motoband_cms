package com.motoband.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.motoband.util.Constants;
import com.motoband.util.PatternMatcher;
import com.motoband.util.ServletPathMatcher;

public class SecurityFilter implements Filter {
	
	//private static final Logger logger = LoggerFactory.getLogger(SecurityFilter.class);
	private static final String PARAM_NAME_EXCLUSIONS = "exclusions";

	protected PatternMatcher pathMatcher = new ServletPathMatcher();
	private Set<String> excludesPattern;
	private String contextPath;
	private ApplicationContext applicationContext;
	
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			// 初始化全局路径
		//	Constants.WEB_APP_PATH = filterConfig.getServletContext().getRealPath("/");
			
			// 初始化过滤条件
			String exclusions = filterConfig.getInitParameter(PARAM_NAME_EXCLUSIONS);
			if (exclusions != null && exclusions.trim().length() != 0) {
				excludesPattern = new HashSet<String>(Arrays.asList(exclusions.split("\\s*,\\s*")));
			}
			contextPath = filterConfig.getServletContext().getContextPath();
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String requestURI = req.getRequestURI();
		// 判断是否需要判断安全认证
		if (isExclusion(requestURI)) {
			chain.doFilter(request, response);
			return;
		}
		/*
		if ((!requestURI.contains("/login")) && requestURI.indexOf("css")<0 && requestURI.indexOf("wb")<0){
		*/
		HttpSession session = req.getSession();
		if (!"logined".equals(session.getAttribute(Constants.SESSION_LOGINED))) {
		  
			//String urlllString=req.getContextPath()+"/admin/login";
			
			RequestDispatcher rDispatcher=req.getRequestDispatcher("/admin/login");
			rDispatcher.forward(req, resp);
		    //resp.sendRedirect(urlllString);
		} else {
			
			if ("/".equals(req.getRequestURI())) {
				session.setAttribute(Constants.SESSION_LOGINED, "nologin");
				request.getRequestDispatcher("/admin/login").forward(req,resp);
			}else {
				chain.doFilter(request, response);
			}
		/*	request.getRequestDispatcher("/admin/index").forward(req,resp);*/
			
		}

	}

	public boolean isExclusion(String requestURI) {
		if (excludesPattern == null) {
			return false;
		}

		if (contextPath != null && requestURI.startsWith(contextPath)) {
			requestURI = requestURI.substring(contextPath.length());
			if (!requestURI.startsWith("/")) {
				requestURI = "/" + requestURI;
			}
		}

		for (String pattern : excludesPattern) {
			if (pathMatcher.matches(pattern, requestURI)) {
				return true;
			}
		}

		return false;
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

}
