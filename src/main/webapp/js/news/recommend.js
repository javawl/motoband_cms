
function openBoxUrl(boxurl){
	 window.open(boxurl);
}
function selnews (nid)
{
	window.open ("../news/newsDynamic?nid=" + nid);
}
function updateScore (nid)
{
	$ ('#update_score').val ($ ("." + nid + ">:first-child").text ());
	$ ('#update_nid').val (nid);
}
function saveRecommend ()
{
	var score = $ ('#update_score').val ();
	var nid = $ ('#update_nid').val ();
	$ ("." + nid + ">:first-child").text (score);
	
	var datas =
	{
	    "userid" : serviceuserid,
	    "sign" : servicesign,
	    "nid" : nid,
	    "score" : score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/saverecommendnews',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("保存成功");
		    }
		    
	    }
	});
}
function saveStoryRecommend ()
{
	var score = $ ('#update_score').val ();
	var nid = $ ('#update_nid').val ();
	$ ("." + nid + ">:first-child").text (score);
	
	var datas =
	{
	    "userid" : serviceuserid,
	    "sign" : servicesign,
	    "nid" : nid,
	    "score" : score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/addstoryrecommendnews',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("保存成功");
		    }
		    
	    }
	});
}
function delRecommenddiag (nid, score)
{
	$ ('#delNewNid').val (nid);
	$ ('#delNewScore').val (score);
	
}
function delStoryRecommend ()
{
	var nid = $ ('#delNewNid').val ();
	var score = $ ('#delNewScore').val ();
	var datas =
	{
	    "nid" : nid,
	    "score" : score
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/delStoryrecommendnews',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("删除成功");
			    $ ('.' + nid).css ("display", "none");
			    
		    }
		    
	    }
	});
}
function delRecommend ()
{
	var nid = $ ('#delNewNid').val ();
	var score = $ ('#delNewScore').val ();
	var datas =
	{
	    "nid" : nid,
	    "score" : score
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/delrecommendnews',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("删除成功");
			    $ ('.' + nid).css ("display", "none");
			    
		    }
		    
	    }
	});
}


function savetwonum(a){
	a+="";
	if(a.length<2){
		return "0"+a;
	}else{
		return a;
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
			    	
			    }else{
			    	alert ("修改失败");
			    	
			    }
		    }
		    
	    }
	});
	
}