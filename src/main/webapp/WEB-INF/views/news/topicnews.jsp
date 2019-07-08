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
<script src="../js/consts.js"></script>
<style>
* {
	margin: 0;
	padding: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	-webkit-text-size-adjust: none;
}

html {
	font-size: 10px;
}

body {
	background-color: #f5f5f5;
	font-size: 1.2em;
}

.tab {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	height: 44px;
	line-height: 44px;
	border-bottom: 1px solid #ff3c3c;
	background-color: #eee;
}

.tab .item {
	display: block;
	-webkit-box-flex: 1;
	-webkit-flex: 1;
	-ms-flex: 1;
	flex: 1;
	width: 100%;
	font-size: 14px;
	text-align: center;
	color: #333;
	text-decoration: none;
}

.tab .cur {
	height: 42px;
	border-bottom: 2px solid #ff3c3c;
	color: #ff3c3c;
}

.content {
	background-color: #fff;
}

.button, .button:visited {
	font-size: 9pt; background-color : #2981e4;
	display: inline-block;
	padding: 5px 10px 6px;
	color: #fff;
	text-decoration: none;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
	-moz-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	-webkit-box-shadow: 0 1px 3px rgba(0, 0, 0, 0.6);
	text-shadow: 0 -1px 1px rgba(0, 0, 0, 0.25);
	border-bottom: 1px solid rgba(0, 0, 0, 0.25);
	position: relative;
	cursor: pointer;
	background-color: #2981e4;
}

.content .item {
	display: -webkit-box;
	display: -webkit-flex;
	display: -ms-flexbox;
	display: flex;
	-ms-flex-align: center;
	-webkit-box-align: center;
	box-align: center;
	-webkit-align-items: center;
	align-items: center;
	padding: 3.125%;
	border-bottom: 1px solid #ddd;
	color: #333;
	text-decoration: none;
}

.content .item img {
	display: block;
	width: 40px;
	height: 40px;
	border: 1px solid #ddd;
}

.content .item h3 {
	display: block;
	-webkit-box-flex: 1;
	-webkit-flex: 1;
	-ms-flex: 1;
	flex: 1;
	width: 100%;
	max-height: 40px;
	overflow: hidden;
	line-height: 20px;
	margin: 0 10px;
	font-size: 1.2rem;
}

.content .item .date {
	display: block;
	height: 20px;
	line-height: 20px;
	color: #999;
}

.opacity {
	-webkit-animation: opacity 0.3s linear;
	animation: opacity 0.3s linear;
}

@
-webkit-keyframes opacity { 0% {
	opacity: 0;
}

100%
{
opacity








:




 




1;
}
}
@
keyframes opacity { 0% {
	opacity: 0;
}
100%
{
opacity








:




 




1;
}
}
.form-control {
	display: block;
	width: 90%;
	height: 34px;
	padding: 6px 12px;
	font-size: 12px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}
</style>
<link rel="stylesheet" href="../css/news/dropload.css">

</head>

