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
<script src="../js/boxmanage/jquery.form.min.js"></script>

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
	var adtype= $("#poolSelect").val();
	if(order==''||order == null){
		order=1;
	}	
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&adtype="+adtype);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var adtype= $("#poolSelect").val();
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var limit= $("#pageSizeSelect").val();
		var order= $("#triangleHidden").val();

		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&adtype="+adtype);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order){

	var limit= $("#pageSizeSelect").val();
	var adtype= $("#poolSelect").val();

	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&adtype="+adtype);


}
</script>
</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">新广告管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
  <button type="button" class="btn btn-success" data-toggle="modal"
		data-target="#addAd"  onclick="javascript:addAd()">添加广告</button>

	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">广告列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','1')">
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
							  <select class="form-control" id="poolSelect"  onchange="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','1')">
									<c:choose>
									   <c:when test="${adtype==0}">
									     <option value="0" selected>开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									   <c:when test="${adtype==1}">
									     <option value="0" >开屏</option>
									     <option value="1" selected>轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==2}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" selected>首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==3}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" selected>首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==4}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" selected>二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==5}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" selected>关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==6}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" selected>发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==7}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" selected>此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==8}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" selected>动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==9}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" selected>动态视频详情</option>
									     <option value="10" >故事详情</option>
									   </c:when>
									    <c:when test="${adtype==10}">
									     <option value="0" >开屏</option>
									     <option value="1" >轮播</option>
									     <option value="2" >首页推荐信息流 </option>
									     <option value="3" >首页分类信息流</option>
									     <option value="4" >二手车信息流</option>
									     <option value="5" >关注信息流 </option>
									     <option value="6" >发现信息流</option>
									     <option value="7" >此刻信息流</option>
									     <option value="8" >动态图文详情</option>
									     <option value="9" >动态视频详情</option>
									     <option value="10" selected>故事详情</option>
									   </c:when>
									
									</c:choose>
							  </select>
						</div> 
					</div>
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  state: </label>
							  <div>
							  <c:choose>
								  <c:when test="${order== 1}">
								     <a  class="down" href="javascript:changeTriangle('/admanage/advertisinglist','${sessionScope.user.user_guid}','1','0')">▲上架</a>
								     <input id="triangleHidden" type="hidden" value="1"/>
								  </c:when>
								  <c:otherwise>
								     <a id="triangle" class="up" href="javascript:changeTriangle('/admanage/advertisinglist','${sessionScope.user.user_guid}','1','1')">▼下架</a>
								     <input id="triangleHidden" type="hidden" value="0"/>
								  </c:otherwise>
								</c:choose>
							</div>
						</div> 
					</div>
					
						
						
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>adid</th>
									<th>title</th>
									<th>subtitle</th>
									<th>des</th>
									<th>adheadtype</th>
									<th>adtype</th>
									<th>state</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${adlist}" var="list"
									varStatus="ids">
									<tr >
									   <td onclick="javascript:trClickAd('${list.adid}')">${list.adid }</td>
										<td onclick="javascript:trClickAd('${list.adid}')">${list.title}</td>
										<td onclick="javascript:trClickAd('${list.adid}')">${list.subtitle}</td>
										<td onclick="javascript:trClickAd('${list.adid}')">${list.des}</td>
										<c:if test="${list.adheadtype== 0}">
										  <td onclick="javascript:trClickAd('${list.adid}')">摩托邦商城</td>
										</c:if>
										<c:if test="${list.adheadtype== 1}">
										  <td onclick="javascript:trClickAd('${list.adid}')">疯狂摩托车</td>
										</c:if>
										<c:if test="${list.adheadtype== 2}">
										  <td onclick="javascript:trClickAd('${list.adid}')">贝纳利</td>
										</c:if>
										<c:if test="${list.adheadtype== 3}">
										  <td onclick="javascript:trClickAd('${list.adid}')">360金融</td>
										</c:if>
										
										<c:if test="${list.adtype== 0}">
										  <td onclick="javascript:trClickAd('${list.adid}')">开屏</td>
										</c:if>
										<c:if test="${list.adtype== 1}">
										  <td onclick="javascript:trClickAd('${list.adid}')">轮播</td>
										</c:if>
										<c:if test="${list.adtype== 2}">
										  <td onclick="javascript:trClickAd('${list.adid}')">首页推荐信息流</td>
										</c:if>
										<c:if test="${list.adtype== 3}">
										  <td onclick="javascript:trClickAd('${list.adid}')">首页分类信息流</td>
										</c:if>
										<c:if test="${list.adtype== 4}">
										  <td onclick="javascript:trClickAd('${list.adid}')">二手车信息流</td>
										</c:if>
										<c:if test="${list.adtype== 5}">
										  <td onclick="javascript:trClickAd('${list.adid}')">关注信息流</td>
										</c:if>
										<c:if test="${list.adtype== 6}">
										  <td onclick="javascript:trClickAd('${list.adid}')">发现信息流</td>
										</c:if>
										<c:if test="${list.adtype== 7}">
										  <td onclick="javascript:trClickAd('${list.adid}')">此刻信息流</td>
										</c:if>
										<c:if test="${list.adtype== 8}">
										  <td onclick="javascript:trClickAd('${list.adid}')">动态图文详情</td>
										</c:if>
										<c:if test="${list.adtype== 9}">
										  <td onclick="javascript:trClickAd('${list.adid}')">动态视频详情 </td>
										</c:if>
										<c:if test="${list.adtype== 10}">
										  <td onclick="javascript:trClickAd('${list.adid}')">故事详情</td>
										</c:if>
										
										<c:if test="${list.state== 0}">
										  <td onclick="javascript:trClickAd('${list.adid}')">下线</td>
										</c:if>
										<c:if test="${list.state== 1}">
										  <td onclick="javascript:trClickAd('${list.adid}')">上线</td>
										</c:if>
										
										<td class="center">
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#editad" onclick="javascript:editad('${list.adid}')">编辑</button>
										<%-- 	<button type="button" class="btn btn-success" data-toggle="modal" data-target="#deletead" onclick="javascript:deletead('${list.adid}')">删除</button> --%>
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#addimgs" onclick="javascript:addimgs('${list.adid}')">添加更多图片</button> 
										</td> 
									</tr>
									<tr >
									   <td colspan="7"><div id="trHiddenDiv${list.adid}"></div>
									  </td>
								   </tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					
					
			<div class="pagination">
			
			<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
			<c:if test="${pageBean.page != 1 }">
				<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			     
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5"/>&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/admanage/advertisinglist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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

	<!-- Modal -->
	<div class="modal fade" id="editad" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">广告信息</h4>
				</div>
				<div class="modal-body" style="height:350px">
					<div class="form-group">
					    <div class="col-sm-12">
					    <label>adid</label>
						<input class="form-control" placeholder="" id="adid"
							value="" readonly="readonly">
						</div>
					</div>
					<div class="form-group">
					  <div class="col-sm-12">
					   <div class="col-sm-6">
					      <label>标题</label>
						    <input class="form-control" placeholder="" id="title"
							value="" >
					   </div>
					   <div class="col-sm-6">
					    <label>副标题</label>
						 <input class="form-control" placeholder="" id="subtitle"
							value="" >
					   </div>
					</div>
					</div>
					
					<div class="form-group">
					   <div class="col-sm-12">
					    <label>简介</label>
						 <input class="form-control" placeholder="" id="des"
							value="" >
					     </div>
					</div>
                    <div class="form-group">
                        <div class="col-sm-12">
                        <label>广告主头像类型:</label>
						<input type="radio" class="" placeholder="" name="adheadtype"
							value="0" >摩托邦商城
						<input type="radio" class="" placeholder="" name="adheadtype"
							value="1" >疯狂摩托车
						<input type="radio" class="" placeholder="" name="adheadtype"
							value="2" >贝纳利
						<input type="radio" class="" placeholder="" name="adheadtype"
							value="3" >360金融
					    </div>
					  
					</div>
                    <div class="form-group">
                        <div class="col-sm-12">
                        <label>广告位:</label>
						<input type="radio" class="" placeholder="" name="adtype"
							value="0" >开屏
						<input type="radio" class="" placeholder="" name="adtype"
							value="1" >轮播
						<input type="radio" class="" placeholder="" name="adtype"
							value="2" >首页推荐信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="3" >首页分类信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="4" >二手车信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="5" >关注信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="6" >发现信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="7" >此刻信息流
						<input type="radio" class="" placeholder="" name="adtype"
							value="8" >动态图文详情
						<input type="radio" class="" placeholder="" name="adtype"
							value="9" >动态视频详情 
						<input type="radio" class="" placeholder="" name="adtype"
							value="10" >故事详情
					    </div>
					  
					</div>

					<div class="form-group">
					<div class="col-sm-6" style="margin-top:27px;">
					<label>状态：</label>
						<input type="radio" class="" placeholder="" name="state"
							value="0" >未上线
						<input type="radio" class="" placeholder="" name="state"
							value="1" >已上线
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
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

	<div class="modal fade" id="addAd" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">广告信息</h4>
				</div>
				<div class="modal-body" style="height:300px">
					
					<div class="form-group">
					   <div class="col-sm-12">
					   <div class="col-sm-6">
					    <label>标题：</label>
						 <input class="form-control" placeholder="" id="addtitle"
							value="" >
					    </div>
					   <div class="col-sm-6">
					    <label>副标题：</label>
						 <input class="form-control" placeholder="" id="addsubtitle"
							value="" >
					     </div>
					     </div>
					</div>
					
					<div class="form-group">
					   <div class="col-sm-12">
					    <label>简介：</label>
						 <input class="form-control" placeholder="" id="adddes"
							value="" >
					     </div>
					</div>
					
					<div class="form-group">
                        <div class="col-sm-12">
                        <label>广告主头像类型:</label>
						<input type="radio" class="" placeholder="" name="addadheadtype"
							value="0" >摩托邦商城
						<input type="radio" class="" placeholder="" name="addadheadtype"
							value="1" >疯狂摩托车
						<input type="radio" class="" placeholder="" name="addadheadtype"
							value="2" >贝纳利
						<input type="radio" class="" placeholder="" name="addadheadtype"
							value="3" >360金融
					    </div>
					  
					</div>
					
					
                    <div class="form-group">
                        <div class="col-sm-12">
                        <label>广告位:</label>
						<input type="radio" class="" placeholder="" name="addadtype"
							value="0" >开屏
						<input type="radio" class="" placeholder="" name="addadtype"
							value="1" >轮播
						<input type="radio" class="" placeholder="" name="addadtype"
							value="2" >首页推荐信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="3" >首页分类信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="4" >二手车信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="5" >关注信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="6" >发现信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="7" >此刻信息流
						<input type="radio" class="" placeholder="" name="addadtype"
							value="8" >动态图文详情
						<input type="radio" class="" placeholder="" name="addadtype"
							value="9" >动态视频详情 
						<input type="radio" class="" placeholder="" name="addadtype"
							value="10" >故事详情
					    </div>
					</div>
					
					<div class="form-group">
					<div class="col-sm-6" style="margin-top:30px;">
					<label>状态：</label>
						<input type="radio" class="" placeholder="" name="addstate"
							value="0" >未上线
						<input type="radio" class="" placeholder="" name="addstate"
							value="1" >已上线
					</div>
					</div>
					</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addConfirm()">确认添加</button>
				</div>
			
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<div class="modal fade" id="deletead" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除广告</h4>
				</div>
				<div class="modal-body">
				   
					<input type="text" value="" style="display: none" id="delAdid"/>
					
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
	
	
	<div class="modal fade" id="addimgs" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加图片</h4>
				</div>
				<div class="modal-body" style="height:300px">
					<div class="form-group">
					<div class="col-sm-6">
					    <label>选择图片类型：</label>
					    <select class="form-control" id="imageSelectType"  onchange="javascript:imgTypeChange(this)">
					        <option value="1">动态</option>
					        <option value="2">话题</option>
					        <option value="3">问答</option>
					        <option value="4">有赞</option>
					        <option value="5">内部链接</option>
					        <option value="6">外部链接</option>
					        <option value="7">小程序</option>
					        <option value="8">motogp</option>
					        <option value="9">二手车</option>
					        <option value="10">本地商家</option>
					       
					    </select>
					    <div>
					        <div id="div1">请输入nid：<input class="form-control" placeholder="" id="inputUrlnid" ></div>
					        <div id="div2"  style="display:none;">请输入keyword：<input class="form-control" placeholder="" id="inputUrlkeyword" ></div>
					        <div id="div3"  style="display:none;">请输入linkurl：<input class="form-control" placeholder="" id="inputUrllinkurl" ></div>
					        <div id="div4"  style="display:none;">请输入miniprogramid：<input class="form-control" placeholder="" id="inputUrlminiprogramid" ></div>
					        <div id="div5"  style="display:none;">请输入gpid：<input class="form-control" placeholder="" id="inputUrlgpid" ></div>
					        <div id="div6"  style="display:none;">请输入secondcarid：<input class="form-control" placeholder="" id="inputUrlsecondcarid" ></div>
					        <div id="div7"  style="display:none;">请输入buserid：<input class="form-control" placeholder="" id="inputUrlbuserid" ></div>
							
					    </div>
					    <input type="text" value="" style="display: none" id="addimgsAdid"/>
					</div>
					</div>
					
					 <div class="form-group">
						<input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"> <input
							type="text" value="" id="titleimg"> <img
							id="titleimgshow" src="" style="width: 144px; height: 144px" />
							<input type="hidden" id="hiddenValue" value="" >
					</div>
					
                   <div class="form-group">
					<div class="col-sm-6">
					    <label>orderindex</label>
						<input class="form-control" placeholder="" id="orderindex"
							value="" >
					</div>
				</div>
				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addImgConfirm()">确认添加</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal -->
	 <div class="modal fade" id="editadimg" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">广告图片信息</h4>
				</div>
				<div class="modal-body" style="height:380px">
				  <div class="form-group">
					<div class="col-sm-6">
					    <label>adimgid</label>
						<input class="form-control" placeholder="" id="adimgidadimg"
							value="" readonly="readonly">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
					    <label>adid</label>
						<input class="form-control" placeholder="" id="adidadimg"
							value="" >
					</div>
				</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>选择图片类型：</label>
					    <select class="form-control" id="imageSelectTypeadimg"  onchange="javascript:imgTypeChangeadimg(this)">
					        <option value="1">动态</option>
					        <option value="2">话题</option>
					        <option value="3">问答</option>
					        <option value="4">有赞</option>
					        <option value="5">内部链接</option>
					        <option value="6">外部链接</option>
					        <option value="7">小程序</option>
					        <option value="8">motogp</option>
					        <option value="9">二手车</option>
					        <option value="10">本地商家</option>
					     
					    </select>
					    <div>
					        <div id="divadimg1">请输入nid：<input class="form-control" placeholder="" id="inputUrlnidadimg" ></div>
					        <div id="divadimg2"  style="display:none;">请输入keyword：<input class="form-control" placeholder="" id="inputUrlkeywordadimg" ></div>
					        <div id="divadimg3"  style="display:none;">请输入linkurl：<input class="form-control" placeholder="" id="inputUrllinkurladimg" ></div>
					        <div id="divadimg4"  style="display:none;">请输入miniprogramid：<input class="form-control" placeholder="" id="inputUrlminiprogramidadimg" ></div>
					        <div id="divadimg5"  style="display:none;">请输入gpid：<input class="form-control" placeholder="" id="inputUrlgpidadimg" ></div>
					        <div id="divadimg6"  style="display:none;">请输入secondcarid：<input class="form-control" placeholder="" id="inputUrlsecondcaridadimg" ></div>
					        <div id="divadimg7"  style="display:none;">请输入buserid：<input class="form-control" placeholder="" id="inputUrlbuseridadimg" ></div>
					    
							
					    </div>
					    <input type="text" value="" style="display: none" id="addimgsAdidadimg"/>
					</div>
					</div>
					
					 <div class="form-group">
						<input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel"> <input
							type="text" value="" id="titleimgadimg"> <img
							id="titleimgshowadimg" src="" style="width: 144px; height: 144px" />
							
					</div>
					
                   <div class="form-group">
					<div class="col-sm-6">
					    <label>orderindex</label>
						<input class="form-control" placeholder="" id="orderindexadimg"
							value="" >
					</div>
				</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:editAdimgConfirm()">确认修改</button>
				</div>
			
			</div>
			
		</div>
		
	</div>
	
	<div class="modal fade" id="deleteadimg" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除广告图片</h4>
				</div>
				<div class="modal-body">
				   
					<input type="text" value="" style="display: none" id="delAdidimg"/>
					<input type="text" value="" style="display: none" id="delAdidimgAdid"/>
					<h3>删除后无法恢复</h3>
					
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delAdimgConfirm()">确认删除</button>
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
				<div id="back" style="display:none; POSITION:fixed;z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
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
					<form id="uploadForm" style="float: left">
							<input type="file" name="FileContent" class="btn btn-info"
								id="inputfile" style="float: left"></input> <input id="subbtn"
								type="submit" style="float: left" class="btn btn-info"
								style="margin-left: 0;" onclick="javascript:imgsubmit()">
						</form>
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel" onclick="javascript:titleImgModel();">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
		
	<script src="../js/admanage/advertisinglist.js"></script> 
	<script src="../js/mallmanage/uploadGroupImage2.js"></script> 
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