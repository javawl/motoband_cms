<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
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


<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />


<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="../js/jquery-ui-1.10.4.custom.min.js"></script>

<script src="../js/wysi/bootstrap-wysiwyg.js" type="text/javascript"></script>
<script src="../js/wysi/jquery.hotkeys.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="../css/boxmanage/addNewBoxPage.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
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

<script charset="utf-8" src="../js/boxmanage/kindeditor.js"></script>
<script type="text/javascript" src="../css/boxmanage/zh-CN.js"></script>

<script src="../js/consts.js"></script>
<script type="text/javascript">
	function pageGoto(url, adminGuid, page) {
		var order = $("#triangleHidden").val();
		var limit = $("#pageSizeSelect").val();
		if (order == '' || order == null) {
			order = 0;
		}
		var orderConditions = $("#triangleHiddenValue").val();

		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions);
	}
	function inputPageGoto(url, adminGuid, totalPage) {
		var inputPage = $("#inputPage").val();
		var reg = /^[1-9]\d*$/;
		if (reg.test(inputPage) == true
				&& (parseInt(inputPage) <= parseInt(totalPage))) {
			var order = $("#triangleHidden").val();
			var limit = $("#pageSizeSelect").val();
			var state = $("#stateSelect").val();
			if (order == '' || order == null) {
				order = 0;
			}

			var orderConditions = $("#triangleHiddenValue").val();
			$("#page-wrapper").load(
					url + "?userGuid=" + adminGuid + "&page=" + page
							+ "&limit=" + limit + "&order=" + order
							+ "&orderConditions=" + orderConditions);

		} else {
			alert("页数不合法，请输入合法的页数");
			return false;
		}
	}
	function changeTriangle(url, adminGuid, page, order, orderConditions) {

		var limit = $("#pageSizeSelect").val();
		var state = $("#stateSelect").val();
		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions);

	}
	function changestate(url, adminGuid, page, obj) {
		var order = $("#triangleHidden").val();
		var limit = $("#pageSizeSelect").val();
		if (order == '' || order == null) {
			order = 0;
		}
		var orderConditions = $("#triangleHiddenValue").val();

		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions);
	}
</script>
</head>

<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">直营店管理</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">直营店列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="col-sm-3"
							style="padding-left: 0; margin-bottom: 15px; float: left">
							<div align="left" style="float: left">
								<label>Choose pageSize: </label> <select class="form-control"
									id="pageSizeSelect"
									onchange="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','1')">
									<c:forEach items="${limitList}" var="optionlimit"
										varStatus="ids">
										<c:choose>
											<c:when test="${optionlimit==limit }">
												<option value="${optionlimit}" selected>${optionlimit}</option>
											</c:when>
											<c:otherwise>
												<option value="${optionlimit}">${optionlimit}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>

							<div align="right">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:lookUserNewPage(0)">增加</button>



							</div>
						</div>
						<%-- <div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  state: </label>
							  <select class="form-control" id="stateSelect"  onchange="javascript:changestate('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','1',this)">
										<c:choose>
											<c:when test="${state==-1 }">
											    <option value="-1" selected>全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
										  <c:when test="${state==0 }">
											    <option value="-1" >全部</option>
											    <option value="0" selected>申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
											<c:when test="${state==1 }">
											    <option value="-1" >全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" selected>申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
											<c:when test="${state==2 }">
											    <option value="-1" >全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" selected>已拒绝</option>
											</c:when>
										</c:choose>
									
							  </select>
						</div> 
					</div> --%>

						<div style="display: none;">
							<input id="triangleHidden" type="hidden" value="${order }" /> <input
								id="triangleHiddenValue" type="hidden"
								value="${orderConditions }" /> <input id="userGuidHidden"
								type="hidden" value="${sessionScope.user.user_guid}"> <input
								id="hiddenValue" type="hidden" value=""> <input
								id="stateHidden" type="hidden" value="${state }" />

						</div>

						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>id</th>
<!-- 									<th>buserid</th> -->
									<th>名称</th>
<!-- 									<th>省</th> -->
									<th>市</th>
									<th>地址</th>
									<th>联系电话</th>
									<th>营业时间</th>
									<th>经度</th>
									<th>纬度</th>
									<th>群号</th>
<!-- 									<th>创建时间</th> -->
<!-- 									<th>最后更新时间</th> -->
									<th>是否上架</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${businessusers}" var="user" varStatus="ids">
									<tr class="${user.buid}">
										<td>${user.buid}</td>
<%-- 										<td>${user.buserid}</td> --%>
										<td>${user.name}</td>
<%-- 										<td>${user.province}</td> --%>
										<td>${user.city}</td>
										<td>${user.address}</td>
										<td>${user.contactphone}</td>
										<td>${user.businesshours}</td>
										<td>${user.longitude}</td>
										<td>${user.latitude}</td>
										<td>${user.groupid}</td>
