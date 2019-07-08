function clickparenttype(){
	 var radio_parenttype= $("input[name='radio_parenttype']:checked").val();
	 var str="";
	 var datas =
		{
		    "parentid" : radio_parenttype
		   
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../mallmanage/getTypeListByParenttypeid',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data!=null && data!="")
			    {
			      var jsondata=eval(data);
			         str+="<div class='col-sm-1'><b>二级分类:</b></div>";
			      for(var i=0; i<jsondata.length;i++){
			    	  str+="<div class='col-sm-1'><input type='radio' name='radio_type' value="+jsondata[i].typeid+" />"+jsondata[i].name+"</div>"; 
			      }
			     $("#radio_type_div").empty();
			 	 $("#radio_type_div").append(str); 
			    }else{
			     $("#radio_type_div").empty();
			    }
			    
		    }
		});
	
}
function ins_clickparenttype(){
	 var radio_parenttype= $("input[name='ins_radio_parenttype']:checked").val();
	 var str="";
	 var datas =
		{
		    "parentid" : radio_parenttype
		   
		}

		$.ajax (
		{
		    type : "POST",
		    url : '../mallmanage/getTypeListByParenttypeid',
		    data : datas,
		    success : function (data)
		    {
			    
			    if (data!=null && data!="")
			    {
			      var jsondata=eval(data);
			         str+="<div class='col-sm-3'><b>二级分类:</b></div>";
			      for(var i=0; i<jsondata.length;i++){
			    	  str+="<div class='col-sm-3'><input type='radio' name='ins_radio_type' value="+jsondata[i].typeid+" />"+jsondata[i].name+"</div>"; 
			      }
			     $("#ins_radio_type_div").empty();
			 	 $("#ins_radio_type_div").append(str); 
			    }else{
			     $("#ins_radio_type_div").empty();
			    }
			    
		    }
		});
	
}
function checkbox_styleAll(){

	   if($("#checkbox_styleAll").get(0).checked){
		  
		   $("[name = checkbox_style]:checkbox").prop("checked",true);
	   }else{
		  
		   $("[name = checkbox_style]:checkbox").prop("checked",false);  
	   }

	}
function checkbox_brandAll(){

	   if($("#checkbox_brandAll").get(0).checked){
		   $("[name = checkbox_brand]:checkbox").prop("checked",true);
	   }else{
		   $("[name = checkbox_brand]:checkbox").prop("checked",false);  
	   }

	}
function checkbox_stateAll(){

	   if($("#checkbox_stateAll").get(0).checked){
		  
		   $("[name = checkbox_state]:checkbox").prop("checked",true);
	   }else{
		  
		   $("[name = checkbox_state]:checkbox").prop("checked",false);  
	   }

	}
function search_reset(){
	$("#checkbox_styleAll").attr("checked",false);
	$("#checkbox_brandAll").attr("checked",false);
	$("#checkbox_stateAll").attr("checked",false);
	$("[name = checkbox_style]:checkbox").attr("checked",false);
	$("[name = checkbox_brand]:checkbox").attr("checked",false);
	$("[name = checkbox_state]:checkbox").attr("checked",false);
	$("#search_price_start").val("");
	$("#search_price_end").val("");
	$("#search_input").val("");
	$("input[name='radio_carprice']").removeAttr('checked');
	$("input[name='radio_parenttype']").removeAttr('checked');
	$("input[name='radio_type']").removeAttr('checked');
	
}

function set_style(){
	 var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/getMallStyleList',
				    success : function (data)
				    {
					 //   console.log(data);
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].styleid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_style").empty();
						  $("#ins_style").append(str);
//					     $("#set_style_select").empty();
//					 	 $("#set_style_select").append(str); 
					    }
				    }
				});
	//	console.log($("#set_style_div").css("display"));
//		if($("#set_style_div").css("display")=='none'){
//			$("#set_style_div").show();
//			
//		}else{
//			$("#set_style_div").hide();
//		}
}

