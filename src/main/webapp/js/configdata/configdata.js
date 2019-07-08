function saveNewConfigdata(){
	var servicename=$("#servicename").val();
	var configkey=$("#configkey").val();
	var configvalue=$("#configvalue").val();
	var comments=$("#comments").val();
	if(servicename==null||servicename==""){
		alert("服务器名字不能空！");
		return false;
	}
	if(configkey==null||configkey==""){
		alert("参数key不能为空！");
		return false;
	}
	if(configvalue==null||configvalue==""){
		alert("参数值不能为空！");
		return false;
	}
	var  datas={
			"servicename":servicename,
			"configkey":configkey,
			"configvalue":configvalue,
			"comments":comments
	}
	$.ajax({
		type : "POST",
		url : "../configdata/insertConfigdata",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("增加新参数成功！");
				$("#page-wrapper").load(
						"../configdata/configdatalist?userGuid=" + data);
			}
		
		}
	});
}
function selconfigdataMessage(configdata_id){
	var  datas={
			"configdata_id":configdata_id
	}
	$.ajax({
		type : "POST",
		url : "../configdata/selconfigdataMessage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				var json=eval("("+data+")");
				$("#edit_id").val(json.id);
				$("#edit_servicename").val(json.servicename);
				$("#edit_configkey").val(json.configkey);
				$("#edit_configvalue").val(json.configvalue);
				$("#edit_comments").val(json.comments);
				
			}
		
		}
	});
}
function saveConfigdataMessage(){
	var id=$("#edit_id").val();
	var servicename=$("#edit_servicename").val();
	var configkey=$("#edit_configkey").val();
	var configvalue=$("#edit_configvalue").val();
	var comments=$("#edit_comments").val();
	if(servicename==null||servicename==""){
		alert("服务器名字不能空！");
		return false;
	}
	if(configkey==null||configkey==""){
		alert("参数key不能为空！");
		return false;
	}
	if(configvalue==null||configvalue==""){
		alert("参数值不能为空！");
		return false;
	}
	var  datas={
			"id":id,
			"servicename":servicename,
			"configkey":configkey,
			"configvalue":configvalue,
			"comments":comments
	}
	$.ajax({
		type : "POST",
		url : "../configdata/saveConfigdataMessage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("参数修改成功！");
				$("#page-wrapper").load(
						"../configdata/configdatalist?userGuid=" + data);
			}
		
		}
	});
}
function delConfdataGuid(confdata_guid){
	$("#delConfigdataGuid").val(confdata_guid);
}
function delConfigdataMessage(){
	var id=$("#delConfigdataGuid").val();
	var datas={
			"id":id
	}
	$.ajax({
		type : "POST",
		url : "../configdata/delConfigdataMessage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("删除成功");
			}
		
		}
	});
}