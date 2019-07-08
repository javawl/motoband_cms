var userid = "admin";
var sign = "eJx1kNFOwjAUhu-3FE2vjQ7WjUHizYDIdEiqQ9Gbplm7erJQSlcSN*O7OycJu-Hcft9--pPz5SGEcJ49X-OiOJy0Y64xEqMZwmNCfHx14caAYNyxwIqej4jfDZmQeGDJTwNWMl46af*scBLFv*JwFwipHZRwdrjYgx7gWlSsr-u-pwbVw-VyO0-pQtMw*XiEpM3p*v2p2i101MYnp9SGJqVVRqzqm50sm-aepiqnD25cT6tA2*PhJeNJ5ecRCY6v0d0mbLJglXbB*bKA7dvtoNLBXp4PCkn3nOkoxt639wPahliJ";
function openModel(){
	  $("#win").css("display","");
	  $("#back").css("display","");
	}
function closeModel(){
$("#win").css("display","none");
$("#back").css("display","none");
}
function openvipModel(){
	$("#win1").css("display","");
	$("#back").css("display","");
}
function opendeletevipModel(){
	$("#win2").css("display","");
	$("#back").css("display","");
}
function closevipModel(){
	$("#win1").css("display","none");
	$("#back").css("display","none");
}

function closedeletevipModel(){
	$("#win2").css("display","none");
	$("#back").css("display","none");
}
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
function searchByuserid2 (targetid)
{
	$ ('#userid').val (targetid);
	var datas =
	{
	    "uid" : targetid
	}
	var datas1 =
	{
	    "userid" : userid,
	    "sign" : sign,
	    "targetid" : targetid
	}
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
			//    console.log(json);
			    $ ('#addtime').val (format (json.addtime, 'yyyy-MM-dd HH:mm:ss'));
			    $ ('#area').val (json.area);
			    $ ('#birth').val (json.birth);
			    $ ('#channel').val (json.channel);
			    $ ('#friendcount').val (json.friendcount);
			    if(json.gender=='0'){
			    	  $ ('#gender').val ("男");
			    }else{
			    	  $ ('#gender').val ("女");
			    }
			  
			    $ ('#headurl').val (json.headurl);
			    $ ('#level').val (json.level);
			    $ ('#mbid').val (json.mbid);
			    $ ('#mobileno').val (json.mobileno);
			    $ ('#newscount').val (json.newscount);
			    $ ('#nickname').val (json.nickname);
			    $ ('#signature').val (json.signature);
			    $ ('#updatetime').val (format (json.updatetime, 'yyyy-MM-dd HH:mm:ss'));
			    $ ('#userid2').val (json.userid);
			    $ ('#approvedes').val (json.approvedes);
			    $ ('#followcount').val (json.followcount);
			    $ ('#followedcount').val (json.followedcount);
			    $ ('#recommendcount').val (json.recommendcount);
			    $ ('#credit').val (json.credit);
			    $ ('#province').val (json.province);
			    $ ('#city').val (json.city);
			    $ ('#nationcode').val (json.nationcode);
			    $ ('#weixinid').val (json.weixinid);
			    $ ('#weiboid').val (json.weiboid);
			    $ ('#qqid').val (json.qqid);
			    
			    if(json.usertype=='0'){
			    	  $ ('#usertype').val ("普通");
			    }else if(json.usertype=='1'){
			    	 $ ('#usertype').val ("官方运营");
			    }else if(json.usertype=='2'){
			    	 $ ('#usertype').val ("达人");
			    }else  if(json.usertype=='3'){
			    	 $ ('#usertype').val ("认证");
			    }else  if(json.usertype=='4'){
			    	 $ ('#usertype').val ("老司机");
			    }
			    
			    if(json.commentswitch=='0'){
			    	  $ ('#commentswitch').val ("开启");
			    }else{
			    	  $ ('#commentswitch').val ("关闭");
			    }
			    if(json.likeswitch=='0'){
			    	  $ ('#likeswitch').val ("开启");
			    }else{
			    	  $ ('#likeswitch').val ("关闭");
			    }
			    if(json.imswitch=='0'){
			    	  $ ('#imswitch').val ("开启");
			    }else{
			    	  $ ('#imswitch').val ("关闭");
			    }
			    if(json.systemswitch=='0'){
			    	  $ ('#systemswitch').val ("开启");
			    }else{
			    	  $ ('#systemswitch').val ("关闭");
			    }
			    if(json.followswitch=='0'){
			    	  $ ('#followswitch').val ("开启");
			    }else{
			    	  $ ('#followswitch').val ("关闭");
			    }
			    if(json.rideshareswitch=='0'){
			    	  $ ('#rideshareswitch').val ("开启");
			    }else{
			    	  $ ('#rideshareswitch').val ("关闭");
			    }
			    if(json.nearuserswitch=='0'){
			    	  $ ('#nearuserswitch').val ("开启");
			    }else{
			    	  $ ('#nearuserswitch').val ("关闭");
			    }
			    if(json.pkswitch=='0'){
			    	$ ('#pkswitch').val ("开启");
			    }else{
			    	$ ('#pkswitch').val ("关闭");
			    }
			    
			    var oldlongstr=json.userprivilegelongstr;
			    console.log(oldlongstr);
			    
			    for(var j=0;j<8;j++){
			    	console.log(oldlongstr.substring(j,j+1));
			    	$("#vip"+j).text(oldlongstr.substring(j,j+1));
			    }
			    
			    
			    $('#usergarage').empty();
			    $('#userridedatelog').empty();
			    $('#userachievement').empty();
			    $ ('#userrideline').empty();
			    
		    }
		    
	    }
	});

	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/getridedata",
	    data : JSON.stringify (datas1),
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    
		    	var json = eval ("("+data+")");
		    	var returndata = JSON.parse(json.data);
		    	
			    $ ('#mileage').val (returndata.mileage);
			    $ ('#ridetime').val (returndata.ridetime);
			    $ ('#ridecount').val (returndata.ridecount);
			    $ ('#garagecount').val (returndata.garagecount);
			    $ ('#linecount').val (returndata.linecount);
			    $ ('#lineupdatetime').val (format (returndata.lineupdatetime, 'yyyy-MM-dd HH:mm:ss'));
			    $ ('#maxmileage').val (returndata.maxmileage);
			    $ ('#maxridetime').val (returndata.maxridetime);
			    $ ('#topspeed').val (returndata.topspeed);
			    $ ('#maxdipangle').val (returndata.maxdipangle);
			    
		    }
		    
	    }
	});
	

	
}
function searchByuserid ()
{
	var targetid = $.trim($('#userid').val ());
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{
	    "uid" : targetid
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
			
		    }
		    $ ('#userlist').html (str);
	    }
	});
	
	userinfoClear();

}
function searchUserRideline ()
{
	var targetid = $.trim($('#userid').val ());
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{
		"userid" : targetid
	}
	// $('#rideline').attr("display","block");
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/searchUserRideLine",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval (data);
			    var str = "";
			    for (var i = 0; i < json.length; i++)
			    {
				    
				    str += "<tr>";
				    str += "	<td>" + json[i].ridelineid + "</td>";
				    str += "	<td><input type='text' style='width:80px;' value='" + json[i].dataurl + "'/></td>";
				    str += "	<td>" + format (json[i].starttime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + format (json[i].endtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + json[i].ridetime + "</td>";
				    str += "	<td>" + json[i].maxelevation + "</td>";
				    str += "	<td>" + json[i].maxspeed + "</td>";
				    str += "	<td>" + json[i].avgspeed + "</td>";
				    str += "	<td>" + json[i].dipangle + "</td>";
				    str += "	<td>" + json[i].hundredtime + "</td>";
				    str += "	<td>" + json[i].fourhundredtime + "</td>";
				    str += "	<td style='color:red'>" + json[i].mileage + "</td>";
				    str += "	<td>" + format (json[i].synctime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + json[i].ischecked + "</td>";
				    str += "	<td>";
				    str += "		<button type='button' class='btn btn-success' onclick=\"javascript:openrideline(\'"
				            + json[i].ridelineid + "\')\">查看</button>";
				    str += "	</td>";
				    str += "</tr>";
				    
			    }
			    
			    $ ('#userrideline').html (str);
			    
		    }
	    }
	});
	
}
function openrideline (ridelineid)
{
	window.open ("../motorideline/getridelinelbasamap?ridelineid=" + ridelineid);
}
function searchUserRideDateLog ()
{
	var targetid = $ ('#userid').val ();
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{
		"userid" : targetid
	}
	// $('#rideline').attr("display","block");
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/searchUserRideDateLog",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval (data);
			    
			    var str = "";
			    for (var i = 0; i < json.length; i++)
			    {
				    
				    str += "<tr>";
				    str += "	<td>" + json[i].id + "</td>";
				    str += "	<td style='color:red'>" + json[i].mileage + "</td>";
				    str += "	<td>" + json[i].ridetime + "</td>";
				    str += "	<td>" + format (json[i].reporttime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + format (json[i].starttime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + format (json[i].endtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + json[i].maxelevation + "</td>";
				    str += "	<td>" + json[i].maxspeed + "</td>";
				    str += "	<td>" + json[i].avgspeed + "</td>";
				    str += "	<td>" + json[i].dipangle + "</td>";
				    str += "	<td>" + json[i].hundredtime + "</td>";
				    str += "<td>" + json[i].fourhundredtime + "</td>";
				    str += "<td style='word-break: break-all; width: 200px;display: inline-block;'>" + json[i].ridelineid + "</td>";
				    str += "<td>" + json[i].temperature + "</td>";
				    str += "<td>" + json[i].ischecked + "</td>";
				    // str += " <td>" + json[i].startlongitude + "</td>";
				    // str += " <td>" + json[i].startlatitude + "</td>";
				    // str += " <td>" + json[i].endlongitude + "</td>";
				    // str += " <td>" + json[i].endlatitude + "</td>";
				    str += "</tr>";
				    
			    }
			    
			    $ ('#userridedatelog').html (str);
			    
		    }
	    }
	});
}
function searchByusernickname ()
{
	
	var targetid = $.trim($ ('#usernickname').val ());
	if (targetid == null || targetid == "")
	{
		alert ("查询用户昵称不能为空！");
		return false;
	}
	var datas =
	{
		"usernickname" : targetid
	}

	$.ajax (
	        {
	            type : "POST",
	            url : "../motouser/searchByusernickname",
	            data : datas,
	            success : function (data)
	            {
		            if (data != "" && data != null)
		            {
			            var json = eval (data);
			            var str = "";
			            for (var i = 0; i < json.length; i++)
			            {
				            
				            str += "<tr>";
				            str += "	<td>" + json[i].userid + "</td>";
				            if(json[i].gender=='0'){
				            	str += "	<td>男</td>";
				            }else{
				            	str += "	<td>女</td>";
				            }
				            str += "	<td>" + json[i].birth + "</td>";
				            str += "	<td>" + json[i].nickname + "</td>";
				            str += "	<td>" + json[i].area + "</td>";
				            str += "	<td><img style='width:60px;height:60px' src='" + json[i].headurl + "'></td>";
				            str += "	<td>" + json[i].signature + "</td>";
				            str += "	<td>" + format (json[i].addtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				            str += "	<td>" + json[i].channel + "</td>";
				            str += "	<td>" + json[i].level + "</td>";
				            str += "	<td>" + json[i].province + "</td>";
				            str += "	<td>" + json[i].city + "</td>";
				            str += "	<td><button type='button' class='btn btn-primary btn-lg' onclick='javascript:searchByuserid2(\""
				                    + json[i].userid + "\")'>用户查询</button></td>";
				            str += "</tr>";
				            
			            }
			            
			            $ ('#userlist').html (str);
			            
			            userinfoClear();
			    	    
			            
		            }
		            
	            }
	        });
}

function searchByusermobileno ()
{
	
	var targetid = $.trim($ ('#usermobileno').val ());
	if (targetid == null || targetid == "")
	{
		alert ("查询用户号码不能为空！");
		return false;
	}
	var str="";
	var datas =
	{
		"mobileno" : targetid
	}

	$.ajax (
	        {
	            type : "POST",
	            url : "../motouser/searchByusermobileno",
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
	    			
	    		    }
	    		    $ ('#userlist').html (str);
	    	    }
	    	});
	
	      userinfoClear();

	    }
