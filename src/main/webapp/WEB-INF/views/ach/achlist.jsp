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
			<h1 class="page-header">成就管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row col-lg-2" style="margin-bottom: 5px;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#addAchModel" onclick="javascript:clearModel()">增加成就</button>
        &nbsp&nbsp<button type="button" class="btn btn-info" onclick="javascript:updateVersion('achievementversion')">更新成就版本</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加成就</button>
			</c:otherwise>
		</c:choose>
		<span>&nbsp;</span>

	</div>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">成就列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
								    <th>ach_code</th>
									<th>achid</th>
									<th>ach_name</th>
									<th>ach_des</th>
									<th>ach_img</th>
									<th>ach_disableimg</th>
									<th>groupname</th>
									<th>mallurl</th>
									<th>score</th>
									<th>achtype</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${achievements}" var="achievement"
									varStatus="ids">
									<tr class="${achievement.achid}">
									   <td>${achievement.ach_code}</td>
										<td>${achievement.achid}</td>
										<td>${achievement.ach_name}</td>
										<td>${achievement.ach_des}</td>
										<c:choose>
										 <c:when test="${achievement.ach_img ==null or achievement.ach_img ==''}">
										   <td> </td>
										 </c:when>
										 <c:when test="${achievement.ach_img !=null and achievement.ach_img !=''}">
										   <td><img width="100px" height="100px" src="${achievement.ach_img}"/></td>
										 </c:when>
										
										</c:choose>
	                                    <c:choose>
	                                     <c:when test="${achievement.ach_disableimg ==null or achievement.ach_disableimg ==''}">
										 <td> </td>
										 </c:when>
										 <c:when test="${achievement.ach_disableimg !=null and achievement.ach_disableimg !=''}">
										   <td><img width="100px" height="100px" src="${achievement.ach_disableimg}"/></td>
										 </c:when>
	                                    </c:choose>
	                                    
										 <td>${achievement.groupname}</td>
										 <c:choose>
	                                     <c:when test="${achievement.mallurl ==null or achievement.mallurl ==''}">
										 <td> </td>
										 </c:when>
										 <c:when test="${achievement.mallurl!=null and achievement.mallurl !=''}">
										   <td><a style="text-decoration:none;"	 href="javascript:gotoMallurlNewPage('${achievement.mallurl}')">${achievement.mallurl}</a></td>
										 </c:when>
	                                    </c:choose>
	                                    <td>${achievement.score}</td>
	                                   <c:choose>
	                                     <c:when test="${achievement.achtype==1}">
	                                          <td name="1">骑行数据类</td>
	                                     </c:when>
	                                     <c:when test="${achievement.achtype==2}">
	                                          <td name="2">动态类</td>
	                                     </c:when>
	                                     <c:when test="${achievement.achtype==3}">
	                                          <td name="3">骑行赛</td>
	                                     </c:when>
	                                     <c:when test="${achievement.achtype==4}">
	                                          <td name="4">等级</td>
	                                     </c:when>
	                                     <c:when test="${achievement.achtype==5}">
	                                          <td name="5">注册</td>
	                                     </c:when>
	                                     <c:when test="${achievement.achtype==6}">
	                                          <td name="6">骑迹景点</td>
	                                     </c:when>
	                                   </c:choose>

										<td class="center"><c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#addAchModel"
														onclick="javascript:updateTopic('${achievement.achid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
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
	<!-- Modal -->
	<div class="modal fade" id="addAchModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">成就信息</h4>
				</div>
				<div class="modal-body">
				<div  class="form-group">
				   <input class="form-control" placeholder="achid" id="upachid" value="">
				   <input type="hidden" id="updateORins" value=""> 
				</div>
					<div class="form-group">
						<input class="form-control"
							placeholder="成就名称" id="achname" value="">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="成就描述" id="achdes"
							value="">
					</div>
					<!-- <div class="form-group">
						<input class="form-control" placeholder="成就显示图" id="topic_content"
							value="">
					</div>
					<div class="form-group">
						<input class="form-control" placeholder="成就隐藏图" id="topic_status"
							value="">
					</div> -->
					<div class="form-group">
						<input class="form-control" placeholder="成就权重" id="achcode"
							value="">
					</div>
					<input type="text" value="" id="titleimg" style="display: none">
					<input type="text" value="" id="selim" style="display: none">
					<div class="form-group">
						<input id="" type="button" value="设置显示图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"
							onclick="javascript:selachimg(1)"> <input type="text"
							value="" id="titleimg1"> <img id="titleimgshow1" src=""
							style="width: 144px; height: 144px" />
					</div>
					<div class="form-group">
						<input id="" type="button" value="设置隐藏图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"
							onclick="javascript:selachimg(2)"> <input type="text"
							value="" id="titleimg2"> <img id="titleimgshow2" src=""
							style="width:144px; height: 144px" />
					</div>
                 <div class="form-group">
                    <select class="form-control"  id="achtype">
					   <option value='1' >骑行数据类</option>
					   <option value='2'>动态类</option>
					   <option value='3' >骑行赛</option>
					    <option value='4'>等级</option>
					   <option value='5' >注册</option>
					   <option value='6' >骑迹景点</option>
					</select>
                 </div>
                 <div class="form-group">
                    <select class="form-control"  id="groupname">
					   <option value='星座挑战' >星座挑战</option>
					    <option value='MotoGP'>MotoGP</option>
					   <option value='车神挑战' >车神挑战</option>
					   <option value='限时挑战'>限时挑战</option>
					   <option value='月度挑战'>月度挑战</option>
					   <option value='24节气'>24节气</option>
					   <option value='节日'>节日</option>
					   <option value='纪念日'>纪念日</option>
					   <option value='十二生肖'>十二生肖</option>
					</select>
                 </div>
                 <div class="form-group">
                 <label>mallurl:</label>
                 <input id="mallurl" class="form-control" placeholder="请输入mallurl"/>
                 </div>
                 <div class="form-group">
                 <label>score:</label>
                 <input id="score" class="form-control" placeholder="请输入score"/>
                 </div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" >关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addAchievement()" id="savebtn">保存</button>
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
	</div>
	<!-- /.modal -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/consts.js"></script>
	<script src="../js/ach/ach.js"></script>
 <!-- <script>
   $(function () { $('#addAchModel').on('show.bs.modal', function () {
	    $ ("#upachid").val ();
		$ ("#achname").val ();
		$ ("#achdes").val ();
		$ ("#titleimg1").val ();
		$ ("#titleimgshow1").attr ('src', "");
		$ ("#titleimg2").val ();
		$ ("#titleimgshow2").attr ('src', "");
		$ ("#achcode").val ();
   })
   });
</script> -->
</body>
</html>