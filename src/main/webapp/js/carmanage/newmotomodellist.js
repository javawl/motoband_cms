function toupdateNewPage(id){
	window.open("../carmanage/newmotomodel_update?mid="+id);
}
function toinsertNewPage(){
	window.open("../carmanage/newmotomodel_ins");
}
function deleteNewMotomodel(mid){
	 if(confirm("你确定要修改此二手车的状态么，此操作不可逆，需谨慎")){
       var adminGuid=$("#userGuidHidden").val();
			var datas =
			{
					"mid" : mid
			}
			$.ajax (
					{
						type : "POST",
						url : "../carmanage/deleteNewMotomodel",
						data : datas,
						success : function (data)
						{
							if (data=='success')
							{
								alert ("修改成功！");
								$("#page-wrapper").load("/secondcar/secondcarlist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
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