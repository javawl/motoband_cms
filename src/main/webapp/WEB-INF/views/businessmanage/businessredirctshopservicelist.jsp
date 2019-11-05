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

<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<link
	href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">

<link
	href="../bower_components/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<script src="../js/consts.js"></script>

<script src="../js/boxmanage/jquery.form.min.js"></script>

<script
	src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>

<script
	src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<script src="../dist/js/sb-admin-2.js"></script>

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

.testbutton {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
	border: 1px solid transparent;
	border-radius: 6px;
	text-rendering: auto;
	font-size: 14px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer
}
</style>

</head>
<script type="text/javascript">
	function pageGoto(url, adminGuid, page) {
		var order = $("#triangleHidden").val();
		var limit = $("#pageSizeSelect").val();
		if (order == '' || order == null) {
			order = 0;
		}
		var orderConditions = $("#triangleHiddenValue").val();
		var buid = $("#shopSelect").val();
		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions + "&buid=" + buid);
	}
	function inputPageGoto(url, adminGuid, totalPage) {
		var inputPage = $("#inputPage").val();
		var buid = $("#shopSelect").val();
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
							+ "&orderConditions=" + orderConditions + "&buid="
							+ buid);

		} else {
			alert("页数不合法，请输入合法的页数");
			return false;
		}
	}
	function changeTriangle(url, adminGuid, page, order, orderConditions) {
		var buid = $("#shopSelect").val();

		var limit = $("#pageSizeSelect").val();
		var state = $("#stateSelect").val();
		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions + "&buid=" + buid);

	}
	function changestate(url, adminGuid, page, obj) {
		var buid = $("#shopSelect").val();
		var order = $("#triangleHidden").val();
		var limit = $("#pageSizeSelect").val();
		if (order == '' || order == null) {
			order = 0;
		}
		var orderConditions = $("#triangleHiddenValue").val();

		$("#page-wrapper").load(
				url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
						+ limit + "&order=" + order + "&orderConditions="
						+ orderConditions + "&buid=" + buid);
	}
