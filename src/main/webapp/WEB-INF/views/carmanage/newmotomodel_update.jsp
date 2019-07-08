<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; ">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>NewMOtoModelPage</title>
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

<script src="../bower_components/jquery/dist/jquery.min.js"></script>

<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/wysi/wysi.css" type="text/css">
</link>

<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />
	
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


<script charset="utf-8" src="../js/boxmanage/kindeditor.js"></script>
<script type="text/javascript" src="../css/boxmanage/zh-CN.js"></script>


</head>
<body>
    
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">新车型库管理——修改车型页</h1>
		</div>
   </div>
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
				 
					<div class="panel-heading">页面内容</div>
					<div class="modal-body" style="height:2200px">
					<div id="back" style="display:none; POSITION:fixed;z-index: 1000; left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
					    <h3><font color="white">上传中，请耐心等待</font></h3></div>
					<div class="form-group">
						<input type="hidden" value="" id="hiddenValue">
						<input type="hidden" value="${newmotomodel.mid}" id="hiddenValueId">
					</div>
					<div class="form-group">
					 <label >车型名称</label>
						<input class="form-control" placeholder="请输入车型名称" id="ins_name"
							value="${newmotomodel.name}">
					</div>
					
					
					
					<div class="form-group" class="col-sm-12">
					    <div class="col-sm-4">
						    <b>选择大品牌</b><select class="form-control"  id="ins_bp"   onclick="javascript:set_brand()"></select>
						</div>
					    <div class="col-sm-4">
						    <b>选择小品牌</b><select class="form-control"  id="ins_brand"   onclick="javascript:set_motomodel()"></select>
						</div>
						<div class="col-sm-4">
						    <b>选择车型</b><select class="form-control"   id="ins_model" ></select>
						</div>
					</div>
					
					
					<div class="form-group">
					<label >价格 (单位元)</label>
						<input class="form-control" placeholder="请输入价格 单位元" id="ins_price"
							value="${newmotomodel.price}">
					</div>
					<div class="form-group">
					 <label >车型风格</label>
					<%-- 	<input class="form-control" placeholder="请输入车型风格" id="ins_style"
							value="${newmotomodel.style}"> --%>
					    <input type="radio"   name="ins_style" value="踏板" >踏板
						<input type="radio"  name="ins_style" value="街车" >街车
						<input type="radio"   name="ins_style" value="跑车" >跑车
						<input type="radio"   name="ins_style" value="经典／复古" >经典/复古
						<input type="radio"  name="ins_style" value="巡航" >巡航
						<input type="radio"  name="ins_style" value="拉力探险" >拉力探险
						<input type="radio"   name="ins_style" value="旅行" >旅行
						<input type="radio"   name="ins_style" value="弯梁" >弯梁
						<input type="radio"  name="ins_style" value="越野滑胎" >越野滑胎
						<input type="radio"   name="ins_style" value="三轮" >三轮
						<input type="radio"   name="ins_style" value="新能源" >新能源
						<input type="radio"  name="ins_style" value="其他" >其他
					</div>
					<div class="form-group">
					 <label >最大功率</label>
						<input class="form-control" placeholder="请输入最大功率" id="ins_maxpower"
							value="${newmotomodel.maxpower}">
					</div>
					<div class="form-group">
					 <label >最大扭矩</label>
						<input class="form-control" placeholder="请输入最大扭矩" id="ins_maxtorque"
							value="${newmotomodel.maxtorque}">
					</div>
					<div class="form-group">
					 <label >发动机排量</label>
						<input class="form-control" placeholder="请输入发动机排量" id="ins_cc"
							value="${newmotomodel.cc}">
					</div>
					<div class="form-group">
					<label >变速箱</label>
						<input class="form-control" placeholder="请输入变速箱" id="ins_gearbox"
							value="${newmotomodel.gearbox}">
					</div>
					<div class="form-group">
					 
					 <label >坐高</label>
						<input class="form-control" placeholder="请输入坐高 " id="ins_sitheight"
							value="${newmotomodel.sitheight}">
					</div>
					<div class="form-group">
					 <label >长宽高   格式：1000*1000*1000</label>
						<input class="form-control" placeholder="请输入长宽高   格式：1000*1000*1000" id="ins_lwh"
							value="${newmotomodel.lwh}">
					</div>
					<div class="form-group">
					 <label >油箱容积  单位升 （L）  </label>
						<input class="form-control" placeholder="请输入油箱容积  单位升 （L）" id="ins_tankcapacity"
							value="${newmotomodel.tankcapacity}">
					</div>
					<div class="form-group">
					 <label >供油方式</label>
						<input class="form-control" placeholder="请输入供油方式" id="ins_oilway"
							value="${newmotomodel.oilway}">
					</div>
					<div class="form-group">
					 <label >传动方式</label>
						<input class="form-control" placeholder="请输入传动方式" id="ins_transmissionway"
							value="${newmotomodel.transmissionway}">
					</div>
					<div class="form-group">
					 <label >前制动器</label>
						<input class="form-control" placeholder="请输入前制动器" id="ins_frontbrake"
							value="${newmotomodel.frontbrake}">
					</div>
					<div class="form-group">
					 
					  <label >后制动器</label>
						<input class="form-control" placeholder="请输入后制动器" id="ins_rearbrake"
							value="${newmotomodel.rearbrake}">
					</div>
					<div class="form-group">
					    <label >前轮胎规格</label>
						<input class="form-control" placeholder="请输入前轮胎规格" id="ins_frontwheelsize"
							value="${newmotomodel.frontwheelsize}">
					</div>
					<div class="form-group">
					  <label >后轮胎规格</label>
						<input class="form-control" placeholder="请输入后轮胎规格" id="ins_rearwheelsize"
							value="${newmotomodel.rearwheelsize}">
					</div>
					<div class="form-group">
					 <label >是否有abs</label>
					   <select class="form-control" id="ins_haveabs">
						 <!--   <option value='0'>无</option>
						   <option value='1'>有</option> -->
					   </select>
					</div>
					<div class="form-group">
					  <label >年份  如：2018</label>
						<input class="form-control" placeholder="请输入年份 如：2018" id="ins_caryear"
							value="${newmotomodel.caryear}">
					</div>
					<div class="form-group">
					 <label >是否有车载巡航</label>
					   <select class="form-control" id="ins_haveonboard">
						  <!--  <option value='0'>无</option>
						   <option value='1'>有</option> -->
					   </select>
					</div>
					<div class="form-group">
					  <label >其他电子设备</label>
						<input class="form-control" placeholder="请输入其他电子设备" id="ins_otherelectronic"
							value="${newmotomodel.otherelectronic}">
					</div>
					
					
					 <div class="form-group" class="col-sm-12">
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置首图" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('1')">
						  <input type="text" value="" id="titleimg"> <img
							id="titleimgshow" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图2" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('2')">
						  <input type="text" value="" id="titleimg2"> <img
							id="titleimgshow2" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图3" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('3')">
						  <input type="text" value="" id="titleimg3"> <img
							id="titleimgshow3" src=""
							style="width: 144px; height: 144px" />
						</div>
					</div>
					
					 <div class="form-group" class="col-sm-12">
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图4" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('4')">
						  <input type="text" value="" id="titleimg4"> <img
							id="titleimgshow4" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图5" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('5')">
						  <input type="text" value="" id="titleimg5"> <img
							id="titleimgshow5" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图6" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('6')">
						  <input type="text" value="" id="titleimg6"> <img
							id="titleimgshow6" src=""
							style="width: 144px; height: 144px" />
						</div>
					</div>
					
					 <div class="form-group" class="col-sm-12">
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图7" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('7')">
						  <input type="text" value="" id="titleimg7"> <img
							id="titleimgshow7" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图8" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('8')">
						  <input type="text" value="" id="titleimg8"> <img
							id="titleimgshow8" src=""
							style="width: 144px; height: 144px" />
						</div>
					 	<div class="col-sm-4">
						  <input id="" type="button" value="设置图9" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:imgageHiddlenvalue('9')">
						  <input type="text" value="" id="titleimg9"> <img
							id="titleimgshow9" src=""
							style="width: 144px; height: 144px" />
						</div>
					</div>
		
				</div>
					<div class="panel-footer">
						
					<!-- <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> -->
					<button type="button" class="btn btn-primary" 
						onclick="javascript:updateNewMotomodelConfirm()">保存</button>
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
						id="closeModel" >关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
		</div>
