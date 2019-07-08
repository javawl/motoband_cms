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
			$ ("#titleimgadd").val ();	
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
			$ ("#titleimgadd").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
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
		$ ("#titleimgshowadd").attr ("src", $ ("#titleimgadd").val ());
	}
	
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
//	
//	
////
////if (document.compatMode == "BackCompat"){
////var elementScrollTop=document.body.scrollTop;
////} else {
//// var elementScrollTop=document.documentElement.scrollTop; 
////}
////var t1=t-elementScrollTop;
//
////	$(".datetimepicker").css("top",t1+34+"px");
////	$(".datetimepicker").css("left",l+"px");
//	var da=$(".datetimepicker");
//	console.log(da.offsetTop);
//	console.log(da.offsetLeft);
//	
////	var left=getElementLeft(e);
////	var top=getElementTop(e);
////	console.log(left);
////	console.log(top);
//	console.log(t);
//
//	console.log(l);
//	console.log("----------");
	

}

function getElementLeft(element){
    var actualLeft = element.offsetLeft;
    var current = element.offsetParent;
    while (current !==null){
        actualLeft += (current.offsetLeft+current.clientLeft);
        current = current.offsetParent;
    }
    return actualLeft;
}


function getElementTop(element) {
	var actualTop = element.offsetTop;
	var current = element.offsetParent;
	while (current !== null) {
		actualTop += (current.offsetTop + current.clientTop);
		current = current.offsetParent;
	}
	return actualTop;
}

function lookRanking(page,gpid,type){
	$("#gpuserlist").empty();
	var str="";
	var pageStr="";
	var datas =
	{
		"gpid" : gpid,
		"type" : type,
        "page":page
	}
	 $.ajax (
  			{
  			    type : "POST",
  			    url : "../motobandgpmanage/gpuserRankinglist",
  			    data:datas,
  			    success : function (data)
  			    {
  			    	
  				    if (data !=null && data!='')
  				    {
  					  var jsondata =eval("("+data+")");
  					  for(var i=0;i<jsondata.length;i++){
  						 str+="<tr>";
  						 str+="<td>"+((parseInt(page)-1)*100+1+i)+"</td>";
  						 str+="<td><a  href=\"javascript:gotoMotoUserlist('"+jsondata[i].userid+"')\">"+jsondata[i].userid+"</a></td>";
  						 str+="<td>"+jsondata[i].nickname+"</td>";
  						 if(parseInt(type)==1){
  							 str+="<td>"+jsondata[i].mileageScore+"</td>";
  						 }
  						 if(parseInt(type)==2){
  							 str+="<td>"+jsondata[i].ridetimeScore+"</td>";
  	  						 
  						 }
  						
  						 str+="<td><button type='button' class='btn btn-danger'  onclick=\"javascript:deletegpuser('"+jsondata[i].userid+"','"+gpid+"','"+type+"')\">删除</button></td>";
  						 str+="</tr>";
  					  }
  					 
  					  $("#gpuserlist").append(str);
  					  
  					  var pageSize=Math.ceil(jsondata[0].redisCount/100);
  					  $("#gppageNow").text(page);
  					  $("#gppageCount").text(pageSize);
  					 
  					  for(var j=1; j<=pageSize;j++){
  						pageStr +="<a class='btn btn-primary' href=\"javascript:gpuserlistpageGoto('"+j+"','"+gpid+"','"+type+"')\">"+j+"</a>&nbsp";
  					  }
  					 $("#gpuserlistPage").empty();
  					 $("#gpuserlistPage").append(pageStr);
  				    }
  				    
  			    }
  			});
}
function gpuserlistpageGoto(page,gpid,type){
	lookRanking(page,gpid,type);
}
function editmotobandgp(gpid){
	$("#hiddenValue").val("1");
	var datas =
	{
		"gpid" : gpid
	}
	var str="";
	
	$.ajax (
	{
	    type : "POST",
	    url : "../motobandgpmanage/getMotobandgpByid",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    var jsonData = eval ("(" + data + ")");
                $("#update_gpid").val(jsonData.gpid);
                $("#update_title").val(jsonData.title);
                $("#update_subtitle").val(jsonData.subtitle);
                $("#update_content").val(jsonData.content);
                $("#titleimg").val(jsonData.picurl);
                $("#titleimgshow").attr("src",jsonData.picurl);
                $("#update_starttime").val(jsonData.starttimeString);
                $("#update_endtime").val(jsonData.endtimeString);
                $("#update_mileage").val(jsonData.mileage);
                $("#update_lap").val(jsonData.lap);
                $("#update_type").val(jsonData.type);
                $("#update_status").val(jsonData.status);
                
                $.ajax (
             			{
             			    type : "POST",
             			    url : "../motobandgpmanage/getachievementlist",
             			    async: false,
             			    success : function (data)
             			    {
             				    if (data != "" && data != null)
             				    {
             					    var jsonData1 = eval ("(" + data + ")");
             		                for(var i=0;i<jsonData1.length;i++){
             		                	if(jsonData.achid==jsonData1[i].achid){
             		                		str+="<option value='"+jsonData1[i].achid+"' selected>"+jsonData1[i].ach_name+"</option>";
             		                	}else{
             		                		str+="<option value='"+jsonData1[i].achid+"'>"+jsonData1[i].ach_name+"</option>";
             		                	}
             		                	
             		                }
             		                $("#update_achid").empty();
             		                $("#update_achid").append(str);     
             				    }
             				    
             			    }
             			});
            	
		    }
		    
	    }
	});
}

