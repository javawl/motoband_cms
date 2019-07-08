function change_contentType(){
	var temp = document.getElementsByName ("ismotopush");
	for (var i = 0; i < temp.length; i++)
	{
		if (temp[i].checked){
			
			$("#motoselfpush").css("display","none");
			$("#notmotopush").css("display","none");
			$("#motopush").css("display","none");
			$("#"+temp[i].value).css("display","block");
			if(temp[i].value=="notmotopush"){
				$("#motopush").css("display","block");
			}
		}
			
	}
}
$ (document).ready (function ()
{
	initUploadForm ();
});
$ ('input[name=FileContent]').change (function ()
{

	initUploadForm ();
});
$('body').on('click', '#downloadBtn', function() {
	$('#downloadImg').attr('src', $('#downloadUrl').text());
});

$('body').on('click', '#deleteBtn', function() {
	var manageUrl =
		$('#url').text();
	var fileid = $('#fileid').text();
	if (!manageUrl) {
		alert('尚未获取管理url');
		return false;
	}
	manageUrl = manageUrl + '/del'; // 请将以下获取签名的链接换成您部署好的服务端http url //
									// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
	$.getJSON('../boxmanage/uploadBoxImg?type=delete&fileid=' +
		encodeURIComponent(fileid),
		function(data) {

			var sign = data.sign,
				url = data.url + '?sign=' + encodeURIComponent(sign);
			$.ajax({
				type: "POST",
				url: url,
				data: {},
				success: function() {
					alert('删除成功');
					$('#downloadRet').css("display", "none");
				},

				dataType: 'json'
			});
		});
});

$('body').on('click', '#queryBtn', function() {
	var manageUrl =
		$('#url').text();
	if (!manageUrl) {
		alert('尚未获取管理url');
		return false;
	}
	$.ajax({
		type: "GET",
		url: manageUrl,
		data: {},
		success: function(data) {
			$('#imgInfo').text(JSON.stringify(data));
		},
		error: function(ret) {
			$('#imgInfo').text(ret.responseText);
		},
		dataType: 'json'
	});
});