function searchUserGarage ()
{
	var targetid = $ ('#userid').val ();
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{
		"userid" : targetid
	}
	// $('#rideline').attr("display","block");
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/searchUserGarage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval (data);
			    var str = "";
			    for (var i = 0; i < json.length; i++)
			    {
				    
				    str += "<tr>";
				    str += "	<td>" + json[i].garageid + "</td>";
				    str += "	<td>" + json[i].userid + "</td>";
				    str += "	<td>" + json[i].brandid + "</td>";
				    str += "	<td>" + json[i].modelid + "</td>";
				    str += "	<td>" + json[i].cc + "</td>";
				    str += "	<td>" + json[i].pyear + "</td>";
				    str += "	<td>" + json[i].pics + "</td>";
				    str += "	<td>" + format (json[i].addtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "	<td>" + json[i].mileage + "</td>";
				    str += "</tr>";
				    
			    }
			    
			    $ ('#usergarage').html (str);
			    
		    }
	    }
	});
	
}
function searchUserAchievement ()
{
	var targetid = $ ('#userid').val ();
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{

	    "userid" : targetid
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/getuserachievement",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    	
			    var jsonData = eval ("("+data+")");
			    var str = "";
			    for (var i = 0; i < jsonData.length; i++)
			    {
				    str += "<tr>";
				    str += "	<td>" + jsonData[i].achid + "</td>";
					str += "	<td>" + jsonData[i].ach_name + "</td>";
				    str += "	<td>" + format (jsonData[i].gettime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    if(jsonData[i].source!=null && jsonData[i].source!='undefined'){
				    	str += "	<td><textarea rows='2' style='width:500px'>" + jsonData[i].source
			            + "</textarea></td>";	
				    }else{
				    	str += "	<td><textarea rows='2' style='width:500px'></textarea></td>";
				    }
				    if(jsonData[i].motogp!=null && jsonData[i].motogp!='undefined'){
				    	 str += "	<td><textarea rows='2' style='width:500px'>" + jsonData[i].motogp
				            + "</textarea></td>";
				    }else{
				    	 str += "	<td><textarea rows='2' style='width:500px'></textarea></td>";
				    }
				   
				    str += "</tr>";
				    
			    }
			    
			    $ ('#userachievement').html (str);
			    
		    }
	    }
	});
}
function addUserBlack ()
{
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("拉黑用户ID不能为空！");
		return false;
	}
