var format = function (time, format)
{
	var t = new Date (time);
	var tf = function (i)
	{
		return (i < 10 ? '0' : '') + i
	};
	return format.replace (/yyyy|MM|dd|HH|mm|ss/g, function (a)
	{
		switch (a)
		{
			case 'yyyy':
				return tf (t.getFullYear ());
				break;
			case 'MM':
				return tf (t.getMonth () + 1);
				break;
			case 'mm':
				return tf (t.getMinutes ());
				break;
			case 'dd':
				return tf (t.getDate ());
				break;
			case 'HH':
				return tf (t.getHours ());
				break;
			case 'ss':
				return tf (t.getSeconds ());
				break;
		}
	})
}


function getTopicNews (label)
{
	var topictype=$("#topictypeHidden").val();
	window.open ("../news/topicnews?label=" + label+"&topictype="+topictype);
	
}




function addBanner (keywords,topictype )
{
 if(confirm("你真的要添加到banner么，此操作不可逆，需谨慎操作")){
	var type=0;
	if(topictype==1){
		type=13;
	}else if(topictype==0){
		type=2;
	}
	var datas =
	{
	    
	    "keywords" : keywords,
	    "type" : type
	
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/addbanner',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    if (data == "success")
			    {
				    alert ("添加成功");
			    }
		    }
		    
	    }
	});
 }
}
function addTopic (topictype)
{
	$ ('#topic_id').val ("");
	$ ('#topic_title').val ("");
	$ ('#topic_keyword').val ("");
	if(topictype==1){
		$ ('#topic_keyword_div').hide();
	}else{
		$ ('#topic_keyword_div').show();
		$ ('#topic_keyword').removeAttr ("readonly");
	}
	
	$ ('#topic_content').val ("");
	$ ('#topic_status').val ("");
	$ ('#topic_orderindex').val ("");
	$ ('#titleimg').val ("");
	$ ('#titleimgshow').attr ("src", "");
	$ ('#topic_userid').val ("");
	$ ('#topic_pubtime').val ("");
	$ ('#topic_picwidth').val ("");
	$ ('#topic_picheight').val ("");
	$ ('#topic_pid').val ("");
	$ ('#topic_businessuserid').val ("");
}
function saveTopic ()
{
	var topictype= $("#topictypeHidden").val();
	var id = $ ('#topic_id').val ();
	var title = $ ('#topic_title').val ();
	var keyword = $ ('#topic_keyword').val ();
	
	var content = $ ('#topic_content').val ();
	var status = $ ('#topic_status').val ();
	var pic = $ ('#titleimg').val ();
	var orderindex = $ ('#topic_orderindex').val ();
	var userid = $ ('#topic_userid').val ();
	var pubtime = $ ('#topic_pubtime').val ();
	var picwidth=$ ('#topic_picwidth').val ();
	var picheight=$ ('#topic_picheight').val ();
	var pid=$ ('#topic_pid').val ();
	var businessuserid=$ ('#topic_businessuserid').val ();
	
	if(topictype==1){
		if(title==null||content==null||status==null||pic==null||orderindex==null||title==""||content==""||status==""||pic==""||orderindex==""||
				userid==""|| pubtime==""||userid==null|| pubtime==null||picwidth==null ||picheight==null ||picwidth=="" ||picheight==""){
			alert("有数据为空");
			return false;
		}
		
	}else{
		
		if(keyword==null||title==null||content==null||status==null||pic==null||orderindex==null||keyword==""||title==""||content==""||status==""||pic==""||orderindex==""){
			alert("有数据为空");
			return false;
		}
		if(keyword.length>16){
			alert("keyword太长");
			return false;
		}
	}
	var datas =
	{
	    "id" : id,
	    "title" : title,
	    "keyword" : keyword,
	    "content" : content,
	    "status" : status,
	    "pic" : pic,
	    "orderindex" : orderindex,
	    "topictype":topictype,
	    "userid":userid,
	    "pubtime":pubtime,
	    "picwidth":picwidth,
	    "picheight":picheight,
	    "pid":pid,
	    "businessuserid":businessuserid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../news/addTopic',
	    data : datas,
	    success : function (data)
	    {
		    if (data != null && data != "")
		    {
			    alert ("保存成功");
			    $(".modal-backdrop").remove();
		    	 $("body").removeClass('modal-open');
			    $ ("#page-wrapper").load ("../news/topicAndDiscusslist?userGuid=" + data+"&page=1&limit=20&order=0&topictype="+topictype);
		    }else{
		    	 alert ("保存失败");
		    }
	    }
	});
	
}
function updateTopic (topicid, orderindex,topictype)
{
	$ ('#topic_keyword_div').show();
	var title = $ ('.topictable .' + topicid + ' td').eq (2).text ();
	var keyword = $ ('.topictable .' + topicid + ' td').eq (1).text ();
	var content =$("#"+topicid+"topiccontent").val();
	var status = $ ('.topictable .' + topicid + ' td').eq (5).text ();
	var pic = $ ('.topictable .' + topicid + ' td img').eq (0).attr("src");
	pic = pic.replace("!thumb", ""); 
	var orderindex = orderindex;
	$ ('#topic_title').val (title);
	$ ('#topic_keyword').val (keyword);
	$ ('#topic_keyword').attr ("readonly", "readonly");
	$ ('#topic_content').val (content);
	$ ('#topic_status').val (status);
	$ ('#titleimg').val (pic);
	$ ('#topic_orderindex').val (orderindex);
	$ ('#titleimgshow').attr ("src", pic);
	// var orderindex = $ ('#topic_orderindex').val (orderindex);
	if(topictype==1){
		var userid = $ ('.topictable .' + topicid + ' td').eq (6).text ();
		var pubtime = $ ('.topictable .' + topicid + ' td').eq (7).text ();
		var picwidth = $ ('.topictable .' + topicid + ' td').eq (8).text ();
		var picheight = $ ('.topictable .' + topicid + ' td').eq (9).text ();
		$ ('#topic_userid').val (userid);
		$ ('#topic_pubtime').val (pubtime);
		$ ('#topic_picwidth').val (picwidth);
		$ ('#topic_picheight').val (picheight);
		
	}
	var pid = $ ('.topictable .' + topicid + ' td').eq (10).text ();
	var businessuserid = $ ('.topictable .' + topicid + ' td').eq (11).text ();
	$ ('#topic_pid').val (pid);
	$ ('#topic_businessuserid').val (businessuserid);
}