function initUploadForm() { 
	$.getJSON('../boxmanage/uploadBoxImg',
		function(data) {

			var sign = data.sign,
				url = data.url + '?sign=' + encodeURIComponent(sign);

			var options = {
				type: 'post',
				url: url,
				dataType: 'json',
				success: function(ret) {
				
					$('#downloadUrl').html(ret.data.download_url);
					$('#fileid').text(ret.data.fileid);
					$('#url').text(ret.data.url);
					$('#downloadRet').show();
					var img_guid = ret.data.fileid;
					var img_url = ret.data.download_url;
					var img_opurl = ret.data.url;
					 var img_name=document.getElementById ("inputfile").files[0].name;
					 var img_groupid=$ ("#nowgroupid").val ();
					var str = "<div class='col-lg-3' id='" +img_guid+ "' onclick=\"javascript:selectImg('" +img_guid+ "');\"><div class='panel panel-default'>" +
		"<div class='panel-body' style='text-align: center;'><img src='" + img_url+ "' width='100' height='100'></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>" +
		"<input class='form-control' placeholder='图片名' name='ImgName' value='" + img_name + "' disabled='disabled'></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>" +
		"<div class='selected_mask' name='selectdiv' style='display: none'><div class='selected_mask_inner'></div>" +
		"<div class='selected_mask_icon'></div></div></div>";
					$('#groupimglist').append(str);

				
					var datas = {
						    "img_guid" : img_guid,
					        "img_url" : img_url,
					        "img_opurl" : img_opurl,
					        "img_name" : img_name,
					        "img_groupid" : img_groupid
					}
					$.ajax({
						type: "POST",
						url: "../boxmanage/addNewBoxImg",
						data: datas,
						success: function(data) {
							if (data != "" && data != null) {
								alert("添加图片资源成功！");
								initUploadForm();
							}
						}
					});
				},
				error: function(ret) {
					alert(ret.responseText);
				}
			};
			// pass options to ajaxForm
			$('#uploadForm').ajaxForm(options);
		});
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
var imglist;
function insertImg() {
	var string = "";
    imglist=new Array();
	for (var i = 0; i < $('.selected').length; i++) {
		string = $('.selected').eq(0).find("img").attr("src");
		imglist[i]=$('.selected').eq(i).find("img").attr("src");
	}
	$("#remoteUrl").val(string);
	$('#selectImgModel').css("display","none");
	$('.selected').removeClass("selected");
	$('body').find("div[name='selectdiv']").css("display", "none");

}


function saveNewBoxMessage() {
	var box_title = $('#box_title').val();
	var box_boxkind=$('#box_boxkind').val();
	var box_subtitle = $("#box_subtitle").val();
	var box_writer = $('#box_writer').val();
	var box_time = $('#boxtime').val();
	var box_type = $("#box_type").val();
	var box_keyword=$("#box_keyword").val();
	var box_submitter=$("#box_submitter").val();
	var box_source=$("#box_source").val();
	var box_approve=$("#box_approve").val();
	var box_boxurl=$("#box_boxurl").val();
	var box_content =  editor.html().replace(/[\n]/ig,'');
	
	var recommendBoxID=$("#recommendBoxID").val();
	
	var a = new Array (); 
	var i=0;
	
	$('#cont').find("iframe").contents().find("img").each(function(i){ 
		
		a[i++]=$(this).attr("src").substr($(this).attr("src").lastIndexOf("/")+1);
		});
	var imglist = JSON.stringify (a);
	if (box_title == null || box_title == "")
	{
		alert ("标题不能为空");
		return false;
	}
	if(box_title.length>32){
		alert ("标题不能超过32字");
		return false;
	}
	
	if (box_subtitle == null || box_subtitle == "")
	{
		alert ("副标题不能为空");
		return false;
	}
	if(box_subtitle.length>64){
		alert ("副标题不能超过64字");
		return false;
	}
	if (box_writer == null || box_writer == "") {
		box_writer = "MotoBand™";
	}
	if (box_time == null || box_time == "") {
		alert("时间不能为空");
		return false;
	}
	if (box_keyword == null || box_keyword == "") {
		alert("关键字不能为空");
		return false;
	}
	
	
	var datas = {
		"box_title": box_title,
		"box_boxkind":box_boxkind,
		"box_subtitle": box_subtitle,
		"box_writer": box_writer,
		"box_time": box_time,
		"box_type": box_type,
		"box_content": box_content,
		"imglist":imglist,
		"box_approve":box_approve,
		"box_source":box_source,
		"box_submitter":box_submitter,
		"box_keyword":box_keyword,
		"box_boxurl":box_boxurl,
		"recommendBoxID":recommendBoxID
		
	}

	$.ajax({
		type: "POST",
		url: "../boxmanage/addNewBoxPage",
		data: datas,
		success: function(data) {
			if (data != "" && data != null) {
				alert("保存手册信息成功");
				window.opener=null;
				window.open('','_self');
				window.close();
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



function showTimeForm(e) {
//	var t = e.offsetTop;
//	var l = e.offsetLeft;
//	var w = e.offsetWidth;
//	var h = e.offsetHeight;
//	while (e = e.offsetParent) {
//		t += e.offsetTop;
//		l += e.offsetLeft;
//	}
//	$(".datetimepicker").css("top", t + 34 + "px");

}

function showgroupimg(groupid) {
	var datas = {
		"group_guid": groupid,
	}
	$.ajax({
		type: "POST",
		url: "../boxmanage/showgroupimg",
		data: datas,
		success: function(data) {
			if (data != "" && data != null) {
				var json = eval("(" + data + ")");
				$("#groupimglist").html("");
				for (var i in json) {
					addimgdiv(json[i]);
				}
			}

		}
	});
}

function addimgdiv(json) {
	var str = "<div class='col-lg-3' id='" + json.img_guid + "' onclick=\"javascript:selectImg('" + json.img_guid + "');\"><div class='panel panel-default'>" +
		"<div class='panel-body' style='text-align: center;'><img src='" + json.img_url + "' width='100' height='100'></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>" +
		"<input class='form-control' placeholder='图片名' name='ImgName' value='" + json.img_name + "' disabled='disabled'></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>" +
		"<div class='selected_mask' name='selectdiv' style='display: none'><div class='selected_mask_inner'></div>" +
		"<div class='selected_mask_icon'></div></div></div>";
	$("#groupimglist").append(str);
}







