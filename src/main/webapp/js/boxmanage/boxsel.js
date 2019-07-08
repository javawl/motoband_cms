$ (document).ready (function ()
{
	initUploadForm ();
});
$ ('input[name=FileContent]').change (function ()
{

	initUploadForm ();
});

$ ('body').on ('click', '#downloadBtn', function ()
{
	$ ('#downloadImg').attr ('src', $ ('#downloadUrl').text ());
});

$ ('body').on ('click', '#deleteBtn', function ()
{
	var manageUrl = $ ('#url').text ();
	var fileid = $ ('#fileid').text ();
	if (!manageUrl)
	{
		alert ('尚未获取管理url');
		return false;
	}
	manageUrl = manageUrl + '/del';
	// 请将以下获取签名的链接换成您部署好的服务端http url
	// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
	$.getJSON ('../boxmanage/uploadBoxImg?type=delete&fileid=' + encodeURIComponent (fileid), function (data)
	{
		
		var sign = data.sign, url = data.url + '?sign=' + encodeURIComponent (sign);
		$.ajax (
		{
		    type : "POST",
		    url : url,
		    data : {},
		    success : function ()
		    {
			    alert ('删除成功');
			    $ ('#inputfile').attr ("disabled", "false");
			    $ ('#subbtn').attr ("disabled", "false");
			    $ ('#downloadRet').css ("display", "none");
		    },
		    
		    dataType : 'json'
		});
	});
});