if (confirm("真的要把用户:"+userid+"添加到黑名单么？此操作不可逆")){	
	var datas =
	{
		
		"userid" : userid
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/addblack',
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
}
function addUserRecommendshow(){
	$("#UserRecommend_score").val(""); 
	$("#UserRecommend_approvedes").val("");
}

function addUserRecommend ()
{

	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("精选用户ID不能为空！");
		return false;
	}
	
	if (confirm ("确认设置为精选？"))
	{
//		var score = prompt ("请输入该动态权值", "");
//		var approvedes = prompt ("请输入认证描述", "");
//		if (isNaN (score)||score==''||score==null)
//		{
//			alert ("输入的不是数字或取消");
//			return false;
//		}
//		if(approvedes==null){
//			return false;
//		}
		
		var score = $("#UserRecommend_score").val(); 
		var approvedes = $("#UserRecommend_approvedes").val();
		var reg=/^[1-9]\d*$/;
		if(score==null || score=="" || reg.test(score)==false){
			alert("score不能为空或者不是数字");
			return false;
		}
		if(approvedes==null){
			approvedes="";
		}	
		var datas =
		{
		    "uid" : userid,
		    "score" : score,
		    "approvedes":approvedes
		}

		
		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/addUserRecommend',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data == "success" )
			    { 
				    alert ("添加用户精选成功");  
			    }else{
			    	alert("失败");
			    }
			    
		    }
		});
	}
}
function addUserOlddrivershow(){
	$("#UserOlddriver_score").val(""); 
	$("#UserOlddriver_approvedes").val("");
}

