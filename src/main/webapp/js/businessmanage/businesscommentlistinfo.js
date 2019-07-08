function deletbusercomment(cid ,buserid){
	if(confirm("你确定要删除这条评论么？此操作不可逆，谨慎使用")){
		var datas =
		{
		    "cid" : cid,
		    "buserid" : buserid
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../businessmanage/deletbusercomment",
		    data : datas,
		    success : function (data)
		    {
			    if (data=='success')
			    {
				    alert ("修改成功！");
				    $("."+cid).remove(); 
			    }else{
			    	alert ("修改失败！");
			    }
		    }
		});
	}
}