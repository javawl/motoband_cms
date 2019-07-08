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
function addTribal(){
	$("#addname").val("");
	$("#titleimgshow").attr("src","");
	$("#titleimg").val("");
	$("#titleimgshowadimg").attr("src","");
	$("#titleimgadimg").val("");
	$("input[name='addtype']").removeAttr('checked');
	$("#addtribalid").val("");
	$("#addaddtime").val("");
	$("#addmainadmins").val("");
	$("#addviceadmins").val("");
	$("#adddes").val("");
	$("#addaddes").val("");
	$("#oldtype").val("");
	
}
function editTribal(tribalid){
	 var str="";
	  var datas =
		{
			"tribalid" :tribalid
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../tribal/getTribalById",
		    data : datas,
		    success : function (data)
		    {
			    if (data !=''&& data !=null)
			    {
			    	var datajson = eval("("+data+")");
			    	$("#addtribalid").val(datajson.tribalid);
			    	$("#oldtype").val(datajson.type);
			    	$("#addaddtime").val(datajson.addtime);
			    	
			    	$("#addname").val(datajson.name);
                    $("#titleimg").val(datajson.logopic);
			    	$("#titleimgshow").attr("src",datajson.logopic)
			    	$("#titleimgadimg").val(datajson.bannerpic);
			    	$("#titleimgshowadimg").attr("src",datajson.bannerpic)
			    	$("#addmainadmins").val(datajson.mainadminsstr);
			    	$("#addviceadmins").val(datajson.viceadminsstr);
			    	$("#adddes").val(datajson.des);
			    	$("input[name='addtype'][value='"+datajson.type+"']").prop("checked",true);
			    }
			    
		    }
		});
}

function editTribalConfirm(){
  var tribalid=	$("#addtribalid").val();
  var addtime=	$("#addaddtime").val();
  var oldtype=	$("#oldtype").val();
  var tribaltype= $("#tribaltypeSelect").val();
  var name=	$("#addname").val();
  if(name==null || name ==""){
	  alert("name不能为空");
	  return;
  }
  var logopic=	$("#titleimgshow").attr("src");
//  if(logopic==null || logopic ==""){
//	  alert("logopic不能为空");
//	  return;
//  }
  var bannerpic=	$("#titleimgshowadimg").attr("src");
//  if(bannerpic==null || bannerpic ==""){
//	  alert("bannerpic不能为空");
//	  return;
//  }
  
  var mainadminsstr=$.trim($("#addmainadmins").val());
//  if(mainadminsstr==null ||mainadminsstr ==""){
//	  alert("mainadminsstr不能为空");
//	  return;
//  }
  var viceadminsstr=$.trim($("#addviceadmins").val());
  
  var des=	$("#adddes").val();
//  if(des==null ||des ==""){
//	  alert("des不能为空");
//	  return;
//  }
  var type= $("input[name='addtype']:checked").val();
  if(type==null ||type ==""){
	  alert("type不能为空");
	  return;
  }
 
  var datas =
	{
		"tribalid" : tribalid,
		"addtime" : addtime,
		"oldtype" : oldtype,
		"name" : name,
		"logopic" : logopic,
		"bannerpic" : bannerpic,
		"mainadminsstr" : mainadminsstr,
		"viceadminsstr":viceadminsstr,
		"des" : des,
		"type" : type
		
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../tribal/insertOrUpdateTribal",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("修改成功");
		    	  $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
		    	$ ("#page-wrapper").load ("/tribal/triballist?page=1&limit=20&order=0&tribaltype="+tribaltype);
		    }else{
		    	alert("修改失败");
		    }
		  
	    }
	});
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
function titleimgonclick(a){
	$("#hiddenValue").val(a);
}

function deltribalfromhot(tribalid){
	if (confirm("真的要把此部落从热门删除么？此操作不可逆")){	
		var datas =
		{
			"tribalid" : tribalid
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../tribal/deltribalfromhot',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data != "" && data != null)
			    {
				    
				    if (data == "success")
				    {
					    alert ("操作成功");
					    $ ("#page-wrapper").load ("/tribal/triballist?page=1&limit=20&order=0&tribaltype=100"); 
				    }else{
				    	alert ("操作失败");
				    	 
				    }
			    }
			    
		    },
		 error : function(data){
			 alert ("操作失败");
			
		 }
		});
		
	  }
}
function addhottribal(tribalid){
	$("#addhot_tribalid").val(tribalid);
}
function addhottribalConfirm(){
	var tribalid=$("#addhot_tribalid").val();
	var score=$("#addscore").val();
	if(score==null || score==""){
		alert("score不能为空");
		return ;
	}
	var datas =
	{
		"tribalid" : tribalid,
		"score" : score
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../tribal/addhottribal',
	    data : datas,
	    success : function (data)
	    {
		    
		    if (data != "" && data != null)
		    {
			    
			    if (data == "success")
			    {
				    alert ("操作成功");
				   
			    }else{
			    	alert ("操作失败");
			    	 
			    }
		    }
		    
	    },
	 error : function(data){
		 alert ("操作失败");
		
	 }
	});
}
function changgeOrderindex(tribalid,orderindex){
	$("#addorderindex_tribalid").val(tribalid);
	$("#addorderindex").val(orderindex);
}
function changgeOrderindexConfirm(){
	var tribalid=$("#addorderindex_tribalid").val();
	var type=$("#tribaltypeSelect").val();;
	var orderindex=$("#addorderindex").val();
	if(orderindex==null || orderindex==""){
		alert("orderindex不能为空");
		return ;
	}
	var datas =
	{
			"tribalid" : tribalid,
			"type" : type,
			"orderindex" : orderindex
	}
	
	$.ajax (
			{
				type : "POST",
				url : '../tribal/changgeOrderindex',
				data : datas,
				success : function (data)
				{
					
					if (data != "" && data != null)
					{
						
						if (data == "success")
						{
							alert ("操作成功");
							 $ ("#page-wrapper").load ("/tribal/triballist?page=1&limit=20&order=0&tribaltype="+type); 
						}else{
							alert ("操作失败");
							
						}
					}
					
				},
				error : function(data){
					alert ("操作失败");
					
				}
			});
}
function  updateVersion(versionname){
	if(confirm("真的要更新版本么？此操作不可逆")){
		 $.ajax (
					{
					    type : "POST",
					    url : '../tribal/sysTribalRedis',
					    success : function (data)
					    {
						 
					    	 if (data=="success")
							    {
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
							    	
					           }else{
					        	   alert("更新失败"); 
					           }
					    }
			});
		
		
	}
}
