var format = function(time, format) {
	var t = new Date(time);
	var tf = function(i) {
		return (i < 10 ? '0' : '') + i
	};
	return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a) {
		switch (a) {
		case 'yyyy':
			return tf(t.getFullYear());
			break;
		case 'MM':
			return tf(t.getMonth() + 1);
			break;
		case 'mm':
			return tf(t.getMinutes());
			break;
		case 'dd':
			return tf(t.getDate());
			break;
		case 'HH':
			return tf(t.getHours());
			break;
		case 'ss':
			return tf(t.getSeconds());
			break;
		}
	})
}

function closetitleImgModel(){
	$('#titleImgModel').on('hidden.bs.modal', function() {

		$('#insertMallModel').css({'overflow-y':'scroll'});
		});
	
}

function getUuid() {
	var len = 32;// 32长度
	var radix = 16;// 16进制
	var chars = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
			.split('');
	var uuid = [], i;
	radix = radix || chars.length;
	if (len) {
		for (i = 0; i < len; i++)
			uuid[i] = chars[0 | Math.random() * radix];
	} else {
		var r;
		uuid[8] = uuid[13] = uuid[18] = uuid[23] = '-';
		uuid[14] = '4';
		for (i = 0; i < 36; i++) {
			if (!uuid[i]) {
				r = 0 | Math.random() * 16;
				uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
			}
		}
	}
	return uuid.join('');
}

$('#grouplist > div').on("click", function() {
	showgroupimg($(this).attr("id"));
	$("#nowgroupid").val($(this).attr("id"));
	$(this).parent("#grouplist").find("div").removeClass('groupaction');
	$(this).addClass('groupaction');

});
$('#XX_grouplist>div').on("click", function() {
	XX_showgroupimg($(this).attr("id"));
	$("#XX_nowgroupid").val($(this).attr("id"));
	$(this).parent("#XX_grouplist").find("div").removeClass('groupaction');
	$(this).addClass('groupaction');

});

// tooltip demo
$('.tooltip-demo').tooltip({
	selector : "[data-toggle=tooltip]",
	container : "body"
})

$(document).ready(function() {
	initUploadForm();
});
$('input[name=FileContent]').change(function() {

	initUploadForm();
});

$('body').on('click', '#downloadBtn', function() {
	$('#downloadImg').attr('src', $('#downloadUrl').text());
});

$('body').on(
		'click',
		'#deleteBtn',
		function() {
			var manageUrl = $('#url').text();
			var fileid = $('#fileid').text();
			if (!manageUrl) {
				alert('尚未获取管理url');
				return false;
			}
			manageUrl = manageUrl + '/del';
			// 请将以下获取签名的链接换成您部署好的服务端http url
			// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
			$.getJSON('../boxmanage/uploadBoxImg?type=delete&fileid='
					+ encodeURIComponent(fileid), function(data) {

				var sign = data.sign, url = data.url + '?sign='
						+ encodeURIComponent(sign);
				$.ajax({
					type : "POST",
					url : url,
					data : {},
					success : function() {
						alert('删除成功');
						$('#inputfile').attr("disabled", "false");
						$('#subbtn').attr("disabled", "false");
						$('#downloadRet').css("display", "none");
					},

					dataType : 'json'
				});
			});
		});

