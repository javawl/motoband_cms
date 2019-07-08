function insertConf() {
	var conf_name = $('#conf_name').val();
	var conf_key = $('#conf_key').val();
	var conf_des = $('#conf_des').val();
	if (conf_name == null || conf_name == "") {
		alert("配置名称未填写！");
		return false;
	}
	if (conf_key == null || conf_key == "") {
		alert("配置key未填写！");
		return false;
	}
	if (conf_des == null || conf_des == "") {
		alert("配置描述未填写！");
		return false;
	}
	var datas = {
		"conf_name" : conf_name,
		"conf_key" : conf_key,
		"conf_des" : conf_des
	}
	$.ajax({
		type : "POST",
		url : "../confmanage/insertConf",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("增加新配置成功！");
//				 $('#insertConfModel').modal('hide');
				$('#insertConfModel').css('display', 'none');
			//	$('.modal-backdrop').css('display', 'none');
				 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
				$("#page-wrapper").load(
						"../confmanage/conflist?userGuid=" + data);
			}
//			$('#insertConfModel').css('display', 'none');
//			$('.modal-backdrop').css('display', 'none');
			

		}
	});
}
function selConf(conf_guid) {
	$("#page-wrapper").load("../confmanage/confsel?conf_guid=" + conf_guid);
}
function saveConf(conf_guid) {
	var parentconf_name = $('#parentconf_name').val();
	var parentconf_key = $('#parentconf_key').val();
	var parentconf_des = $('#parentconf_des').val();

	if (parentconf_name == null || parentconf_name == "") {
		alert("父配置名称未填写！");
		return false;
	}
	if (parentconf_key == null || parentconf_key == "") {
		alert("父配置key未填写！");
		return false;
	}
	if (parentconf_des == null || parentconf_des == "") {
		alert("父配置描述未填写！");
		return false;
	}
	var datas = {
		"conf_guid" : conf_guid,
		"conf_name" : parentconf_name,
		"conf_key" : parentconf_key,
		"conf_des" : parentconf_des
	};
	$.ajax({
		type : "POST",
		url : "../confmanage/saveConf",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("修改父权限成功");
				$("#page-wrapper").load(
						"../confmanage/confsel?conf_guid=" + conf_guid);
			}
		}
	});
}
function insertChildConf(partent_guid) {
	var name = $("#childconf_name").val();
	var key = $("#childconf_key").val();
	var des = $("#childconf_des").val();
	if (name == null || name == "") {
		alert("新增的子配置名称未填写！");
		return false;
	}
	if (key == null || key == "") {
		alert("新增的子配置key未填写！");
		return false;
	}
	if (des == null || des == "") {
		alert("新增的子配置描述未填写！");
		return false;
	}
	var datas = {
		"partentconf_guid" : partent_guid,
		"conf_name" : name,
		"conf_key" : key,
		"conf_des" : des
	};
	$.ajax({
		type : "POST",
		url : "../confmanage/insertChildConf",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("添加子权限成功");
	//			$('#insertConfModel').modal('hide');
				$('#insertConfModel').css('display', 'none');
			//	$('.modal-backdrop').css('display', 'none');
				 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
				$("#page-wrapper").load(
						"../confmanage/confsel?conf_guid=" + data);
			
			
			}

		}
	});
}
function saveChildConf(conf_guid, partentguid) {

	var name = $("#" + conf_guid).find("input[id='conf_name']").val();
	var key = $("#" + conf_guid).find("input[id='conf_key']").val();
	var des = $("#" + conf_guid).find("input[id='conf_des']").val();
	if (name == null || name == "") {
		alert("修改的子配置名称未填写！");
		return false;
	}
	if (key == null || key == "") {
		alert("修改的子配置key未填写！");
		return false;
	}
	if (des == null || des == "") {
		alert("修改的子配置描述未填写！");
		return false;
	}
	var datas = {
		"conf_guid" : conf_guid,
		"conf_name" : name,
		"conf_key" : key,
		"conf_des" : des
	};
	$.ajax({
		type : "POST",
		url : "../confmanage/saveChildConf",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("修改子权限成功");
				$("#page-wrapper").load(
						"../confmanage/confsel?conf_guid=" + partentguid);
			
			}

		}
	});
}
function openDelChildConfModel(conf_guid){
	$("#delChildConfInput").val(conf_guid);
}
function delChildConf(parent_guid){
	var conf_guid=$("#delChildConfInput").val();
	var datas = {
			"conf_guid" : conf_guid,
			"conf_parentguid" : parent_guid
		};
	$.ajax({
		type : "POST",
		url : "../confmanage/delChildConf",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("删除子权限成功");
				$("#page-wrapper").load(
						"../confmanage/confsel?conf_guid=" + parent_guid);
				$('#delChildConfModel').css('display', 'none');
			//	$('.modal-backdrop').css('display', 'none');
				 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
				
			}
		}
	});
}

/*
 * function saveChildConf(conf_guid){ var
 * parentconf_name=$('#'+conf_guid+">#childconf_name").val(); var
 * parentconf_key=$('#'+conf_guid+">#childconf_key").val(); var
 * parentconf_des=$('#'+conf_guid+">#childconf_des").val();
 * if(parentconf_name==null||parentconf_name==""){ alert("配置名称未填写！"); return
 * false; } if(parentconf_key==null||parentconf_key==""){ alert("配置key未填写！");
 * return false; } if(parentconf_des==null||parentconf_des==""){
 * alert("配置描述未填写！"); return false; } var datas={ "conf_name":parentconf_name,
 * "conf_key":parentconf_key, "conf_des":parentconf_des } $.ajax({ type :
 * "POST", url : "../confmanage/saveConf", data:datas, success : function(data) {
 * if(data!=""&&data!=null){
 * //$("#page-wrapper").load("../confmanage/conflist?userGuid=" + data); } } }); }
 */
