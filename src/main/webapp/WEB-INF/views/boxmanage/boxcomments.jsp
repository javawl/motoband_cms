<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Language" content="zh-CN" />
<meta name="viewport" content="width=device-width,initial-scale=1">
<meta content="yes" name="apple-mobile-web-app-capable">
<meta content="black" name="apple-mobile-web-app-status-bar-style">
<meta content="telephone=no" name="format-detection">
<title>阅读评论</title>
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

	<div style="width: 100%;">
			<!--加入评论  -->、
		<div id="newsDynamic">
		   <c:choose>
		     <c:when test="${commentlist ==null or commentlist==''}">
		         <h3><font color="yellow">此阅读还没有评论哦~赶紧去抢沙发吧</font></h3>
		     </c:when>
		   </c:choose>
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
								  <a href="javascript:deleteboxcomment('${clist.cid}','${clist.boxid}')" style="text-decoration:none"><font color="red">删除</font></a></span>
							</c:when>
							<c:otherwise>
								<span>&nbsp&nbsp&nbsp</span>
								<span><font color="#828282">${clist.content}</font></span>
								<span>&nbsp&nbsp&nbsp</span>
								<span style="float:right"><font color="#828282">回复于：${clist.ctimeString}&nbsp</font>
								  <a href="javascript:deleteboxcomment('${clist.cid}','${clist.boxid}')" style="text-decoration:none"><font color="red">删除</font></a></span>
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
	 function deleteboxcomment(cid,boxid){
		 if(confirm("您真的要把这条评论删除么？此操作不可逆")){
				var datas =
				{
				    "cid" : cid,
				    "boxid":boxid
				   
				}

				$.ajax (
				{
				    type : "POST",
				    url : '../boxmanage/deleteboxcomment',
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