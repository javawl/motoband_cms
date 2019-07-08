function editiu(iuid){
	var datas =
	{
	    "iuid" : iuid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/getInsuranceupdateByid',
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null){
			   var datajson = eval("("+data+")");
			  $("#update_iuid").val(datajson.iuid);
			  $("#update_desc").val(datajson.desc);
			  $("#update_addtime").val(datajson.addtimeString);
			 
		    }
		    
	    }
	});
}
function editiuConfirm(){
	  var iuid=$("#update_iuid").val();
	  var desc=$("#update_desc").val();
	  var addtime=$("#update_addtime").val();
	  var adminGuid=$("#userGuidHidden").val();
	  if(desc==null || desc==''){
			 alert("desc不能为空");
			 return;
		 }
		 if(addtime==null ||addtime==''){
			 alert("addtime不能为空");
			 return;
		 }
	var datas =
	{
	    "iuid" : iuid,
	    "desc" : desc,
	    "addtime" : addtime
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/updateInsuranceupdate',
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success'){
		    	alert("编辑成功");
	//	    	 $('#editiuModel').modal('hide');
					$('#editiuModel').css('display', 'none');
			//		$('.modal-backdrop').css('display', 'none');
					 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
		    	  $("#page-wrapper").load("../payordermanage/insuranceupdatelist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
		    }
		    
	    }
	});
}
function deleteiu(iuid){
	var adminGuid=$("#userGuidHidden").val();
	if(confirm("真的要删除么？此操作不可逆")){
		var datas =
		{
		    "iuid" : iuid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../payordermanage/deleteInsuranceupdateByid',
		    data : datas,
		    success : function (data)
		    {
			    if (data =='success'){
				  alert("删除成功");
				
		    	  $("#page-wrapper").load("../payordermanage/insuranceupdatelist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
				 
			    }
			    
		    }
		});
	}
}
function addiu(){
	 $("#ins_desc").val("");
	 $("#ins_addtime").val("");
}
function addiuConfirm(){
	
	 var desc=$("#ins_desc").val();
	 var addtime=$("#ins_addtime").val();
	 var adminGuid=$("#userGuidHidden").val();
	 if(desc==null || desc==''){
		 alert("desc不能为空");
		 return;
	 }
	 if(addtime==null ||addtime==''){
		 alert("addtime不能为空");
		 return;
	 }
	var datas =
	{
			"desc" : desc,
		    "addtime" : addtime
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/addInsuranceupdate',
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success'){
		    	  alert("添加成功");
	//	    	  $('#addiuModel').modal('hide');
		    	  $('#addiuModel').css('display', 'none');
			//	  $('.modal-backdrop').css('display', 'none');
		    	  $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
		    	  $("#page-wrapper").load("../payordermanage/insuranceupdatelist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
		    }
		    
	    }
	});
}

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

function changeCarCheckCode(){
	var adminGuid=$("#userGuidHidden").val();
	var carcheckcode=$.trim($("#carcheckcode").val());
	 if(carcheckcode==null || carcheckcode==''){
		 alert("carcheckcode不能为空");
		 return;
	 }
	var datas =
	{
		"carcheckcode" : carcheckcode
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/updateCarCheckCode',
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success'){
		    	  alert("更改成功");
		    	  $("#page-wrapper").load("../payordermanage/insuranceupdatelist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
		    }
		    
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("更改失败，重试");
           
        }
	});
	
}