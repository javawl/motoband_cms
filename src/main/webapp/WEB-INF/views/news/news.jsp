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
<style type="text/css">
body {
	margin: 0 auto;
}

.k {
	display: none;
}

.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	float: left;
	clear: left;
	padding: 0 0 0 5px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 45%;
}

.ui-timepicker-div td {
	font-size: 90%;
}

.ui-tpicker-grid-label {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

.ui-timepicker-rtl {
	direction: rtl;
}

.ui-timepicker-rtl dl {
	text-align: right;
	padding: 0 5px 0 0;
}

.ui-timepicker-rtl dl dt {
	float: right;
	clear: right;
}

.ui-timepicker-rtl dl dd {
	margin: 0 45% 10px 10px;
}

#editor {
	overflow: scroll;
	max-height: 650px
}

.selected_mask {
	padding-bottom: 20px;
	padding-left: 15px;
	padding-right: 15px;
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	position: absolute;
}

.selected_mask_inner {
	width: 100%;
	height: 100%;
	-moz-opacity: .6;
	-khtml-opacity: .6;
	opacity: .6;
	background-color: #000;
	filter: alpha(opacity = 60);
}

.selected_mask_icon {
	position: absolute;
	top: 0;
	left: 0;
	background: transparent
		url(../css/boxmanage/icon_card_selected218877.png) no-repeat 0 0;
	width: 100%;
	height: 100%;
	vertical-align: middle;
	display: inline-block;
	background-position: 50% 50%;
}

.groupdiv {
	width: 90%;
	margin: 5%;
	margin-right: 12%;
	text-align: center;
	line-height: 120px;
	height: 120px;
	background-color: #8c8c8c;
	font-size: 14pt;
	text-align: center;
	color: #ffffff;
}

.groupaction {
	filter: alpha(opacity = 50);
	opacity: 0.5;
}

</style>

