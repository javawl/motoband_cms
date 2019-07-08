function versionmodel (sign)
{
	$ ("#updateversion").val (sign);

}
function updateversion ()
{

	var sign = $ ("#updateversion").val ();

	datas =
	{
		"sign" : sign
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../dataversion/updateversion",
	    data : datas,
	    success : function (data)
	    {
	    	if(data!=null&&data!=""){
	    		alert("更新版本成功");
		    	$("#page-wrapper").load("../dataversion/dataversionlist?userGuid=" + data);
	    	}
	    	
	    }
	});
}

function  updateTribalVersion(versionname){
	if(confirm("真的要更新版本么？此操作不可逆")){
		 $.ajax (
					{
					    type : "POST",
					    url : '../tribal/sysTribalRedis',
					    success : function (data)
					    {
						 
					    	 if (data=="success")
							    {
					    		 var datas =
					    			{
					    			    "sign" :versionname
					    			}
					    		 $.ajax (
					    					{
					    					    type : "POST",
					    					    url : '../dataversion/updateversion',
					    					    data:datas,
					    					    success : function (data)
					    					    {
					    						 
					    					    	 if (data!=null&&data!="")
					    							    {
					    							    	alert("更新成功");
					    							    	
					    					           }
					    					    }
					    					});	
							    	
					           }else{
					        	   alert("更新失败"); 
					           }
					    }
			});
		
		
	}
}