</div>

    <script src="../bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="../js/carmanage/newmotomodel_update.js"></script>
	<script type="text/javascript" src="../js/mallmanage/uploadGroupImage.js" ></script>
	
	 <script src="../js/bootstrap-datetimepicker.js"
	type="text/javascript" charset="utf-8"></script>
    <script src="../js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
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
	
	   var bpid='${newmotomodel.bpid}';
	   var brandid='${newmotomodel.brandid}';
	   var modelid='${newmotomodel.modelid}';
	   set_bp_brand_model(bpid,brandid,modelid);
	   $("#ins_haveabs option[value='"+'${newmotomodel.haveabs}'+"'] ").prop("selected","selected");
	   $("#ins_haveabs option[value='"+'${newmotomodel.haveonboard}'+"'] ").prop("selected","selected");
	   
	   var abs='${newmotomodel.haveabs}';
	   var absStr='';
	   if(abs=='1'){
		   absStr+="<option value='0'>无</option><option value='1' selected>有</option>";
      	 $("#ins_haveabs").empty();
	     $("#ins_haveabs").append(absStr);
      }else if(abs=='0'){
    	  absStr+="<option value='0'  selected>无</option><option value='1'>有</option>";
      	 $("#ins_haveabs").empty();
		 $("#ins_haveabs").append(absStr);
      }
	   
	   var onboard='${newmotomodel.haveonboard}';
	   var onboardStr='';
	   if(onboard=='1'){
		   onboardStr+="<option value='0'>无</option><option value='1' selected>有</option>";
      	 $("#ins_haveonboard").empty();
	     $("#ins_haveonboard").append(onboardStr);
      }else if(onboard=='0'){
    	  onboardStr+="<option value='0'  selected>无</option><option value='1'>有</option>";
      	 $("#ins_haveonboard").empty();
		 $("#ins_haveonboard").append(onboardStr);
      }
	   
	   var array = new Array();  
	    <c:forEach items="${piclist}" var="item" varStatus="status" >  
	        array.push("${item}");  
	    </c:forEach>  
	    for(var i=0;i<array.length;i++){ 
	    	 if(i==0){
				  $("#titleimg").val(array[i]);
				  $("#titleimgshow").attr("src",array[i]);
			   }else{
				  $("#titleimg"+(i+1)).val(array[i]);
				  $("#titleimgshow"+(i+1)).attr("src",array[i]);
			   } 
	    }  
	    
	    var newmotomodelstyle= "${newmotomodel.style}";
	    
		$("input[name='ins_style'][value='"+newmotomodelstyle+"']").prop("checked",true);

	  
	</script> 
</body>

</html>
