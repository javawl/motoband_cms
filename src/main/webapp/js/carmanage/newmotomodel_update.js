function set_bandparent(){
	$("#ins_bp").removeAttr("checked");
	var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../carmanage/selectCarbrandParentname',
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					      str+="<option value='0'>请选择大品牌</option>";
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].bpid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_bp").empty();
						  $("#ins_bp").append(str);

					    }
				    }
				});

}
function set_brand(){
	$("#ins_brand").removeAttr("checked");
	var bpid=$("#ins_bp").val();
	var str="";
	var datas =
		{
		    "bpid" :bpid
		   
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../carmanage/getBrandListByBpid',
				    data:datas,
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					      str+="<option value='0'>请选择小品牌</option>"; 
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].brandid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_brand").empty();
						  $("#ins_brand").append(str);

					    }
				    }
				});

}
function set_motomodel(){
	$("#ins_model").removeAttr("checked");
	var brandid=$("#ins_brand").val();
	var str="";
	var datas =
	{
			"brandid" :brandid
			
	}
	$.ajax (
			{
				type : "POST",
				url : '../carmanage/getMotomodelByBrandid',
				data:datas,
				success : function (data)
				{
					if (data!=null && data!="")
					{
						var jsondata=eval(data);
						 str+="<option value='0'>请选择车型</option>";
						for(var i=0; i<jsondata.length;i++){
							str+="<option value='"+jsondata[i].modelid+"'>"+jsondata[i].name+"</option>";
							
						}
						$("#ins_model").empty();
						$("#ins_model").append(str);
						
					}
				}
			});
	
}

function set_bp_brand_model(bpid,brandid,modelid){
	$("#ins_bp").removeAttr("checked");
	var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../carmanage/selectCarbrandParentname',
				    success : function (data)
				    {
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					      str+="<option value='0'>请选择大品牌</option>";
					      for(var i=0; i<jsondata.length;i++){
					    	  if(bpid==jsondata[i].bpid){
					    		  str+="<option value='"+jsondata[i].bpid+"' selected>"+jsondata[i].name+"</option>";
					    	  }else{
					    		  str+="<option value='"+jsondata[i].bpid+"'>"+jsondata[i].name+"</option>"; 
					    	  }
			    
					      }
					      $("#ins_bp").empty();
						  $("#ins_bp").append(str);

					    }
				    }
				});
	 
	 $("#ins_brand").removeAttr("checked");
		var str1="";
		var datas =
			{
			    "bpid" :bpid
			   
			}
		 $.ajax (
					{
					    type : "POST",
					    url : '../carmanage/getBrandListByBpid',
					    data:datas,
					    success : function (data)
					    {
						    if (data!=null && data!="")
						    {
						      var jsondata=eval(data);
						      str1+="<option value='0'>请选择小品牌</option>"; 
						      for(var i=0; i<jsondata.length;i++){
						    	  if(brandid==jsondata[i].brandid){
						    		  str1+="<option value='"+jsondata[i].brandid+"' selected>"+jsondata[i].name+"</option>"; 
						    	  }else{
						    		  str1+="<option value='"+jsondata[i].brandid+"'>"+jsondata[i].name+"</option>"; 
						    	  }
						    	 
						    
						      }
						      $("#ins_brand").empty();
							  $("#ins_brand").append(str1);

						    }
					    }
					});
		
		$("#ins_model").removeAttr("checked");
		var str2="";
		var datas1 =
		{
				"brandid" :brandid
				
		}
		$.ajax (
				{
					type : "POST",
					url : '../carmanage/getMotomodelByBrandid',
					data:datas1,
					success : function (data)
					{
						if (data!=null && data!="")
						{
							var jsondata=eval(data);
							 str2+="<option value='0'>请选择车型</option>";
							for(var i=0; i<jsondata.length;i++){
								if(modelid==jsondata[i].modelid){
									str2+="<option value='"+jsondata[i].modelid+"' selected>"+jsondata[i].name+"</option>";
								}else{
									str2+="<option value='"+jsondata[i].modelid+"'>"+jsondata[i].name+"</option>";
								}
								
								
							}
							$("#ins_model").empty();
							$("#ins_model").append(str2);
							
						}
					}
				});

}

