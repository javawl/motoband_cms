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
	var order= $("#triangleHidden").val();
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	var limit= $("#pageSizeSelect").val();
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&orderConditions="+orderConditions);	
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

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">mgp管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>


	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">gp列表<span>&nbsp&nbsp</span><button type="button" class="btn btn-success" data-toggle="modal" data-target="#addmotobandgpModel" onclick="javascript:addmotobandgp()">添加</button>
				&nbsp&nbsp<button type="button" class="btn btn-info" onclick="javascript:updateVersion('motobandgpversion')">更新骑行赛版本</button>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
					<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1')">
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
									<th>gpid<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='gpid'}">
									     <a class="down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','gpid')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='gpid'}">
									     <a class="up" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','0','gpid')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','gpid')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									<%-- <span>&nbsp</span>
									<c:choose>
									  <c:when test="${order== 0}">
									     <a  class="down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1')" style="text-decoration:none;">▼</a>
									     <input id="triangleHidden" type="hidden" value="0"/>
									  </c:when>
									  <c:otherwise>
									     <a id="triangle" class="up" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','0')"   style="text-decoration:none;">▲</a>
									     <input id="triangleHidden" type="hidden" value="1"/>
									  </c:otherwise>
									</c:choose> --%>
									</th>
									<th>title</th>
									<th>subtitle</th>
									<th>content</th>
									<th>picurl</th>
									<th>starttime<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='starttime'}">
									     <a class="down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','starttime')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='starttime'}">
									     <a class="up" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','0','starttime')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','starttime')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>endtime<span>&nbsp</span>
									 <c:choose>
									  <c:when test="${order== '0' && orderConditions=='endtime'}">
									     <a class="down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','endtime')" style="text-decoration:none;">▼</a>   
									  </c:when>
									  <c:when test="${order== '1'  && orderConditions=='endtime'}">
									     <a class="up" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','0','endtime')"   style="text-decoration:none;">▲</a>
									  </c:when>
									  <c:otherwise>
									     <a class="up_down" href="javascript:changeTriangle('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1','1','endtime')"   style="text-decoration:none;">◊</a>									     
									  </c:otherwise>
									</c:choose> 
									</th>
									<th>mileage</th>
									<th>圈数</th>
									<th>achid</th>
									<th>type</th>
									<th>status</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${motobandgplist}" var="gplist"
									varStatus="ids">
									<tr class="${gplist.gpid}">
									    <td>${gplist.gpid }</td>
										<td>${gplist.title}</td>
										<td>${gplist.subtitle}</td>
										<td>${gplist.content}</td>
										<td><img width="200px" height="100px" src="${gplist.picurl}!thumb"/></td>
										<td>${gplist.starttimeString}</td>
										<td>${gplist.endtimeString}</td>
										<td>${gplist.mileage}</td>
										<td>${gplist.lap}</td>
										<td>${gplist.achid}</td>
										<c:choose>
										  <c:when test="${gplist.type=='1'}">
										      <td>竞距</td>
										  </c:when>
										  <c:when test="${gplist.type=='2'}">
										      <td>竞速</td>
										  </c:when>
										</c:choose>
										<c:choose>
										  <c:when test="${gplist.status=='1'}">
										      <td>上线</td>
										  </c:when>
										  <c:when test="${gplist.status=='0'}">
										      <td>未上线</td>
										  </c:when>
										</c:choose>
									    <td>
									        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#lookRankingModel" onclick="javascript:lookRanking('1','${gplist.gpid}','${gplist.type}')">查看前100排名</button>
									        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#editmotobandgpModel" onclick="javascript:editmotobandgp('${gplist.gpid}')">编辑</button>
									        <button type="button" class="btn btn-success" data-toggle="modal" data-target="#lookJoinGPCountModel" onclick="javascript:lookJoinGPCount('${gplist.gpid}')">查看参与数据</button>
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
				<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/motobandgpmanage/motobandgplist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>

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
	<div class="modal fade" id="editmotobandgpModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">gp信息</h4>
				</div>
				<div class="modal-body" style="height:730px">
				<div class="form-group">
				    <div class="col-sm-12">
				      <div class="form-group">
								<div class="col-sm-12">
									<label>gpid</label> <input class="form-control" placeholder=""
										id="update_gpid" value="" readonly="readonly">
										<input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
										<input id="hiddenValue" type="hidden" value="">
								</div>
					  </div>
				    </div>
				    <div class="col-sm-12">
				        <div class="col-sm-6">
				           
							<div class="form-group">
								<div class="col-sm-12">
									<label>title</label> <input class="form-control" placeholder=""
										id="update_title" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>subtitle</label> <input class="form-control"
										placeholder="" id="update_subtitle" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>mileage</label> <input class="form-control"
										placeholder="" id="update_mileage" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>lap</label> <input class="form-control"
										placeholder="" id="update_lap" value="">
								</div>
							</div>
				        </div>
				        <div class="col-sm-6">
				           <div class="form-group">
				           <label>picurl</label>
				           <label><font color="red">请选择750x350的图片</font></label>
				           <div>
								<input id="" type="button" value="设置图片" class="btn btn-info"
									data-toggle="modal" data-target="#titleImgModel"> <input
									type="text" value="" id="titleimg"> <img
									id="titleimgshow" src="" style="width: 200px; height: 100px" />
							</div>
							</div>
				        </div>
				    </div>
				     <div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-6" style="margin-top:14px;">
									<label>starttime</label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="update_starttime" value=""
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
											placeholder="" id="update_endtime" value=""
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
						</div>
				    <div class="col-sm-12">
				        <div class="form-group">
								<div class="col-sm-12">
									<label>content</label> <textarea style='width:500px;height: 180px' 
										placeholder="" id="update_content" value=""></textarea>
								</div>
						</div>
				    </div>
				    <div class="col-sm-12">
				       <div class="form-group">
								<div class="col-sm-12">
									<label>achid</label>
									<select class="form-control" id="update_achid">
									</select>
								</div>
					  </div>
				    </div>
				   
				    <div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-6" style="margin-top:14px;">
									<label>type</label> <select class="form-control"
										id="update_type">
										<option value='1'>竞距</option>
										<option value='2'>竞速</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>status</label> <select class="form-control"
										id="update_status">
										<option value='0'>未上线</option>
										<option value='1'>上线</option>
									</select>
								</div>
							</div>
					</div>
				</div>
				
				
                </div>   
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:editmotobandgpConfirm()">确认修改</button>
				</div>
			
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

