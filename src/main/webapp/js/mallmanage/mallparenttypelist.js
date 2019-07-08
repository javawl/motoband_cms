function insparenttype(){
	$("#ins_name").val("");
	$("[name = checkbox_brand]:checkbox").prop("checked",false); 
	$("[name = checkbox_brandAll]:checkbox").prop("checked",false); 
}
function addConfirm(){
	var name=$.trim($("#ins_name").val());
	var adminGuid=$("#userGuidHidden").val();
	 var brand_arr=[];
	 $("input[name='checkbox_brand']:checked").each(function() {
	    	brand_arr.push($(this).val());
	    });
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name,
	    "brand_arr":brand_arr
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/insMallParentType',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("添加成功");
				    //	$('#insparenttypeModel').modal('hide');
				    	$('#insparenttypeModel').css('display', 'none');
	  				//	  $('.modal-backdrop').css('display', 'none');
				    	 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/mallparenttypelist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function checkbox_brandAll(){

	   if($("#checkbox_brandAll").get(0).checked){
		   $("[name = checkbox_brand]:checkbox").prop("checked",true);
	   }else{
		   $("[name = checkbox_brand]:checkbox").prop("checked",false);  
	   }

	}
function editparenttype(parentid){
	$("#update_hiddenID").val(parentid);
	var datas =
	{
	    "parentid" :parentid
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/getparenttypeByparentid',
			    data:datas,
			    success : function (data)
			    {
				 
				    if (data!=null)
				    {
				    	var jsondata=eval("("+data+")");
				   
				    	$("#update_name").val(jsondata.name);
				    	
				    	$("[name = update_checkbox_brand]:checkbox").prop("checked",false); 
		    			$("[name = update_checkbox_brandAll]:checkbox").prop("checked",false);
		    			var brandids_arr=jsondata.brandids.split(',');
				    	if(brandids_arr.length>0){
				    		for(var i=0;i<brandids_arr.length;i++){
				    			
				    			$("input[name='update_checkbox_brand']").each(function() {
						    		 
						 	    	if(brandids_arr[i]==$(this).val()){
						 	    		$(this).prop("checked",true);
						 	    	}
						 	    });
				    		}
				    	}
				    	 
				    	
				    }
			    }
			});
}
function editparenttypeConfirm(){
	var parentid=$("#update_hiddenID").val();
	var name=$.trim($("#update_name").val());
	var adminGuid=$("#userGuidHidden").val();
	 var brand_arr=[];
	 $("input[name='update_checkbox_brand']:checked").each(function() {
	    	brand_arr.push($(this).val());
	    });
	if(name==null || name==""){
		alert("name不能为空");
		return ;
	}
	var datas =
	{
	    "name" :name,
	    "brand_arr":brand_arr,
	    "parentid":parentid
	   
	}
 $.ajax (
			{
			    type : "POST",
			    url : '../mallmanage/updateMallParentType',
			    data:datas,
			    success : function (data)
			    {
				  //  console.log(data);
				    if (data=="success")
				    {
				    	alert("编辑成功");
				    	$('#editparenttypeModel').css('display', 'none');
				    	 $(".modal-backdrop").remove();
				    	  $("body").removeClass('modal-open');
				    	$ ("#page-wrapper").load ("/mallmanage/mallparenttypelist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
				    }
			    }
			});

}
function update_checkbox_brandAll(){

	   if($("#update_checkbox_brandAll").get(0).checked){
		   $("[name = update_checkbox_brand]:checkbox").prop("checked",true);
	   }else{
		   $("[name = update_checkbox_brand]:checkbox").prop("checked",false);  
	   }

	}