<body>
	<input id="temptopicnews" style="display: none;"
		value="${temptopicnews}" />
	<input id="temptopichot" style="display: none;" value="${temptopichot}" />
	<div class="tab">
		<a href="javascript:;" class="item cur">时间</a> <a href="javascript:;"
			class="item">热度</a>
	</div>
	<div class="content" style="font-size: 11pt;">
		<div class="lists">
			<div  id="${tempnews.nid}"
				style="background-color:#ddecdb; margin-top: 10px; height: 660px; margin-left: 5px; margin-right: 5px; margin-bottom:10px;float:left; width:340px;border: 2px solid #ddd;">
				<c:choose>
				  <c:when test="${tempnews.recommend==1}">
				     <div style="float: left;position:relative">
					<img style="width: 335px ;height:335px" src="${tempnews.picurl }!thumb">
					<button class="btn btn-warning btn-sm" style="position:absolute;right:-1px;top:-1px;">精</button>
				</div>
				  </c:when>
				  <c:otherwise>
				     <div style="float: left;">
					<img style="width: 335px ;height:335px" src="${tempnews.picurl }!thumb">
				</div>
				  </c:otherwise>
				</c:choose>
				<div>动态ID:${tempnews.nid }</div>
				<div>发布用户ID:${tempnews.userid }</div>
				<div>发布时间:${tempnews.ptimeString}</div>
				<div>动态内容:<div style="margin-left: 4px;"><textarea rows="3" cols="45">${tempnews.content }</textarea></div>
				</div>
				<div>动态keyword:${tempnews.keywords }</div>
				<div>动态label:${tempnews.labels }</div>
				<div >
					动态类型:
					<c:if test="${tempnews.type==4 }">
						<font color="red">${tempnews.type }</font>
					</c:if>
					<c:if test="${tempnews.type!=4 }">
						${tempnews.type }
					</c:if>
				</div>
				<div>动态评论数:${tempnews.ccount }</div>
				<div>动态点赞数:${tempnews.lcount }</div>
				<c:choose>
				   <c:when test="${tempnews.type ==4 or tempnews.type ==8 }">
				      	<c:choose>
				   <c:when test="${tempnews.categoryid == 1 }">
				      <div>动态分类:<span id="${tempnews.nid }1">用车经验</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
					</c:when>
				   <c:when test="${tempnews.categoryid == 2 }">
				      <div>动态分类:<span id="${tempnews.nid }2">摩旅游记</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${tempnews.categoryid == 3 }">
				      <div>动态分类:<span id="${tempnews.nid }3">评测</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${tempnews.categoryid == 4 }">
				      <div>动态分类:<span id="${tempnews.nid }4">生活文化</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${tempnews.categoryid == 5 }">
				      <div>动态分类:<span id="${tempnews.nid }5">改装</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${tempnews.categoryid == 6 }">
				      <div>动态分类:<span id="${tempnews.nid }6">竞技极限</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				   </c:when>
				  <c:otherwise>
				       <div>动态分类:<span id="${tempnews.nid }0">未分类</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${tempnews.nid}','${tempnews.categoryid}')">更改分类</button></div>
				  </c:otherwise>
				</c:choose>
				   </c:when>
				</c:choose>
			    <c:choose>
			       <c:when test="${topictype==1 }">
			           <div  style="margin-left: 5px;">
			           <a class="button" href="javascript:void(0)"
							onclick="addRecommendNews('${tempnews.nid}','${tempnews.userid }')">设置精选动态</a>
			           <a class="btn btn-danger" href="javascript:void(0)"
						    onclick="deleteUserSingleNews('${tempnews.nid}','${tempnews.userid }')">删除动态</a>
			           </div>
			       </c:when>
			       <c:otherwise>
			       <div  style="margin-left: 5px;">
					<%-- 	<a class="button" href="javascript:void(0)"
							onclick="delnewstopic('${tempnews.nid}','${tempnews.keywords }')">删除动态话题</a> --%>
						<a class="button" href="javascript:void(0)"
							onclick="addRecommendNews('${tempnews.nid}','${tempnews.userid }')">设置精选动态</a> 
							<%-- <a class="button" href="javascript:void(0)"
							onclick="opener('${tempnews.nid}','${tempnews.keywords }')">修改动态话题</a> --%>
					    <a class="btn btn-danger"
									href="javascript:void(0)"
									onclick="deleteUserSingleNews('${tempnews.nid}','${tempnews.userid }')">删除动态</a> 
				</div>
			       </c:otherwise>
			    
			    </c:choose>
				
			</div>
		</div>
		<div class="lists">

			<div id="${temphots.nid}"
				style="background-color:#ddecdb; margin-top: 10px; height: 660px; margin-left: 5px; margin-right: 5px; margin-bottom:10px;float:left; width:340px;border: 2px solid #ddd;">
				<c:choose>
				  <c:when test="${temphots.recommend==1}">
				     <div style="float: left;position:relative">
					   <img style="width: 335px ;height:335px" src="${temphots.picurl }!thumb">
					   <button class="btn btn-warning btn-sm" style="position:absolute;right:-1px;top:-1px;">精</button>
				    </div>
				  </c:when>
				  <c:otherwise>
				      <div style="float: left;">
					  <img style="width: 335px ;height:335px" src="${temphots.picurl }!thumb">
				     </div>
				  </c:otherwise>
				</c:choose>
				<div>动态ID:${temphots.nid }</div>
				<div>发布用户ID:${temphots.userid }</div>
				<div>发布时间:${temphots.ptimeString}</div>
				<div>动态内容：<div style="margin-left: 4px;"><textarea rows="3" cols="45">${temphots.content }</textarea></div>
				</div>
				<div>动态keyword: ${temphots.keywords }</div>
				<div>动态label:${temphots.labels }</div>
				<div >
					动态类型:
					<c:if test="${temphots.type==4 }">
						<font color="red">${temphots.type }</font>
					</c:if>
					<c:if test="${temphots.type!=4 }">
						${temphots.type }
					</c:if>
				</div>
				<div>动态评论数:${temphots.ccount }</div>
				<div>动态点赞数:${temphots.lcount }</div>
				<c:choose>
				    <c:when test="${temphots.type ==4 or temphots.type ==8 }"> 
				      <c:choose>
				   <c:when test="${temphots.categoryid == 1 }">
				      <div>动态分类:<span id="${temphots.nid }1">用车经验</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
					</c:when>
				   <c:when test="${temphots.categoryid == 2 }">
				      <div>动态分类:<span id="${temphots.nid }2">摩旅游记</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${temphots.categoryid == 3 }">
				      <div>动态分类:<span id="${temphots.nid }3">评测&nbsp</span><button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${temphots.categoryid == 4 }">
				      <div>动态分类:<span id="${temphots.nid }4">生活文化</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${temphots.categoryid == 5 }">
				      <div>动态分类:<span id="${temphots.nid }5">改装</span> &nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				   </c:when>
				   <c:when test="${temphots.categoryid == 6 }">
				      <div>动态分类:<span id="${temphots.nid }6">竞技极限</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				   </c:when>
				  <c:otherwise>
				       <div>动态分类:<span id="${temphots.nid }0">未分类</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory('${temphots.nid}','${temphots.categoryid}')">更改分类</button></div>
				  </c:otherwise>
				</c:choose>
				    </c:when>
				</c:choose>
			   <c:choose>
			        <c:when test="${topictype==1 }">
			           <div  style="margin-left: 5px;">
			           <a class="button" href="javascript:void(0)"
							onclick="addRecommendNews('${temphots.nid}','${temphots.userid }')">设置精选动态</a>
			           <a class="btn btn-danger" href="javascript:void(0)"
						    onclick="deleteUserSingleNews('${temphots.nid}','${temphots.userid }')">删除动态</a>
			           <a class="btn btn-info" href="javascript:void(0)"
						    onclick="changeDiscussOrderindex('${temphots.nid}','${label }')">修改排序</a>
			           </div>
			       </c:when>
			       <c:otherwise>
			           <div style="margin-left: 5px;">
					<%-- 	<a class="button" href="javascript:void(0)"
							onclick="delnewstopic('${temphots.nid}','${temphots.keywords }')">删除动态话题</a> --%>
						<a class="button" href="javascript:void(0)"
							onclick="addRecommendNews('${temphots.nid}','${temphots.userid }')">设置精选动态</a>
					<%-- 	<a class="button" href="javascript:void(0)" onclick="opener('${temphots.nid}','${temphots.keywords }')">修改动态话题</a> --%>
						<a class="btn btn-danger"
									href="javascript:void(0)"
									onclick="deleteUserSingleNews('${temphots.nid}','${temphots.userid }')">删除动态</a>
				</div>
			       </c:otherwise>
			   </c:choose>
				
			</div>
		</div>
	</div>
	<!-- jQuery1.7以上 或者 Zepto 二选一，不要同时都引用 -->
	<script src="../js/news/zepto.min.js"></script>
	<script src="../js/news/dropload.js"></script>
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
        $ (function ()
        {
	        var itemIndex = 0;
	        var tab1LoadEnd = false;
	        var tab2LoadEnd = false;
	        // tab
	        $ ('.tab .item').on ('click', function ()
	        {
		        var $this = $ (this);
		        itemIndex = $this.index ();
		        
		        $this.addClass ('cur').siblings ('.item').removeClass ('cur');
		        
		        $ ('.lists').eq (itemIndex).show ().siblings ('.lists').hide ();
		        // 如果选中菜单一
		        if (itemIndex == '0')
		        {
			        
			        // 如果数据没有加载完
			        if (!tab1LoadEnd)
			        {
				        // 解锁
				        dropload.unlock ();
				        dropload.noData (false);
			        }
			        else
			        {
				        // 锁定
				        dropload.lock ('down');
				        dropload.noData ();
			        }
			        // 如果选中菜单二
		        }
		        else if (itemIndex == '1')
		        {
			        
			        if (!tab2LoadEnd)
			        {
				        // 解锁
				        dropload.unlock ();
				        dropload.noData (false);
			        }
			        else
			        {
				        // 锁定
				        dropload.lock ('down');
				        dropload.noData ();
			        }
		        }
		        // 重置
		        dropload.resetload ();
	        });
	        var userid = serviceuserid;
	        var sign = servicesign;
	        var counter = 0;
	        // 每页展示10个
	        var num = 10;
	        var pageStart = 0, pageEnd = 0;
	        
	        // dropload
	        var dropload = $ ('.content')
	                .dropload (
	                        {
	                            scrollArea : window,
	                            loadDownFn : function (me)
	                            {
		                            
		                            // 加载菜单一的数据
		                            if (itemIndex == '0')
		                            {
		                            	var topictype="${topictype}";
			                            var datas =
			                            {
			                                "userid" : userid,
			                                "sign" : sign,
			                                "topictype" : '${topictype}',
			                                "label" : '${label}',
			                                "nid" : $ ('#temptopicnews').val ()
			                            }
			                            $
			                                    .ajax (
			                                    {
			                                        type : 'POST',
			                                        url : '../news/getmorenewsbylabel',
			                                     //   data : JSON.stringify (datas),
			                                        data : datas,
			                                      //  dataType : 'json',
			                                        success : function (data)
			                                        {
			                                        	var json = eval ("("+data+")");
				                                        var result = '';
				                                        if (json.data != null)
				                                        {
				                                        	var returndata = JSON.parse(json.data);
					                                        for (var i = 0; i < returndata.length; i++)
					                                        {
					                                        	 var newstype = "";
							                                        if (returndataa[i].type == 4)
							                                        {
								                                        newstype = "<font color='red'>4</font>"
							                                        }
							                                        else
							                                        {
								                                        newstype = "4"
							                                        }
						                                        //result += '<a class="item opacity" href="' + data.lists[i].link + '">' + '<img src="' + data.lists[i].pic + '" alt="">' + '<h3>' + data.lists[i].title + '</h3>' + '<span class="date">' + data.lists[i].date + '</span>' + '</a>';
						                                        result += '<div id="'returndataa[i].nid+'"style="background-color:#ddecdb; margin-top: 10px; height: 660px; margin-left: 5px; margin-right: 5px; margin-bottom:10px;float:left; width:340px;border: 2px solid #ddd;">';
						                                        if(returndata[i].recommend==1){
						                                        	result+=' <div style="float:left;position:relative"><img style="width:335px;height:335px" src="' + returndata[i].picurl + '!thumb"><button class="btn btn-warning btn-sm" style="position:absolute;right:-1px;top:-1px;">精</button></div>';
						                                        }else{
						                                        	result+=' <div style="float:left;"><img style="width:335px;height:335px" src="' + returndata[i].picurl + '!thumb"></div>';
						                                        }
						                                       
						                                        result+=' <div>动态ID：'
						                                                + returndata[i].nid
						                                                + '</div><div>发布用户ID：'
						                                                + returndata[i].userid
						                                                + '</div><div>发布时间：'
						                                                + format (returndata[i].ptime,
						                                                        'yyyy-MM-dd HH:mm:ss')
						                                                + '</div><div>动态内容：<div style="margin-left: 4px;"><textarea rows="3" cols="45">'
						                                                + returndata[i].content
						                                                + '</textarea></div></div><div>动态keyword：'
						                                                + returndata[i].keywords
						                                                + '</div><div>动态label:'
						                                                + returndata[i].labels
						                                                + '</div><div>动态类型:'
						                                                + newstype
						                                                + '</div><div>动态评论数：'
						                                                + returndata[i].ccount
						                                                + '</div><div>动态点赞数：'
						                                                + returndata[i].lcount
						                                                + '</div>';
						                         if(returndata[i].type==4 || returndata[i].type==8){
				                                                switch (returndata[i].categoryid){
				                                                case 1:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'1">用车经验</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 2:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'2">摩旅游记</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 3:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'3">评测</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 4:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'4">生活文化</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 5:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'5">改装</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 6:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'6">竞技极限</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                default :
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'0">未分类</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                }
						                         }        
						                                 if(topictype==1){
						                                	 result +='<div  style="margin-left: 5px;"><a class="button" href="javascript:void(0)" onclick="addRecommendNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                + '\')">设置精选动态</a><a class="btn btn-danger" href="javascript:void(0)" onclick="deleteUserSingleNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                +'\')">删除动态</a></div></div>';
						                                 }else{
						                                	 result +='<div  style="margin-left: 5px;">'
						                                	      /*+'<a class="button" href="javascript:void(0)" onclick="delnewstopic(\''
					                                                + returndata[i].nid
					                                                + '\',\''
					                                                + returndata[i].keywords
					                                                + '\')">删除动态话题</a>' */
					                                                +'<a class="button" href="javascript:void(0)" onclick="addRecommendNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                + '\')">设置精选动态</a>'
					                                               /*  +'<a class="button" href="javascript:void(0)" onclick="opener(\''
					                                                + returndata[i].nid
					                                                + '\',\''
					                                                + returndata[i].keywords
					                                                + '\')">修改动态话题</a>' */
					                                                + '<a class="btn btn-danger" href="javascript:void(0)" onclick="deleteUserSingleNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                +'\')">删除动态</a></div></div>';
						                                 }
				                                               
						                                        $ ('#temptopicnews').val (returndata[i].nid);
					                                        }
				                                        }
				                                        // 为了测试，延迟1秒加载
				                                        setTimeout (function ()
				                                        {
					                                        $ ('.lists').eq (itemIndex).append (result);
					                                        // 每次数据加载完，必须重置
					                                        me.resetload ();
				                                        }, 1000);
				                                        
			                                        },
			                                        error : function (xhr, type)
			                                        {
				                                        // alert('Ajax error!');
				                                        // 即使加载出错，也得重置
				                                        me.resetload ();
			                                        }
			                                    });
			                            // 加载菜单二的数据
		                            }
		                            else if (itemIndex == '1')
		                            {
		                            	var topictype="${topictype}";
			                            var datas =
			                            {
			                                "userid" : userid,
			                                "sign" : sign,
			                                "topictype" : '${topictype}',
			                                "label" : '${keyword}',
			                                "nid" : $ ('#temptopichot').val ()
			                            }
			                            $
			                                    .ajax (
			                                    {
			                                        type : 'POST',
			                                        url :  '../news/getmorenewsbylabel',
			                                        data : datas,
			                                      //  data : JSON.stringify (datas),
			                                      //  dataType : 'json',
			                                        success : function (data)
			                                        {
			                                        	var json = eval ("("+data+")");
				                                        var result = '';
				                                        if (json.data != null)
				                                        {
				                                        	var returndata = JSON.parse(json.data);
					                                        for (var i = 0; i < returndata.length; i++)
					                                        {
					                                        	 var newstype = "";
							                                        if (returndata[i].type == 4)
							                                        {
								                                        newstype = "<font color='red'>4</font>"
							                                        }
							                                        else
							                                        {
								                                        newstype = "4"
							                                        }
						                                        //result += '<a class="item opacity" href="' + data.lists[i].link + '">' + '<img src="' + data.lists[i].pic + '" alt="">' + '<h3>' + data.lists[i].title + '</h3>' + '<span class="date">' + data.lists[i].date + '</span>' + '</a>';
						                                        result += '<div id="'+returndata[i].nid+'"style="background-color:#ddecdb; margin-top: 10px; height: 660px; margin-left: 5px; margin-right: 5px; margin-bottom:10px;float:left; width:340px;border: 2px solid #ddd;">';
						                                        if(returndata[i].recommend==1){
						                                        	result+=' <div style="float:left;position:relative"><img style="width:335px;height:335px" src="' + returndata[i].picurl + '!thumb"><button class="btn btn-warning btn-sm" style="position:absolute;right:-1px;top:-1px;">精</button></div>';
						                                        }else{
						                                        	result+=' <div style="float:left;"><img style="width:335px;height:335px" src="' + returndata[i].picurl + '!thumb"></div>';
						                                        }
						                                       
						                                        result+=' <div>动态ID：'
						                                                + returndata[i].nid
						                                                + '</div><div>发布用户ID：'
						                                                + returndata[i].userid
						                                                + '</div><div>发布时间：'
						                                                + format (returndata[i].ptime,
						                                                        'yyyy-MM-dd HH:mm:ss')
						                                                + '</div><div >动态内容：<div style="margin-left: 4px;"><textarea rows="3" cols="45">'
						                                                + returndata[i].content
						                                                + '</textarea></div></div><div>动态keyword：'
						                                                + returndata[i].keywords
						                                                + '</div><div>动态label:'
						                                                + returndata[i].labels
						                                                + '</div><div>动态类型:'
						                                                + newstype
						                                                + '</div><div>动态评论数：'
						                                                + returndata[i].ccount
						                                                + '</div><div>动态点赞数：'
						                                                + returndata[i].lcount
						                                                + '</div>';
						                                if(returndata[i].type==4 || returndata[i].type==8){
				                                                switch (returndata[i].categoryid){
				                                                case 1:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'1">用车经验</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 2:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'2">摩旅游记</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 3:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'3">评测</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 4:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'4">生活文化</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 5:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'5">改装</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                case 6:
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'6">竞技极限</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                default :
				                                                	 result += '<div>动态分类:<span id="'+returndata[i].nid+'0">未分类</span>&nbsp<button class="btn btn-primary btn-xs" onclick="javascript:changeCategory(\''
				                                                			+returndata[i].nid
				                                                			+'\',\''+returndata[i].categoryid
				                                                			+'\')">更改分类</button></div>';
				                                                	break;
				                                                }
						                                }    
						                                if(topictype==1){
						                                	 result +='<div  style="margin-left: 5px;"><a class="button" href="javascript:void(0)" onclick="addRecommendNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                + '\')">设置精选动态</a><a class="btn btn-danger" href="javascript:void(0)" onclick="deleteUserSingleNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                +'\')">删除动态</a><a class="btn btn-info" href="javascript:void(0)" onclick="changeDiscussOrderindex(\''
					                                                + returndata[i].nid
					                                                + '\',\''+"${keyword}"
					                                                +'\')">修改顺序</a></div></div>';
						                                 }else{
						                                	 result +='<div  style="margin-left: 5px;">'
						                                	      /*   +'<a class="button" href="javascript:void(0)" onclick="delnewstopic(\''
					                                                + returndata[i].nid
					                                                + '\',\''
					                                                + returndata[i].keywords
					                                                + '\')">删除动态话题</a>' */
					                                                +'<a class="button" href="javascript:void(0)" onclick="addRecommendNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                + '\')">设置精选动态</a>'
					                                               /*  +'<a class="button" href="javascript:void(0)" onclick="opener(\''
					                                                + returndata[i].nid
					                                                + '\',\''
					                                                + returndata[i].keywords
					                                                + '\')">修改动态话题</a>' */
					                                                +'<a class="btn btn-danger" href="javascript:void(0)" onclick="deleteUserSingleNews(\''
					                                                + returndata[i].nid
					                                                + '\',\''+returndata[i].userid
					                                                +'\')">删除动态</a></div></div>';
						                                 }
						                                        $ ('#temptopichot').val (returndata[i].nid);
					                                        }
				                                        }
				                                        
				                                        // 为了测试，延迟1秒加载
				                                        setTimeout (function ()
				                                        {
					                                        $ ('.lists').eq (itemIndex).append (result);
					                                        // 每次数据加载完，必须重置
					                                        me.resetload ();
				                                        }, 1000);
				                                        
			                                        },
			                                        error : function (xhr, type)
			                                        {
				                                        // alert('Ajax error!');
				                                        // 即使加载出错，也得重置
				                                        me.resetload ();
			                                        }
			                                    });
		                            }
	                            }
	                        });
        });
	</script>
	<div id="fullscreen"
		style="display: none; width: 100%; position: fixed; top: 0px; background-color: black; opacity: 0.5;border-"></div>
	<div id="er"
		style="margin:5px auto;padding-top:20px;font-size:20px;display: none; width: 320px; height: 300px; background-color: white; position: fixed; top: 0; left: 0; text-align: center;">
		<div style="margin:5px">
		动态ID:
			<input type="text" style="margin-left:auto;margin-right:auto; " class="form-control" id='updkeywordnid' readonly="readonly" value="" />
		</div>
		<div style="margin:5px">
		原话题:
			<input type="text" style="margin-left:auto;margin-right:auto; " class="form-control" id='updkeywords' readonly="readonly" value="" />
		</div>
		<div style="margin:5px">
		新话题1:
			<select class="form-control" style="margin-left:auto;margin-right:auto; " id='topickeyword1'>
				<c:forEach items="${topics}" var="topic" varStatus="ids">
					<option value="${topic.keyword}">${topic.keyword}</option>
				</c:forEach>
			</select>
		</div>
		<div style="margin:5px">
		新话题2:
			<select class="form-control" style="margin-left:auto;margin-right:auto; " id='topickeyword2'>
				<option value=""></option>
				<c:forEach items="${topics}" var="topic" varStatus="ids">
					<option value="${topic.keyword}">${topic.keyword}</option>
				</c:forEach>
			</select>
		</div>
		<div style="margin:5px">
			<a class="button" href="javascript:void(0)"
				onclick="changekeywords();">更改话题</a>
		</div>
	</div>

	<div id="back"
		style="display: none; POSITION: fixed; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.6); filter: alpha(opacity = 60)"></div>
	<div id="win"
		style="display: none; POSITION: fixed; left: 50%; top: 50%; width: 600px; margin-left: -300px; margin-top: -200px; text-align: center">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true" onclick="javascript:closeModel()">&times;</button>
				<h4 class="modal-title" id="myModalLabel">添加到精选</h4>
			</div>
			<div class="modal-body">
				<div class="form-group">
					<input class="form-control" placeholder="请输入score"
						id="RecommendNews_score" value="">
				</div>
				<div class="form-group">
					<select class="form-control" id="RecommendNews_addtype">
						<option value='0'>首屏不显示</option>
						<option value='1'>首屏显示</option>
					</select>
				</div>
				<div class="form-group">
					<select class="form-control" id="RecommendNews_styletype">
						<option value='3'>左图右文</option>
						<option value='1'>横幅 </option>
						<option value='2'>三图</option>
					</select>
				   </div>
				   <div class="form-group">
						<input class="form-control" placeholder="请输入图片url 多图以英文逗号隔开" id="RecommendNews_stylepic"
							value="">
				   </div>
				<input class="form-control" id="RecommendNews_nid"
					style="display: none;" value=""> <input
					class="form-control" id="RecommendNews_userid"
					style="display: none;" value="">
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"
					id="closedelBoxModel" onclick="javascript:closeModel()">关闭</button>
				<button type="button" class="btn btn-primary" data-dismiss="modal"
					onclick="javascript:addRecommendNewsConfirm()">确认</button>
			</div>
		</div>

	</div>
	
	<div id="Category" style="display:none; POSITION:fixed; left:50%; top:50%; width:600px;  margin-left:-300px; margin-top:-200px; text-align:center">
<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closeCategoryModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改分类</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入score" id="category_score"
							value="" type="hidden">
				   </div>
				<div class="form-group">
					<input type="radio"  name="category_type" value="1">用车经验
					<input type="radio"  name="category_type" value="2">摩旅游记	
					<input type="radio"  name="category_type" value="3">评测
					<input type="radio"  name="category_type" value="4">生活文化
					<input type="radio"  name="category_type" value="5">改装
					<input type="radio"  name="category_type" value="6">竞技极限
				 </div>
						<input class="form-control"  id="category_nid"
							style="display: none;" value="">
							<input class="form-control"  id="old_categoryid"
							style="display: none;" value="">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel" onclick="javascript:closeCategoryModel()">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:changeCategoryConfirm()">确认</button>
				</div>
			</div>
   </div>
   
   	<div id="DiscussOrderindex" style="display:none; POSITION:fixed; left:50%; top:50%; width:600px;  margin-left:-300px; margin-top:-200px; text-align:center">
<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true" onclick="javascript:closeDiscussOrderindexModel()">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改排序</h4>
				</div>
				<div class="modal-body">
				   <div class="form-group">
						<input class="form-control" placeholder="请输入orderindex" id="discuss_orderindex"
							value="" type="text">
				   </div>
				
						<input class="form-control"  id="discuss_nid"
							style="display: none;" value="">
							<input class="form-control"  id="discuss_keyword"
							style="display: none;" value="">
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel" onclick="javascript:closeDiscussOrderindexModel()">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:changeDiscussOrderindexConfirm()">确认</button>
				</div>
			</div>
   </div>
	<script>
		function opener(nid, keywords) {
			$("#updkeywordnid").val(nid);
			$('#updkeywords').val(keywords);
			var bodyheight = $(window).height();
			var bodywidth = $(window).width();
			var top = (bodyheight - $("#er").css("height").replace("px", "")) / 2;
			var left = (bodywidth - $("#er").css("width").replace("px", "")) / 2;
			$("#er").css("top", top);
			$("#er").css("left", left);
			$("#fullscreen").css("height", bodyheight + "px");
			$("#fullscreen").css("display", "block");
			$("#er").css("display", "block");
		}
		$("#er").click(function() {
			//$("#fullscreen").css("display", "none");
			//$("#er").css("display", "none");
		})
		$("#fullscreen").click(function() {
			$("#fullscreen").css("display", "none");
			$("#er").css("display", "none");
		})
	</script>
	<script src="../js/news/topicnews.js"></script>
</body>
</html>