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
	        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>"
	        + "<textarea   rows='2' style='word-break: break-all;'   class='form-control' placeholder='图片名' name='ImgName' value='"
	        + json.img_name
	        + "' disabled='disabled'>"+json.img_name+"</textarea></div>"
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
	        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>"
	        + "<textarea  rows='2' style='word-break: break-all;'  class='form-control' placeholder='图片名' name='ImgName' value='"
	        + json.img_name
	        + "' disabled='disabled'>"+json.img_name+"</textarea></div>"
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
	$('#titleImgModel').on('hidden.bs.modal', function() {

		$('#insertMallModel').css({'overflow-y':'scroll'});
	});
}

function closetitleImgModel(){
	$('#titleImgModel').on('hidden.bs.modal', function() {

		$('#insertMallModel').css({'overflow-y':'scroll'});
		});
	
}



function set_userlevel(){
	 var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../giftexchangemanage/getUserLevels',
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].level+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_condition").empty();
						  $("#ins_condition").append(str);
					    }
				    }
				});
}

function set_giftlist(){
	 var str="";
	
	 $.ajax (
				{
				    type : "POST",
				    url : '../giftexchangemanage/getGiftList',
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].giftid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_giftid").empty();
						  $("#ins_giftid").append(str);
					    }
				    }
				});
}

function set_style(){
	 var str="";
	 var datas={
			"state":1 
	 }
	 $.ajax (
				{
				    type : "POST",
				    url : '../giftexchangemanage/getBusersBystate',
				    data:datas,
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].userid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_style").empty();
						  $("#ins_style").append(str);
					    }
				    }
				});
}

function select_style_show(){
	var new_selected_val = $("#ins_style").val();
   var str="";
   var mallstyle=$(".mallstyle");
   var flag= false;
 if(mallstyle.length!="0"){
   for(var i=0;i<new_selected_val.length;i++){
   	flag= false;
   	    for(var j=0;j<mallstyle.length;j++){
   	    	if($(".mallstyle").eq(j).attr("name")==new_selected_val[i]){
   	    		flag=true;
    	  		 }
   	   }
   	    if(!flag){
   	    	  var new_selected_text= $("#ins_style [value='"+new_selected_val[i]+"']").text();
   	    	  str+="<span id='style"+new_selected_text+"' class='mallstyle' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+new_selected_text+"')\">×</button></span>";  
   	    }
        }
    }else{
   	for(var i=0;i<new_selected_val.length;i++){ 
		 var new_selected_text= $("#ins_style [value='"+new_selected_val[i]+"']").text();
	     str+="<span id='style"+new_selected_text+"' class='mallstyle' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+new_selected_text+"')\">×</button></span>";  
	    	
   	 }
   }
   $("#style_div").append(str);
}

function deleteStyletext(text){
	if(text.indexOf(".") > 0){
		var textspilt=text.split(".");
		var temp="";
		for(var i=0;i<textspilt.length;i++){
			if(i==textspilt.length-1){
				temp+=textspilt[i];
			}else{
				temp+=textspilt[i]+"\\.";
			}
		}
		text=temp;
	}
	$("#style"+text).remove();
}

