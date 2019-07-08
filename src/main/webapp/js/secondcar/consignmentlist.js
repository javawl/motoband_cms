function showTimeForm(e){
//	var t=e.offsetTop; 
//	var l=e.offsetLeft; 
//	var w=e.offsetWidth;
//	var h=e.offsetHeight; 
//	while(e=e.offsetParent)
//	{ 
//		t+=e.offsetTop; 
//		l+=e.offsetLeft; 
//	} 
//
//    $(".datetimepicker").css("top",t+34+"px");

}

function addRemarkandState(consignmentid,state){
	var remark=$("#"+consignmentid+"remark").val();
	$("#addconsignmentid").val(consignmentid);
	$("#addremark").val(remark);
	$("input[name='addstate'][value='"+state+"']").prop("checked",true);
}
function addRemarkandStateConfirm(){
	var consignmentid=$("#addconsignmentid").val();
	var remark=$("#addremark").val();
	var state=	$("input[name='addstate']:checked").val();
	
	var adminGuid=$("#GuidHidden").val();
	var startTime=$("#starttime").val();
	var endTime=$("#endtime").val();
	var datas =
	{
		"consignmentid" : consignmentid,
		"remark" : remark,
		"state" : state
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../secondcar/addRemarkandState",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("修改成功");
		    	 $(".modal-backdrop").remove();
		    	 $("body").removeClass('modal-open');
		    	 $("#page-wrapper").load("/secondcar/consignmentlist?userGuid=" + adminGuid+ "&page=0&limit=20&order=0&startTime="+startTime+"&endTime="+endTime);
		    }else{
		    	alert("修改失败");
		    }
		  
	    }
	});
}