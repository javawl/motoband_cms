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

<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<link
	href="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"
	rel="stylesheet">

<link
	href="../bower_components/datatables-responsive/css/dataTables.responsive.css"
	rel="stylesheet">

<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">


<script src="../js/consts.js"></script>

<script src="../js/boxmanage/jquery.form.min.js"></script>

<script
	src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>

<script
	src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

<script src="../dist/js/sb-admin-2.js"></script>

<style type="text/css">
body {
	margin: 0 auto;
}

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

.testbutton {
	color: #fff;
	background-color: #337ab7;
	border-color: #2e6da4;
	border: 1px solid transparent;
	border-radius: 6px;
	text-rendering: auto;
	font-size: 14px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer
}
</style>

</head>

<body onload="">

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">活动中心消息管理管理</h1>
		</div>
	</div>


	<div class="row">
		<div class="row col-lg-2" style="margin-bottom: 5px;">
			<button type="button" class="btn btn-primary btn-lg"
				data-toggle="modal" data-target="#addbannerModel"
				onclick="javascript:addbanner()">添加消息</button>
			<input type="hidden" value="0" id="hiddenValue"> <input
				type="hidden" value="${sessionScope.user.user_guid}"
				id="userGuidhiddenValue">
		</div>
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">活动中心消息</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">

						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>taskid</th>
									<th>name</th>
									<th>createtime</th>
									<th>updatetime</th>
									<th>state</th>

									<th>title</th>
									<!-- <th>subtitle</th>
									<th>des</th> -->
									<th>linktype</th>
									<th>imgurl</th>
									<th>linkurl</th>
									<th>gpid</th>
									<th>nid</th>
									<th>keyword</th>
									<th>secondcarid</th>
									<th>miniprogramid</th>
									<th>buserid</th>

									<th>detail</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${messagetaskmodellist}" var="messagetask"
									varStatus="ids">
									<tr class="${messagetask.taskid}">

										<td>${messagetask.taskid}</td>
										<td>${messagetask.name}</td>
										<td>${messagetask.createtimeString}</td>
										<td>${messagetask.updatetimeString}</td>
										<c:choose>
											<c:when test="${messagetask.state==0}">
												<td>未完成</td>
												<%-- 												<% out.print("<script type='text/javascript'>getMsg('" + ${messagetask.taskid} + "')</script>");%> --%>
											</c:when>
											<c:when test="${messagetask.state==1}">
												<td>已完成</td>
											</c:when>
										</c:choose>

										<td>${messagetask.title}</td>
										<%-- 	<td>${messagetask.subtitle}</td>
										<td>${messagetask.des}</td> --%>
										<c:choose>
											<c:when test="${messagetask.linktype =='1'}">
												<td class="center">动态</td>
											</c:when>
											<c:when test="${messagetask.linktype =='2'}">
												<td class="center">话题</td>
											</c:when>
											<c:when test="${messagetask.linktype =='3'}">
												<td class="center">问答</td>
											</c:when>
											<c:when test="${messagetask.linktype =='4'}">
												<td class="center">有赞</td>
											</c:when>
											<c:when test="${messagetask.linktype =='5'}">
												<td class="center">内部链接</td>
											</c:when>
											<c:when test="${messagetask.linktype =='6'}">
												<td class="center">外部链接</td>
											</c:when>
											<c:when test="${messagetask.linktype =='7'}">
												<td class="center">小程序</td>
											</c:when>
											<c:when test="${messagetask.linktype =='8'}">
												<td class="center">motogp</td>
											</c:when>
											<c:when test="${messagetask.linktype =='9'}">
												<td class="center">二手车</td>
											</c:when>
											<c:when test="${messagetask.linktype =='10'}">
												<td class="center">本地商家</td>
											</c:when>
											<c:when test="${messagetask.linktype =='11'}">
												<td class="center">话题列表</td>
											</c:when>
											<c:when test="${messagetask.linktype =='12'}">
												<td class="center">讨论列表</td>
											</c:when>
											<c:otherwise>
												<td class="center">未知</td>
											</c:otherwise>
										</c:choose>
										<td><img src='${messagetask.imgurl}'
											style='width: 100px; height: auto;' /></td>
										<td style="word-break: break-all;">${messagetask.linkurl}</td>
										<td>${messagetask.gpid}</td>
										<td style="word-break: break-all;">${messagetask.nid}</td>
										<td style="word-break: break-all;">${messagetask.keyword}</td>
										<td style="word-break: break-all;">${messagetask.secondcarid}</td>
										<td>${messagetask.miniprogramid}</td>
										<td style="word-break: break-all;">${messagetask.buserid}</td>

										<td style='width: 150px; height: auto;'>
											<div>
												<font color="blue" id='${messagetask.taskid}_1'>/</font>
											</div>
											<div>
												成功：<font color="green" id='${messagetask.taskid}_2'></font>
											</div>
											<div>
												失败：<font color="red" id='${messagetask.taskid}_3'></font>
											</div>
											<div>
