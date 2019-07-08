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

<script src="../js/consts.js"></script>
<script type="text/javascript">

function pageGoto(url, adminGuid,page){ 
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	if(order==''||order == null){
		order=0;
	}	
	var orderConditions= $("#triangleHiddenValue").val();
  //  search(adminGuid,page,20,order);
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var order= $("#triangleHidden").val();
		var limit= $("#pageSizeSelect").val();
		if(order==''||order == null){
			order=0;
		}
	//	 search(adminGuid,inputPage,20,order);
		var orderConditions= $("#triangleHiddenValue").val();
    	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order,orderConditions){

 	var limit= $("#pageSizeSelect").val();

	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions);

} 



</script>
</head>

<body >
	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">商品列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
			           	<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1')">
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
                         
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>商品id</th>
									<th>商品名</th>
									<th>商品图片</th>
									<th>市场价</th>
									<th>优惠价<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='newprice'}">
									     <a class="down" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','1','newprice')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='newprice'}">
									     <a class="up" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','0','newprice')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','1','newprice')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>一级分类</th>
									<th>二级分类</th>
									<th>车型</th>
									<th>商品品牌</th>
									<th>车型价位</th>
									<th>商品状态<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='state'}">
									     <a class="down" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','1','state')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='state'}">
									     <a class="up" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','0','state')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1','1','state')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>操作</th> 
								</tr>
							</thead>
							 <tbody>
								<c:forEach items="${mallproductlist}" var="mallproducts" varStatus="ids">
									<tr id="${mallproducts.id}">
										<td>${mallproducts.id}</td>
										<td>${mallproducts.title}</td>
										<td><img width="100px" height="100px" src="${mallproducts.picurl}"></td>
										<td>${mallproducts.oldprice_show}元</td>
										<td>${mallproducts.newprice_show}元</td>
										<td>${mallproducts.parenttypeName}</td>
										<td>${mallproducts.typeName}</td>
										<td>${mallproducts.model}</td>
										<td>${mallproducts.probrandName}</td>
										<c:choose>
										  <c:when test="${mallproducts.level =='A'}">
										      <td class="center">0~3万</td>
										  </c:when>
										  <c:when  test="${mallproducts.level=='B'}">
										       <td class="center">3~6万</td>
										  </c:when>
										   <c:when test="${mallproducts.level =='C'}">
										      <td class="center">6~10万</td>
										  </c:when>
										  <c:when  test="${mallproducts.level =='D'}">
										       <td class="center">10万以上</td>
										  </c:when>
										</c:choose>
										<c:choose>
										  <c:when test="${mallproducts.state =='1'}">
										      <td class="center">上架</td>
										  </c:when>
										  <c:when  test="${mallproducts.state =='0'}">
										       <td class="center">下架</td>
										  </c:when>
										</c:choose>
										
										
										<td class="center">
										<button type="button" class="btn btn-info" disabled="disabled"
												onclick="javascript:changeState('${mallproducts.id}','${mallproducts.state}')">上/下架</button>
												<button type="button" class="btn btn-success"  disabled="disabled"
												
												onclick="javascript:updateProductNewPage('${mallproducts.id}')">编辑</button>
										<%-- <button type="button" class="btn btn-danger"
												onclick="javascript:delmallproduct('${mallproducts.id}')">删除</button> --%>
										
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
				<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			       
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/mallmanage/mallproductrecommendlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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
    <script src="../js/mallmanage/malllist.js"></script>
    <script src="../js/mallmanage/mallproductlist.js"></script>

</body>


</html>