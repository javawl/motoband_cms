function rest() {
	$("#hiddenValue").val("1");
	$('#titleimgshowadd').attr("src", "");
	$('#titleimgadd').val("");
	$("#bsid").val("");
	$("#uuid").val("");
	$("#lables").val("");
	$("#name").val("");
	$("#value").val("");
	$("#linktype").val("");
	$("#orderindex").val("");
}
function check() {
	var excel_file = $("#excel_file").val();
	if (excel_file == "" || excel_file.length == 0) {
		alert("请选择文件路径！");
		return false;
	} else {
		return true;
	}
}
function addbannerConfirm() {
	var bsid = $("#bsid").val();
	if(bsid==null||bsid==""){
		bsid=0;
	}
	var uuid = $('#uuid').val();
	var name = $('#name').val();
	var value = $('#value').val();
	var linktype = $('#linktype').val();
	var orderindex = $("#orderindex").val();
	var icon = $("#titleimgadd").val();
	var lables = $("#lables").val();
	var buid = $("#shopEditInfoSelect").val();// 门店id
	var state = $("input[name='state']:checked").val();
	var userGuid = $("#userGuidhiddenValue").val();

	var datas = {
		"bsid" : bsid,
		"uuid":uuid,
		"name" : name,
		"value" : value,
		"linktype" : linktype,
		"orderindex" : orderindex,
		"icon" : icon,
		"buid" : buid,
		"state" : state,
		"lables" : lables
	}

	$.ajax({
		type : "POST",
		url : '../businessmanage/updateBUserServiceV_3_8_0',
		data : datas,
		success : function(data) {

			if (data != "" && data != null) {
				if (data == "success") {
					alert("保存成功");
					$(".modal-backdrop").remove();
					$("body").removeClass('modal-open');
					$("#page-wrapper").load("../businessmanage/businessredirctshopservicelist?userGuid="
									+ userGuid + "&page=1&limit=20&order=0&buid=" + buid);
				} else {
					alert("保存失败");
					$(".modal-backdrop").remove();
					$("body").removeClass('modal-open');
				}
			}

		},
		error:function(data){
			alert("检查是否有未填写的数据,error msg:"+data)
		}
	});
}
function editaddbannerModel(bsid){
	$("#hiddenValue").val("1");
	var datas = {
			"bsid" : bsid
		}
	$.ajax({
		type : "POST",
		url : '../businessmanage/getBuserServiceV_3_8_0',
		data : datas,
		success : function(data) {
			if (data != null && data != '') {
				var jsondata = eval("(" + data + ")");
				$("#bsid").val(jsondata.bsid);
				$("#uuid").val(jsondata.uuid);
				$("#name").val(jsondata.name);
				if (jsondata.lables != null) {
					 $("#lables").val(jsondata.lables);
				}
				 $("#value").val(jsondata.value);
				 $("#linktype").val(jsondata.linktype);
				 $("#orderindex").val(jsondata.orderindex);
				 $("#titleimgadd").val(jsondata.icon);
				 $("#titleimgshowadd").attr("src",jsondata.icon);
				if(jsondata.state==0){
					$("input:radio[name='state']").eq(1).attr("checked",'checked');
				}else{
					$("input:radio[name='state']").eq(0).attr("checked",'checked');
				}

			}
		},
		error:function(data){
			alert("error msg:"+data)
		}
	});
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

$('input[name=FileContent]').change(function() {

	// initUploadForm ();
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
		if (flag == '1') {
			$("#titleimgadd").val();
		} else {
			$("#titleimg").val();
		}
	} else {
		$("#XX_groupimglist").children("div").removeClass('selected');
		$("#XX_groupimglist").find("div[name='XX_selectdiv']").css("display",
				"none");
		$('#XX_' + img_guid).addClass('selected');
		$('#XX_' + img_guid).find("div[name='XX_selectdiv']").css("display",
				"block");

		var flag = $("#hiddenValue").val();
		if (flag == '1') {
			$("#titleimgadd").val($('#XX_' + img_guid).find("img").attr("src"));
		} else {
			$("#titleimg").val($('#XX_' + img_guid).find("img").attr("src"));
		}
	}

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

function insertTitleImg() {

	var flag = $("#hiddenValue").val();
	if (flag == '1') {
		$("#titleimgshowadd").attr("src", $("#titleimgadd").val());
	} else {
		$("#titleimgshow").attr("src", $("#titleimg").val());
	}

}
function imgTypeChange(obj) {
	$(obj).next("div").find("div").each(function() {
		$(this).hide();
	});
	$("#inputUrlnid").val("");
	$("#inputUrlkeyword").val("");
	$("#inputUrllinkurl").val("");
	$("#inputUrlminiprogramid").val("");
	$("#inputUrlgpid").val("");
	$("#inputUrlsecondcarid").val("");
	$("#inputUrlbuserid").val("");

	switch (parseInt(obj.value)) {
	case 1:
		$("#div1").show();
		break;
	case 2:
	case 3:
		$("#div2").show();
		break;
	case 4:
	case 5:
	case 6:
		$("#div3").show();
		break;
	case 7:
		$("#div3").show();
		$("#div4").show();
		break;
	case 8:
		$("#div5").show();
		break;
	case 9:
		$("#div6").show();
		break;
	case 10:
		$("#div7").show();
		break;
	}
}

function userTypeChange(obj) {
	if (parseInt(obj.value) == 2) {
		$("#exceldiv").show();
	} else {
		$("#exceldiv").hide();
	}
}

function clearfilemsg() {
	$("#importMsg").text("");
}
