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
$(document).ready(function(){ 
	    var data={
	    		"userGuid":0,
	    		"page":1,
	    		"limit":20,
	    		"order":0
	    }
	$("#mallListTable").load("/mallmanage/mallproductlist",data);
	}); 
	
	
 function oldpriceCheck(){
	 var ins_oldprice=$.trim($("#ins_oldprice").val());
	 var reg=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
		if(reg.test(ins_oldprice)==false){
			alert("价格不合法，请重新输入");
			return false;
		}
 }
 function newpriceCheck(){
	 var ins_newprice=$.trim($("#ins_newprice").val());
	 var reg=/^(0|[1-9][0-9]{0,9})(\.[0-9]{1,2})?$/;
		if(reg.test(ins_newprice)==false){
			alert("价格不合法，请重新输入");
			return false;
		}
 }
</script>
</head>

<body >
<div id="back" style="display:none; POSITION:fixed;z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
				<h3><font color="white">后台运行中，请耐心等待</font></h3></div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">商品管理&nbsp<button type="button" class="btn btn-success"  disabled="disabled"
			
			onclick="javascript:insertMall()">新增商品</button>
			
			</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	
   <div class="row">
      <div class="col-lg-12">
                    <div class="col-sm-12">
					        <div class="col-sm-1"><b>一级分类:</b></div>
					       <c:forEach items="${mallParentTypeList}" var="mallParentType" >
					          <div class="col-sm-1"><input type="radio" name="radio_parenttype" value="${mallParentType.parentid }" onclick="clickparenttype()" />${mallParentType.name }</div>
					       </c:forEach>
					  </div>
					  <div class="col-sm-12" id="radio_type_div"></div>
					   <div class="col-sm-12">
					         <div class="col-sm-2">
					           <span><b>风格(多选):</b><input type="checkbox"  id="checkbox_styleAll" onclick="checkbox_styleAll()"/>全选</span>
					         </div> 
					         <c:forEach items="${mallStyleList}" var="mallStyle" >
					            <div class="col-sm-1"><input type="checkbox" name="checkbox_style" value="${mallStyle.styleid }" />${mallStyle.name }</div> 
					         </c:forEach>
		  	
					  </div>
					   <div class="col-sm-12">
					          <div class="col-sm-2">
					           <span><b>品牌(多选):</b><input type="checkbox"  id="checkbox_brandAll" onclick="checkbox_brandAll()"/>全选</span>
					         </div> 
					         <c:forEach items="${mallBrandList}" var="mallBrand" >
					            <div class="col-sm-1"><input type="checkbox" name="checkbox_brand" value="${mallBrand.brandid }" />${mallBrand.name }</div> 
					         </c:forEach>
					  </div>
					  <div class="col-sm-12">
					      <div class="col-sm-1"><b>车型价位：</b></div>
					      <div class="col-sm-1"><input type="radio" name="radio_carprice" value="A" />0~3万</div>
					      <div class="col-sm-1"><input type="radio" name="radio_carprice" value="B" />3~6万</div>
					      <div class="col-sm-1"><input type="radio" name="radio_carprice" value="C" />6~10万</div>
					      <div class="col-sm-1"><input type="radio" name="radio_carprice" value="D" />10万以上</div>  
					  </div>
					  <div class="col-sm-12">
					     <div class="col-sm-6">
					          <div class="col-sm-4" >
					              <b>价格：</b><input type="text" id="search_price_start"/>
					          </div>
					          <div class="col-sm-1">————</div>
					          <div class="col-sm-3" >
					              <input type="text" id="search_price_end"/>
					          </div>
					     </div>
					     <div class="col-sm-6">
					       <b>搜索：</b><input type="text" id="search_input"/>
					     </div>
					  </div>
					   <div class="col-sm-12">
					         <div class="col-sm-2">
					           <span><b>上架状态:</b><input type="checkbox"  id="checkbox_stateAll" onclick="checkbox_stateAll()"/>全选</span>
					         </div> 
					         <div class="col-sm-1"><input type="checkbox" name="checkbox_state" value="1" />是</div> 
							 <div class="col-sm-1"><input type="checkbox" name="checkbox_state"  value="0" />否</div>	  	
					  </div>
					  <div class="col-sm-12">
					     <hr>
					  </div>
					 
					  <div class="col-sm-12">
					     <div class="col-sm-3">
					     </div>
					     <div class="col-sm-6">
					       <button type="button" class="btn btn-success"
			                          onclick="javascript:search('0','1','20','0')">搜索</button>
			              <button type="button" class="btn btn-primary"
			                          onclick="javascript:search_reset()">重置</button>
					     </div>
					     <div class="col-sm-3">
					     </div>
					  </div>
					  <div class="col-sm-12">
					   <br> 
					  </div>
      </div>
   </div>
	

  <div id="mallListTable"></div>
	
	
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

	<div class="modal fade" id="insertMallModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">商品各项</h4>
				</div>
				<div class="modal-body" style="height:1450px">
					<div class="form-group">
						<input class="form-control" placeholder="请输入商品名" id="ins_title"
							value="">
						<input type="hidden" value="" id="hiddenValue">
						<input type="hidden" value="" id="hiddenValueId">
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
					    <b>选择品牌</b><select class="form-control"  id="ins_probrand"></select>
					    
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
						  <b>选择车型</b><!-- <button type="button" class="btn btn-success btn-xs" onclick="javascript:set_carmodel()">刷新</button> --><select class="form-control" size="12"  id="ins_model" multiple="multiple" 
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
					     <b>设置风格</b><select class="form-control"  id="ins_style" multiple="multiple"
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
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:insertMallConfirm()">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



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
	
	<!-- usermanage.js -->
	<script src="../js/mallmanage/malllist.js"></script>

</body>


</html>