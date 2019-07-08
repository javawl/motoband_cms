function editad(adid){
	var datas =
	{
		"adid" : adid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/editAdvertising",
	    data : datas,
	    success : function (data)
	    {
		   if(data!=null && data !=''){
			   var datajson = eval("("+data+")");
				$("#adid").val(datajson.adid);
				$("#title").val(datajson.title);
				$("#subtitle").val(datajson.subtitle);
				$("#des").val(datajson.des);
				$("input[name='adtype'][value='"+datajson.adtype+"']").attr("checked",true);
				$("input[name='adheadtype'][value='"+datajson.adheadtype+"']").attr("checked",true);
				$("input[name='state'][value='"+datajson.state+"']").attr("checked",true);
				
		   }
		    
	    }
	});
}

function deletead(adid){
	$("#delAdid").val(adid);
}
function addimgs(adid){
	$("#hiddenValue").val("1");
	$("#addimgsAdid").val(adid);
//	var linktype=	$("#imageSelectType").val();
	$("#imageSelectType option").removeAttr("selected");
	$("#div2").hide();
	$("#div3").hide();
	$("#div4").hide();
	$("#div5").hide();
	$("#div6").hide();
	$("#div7").hide();
	$("#div1").show();
	
	$("#inputUrlnid").val("");
	$("#inputUrlkeyword").val("");
	$("#inputUrllinkurl").val("");
	$("#inputUrlminiprogramid").val("");
	$("#inputUrlgpid").val("");
	$("#inputUrlsecondcarid").val("");
	$("#inputUrlbuserid").val("");
	
	$("#titleimgshow").attr("src","");
	$("#orderindex").val("");
	$("#titleimg").val("");
}
function editConfirm(){
	var adid= $("#adid").val();
	var title= $("#title").val();
	var adtype= $("input[name='adtype']:checked").val();
	var adheadtype= $("input[name='adheadtype']:checked").val();
	var subtitle= $("#subtitle").val();
	var des= $("#des").val();
	var state= $("input[name='state']:checked").val();
	
	var order= $("#triangleHidden").val();
	var limit= $("#pageSizeSelect").val();
	var oldadtype= $("#poolSelect").val();
	if(order==''||order == null){
		order=1;
	}
	
	
	if (title == null || title == "")
	{
		alert ("标题不能为空");
		return false;
	}

	var datas =
	{
		"adid" : adid,
		"title":title,
		"adtype":adtype,
		"subtitle":subtitle,
		"des":des,
		"state": state,
		"oldadtype":oldadtype,
		"order":order,
		"adheadtype":adheadtype
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/updateAdvertising",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("修改成功");
		    	$ ("#page-wrapper").load ("/admanage/advertisinglist?page=1&limit=20&order="+state+"&adtype="+adtype);
		    }
		    
	    }
	});
}
function addAd(){
	 $("#addadtitle").val("");
	 $("input[name='addadtype']").removeAttr('checked');
	 $("input[name='addadheadtype']").removeAttr('checked');
	 $("#addstarttime").val("");
	 $("#addendtime").val("");
	 $("input[name='addstate']").removeAttr('checked');
}
function addConfirm(){
	var title= $("#addtitle").val();
	var subtitle= $("#addsubtitle").val();
	var des= $("#adddes").val();
	var adtype= $("input[name='addadtype']:checked").val();
	var adheadtype= $("input[name='addadheadtype']:checked").val();
	var state= $("input[name='addstate']:checked").val();
	if (title == null || title == "")
	{
		alert ("标题不能为空");
		return false;
	}
	if (adtype == null || adtype == "")
	{
		alert ("adtype不能为空");
		return false;
	}
	if (state == null ||state == "")
	{
		alert ("state不能为空");
		return false;
	}
	
	var datas =
	{
		"title":title,
		"adtype":adtype,
		"subtitle":subtitle,
		"des":des,
		"adheadtype": adheadtype,	
		"state": state	
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/addAdvertising",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("添加成功");
		    	$ ("#page-wrapper").load ("/admanage/advertisinglist?page=1&limit=20&order="+state+"&adtype="+adtype);
		    }else{
		    	alert("添加失败");
		    }
		    
	    }
	});
}
function delConfirm(){
	var adid= $("#delAdid").val();
	var datas =
	{
		"adid" : adid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/delAd",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("删除成功");
		    	$ ("#page-wrapper").load ("/admanage/adlist?page=1&limit=20&order=1");
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
function imgTypeChange(obj){
	$(obj).next("div").find("div").each(function(){
		$(this).hide();
	});
	 $("#inputUrlnid").val("");
	 $("#inputUrlkeyword").val("");
	 $("#inputUrllinkurl").val("");
	 $("#inputUrlminiprogramid").val("");
	 $("#inputUrlgpid").val("");
	 $("#inputUrlsecondcarid").val("");
	 $("#inputUrlbuserid").val("");
	
	switch (parseInt(obj.value)){
	  case 1:
		  $("#div1").show();
	    break;
	  case 2:
	  case 3:
		  $("#div2").show();
	    break;
	  case 4:
	  case 5:
	  case 6:
		  $("#div3").show();
	    break;
	  case 7:
		  $("#div3").show();
		  $("#div4").show();
		    break;
	  case 8:
		  $("#div5").show();
		    break;
	  case 9:
		  $("#div6").show();
		    break;
	  case 10:
		  $("#div7").show();
		    break;
	}
	
	
}
function imgTypeChangeadimg(obj){
	$(obj).next("div").find("div").each(function(){
		$(this).hide();
	});
	$("#inputUrlnidadimg").val("");
	 $("#inputUrlkeywordadimg").val("");
	 $("#inputUrllinkurladimg").val("");
	 $("#inputUrlminiprogramidadimg").val("");
	 $("#inputUrlgpidadimg").val("");
	 $("#inputUrlsecondcaridadimg").val("");
	 $("#inputUrlbuseridadimg").val("");
	
	switch (parseInt(obj.value)){
	  case 1:
		  $("#divadimg1").show();
	    break;
	  case 2:
	  case 3:
		  $("#divadimg2").show();
	    break;
	  case 4:
	  case 5:
	  case 6:
		  $("#divadimg3").show();
	    break;
	  case 7:
		  $("#divadimg3").show();
		  $("#divadimg4").show();
		    break;
	  case 8:
		  $("#divadimg5").show();
		    break;
	  case 9:
		  $("#divadimg6").show();
		    break;
	  case 10:
		  $("#divadimg7").show();
		    break;
	}
	
}
function addImgConfirm(){
  var adid=	$("#addimgsAdid").val();
  var linktype=	$("#imageSelectType").val();
  switch (parseInt(linktype)){
  case 1:
	  var nid=$.trim($("#inputUrlnid").val());
	  if(nid==null || nid ==""){
		  alert("nid不能为空");
		  return;
	  }
    break;
  case 2:
  case 3:
	  var keyword=$.trim($("#inputUrlkeyword").val());
	  if(keyword==null || keyword ==""){
		  alert("keyword不能为空");
		  return;
	  }
    break;
  case 4:
  case 5:
  case 6:
	  var linkurl=$.trim($("#inputUrllinkurl").val());
	  if(linkurl==null || linkurl ==""){
		  alert("linkurl不能为空");
		  return;
	  }
    break;
  case 7:
	  var miniprogramid=$.trim($("#inputUrlminiprogramid").val());
	  if(miniprogramid==null || miniprogramid ==""){
		  alert("miniprogramid不能为空");
		  return;
	  }
    break;
  case 8:
	  var gpid=$.trim($("#inputUrlgpid").val());
	  if(gpid==null || gpid ==""){
		  alert("gpid不能为空");
		  return;
	  }
    break;
    
  case 9:
	  var secondcarid=$.trim($("#inputUrlsecondcarid").val());
	  if (secondcarid == null || secondcarid == ""){
		  alert ("secondcarid不能为空");
		  return false;
	  }
    break;
  case 10:
	  var buserid=$.trim($("#inputUrlbuserid").val());
	  if (buserid == null || buserid == ""){
		  alert ("buserid不能为空");
		  return false;
	  }
	  break;
  }
  var imgurl=	$("#titleimgshow").attr("src");
  if(imgurl==null || imgurl ==""){
	  alert("imgurl不能为空");
	  return;
  }
  var orderindex=	$.trim($("#orderindex").val());
  if(orderindex==null ||orderindex ==""){
	  alert("orderindex不能为空");
	  return;
  }
  var datas =
	{
		"adid" : adid,
		"linktype" : linktype,
		"imgurl" : imgurl,
		"linkurl" : linkurl,
		"gpid" : gpid,
		"nid" : nid,
		"keyword" : keyword,
		"secondcarid" : secondcarid,
		"buserid":buserid,
		"orderindex" : orderindex,
		"miniprogramid" : miniprogramid
		
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/insertAdvertisingImg",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("添加图片成功");
		    }
		    
	    }
	});
}
//function initUploadForm ()
//{
//	// 请将以下获取签名的链接换成您部署好的服务端http url
//	// 建议通过业务登陆态检查来增强安全性，避免签名被非法获取
//	$.getJSON ('../boxmanage/uploadBoxImg', function (data)
//	{
//		
//		var sign = data.sign, url = data.url + '?sign=' + encodeURIComponent (sign);
//		
//		var options =
//		{
//		    type : 'post',
//		    url : url,
//		    dataType : 'json',
//		    success : function (ret)
//		    {
//			    $ ('#downloadUrl').html (ret.data.download_url);
//			    $ ('#fileid').text (ret.data.fileid);
//			    $ ('#url').text (ret.data.url);
//			    $ ('#downloadRet').show ();
//				var str="<div class='row'><div class='col-lg-4' id='"+ret.data.fileid+
//				"'onclick=\"javascript:selectImg('"+ret.data.fileid+
//				"');\""+"><div class='panel panel-default'><div class='panel-body' style='text-align: center;'>"+
//							"<img src='"+ret.data.download_url+"' width='100' height='100'></div><div class='panel-footer center' style='text-align: center;'>"+
//							"<input type='button' value='选择' class='btn btn-info'></div></div>"+
//					"<div class='selected_mask' name='selectdiv' style='display: none'>"+
//						"<div class='selected_mask_inner'></div>"+
//						"<div class='selected_mask_icon'></div></div></div>";
//				$('#imglistdiv').append(str);
//			    
//			    var img_guid = ret.data.fileid;
//			    var img_url = ret.data.download_url;
//			    var img_opurl = ret.data.url;
//			    var datas =
//			    {
//			        "img_guid" : img_guid,
//			        "img_url" : img_url,
//			        "img_opurl" : img_opurl
//			    }
//			    $.ajax (
//			    {
//			        type : "POST",
//			        url : "../boxmanage/addNewBoxImg",
//			        data : datas,
//			        success : function (data)
//			        {
//				        if (data != "" && data != null)
//				        {
//					        alert ("添加图片资源成功！");
//					        initUploadForm();
//				        }
//				        
//			        }
//			    });
//		    },
//		    error : function (ret)
//		    {
//			    alert (ret.responseText);
//		    }
//		};
//		
//		// pass options to ajaxForm
//		//$ ('#uploadForm').ajaxForm (options);
//	});
//}
//function showImgModel ()
//{
//	if ($ ('#ImgModel').css ("display") == "none")
//	{
//		$ ('#ImgModel').css ("display", "block");
//	}
//	else
//	{
//		$ ('#ImgModel').css ("display", "none");
//	}
//}
//function cleanFontStyle ()
//{
//	$ ("#editor").find ("span").css ("font-family",
//	        "'Helvetica Neue',Helvetica,'Hiragino Sans GB','Microsoft YaHei',Arial,sans-serif");
//}
//function selectImg (img_guid)
//{
//	if ($ ('#' + img_guid).hasClass ('selected'))
//	{
//		$ ('#' + img_guid).removeClass ('selected');
//		$ ('#' + img_guid).find ("div[name='selectdiv']").css ("display", "none");
//	}
//	else
//	{
//		$ ('#' + img_guid).addClass ('selected');
//		$ ('#' + img_guid).find ("div[name='selectdiv']").css ("display", "block");
//	}
//	
//}
//function insertImg ()
//{
//	var string = "";
//	imglist = new Array ();
//	for (var i = 0; i < $ ('.selected').length; i++)
//	{
//		string = $ ('.selected').eq (0).find ("img").attr ("src");
//		imglist[i] = $ ('.selected').eq (i).find ("img").attr ("src");
//	}
//	$ ("#remoteUrl").val (string);
//	$ ('#selectImgModel').css ("display", "none");
//	$ ('.selected').removeClass ("selected");
//	$ ('body').find ("div[name='selectdiv']").css ("display", "none");
//	
//}

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
		
	}else{
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
//function XX_addimgdiv (json)
//{
//	var str = "<div class='col-lg-3' id='XX_"
//	        + json.img_guid
//	        + "' onclick=\"javascript:XX_selectImg('XX_"
//	        + json.img_guid
//	        + "');\"><div class='panel panel-default'>"
//	        + "<div class='panel-body' style='text-align: center;'><img src='"
//	        + json.img_url
//	        + "' width='100' height='100'></div>"
//	        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>"
//	        + "<input class='form-control' placeholder='图片名' name='ImgName' value='"
//	        + json.img_name
//	        + "' disabled='disabled'></div>"
//	        + "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
//	        + "<div class='selected_mask' name='XX_selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
//	        + "<div class='selected_mask_icon'></div></div></div>";
//	$ ("#XX_groupimglist").append (str);
//}
//function XX_showgroupimg (XX_groupid)
//{
//	var groupid = XX_groupid.replace ("XX_", "");
//	var datas =
//	{
//		"group_guid" : groupid,
//	}
//	$.ajax (
//	{
//	    type : "POST",
//	    url : "../boxmanage/showgroupimg",
//	    data : datas,
//	    success : function (data)
//	    {
//		    if (data != "" && data != null)
//		    {
//			    var json = eval ("(" + data + ")");
//			    $ ("#XX_groupimglist").html ("");
//			    for ( var i in json)
//			    {
//				    XX_addimgdiv (json[i]);
//			    }
//		    }
//		    
//	    }
//	});
//}
//function addimgdiv (json)
//{
//	var str = "<div class='col-lg-3' id='"
//	        + json.img_guid
//	        + "' onclick=\"javascript:selectImg('"
//	        + json.img_guid
//	        + "');\"><div class='panel panel-default'>"
//	        + "<div class='panel-body' style='text-align: center;'><img src='"
//	        + json.img_url
//	        + "' width='100' height='100'></div>"
//	        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 30px;'>"
//	        + "<input class='form-control' placeholder='图片名' name='ImgName' value='"
//	        + json.img_name
//	        + "' disabled='disabled'></div>"
//	        + "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
//	        + "<div class='selected_mask' name='selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
//	        + "<div class='selected_mask_icon'></div></div></div>";
//	$ ("#groupimglist").append (str);
//}
//function showgroupimg (groupid)
//{
//	var datas =
//	{
//		"group_guid" : groupid,
//	}
//	$.ajax (
//	{
//	    type : "POST",
//	    url : "../boxmanage/showgroupimg",
//	    data : datas,
//	    success : function (data)
//	    {
//		    if (data != "" && data != null)
//		    {
//			    var json = eval ("(" + data + ")");
//			    $ ("#groupimglist").html ("");
//			    for ( var i in json)
//			    {
//				    addimgdiv (json[i]);
//			    }
//		    }
//		    
//	    }
//	});
//}
//$ ('#grouplist > div').on ("click", function ()
//		{
//			showgroupimg ($ (this).attr ("id"));
//			$ ("#nowgroupid").val ($ (this).attr ("id"));
//			$ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
//			$ (this).addClass ('groupaction');
//			
//		});
//		$ ('#XX_grouplist>div').on ("click", function ()
//		{
//			XX_showgroupimg ($ (this).attr ("id"));
//			$ ("#XX_nowgroupid").val ($ (this).attr ("id"));
//			$ (this).parent ("#XX_grouplist").find ("div").removeClass ('groupaction');
//			$ (this).addClass ('groupaction');
//			
//		});
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

function openTable(adid){

	var datas =
	{
		"adid" : adid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/getAdvertisingimgByadid",
	    data : datas,
	    success : function (data)
	    {  
	      var str="<table  borderColor='#7B7B7B' cellPadding='1' width='1000px'  border='1' bgcolor='#fffbec' align='center' ><tr>"
			+"<th style='text-align:center;'>adimgid</th>"
			+"<th style='text-align:center;'>adid</th>"
			+"<th style='text-align:center;'>linktype</th>"
			+"<th style='text-align:center;'>imgurl</th>"
			+"<th style='text-align:center;'>linkurl</th>"
			+"<th style='text-align:center;'>gpid</th>"
			+"<th style='text-align:center;'>nid</th>"
			+"<th style='text-align:center;'>keyword</th>"
			+"<th style='text-align:center;'>secondcarid</th>"
			+"<th style='text-align:center;'>miniprogramid</th>"
			+"<th style='text-align:center;'>buserid</th>"
			+"<th style='text-align:center;'>orderindex</th>"
			+"<th style='text-align:center;'>operate</th>"
			+"</tr>";
		   if(data!=null && data !=''){
			   var datajson = eval("("+data+")");
			 
			   for(var i=0 ; i<datajson.length;i++){
				 
			str = str+"<tr>"
			+"<td style='text-align:center;'>"+datajson[i].adimgid+"</td>"
			+"<td style='text-align:center;'>"+datajson[i].adid+"</td>";
			var linktype=datajson[i].linktype;
			switch(parseInt(linktype)){
			case 1:
				str+="<td style='text-align:center;'>动态</td>";
				break;
			case 2:
				str+="<td style='text-align:center;'>话题</td>";
				break;
			case 3:
				str+="<td style='text-align:center;'>问答</td>";
				break;
			case 4:
				str+="<td style='text-align:center;'>有赞</td>";
				break;
			case 5:
				str+="<td style='text-align:center;'>内部链接 </td>";
				break;
			case 6:
				str+="<td style='text-align:center;'>外部链接</td>";
				break;
			case 7:
				str+="<td style='text-align:center;'>小程序</td>";
				break;
			case 8:
				str+="<td style='text-align:center;'>motogp</td>";
				break;
			case 9:
				str+="<td style='text-align:center;'>二手车</td>";
				break;
			case 10:
				str+="<td style='text-align:center;'>本地商家</td>";
				break;
			
			}
			
			str+="<td style='text-align:center;'><img width='100px' height='100px' src='"+datajson[i].imgurl+"'></td>";
			if(typeof (datajson[i].linkurl) != 'undefined'){
				str = str+"<td style='text-align:center;'><a href=\"javascript:toNewOpenPage('"+datajson[i].linkurl+"')\">"+datajson[i].linkurl+"</a></td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(datajson[i].gpid !="0"){
				str = str+"<td style='text-align:center;'>"+datajson[i].gpid+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(typeof (datajson[i].nid) != 'undefined'){
				str = str+"<td style='text-align:center;'>"+datajson[i].nid+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(typeof (datajson[i].keyword) != 'undefined'){
				str = str+"<td style='text-align:center;'>"+datajson[i].keyword+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(typeof (datajson[i].secondcarid) != 'undefined'){
				str = str+"<td style='text-align:center;'>"+datajson[i].secondcarid+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(typeof (datajson[i].miniprogramid) != 'undefined'){
				str = str+"<td style='text-align:center;'>"+datajson[i].miniprogramid+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			if(typeof (datajson[i].buserid) != 'undefined'){
				str = str+"<td style='text-align:center;'>"+datajson[i].buserid+"</td>";
			}else{
				str = str+"<td style='text-align:center;'>无</td>";
			}
			
			str=str+"<td style='text-align:center;'>"+datajson[i].orderindex+"</td>"
			+"<td  style='text-align:center;'>"
			+"<button type='button' class='btn btn-success' data-toggle='modal'"
			+"  data-target='#editadimg' onclick=\"javascript:editadimg('"+datajson[i].adimgid+"')\">编辑</button>"
			+"<button type='button' class='btn btn-danger' data-toggle='modal'"
			+" data-target='#deleteadimg'"
			+" onclick=\"javascript:deleteadimg('"+datajson[i].adimgid+"','"+datajson[i].adid+"')\">删除</button>"
			+"</td>"
			+" </tr>";
			   }
			   str=str+"</table>";
			  $("#trHiddenDiv"+adid).attr("display","block");

			   $("#trHiddenDiv"+adid).append (str);	
		   }
		    
	    }
	});
}
function trClickAd(adid){
	if($("#trHiddenDiv"+adid).attr("display")=='block'){
		  $("#trHiddenDiv"+adid).attr("display","none");
		  $("#trHiddenDiv"+adid).empty();
		  return;
	}
	openTable(adid);
}

function editadimg(adimgid){
	$("#hiddenValue").val("2");
	 var str="";
	  var datas =
		{
			"adimgid" :adimgid
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../admanage/getAdvertisingimgByadImgid",
		    data : datas,
		    success : function (data)
		    {
			    if (data !=''&& data !=null)
			    {
			    	var datajson = eval("("+data+")");
			    	$("#adimgidadimg").val(datajson.adimgid);
			    	$("#adidadimg").val(datajson.adid);
			    	$("#imageSelectTypeadimg").val(datajson.linktype);
			    	for(var i=1 ;i<=7;i++){
			    		 $("#divadimg"+i).hide();
			    	}
			    	switch (parseInt(datajson.linktype)){
			    	  case 1:
			    		  $("#divadimg1").show();
			    		  $("#inputUrlnidadimg").val(datajson.nid);
			    	    break;
			    	  case 2:
			    	  case 3:
			    		  $("#divadimg2").show();
			    		  $("#inputUrlkeywordadimg").val(datajson.keyword);
			    	    break;
			    	  case 4:
			    	  case 5:
			    	  case 6:
			    		  $("#divadimg3").show();
			    		  $("#inputUrllinkurladimg").val(datajson.linkurl);
			    	    break;
			    	  case 7:
			    		  $("#divadimg3").show();
			    		  $("#inputUrllinkurladimg").val(datajson.linkurl);
			    		  $("#divadimg4").show();
			    		  $("#inputUrlminiprogramidadimg").val(datajson.miniprogramid);
			    		    break;
			    	  case 8:
			    		  $("#divadimg5").show();
			    		  $("#inputUrlgpidadimg").val(datajson.gpid);
			    		    break;
			    	  case 9:
			    		  $("#divadimg6").show();
			    		  $("#inputUrlsecondcaridadimg").val(datajson.secondcarid);
			    		    break;
			    	  case 10:
			    		  $("#divadimg7").show();
			    		  $("#inputUrlbuseridadimg").val(datajson.buserid);
			    		    break;
			    	    
			    	  }
                    $("#titleimgadimg").val(datajson.imgurl);
			    	$("#titleimgshowadimg").attr("src",datajson.imgurl)
			    	$("#orderindexadimg").val(datajson.orderindex);
			    }
			    
		    }
		});
}
function deleteadimg(adimgid,adid){
	$("#delAdidimg").val(adimgid);
	$("#delAdidimgAdid").val(adid);
}
function editAdimgConfirm(){
	 var adimgid=	$("#adimgidadimg").val();
	 var adid=	$("#adidadimg").val();
	  var linktype=	$("#imageSelectTypeadimg").val();
	  switch (parseInt(linktype)){
	  case 1:
		  var nid=$.trim($("#inputUrlnidadimg").val());
		  if(nid==null || nid ==""){
			  alert("nid不能为空");
			  return;
		  }
	    break;
	  case 2:
	  case 3:
		  var keyword=$.trim($("#inputUrlkeywordadimg").val());
		  if(keyword==null || keyword ==""){
			  alert("keyword不能为空");
			  return;
		  }
	    break;
	  case 4:
	  case 5:
	  case 6:
		  var linkurl=$.trim($("#inputUrllinkurladimg").val());
		  if(linkurl==null || linkurl ==""){
			  alert("linkurl不能为空");
			  return;
		  }
	    break;
	  case 7:
		  var miniprogramid=$.trim($("#inputUrlminiprogramidadimg").val());
		  if(miniprogramid==null || miniprogramid ==""){
			  alert("miniprogramid不能为空");
			  return;
		  }
	    break;
	  case 8:
		  var gpid=$.trim($("#inputUrlgpidadimg").val());
		  if(gpid==null || gpid ==""){
			  alert("gpid不能为空");
			  return;
		  }
	    break;
	    
	  case 9:
		  var secondcarid=$.trim($("#inputUrlsecondcaridadimg").val());
		  if (secondcarid == null || secondcarid == ""){
			  alert ("secondcarid不能为空");
			  return false;
		  }
	    break;
	  case 10:
		  var buserid=$.trim($("#inputUrlbuseridadimg").val());
		  if (buserid == null || buserid == ""){
			  alert ("buserid不能为空");
			  return false;
		  }
		  break;
	  }
	  var imgurl=	$("#titleimgshowadimg").attr("src");
	  var orderindex=	$.trim($("#orderindexadimg").val());
	  
	  if (adid == null || adid == "")
		{
			alert ("adid不能为空");
			return false;
		}
		if (imgurl == null || imgurl == "")
		{
			alert ("imgurl不能为空");
			return false;
		}
		if (orderindex == null || orderindex== "")
		{
			alert ("orderindex不能为空");
			return false;
		}

	  
	  var datas =
		{
			"adid" : adid,
			"adimgid" : adimgid,
			"linktype" : linktype,
			"imgurl" : imgurl,
			"linkurl" : linkurl,
			"gpid" : gpid,
			"nid" : nid,
			"keyword" : keyword,
			"secondcarid" : secondcarid,
			"buserid" : buserid,
			"orderindex" : orderindex,
			"miniprogramid" : miniprogramid
			
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../admanage/updateAdvertisingImg",
		    data : datas,
		    success : function (data)
		    {
			    if (data =='success')
			    {
			    	alert("编辑图片成功");
			    	$("#trHiddenDiv"+adid).empty();
			    	openTable(adid);
			    }
			    
		    }
		});
}
function delAdimgConfirm(){
	var adimgid= $("#delAdidimg").val();
	var adid= $("#delAdidimgAdid").val();
	var datas =
	{
		"adimgid" : adimgid,
		"adid" : adid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../admanage/delAdvertisingimg",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("删除成功");
		    	$("#trHiddenDiv"+adid).empty();
		    	openTable(adid);
		    }
		    
	    }
	});
}
function toNewOpenPage(url){
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