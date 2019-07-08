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
	var nowtypeid=$("#typeselect").val();
	var title=$("#inputTitle").val();
//	var title1= encodeURI(encodeURI(title)); 
//	console.log(title1);
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&nowtypeid="+nowtypeid+"&order="+order+"&title="+title+"&orderConditions="+orderConditions);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var order= $("#triangleHidden").val();
		var limit= $("#pageSizeSelect").val();
		var nowtypeid=$("#typeselect").val();
		var title=$("#inputTitle").val();
		if(order==''||order == null){
			order=0;
		}
		var orderConditions= $("#triangleHiddenValue").val();
		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&nowtypeid="+nowtypeid+"&order="+order+"&title="+title+"&orderConditions="+orderConditions);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order,orderConditions){

	var limit= $("#pageSizeSelect").val();
	var nowtypeid=$("#typeselect").val();
	var title=$("#inputTitle").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&nowtypeid="+nowtypeid+"&order="+order+"&title="+title+"&orderConditions="+orderConditions);


}

</script>
</head>

<body >

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">手册管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="row col-lg-2" style="margin-bottom: 5px;">
		<c:choose>
			<c:when test="${insCheck=='lock'}">
				<button type="button" class="btn btn-success"
					onclick="javascript:addNewBoxPage()">增加手册分享</button>
				<!-- <button type="button" class="btn btn-success"
					onclick="javascript:addNewBoxPage1()">增加普通手册分享</button> -->
				<!-- data-toggle="modal"
					data-target="#insertBoxModel" -->
			</c:when>
			<c:otherwise>
				<button type="button" class="btn btn-success disabled">增加手册分享</button>
			</c:otherwise>
		</c:choose>
	</div>
	<div class="row col-lg-2" style="margin-bottom: 5px;">
		<button type="button" class="btn btn-success"
			onclick="javascript:imgSourseManage()">图片素材管理</button>
		<!-- data-toggle="modal"
					data-target="#insertBoxModel" -->

	</div>
	<div class="row col-lg-2" style="margin-bottom: 5px;">
		<button type="button" class="btn btn-success"
			onclick="javascript:boxRecommendList()">用户推荐内容管理</button>
		<!-- data-toggle="modal"
					data-target="#insertBoxModel" -->

	</div>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">手册列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
							 <div id="" class="">
								<label>Search by type: </label> <select class="form-control"
									id='typeselect' onchange="javascript:typechanged()">
									<c:forEach items="${boxtypelist}" var="boxtype" varStatus="ids">
										<c:choose>
											<c:when test="${boxtype.typeid==nowtypeid }">
												<option value="${boxtype.typeid}" selected>${boxtype.description}</option>
											</c:when>
											<c:otherwise>
												<option value="${boxtype.typeid}">${boxtype.description}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','1')">
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
						<div class="col-sm-4" >
						<label>Search by title: </label>
						    <div class="col-sm-10">
						       <input id="inputTitle" type="text" class="form-control" value="${title }"/>
						    </div>
						    <div class="col-sm-2">
						    <button class="btn btn-primary" onclick="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','1')" >搜索</button>
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
									<th>boxid<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='boxid'}">
									     <a class="down" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','1','boxid')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='boxid'}">
									     <a class="up" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','0','boxid')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','1','boxid')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									
									</th>
									<th>boxtime</th>
									<th width="100px">栏目<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='description'}">
									     <a class="down" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','1','description')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='description'}">
									     <a class="up" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','0','description')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/boxmanage/boxlist','${sessionScope.user.user_guid}','1','1','description')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>title</th>
									<th>submitter</th>
									<th>source</th>
									<th>keyword</th>
									<th>ismotoband</th>
									<th width="100px">是否推荐</th>
									<th width="100px">是否热门</th>
									<th>boxurl</th>
									<th>status</th>
									<th>是否发过动态</th>
									<th>operate</th> 
								</tr>
							</thead>
							 <tbody>
								<c:forEach items="${boxlist}" var="BoxMessages" varStatus="ids">
									<tr class="${BoxMessages.boxid}">
										<td>${BoxMessages.boxid}<span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</span></td>
										<td>${BoxMessages.timeString}</td>
										<td>${BoxMessages.description}</td>
										<td>${BoxMessages.title}</td>
										<td>${BoxMessages.submitter}</td>
										<td>${BoxMessages.source}</td>
										<td><input type='text' value='${BoxMessages.keyword}'></td>
										<td>${BoxMessages.ismotoband}</td>
										<c:choose>
										  <c:when test="${BoxMessages.approve =='1'}">
										      <td class="center">推荐</td>
										  </c:when>
										  <c:when  test="${BoxMessages.approve =='0'}">
										       <td class="center">不推荐</td>
										  </c:when>
										</c:choose>
										<c:choose>
										  <c:when test="${BoxMessages.ishot =='1'}">
										      <td class="center">热门</td>
										  </c:when>
										  <c:when  test="${BoxMessages.ishot =='0'}">
										       <td class="center">不热门</td>
										  </c:when>
										</c:choose>
										<td><a target="_blank" href='${BoxMessages.boxurl}'>${BoxMessages.boxurl}</a></td>
										<c:choose>
										  <c:when test="${BoxMessages.status =='1'}">
										      <td class="center">上线</td>
										  </c:when>
										  <c:when  test="${BoxMessages.status =='0'}">
										       <td class="center">未上线</td>
										  </c:when>
										</c:choose>
										<c:choose>
										  <c:when test="${BoxMessages.news =='1'}">
										      <td class="center">发过动态</td>
										  </c:when>
										  <c:when  test="${BoxMessages.news =='0'}">
										       <td class="center" id="news${BoxMessages.boxid}">没发过</td>
										  </c:when>
										</c:choose>
										
										<td class="center">
										<button type="button" class="btn btn-info"
												onclick="javascript:lookcomments('${BoxMessages.boxid}')">查看评论</button>
										<button type="button" class="btn btn-primary"
												onclick="javascript:releaseNews('${BoxMessages.typeid}','${BoxMessages.boxid}')">发布动态</button>
										<button type="button" class="btn btn-info"
												onclick="javascript:boxAddToRecommendnews('${BoxMessages.typeid}','${BoxMessages.boxid}')">添加到精选</button>
										<button type="button" class="btn btn-primary"
												onclick="javascript:sellook('${BoxMessages.boxid}')">浏览量</button>
										<c:choose>
												<c:when test="${updateCheck=='lock'}">
													<button type="button" class="btn btn-primary btn-lg"
														onclick="javascript:selBoxMessage('${BoxMessages.boxid}')">编辑</button>
													<!--  -->
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">编辑</button>
												</c:otherwise>
											</c:choose>
											
											<c:choose>
												<c:when test="${delCheck=='lock'}">
													<button type="button" class="btn btn-primary disabled  btn-lg"
														data-toggle="modal" data-target="#delBoxModel"
														onclick="javascript:delBoxGuid('${BoxMessages.boxid}')">删除</button>
												</c:when>
												<c:otherwise>
													<button type="button"
														class="btn btn-primary disabled btn-lg">删除</button>
												</c:otherwise>
											</c:choose> <c:choose>
												<c:when test="${BoxMessages.ishot=='1'}">
													<button type="button" class="btn  btn-lg"
														id="ishotbox${BoxMessages.boxid }" style='display: block;background-color:#FFFF000 !important;border-color:#FFFF000'
														onclick="javascript:delBoxHot('${BoxMessages.boxid}')">取消热门</button>
													<button type="button" class="btn  btn-lg"
														id='isnothotbox${BoxMessages.boxid }'
														style='display: none;background-color:#FF00000 !important;border-color:#FF00000'
														onclick="javascript:addBoxHot('${BoxMessages.boxid}')">设置热门</button>
												</c:when>
												<c:otherwise>
													<button type="button" class="btn  btn-lg"
														id="ishotbox${BoxMessages.boxid }" style='display: none;background-color:#FFFF000 !important;border-color:#FFFF000'
														onclick="javascript:delBoxHot('${BoxMessages.boxid}')">取消热门</button>
													<button type="button" class="btn  btn-lg"
														id='isnothotbox${BoxMessages.boxid }'
														style='display: block;background-color:#FF00000 !important;border-color:#FF00000'
														onclick="javascript:addBoxHot('${BoxMessages.boxid}')">设置热门</button>
												</c:otherwise>
											</c:choose>
										<%-- 	<button type="button" class="btn btn-success"
												onclick="javascript:addBoxBanner('${BoxMessages.boxid}')">添加内容横幅</button>
											<button type="button" class="btn btn-success"
												onclick="javascript:addBanner('${BoxMessages.boxid}','${BoxMessages.boxurl}')">添加摩托邦横幅</button> --%>
											
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
				<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/boxmanage/boxlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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
		                    1, "desc"
		            ]
	            ]
	        });
	        
        }); 
        
	</script>
 -->

	<!-- Modal -->
	<div class="modal fade" id="delBoxModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除选中手册</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delBoxGuid">
					<h3>删除手册信息后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delBoxMessage()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="insertBoxModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加新用户</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<input class="form-control" placeholder="新用户名" id="newusername"
							value="">
					</div>

					<div class="form-group">
						<input class="form-control" placeholder="登录名"
							id="newuserloginname" value=""
							onblur="javascript:checkLoginName()">
						<p class="text-danger" id="dangertext" style="display: none">登录名已被使用！</p>
					</div>
					<div class="form-group">
						<input type="radio" name="newusersex" id="man" value="男"
							checked="">男 <input type="radio" name="newusersex"
							id="woman" value="女">女

					</div>
					<div class="form-group">
						<input class="form-control" placeholder="用户手机号" id="newusertel"
							value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveNewUser()" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<!-- usermanage.js -->
	<script src="../js/boxmanage/boxmanage.js"></script>
	<script type="text/javascript">
	
	
	</script>
</body>


</html>