$ ('body').on ('click', '#queryBtn', function ()
{
	var manageUrl = $ ('#url').text ();
	if (!manageUrl)
	{
		alert ('尚未获取管理url');
		return false;
	}
	$.ajax (
	{
	    type : "GET",
	    url : manageUrl,
	    data : {},
	    success : function (data)
	    {
		    $ ('#imgInfo').text (JSON.stringify (data));
	    },
	    error : function (ret)
	    {
		    $ ('#imgInfo').text (ret.responseText);
	    },
	    dataType : 'json'
	});
});
function initUploadForm ()
{
	// 请将以下获取签名的链接换成您部署好的服务端http url
	// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
	$.getJSON ('../boxmanage/uploadBoxImg', function (data)
	{
		
		var sign = data.sign, url = data.url + '?sign=' + encodeURIComponent (sign);
		
		var options =
		{
		    type : 'post',
		    url : url,
		    dataType : 'json',
		    success : function (ret)
		    {
			    $ ('#downloadUrl').html (ret.data.download_url);
			    $ ('#fileid').text (ret.data.fileid);
			    $ ('#url').text (ret.data.url);
			    $ ('#downloadRet').show ();
				var str="<div class='row'><div class='col-lg-4' id='"+ret.data.fileid+
				"'onclick=\"javascript:selectImg('"+ret.data.fileid+
				"');\""+"><div class='panel panel-default'><div class='panel-body' style='text-align: center;'>"+
							"<img src='"+ret.data.download_url+"' width='100' height='100'></div><div class='panel-footer center' style='text-align: center;'>"+
							"<input type='button' value='选择' class='btn btn-info'></div></div>"+
					"<div class='selected_mask' name='selectdiv' style='display: none'>"+
						"<div class='selected_mask_inner'></div>"+
						"<div class='selected_mask_icon'></div></div></div>";
				$('#imglistdiv').append(str);
			    
			    var img_guid = ret.data.fileid;
			    var img_url = ret.data.download_url;
			    var img_opurl = ret.data.url;
			    var datas =
			    {
			        "img_guid" : img_guid,
			        "img_url" : img_url,
			        "img_opurl" : img_opurl
			    }
			    $.ajax (
			    {
			        type : "POST",
			        url : "../boxmanage/addNewBoxImg",
			        data : datas,
			        success : function (data)
			        {
				        if (data != "" && data != null)
				        {
					        alert ("添加图片资源成功！");
					        initUploadForm();
				        }
				        
			        }
			    });
		    },
		    error : function (ret)
		    {
			    alert (ret.responseText);
		    }
		};
		
		// pass options to ajaxForm
		$ ('#uploadForm').ajaxForm (options);
	});
}
function saveBoxMessage (box_id)
{
	var box_writer = $ ('#box_writer').val ();
	var box_title = $ ('#box_title').val ();
	var box_subtitle = $ ('#box_subtitle').val ();
	var box_time = $ ('#boxtime').val ();
	var temp = document.getElementsByName ("box_status");
	var box_status = "";
	for (var i = 0; i < temp.length; i++)
	{
		if (temp[i].checked)
			box_status = temp[i].value;
	}
	var box_type = $ ("#box_type").val ();
	var box_content = $ ('#editor').cleanHtml ();
	
	if (box_title == null || box_title == "")
	{
		alert ("标题不能为空");
		return false;
	}
	if (box_subtitle == null || box_subtitle == "")
	{
		alert ("副标题不能为空");
		return false;
	}
	if (box_writer == null || box_writer == "")
	{
		box_writer="MotoBand™";
	}
	if (box_time == null || box_time == "")
	{
		alert ("时间不能为空");
		return false;
	}
	
	var datas =
	{
	    "box_id" : box_id,
	    "box_title" : box_title,
	    "box_subtitle" : box_subtitle,
	    "box_writer" : box_writer,
	    "box_type" : box_type,
	    "box_time" : box_time,
	    "box_content" : box_content,
	    "box_status" : box_status
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/addBoxPage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("保存手册信息成功");
			    $ ("#page-wrapper").load ("../boxmanage/boxlist?userGuid=" + data.userid);
		    }
		    
	    }
	});
	
}
function showImgModel ()
{
	if ($ ('#ImgModel').css ("display") == "none")
	{
		$ ('#ImgModel').css ("display", "block");
	}
	else
	{
		$ ('#ImgModel').css ("display", "none");
	}
}
function cleanFontStyle ()
{
	$ ("#editor").find ("span").css ("font-family",
	        "'Helvetica Neue',Helvetica,'Hiragino Sans GB','Microsoft YaHei',Arial,sans-serif");
}
function selectImg (img_guid)
{
	if ($ ('#' + img_guid).hasClass ('selected'))
	{
		$ ('#' + img_guid).removeClass ('selected');
		$ ('#' + img_guid).find ("div[name='selectdiv']").css ("display", "none");
	}
	else
	{
		$ ('#' + img_guid).addClass ('selected');
		$ ('#' + img_guid).find ("div[name='selectdiv']").css ("display", "block");
	}
	
}
function XX_selectImg (XX_img_guid)
{
	var img_guid=XX_img_guid.replace("XX_","");
	if ($ ('#XX_' + img_guid).hasClass ('selected'))
	{
		$("#XX_groupimglist").children("div").removeClass('selected');
		$("#XX_groupimglist").find("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).removeClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "none");
		$("#titleimg").val();
	}
	else
	{
		$("#XX_groupimglist").children("div").removeClass('selected');
		$("#XX_groupimglist").find("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).addClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "block");
	
		$("#titleimg").val($ ('#XX_' + img_guid).find ("img").attr("src"));
	}
	
}
function insertImg ()
{
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

function previewBoxMessage (box_id)
{

	var box_title = $ ('#box_title').val ();
	var box_boxkind=$('#box_boxkind').val();
	var box_subtitle = $ ('#box_subtitle').val ();
	var box_writer = $ ('#box_writer').val ();
	var box_time = $ ('#boxtime').val ();
	var box_type = $ ("#box_type").val ();
	var box_keyword=$("#box_keyword").val();
	var box_submitter=$("#box_submitter").val();
	var box_source=$("#box_source").val();
	var box_approve=$("#box_approve").val();
	var box_boxurl=$('#box_boxurl').val();
	var box_content = editor.html().replace(/[\n]/ig,'');

//	if (box_boxurl == null || box_boxurl == "")
//	{
//		alert ("微信地址不能为空");
//		return false;
//	}
	
	var temp = document.getElementsByName ("box_status");
	var box_status = "";
	var box_titleimg=$('#titleimg').val();

	for (var i = 0; i < temp.length; i++)
	{
		if (temp[i].checked)
			box_status = temp[i].value;
	}
	var a = new Array (); 
	var i=0;

	$('#cont').find("iframe").contents().find("img").each(function(i){ 
		
		a[i++]=$(this).attr("src").substr($(this).attr("src").lastIndexOf("/")+1)

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
	if (box_writer == null || box_writer == "")
	{
		box_writer="MotoBand™";
	}
	if (box_time == null || box_time == "")
	{
		alert ("时间不能为空");
		return false;
	}
	if (box_keyword == null || box_keyword == "") {
		alert("关键字不能为空");
		return false;
	}
	var datas =
	{
	    "box_id" : box_id,
	    "box_title" : box_title,
	    "box_boxkind":box_boxkind,
	    "box_subtitle" : box_subtitle,
	    "box_writer" : box_writer,
	    "box_time" : box_time,
	    "box_type" : box_type,
	    "box_content" : box_content,
	    "imglist":imglist,
	    "box_status" : box_status,
	    "preview" : 1,
	    "box_titleimg":box_titleimg,
	    "box_approve":box_approve,
		"box_source":box_source,
		"box_submitter":box_submitter,
		"box_keyword":box_keyword,
	    "box_boxurl":box_boxurl
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/addBoxPage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {

			    json = eval ('('+data+')');

			    alert ("保存手册信息成功");
			    window.open(json.root[0].showurl);
			    
		    }
		    
	    }
	});
}
function showTimeForm(e){
//	var t=e.offsetTop; 
//	var l=e.offsetLeft; 
//	var w=e.offsetWidth;
//	var h=e.offsetHeight; 
//	while(e=e.offsetParent)
//	{ 
//		t+=e.offsetTop; 
//		l+=e.offsetLeft; 
//	} 
//
//    $(".datetimepicker").css("top",t+34+"px");

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
function XX_showgroupimg(XX_groupid) {
	var groupid=XX_groupid.replace("XX_","");
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
				$("#XX_groupimglist").html("");
				for (var i in json) {
					XX_addimgdiv(json[i]);
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
function XX_addimgdiv(json) {
	var str = "<div class='col-lg-3' id='XX_" + json.img_guid + "' onclick=\"javascript:XX_selectImg('XX_" + json.img_guid + "');\"><div class='panel panel-default'>" +
	"<div class='panel-body' style='text-align: center;'><img src='" + json.img_url + "' width='100' height='100'></div>" +
	"<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>" +
	"<input class='form-control' placeholder='图片名' name='ImgName' value='" + json.img_name + "' disabled='disabled'></div>" +
	"<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>" +
	"<div class='selected_mask' name='XX_selectdiv' style='display: none'><div class='selected_mask_inner'></div>" +
	"<div class='selected_mask_icon'></div></div></div>";
	$("#XX_groupimglist").append(str);
}








