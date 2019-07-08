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
<script type="text/javascript">
function pageGoto(url, adminGuid,page){  
	var order= $("#triangleHidden").val();
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	var limit= $("#pageSizeSelect").val();
	var nowtypeid=$("#typeselect").val();
	var nowbrandid=$("#brandselect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&nowtypeid="+nowtypeid+"&nowbrandid="+nowbrandid+"&order="+order+"&orderConditions="+orderConditions);

}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var order= $("#triangleHidden").val();
		if(order==''||order == null){
			order=0;
		}
		var orderConditions= $("#triangleHiddenValue").val();
		var limit= $("#pageSizeSelect").val();
		var nowtypeid=$("#typeselect").val();
		var nowbrandid=$("#brandselect").val();
		$ ("#page-wrapper").load (url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&nowtypeid="+nowtypeid+"&nowbrandid="+nowbrandid+"&order="+order+"&orderConditions="+orderConditions);

	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order,orderConditions){

	var limit= $("#pageSizeSelect").val();
	var nowtypeid=$("#typeselect").val();
	var nowbrandid=$("#brandselect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&nowtypeid="+nowtypeid+"&nowbrandid="+nowbrandid+"&order="+order+"&orderConditions="+orderConditions);

}

</script>

</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12" style="height:">
			<h1 class="page-header">车辆管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<div class="row" style="margin-bottom: 10px; padding-left: 2%;">
	  <c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertBrandModel" onclick="javascript:insertbrandbp()">增加小品牌</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加小品牌</button>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-info" data-toggle="modal"
					data-target="#insertTypeModel">增加摩托车风格（类型）</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-info disabled">增加摩托车风格（类型）</button>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success" data-toggle="modal"
					data-target="#insertCarModel">增加摩托车车型</button>
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加摩托车车型</button>
			</c:otherwise>
		</c:choose>
		
		
		
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">车辆列表&nbsp<button type="button" class="btn btn-success"
					 onclick="javascript:updateversion('motomodel','${sessionScope.user.user_guid}')">更新车辆版本</button></div>

				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="col-sm-12"
							style="padding-left: 0; margin-bottom: 15px">
							<div id="" class="col-sm-6" style="padding-left:0px">
								<label>Search by type: </label> <select class="form-control"
									 id='typeselect'
									onchange="javascript:typechanged()">
									<c:forEach items="${mototypelist}" var="mototype"
										varStatus="ids">
										<c:choose>
											<c:when test="${mototype.typeid==nowtypeid }">
												<option value="${mototype.typeid}" selected>${mototype.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${mototype.typeid}">${mototype.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									
									<option value="-1" <c:if test="${nowtypeid=='-1'}">selected</c:if>>全部</option>
									
								</select> 
								</div>
								<div class="col-sm-6" style="padding-left:0px"><label>Search by brand: </label> <select class="form-control"
									id='brandselect' onchange="javascript:brandchanged()">
									<c:forEach items="${motobrandlist}" var="motobrand"
										varStatus="ids">
										<c:choose>
											<c:when test="${motobrand.brandid==nowbrandid }">
												<option value="${motobrand.brandid}" selected>${motobrand.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${motobrand.brandid}">${motobrand.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<option value="-1" <c:if test="${nowbrandid=='-1'}">selected</c:if>>全部</option>
								</select>
							</div>
							<div class="col-sm-6" style="padding-left:0px">
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','1')">
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


                         <div style="display:none;">
                             <input id="triangleHidden" type="hidden" value="${order }"/>
						     <input id="triangleHiddenValue" type="hidden" value="${orderConditions }"/>
                         </div>

						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
								<th>carid<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='modelid'}">
									     <a class="down" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','1','modelid')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='modelid'}">
									     <a class="up" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','0','modelid')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','1','modelid')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
		
								</th>
									<th>carname<span>&nbsp</span>
									<c:choose>
									  <c:when test="${order== '0'  && orderConditions=='name'}">
									     <a class="down" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','1','name')" style="text-decoration:none;">▼</a>
									  </c:when>
									  <c:when test="${order== '1' && orderConditions=='name'}">
									   <a class="up" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','0','name')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/carmanage/carlist','${sessionScope.user.user_guid}','1','1','name')"   style="text-decoration:none;">◊</a>
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>carcc</th>
									<th>cartype</th>
									<th>carbrand</th>
									<th>carbrandparent</th>
									<th>state</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${carlists}" var="CarMessages" varStatus="ids">
									<tr class="${CarMessages.modelid}">
									<td>${CarMessages.modelid}</td>
										<td>${CarMessages.name}</td>
										<td>${CarMessages.cc}</td>
										<td>${CarMessages.typename}</td>
										<td class="center">${CarMessages.brandname}</td>
										<td class="center">${CarMessages.brandparentname}</td>
										<c:choose>
										  <c:when test="${CarMessages.state=='0'}">
										      <td>上架</td>
										  </c:when>
										  <c:when test="${CarMessages.state=='1'}">
										      <td>下架</td>
										  </c:when>
										</c:choose>
										<td class="center"><c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														onclick="javascript:selCarMessage('${CarMessages.modelid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-danger btn-lg"
														data-toggle="modal" data-target="#delCarModel"
														onclick="javascript:delCarGuid('${CarMessages.modelid}')">删除</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-danger disabled btn-lg">删除</button>
												</c:otherwise>
											</c:choose></td>

									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
					<div class="pagination">
			
			<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
			<c:if test="${pageBean.page != 1 }">
				<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			     
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/carmanage/carlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<!-- <script>
		$ (document).ready (function ()
        {
	        $ ('#dataTables-example').DataTable (
	        {
		        responsive : true
	        });
        });
	</script> -->


	<!-- Modal -->
	<div class="modal fade" id="delCarModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除车辆</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delCarGuid">
					<h3>删除车辆信息后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delCarMessage()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertBrandModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加小品牌</h4>
				</div>
				<div class="modal-body">
					<input class="form-control" placeholder="输入小品牌名称"
							id="newcarbrand" value="" onblur="javascript:checkCarBrand();">
							<p class="text-danger" id="dangerbrandtext" style="display: none">品牌名已被使用！</p>
							<label>请选择大品牌</label>
					        <select class="form-control" id="newcarbrandparentname"></select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:insertNewCarBrand()" id="saveBrandbtn">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertTypeModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">添加摩托车风格（类型）</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="输入摩托车风格（类型）"
							id="newcartype" value="" onblur="javascript:checkCarType();">
							<p class="text-danger" id="dangertypetext" style="display: none">类型名已被使用！</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="closeinsertCarModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:insertNewCarType()" id="saveTypebtn">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertCarModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新车辆</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="请输入新车辆名" id="newcarname"
							value="" onblur="javascript:checkCarName()">
							<p class="text-danger" id="dangertext" style="display: none">车辆名已被使用！</p>
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="请输入排量，必须是数字"
							id="newcarcc" value="">
					</div>
					<div class="form-group">
					     <label>请选择风格（类型）</label>
						 <select class="form-control"
									 id='newCar_Type'>
									<c:forEach items="${mototypelist}" var="mototype"
										varStatus="ids">
												<option value="${mototype.typeid}">${mototype.name}</option>
									</c:forEach>
									<option value="0" selected>未知</option>
								</select> 

					</div>
					<div class="form-group">
					<label>请选择小品牌</label>
						<select class="form-control"
									id='newCar_Brand' >
									<c:forEach items="${motobrandlist}" var="motobrand"
										varStatus="ids">									
												<option value="${motobrand.brandid}">${motobrand.name}</option>								
									</c:forEach>
								</select>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal" id="closeinsertCarModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveNewCar()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- usermanage.js -->
	<script src="../js/carmanage/carmanage.js"></script>
	
</body>


</html>