function addBoxBanner(boxid){
	var datas =
	{
		
		"boxid" : boxid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/addBoxBanner",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    	alert("添加到内容页面横幅成功！");
		    	
			    
		    }
		    
	    }
	});
	
}
function delBoxHot(boxid){
	var datas =
	{
		"state":0,
		"boxid" : boxid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/boxhotmanage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    	alert("删除热门成功");
		    	$ ('#ishotbox' + boxid).css ("display", "none");
		    	$ ('#isnothotbox' + boxid).css ("display", "block");
		    	$ ('.' + boxid + ' td').eq (9).text (0);
			    
		    }
		    
	    }
	});
}
function addBoxHot(boxid){
	var datas =
	{
		"state":1,
		"boxid" : boxid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/boxhotmanage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    	alert("添加热门成功");
		    	$ ('#ishotbox' + boxid).css ("display", "block");
		    	$ ('#isnothotbox' + boxid).css ("display", "none");
		    	$ ('.' + boxid + ' td').eq (9).text (1);
		    }
		    
	    }
	});
}
function sellook (boxid)
{
	var datas =
	{
		"boxid" : boxid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/sellook",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("文章ID：" + boxid + ",  浏览量：" + data);
			    
		    }
		    
	    }
	});
}
function delFromBoxRecommend (recommendBoxID)
{
	var datas =
	{
		"recommendBoxID" : recommendBoxID
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/delFromBoxRecommend",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    		alert("删除成功");
		    	 $ ('.' + recommendBoxID).css ("display", "none");
		    }
		    
	    }
	});
}
function addToBox (recommendBoxID)
{
	window.open ('../boxmanage/addNewBoxPage?recommendBoxID=' + recommendBoxID);
}
function boxRecommendList ()
{
	$ ("#page-wrapper").load ("../boxmanage/boxRecommendList");
}
function addNewBoxPage ()
{
	// $("#page-wrapper").load("../boxmanage/addNewBoxPage");
	window.open ('../boxmanage/addNewBoxPage');
}
function addNewBoxPage1 ()
{
	$ ("#page-wrapper").load ("../boxmanage/addNewBoxPage1");
	// window.open ('../boxmanage/addNewBoxPage1');
}
function selBoxMessage (box_id)
{
	window.open ("../boxmanage/boxsel?box_id=" + box_id);
}
function imgSourseManage ()
{
	$ ("#page-wrapper").load ("../boxmanage/imgSourceManage");
}
function typechanged ()
{
	var order= $("#triangleHidden").val();
	var orderConditions= $("#triangleHiddenValue").val();
	$ ("#page-wrapper").load ("../boxmanage/boxlist?nowtypeid=" + $ ("#typeselect").val ()+"&page=1&limit="+$("#pageSizeSelect").val()+"&order="+order+"&orderConditions="+orderConditions);
}
function delBoxGuid (box_guid)
{
	$ ("#delBoxGuid").val (box_guid);
}
function delBoxMessage ()
{
	var box_guid = $ ("#delBoxGuid").val ();
	var datas =
	{
		"box_id" : box_guid
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/delBoxMessage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("删除手册信息成功");
			    $ ("#closedelBoxModel").click ();
			    $ ("#page-wrapper").load ("../boxmanage/boxlist?userGuid=" + data);
		    }
		    
	    }
	});
}
function addBanner (boxid, boxurl)
{
	
	var datas =
	{
	    // "userid" : serviceuserid,
	    // "sign" : servicesign,
	    // "data" :
	    // {
	    "boxurl" : boxurl,
	    "type" : 1,
	    "boxid" : boxid
	// }
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/addbanner',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
				    alert ("添加成功");
			    }
		    }
		    
	    }
	});
	
}
function lookcomments(boxid){
	 window.open("../boxmanage/boxcomments?boxid=" + boxid);
}
function releaseNews(typeid,boxid){
	if (confirm ("确认要把这条box发布动态？")){
	var datas =
	{
		"typeid" : typeid,
		"boxid":boxid
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/releaseNews",
	    data : datas,
	    success : function (data)
	    {
		    if (data=='success')
		    {
			    alert ("发布成功");
			    $("#news"+boxid).text("发过动态");
			    
		    }else{
		    	 alert ("该手册已经发过动态，无需再发");
		    }
		    
	    }
	});
  }
}
function boxAddToRecommendnews(typeid,boxid){
if (confirm ("确认要添加到精选？")){
	var addtype = prompt ("是否设置为主屏显示，请输入0或者1（0 不显示   1显示）", 1);
		if(isNaN (addtype)){
			alert ("输入的不是数字");
			return ;
		}else if(parseInt(addtype)!=0 && parseInt(addtype)!=1 ){
			alert ("输入的不是0和1");
			return ;
		}
	var datas =
	{
		"typeid" : typeid,
		"boxid":boxid,
		"addtype":addtype
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/boxAddToRecommendnews",
	    data : datas,
	    success : function (data)
	    {
		    if (data=='success')
		    {
			    alert ("添加成功");
			    
		    }else{
		    	alert("添加失败，请先发布动态再添加");
		    }
		    
	    }
	});
	}
}