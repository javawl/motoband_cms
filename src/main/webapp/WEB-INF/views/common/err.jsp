<%@page contentType="text/html;charset=UTF-8" %>
<%@page pageEncoding="UTF-8" %>

<div data-role="page">
    <div data-role="header" data-theme="c">
    	
    	<h1>错误页面</h1>
    </div>
    <div data-role="content">
       <%--  <% Exception ex = (Exception)request.getAttribute("exception"); %>
        <H2>Exception: <%= ex.getMessage()%></H2>
        <P/>
        <%ex.printStackTrace(new java.io.PrintWriter(out)); %> --%>
        系统出现异常,返回系统首页
    </div>
</div>
<%@include file="end.jsp" %>