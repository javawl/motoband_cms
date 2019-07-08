function changeState(id,state){
	 $("#back").css("display","");
	 var text =$("#"+id).find('td:nth-child(11)').text();
	 if(text=="上架"){
		  state="1"; 
	 }
    if(text=="下架"){
    	state="0"; 
	 }
	 var datas =
		{
		    "id" : id,
		    "state":state
		   
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../mallmanage/updateProductState',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data =="success")
			    {
			     alert("修改成功");
			     if(state=='1'){
			    
			    	 $("#"+id).find('td:nth-child(11)').text("下架"); 
			     }else{
			    	 
			    	 $("#"+id).find('td:nth-child(11)').text("上架");
			     }
			     $("#back").css("display","none");
			    }else{
			    	 alert("修改失败");
			    	 $("#back").css("display","none");
			    }
			    
		    }
		});
}
function delmallproduct(id){
	if(confirm("你真的要删除该商品么，此操作不可逆")){
	var datas =
	{
	    "id" : id
 
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../mallmanage/deleteProductByid',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data =="success")
		    {
		     alert("删除成功");
		     $("#"+id).remove();
		    }else{
		    	 alert("删除失败");
		    }
		    
	    }
	});
	}
}
function addtoUsecarMain(id){
	$("#activityindex").val(id);
	$("#addtomaingroup_orderindex").val("");
}
function addtoUsecarMainConfirm(){
if(confirm("你确定要添加该商品到首页么？该操作不可逆，谨慎操作！")){
	var id=$("#activityindex").val();
	var groupid=$("#addtomaingroup_select").val();
	var orderindex=$("#addtomaingroup_orderindex").val();
	
    var datas =
	{
	    "id" : id,
	    "groupid" : groupid,
	    "orderindex" : orderindex
	    
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../mallmanage/productToUsecarmain",
	    data : datas,
	    success : function (data)
	    {
		    if (data=='success')
		    {
			      alert ("修改成功！");
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
function addMallproductKeywords(id,title,keyword){
	$("#mallproductid").val(id);
	$("#mallproducttitle").val(title);
	$("#addkeyword").val(keyword);
}
function addMallproductKeywordsConfirm(){
	if(confirm("你确定要添加此关键字么？该操作不可逆，谨慎操作！")){
		var id=$("#mallproductid").val();
		var keyword=$("#addkeyword").val();
		var title=$("#mallproducttitle").val();
		
		var datas =
		{
				"id" : id,
				"keyword" : keyword,
				"title":title
				
		}
		$.ajax (
				{
					type : "POST",
					url : "../mallmanage/addMallproductKeywords",
					data : datas,
					success : function (data)
					{
						if (data=='success')
						{
							alert ("添加成功！");
							 var data11={
							    		"userGuid":0,
							    		"page":1,
							    		"limit":20,
							    		"order":0
							    }
						$("#mallListTable").load("/mallmanage/mallproductlist",data11);
						}else{
							alert ("添加失败！");
						}
					},
					error :function (data){
						alert ("添加出错！");
					}
				});
	}
}