function checkNewConfKey(){
	var conf_key=$("#conf_key").val();
	if(conf_key==""||conf_key==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
				"conf_key" : conf_key
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewConfKey",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertext').css('display', 'none');
							$('#savebtn').removeClass('disabled');
						} else {
							if (conf_name != null || conf_name != "") {
								$('#dangertext').css('display', 'block');
								$('#savebtn').addClass('disabled');
							}
						}

					}

				}
			});
	}
}
function checkNewParentConfKey(parentconf_guid){
	var parentconf_key=$("#parentconf_key").val();
	if(parentconf_key==""||parentconf_key==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
				"parentconf_guid":parentconf_guid,
				"parentconf_key" : parentconf_key
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewParentConfKey",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertextKey').css('display', 'none');
							$('#savebtn').removeClass('disabled');
						} else {
							if (parentconf_name != null || parentconf_name != "") {
								$('#dangertextKey').css('display', 'block');
								$('#savebtn').addClass('disabled');
							}
						}

					}

				}
			});
	}
}
function checkNewChildConfKey(childconf_guid){
	var childconf_key=$("#"+childconf_guid).find("input[id='conf_key']").val();
	if(childconf_key==""||childconf_key==null){
		$("#"+childconf_guid).find("button[id='savebtn']").addClass('disabled');
	}else{
		var datas = {
				"childconf_guid":childconf_guid,
				"childconf_key" : childconf_key
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewChildConfKey",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$("#"+childconf_guid).find("p[id='dangertextChildKey']").css('display', 'none');
							$("#"+childconf_guid).find("button[id='savebtn']").removeClass('disabled');
						} else {
							if (childconf_name != null || childconf_name != "") {
								$("#"+childconf_guid).find("p[id='dangertextChildKey']").css('display', 'block');
								$("#"+childconf_guid).find("button[id='savebtn']").addClass('disabled');
							}
						}
					}
				}
			});
	}
}
function checkNewConfName(){
	var conf_name=$("#conf_name").val();
	if(conf_name==""||conf_name==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
				"conf_name" : conf_name
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewConfName",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertextName').css('display', 'none');
							$('#savebtn').removeClass('disabled');
						} else {
							if (conf_name != null || conf_name != "") {
								$('#dangertextName').css('display', 'block');
								$('#savebtn').addClass('disabled');
							}
						}

					}

				}
			});
	}
}

function checkNewParentConfName(parentconf_guid){
	var parentconf_name=$("#parentconf_name").val();
	if(parentconf_name==""||parentconf_name==null){
		$('#savebtn').addClass('disabled');
	}else{
		var datas = {
				"parentconf_guid":parentconf_guid,
				"parentconf_name" : parentconf_name
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewParentConfName",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertextName').css('display', 'none');
							$('#savebtn').removeClass('disabled');
						} else {
							if (parentconf_name != null || parentconf_name != "") {
								$('#dangertextName').css('display', 'block');
								$('#savebtn').addClass('disabled');
							}
						}
					}
				}
			});
	}
}
function checkNewChildConfName(childconf_guid){
	var childconf_name=$("#"+childconf_guid).find("input[id='conf_name']").val();
	if(childconf_name==""||childconf_name==null){
		$("#"+childconf_guid).find("button[id='savebtn']").addClass('disabled');
	}else{
		var datas = {
				"childconf_guid":childconf_guid,
				"childconf_name" : childconf_name
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkNewChildConfName",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$("#"+childconf_guid).find("p[id='dangertextChildName']").css('display', 'none');
							$("#"+childconf_guid).find("button[id='savebtn']").removeClass('disabled');
						} else {
							if (childconf_name != null || childconf_name != "") {
								$("#"+childconf_guid).find("p[id='dangertextChildName']").css('display', 'block');
								$("#"+childconf_guid).find("button[id='savebtn']").addClass('disabled');
							}
						}
					}
				}
			});
	}
}
function checkchildname(parent_guid){
	var childname=$("#childconf_name").val();
	if(childname==""||childname==null){
		$("#inssavebtn").addClass('disabled');
	}else{
		var datas = {
				"parent_guid":parent_guid,
				"childname" : childname
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkchildname",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertextNewChildName').css('display', 'none');
							$('#inssavebtn').removeClass('disabled');
						} else {
							if (childname != null || childname != "") {
								$('#dangertextNewChildName').css('display', 'block');
								$('#inssavebtn').addClass('disabled');
							}
						}
					}
				}
			});
	}
}
function checkchildkey(parent_guid){
	var childconf_key=$("#childconf_key").val();
	if(childconf_key==""||childconf_key==null){
		$("#inssavebtn").addClass('disabled');
	}else{
		var datas = {
				"parent_guid":parent_guid,
				"childconf_key" : childconf_key
			}
			$.ajax({
				type : "POST",
				url : "../confmanage/checkchildkey",
				data : datas,
				success : function(data) {
					if (data != "" && data != null) {
						if (data == "ok") {
							$('#dangertextNewChildKey').css('display', 'none');
							$('#inssavebtn').removeClass('disabled');
						} else {
							if (childconf_name != null || childconf_name != "") {
								$('#dangertextNewChildKey').css('display', 'block');
								$('#inssavebtn').addClass('disabled');
							}
						}
					}
				}
			});
	}
}


