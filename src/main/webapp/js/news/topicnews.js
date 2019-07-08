function openModel(){
  $("#win").css("display","");
  $("#back").css("display","");
}
function closeModel(){
  $("#win").css("display","none");
  $("#back").css("display","none");
}
function openCategoryModel(){
	  $("#Category").css("display","");
	  $("#back").css("display","");
	}
function closeCategoryModel(){
	  $("#Category").css("display","none");
	  $("#back").css("display","none");
	}
function openPidModel(){
	  $("#pidModel").css("display","");
	  $("#back").css("display","");
	}
function closePidModel(){
	  $("#pidModel").css("display","none");
	  $("#back").css("display","none");
	}
function openBusinessuseridModel(){
	$("#businessuseridModel").css("display","");
	$("#back").css("display","");
}
function closeBusinessuseridModel(){
	$("#businessuseridModel").css("display","none");
	$("#back").css("display","none");
}

function openDiscussOrderindexModel(){
	  $("#DiscussOrderindex").css("display","");
	  $("#back").css("display","");
	}
function closeDiscussOrderindexModel(){
	  $("#DiscussOrderindex").css("display","none");
	  $("#back").css("display","none");
	}
function openNewmotomodelCePingModel(){
	$("#NewmotomodelCePing").css("display","");
	$("#back").css("display","");
}
function closeNewmotomodelCePingModel(){
	$("#NewmotomodelCePing").css("display","none");
	$("#back").css("display","none");
}

