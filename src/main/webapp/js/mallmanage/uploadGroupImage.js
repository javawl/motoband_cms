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
					 var img_groupid=$ ("#XX_nowgroupid").val ();
					 console.log(img_groupid.substring(0,1));
					if(img_groupid.substring(0,1)=="X"){
						img_groupid=img_groupid.substring(3);

					 }
					/*var str = "<div class='col-lg-3' id='" +img_guid+ "' onclick=\"javascript:selectImg('" +img_guid+ "');\"><div class='panel panel-default'>" +
		"<div class='panel-body' style='text-align: center;'><img src='" + img_url+ "' width='100' height='100'></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>" +
		"<textarea   rows='2' style='word-break: break-all;'  class='form-control' placeholder='图片名' name='ImgName' value='" + img_name + "' disabled='disabled'>" + img_name + "</textarea></div>" +
		"<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>" +
		"<div class='selected_mask' name='selectdiv' style='display: none'><div class='selected_mask_inner'></div>" +
		"<div class='selected_mask_icon'></div></div></div>";*/
					var str = "<div class='col-lg-3' id='XX_"
				        + img_guid
				        + "' onclick=\"javascript:XX_selectImg('XX_"
				        + img_guid
				        + "');\"><div class='panel panel-default'>"
				        + "<div class='panel-body' style='text-align: center;'><img src='"
				        + img_url
				        + "' width='100' height='100'></div>"
				        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>"
				        + "<textarea   rows='2' style='word-break: break-all;'   class='form-control' placeholder='图片名' name='ImgName' value='"
				        + img_name
				        + "' disabled='disabled'>"+img_name+"</textarea></div>"
				        + "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
				        + "<div class='selected_mask' name='XX_selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
				        + "<div class='selected_mask_icon'></div></div></div>";
					$('#XX_groupimglist').append(str);

				
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
								 $("#back1").css("display","none");
								initUploadForm();
							}
						}
					});
				},
				error: function(ret) {
					alert(ret.responseText);
					$("#back1").css("display","none");
				}
			};
			// pass options to ajaxForm
			$('#uploadForm').ajaxForm(options);
		});
}
function imgsubmit(){
	
	 $("#back1").css("display","");
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




function showImgModel() {
	if ($('#ImgModel').css("display") == "none") {
		$('#ImgModel').css("display", "block");
	} else {
		$('#ImgModel').css("display", "none");
	}
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
