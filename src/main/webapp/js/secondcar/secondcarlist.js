function addkeyword(id){
	$("#addkeyword_secondcarid").val(id);
	$("[name = checkbox_scmzname]:checkbox").attr("checked",false);
	
}
function addkeywordConfirm(){
	 var checkbox_scmzname_arr=[];
	 $("input[name='checkbox_scmzname']:checked").each(function() {
		 checkbox_scmzname_arr.push($(this).val());
	    });
	var secondcarid=$("#addkeyword_secondcarid").val();
/*	var keyword=$("#addkeyword_keyword").val();*/
	var createuser= $("#input_createuser").val();
	var adminGuid= $("#userGuidHidden").val();
	/*if(keyword==null || keyword==""){
		alert("keyword不能为空");
		return false;
	}*/
    var datas =
	{
	    "secondcarid" : secondcarid,
	    "checkbox_scmzname_arr" : checkbox_scmzname_arr
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../secondcar/addkeyword",
	    data : datas,
	    success : function (data)
	    {
		    if (data=='success')
		    {
			      alert ("修改成功！");
	//		      $("#page-wrapper").load("/secondcar/secondcarlist?userGuid=" + adminGuid+ "&page=0&limit=20&order=0&createuser="+createuser);
		    }else{
		    	 alert ("修改失败！");
		    	 
		    }
	    },
	error :function (data){
		alert ("修改出错！");
	}
	});

}
function changeState(secondcarid,state){
  if(confirm("你确定要修改此二手车的状态么，此操作不可逆，需谨慎")){
	    var createuser= $("#input_createuser").val();
		var adminGuid= $("#userGuidHidden").val();

		var datas =
		{
				"secondcarid" : secondcarid,
				"state" : state
		}
		$.ajax (
				{
					type : "POST",
					url : "../secondcar/changeState",
					data : datas,
					success : function (data)
					{
						if (data=='success')
						{
							alert ("修改成功！");
							$("#page-wrapper").load("/secondcar/secondcarlist?userGuid=" + adminGuid+ "&page=0&limit=20&order=0&createuser="+createuser);
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