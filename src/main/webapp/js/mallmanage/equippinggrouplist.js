function addequippinggroup(){
	$("#addgroupid").val("");
	$("#addtitle").val("");
	$("#addsubtitle").val("");
	$("#addurl").val("");
	$("#addorderindex").val("");
	$("input[name='addstate']").removeAttr('checked');
	$("input[name='addtype']").removeAttr('checked');
}

function editequippinggroup(id){
	    var datas =
		{
		    "groupid" : id
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../mallmanage/getEquippinggroupByGroupid",
		    data : datas,
		    success : function (data)
		    {
			    if (data!=null && data!="")
			    {
				  var jsondata=eval("("+data+")");
				  $("#addgroupid").val(jsondata.groupid);
				  $("#addtitle").val(jsondata.title);
				  $("#addsubtitle").val(jsondata.subtitle);
				  $("#addurl").val(jsondata.url);
				  $("#addorderindex").val(jsondata.orderindex);
				  $("input[name='addstate'][value='"+jsondata.state+"']").prop("checked",true);
				  $("input[name='addtype'][value='"+jsondata.type+"']").prop("checked",true);
				 
			    }
		    }
		
		});
	
}

function editequippinggroupConfirm(grouptype){
	var groupid= $("#addgroupid").val();
	var title= $("#addtitle").val();
	var subtitle= $("#addsubtitle").val();
	var url= $("#addurl").val();
	var orderindex= $("#addorderindex").val();
	var state= $("input[name='addstate']:checked").val();
	var type= $("input[name='addtype']:checked").val();;
	
	
	var limit= $("#pageSizeSelect").val();
		
	    var datas =
		{
		    "groupid" : groupid,
		    "title" : title,
		    "subtitle" : subtitle,
		    "url" : url,
		    "state" : state,
		    "orderindex" : orderindex,
		    "type" : type,
		    "grouptype":grouptype
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../mallmanage/insertOrupdateEquippingGroup",
		    data : datas,
		    success : function (data)
		    {
			    if (data=='success')
			    {
				      alert ("修改成功！");
				      $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
			    	  
				      $("#page-wrapper").load("../mallmanage/equippinggrouplist?userGuid=0&page=1&limit="+limit+"&order=0&grouptype="+grouptype);
			    }else{
			    	 alert ("修改失败！");
			    }
		    },
		error :function (data){
			alert ("修改出错！");
		}
		});
}
