<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null
			? request.getParameter("content1")
			: "";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; ">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>LookUserNewPage</title>
<!-- MetisMenu CSS -->
<link href="/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/bower_components/metisMenu/dist/metisMenu.min.css"
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
<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<!-- <link href="../css/wysi/bootstrap-responsive.min.css" rel="stylesheet"> -->
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/wysi/wysi.css" type="text/css">
</link>

<script type="text/javascript"
	src="../js/jquery-ui-1.10.4.custom.min.js"></script>



<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />
<script src="../js/bootstrap-datetimepicker.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" charset="utf-8"></script>

<script src="../js/wysi/bootstrap-wysiwyg.js" type="text/javascript"></script>
<script src="../js/wysi/jquery.hotkeys.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="../css/boxmanage/addNewBoxPage.css" />
<style type="text/css">
body {
	width: 1200px;
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
<script>
	$ ('#grouplist > div').on ("click", function ()
    {
		console.log("111111");
	    showgroupimg ($ (this).attr ("id"));
	    $ ("#nowgroupid").val ($ (this).attr ("id"));
	    $ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
	    $ (this).addClass ('groupaction');
	    
    });
</script>


<script charset="utf-8" src="../js/boxmanage/kindeditor.js"></script>
<script type="text/javascript" src="../css/boxmanage/zh-CN.js"></script>

</head>
<body>
    
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">用户管理——编辑页</h1>
			<input id="mainbrandidsHidden" type="hidden" value=""/>
			<input id="recommendbrandidsHidden" type="hidden" value=""/>
			<input id="useridHidden" type="hidden" value="${userid }"/>
			<input id="hiddenValue" type="hidden" value=""/>
			
		</div>
   </div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">页面内容</div>
					<div class="modal-body" style="height:5800px">
					<div id="back" style="display:none; POSITION:fixed; z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
					<h3><font color="white">上传中，请耐心等待</font></h3></div>
					<div class="form-group">
					    <input class="form-control" placeholder="aid" id="look_userid"
							value="" readonly="readonly" >
							<input id="hidden_buid" type="hidden" value="" />
							<input id="hidden_state" type="hidden" value="" />
					</div>
                    <div class="form-group">
					   <b>商家名称</b><input class="form-control" placeholder="name" id="look_name"
							value="" >
					</div>
                    <div class="form-group">
					    <b>联系人姓名</b><input class="form-control" placeholder="contactname" id="look_contactname"
							value="" >
					</div>
                    <div class="form-group">
					    <b>联系人电话</b><input class="form-control" placeholder="contactphone" id="look_contactphone"
							value="" >
					</div>
                    <div class="form-group">
					    <div><b>商家类型</b></div><!-- <input class="form-control" placeholder="type" id="look_type"
							value="" > -->
							 <div class="col-sm-3"><input type="radio" name="look_type" value="0" />维修店</div>
							 <div class="col-sm-3"><input type="radio" name="look_type" value="1" />品牌店</div>
							 <div class="col-sm-3"><input type="radio" name="look_type" value="2" />综合店</div>
							 <div class="col-sm-3"><input type="radio" name="look_type" value="3" />4s店</div>
							
					</div>
					<div style="clear:both;"></div>
                    <div class="form-group">
					    <b>商家主营品牌</b><input class="form-control" placeholder="mainbrand" id="look_mainbrand"
							value="" ><button onclick="javascript:changemainbrand()">更换</button>
					</div>
					<div id="select_mainbrand_div" style="display:none;">
					    <select class="form-control"  id="select_mainbrand" multiple="multiple" size="10">
					        <c:forEach  items="${brandparentModels }"  var="brandparent" varStatus="ids">
					             <option value='${brandparent.bpid}'>${brandparent.name}</option>
					        </c:forEach>
					    </select>
					    <button onclick="javascript:changemainbrandCancel()">取消</button>
					    <button onclick="javascript:changemainbrandConfirm()">确认</button>
					</div>
                    <div class="form-group">
					    <b>地址</b><input class="form-control" placeholder="address" id="look_address"
							value="" >
					</div>
                    <div class="form-group">
					    <b>店面面积</b><input class="form-control" placeholder="businessarea" id="look_businessarea"
							value="" >
					</div>
                    <div class="form-group">
					    <b>职员人数</b><input class="form-control" placeholder="staffcount" id="look_staffcount"
							value="" >
					</div>
                    <div class="form-group">
					   <b>从业年限</b><input class="form-control" placeholder="age" id="look_age"
							value="" >
					</div>
                    <div class="form-group">
					    <b>精修品牌</b><input class="form-control" placeholder="recommendbrand" id="look_recommendbrand"
							value="" ><button onclick="javascript:changerecommendbrand()">更换</button>
					</div>
					<div id="select_recommendbrand_div" style="display:none;">
					    <select class="form-control"  id="select_recommendbrand" multiple="multiple" size="10">
					        <c:forEach  items="${brandparentModels }"  var="brandparent" varStatus="ids">
					             <option value='${brandparent.bpid}'>${brandparent.name}</option>
					        </c:forEach>
					    </select>
					    <button onclick="javascript:changerecommendbrandCancel()">取消</button>
					    <button onclick="javascript:changerecommendbrandConfirm()">确认</button>
					</div>
                    
                    <div class="form-group">
					    <b>其他</b><input class="form-control" placeholder="other" id="look_other"
							value="" >
					</div>
                    <div class="form-group">
					  <div><b>状态</b></div>
						<div class="col-sm-3"><input type="radio" name="look_state" value="0" />审核通过（下架）</div>
						<div class="col-sm-3"><input type="radio" name="look_state" value="1" />上架</div>
					</div>
					<div style="clear:both;"></div>
                    <div class="form-group">
					    <b>驳回原因</b><input class="form-control" placeholder="reason" id="look_reason"
							value=""  readonly="readonly" >
					</div>
                    <div class="form-group">
					   <b>省</b><input class="form-control" placeholder="province" id="look_province"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>市</b><input class="form-control" placeholder="city" id="look_city"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>添加时间</b><input class="form-control" placeholder="addtime" id="look_addtime"
							value="" readonly="readonly" >
					</div>
                    
                    <div class="form-group">
					    <b>经度</b><input class="form-control" placeholder="longitude" id="look_longitude"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>纬度</b><input class="form-control" placeholder="latitude" id="look_latitude"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>lbsid</b><input class="form-control" placeholder="lbsid" id="look_lbsid"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>开始营业时间</b><input class="form-control" placeholder="officestarttime" id="look_officestarttime"
							value="" >
					</div>
                    <div class="form-group">
					    <b>营业结束时间</b><input class="form-control" placeholder="officeendtime" id="look_officeendtime"
							value="" >
					</div>
                    <div class="form-group">
					    <b>商家关键词</b><input class="form-control" placeholder="busikeyword" id="look_busikeyword"
							value="" >
					</div>
                    <div class="form-group">
					    <b>商家客服</b><input class="form-control" placeholder="多客服userid  英文逗号隔开" id="look_kfuseridlist"
							value="" >
					</div>
					<div class="form-group">
					    <span><b>店铺简介</b></span><!-- <input class="form-control" placeholder="des" id="look_des"
							value="" > -->
							<div><textarea style="width: 100%;" class="form-group" id="look_des" rows="3" cols="" placeholder="des"></textarea></div>
					</div>
                    <div class="form-group">
					    <span><b>详细介绍</b></span><!-- <input class="form-control" placeholder="desdetail" id="look_desdetail"
							value="" > -->
							<div><textarea style="width: 100%;" class="form-group" id="look_desdetail" rows="3" cols="" placeholder="desdetail"></textarea></div>
					</div>
                    <div class="form-group">
					    <div><b>认证商家</b></div><!-- <input class="form-control" placeholder="isapprove" id="look_isapprove"
							value="" > -->
							<div class="col-sm-1"><input type="radio" name="look_isapprove" value="0" />否</div>
							<div class="col-sm-1"><input type="radio" name="look_isapprove" value="1" />是</div>
					</div>
					<div style="clear:both;"></div>
                    <div class="form-group">
					    <b>商家代码</b><input class="form-control" placeholder="businesscode" id="look_businesscode"
							value="" readonly="readonly">
					</div>
					<div class="form-group">
					    <div><b>服务能力</b></div><!-- <input class="form-control" placeholder="businessserviceliststr" id="look_businessserviceliststr"
							value="" > -->
							<c:forEach items="${businessServiceModels}" var="service" >
					            <div class="col-sm-3"><input type="checkbox" name="checkbox_businessservice" value="${service.bsid }" onchange="javascript:changeservicecheckbox(this)"/>${service.name }</div> 
					         </c:forEach>
					</div>
					<div style="clear:both;"></div>
					<div class="form-group">
					   <c:forEach items="${businessServiceModels}" var="service" >
					        <div id="servicecontentDiv${service.bsid }" style="display: none;"><span>${service.name }描述：</span><div><textarea style="width: 100%;" class="form-group" id="look_servicecontent${service.bsid }" rows="3" cols="" placeholder="请输入${service.name }描述"></textarea></div></div>
					   </c:forEach>
					</div>
					<div style="clear:both;"><hr></div>
					<div class="form-group">
					   <div class="col-sm-6">
					       <b>营业执照</b>
				          <div>
							<input id="" type="button" value="设置营业执照" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('1')">
						  </div>
						  <div>
							<input type="text" value="" id="titleimg"> <img
								id="titleimgshow" src=""
								style="width: 144px; height: 144px" onclick="javascript:toNewPageOpenPic('1')" />
						  </div>
					   </div>
					    <div class="col-sm-6">
					       <b>用户头像</b>
				          <div>
							<input id="" type="button" value="设置头像" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('2')">
						  </div>
						  <div>
							<input type="text" value="" id="headurl"> <img
								id="headurlshow" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					</div> 
					<div style="clear:both;"><br><hr></div>
				    <div class="form-group">
					   <div class="col-sm-3">
					       <b>门头照片1</b>
				          <div>
							<input id="" type="button" value="设置门头照片1" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('3')">
						  </div>
						  <div>
							<input type="text" value="" id="doorpics1"> <img
								id="doorpicsshow1" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					    <div class="col-sm-3">
					       <b>门头照片2</b>
				          <div>
							<input id="" type="button" value="设置门头照片2" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('4')">
						  </div>
						  <div>
							<input type="text" value="" id="doorpics2"> <img
								id="doorpicsshow2" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					   <div class="col-sm-3">
					       <b>门头照片3</b>
				          <div>
							<input id="" type="button" value="设置门头照片3" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('5')">
						  </div>
						  <div>
							<input type="text" value="" id="doorpics3"> <img
								id="doorpicsshow3" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					    <div class="col-sm-3">
					       <b>门头照片4</b>
				          <div>
							<input id="" type="button" value="设置门头照片4" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('6')">
						  </div>
						  <div>
							<input type="text" value="" id="doorpics4"> <img
								id="doorpicsshow4" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					</div> 
					<div style="clear:both;"><br><hr></div>
				    <div class="form-group">
					   <div class="col-sm-3">
					       <b>店内照片1</b>
				          <div>
							<input id="" type="button" value="设置店内照片1" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('7')">
						  </div>
						  <div>
							<input type="text" value="" id="shoppics1"> <img
								id="shoppicsshow1" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					    <div class="col-sm-3">
					       <b>店内照片2</b>
				          <div>
							<input id="" type="button" value="设置店内照片2" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('8')">
						  </div>
						  <div>
							<input type="text" value="" id="shoppics2"> <img
								id="shoppicsshow2" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					   <div class="col-sm-3">
					       <b>店内照片3</b>
				          <div>
							<input id="" type="button" value="设置店内照片3" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('9')">
						  </div>
						  <div>
							<input type="text" value="" id="shoppics3"> <img
								id="shoppicsshow3" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					    <div class="col-sm-3">
					       <b>店内照片4</b>
				          <div>
							<input id="" type="button" value="设置店内照片4" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel" onclick="javascript:insertTitleImgHidden('10')">
						  </div>
						  <div>
							<input type="text" value="" id="shoppics4"> <img
								id="shoppicsshow4" src=""
								style="width: 144px; height: 144px" />
						  </div>
					   </div>
					</div>
					<div style="clear:both;"><br><hr></div>
					<div class="form-group">
					    <span><b>商家活动1：</b></span>
					    <div style="width: 75%;"><input class="form-control" placeholder="baid" id="look_activitybaid1" value="" readonly="readonly"></div>
						<div>
							<div style="width: 75%; ">
								<input class="form-control" placeholder="title"
									id="look_activitytitle1" value="">
								<textarea id="look_activitycontent1" rows="7" cols="" placeholder="content"
									style="width: 100%;"></textarea>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片1第一张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('111')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics11">
									<img id="activitypicsshow11" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片1第二张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('112')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics12">
									<img id="activitypicsshow12" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片1第三张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('113')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics13">
									<img id="activitypicsshow13" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片1第四张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('114')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics14">
									<img id="activitypicsshow14" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div>
							    <!-- <div><input id="addtoUsecarMainBtn1" type="button" value="添加到用车首页" class="btn btn-success"
										data-toggle="modal" data-target="#addtoUsecarMainModel"  style="margin-left: 2px;" onclick="javascript:addtoUsecarMain('1')"></div> -->
							   <div><input id="addNewActivityBtn1" type="button" value="添加新活动" class="btn btn-warning"
										style="margin-left: 2px; display: none;" onclick="javascript:addNewActivity('1')"></div>
							   <div><input id="deleteActivityBtn1" type="button" value="删除该活动" class="btn btn-danger"
										style="margin-left: 2px;" onclick="javascript:deleteActivity('1')"></div>
							</div>
							<div style="clear:both;"></div>
						</div>
					</div>
                    <div class="form-group">
                     <span><b>商家活动2：</b></span>
                      <div style="width: 75%;"><input class="form-control" placeholder="baid" id="look_activitybaid2" value="" readonly="readonly"></div>
						<div>
							<div style="width: 75%; ">
								<input class="form-control" placeholder="title"
									id="look_activitytitle2" value="">
								<textarea id="look_activitycontent2" rows="7" cols="" placeholder="content"
									style="width: 100%;"></textarea>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片2第一张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('121')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics21">
									<img id="activitypicsshow21" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片2第二张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('122')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics22">
									<img id="activitypicsshow22" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片2第三张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('123')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics23">
									<img id="activitypicsshow23" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片2第四张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('124')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics24">
									<img id="activitypicsshow24" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div>
							  <!--   <div><input id="addtoUsecarMainBtn2" type="button" value="添加到用车首页" class="btn btn-success"
										data-toggle="modal" data-target="#addtoUsecarMainModel"  style="margin-left: 2px;" onclick="javascript:addtoUsecarMain('2')"></div> -->
							   <div><input id="addNewActivityBtn2" type="button" value="添加新活动" class="btn btn-warning"
										style="margin-left: 2px; display: none;" onclick="javascript:addNewActivity('2')"></div>
							   <div><input id="deleteActivityBtn2" type="button" value="删除该活动" class="btn btn-danger"
										style="margin-left: 2px;" onclick="javascript:deleteActivity('2')"></div>
							</div>
							<div style="clear:both;"></div>
						</div>
					</div>
                     <div class="form-group">
                      <span><b>商家活动3：</b></span>
                       <div style="width: 75%;"><input class="form-control" placeholder="baid" id="look_activitybaid3" value="" readonly="readonly"></div>
						<div>
							<div style="width: 75%; ">
								<input class="form-control" placeholder="title"
									id="look_activitytitle3" value="">
								<textarea id="look_activitycontent3" rows="7" cols="" placeholder="content"
									style="width: 100%;"></textarea>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片3第一张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('131')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics31">
									<img id="activitypicsshow31" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片3第二张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('132')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics32">
									<img id="activitypicsshow32" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片3第三张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('133')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics33">
									<img id="activitypicsshow33" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片3第四张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('134')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics34">
									<img id="activitypicsshow34" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div>
							  <!--   <div><input id="addtoUsecarMainBtn3" type="button" value="添加到用车首页" class="btn btn-success"
										data-toggle="modal" data-target="#addtoUsecarMainModel"  style="margin-left: 2px;" onclick="javascript:addtoUsecarMain('3')"></div> -->
							   <div><input id="addNewActivityBtn3" type="button" value="添加新活动" class="btn btn-warning"
										style="margin-left: 2px; display: none;" onclick="javascript:addNewActivity('3')"></div>
							   <div><input id="deleteActivityBtn3" type="button" value="删除该活动" class="btn btn-danger"
										style="margin-left: 2px;" onclick="javascript:deleteActivity('3')"></div>
							</div>
							<div style="clear:both;"></div>
						</div>
					</div>
					<div class="form-group">
					 <span><b>商家活动4：</b></span>
					  <div style="width: 75%;"><input class="form-control" placeholder="baid" id="look_activitybaid4" value="" readonly="readonly"></div>
						<div>
							<div style="width: 75%; ">
								<input class="form-control" placeholder="title"
									id="look_activitytitle4" value="">
								<textarea id="look_activitycontent4" rows="7" cols="" placeholder="content"
									style="width: 100%;"></textarea>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片4第一张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('141')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics41">
									<img id="activitypicsshow41" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片4第二张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('142')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics42">
									<img id="activitypicsshow42" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片4第三张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('143')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics43">
									<img id="activitypicsshow43" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div class="col-lg-2">
								<div>
									<input id="" type="button" value="设置活动照片4第四张" class="btn btn-info"
										data-toggle="modal" data-target="#titleImgModel"  style="margin-left: 2px;" onclick="javascript:insertTitleImgHidden('144')">
								</div>
								<div>
								    <input type="text" value="" id="activitypics44">
									<img id="activitypicsshow44" src=""
										style="width: 144px; height: 148px;margin-left: 2px;" />
								</div>
							</div>
							<div>
							  <!--   <div><input id="addtoUsecarMainBtn4" type="button" value="添加到用车首页" class="btn btn-success"
										data-toggle="modal" data-target="#addtoUsecarMainModel"  style="margin-left: 2px;" onclick="javascript:addtoUsecarMain('4')"></div> -->
							   <div><input id="addNewActivityBtn4" type="button" value="添加新活动" class="btn btn-warning"
										style="margin-left: 2px; display: none;" onclick="javascript:addNewActivity('4')" ></div>
							   <div><input id="deleteActivityBtn4" type="button" value="删除该活动" class="btn btn-danger"
										style="margin-left: 2px;" onclick="javascript:deleteActivity('4')"></div>
							</div>
							<div style="clear:both;"></div>
						</div>
					</div>

				</div>
					<div class="panel-footer">
						
					<button id="editBtn" type="button" class="btn btn-success" onclick="javascript:toEdit()">编辑</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:editUserConfirm()">保存编辑结果</button>
					</div>
				</div>
			</div>

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
				<div class="modal-body" style="padding: 5px;" >
				<div id="back1" style="display:none; POSITION:fixed; z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
				<h3><font color="white">上传中，请耐心等待</font></h3></div>
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
											<textarea   rows='2' style='word-break: break-all;' class="form-control" placeholder="图片名" name="ImgName" 
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


<div class="modal fade" id="addtoUsecarMainModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" id="timesBtn"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加到首页</h4>
				</div>
				<div class="modal-body">
				   
					<input type="text" value="" style="display: none" id="activityindex"/>
					<div class="form-group">
					<span><b>选择组名</b></span>
					   <select id="addtomaingroup_select" class="form-control">
					   </select>
					</div>
					<div class="form-group">
					<span><b>orderindex</b></span>
					    <input type="text"  class="form-control" value=""  id="addtomaingroup_orderindex"/>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal" id="addtomainBtn"
						onclick="javascript:addtoUsecarMainConfirm()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../js/businessmanage/businessuserlist.js"></script>
	<script type="text/javascript" src="../js/mallmanage/uploadGroupImage.js" ></script>
    <script type="text/javascript">
	   var userid=$("#useridHidden").val();
	   lookuser(userid);
	   $("input,select,button,textarea").prop('disabled',true);
	   $("#addtoUsecarMainBtn1").prop('disabled',false);
	   $("#addtoUsecarMainBtn2").prop('disabled',false);
	   $("#addtoUsecarMainBtn3").prop('disabled',false);
	   $("#addtoUsecarMainBtn4").prop('disabled',false);
	   $("#addtomaingroup_select").prop('disabled',false);
	   $("#addtomaingroup_orderindex").prop('disabled',false);
	   $("#closedelBoxModel").prop('disabled',false);
	   $("#editBtn").prop('disabled',false);
	   $("#addtomainBtn").prop('disabled',false);
	   $("#timesBtn").prop('disabled',false);
	</script> 
</body>
</html>