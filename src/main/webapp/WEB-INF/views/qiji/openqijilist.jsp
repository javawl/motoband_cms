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


<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />


<!-- <script src="../bower_components/jquery/dist/jquery.min.js"></script> -->
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


<script src="../js/consts.js"></script>
<script type="text/javascript">
	function pageGoto(url, adminGuid, page) {
		var order = $("#triangleHidden").val();
		var limit = $("#pageSizeSelect").val();
		var orderConditions = $("#triangleHiddenValue").val();
		if (order == '' || order == null) {
			order = 0;
		}
		url = url + "?userGuid=" + adminGuid + "&page=" + page + "&limit="
				+ limit + "&order=" + order + "&orderConditions="
				+ orderConditions
		var province = $("#provinceSelect").val();
		if (typeof (province) == "undefined" || province == null
				|| province.length == 0) {
			province == null;
		} else {
			url += "&province=" + province;
		}
		var city = $("#citySelect").val();
		if (typeof (city) == "undefined" || city == null || city.length == 0) {
			city == null;
		} else {
			url += "&city=" + city;
		}
		$("#dataTables-example").text("正在加载中。。。。");
		$("#page-wrapper").load(url);
	}
	function inputPageGoto(url, adminGuid, totalPage) {
		var inputPage = $("#inputPage").val();
		var reg = /^[1-9]\d*$/;
		if (reg.test(inputPage) == true
				&& (parseInt(inputPage) <= parseInt(totalPage))) {
			pageGoto(url,adminGuid,inputPage);

		} else {
			alert("页数不合法，请输入合法的页数");
			return false;
		}
	}
</script>
</head>

<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">骑迹景点管理</h1>
		</div>
	</div>

	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					骑迹景点列表 &nbsp&nbsp
					<button type="button" class="btn btn-info" data-toggle="modal"
						data-target="#editopenqijiModel"
						onclick="javascript:addopenqiji()">添加骑迹景点</button>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
							<div>
								<label>Choose province: </label> <select class="form-control"
									id="provinceSelect"
									onchange="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','1')">
									<option value="">请选择省</option>
									<c:forEach items="${provincelist}" var="optionprovince"
										varStatus="ids">

										<c:choose>
											<c:when test="${optionprovince==province }">
												<option value="${optionprovince}" selected>${optionprovince}</option>
											</c:when>
											<c:otherwise>
												<option value="${optionprovince}">${optionprovince}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select><label>Choose city: </label> <select class="form-control"
									id="citySelect"
									onchange="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','1')">
									<option value="">请选择市</option>
									<c:forEach items="${citylist}" var="optioncity"
										varStatus="ids">
										<c:choose>
											<c:when test="${optioncity==city }">
												<option value="${optioncity}" selected>${optioncity}</option>
											</c:when>
											<c:otherwise>
												<option value="${optioncity}">${optioncity}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select> <label>Choose pageSize: </label> <select class="form-control"
									id="pageSizeSelect"
									onchange="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','1')">
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
						</div>


						<div style="display: none;">
							<input id="triangleHidden" type="hidden" value="${order }" /> <input
								id="triangleHiddenValue" type="hidden"
								value="${orderConditions }" /> <input id="userGuidHidden"
								type="hidden" value="${sessionScope.user.user_guid}"> <input
								id="hiddenValue" type="hidden" value="">

						</div>

						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>lid</th>
									<th>sid</th>
									<th>longitude</th>
									<th>latitude</th>
									<th>province</th>
									<th>city</th>
									<th>name</th>
									<th>operation</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${openqijilists}" var="openqijimodel"
									varStatus="ids">
									<tr class="${openqijimodel.lid}">
										<td>${openqijimodel.lid}</td>
										<td>${openqijimodel.sid}</td>
										<td>${openqijimodel.longitude}</td>
										<td>${openqijimodel.latitude}</td>
										<td>${openqijimodel.province}</td>
										<td>${openqijimodel.city}</td>
										<td>${openqijimodel._name}</td>
										<td class="center">
											<button type="button" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#editopenqijiModel"
												onclick="javascript:editopenqiji('${openqijimodel.sid}')">编辑</button>
											<button type="button" class="btn btn-danger btn-lg"
												onclick="javascript:delopenqiji('${openqijimodel.sid}','${ pageBean.page}')">删除</button>

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
								href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','1')">首页</a>
							<a class="btn btn-primary"
								href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
						</c:if>

						<c:choose>
							<c:when test="${pageBean.totalPage >10}">

								<c:forEach var="i" begin="1" end="5">
									<c:choose>
										<c:when test="${pageBean.page != i }">
											<a class="btn btn-primary"
												href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
												href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${i}')">${i}</a>
										</c:when>
										<c:otherwise>
											<span class="btn btn-danger disabled">${i}</span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageBean.page  != pageBean.totalPage}">
									<a class="btn btn-primary"
										href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
									<a class="btn btn-primary"
										href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
								</c:if>
							</c:when>
							<c:otherwise>
								<c:forEach var="i" begin="1" end="${pageBean.totalPage }">
									<c:choose>
										<c:when test="${pageBean.page != i }">
											<a class="btn btn-primary"
												href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${i}')">${i}</a>
										</c:when>
										<c:otherwise>
											<span class="btn btn-danger disabled">${i}</span>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:if test="${pageBean.page  != pageBean.totalPage}">
									<a class="btn btn-primary"
										href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
									<a class="btn btn-primary"
										href="javascript:pageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
								</c:if>
							</c:otherwise>
						</c:choose>
						<span>&nbsp输入页数：</span> <span><input type="text"
							id="inputPage" size="5" />&nbsp
							<button type="button" class="btn btn-success btn-lg"
								onclick="javascript:inputPageGoto('/qiji/openqijilist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">跳转</button></span>


					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>


	<div class="modal fade" id="editopenqijiModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">骑迹景点详情</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="" id="addsid" value=""
							type="hidden">
						<!-- <input class="form-control" placeholder="" id="addaddtime"
							value="" type="hidden">
				<input class="form-control" placeholder="" id="oldtype"
							value="" type="hidden"> -->
						<label>景点名称</label> <input class="form-control"
							placeholder="请输入景点名称" id="addname" value="">
					</div>
					<div class="form-group">
						<label>省</label> <input class="form-control"
							placeholder="请输入景点所在的省，如北京市，海南省,新疆维吾尔自治区" id="addprovince"
							value="">
					</div>
					<div class="form-group">
						<label>市</label> <input class="form-control"
							placeholder="请输入景点所在的市，如北京市，延边朝鲜族自治州,湘西土家族苗族自治州" id="addcity"
							value="">
					</div>
					<div class="form-group">
						<label>经纬度</label> <input class="form-control"
							placeholder="请输入经纬度,'经度+维度',如111.940666,25.59102"
							id="addlonandlat" value="">
					</div>

					<!-- 	
				 <div class="form-group">
				 <div class="col-sm-12"  style="margin-top: 15px;">
                        <label>类型:</label>
						<input type="radio" class="" placeholder="" name="addtype"
							value="0" >地域 
						<input type="radio" class="" placeholder="" name="addtype"
							value="1" >品牌
						<input type="radio" class="" placeholder="" name="addtype"
							value="2" >主题
					</div>
				 </div> -->
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:editopenqijiConfirm('${ pageBean.page}')">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->




	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/consts.js"></script>
	<script src="../js/qiji/openqijilist.js"></script>


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

		$('#editopenqijiModel').modal({
			backdrop : 'static',
			keyboard : false,
			show : false
		});
	</script>

</body>


</html>