$('body').on('click', '#queryBtn', function() {
	var manageUrl = $('#url').text();
	if (!manageUrl) {
		alert('尚未获取管理url');
		return false;
	}
	$.ajax({
		type : "GET",
		url : manageUrl,
		data : {},
		success : function(data) {
			$('#imgInfo').text(JSON.stringify(data));
		},
		error : function(ret) {
			$('#imgInfo').text(ret.responseText);
		},
		dataType : 'json'
	});
});
function initUploadForm() {
	// 请将以下获取签名的链接换成您部署好的服务端http url
	// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
	$
			.getJSON(
					'../boxmanage/uploadBoxImg',
					function(data) {

						var sign = data.sign, url = data.url + '?sign='
								+ encodeURIComponent(sign);

						var options = {
							type : 'post',
							url : url,
							dataType : 'json',
							success : function(ret) {
								$('#downloadUrl').html(ret.data.download_url);
								$('#fileid').text(ret.data.fileid);
								$('#url').text(ret.data.url);
								$('#downloadRet').show();
								var str = "<div class='row'><div class='col-lg-4' id='"
										+ ret.data.fileid
										+ "'onclick=\"javascript:selectImg('"
										+ ret.data.fileid
										+ "');\""
										+ "><div class='panel panel-default'><div class='panel-body' style='text-align: center;'>"
										+ "<img src='"
										+ ret.data.download_url
										+ "' width='100' height='100'></div><div class='panel-footer center' style='text-align: center;'>"
										+ "<input type='button' value='选择' class='btn btn-info'></div></div>"
										+ "<div class='selected_mask' name='selectdiv' style='display: none'>"
										+ "<div class='selected_mask_inner'></div>"
										+ "<div class='selected_mask_icon'></div></div></div>";
								$('#imglistdiv').append(str);

								var img_guid = ret.data.fileid;
								var img_url = ret.data.download_url;
								var img_opurl = ret.data.url;
								var datas = {
									"img_guid" : img_guid,
									"img_url" : img_url,
									"img_opurl" : img_opurl
								}
								$.ajax({
									type : "POST",
									url : "../boxmanage/addNewBoxImg",
									data : datas,
									success : function(data) {
										if (data != "" && data != null) {
											alert("添加图片资源成功！");
											initUploadForm();
										}

									}
								});
							},
							error : function(ret) {
								alert(ret.responseText);
							}
						};

						// pass options to ajaxForm
						// $ ('#uploadForm').ajaxForm (options);
					});
}
function saveBoxMessage(box_id) {
	var box_writer = $('#box_writer').val();
	var box_title = $('#box_title').val();
	var box_subtitle = $('#box_subtitle').val();
	var box_time = $('#boxtime').val();
	var temp = document.getElementsByName("box_status");
	var box_status = "";
	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			box_status = temp[i].value;
	}
	var box_type = $("#box_type").val();
	var box_content = $('#editor').cleanHtml();

	if (box_title == null || box_title == "") {
		alert("标题不能为空");
		return false;
	}
	if (box_subtitle == null || box_subtitle == "") {
		alert("副标题不能为空");
		return false;
	}
	if (box_writer == null || box_writer == "") {
		box_writer = "MotoBand™";
	}
	if (box_time == null || box_time == "") {
		alert("时间不能为空");
		return false;
	}

	var datas = {
		"box_id" : box_id,
		"box_title" : box_title,
		"box_subtitle" : box_subtitle,
		"box_writer" : box_writer,
		"box_type" : box_type,
		"box_time" : box_time,
		"box_content" : box_content,
		"box_status" : box_status
	}

	$.ajax({
		type : "POST",
		url : "../boxmanage/addBoxPage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				alert("保存手册信息成功");
				$("#page-wrapper").load(
						"../boxmanage/boxlist?userGuid=" + data.userid);
			}

		}
	});

}
function showImgModel() {
	if ($('#ImgModel').css("display") == "none") {
		$('#ImgModel').css("display", "block");
	} else {
		$('#ImgModel').css("display", "none");
	}
}
function cleanFontStyle() {
	$("#editor")
			.find("span")
			.css(
					"font-family",
					"'Helvetica Neue',Helvetica,'Hiragino Sans GB','Microsoft YaHei',Arial,sans-serif");
}
function selectImg(img_guid) {
	if ($('#' + img_guid).hasClass('selected')) {
		$('#' + img_guid).removeClass('selected');
		$('#' + img_guid).find("div[name='selectdiv']").css("display", "none");
	} else {
		$('#' + img_guid).addClass('selected');
		$('#' + img_guid).find("div[name='selectdiv']").css("display", "block");
	}

}
function XX_selectImg(XX_img_guid) {
	var img_guid = XX_img_guid.replace("XX_", "");
	if ($('#XX_' + img_guid).hasClass('selected')) {
		$("#XX_groupimglist").children("div").removeClass('selected');
		$("#XX_groupimglist").find("div[name='XX_selectdiv']").css("display",
				"none");
		$('#XX_' + img_guid).removeClass('selected');
		$('#XX_' + img_guid).find("div[name='XX_selectdiv']").css("display",
				"none");
		var flag = $("#hiddenValue").val();
		$("#shoppics" + flag).val();
	} else {
		$("#XX_groupimglist").children("div").removeClass('selected');
		$("#XX_groupimglist").find("div[name='XX_selectdiv']").css("display",
				"none");
		$('#XX_' + img_guid).addClass('selected');
		$('#XX_' + img_guid).find("div[name='XX_selectdiv']").css("display",
				"block");
		var flag = $("#hiddenValue").val();
		$("#shoppics"+flag).val($('#XX_' + img_guid).find("img").attr("src"));
	}

}
function insertImg() {
	var string = "";
	imglist = new Array();
	for (var i = 0; i < $('.selected').length; i++) {
		string = $('.selected').eq(0).find("img").attr("src");
		imglist[i] = $('.selected').eq(i).find("img").attr("src");
	}
	$("#remoteUrl").val(string);
	$('#selectImgModel').css("display", "none");
	$('.selected').removeClass("selected");
	$('body').find("div[name='selectdiv']").css("display", "none");

}

