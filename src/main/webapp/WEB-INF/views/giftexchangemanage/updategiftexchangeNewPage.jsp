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
<title>Giftexchange</title>
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
			<h1 class="page-header">礼物兑换管理——修改兑换</h1>
		</div>
   </div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">页面内容</div>
					<div class="modal-body" style="height:1200px">
					<div id="back" style="display:none; POSITION:fixed; z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
					<h3><font color="white">上传中，请耐心等待</font></h3></div>
					<div class="form-group">
						<b>活动名称：</b><input class="form-control" placeholder="最多输入10个字" id="ins_name"
							value="">
						<input type="hidden" value="" id="hiddenValue">
						<input type="hidden" value="" id="hiddenValueId">
						<input type="hidden" value="${exchangeid }" id="hiddenValueExchangeid">
					</div>
                    <div class="form-group">
						<b>商品简介：</b><textarea class="form-control" id="ins_des"
							placeholder="最多输入500字" rows="3"></textarea>
					</div>
                    <div class="form-group">
						<b>兑换条件：</b><select class="form-control" id="ins_condition"></select>
					</div>
					<div class="form-group">
						<b>兑换范围：</b><input class="form-control" placeholder="输入地区名称（逗号区分）" id="ins_scope"
							value="">
					</div>
					<div class="form-group">
						<b>兑换库存：</b><input class="form-control" placeholder="输入库存数量（1-999）" id="ins_sumcount"
							value="">
					</div>
					<div class="form-group" class="col-sm-12">
				    <div class="col-sm-6">
				    <label><b>有效期开始时间</b></label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="ins_validitystarttime" value=""
											readonly="readonly" style="cursor: pointer;" > <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
				    </div>
				    <div class="col-sm-6">
									<label><b>有效期结束时间</b></label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="ins_validityendtime" value=""
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
				   </div>
			    </div>
					 <div class="form-group" class="col-sm-12">
					     <b>选择支持兑换商家：</b><!-- <button type="button" class="btn btn-success btn-xs" onclick="javascript:select_styleAll()">全选</button>&nbsp
					                  <button type="button" class="btn btn-success btn-xs" onclick="javascript:select_NostyleAll()">全不选</button> -->
					     <select class="form-control"  id="ins_style" multiple="multiple"
							 onclick="javascript:select_style_show()"></select> 
							 <div id="style_div"></div> 
					</div>
					 
					  <div class="form-group">
						<b>选择兑换礼物：</b><select class="form-control" id="ins_giftid"></select>
					 </div>
						
					 <div class="form-group" class="col-sm-12">
						<b>兑换数量</b><input class="form-control" placeholder="请输入兑换数量" id="ins_giftcount"
							value="">
					</div>
                    <div class="form-group">
						<b>温馨提示：</b><textarea class="form-control" id="ins_remind"
							placeholder="最多输入500字" rows="3"></textarea>
					</div>
					<div class="form-group">
					    <div><b>状态</b></div>
							 <div class="col-sm-3"><input type="radio" name="ins_state" value="0" />下架</div>
							 <div class="col-sm-3"><input type="radio" name="ins_state" value="1" />上架</div>
					</div>
					<div style="clear:both;"></div>
					<br>
					
					 <div class="form-group" class="col-sm-12">
							<div class="col-sm-12">
								<input id="" type="button" value="设置图片" class="btn btn-info"
									data-toggle="modal" data-target="#titleImgModel">
							</div>
							<div class="col-sm-12">
								<input type="text" value="" id="titleimgadimg"> <img
									id="titleimgshowadimg" src=""
									style="width: 144px; height: 144px" />
							</div>
				  </div>
				
				</div>
					<div class="panel-footer">
						
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
					<button type="button" class="btn btn-primary" 
						onclick="javascript:updategiftexchangeConfirm()">保存</button>
					</div>
				</div>
			</div>

		</div>

		<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
		<!-- Custom Theme JavaScript -->
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

    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../js/giftexchangemanage/giftexchangelist.js"></script>
	<script type="text/javascript" src="../js/mallmanage/uploadGroupImage.js" ></script>
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
                minView: 2,
                format : 'yyyy-mm-dd',
                pickerPosition : "bottom"
            
            });
	</script> 
	
	 <script type="text/javascript">
	   set_style();
	   set_giftlist();
	   set_userlevel();
	   var exchangeid= $("#hiddenValueExchangeid").val();
	   updategiftexchange(exchangeid);
	   $("#hiddenValue").val("2");
	</script> 
</body>
</html>
