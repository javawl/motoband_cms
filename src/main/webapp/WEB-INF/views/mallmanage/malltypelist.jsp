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
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	var limit= $("#pageSizeSelect").val();
	var name= $("#search_input").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&name="+name);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var limit= $("#pageSizeSelect").val();
		var order= $("#triangleHidden").val();
		if(order==''||order == null){
			order=0;
		}
		var orderConditions= $("#triangleHiddenValue").val();
		var name= $("#search_input").val();
		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions+"&name="+name);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
/* function changeTriangle(url, adminGuid,page,order,orderConditions){

	var limit= $("#pageSizeSelect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions);


} */
</script>
</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">二级分类管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
   
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">二级分类列表&nbsp<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#instypeModel"  onclick="javascript:instype()">添加二级分类</button></div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1')">
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
					<div class="col-sm-3">
					     <div class="col-sm-10">
					     <input class="form-control" placeholder="请输入二级分类名" id="search_input" value="${name }">
					    </div>
					    <div class="col-sm-2">
					    <button type="button" class="btn btn-success"  onclick="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1')">搜索</button>
					    </div>
					</div>
						
						
                         <div style="display:none;">
                             <input id="triangleHidden" type="hidden" value="${order }"/>
						     <input id="triangleHiddenValue" type="hidden" value="${orderConditions }"/>
                         </div>
						
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
								<%-- <th>orderindex<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='orderindex'}">
									     <a class="down" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','1','orderindex')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='orderindex'}">
									     <a class="up" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','0','orderindex')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','1','orderindex')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>bid<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='bid'}">
									     <a class="down" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','1','bid')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='bid'}">
									     <a class="up" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','0','bid')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1','1','bid')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th> --%>
									<th>typeid</th>
									<th>name</th>
									<th>parentName</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${mallTypeList}" var="list"
									varStatus="ids">
									<tr id="${list.typeid}">
									   <td >${list.typeid}</td>
									   <td >${list.name}</td>
									  <td>${list.parentName}</td>
								
								  
										<td class="center">
										        暂无操作
											<%-- <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editparenttypeModel" onclick="javascript:editparenttype('${list.typeid}')">编辑</button> --%>
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
				<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5"/>&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/mallmanage/malltypelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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

	
	
	
	<div class="modal fade" id="deletebox" tabindex="-1" role="dialog"
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
	
	
	<div class="modal fade" id="instypeModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加二级级分类</h4>
				</div>
				<div class="modal-body" >
				 <div class="form-group">
					        <div class="col-sm-3"><b>一级分类:</b></div>
					       <c:forEach items="${mallParentTypeList}" var="mallParentType" >
					          <div class="col-sm-3"><input type="radio" name="ins_radio_parenttype" value="${mallParentType.parentid }"  />${mallParentType.name }</div>
					       </c:forEach>
					  </div>
					<div class="form-group">
						<input class="form-control" placeholder="输入二级分类名称" id="ins_name"
							value="" >
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
	 <div class="modal fade" id="editbox" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">box信息</h4>
				</div>
				<div class="modal-body" style="height:220px">
				 <div class="form-group">
					<div class="col-sm-12">
					    <label>bid</label>
						<input class="form-control" placeholder="" id="update_bid"
							value="" readonly="readonly">
							<input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
							<input id="hiddenValue" type="hidden" value="">
					</div>
				</div>
				<div class="form-group">
						<div class="col-sm-6" >
						<label>boxid</label>
							<select class="form-control" id="update_boxid">
								
							</select>
						</div>
				</div>
				<div class="form-group">
						<div class="col-sm-6" >
						<label>pid</label>
							<select class="form-control" id="update_pid">
								
							</select>
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
			
	<script src="../js/mallmanage/malltypelist.js"></script> 

</body>


</html>