function editmotobandgpConfirm(){
	var adminGuid=$("#userGuidHidden").val();
	var gpid= $("#update_gpid").val();
	var title= $("#update_title").val();
	var subtitle= $("#update_subtitle").val();
	var content= $("#update_content").val();
	var picurl= $("#titleimg").val();
	var starttime= $("#update_starttime").val();
	var endtime= $("#update_endtime").val();
	var mileage= $("#update_mileage").val();
	var lap= $("#update_lap").val();
	var type= $("#update_type").val();
	var status= $("#update_status").val();
	var achid=$("#update_achid").val();
	if(title==null || title==''){
		alert("title不能为空");
		return;
	}
	if(subtitle==null || subtitle==''){
		alert("subtitle不能为空");
		return;
	}
	if(content==null || content==''){
		alert("content不能为空");
		return;
	}
	if(picurl==null || picurl==''){
		alert("picurl不能为空");
		return;
	}
	if(starttime==null || starttime==''){
		alert("starttime不能为空");
		return;
	}
	if(endtime==null || endtime==''){
		alert("endtime不能为空");
		return;
	}
	if(mileage==null || mileage==''){
		alert("mileage不能为空");
		return;
	}
	if(lap==null || lap==''){
		alert("lap不能为空");
		return;
	}
	if(type==null ||type==''){
		alert("type不能为空");
		return;
	}
	if(status==null || status==''){
		alert("status不能为空");
		return;
	}
	if(achid==null || achid==''){
		alert("achid不能为空");
		return;
	}
	var datas =
	{
		"gpid" : gpid,
		"title" : title,
		"subtitle" : subtitle,
		"content" : content,
		"picurl" : picurl,
		"starttime" : starttime,
		"endtime" : endtime,
		"mileage" : mileage,
		"lap" : lap,
		"achid" : achid,
		"type" : type,
		"status" : status	
	}
	 $.ajax (
  			{
  			    type : "POST",
  			    url : "../motobandgpmanage/updatemotobandgpByid",
  			    data:datas,
  			    success : function (data)
  			    {
  				    if (data =='success')
  				    {
  					   alert("编辑成功"); 
  					// $('#editmotobandgpModel').modal('hide');
  					  $('#editmotobandgpModel').css('display', 'none');
  					//  $('.modal-backdrop').css('display', 'none');
  					 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
  					 $ ("#page-wrapper").load ("../motobandgpmanage/motobandgplist?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
  				    }
  				    
  			    }
  			});
}
function addmotobandgp(){
	$("#hiddenValue").val("2");	
	var str="";
	  $.ajax (
   			{
   			    type : "POST",
   			    url : "../motobandgpmanage/getachievementlist",
   			    success : function (data)
   			    {
   				    if (data != "" && data != null)
   				    {
   					    var jsonData1 = eval ("(" + data + ")");
   		                for(var i=0;i<jsonData1.length;i++){
   		                	str+="<option value='"+jsonData1[i].achid+"'>"+jsonData1[i].ach_name+"</option>";
   		                }
   		                $("#ins_achid").empty();
   		                $("#ins_achid").append(str);     
   				    }
   				    
   			    }
   			});
}
function addmotobandgpConfirm(){
	var adminGuid=$("#userGuidHidden").val();
	var title= $.trim($("#ins_title").val());
	var subtitle= $.trim($("#ins_subtitle").val());
	var content= $.trim($("#ins_content").val());
	var picurl= $.trim($("#titleimgadd").val());
	var starttime= $("#ins_starttime").val();
	var endtime= $("#ins_endtime").val();
	var mileage= $.trim($("#ins_mileage").val());
	var lap= $.trim($("#ins_lap").val());
	var type= $("#ins_type").val();
	var status= $("#ins_status").val();
	var achid=$("#ins_achid").val();
	var reg=/^[1-9]\d*$/;
	if(title==null || title==''){
		alert("title不能为空");
		return;
	}
	if(subtitle==null || subtitle==''){
		alert("subtitle不能为空");
		return;
	}
	if(content==null || content==''){
		alert("content不能为空");
		return;
	}
	if(picurl==null || picurl==''){
		alert("picurl不能为空");
		return;
	}
	if(starttime==null || starttime==''){
		alert("starttime不能为空");
		return;
	}
	if(endtime==null || endtime==''){
		alert("endtime不能为空");
		return;
	}
	if(mileage==null || mileage==''||reg.test(mileage)==false){
		alert("mileage不能为空或者不是合法数字");
		return;
	}
	if(lap==null || lap==''||reg.test(lap)==false){
		alert("lap不能为空或者不是合法数字");
		return;
	}
	if(type==null ||type==''){
		alert("type不能为空");
		return;
	}
	if(status==null || status==''){
		alert("status不能为空");
		return;
	}
	if(achid==null || achid==''){
		alert("achid不能为空");
		return;
	}
	var datas =
	{
		"title" : title,
		"subtitle" : subtitle,
		"content" : content,
		"picurl" : picurl,
		"starttime" : starttime,
		"endtime" : endtime,
		"mileage" : mileage,
		"lap" : lap,
		"achid" : achid,
		"type" : type,
		"status" : status	
	}
	 $.ajax (
  			{
  			    type : "POST",
  			    url : "../motobandgpmanage/addmotobandgp",
  			    data:datas,
  			    success : function (data)
  			    {
  				    if (data =='success')
  				    {
  					   alert("添加成功"); 
  				//	 $('#addmotobandgpModel').modal('hide');
  					  $('#addmotobandgpModel').css('display', 'none');
  				//	  $('.modal-backdrop').css('display', 'none');
  					 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
  					 $ ("#page-wrapper").load ("../motobandgpmanage/motobandgplist?userGuid=" +adminGuid+"&page=1&limit=20&order=0");
  				    }
  				    
  			    }
  			});
}
function deletegpuser(userid,gpid,type){
	if(confirm("真的要把用户:"+userid+"从骑行赛中移除么？此操作不可逆")){
		var datas =
		{
		    "userid" : userid,
		    "gpid" : gpid,
			"type" : type
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../motobandgpmanage/gpUserRemove',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data=='success')
			    {
				  alert ("移除成功");
				  lookRanking(1,gpid,type);
			    }
			    
		    }
		});
	}
}
function gotoMotoUserlist(param_userid){
	var adminGuid=$("#userGuidHidden").val();
	$(".modal-backdrop").remove();
	$("body").removeClass('modal-open');
	 $("#page-wrapper").load("../motouser/motouserlist?userGuid=" + adminGuid+"&motouserid="+param_userid);
/*	var datas =
	{

	    "uid" : param_userid
	}
    var str="";
	$.ajax (
	{
	    type : "POST",
	    url : "../motouser/getuserprofile",
	    data : datas,
	    success : function (data)
	    {
	    	
		    if (data != "" && data != null)
		    {
			    var json = eval ("("+data+")");
			    str += "<tr>";
	            str += "	<td>" + json.userid + "</td>";
	            if(json.gender=='0'){
	            	str += "	<td>男</td>";
	            }else{
	            	str += "	<td>女</td>";
	            }
	            
	            str += "	<td>" + json.birth + "</td>";
	            str += "	<td>" + json.nickname + "</td>";
	            str += "	<td>" + json.area + "</td>";
	            str += "	<td><img style='width:60px;height:60px' src='" + json.headurl + "'></td>";
	            str += "	<td>" + json.signature + "</td>";
	            str += "	<td>" + format (json.addtime, 'yyyy-MM-dd HH:mm:ss') + "</td>";
	            str += "	<td>" + json.channel + "</td>";
	            str += "	<td>" + json.level + "</td>";
	            str += "	<td>" + json.province + "</td>";
	            str += "	<td>" + json.city + "</td>";
	            str += "	<td><button type='button' class='btn btn-primary btn-lg' onclick='javascript:searchByuserid2(\""
	                    + json.userid + "\")'>用户查询</button></td>";
	            str += "</tr>";
	            
	            $('#userid').val(json.userid);

		    }
		    $ ('#userlist').html (str);
		    
	    }
	});
	
	 $ ('#addtime').val ("");
	    $ ('#area').val ("");
	    $ ('#birth').val ("");
	    $ ('#channel').val ("");
	    $ ('#friendcount').val ("");
	    $ ('#gender').val ("");
	    $ ('#headurl').val ("");
	    $ ('#level').val ("");
	    $ ('#mbid').val ("");
	    $ ('#mobileno').val ("");
	    $ ('#newscount').val ("");
	    $ ('#nickname').val ("");
	    $ ('#signature').val ("");
	    $ ('#updatetime').val ("");
	    $ ('#userid2').val ("");
	    $ ('#mileage').val ("");
	    $ ('#ridetime').val ("");
	    $ ('#ridecount').val ("");
	    $ ('#garagecount').val ("");
	    $ ('#linecount').val ("");
	    $ ('#lineupdatetime').val ("");
	    $ ('#maxmileage').val ("");
	    $ ('#maxridetime').val ("");
	    $ ('#topspeed').val ("");
	    $ ('#maxdipangle').val ("");
	    
	    $('#usergarage').empty();
	    $('#userridedatelog').empty();
	    $('#userachievement').empty();
	    $ ('#userrideline').empty();
 */
}
function lookJoinGPCount(gpid){
	var datas =
	{

	    "gpid" : gpid
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../motobandgpmanage/lookJoinGPCount",
	    data : datas,
	    success : function (data)
	    {
	    	
		    if (data != "" && data != null)
		    {
			    var json = eval ("("+data+")");
			    $("#joingpCount").val(json.joingpCount);
			    $("#completedgpCount").val(json.completedgpCount);
		    }else{
		    	 $("#joingpCount").val("");
				    $("#completedgpCount").val("");
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
