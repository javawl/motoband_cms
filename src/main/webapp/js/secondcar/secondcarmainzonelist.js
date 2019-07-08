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
function addScmz(){
	$("#addtitle").val("");
	$("#addscmztitle").val("");
	$("#addcity").val("");
	$("#addstyle").val("");
	$("#addkeyword").val("");
	$("#titleimgshow").attr("src","");
	$("#titleimg").val("");
	$("input[name='addstore']").removeAttr('checked');
	$("#addbpid").val("");
	$("#addbrandid").val("");
	$("#addmodelid").val("");
	$("#addminprice").val("");
	$("#addmaxprice").val("");
	$("#addminage").val("");
	$("#addmaxage").val("");
	$("#addminkilometer").val("");
	$("#addmaxkilometer").val("");
	$("input[name='addprocedure']").removeAttr('checked');
	$("input[name='addhasolddrivercomment']").removeAttr('checked');
	$("input[name='addhasofficial']").removeAttr('checked');
	$("input[name='addcreateusertype']").removeAttr('checked');
	$("input[name='addstate']").removeAttr('checked');
	$("#addscmzid").val("");
	$("#addorderindex").val("");
}
function editScmz(scmzid){
	 var str="";
	  var datas =
		{
			"scmzid" :scmzid
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../secondcar/getSecondcarMainZoneById",
		    data : datas,
		    success : function (data)
		    {
			    if (data !=''&& data !=null)
			    {
			    	var datajson = eval("("+data+")");
			    	$("#addtitle").val(datajson.searchsecondcar.title);
			    	$("#addcity").val(datajson.searchsecondcar.city);
			    	$("#addstyle").val(datajson.searchsecondcar.style);
			    	$("#addkeyword").val(datajson.searchsecondcar.keyword);
			    	$("#titleimgshow").attr("src",datajson.picurl);
			    	$("#titleimg").val(datajson.picurl);
			    	$("input[name='addstore'][value='"+datajson.searchsecondcar.store+"']").prop("checked",true);
			    	$("#addbpid").val(datajson.searchsecondcar.bpid);
			    	$("#addbrandid").val(datajson.searchsecondcar.brandid);
			    	$("#addmodelid").val(datajson.searchsecondcar.modelid);
			    	$("#addminprice").val(datajson.searchsecondcar.minprice);
			    	$("#addmaxprice").val(datajson.searchsecondcar.maxprice);
			    	$("#addmaxage").val(datajson.searchsecondcar.maxage);
			    	$("#addminage").val(datajson.searchsecondcar.minage);
			    	$("#addminkilometer").val(datajson.searchsecondcar.minkilometer);
			    	$("#addmaxkilometer").val(datajson.searchsecondcar.maxkilometer);
			    	$("input[name='addprocedure'][value='"+datajson.searchsecondcar.procedure+"']").prop("checked",true);
			    	$("input[name='addhasolddrivercomment'][value='"+datajson.searchsecondcar.hasolddrivercomment+"']").prop("checked",true);
			    	$("input[name='addhasofficial'][value='"+datajson.searchsecondcar.hasofficial+"']").prop("checked",true);
			    	$("input[name='addcreateusertype'][value='"+datajson.searchsecondcar.createusertype+"']").prop("checked",true);
			    	$("input[name='addstate'][value='"+datajson.state+"']").prop("checked",true);
			    	$("#addorderindex").val(datajson.orderindex);
			    	$("#addscmztitle").val(datajson.title);
			    	$("#addscmzid").val(datajson.scmzid);
			    	
			    	
			    }
			    
		    }
		});
}

function editScmzConfirm(){
	var title=	$("#addtitle").val();
	var scmztitle=	$("#addscmztitle").val();
	var city=	$("#addcity").val();
	var style=	$("#addstyle").val();
	var keyword=	$("#addkeyword").val();
	var picurl=	$("#titleimgshow").attr("src");
	var store= $("input[name='addstore']:checked").val();
	var bpid=	$("#addbpid").val();
	var brandid=	$("#addbrandid").val();
	var modelid=	$("#addmodelid").val();
	var minprice=	$("#addminprice").val();
	var maxprice=	$("#addmaxprice").val();
	var maxage=	$("#addmaxage").val();
	var minage=	$("#addminage").val();
	var minkilometer=	$("#addminkilometer").val();
	var maxkilometer=	$("#addmaxkilometer").val();
	var procedure=	$("input[name='addprocedure']:checked").val();
	var hasolddrivercomment=	$("input[name='addhasolddrivercomment']:checked").val();
	var hasofficial=	$("input[name='addhasofficial']:checked").val();
	var createusertype=	$("input[name='addcreateusertype']:checked").val();
	var state=	$("input[name='addstate']:checked").val();
	var orderindex=	$("#addorderindex").val();
	var scmzid=	$("#addscmzid").val();
	
	if(picurl==null || picurl==""){
		alert("图片不能为空");
		return ;
	}
	if(state==null || state==""){
		alert("状态不能为空");
		return ;
	}
	if(scmztitle==null || scmztitle==""){
		alert("专区标题不能为空");
		return ;
	}
	
 
  var datas =
	{
		"title" : title,
		"scmztitle" : scmztitle,
		"city" : city,
		"style" : style,
		"keyword" : keyword,
		"picurl" : picurl,
		"store" : store,
		"bpid" : bpid,
		"brandid":brandid,
		"modelid" : modelid,
		"minprice" : minprice,
		"maxprice" : maxprice,
		"maxage" : maxage,
		"minage" : minage,
		"minkilometer" : minkilometer,
		"maxkilometer" : maxkilometer,
		"procedure" : procedure,
		"hasolddrivercomment" : hasolddrivercomment,
		"hasofficial" : hasofficial,
		"createusertype" : createusertype,
		"state" : state,
		"orderindex" : orderindex,
		"scmzid" : scmzid
		
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../secondcar/insertOrUpdateSecondcarMainZone",
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success')
		    {
		    	alert("修改成功");
		    	 $(".modal-backdrop").remove();
		    	 $("body").removeClass('modal-open');
		    	$ ("#page-wrapper").load ("/secondcar/secondcarmainzonelist?page=1&limit=20&order=0");
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

//function deltribalfromhot(tribalid){
//	if (confirm("真的要把此部落从热门删除么？此操作不可逆")){	
//		var datas =
//		{
//			"tribalid" : tribalid
//		}
//
//		$.ajax (
//		{
//		    type : "POST",
//		    url : '../tribal/deltribalfromhot',
//		    data : datas,
//		    success : function (data)
//		    {
//			    
//			    if (data != "" && data != null)
//			    {
//				    
//				    if (data == "success")
//				    {
//					    alert ("操作成功");
//					    $ ("#page-wrapper").load ("/tribal/triballist?page=1&limit=20&order=0&tribaltype=100"); 
//				    }else{
//				    	alert ("操作失败");
//				    	 
//				    }
//			    }
//			    
//		    },
//		 error : function(data){
//			 alert ("操作失败");
//			
//		 }
//		});
//		
//	  }
//}
/*function addhottribal(tribalid){
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
*/