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
<title>MallproductPage</title>
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

<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />
	
<script type="text/javascript"
	src="../js/jquery-ui-1.10.4.custom.min.js"></script>



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



<!-- <link rel="stylesheet" href="../css/boxmanage/default/default.css" />
<link rel="stylesheet" href="../css/boxmanage/code/prettify.css" /> -->
<script charset="utf-8" src="../js/boxmanage/kindeditor.js"></script>
<script type="text/javascript" src="../css/boxmanage/zh-CN.js"></script>
<!-- <script charset="utf-8" src="../css/boxmanage/code/prettify.js"></script> -->
<script>
	
    function oldpriceCheck(){
   	 var ins_oldprice=$.trim($("#ins_oldprice").val());
   	// var reg=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
   	    var reg=/^([1-9]\d{0,15}|0)(\.\d{1,2})?$/;
   		if(reg.test(ins_oldprice)==false){
   			alert("价格不合法，请重新输入");
   			return false;
   		}
    }
    function newpriceCheck(){
   	 var ins_newprice=$.trim($("#ins_newprice").val());
   	// var reg=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
   	   var reg=/^([1-9]\d{0,15}|0)(\.\d{1,2})?$/;
   		if(reg.test(ins_newprice)==false){
   			alert("价格不合法，请重新输入");
   			return false;
   		}
    }
</script>

