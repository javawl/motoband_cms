<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="../js/consts.js"></script>

<script>
	var format = function (time, format)
    {
	    var t = new Date (time);
	    var tf = function (i)
	    {
		    return (i < 10 ? '0' : '') + i
	    };
	    return format.replace (/yyyy|MM|dd|HH|mm|ss/g, function (a)
	    {
		    switch (a)
		    {
			    case 'yyyy':
				    return tf (t.getFullYear ());
				    break;
			    case 'MM':
				    return tf (t.getMonth () + 1);
				    break;
			    case 'mm':
				    return tf (t.getMinutes ());
				    break;
			    case 'dd':
				    return tf (t.getDate ());
				    break;
			    case 'HH':
				    return tf (t.getHours ());
				    break;
			    case 'ss':
				    return tf (t.getSeconds ());
				    break;
		    }
	    })
    }
</script>
<script type="text/javascript">

function pageGoto(url, adminGuid,page){ 
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var limit= $("#pageSizeSelect").val();
		var order= $("#triangleHidden").val();

		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order){

	var limit= $("#pageSizeSelect").val();

	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order);


}
</script>
</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">活动管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
    <button type="button" class="btn btn-success" 
		 onclick="javascript:updateactivityversion('activityversion')">更新活动版本</button>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">活动列表&nbsp<button type="button" class="btn btn-success" data-toggle="modal"
		data-target="#addactivity"  onclick="javascript:addactivity()">添加活动</button></div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','1')">
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
						
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr><th>orderindex<span>&nbsp</span>
									<c:choose>
									  <c:when test="${order== 0}">
									     <a  class="down" href="javascript:changeTriangle('/activitymanage/activitylist','${sessionScope.user.user_guid}','1','1')" style="text-decoration:none;">▼</a>
									     <input id="triangleHidden" type="hidden" value="0"/>
									  </c:when>
									  <c:otherwise>
									     <a id="triangle" class="up" href="javascript:changeTriangle('/activitymanage/activitylist','${sessionScope.user.user_guid}','1','0')"   style="text-decoration:none;">▲</a>
									     <input id="triangleHidden" type="hidden" value="1"/>
									  </c:otherwise>
									</c:choose>
								</th>
									<th>aid</th>
									<th>title</th>
									<th>subtitle</th>
									<th>picurl</th>
									<th>type</th>
									<th>keyword</th>
									<th>gpid</th>
									<th>runid</th>
									<th>boxid</th>
									<th>mallurl</th>
									<th>url</th>
									<th>pid</th>
									<th>state</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${activitylist}" var="list"
									varStatus="ids">
									<tr >
									   <td >${list.orderindex}</td>
									   <td >${list.aid }</td>
									   <td >${list.title}</td>
									   <td >${list.subtitle}</td>
									   <td ><img alt="" src="${list.picurl}!thumb" style="width: 100px;height: 100px"></td>
									   <c:choose>
									     <c:when test="${list.type== 0}">
									       <td>普通链接 </td>
									     </c:when>
									     <c:when test="${list.type== 1}">
									       <td>骑行学院</td>
									     </c:when>
									     <c:when test="${list.type== 2}">
									       <td>具体话题</td>
									     </c:when>
									     <c:when test="${list.type== 3}">
									       <td>话题列表</td>
									     </c:when>
									     <c:when test="${list.type== 4}">
									       <td>具体GP</td>
									     </c:when>
									     <c:when test="${list.type== 5}">
									       <td>GP列表 </td>
									     </c:when>
									     <c:when test="${list.type== 6}">
									       <td>具体约跑 </td>
									     </c:when>
									     <c:when test="${list.type== 7}">
									       <td>约跑大厅 </td>
									     </c:when>
									     <c:when test="${list.type== 8}">
									       <td>商城 </td>
									     </c:when>
									     <c:when test="${list.type== 9}">
									       <td>保险 </td>
									     </c:when>
									     <c:when test="${list.type== 10}">
									       <td>手册</td>
									     </c:when>
									   </c:choose>
							
										
										<td >${list.keyword}</td>
										<c:choose>
										  <c:when test="${list.gpid >0}">
										    <td >${list.gpid }</td>
										  </c:when>
										  <c:otherwise>
										    <td ></td>
										  </c:otherwise>
										</c:choose>
										
										<td >${list.runid}</td>
										<c:choose>
										  <c:when test="${list.boxid >0}">
										    <td >${list.boxid }</td>
										  </c:when>
										  <c:otherwise>
										    <td ></td>
										  </c:otherwise>
										</c:choose>
										<td >${list.mallurl}</td>
										<td >${list.url}</td>
										<td >${list.pid}</td>
									<c:choose>
										 <c:when test="${list.state =='1'}">
											<td class="center">上线</td>
										</c:when>
									    <c:when  test="${list.state =='0'}">
											<td class="center">未上线</td>
										</c:when>
								  </c:choose>
										<td class="center">
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#editactivity" onclick="javascript:editactivity('${list.aid}')">编辑</button>
											<%-- <button type="button" class="btn btn-success" data-toggle="modal" data-target="#deletead" onclick="javascript:deletead('${list.adid}')">删除</button> --%>
										</td> 
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					
					
			<div class="pagination">
			
			<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
			<c:if test="${pageBean.page != 1 }">
				<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			     
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5"/>&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/activitymanage/activitylist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
	</div>
					
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->


	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

	
	
	
	<div class="modal fade" id="deleteactivity" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除广告</h4>
				</div>
				<div class="modal-body">
				   
					<input type="text" value="" style="display: none" id="delAid"/>
					
					<h3>删除后无法恢复</h3>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delConfirm()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	
	<div class="modal fade" id="addactivity" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加活动</h4>
				</div>
				<div class="modal-body" style="height:360px">
					 <div class="form-group">
					<div class="col-sm-6">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6" style="padding-bottom: 10px">
					    <label>title</label>
						<input class="form-control" placeholder="" id="ins_title"
							value="" >
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6" style="padding-bottom: 10px">
					    <label>subtitle</label>
						<input class="form-control" placeholder="" id="ins_subtitle"
							value="" >
					</div>
				</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>选择类型：</label>
					    <select class="form-control" id="SelectTypeadd"  onchange="javascript:SelectTypeChangeadd(this)">
					       <option value="0">普通外链 </option>
					       <option value="1">骑行学院 </option>
					       <option value="2">具体话题  </option>
					       <option value="3">话题列表</option>
					       <option value="4">具体GP </option>
					       <option value="5">GP列表 </option>
					       <option value="6">具体约跑</option>
					       <option value="7">约跑大厅 </option>
					       <option value="8">商城 </option>
					       <option value="9">保险 </option>
					       <option value="10">手册</option>
					    </select>
					    <div>
							<div id="divadd0">请输入url：<input class="form-control" placeholder="" id="inputUrl_urladd" ></div>
							<div id="divadd1" style="display:none;">请选择pid：<select class="form-control" id="inputUrl_pidadd" ></select></div>
							<div id="divadd2" style="display:none;">请选择keyword：<select class="form-control" id="inputUrl_keywordadd" ></select></div>
							<div id="divadd3" style="display:none;"></div>
							<div id="divadd4" style="display:none;">请选择gp：<select class="form-control" id="inputUrl_gpidadd" ></select></div>
							<div id="divadd5" style="display:none;"></div>
							<div id="divadd6" style="display:none;">请输入runid：<input class="form-control" placeholder="" id="inputUrl_runidadd" ></div>
							<div id="divadd7" style="display:none;"></div>
							<div id="divadd8" style="display:none;">请输入mallurl：<input class="form-control" placeholder="" id="inputUrl_mallurladd" ></div>
							<div id="divadd9" style="display:none;"></div>
							<div id="divadd10" style="display:none;">请选择boxid：<select class="form-control" id="inputUrl_boxidadd" ></select></div>
					    </div>
					   
					</div>
					</div>
					
					 <div class="form-group">
						<input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"> <input
							type="text" value="" id="titleimgadd"> <img
							id="titleimgshowadd" src="" style="width: 144px; height: 144px" />
							
					</div>
					<div class="form-group">
						<div class="col-sm-6" style="margin-top: 25px">
							<select class="form-control" id="ins_state">
								<option value='0'>未上线</option>
								<option value='1'>上线</option>
							</select>
						</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>orderindex</label>
						<input class="form-control" placeholder="" id="ins_orderindex"
							value="" >
					</div>
				</div>
				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:addConfirm()">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal -->
	 <div class="modal fade" id="editactivity" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">活动信息</h4>
				</div>
				<div class="modal-body" style="height:420px">
				  <div class="form-group">
					<div class="col-sm-6">
					    <label>aid</label>
						<input class="form-control" placeholder="" id="update_aid"
							value="" readonly="readonly">
							<input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
							<input id="hiddenValue" type="hidden" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6" style="padding-bottom: 10px">
					    <label>title</label>
						<input class="form-control" placeholder="" id="update_title"
							value="" >
					</div>
				</div>
				
				
					<div class="form-group">
					<div class="col-sm-6">
					    <label>选择类型：</label>
					    <select class="form-control" id="SelectType"  onchange="javascript:SelectTypeChange(this)">
					       <option value="0">普通外链 </option>
					       <option value="1">骑行学院 </option>
					       <option value="2">具体话题  </option>
					       <option value="3">话题列表</option>
					       <option value="4">具体GP </option>
					       <option value="5">GP列表 </option>
					       <option value="6">具体约跑</option>
					       <option value="7">约跑大厅 </option>
					       <option value="8">商城 </option>
					       <option value="9">保险 </option>
					       <option value="10">手册</option>
					    </select>
					    <div>
							<div id="div0">请输入url：<input class="form-control" placeholder="" id="inputUrl_url" ></div>
							<div id="div1" style="display:none;">请选择pid：<select class="form-control" id="inputUrl_pid" ></select></div>
							<div id="div2" style="display:none;">请选择keyword：<select class="form-control" id="inputUrl_keyword" ></select></div>
							<div id="div3" style="display:none;"></div>
							<div id="div4" style="display:none;">请选择gp：<select class="form-control" id="inputUrl_gpid" ></select></div>
							<div id="div5" style="display:none;"></div>
							<div id="div6" style="display:none;">请输入runid：<input class="form-control" placeholder="" id="inputUrl_runid" ></div>
							<div id="div7" style="display:none;"></div>
							<div id="div8" style="display:none;">请输入mallurl：<input class="form-control" placeholder="" id="inputUrl_mallurl" ></div>
							<div id="div9" style="display:none;"></div>
							<div id="div10" style="display:none;">请选择boxid：<select class="form-control" id="inputUrl_boxid" ></select></div>
					    </div>
					   
					</div>
					</div>
					
					 <div class="form-group">
						<input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"> <input
							type="text" value="" id="titleimg"> <img
							id="titleimgshow" src="" style="width: 144px; height: 144px" />
							
					</div>
					<div class="form-group">
					<div class="col-sm-6" >
					    <label>subtitle</label>
						<input class="form-control" placeholder="" id="update_subtitle"
							value="" >
					</div>
				   </div>
					<div class="form-group">
						<div class="col-sm-6" style="margin-top: 25px">
							<select class="form-control" id="update_state">
								<option value='0'>未上线</option>
								<option value='1'>上线</option>
							</select>
						</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>orderindex</label>
						<input class="form-control" placeholder="" id="update_orderindex"
							value="" >
					</div>
				</div>
				
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:editConfirm()">确认修改</button>
				</div>
			
			</div>
			
		</div>
		
	</div>
		
		<div class="modal fade" id="titleImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closetitleImgModel();">&times;</button>
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
					<!-- <form id="XX_uploadForm" style="float: left">
								<input type="file" name="XX_FileContent" class="btn btn-info"
									id="inputfile" style="float: left"></input> <input id="subbtn"
									type="submit" style="float: left" class="btn btn-info"
									style="margin-left: 0;">
							</form> -->
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel" onclick="">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
		
	<script src="../js/activitymanage/activitylist.js"></script> 
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