function previewBoxMessage(box_id) {

	var box_writer = $('#box_writer').val();
	var box_title = $('#box_title').val();
	var box_subtitle = $('#box_subtitle').val();
	var box_time = $('#boxtime').val();

	var box_boxkind = $('#box_boxkind').val();
	var box_boxurl = $('#box_boxurl').val();

	if (box_boxurl == null || box_boxurl == "") {
		alert("微信地址不能为空");
		return false;
	}

	var temp = document.getElementsByName("box_status");
	var box_status = "";
	var box_titleimg = $('#titleimg').val();

	for (var i = 0; i < temp.length; i++) {
		if (temp[i].checked)
			box_status = temp[i].value;
	}
	var box_type = $("#box_type").val();
	var box_content = editor.html().replace(/[\n]/ig, '');
	var a = new Array();
	var i = 0;

	$('#cont').find("iframe").contents().find("img").each(
			function(i) {

				a[i++] = $(this).attr("src").substr(
						$(this).attr("src").lastIndexOf("/") + 1)

			});
	var imglist = JSON.stringify(a);
	if (box_title == null || box_title == "") {
		alert("标题不能为空");
		return false;
	}
	if (box_title.length > 20) {
		alert("标题不能超过20字");
		return false;
	}

	if (box_subtitle == null || box_subtitle == "") {
		alert("副标题不能为空");
		return false;
	}
	if (box_subtitle.length > 64) {
		alert("副标题不能超过64字");
		return false;
	}
	if (box_writer == null || box_writer == "") {
		box_writer = "MotoBand™";
	}
	if (box_time == null || box_time == "") {
		alert("时间不能为空");
		return false;
	}

	var datas = {
		"box_id" : box_id,
		"box_title" : box_title,
		"box_subtitle" : box_subtitle,
		"box_writer" : box_writer,
		"box_type" : box_type,
		"box_time" : box_time,
		"box_content" : box_content,
		"box_status" : box_status,
		"box_boxkind" : box_boxkind,
		"imglist" : imglist,
		"preview" : 1,
		"box_titleimg" : box_titleimg,
		"box_boxurl" : box_boxurl
	}

	$.ajax({
		type : "POST",
		url : "../boxmanage/addBoxPage",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {

				json = eval('(' + data + ')');

				alert("保存手册信息成功");
				window.open(json.root[0].showurl);

			}

		}
	});
}
function showTimeForm(e) {
	var t = e.offsetTop;
	var l = e.offsetLeft;
	var w = e.offsetWidth;
	var h = e.offsetHeight;
	while (e = e.offsetParent) {
		t += e.offsetTop;
		l += e.offsetLeft;
	}

	$(".datetimepicker").css("top", t + 34 + "px");

}
function showgroupimg(groupid) {
	var datas = {
		"group_guid" : groupid,
	}
	$.ajax({
		type : "POST",
		url : "../boxmanage/showgroupimg",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				var json = eval("(" + data + ")");
				$("#groupimglist").html("");
				for ( var i in json) {
					addimgdiv(json[i]);
				}
			}

		}
	});
}
function XX_showgroupimg(XX_groupid) {
	var groupid = XX_groupid.replace("XX_", "");
	var datas = {
		"group_guid" : groupid,
	}
	$.ajax({
		type : "POST",
		url : "../boxmanage/showgroupimg",
		data : datas,
		success : function(data) {
			if (data != "" && data != null) {
				var json = eval("(" + data + ")");
				$("#XX_groupimglist").html("");
				for ( var i in json) {
					XX_addimgdiv(json[i]);
				}
			}

		}
	});
}
function addimgdiv(json) {
	var str = "<div class='col-lg-3' id='"
			+ json.img_guid
			+ "' onclick=\"javascript:selectImg('"
			+ json.img_guid
			+ "');\"><div class='panel panel-default'>"
			+ "<div class='panel-body' style='text-align: center;'><img src='"
			+ json.img_url
			+ "' width='100' height='100'></div>"
			+ "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>"
			+ "<input class='form-control' placeholder='图片名' name='ImgName' value='"
			+ json.img_name
			+ "' disabled='disabled'></div>"
			+ "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
			+ "<div class='selected_mask' name='selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
			+ "<div class='selected_mask_icon'></div></div></div>";
	$("#groupimglist").append(str);
}
function XX_addimgdiv(json) {
	var str = "<div class='col-lg-3' id='XX_"
			+ json.img_guid
			+ "' onclick=\"javascript:XX_selectImg('XX_"
			+ json.img_guid
			+ "');\"><div class='panel panel-default'>"
			+ "<div class='panel-body' style='text-align: center;'><img src='"
			+ json.img_url
			+ "' width='100' height='100'></div>"
			+ "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>"
			+ "<input class='form-control' placeholder='图片名' name='ImgName' value='"
			+ json.img_name
			+ "' disabled='disabled'></div>"
			+ "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
			+ "<div class='selected_mask' name='XX_selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
			+ "<div class='selected_mask_icon'></div></div></div>";
	$("#XX_groupimglist").append(str);
}

