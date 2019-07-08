
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
function addAdpoolimg(){
	$("#hiddenValue").val("1");
	var linktype=	$("#imageSelectType").val();
	$("#imageSelectType option").removeAttr("selected");
	$("#div"+linktype).hide();
	$("#div0").show();
	$("#inputUrllinkurl").val("");
	$("#titleimgshow").attr("src","");
	$("#orderindex").val("");
	$("#titleimg").val("");
	 $("input[name='addstate']").removeAttr('checked');
	 $("input[name='addpool']").removeAttr('checked');
	 $("#addadpoolimgid").val("");
	 $("#oldadtype").val("");
	 $("#addadtitle").val("");
	 $("#addprice").val("");
	 $("#addaddes").val("");
	
}
function clearAddModel(){
	var linktype=	$("#imageSelectType").val();
	$("#imageSelectType option").removeAttr("selected");
	$("#div"+linktype).hide();
	$("#div0").show();
	$("#inputUrllinkurl").val("");
	$("#titleimgshow").attr("src","");
	$("#orderindex").val("");
	$("#titleimg").val("");
	 $("input[name='addstate']").removeAttr('checked');
	 $("input[name='addpool']").removeAttr('checked');
	 $("#addadpoolimgid").val("");
	 $("#oldadtype").val("");
	 $("#addadtitle").val("");
	 $("#addprice").val("");
	 $("#addaddes").val("");
}
function editAdpoolimg(adpoolimgid){
	$("#hiddenValue").val("1");
	 var str="";
	  var datas =
		{
			"adpoolimgid" :adpoolimgid
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../admanage/getAdpoolimgidById",
		    data : datas,
		    success : function (data)
		    {
			    if (data !=''&& data !=null)
			    {
			    	var datajson = eval("("+data+")");
			    	$("#addadpoolimgid").val(datajson.adpoolimgid);
			    	$("#oldadtype").val(datajson.adtype);
			    	$("#imageSelectType").val(datajson.linktype);
			    	for(var i=0 ;i<=6;i++){
			    		 $("#div"+i).hide();
			    	}
			    	switch (parseInt(datajson.linktype)){
			    	  case 0:
			    		 $("#div"+datajson.linktype).show();
			    		 $("#inputUrllinkurl").val(datajson.linkurl);
			    	    break;
			    	  case 1:
			    		  $.ajax (
			  					{
			  					    type : "POST",
			  					    url : "../admanage/getmotoGp",
			  					    success : function (data)
			  					    {
			  						    if (data !=null && data !='')
			  						    {
			  						    	var datajson1 = eval("("+data+")");
			  						    	for(var i=0 ; i<datajson1.length;i++){
			  						    		if(datajson1[i].gpid==datajson.gpid){
			  						    			str=str+"<option value='"+datajson1[i].gpid+"' selected>"+datajson1[i].title+"</option>";
			  						    		}else{
			  						    		    str=str+"<option value='"+datajson1[i].gpid+"' >"+datajson1[i].title+"</option>";
			  						    		}
			  						    	}
			  						    	$("#inputUrlgpid").empty();
			  						    	$("#inputUrlgpid").append(str);
			  						    }
			  						    
			  					    }
			  					});
			    		 
			    		  $("#div"+datajson.linktype).show();
			    		
			    	    break;
			    	  case 2:
			    		  $.ajax (
			  					{
			  					    type : "POST",
			  					    url : "../admanage/getmotoKeywords",
			  					    success : function (data)
			  					    {
			  						    if (data !=null && data !='')
			  						    {
			  						    	var datajson1 = eval("("+data+")");
			  						    	for(var i=0 ; i<datajson1.length;i++){
			  						    		if($.trim(datajson1[i].keyword)==$.trim(datajson.keyword)){
			  						    			str=str+"<option value='"+$.trim(datajson1[i].keyword)+"' selected>"+datajson1[i].keyword+"</option>";
			  						    		}else{
			  						    			str=str+"<option value='"+$.trim(datajson1[i].keyword)+"' >"+datajson1[i].keyword+"</option>";	
			  						    		}
			  						    		
			  						    	}
			  						    	$("#inputUrlkeyword").empty();
			  						    	$("#inputUrlkeyword").append(str);
			  						    }
			  						    
			  					    }
			  					});
			    		  $("#div"+datajson.linktype).show();
			    	    break;
			    	  case 3:
			    		  $("#div"+datajson.linktype).show();
			    		  $("#inputUrlmallurl").val(datajson.mallurl);
			    	    break;
			    	  case 6:
			    		  $.ajax (
			  					{
			  					    type : "POST",
			  					    url : "../admanage/getmotoBoxid",
			  					    success : function (data)
			  					    {
			  						    if (data !=null && data !='')
			  						    {
			  						    	var datajson1 = eval("("+data+")");
			  						    	for(var i=0 ; i<datajson1.length;i++){
			  						    		if(datajson1[i].boxid==datajson.boxid){
			  						    			str=str+"<option value='"+datajson1[i].boxid+"' selected>"+datajson1[i].boxid+"</option>";	
			  						    		}else{
			  						    			str=str+"<option value='"+datajson1[i].boxid+"' >"+datajson1[i].boxid+"</option>";
			  						    		}
			  						    		
			  						    	}
			  						    	$("#inputUrlboxid").empty();
			  						    	$("#inputUrlboxid").append(str);
			  						    }
			  						    
			  					    }
			  					});
			    		  $("#div"+datajson.linktype).show();
			    	    break;
			    	  case 7:
			    		  $("#div"+datajson.linktype).show();
			    		  $("#inputUrlsecondcarid").val(datajson.secondcarid);
			    	    break;
			    	  }
                    $("#titleimg").val(datajson.imgurl);
			    	$("#titleimgshow").attr("src",datajson.imgurl)
			    	$("#addprice").val(datajson.price);
			    	$("#addadtitle").val(datajson.adtitle);
			    	$("#addaddes").val(datajson.addes);
			    	$("#orderindex").val(datajson.orderindex);
			    	$("input[name='addstate'][value='"+datajson.state+"']").prop("checked",true);
			    	$("input[name='addpool'][value='"+datajson.adtype+"']").prop("checked",true);
			    }
			    
		    }
		});
}
function imgTypeChange(obj){
	$(obj).next("div").find("div").each(function(){
		$(this).hide();
	});
	var str ="";
	switch (parseInt(obj.value)){
	  case 1:
			$.ajax (
					{
					    type : "POST",
					    url : "../admanage/getmotoGp",
					    success : function (data)
					    {
						    if (data !=null && data !='')
						    {
						    	var datajson = eval("("+data+")");
						    	for(var i=0 ; i<datajson.length;i++){
						    		str=str+"<option value='"+datajson[i].gpid+"' >"+datajson[i].title+"</option>";
						    	}
						    	$("#inputUrlgpid").empty();
						    	$("#inputUrlgpid").append(str);
						    }
						    
					    }
					});
			
	    break;
	  case 2:
		  $.ajax (
					{
					    type : "POST",
					    url : "../admanage/getmotoKeywords",
					    success : function (data)
					    {
						    if (data !=null && data !='')
						    {
						    	var datajson = eval("("+data+")");
						    	for(var i=0 ; i<datajson.length;i++){
						    		str=str+"<option value='"+$.trim(datajson[i].keyword)+"' >"+datajson[i].keyword+"</option>";
						    	}
						    	$("#inputUrlkeyword").empty();
						    	$("#inputUrlkeyword").append(str);
						    }
						    
					    }
					});
	    break;
	  case 6:
		  $.ajax (
					{
					    type : "POST",
					    url : "../admanage/getmotoBoxid",
					    success : function (data)
					    {
						    if (data !=null && data !='')
						    {
						    	var datajson = eval("("+data+")");
						    	for(var i=0 ; i<datajson.length;i++){
						    		str=str+"<option value='"+datajson[i].boxid+"' >"+datajson[i].boxid+"</option>";
						    	}
						    	$("#inputUrlboxid").empty();
						    	$("#inputUrlboxid").append(str);
						    }
						    
					    }
					});
	    break;
	}
	$("#div" + obj.value).show();
}

