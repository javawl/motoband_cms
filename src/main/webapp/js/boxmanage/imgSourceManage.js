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
function delImg (img_opurl, img_guid)
{
  $("#del_img_opurl").val(img_opurl);
  $("#del_img_guid").val(img_guid);
}
function delSingleImgConfirm(){
	var img_opurl= $("#del_img_opurl").val();
	var img_guid= $("#del_img_guid").val();
	var datas =
	{
		"img_guid" : img_guid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/delBoxImg",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    
			    if (data == "ok")
			    {
				    var manageUrl = img_opurl + "/del";
				  //  console.log(manageUrl);
				    $.getJSON ('../boxmanage/uploadBoxImg?type=delete&fileid=' + encodeURIComponent (img_guid),
				            function (data)
				            {
				    	      // console.log(data);
					            var sign = data.signdel, url = manageUrl + '?sign=' + encodeURIComponent (sign);
					            $.ajax (
					            {
					                type : "POST",
					                url : url,
					                data : {},
					                success : function (data)
					                {
					                //	console.log(data);
						                alert ("删除成功");
						                $ ("#" + img_guid).css ("display", "none");
					                },
					                dataType : 'json'
					            });
				            });
			    }
			    else
			    {
				    alert ("图片被文章引用不可删除！");
			    }
			    
		    }
		    
	    }
	});
	
}


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
			    var datas =
			    {
				    "img_guid" : img_guid
			    }
			    $.ajax (
			    {
			        type : "POST",
			        url : "../boxmanage/delBoxImg",
			        data : datas,
			        success : function (data)
			        {
				        if (data != "" && data != null)
				        {
					        alert ("图片删除成功！");
					        $ ("#page-wrapper").load ("../boxmanage/imgSourceManage");
				        }
				        
			        }
			    });
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
		console.log(url);
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
					        $ ("#page-wrapper").load ("../boxmanage/imgSourceManage");
				        }
				        
			        }
			    });
		    },
		    error : function (ret)
		    {
			    alert (ret.responseText);
		    }
		};
		$ ('#uploadForm').ajaxForm (options);
	});
}
function subfile ()
{
	$("#back").css("display","");
	
	var url = "", sign = "";
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/getSignUrl",
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    json = eval ("(" + data + ")");
			    
			    url = json.url;
			    sign = json.sign;
			    console.log(url+","+sign);
			    subfile1 (url, sign);
		    }
		    
	    },
	    error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert("上传失败，点击重试");
            $("#back").css("display","none");
        }
	
	});
}
function subfile1 (url, sign)
{
	var filelength = document.getElementById ("file2").files.length;
	var filename = "", groupid = "";
	var map = {}, newfilename = "";
	for (var i = 0; i < filelength; i++)
	{
		filename = document.getElementById ("file2").files[i].name;
		groupid = $ ("#nowgroupid").val ();
		newfilename = Math.uuidFast ()
		var imgurl = url + newfilename + "?sign=" + sign;
		map[newfilename] = filename;
		var formData = new FormData ();
		formData.append ('op', 'upload');
		formData.append ('fileContent', document.getElementById ("file2").files[i]);
		
		$.ajax (
		{
		    type : "POST",
		    url : imgurl,
		    data : formData,
		    processData : false,
		    contentType : false,
		    async : true,
		    success : function (ret)
		    {
		    	
			    console.log (ret);
			   
			    var json = eval ("(" + ret + ")");
			 //   var infoArr=json.data.info[0];
			 //   var aa=infoArr['0'].height;
			 //   console.log(aa);
			    var img_guid = json.data.fileid;
			    var img_url = json.data.download_url;
			    var img_opurl = json.data.url;
			    var img_name = map[json.data.fileid];
			    var datas =
			    {
			        
			        "img_guid" : img_guid,
			        "img_url" : img_url,
			        "img_opurl" : img_opurl,
			        "img_name" : img_name,
			        "img_groupid" : groupid
			    
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
					        var json = eval ("(" + data + ")");
					        addimgdiv (json);
					        alert ("添加图片资源成功！");
					        $("#back").css("display","none");
					        // $ ("#page-wrapper").load
					        // ("../boxmanage/imgSourceManage");
				        }
				        
			        },
				    error: function(XMLHttpRequest, textStatus, errorThrown) {
			            alert("上传失败，点击重试");
			            $("#back").css("display","none");
			        }
			    });
		    },
		    error: function(XMLHttpRequest, textStatus, errorThrown) {
	            alert("上传失败，点击重试");
	            $("#back").css("display","none");
	        }
		});
		
		
	}
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
function addimgdiv (json)
{
	var str = "<div class='col-lg-3' id='"
	        + json.img_guid
	        + "'><div style='z-index: 10000;float: left;'>"
	        + "<input type='checkbox' style='margin: 2px 2px;width: 20px;height: 20px;' name='imgcheck' value='"
	        + json.img_guid
	        + "'></div>"
	        + "<div class='panel panel-default'><div class='panel-body' style='text-align: center;'>"
	        + "<img src='"
	        + json.img_url
	        + "' width='100' height='100'></div>"
	        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>"
	        + "<textarea   rows='2' style='word-break: break-all;' class='form-control' placeholder='图片名' name='ImgName' value='"
	        + json.img_name
	        + "' disabled='disabled'>"+json.img_name+"</textarea></div>"
	        + "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'>"
	        + "<button type='button' class='btn btn-success btn-dropbox' onclick=\"javascript:editImgName('"
	        + json.img_guid
	        + "')\"><i name='editName' class='fa fa-edit'></i></button>"
	        + "<button type='button' class='btn btn-primary btn-dropbox' data-toggle='modal' data-target='#imgChangeGroupModel' onclick=\"javascript:showimgChangeGroup('"
	        + json.img_guid + "');\"><i class='fa fa-exchange'></i></button>"
	        + "<button type='button' class='btn btn-warning btn-dropbox'  data-toggle='modal' data-target='#delSingleImgModel' onclick=\"javascript:delImg('"
	        + json.img_opurl + "','" + json.img_guid + "');\"><i class='fa fa-trash'></i></button>"
	        + "</div></div></div>";
	$ ("#groupimglist").append (str);
}
function editImgName (guid)
{
	if ($ ('#' + guid).find ("textarea[name='ImgName']").attr ("disabled") == "disabled")
	{
		$ ('#' + guid).find ("textarea[name='ImgName']").removeAttr ('disabled');
		$ ('#' + guid).find ("i[name='editName']").removeClass ("fa-edit");
		$ ('#' + guid).find ("i[name='editName']").addClass ("fa-check");
	}
	else
	{
		var newname = $ ('#' + guid).find ("textarea[name='ImgName']").val ();
		var datas =
		{
		    "img_guid" : guid,
		    "newname" : newname
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../boxmanage/updateImgName",
		    data : datas,
		    success : function (data)
		    {
			    $ ('#' + guid).find ("textarea[name='ImgName']").attr ('disabled', "disabled");
			    $ ('#' + guid).find ("i[name='editName']").removeClass ("fa-check");
			    $ ('#' + guid).find ("i[name='editName']").addClass ("fa-edit");
		    }
		});
		
	}
	
}
function showimgChangeGroup (guid)
{
	$ ("#changeimgguid").val (guid);
	var temprole = document.getElementsByName ("imgnewgroup");
	var nowgroupid = $ ("#nowgroupid").val ();
	for (var j = 0; j < temprole.length; j++)
	{
		if (temprole[j].value == nowgroupid)
		{
			temprole[j].checked = true;
		}
		
	}
}
function saveimgNewGroup ()
{
	var img_guid = $ ("#changeimgguid").val ();
	var temprole = document.getElementsByName ("imgnewgroup");
	var imgnewgroup = "";
	for (var j = 0; j < temprole.length; j++)
	{
		if (temprole[j].checked)
		{
			imgnewgroup = temprole[j].value;
		}
	}
	var datas =
	{
	    "img_guid" : img_guid,
	    "imgnewgroup" : imgnewgroup
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/saveimgNewGroup",
	    data : datas,
	    success : function (data)
	    {
		    if (data != null && data != "")
		    {
			    alert ("图片更换成功！");
			    $ ('#' + img_guid).css ("display", "none");
		    }
		    
	    }
	});
}
function DelImgList ()
{
	var str = document.getElementsByName ("imgcheck");
	var objarray = str.length;
	var j = 0;
	var a = new Array ();
	for (i = 0; i < objarray; i++)
	{
		if (str[i].checked == true)
		{
			a[j] = str[i].value;
			j++;
		}
	}
	var imgguidlist = JSON.stringify (a)

	var datas =
	{
		"imgguidlist" : imgguidlist
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../boxmanage/delImgList",
	    data : datas,
	    success : function (data)
	    {
		    if (data != null && data != "")
		    {
			    alert (data);
			    if (data == "删除成功")
			    {
				    for (i = 0; i < objarray; i++)
				    {
					    if (str[i].checked == true)
					    {
						    
						    $ ('#' + str[i].value).css ("display", "none");
						    
					    }
				    }
			    }
		    }
	    }
	});
}
function saveimgListNewGroup ()
{
	var temprole = document.getElementsByName ("imglistnewgroup");
	var imglistnewgroup = "";
	for (var j = 0; j < temprole.length; j++)
	{
		if (temprole[j].checked)
		{
			imglistnewgroup = temprole[j].value;
		}
	}
	var str = document.getElementsByName ("imgcheck");
	var objarray = str.length;
	var j = 0;
	var a = new Array ();
	for (i = 0; i < objarray; i++)
	{
		if (str[i].checked == true)
		{
			a[j] = str[i].value;
			j++;
		}
	}
	var imgguidlist = JSON.stringify (a)

	var datas =
	{
	    "imglistnewgroup" : imglistnewgroup,
	    "imgguidlist" : imgguidlist
	}
	$.ajax (
			{
			    type : "POST",
			    url : "../boxmanage/saveimgListNewGroup",
			    data : datas,
			    success : function (data)
			    {
				    if (data != null && data != "")
				    {
					    alert (data);
					    for (var u = 0; u < objarray; u++)
						{
							if (str[u].checked == true)
							{
								$ ('#' + str[u].value).css ("display", "none");
							}
						}
				    }
				    
			    }
			});
}
function checkGroupName(){
	var groupname=$('#groupname').val();
	var datas = {
			"groupname" : groupname
		}
		$.ajax({
			type : "POST",
			url : "../boxmanage/checkGroupName",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					if (data == "ok") {
						$('#dangertext').css('display', 'none');
						$('#savebtn').removeClass('disabled');
					} else {
						if (groupname != null || groupname != "") {
							$('#dangertext').css('display', 'block');
							$('#savebtn').addClass('disabled');
						}
					}
				}
			}
		});
}
function addImgGroup(){
	var groupname=$('#groupname').val();
	if(groupname==null||groupname==""){
		alert("组名不能为空！");
		return false;
	}
	var datas = {
			"groupname" : groupname
		}
		$.ajax({
			type : "POST",
			url : "../boxmanage/addImgGroup",
			data : datas,
			success : function(data) {
				if (data != "" && data != null) {
					$("#page-wrapper").load("../boxmanage/imgSourceManage");
					$(".modal-backdrop").remove();
			    	$("body").removeClass('modal-open');
				}
			}
		});
}