function delnewstopic (nid, keywords)
{
	if (confirm ("确定删除该动态话题？"))
	{
		if (confirm ("再次确认，删除后无法恢复！"))
		{
			var datas =
			{
			    "userid" : serviceuserid,
			    "sign" : servicesign,
			    "nid" : nid,
			    "keywords" : keywords
			}

			$.ajax (
			{
			    type : "POST",
			    url : serviceurl + '/delnewstopic',
			    data : JSON.stringify (datas),
			    success : function (data)
			    {
				    if (data != "" && data != null)
				    {
					    var json = eval ('(' + data + ')');
					    if (json.code == "0")
					    {
						    
						    alert ("删除话题成功");
					    }
					    else
					    {
						    alert ("删除话题失败");
					    }
				    }
				    
			    }
			});
		}
	}
	
}
function savetwonum(a){
	a+="";
	if(a.length<2){
		return "0"+a;
	}else{
		return a;
	}
}
function addRecommendNews (nid,userid)
{
	if (confirm ("确认设置为精选？"))
	{
		
		var myDate = new Date();
		year=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
		month=myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
		month=savetwonum(month);
		day=myDate.getDate(); //获取当前日(1-31)
		day=savetwonum(day);
		hours=myDate.getHours() ; //获取当前小时数(0-23)
		hours=savetwonum(hours);
		minutes=myDate.getMinutes(); //获取当前分钟数(0-59)
		minutes=savetwonum(minutes);
		seconds=myDate.getSeconds(); //获取当前秒数(0-59)
		seconds=savetwonum(seconds);
		
		var nowdate=year+month+day+hours+minutes;
		$("#RecommendNews_score").val(nowdate);
		$("#RecommendNews_nid").val(nid);
		$("#RecommendNews_userid").val(userid);
		//$("#RecommendNews_addtype option").removeAttr("selected");
		//$("#RecommendNews_addtype  option[value='0'] ").css("selected","selected");
		$("#RecommendNews_addtype").empty();
		$("#RecommendNews_addtype").append("<option value='0' selected>首屏不显示</option><option value='1'>首屏显示</option>");
		$("#RecommendNews_styletype").empty();
		$("#RecommendNews_styletype").append("<option value='1' selected>横幅 </option><option value='2'>三图</option><option value='3'>左图右文</option>");
		$("#RecommendNews_stylepic").val("");
	
		openModel();
		
	}
}
function addRecommendNewsConfirm(){
	var score=$("#RecommendNews_score").val();
	var nid=$("#RecommendNews_nid").val();
	var userid=$("#RecommendNews_userid").val();
	var addtype=$("#RecommendNews_addtype").val();
	var styletype=$("#RecommendNews_styletype").val();
	var stylepic=$("#RecommendNews_stylepic").val();
	var reg=/^[1-9]\d*$/;
	if(score==''||score==null ||reg.test(score)==false){
		alert("score不合法");
		return false;
		
	}
	var datas =
	{
	    "nid" : nid,
	    "addtype":addtype,
	    "score" : score,
	    "userid":userid,
	    "styletype":styletype,
	    "stylepic":stylepic
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/addrecommendnews',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("添加精选成功");
			    	
			    	closeModel();
			    }else{
			    	alert ("添加精选失败");
			    	
			    	closeModel();
			    }
		    }
		    
	    }
	});
}
function changekeywords ()
{
	
	var datas =
	{
	    "userid" : serviceuserid,
	    "sign" : servicesign,
	    "keyword1" : $ ('#topickeyword1').val (),
	    "keyword2" : $ ('#topickeyword2').val (),
	    "nid" : $ ('#updkeywordnid').val (),
	    "keywords" : $ ('#updkeywords').val ()
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : serviceurl + '/news/changekeywords',
	    data : JSON.stringify (datas),
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    var json = eval ('(' + data + ')');
			    if (json.code == "0")
			    {
				    $ ("#fullscreen").css ("display", "none");
				    $ ("#er").css ("display", "none");
				    alert ("更换成功");
			    }
			    else
			    {
				    $ ("#fullscreen").css ("display", "none");
				    $ ("#er").css ("display", "none");
				    alert ("更换失败");
			    }
		    }
		    
	    }
	});
	
}
function deleteUserSingleNews(nid,userid){
	if (confirm ("确认要删除这条动态？此操作不可逆")){
		var datas =
		{
		    "nid" : nid,
		    "userid":userid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/deleteUserSingleNews',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data != "" && data != null)
			    {
				    if (data == "success")
				    {
				    	alert ("删除成功");
				    	$("#"+nid).remove();
				    	
				    }else{
				    	alert ("删除失败");
				    	
				    }
			    }
			    
		    }
		});
	}

}
function changeCategory(nid,categoryid){
	var myDate = new Date();
	year=myDate.getFullYear(); //获取完整的年份(4位,1970-????)
	month=myDate.getMonth()+1; //获取当前月份(0-11,0代表1月)
	month=savetwonum(month);
	day=myDate.getDate(); //获取当前日(1-31)
	day=savetwonum(day);
	hours=myDate.getHours() ; //获取当前小时数(0-23)
	hours=savetwonum(hours);
	minutes=myDate.getMinutes(); //获取当前分钟数(0-59)
	minutes=savetwonum(minutes);
	seconds=myDate.getSeconds(); //获取当前秒数(0-59)
	seconds=savetwonum(seconds);
	
	var nowdate=year+month+day+hours+minutes;
	$("#category_score").val(nowdate);
	$("#category_nid").val(nid);
	$("input[name='category_type'][value='"+categoryid+"']").attr("checked",true);
	if(categoryid>0){
		$("#old_categoryid").val(categoryid);
	}else{
		$("#old_categoryid").val(0);
	}
	
	openCategoryModel();
}
function changeCategoryConfirm(){
	var score= $("#category_score").val();
	var nid=$("#category_nid").val();
	var oldcategoryid=$("#old_categoryid").val();
	var categoryid= $("input[name='category_type']:checked").val();
	var str="";
	/*if(score==null ||score==''){
		alert("score不能为空");
		return;
	}*/
	if(categoryid==null ||categoryid==''){
		alert("categoryid不能为空");
		return;
	}
	switch(parseInt(categoryid)){
	case 1:
		str="用车经验";
		break;
	case 2:
		str="摩旅游记";
		break;
	case 3:
		str="评测";
		break;
	case 4:
		str="生活文化";
		break;
	case 5:
		str="改装";
		break;
	case 6:
		str="竞技极限";
		break;
	default:
		str="未分类";
		break;
	
	}
	var datas =
	{
	    "nid" : nid,
	    "categoryid":categoryid,
	    "score":score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/updateNewsCategory',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("修改成功");
			    	$("#"+nid+oldcategoryid).text(str);
			    	closeCategoryModel();
			    	
			    }else{
			    	alert ("修改失败");
			    	closeCategoryModel();
			    }
		    }
		    
	    }
	});
	
}
function deleteFromCategory(nid,categoryid){
	if(confirm ("确认要从池中移除此动态吗？此操作不可逆")){
		
	
	var datas =
	{
	    "nid" : nid,
	    "categoryid":categoryid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/deleteFromCategory',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("移除成功");
			    	$("#"+nid).remove();
			    	
			    }else{
			    	alert ("移除失败");
			    	
			    }
		    }
		    
	    }
	});
	}
}
function delFromLabel(nid,label){
	if(confirm ("确认要从池中移除此动态吗？此操作不可逆")){
	var datas =
	{
	    "nid" : nid,
	    "label":label
	   
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/delFromLabel',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("移除成功");
			    	$("#"+nid).remove();
			    	
			    }else{
			    	alert ("移除失败");
			    	
			    }
		    }
		    
	    }
	});
	}
}
function addNewsPid(nid){
	$("#pid_nid").val(nid);
	var pid=$("#"+nid+"pid").text();
	if(pid==null||pid=='undefined'){
		pid="";
	}
	$("#input_pid").val(pid);
	openPidModel();
	
}
function addNewsBusinessuserid(nid){
	$("#businessuserid_nid").val(nid);
	var businessuserid=$("#"+nid+"businessuserid").text();
	console.log(businessuserid);
	if(businessuserid==null||businessuserid=='undefined'){
		businessuserid="";
	}
	$("#input_businessuserid").val(businessuserid);
	openBusinessuseridModel();
	
}

