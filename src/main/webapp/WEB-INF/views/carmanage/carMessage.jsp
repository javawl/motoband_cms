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
    <style>
    body {
	width: 700px;
	margin: 0 auto;
}
    </style>
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">摩托车管理</h1>
		</div>
		<!-- /.col-lg-12 -->
		<div class="form-group" style="padding-left: 2%">
			<h3 class="page-header">
				摩托车：${carModel.name} <input class="form-control" id="car_id"
					value="${carModel.modelid}" style="display: none">
				<%-- <input class="form-control"
					id="role_name" value="${role_name}" style="display: none">  --%>
			</h3>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-info">
				<div class="panel-heading">摩托车信息</div>
				<div class="panel-body">
					<div class="form-group">
						<p class="text-primary">摩托车名</p>
						<input class="form-control" placeholder="车名" id="newcarname"
							value="${carModel.name}"
							onblur="javascript:checkCarNewName('${carModel.name}')">
						<p class="text-danger" id="dangertext" style="display: none">摩托车名已被使用！</p>
					</div>
					<div class="form-group">
						<p class="text-primary">排量</p>
						<input class="form-control" placeholder="排量" id="car_cc"
							value="${carModel.cc}">
					</div>
					<div class="form-group">
						<p class="text-primary">类型：</p>
						<select class="form-control" id='typeselect'>
							<c:forEach items="${mototypelist}" var="mototype" varStatus="ids">
								<c:choose>
									<c:when test="${mototype.typeid==carModel.typeid }">
										<option value="${mototype.typeid}" selected>${mototype.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${mototype.typeid}">${mototype.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<option value="0"
								<c:if test="${carModel.typeid=='0'}">selected</c:if>>未知</option>
						</select>
					</div>
					<div class="form-group">
						<p class="text-primary">品牌</p>
						<select class="form-control" id='brandselect'>
							<c:forEach items="${motobrandlist}" var="motobrand"
								varStatus="ids">
								<c:choose>
									<c:when test="${motobrand.brandid==carModel.brandid }">
										<option value="${motobrand.brandid}" selected>${motobrand.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${motobrand.brandid}">${motobrand.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>

						</select>
					</div>

				</div>
				<!-- body -->
			</div>
			<div class="panel-footer">
				<button type="button" class="btn btn-primary"
					onclick="javascript:saveCarMessage()" id="savebtn">保存该车辆信息</button>

			</div>
		</div>
	</div>
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/carmanage/carmanage.js"></script>
  
</body>
</html>