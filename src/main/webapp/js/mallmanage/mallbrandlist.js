function insbrand(){
	$("#ins_name").val("");
}
function addConfirm(){
	var name=$.trim($("#ins_name").val());
	var adminGuid=$("#userGuidHidden").val();
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/insMallBrand',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				    //	$('#insbrandModel').modal('hide');
				    	  $('#insbrandModel').css('display', 'none');
	  					 // $('.modal-backdrop').css('display', 'none');
				    	  $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/mallbrandlist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function brandNameCheck(){
	var name=$.trim($("#ins_name").val());
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}

	var datas =
	{
	    "name" :name
	    
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/getMallBrandByName',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data!=null && data!="")
				    {
				    	alert("品牌名已经存在");
				    } 
			    }
			});
}
function showProductNameList(probrand,name,productCount){
	var str="";
	var datas =
	{
	    "probrand" :probrand
	    
	}
    $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/getMallProductByProbrand',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data!=null && data!="")
				    {
				    	var jsondata=eval("("+data+")");
				    	str+="品牌\"<b>"+name+"</b>\"正在被以下"+productCount+"个商品使用";
				    	str+="<ul>";
				    	for(var i=0;i<jsondata.length;i++){
				    		str+="<li><a style='text-decoration:none;' href=\"javascript:updateProductNewPage('"+jsondata[i].id+"')\">"+jsondata[i].title+"</a></li>";
				    	}
				    	str+="</ul>";
				    	$("#productNameShow").empty();
				    	$("#productNameShow").append(str);
				    } 
			    }
			});
}