function select_style_show(){
//	  var new_selected_text = $("#ins_style option:selected").text();
//	  var new_selected_val = $("#ins_style").val();
//      var div_show= $("#style_div").text();
//      var str="";
//      var mallstyle=$(".mallstyle");
//   
//      var flag= false;
//      if(mallstyle.length!="0"){
//    	  for(var i=0; i<mallstyle.length;i++){
//    		  
//    		  if($(".mallstyle").eq(i).attr("name")==new_selected_val){
//    			  flag=true;
//    		  }
//    	  }
//    	  if(!flag){
//    		  str+="<span id='"+new_selected_val+"' class='mallstyle' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+new_selected_val+"')\">×</button></span>";  
//			  $("#style_div").append(str);
//    	  }
//    	
//      }else{
//    	  
//    	  str+="<span id='"+new_selected_val+"' class='mallstyle' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+new_selected_val+"')\">×</button></span>";  
//		  $("#style_div").append(str);
//      }
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

function set_label(){
	 var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/getMallLabelList',
				    success : function (data)
				    {
					  //  console.log(data);
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].labelid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_label").empty();
						  $("#ins_label").append(str);

					    }
				    }
				});

}

function select_label_show(){
//	  var new_selected_text = $("#ins_label option:selected").text();
//	  var new_selected_val = $("#ins_label").val();
//    var div_show= $("#label_div").text();
//    var str="";
//    var malllabel=$(".malllabel");
//  
//    var flag= false;
//    if(malllabel.length!="0"){
//  	  for(var i=0; i<malllabel.length;i++){
//  		  
//  		  if($(".malllabel").eq(i).attr("name")==new_selected_val){
//  			  flag=true;
//  		  }
//  	  }
//  	  if(!flag){
//  		  str+="<span id='"+new_selected_val+"' class='malllabel' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteLabeltext('"+new_selected_val+"')\">×</button></span>";  
//			  $("#label_div").append(str);
//  	  }
//  	
//    }else{
//  	  
//  	  str+="<span id='"+new_selected_val+"' class='malllabel' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteLabeltext('"+new_selected_val+"')\">×</button></span>";  
//		  $("#label_div").append(str);
//    }
	var new_selected_val = $("#ins_label").val();
    var str="";
    var malllabel=$(".malllabel");
    var flag= false;
  if(malllabel.length!="0"){
    for(var i=0;i<new_selected_val.length;i++){
    	flag= false;
    	    for(var j=0;j<malllabel.length;j++){
    	    	if($(".malllabel").eq(j).attr("name")==new_selected_val[i]){
    	    		flag=true;
     	  		 }
    	   }
    	    if(!flag){
    	    	  var new_selected_text= $("#ins_label [value='"+new_selected_val[i]+"']").text();
    	    	  str+="<span id='label"+new_selected_text+"' class='malllabel' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteLabeltext('"+new_selected_text+"')\">×</button></span>";  
    	    }
         }
     }else{
    	for(var i=0;i<new_selected_val.length;i++){ 
		 var new_selected_text= $("#ins_label [value='"+new_selected_val[i]+"']").text();
 	     str+="<span id='label"+new_selected_text+"' class='malllabel' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteLabeltext('"+new_selected_text+"')\">×</button></span>";  
	    	
    	 }
    }
    $("#label_div").append(str);

}
function set_probrand(){
	 var str="";
	 var name =$("#input_probrand").val();
	 var datas =
		{
		    "name" :name
		   
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/getMallBrandList',
				    data:datas,
				    success : function (data)
				    {
					 //   console.log(data);
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].brandid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_probrand").empty();
						  $("#ins_probrand").append(str);

					    }else{
					    	$("#ins_probrand").empty();
							$("#ins_probrand").append(str);

					    }
				    }
				});

}
function set_brand(){
	 var str="";
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/getMotoBrandList',
				    success : function (data)
				    {
					   // console.log(data);
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].brandid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_brand").empty();
						  $("#ins_brand").append(str);

					    }
				    }
				});

}
function set_carmodel(){
	$("input[name='ins_carmodelAll']").removeAttr("checked");
	var brandids=$("#ins_brand").val();
//	var arr=new Array();
//	arr[0]=brandids;
//	console.log(brandids);
//	console.log(brandids.length);
//	console.log(brandids[0]);
//	console.log(arr);
	var str="";
	var datas =
		{
		    "brandids" :brandids
		   
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/getMotoModelList',
				    data:datas,
				    success : function (data)
				    {
					  //  console.log(data);
					    if (data!=null && data!="")
					    {
					      var jsondata=eval(data);
					        
					      for(var i=0; i<jsondata.length;i++){
					    	  str+="<option value='"+jsondata[i].modelid+"'>"+jsondata[i].name+"</option>";
					    
					      }
					      $("#ins_model").empty();
						  $("#ins_model").append(str);

					    }
				    }
				});

}
function select_model_show(){
//	var new_selected_text = $("#ins_model option:selected").text();
//	console.log( $("#ins_model [value='1']").text());
	var new_selected_val = $("#ins_model").val();
    var str="";
    var mallmodel=$(".mallmodel");
    var flag= false;
  if(mallmodel.length!="0"){
    for(var i=0;i<new_selected_val.length;i++){
    	flag= false;
    	    for(var j=0;j<mallmodel.length;j++){
    	    	var arr = $(".mallmodel").eq(j).attr("name").split('_');
    	    	//console.log(arr);
    	    	if(arr[1]==new_selected_val[i]){
    	    		flag=true;
     	  		 }
    	   }
    	    if(!flag){
    	    	  var new_selected_text= $("#ins_model [value='"+new_selected_val[i]+"']").text();
    	    	    var brandid="";
    	    	    var datas =
    	    		{
    	    		    "motoid" :new_selected_val[i]
    	    		   
    	    		}
    	    	   $.ajax (
    	    				{
    	    				    type : "POST",
    	    				    url : '../mallmanage/getCarModelById',
    	    				    data:datas,
    	    				    async: false,
    	    				    success : function (data)
    	    				    {
    	    					   // console.log(data);
    	    					    if (data!=null && data!="")
    	    					    {
    	    					      var jsondata=eval("("+data+")");
    	    					        
    	    					      brandid=jsondata.brandid;
    	    					    }
    	    				    }
    	    				});
    	    	    
    	    	    
    	    	    str+="<span id='model"+new_selected_val[i]+"' class='mallmodel' name='"+brandid+"_"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteModeltext('"+new_selected_val[i]+"')\">×</button></span>";  
    	    }
    	  
   		 
         }
     }else{
    	for(var i=0;i<new_selected_val.length;i++){ 
	    		var new_selected_text= $("#ins_model [value='"+new_selected_val[i]+"']").text();
	    		var brandid="";
	     	    var datas =
	     		{
	     		    "motoid" :new_selected_val[i]
	     		   
	     		}
	     	   $.ajax (
	     				{
	     				    type : "POST",
	     				    url : '../mallmanage/getCarModelById',
	     				    data:datas,
	     				    async: false,
	     				    success : function (data)
	     				    {
	     					   // console.log(data);
	     					    if (data!=null && data!="")
	     					    {
	     					      var jsondata=eval("("+data+")");
	     					        
	     					      brandid=jsondata.brandid;
	     					    //  console.log(brandid);
	     					    }
	     				    }
	     				});
	     	     str+="<span id='model"+new_selected_val[i]+"' class='mallmodel' name='"+brandid+"_"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteModeltext('"+new_selected_val[i]+"')\">×</button></span>";  
	    	
    	 }
    }
    $("#model_div").append(str);
    
 
}
function select_brand_show(){
//	 var new_selected_text = $("#ins_brand option:selected").text();
//	  var new_selected_val = $("#ins_brand").val();
//	  var div_show= $("#brand_div").text();
//	  var str="";
//	  var mallbrand=$(".mallbrand");
//
//	  var flag= false;
//	  if(mallbrand.length!="0"){
//		  for(var i=0; i<mallbrand.length;i++){
//			  
//			  if($(".mallbrand").eq(i).attr("name")==new_selected_val){
//				  flag=true;
//			  }
//		  }
//		  if(!flag){
//			  str+="<span id='"+new_selected_val+"' class='mallbrand' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteBrandtext('"+new_selected_val+"')\">×</button></span>";  
//				  $("#brand_div").append(str);
//		  }
//		
//	  }else{
//		  
//		  str+="<span id='"+new_selected_val+"' class='mallbrand' name='"+new_selected_val+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteBrandtext('"+new_selected_val+"')\">×</button></span>";  
//			  $("#brand_div").append(str);
//	  }
	var new_selected_val = $("#ins_brand").val();
    var str="";
    var mallbrand=$(".mallbrand");
    var flag= false;
  if(mallbrand.length!="0"){
    for(var i=0;i<new_selected_val.length;i++){
    	flag= false;
    	    for(var j=0;j<mallbrand.length;j++){
    	    	if($(".mallbrand").eq(j).attr("name")==new_selected_val[i]){
    	    		flag=true;
     	  		 }
    	   }
    	    if(!flag){
    	    	  var new_selected_text= $("#ins_brand [value='"+new_selected_val[i]+"']").text();
    	    	  str+="<span id='brand"+new_selected_val[i]+"' class='mallbrand' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteBrandtext('"+new_selected_val[i]+"')\">×</button></span>";  
    	    }
         }
     }else{
    	for(var i=0;i<new_selected_val.length;i++){ 
		 var new_selected_text= $("#ins_brand [value='"+new_selected_val[i]+"']").text();
 	     str+="<span id='brand"+new_selected_val[i]+"' class='mallbrand' name='"+new_selected_val[i]+"'>"+new_selected_text+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteBrandtext('"+new_selected_val[i]+"')\">×</button></span>";  
	    	
    	 }
    }
    $("#brand_div").append(str);

}

