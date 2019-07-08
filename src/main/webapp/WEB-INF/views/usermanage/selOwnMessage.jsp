<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MotoBand CMS</title>

<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">个人信息</h1>
		</div>
		<!-- /.col-lg-12 -->
		<div class="form-group" style="padding-left: 2%">
			<h3 class="page-header">
				用户名：${userMessage.user_name} <input class="form-control"
					id="user_guid" value="${userMessage.user_guid}"
					style="display: none">
				<%-- <input class="form-control"
					id="role_name" value="${role_name}" style="display: none">  --%>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">个人信息配置</div>
				<div class="panel-body">
					<div class="form-group">
						<p class="text-primary">用户名</p>
						<input class="form-control" placeholder="用户名" id="user_name"
							value="${userMessage.user_name}">
					</div>
					<div class="form-group">
						<p class="text-primary">性别：</p>
						<c:choose>
							<c:when test="${userMessage.user_sex=='男'}">
								<input type="radio" name="usersex" id="man" value="男" checked="">男
						<input type="radio" name="usersex" id="woman" value="女">女
					</c:when>
							<c:otherwise>
								<input type="radio" name="usersex" id="man" value="男">男
						<input type="radio" name="usersex" id="woman" value="女" checked="">女
						</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<p class="text-primary">用户手机号：</p>
						<input class="form-control" placeholder="用户手机号" id="user_tel"
							value="${userMessage.user_tel }">
					</div>
				</div>
				<!-- body -->
			</div>
			<div class="panel-footer">
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#updatePasswordModel"
					>修改密码</button>
				<button type="button" class="btn btn-primary"
					onclick="javascript:saveOwnMessage()">保存该用户信息</button>
				<!--  -->
			</div>
		</div>
	</div>

	<script src="../js/usermanage/usermanage.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<!-- Modal -->
	<div class="modal fade" id="updatePasswordModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改个人密码</h4>
				</div>
				<div class="modal-body">
				<div class="form-group">
						<input class="form-control" type="password" placeholder="原密码或初始密码" id="oldpassword"
							value="">
					</div>
					<div class="form-group">
						<input class="form-control" type="password" placeholder="新密码" id="newpassword"
							value="" >
							
					</div>
					<div class="form-group">
						<input class="form-control password" placeholder="再次输入新密码" id="newpassword2"
							value="" type="password" onblur="javascript:checkpasswordSame()">
							<p class="text-danger" id="dangertext" style="display:none">请保持密码一致！</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"  onclick="javascript:updateOwnPassword()">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>
</html>