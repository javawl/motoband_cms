function clearModel(){
//	$ ("#upachid").val ("");
//	$ ("#upachid").css("display","inline");
//	$ ("#achname").val("");
//	$ ("#achdes").val ("");
//	$ ("#titleimg1").val ("");
//	$ ("#titleimgshow1").attr ('src', "");
//	$ ("#titleimg2").val ("");
//	$ ("#titleimgshow2").attr ('src', "");
//	$ ("#achcode").val ("");
//	$ ("#mallurl").val ("");
	$("#updateORins").val(1);
}
function updateTopic (achid)
{
	
	var achid = $ ('.' + achid + ' td').eq (1).text ();
	var achname = $ ('.' + achid + ' td').eq (2).text ();
	var achdes = $ ('.' + achid + ' td').eq (3).text ();
	var achimg = $ ('.' + achid + ' td img').eq (0).attr("src");
	var achdisableimg = $ ('.' + achid + ' td img').eq (1).attr("src");
	var achcode = $ ('.' + achid + ' td').eq (0).text ();
	var groupname = $ ('.' + achid + ' td').eq (6).text ();
	var mallurl = $ ('.' + achid + ' td').eq (7).text ();
	var score = $ ('.' + achid + ' td').eq (8).text ();
	var achtype = $ ('.' + achid + ' td').eq (9).attr("name");
	$ ("#upachid").val (achid);
	$ ("#upachid").css("display","none");
	$ ("#achname").val (achname);
	$ ("#achdes").val (achdes);
	$ ("#titleimg1").val (achimg);
	$ ("#titleimgshow1").attr ('src', achimg);
	$ ("#achcode").val (achcode);
	$("#score").val(score);
	console.log(score)
	if(achdisableimg==null ||achdisableimg==''){
		$ ("#titleimg2").val ('');
		$ ("#titleimgshow2").attr ('src', '');
	}else{
		$ ("#titleimg2").val (achdisableimg);
		$ ("#titleimgshow2").attr ('src', achdisableimg);
	}
	
	
	//console.log(groupname);
	//console.log(typeof(mallurl));
	//console.log("aaaaa'"+mallurl+"'aaaa");
	//$("#groupname").removeAttr('selected');
	//$("#groupname option[value='"+groupname+"'] ").prop("selected",true);
	var str="";
	if(groupname=="星座挑战"){
		str+=" <option value='星座挑战' selected >星座挑战</option>";
	}else{
		str+=" <option value='星座挑战' >星座挑战</option>";
	}
	if(groupname=="MotoGP"){
		str+=" <option value='MotoGP' selected >MotoGP</option>";
	}else{
		str+=" <option value='MotoGP' >MotoGP</option>";
	}
	
	if(groupname=="车神挑战"){
		str+=" <option value='车神挑战' selected >车神挑战</option>";
	}else{
		str+=" <option value='车神挑战' >车神挑战</option>";
	}
	if(groupname=="限时挑战"){
		str+=" <option value='限时挑战' selected >限时挑战</option>";
	}else{
		str+=" <option value='限时挑战' >限时挑战</option>";
	}
	if(groupname=="月度挑战"){
		str+=" <option value='月度挑战' selected >月度挑战</option>";
	}else{
		str+=" <option value='月度挑战' >月度挑战</option>";
	}
	if(groupname=="24节气"){
		str+=" <option value='24节气' selected >24节气</option>";
	}else{
		str+=" <option value='24节气' >24节气</option>";
	}
	if(groupname=="节日"){
		str+=" <option value='节日' selected >节日</option>";
	}else{
		str+=" <option value='节日' >节日</option>";
	}
	if(groupname=="纪念日"){
		str+=" <option value='纪念日' selected >纪念日</option>";
	}else{
		str+=" <option value='纪念日' >纪念日</option>";
	}
	if(groupname=="十二生肖"){
		str+=" <option value='十二生肖' selected >十二生肖</option>";
	}else{
		str+=" <option value='十二生肖' >十二生肖</option>";
	}
	
	
	var str1="";
	if(achtype=="1"){
		str1+=" <option value='1' selected >骑行数据类</option>";
	}else{
		str1+=" <option value='1' >骑行数据类</option>";
	}
	if(achtype=="2"){
		str1+=" <option value='2' selected >动态类</option>";
	}else{
		str1+=" <option value='2' >动态类</option>";
	}
	if(achtype=="3"){
		str1+=" <option value='3' selected >骑行赛</option>";
	}else{
		str1+=" <option value='3' >骑行赛</option>";
	}
	if(achtype=="4"){
		str1+=" <option value='4' selected >等级</option>";
	}else{
		str1+=" <option value='4' >等级</option>";
	}
	if(achtype=="5"){
		str1+=" <option value='5' selected >注册</option>";
	}else{
		str1+=" <option value='5' >注册</option>";
	}
	if(achtype=="6"){
		str1+=" <option value='6' selected >骑迹景点</option>";
	}else{
		str1+=" <option value='6' >骑迹景点</option>";
	}
	$("#groupname").empty();
	$("#groupname").append(str);
	$("#achtype").empty();
	$("#achtype").append(str1);
	if(mallurl!=""&& mallurl!=null){
	  $ ("#mallurl").val ($.trim(mallurl));
	}else{
	  $("#mallurl").val ("");
	}

	$("#updateORins").val(0);
	
}
function addAchievement ()
{   
	var updateORins=$("#updateORins").val();
	var achid = $ ("#upachid").val ();
	console.log(achid);
	var achname = $ ("#achname").val ();
	var achdes = $ ("#achdes").val ();
	var achimg = $ ("#titleimg1").val ();
	var achdisableimg = $ ("#titleimg2").val ();
	var achcode = $ ("#achcode").val ();
	var groupname= $ ("#groupname").val ();
	var mallurl = $.trim($ ("#mallurl").val ());
	var score= $ ("#score").val ();
	var achtype= $ ("#achtype").val ();
	var reg=/^[1-9]\d*$/;
	
	if(achid==null || achid=='' || reg.test(achid)==false){
		alert("achid不能为空或者不是合法数字");
		return;
	}
	if(achname==null || achname==''){
		alert("achname不能为空");
		return;
	}
	if(achdes==null || achdes==''){
		alert("achdes不能为空");
		return;
	}
	if(achimg==null || achimg==''){
		alert("achimg不能为空");
		return;
	}
	if(achdisableimg==null|| achdisableimg==''){
		alert("achdisableimg不能为空");
		return;
	}
	if(achcode==null || achcode==''||reg.test(achcode)==false){
		alert("achcode不能为空或者不是合法数字");
		return;
	}
	if(groupname==null || groupname==''){
		alert("groupname不能为空");
		return;
	}
	if(achtype==null || achtype==''){
		alert("achtype不能为空");
		return;
	}
	var datas =
	{
		"updateORins":updateORins,
	    "achid" : achid,
	    "achname" : achname,
	    "achdes" : achdes,
	    "achimg" : achimg,
	    "achdisableimg" : achdisableimg,
	    "achcode" : achcode,
	    "mallurl":mallurl,
	    "score":score,
	    "groupname":groupname,
	    "achtype":achtype
	}
	$.ajax (
	{
	    type : "POST",
	    url : '../ach/addachievement',
	    data : datas,
	    success : function (data)
	    {
		    console.log(data)
	    	if(data!=null&&data!=""){
	    		alert("保存成功");
		    	$("#page-wrapper").load("../ach/achlist?userGuid=" + data);
	    	}else{
	    		alert("保存失败！！！");	
	    	}
		    
	    },
	  error :function(data){
		  alert("保存失败！！！achid可能已经存在，尝试更换新的achid");
	 }
	});
	
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
		$ ("#titleimg").val ();
	}
	else
	{
		$ ("#XX_groupimglist").children ("div").removeClass ('selected');
		$ ("#XX_groupimglist").find ("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).addClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "block");
		
		$ ("#titleimg").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
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
	if ($ ("#selim").val () == 1)
	{
		$ ("#titleimg1").val ($ ("#titleimg").val ());
		$ ("#titleimgshow1").attr ("src", $ ("#titleimg").val ());
	}
	else if ($ ("#selim").val () == 2)
	{
		$ ("#titleimg2").val ($ ("#titleimg").val ());
		$ ("#titleimgshow2").attr ("src", $ ("#titleimg").val ());
	}
	
}
function selachimg (k)
{
	if (k == 1)
	{
		$ ("#selim").val (1);
	}
	else if (k == 2)
	{
		$ ("#selim").val (2);
	}
}
function gotoMallurlNewPage(url){
	window.open(url);
}
function  updateVersion(versionname){
	if(confirm("真的要更新版本么？此操作不可逆")){
		var datas =
		{
		    "sign" :versionname
		 
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../dataversion/updateversion',
				    data:datas,
				    success : function (data)
				    {
					 
				    	 if (data!=null&&data!="")
						    {
						    	alert("更新成功");
						    	
				           }
				    }
				});
	}
	}
