function login(){
	if($("#loginname").val() == null || $("#loginname").val() == "" ){
		alert('用户名不能为空');
		return;
	}
	if($("#password").val() == null || $("#password").val() == "" ){
		alert('密码不能为空');
		return;
	}
	var loginType = $("input[name='status']:checked").val();
	$("#loginType").val(loginType);		//为某属性赋值（value）
	//$("#loginForm").submit();

	var datas = {
		    "loginname" :$("#loginname").val(),
	        "password" : $("#password").val()
	}

	
	$.ajax({
		type: "POST",
		url: "/admin/main",
		data: datas,
		success: function(data) {
			if (data != "" && data != null) {

			   if(data=="true"){
			   window.location.href="/admin/index";
			   }else{
				   alert("账户或密码错误");
			   }
			}
		}
	});
}

//enter键触发登录功能
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
     if(e && e.keyCode==13){ // enter 键
    	 login();
    }
}; 