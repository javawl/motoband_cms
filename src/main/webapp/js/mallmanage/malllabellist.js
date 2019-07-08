function inslabel(){
	$("#ins_name").val("");
}
function addConfirm(){
	var name=$.trim($("#ins_name").val());
	var color=$.trim($("#ins_color").val());
	var adminGuid=$("#userGuidHidden").val();
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	if(color==null || color==""){
		alert("color不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name,
	    "color":color
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/insMallLabel',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				    //	$('#inslabelModel').modal('hide');
				    	 $('#inslabelModel').css('display', 'none');
	  				//	  $('.modal-backdrop').css('display', 'none');
				    	 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/malllabellist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function labelNameCheck(){
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
			    url : '../mallmanage/getMallLabelByName',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data!=null && data!="")
				    {
				    	alert("标签名已经存在");
				    }
			    }
			});
}