function addUserOlddriver ()
{
	
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("老司机用户ID不能为空！");
		return false;
	}
	
	if (confirm ("确认设置为老司机？"))
	{
		var score = $("#UserOlddriver_score").val(); 
		var approvedes = $("#UserOlddriver_approvedes").val();
		var reg=/^[1-9]\d*$/;
		if(score==null || score=="" || reg.test(score)==false){
			alert("score不能为空或者不是数字");
			return false;
		}
		if(approvedes==null){
			approvedes="";
		}	
		var datas =
		{
				"uid" : userid,
				"score" : score,
				"approvedes":approvedes
		}
		
		
		$.ajax (
				{
					type : "POST",
					url : '../motouser/addUserOlddriver',
					data : datas,
					success : function (data)
					{
						
						if (data == "success" )
						{ 
							alert ("添加老司机成功");  
						}else{
							alert("失败");
						}
						
					}
				});
	}
}

function addUserBoxApproveshow(){
	$("#BoxApprove_media").val(""); 
	$("#BoxApprove_approvedes").val("");		
}
function addUserBoxApprove(){
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("box认证用户ID不能为空！");
		return false;
	}
	
	if (confirm ("确认设置为box认证用户？"))
	{
//		var media = prompt ("请输入媒体", "");
//		var approvedes = prompt ("请输入认证描述", "");
//		if(approvedes==null){
//			return false;
//		}
//		if(media==null){
//			return false;
//		}
		var media = $("#BoxApprove_media").val(); 
		var approvedes = $("#BoxApprove_approvedes").val();		
		if(media==null){
			media="";
		}
		if(approvedes==null){
			approvedes="";
		}
		var datas =
		{
		    "uid" : userid,
		    "media" : media,
		    "approvedes": approvedes
		}

		
		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/addUserBoxApprove',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data == "success")
			    {
				    
				    alert ("添加认证用户成功");
				    
			    }else{
			    	alert("失败");
			    }
			    
		    }
		});
	}
}
function addUserOperation(){
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("运营用户ID不能为空！");
		return false;
	}
	
	if (confirm ("确认设置为运营用户？"))
	{
		var approvedes = prompt ("请输入认证描述", "");
		if(approvedes==null){
			return false;
		}
		var datas =
		{
		    "uid" : userid,
		    "approvedes": approvedes
		}

		
		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/addUserOperation',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data == "success")
			    {
				    
				    alert ("添加运营用户成功");
				   
			    }
			    
		    }
		});
	}
}
function addUserOrdinary(){
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("运营用户ID不能为空！");
		return false;
	}
	
	if (confirm ("确认设置为普通用户？"))
	{
//		var approvedes = prompt ("请输入认证描述", "");
//		if(approvedes==null){
//			return false;
//		}
		var datas =
		{
		    "uid" : userid
//		    "approvedes": approvedes
		}

		
		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/addUserOrdinary',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data == "success")
			    {
				    
				    alert ("添加普通用户成功");
				   
			    }
			    
		    }
		});
	}
}
function idAndNameChange(obj){
	var a = $(obj).val();
	if(a=='0'){
		$("#idAndName0").show();
		$("#idAndName1").hide();
		$("#idAndName2").hide();
	}
	if(a=='1'){
		$("#idAndName1").show();
		$("#idAndName0").hide();
		$("#idAndName2").hide();
	}
	if(a=='2'){
		$("#idAndName2").show();
		$("#idAndName0").hide();
		$("#idAndName1").hide();
	}
}
function searchUserNews(){
	var targetid = $ ('#userid').val ();
	if(targetid=="" || targetid ==null){
		alert("userid不能为空");
		return;
	}
	window.open("../news/motouserIndividualNews?targetid="+targetid);
}
function lookBlacklist(){
	var str="";
	$.ajax (
			{
			    type : "POST",
			    url : '../motouser/lookBlacklist',
			    success : function (data)
			    {
				    
				    if (data != "" && data != null)
				    {
					    var jsonStr = eval("("+data+")");
                          for(var i=0 ;i<jsonStr.length;i++){
                        	  str+="<tr>";
                        	  str+="<td>"+jsonStr[i]+"</td>";
                        	  str+="<td><button type='button' class='btn btn-danger'  onclick=\"javascript:deleteBlackUser('"+jsonStr[i]+"')\">移除</button></td>";
                        	  str+="</tr>";
                          }
                          $("#blacklist").empty();
                          $("#blacklist").append(str);
				    }
				    
			    }
			});
}
function deleteBlackUser(userid){
	if(confirm("真的要把用户:"+userid+"从黑名单移除么？此操作不可逆")){
		var datas =
		{
		    "userid" : userid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/blackUserRemove',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data=='success')
			    {
				  alert ("移除成功");
				  lookBlacklist();
			    }
			    
		    }
		});
	}
}
function deleteUserAllNews(){
	var targetid = $ ('#userid').val ();
	if(targetid=="" || targetid ==null){
		alert("userid不能为空");
		return;
	}
	if(confirm("真的要把用户:"+targetid+"的动态全部删除么？此操作不可逆")){
		var datas =
		{
		    "userid" : targetid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/deleteUserAllNews',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data.indexOf("1")>0)
			    {
				  alert ("删除失败");
				  
			    }else{
			    	alert("删除成功");
			    }
			    
		    }
		});
	}
}
function deleteUserAllComments(){
	var targetid = $ ('#userid').val ();
	if(targetid=="" || targetid ==null){
		alert("userid不能为空");
		return;
	}
	if(confirm("真的要把用户:"+targetid+"的全部评论删除么？此操作不可逆")){
		var datas =
		{
		    "userid" : targetid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/deleteUserAllComments',
		    data : datas,
		    success : function (data)
		    {
			    console.log(data);
			    if (data.indexOf("1")>0)
			    {
				  alert ("删除失败");
			    }else{
			    	alert("删除成功");
			    }
			    
		    }
		});
	}
}
function updateApprovedes(){
	var targetid = $ ('#userid').val ();
	var approvedes= $('#approvedes').val ();
	if(targetid=="" || targetid ==null){
		alert("userid不能为空");
		return;
	}
	
	if(confirm("您确定要修改:"+targetid+"用户的认证信息")){
		var datas =
		{
		    "uid" : targetid,
		    "approvedes":approvedes
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/updateApprovedes',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data=='success')
			    {
				  alert ("修改成功");
			    }else{
			    	alert("修改失败");
			    }
			    
		    }
		});
	}
}
function userinfoClear(){
	$ ('#userid2').val ("");
	$ ('#mbid').val ("");
	$ ('#nickname').val ("");
	$ ('#gender').val ("");
	$ ('#birth').val ("");
	$ ('#area').val ("");
	$ ('#friendcount').val ("");
	$ ('#newscount').val ("");
	$ ('#addtime').val ("");
	$ ('#updatetime').val ("");
	$ ('#mobileno').val ("");
	$ ('#signature').val ("");
	$ ('#level').val ("");
	$ ('#channel').val ("");
	$ ('#usertype').val ("");
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
	$ ('#credit').val ("");
	$ ('#province').val ("");
	$ ('#city').val ("");
	$ ('#approvedes').val ("");
    $ ('#headurl').val ("");
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
    $ ('#nationcode').val ("");
    $ ('#pkswitch').val ("");
    $ ('#weixinid').val ("");
    $ ('#weiboid').val ("");
    $ ('#qqid').val ("");
    
    for(var j=0;j<8;j++){
    	$("#vip"+j).text("");
    }
    
    
    $('#usergarage').empty();
    $('#userridedatelog').empty();
    $('#userachievement').empty();
    $ ('#userrideline').empty();
}
function adduserCreditConfirm(){
	var userid = $ ('#userid').val ();
	var addcredit = $.trim($('#user_addcredit').val ());
	if (userid == null || userid == "")
	{
		alert ("用户ID不能为空！");
		return false;
	}
	
	var reg=/^(-)?[1-9][0-9]*$/;
	if(addcredit==null || addcredit=="" || reg.test(addcredit)==false){
		alert("积分不能为空或者不是数字");
		return false;
	}
if (confirm("真的要为用户:"+userid+"添加"+addcredit+"积分么？此操作不可逆")){	
	var datas =
	{
		
		"userid" : userid,
		"addcredit":addcredit
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../motouser/adduserCredit',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    if (data == "success")
			    {
				    alert ("添加成功");
				    closeModel();
			    }else{
			    	alert ("添加失败");
			    	 closeModel();
			    }
		    }
		    
	    },
	 error : function(data){
		 alert ("添加失败");
		 closeModel();
	 }
	});
	
  }
}
function adduserCredit(){
	$('#user_addcredit').val ("");
	openModel();
}
function adduservipConfirm(){
	var userid = $ ('#userid').val ();
	var addvip = $('#user_addvip').val ();
	if (userid == null || userid == "")
	{
		alert ("用户ID不能为空！");
		return false;
	}
	if (addvip == null || addvip == "" ||addvip=="0")
	{
		alert ("请选择会员类型");
		return false;
	}
	
	
	if (confirm("真的要为用户:"+userid+"添加该会员吗？此操作为强行加vip，不可逆，涉及金钱后果自负！！！")){	
		var datas =
		{
				"userid" : userid,
				"addvip":addvip
		}
		
		$.ajax (
				{
					type : "POST",
					url : '../motouser/adduservip',
					data : datas,
					success : function (data)
					{
						
						if (data != "" && data != null)
						{
							
							if (data == "success")
							{
								alert ("添加成功");
								closevipModel();
							}else{
								alert ("添加失败");
								closevipModel();
							}
						}
						
					},
					error : function(data){
						alert ("添加失败");
						closevipModel();
					}
				});
		
	}
}

