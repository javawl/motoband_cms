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
<link href="/bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="/dist/css/timeline.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="/bower_components/morrisjs/morris.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<!-- Custom CSS -->
<link href="/dist/css/loading.css" rel="stylesheet">
</head>
<body onload="">
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.html">MotoBand CMS</a>
			</div>
			<!-- /.navbar-header -->
			<ul class="nav navbar-top-links navbar-right">


				<!-- /.dropdown -->
				<li class="dropdown" id="ownlist"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#" name="ownlist"> <i
						class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
				</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="#"><i class="fa fa-user fa-fw"></i>${ admin.user_name}</a></li>
						<li><a href="javascript:selOwnMessage('${admin.user_guid}')"><i
								class="fa fa-gear fa-fw"></i>个人信息设置</a></li>
						<li class="divider"></li>
						<li><a href="../admin/logout"><i
								class="fa fa-sign-out fa-fw"></i> 登出</a></li>
					</ul> <!-- /.dropdown-user --></li>
				<!-- /.dropdown -->
			</ul>
			<!-- /.navbar-top-links -->

			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">

						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 系统管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="user" style="display: none"><a
									href="javascript:userlist('../usermanage/userlist','${admin.user_guid}')">用户管理</a></li>
								<li id="role" style="display: none"><a
									href="javascript:rolelist('../rolemanage/rolelist','${admin.user_guid}')">角色管理</a></li>
								<li id="conf" style="display: none"><a
									href="javascript:conflist('../confmanage/conflist','${admin.user_guid}')">配置管理</a></li>

							</ul> <!-- /.nav-second-level --></li>

						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> CMS管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="imgmanage" style="display: none"><a
									href="javascript:imgmanage()">图片管理</a></li>
								<li id="dataversion" style="display: none"><a
									href="javascript:dataversionlist('../dataversion/dataversionlist','${admin.user_guid}')">版本管理</a></li>
								<li id="configdata" style="display: none"><a
									href="javascript:configdata('../configdata/configdatalist','${admin.user_guid}')">参数管理</a></li>
								<li id="clientupdate" style="display: none"><a
									href="javascript:clientupdate('../clientupdate/clientupdatelist','${admin.user_guid}')">更新管理</a></li>
							</ul></li>
						<%--   <li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								内容管理<span class="fa arrow"></span></a>
						 <ul class="nav nav-second-level">
						   <li id="box" style="display: none"><a href="javascript:boxlist('../boxmanage/boxlist','${admin.user_guid}')">手册管理</a></li>
						   <li id="boxkeyword" style="display: none"><a href="javascript:boxkeywordlist('../boxmanage/boxkeywordlist','${admin.user_guid}')">内容摘要</a></li>
						   <li id="boxbanner" style="display: none"><a href="javascript:boxbannerlist('../boxmanage/boxbannerlist','${admin.user_guid}')">内容横幅</a></li>	
					     </ul> 	
				      </li> --%>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 车型库<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="car" style="display: none"><a
									href="javascript:carlist('../carmanage/carlist','${admin.user_guid}')">车辆管理</a></li>
								<li id="newmotomodel" style="display: none"><a
									href="javascript:newmotomodellist('../carmanage/newmotomodellist','${admin.user_guid}')">新车型库管理</a></li>
								<li id="brandparent" style="display: none"><a
									href="javascript:brandparentlist('../brandparentmanage/brandparentlist','${admin.user_guid}')">大品牌管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 用户管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="motouser" style="display: none"><a
									href="javascript:motouser('../motouser/motouserlist','${admin.user_guid}')">用户管理</a></li>
								<li id="userrecommend" style="display: none"><a
									href="javascript:userrecommend('../motouser/userrecommend1','${admin.user_guid}')">精选用户</a></li>
								<li id="useroperation" style="display: none"><a
									href="javascript:useroperation('../motouser/useroperation','${admin.user_guid}')">官方运营</a></li>
								<li id="userboxapprove" style="display: none"><a
									href="javascript:userboxapprove('../motouser/userboxapprove','${admin.user_guid}')">认证用户</a></li>
								<li id="sendSystemMessage" style="display: none"><a
									href="javascript:sendSystemMessage('../motouser/sendSystemMessage','${admin.user_guid}')">活动中心消息</a></li>
								<%-- <li id="userolddriver" style="display: none"><a href="javascript:userolddriver('../motouser/userolddriver','${admin.user_guid}')">老司机用户</a></li> --%>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 动态管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="motonews" style="display: none"><a
									href="javascript:motonews('../news/news','${admin.user_guid}')">动态管理</a></li>
								<li id="motorecommend" style="display: none"><a
									href="javascript:motorecommend('../news/recommendnewslist','${admin.user_guid}')">精选管理</a></li>
								<li id="motoboxnews" style="display: none"><a
									href="javascript:motoboxnews('../news/motoboxnews','${admin.user_guid}')">外链动态</a></li>
								<li id="motorecommendnew" style="display: none"><a
									href="javascript:motorecommend_new('../news/recommendnewslist_new','${admin.user_guid}')">首页动态</a></li>
								<li id="motorecommendheavy" style="display: none"><a
									href="javascript:motorecommend_heavy('../news/recommendnewslist_heavy','${admin.user_guid}')">首页重内容</a></li>
								<li id="newsrecommendcategory" style="display: none"><a
									href="javascript:newsrecommendcategory('../news/newsrecommendcategorylist','${admin.user_guid}')">精选分类管理</a></li>
								<li id="newscategory" style="display: none"><a
									href="javascript:newscategory('../news/newscategorylist','${admin.user_guid}')">动态分类管理</a></li>
								<li id="storyrecommend" style="display: none"><a
									href="javascript:storyrecommend('../news/storyrecommend','${admin.user_guid}')">故事精选</a></li>
								<%-- 	<li id="motostandby" style="display: none"><a href="javascript:ach('../standby/standbylist','${admin.user_guid}')">备用精选</a></li>
								<li id="motonewsbest" style="display: none"><a href="javascript:newsbest('../newsbest/newsbestlist','${admin.user_guid}')">每日最佳</a></li> --%>
								<li id="banner" style="display: none"><a
									href="javascript:banner('../news/bannerlist','${admin.user_guid}')">横幅管理</a></li>
								<li id="newbanner" style="display: none"><a
									href="javascript:newbanner('../news/newbannerlist','${admin.user_guid}')">新横幅管理</a></li>
								<li id="activity" style="display: none"><a
									href="javascript:activity('../activitymanage/activitylist','${admin.user_guid}')">活动管理</a></li>
								<%-- 	<li id="storyOrboxTemp" style="display: none"><a href="javascript:storyOrboxTemp('../news/storyOrboxTemp','${admin.user_guid}')">临时故事手册管理</a></li> --%>
								<li id="topicAndDiscuss" style="display: none"><a
									href="javascript:topicAndDiscuss('../news/topicAndDiscusslist','${admin.user_guid}')">话题和讨论管理</a></li>
							</ul></li>

						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 骑行赛管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="motoach" style="display: none"><a
									href="javascript:ach('../ach/achlist','${admin.user_guid}')">成就管理</a></li>
								<li id="motobandgp" style="display: none"><a
									href="javascript:motobandgp('../motobandgpmanage/motobandgplist','${admin.user_guid}')">线上骑行赛管理</a></li>
							</ul></li>

						<%--     <li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								保险管理<span class="fa arrow"></span></a>
						 <ul class="nav nav-second-level">
						        <li id="payorder" style="display: none"><a href="javascript:payorder('../payordermanage/payorderlist','${admin.user_guid}')">保单管理</a></li>
								<li id="payorderlog" style="display: none"><a href="javascript:payorderlog('../payordermanage/payorderloglist','${admin.user_guid}')">支付流水</a></li>
								<li id="insuranceupdate" style="display: none"><a href="javascript:insuranceupdate('../payordermanage/insuranceupdatelist','${admin.user_guid}')">保险更新管理</a></li>
					     </ul> 	
				      </li> --%>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 广告管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="ad" style="display: none"><a
									href="javascript:ad('../admanage/adlist','${admin.user_guid}')">开屏广告管理</a></li>
								<li id="adpool" style="display: none"><a
									href="javascript:adpool('../admanage/adpoollist','${admin.user_guid}')">广告池管理</a></li>
								<li id="advertising" style="display: none"><a
									href="javascript:advertising('../admanage/advertisinglist','${admin.user_guid}')">新广告管理</a></li>
							</ul></li>
						<%--   <li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								骑行学院管理<span class="fa arrow"></span></a>
						 <ul class="nav nav-second-level">
						       	<li id="motoschoolpackage" style="display: none"><a href="javascript:motoschoolpackage('../motoschoolmanage/packagelist','${admin.user_guid}')">骑行学院系列</a></li>
								<li id="motoschoolbox" style="display: none"><a href="javascript:motoschoolbox('../motoschoolmanage/boxlist','${admin.user_guid}')">骑行学院文章</a></li>
								<li id="motoschoolvideo" style="display: none"><a href="javascript:motoschoolvideo('../motoschoolmanage/videolist','${admin.user_guid}')">骑行学院视频</a></li>
					     </ul> 	
				      </li> --%>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								举报日志管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="reportlog" style="display: none"><a
									href="javascript:reportlog('../reportlogmanage/reportloglist','${admin.user_guid}')">举报日志管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 商城<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="mall" style="display: none"><a
									href="javascript:mall('../mallmanage/malllist','${admin.user_guid}')">商品管理</a></li>
								<%-- 	 <li id="mallproductrecommend" style="display: none"><a
									href="javascript:mallproductrecommend('../mallmanage/mallproductrecommendlist','${admin.user_guid}')">精选商品管理</a></li>
							<li id="mallparenttype" style="display: none"><a
									href="javascript:mallparenttype('../mallmanage/mallparenttypelist','${admin.user_guid}')">一级分类管理</a></li>
							<li id="malltype" style="display: none"><a
									href="javascript:malltype('../mallmanage/malltypelist','${admin.user_guid}')">二级分类管理</a></li>
							<li id="mallbrand" style="display: none"><a
									href="javascript:mallbrand('../mallmanage/mallbrandlist','${admin.user_guid}')">商品品牌管理</a></li>
							<li id="malllabel" style="display: none"><a
									href="javascript:malllabel('../mallmanage/malllabellist','${admin.user_guid}')">商品标签管理</a></li>
							<li id="mallstyle" style="display: none"><a
									href="javascript:mallstyle('../mallmanage/mallstylelist','${admin.user_guid}')">商品风格管理</a></li> --%>
								<li id="mallnotify" style="display: none"><a
									href="javascript:mallnotify('../mallmanage/mallnotifylist','${admin.user_guid}')">商品消息管理</a></li>
								<li id="mallbase" style="display: none"><a
									href="javascript:mallbase('../mallmanage/mallbaselist','${admin.user_guid}',0)">商品首页管理</a></li>
								<li id="equippinggroup" style="display: none"><a
									href="javascript:equippinggroup('../mallmanage/equippinggrouplist','${admin.user_guid}',0)">商品首页分组管理</a></li>
								<li id="mallbasecredit" style="display: none"><a
									href="javascript:mallbase('../mallmanage/mallbaselist','${admin.user_guid}',1)">积分首页管理</a></li>
								<li id="equippinggroupcredit" style="display: none"><a
									href="javascript:equippinggroup('../mallmanage/equippinggrouplist','${admin.user_guid}',1)">积分首页分组管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								数据统计管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="dataStatistics" style="display: none"><a
									href="javascript:dataStatistics('../dataStatisticsmanage/dataStatisticslist','${admin.user_guid}')">数据统计</a></li>
								<%-- <li id="businessdataStatistics" style="display: none"><a href="javascript:businessdataStatistics('../dataStatisticsmanage/businessdataStatisticslist','${admin.user_guid}')">商家数据统计</a></li> --%>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 商家管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="businessapply" style="display: none"><a
									href="javascript:businessapplylist('../businessmanage/businessapplylist','${admin.user_guid}')">商家申请管理(废弃)</a></li>
								<li id="businessuser" style="display: none"><a
									href="javascript:businessuserlist('../businessmanage/businessuserlist','${admin.user_guid}')">商家用户管理(废弃)</a></li>
								<%--  <li id="businessuserinfoapply" style="display: none"><a href="javascript:businessuserinfoapplylist('../businessmanage/businessuserinfoapplylist','${admin.user_guid}')">商家信息审核申请</a></li>  --%>
								<%--  <li id="usecarmain" style="display: none"><a href="javascript:usecarmainlist('../businessmanage/usecarmainlist','${admin.user_guid}')">用车首页管理</a></li>  --%>
								<li id="businesscommment" style="display: none"><a
									href="javascript:businesscommentlist('../businessmanage/businesscommentlist','${admin.user_guid}')">商家评论管理</a></li>
								<li id="businessredirctshoplist" style="display: none"><a
									href="javascript:businessredirctshoplist('../businessmanage/businessredirctshoplist','${admin.user_guid}')">直营店管理</a></li>
								<li id="businessredirctshopservicelist" style="display: none"><a
									href="javascript:businessredirctshopservicelist('../businessmanage/businessredirctshopservicelist','${admin.user_guid}')">直营店服务管理</a></li>
							</ul></li>
						<%--   <li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								礼物兑换管理<span class="fa arrow"></span></a>
						 <ul class="nav nav-second-level">
						       <li id="giftexchange" style="display: none"><a href="javascript:giftexchangelist('../giftexchangemanage/giftexchangelist','${admin.user_guid}')">礼物兑换</a></li> 
					     </ul> 	
				      </li> --%>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 二手车管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="secondcar" style="display: none"><a
									href="javascript:secondcarlist('../secondcar/secondcarlist','${admin.user_guid}')">二手车管理</a></li>
							</ul>
							<ul class="nav nav-second-level">
								<li id="secondcarmainzone" style="display: none"><a
									href="javascript:secondcarmainzonelist('../secondcar/secondcarmainzonelist','${admin.user_guid}')">二手车首页专区管理</a></li>
							</ul> <%--  <ul class="nav nav-second-level">
						       <li id="consignment" style="display: none"><a href="javascript:consignmentlist('../secondcar/consignmentlist','${admin.user_guid}')">寄售管理</a></li> 
					     </ul> --%></li>
						<%--  <li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								部落管理<span class="fa arrow"></span></a>
						 <ul class="nav nav-second-level">
						       <li id="tribal" style="display: none"><a href="javascript:triballist('../tribal/triballist','${admin.user_guid}')">部落管理</a></li> 
					     </ul> 	
				      </li> --%>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i> 开屏图管理<span
								class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="openscreen" style="display: none"><a
									href="javascript:openscreenlist('../motouser/openscreenlist','${admin.user_guid}')">开屏图管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								骑迹景点管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="openqiji" style="display: none"><a
									href="javascript:openqijilist('../qiji/openqijilist','${admin.user_guid}')">骑迹景点管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								bot用户管理<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="botuser" style="display: none"><a
									href="javascript:openbotuserlist('../botuser/botuserlist','${admin.user_guid}')">bot管理</a></li>
							</ul></li>
						<li><a href="#"><i class="fa fa-files-o fa-fw"></i>
								腾讯云iot<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level">
								<li id="iot" style="display: none"><a
									href="javascript:opendevicelog('../iot/devicelog','${admin.user_guid}')">设备日志</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>

		<div id="page-wrapper" style="height: 1480px;">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">MotoBand CMS</h1>
					<div id="fountainTextG" style="display: none;">
						<div id="fountainTextG_1" class="fountainTextG">真</div>
						<div id="fountainTextG_2" class="fountainTextG">的</div>
						<div id="fountainTextG_3" class="fountainTextG">在</div>
						<div id="fountainTextG_4" class="fountainTextG">努</div>
						<div id="fountainTextG_5" class="fountainTextG">力</div>
						<div id="fountainTextG_6" class="fountainTextG">加</div>
						<div id="fountainTextG_7" class="fountainTextG">载</div>
						<div id="fountainTextG_8" class="fountainTextG">中</div>
					</div>

				</div>
				<!-- /.col-lg-12 -->
			</div>

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->



	<!-- jQuery -->
	<script src="/bower_components/jquery/dist/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="/bower_components/metisMenu/dist/metisMenu.min.js"></script>



	<!-- Custom Theme JavaScript -->
	<script src="/dist/js/sb-admin-2.js"></script>
	<!-- index.js -->
	<script src="/js/admin/index.js"></script>
	<script src="/js/Math.uuid.js"></script>
</body>
</html>