</head>
<body>
    
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">商品管理——修改商品页</h1>
		</div>
   </div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
				 
					<div class="panel-heading">页面内容</div>
					<div class="modal-body" style="height:1650px">
					<div id="back" style="display:none; POSITION:fixed;z-index: 1000; left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
					    <h3><font color="white">上传中，请耐心等待</font></h3></div>
					<div class="form-group">
						<input class="form-control" placeholder="请输入商品名" id="ins_title"
							value="">
						<input type="hidden" value="" id="hiddenValue">
						<input type="hidden" value="${mallproductid}" id="hiddenValueId">
					</div>
                    <div class="form-group">
						<textarea class="form-control" id="ins_des"
							placeholder="请输入商品简介" rows="3"></textarea>
					</div>
					 <div class="form-group" class="col-sm-12">
					        <div class="col-sm-3"><b>一级分类:</b></div>
					       <c:forEach items="${mallParentTypeList}" var="mallParentType" >
					          <div class="col-sm-3"><input type="radio" name="ins_radio_parenttype" value="${mallParentType.parentid }" onclick="ins_clickparenttype()" />${mallParentType.name }</div>
					       </c:forEach>
					  </div>
					 
					  <div class="form-group" class="col-sm-12"><br><br><br></div>
					  
					 <div class="form-group" class="col-sm-12" id="ins_radio_type_div"></div>
					 
					 <div class="form-group" class="col-sm-12"><br><br><br></div>
					 
					 <div class="form-group" class="col-sm-12">
					    <b>选择品牌</b><input type="text" size="20" id="input_probrand"><button class="btn btn-info btn-xs" onclick="javascript:search_probrand()">搜索</button>
					    <select class="form-control"  id="ins_probrand"></select>
					    
					</div>
					
					<!-- <div class="form-group">
					 设置风格：<button type="button" class="btn btn-success btn-xs" onclick="javascript:set_style()">设置风格</button>
					        <div id="set_style_div" style="display:none;"><select class="form-control" multiple="multiple"  id="set_style_select"></select></div>
					        <input type="text" name="username" value=""  style="border:0px;border-bottom:#000000 1px solid;">
					</div> -->
					<!--  <div class="form-group">
					 设置风格：<select class="form-control" multiple="multiple"  id="ins_style"  onclick="javascript:select_show()" ></select>
					        <div id="style_div"></div>
					</div>  -->
					 <div class="form-group" class="col-sm-12">
					    <div class="col-sm-6">
						<b>选择车型品牌</b><select class="form-control"  id="ins_brand" multiple="multiple" size="12" onclick="javascript:set_carmodel()"
							></select>
						</div>
						<div class="col-sm-6">
						  <b>选择车型</b><input type="radio" name="ins_carmodelAll" value="0" />全选<input type="radio" name="ins_carmodelAll" value="1" />全不选
						 <!--  <button type="button" class="btn btn-success btn-xs" onclick="javascript:select_carmodelAll()">全选</button>&nbsp
						               <button type="button" class="btn btn-success btn-xs" onclick="javascript:select_NocarmodelAll()">全不选</button> -->
						  <select class="form-control" size="12"  id="ins_model" multiple="multiple" 
							></select>
						</div>
						 
					</div>
					<div class="form-group" class="col-sm-12"><button type="button" class="btn btn-success"
			                     style="margin-left:220px;"     onclick="javascript:brandAndmodelConfirm()">确定车型品牌和车型</button>
			         </div>
			         <div class="form-group" class="col-sm-12">
			            <div id="brand_div"></div>
			            <div id="model_div"></div>
			         </div>
					 <div class="form-group" class="col-sm-12">
					     <b>设置风格</b><button type="button" class="btn btn-success btn-xs" onclick="javascript:select_styleAll()">全选</button>&nbsp
					                  <button type="button" class="btn btn-success btn-xs" onclick="javascript:select_NostyleAll()">全不选</button>
					     <select class="form-control"  id="ins_style" multiple="multiple"
							 onclick="javascript:select_style_show()"></select> 
							 <div id="style_div"></div> 
					</div>
					 <div class="form-group" class="col-sm-12">
					    <b>选择标签</b><select class="form-control"  id="ins_label"  multiple="multiple"
							onclick="javascript:select_label_show()"></select>
							<div id="label_div"></div> 
					 </div>
					  <div class="form-group" class="col-sm-12">
					     <b>关键字</b><input class="form-control" placeholder="请输入关键字" id="ins_keyword" value="">
					  </div> 
						
					 <div class="form-group" class="col-sm-12">
						<b>商品链接</b><input class="form-control" placeholder="请输入商品链接" id="ins_mallurl"
							value="">
					</div>
					<div class="form-group" class="col-sm-12">
					      <div class="col-sm-3"><b>档次</b></div>
					      <div class="col-sm-2"><input type="radio" name="ins_level" value="A" />0~3万</div>
					      <div class="col-sm-2"><input type="radio" name="ins_level" value="B" />3~6万</div>
					      <div class="col-sm-2"><input type="radio" name="ins_level" value="C" />6~10万</div>
					      <div class="col-sm-3"><input type="radio" name="ins_level" value="D" />10万以上</div> 
					 </div>
					 <div class="form-group" class="col-sm-12">
					      <div class="col-sm-2"><b>商品来源</b></div>
					      <div class="col-sm-2"><input type="radio" name="ins_source" value="0" />自营</div>
					      <div class="col-sm-3"><input type="radio" name="ins_source" value="1" />淘宝普通</div>
					      <div class="col-sm-3"><input type="radio" name="ins_source" value="2" />淘宝淘客</div> 
					      <div class="col-sm-2"><input type="radio" name="ins_source" value="3" />其他</div> 
					     
					 </div>
					<div class="form-group" class="col-sm-12">
					  <div class="col-sm-6">
					 <label>是否套餐</label>
					    <select class="form-control" id="ins_ispackage">
						<option value='0'>否</option>
						<option value='1'>是</option>
					     </select>
					  </div>
					<div class="col-sm-6">
					    <label>是否推荐</label>
					  <select class="form-control" id="ins_isrecommend">
						<option value='0'>否</option>
						<option value='1'>是</option>
					</select>
					</div>
					 
					</div>
					
					 <div class="form-group" class="col-sm-12">
					    <div class="col-sm-6">
							<div class="col-sm-12">
								<input id="" type="button" value="设置图片" class="btn btn-info"
									data-toggle="modal" data-target="#titleImgModel">
							</div>
							<div class="col-sm-12">
								<input type="text" value="" id="titleimg"> <img
									id="titleimgshow" src=""
									style="width: 144px; height: 144px" />
							</div>
						</div>
						 <div class="col-sm-6">
						  <div class="col-sm-12">
						     <b>市场价：￥</b><input class="form-control" placeholder="市场价,最多两位小数" id="ins_oldprice" 
							value="">
						  </div>
						  <div class="col-sm-12">
						     <b>优惠价：￥</b><input class="form-control" placeholder="优惠价,最多两位小数" id="ins_newprice" 
							value="">
						  </div>
						    <div class="col-sm-12">
							  <label>是否上架</label>
							  <select class="form-control" id="ins_state">
								<option value='0'>否</option>
								<option value='1'>是</option>
							</select>
							</div>
							<div class="col-sm-12"><br><br></div>
						 </div>
					</div>
				<div class="col-sm-12"></div>
				<div class="form-group" class="col-sm-12">
				    <div class="col-sm-6"></div>
				    <div class="col-sm-6">
									<label>上架时间</label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="ins_ptime" value=""
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
				   </div>
			 </div>
				</div>
					<div class="panel-footer">
						
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
					<button type="button" class="btn btn-primary" 
						onclick="javascript:insertMallConfirm()">保存</button>
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
	<!-- 	<script src="../js/boxmanage/addNewBoxPage.js"></script> -->

	<!-- 	<script type="text/javascript">
			/* $ ("input[name='act_start_time'],input[name='act_stop_time']").datetimepicker (); */
            $ (".form_datetime").datetimepicker (
            {
                language : 'zh-CN',
                autoclose : 1,
                todayBtn : 1,
                minuteStep : 1,
                format : 'yyyy-mm-dd hh:ii',
                pickerPosition : "bottom-left"
            
            });
		</script> -->
	
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
				<div id="back1" style="display:none; POSITION:fixed;z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
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


		<!-- /.modal -->
	 <!-- 	<script type="text/javascript">
			function closeDialog ()
            {
	            $ ("#titleImgModel").css ("display", "none");
            }
		</script>

	
		<div class="modal fade" id="contentHTMLModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">文章源码</h4>
					</div>
					<div class="modal-body">
						<div class="form-group"
							style="height: 70%; overflow: auto; overflow-x: hidden;">
							<textarea id="contentHTML" cols="78" rows="34"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="javascript:saveContentHTML()" id="savebtn">保存</button>
					</div>
				</div>
				
			</div>
			
		</div> -->
		
		<script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- 	<script>
			$ ('#grouplist > div').on ("click", function ()
            {
	            showgroupimg ($ (this).attr ("id"));
	            $ ("#nowgroupid").val ($ (this).attr ("id"));
	            $ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
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
            
            change_contentType(); 
		</script>  -->
   
    <script src="../js/mallmanage/malllist.js"></script>
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
                format : 'yyyy-mm-dd hh:ii',
                pickerPosition : "bottom"
            
            });
	</script> 
		
	 <script type="text/javascript">
	   set_style();
	 //  set_probrand();
	   set_label();
	   set_brand();
	   $("#hiddenValue").val("1");
	   var pid= $("#hiddenValueId").val();
	   updateMallProduct(pid);
	</script> 
</body>
</html>
