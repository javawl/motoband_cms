var userid = "admin";
var sign = "eJx1kNFOwjAUhu-3FE2vjQ7WjUHizYDIdEiqQ9Gbplm7erJQSlcSN*O7OycJu-Hcft9--pPz5SGEcJ49X-OiOJy0Y64xEqMZwmNCfHx14caAYNyxwIqej4jfDZmQeGDJTwNWMl46af*scBLFv*JwFwipHZRwdrjYgx7gWlSsr-u-pwbVw-VyO0-pQtMw*XiEpM3p*v2p2i101MYnp9SGJqVVRqzqm50sm-aepiqnD25cT6tA2*PhJeNJ5ecRCY6v0d0mbLJglXbB*bKA7dvtoNLBXp4PCkn3nOkoxt639wPahliJ";

function selnews (nid)
{
	window.open ("../news/newsDynamic?nid=" + nid);
}
function updateScore (nid)
{
	$ ('#update_score').val ($ ("." + nid + ">:first-child").text ());
	$ ('#update_nid').val (nid);
}
function saveUserRecommend ()
{
	var score = $ ('#update_score').val ();
	if(score==null && score ==""){
		alert("score不能为空");
		return;
	}
	var userid = $ ('#update_nid').val ();
	$ ("." + userid + ">:first-child").text (score);
	
	var datas =
	{
	    "uid" : userid,
	    "score" : score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/updateUserRecommend',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("编辑成功");
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
function delRecommenddiag (nid)
{
	$ ('#delNewNid').val (nid);

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
	var datas =
	{
	    "userid" : nid

	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/deleteUserRecommend',
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
function publishUserRecommend ()
{
	
	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/publishUserRecommend',
	    data : "",
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    if (data == "success")
			    {
				    alert ("发布成功");
			    }
		    }
		    
	    }
	});
}
function gotoMotoUserlist(param_userid,adminGuid){
	$ ("#page-wrapper").load ("../motouser/motouserlist?userGuid=" + adminGuid+"&motouserid="+param_userid);
	
/*	var datas =
	{

	    "uid" : param_userid
	}
    var str="";
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/getuserprofile",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval ("("+data+")");
			    str += "<tr>";
	            str += "	<td>" + json.userid + "</td>";
	            if(json.gender=='0'){
	            	str += "	<td>男</td>";
	            }else{
	            	str += "	<td>女</td>";
	            }
	            
	            str += "	<td>" + json.birth + "</td>";
	            str += "	<td>" + json.nickname + "</td>";
	            str += "	<td>" + json.area + "</td>";
	            str += "	<td><img style='width:60px;height:60px' src='" + json.headurl + "'></td>";
	            str += "	<td>" + json.signature + "</td>";
	            str += "	<td>" + format (json.addtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
	            str += "	<td>" + json.channel + "</td>";
	            str += "	<td>" + json.level + "</td>";
	            str += "	<td>" + json.province + "</td>";
	            str += "	<td>" + json.city + "</td>";
	            str += "	<td><button type='button' class='btn btn-primary btn-lg' onclick='javascript:searchByuserid2(\""
	                    + json.userid + "\")'>用户查询</button></td>";
	            str += "</tr>";
	            
	            $('#userid').val(json.userid);

		    }
		    $ ('#userlist').html (str);
		    
	    }
	});
	
	 $ ('#addtime').val ("");
	    $ ('#area').val ("");
	    $ ('#birth').val ("");
	    $ ('#channel').val ("");
	    $ ('#friendcount').val ("");
	    $ ('#gender').val ("");
	    $ ('#headurl').val ("");
	    $ ('#level').val ("");
	    $ ('#mbid').val ("");
	    $ ('#mobileno').val ("");
	    $ ('#newscount').val ("");
	    $ ('#nickname').val ("");
	    $ ('#signature').val ("");
	    $ ('#updatetime').val ("");
	    $ ('#userid2').val ("");
	    $ ('#mileage').val ("");
	    $ ('#ridetime').val ("");
	    $ ('#ridecount').val ("");
	    $ ('#garagecount').val ("");
	    $ ('#linecount').val ("");
	    $ ('#lineupdatetime').val ("");
	    $ ('#maxmileage').val ("");
	    $ ('#maxridetime').val ("");
	    $ ('#topspeed').val ("");
	    $ ('#maxdipangle').val ("");
	    $ ('#usertype').val ("");
	    $ ('#approvedes').val ("");
	    $ ('#commentswitch').val ("");
	    $ ('#likeswitch').val ("");
	    $ ('#imswitch').val ("");
	    $ ('#systemswitch').val ("");
	    $ ('#followswitch').val ("");
	    $ ('#rideshareswitch').val ("");
	    $ ('#nearuserswitch').val ("");
	    $ ('#followcount').val ("");
	    $ ('#followedcount').val ("");
	    $ ('#recommendcount').val ("");
	    
	    $('#usergarage').empty();
	    $('#userridedatelog').empty();
	    $('#userachievement').empty();
	    $ ('#userrideline').empty();*/
}
function updateOlddriverScore (userid)
{
	$ ('#update_score').val ($ ("." + userid + ">:first-child").text ());
	$ ('#update_userid').val (userid);
}
function saveOlddriver()
{
	var score = $ ('#update_score').val ();
	if(score==null && score ==""){
		alert("score不能为空");
		return;
	}
	var userid = $ ('#update_userid').val ();
	$ ("." + userid + ">:first-child").text (score);
	
	var datas =
	{
	    "uid" : userid,
	    "score" : score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/updateUserOlddriver',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("编辑成功");
		    }
		    
	    }
	});
}
function delOlddriverdiag(userid)
{
	$ ('#deluserid').val (userid);

}
function delOlddriver()
{
	var userid = $ ('#deluserid').val ();
	var datas =
	{
	    "userid" : userid

	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/deleteUserOlddriver',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    alert ("删除成功");
			    $ ('.' + userid).css ("display", "none");
			    
		    }
		    
	    }
	});
}