<!-- Modal -->
	<div class="modal fade" id="addmotobandgpModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">gp信息</h4>
				</div>
				<div class="modal-body" style="height:730px">
				<div class="form-group">
				   
				    <div class="col-sm-12">
				        <div class="col-sm-6">
				           
							<div class="form-group">
								<div class="col-sm-12">
									<label>title</label> <input class="form-control" placeholder=""
										id="ins_title" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>subtitle</label> <input class="form-control"
										placeholder="" id="ins_subtitle" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>mileage</label> <input class="form-control"
										placeholder="" id="ins_mileage" value="">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<label>lap</label> <input class="form-control"
										placeholder="" id="ins_lap" value="">
								</div>
							</div>
				        </div>
				        <div class="col-sm-6">
				           <div class="form-group">
				           <label>picurl</label>
								<input id="" type="button" value="设置图片" class="btn btn-info"
									data-toggle="modal" data-target="#titleImgModel"> <input
									type="text" value="" id="titleimgadd"> <img
									id="titleimgshowadd" src="" style="width: 144px; height: 144px" />
							</div>
				        </div>
				    </div>
				    <div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-6" style="margin-top:14px;">
									<label>starttime</label>
									<div class="input-append date form_datetime"
										onclick="javascript:showTimeForm(this)">
										<input size="16" type="text" class="form-control"
											placeholder="" id="ins_starttime" value=""
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
											placeholder="" id="ins_endtime" value=""
											readonly="readonly" style="cursor: pointer;"> <span
											class="add-on"><i class="icon-th"></i></span>
									</div>
								</div>
							</div>
						</div>
				    <div class="col-sm-12">
				        <div class="form-group">
								<div class="col-sm-12">
									<label>content</label> <textarea style='width:500px;height: 230px' 
										placeholder="" id="ins_content" value=""></textarea>
								</div>
						</div>
				    </div>
				    <div class="col-sm-12">
				       <div class="form-group">
								<div class="col-sm-12">
									<label>achid</label>
									<select class="form-control" id="ins_achid">
									</select>
								</div>
					  </div>
				    </div>
				    
				    <div class="col-sm-12">
							<div class="form-group">
								<div class="col-sm-6" style="margin-top:14px;">
									<label>type</label> <select class="form-control"
										id="ins_type">
										<option value='1'>竞距</option>
										<option value='2'>竞速</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-6">
									<label>status</label> <select class="form-control"
										id="ins_status">
										<option value='0'>未上线</option>
										<option value='1'>上线</option>
									</select>
								</div>
							</div>
					</div>
				</div>
				
				
                </div>   
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:addmotobandgpConfirm()">确认添加</button>
				</div>
			
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


<div class="modal fade" id="lookRankingModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" style="width: 870px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">前100名</h4>
				</div>
				<div class="modal-body">
					<div class="row" id='rankinglistrow' style="display: block">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">列表</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="dataTable_wrapper">
										<table
											class="table table-striped table-bordered table-hover compact"
											id="dataTables-example">
											<thead>
												<tr>
												    <th>top</th>
													<th>userid</th>
													<th>nickname</th>
													<th>score</th>
													<th>operate</th>
												</tr>
											</thead>
											<tbody id="gpuserlist">

											</tbody>
										</table>
										<!-- DataTables JavaScript -->
										<div>当前第<span id="gppageNow"></span>页/共<span id="gppageCount"></span>页</div>
										<div id="gpuserlistPage"></div>
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div class="modal fade" id="lookJoinGPCountModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">参赛情况</h4>
				</div>
				<div class="modal-body">
					<div class="row" id='rankinglistrow' style="display: block">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">数据</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									 <div class="form-group">
									         <label>参与人数：</label> 
									         <input class="form-control" placeholder=""
												id="joingpCount" value="" readonly="readonly">
											<label>完赛人数：</label> 
									        <input class="form-control" placeholder=""
												id="completedgpCount" value="" readonly="readonly">
					                 </div>
									
								</div>
								
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<!-- /.row -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
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
						aria-hidden="true" onclick="javascript:closetitleImgModel();">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择图片</h4>
				</div>
				<div class="modal-body" style="padding: 5px;">
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


	 <script src="../js/motobandgpmanage/motobandgplist.js"></script>  
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
  <!-- <script type="text/javascript">
	    $('#titleImgModel').on('hidden.bs.modal', function() {
		$('body').css("overflow","hidden");
		$('#editmotobandgpModel').css({'overflow-y':'scroll'});
		});
	    $('#editmotobandgpModel').on('hidden.bs.modal', function() {
			$('body').css("overflow-y","scroll");
			$('.modal-open').css("overflow","hidden");
		});
	</script>  -->
</body>


</html>