function insertTitleImg() {
	var flag = $("#hiddenValue").val();
		$("#shoppicsshow"+flag).attr("src", $("#shoppics"+flag).val());
}

function insertHiddleValue(value) {
	$("#hiddenValue").val(value);
}
function showTimeForm(e) {
	// var t=e.offsetTop;
	// var l=e.offsetLeft;
	// var w=e.offsetWidth;
	// var h=e.offsetHeight;
	// while(e=e.offsetParent)
	// {
	// t+=e.offsetTop;
	// l+=e.offsetLeft;
	// }
	//
	// $(".datetimepicker").css("top",t+34+"px");
}

function resetpassword(userid) {
	$("#resetpassword_userid").val(userid);
	$("#resetpassword_password").val("");

}

function resetpasswordConfirm() {
	var userid = $("#resetpassword_userid").val();
	var password = $("#resetpassword_password").val();

	if (password == null || password == '') {
		alert("password不能为空");
		return false;
	}
	if (confirm("确定要重置该商户的密码么？该操作不可逆，需谨慎")) {

		var datas = {
			"userid" : userid,
			"password" : password
		}
		$.ajax({
			type : "POST",
			url : "../businessmanage/resetBuserPassword",
			data : datas,
			success : function(data) {
				if (data == 'success') {
					alert("修改成功！");
				} else {
					alert("修改失败！");
				}
			}
		});
	}
}
function changetoapprove(userid) {
	if (confirm("确定要把该商家设置为认证商家么？该操作不可逆，需谨慎")) {

		var datas = {
			"userid" : userid
		}
		$.ajax({
			type : "POST",
			url : "../businessmanage/changeToApprove",
			data : datas,
			success : function(data) {
				if (data == 'success') {
					alert("修改成功！");
				} else {
					alert("修改失败！");
				}
			}
		});
	}
}

