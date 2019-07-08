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
		//$ ('#uploadForm').ajaxForm (options);
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

function XX_selectImg (XX_img_guid)
{
	var img_guid = XX_img_guid.replace ("XX_", "");
	if ($ ('#XX_' + img_guid).hasClass ('selected'))
	{
		$ ("#XX_groupimglist").children ("div").removeClass ('selected');
		$ ("#XX_groupimglist").find ("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).removeClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "none");
		var flag = $("#hiddenValue").val();
		if(flag=='1'){
			$ ("#titleimg").val ();
			}
			if(flag=='2'){
				$ ("#titleimgins").val ();
			}
		
		
	}
	else
	{
		$ ("#XX_groupimglist").children ("div").removeClass ('selected');
		$ ("#XX_groupimglist").find ("div[name='XX_selectdiv']").css ("display", "none");
		$ ('#XX_' + img_guid).addClass ('selected');
		$ ('#XX_' + img_guid).find ("div[name='XX_selectdiv']").css ("display", "block");
		var flag = $("#hiddenValue").val();
		if(flag=='1'){
			$ ("#titleimg").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
			}
			if(flag=='2'){
				$ ("#titleimgins").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
			}
		
		
		
	}
	
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
function insertTitleImg ()
{
	var flag = $("#hiddenValue").val();
	if(flag=='1'){
		$("#titleimgshow").attr ("src", $ ("#titleimg").val ());
		}
		if(flag=='2'){
			$("#titleimgshowins").attr ("src", $ ("#titleimgins").val ());	
		}
	
	
}

function editbrandparent(bpid){
	$("#hiddenValue").val("1");
	var datas={
		"bpid":bpid	
	}
	$.ajax (
			{
			    type : "POST",
			    url : "../brandparentmanage/getBrandparentByid",
			    data : datas,
			    success : function (data)
			    {
				    if (data != "" && data != null)
				    {
					    var jsonStr = eval ("(" + data + ")");
					   $("#update_bpid").val(jsonStr.bpid);
					   $("#update_name").val(jsonStr.name);
					   $("#titleimg").val(jsonStr.imgurl);
					   $("#titleimgshow").attr("src",jsonStr.imgurl);
					   $("#update_ishot").val(jsonStr.ishot);
					   $("#update_orderindex").val(jsonStr.orderindex);
					   
				    }
				    
			    }
			});
	
}
function editConfirm(){
	 var adminGuid=$("#userGuidHidden").val();
	 var bpid=  $("#update_bpid").val();
	 var name= $.trim($("#update_name").val());
	 var imgurl= $.trim($("#titleimg").val());
	 var ishot=  $("#update_ishot").val();
	 var orderindex=  $.trim($("#update_orderindex").val());
	 if(name==null && name ==''){
		 alert("name不能为空");
		 return;
	 }
	 if(imgurl==null && imgurl ==''){
		 alert("imgurl不能为空");
		 return;
	 }
	 if(orderindex==null && orderindex ==''){
		 alert("orderindex不能为空");
		 return;
	 }
	 var datas={
				"bpid":bpid,
				"name":name,
				"imgurl":imgurl,
				"ishot":ishot,
				"orderindex":orderindex
				
			}
			$.ajax (
					{
					    type : "POST",
					    url : "../brandparentmanage/updatebrandparentByid",
					    data : datas,
					    success : function (data)
					    {
						    if (data=='success')
						    {
							   alert("编辑成功");
							   $("#page-wrapper").load("../brandparentmanage/brandparentlist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
						    }
						    
					    }
					});
}
function insbrandparent(){
	$("#hiddenValue").val("2");
	 $("#ins_name").val("");
	 $("#titleimgins").val("");
	 $("#ins_ishot").val("");
	 $("#ins_orderindex").val("");
	 $("#titleimgshowins").attr("src","");
}
function insConfirm(){
	
	
	 var adminGuid=$("#userGuidHidden").val();
	 var name= $.trim($("#ins_name").val());
	 var imgurl= $.trim($("#titleimgins").val());
	 var ishot=  $("#ins_ishot").val();
	 var orderindex=  $.trim($("#ins_orderindex").val());
	 if(name==null && name ==''){
		 alert("name不能为空");
		 return;
	 }
	 if(imgurl==null && imgurl ==''){
		 alert("imgurl不能为空");
		 return;
	 }
	 if(orderindex==null && orderindex ==''){
		 alert("orderindex不能为空");
		 return;
	 }
	 var datas={
				"name":name,
				"imgurl":imgurl,
				"ishot":ishot,
				"orderindex":orderindex
				
			}
			$.ajax (
					{
					    type : "POST",
					    url : "../brandparentmanage/insbrandparent",
					    data : datas,
					    success : function (data)
					    {
						    if (data=='success')
						    {
							   alert("添加成功");
							   $("#page-wrapper").load("../brandparentmanage/brandparentlist?userGuid=" + adminGuid+ "&page=1&limit=20&order=0");
						    }
						    
					    }
					});
}