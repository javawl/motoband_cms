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
			<h1 class="page-header">用户管理</h1>
		</div>
		<!-- /.col-lg-12 -->
		<div class="form-group" style="padding-left: 2%">
			<h3 class="page-header">
				用户：${userMessage.user_name}
				 <input class="form-control" id="user_guid" value="${userMessage.user_guid}"
					style="display: none"><%-- <input class="form-control"
					id="role_name" value="${role_name}" style="display: none">  --%>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">用户信息配置</div>
				<div class="panel-body">
					<div class="form-group">
					    <p class="text-primary">用户名</p>
						<input class="form-control" placeholder="用户名" id="user_name"
							value="${userMessage.user_name}">
					</div>
					<div class="form-group">
					    <p class="text-primary">登录名</p>
						<input class="form-control" placeholder="登录名" id="user_loginname"
							value="${userMessage.user_loginname}" onblur="javascript:checkNewLoginName('${userMessage.user_loginname}')">
							<p class="text-danger" id="dangertext" style="display:none">登录名已被使用！</p>
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
					
					<div class="col-lg-12">
						<div class="well">
							<h4>角色选择</h4>

							<c:forEach items="${rolelist}" var="roleMessage" varStatus="ids">
								<c:choose>
									<c:when test="${ids.index%3==0 }">
										<div class="form-group">
									</c:when>
								</c:choose>


								<c:choose>
									<c:when
										test="${roleMessage.role_guid==userMessage.role_guid}">
										<label class="radio-inline"> <input type="radio"
											name="userrole" id="${roleMessage.role_guid }"
											value="${roleMessage.role_guid }" checked="checked">${roleMessage. role_name}
										</label>
									</c:when>

									<c:otherwise>
										<label class="radio-inline"> <input type="radio"
											name="userrole" id="${roleMessage.role_guid }"
											value="${roleMessage.role_guid }">${roleMessage. role_name}
											</label>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${ids.index%3==2 }">
						</div>
						</c:when>
						</c:choose>

						</c:forEach>

					</div>
				</div>
				<!-- body -->
			</div>
			<div class="panel-footer">
				<button type="button" class="btn btn-primary"
					onclick="javascript:saveUserMessage()" id="savebtn">保存该用户信息</button>
				<!--  -->
				<button type="button" class="btn btn-primary"
				data-toggle="modal" data-target="#insertRoleModel"
					onclick="javascript:resetUserPsw()" id="savebtn">重置用户密码</button>
			</div>
		</div>
	</div>





	<!-- saveRoleConf -->

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr(
					'title', 'Collapse this branch');
			$('.tree li.parent_li > span').on(
					'click',
					function(e) {
						var children = $(this).parent('li.parent_li').find(
								' > ul > li');
						if (children.is(":visible")) {
							children.hide('fast');
							$(this).attr('title', 'Expand this branch').find(
									' > i').addClass('icon-plus-sign')
									.removeClass('icon-minus-sign');
							$(this).attr('title', 'Expand this branch').find(
									' > i').addClass('glyphicon-plus-sign')
									.removeClass('glyphicon-minus-sign');
						} else {
							children.show('fast');
							$(this).attr('title', 'Collapse this branch').find(
									' > i').addClass('icon-minus-sign')
									.removeClass('icon-plus-sign');
							$(this).attr('title', 'Collapse this branch').find(
									' > i').addClass('glyphicon-minus-sign')
									.removeClass('glyphicon-plus-sign');
						}
						e.stopPropagation();
					});
		});

		function childselect(guid) {
			if ($("#" + guid).children("input[name='" + guid + "']:checkbox")
					.is(":checked") == true) {
				$("#" + guid).find("input[name='" + guid + "']:checkbox").prop(
						"checked", true);
			} else {

				$("#" + guid).find("input[name='" + guid + "']:checkbox").prop(
						"checked", false);
			}
		}
		function checkParentChecked(childguid, parentguid) {

			if ($("#" + childguid).is(":checked") == false) {
				$("#treetable").find("input[id='" + parentguid + "']:checkbox")
						.prop("checked", false);
			}
		}
		/*function allselect(){
		 console.log($("#allselect").is(":checked"));
		 if($("#allselect").is(":checked") == false){
		 $("#treetable").find("input:checkbox").prop("checked",true);
		 }else{
		 $("#treetable").find("input:checkbox").prop("checked",false);
		 }
		
		 } */
	</script>
</body>
</html>