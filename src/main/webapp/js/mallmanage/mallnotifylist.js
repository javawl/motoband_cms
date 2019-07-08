function insnotify(){
	$("#ins_name").val("");
	$("#ins_mallurl").val("");
	$("input[name='ins_source']").attr("checked",false);
	$("#titleimg").val("");
	$("#hiddenValue").val("1");
}
function addConfirm(){
	var name=$.trim($("#ins_name").val());
	var mallurl=$.trim($("#ins_mallurl").val());
	var source=$("input[name='ins_source']:checked").val();
	var picurl=$("#titleimg").val();
	var adminGuid=$("#userGuidHidden").val();
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	if(mallurl==null || mallurl==""){
		alert("mallurl不能为空");
		return ;
	}
	if(source==null || source==""){
		alert("name不能为空");
		return ;
	}
	if(picurl==null || picurl==""){
		alert("name不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name,
	    "mallurl":mallurl,
	    "source":source,
	    "picurl":picurl
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/insMallNotify',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				   // 	$('#insnotifyModel').modal('hide');
				    	$('#insnotifyModel').css('display', 'none');
	  				//	  $('.modal-backdrop').css('display', 'none');
				    	 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/mallnotifylist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function openmallurl(url){
	window.open(url);
}
function editNotify(id){
	$("#hiddenValue").val("2");
	$("#hiddenValueNotifyID").val(id);
	var datas =
	{
	    "notifyid" :id,
	  
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/getMallNotifyByid',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data!=null && data !="")
				    {
				      var jasondata= eval("("+data+")");
				    $("#update_name").val(jasondata.name);
				  	$("#update_mallurl").val(jasondata.mallurl);
				  	$("input[name='update_source'][value="+jasondata.source+"]").prop("checked",true);
				  	$("#titleimgadimg").val(jasondata.picurl);
				  	$("#titleimgshowadimg").attr("src",jasondata.picurl);
				    }
			    }
			});
	
	
	
}
function editNotifyConfirm(){
	  var notifyid=$("#hiddenValueNotifyID").val();
	  var name=$("#update_name").val();
	  var mallurl=$("#update_mallurl").val();
	  var source=$("input[name='update_source']:checked").val();
	  var picurl=$("#titleimgadimg").val();
	  var adminGuid=$("#userGuidHidden").val();	
	var datas =
	{
	    "notifyid" :notifyid,
	    "name":name,
	    "mallurl":mallurl,
	    "source":source,
	    "picurl":picurl
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/updateMallNotify',
			    data:datas,
			    success : function (data)
			    {
				 
			    	 if (data=="success")
					    {
					    	alert("修改成功");
					    //	$('#editNotifyModel').modal('hide');
					    	$('#editNotifyModel').css('display', 'none');
		  				//	  $('.modal-backdrop').css('display', 'none');
					    	 $(".modal-backdrop").remove();
					    	  $("body").removeClass('modal-open');
					    	$ ("#page-wrapper").load ("/mallmanage/mallnotifylist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
			         }
			    }
			});
}
function deleteNotify(id){
	$("#delNotifyid").val(id);
}
function deleteNotifyConfirm(){
	var notifyid=$("#delNotifyid").val();
    var adminGuid=$("#userGuidHidden").val();	
	var datas =
	{
	    "notifyid" :notifyid
	 
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/delMallNotifyByid',
			    data:datas,
			    success : function (data)
			    {
				 
			    	 if (data=="success")
					    {
					    	alert("删除成功");
					    	
					    	$ ("#page-wrapper").load ("/mallmanage/mallnotifylist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
			           }
			    }
			});
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
			$ ("#titleimgadimg").val ();
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
			$ ("#titleimgadimg").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
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
		$ ("#titleimgshow").attr ("src", $ ("#titleimg").val ());
	}
	if(flag=='2'){
		$ ("#titleimgshowadimg").attr ("src", $ ("#titleimgadimg").val ());
	}
	
}