function lookUserNewPage(buid) {
	window.open("../businessmanage/lookBusinessRedirctshopInfo?buid=" + buid);
}

function lookuser(buid) {
	var datas = {
		"buid" : buid

	}
	$.ajax({
		type : "POST",
		url : "../businessmanage/lookbusinessredirctshoplist",
		data : datas,
		success : function(data) {
			if (data != null && data != '') {
				var jsondata = eval("(" + data + ")");
				$("#look_groupid").val(jsondata.groupid);
				$("#look_orderindex").val(jsondata.orderindex);
				$("#hidden_buid").val(jsondata.buid);
				$("#look_buserid").val(jsondata.buserid);
				$("#look_name").val(jsondata.name);
				if (jsondata.contactphone != null) {
					// console.log(jsondata.contactphone);
					$("#look_contactphone").val(jsondata.contactphone);

					$(jsondata.pics).each(function(index, element) {
						// 遍历
						// for (k in element) {
						console.log(index, element[index]);
						$("#shoppics" + (index + 1)).val(element);
						$("#shoppicsshow" + (index + 1)).attr("src", element);
						// }
					});
				}
				// $("#look_type").val(jsondata.type);
				$("#look_address").val(jsondata.address);
				$("#look_province").val(jsondata.province);
				$("#look_city").val(jsondata.city);
				$("#look_createtime").val(
						format(jsondata.createtime, 'yyyy-MM-dd  HH:mm:ss'));
				// $("#look_businessserviceliststr").val(jsondata.businessserviceliststr);
				$("#look_longitude").val(jsondata.longitude);
				$("#look_latitude").val(jsondata.latitude);
				$("#look_businesshours").val(jsondata.businesshours);
				$("#look_businesslables").val(jsondata.lables);
				$("#look_kfuseridlist").val(jsondata.kfuseridlist);
				$("#look_des").val(jsondata.des);
				// $("#headurl").val(jsondata.headurl);
				// $("#headurlshow").attr("src",jsondata.headurl);
				// if(jsondata.shoppics!=null && jsondata.shoppics !=''){
				// var shoppicarr=jsondata.shoppics.split(",");

				// var str="";
				// for(var i=0;i<jsondata.usecarmaingroupModels.length;i++){
				// str+='<option
				// value="'+jsondata.usecarmaingroupModels[i].groupid+'">'+jsondata.usecarmaingroupModels[i].name+'</option>';
				// }
				// $("#addtomaingroup_select").empty();
				// $("#addtomaingroup_select").append(str);

				$("#hidden_buserid").val(jsondata.buserid);
				if (jsondata.state == 0) {
					$("input:radio[name='look_state']").eq(1).attr("checked",
							'checked');
				} else {
					$("input:radio[name='look_state']").eq(0).attr("checked",
							'checked');
				}
				$("#hidden_state").val(jsondata.state);

			}
		}
	});
}
function changemainbrand() {
	$("#select_mainbrand_div").show();
	$("#select_mainbrand option").each(
			function() {
				$("#select_mainbrand option[value='" + $(this).val() + "']")
						.prop("selected", false);

			});
}
function changemainbrandConfirm() {
	$("#select_mainbrand_div").hide();
	var mainbrandids = $("#select_mainbrand").val();
	var idstr = '';
	var textstr = '';
	for (var i = 0; i < mainbrandids.length; i++) {
		idstr += mainbrandids[i] + ",";
		textstr += ''
				+ $("#select_mainbrand [value='" + mainbrandids[i] + "']")
						.text() + ',';
	}
	$("#look_mainbrand").val("");
	$("#look_mainbrand").val(textstr.substring(0, textstr.length - 1));
	$("#mainbrandidsHidden").val("");
	$("#mainbrandidsHidden").val(idstr.substring(0, idstr.length - 1));

}
function changemainbrandCancel() {
	$("#select_mainbrand_div").hide();

}
function changerecommendbrand() {
	$("#select_recommendbrand_div").show();
	$("#select_recommendbrand option").each(
			function() {
				$(
						"#select_recommendbrand option[value='" + $(this).val()
								+ "']").prop("selected", false);

			});
}
function changerecommendbrandConfirm() {
	$("#select_recommendbrand_div").hide();
	var recommendbrandids = $("#select_recommendbrand").val();
	var idstr = '';
	var textstr = '';
	for (var i = 0; i < recommendbrandids.length; i++) {
		idstr += recommendbrandids[i] + ",";
		textstr += ''
				+ $(
						"#select_recommendbrand [value='"
								+ recommendbrandids[i] + "']").text() + ',';
	}
	$("#look_recommendbrand").val("");
	$("#look_recommendbrand").val(textstr.substring(0, textstr.length - 1));
	$("#recommendbrandidsHidden").val("");
	$("#recommendbrandidsHidden").val(idstr.substring(0, idstr.length - 1));

}
function changerecommendbrandCancel() {
	$("#select_recommendbrand_div").hide();

}
function editApplyConfirm() {
	var aid = $("#look_aid").val();
	var name = $("#look_name").val();
	var contactname = $("#look_contactname").val();
	var contactphone = $("#look_contactphone").val();
	var type = $("input[name='look_type']:checked").val();
	var mainbrand = $("#mainbrandidsHidden").val();
	var address = $("#look_address").val();
	var businessarea = $("#look_businessarea").val();
	var staffcount = $("#look_staffcount").val();
	var age = $("#look_age").val();
	var recommendbrand = $("#recommendbrandidsHidden").val();
	var license = $("#titleimgshow").attr("src");
	var other = $("#look_other").val();
	var state = $("#look_state").val();
	var reason = $("#look_reason").val();
	var province = $("#look_province").val();
	var city = $("#look_city").val();
	var addtime = $("#look_addtime").val();
	var businessservice_arr = [];
	$("input[name='checkbox_businessservice']:checked").each(function() {
		businessservice_arr.push($(this).val());
	});
	var longitude = $("#look_longitude").val();
	var latitude = $("#look_latitude").val();
	if (name == null || name == '' || contactname == null || contactname == ''
			|| contactphone == null || contactphone == '' || type == null
			|| type == '' || address == null || address == ''
			|| businessarea == null || businessarea == '' || staffcount == null
			|| staffcount == '' || age == null || age == '' || license == null
			|| license == '' || province == null || province == ''
			|| city == null || city == '' || longitude == null
			|| longitude == '' || latitude == null || latitude == '') {
		alert("有数据为空");
		return;
	}
	var datas = {
		"aid" : aid,
		"name" : name,
		"contactname" : contactname,
		"contactphone" : contactphone,
		"type" : type,
		"mainbrand" : mainbrand,
		"address" : address,
		"businessarea" : businessarea,
		"staffcount" : staffcount,
		"age" : age,
		"recommendbrand" : recommendbrand,
		"license" : license,
		"other" : other,

		"province" : province,
		"city" : city,

		"longitude" : longitude,
		"latitude" : latitude,
		"businessservice_arr" : businessservice_arr

	}
	$.ajax({
		type : "POST",
		url : "../businessmanage/updateApply",
		data : datas,
		success : function(data) {
			if (data == 'success') {
				alert("修改成功！");
				window.opener = null;
				window.open('', '_self');
				window.close();
			} else {
				alert("修改失败！");
			}
		},
		error : function(data) {
			alert("修改出错！");
		}
	});
}
function insertTitleImgHidden(value) {
	$("#hiddenValue").val(value);
}

