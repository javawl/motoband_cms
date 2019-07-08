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
	var inputuserid =$.trim($("#inputuserid").val());
	var inputorder_no =$.trim($("#inputorder_no").val());
	var state= $("#stateSelect").val();
	var starttime= $("#starttime").val();
	if(starttime != null && starttime !=''){
		starttime=new Date(starttime).getTime();
	}
	
	var endtime= $("#endtime").val();
	if(endtime != null && endtime !=''){
		endtime=new Date(endtime).getTime();
	}
	
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&userid="+inputuserid+"&order_no="+inputorder_no+"&state="+state+"&starttime="+starttime+"&endtime="+endtime);	
}
function inputPageGoto(url, adminGuid,totalPage){
	var inputPage=$("#inputPage").val();
	var reg=/^[1-9]\d*$/;
	if(reg.test(inputPage)==true && (parseInt(inputPage)<=parseInt(totalPage))){
		var limit= $("#pageSizeSelect").val();
		var order= $("#triangleHidden").val();
		var inputuserid =$.trim($("#inputuserid").val());
		var inputorder_no =$.trim($("#inputorder_no").val());
		var state= $("stateSelect").val();
		var starttime= $("#starttime").val();
		if(starttime != null && starttime !=''){
			starttime=new Date(starttime).getTime();
		}
		
		var endtime= $("#endtime").val();
		if(endtime != null && endtime !=''){
			endtime=new Date(endtime).getTime();
		}
		$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+inputPage+"&limit="+limit+"&order="+order+"&userid="+inputuserid+"&order_no="+inputorder_no+"&state="+state+"&starttime="+starttime+"&endtime="+endtime);
	   
	}else{
	    alert("页数不合法，请输入合法的页数");
	   return false;
	}
}
function changeTriangle(url, adminGuid,page,order){

	var limit= $("#pageSizeSelect").val();
	var inputuserid =$.trim($("#inputuserid").val());
	var inputorder_no =$.trim($("#inputorder_no").val());
	var state= $("stateSelect").val();
	var starttime= $("#starttime").val();
	if(starttime != null && starttime !=''){
		starttime=new Date(starttime).getTime();
	}
	
	var endtime= $("#endtime").val();
	if(endtime != null && endtime !=''){
		endtime=new Date(endtime).getTime();
	}
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+ "&page="+page+"&limit="+limit+"&order="+order+"&userid="+inputuserid+"&order_no="+inputorder_no+"&state="+state+"&starttime="+starttime+"&endtime="+endtime);


}
function gotoPaylog(url, adminGuid,orderid){
	$("#page-wrapper").load(url + "?userGuid=" + adminGuid+"&page=1&limit=20&order=0&inputorder_id="+orderid);
}
function imgtoNewPage(obj){
	var src = $(obj).attr("src");
	window.open(src);
}
</script>
</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">保单管理</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>


	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">保单列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
			 <div class="form-group"> 
					<div class="col-lg-4" style="padding-left: 0; margin-bottom: 15px">
                      <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">
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
           </div>
            <div class="form-group"> 
					<div class="col-lg-4">
					<label>选择查询类型：</label>
						<select class="form-control" id="idAndOrderSeclect" onchange="javascript:idAndOrderChange(this)">
							<option value='0'>用户id</option>
							<option value='1'>保单号</option>
						</select>

					</div>
			</div>	
					<div class="form-group"> 
						    <div class="col-lg-4" style="padding-left: 0;">
						    <label>选择付款类型：</label>
						      <select class="form-control" id="stateSelect"  onchange="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">
						       
						       <c:choose>
						         <c:when test="${state=='0'}">
						            <option value='-1'>全部</option>
						            <option value='0' selected>待付款</option>
						            <option value='1'>已付款</option>
						            <option value='2'>已退款</option>
						         </c:when>
						         <c:when test="${state=='1'}">
						            <option value='-1'>全部</option>
						            <option value='0'>待付款</option>
						            <option value='1' selected>已付款</option>
						            <option value='2'>已退款</option>
						         </c:when>
						         <c:when test="${state=='2'}">
						          <option value='-1'>全部</option>
						           <option value='0'>待付款</option>
						           <option value='1'>已付款</option>
						           <option value='2' selected >已退款</option>
						         </c:when>
						       <c:otherwise>
						          <option value='-1'>全部</option>
						          <option value='0'>待付款</option>
						          <option value='1'>已付款</option>
						          <option value='2'>已退款</option>
						       </c:otherwise>
						      </c:choose>
						      </select>
						 
						    </div>
                      </div>
				<div class="form-group">
				  <div class="col-lg-4">
				    <div id="idAndOrder0">
					    <div class="col-lg-10">
						<input style="margin-top:5px" class="form-control" placeholder="请输入用户ID" id="inputuserid"/>
					   </div>
                       <div class="col-lg-2" >
						<button type="button" style="margin-top:5px" class="btn btn-primary btn-lg"
							onclick="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">查询</button>
					  </div>
				  </div>
			 
			      <div id="idAndOrder1" style="display:none;">
					<div class="form-group col-lg-10">
						<input style="margin-top:5px" class="form-control" placeholder="请输入保单号" id="inputorder_no"/>
					</div>
					<div class="col-lg-2">
						<button type="button" style="margin-top:5px" class="btn btn-primary btn-lg"
								onclick="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">查询</button>
					</div>
				</div> 
		   </div>
		</div>
	
						 <%-- <div class="form-group"> 
						    <div class="col-sm-3">
						      <input type="text" class="form-control" id="inputuserid" placeholder="请输入用户id">
						      
						    </div>
						    <button type="button" class="btn btn-success" onclick="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">查询</button>
                        </div> 
  
                       <div class="form-group"> 
						    <div class="col-sm-3">
						      <input type="text" class="form-control" id="inputorder_no" placeholder="请输入保单号">
						    </div>
						    <button type="button" class="btn btn-success" onclick="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">查询</button>
                     </div> --%>
    
                    <div class="form-group col-lg-12"> 
					   <div class="col-sm-3" style="padding-left: 0;margin-bottom: 15px">
					    <label>choose starttime：</label>
					  <div class="input-append date form_datetime" onclick="javascript:showTimeForm(this)" >
						<input size="16" type="text" class="form-control" placeholder="" id="starttime"
							value="${starttime}" readonly="readonly" style="cursor: pointer;">
							<span class="add-on"><i class="icon-th"></i></span>
					  </div>
                    </div>
					<div class="col-sm-3">
					    <label>choose endtime：</label>
					  <div class="input-append date form_datetime" onclick="javascript:showTimeForm(this)" >
						<input size="16" type="text" class="form-control" placeholder="" id="endtime"
							value="${endtime}" readonly="readonly" style="cursor: pointer;">
							<span class="add-on"><i class="icon-th"></i></span>
							
					  </div>
					 
                    </div>
                    <div class="col-sm-3" style="padding-left: 0;margin-top: 28px"><button type="button" class="btn btn-success" onclick="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">查询</button></div>
                  </div> 
                 
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>payid<span>&nbsp</span>
									<c:choose>
									  <c:when test="${order== 0}">
									     <a  class="down" href="javascript:changeTriangle('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1','1')" style="text-decoration:none;">▼</a>
									     <input id="triangleHidden" type="hidden" value="0"/>
									  </c:when>
									  <c:otherwise>
									     <a id="triangle" class="up" href="javascript:changeTriangle('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1','0')"   style="text-decoration:none;">▲</a>
									     <input id="triangleHidden" type="hidden" value="1"/>
									  </c:otherwise>
									</c:choose>
									</th>
									<th>userid</th>
									<th>userName</th>
									<th>userIDInfo</th>
									<th>plateNumber</th>
									<th>order_no</th>
									<th>packagename</th>
									<th>price</th>
									<th>ordertime</th>
									<th>orderstate</th>
									<th>state</th>
									<th>policyStatus</th>
									<th>businesscode</th>
									<th>channel</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${payorderlist}" var="paylist"
									varStatus="ids">
									<tr class="${paylist.payid}">
									    <td>${paylist.payid }</td>
										<td>${paylist.userid}</td>
										<td>${paylist.userName}</td>
										<td>${paylist.userIDInfo}</td>
										<td>${paylist.plateNumber}</td>
										<td><a href="javascript:gotoPaylog('/payordermanage/payorderloglist','${sessionScope.user.user_guid}','${paylist.order_no}')">${paylist.order_no}</a></td>
										<td>${paylist.packagename}</td>
										<td>${paylist.price/100}</td>
										<td>${paylist.ordertimeString }</td>
										<c:choose>
										  <c:when test="${paylist.orderstate == 0 }">
										    <td>待处理</td>
										  </c:when>
										  <c:when test="${paylist.orderstate == 1 }">
										    <td>成功</td>
										  </c:when>
										  <c:when test="${paylist.orderstate == 2 }">
										    <td>失败</td>
										  </c:when>
										</c:choose>
										
										<c:choose>
										  <c:when test="${paylist.state == 0 }">
										    <td>待付款</td>
										  </c:when>
										  <c:when test="${paylist.state == 1 }">
										    <td>已付款</td>
										  </c:when>
										  <c:when test="${paylist.state == 2 }">
										    <td>已退款</td>
										  </c:when>
										</c:choose>
										
										<c:choose>
										  <c:when test="${paylist.policyStatus == 1 }">
										    <td>核保——提交资料</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 2 }">
										    <td>撤单</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 3 }">
										    <td>核保——核保中</td>
										    </c:when>
										    <c:when test="${paylist.policyStatus == 4 }">
										    <td>核保——核保失败</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 5 }">
										    <td>核保——核保成功</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 6 }">
										    <td>承保——承保成功未生效</td>
										     </c:when>
										    <c:when test="${paylist.policyStatus == 7 }">
										    <td>生效</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 8 }">
										    <td>失效</td>
										  </c:when>
										 <%--  <c:when test="${paylist.policyStatus == 9 }">
										    <td>理赔——核赔中</td>
										     </c:when>
										    <c:when test="${paylist.policyStatus == 10 }">
										    <td>理赔——拒赔</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 11 }">
										    <td>理赔——同意理赔</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 12 }">
										    <td>理赔——待支付理赔金</td>
										     </c:when>
										    <c:when test="${paylist.policyStatus == 13 }">
										    <td>终止——理赔终止</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 14 }">
										    <td>终止——过期终止</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 15 }">
										    <td>终止——违约终止</td>
										     </c:when>
										    <c:when test="${paylist.policyStatus == 16 }">
										    <td>终止——投保人解除终止</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 17 }">
										    <td>终止——其他终止</td>
										  </c:when>
										  <c:when test="${paylist.policyStatus == 18 }">
										    <td>失效</td>
										     </c:when> --%>
										   <c:otherwise>
										       <td>未知</td>
										   </c:otherwise>
										</c:choose>
										<td>${paylist.businesscode }</td>
										<td>${paylist.channel }</td>
										<td class="center">
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#lookCarInfo" onclick="javascript:lookCar('${paylist.payid}')">查看车辆信息</button>
											<button type="button" class="btn btn-success" data-toggle="modal" data-target="#lookUserInfo" onclick="javascript:lookUser('${paylist.payid}')">查看用户信息</button>
											<c:if test="${paylist.state != 0 }">
											  <button type="button" class="btn btn-info" data-toggle="modal" data-target="#lookInsuranceInfo" onclick="javascript:lookInsurance('${paylist.order_no}')">查看保单信息</button> 
											</c:if>
											<c:choose>
												<c:when test="${updateCheck=='lock'}">
												    <c:choose>
												       <c:when test="${paylist.state != 0 }">
												          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#changePolicyStatusModel" onclick="javascript:changePolicyStatus('${paylist.order_no}','${paylist.policyStatus}')">修改保单状态</button> 
												       </c:when>
												    
												    </c:choose>
												</c:when>
											</c:choose>
											
											<button type="button" class="btn btn-success"  onclick="javascript:refreshpolicystatus('${paylist.payid}','${paylist.userid}','${paylist.order_no}')">刷新保单状态</button>
											
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
				<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			      
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/payordermanage/payorderlist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
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
	<div class="modal fade" id="lookCarInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">车辆信息</h4>
				</div>
				<div class="modal-body" style="height:1600px">
					<div class="form-group">
					    <div class="col-sm-6">
					    <label>品牌</label>
						<input class="form-control" placeholder="" id="brand"
							value="" readonly="readonly">
						</div>
					</div>

					<div class="form-group">
					   <div class="col-sm-6">
					    <label>随机码</label>
						 <input class="form-control" placeholder="" id="carCheckCode"
							value="" readonly="readonly">
					     </div>
					</div>
                    <div class="form-group">
                     <div class="col-sm-6">
                     <label>排量</label>
						<input class="form-control" placeholder="" id="displacement"
							value="" readonly="readonly">
					  </div>
					</div>

					<div class="form-group">
					 <div class="col-sm-6">
					    <label>enginNumber</label>
						<input class="form-control" placeholder="" id="enginNumber"
							value="" readonly="readonly">
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车型</label>
						<input class="form-control" placeholder="" id="model"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车牌号</label>
						<input class="form-control" placeholder="" id="plateNumber"
							value="" readonly="readonly">
					</div>
					</div>
                   
					
					
					<div class="form-group">
					<div class="col-sm-6">
					<label>保单价</label>
						<input class="form-control" placeholder="" id="purchasePrice"
							value="" readonly="readonly">
					</div>
					</div>
                    <div class="form-group">
                    <div class="col-sm-6">
                    <label>购车时间</label>
						<input class="form-control" placeholder="" id="purchaseTime"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>注册时间</label>
						<input class="form-control" placeholder="" id="regdate"
							value="" readonly="readonly">
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车辆识别码</label>
						<input class="form-control" placeholder="" id="vin"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					  <label>身份证正面照片</label>
					  <div><img id="identityPositivePhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>
					</div>
                    <div class="form-group">
                    <div class="col-sm-6">
                    <label>身份证反面照片</label>
						<div><img id="identityNegativePhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>行驶本照片</label>
						<div><img id="drivingPhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>		
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					 <label>购车发票照片</label>
						<div><img id="invoicePhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>		
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车身左前照</label>
						<div><img id="carLeftFrontPhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>		
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车身右前照</label>
						<div><img id="carRightFrontPhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车身左后照</label>
						<div><img id="carLeftBehindPhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>	
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					<label>车身右后照</label>
						<div><img id="carRightBehindPhoto" src="" width="250px" height="250px" onclick="javascript:imgtoNewPage(this)"></img></div>
					</div>
					</div>
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
	<!-- /.modal -->
	
	<div class="modal fade" id="lookUserInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">用户信息</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
					    <label>motoband用户id</label>
						<input class="form-control" placeholder="" id="userKey"
							value="" readonly="readonly">
					</div>

					<div class="form-group">
					    <label>用户名</label>
						<input class="form-control" placeholder="" id="userName"
							value="" readonly="readonly">
					</div>
                    <div class="form-group">
                    <label>手机号</label>
						<input class="form-control" placeholder="" id="userPhone"
							value="" readonly="readonly">
					</div>

					<div class="form-group">
					    <label>身份证号</label>
						<input class="form-control" placeholder="" id="userIDInfo"
							value="" readonly="readonly">
					</div>
					
					<div class="form-group">
					    <label>用户邮箱</label>
						<input class="form-control" placeholder="" id="userEmail"
							value="" readonly="readonly">
					</div>
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
	<!-- /.modal -->
	
	
	<div class="modal fade" id="lookInsuranceInfo" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">保单信息</h4>
				</div>
				<div class="modal-body" style="height:450px">
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保单ID</label>
						<input class="form-control" placeholder="" id="iid"
							value="" readonly="readonly">
					</div>
					</div>

					<div class="form-group">
					<div class="col-sm-6">
					    <label>悟空保单号</label>
						<input class="form-control" placeholder="" id="channelOrderNo"
							value="" readonly="readonly">
					</div>
					</div>
                    <div class="form-group">
                    <div class="col-sm-6">
                    <label>产品号</label>
						<input class="form-control" placeholder="" id="productNo"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保单号</label>
						<input class="form-control" placeholder="" id="policyNo"
							value="" readonly="readonly">
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保险码</label>
						<input class="form-control" placeholder="" id="insuranceCode"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>policyholder</label>
						<input class="form-control" placeholder="" id="policyholder"
							value="" readonly="readonly">
					</div>
					</div>
                    <div class="form-group">
                    <div class="col-sm-6">
                    <label>被保人名</label>
						<input class="form-control" placeholder="" id="userName1"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>创建时间</label>
						<input class="form-control" placeholder="" id="createTime"
							value="" readonly="readonly">
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>起保时间</label>
						<input class="form-control" placeholder="" id="policyBeginDate"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保单结束时间</label>
						<input class="form-control" placeholder="" id="policyEndDate"
							value="" readonly="readonly">
					</div>
					</div>
                    <div class="form-group">
                    <div class="col-sm-6">
                    <label>价格</label>
						<input class="form-control" placeholder="" id="premium"
							value="" readonly="readonly">
					</div>
                    </div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保额</label>
						<input class="form-control" placeholder="" id="suminsured"
							value="" readonly="readonly">
					</div>
					</div>
					<div class="form-group">
					<div class="col-sm-6">
					    <label>保单状态</label>
						<input class="form-control" placeholder="" id="policyStatus"
							value="" readonly="readonly">
					</div>
					</div>
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
	<!-- /.modal -->
	
	<div class="modal fade" id="changePolicyStatusModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改PolicyStatus状态</h4>
				</div>
				<div class="modal-body">
				    <input id="policyStatusHidden"  type="hidden" value=""/>
				    <input id="GuidHidden" type="hidden" value='${sessionScope.user.user_guid}'>
					<div class="form-group">
					    <label>请选择PolicyStatus状态</label>
						<select class="form-control"  id="updatepolicyStatus" >
							<option value='1' >核保——提交资料</option>
							<option value='2'>撤单</option>
							<option value='3' >核保——核保中</option>
							<option value='4'>核保——核保失败</option>
							<option value='5' >核保——核保成功</option>
							<option value='6'>承保——承保成功未生效</option>
							<option value='7' >生效</option>
							<option value='8'>失效</option>
							<!-- <option value='9' >理赔——核赔中</option>
							<option value='10'>理赔——拒赔</option>
							<option value='11' >理赔——同意理赔</option>
							<option value='12'>理赔——待支付理赔金</option>
							<option value='13' >终止——理赔终止</option>
							<option value='14'>终止——过期终止</option>
							<option value='15' >终止——违约终止</option>
							<option value='16'>终止——投保人解除终止</option>
							<option value='17' >终止——其他终止</option>
							<option value='18'>失效</option> -->
							
						</select>
					</div>

			</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:changePolicyStatusConfirm()">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
		
	<script src="../js/payordermanage/payorderlist.js"></script> 
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