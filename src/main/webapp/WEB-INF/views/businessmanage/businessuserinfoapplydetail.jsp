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

<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<script src="../js/consts.js"></script>
<script type="text/javascript">

</script>
</head>

<body >
   <div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">商家信息审核申请</h1>
		</div>
	</div>

	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">商家信息审核申请</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
                         <div style="display:none;">
						     <input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
							 <input id="hiddenValue" type="hidden" value="${buserid }">
                           </div>
                         <div>
                            <c:choose>
                               <c:when test="${baseinfoState==0 }">
                                  <div style="float: left;position:relative">
					                  <button type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('1')">基础信息审核</button>
									 <button class="btn btn-default btn-danger btn-xs" style="position:absolute;right:-1px;top:-1px;"><i class="fa fa-plus"></i></button>
								 </div>
                               </c:when>
                               <c:otherwise>
                                  <div>
                                     <button style="float: left;" type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('1')">基础信息审核</button>
                                  </div>
                               </c:otherwise>
                            </c:choose>
                            <c:choose>
                               <c:when test="${environmentalState==0 }">
                                  <div style="float: left;position:relative">
					                  <button type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('2')">环境设备审核</button>
									 <button class="btn btn-default btn-danger btn-xs" style="position:absolute;right:-1px;top:-1px;"><i class="fa fa-plus"></i></button>
								 </div>
                               </c:when>
                               <c:otherwise>
                                  <div>
                                     <button  style="float: left;" type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('2')">环境设备审核</button>
                                  </div>
                               </c:otherwise>
                            </c:choose>
                            <c:choose>
                               <c:when test="${servicelistState==0 }">
                                  <div style="float: left;position:relative">
					                  <button type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('3')">服务能力审核</button>
									 <button class="btn btn-default btn-danger btn-xs" style="position:absolute;right:-1px;top:-1px;"><i class="fa fa-plus"></i></button>
								 </div>
                               </c:when>
                               <c:otherwise>
                                  <div>
                                     <button t style="float: left;" ype="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('3')">服务能力审核</button>
                                  </div>
                               </c:otherwise>
                            </c:choose>
                            <c:choose>
                               <c:when test="${activitylistState==0 }">
                                  <div style="float: left;position:relative">
					                  <button type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('4')">店铺活动审核</button>
									 <button class="btn btn-default btn-danger btn-xs" style="position:absolute;right:-1px;top:-1px;"><i class="fa fa-plus"></i></button>
								 </div>
                               </c:when>
                               <c:otherwise>
                                  <div>
                                     <button  style="float: left;" type="button" class="btn btn-primary btn-lg"
									          onclick="javascript:divshow('4')">店铺活动审核</button>
                                  </div>
                               </c:otherwise>
                            </c:choose>
                         </div>
                         <div  style="clear: both;" ></div>
                         <br>
                         <br>
                         <div id="baseinfoDiv" style="display: none">
                                <div class="form-group">
								    <b>商家名称</b><input class="form-control" placeholder="name" id="baseinfo_name"
										value="${businessBaseinfoModel.name }" >
								</div>
			                    <div class="form-group">
								   <b>联系人电话</b><input class="form-control" placeholder="contactphone" id="baseinfo_contactphone"
										value="${businessBaseinfoModel.contactphone }" >
								</div>
			                    <div class="form-group">
								    <b>营业开始时间</b><input class="form-control" placeholder="officestarttime" id="baseinfo_officestarttime"
										value="${businessBaseinfoModel.officestarttime }" >
								</div>
			                    <div class="form-group">
								    <b>营业结束时间</b><input class="form-control" placeholder="officeendtime" id="baseinfo_officeendtime"
										value="${businessBaseinfoModel.officeendtime }" >
								</div>
			                    <div class="form-group">
								    <b>地址</b><input class="form-control" placeholder="address" id="baseinfo_address"
										value="${businessBaseinfoModel.address }" >
								</div>
			                    <div class="form-group">
								   <b>头像</b><img   id="baseinfo_headurl" src="${businessBaseinfoModel.headurl }" height="100px"
										value="" >
								</div>
			                    <div class="form-group">
								    <b>省</b><input class="form-control" placeholder="province" id="baseinfo_province"
										value="${businessBaseinfoModel.province }" >
								</div>
			                    <div class="form-group">
								    <b>市</b><input class="form-control" placeholder="city" id="baseinfo_city"
										value="${businessBaseinfoModel.city }" >
								</div>
			                    <div class="form-group">
								    <b>店铺简介</b><textarea class="form-control" placeholder="des" id="baseinfo_des" rows="3"
										value="" >${businessBaseinfoModel.des }</textarea>
								</div>
			                    <div class="form-group">
								    <b>详细介绍</b><textarea class="form-control" placeholder="desdetail" id="baseinfo_desdetail" rows="4"
										value="" >${businessBaseinfoModel.desdetail }</textarea>
								</div>
			                    <div class="form-group">
								   <b>纬度</b><input class="form-control" placeholder="latitude" id="baseinfo_latitude"
										value="${businessBaseinfoModel.latitude }" >
								</div>
			                    <div class="form-group">
								    <b>经度</b><input class="form-control" placeholder="longitude" id="baseinfo_longitude"
										value="${businessBaseinfoModel.longitude }" >
								</div>
			                    <div class="form-group">
								    <b>商家代码</b><input class="form-control" placeholder="businesscode" id="baseinfo_businesscode"
										value="${businessBaseinfoModel.businesscode }" >
								</div>
								<div>
								    <button type="button" class="btn btn-primary" 
						                   onclick="javascript:changeuserinfoapplystate('1','1')" >审核通过</button>
						             <button type="button" class="btn btn-primary" 
						                   onclick="javascript:changeuserinfoapplystate('1','2')" >审核不通过</button>
						            <br>
						            <br>
						            <div class="form-group" >
						               <textarea  placeholder="选择不通过时 ，请在此处输入不通过原因" id="baseinfo_reason" rows='3' style="width: 70%;"></textarea>
						            </div>
						            
								</div>
                         </div>
                         <div id="environmentalDiv"  style="display: none">
                            <div>
                                <div><b>门头照片:</b></div>
                               <c:forEach items="${doorpicslist}" var="doorpic" varStatus="ids">
                                <div  style="float:left">
                                   <img alt="" src="${doorpic}"  height="100px">
                                </div>
                              </c:forEach>
                              <div style="clear:both;"></div>
                            </div>
                            <div>
                              <div><b>店内实景照片:</b></div>
                               <c:forEach items="${shoppicslist}" var="shoppic" varStatus="ids">
	                                <div  style="float:left">
	                                   <img alt="" src="${shoppic}"  height="100px">
	                                </div>
                                </c:forEach>
                                <div style="clear:both;"></div>
                            </div>
                            <div>
							    <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('2','1')" >审核通过</button>
					             <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('2','2')" >审核不通过</button>
					            <br>
					            <br>
					            <div class="form-group" >
					               <textarea  placeholder="选择不通过时 ，请在此处输入不通过原因" id="environmental_reason" rows='3' style="width: 70%;"></textarea>
					            </div>
					            
							</div>
                         </div>
                         <div id="businessservicelistDiv"  style="display: none">
                          <c:forEach items="${businessServiceModellist}" var="serviceModel" varStatus="ids">
                               <span><b>${serviceModel.name }介绍:</b></span><div><textarea rows="3" cols="150">${serviceModel.content }</textarea></div>
	                             <hr>   
                          </c:forEach>
                           <div>
							    <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('3','1')" >审核通过</button>
					             <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('3','2')" >审核不通过</button>
					            <br>
					            <br>
					            <div class="form-group" >
					               <textarea  placeholder="选择不通过时 ，请在此处输入不通过原因" id="servicelist_reason" rows='3' style="width: 70%;"></textarea>
					            </div>
					            
							</div>
                         </div>
                         <div id="businessactivitylistDiv"  style="display: none">
                            <c:forEach items="${businessActivityModellist}" var="activityModel" varStatus="ids">
	                            <div>
	                                <div><input class="form-control" placeholder="baid" value="${activityModel.baid}" readonly="readonly" style="width: 55%;"></div>
		                            <span><b>${activityModel.title }:</b></span>
		                            <div>
		                             <div><textarea rows="5" cols="" style="width: 50%;float:left;">${activityModel.content }</textarea></div>
		                            </div>
		                             <div >
		                                <c:set value="${ fn:split(activityModel.pics, ',') }" var="str" />
											<c:forEach items="${ str }" var="s">
											     <img alt="" src="${s}" style="height: 100px;padding-left: 2px;">
											</c:forEach>
		                            </div>
		                            <div style="clear:both;"></div>
	                            </div>
	                             <hr>   
                             </c:forEach>
                             <div>
							    <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('4','1')" >审核通过</button>
					             <button type="button" class="btn btn-primary" 
					                   onclick="javascript:changeuserinfoapplystate('4','2')" >审核不通过</button>
					            <br>
					            <br>
					            <div class="form-group" >
					               <textarea  placeholder="选择不通过时 ，请在此处输入不通过原因" id="activitylist_reason" rows='3' style="width: 70%;"></textarea>
					            </div>
							</div>
                        </div>
		
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
<div class="modal fade" id="changestateModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
					    <input class="form-control" placeholder="aid" id="updatestate_aid"
							value="" style="display:none">
					    <select class="form-control" id="updatestateSelect"  onchange="javascript:updatestate(this)">
					    
					    </select>
					</div>

					<div class="form-group" id="updatestate_reason_div" >
						<input class="form-control" placeholder="reason" id="updatestate_reason"
							value="" >
					</div>
					<div class="form-group" id="updatestate_psw_div" >
						<input class="form-control" placeholder="userid,建议使用手机号" id="updatestate_userid"
							value="" >
						<input class="form-control" placeholder="password" id="updatestate_password"
							value="" >
					</div>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:changestateConfirm()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<div class="modal fade" id="lookapplyModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
					    <input class="form-control" placeholder="aid" id="look_aid"
							value="" readonly="readonly" >
					</div>
                  
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:changestateConfirm()" id="savebtn">保存</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
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
	
	
	<!-- DataTables JavaScript -->
	<script src="../js/news/dataversion.js"></script>
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
    <script src="../js/consts.js"></script>
	<script src="../js/businessmanage/businessuserinfoapplydetail.js"></script>

     <script src="../js/bootstrap-datetimepicker.js"
	type="text/javascript" charset="utf-8"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
			/* $ ("input[name='act_start_time'],input[name='act_stop_time']").datetimepicker (); */
            $ (".form_datetime").datetimepicker (
            {
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