function changeservicecheckbox(obj) {
	// var i=0;
	// $("input[name='checkbox_businessservice']:checked").each(function() {
	// i=i+1;
	// });
	// if(i>4){
	// alert("最多选四个");
	// if(obj.checked){
	// obj.checked=false;
	// }
	// return false;
	// }
	$("input[name='checkbox_businessservice']").each(function() {
		if ($(this).prop("checked") == true) {
			$("#servicecontentDiv" + $(this).val()).show();
		} else {
			$("#servicecontentDiv" + $(this).val()).hide();
		}
	});
}

function editUserConfirm() {
	$("#back").css("display", "");
	var buid = $("#hidden_buid").val();
	var buserid = $("#look_buserid").val();
	var name = $("#look_name").val();
	var contactphone = $("#look_contactphone").val();
	var address = $("#look_address").val();
	var province = $("#look_province").val();
	var city = $("#look_city").val();
	var longitude = $("#look_longitude").val();
	var latitude = $("#look_latitude").val();
	var businesshours = $("#look_businesshours").val();
	var lables = $("#look_businesslables").val();
	var kfuseridlist = $("#look_kfuseridlist").val();
	var des = $("#look_des").val();
	var state = $("input[name='look_state']:checked").val();
	var groupid = $("#look_groupid").val();
	var orderindex = $("#look_orderindex").val();

	var pics = [];
	for (var i = 1; i < 9; i++) {
		var pic = $("#shoppics" + i).val();
		if (pic != null && pic != "") {
			pics.push(pic)
		}
	}

	if (name == null || name == '' || contactphone == null
			|| contactphone == '' || address == null || address == ''
			|| province == null || province == '' || city == null || city == ''
			|| longitude == null || longitude == '' || latitude == null
			|| latitude == '') {
		alert("有数据为空");
		$("#back").css("display", "none");
		return;
	}

	var datas = {
		"buid" : buid,
		"buserid" : buserid,
		"name" : name,
		"contactphone" : contactphone,
		"address" : address,
		"province" : province,
		"city" : city,
		"longitude" : longitude,
		"latitude" : latitude,
		"businesshours" : businesshours,
		"lables" : lables,
		"state" : state,

		/*
		 * "state" : state, "addtime" : addtime, "reason" : reason,
		 * "businesscode" : businesscode,
		 */

		"kfuseridlist" : kfuseridlist,
		"des" : des,
		"pics" : pics.toString(),
		"groupid" : groupid,
		"orderindex":orderindex
	}
	$.ajax({
		type : "POST",
		url : "../businessmanage/updateBUserV_3_8_0",
		data : datas,
		success : function(data) {
			if (data == 'success') {
				alert("修改成功！");
				$("#back").css("display", "none");
				window.opener = null;
				window.open('', '_self');
				window.close();
			} else {
				alert("修改失败！");
				$("#back").css("display", "none");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			console.log(XMLHttpRequest.status + "  "
					+ XMLHttpRequest.readyState + "  " + textStatus + "   "
					+ errorThrown);

			alert("修改出错！");
			$("#back").css("display", "none");
		}

	});
}

function toEdit() {
	$("input,select,button,textarea").prop('disabled', false);
}

function uploadapprovepic(userid) {
	$("#uploadapprovepic_userid").val(userid);
}
function uploadapprovepicConfirm() {
	var userid = $("#uploadapprovepic_userid").val();
	var approvepic = $("#approvepicshow").attr("src");
	if (approvepic == null || approvepic == '') {
		alert("图片不能为空");
		return false;
	}
	var datas = {

		"approvepic" : approvepic,
		"userid" : userid
	}
	$.ajax({
		type : "POST",
		url : "../businessmanage/uploadapprovepic",
		data : datas,
		success : function(data) {
			if (data == 'success') {
				alert("修改成功！");
			} else {
				alert("修改失败！");
			}
		},
		error : function(data) {
			alert("修改出错！");
		}
	});

}

function toNewPageOpenPic(value) {
	var picurl = '';
	if (parseInt(value) == 1) {
		picurl = $("#titleimg").val();
	}
	window.open(picurl);
}