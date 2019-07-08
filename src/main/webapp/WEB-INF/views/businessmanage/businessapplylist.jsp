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
	var state= $("#stateSelect").val();
	if(order==''||order == null){
		order=0;
	}	
	var orderConditions= $("#triangleHiddenValue").val();
  
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&state="+state);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var order= $("#triangleHidden").val();
		var limit= $("#pageSizeSelect").val();
		var state= $("#stateSelect").val();
		if(order==''||order == null){
			order=0;
		}
	
		var orderConditions= $("#triangleHiddenValue").val();
    	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&state="+state);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order,orderConditions){

 	var limit= $("#pageSizeSelect").val();
 	var state= $("#stateSelect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&state="+state);

} 
function changestate(url, adminGuid,page,obj){ 
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	if(order==''||order == null){
		order=0;
	}	
	var orderConditions= $("#triangleHiddenValue").val();
  
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&state="+obj.value);	
}


</script>
</head>

<body >
   <div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">商品申请管理</h1>
		</div>
	</div>

	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">商家申请列表 </div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
			           	<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','1')">
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
							  <label>Choose  state: </label>
							  <select class="form-control" id="stateSelect"  onchange="javascript:changestate('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','1',this)">
										<c:choose>
											<c:when test="${state==-1 }">
											    <option value="-1" selected>全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
										  <c:when test="${state==0 }">
											    <option value="-1" >全部</option>
											    <option value="0" selected>申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
											<c:when test="${state==1 }">
											    <option value="-1" >全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" selected>申请通过</option>
											    <option value="2" >已拒绝</option>
											</c:when>
											<c:when test="${state==2 }">
											    <option value="-1" >全部</option>
											    <option value="0" >申请中</option>
											    <option value="1" >申请通过</option>
											    <option value="2" selected>已拒绝</option>
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
							  <input id="stateHidden" type="hidden" value="${state }"/>
							  
                         </div>
                         
						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>aid</th>
									<th>商家名称</th>
									<th>联系人姓名</th>
									<th>联系电话</th>
									<th>商家类型</th>
								<!-- 	<th>主营品牌</th> -->
									<th>地址</th>
									<th>面积</th>
									<th>员工数</th>
									<th>从业年限</th>
									<!-- <th>精修品牌</th> -->
									<th>申请时间</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${businessapplys}" var="apply" varStatus="ids">
									<tr class="${apply.aid}">
									    <td>${apply.aid}</td>
										<td>${apply.name}</td>
										<td>${apply.contactname}</td>
										<td>${apply.contactphone}</td>
										<td>${apply.type}</td>
										<%-- <td>${apply.mainbrand}</td> --%>
										<td>${apply.address}</td>
										<td>${apply.businessarea}</td>
										<td>${apply.staffcount}</td>
										<td>${apply.age}</td>
									<%-- 	<td>${apply.recommendbrand}</td> --%>
										<td>${apply.addtimeString}</td>
										<td class="center">
										    <button type="button" class="btn btn-primary btn-lg"
														onclick="javascript:lookApplyNewPage('${apply.aid}')">查看详情</button>
										    <c:choose>
										        <c:when test="${apply.state==0}">
										           <button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#changestateModel"
														onclick="javascript:updateapplystate('${apply.aid}','${apply.state}')">审核</button>
										        </c:when>
										        <c:otherwise>
										            <button type="button" class="btn btn-primary btn-lg"
														disabled="disabled">审核</button>
										        </c:otherwise>
										    </c:choose>
													
														
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
				<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/businessmanage/businessapplylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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
					  <!--  <b>营业执照</b><img id="look_license" src=""  height="100px"/> -->
				        <div>
							<input id="" type="button" value="设置图片" class="btn btn-info"
								data-toggle="modal" data-target="#titleImgModel">
						</div>
						<div>
							<input type="text" value="" id="titleimg"> <img
								id="titleimgshow" src=""
								style="width: 144px; height: 144px" />
						</div>
					</div> 
                    <div class="form-group">
					    <b>其他</b><input class="form-control" placeholder="other" id="look_other"
							value="" >
					</div>
                    <div class="form-group">
					    <b>状态</b><input class="form-control" placeholder="state" id="look_state"
							value=""  readonly="readonly">
					</div>
                    <div class="form-group">
					    <b>驳回原因</b><input class="form-control" placeholder="reason" id="look_reason"
							value=""  readonly="readonly" >
					</div>
                    <div class="form-group">
					   <b>省</b><input class="form-control" placeholder="province" id="look_province"
							value="" >
					</div>
                    <div class="form-group">
					    <b>市</b><input class="form-control" placeholder="city" id="look_city"
							value="" >
					</div>
                    <div class="form-group">
					    <b>申请时间</b><input class="form-control" placeholder="addtime" id="look_addtime"
							value="" readonly="readonly" >
					</div>
                    <div class="form-group">
					    <div><b>服务能力</b></div><!-- <input class="form-control" placeholder="businessserviceliststr" id="look_businessserviceliststr"
							value="" > -->
							<c:forEach items="${businessServiceModels}" var="service" >
					            <div class="col-sm-3"><input type="checkbox" name="checkbox_businessservice" value="${service.bsid }" />${service.name }</div> 
					         </c:forEach>
					</div>
					<div style="clear:both;"></div>
                    <div class="form-group">
					    <b>经度</b><input class="form-control" placeholder="longitude" id="look_longitude"
							value="" >
					</div>
                    <div class="form-group">
					    <b>纬度</b><input class="form-control" placeholder="latitude" id="look_latitude"
							value="" >
					</div>
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<!-- <button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:lookapplyConfirm()" id="savebtn">保存编辑</button> -->
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>	
			
	
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
	
	
	<!-- DataTables JavaScript -->
	<script src="../js/news/dataversion.js"></script>
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
    <script src="../js/consts.js"></script>
	<script src="../js/businessmanage/businessapplylist.js"></script>
	

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