function search_probrand(){
	set_probrand();
}
function brandAndmodelConfirm(){
	var carmodelAll=$("input[name='ins_carmodelAll']:checked").val();
	if(carmodelAll=='0'){
		select_carmodelAll();
	}
	if(carmodelAll=='1'){
		select_NocarmodelAll();
	}
	select_brand_show();
	select_model_show();
	$("input[name='ins_carmodelAll']").removeAttr("checked");
}
function deleteBrandtext(id){
	 $("#brand"+id).remove();
	 var idarr=[];
	 var mallmodel=$(".mallmodel");
	 for( var j=0;j<mallmodel.length;j++){
	     var arr = $(".mallmodel").eq(j).attr("name").split('_');
	     if(arr[0]==id){
	    	
	    	 idarr.push(arr[1]);
	     }
	 }
	 for(var i=0;i<idarr.length;i++){
		 $("#model"+idarr[i]).remove();
	 }

}
function deleteModeltext(id){
	var name=$("#model"+id).attr("name").split('_');
	$("#model"+id).remove();
	var flag=false;
	var mallmodel=$(".mallmodel");
	if(mallmodel.length!="0"){
		for( var j=0;j<mallmodel.length;j++){
  		  var arr = $(".mallmodel").eq(j).attr("name").split('_');
  		  if(arr[0]==name[0]){
  			flag=true;
  		  }
  	  }
		if(!flag){
			var mallbrand=$(".mallbrand");
			if(mallbrand.length!="0"){
				for( var i=0;i<mallbrand.length;i++){
					if($(".mallbrand").eq(i).attr("name")==name[0]){
						$("#brand"+name[0]).remove();
					}
				}
			}
		}
	}
	
}
function deleteLabeltext(text){
	
	$("#label"+text).remove();
}
function deleteStyletext(text){
	$("#style"+text).remove();
}
 function select_carmodelAll(){
	 $("#ins_model option").each(function(){
		 $("#ins_model option[value='"+$(this).val()+"']").prop("selected",true); 
		 
	 });
 }
