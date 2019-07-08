function selUserMessage(user_guid) {

	$("#page-wrapper").load("../usermanage/usersel?user_guid=" + user_guid);
}
function saveUserMessage() {
	var temp = document.getElementsByName("usersex");
	var user_sex = "";
	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			user_sex = temp[i].value;
	}
	var temprole = document.getElementsByName("userrole");
	var role_guid = "";
	for (var j = 0; j < temprole.length; j++) {
		if (temprole[j].checked)
			role_guid = temprole[j].value;
	}
	var user_guid = $("#user_guid").val();
	var user_name = $("#user_name").val();
	var user_loginname = $("#user_loginname").val();
	var user_tel = $("#user_tel").val();

	if (user_name == null || user_name == "") {
		alert("用户名未填写！");
		return false;
	}
	if (user_loginname == null || user_loginname == "") {
		alert("用户登录名未填写！");
		return false;
	}
	if (role_guid == null || role_guid == "") {
		role_guid = "10000";
	}
	var datas = {
		"user_name" : user_name,
		"user_loginname" : user_loginname,
		"user_tel" : user_tel,
		"user_sex" : user_sex,
		"role_guid" : role_guid,
		"user_guid" : user_guid
	}
	$.ajax({
		type : "POST",
		url : "../usermanage/saveUserMessage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("保存角色信息成功");
				$("#page-wrapper").load(
						"../usermanage/usersel?user_guid=" + user_guid);
			}
		}
	});
}
function checkLoginName() {
	var user_loginname = $("#newuserloginname").val();

	var datas = {
		"user_loginname" : user_loginname
	}
	$.ajax({
		type : "POST",
		url : "../usermanage/checkNewLoginUser",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				if (data == "ok") {
					$('#dangertext').css('display', 'none');
					$('#savebtn').removeClass('disabled');
				} else {
					if (user_loginname != null || user_loginname != "") {
						$('#dangertext').css('display', 'block');
						$('#savebtn').addClass('disabled');
					}
				}
			}
		}
	});
}
function checkNewLoginName(oldloginname) {
	var user_loginname = $("#user_loginname").val();
	if (user_loginname == oldloginname) {
		$('#dangertext').css('display', 'none');
		$('#savebtn').removeClass('disabled');
	} else {
		var datas = {
			"user_loginname" : user_loginname
		}
		$.ajax({
			type : "POST",
			url : "../usermanage/checkNewLoginUser",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					if (data == "ok") {

						$('#dangertext').css('display', 'none');
						$('#savebtn').removeClass('disabled');
					} else {
						if (user_loginname != null || user_loginname != "") {
							$('#dangertext').css('display', 'block');
							$('#savebtn').addClass('disabled');
						}
					}
				}
			}
		});
	}
}

function checkpasswordSame() {
	var newpassword = $("#newpassword").val();
	var newpassword2 = $("#newpassword2").val();
	if (newpassword == newpassword2) {
		$('#dangertext').css('display', 'none');
		$('#savebtn').removeClass('disabled');
	} else {
		$('#dangertext').css('display', 'block');
		$('#savebtn').addClass('disabled');
	}
}
function saveOwnMessage(){
	var username = $("#user_name").val();
	var userguid=$("#user_guid").val();
	var temp = document.getElementsByName("usersex");
	var usertel = $("#user_tel").val();

	var usersex = "";
	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			usersex = temp[i].value;
	}
	if (username == null || username == "") {
		alert("用户名未填写！");
		return false;
	}
	var datas = {
			"user_guid":userguid,
			"user_name" : username,
			"user_sex" : usersex,
			"user_tel" : usertel
		}
		$.ajax({
			type : "POST",
			url : "../usermanage/saveOwnMessage",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					alert("修改个人信息成功！");
					$("#page-wrapper").load("../usermanage/selOwnMessage?userGuid=" + userguid);
				}

			}
		});
}
function saveNewUser() {
	var username = $("#newusername").val();
	var userloginname = $("#newuserloginname").val();
	var temp = document.getElementsByName("newusersex");
	var usertel = $("#newusertel").val();
	var usersex = "";
	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			usersex = temp[i].value;
	}
	if (username == null || username == "") {
		alert("用户名未填写！");
		return false;
	}
	if (userloginname == null || userloginname == "") {
		alert("用户登录名未填写！");
		return false;
	}
	var datas = {
		"user_name" : username,
		"user_loginname" : userloginname,
		"user_sex" : usersex,
		"user_tel" : usertel
	}
	$.ajax({
		type : "POST",
		url : "../usermanage/insNewUser",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("添加用户成功！");
				$("#page-wrapper").load(
						"../usermanage/userlist?userGuid=" + data);
			//	$('.modal-backdrop').css('display', 'none');
				 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
			}
		}
	});
}
function updateOwnPassword(){
	var newpassword = $("#newpassword").val();
	var oldpassword = $("#oldpassword").val();
	var user_guid = $("#user_guid").val();
	if (newpassword == null || newpassword == "") {
		alert("新密码未填写！");
		return false;
	}
	if (oldpassword == null || oldpassword == "") {
		alert("旧密码未填写！");
		return false;
	}
	var datas = {
			"user_guid" : user_guid,
			"oldpassword" : oldpassword,
			"newpassword" : newpassword
		}
		$.ajax({
			type : "POST",
			url : "../usermanage/updateOwnPassword",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					if(data=="success"){
						alert("用户密码修改成功！");
						$("#page-wrapper").load("../usermanage/selOwnMessage?userGuid=" + user_guid);
					//	$('.modal-backdrop').css('display', 'none');
						 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
					}else{
						alert("原密码错误");	
					}
				}

			}
		});
	
	
}
function delUserGuid(user_guid){
	$('#delUserGuid').val(user_guid);
}
function delUserMessage(){
	var user_guid=$('#delUserGuid').val();
	var datas = {
			"user_guid" : user_guid	
		}
		$.ajax({
			type : "POST",
			url : "../usermanage/delUserMessage",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					alert("删除成功!");
					$("#page-wrapper").load("../usermanage/userlist?userGuid=" + data);
				//	$('.modal-backdrop').css('display', 'none');
					 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
				}
			}
		});
}
function resetUserPsw(){
	var user_guid=$("#user_guid").val();
	var datas = {
			"user_guid" : user_guid	
		}
		$.ajax({
			type : "POST",
			url : "../usermanage/resetUserPsw",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					alert("重置密码成功!");
					$("#page-wrapper").load(
							"../usermanage/usersel?user_guid=" + user_guid);
				}

			}
		});
}
