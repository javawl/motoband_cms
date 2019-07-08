function lookCar(payid){
	var datas =
	{
	    "payid" : payid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/lookCar',
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null){
			   var datajson = eval("("+data+")");
			  $("#brand").val(datajson.brand);
			  $("#carCheckCode").val(datajson.carCheckCode);
			  $("#displacement").val(datajson.displacement);
			  $("#enginNumber").val(datajson.enginNumber);
			  $("#carRightBehindPhoto").attr("src",datajson.imgInfoURL.carRightBehindPhoto);
			  $("#identityPositivePhoto").attr("src",datajson.imgInfoURL.identityPositivePhoto);
			  $("#invoicePhoto").attr("src",datajson.imgInfoURL.invoicePhoto);
			  $("#carLeftBehindPhoto").attr("src",datajson.imgInfoURL.carLeftBehindPhoto);
			  $("#drivingPhoto").attr("src",datajson.imgInfoURL.drivingPhoto);
			  $("#carRightFrontPhoto").attr("src",datajson.imgInfoURL.carRightFrontPhoto);
			  $("#carLeftFrontPhoto").attr("src",datajson.imgInfoURL.carLeftFrontPhoto);
			  $("#identityNegativePhoto").attr("src",datajson.imgInfoURL.identityNegativePhoto);
			  $("#model").val(datajson.model);
			  $("#plateNumber").val(datajson.plateNumber);
			  $("#policyRecipientAddress").val(datajson.policyRecipientAddress);
			  $("#policyRecipientName").val(datajson.policyRecipientName);
			  $("#policyRecipientPhone").val(datajson.policyRecipientPhone);
			  $("#purchasePrice").val(datajson.purchasePrice/100+"元");
			  $("#purchaseTime").val(datajson.purchaseTime);
			  $("#regdate").val(datajson.regdate);
			  $("#vin").val(datajson.vin);
			  
		    }
		    
	    }
	});
}
function lookUser(payid){
	var datas =
	{
	    "payid" : payid
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/lookUser',
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null){
			   var datajson = eval("("+data+")");
			  $("#userIDInfo").val(datajson.userIDInfo);
			  $("#userKey").val(datajson.userKey);
			  $("#userName").val(datajson.userName);
			  $("#enginNumber").val(datajson.enginNumber);
			  $("#userPhone").val(datajson.userPhone);
			  $("#userEmail").val(datajson.userEmail);
		    }
		    
	    }
	});
}
function lookInsurance(order_no){
	var datas =
	{
	    "order_no" : order_no
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/lookInsurance',
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null){
			   var datajson = eval("("+data+")");
			 
			  $("#iid").val(datajson.iid);
			  $("#channelOrderNo").val(datajson.channelOrderNo);
			  $("#productNo").val(datajson.productNo);
			  $("#policyNo").val(datajson.policyNo);
			  $("#insuranceCode").val(datajson.insuranceCode);
			  $("#policyholder").val(datajson.policyholder);
			  $("#userName1").val(datajson.userName);
			  $("#createTime").val(datajson.createTime);
			  $("#policyBeginDate").val(datajson.policyBeginDate);
			  $("#policyEndDate").val(datajson.policyEndDate);
			  $("#purchaseTime").val(datajson.purchaseTime);
			  $("#premium").val(datajson.premium/100+"元");
			  $("#suminsured").val(datajson.suminsured/100+"元");
			  var status=datajson.policyStatus;
			  switch (status)
			  {
			  case 1:
				  $("#policyStatus").val("核保——提交资料");
			    break;
			  case 2:
				  $("#policyStatus").val("撤单");
			    break;
			  case 3:
				  $("#policyStatus").val("核保——核保中");
			    break;
			  case 4:
				  $("#policyStatus").val("核保——核保失败");
			    break;
			  case 5:
				  $("#policyStatus").val("核保——核保成功");
			    break;
			  case 6:
				  $("#policyStatus").val("承保——承保成功生效");
			    break;
			  case 7:
				  $("#policyStatus").val("生效");
			    break;
			  case 8:
				  $("#policyStatus").val("理赔——申请理赔");
			    break;
			  case 9:
				  $("#policyStatus").val("理赔——核赔中");
			    break;
			  case 10:
				  $("#policyStatus").val("理赔——拒赔");
			    break;
			  case 11:
				  $("#policyStatus").val("理赔——同意理赔");
			    break;
			  case 12:
				  $("#policyStatus").val("理赔——待支付理赔金");
			    break;
			  case 13:
				  $("#policyStatus").val("终止——理赔终止");
			    break;
			  case 14:
				  $("#policyStatus").val("终止——过期终止");
			    break;
			  case 15:
				  $("#policyStatus").val("终止——违约终止");
			    break;
			  case 16:
				  $("#policyStatus").val("终止——投保人解除终止");
			    break;
			  case 17:
				  $("#policyStatus").val("终止——其他终止");
			    break;
			  case 18:
				  $("#policyStatus").val("失效");
			    break;
			  }
			  
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
function idAndOrderChange(obj){
	var a = $(obj).val();
	if(a=='0'){
		$("#idAndOrder0").show();
		$("#idAndOrder1").hide();
	}
	if(a=='1'){
		$("#idAndOrder1").show();
		$("#idAndOrder0").hide();
	}
}
function changePolicyStatus(orderNo,policyStatus){
	$("#updatepolicyStatus option[value='"+policyStatus+"'] ").prop("selected","selected");
	$("#policyStatusHidden").val(orderNo);
}
function changePolicyStatusConfirm(){
	var policyStatus=$("#updatepolicyStatus").val();
	var channelOrderNo=$("#policyStatusHidden").val();
	var admin=$("#GuidHidden").val();
	var datas =
	{
	    "channelOrderNo" : channelOrderNo,
	    "policyStatus":policyStatus
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/changePolicyStatus',
	    data : datas,
	    success : function (data)
	    {
		    if (data =='success'){
			    alert("修改成功");
			    $ ("#page-wrapper").load ("../payordermanage/payorderlist?userGuid="+admin+"&page=1&limit=20&order=0");
		    }else{
		    	 alert("修改失败");
		    }
		    
	    }
	});
}
function refreshpolicystatus(payid,userid,channelOrderNo){
	var datas =
	{
	    "userid" : userid,
	    "channelOrderNo":channelOrderNo
	}

	$.ajax (
	{
	    type : "POST",
	    url : '../payordermanage/refreshpolicystatus',
	    data : datas,
	    success : function (data)
	    {
		    if (data =='fail'){
		    	alert("刷新失败");
		    }else{
		     //  alert("刷新成功");
		      $("."+payid).find("td").eq(11).text(data); 
		    }
		    
	    }
	});
}