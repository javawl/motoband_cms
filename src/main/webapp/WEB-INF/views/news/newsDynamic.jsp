<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>摩托邦骑行</title>
<style>
@font-face {
	font-family: 'MyNewFont';
	src: url('../css/fonts/FV_Almelo.ttf');
	/* src: url('../css/fonts/cmake_n.ttf'); */
}

body {
	/* height: 100%; */
	max-width: 960px;
	margin: 0 auto;
	background-color: rgba(43, 45, 46, 1);
	/* text-align: center; */
}

@media only screen and (min-width: 376px) {
	.ride {
		margin-left: 8pt;
		font-size: 19pt;
	}
	#ridetime {
		margin-left: 7pt;
		font-size: 15pt;
		width: 56pt;
	}
	#ridemileage {
		margin-left: 7pt;
		font-size: 15pt;
		width: 45pt;
	}
	#ridemaxspeed {
		margin-left: 7pt;
		font-size: 15pt;
		width: 48pt;
	}
	#ridedipangle {
		margin-left: 7pt;
		font-size: 15pt;
		width: 34pt;
	}
}

@media only screen and (min-width: 321px) and (max-width: 375px) {
	.ride {
		margin-left: 8pt;
		font-size: 19pt;
	}
	#ridetime {
		margin-left: 5pt;
		font-size: 12pt;
		width: 53pt;
	}
	#ridemileage {
		margin-left: 5pt;
		font-size: 12pt;
		width: 42pt;
	}
	#ridemaxspeed {
		margin-left: 5pt;
		font-size: 12pt;
		width: 45pt;
	}
	#ridedipangle {
		margin-left: 5pt;
		font-size: 12pt;
		width: 31pt;
	}
}

@media only screen and (max-width: 320px) {
	.ride {
		margin-left: 1px;
		font-size: 8pt;
	}
	#ridetime {
		margin-left: 2pt;
		font-size: 8pt;
		width: 50pt;
	}
	#ridemileage {
		margin-left: 2pt;
		font-size: 8pt;
		width: 40pt;
	}
	#ridemaxspeed {
		margin-left: 2pt;
		font-size: 8pt;
		width: 43pt;
	}
	#ridedipangle {
		margin-left: 2pt;
		font-size: 8pt;
		width: 29pt;
	}
}
</style>
<script src="/bower_components/jquery/dist/jquery.min.js"></script>
</head>


