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
			<h1 class="page-header">精选管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<!--  -->
	<%-- <div class="row col-lg-2" style="margin-bottom: 5px;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success"
					onclick="javascript:addNewBoxPage()">增加手册分享</button>
				<!-- data-toggle="modal"
					data-target="#updatescoreModel" -->
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加手册分享</button>
			</c:otherwise>
		</c:choose>
	</div> --%>


	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">精选列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','1')">
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
								<tr>
									<th>score<span>&nbsp</span>
									<c:choose>
									  <c:when test="${order== 0}">
									     <a  class="down" href="javascript:changeTriangle('/news/recommendnewslist','${sessionScope.user.user_guid}','1','1')" style="text-decoration:none;">▼</a>
									     <input id="triangleHidden" type="hidden" value="0"/>
									  </c:when>
									  <c:otherwise>
									     <a id="triangle" class="up" href="javascript:changeTriangle('/news/recommendnewslist','${sessionScope.user.user_guid}','1','0')"   style="text-decoration:none;">▲</a>
									     <input id="triangleHidden" type="hidden" value="1"/>
									  </c:otherwise>
									</c:choose>
									</th>
									<th>nid</th>
									<th>userid</th>
									<th>keywords</th>
									<th>labels</th>
									<th>发布时间</th>
									<th>更新时间</th>
									<th>picurl</th>
									<th>type</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${recommendNewsModels}" var="newsMessages"
									varStatus="ids">
									<tr class="${newsMessages.nid}">
										<td>${newsMessages.score}</td>
										<td>${newsMessages.nid}</td>
										<td>${newsMessages.userid}</td>
										<td>${newsMessages.keywords}</td>
										<td>${newsMessages.labels }</td>
										<td>${newsMessages.ptimeString }</td>
										<td>${newsMessages.updatetimeString }</td>
										<td><img src='${newsMessages.picurl}!thumb'
											style='width: 100px; height: 100px;' /></td>
										<c:choose>
										 <c:when test="${newsMessages.type =='0' }">
										     <td>图文</td>
										  </c:when>
										  <c:when test="${newsMessages.type =='1' }">
										     <td>视频</td>
										  </c:when>
										  <c:when test="${newsMessages.type =='2' }">
										     <td>线路 </td>
										  </c:when>										  
										  <c:when test="${newsMessages.type =='3' }">
										     <td>路书</td>
										  </c:when>
										  <c:when test="${newsMessages.type =='4' }">
										     <td>故事</td>
										  </c:when>
										   <c:when test="${newsMessages.type =='5' }">
										     <td>motogp</td>
										  </c:when>
										  <c:when test="${newsMessages.type =='6' }">
										     <td>骑行 </td>
										  </c:when>
										  <c:when test="${newsMessages.type =='7' }">
										     <td>成就</td>
										  </c:when>
										  <c:when test="${newsMessages.type =='8' }">
										     <td>box</td>
										  </c:when>
										   <c:when test="${newsMessages.type =='9' }">
										     <td>discuss</td>
										  </c:when>
										  <c:otherwise>
										  <td>未知</td>
										  </c:otherwise>
										</c:choose>
										
										<td class="center">
											<button type="button" class="btn btn-success"
												onclick="javascript:selnews('${newsMessages.nid}')">查看动态</button>
											<%-- <c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#updatescoreModel"
														onclick="javascript:updateScore('${newsMessages.nid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														data-toggle="modal" data-target="#delBoxModel"
														onclick="javascript:delRecommenddiag('${newsMessages.nid}','${newsMessages.score }')">删除</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">删除</button>
												</c:otherwise>
											</c:choose> --%>
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
				<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/news/recommendnewslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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
<!-- 	<script>
		$ (document).ready (function ()
        {
	        $ ('#dataTables-example').DataTable (
	        {
	            pageLength : 20,//首次加载的数据条数
	            processing : true,//载入数据的时候是否显示“载入中”
	            lengthMenu : [
	                    [
	                            20, 50, 100, -1
	                    ], [
	                            20, 50, 100, "All"
	                    ]
	            ],
	            order : [
		            [
		                    0, "desc"
		            ]
	            ]
	        });
	        
        });
	</script> -->
	

	<!-- Modal -->
	<div class="modal fade" id="delBoxModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除精选</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delNewNid">
					<input type="text" value="" style="display: none" id="delNewScore">
					<h3>删除后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delRecommend()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="updatescoreModel" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改权重</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="" id="update_nid"
							value="" readonly="readonly">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="" id="update_score"
							value="">
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveRecommend()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script src="../js/news/recommend.js"></script>
</body>


</html>