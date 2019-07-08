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
<title>BoxPage</title>
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
<!-- <link type="text/css" rel="stylesheet" href="../css/timer/admin.css"/> -->
<!-- <link href="../css/wysi/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">-->
<!-- <link href="../css/wysi/bootstrap-responsive.min.css" rel="stylesheet"> -->
<!-- <link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet"> -->
<link rel="stylesheet" href="../css/wysi/wysi.css" type="text/css">
</link>
<!-- <link rel="stylesheet" type="text/css" href="../css/timer/jquery-ui.css" /> -->
<script type="text/javascript"
	src="../js/jquery-ui-1.10.4.custom.min.js"></script>
<!-- 	<script type="text/javascript" src='../js/boxmanage/seed-min.js'></script>
		<script type="text/javascript" src='../js/boxmanage/editor.js'></script>
			<script type="text/javascript" src='../js/boxmanage/avalon.js'></script> -->
<!--<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-zh-CN.js"></script> -->


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




<!-- <link rel="stylesheet" href="../css/boxmanage/default/default.css" />
<link rel="stylesheet" href="../css/boxmanage/code/prettify.css" /> -->
<script charset="utf-8" src="../js/boxmanage/kindeditor.js"></script>
<script type="text/javascript" src="../css/boxmanage/zh-CN.js"></script>
<!-- <script charset="utf-8" src="../css/boxmanage/code/prettify.js"></script> -->
<script>
	/* 	KindEditor.ready (function (K)
     {
     var editor1 = K.create ('textarea[name="content1"]',
    
     {
     cssPath : '../css/boxmanage/code/prettify.css',
     uploadJson : '../boxmanage/upload_json',
     fileManagerJson : '../boxmanage/file_manager_json',
     allowFileManager : true,
     afterCreate : function ()
     {
     var self = this;
     K.ctrl (document, 13, function ()
     {
     self.sync ();
     document.forms['example'].submit ();
     });
     K.ctrl (self.edit.doc, 13, function ()
     {
     self.sync ();
     document.forms['example'].submit ();
     });
     }
     });
     prettyPrint ();
     }); */
    var editor;
    KindEditor.ready (function (K)
    {
	    var options =
	    {
	        width : '100%',
	        height : '500px',
	        cssPath : '../css/boxmanage/code/prettify.css',
	        allowImageUpload : false,
	        filterMode : true,
	        allowFileManager : true,
	        resizeType : '1',
	        zIndex : '1001',
	        filterMode : false
	    
	    };
	    editor = K.create ('textarea[name="content"]', options);
	    
	    editor.html ('${noteModel.note_content }');
    });