function updateNewMotomodelConfirm(){
	var mid=$("#hiddenValueId").val();
	var modelid=$("#ins_model").val();
	var brandid=$("#ins_brand").val();
	var bpid=$("#ins_bp").val();
	var name=$("#ins_name").val();
	var price=$("#ins_price").val();
/*	var style=$("#ins_style").val();*/
	var style=$("input[name='ins_style']:checked").val();
	var maxpower=$("#ins_maxpower").val();
	var maxtorque=$("#ins_maxtorque").val();
	var cc=$("#ins_cc").val();
	var gearbox=$("#ins_gearbox").val();
	var sitheight=$("#ins_sitheight").val();
	var lwh=$("#ins_lwh").val();
	var tankcapacity=$("#ins_tankcapacity").val();
	var oilway=$("#ins_oilway").val();
	var transmissionway=$("#ins_transmissionway").val();
	var frontbrake=$("#ins_frontbrake").val();
	var rearbrake=$("#ins_rearbrake").val();
	var frontwheelsize=$("#ins_frontwheelsize").val();
	var rearwheelsize=$("#ins_rearwheelsize").val();
	var haveabs=$("#ins_haveabs").val();
	var caryear=$("#ins_caryear").val();
	var otherelectronic=$("#ins_otherelectronic").val();
	var haveonboard=$("#ins_haveonboard").val();
	
	//pic
	var pic1=$("#titleimg").val();
	var pic2=$("#titleimg2").val();
	var pic3=$("#titleimg3").val();
	var pic4=$("#titleimg4").val();
	var pic5=$("#titleimg5").val();
	var pic6=$("#titleimg6").val();
	var pic7=$("#titleimg7").val();
	var pic8=$("#titleimg8").val();
	var pic9=$("#titleimg9").val();
	
	
	if(modelid==null||modelid==''||bpid==null ||bpid==''||brandid==null||brandid==''||name==null||name=='' || style==null || style==''){
		alert("大品牌、小品牌、车型、风格和名称都不能为空");
		return;
	}
	
	$("#back1").css("display","");
	
	var datas =
	{
			"mid" :mid,
			"modelid" :modelid,
			"brandid" :brandid,
			"bpid" :bpid,
			"name" :name,
			"price" :price,
			"style" :style,
			"maxpower" :maxpower,
			"maxtorque" :maxtorque,
			"cc" :cc,
			"gearbox" :gearbox,
			"sitheight" :sitheight,
			"lwh" :lwh,
			"tankcapacity" :tankcapacity,
			"oilway" :oilway,
			"transmissionway" :transmissionway,
			"frontbrake" :frontbrake,
			"rearbrake" :rearbrake,
			"frontwheelsize" :frontwheelsize,
			"rearwheelsize" :rearwheelsize,
			"haveabs" :haveabs,
			"caryear" :caryear,
			"otherelectronic" :otherelectronic,
			"haveonboard" :haveonboard,
			
			"pic1":pic1,
			"pic2":pic2,
			"pic3":pic3,
			"pic4":pic4,
			"pic5":pic5,
			"pic6":pic6,
			"pic7":pic7,
			"pic8":pic8,
			"pic9":pic9
			
	}
	
	$.ajax (
			{
				type : "POST",
				url : '../carmanage/insertOrupdateNewMotomodel',
				data:datas,
				success : function (data)
				{
					if (data=="success")
					{
						 alert("保存成功");
						 $("#back1").css("display","none");
						 window.opener=null;
					     window.open('','_self');
					     window.close();
					}else{
						alert("保存失败");
						$("#back1").css("display","none");
					}
				},
			  error:function(data){
				  alert("发生错误");
			  }
			});
}

//图片加载js
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
			$ ("#titleimg2").val ();
		}
		if(flag=='3'){
			$ ("#titleimg3").val ();
		}
		if(flag=='4'){
			$ ("#titleimg4").val ();
		}
		if(flag=='5'){
			$ ("#titleimg5").val ();
		}
		if(flag=='6'){
			$ ("#titleimg6").val ();
		}
		if(flag=='7'){
			$ ("#titleimg7").val ();
		}
		if(flag=='8'){
			$ ("#titleimg8").val ();
		}
		if(flag=='9'){
			$ ("#titleimg9").val ();
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
			$ ("#titleimg2").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='3'){
			$ ("#titleimg3").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='4'){
			$ ("#titleimg4").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='5'){
			$ ("#titleimg5").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='6'){
			$ ("#titleimg6").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='7'){
			$ ("#titleimg7").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='8'){
			$ ("#titleimg8").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
		}
		if(flag=='9'){
			$ ("#titleimg9").val ($ ('#XX_' + img_guid).find ("img").attr ("src"));
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
function insertTitleImg ()
{
	var flag = $("#hiddenValue").val();
	if(flag=='1'){
		$ ("#titleimgshow").attr ("src", $ ("#titleimg").val ());
	}
	if(flag=='2'){
		$ ("#titleimgshow2").attr ("src", $ ("#titleimg2").val ());
	}
	if(flag=='3'){
		$ ("#titleimgshow3").attr ("src", $ ("#titleimg3").val ());
	}
	if(flag=='4'){
		$ ("#titleimgshow4").attr ("src", $ ("#titleimg4").val ());
	}
	if(flag=='5'){
		$ ("#titleimgshow5").attr ("src", $ ("#titleimg5").val ());
	}
	if(flag=='6'){
		$ ("#titleimgshow6").attr ("src", $ ("#titleimg6").val ());
	}
	if(flag=='7'){
		$ ("#titleimgshow7").attr ("src", $ ("#titleimg7").val ());
	}
	if(flag=='8'){
		$ ("#titleimgshow8").attr ("src", $ ("#titleimg8").val ());
	}
	if(flag=='9'){
		$ ("#titleimgshow9").attr ("src", $ ("#titleimg9").val ());
	}
	$('#titleImgModel').on('hidden.bs.modal', function() {

		$('#insertMallModel').css({'overflow-y':'scroll'});
	});
}
$ ('#XX_grouplist>div').on ("click", function ()
		{
			XX_showgroupimg ($ (this).attr ("id"));
			$ ("#XX_nowgroupid").val ($ (this).attr ("id"));
			$ (this).parent ("#XX_grouplist").find ("div").removeClass ('groupaction');
			$ (this).addClass ('groupaction');
			
});

function imgageHiddlenvalue(a){
	$("#hiddenValue").val(a);
}

