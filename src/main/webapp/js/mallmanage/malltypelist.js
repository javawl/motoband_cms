function insparenttype(){
	$("input[name='ins_radio_parenttype']").removeAttr('checked');
	$("#ins_name").val("");
}
function addConfirm(){
	var name=$.trim($("#ins_name").val());
	var adminGuid=$("#userGuidHidden").val();
	var parenttype=$("input[name='ins_radio_parenttype']:checked").val();
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	if(parenttype==null ||parenttype==""){
		alert("parenttype不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name,
	    "parentid":parenttype
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/insMallType',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				    //	$('#instypeModel').modal('hide');
				    	$('#instypeModel').css('display', 'none');
	  					 $('.modal-backdrop').css('display', 'none');
				    	$ ("#page-wrapper").load ("/mallmanage/malltypelist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}