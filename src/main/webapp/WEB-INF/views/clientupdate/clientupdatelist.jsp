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
<script type="text/javascript">
function changectypeselect(adminGuid,obj){
	$("#page-wrapper").load("../clientupdate/clientupdatelist?userGuid=" + adminGuid+ "&ctype="+obj.value);

} 
</script>

</head>

<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">更新管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row col-lg-2" style="margin-bottom: 5px;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertUpdateModel"  onclick="javascript:insUpdateConf('${sessionScope.user.user_guid}')">增加更新</button>

			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加更新</button>
			</c:otherwise>
		</c:choose>

	</div>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">更新列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					
					     <div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  ctype: </label>
							  <select class="form-control" id="ctypeSelect"  onchange="javascript:changectypeselect('${sessionScope.user.user_guid}',this)">
										<c:choose>
											<c:when test="${ctype=='1' }">
										           <option value="1" selected>Ios</option>
										           <option value="2" >Android</option>
										           <option value="3" >BussinessIos</option>
										           <option value="4" >BusinessAndroid</option>
											</c:when>
											<c:when test="${ctype=='2' }">
										           <option value="1" >Ios</option>
										           <option value="2" selected>Android</option>
										           <option value="3" >BussinessIos</option>
										           <option value="4" >BusinessAndroid</option>
											</c:when>
											<c:when test="${ctype=='3' }">
										           <option value="1" >Ios</option>
										           <option value="2" >Android</option>
										           <option value="3" selected>BussinessIos</option>
										           <option value="4" >BusinessAndroid</option>
											</c:when>
											<c:when test="${ctype=='4' }">
										           <option value="1" >Ios</option>
										           <option value="2" >Android</option>
										           <option value="3" >BussinessIos</option>
										           <option value="4" selected>BusinessAndroid</option>
											</c:when>
										</c:choose>
									
							  </select>
						</div> 
					</div>
					
					
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
								    <th>id</th>
									<th>cversion</th>
									<th>tcversion</th>
									<th>ctype</th>
									<th>content</th>
									<th>downloadurl</th>
									<th>state</th>
									<th>ishighversion</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${updateconflist}" var="updateconf"
									varStatus="ids">
									<tr class="">
									    <td>${updateconf.id}</td>
										<td>${updateconf.cversion}</td>
										<td>${updateconf.tcversion}</td>
										<c:choose>
											 <c:when test="${updateconf.ctype =='1'}">
												<td class="center">ios</td>
											</c:when>
										    <c:when  test="${updateconf.ctype =='2'}">
												<td class="center">android</td>
											</c:when>
											 <c:when  test="${updateconf.ctype =='3'}">
												<td class="center">businessIos</td>
											</c:when>
											 <c:when  test="${updateconf.ctype =='4'}">
												<td class="center">businessAndroid</td>
											</c:when>
                                      </c:choose>
										<td>${updateconf.content}</td>
										<td>${updateconf.downloadurl}</td>
										<c:choose>
											 <c:when test="${updateconf.state =='0'}">
												<td class="center">不更新</td>
											</c:when>
										    <c:when  test="${updateconf.state =='1'}">
												<td class="center">更新</td>
											</c:when>
											<c:when  test="${updateconf.state =='2'}">
												<td class="center">强制更新</td>
											</c:when>
									  </c:choose>
									  <c:choose>
									      <c:when test="${updateconf.ishighversion=='0'}">
									          <td>否</td>
									      </c:when>
									      <c:when test="${updateconf.ishighversion=='1'}">
									          <td>是</td>
									      </c:when>
									  </c:choose>
									  
										<td class="center"><c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
													data-toggle="modal" data-target="#updateUpdateModel"
														onclick="javascript:editUpdateConf('${updateconf.id}','${sessionScope.user.user_guid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#delUpdateModel"
														onclick="javascript:delUpdateConf('${updateconf.id}','${sessionScope.user.user_guid}')">删除</button>
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
						<!-- DataTables JavaScript -->
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
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>
	<!-- Modal -->
	<div class="modal fade" id="insertUpdateModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加更新</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="当前版本" id="ins_cversion"
							value="" >
						<input type="text" value="" style="display: none" id="ins_userGuid"/>
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="指定特定的升级版本   不填会默认升成最高版本" id="ins_tcversion"
							value="">
					</div>
					<div class="form-group">
					<select class="form-control" id="ins_ctype">
						<option value='1'>ios</option>
						<option value='2'>android</option>
						<option value='3'>businessIos</option>
						<option value='4'>businessAndroid</option>
					</select>
						
					</div>
					
					<div class="form-group">
					      <textarea class="form-control" placeholder="升级内容" id="ins_content"
							rows="8"></textarea>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="升级地址" id="ins_downloadurl"
							value="">
					</div>
					<div class="form-group">
						<select class="form-control" id="ins_state">
						<option value='0'>不更新</option>
						<option value='1'>更新</option>
						<option value='2'>强制更新</option>
					</select>
					</div>
					<div class="form-group">
					<label>是否最高版本</label>
						<select class="form-control" id="ins_ishighversion">
						<option value='0'>否</option>
						<option value='1'>是</option>
					</select>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:insClientUpdate()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="updateUpdateModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑更新</h4>
				</div>
				<div class="modal-body">
				    <div class="form-group">
						<input class="form-control" type="text" style="display: none"  id="update_id" >
						<!-- <input class="form-control" type="text" style="display: none"  id="update_oldcversion" >
						<input class="form-control" type="text" style="display: none"  id="update_oldtcversion" >
						<input class="form-control" type="text" style="display: none"  id="update_oldctype" > -->
						<input class="form-control" type="text" style="display: none"  id="update_userGuid" >
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="当前版本" id="update_cversion"
							value="" >
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="指定特定的升级版本   不填会默认升成最高版本" id="update_tcversion"
							value="">
					</div>
					<div class="form-group">
					<select class="form-control" id="update_ctype">
						<option value='1'>ios</option>
						<option value='2'>android</option>
						<option value='3'>businessIos</option>
						<option value='4'>businessAndroid</option>
					</select>
						
					</div>
					
					<div class="form-group">
						<textarea class="form-control" placeholder="升级内容" id="update_content"
							rows="8"></textarea>
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="升级地址" id="update_downloadurl"
							value="">
					</div>
					<div class="form-group">
					<select class="form-control" id="update_state">
						<option value='0'>不更新</option>
						<option value='1'>更新</option>
						<option value='2'>强制更新</option>
					</select>
						
					</div>
					<div class="form-group">
					<label>是否最高版本</label>
						<select class="form-control" id="update_ishighversion">
						<option value='0'>否</option>
						<option value='1'>是</option>
					</select>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:updateUpdateConf()" id="savebtn1">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<div class="modal fade" id="delUpdateModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除更新</h4>
				</div>
				<div class="modal-body">
				    <input type="text" value="" style="display: none" id="del_id"/>
				<!-- 	<input type="text" value="" style="display: none" id="del_cversion"/>
					<input type="text" value="" style="display: none" id="del_tcversion"/>
					<input type="text" value="" style="display: none" id="del_ctype"/> -->
					<input type="text" value="" style="display: none" id="del_userGuid"/>
					<h3>删除后无法恢复</h3>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delConfirm()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/consts.js"></script>
	<script src="../js/clientupdate/clientupdate.js"></script>
</body>
</html>