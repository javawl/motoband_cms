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
<body onload="">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">配置管理</h1>
		</div>
		<!-- /.col-lg-12 -->

		<div class="form-group">
			<input class="form-control" placeholder="父权限名称" id="parentconf_name"
				value="${parentConf.privilege_name}" onblur="javascript:checkNewParentConfName('${parentConf.privilege_guid}')">
				<p class="text-danger" id="dangertextName" style="display: none">父权限名字已被使用！</p>
		</div>
		<div class="form-group">
			<input class="form-control" placeholder="父权限KEY" id="parentconf_key"
				value="${parentConf.privilege_key}" onblur="javascript:checkNewParentConfKey('${parentConf.privilege_guid}')">
				<p class="text-danger" id="dangertextKey" style="display: none">父权限KEY已被使用！</p>
		</div>
		<div class="form-group">
			<input class="form-control" placeholder="父权限描述" id="parentconf_des"
				value="${parentConf.privilege_des}">
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-primary" id="savebtn"
				onclick="javascript:saveConf('${parentConf.privilege_guid}')">确认修改父权限</button>
			<!-- data-toggle="modal" data-target="#insertRoleModel" -->
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-success"
			 data-toggle="modal"  data-target="#insertChildConfModel">增加子权限</button>
		</div>

		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">配置子权限</div>
				<!-- .panel-heading -->
				<div class="panel-body">
					<div class="panel-group" id="accordion">


						<c:forEach items="${childConf}" var="ConfMessages" varStatus="ids">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion"
											href="#${ConfMessages.privilege_guid}">${ConfMessages.privilege_name}</a>
									</h4>
								</div>
								<div id="${ConfMessages.privilege_guid}"
									class="panel-collapse collapse">
									<div class="panel-body">
										<div class="form-group">
											<input class="form-control" placeholder="配置权限名称"
												id="conf_name" value='${ConfMessages.privilege_name}' onblur="javascript:checkNewChildConfName('${ConfMessages.privilege_guid}');">
												<p class="text-danger" id="dangertextChildName" style="display: none">子权限名字已被使用！</p>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="配置key值"
												id="conf_key" value='${ConfMessages.privilege_key}' onblur="javascript:checkNewChildConfKey('${ConfMessages.privilege_guid}')">
												<p class="text-danger" id="dangertextChildKey" style="display: none">子权限KEY已被使用！</p>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="配置描述"
												id="conf_des"  value='${ConfMessages.privilege_des}'>
										</div>
										<div class="form-group">
											<button type="button" class="btn btn-primary"
												onclick="javascript:saveChildConf('${ConfMessages.privilege_guid}','${parentConf.privilege_guid}')" id="savebtn">修改子权限</button>
												&nbsp;
											<!-- data-toggle="modal" data-target="#insertRoleModel" -->
											<button type="button" class="btn btn-primary"
											data-toggle="modal" data-target="#delChildConfModel"
											onclick="javascript:openDelChildConfModel('${ConfMessages.privilege_guid}');">删除子权限</button>
											<!--  -->
										</div>
									</div>
								</div>
							</div>
						</c:forEach>


					</div>
				</div>
				<!-- .panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

	<!-- confmanage.js -->
	<script src="../js/confmanage/confmanage.js"></script>

	
	<!-- Modal -->
	<div class="modal fade" id="insertChildConfModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新子权限</h4>
				</div>
				<div class="modal-body">
						<fieldset>
							<div class="form-group">
								<input class="form-control" placeholder="新配置权限名称"  id="childconf_name" onblur="javascript:checkchildname('${parentConf.privilege_guid}');">
								<p class="text-danger" id="dangertextNewChildName" style="display: none">新子权限名字已被使用！</p>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="配置key值" id="childconf_key" onblur="javascript:checkchildkey('${parentConf.privilege_guid}')">
								<p class="text-danger" id="dangertextNewChildKey" style="display: none">新子权限key已被使用！</p>
							</div>
							<div class="form-group">
								<input class="form-control" placeholder="配置描述" id="childconf_des">
							</div>
						</fieldset>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="inssavebtn" onclick="javascript:insertChildConf('${parentConf.privilege_guid}')">保存</button>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal -->
	<div class="modal fade" id="delChildConfModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除子权限</h4>
				</div>
				<div class="modal-body">
						<fieldset>
							<div class="form-group">
							<input type="text" id="delChildConfInput" style="display:none" value=""/>
							   <h2>确定删除该子权限，删除后无法恢复</h2>
							</div>
						</fieldset>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消删除</button>
					<button type="button" class="btn btn-primary" onclick="javascript:delChildConf('${parentConf.privilege_guid}')">确认删除</button>
				</div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
</body>


</html>