function select_NocarmodelAll(){
	 $("#ins_model option").each(function(){
			 $("#ins_model option[value='"+$(this).val()+"']").prop("selected",false); 
			 
	});
	 var brandids=$("#ins_brand").val();
	   $("#ins_brand option[value='"+brandids[0]+"']").prop("selected",false); 
     var idarr=[];
	 var mallmodel=$(".mallmodel");
	 for( var j=0;j<mallmodel.length;j++){
	     var arr = $(".mallmodel").eq(j).attr("name").split('_');
	     if(arr[0]==brandids[0]){
	    	
	    	 idarr.push(arr[1]);
	     }
	 }
	 for(var i=0;i<idarr.length;i++){
		 $("#model"+idarr[i]).remove();
	 }
	
	 $("#brand"+brandids[0]).remove();
}
function select_styleAll(){
	$("#ins_style option").each(function(){
		 $("#ins_style option[value='"+$(this).val()+"']").prop("selected",true); 
		 
	 });
	$("#style_div").empty();
	 select_style_show();
}
function select_NostyleAll(){
	$("#ins_style option").each(function(){
		 $("#ins_style option[value='"+$(this).val()+"']").prop("selected",false); 
		 
	 });
	$("#style_div").empty();
}	
function insertMall(){
//	$("#hiddenValue").val("1");
//	$("#ins_title").val("");
//	$("#ins_des").val("");
//	$("input[name='ins_radio_parenttype']").removeAttr("checked");
//	$("#ins_radio_type_div").empty();
//
//	$("#ins_probrand option").removeAttr("selected");
//    $("#ins_keyword").val("");
//	$("#brand_div").empty();
//	$("#model_div").empty();
//	$("#style_div").empty();
//	$("#label_div").empty();
//    $("#ins_mallurl").val("");
//	$("input[name='ins_level']").removeAttr("checked");
//	$("input[name='ins_source']").removeAttr("checked");
//	$("#ins_ispackage option").removeAttr("selected");
//	$("#ins_isrecommend option").removeAttr("selected");
//	$("#titleimg").val("");
//	$("#titleimgshow").attr("src","");
//	$("#ins_oldprice").val("");
//	$("#ins_newprice").val("");
//	$("#ins_state option").removeAttr("selected");
	
window.open("../mallmanage/mallproduct_ins?userGuid=0&page=1&limit=20&order=0");

}
function updateProductNewPage(id){
	window.open("../mallmanage/mallproduct_update?userGuid=0&id="+id+"&page=1&limit=20&order=0");
}

