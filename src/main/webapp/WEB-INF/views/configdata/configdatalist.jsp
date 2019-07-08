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
			<h1 class="page-header">参数管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row" style="margin-bottom: 2%; padding-left: 2%;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertConfigdataModel">增加</button>
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
				<div class="panel-heading">参数列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example" style="width:1550px">
							<thead>
								<tr>
									<th>servicename</th>
									<th>configkey</th>
									<th>configvalue</th>
									<th>comments</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${configdatas}" var="configdata"
									varStatus="ids">
									<tr class="${configdata.id}">
										<td>${configdata.servicename}</td>
										<td>${configdata.configkey}</td>
										<td style="word-break:break-all;">${configdata.configvalue}</td>
										<td>${configdata.comments}</td>
										<td class="center"><c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#editModel"
														onclick="javascript:selconfigdataMessage('${configdata.id}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#delConfdataModel"
														onclick="javascript:delConfdataGuid('${configdata.id}')">删除</button>
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
		$ (document).ready (function ()
        {
	        $ ('#dataTables-example').DataTable (
	        {

		        responsive : true
	        });
        });
	</script>


	<!-- Modal -->
	<div class="modal fade" id="delConfdataModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除用户</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delConfigdataGuid">
					<h3>删除用户信息后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:delConfigdataMessage()" data-dismiss="modal">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="editModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">编辑参数</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
					<input class="form-control"  id="edit_id"
							value="" style="display:none">
						<input class="form-control" placeholder="服务器名"
							id="edit_servicename" value="">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="参数key"
							id="edit_configkey" value="">
						<p class="text-danger" id="dangertext" style="display: none">登录名已被使用！</p>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="参数值"
							id="edit_configvalue" value="">

					</div>
					<div class="form-group">
						<input class="form-control" placeholder="参数描述" id="edit_comments"
							value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:saveConfigdataMessage()" data-dismiss="modal">确认修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertConfigdataModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新参数</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="服务器名" id="servicename"
							value="">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="参数key" id="configkey"
							value="">
						<p class="text-danger" id="dangertext" style="display: none">登录名已被使用！</p>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="参数值" id="configvalue"
							value="">

					</div>
					<div class="form-group">
						<input class="form-control" placeholder="参数描述" id="comments"
							value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:saveNewConfigdata()" id="savebtn"
						data-dismiss="modal">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- usermanage.js -->
	<script src="../js/configdata/configdata.js"></script>
</body>


</html>