</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">动态管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row col-lg-2" style="margin-bottom: 5px;">
		
	<button type="button" class="btn btn-info"  onclick="javascript:getmomentnews()">查看此刻动态</button>
	
	</div>

	<!-- /.row -->
	<%-- <div class="row">
		<div id="aaaa" class="col-lg-12">
			<div  class="panel panel-default">
				<div class="panel-heading">标签列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>labelid</th>
									<th>labelkeyword</th>
									<th>type</th>
									<th>orderindex</th>
									<th>state</th>
									<th>imageurl</th>
									<th>cimageurl</th>
									<th>recommendindex</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${newslabels}" var="newslabel" varStatus="ids">
									<tr class="${newslabel.labelid}">
										<td>${newslabel.labelid}</td>
										<td>${newslabel.labelkeyword}</td>
										<td>${newslabel.type}</td>
										<td>${newslabel.orderindex}</td>
										<c:choose>
											 <c:when test="${newslabel.state =='1'}">
												<td class="center">上线</td>
											</c:when>
										    <c:when  test="${newslabel.state =='0'}">
												<td class="center">未上线</td>
											</c:when>
									   </c:choose>
										<td><img width="100px" height="100px" src="${newslabel.imageurl}"/></td>
										<td><img width="100px" height="100px" src="${newslabel.cimageurl}"/></td>
										<td>${newslabel.recommendindex}</td>
										<td class="center">
											<c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#addTopicModel"
														onclick="javascript:updateTopic('','','')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose>
											<button type="button" class="btn btn-primary btn-lg"
												onclick="javascript:getLabelNews('${newslabel.labelkeyword}')">查看动态</button>
											<!-- <button type="button" class="btn btn-success"
												onclick="javascript:addBanner('')">添加到banner</button> -->
                                              <button type="button" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#editnewslableModel"
												onclick="javascript:editnewslable('${newslabel.labelid}')">编辑</button>
										</td>
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
			
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<!-- Modal -->
	
	<div class="modal fade" id="editnewslableModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">标签信息</h4>
				</div>
				<div class="modal-body" style="height:470px">
				    <div class="col-sm-12">
				      <div class="form-group">
								<div class="col-sm-6">
									<label>labelid</label> <input class="form-control" placeholder=""
										id="update_labelid" value="" readonly="readonly">
										<input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
										<input id="hiddenValue" type="hidden" value="">
							</div>
					  </div>
					  <div class="form-group">
								<div class="col-sm-6">
									<label>labelkeyword</label> <input class="form-control" placeholder=""
										id="update_labelkeyword" value="" readonly="readonly">
								</div>
					  </div>
				    
				    </div>
				    <div class="col-sm-12">
				          <div class="form-group">
								<div class="col-sm-6" style="margin-top:14px;">
									<label>type</label> <input class="form-control" placeholder=""
										id="update_type" value="" readonly="readonly">
								</div>
					       </div>
					       <div class="form-group">
								<div class="col-sm-6">
									<label>orderindex</label> <input class="form-control" placeholder=""
										id="update_orderindex" value="" >
								</div>
					       </div>
				    </div>
				    
				     <div class="col-sm-12">
						<div class="form-group">
						
						    <div class="col-sm-6" style="margin-top:14px;">
						    <label><font color="red">请选择320x320的图片</font></label>
						    <div>
							<input id="" type="button" value="设置imageurl图片" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertHiddleValue('2')"> <input
								type="text" value="" id="titleimgimageurl"> <img
								id="titleimgshowimageurl" src="" style="width: 144px; height: 144px" />
							</div>
							</div>
						</div>
						<div class="form-group">
						
						    <div class="col-sm-6">
						    <label><font color="red">请选择320x320的图片</font></label>
						    <div>
							<input id="" type="button" value="设置cimageurl图片" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertHiddleValue('3')"> <input
								type="text" value="" id="titleimgcimageurl"> <img
								id="titleimgshowcimageurl" src="" style="width: 144px; height: 144px" />
							</div>
							</div>
						</div>
					</div>
                      <div class="col-sm-12">
				          <div class="form-group">
				             <div class="col-sm-6" style="margin-top:14px;">
				             <label>state</label>
								<select class="form-control" id="update_state">
									<option value='0'>未上线</option>
									<option value='1'>上线</option>
								</select>
							</div>
					       </div>
					       <div class="form-group">
								<div class="col-sm-6">
									<label>recommendindex</label> <input class="form-control" placeholder=""
										id="update_recommendindex" value="" >
								</div>
					       </div>
				    </div>
				    

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:editnewslableConfirm()" >保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<!-- Modal -->
	<div class="modal fade" id="titleImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closetitleImgModel();">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择图片</h4>
				</div>
				<div class="modal-body" style="padding: 5px;">
					<div class="form-group" id="imglistdiv"
						style="height: 600px; overflow: auto; overflow-x: hidden;">
						<div
							style="float: left; width: 24%; padding-right: 0px; height: 100%; border: 1px solid #E7E7EB; overflow: auto;"
							id="XX_grouplist">
							<input type="text" style="display: none"
								value="${imggroups[0].group_guid}" id="XX_nowgroupid">
							<c:forEach items="${imggroups}" var="imggroup" varStatus="ids">
								<div
									class="groupdiv <c:if test='${ids.index==0}'>groupaction</c:if>"
									id="XX_${imggroup.group_guid}">${imggroup.group_name}</div>
							</c:forEach>
						</div>
						<div
							style="float: left; width: 75%; padding-left: 0px; border: 1px solid #E7E7EB; height: 100%; overflow: auto;"
							id="XX_groupimglist">
							<c:forEach items="${motoimgs}" var="imgMessages" varStatus="ids">
								<div class="col-lg-3" id="XX_${imgMessages.img_guid }"
									onclick="javascript:XX_selectImg('XX_${imgMessages.img_guid }');">
									<div class="panel panel-default">
										<div class="panel-body" style="text-align: center;">
											<img src="${imgMessages.img_url }" width="100" height="100">
										</div>
										<div class="panel-footer"
											style="text-align: center; padding: 0px; font-size: 9pt; height: 30px;">
											<input class="form-control" placeholder="图片名" name="ImgName"
												value="${imgMessages.img_name }" disabled="disabled">
										</div>
										<div class="panel-footer"
											style="text-align: center; padding: 5px 0px;">
											<input type="button" value="选择" class="btn btn-info"
												onclick="">
										</div>
									</div>
									<div class="selected_mask" name="XX_selectdiv"
										style="display: none">
										<div class="selected_mask_inner"></div>
										<div class="selected_mask_icon"></div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- <form id="XX_uploadForm" style="float: left">
								<input type="file" name="XX_FileContent" class="btn btn-info"
									id="inputfile" style="float: left"></input> <input id="subbtn"
									type="submit" style="float: left" class="btn btn-info"
									style="margin-left: 0;">
							</form> -->
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel" onclick="javascript:titleImgModel();">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div> --%>
	
	<script src="../js/news/dataversion.js"></script>
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/consts.js"></script>
	<script src="../js/news/news.js"></script>
	<!-- load页面的js -->
	 <!-- jQuery1.7以上 或者 Zepto 二选一，不要同时都引用 -->
	<!-- <script src="../js/news/zepto.min.js"></script>
	<script src="../js/news/dropload.js"></script>  -->
	
	
	<!-- <script src="../js/news/topicnews.js"></script> -->
</body>
</html>