function updateMallProduct(id){
	$("#hiddenValue").val("1");
	$("#hiddenValueId").val(id);
	
	$("#ins_title").val("");
	$("#ins_des").val("");
	$("input[name='ins_radio_parenttype']").removeAttr("checked");
    $("#ins_keyword").val("");
	$("#brand_div").empty();
	$("#model_div").empty();
	$("#style_div").empty();
	$("#label_div").empty();
    $("#ins_mallurl").val("");
    $("input[name='ins_level']").removeAttr("checked");
	$("input[name='ins_source']").removeAttr("checked");
	$("#titleimg").val("");
	$("#titleimgshow").attr("src","");
	$("#ins_oldprice").val("");
	$("#ins_newprice").val("");
	
	set_style();
	set_label();
	set_brand();
	var str="";
	var str2="";
	var brandstr="";
	var modelstr="";
	var stylestr="";
	var labelstr="";
	var datas={
		"id":id	
	}
	$.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/getMallProductById',
			    data:datas,
			    success : function (data)
			    {
				    if (data!=null && data!="")
				    {
				      var jsondata=eval("("+data+")");
				   //   console.log(jsondata);
				      $("#ins_title").val(jsondata.title);
				      $("#ins_des").val(jsondata.des);
				     
				      $("input[name='ins_radio_parenttype'][value='"+jsondata.parenttype+"']").prop("checked",true);
				      
				     // ins_clickparenttype();
				    
				 	 var datas2 =
				 		{
				 		    "parentid" : jsondata.parenttype
				 		   
				 		}

				 		$.ajax (
				 		{
				 		    type : "POST",
				 		    url : '../mallmanage/getTypeListByParenttypeid',
				 		    data : datas2,
				 		    success : function (data)
				 		    {
				 			    
				 			    if (data!=null && data!="")
				 			    {
				 			      var jsondata2=eval(data);
				 			         str2+="<div class='col-sm-3'><b>二级分类:</b></div>";
				 			      for(var i=0; i<jsondata2.length;i++){
				 			    	  if(jsondata2[i].typeid==jsondata.type){
				 			    		 str2+="<div class='col-sm-3'><input type='radio' name='ins_radio_type' value="+jsondata2[i].typeid+" checked />"+jsondata2[i].name+"</div>";  
				 			    	  }else{
				 			    		 str2+="<div class='col-sm-3'><input type='radio' name='ins_radio_type' value="+jsondata2[i].typeid+" />"+jsondata2[i].name+"</div>"; 
				 			    	  }
				 			    	  
				 			      }
				 			     $("#ins_radio_type_div").empty();
				 			 	 $("#ins_radio_type_div").append(str2); 
				 			    }
				 			    
				 		    }
				 		});
				      
				      $("input[name='ins_radio_type'][value='"+jsondata.type+"']").prop("checked",true);
				     
				   //   $("#ins_probrand option[value='"+jsondata.probrand+"'] ").css("selected","selected");
						     
						 	  $.ajax (
						 				{
						 				    type : "POST",
						 				    url : '../mallmanage/getMallBrandList',
						 				    async: false,
						 				    success : function (data)
						 				    {
						 					 // console.log(data);
						 					    if (data!=null && data!="")
						 					    {
						 					      var jsondata1=eval("("+data+")");
						 					        
						 					      for(var i=0; i<jsondata1.length;i++){
						 					    	//  console.log(typeof(jsondata1[i].brandid)+"  "+typeof(jsondata.probrand));
						 					    	  if(jsondata1[i].brandid==jsondata.probrand){
						 					    		 str+="<option value='"+jsondata1[i].brandid+"' selected >"+jsondata1[i].name+"</option>";  
						 					    	  }else{
						 					    		 str+="<option value='"+jsondata1[i].brandid+"'>"+jsondata1[i].name+"</option>";  
						 					    	  }
						 					    	 
						 					    
						 					      }
						 					      $("#ins_probrand").empty();
						 						  $("#ins_probrand").append(str);
		
						 					    }
						 				    }
						 				});

				 
				      $("#ins_keyword").val(jsondata.keyword);
				   //   console.log(jsondata.brandids);
                    if(jsondata.brandids !=null && jsondata.brandids!=""){
                    	  var brands=jsondata.brandids.split(",");
	   				      var brandstext=jsondata.brand.split(",");
	   				      for(var i=0; i<brands.length;i++){
	   				    	  brandstr+="<span id='brand"+brands[i]+"' class='mallbrand' name='"+brands[i]+"'>"+brandstext[i]+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteBrandtext('"+brands[i]+"')\">×</button></span>";  
	   				      }
	   				      $("#brand_div").empty();
	   				      $("#brand_div").append(brandstr);
				      }
				     
                    if(jsondata.modelids !=null && jsondata.modelids!=""){
                    	  var models=jsondata.modelids.split(",");
	   				      var modelstext=jsondata.model.split(",");
	   				      for(var i=0; i<models.length;i++){
	   				    	   var brandid1="";
	   		    	    	    var datas1 =
	   		    	    		{
	   		    	    		    "motoid" :models[i]
	   		    	    		   
	   		    	    		}
	   		    	    	   $.ajax (
	   		    	    				{
	   		    	    				    type : "POST",
	   		    	    				    url : '../mallmanage/getCarModelById',
	   		    	    				    data:datas1,
	   		    	    				    async: false,
	   		    	    				    success : function (data)
	   		    	    				    {
	   		    	    					   // console.log(data);
	   		    	    					    if (data!=null && data!="")
	   		    	    					    {
	   		    	    					      var jsondata1=eval("("+data+")");
	   		    	    					        
	   		    	    					      brandid1=jsondata1.brandid;
	   		    	    					    }
	   		    	    				    }
	   		    	    				});
	   				    	  modelstr+="<span id='model"+models[i]+"' class='mallmodel' name='"+brandid1+"_"+models[i]+"'>"+modelstext[i]+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteModeltext('"+models[i]+"')\">×</button></span>";  
	   				      }
	   				      $("#model_div").empty();
	   				      $("#model_div").append(modelstr);
                    }
				     
				      
				      if(jsondata.styleids !=null && jsondata.styleids!=""){
				    	  var styles=jsondata.styleids.split(",");
					      var stylestext=jsondata.style.split(",");
					      for(var i=0; i<styles.length;i++){
					    	  stylestr+="<span id='style"+stylestext[i]+"' class='mallstyle' name='"+styles[i]+"'>"+stylestext[i]+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteStyletext('"+stylestext[i]+"')\">×</button></span>";  
					      }
					      $("#style_div").empty();
					      $("#style_div").append(stylestr);
				      }
				     
				      
				      if(jsondata.labelids !=null && jsondata.labelids!=""){
				    	  var labels=jsondata.labelids.split(",");
					      var labelstext=jsondata.label.split(",");
					      for(var i=0; i<labels.length;i++){
					    	  labelstr+="<span id='label"+labelstext[i]+"' class='malllabel' name='"+labels[i]+"'>"+labelstext[i]+"<button type='button' class='btn btn-danger btn-xs' onclick=\"javacript:deleteLabeltext('"+labelstext[i]+"')\">×</button></span>";  
					      }
					      $("#label_div").empty();
					      $("#label_div").append(labelstr);
				      }
				     
				      
				      $("#ins_mallurl").val(jsondata.mallurl);
				      $("input[name='ins_level'][value='"+jsondata.level+"']").prop("checked",true);
				      $("input[name='ins_source'][value='"+jsondata.source+"']").prop("checked",true);
				      
				    //  $("#ins_ispackage option[value='"+jsondata.ispackage+"'] ").css("selected","selected");
				        var ispackageStr="";
				        if(jsondata.ispackage=='1'){
				        	 ispackageStr+="<option value='0'>否</option><option value='1' selected>是</option>";
				        	 $("#ins_ispackage").empty();
	 						 $("#ins_ispackage").append(ispackageStr);
				        }else if(jsondata.ispackage=='0'){
				        	 ispackageStr+="<option value='0'  selected>否</option><option value='1'>是</option>";
				        	 $("#ins_ispackage").empty();
	 						 $("#ins_ispackage").append(ispackageStr);
				        }
				   //   $("#ins_isrecommend option[value='"+jsondata.isrecommend+"'] ").css("selected","selected");
				        var isrecommendStr="";
				        if(jsondata.isrecommend=='1'){
				        	isrecommendStr+="<option value='0'>否</option><option value='1' selected>是</option>";
				        	 $("#ins_isrecommend").empty();
	 						 $("#ins_isrecommend").append(isrecommendStr);
				        }else if(jsondata.isrecommend=='0'){
				        	isrecommendStr+="<option value='0' selected>否</option><option value='1' >是</option>";
				        	 $("#ins_isrecommend").empty();
	 						 $("#ins_isrecommend").append(isrecommendStr);
				        }

				      $("#titleimg").val(jsondata.picurl);
				      $("#titleimgshow").attr("src",jsondata.picurl);
				      $("#ins_oldprice").val(jsondata.oldprice/100);
				      $("#ins_newprice").val(jsondata.newprice/100);
				  //    $("#ins_state option[value='"+jsondata.state+"'] ").css("selected","selected");
				        var stateStr="";
				        if(jsondata.state=='1'){
				        	 stateStr+="<option value='0'>否</option><option value='1' selected>是</option>";
				        	 $("#ins_state").empty();
	 						 $("#ins_state").append(stateStr);
				        }else if(jsondata.state=='0'){
				        	 stateStr+="<option value='0' selected>否</option><option value='1'>是</option>";
				        	 $("#ins_state").empty();
	 						 $("#ins_state").append(stateStr);
				        }
                   
				        $("#ins_ptime").val(jsondata.ptimeString);
				    }
			    }
			});
}
function insertMallConfirm(){
 
 var title=$.trim($("#ins_title").val());
 var des=$.trim($("#ins_des").val());
 var parenttype=$("input[name='ins_radio_parenttype']:checked").val();
 var type=$("input[name='ins_radio_type']:checked").val();
 var probrand=$("#ins_probrand").val();
 var keyword=$("#ins_keyword").val();
// var brand_arr=$("#ins_brand").val();
// var model_arr=$("#ins_model").val();
// var style_arr=$("#ins_style")
// var label_arr=$("#ins_label").val();
 var mallmodel=$(".mallmodel");
 var modelids="";
 for(var i=0;i<mallmodel.length;i++){
	 var arr = $(".mallmodel").eq(i).attr("name").split('_');
	 modelids+=""+arr[1]+",";
 }
 var mallbrand=$(".mallbrand");
 var brandids="";
 for(var i=0;i<mallbrand.length;i++){
	 var name = $(".mallbrand").eq(i).attr("name");
	 brandids+=""+name+",";
 }
 var brandtext=$("#brand_div").text();
 var modeltext=$("#model_div").text();
 var styletext=$("#style_div").text();
 var labeltext=$("#label_div").text();
 
 var mallurl=$("#ins_mallurl").val();
 var level=$("input[name='ins_level']:checked").val();
 var source=$("input[name='ins_source']:checked").val();
 var ispackage=$("#ins_ispackage").val();
 var isrecommend=$("#ins_isrecommend").val();
 var picurl=$("#titleimg").val();
 var oldprice=$.trim($("#ins_oldprice").val());
 var newprice=$.trim($("#ins_newprice").val());
 var state=$("#ins_state").val();
 var puuid=$("#hiddenValueProductId").val();
 var ptime=$("#ins_ptime").val();
 var reg=/^([1-9]\d{0,15}|0)(\.\d{1,2})?$/;
 
	
 if(title==null || title==''){
	alert("title不能为空");
	return;
 }
 if(parenttype==null || parenttype==''){
		alert("parenttype不能为空");
		return;
	 }
 if(type==null || type==''){
		alert("type不能为空");
		return;
	 }
 if(probrand==null || probrand==''){
		alert("probrand不能为空");
		return;
	 }
 if(picurl==null || picurl==''){
		alert("picurl不能为空");
		return;
	 }
 if(oldprice!=null && oldprice !=""){
	 if(reg.test(oldprice)==false){
		 alert("oldprice不合法");
			return; 
	 }
 }
 if(newprice==null || newprice=='' || reg.test(newprice)==false){
		alert("newprice不能为空或者不合法");
		return;
	 }
 if(mallurl==null || mallurl=='' ){
		alert("mallurl不能为空");
		return;
	 }
 if(level==null || level==''){
		alert("level不能为空");
		return;
	 }
if(source==null || source==''){
		alert("source不能为空");
		return;
	 }


 var pid= $("#hiddenValueId").val();
 if(pid!=null && pid!=""){
	 $("#back").css("display","");
	 var datas =
		{   "id":pid,
		    "title" :title ,
		    "des" :des ,
		    "parenttype" :parenttype ,
		    "type" :type ,
		    "probrand" :probrand ,
		    "keyword" :keyword ,
//		    "brand_arr" :brand_arr ,
//		    "model_arr" :model_arr ,
//		    "style_arr" :style_arr ,
//		    "label_arr" :label_arr ,
		    "brandtext":brandtext,
		    "modeltext":modeltext,
		    "styletext":styletext,
		    "labeltext":labeltext,
		    "modelids":modelids,
		    "brandids":brandids,
		    
		    "mallurl" :mallurl ,
		    "level" :level ,
		    "source" :source ,
		    "ispackage" :ispackage ,
		    "isrecommend" :isrecommend ,
		    "picurl" :picurl ,
		    "oldprice" :oldprice ,
		    "newprice" :newprice ,
		    "state" :state,
		    "ptime":ptime
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/updateMallProduct',
				    data:datas,
				    success : function (data)
				    {
					    if (data=="success")
					    {
					    alert("更新成功");
					    $("#back").css("display","none");
					    $("#hiddenValueId").val("");
//					    $('#insertMallModel').modal('hide');
//					    var data1={
//					    		"userGuid":0,
//					    		"page":1,
//					    		"limit":20,
//					    		"order":0
//					    }
//					    $("#mallListTable").load("/mallmanage/mallproductlist",data1);
					     window.opener=null;
					     window.open('','_self');
					     window.close();
					    }else{
					    	$("#back").css("display","none");
					    }
				    }
				});
 }else{
	 $("#back").css("display","");
	 var datas =
		{
			 "id":puuid,
		    "title" :title ,
		    "des" :des ,
		    "parenttype" :parenttype ,
		    "type" :type ,
		    "probrand" :probrand ,
		    "keyword" :keyword ,
//		    "brand_arr" :brand_arr ,
//		    "model_arr" :model_arr ,
//		    "style_arr" :style_arr ,
//		    "label_arr" :label_arr ,
		    "brandtext":brandtext,
		    "modeltext":modeltext,
		    "styletext":styletext,
		    "labeltext":labeltext,
		    "modelids":modelids,
		    "brandids":brandids,
		    
		    "mallurl" :mallurl ,
		    "level" :level ,
		    "source" :source ,
		    "ispackage" :ispackage ,
		    "isrecommend" :isrecommend ,
		    "picurl" :picurl ,
		    "oldprice" :oldprice ,
		    "newprice" :newprice ,
		    "state" :state 
		}
	 $.ajax (
				{
				    type : "POST",
				    url : '../mallmanage/insertMallProduct',
				    data:datas,
				    success : function (data)
				    {
					    if (data=="success")
					    {
					    alert("添加成功");
					    $("#back").css("display","none");
					    /*  $('#insertMallModel').modal('hide');
					    var data1={
					    		"userGuid":0,
					    		"page":1,
					    		"limit":20,
					    		"order":0
					    }
					    $("#mallListTable").load("/mallmanage/mallproductlist",data1);*/
					    window.opener=null;
					    window.open('','_self');
					    window.close();
					    }else{
					    	$("#back").css("display","none");
					    }
				    }
				  
				}); 
 }

}