</script>
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">手册管理——编辑分享页</h1>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">页面内容</div>
					<div class="panel-body">
						<div class="form-group col-lg-10">
							<p class="text-primary">标题（建议32字以内）：</p>
							<input class="form-control" placeholder="标题" id="box_title"
								value="${motobox.title}">
						</div>
						<div class="form-group col-lg-2">
							<p class="text-primary">手册类型：</p>
							<select class="form-control" id='box_type'>
								<c:forEach items="${boxtypelist}" var="boxtype" varStatus="ids">
									<c:choose>
										<c:when test="${boxtype.typeid==motobox.typeid }">
											<option value="${boxtype.typeid}" selected>${boxtype.description}</option>
										</c:when>
										<c:otherwise>
											<option value="${boxtype.typeid}">${boxtype.description}</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-lg-12">
							<p class="text-primary">副标题（建议30字以内）：</p>
							<input class="form-control" placeholder="副标题" id="box_subtitle"
								value="${motobox.subtitle}">
						</div>
						<div class="form-group col-lg-3">
							<p class="text-primary">作者：</p>
							<input class="form-control" placeholder="作者" id="box_writer"
								value="${noteModel.note_writer}">
						</div>
						<div class="form-group col-lg-3">
							<p class="text-primary">日期：</p>
							<div class="input-append date form_datetime"
								onclick="javascript:showTimeForm(this)">
								<input size="16" type="text" value="${puttime}" readonly
									id="boxtime" style="cursor: pointer;" class="form-control">
								<span class="add-on"><i class="icon-th"></i></span>
							</div>
						</div>
						<!-- <div class="form-group col-lg-3">
							<p class="text-primary">日期：</p>
							<div class="input-append date form_datetime"
								onclick="javascript:showTimeForm(this)">
								<input size="16" type="text" value="" readonly id="boxtime"
									style="cursor: pointer;" class="form-control"> <span
									class="add-on"><i class="icon-th"></i></span>
							</div>
						</div>	 -->
						<div class="form-group col-lg-3">
							<p class="text-primary">发布状态：</p>
							<c:choose>
								<c:when test="${motobox.status==0 }">
									<c:choose>
										<c:when test="${pushCheck=='lock' }">
											<input type="radio" name="box_status" id="man" value="0"
												checked="checked" />未发布
						                    <input type="radio" name="box_status"
												id="woman" value="1" />发布
										</c:when>
										<c:otherwise>
											<input type="radio" name="box_status" id="man" value="0"
												checked="checked" disabled="">未发布
						                   <input type="radio" name="box_status"
												id="woman" value="1" disabled="">发布
										</c:otherwise>
									</c:choose>
								</c:when>

								<c:otherwise>
									<c:choose>
										<c:when test="${pushCheck=='lock' }">
											<input type="radio" name="box_status" id="man" value="0" />未发布
						                    <input type="radio" name="box_status"
												id="woman" value="1" checked="checked" />发布
										</c:when>
										<c:otherwise>
											<input type="radio" name="box_status" id="man" value="0"
												disabled="">未发布
						                   <input type="radio" name="box_status"
												id="woman" value="1" disabled="" checked="checked">发布
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="form-group col-lg-2">
							<p class="text-primary">列表显示类型：</p>
							<select class="form-control" id='box_boxkind'>
								
							<option value ="0" <c:if test="${motobox.boxkind  == '0'}">selected</c:if>>无图</option>
							<option value ="1" <c:if test="${motobox.boxkind  == '1'}">selected</c:if>>图文</option>
							<option value ="2" <c:if test="${motobox.boxkind  == '2'}">selected</c:if>>大图</option>
							
							</select>
						</div>
						<div class="form-group col-lg-12" style="display: block">
							<p class="text-primary">封面图片(图片为方形等比，建议144*144)：</p>
							<input id="" type="button" value="设置封面图片" class="btn btn-info"
								data-toggle="modal"
								data-target="#titleImgModel">
								<input type="text" value="${box_titleimage}" id="titleimg">
								<img id="titleimgshow" src="${box_titleimage}" style="width:144px;height:144px"/>
						</div>
						<div class="form-group col-lg-12">
							<p class="text-primary">搜索关键字：</p>
							<input class="form-control" placeholder="关键字" id="box_keyword"
								value="${motobox.keyword}">
						</div>
						
						<div class="form-group col-lg-12" id="notmotopush"
							style="display: ${notmotopush}">
							<div class="form-group col-lg-4">
								<p class="text-primary">推荐人ID：</p>
								<input class="form-control" placeholder="推荐人id"
									id="box_submitter" value="${motobox.submitter }">
							</div>
							<div class="form-group col-lg-4">
								<p class="text-primary">来源：</p>
								<input class="form-control" placeholder="来源" id="box_source"
									value="${motobox.source }">
							</div>
							<div class="form-group col-lg-4">
								<p class="text-primary">是否V：</p>
								<input class="form-control" placeholder="是否加V" id="box_approve"
									value="${motobox.approve }">
							</div>

						</div>
						<div class="form-group col-lg-10" id='motopush' style="display:${motopush}">
							<p class="text-primary">链接地址：</p>
							<input class="form-control" placeholder="链接地址" id="box_boxurl"
								value="${motobox.localpath}">
						</div>
					

					
						<div class="col-lg-12"
							style="background-color: whitesmoke; padding-top: 20px;display:${motoselfpush};" id='motoselfpush'>
							<p class="text-primary">手册内容：</p>
							<div class="container" id="cont" style="padding: 0; width: 100%">
								<textarea id="editor_id" name="content"
									style="width: 700px; height: 300px;">
                                 </textarea>
                               </div>
						</div>
						<!-- body -->
					</div>
					<div class="panel-footer">
					<button type="button" class="btn btn-primary"
							onclick="javascript:previewBoxMessage('${motobox.boxid}')"
							id="savebtn">预览并保存手册</button>
					<%-- 	<button type="button" class="btn btn-primary"
							onclick="javascript:saveBoxMessage('${motobox.boxid}')"
							id="savebtn">保存手册信息</button> --%>
						<!-- data-toggle="
								modal" data-target="#insertRoleModel"-->

						</div>
					</div>
				</div>

			</div>
			<!-- saveRoleConf -->

			<!-- Metis Menu Plugin JavaScript -->
			<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
			<!-- Custom Theme JavaScript -->
			<script src="../dist/js/sb-admin-2.js"></script>
			<script src="../js/boxmanage/jquery.form.min.js"></script>
			<script src="../js/boxmanage/boxsel.js"></script>

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
			<!-- Modal -->
			<div class="modal fade in" id="selectImgModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog" style="width: 80%">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true" onclick="javascript:closeDialog();">&times;</button>
							<h4 class="modal-title" id="myModalLabel">选择图片</h4>
						</div>
						<div class="modal-body" style="padding: 5px;">
							<div class="form-group" id="imglistdiv"
								style="height: 600px; overflow: auto; overflow-x: hidden;">
								<div
									style="float: left; width: 24%; padding-right: 0px; height: 100%; border: 1px solid #E7E7EB; overflow: auto;"
									id="grouplist">
									<input type="text" style="display: none"
										value="${imggroups[0].group_guid}" id="nowgroupid">
									<c:forEach items="${imggroups}" var="imggroup" varStatus="ids">
										<div
											class="groupdiv <c:if test='${ids.index==0}'>groupaction</c:if>"
											id="${imggroup.group_guid}">${imggroup.group_name}</div>
									</c:forEach>
								</div>
								<div
									style="float: left; width: 75%; padding-left: 0px; border: 1px solid #E7E7EB; height: 100%; overflow: auto;"
									id="groupimglist">
									<c:forEach items="${motoimgs}" var="imgMessages"
										varStatus="ids">
										<div class="col-lg-3" id="${imgMessages.img_guid }"
											onclick="javascript:selectImg('${imgMessages.img_guid }');">
											<div class="panel panel-default">
												<div class="panel-body" style="text-align: center;">
													<img src="${imgMessages.img_url }" width="100" height="100">
												</div>
												<div class="panel-footer"
													style="text-align: center; padding: 0px; font-size: 9pt; height: 30px;">
													<input class="form-control" placeholder="图片名"
														name="ImgName" value="${imgMessages.img_name }"
														disabled="disabled">
												</div>
												<div class="panel-footer"
													style="text-align: center; padding: 5px 0px;">
													<input type="button" value="选择" class="btn btn-info"
														onclick="">
												</div>
											</div>
											<div class="selected_mask" name="selectdiv"
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
									style="margin-left: 0;">
							</form>
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="closeModel"
								onclick="javascript:closeDialog();">关闭</button>
							<button type="button" class="btn btn-primary" id="savebtn"
								data-dismiss="modal" onclick="javascript:insertImg()">插入图片</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<script type="text/javascript">
				function closeDialog ()
                {
	                $ ("#selectImgModel").css ("display", "none");
                }
			</script>

			<!-- Modal -->
			<div class="modal fade" id="titleImgModel" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
									<c:forEach items="${motoimgs}" var="imgMessages"
										varStatus="ids">
										<div class="col-lg-3" id="XX_${imgMessages.img_guid }"
											onclick="javascript:XX_selectImg('XX_${imgMessages.img_guid }');">
											<div class="panel panel-default">
												<div class="panel-body" style="text-align: center;">
													<img src="${imgMessages.img_url }" width="100" height="100">
												</div>
												<div class="panel-footer"
													style="text-align: center; padding: 0px; font-size: 9pt; height: 30px;">
													<input class="form-control" placeholder="图片名"
														name="ImgName" value="${imgMessages.img_name }"
														disabled="disabled">
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
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="closeModel"
								onclick="javascript:titleImgModel();">关闭</button>
							<button type="button" class="btn btn-primary" id="savebtn"
								data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->
			<script>
				function insertTitleImg ()
                {
	                $("#titleimgshow").attr("src",$("#titleimg").val());
                }
			</script>
			<!-- Bootstrap Core JavaScript -->
			<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
			<script>
			$ ('#grouplist > div').on ("click", function ()
				    {
					    showgroupimg ($ (this).attr ("id"));
					    $ ("#nowgroupid").val ($ (this).attr ("id"));
					    $ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
					    $ (this).addClass ('groupaction');
					    
				    });
					$('#XX_grouplist>div').on("click",function(){
						XX_showgroupimg($(this).attr("id"));
						$ ("#XX_nowgroupid").val ($ (this).attr ("id"));
					    $ (this).parent ("#XX_grouplist").find ("div").removeClass ('groupaction');
					    $ (this).addClass ('groupaction');
						
					});
                
                // tooltip demo
                $ ('.tooltip-demo').tooltip (
                {
                    selector : "[data-toggle=tooltip]",
                    container : "body"
                })

                // popover demo
                $ ("[data-toggle=popover]").popover ()
			</script>
</body>
</html>
