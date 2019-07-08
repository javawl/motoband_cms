function deleteusecarmain(id){
	var limit= $("#pageSizeSelect").val();
	var groupid= $("#groupSelect").val();
	if(confirm("你确定要删除么？该操作不可逆，谨慎操作！")){
	    var datas =
		{
		    "id" : id
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../businessmanage/deleteusecarmain",
		    data : datas,
		    success : function (data)
		    {
			    if (data=='success')
			    {
				    alert ("删除成功！");
				    $("#page-wrapper").load("../businessmanage/usecarmainlist?userGuid=0&page=1&limit="+limit+"&order=0&groupid="+groupid);
			    }else{
			    	 alert ("删除失败！");
			    }
		    },
		error :function (data){
			alert ("删除出错！");
		}
		});
	}
}
function changeorderindex(id){
	$("#changeorderindex_id").val(id);
}
function changeorderindexConfirm(){
	var id=$("#changeorderindex_id").val();
	var orderindex=$("#changeorderindex_orderindex").val();
	var limit= $("#pageSizeSelect").val();
	var groupid= $("#groupSelect").val();
	if(orderindex==null || orderindex==''){
		alert("orderindex不可为空！");
		return false;
	}
	if(confirm("你确定要修改orderindex么？该操作不可逆，谨慎操作！")){
		
	    var datas =
		{
		    "id" : id,
		    "orderindex" : orderindex
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../businessmanage/changeorderindex",
		    data : datas,
		    success : function (data)
		    {
			    if (data=='success')
			    {
				      alert ("修改成功！");
				      $("#page-wrapper").load("../businessmanage/usecarmainlist?userGuid=0&page=1&limit="+limit+"&order=0&groupid="+groupid);
			    }else{
			    	 alert ("修改失败！");
			    }
		    },
		error :function (data){
			alert ("修改出错！");
		}
		});
	}
}
function  updateVersion(versionname){
	if(confirm("真的要更新版本么？此操作不可逆")){
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
	}
	}