function search(userGuid,page,limit,order){
	 var search_parenttype=$("input[name='radio_parenttype']:checked").val();
	 var search_type=$("input[name='radio_type']:checked").val();
	 var search_probrand_arr=[];
	 var search_style_arr=[];
	 var search_level=$("input[name='radio_carprice']:checked").val();
	 var search_price_start=$("#search_price_start").val();
	 var search_price_end=$("#search_price_end").val();
	 var search_input=$("#search_input").val();
	 var search_state_arr=[];
	 
    $("input[name='checkbox_brand']:checked").each(function() {
    	search_probrand_arr.push($(this).val());
    });
    $("input[name='checkbox_style']:checked").each(function() {
    	search_style_arr.push($(this).val());
    });
    $("input[name='checkbox_state']:checked").each(function() {
    	search_state_arr.push($(this).val());
    });
	    
	 var data={
	    		"userGuid":userGuid,
	    		"page":page,
	    		"limit":limit,
	    		"order":order,
	    		 "search_parenttype":search_parenttype,
	    		 "search_type":search_type,
	    		 "search_probrand_arr":search_probrand_arr,
	    		 "search_style_arr":search_style_arr,
	    		 "search_level":search_level,
	    		 "search_price_start":search_price_start,
	    		 "search_price_end":search_price_end,
	    		 "search_input":search_input,
	    		 "search_state_arr":search_state_arr
	    }
$("#mallListTable").load("/mallmanage/mallproductlist",data);
	 
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