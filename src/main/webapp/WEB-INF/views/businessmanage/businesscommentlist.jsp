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
  function lookcommentlist(adminGuid){
	  var buserid=$("#buserSelect").val();
	  $("#commentlistdiv").load("/businessmanage/businesscommentlistinfo?userGuid=" + adminGuid+ "&page=1&limit=20&order=0&buserid="+buserid);

  }
</script>

</head>

<body >
   <div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">商家评论管理</h1>
		</div>
	</div>

	<div class="row" >
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading"><b>商家评论列表 </b><span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp选择商家:</span>
				              <select  id="buserSelect" >
								    <c:forEach items="${buserlist}" var="ugmodel" varStatus="ids">
										<option value="${ugmodel.userid}">${ugmodel.name}</option>
								     </c:forEach>
							  </select>
							  <button type="button" class="btn btn-primary btn-lg"
													onclick="javascript:lookcommentlist('${userGuid}')">查看评论</button>
							</div>
				
				<div class="panel-body" id="commentlistdiv">
					
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	

	<script src="../js/news/dataversion.js"></script>
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
    <script src="../js/consts.js"></script>

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