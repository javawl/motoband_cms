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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<select class="form-control" id="inputUrlgpid"
			onchange="javascript:gpSelectChange(this)">
			<c:forEach items="${limitList}" var="optionlimit" varStatus="ids">
				<c:choose>
					<c:when test="${optionlimit==limit }">
						<option value='' ></option>
					</c:when>
					<c:otherwise>
						<option value="${optionlimit}">${optionlimit}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</div>
	// 日志记录
	public	static Logger logger = LoggerFactory.getLogger(newsController.class);
	logger.debug("request data:[url:"+urlString+"  data:"+data+"]");
	logger.debug("response data:["+result+"]");
	
	display:none; POSITION:absolute; z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:#000; filter:alpha(opacity=60)
	<script src="../js/jquery-1.11.0.min.js"></script>
	
	<select class="form-control" id="update_state">
		<option value='0'>未上线</option>
		<option value='1'>上线</option>
	</select>
	
	<c:choose>
		 <c:when test="${bannerMessages.state =='1'}">
			<td class="center">上线</td>
		</c:when>
	    <c:when  test="${bannerMessages.state =='0'}">
			<td class="center">未上线</td>
		</c:when>
  </c:choose>
   <label><font color="red">请选择750x350的图片</font></label>
   style="word-break:break-all;"
	<a class='btn btn-primary' href="javascript:pageGoto('')">1</a>				
	style="height:800px"
	style="margin-top:14px;"
	
	style="text-decoration:none;"	
				
	 $('#addmotobandgpModel').modal('hide');
	 
	//当$("#addmotobandgpModel").modal("hide");执行完，但有可能modal('hide')里面的样式操作，
	//dom操作还没有渲染完，页面刷新了，所以造成了modal有样式残余。(加载页面滚动消失)
	$(".modal-backdrop").remove();
	$("body").removeClass('modal-open');
	 
	 $("input[name='adtype'][value='"+datajson.adtype+"']").attr("checked",true);
	 var adtype= $("input[name='adtype']:checked").val();
	    $("#RecommendNews_addtype option").removeAttr("selected");
		$("#RecommendNews_addtype  option[value='0'] ").css("selected","selected");
		$("#RecommendNews_addtype").empty();
		$("#RecommendNews_addtype").append("<option value='0' selected>首屏不显示</option><option value='1'>首屏显示</option>");
	 <b class="caret"></b>
	 
<script type="text/javascript">
function importOnce(scriptPath) {

    var scripts = document.getElementsByTagName("script");

    for (var index = 0; index < scripts.length; index++) {

      if (scripts[index].src.lastIndexOf(scriptPath) != -1) {

        return;

      }

    }

    var scriptTag = document.createElement("script");

    scriptTag.type = "text/javascript";

    scriptTag.src = scriptPath;

    var headTag = document.getElementsByTagName("head")[0];

    headTag.appendChild(scriptTag);

  }
  importOnce("../bower_components/jquery/dist/jquery.min.js");
</script>
<script>
   $(function () { $('#myModal').on('hide.bs.modal', function () {
      alert('嘿，我听说您喜欢模态框...');})
   });
</script>				
  
</body>
</html> 