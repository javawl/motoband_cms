function insstyle(){
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
			    url : '../mallmanage/insMallStyle',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				    	//$('#insstyleModel').modal('hide');
				    	$('#insstyleModel').css('display', 'none');
	  				//	 $('.modal-backdrop').css('display', 'none');
				    	 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/mallstylelist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function styleNameCheck(){
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
			    url : '../mallmanage/getMallStyleByName',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data!=null && data!="")
				    {
				    	alert("风格已经存在");
				    }
			    }
			});
}