function addNewsPidConfirm(){
	var pid=$.trim($("#input_pid").val());
	var nid=$("#pid_nid").val();
	if(pid==null){
		pid="";
	}
	var datas =
	{
	    "nid" : nid,
	    "pid":pid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/updateNewsPid',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("修改成功");
			    	$("#"+nid+"pid").text(pid);
			    	closePidModel();
			    	
			    }else{
			    	alert ("修改失败");
			    	closePidModel();
			    }
		    }
		    
	    }
	});
}
function addNewsBusinessuseridConfirm(){
	var businessuserid=$.trim($("#input_businessuserid").val());
	var nid=$("#businessuserid_nid").val();
	if(businessuserid==null){
		businessuserid="";
	}
	var datas =
	{
			"nid" : nid,
			"businessuserid":businessuserid
	}
	
	$.ajax (
			{
				type : "POST",
				url : '../news/updateNewsBusinessuserid',
				data : datas,
				success : function (data)
				{
					
					if (data != "" && data != null)
					{
						if (data == "success")
						{
							alert ("修改成功");
							$("#"+nid+"businessuserid").text(businessuserid);
							closeBusinessuseridModel();
							
						}else{
							alert ("修改失败");
							closeBusinessuseridModel();
						}
					}
					
				}
			});
}
function changeDiscussOrderindexConfirm(){
	if(confirm("你真的要改变此动态的顺序么，此操作不可逆，需谨慎操作")){
	
	var nid=$("#discuss_nid").val();
	var keyword=$("#discuss_keyword").val();
	var orderindex=$("#discuss_orderindex").val();
	if(orderindex==null||orderindex==''){
		alert("orderindex不能为空");
		return false;
	}
	var datas =
	{
	    "nid" : nid,
	    "keyword":keyword,
	    "orderindex":orderindex
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/changeDiscussOrderindex',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("修改成功");
			    	closeDiscussOrderindexModel();
			    	
			    }else{
			    	alert ("修改失败");
			    	closeDiscussOrderindexModel();
			    }
		    }
		    
	    }
	});
	
	}
}
function changeDiscussOrderindex(nid,keyword){
	
	$("#discuss_nid").val(nid);
	$("#discuss_keyword").val(keyword);
	$("#discuss_orderindex").val("");
	openDiscussOrderindexModel();
}


function set_bandparent(){
	$("#ins_bp").removeAttr("checked");
	var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../carmanage/selectCarbrandParentname',
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					      str+="<option value='0'>请选择大品牌</option>";
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].bpid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_bp").empty();
						  $("#ins_bp").append(str);

					    }
				    }
				});

}
function set_brand(){
	$("#ins_brand").removeAttr("checked");
	var bpid=$("#ins_bp").val();
	var str="";
	var datas =
		{
		    "bpid" :bpid
		   
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../carmanage/getBrandListByBpid',
				    data:datas,
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					      str+="<option value='0'>请选择小品牌</option>"; 
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].brandid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_brand").empty();
						  $("#ins_brand").append(str);

					    }
				    }
				});

}
function set_motomodel(){
	$("#ins_model").removeAttr("checked");
	var brandid=$("#ins_brand").val();
	var str="";
	var datas =
	{
			"brandid" :brandid
			
	}
	$.ajax (
			{
				type : "POST",
				url : '../carmanage/getMotomodelByBrandid',
				data:datas,
				success : function (data)
				{
					if (data!=null && data!="")
					{
						var jsondata=eval(data);
						 str+="<option value='0'>请选择车型</option>";
						for(var i=0; i<jsondata.length;i++){
							str+="<option value='"+jsondata[i].modelid+"'>"+jsondata[i].name+"</option>";
							
						}
						$("#ins_model").empty();
						$("#ins_model").append(str);
						
					}
				}
			});
	
}

function changeNewmotomodelCePingConfirm(){
	if(confirm("你真的要把此测评放到该车型下吗，此操作不可逆，需谨慎操作")){
	
	var nid=$("#newmotomodelceping_nid").val();
	var modelid=$("#ins_model").val();
	if(modelid==null||modelid==''){
		alert("请选择车型再提交");
		return false;
	}
	var datas =
	{
	    "nid" : nid,
	    "modelid":modelid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/changeNewmotomodelCePing',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
			    	alert ("修改成功");
			    	closeNewmotomodelCePingModel();
			    	
			    }else{
			    	alert ("修改失败");
			    	closeNewmotomodelCePingModel();
			    }
		    }
		    
	    }
	});
	
	}
}
function changeNewmotomodelCePing(nid){
	
	$("#newmotomodelceping_nid").val(nid);
	openNewmotomodelCePingModel();
	set_bandparent();
}