$ ('#grouplist > div').on ("click", function ()
{
	showgroupimg ($ (this).attr ("id"));
	$ ("#nowgroupid").val ($ (this).attr ("id"));
	$ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
	$ (this).addClass ('groupaction');
	
});
$ ('#XX_grouplist>div').on ("click", function ()
{
	XX_showgroupimg ($ (this).attr ("id"));
	$ ("#XX_nowgroupid").val ($ (this).attr ("id"));
	$ (this).parent ("#XX_grouplist").find ("div").removeClass ('groupaction');
	$ (this).addClass ('groupaction');
	
});

// tooltip demo
$ ('.tooltip-demo').tooltip (
{
    selector : "[data-toggle=tooltip]",
    container : "body"
})

// popover demo
$ ("[data-toggle=popover]").popover ()

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
	$
	        .getJSON (
	                '../boxmanage/uploadBoxImg',
	                function (data)
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
			                    $ ('#imglistdiv').append (str);
			                    
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
					                        initUploadForm ();
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
		                // $ ('#uploadForm').ajaxForm (options);
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
		box_writer = "MotoBand™";
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
	var img_guid = XX_img_guid.replace ("XX_", "");
	if ($ ('#XX_' + img_guid).hasClass ('selected'))
	{
		$ ("#XX_groupimglist").children ("div").removeClass ('selected');
		$ ("#XX_groupimglist").find ("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).removeClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "none");
		var flag =$("#hiddenValue").val();
		if(flag=='1'){
			$ ("#titleimg").val ();
		}else if(flag=='2'){
			$ ("#titleimgimageurl").val ();
		}else if(flag=='3'){
			$ ("#titleimgcimageurl").val ();
		}
		
	}
	else
	{
		$ ("#XX_groupimglist").children ("div").removeClass ('selected');
		$ ("#XX_groupimglist").find ("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).addClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "block");
		var flag =$("#hiddenValue").val();
		if(flag=='1'){
			$ ("#titleimg").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}else if(flag=='2'){
			$ ("#titleimgimageurl").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}else if(flag=='3'){
			$ ("#titleimgcimageurl").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
	}
	
}
function insertImg ()
{
	var string = "";
	imglist = new Array ();
	for (var i = 0; i < $ ('.selected').length; i++)
	{
		string = $ ('.selected').eq (0).find ("img").attr ("src");
		imglist[i] = $ ('.selected').eq (i).find ("img").attr ("src");
	}
	$ ("#remoteUrl").val (string);
	$ ('#selectImgModel').css ("display", "none");
	$ ('.selected').removeClass ("selected");
	$ ('body').find ("div[name='selectdiv']").css ("display", "none");
	
}

function previewBoxMessage (box_id)
{
	
	var box_writer = $ ('#box_writer').val ();
	var box_title = $ ('#box_title').val ();
	var box_subtitle = $ ('#box_subtitle').val ();
	var box_time = $ ('#boxtime').val ();
	
	var box_boxkind = $ ('#box_boxkind').val ();
	var box_boxurl = $ ('#box_boxurl').val ();
	
	if (box_boxurl == null || box_boxurl == "")
	{
		alert ("微信地址不能为空");
		return false;
	}
	
	var temp = document.getElementsByName ("box_status");
	var box_status = "";
	var box_titleimg = $ ('#titleimg').val ();
	
	for (var i = 0; i < temp.length; i++)
	{
		if (temp[i].checked)
			box_status = temp[i].value;
	}
	var box_type = $ ("#box_type").val ();
	var box_content = editor.html ().replace (/[\n]/ig, '');
	var a = new Array ();
	var i = 0;
	
	$ ('#cont').find ("iframe").contents ().find ("img").each (function (i)
	{
		
		a[i++] = $ (this).attr ("src").substr ($ (this).attr ("src").lastIndexOf ("/") + 1)

	});
	var imglist = JSON.stringify (a);
	if (box_title == null || box_title == "")
	{
		alert ("标题不能为空");
		return false;
	}
	if (box_title.length > 20)
	{
		alert ("标题不能超过20字");
		return false;
	}
	
	if (box_subtitle == null || box_subtitle == "")
	{
		alert ("副标题不能为空");
		return false;
	}
	if (box_subtitle.length > 64)
	{
		alert ("副标题不能超过64字");
		return false;
	}
	if (box_writer == null || box_writer == "")
	{
		box_writer = "MotoBand™";
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
	    "box_status" : box_status,
	    "box_boxkind" : box_boxkind,
	    "imglist" : imglist,
	    "preview" : 1,
	    "box_titleimg" : box_titleimg,
	    "box_boxurl" : box_boxurl
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
			    
			    json = eval ('(' + data + ')');
			    
			    alert ("保存手册信息成功");
			    window.open (json.root[0].showurl);
			    
		    }
		    
	    }
	});
}
function showTimeForm (e)
{
	var t = e.offsetTop;
	var l = e.offsetLeft;
	var w = e.offsetWidth;
	var h = e.offsetHeight;
	while (e = e.offsetParent)
	{
		t += e.offsetTop;
		l += e.offsetLeft;
	}
	
	$ (".datetimepicker").css ("top", t + 34 + "px");
	
}
function showgroupimg (groupid)
{
	var datas =
	{
		"group_guid" : groupid,
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/showgroupimg",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval ("(" + data + ")");
			    $ ("#groupimglist").html ("");
			    for ( var i in json)
			    {
				    addimgdiv (json[i]);
			    }
		    }
		    
	    }
	});
}
function XX_showgroupimg (XX_groupid)
{
	var groupid = XX_groupid.replace ("XX_", "");
	var datas =
	{
		"group_guid" : groupid,
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/showgroupimg",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var json = eval ("(" + data + ")");
			    $ ("#XX_groupimglist").html ("");
			    for ( var i in json)
			    {
				    XX_addimgdiv (json[i]);
			    }
		    }
		    
	    }
	});
}
function addimgdiv (json)
{
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
	$ ("#groupimglist").append (str);
}
function XX_addimgdiv (json)
{
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
	$ ("#XX_groupimglist").append (str);
}

function insertTitleImg ()
{
	var flag =$("#hiddenValue").val();
	if(flag=='1'){
		$ ("#titleimgshow").attr ("src", $ ("#titleimg").val ());
	}else if(flag=='2'){
		$ ("#titleimgshowimageurl").attr ("src", $ ("#titleimgimageurl").val ());
	}else if(flag=='3'){
		$ ("#titleimgshowcimageurl").attr ("src", $ ("#titleimgcimageurl").val ());
	}
}

function insertHiddleValue(value){
	$("#hiddenValue").val(value);
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

function updateisman(topicid,ismain){
	var datas =
	{
	    "topicid" : topicid,
	    "ismain" : ismain
	}

	$.ajax (
	{
	    type : "POST",
	    url : '/news/updateTopicIsMain',
	    data : datas,
	    success : function (data)
	    {
		    if (data != null && data != "")
		    {
			    $ ("#page-wrapper").load ("../news/topicAndDiscusslist?userGuid=0&page=1&limit=20&order=0&topictype=1");
			    alert ("保存成功");
		    }else{
		    	 alert ("保存失败");
		    }
	    }
	});
}