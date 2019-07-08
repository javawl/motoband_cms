function startjob(status){
	var islikecount=$("#islikecount").val();
	var isgiftcount=$("#isgiftcount").val();
	var isfollowcount=$("#isfollowcount").val();
	  var datas =
		{
			"status" :status,
			"islikecount":islikecount,
			"isgiftcount":isgiftcount,
			"isfollowcount":isfollowcount
			
		}
	$.ajax (
			{
			    type : "POST",
			    url : "/botuser/startjob",
			    data : datas,
			    success : function (data)
			    {
			    	 $(".modal-backdrop").remove();
			    	  $("body").removeClass('modal-open');
			    	var url="/botuser/botuserlist?userGuid=0&page=1&limit=20&order=0"
			    		 $("#page-wrapper").load (url);
//			    	window.location.reload()
				    	 
//				    if (data !=''&& data !=nULL&&DATA=="TRUE")
//				    {
//				    	$("#addsid").val(data.sid);
//				    	$("#addname").val(data._name);
//				    	$("#addprovince").val(data.province);
//	                    $("#addcity").val(data.city);
//	                    $("#addlonandlat").val(data.longitude+","+data.latitude);
//				    }
				    
			    }
			});
};