<body>
	<!-- <div
		style="height: 11pt; margin: 10pt; text-align: right; background-color: rgba(50, 53, 53, 1);">
		<img src="../images/Group4.png" style="height: 11pt"> 
	</div>-->
	<div style="width: 100%;">
		<div
			style="width: 100%; margin-top: 10pt; height: 34.8pt; position: relative; margin-bottom: 10pt">
			<div
				style="float: left; width: 34.8pt; height: 34.8pt; margin-left: 10pt; margin-right: 5.2pt;">
				<img src="${news.headurl}"
					style="width: 34.8pt; height: 34.8pt; border-radius: 50%;" />
			</div>
			<div
				style="float: left; width: 38%; font-size: 13pt; color: rgb(230, 230, 230); text-align: left; line-height: 25pt; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
				${news.nickname}</div>
			<div
				style="float: right; font-size: 9pt; line-height: 20pt; color: rgba(200, 200, 200, 1); text-align: right; margin-right: 10pt; width: 30%">
				<c:choose>
					<c:when test="${islocation==true}">
						<!-- <img
							style="float: left; margin-top: 6px; width: 24px; height: 24px;"
							src="http://libres-10013836.file.myqcloud.com/place_icon%402x.png"> -->
						<div
							style="font-size: 10px; float: right; height: 20pt; width: 70%; padding-top: 6px; text-align: right; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
							${news.location}</div>
					</c:when>
					<c:otherwise>
						<span style="width: 100%; line-height: 25pt;">&nbsp;</span>
					</c:otherwise>
				</c:choose>
				<div
					style="float: right; text-align: right; font-size: 9px; color: rgba(150, 150, 150, 1); line-height: 12pt;">
					${ptimeString}</div>
			</div>
		</div>
		<div
			style="text-align: justify; font-size: 14pt; color: rgba(230, 230, 230, 1); margin: 9.7pt 15pt;">
			${news.title}</div>

		<c:forEach items="${stroylist}" var="news" varStatus="ids">
			<div id="pic"
				style="margin-left: auto; margin-right: auto; width: 93%;">

				<c:choose>

					<c:when test="${news.type==1}">
						<video src="${news.videourl}" controls="controls" width=100% poster="${news.picurl }"
							height=100%>浏览器不支持video
						</video>

					</c:when>
					<c:otherwise>
						<img src="${news.picurl}" style="width: 100%; " />
					</c:otherwise>
				</c:choose>
			</div>

			<div
				style="text-align: justify; font-size: 10pt; color: rgba(230, 230, 230, 1); margin: 9.7pt 15pt;">
				${news.content}</div>
		</c:forEach>

		<%--  <div id="ridedata"
					style="width: 100%; position: relative; top: 90%; z-index: 1000; height: 44.5pt; background-color: rgba(0, 0, 0, 0.3); margin-top: 20px">
					<div id="ridetime"
						style="padding-top: 10pt; height: 100%; float: left;">
						<div
							style="width: 100%; text-align: center; line-height: 18.6pt; color: rgba(69, 227, 191, 1); font-family: 'MyNewFont';">${ridetime}</div>
						<div
							style="color: rgba(200, 200, 200, 1); margin-left: auto; margin-right: auto; width: 35.5pt; height: 12pt; font-size: 8.5pt; line-height: 12pt;">时长time</div>
					</div>
					<div id="ridemileage"
						style="padding-top: 10pt; height: 100%; float: left;">
						<div
							style="width: 100%; text-align: center; line-height: 18.6pt; color: rgba(69, 227, 191, 1); font-family: 'MyNewFont';">${ridedataModel.mileage}</div>
						<div
							style="color: rgba(200, 200, 200, 1); margin-left: auto; margin-right: auto; width: 35.5pt; height: 12pt; font-size: 8.5pt; line-height: 12pt;">里程km</div>
					</div>
					<div id="ridemaxspeed"
						style="padding-top: 10pt; height: 100%; float: left;">
						<div
							style="width: 100%; text-align: center; line-height: 18.6pt; color: rgba(255, 255, 255, 1); font-family: 'MyNewFont';">${ridedataModel.maxspeed }</div>
						<div
							style="color: rgba(200, 200, 200, 1); margin-left: auto; margin-right: auto; width: 40pt; height: 12pt; font-size: 8.5pt; line-height: 12pt;">极速km/h</div>
					</div>
					<div id="ridedipangle"
						style="padding-top: 10pt; height: 100%; float: left; padding-left: 5px; text-align: center">
						<div
							style="width: 100%; text-align: center; line-height: 18.6pt; color: rgba(255, 255, 255, 1); font-family: 'MyNewFont';">${dipangle}</div>
						<div
							style="color: rgba(200, 200, 200, 1); margin-left: auto; margin-right: auto; width: 24.1pt; height: 12pt; font-size: 8.5pt; line-height: 12pt;">倾角°</div>
					</div>
					<div class="ride"
						style="height: 100%; border-left: 1px solid #969999; float: right; margin-right: 5px">
						<a
							href="../motorideline/index?ridelineid=${ridedataModel.ridelineid}"><img
							src="http://libres-10013836.file.myqcloud.com/button_line_open%402x.png"
							style="margin-top: 5pt; margin-left: 8pt; width: 35pt; height: 35pt;" /></a>
					</div>
				</div>  --%>
		<div
			style="font-size: 8px; color: rgba(130, 130, 130, 1); margin: 15pt 15pt; height: 21pt">
			<div style="float: left;">
				<div style="float: left;">
					<img src="../images/like_click_button.png" width="21pt"
						height="21pt" style="margin: 0pt 6pt 0pt">
				</div>
				<div style="float: right; font-size: 13px">${news.lcount}</div>
			</div>
			<div style="float: left;">
				<div style="float: left;">
					<img src="../images/talk_button.png" width="21pt" height="21pt"
						style="margin: 0pt 6pt 0pt">
				</div>
				<div style="float: right; font-size: 13px">${news.ccount}</div>

			</div>
			<div style="float: right; margin: 5pt">${news.newsupdatetime}</div>
		</div>
		<hr
			style="margin-left: 10pt; margin-right: 10pt; border: 1px solid #828282;">
		<div style="margin: 9.7pt 15pt; height: 12pt">
			<div
				style="font-size: 11px; color: #D9D9D9; letter-spacing: 0.35px; float: left; margin-right: 10pt;line-height:15pt">
				标签</div>
			<div
				style="font-size: 11px; color: #44C494; letter-spacing: 0.35px; float: left;line-height:15pt">
				${news.labels }</div>

		</div>
		<hr
			style="margin-left: 10pt; margin-right: 10pt; border: 1px solid #828282;">
			
			<!--加入评论  -->、
		<div id="newsDynamic">
			<c:forEach items="${commentlist}" var="clist">
				<div id="commentOne${clist.cid}" style="margin-top:10px;padding-bottom:10px;border-bottom:1px dashed;border-bottom-color:white">
					<div>
						<img width="40px" height="40px" src="${clist.userHeadurl}" style="vertical-align:text-top"/><font
							color="#44C494">${clist.usernickname}</font>
					</div>
					<div>
						<c:choose>
							<c:when
								test="${clist.tusernickname != null and clist.tusernickname !='' }">
								<span>&nbsp&nbsp&nbsp</span>
								<span><font color="#b2b9bbf2">回复</font><font
									color="#44C494">${clist.tusernickname} :</font></span>
								<span><font color="#828282">${clist.content}</font></span>
								<span>&nbsp&nbsp&nbsp</span>
								<span style="float:right"><font color="#828282">回复于：${clist.ctimeString}&nbsp</font>
								  <a href="javascript:deletenewscomment('${clist.cid}','${clist.nid}')" style="text-decoration:none"><font color="red">删除</font></a></span>
							</c:when>
							<c:otherwise>
								<span>&nbsp&nbsp&nbsp</span>
								<span><font color="#828282">${clist.content}</font></span>
								<span>&nbsp&nbsp&nbsp</span>
								<span style="float:right"><font color="#828282">回复于：${clist.ctimeString}&nbsp</font>
								  <a href="javascript:deletenewscomment('${clist.cid}','${clist.nid}')" style="text-decoration:none"><font color="red">删除</font></a></span>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</c:forEach>
		</div>

		<div style="margin: 13.7pt 15pt; height: 300px;text-align: center;" >
			<img src="../images/Group2@3x.png" height=300px>
		</div>
	</div>


	<script>
		var pic = document.getElementsByClassName ("pic");
        pic.style.height = pic.offsetWidth + "px";
        
        var controls = document.getElementsByName ("pic");
        for (var i = 0; i < controls.length; i++)//这里是length还是count记不清了
        {
	        controls[i].style.height = controls[i].offsetWidth + "px";
        }

        /* var ridedata = document.getElementById ("ridedata");
        ridedata.style.top = pic.offsetHeight - 59 + "px"; */
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
        //format(json.data[i].ptime, 'yyyy-MM-dd HH:mm:ss')
	</script>
	<script type="text/javascript" src="../js/download.js"></script>
	<script type="text/javascript">
		document.getElementById ("downloadModel").style.width = document.body.offsetWidth + "px";
	</script>
	<script type="text/javascript">
	 function deletenewscomment(cid,nid){
		 if(confirm("您真的要把这条评论删除么？此操作不可逆")){
				var datas =
				{
				    "cid" : cid,
				    "nid" : nid
				   
				}

				$.ajax (
				{
				    type : "POST",
				    url : '../news/deletenewscomment',
				    data : datas,
				    success : function (data)
				    {
					    
					    if (data=='success')
					    {
						  alert ("删除成功");
						  $("#commentOne"+cid).remove();
					    }else{
					    	alert ("删除失败");
					    }
					    
				    }
				});
			}
	 }
	</script>
</body>
</html>