function deleteuservipConfirm(){
	var userid = $ ('#userid').val ();
	var addvip = $('#user_deletevip').val ();
	if (userid == null || userid == "")
	{
		alert ("用户ID不能为空！");
		return false;
	}
	if (addvip == null || addvip == "" ||addvip=="0")
	{
		alert ("请选择会员类型");
		return false;
	}
	
	
	if (confirm("真的要为用户:"+userid+"删除该会员吗？")){	
		var datas =
		{
				"userid" : userid,
				"deletevip":addvip
		}
		
		$.ajax (
				{
					type : "POST",
					url : '../motouser/deleteuservip',
					data : datas,
					success : function (data)
					{
						
						if (data != "" && data != null)
						{
							
							if (data == "success")
							{
								alert ("成功");
							}else{
								alert ("失败");
								
							}
						}
						closedeletevipModel();
						
					},
					error : function(data){
						alert ("添加失败");
						closedeletevipModel();
					}
				});
		
	}
}
function adduservip(){
	openvipModel();
}

function deleteuservip(){
	opendeletevipModel();
}
function resetUserPSW(){
	var userid = $ ('#userid').val ();
	if (userid == null || userid == "")
	{
		alert ("用户ID不能为空！");
		return false;
	}
	if (confirm("真的要为用户:"+userid+"重置密码么？此操作不可逆")){	
		var datas =
		{
			"userid" : userid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motouser/resetUserPSW',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data != "" && data != null)
			    {
				    
				    if (data == "success")
				    {
					    alert ("操作成功");
					   
				    }else{
				    	alert ("操作失败");
				    	 
				    }
			    }
			    
		    },
		 error : function(data){
			 alert ("操作失败");
			
		 }
		});
		
	  }
}

function searchUserCreditLog ()
{
	var targetid = $ ('#userid').val ();
	if (targetid == null || targetid == "")
	{
		alert ("查询用户ID不能为空！");
		return false;
	}
	var datas =
	{
		"userid" : targetid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/searchUserCreditLog",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval (data);
			    
			    var str = "";
			    for (var i = 0; i < json.length; i++)
			    {
				    
				    str += "<tr>";
				    str += "	<td>" + json[i].creditlogid + "</td>";
				    str += "	<td >" + json[i].userid + "</td>";
				    str += "	<td>" + json[i].title + "</td>";
				    if(json[i].actiontype==0){
				    	 str += "	<td>增加</td>";
				    }else{
				    	str += "	<td>减少</td>";
				    }
				   
				    str += "	<td>" + json[i].score + "</td>";
				    str += "	<td>" + format (json[i].logtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
				    str += "</tr>";
				    
			    }
			    
			    $ ('#usercreditlog').html (str);
			    
		    }
	    }
	});
}

