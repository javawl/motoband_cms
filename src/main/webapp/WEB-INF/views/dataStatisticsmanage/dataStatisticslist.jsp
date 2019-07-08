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

/* .modal { overflow: auto} */

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
	var type= $("#typeHidden").val();
	var limit= $("#pageSizeSelect").val();
	var startTime=$("#starttime").val();
	var endTime=$("#endtime").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&startTime="+startTime+"&endTime="+endTime+"&type="+type);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var type= $("#typeHidden").val();
		var limit= $("#pageSizeSelect").val();
		var startTime=$("#starttime").val();
		var endTime=$("#endtime").val();
		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&startTime="+startTime+"&endTime="+endTime+"&type="+type);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}


</script>

</head>

<body >

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">数据统计管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>


	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">数据列表    &nbsp &nbsp &nbsp &nbsp &nbsp 用户数：${mbusercount}   &nbsp &nbsp &nbsp &nbsp &nbsp 二手车总数：${secondcarcount}   </div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px;margin-top: 14px;">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','1')">
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
				 <div class="col-sm-6">
							<div class="form-group">
								<div class="col-sm-6" style="margin-top: 14px;">
									<label>starttime</label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="starttime" value="${startTime }"
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>endtime</label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="endtime" value="${endTime }"
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-3" style="margin-top: 40px;">
						<c:choose>
						   <c:when test="${type==1 }">
						       <button type="button" class="btn btn-danger"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','1')">日</button>
						     <%--   <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','2')">周</button> --%>
						       <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','3')">月</button>
						   </c:when>
						<%--   <c:when test="${type==2 }">
						       <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','1')">日</button>
						       <button type="button" class="btn btn-danger"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','2')">周</button>
						       <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','3')">月</button>
						  </c:when> --%>
						  <c:when test="${type==3 }">
						       <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','1')">日</button>
						     <%--   <button type="button" class="btn btn-success"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','2')">周</button> --%>
						       <button type="button" class="btn btn-danger"  onclick="javascript:changeType('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','3')">月</button>
						  </c:when>
						</c:choose>
						
						</div>
						
						 <input type="hidden"  value="${type }" id="typeHidden">
						 
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									
									<th>日期</th>
									<th>APP打开次数 </th>
									<th>独立用户数</th>
									<th>新增用户</th>
									<th>骑行次数</th>
									<th>动态数</th>
									<th>故事数</th>
									<th>视频动态数</th>
									<th>线路动态数</th>
									<th>图文数</th>
									<th>精选数</th>
									<th>点赞数</th>
									<th>评论数</th>
									<th>礼物数</th>
									<th>送礼独立用户数</th>
									<th>礼物分布动态数</th>
									<th>参与讨论人数</th>
									<th>发布讨论人数</th>
									<th>发布讨论次数</th>
								<!-- 	<th>兑换礼物人数</th>
									<th>兑换礼物次数</th>
									<th>兑换总积分</th>
									<th>转赠礼物人数</th>
									<th>转赠礼物总数</th> -->
									<th>文章转载人数</th>
									<th>文章转载次数</th>
								<!-- 	<th>商家合计数</th>
									<th>商家日增数</th> 
									<th>二手车合计数</th> -->
									<th>二手车日增数</th>
									
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dataStatisticslist}" var="dslist"
									varStatus="ids">
									<tr class="${dslist.id}">
									<td>${dslist.timeformat}</td>
								    <td>${dslist.openAppCount}</td> 
									<td>${dslist.distinctUserCount}</td>
									<td>${dslist.newAddUserCount}</td>
									<td>${dslist.ridedataCount}</td>
									<td>${dslist.newsCount}</td>
									<td>${dslist.newsStoryCount}</td>
									<td>${dslist.newsVideoCount}</td>
									<td>${dslist.newsRidelineCount}</td>
									<td>${dslist.newsGraphicCount}</td>
									<td>${dslist.newsRecommmentCount}</td>
									<td>${dslist.newsLikeCount}</td>
									<td>${dslist.newsCommentCount}</td>
									<td>${dslist.giftCount}</td>
									<td>${dslist.giftDistinctUserCount}</td>
									<td>${dslist.giftDistinctNidCount}</td>
									<td>${dslist.newsDiscussCount}</td>
									<td>${dslist.newsPublishDiscussUserCount}</td>
									<td>${dslist.newsPublishDiscussCount}</td>
								<%-- 	<td>${dslist.giftexchangeUserCount}</td>
									<td>${dslist.giftexchangeCount}</td>
									<td>${dslist.giftexchangeCreditCount}</td>
									<td>${dslist.giftgivenUserCount}</td>
									<td>${dslist.giftgivenGiftCount}</td> --%>
									<td>${dslist.newsBoxUserCount}</td>
									<td>${dslist.newsBoxCount}</td>
								<%-- 	<td>${dslist.buserSumCount}</td>
									<td>${dslist.buserAddCount}</td>
									<td>${dslist.secondcarSumCount}</td> --%>
									<td>${dslist.secondcarAddCount}</td>
									
									
									<%-- <td><fmt:formatNumber value="${dslist.openAppCount*dslist.ratio1+(1-(dslist.openAppCount*dslist.ratio1%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.distinctUserCount*dslist.ratio1+(1-(dslist.distinctUserCount*dslist.ratio1%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newAddUserCount*dslist.ratio1+(1-(dslist.newAddUserCount*dslist.ratio1%1))%1}" type="number" pattern="#"/></td>
									
									<td><fmt:formatNumber value="${dslist.ridedataCount*dslist.ratio2+(1-(dslist.ridedataCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsCount*dslist.ratio2+(1-(dslist.newsCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsStoryCount*dslist.ratio2+(1-(dslist.newsStoryCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsVideoCount*dslist.ratio2+(1-(dslist.newsVideoCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsRidelineCount*dslist.ratio2+(1-(dslist.newsRidelineCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsGraphicCount*dslist.ratio2+(1-(dslist.newsGraphicCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsRecommmentCount*dslist.ratio2+(1-(dslist.newsRecommmentCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsLikeCount*dslist.ratio2+(1-(dslist.newsLikeCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsCommentCount*dslist.ratio2+(1-(dslist.newsCommentCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftCount*dslist.ratio2+(1-(dslist.giftCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftDistinctUserCount*dslist.ratio2+(1-(dslist.giftDistinctUserCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftDistinctNidCount*dslist.ratio2+(1-(dslist.giftDistinctNidCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsDiscussCount*dslist.ratio2+(1-(dslist.newsDiscussCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsPublishDiscussUserCount*dslist.ratio2+(1-(dslist.newsPublishDiscussUserCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsPublishDiscussCount*dslist.ratio2+(1-(dslist.newsPublishDiscussCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftexchangeUserCount*dslist.ratio2+(1-(dslist.giftexchangeUserCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftexchangeCount*dslist.ratio2+(1-(dslist.giftexchangeCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftexchangeCreditCount*dslist.ratio2+(1-(dslist.giftexchangeCreditCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftgivenUserCount*dslist.ratio2+(1-(dslist.giftgivenUserCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.giftgivenGiftCount*dslist.ratio2+(1-(dslist.giftgivenGiftCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsBoxUserCount*dslist.ratio2+(1-(dslist.newsBoxUserCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.newsBoxCount*dslist.ratio2+(1-(dslist.newsBoxCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.buserSumCount*dslist.ratio2+(1-(dslist.buserSumCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.buserAddCount*dslist.ratio2+(1-(dslist.buserAddCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.secondcarSumCount*dslist.ratio2+(1-(dslist.secondcarSumCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									<td><fmt:formatNumber value="${dslist.secondcarAddCount*dslist.ratio2+(1-(dslist.secondcarAddCount*dslist.ratio2%1))%1}" type="number" pattern="#"/></td>
									 --%>
									 
									 <%-- <td><fmt:formatNumber value="${dslist.openAppCount*dslist.ratio1}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.distinctUserCount*dslist.ratio1}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newAddUserCount*dslist.ratio1}" type="number" maxFractionDigits="0"/></td>
									
									<td><fmt:formatNumber value="${dslist.ridedataCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsStoryCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsVideoCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsRidelineCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsGraphicCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsRecommmentCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsLikeCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsCommentCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.giftCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.giftDistinctUserCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.giftDistinctNidCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsDiscussCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsPublishDiscussUserCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsPublishDiscussCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									
									<td><fmt:formatNumber value="${dslist.newsBoxUserCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									<td><fmt:formatNumber value="${dslist.newsBoxCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									
									<td><fmt:formatNumber value="${dslist.secondcarAddCount*dslist.ratio2}" type="number" maxFractionDigits="0"/></td>
									 --%>
									   
									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
					<!-- /.table-responsive -->
			<div class="pagination">
			
			<span><h3>第${ pageBean.page}页/共${ pageBean.totalPage}页</h3></span>
			<c:if test="${pageBean.page != 1 }">
				<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/dataStatisticsmanage/dataStatisticslist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>

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
	 <script src="../js/dataStatistics/dataStatisticslist.js"></script>  
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
                format : 'yyyy-mm-dd',
                pickerPosition : "bottom",
                minView: "month"	
            
            });
			
			
			function changeType(url,adminGuid,type){
				var limit= $("#pageSizeSelect").val();
				var startTime=$("#starttime").val();
				var endTime=$("#endtime").val();
				$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page=1"+"&limit="+limit+"&startTime="+startTime+"&endTime="+endTime+"&type="+type);
			}
		</script>
 
</body>


</html>