function  addgiftexchangeNewpage(userGuid){
	window.open("../giftexchangemanage/addgiftexchangeNewPage?userGuid="+userGuid);
}
function  updategiftexchangeNewpage(userGuid,exchangeid){
	window.open("../giftexchangemanage/updategiftexchangeNewPage?userGuid="+userGuid+"&exchangeid="+exchangeid);
}
function addgiftexchangeConfirm(){
	 $("#back").css("display","");
	var name=$("#ins_name").val();
	var des=$("#ins_des").val();
	var condition=$("#ins_condition").val();
	var scope=$("#ins_scope").val();
	var sumcount=$("#ins_sumcount").val();
	var validitystarttime=$("#ins_validitystarttime").val();
	var validityendtime=$("#ins_validityendtime").val();
	var giftid=$("#ins_giftid").val();
	var giftcount=$("#ins_giftcount").val();
	var remind=$("#ins_remind").val();
	var pic=$("#titleimgshow").attr("src");
	 var state= $("input[name='ins_state']:checked").val();
	if(name==null || name==""){
		alert("name不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(des==null || des==""){
		alert("des不能为空");
		$("#back").css("display","none");
		return false;	
	}
	if(condition==null || condition==""){
		alert("condition不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(scope==null || scope==""){
		alert("scope不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(sumcount==null || sumcount==""){
		alert("sumcount不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(validitystarttime==null || validitystarttime==""){
		alert("validitystarttime不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(validityendtime==null || validityendtime==""){
		alert("validityendtime不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(giftid==null || giftid==""){
		alert("giftid不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(giftcount==null || giftcount==""){
		alert("giftcount不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(remind==null || remind==""){
		alert("remind不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(pic==null || pic==""){
		alert("pic不能为空");
		$("#back").css("display","none");
		return false;	
	}
	if(state==null || state==""){
		alert("state不能为空");
		$("#back").css("display","none");
		return false;	
	}
	
	var buserids="";
	 var mallstyle=$(".mallstyle");
	 if(mallstyle.length!="0"){
		   	for(var j=0;j<mallstyle.length;j++){
		   		if(j==mallstyle.length-1){
		   			buserids+=$(".mallstyle").eq(j).attr("name");
		   		}else{
		   			buserids+=$(".mallstyle").eq(j).attr("name")+",";
		   		}
		   	    	
		   	 }
	}else{
		alert("商家不能为空");
		$("#back").css("display","none");
		return false;
	}
	 
	 var datas={
			"name":name,
			"des":des,
			"condition":condition,
			"scope":scope,
			"sumcount":sumcount,
			"validitystarttime":validitystarttime,
			"validityendtime":validityendtime,
			"buserids":buserids,
			"giftid":giftid,
			"giftcount":giftcount,
			"remind":remind,
			"pic":pic,
			"state":state
	 }
	 $.ajax (
				{
				    type : "POST",
				    url : '../giftexchangemanage/addGiftexchange',
				    data:datas,
				    success : function (data)
				    {
					    if (data=="success")
					    {
					    	alert("添加成功");
					    	 $("#back").css("display","none");
		    			     window.opener=null;
						     window.open('','_self');
						     window.close();
					    }else{
					    	alert("添加失败");
					    	 $("#back").css("display","none");
					    }
				    }
				});
}
function updategiftexchange(exchangeid){
	     var stylestr="";
		 var datas={
				"exchangeid":exchangeid
		 }
		 $.ajax (
					{
					    type : "POST",
					    url : '../giftexchangemanage/getGiftexchangeByid',
					    data:datas,
					    success : function (data)
					    {
						    if (data!=null && data!='')
						    {
						    	var jsondata= eval("("+data+")");
						    	$("#ins_name").val(jsondata.name);
						    	$("#ins_des").val(jsondata.des);
						    	$("#ins_condition").val(jsondata.condition);
						        $("#ins_scope").val(jsondata.scope);
						    	$("#ins_sumcount").val(jsondata.sumcount);
						    	$("#ins_validitystarttime").val(jsondata.validitystarttimeString);
						    	$("#ins_validityendtime").val(jsondata.validityendtimeString);
						    	$("#ins_giftid").val(jsondata.giftid);
						    	$("#ins_giftcount").val(jsondata.giftcount);
						    	$("#ins_remind").val(jsondata.remind);
						    	$("#titleimgshowadimg").attr("src",jsondata.pic);
						    	$("#titleimgadimg").val(jsondata.pic);
						    	$("input[name='ins_state'][value='"+jsondata.state+"']").prop("checked",true);
						    	
						    	  if(jsondata.buserids !=null && jsondata.buserids!=""){
							    	  var styles=jsondata.buserids.split(",");
								      var stylestext=jsondata.busernames.split(",");
								      for(var i=0; i<styles.length;i++){
								    	  stylestr+="<span id='style"+stylestext[i]+"' class='mallstyle' name='"+styles[i]+"'>"+stylestext[i]+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+stylestext[i]+"')\">×</button></span>";  
								      }
								      $("#style_div").empty();
								      $("#style_div").append(stylestr);
							      }
						    }
					    }
					});
}

function updategiftexchangeConfirm(){
	 $("#back").css("display","");
	var exchangeid=$("#hiddenValueExchangeid").val();
	var name=$("#ins_name").val();
	var des=$("#ins_des").val();
	var condition=$("#ins_condition").val();
	var scope=$("#ins_scope").val();
	var sumcount=$("#ins_sumcount").val();
	var validitystarttime=$("#ins_validitystarttime").val();
	var validityendtime=$("#ins_validityendtime").val();
	var giftid=$("#ins_giftid").val();
	var giftcount=$("#ins_giftcount").val();
	var remind=$("#ins_remind").val();
	var pic=$("#titleimgshowadimg").attr("src");
	 var state= $("input[name='ins_state']:checked").val();
	if(name==null || name==""){
		alert("name不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(des==null || des==""){
		alert("des不能为空");
		$("#back").css("display","none");
		return false;	
	}
	if(condition==null || condition==""){
		alert("condition不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(scope==null || scope==""){
		alert("scope不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(sumcount==null || sumcount==""){
		alert("sumcount不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(validitystarttime==null || validitystarttime==""){
		alert("validitystarttime不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(validityendtime==null || validityendtime==""){
		alert("validityendtime不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(giftid==null || giftid==""){
		alert("giftid不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(giftcount==null || giftcount==""){
		alert("giftcount不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(remind==null || remind==""){
		alert("remind不能为空");
		 $("#back").css("display","none");
		return false;	
	}
	if(pic==null || pic==""){
		alert("pic不能为空");
		$("#back").css("display","none");
		return false;	
	}
	if(state==null || state==""){
		alert("state不能为空");
		$("#back").css("display","none");
		return false;	
	}
	
	var buserids="";
	 var mallstyle=$(".mallstyle");
	 if(mallstyle.length!="0"){
		   	for(var j=0;j<mallstyle.length;j++){
		   		if(j==mallstyle.length-1){
		   			buserids+=$(".mallstyle").eq(j).attr("name");
		   		}else{
		   			buserids+=$(".mallstyle").eq(j).attr("name")+",";
		   		}
		   	    	
		   	 }
	}else{
		alert("商家不能为空");
		$("#back").css("display","none");
		return false;
	}
	 
	 var datas={
			"exchangeid":exchangeid,
			"name":name,
			"des":des,
			"condition":condition,
			"scope":scope,
			"sumcount":sumcount,
			"validitystarttime":validitystarttime,
			"validityendtime":validityendtime,
			"buserids":buserids,
			"giftid":giftid,
			"giftcount":giftcount,
			"remind":remind,
			"pic":pic,
			"state":state
	 }
	 $.ajax (
				{
				    type : "POST",
				    url : '../giftexchangemanage/updateGiftexchange',
				    data:datas,
				    success : function (data)
				    {
					    if (data=="success")
					    {
					    	alert("修改成功");
					    	 $("#back").css("display","none");
		    			     window.opener=null;
						     window.open('','_self');
						     window.close();
					    }else{
					    	alert("修改失败");
					    	 $("#back").css("display","none");
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