<%-- 										<td>${user.createtime}</td> --%>
<%-- 										<td>${user.updatetime}</td> --%>
										<c:choose>
											<c:when test="${user.state==0}">
												<td>是</td>
											</c:when>
											<c:when test="${user.state==1}">
												<td>否</td>
											</c:when>
											<c:otherwise>
												<td>未知</td>
											</c:otherwise>
										</c:choose>
										<td class="center">
											<button type="button" class="btn btn-primary btn-lg"
												onclick="javascript:lookUserNewPage('${user.buid}')">查看用户信息</button>
											<button type="button" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#uploadapprovepicModel"
												onclick="javascript:uploadapprovepic('${user.buid}')">上传认证图片</button>

										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<!-- /.table-responsive -->
					<div class="pagination">

						<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
						<c:if test="${pageBean.page != 1 }">
							<a class="btn btn-primary"
								href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','1')">首页</a>
							<a class="btn btn-primary"
								href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
						</c:if>

						<c:choose>
							<c:when test="${pageBean.totalPage >10}">

								<c:forEach var="i" begin="1" end="5">
									<c:choose>
										<c:when test="${pageBean.page != i }">
											<a class="btn btn-primary"
												href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
										</c:when>
										<c:otherwise>
											<span class="btn btn-danger disabled">${i}</span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<span>...</span>
								<c:forEach var="i" begin="${pageBean.totalPage-4}"
									end="${pageBean.totalPage}">
									<c:choose>
										<c:when test="${pageBean.page != i }">
											<a class="btn btn-primary"
												href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
										</c:when>
										<c:otherwise>
											<span class="btn btn-danger disabled">${i}</span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageBean.page  != pageBean.totalPage}">
									<a class="btn btn-primary"
										href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
									<a class="btn btn-primary"
										href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
									<c:choose>
										<c:when test="${pageBean.page != i }">
											<a class="btn btn-primary"
												href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
										</c:when>
										<c:otherwise>
											<span class="btn btn-danger disabled">${i}</span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageBean.page  != pageBean.totalPage}">
									<a class="btn btn-primary"
										href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
									<a class="btn btn-primary"
										href="javascript:pageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
								</c:if>
							</c:otherwise>
						</c:choose>
						<span>&nbsp输入页数：</span> <span><input type="text"
							id="inputPage" size="5" />&nbsp
							<button type="button" class="btn btn-success btn-lg"
								onclick="javascript:inputPageGoto('/businessmanage/businessredirctshoplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">跳转</button></span>


					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="modal fade" id="addBusineRedirctShop" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="userid"
							id="resetpassword_userid" value="" style="display: none">
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="password"
							id="resetpassword_password" value="">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:resetpasswordConfirm()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal fade" id="uploadapprovepicModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="userid"
							id="uploadapprovepic_userid" value="" style="display: none">
					</div>
					<div class="form-group">
						<div>
							<input id="" type="button" value="设置认证照片" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel"
								onclick="javascript:insertTitleImgHidden('15')">
						</div>
						<div>
							<input type="text" value="" id="approvepic"> <img
								id="approvepicshow" src="" style="width: 144px; height: 144px" />
						</div>

					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:uploadapprovepicConfirm()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>


	<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/boxmanage/jquery.form.min.js"></script>

	<div class="modal fade" id="titleImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closetitleImgModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择图片</h4>
				</div>
				<div class="modal-body" style="padding: 5px;">
					<div id="back1"
						style="display: none; POSITION: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.6); filter: alpha(opacity = 60)">
						<h3>
							<font color="white">上传中，请耐心等待</font>
						</h3>
					</div>
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
											style="text-align: center; padding: 0px; font-size: 9pt; height: 60px;">
											<textarea rows='2' style='word-break: break-all;'
												class="form-control" placeholder="图片名" name="ImgName"
												<%-- value="${imgMessages.img_name }" --%> disabled="disabled">${imgMessages.img_name }</textarea>
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
					<form id="uploadForm" style="float: left">
						<input type="file" name="FileContent" class="btn btn-info"
							id="inputfile" style="float: left"></input> <input id="subbtn"
							type="submit" style="float: left" class="btn btn-info"
							style="margin-left: 0;" onclick="javascript:imgsubmit()">
					</form>

					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel" onclick="javascript:closetitleImgModel()">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<script src="../js/news/dataversion.js"></script>
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/consts.js"></script>
	<script src="../js/businessmanage/businessredirctshoplist.js"></script>
	<script type="text/javascript"
		src="../js/mallmanage/uploadGroupImage.js"></script>


	<script src="../js/bootstrap-datetimepicker.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../js/bootstrap-datetimepicker.zh-CN.js"
		type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
		/* $ ("input[name='act_start_time'],input[name='act_stop_time']").datetimepicker (); */
		$(".form_datetime").datetimepicker({
			language : 'zh-CN',
			autoclose : 1,
			todayBtn : 1,
			minuteStep : 1,
			format : 'yyyy-mm-dd hh:ii',
			pickerPosition : "bottom"

		});
	</script>

</body>


</html>