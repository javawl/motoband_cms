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


<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />
	
	
<!-- <script src="../bower_components/jquery/dist/jquery.min.js"></script> -->	
<script src="../js/wysi/bootstrap-wysiwyg.js" type="text/javascript"></script>
<script src="../js/wysi/jquery.hotkeys.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css"
	href="../css/boxmanage/addNewBoxPage.css" />
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
.k {
	display: none;
}

.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	float: left;
	clear: left;
	padding: 0 0 0 5px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 45%;
}

.ui-timepicker-div td {
	font-size: 90%;
}

.ui-tpicker-grid-label {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

.ui-timepicker-rtl {
	direction: rtl;
}

.ui-timepicker-rtl dl {
	text-align: right;
	padding: 0 5px 0 0;
}

.ui-timepicker-rtl dl dt {
	float: right;
	clear: right;
}

.ui-timepicker-rtl dl dd {
	margin: 0 45% 10px 10px;
}

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


<script src="../js/consts.js"></script>
<script type="text/javascript">

function pageGoto(url, adminGuid,page){ 
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	if(order==''||order == null){
		order=0;
	}	
	var orderConditions= $("#triangleHiddenValue").val();
  
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
			<h1 class="page-header">用车首页二手车搜索专区管理</h1>
		</div>
	</div>

	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">二手车搜索专区列表
				&nbsp&nbsp<button type="button" class="btn btn-info"   data-toggle="modal" data-target="#editscmzModel"  onclick="javascript:addScmz()">添加专区</button>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
			           	<div class="col-sm-3" style="padding-left: 0; margin-bottom: 15px">
                          <div>
							  <label>Choose  pageSize: </label>
							  <select class="form-control" id="pageSizeSelect"  onchange="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','1')">
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
						     <input id="userGuidHidden" type="hidden" value="${sessionScope.user.user_guid}">
							 <input id="hiddenValue" type="hidden" value="">
							  
                         </div>
                         
						<table
							class="table table-striped table-bordered table-hover compact topictable"
							id="dataTables-example">
							<thead>
								<tr>
									<th>scmzid</th>
									<th>title</th>
									<th>picurl</th>
									<th>searchsecondcarstr</th>
									<th>state</th>
									<th>orderindex</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${secondcarmainzonelist}" var="scmzmodel" varStatus="ids">
									<tr class="${scmzmodel.scmzid}">
									   <td>${scmzmodel.scmzid}</td>
									   <td>${scmzmodel.title}</td>
									    <td><img src='${scmzmodel.picurl}!thumb' width='100px' height='100px'></td>
									    <td style="word-wrap: break-word;word-break: break-all;">${scmzmodel.searchsecondcarstr}</td>
									    <c:choose>
									       <c:when test="${scmzmodel.state=='0'}">
									          <td>下线</td>
									       </c:when>
									       <c:when test="${scmzmodel.state=='1'}">
									          <td>上线</td>
									       </c:when>
									       <c:otherwise>
									          <td>未知</td>
									       </c:otherwise>
									    </c:choose>
									   <td>${scmzmodel.orderindex}</td>
									   
										<td class="center">
										    <button type="button" class="btn btn-primary btn-lg"
										               data-toggle="modal" data-target="#editscmzModel"
														onclick="javascript:editScmz('${scmzmodel.scmzid}')">编辑</button>
									
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
				<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','1')">首页</a>
				<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.page-1}')">上一页</a>
			</c:if>
			
			<c:choose>
			   <c:when test="${pageBean.totalPage >10}">
			     
			       <c:forEach var="i" begin="1" end="5">
				          <c:choose>
				          <c:when test="${pageBean.page != i }">
					          <a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
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
					          <a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
				          </c:when>
						  <c:otherwise>
						      <span class="btn btn-danger disabled">${i}</span>
						  </c:otherwise>
				          </c:choose>
			      </c:forEach>
			      <c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
			     </c:if>
			   </c:when>
			   <c:otherwise>
			        <c:forEach var="i" begin="1" end="${pageBean.totalPage }">
					<c:choose>
					  <c:when test="${pageBean.page != i }">
						<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${i}')">${i}</a>
					  </c:when>
					  <c:otherwise>
					      <span class="btn btn-danger disabled">${i}</span>
					  </c:otherwise>
					 </c:choose>
					</c:forEach>
					<c:if test="${pageBean.page  != pageBean.totalPage}">
						<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.page+1}')">下一页</a>
						<a class="btn btn-primary" href="javascript:pageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')">尾页</a>
					</c:if>
			   </c:otherwise>
			</c:choose>
			<span>&nbsp输入页数：</span>
			<span><input type="text" id="inputPage" size="5" />&nbsp<button type="button"  class="btn btn-success btn-lg" onclick="javascript:inputPageGoto('/secondcar/secondcarmainzonelist','${sessionScope.user.user_guid}','${pageBean.totalPage}')" >跳转</button></span>
	
		
	</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	

	<div class="modal fade" id="editscmzModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">二手车专区详情</h4>
				</div>
				<div class="modal-body" style="height:870px">
				<div class="form-group">
				<input class="form-control" placeholder="" id="addscmzid"
							value="" type="hidden">
				</div>
				<div class="form-group">
					<div class="col-sm-6">
					   <label>专区标题(名称)</label>
				        <input class="form-control" placeholder="请输入专区标题" id="addscmztitle"
							value="">	
					</div>
					<div class="col-sm-6">
					  <label>搜索标题</label>
				        <input class="form-control" placeholder="请输入搜索标题" id="addtitle"
							value="">	
					</div>
				</div>
				
				
				<div class="form-group">
				<label>城市</label>
				<input class="form-control" placeholder="请输入city" id="addcity"
							value="">	
				</div>
				<div class="form-group">
				<label>车型</label>
				<input class="form-control" placeholder="请输入车型" id="addstyle"
							value="">	
				</div>
				<div class="form-group">
				<label>关键字</label>
				<input class="form-control" placeholder="请输入关键字" id="addkeyword"
							value="">	
				</div>
				<div class="form-group">
					<div class="col-sm-4">
						<label>大品牌id</label> <input class="form-control"
							placeholder="请输入大品牌id" id="addbpid" value="">
					</div>
					<div class="col-sm-4">
						<label>小品牌id</label> <input class="form-control"
							placeholder="请输入小品牌id" id="addbrandid" value="">
					</div>
					<div class="col-sm-4">
						<label>车型id</label> <input class="form-control"
							placeholder="请输入车型id" id="addmodelid" value="">
					</div>
				</div>
				
				<div class="form-group">
					<div class="col-sm-6">
						<label>最小价格</label> <input class="form-control"
							placeholder="请输入最小价格" id="addminprice" value="">
					</div>
					<div class="col-sm-6">
						<label>最大价格</label> <input class="form-control"
							placeholder="请输入最大价格" id="addmaxprice" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label>最小车龄</label> <input class="form-control"
							placeholder="请输入最小车龄" id="addminage" value="">
					</div>
					<div class="col-sm-6">
						<label>最大车龄</label> <input class="form-control"
							placeholder="请输入最大车龄" id="addmaxage" value="">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-6">
						<label>最小里程</label> <input class="form-control"
							placeholder="请输入最小里程" id="addminkilometer" value="">
					</div>
					<div class="col-sm-6">
						<label>最大里程</label> <input class="form-control"
							placeholder="请输入最大里程" id="addmaxkilometer" value="">
					</div>
				</div>
				
				<div class="form-group">
				 <div class="col-sm-12">
                        <label>排序:</label>
						<input type="radio" class="" placeholder="" name="addstore"
							value="1" >最新发布
						<input type="radio" class="" placeholder="" name="addstore"
							value="2" >价格最低
						<input type="radio" class="" placeholder="" name="addstore"
							value="3" >价格最高
						<input type="radio" class="" placeholder="" name="addstore"
							value="4" >车龄最短
						<input type="radio" class="" placeholder="" name="addstore"
							value="5" >里程最少
					</div>
				 </div>
				
				<div class="form-group">
				 <div class="col-sm-12">
                        <label>手续情况:</label>
						<input type="radio" class="" placeholder="" name="addprocedure"
							value="0" >不限
						<input type="radio" class="" placeholder="" name="addprocedure"
							value="1" >正规
						<input type="radio" class="" placeholder="" name="addprocedure"
							value="2" >其他
					</div>
				 </div>
				<div class="form-group">
				 <div class="col-sm-12">
                        <label>是否老司机车评:</label>
						<input type="radio" class="" placeholder="" name="addhasolddrivercomment"
							value="0" >不限
						<input type="radio" class="" placeholder="" name="addhasolddrivercomment"
							value="1" >否
						<input type="radio" class="" placeholder="" name="addhasolddrivercomment"
							value="2" >是
					</div>
				 </div>
				<div class="form-group">
				 <div class="col-sm-12">
                        <label>是否摩托邦官方:</label>
						<input type="radio" class="" placeholder="" name="addhasofficial"
							value="0" >不限
						<input type="radio" class="" placeholder="" name="addhasofficial"
							value="1" >否
						<input type="radio" class="" placeholder="" name="addhasofficial"
							value="2" >是
					</div>
				 </div>
				<div class="form-group">
				 <div class="col-sm-12">
                        <label>个人车源或者商家车源:</label>
						<input type="radio" class="" placeholder="" name="addcreateusertype"
							value="0" >不限
						<input type="radio" class="" placeholder="" name="addcreateusertype"
							value="1" >个人
						<input type="radio" class="" placeholder="" name="addcreateusertype"
							value="2" >商家
					</div>
				 </div>
				
				
				<div class="form-group">
					<div class="col-sm-12">
					    <label>图片(必选)</label>
					    <input id="" type="button" value="设置图片" class="btn btn-info"
							data-toggle="modal" data-target="#titleImgModel" onclick="javascript:titleimgonclick('1')" > <input
							type="text" value="" id="titleimg"> <img
							id="titleimgshow" src="" style="width: 144px; height: 144px" />
					</div>
				</div>
					
					
				 <div class="form-group">
				 <div class="col-sm-6" >
                        <label>状态(必填):</label>
						<input type="radio" class="" placeholder="" name="addstate"
							value="0" >下线
						<input type="radio" class="" placeholder="" name="addstate"
							value="1" >上线
					</div>
				 <div class="col-sm-6" >
                        <div style="float: left;"><label>顺序:</label></div>
						<div style="float: left;"><input class="form-control" placeholder="请输入顺序，必须数字" id="addorderindex"
							value=""></div>	
					</div>
				 </div>
				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" 
						onclick="javascript:editScmzConfirm()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	
	<!-- <div class="modal fade" id="addhottribalModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加到热门</h4>
				</div>
				<div class="modal-body" >
				<div class="form-group">
				<input class="form-control" placeholder="" id="addhot_tribalid"
							value="" type="hidden">
				<label>score(填数字，用于倒序排序，建议时间戳)</label>
				<input class="form-control" placeholder="请输入score" id="addscore"
							value="">	
				</div>
				</div>
			
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addhottribalConfirm()">确认</button>
				</div>
			</div>
			/.modal-content
		</div>
		/.modal-dialog
	</div> -->
			
	
	<div class="modal fade" id="titleImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" style="width: 80%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closetitleImgModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">选择图片</h4>
				</div>
				<div class="modal-body" style="padding: 5px;" >
				<div id="back1" style="display:none; POSITION:fixed; z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
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
											style="text-align: center; padding: 0px; font-size: 9pt; height: 60px;">
											<textarea   rows='2' style='word-break: break-all;' class="form-control" placeholder="图片名" name="ImgName" 
												<%-- value="${imgMessages.img_name }" --%> disabled="disabled">${imgMessages.img_name }</textarea>
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
			<!-- 	<form id="uploadForm" style="float: left">
							<input type="file" name="FileContent" class="btn btn-info"
								id="inputfile" style="float: left"></input> <input id="subbtn"
								type="submit" style="float: left" class="btn btn-info"
								style="margin-left: 0;" onclick="javascript:imgsubmit()">
						</form> -->
					
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closeModel" onclick="javascript:closetitleImgModel()">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
</div>
	
	
	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
    <script src="../js/consts.js"></script>
	<script src="../js/secondcar/secondcarmainzonelist.js"></script>
	

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
			
            $('#editscmzModel').modal({backdrop: 'static', keyboard: false, show:false}); 
	</script> 

</body>


</html>