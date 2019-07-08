
function deletebox(aid){
	$("#delAid").val(aid);
}
function addbox(){
	$("#hiddenValue").val("2");
	$("#ins_orderindex").val("");
	var str="";
	var str1="";
	$.ajax (
			{
			    type : "POST",
			    url : "../motoschoolmanage/getPackageTitleList",
			    async:false,
			    success : function (data)
			    {
				    if (data !=''&& data !=null)
				    {
				    	var datajson1 = eval("("+data+")");
				    	for(var i=0;i<datajson1.length;i++){
				    		
					    		str+="<option value='"+datajson1[i].pid+"' >"+datajson1[i].title+"</option>";
					    	
				    	}
				    	$("#ins_pid").empty();
				    	$("#ins_pid").append(str);
				    	
				    }
				    
			    }
			});
	$.ajax (
			{
			    type : "POST",
			    url : "../boxmanage/getBoxTitleList",
			    async:false,
			    success : function (data)
			    {
				    if (data !=''&& data !=null)
				    {
				    	var datajson2 = eval("("+data+")");
				    	for(var i=0;i<datajson2.length;i++){
				    		
					    		str1+="<option value='"+datajson2[i].boxid+"' >"+datajson2[i].title+"</option>";
					    	
				    	}
				    	$("#ins_boxid").empty();
				    	$("#ins_boxid").append(str1);
				    	
				    }
				    
			    }
			});
}

function delConfirm(){
	/*var adid= $("#delAdid").val();
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
		    	$ ("#page-wrapper").load ("/admanage/adlist?page=1&limit=20&order=0");
		    }
		    
	    }
	});*/
}

function addConfirm(){
	var adminGuid= $("#userGuidHidden").val();
	 var boxid=	$("#ins_boxid").val();
	 var pid=	$("#ins_pid").val();
	  var state=$("#ins_state").val();
	  var orderindex=	$.trim($("#ins_orderindex").val());
	  
		if (orderindex == null || orderindex== "")
		{
			alert ("orderindex不能为空");
			return false;
		}

	  
	  var datas =
		{
				"boxid" : boxid,
				"pid" : pid,
				"orderindex" : orderindex,
				"state":state
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../motoschoolmanage/insBox",
		    data : datas,
		    success : function (data)
		    {
			    if (data =='success')
			    {
			    	alert("添加成功");
			  //  	$('#addbox').modal('hide');
			  	  $('#addbox').css('display', 'none');
			//	  $('.modal-backdrop').css('display', 'none');
			  	 $(".modal-backdrop").remove();
		    	  $("body").removeClass('modal-open');
			    	$ ("#page-wrapper").load ("../motoschoolmanage/boxlist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
			    }
			    
		    }
		});
}


function editbox(bid){
	$("#hiddenValue").val("1");
	
	 var str="";
	 var str1="";
	  var datas =
		{
			"bid" :bid
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../motoschoolmanage/getBoxBybid",
		    data : datas,
		    success : function (data)
		    {
			    if (data !=''&& data !=null)
			    {
			    	var datajson = eval("("+data+")");
			    	$("#update_bid").val(datajson.bid);
			    	
			    	$("#update_orderindex").val(datajson.orderindex);
			    	$("#update_state").val(datajson.state);
			    	
			    	$.ajax (
							{
							    type : "POST",
							    url : "../motoschoolmanage/getPackageTitleList",
							    async:false,
							    success : function (data)
							    {
								    if (data !=''&& data !=null)
								    {
								    	var datajson1 = eval("("+data+")");
								    	for(var i=0;i<datajson1.length;i++){
								    		if(datajson.pid==datajson1[i].pid){
									    		str+="<option value='"+datajson1[i].pid+"' selected>"+datajson1[i].title+"</option>";
									    	}else{
									    		str+="<option value='"+datajson1[i].pid+"' >"+datajson1[i].title+"</option>";
									    	}
								    	}
								    	$("#update_pid").empty();
								    	$("#update_pid").append(str);
								    	
								    }
								    
							    }
							});
			    	$.ajax (
							{
							    type : "POST",
							    url : "../boxmanage/getBoxTitleList",
							    async:false,
							    success : function (data)
							    {
								    if (data !=''&& data !=null)
								    {
								    	var datajson2 = eval("("+data+")");
								    	for(var i=0;i<datajson2.length;i++){
								    		if(datajson.boxid==datajson2[i].boxid){
									    		str1+="<option value='"+datajson2[i].boxid+"' selected>"+datajson2[i].title+"</option>";
									    	}else{
									    		str1+="<option value='"+datajson2[i].boxid+"' >"+datajson2[i].title+"</option>";
									    	}
								    	}
								    	$("#update_boxid").empty();
								    	$("#update_boxid").append(str1);
								    	
								    }
								    
							    }
							});
			    }
			    
		    }
		});
}

function editConfirm(){
	var adminGuid= $("#userGuidHidden").val();
	 var bid=	$("#update_bid").val();
	 var boxid=	$("#update_boxid").val();
	 var pid=	$("#update_pid").val();
	  var orderindex=$.trim($("#update_orderindex").val());
	  var state =$("#update_state").val();
	  if (boxid == null || boxid == "")
		{
			alert ("boxid不能为空");
			return false;
		}
	  if (pid == null || pid == "")
		{
			alert ("pid不能为空");
			return false;
		}
	
		if (orderindex == null || orderindex== "")
		{
			alert ("orderindex不能为空");
			return false;
		}

	  
	  var datas =
		{
			"bid" : bid,
			"boxid" : boxid,
			"pid" : pid,
			"orderindex" : orderindex,
			"state":state
			
		}
		$.ajax (
		{
		    type : "POST",
		    url : "../motoschoolmanage/updateBox",
		    data : datas,
		    success : function (data)
		    {
			    if (data =='success')
			    {
			    	alert("编辑成功");
			    	 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
			    	$ ("#page-wrapper").load ("../motoschoolmanage/boxlist?userGuid=" + adminGuid+"&page=1&limit=20&order=0");
			    }
			    
		    }
		});
}
