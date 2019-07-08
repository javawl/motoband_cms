function editUpdateConf(id,userGuid){
	var datas={
			"id":id
	}

	$.ajax (
			{
			    type : "POST",
			    url : "../clientupdate/getUpdateConf",
			    data : datas,
			    success : function (data)
			    {
				    if (data != "" && data != null)
				    {
					   var datajson = eval("("+data+")");
					   
//					   $('#update_oldcversion').val(datajson.cversion);
//						$('#update_oldtcversion').val(datajson.tcversion);
//						$('#update_oldctype').val(datajson.ctype);
						$('#update_id').val(datajson.id);
					    $('#update_cversion').val(datajson.cversion);
					    if(datajson.tcversion==null||datajson.tcversion==""||typeof(datajson.tcversion)=="undefined"){
					    	$('#update_tcversion').val("");
					    }else{
					    	$('#update_tcversion').val(datajson.tcversion);
					    }
						
						$('#update_ctype').val(datajson.ctype);
						$('#update_content').val(datajson.content);
						$('#update_downloadurl').val(datajson.downloadurl);
						$('#update_state').val(datajson.state);
						$('#update_ishighversion').empty();
						var str="";
						var ishighversion= datajson.ishighversion;
						if(ishighversion==1){
							str+="<option value='0'>否</option><option value='1' selected>是</option>";
							$('#update_ishighversion').append(str);
						}else{
							str+="<option value='0' selected>否</option><option value='1' >是</option>";
							$('#update_ishighversion').append(str);
						}
						
						$('#update_userGuid').val(userGuid);
				    }
				    
			    }
			});

}
function delUpdateConf(id,userGuid){
//	 $('#del_cversion').val(cversion);
//	$('#del_tcversion').val(tcversion);
//	$('#del_ctype').val(ctype);
	$('#del_id').val(id);
	$('#del_userGuid').val(userGuid);
}
function updateUpdateConf(){
//	var old_cversion=$('#update_oldcversion').val();
//	var old_tcversion=$('#update_oldtcversion').val();
//	var old_ctype=$('#update_oldctype').val();
	var id=$('#update_id').val();
	var cversion=$('#update_cversion').val();
	var tcversion=$('#update_tcversion').val();
	var ctype=$('#update_ctype').val();
	var content=$('#update_content').val();
	var downloadurl=$('#update_downloadurl').val();
	var state=$('#update_state').val();
	var ishighversion=$('#update_ishighversion').val();
	var adminGuid=$('#update_userGuid').val();
	if(cversion ==null ||cversion==""){
		alert("cversion不能为空");
		return;
	}
	

	if(content ==null ||content==""){
		alert("content不能为空");
		return;
	}
	if(downloadurl ==null ||downloadurl==""){
		alert("cversion不能为空");
		return;
	}
	var datas={
			/*"old_cversion":old_cversion,
			"old_tcversion":old_tcversion,
			"old_ctype":old_ctype,*/
			"id":id,
			"cversion":cversion,
			"tcversion":tcversion,
			"ctype":ctype,
			"content":content,
			"downloadurl":downloadurl,
			"state":state,
			"ishighversion":ishighversion
	}
	// console.log(datas);
	$.ajax (
			{
			    type : "POST",
			    url : "../clientupdate/updateUpdateConf",
			    data : datas,
			    success : function (data)
			    {
				    if (data =='success')
				    {
					   alert("编辑成功");
					   $ ("#page-wrapper").load ("/clientupdate/clientupdatelist?userGuid=" + adminGuid+"&ctype="+ctype);
					   
					    
				    }
				    
			    }
			});
}
function insUpdateConf(adminGuid){
	$("#ins_userGuid").val(adminGuid);
}
function insClientUpdate(){
	var cversion=$('#ins_cversion').val();
	var tcversion=$('#ins_tcversion').val();
	var ctype=$('#ins_ctype').val();
	var content=$('#ins_content').val();
	var downloadurl=$('#ins_downloadurl').val();
	var state=$('#ins_state').val();
	var adminGuid=$("#ins_userGuid").val();
	var ishighversion=$("#ins_ishighversion").val();
	if(cversion ==null ||cversion==""){
		alert("cversion不能为空");
		return;
	}
	

	if(content ==null ||content==""){
		alert("content不能为空");
		return;
	}
	if(downloadurl ==null ||downloadurl==""){
		alert("downloadurl不能为空");
		return;
	}
	var datas={
			"cversion":cversion,
			"tcversion":tcversion,
			"ctype":ctype,
			"content":content,
			"downloadurl":downloadurl,
			"state":state,
			"ishighversion":ishighversion
	}
   
	$.ajax (
			{
			    type : "POST",
			    url : "../clientupdate/insertclientupdate",
			    data : datas,
			    success : function (data)
			    {
				    if (data=="success")
				    {
					   alert("添加成功");
					  
					$ ("#page-wrapper").load ("/clientupdate/clientupdatelist?userGuid=" + adminGuid+"&ctype="+ctype);
					    
				    }
				    
			    }
			});
}
function delConfirm(){
//	var cversion=$('#del_cversion').val();
//	var tcversion=$('#del_tcversion').val();
//	var ctype=$('#del_ctype').val();
	var id=$('#del_id').val();
	var adminGuid=$('#del_userGuid').val();
	var datas={
//			"cversion":cversion,
//			"tcversion":tcversion,
//			"ctype":ctype
			"id":id
		
	}

	$.ajax (
			{
			    type : "POST",
			    url : "../clientupdate/deleteclientupdate",
			    data : datas,
			    success : function (data)
			    {
				    if (data =='success')
				    {
					   alert("删除成功");
					   $ ("#page-wrapper").load ("/clientupdate/clientupdatelist?userGuid=" + adminGuid+"&ctype=1");
					   
					    
				    }
				    
			    }
			});
}