<%-- 												<div class="testbutton" value="预览" onclick="edit('${messagetask}')">预览</div> --%>
<%-- 												<div class="testbutton" value="预览" onclick="failagain('${messagetask.taskid}')">编辑</div> --%>
												<div class="testbutton" value="查看进度" onclick="getMsg('${messagetask.taskid}')">查看进度</div>
												<div class="testbutton" value="重试" onclick="failagain('${messagetask.taskid}')">重试</div>
												<div class="testbutton" value="强制重新推送" onclick="again('${messagetask.taskid}')">强制重新推送</div>
												

											</div>
										</td>

									</tr>
								</c:forEach>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</div>
	</div>



	<div class="modal fade" id="addbannerModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加消息</h4>
				</div>
				<div class="modal-body" style="height: 750px">

					<div class="form-group">
						<input class="form-control" type="hidden" id="ins_bannerid"
							value=""> <input class="form-control" type="hidden"
							id="ins_taskid" value="">

					</div>

					<div class="form-group">
						<input class="form-control" placeholder="任务名称" id="ins_name"
							value="">
					</div>
					<hr>

					<div class="form-group">
						<label>跳转类型</label> <select class="form-control"
							id="imageSelectType" onchange="javascript:imgTypeChange(this)">
							<option value='1'>动态</option>
							<option value='2'>话题</option>
							<option value='3'>问答</option>
							<option value='4'>有赞</option>
							<option value='5'>内部链接</option>
							<option value='6'>外部链接</option>
							<option value='7'>小程序</option>
							<option value='8'>motogp</option>
							<option value='9'>二手车</option>
							<option value='10'>本地商家</option>
							<option value='11'>话题列表</option>
							<option value='12'>讨论列表</option>
						</select>
						<div>
							<div id="div1">
								请输入nid：<input class="form-control" placeholder=""
									id="inputUrlnid">
							</div>
							<div id="div2" style="display: none;">
								请输入keyword：<input class="form-control" placeholder=""
									id="inputUrlkeyword">
							</div>
							<div id="div3" style="display: none;">
								请输入linkurl：<input class="form-control" placeholder=""
									id="inputUrllinkurl">
							</div>
							<div id="div4" style="display: none;">
								请输入miniprogramid：<input class="form-control" placeholder=""
									id="inputUrlminiprogramid">
							</div>
							<div id="div5" style="display: none;">
								请输入gpid：<input class="form-control" placeholder=""
									id="inputUrlgpid">
							</div>
							<div id="div6" style="display: none;">
								请输入secondcarid：<input class="form-control" placeholder=""
									id="inputUrlsecondcarid">
							</div>
							<div id="div7" style="display: none;">
								请输入buserid：<input class="form-control" placeholder=""
									id="inputUrlbuserid">
							</div>
						</div>
					</div>


					<div class="form-group">
						<div class="col-sm-6">
							<input class="form-control" placeholder="标题" id="ins_title"
								value="">
						</div>
						<div class="col-sm-6" style='display: none;'>
							<input class="form-control" placeholder="副标题" id="ins_subtitle"
								value="">
						</div>
					</div>

					<div class="form-group">
						<br></br>
					</div>

					<div class="form-group">
						<textarea class="form-control" placeholder="描述" id="ins_des"
							rows="3" style="word-break: break-all"></textarea>
					</div>



					<div class="form-group">
						<div class="col-sm-6">
							<label><font color="red">请选择750x350的图片</font></label>
							<div>
								<input id="" type="button" value="设置banner图片"
									class="btn btn-info" data-toggle="modal"
									data-target="#titleImgModel"> <input type="text"
									value="" id="titleimgadd"> <img id="titleimgshowadd"
									src="" style="width: 280px; height: 144px" />
							</div>
						</div>
						<div class="col-sm-6">
							<label>选择要发送的用户</label> <select class="form-control"
								id="userSelectType" onchange="javascript:userTypeChange(this)">
								<option value='1'>全量用户</option>
								<option value='2'>指定用户</option>
							</select>

							<div id="exceldiv" style="display: none;">
								<form action="../motouser/batchimport" method="post"
									enctype="multipart/form-data" target='ifr'
									onsubmit="return check();">
									<div style="margin: 30px;">
										<input id="excel_file" type="file" name="filename"
											accept="xlsx" size="80" onclick="javascript:clearfilemsg()" />
										<br></br> <input id="excel_button" type="submit"
											value="上传Excel" />

									</div>
									<font id="importMsg" color="red" style="margin: 30px;"></font><input
										type="hidden" />

								</form>
								<iframe name='ifr' id="ifr" style='display: none;'></iframe>
							</div>

						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-12">
							<font color='red'>注意：指定用户时 先选择excel文件 再上传Excel文件 所有信息填写完毕
								再提交任务 任务一经提交便开始发送消息 不可撤回 请谨慎操作！！！</font>
						</div>
					</div>
					<div class="form-group">
						<input type="text" placeholder="任务执行时间" id="ins_start_time"
							value="">
					</div>



					<!--     <button type="button" class="btn btn-primary" id="savebtn"  onclick="javascript:addbannerConfirm()">提交</button> -->


				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="javascript:addbannerConfirm()">提交任务</button>
				</div>
			</div>
		</div>
	</div>






	<!-- Modal -->
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
					<div id="back"
						style="display: none; POSITION: fixed; z-index: 1000; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.6); filter: alpha(opacity = 60)">
						<h3>
							<font color="white">上传中，请耐心等待</font>
						</h3>
					</div>
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
						id="closeModel">关闭</button>
					<button type="button" class="btn btn-primary" id="savebtn"
						data-dismiss="modal" onclick="javascript:insertTitleImg()">选择图片</button>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/laydate/laydate.js"></script>
	<script type="text/javascript">
		var iframe = document.getElementById("ifr");
		iframe.onload = function() {
			var bodycontent = iframe.contentDocument.body.textContent;
			var json = eval("(" + bodycontent + ")");
			$("#importMsg").text(json.msg);
			$("#ins_taskid").val(json.taskid);

		}
		//时间选择器
		laydate.render({
			elem : '#ins_start_time',
			type : 'datetime'
		});
	</script>



	<script src="../js/motouser/sendSystemMessage.js"></script>
	<script src="../js/mallmanage/uploadGroupImage2.js"></script>
	<script type="text/javascript">
		$('#titleImgModel').on('hidden.bs.modal', function() {
			$('#addbannerModel').css({
				'overflow-y' : 'scroll'
			});
		});
	</script>
</body>


</html>