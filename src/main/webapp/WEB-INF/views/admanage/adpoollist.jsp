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

function pageGoto(url, adminGuid,page){ 
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	var adtype= $("#poolSelect").val();
	if(order==''||order == null){
		order=0;
	}	
	var orderConditions= $("#triangleHiddenValue").val();
  
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&adtype="+adtype);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var order= $("#triangleHidden").val();
		var limit= $("#pageSizeSelect").val();
		var adtype= $("#poolSelect").val();
		if(order==''||order == null){
			order=0;
		}
	
		var orderConditions= $("#triangleHiddenValue").val();
    	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&adtype="+adtype);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order,orderConditions){

 	var limit= $("#pageSizeSelect").val();
 	var adtype= $("#poolSelect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&adtype="+adtype);

} 



</script>
</head>

<body >
   <div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">广告池管理</h1>
		</div>
	</div>

	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">广告池广告列表 
				&nbsp&nbsp<button type="button" class="btn btn-info"   data-toggle="modal" data-target="#editAdpoolimgModel"  onclick="javascript:addAdpoolimg()">添加广告</button>
				&nbsp&nbsp<button type="button" class="btn btn-info" onclick="javascript:updateVersion('adpoolversion')">更新广告池版本</button>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
			           	<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','1')">
							    <c:forEach items="${limitList}" var="optionlimit" varStatus="ids">
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
						<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  pool: </label>
							  <select class="form-control" id="poolSelect"  onchange="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','1')">
									<c:choose>
									   <c:when test="${adtype==0}">
									     <option value="0" selected>首页广告</option>
									     <option value="1" >关注广告</option>
									     <option value="2" >首页搜索广告</option>
									     <option value="3" >用车搜索广告</option>
									     <option value="4" >工具广告</option>
									   </c:when>
									   <c:when test="${adtype==1}">
									     <option value="0" >首页广告</option>
									     <option value="1" selected>关注广告</option>
									     <option value="2" >首页搜索广告</option>
									     <option value="3" >用车搜索广告</option>
									     <option value="4" >工具广告</option>
									   </c:when>
									   <c:when test="${adtype==2}">
									     <option value="0" >首页广告</option>
									     <option value="1" >关注广告</option>
									     <option value="2" selected>首页搜索广告</option>
									     <option value="3" >用车搜索广告</option>
									     <option value="4" >工具广告</option>
									   </c:when>
									   <c:when test="${adtype==3}">
									     <option value="0" >首页广告</option>
									     <option value="1" >关注广告</option>
									     <option value="2" >首页搜索广告</option>
									     <option value="3" selected>用车搜索广告</option>
									     <option value="4" >工具广告</option>
									   </c:when>
									   <c:when test="${adtype==4}">
									     <option value="0" >首页广告</option>
									     <option value="1" >关注广告</option>
									     <option value="2" >首页搜索广告</option>
									     <option value="3" >用车搜索广告</option>
									     <option value="4" selected>工具广告</option>
									   </c:when>
									
									</c:choose>
							  </select>
						</div> 
					</div>
					
                         <div style="display:none;">
                             <input id="triangleHidden" type="hidden" value="${order }"/>
						     <input id="triangleHiddenValue" type="hidden" value="${orderConditions }"/>
						     <input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
							 <input id="hiddenValue" type="hidden" value="">
							  <input id="adtypeHidden" type="hidden" value="${adtype }"/>
							  
                         </div>
                         
						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>adpoolimgid</th>
									<th>adtype</th>
									<th>adtitle</th>
									<th>price</th>
									<th>linktype</th>
									<th>imgurl</th>
									<th>linkurl</th>
									<th>gpid</th>
									<th>keyword</th>
									<th>mallurl</th>
									<th>boxid</th>
									<th>secondcarid</th>
									<th>orderindex</th>
									<th>state</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${adPoolImgModels}" var="adpoolimgs" varStatus="ids">
									<tr class="${adpoolimgs.adpoolimgid}">
									    <td>${adpoolimgs.adpoolimgid}</td>
									    <c:choose>
									     <c:when test="${adpoolimgs.adtype==0}"><td>首页广告</td></c:when>
									     <c:when test="${adpoolimgs.adtype==1}"><td>关注广告</td></c:when>
									     <c:when test="${adpoolimgs.adtype==2}"><td>首页搜索广告</td></c:when>
									     <c:when test="${adpoolimgs.adtype==3}"><td>用车搜索广告</td></c:when>
									     <c:when test="${adpoolimgs.adtype==4}"><td>工具广告</td></c:when>
									     <c:otherwise><td>未知</td></c:otherwise>
									    </c:choose>
									    <td>${adpoolimgs.adtitle}</td>
									    <td>${adpoolimgs.price}</td>
									    <c:choose>
									       <c:when test="${adpoolimgs.linktype==0}">
									          <td>普通外链</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==1}">
									          <td>活动（MotobandGP）</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==2}">
									          <td>话题页面</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==3}">
									          <td>商品详细页</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==4}">
									          <td>保险页面</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==5}">
									          <td>贷款页面 </td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==6}">
									          <td>文章详细页面</td>
									       </c:when>
									       <c:when test="${adpoolimgs.linktype==7}">
									          <td>二手车详情页</td>
									       </c:when>
									       <c:otherwise>
									          <td>未知</td>
									       </c:otherwise>
									    </c:choose>
									    <td><img alt="" src="${adpoolimgs.imgurl }!thumb" width="100px" height="100px"></td>
									    <c:choose>
									       <c:when test="${adpoolimgs.linkurl!=null and adpoolimgs.linkurl!=''}"><td>${adpoolimgs.linkurl}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
									    <c:choose>
									       <c:when test="${adpoolimgs.gpid!=null and adpoolimgs.gpid!=''}"><td>${adpoolimgs.gpid}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
									    <c:choose>
									       <c:when test="${adpoolimgs.keyword!=null and adpoolimgs.keyword!=''}"><td>${adpoolimgs.keyword}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
									    <c:choose>
									       <c:when test="${adpoolimgs.mallurl!=null and adpoolimgs.mallurl!=''}"><td>${adpoolimgs.mallurl}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
									    <c:choose>
									       <c:when test="${adpoolimgs.boxid!=null and adpoolimgs.boxid!=''}"><td>${adpoolimgs.boxid}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
									    <c:choose>
									       <c:when test="${adpoolimgs.secondcarid!=null and adpoolimgs.secondcarid!=''}"><td>${adpoolimgs.secondcarid}</td></c:when>
									       <c:otherwise><td>无</td></c:otherwise>
									    </c:choose>
										<td>${adpoolimgs.orderindex}</td>
										<c:choose>
										   <c:when test="${adpoolimgs.state==0}"><td>下线</td></c:when>
										   <c:when test="${adpoolimgs.state==1}"><td>上线</td></c:when>
										</c:choose>
										<td class="center">
										    <button type="button" class="btn btn-primary btn-lg"
										               data-toggle="modal" data-target="#editAdpoolimgModel"
														onclick="javascript:editAdpoolimg('${adpoolimgs.adpoolimgid}')">编辑</button>
									
											<%-- <button type="button" class="btn btn-primary btn-lg"
												data-toggle="modal" data-target="#delBoxModel"
												onclick="javascript:getTopicNews('${topic.keyword}')">查看动态</button>
											<button type="button" class="btn btn-success"
												onclick="javascript:addBanner('${topic.keyword}','${topictype }')">添加到banner</button>
 --%>
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
				<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <span>...</span>
			      <c:forEach var="i" begin="${pageBean.totalPage-4}" end="${pageBean.totalPage}">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/admanage/adpoollist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
	</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	

	<div class="modal fade" id="editAdpoolimgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">广告详情</h4>
				</div>
				<div class="modal-body" style="height:500px">
				<div class="form-group">
				<div class="col-sm-6">
				<label>标题</label>
				<input class="form-control" placeholder="请输入标题" id="addadtitle"
							value="">	
				</div>
				<div class="col-sm-6">
				<label>价格</label>
				<input class="form-control" placeholder="请输入价格" id="addprice"
							value="">	
				</div>
				</div>
				<div class="form-group">
				<label>简介</label>
				<textarea rows="3" cols="" placeholder="请输入广告简介" id="addaddes" style="width: 100%;"></textarea>
				</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>选择图片类型：</label>
					    <select class="form-control" id="imageSelectType"  onchange="javascript:imgTypeChange(this)">
					       <option value="0">普通外链 </option>
					       <option value="1">活动（MotobandGP）</option>
					       <option value="2">话题页面 </option>
					       <option value="3">商品详细页</option>
					       <option value="4">保险页面</option>
					       <option value="5">贷款页面 </option>
					       <option value="6">文章详细页面</option>
					       <option value="7">二手车详情页面</option>
					    </select>
					    <div>
							<div id="div0">请输入linkurl：<input class="form-control" placeholder="" id="inputUrllinkurl" ></div>
							<div id="div1" style="display:none;">请选择gpid：<select class="form-control" id="inputUrlgpid" ></select></div>
							<div id="div2" style="display:none;">请选择keyword：<select class="form-control" id="inputUrlkeyword" ></select></div>
							<div id="div3" style="display:none;">请输入mallurl：<input class="form-control" placeholder="" id="inputUrlmallurl" ></div>
							<div id="div4" style="display:none;"></div>
							<div id="div5" style="display:none;"></div>
							<div id="div6" style="display:none;">请选择boxid：<select class="form-control" id="inputUrlboxid"></select></div>
							<div id="div7" style="display:none;">请输入secondcarid：<input class="form-control" placeholder="" id="inputUrlsecondcarid" ></div>
							
					    </div>
					    <input type="text" value="" style="display: none" id="addimgsAdid"/>
					</div>
					</div>
					
					 <div class="form-group">
						<input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"> <input
							type="text" value="" id="titleimg"> <img
							id="titleimgshow" src="" style="width: 144px; height: 144px" />
					</div>
					
                   <div class="form-group">
					<div class="col-sm-6">
					    <label>orderindex</label>
						<input class="form-control" placeholder="" id="orderindex"
							value="" >
						<input class="form-control" placeholder="" id="addadpoolimgid"
							value="" type="hidden">	
						<input class="form-control" placeholder="" id="oldadtype"
							value="" type="hidden">	
					</div>
					<div class="col-sm-6" style="margin-top: 25px;">
                        <label>state:</label>
						<input type="radio" class="" placeholder="" name="addstate"
							value="0" >下线
						<input type="radio" class="" placeholder="" name="addstate"
							value="1" >上线
					    </div>
				</div>
				 <div class="form-group">
				 <div class="col-sm-12"  style="margin-top: 15px;">
                        <label>adtype:</label>
						<input type="radio" class="" placeholder="" name="addpool"
							value="0" >首页广告 
						<input type="radio" class="" placeholder="" name="addpool"
							value="1" >关注广告
						<input type="radio" class="" placeholder="" name="addpool"
							value="2" >首页搜索广告
						<input type="radio" class="" placeholder="" name="addpool"
							value="3" >用车搜索广告 
						<input type="radio" class="" placeholder="" name="addpool"
							value="4" >工具广告     
					    
					</div>
				 </div>
				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:editAdpoolimgConfirm()">确认</button>
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
			<!-- 	<form id="uploadForm" style="float: left">
							<input type="file" name="FileContent" class="btn btn-info"
								id="inputfile" style="float: left"></input> <input id="subbtn"
								type="submit" style="float: left" class="btn btn-info"
								style="margin-left: 0;" onclick="javascript:imgsubmit()">
						</form> -->
					
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
	
	
	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
    <script src="../js/consts.js"></script>
	<script src="../js/admanage/adpoollist.js"></script>
	

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
			
            $('#editAdpoolimgModel').modal({backdrop: 'static', keyboard: false, show:false}); 
	</script> 

</body>


</html>