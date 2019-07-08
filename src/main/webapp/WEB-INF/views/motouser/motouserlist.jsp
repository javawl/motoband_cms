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
<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
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
</head>
<body>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">生产环境用户信息查询</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>




	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">用户信息 <span>&nbsp&nbsp&nbsp&nbsp&nbsp</span><button type="button" class="btn btn-primary" data-toggle="modal" data-target="#lookBlacklistModel" onclick="javascript:lookBlacklist()">黑名单管理</button> </div>
				<!-- /.panel-heading -->
				<div class="panel-body">
				<div  class="form-group col-lg-12">
					<div class="form-group col-lg-3">
					<label>选择查询类型：</label>
						<select class="form-control" id="idAndNameSeclect" onchange="javascript:idAndNameChange(this)">
							<option value='1'>用户昵称</option>
							<option value='0'>用户id</option>
							<option value='2'>mobileno</option>
						</select>

					</div>
				</div>
				<div id="idAndName0" class="form-group col-lg-12" style="display:none;">
					<div class="form-group col-lg-3">
						<input class="form-control col-lg-3" placeholder="" id="userid"
							value="${motouserid}"/>
					</div>
					<div class="form-group col-lg-3">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="javascript:searchByuserid()">查询</button>
					</div>
			  </div>
			  <div id="idAndName1" class="form-group col-lg-12" >
					<div class="form-group col-lg-3">
						<input class="form-control col-lg-3" placeholder="" id="usernickname"
						 value="${motousernickname}"/>
					</div>
					<div class="form-group col-lg-3">
						<button type="button" class="btn btn-primary btn-lg"
								onclick="javascript:searchByusernickname()">查询</button>
					</div>
				</div>
				<div id="idAndName2" class="form-group col-lg-12" style="display:none;">
					<div class="form-group col-lg-3">
						<input class="form-control col-lg-3" placeholder="" id="usermobileno"
						 value="${motousermobileno}"/>
					</div>
					<div class="form-group col-lg-3">
						<button type="button" class="btn btn-primary btn-lg"
								onclick="javascript:searchByusermobileno()">查询</button>
					</div>
				</div>
					<div class="form-group col-lg-12">
						<hr>
					</div>
					<div class="row" style="display: block">
						<div class="col-lg-12">
							<div class="panel panel-default">
								<div class="panel-heading">用户列表</div>
								<!-- /.panel-heading -->
								<div class="panel-body">
									<div class="dataTable_wrapper">
										<table
											class="table table-striped table-bordered table-hover compact"
											id="dataTables-example">
											<thead>
												<tr>
													<th>userid</th>
													<th>gender</th>
													<th>birth</th>
													<th>nickname</th>
													<th>area</th>
													<th>headurl</th>
													<th>signature</th>
													<th>addtime</th>
													<th>channel</th>
													<th>level</th>
													<th>province</th>
													<th>city</th>
													<th>operate</th>
												</tr>
											</thead>
											<tbody id="userlist">

											</tbody>
										</table>
										<!-- DataTables JavaScript -->
									</div>
									<!-- /.table-responsive -->
								</div>
								<!-- /.panel-body -->
							</div>
							<!-- /.panel -->
						</div>
						<!-- /.col-lg-12 -->
					</div>
					<div class="form-group col-lg-12">
						<hr>
						<label><font color="red">注：1代表是，0代表否</font></label>
						<table class="table table-striped table-bordered table-hover compact">
						  <tr>
						     <th>语音会员</th>
						     <th>白银会员</th>
						     <th>黄金会员</th>
						     <th>钻石会员</th>
						     <th>认证（蓝v）</th>
						     <th>官方运营 （黄v）</th>
						     <th>达人（ 绿v）</th>
						     <th>老司机</th>
						  </tr>
						  <tr>
						     <td id="vip7"></td>
						     <td id="vip6"></td>
						     <td id="vip5"></td>
						     <td id="vip4"></td>
						     <td id="vip3"></td>
						     <td id="vip2"></td>
						     <td id="vip1"></td>
						     <td id="vip0"></td>
						  </tr>
						</table>
						<hr>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">用户ID</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="用户ID" id="userid2"
								value="">
						</div>
						<div class="form-group col-lg-2">用户编号:</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="用户编号" id="mbid" value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">昵称</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="昵称" id="nickname"
								value="">
						</div>
						<div class="form-group col-lg-2">性别</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="性别" id="gender" value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">生日</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="生日" id="birth" value="">
						</div>
						<div class="form-group col-lg-2">地区</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="地区" id="area" value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">好友总数</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="好友总数" id="friendcount"
								value="">
						</div>
						<div class="form-group col-lg-2">动态总数</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="动态总数" id="newscount"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">添加时间</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="添加时间" id="addtime"
								value="">
						</div>
						<div class="form-group col-lg-2">更新时间</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="更新时间" id="updatetime"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">手机号</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="手机号" id="mobileno"
								value="">
						</div>
						<div class="form-group col-lg-2">签名</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="签名" id="signature"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">等级</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="等级" id="level" value="">
						</div>
						<div class="form-group col-lg-2">注册渠道</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="注册渠道" id="channel"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">用户类型</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="用户类型" id="usertype" value="">
						</div>
						<div class="form-group col-lg-2">评论通知开关</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="评论通知开关" id="commentswitch"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">点赞通知开关</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="点赞通知开关" id="likeswitch" value="">
						</div>
						<div class="form-group col-lg-2">私聊通知开关</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="私聊通知开关" id="imswitch"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">系统通知开关</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="系统通知开关" id="systemswitch" value="">
						</div>
						<div class="form-group col-lg-2">粉丝通知开关</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="粉丝通知开关" id="followswitch"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">骑行分享开关</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="骑行分享开关" id="rideshareswitch" value="">
						</div>
						<div class="form-group col-lg-2">错过的人开关</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="错过的人开关" id="nearuserswitch"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">他关注的人数</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="他关注的人数" id="followcount" value="">
						</div>
						<div class="form-group col-lg-2">粉丝数</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="粉丝数" id="followedcount"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">精选动态数</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="精选动态数" id="recommendcount" value="">
						</div>
					    <div class="form-group col-lg-2">积分数</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="积分数" id="credit"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">省</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="省" id="province" value="">
						</div>
					    <div class="form-group col-lg-2">市</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="市" id="city"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">地区码</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="地区码" id="nationcode" value="">
						</div>
					    <div class="form-group col-lg-2">pk开关</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="pk开关" id="pkswitch"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">微信ID</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="微信ID" id="weixinid" value="">
						</div>
					    <div class="form-group col-lg-2">微博ID</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="微博ID" id="weiboid"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">qqid</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="qqid" id="qqid" value="">
						</div>
					   
					</div>
					<div class="form-group col-lg-12">
						<div class="form-group col-lg-2">认证描述</div>
						<div class="form-group col-lg-12">
							<div class="form-group col-lg-10">
								<textarea class="form-control" placeholder="认证描述" id="approvedes"
									value=""></textarea>
							</div>
							<div class="form-group col-lg-2">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:updateApprovedes()">确定修改</button>
							</div>
						</div>

					</div>
					<div class="form-group col-lg-12">
						<div class="form-group col-lg-2">头像</div>
						<div class="form-group col-lg-11">
							<input class="form-control" placeholder="头像" id="headurl"
								value="">
						</div>

					</div>
					<div class="form-group col-lg-12">
						<hr>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">里程</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="里程" id="mileage"
								value="">
						</div>
						<div class="form-group col-lg-2">骑行时间</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="骑行时间" id="ridetime"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">骑行次数</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="骑行次数" id="ridecount"
								value="">
						</div>
						<div class="form-group col-lg-2">车库数量</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="车库数量" id="garagecount"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">线路数量</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="线路数量" id="linecount"
								value="">
						</div>
						<div class="form-group col-lg-2">线路更新时间</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="线路更新时间"
								id="lineupdatetime" value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">最大里程</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="最大里程" id="maxmileage"
								value="">
						</div>
						<div class="form-group col-lg-2">最大骑行时间</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="最大骑行时间" id="maxridetime"
								value="">
						</div>
					</div>
					<div class="form-group col-lg-6">
						<div class="form-group col-lg-2">最大速度</div>
						<div class="form-group col-lg-4">
							<input class="form-control" placeholder="最大速度" id="topspeed"
								value="">
						</div>
						<div class="form-group col-lg-2">最大倾角</div>
						<div class="form-group col-lg-3">
							<input class="form-control" placeholder="最大倾角" id="maxdipangle"
								value="">
						</div>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<!-- <div class="panel-heading">查询</div> -->
				<!-- /.panel-heading -->
				<div class="panel-body">
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserRideline()">查询用户线路</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserRideDateLog()">查询用户骑行日志</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserGarage()">查询用户车库</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserAchievement()">查询用户成就</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserNews()">查询用户动态</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:searchUserCreditLog()">查询用户积分日志</button>

					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:addUserBlack()">添加到黑名单</button>
					<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addUserRecommendModel" 
						onclick="javascript:addUserRecommendshow()">添加到精选用户（达人-绿V）</button>
					<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addUserBoxApproveModel" 
						onclick="javascript:addUserBoxApproveshow()">添加到box认证用户（认证-蓝V）</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:addUserOperation()">添加到官方运营(官方运营-黄V)</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:addUserOrdinary()">添加到普通用户(恢复普通用户)</button>
				<!-- 	<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addUserOlddriverModel" 
						onclick="javascript:addUserOlddrivershow()">添加到老司机</button> -->
								
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:deleteUserAllNews()">删除用户所有动态</button>
					<button type="button" class="btn btn-primary btn-lg"
						onclick="javascript:deleteUserAllComments()">删除用户所有动态评论</button>
					<c:choose>
						<c:when test="${updateCheck=='lock'}">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:adduserCredit()">增加积分</button>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${updateCheck=='lock'}">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:resetUserPSW()">重置用户密码</button>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${updateCheck=='lock'}">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:adduservip()">增加会员</button>
						</c:when>
					</c:choose>
					<c:choose>
						<c:when test="${updateCheck=='lock'}">
								<button type="button" class="btn btn-primary btn-lg"
									onclick="javascript:deleteuservip()">删除会员</button>
						</c:when>
					</c:choose>

				</div>
				
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<!-- /.row -->
	<div class="row" id='achievement' style="display: block">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">成就列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>achid</th>
									<th>ach_name</th>
									<th>gettime</th>
									<th>source</th>
									<th>motogp</th>
								</tr>
							</thead>
							<tbody id="userachievement">

							</tbody>
						</table>
						<!-- DataTables JavaScript -->
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
	<!-- /.row -->
	<div class="row" id='garage' style="display: block">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">车库列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>garageid</th>
									<th>userid</th>
									<th>brandid</th>
									<th>modelid</th>
									<th>cc</th>
									<th>pyear</th>
									<th>pics</th>
									<th>addtime</th>
									<th>mileage</th>
								</tr>
							</thead>
							<tbody id="usergarage">

							</tbody>
						</table>
						<!-- DataTables JavaScript -->
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
	<!-- /.row -->
	<div class="row" id='ridedatelog' style="display: block">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">骑行日志列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>id</th>
									<th>mileage</th>
									<th>ridetime</th>
									<th>reporttime</th>
									<th>starttime</th>
									<th>endtime</th>
									<th>maxelevation</th>
									<th>maxspeed</th>
									<th>avgspeed</th>
									<th>dipangle</th>
									<th>hundredtime</th>
									<th>fourhundredtime</th>
									<th style="word-break: break-all; width: 200px;display: inline-block;">ridelineid</th>
									<th>temperature</th>
									<th>ischecked</th>
							

								</tr>
							</thead>
							<tbody id="userridedatelog">

							</tbody>
						</table>
						<!-- DataTables JavaScript -->
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
	<!-- /.row -->
	<div class="row" id='rideline' style="display: block">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">线路列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>ridelineid</th>
									<th>dataurl</th>
									<th>starttime</th>
									<th>endtime</th>
									<th>ridetime</th>
									<th>maxelevation</th>
									<th>maxspeed</th>
									<th>avgspeed</th>
									<th>dipangle</th>
									<th>hundredtime</th>
									<th>fourhundredtime</th>
									<th>mileage</th>
									<th>synctime</th>
									<th>ischecked</th>
									<th>operate</th>
								</tr>
							</thead>
							<tbody id="userrideline">

							</tbody>
						</table>
						<!-- DataTables JavaScript -->
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
	
		<div class="row" id='creditlog' style="display: block">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">积分日志列表</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table
							class="table table-striped table-bordered table-hover compact"
							id="dataTables-example">
							<thead>
								<tr>
									<th>creditlogid</th>
									<th>userid</th>
									<th>title</th>
									<th>actiontype</th>
									<th>score</th>
									<th>logtime</th>
								</tr>
							</thead>
							<tbody id="usercreditlog">

							</tbody>
						</table>
						<!-- DataTables JavaScript -->
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
	
	
	<!-- DataTables JavaScript -->
	<script
		src="../bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
	<script
		src="../bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	
