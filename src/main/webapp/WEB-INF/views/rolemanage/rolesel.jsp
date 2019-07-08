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
			<h1 class="page-header">角色管理</h1>
		</div>
		<!-- /.col-lg-12 -->
		<div class="form-group" style="padding-left: 2%">
			<h3 class="page-header">${role_name}</h3>
		</div>
		<div class="form-group" style="padding-left:2%">
			<input class="form-control" id="newrole_name" value="${role_name}" onblur="javascript:checkRoleName('${role_guid}');">
			<p class="text-danger" id="dangertext" style="display: none">角色名已被使用！</p>
		</div>
		<div class="form-group" style="padding-left:2%">
			<input class="form-control" id="newrole_des" value="${role_des}">
		</div>
		<div class="form-group" style="padding-left:2%">
			<button type="button" class="btn btn-primary"  id="savebtn"
				onclick="javascript:saveNewRoleMessage()">保存该角色基本信息</button>
		</div>
		<div class="form-group" style="padding-left: 2%">
			<h3 class="page-header">${role_name}权限配置
				<input class="form-control" id="role_guid" value="${role_guid}"
					style="display: none"> <input class="form-control"
					id="role_name" value="${role_name}" style="display: none">
			</h3>
		</div>
	</div>
	<div class="tree well" id="treetable">
		<ul>
			<li><span><i class="glyphicon glyphicon-folder-open"></i>
					权限</span> <!-- <input type="checkbox"
							 id="allselect" value="" onclick="javascript:allselect()" />选中所有权限 -->
				<ul>

					<c:forEach items="${parentPrivilegelist}" var="parentMessages"
						varStatus="ids">
						<li id="${parentMessages.privilege_guid}"><span><i
								class="glyphicon glyphicon-minus-sign"></i>${parentMessages.privilege_name}</span>
							<input type="checkbox" class="btnCheckAll"
							name="${parentMessages.privilege_guid}"
							id="${parentMessages.privilege_guid}"
							value="${parentMessages.privilege_guid}"
							${parentMessages.privilege_checked}
							onclick="javascript:childselect('${parentMessages.privilege_guid}')" />
							${parentMessages.privilege_des}
							<ul>
								<c:forEach items="${childPrivilegelist}" var="childMessages"
									varStatus="ids">
									<c:choose>
										<c:when
											test="${childMessages.privilege_parentguid==parentMessages.privilege_guid}">
											<li><span><i class="glyphicon glyphicon-leaf"></i>
													${childMessages.privilege_name}</span> <input type="checkbox"
												name="${parentMessages.privilege_guid}"
												${childMessages.privilege_checked }
												id="${childMessages.privilege_guid}"
												value="${childMessages.privilege_guid}"
												onclick="javascript:checkParentChecked('${childMessages.privilege_guid}','${parentMessages.privilege_guid}')" />
												${childMessages.privilege_des}</li>
										</c:when>
									</c:choose>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul></li>
		</ul>
	</div>
	<!-- /.tree well -->
	<div class="form-group">
		<button type="button" class="btn btn-primary"
			onclick="javascript:saveRoleConf()">保存该角色权限配置</button>
		<!-- data-toggle="modal" data-target="#insertRoleModel" -->
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
		function allselect(){
		 console.log($("#allselect").is(":checked"));
		 if($("#allselect").is(":checked") == false){
		 $("#treetable").find("input:checkbox").prop("checked",true);
		 }else{
		 $("#treetable").find("input:checkbox").prop("checked",false);
		 }
		
		 } 
	</script>
</body>
</html>