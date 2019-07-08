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

<!-- DataTables CSS -->
<link
	href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link
	href="../bower_components/datatables-responsive/css/dataTables.responsive.css"
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
			<h1 class="page-header">角色管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row" style="margin-bottom: 2%; padding-left: 2%;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertRoleModel">增加</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加</button>
			</c:otherwise>
		</c:choose>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">配置列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>rolename</th>
									<th>roledes</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${rolelist}" var="RoleMessages"
									varStatus="ids">
									<tr class="${RoleMessages.role_guid}">
										<td>${RoleMessages.role_name}</td>
										<td>${RoleMessages.role_des}</td>
										<td class="center"><c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														onclick="javascript:rolesel('${RoleMessages.role_guid}','${RoleMessages.role_name}' ,'${RoleMessages.role_des }')">编辑</button>
													<!-- data-toggle="modal" data-target="#updateRoleModel" -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#delRoleModel"
														onclick="javascript:delRoleGuid('${RoleMessages.role_guid}')">删除</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">删除</button>
												</c:otherwise>
											</c:choose></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->


	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>
	<!-- Modal -->
	<div class="modal fade" id="delRoleModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除角色信息</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delRoleGuid">
					<h3>删除角色信息后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="javascript:delRoleMessage()">确认删除该角色</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertRoleModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新角色</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="新角色名称" id="newrolename"
							value="" onblur="javascript:checkNewRoleName();">
							<p class="text-danger" id="dangertext" style="display: none">角色名已被使用！</p>
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="新角色描述" id="newroledes"
							value="">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						onclick="javascript:insRole()">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- confmanage.js -->
	<script src="../js/rolemanage/rolemanage.js"></script>

</body>


</html>