<div class="modal fade" id="lookBlacklistModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">黑名单用户管理</h4>
				</div>
				<div class="modal-body">
						<div class="row" id='blacklistrow' style="display: block">
							<div class="col-lg-12">
								<div class="panel panel-default">
									<div class="panel-heading">黑名单列表</div>
									<!-- /.panel-heading -->
									<div class="panel-body">
										<div class="dataTable_wrapper">
											<table
												class="table table-striped table-bordered table-hover compact"
												id="dataTables-example">
												<thead>
													<tr>
														<th>userid</th>
														<th>operate</th>
													</tr>
												</thead>
												<tbody id="blacklist">
					
												</tbody>
											</table>
											<!-- DataTables JavaScript -->
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
	<!-- /.modal -->
	
	<div class="modal fade" id="addUserBoxApproveModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加到认证用户</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入媒体" id="BoxApprove_media"
							value="">
					</div>
					<div class="form-group">
						<textarea class="form-control" placeholder="请输入认证" id="BoxApprove_approvedes"
							value=""></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addUserBoxApprove()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>

<div class="modal fade" id="addUserRecommendModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加到达人</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入score" id="UserRecommend_score"
							value="">
					</div>
					<div class="form-group">
						<textarea class="form-control" placeholder="请输入认证" id="UserRecommend_approvedes"
							value=""></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addUserRecommend()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<div class="modal fade" id="addUserOlddriverModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加到老司机</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入score" id="UserOlddriver_score"
							value="">
					</div>
					<div class="form-group">
						<textarea class="form-control" placeholder="请输入认证" id="UserOlddriver_approvedes"
							value=""></textarea>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addUserOlddriver()">确认</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	
	<div id="back"
		style="display: none; POSITION: fixed; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.6); filter: alpha(opacity = 60)"></div>
	<div id="win"
		style="display: none; POSITION: fixed; left: 50%; top: 50%; width: 600px; margin-left: -300px; margin-top: -200px; text-align: center">
		<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closeModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加积分</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入要增加的积分" id="user_addcredit"
							value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel" onclick="javascript:closeModel()">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:adduserCreditConfirm()">确认</button>
				</div>
			</div>

	</div>
	
	<div id="win1"
		style="display: none; POSITION: fixed; left: 50%; top: 50%; width: 600px; margin-left: -300px; margin-top: -200px; text-align: center">
		<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closeModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">添加会员</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
				      <select class="form-control" id="user_addvip">
				         <option value="0">请选择会员类型</option>
				         <option value="4">语音会员</option>
				         <option value="1">银卡会员</option>
				         <option value="2">黄金会员</option>
				         <option value="3">钻石会员</option>
				      </select>
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel" onclick="javascript:closevipModel()">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:adduservipConfirm()">确认</button>
				</div>
			</div>

	</div>
	
	<div id="win2"
		style="display: none; POSITION: fixed; left: 50%; top: 50%; width: 600px; margin-left: -300px; margin-top: -200px; text-align: center">
		<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closedeletevipModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">删除会员</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
				      <select class="form-control" id="user_deletevip">
				         <option value="0">请选择会员类型</option>
				         <option value="4">语音会员</option>
				         <option value="1">银卡会员</option>
				         <option value="2">黄金会员</option>
				         <option value="3">钻石会员</option>
				      </select>
						
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel" onclick="javascript:closedeletevipModel()">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:deleteuservipConfirm()">确认</button>
				</div>
			</div>

	</div>
	
<!-- <div class="modal fade" id="adduserCreditModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">增加积分</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入要增加的积分" id="user_addcredit"
							value="">
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:adduserCreditConfirm()">确认</button>
				</div>
			</div>
			/.modal-content
		</div>
		/.modal-dialog
	</div> -->
	<script src="../js/motouser/motouser.js"></script>
	<!-- <script type="text/javascript">
	window.onload = function() { 
		var targetid = $.trim($('#userid').val ());
		if (targetid == null || targetid == "")
		{
			return false;
		}else{
			searchByuserid();
		}
		}; 
	</script> -->
</body>
</html>