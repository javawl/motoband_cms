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
			<h1 class="page-header">版本管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<%-- 	<div class="row" style="margin-bottom: 2%; padding-left: 2%;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertUserModel">增加</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加</button>
			</c:otherwise>
		</c:choose>
	</div> --%>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">版本列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>motobrandversion</th>
									<th>motomodelversion</th>
									<th>mototypeversion</th>
									<th>configversion</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td rowspan="2"><div>${dataversion.motobrandversion}</div>
										<div>
											<button type="button" class="btn btn-success"
												data-toggle="modal" data-target="#versionModel"
												onclick="javascript:versionmodel('motobrand')">brand版本更新</button>
										</div></td>

									<td rowspan="2"><div>${dataversion.motomodelversion}</div>
										<div>
											<button type="button" class="btn btn-success"
												data-toggle="modal" data-target="#versionModel"
												onclick="javascript:versionmodel('motomodel')">model版本更新</button>
										</div></td>
									<td rowspan="2"><div>${dataversion.mototypeversion}</div>
										<div>
											<button type="button" class="btn btn-success"
												data-toggle="modal" data-target="#versionModel"
												onclick="javascript:versionmodel('mototype')">type版本更新</button>
										</div></td>
									<td rowspan="2"><div>${dataversion.configversion}</div>
										<div>
											<button type="button" class="btn btn-success"
												data-toggle="modal" data-target="#versionModel"
												onclick="javascript:versionmodel('config')">config版本更新</button>
										</div></td>

								</tr>
							<thead>
								<tr>
									<th>achievementversion</th>
									<th>userlevelversion</th>
									<th>clientupdateversion</th>
									<th>bannerversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.achievementversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('achievementversion')">achievement版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.userlevelversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('userlevelversion')">userlevel版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.clientupdateversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('clientupdateversion')">clientupdate版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.bannerversion }</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('bannerversion')">banner版本更新</button>
									</div></td>
							</tr>

							<thead>
								<tr>
									<th>hotcityversion</th>
									<th>weatherversion</th>
									<th>topicversion</th>
									<th>runtypeversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.hotcityversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('hotcityversion')">hotcity版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.weatherversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('weatherversion')">weather版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.topicversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('topicversion')">topic版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.runtypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('runtypeversion')">runtype版本更新</button>
									</div></td>
							</tr>

							<thead>
								<tr>
									<th>mallparenttypeversion</th>
									<th>newslabelversion</th>
									<th>motobandgpversion</th>
									<th>serviceconfigversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.mallparenttypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallparenttypeversion')">mallparenttype版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.newslabelversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('newslabelversion')">newslabel版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.motobandgpversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('motobandgpversion')">motobandgp版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.serviceconfigversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('serviceconfigversion')">serviceconfig版本更新</button>
									</div></td>
							</tr>

							<thead>
								<tr>
									<th>boxtypeversion</th>
									<th>insurancemotodataversion</th>
									<th>insurancepackageversion</th>
									<th>insuranceproductversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.boxtypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('boxtypeversion')">boxtype版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.insurancemotodataversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('insurancemotodataversion')">insurancemotodata版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.insurancepackageversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('insurancepackageversion')">insurancepackage版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.insuranceproductversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('insuranceproductversion')">insuranceproduct版本更新</button>
									</div></td>
							</tr>

							<thead>
								<tr>
									<th>adversion</th>
									<th>motobrandparentversion</th>
									<th>insuranceupdateversion</th>
									<th>activityversion</th>

								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.adversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('adversion')">ad版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.motobrandparentversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('motobrandparentversion')">motobrandparent版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.insuranceupdateversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('insuranceupdateversion')">insuranceupdate版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.activityversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('activityversion')">activity版本更新</button>
									</div></td>

							</tr>

							<thead>
								<tr>
									<th>schoolpackageversion</th>
									<th>schoolvideoversion</th>
									<th>schoolboxversion</th>
									<th>newscategoryversion</th>

								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.schoolpackageversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('schoolpackageversion')">schoolpackage版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.schoolvideoversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('schoolvideoversion')">schoolvideo版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.schoolboxversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('schoolboxversion')">schoolbox版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.newscategoryversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('newscategoryversion')">newscategory版本更新</button>
									</div></td>

							</tr>
							<thead>
								<tr>
									<th>mallversion</th>
									<th>mallnewcarversion</th>
									<th>mallsecondcarversion</th>
									<th>mallbrandversion</th>

								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.mallversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallversion')">mall版本更新(包含商城活动提醒)</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.mallnewcarversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallnewcarversion')">mallnewcar版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.mallsecondversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallsecondcarversion')">mallsecondcar版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.mallbrandversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallbrandversion')">mallbrand版本更新</button>
									</div></td>

							</tr>

							<thead>
								<tr>
									<th>malltypeversion</th>
									<th>mallstyleversion</th>
									<th>malllabelversion</th>
									<th>mallnotifyversion</th>

								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.malltypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('malltypeversion')">malltype版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.mallstyleversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallstyleversion')">mallstyle版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.malllabelversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('malllabelversion')">malllabel版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.mallnotifyversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('mallnotifyversion')">mallnotify版本更新</button>
									</div></td>

							</tr>
							<thead>
								<tr>
									<th>giftversion</th>
									<th>discussversion</th>
									<th>businessserviceversion</th>
									<th>businesstypeversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.giftversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('giftversion')">gift版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.discussversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('discussversion')">discuss版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.businessserviceversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('businessserviceversion')">businessservice版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.businesstypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('businesstypeversion')">businesstype版本更新</button>
									</div></td>
							</tr>
							<thead>
								<tr>
									<th>usecarmainversion</th>
									<th>giftexchangeversion</th>
									<th>adpoolversion</th>
									<th>tribalversion</th>
								</tr>
							</thead>
							<tr>

								<td rowspan="2"><div>${dataversion.usecarmainversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('usecarmainversion')">usecarmain版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.giftexchangeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('giftexchangeversion')">giftexchange版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.adpoolversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('adpoolversion')">adpool版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.tribalversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											onclick="javascript:updateTribalVersion('tribalversion')">tribal版本更新</button>
									</div></td>
							</tr>
							<thead>
								<tr>
									<th>equippingversion</th>
									<th>tribaltypeversion</th>
									<th>vipcardinfoversion</th>
								</tr>
							</thead>
							<tr>
								<td rowspan="2"><div>${dataversion.equippingversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('equippingversion')">equipping版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.tribaltypeversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('tribaltypeversion')">tribaltype版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.vipcardinfoversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('vipcardinfoversion')">vipcardinfo版本更新</button>
									</div>
								<td rowspan="2"><div>${dataversion.qijiversion}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('qijiversion')">qijiversion版本更新</button>
									</div></td>
								<td rowspan="2"><div>${dataversion.businessservicev3_8_0version}</div>
									<div>
										<button type="button" class="btn btn-success"
											data-toggle="modal" data-target="#versionModel"
											onclick="javascript:versionmodel('businessservicev3_8_0version')">businessservicev3_8_0version版本更新</button>
									</div></td>
							</tr>
<!-- 							<tr> -->
<%-- 								<td rowspan="2"><div>${dataversion.mallactivityversion}</div> --%>
<!-- 									<div> -->
<!-- 										<button type="button" class="btn btn-success" -->
<!-- 											data-toggle="modal" data-target="#versionModel" -->
<!-- 											onclick="javascript:versionmodel('mallactivityversion')">mallactivityversion版本更新</button> -->
<!-- 									</div></td> -->
<!-- 							</tr> -->
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
	<!-- <script>
		$ (document).ready (function ()
        {
	        $ ('#dataTables-example').DataTable (
	        {
		        responsive : true
	        });
        });
	</script>
 -->

	<!-- Modal -->
	<div class="modal fade" id="versionModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">更新版本</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none"
						id="updateversion">
					<h3>确认更新版本提醒！</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:updateversion()">确认更新</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertUserModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新用户</h4>
				</div>
				<div class="modal-body"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:saveNewUser()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<script src="../js/dataversion/dataversion.js"></script>
</body>


</html>