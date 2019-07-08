<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="../js/consts.js"></script>
<script>
	var format = function (time, format)
    {
	    var t = new Date (time);
	    var tf = function (i)
	    {
		    return (i < 10 ? '0' : '') + i
	    };
	    return format.replace (/yyyy|MM|dd|HH|mm|ss/g, function (a)
	    {
		    switch (a)
		    {
			    case 'yyyy':
				    return tf (t.getFullYear ());
				    break;
			    case 'MM':
				    return tf (t.getMonth () + 1);
				    break;
			    case 'mm':
				    return tf (t.getMinutes ());
				    break;
			    case 'dd':
				    return tf (t.getDate ());
				    break;
			    case 'HH':
				    return tf (t.getHours ());
				    break;
			    case 'ss':
				    return tf (t.getSeconds ());
				    break;
		    }
	    })
    }
</script>
</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">认证用户</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">认证用户</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">

						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>userid</th>
									<th>gender</th>
									<th>birth</th>
									<th>nickname</th>
									<th>area</th>
									<th>headurl</th>
									<th>signature</th>
									<th>channel</th>
									<th>level</th>
									<th>province</th>
									<th>city</th>
									<th>operate</th>
								
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${userModels}" var="userMessages"
									varStatus="ids">
									<tr class="${userMessages.userid}">
										<td><a href="javascript:gotoMotoUserlist('${userMessages.userid}','${sessionScope.user.user_guid}')">${userMessages.userid}</a></td>
										<td>${userMessages.gender}</td>
										<td>${userMessages.birth}</td>
										<td>${userMessages.nickname }</td>
										<td>${userMessages.area }</td>
										<td><img style='width:60px;height:60px' src='${userMessages.headurl}!thumb'></td>
										<td>${userMessages.signature }</td>
										<td>${userMessages.channel }</td>
										<td>${userMessages.level }</td>
										<td>${userMessages.province }</td>
										<td>${userMessages.city }</td>
										<td class="center">
											暂无操作
											<%-- <c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#updatescoreModel"
														onclick="javascript:updateScore('${userMessages.userid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#delBoxModel"
														onclick="javascript:delRecommenddiag('${userMessages.userid}')">删除</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">删除</button>
												</c:otherwise> 
											</c:choose>  --%>
										</td>
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
	            pageLength : 20,//首次加载的数据条数
	            processing : true,//载入数据的时候是否显示“载入中”
	            lengthMenu : [
	                    [
	                            20, 50, 100, -1
	                    ], [
	                            20, 50, 100, "All"
	                    ]
	            ],
	            order : [
		            [
		                    0, "desc"
		            ]
	            ]
	        });
	        
        });
	</script>
	

	<!-- Modal -->
	<div class="modal fade" id="delBoxModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除精选</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delNewNid">
					<h3>删除后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delRecommend()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="updatescoreModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改权重</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="" id="update_nid"
							value="" readonly="readonly">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="" id="update_score"
							value="">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveUserRecommend()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script src="../js/motouser/recommend.js"></script>
</body>


</html>