function rolesel(role_guid, role_name,role_des) {
	$("#page-wrapper").load(
			"../rolemanage/rolesel?role_guid=" + role_guid + "&role_name="
					+ role_name+"&role_des="+role_des);
}
function saveRoleConf() {
	var role_guid = $("#role_guid").val();
	if (role_guid == '10000') {
		alert("该系统锁定无法修改");
		return false;
	} else {
		var checkarray = new Array();
		var j = 0;
		for (var i = 0; i < $("#treetable").find("input:checkbox").length; i++) {
			if ($("#treetable").find("input:checkbox").eq(i).is(":checked") == true) {
				checkarray[j] = $("#treetable").find("input:checkbox").eq(i)
						.val();
				j++;
			}
		}

		var role_name = $("#role_name").val();
		var datas = {
			"role_guid" : role_guid,
			"role_name" : role_name,
			"role_conf" : checkarray.join(",")
		}
		$.ajax({
			type : "POST",
			url : "../rolemanage/saveRoleConf",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					alert("保存角色权限成功");
					$("#page-wrapper").load(
							"../rolemanage/rolesel?role_guid=" + role_guid
									+ "&role_name=" + role_name+"&role_des="+role_des);
				}
			}
		});
	}
}
function arrayToJson(o) {
	var r = [];
	if (typeof o == "string")
		return "\""
				+ o.replace(/([\'\"\\])/g, "\\$1").replace(/(\n)/g, "\\n")
						.replace(/(\r)/g, "\\r").replace(/(\t)/g, "\\t") + "\"";
	if (typeof o == "object") {
		if (!o.sort) {
			for ( var i in o)
				r.push(i + ":" + arrayToJson(o[i]));
			if (!!document.all
					&& !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/
							.test(o.toString)) {
				r.push("toString:" + o.toString.toString());
			}
			r = "{" + r.join() + "}";
		} else {
			for (var i = 0; i < o.length; i++) {
				r.push(arrayToJson(o[i]));
			}
			r = "{" + r.join() + "}";
		}
		return r;
	}
	return o.toString();
}
function insRole() {
	var rolename = $("#newrolename").val();
	var roledes = $("#newroledes").val();
	if (rolename == null || rolename == "") {
		alert("新角色名称未填写！");
		return false;
	}
	var datas = {
		"role_name" : rolename,
		"role_des" : roledes
	}
	$.ajax({
		type : "POST",
		url : "../rolemanage/insNewRole",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("添加新角色成功！");
				$("#page-wrapper").load(
						"../rolemanage/rolelist?userGuid=" + data);
			//	$('.modal-backdrop').css('display', 'none');
				 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
			}

		}
	});
}
function delRoleGuid(role_guid) {

	$('#delRoleGuid').val(role_guid);
	console.log($('#delRoleGuid').val());
}
function delRoleMessage() {
	var role_guid = $('#delRoleGuid').val();
	if (role_guid == '10000') {
		alert("该系统锁定无法删除");
		return false;
	} else {
		var datas = {
			"role_guid" : role_guid
		}
		$.ajax({
			type : "POST",
			url : "../rolemanage/delRoleMessage",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					alert("删除成功!");
					$("#page-wrapper").load(
							"../rolemanage/rolelist?userGuid=" + data);
				//	$('.modal-backdrop').css('display', 'none');
					 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
				}

			}
		});
	}
}
function checkNewRoleName(){
	var newrolename = $('#newrolename').val();
	if(newrolename==""||newrolename==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
			"newrolename" : newrolename
		}
		$.ajax({
			type : "POST",
			url : "../rolemanage/checkNewRoleName",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					if (data == "ok") {
						$('#dangertext').css('display', 'none');
						$('#savebtn').removeClass('disabled');
					} else {
						if (newrolename != null || newrolename != "") {
							$('#dangertext').css('display', 'block');
							$('#savebtn').addClass('disabled');
						}
					}
				}

			}
		});
	}
	
}
function saveNewRoleMessage(){
	var role_guid=$('#role_guid').val();
	var newrole_name=$('#newrole_name').val();
	var newrole_des=$('#newrole_des').val();
	if(newrole_name==""||newrole_name==null){
		alert("角色名不能为空");
		return false;
	}/*else if(newrole_des==""||newrolename==null){
		alert("角色描述不能为空");
		return false;
	}*/
	var datas = {
			"role_guid":role_guid,
			"newrole_name":newrole_name,
			"newrole_des" : newrole_des
		}
	$.ajax({
		type : "POST",
		url : "../rolemanage/saveNewRoleMessage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("保存角色信息成功");
				$("#page-wrapper").load(
						"../rolemanage/rolesel?role_guid=" + role_guid
								+ "&role_name=" + newrole_name+"&role_des="+newrole_des);
			}

		}
	});
	
}
function checkRoleName(role_guid){
	var newrole_name=$('#newrole_name').val();
	if(newrole_name==""||newrole_name==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
				"role_guid":role_guid,
				"newrole_name" : newrole_name
			}
			$.ajax({
				type : "POST",
				url : "../rolemanage/checkRoleName",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertext').css('display', 'none');
							$('#savebtn').removeClass('disabled');
						} else {
							if (newrole_name != null || newrole_name != "") {
								$('#dangertext').css('display', 'block');
								$('#savebtn').addClass('disabled');
							}
						}
					}
				}
			});
	}
	
}