function editAdpoolimgConfirm(){
  var adpoolimgid=	$("#addadpoolimgid").val();
  var oldadtype=	$("#oldadtype").val();
  
  var limit= $("#pageSizeSelect").val();
  var selectadtype= $("#poolSelect").val();
 
  var linktype=	$("#imageSelectType").val();
  switch (parseInt(linktype)){
  case 0:
	  var linkurl=$.trim($("#inputUrllinkurl").val());
	  if(linkurl==null || linkurl ==""){
		  alert("linkurl不能为空");
		  return;
	  }
    break;
  case 1:
	  var gpid=$.trim($("#inputUrlgpid").val());
	  if(gpid==null || gpid ==""){
		  alert("gpid不能为空");
		  return;
	  }
    break;
  case 2:
	  var keyword=$.trim($("#inputUrlkeyword").val());
	  if(keyword==null || keyword ==""){
		  alert("keyword不能为空");
		  return;
	  }
    break;
  case 3:
	  var mallurl=$.trim($("#inputUrlmallurl").val());
	  if(mallurl==null || mallurl ==""){
		  alert("mallurl不能为空");
		  return;
	  }
    break;
  case 6:
	  var boxid=$.trim($("#inputUrlboxid").val());
	  if(boxid==null || boxid ==""){
		  alert("boxid不能为空");
		  return;
	  }
    break;
  case 7:
	  var secondcarid=$.trim($("#inputUrlsecondcarid").val());
	  if(secondcarid==null || secondcarid ==""){
		  alert("secondcarid不能为空");
		  return;
	  }
	  break;
  }
  var imgurl=	$("#titleimgshow").attr("src");
  if(imgurl==null || imgurl ==""){
	  alert("imgurl不能为空");
	  return;
  }
  var orderindex=$.trim($("#orderindex").val());
  if(orderindex==null ||orderindex ==""){
	  alert("orderindex不能为空");
	  return;
  }
  var state= $("input[name='addstate']:checked").val();
  if(state==null ||state ==""){
	  alert("state不能为空");
	  return;
  }
  var adtype=$("input[name='addpool']:checked").val();
  if(adtype==null ||adtype ==""){
	  alert("adtype不能为空");
	  return;
  }
  var price=	$("#addprice").val();
  if(price==null ||price ==""){
	  alert("price不能为空");
	  return;
  }
  var adtitle=	$("#addadtitle").val();
  if(adtitle==null ||adtitle ==""){
	  alert("adtitle不能为空");
	  return;
  }
  var addes=	$("#addaddes").val();
  if(addes==null ||addes ==""){
	  alert("addes不能为空");
	  return;
  }
  var datas =
	{
		"adpoolimgid" : adpoolimgid,
		"linktype" : linktype,
		"imgurl" : imgurl,
		"linkurl" : linkurl,
		"gpid" : gpid,
		"keyword" : keyword,
		"mallurl" : mallurl,
		"boxid":boxid,
		"orderindex" : orderindex,
		"state" : state,
		"adtype" : adtype,
		"oldadtype" : oldadtype,
		"adtitle" : adtitle,
		"price" : price,
		"addes" : addes
		
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/insertOrUpdateAdpoolImg",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("修改成功");
		    //	clearAddModel();
		    	 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
		    	$ ("#page-wrapper").load ("/admanage/adpoollist?page=1&limit="+limit+"&order=0&adtype="+selectadtype);
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