</script>
<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">直营店服务管理</h1>
		</div>
	</div>


	<div class="row">
		<div class="row col-lg-2" style="margin-bottom: 5px;">
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#addbannerModel"
				onclick="javascript:rest()">添加服务</button>
			<label>Choose pageSize: </label> <select class="form-control"
				id="pageSizeSelect"
				onchange="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','1')">
				<c:forEach items="${limitList}" var="optionlimit" varStatus="ids">
					<c:choose>
						<c:when test="${optionlimit==limit }">
							<option value="${optionlimit}" selected>${optionlimit}</option>
						</c:when>
						<c:otherwise>
							<option value="${optionlimit}">${optionlimit}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <label>Choose shop: </label> <select class="form-control"
				id="shopSelect"
				onchange="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','1')">
				<option value="0">全部</option>
				<c:forEach items="${businessusers}" var="optionlimit"
					varStatus="ids">
					<c:choose>
						<c:when test="${optionlimit.buid==buid }">
							<option value="${optionlimit.buid}" selected>${optionlimit.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${optionlimit.buid} ">${optionlimit.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select> <input type="hidden" value="0" id="hiddenValue"> <input
				type="hidden" value="${sessionScope.user.user_guid}"
				id="userGuidhiddenValue">
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">活动中心消息</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>序号</th>
									<th>图标</th>
									<th>服务标签</th>
									<th>服务名称</th>
									<th>链接</th>
									<th>链接类型</th>
									<th>排序</th>
									<th>是否上架</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${businessusersservice}" var="user"
									varStatus="ids">
									<tr class="${user.bsid}">
										<td>${user.bsid}</td>
										<td><img alt="icon" src="${user.icon}"></td>
										<td>${user.lables}</td>
										<td>${user.name}</td>
										<td>${user.value}</td>
										<c:choose>
											<c:when test="${user.linktype =='1'}">
												<td class="center">动态</td>
											</c:when>
											<c:when test="${user.linktype =='2'}">
												<td class="center">话题</td>
											</c:when>
											<c:when test="${user.linktype =='3'}">
												<td class="center">问答</td>
											</c:when>
											<c:when test="${user.linktype =='4'}">
												<td class="center">有赞</td>
											</c:when>
											<c:when test="${user.linktype =='5'}">
												<td class="center">内部链接</td>
											</c:when>
											<c:when test="${user.linktype =='6'}">
												<td class="center">外部链接</td>
											</c:when>
											<c:when test="${user.linktype =='7'}">
												<td class="center">小程序</td>
											</c:when>
											<c:when test="${user.linktype =='8'}">
												<td class="center">motogp</td>
											</c:when>
											<c:when test="${user.linktype =='9'}">
												<td class="center">二手车</td>
											</c:when>
											<c:when test="${user.linktype =='10'}">
												<td class="center">本地商家</td>
											</c:when>
											<c:when test="${user.linktype =='11'}">
												<td class="center">话题列表</td>
											</c:when>
											<c:when test="${user.linktype =='12'}">
												<td class="center">讨论列表</td>
											</c:when>
											<c:when test="${user.linktype =='13'}">
												<td class="center">此刻</td>
											</c:when>
											<c:when test="${user.linktype =='14'}">
												<td class="center">聊天群</td>
											</c:when>
											<c:when test="${user.linktype =='15'}">
												<td class="center">消息列表</td>
											</c:when>
											<c:otherwise>
												<td class="center">未知</td>
											</c:otherwise>
										</c:choose>
										<td>${user.orderindex }</td>
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
												data-toggle="modal" data-target="#addbannerModel"
												onclick="javascript:editaddbannerModel('${user.bsid}')">编辑用户信息</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="pagination">

							<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
							<c:if test="${pageBean.page != 1 }">
								<a class="btn btn-primary"
									href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','1')">首页</a>
								<a class="btn btn-primary"
									href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
							</c:if>

							<c:choose>
								<c:when test="${pageBean.totalPage >10}">

									<c:forEach var="i" begin="1" end="5">
										<c:choose>
											<c:when test="${pageBean.page != i }">
												<a class="btn btn-primary"
													href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
													href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
											</c:when>
											<c:otherwise>
												<span class="btn btn-danger disabled">${i}</span>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${pageBean.page  != pageBean.totalPage}">
										<a class="btn btn-primary"
											href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
										<a class="btn btn-primary"
											href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
										<c:choose>
											<c:when test="${pageBean.page != i }">
												<a class="btn btn-primary"
													href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
											</c:when>
											<c:otherwise>
												<span class="btn btn-danger disabled">${i}</span>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${pageBean.page  != pageBean.totalPage}">
										<a class="btn btn-primary"
											href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
										<a class="btn btn-primary"
											href="javascript:pageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
									</c:if>
								</c:otherwise>
							</c:choose>
							<span>&nbsp输入页数：</span> <span><input type="text"
								id="inputPage" size="5" />&nbsp
								<button type="button" class="btn btn-success btn-lg"
									onclick="javascript:inputPageGoto('/businessmanage/businessredirctshopservicelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">跳转</button></span>


						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="addbannerModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">服务详情</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="" id="bsid" value=""
							type="hidden"> <input class="form-control" placeholder=""
							id="uuid" value="" type="hidden">
					</div>
					<div class="form-group">
						<label>选择门店 </label> <select class="form-control"
							id="shopEditInfoSelect">
							<c:forEach items="${businessusers}" var="optionlimit"
								varStatus="ids">
								<c:choose>
									<c:when test="${optionlimit.buid==buid }">
										<option value="${optionlimit.buid}" selected>${optionlimit.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${optionlimit.buid} ">${optionlimit.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>服务名称</label> <input class="form-control"
							placeholder="输入服务名称" id="name" value="">
					</div>
					<div class="form-group">
						<label>标签</label> <input class="form-control"
							placeholder="输入服务标签，多个以逗号相隔" id="lables" value="">
					</div>
					<div class="form-group">
						<label>跳转地址</label> <input class="form-control"
							placeholder="请输入跳转地址" id="value" value="">
					</div>
					<label>跳转类型</label> <select class="form-control" id="linktype">
						<option value='1'>动态</option>
						<option value='2'>话题</option>
						<option value='3'>问答</option>
						<option value='4'>有赞</option>
						<option value='5'>内部链接</option>
						<option value='6'>外部链接</option>
						<option value='7'>小程序</option>
						<option value='8'>motogp</option>
						<option value='9'>二手车</option>
						<option value='10'>本地商家</option>
						<option value='11'>话题列表</option>
						<option value='12'>讨论列表</option>
						<option value='13'>此刻</option>
						<option value='14'>聊天群</option>
						<option value='15'>消息列表</option>
					</select>
					<div class="form-group">
						<label>排序(越大越靠前)</label> <input class="form-control"
							placeholder="请输入排序" id="orderindex" value="">
					</div>
					<div class="col-sm-6">
						<label><font color="red">请选择服务icon</font></label>
						<div>
							<input id="" type="button" value="设置icon图片" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel"> <input
								type="text" value="" id="titleimgadd"> <img
								id="titleimgshowadd" src="" style="width: 280px; height: 144px" />
						</div>
					</div>
					<div class="col-sm-6">

						<div id="exceldiv" style="display: none;">
							<form action="../motouser/batchimport" method="post"
								enctype="multipart/form-data" target='ifr'
								onsubmit="return check();">
								<div style="margin: 30px;">
									<input id="excel_file" type="file" name="filename"
										accept="xlsx" size="80" onclick="javascript:clearfilemsg()" />
									<br></br> <input id="excel_button" type="submit"
										value="上传Excel" />

								</div>
								<font id="importMsg" color="red" style="margin: 30px;"></font><input
									type="hidden" />

							</form>
							<iframe name='ifr' id="ifr" style='display: none;'></iframe>
						</div>

					</div>
					<div>
						<div>
							<b>状态</b>
						</div>
						<div class="col-sm-3">
							<input type="radio" name="state" value="1" />审核通过（下架）
						</div>
						<div class="col-sm-3">
							<input type="radio" name="state" value="0" />上架
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary"
							onclick="javascript:addbannerConfirm()">提交任务</button>
					</div>
				</div>

			</div>
		</div>
	</div>






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
					<div id="back"
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
					<form id="uploadForm" style="float: left">
						<input type="file" name="FileContent" class="btn btn-info"
							id="inputfile" style="float: left"></input> <input id="subbtn"
							type="submit" style="float: left" class="btn btn-info"
							style="margin-left: 0;" onclick="javascript:imgsubmit()">
					</form>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/laydate/laydate.js"></script>
	<script type="text/javascript">
		var iframe = document.getElementById("ifr");
		iframe.onload = function() {
			var bodycontent = iframe.contentDocument.body.textContent;
			var json = eval("(" + bodycontent + ")");
			$("#importMsg").text(json.msg);
			$("#ins_taskid").val(json.taskid);

		}
		//时间选择器
		laydate.render({
			elem : '#ins_start_time',
			type : 'datetime'
		});
	</script>


	<script src="../js/motouser/sendSystemMessage.js"></script>
	<script src="../js/businessmanage/businessredirctshopservicelist.js"></script>
	<script src="../js/mallmanage/uploadGroupImage2.js"></script>
	<script type="text/javascript">
		$('#titleImgModel').on('hidden.bs.modal', function() {
			$('#addbannerModel').css({
				'overflow-y' : 'scroll'
			});
		});
	</script>
</body>


</html>
