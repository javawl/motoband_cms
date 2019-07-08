
function selCarMessage (motoid)
{
	window.open("../carmanage/carMessage?motoid=" + motoid);
}
function typechanged ()
{
	var order= $("#triangleHidden").val();
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	$ ("#page-wrapper").load (
	        "../carmanage/carlist?nowtypeid=" + $ ("#typeselect").val () + "&nowbrandid=" + $ ("#brandselect").val ()+"&page=1&limit="+$("#pageSizeSelect").val()+"&order="+order+"&orderConditions="+orderConditions);
}
function brandchanged ()
{
	var order= $("#triangleHidden").val();
	if(order==''||order == null){
		order=0;
	}
	var orderConditions= $("#triangleHiddenValue").val();
	$ ("#page-wrapper").load (
	        "../carmanage/carlist?nowtypeid=" + $ ("#typeselect").val () + "&nowbrandid=" + $ ("#brandselect").val ()+"&page=1&limit="+$("#pageSizeSelect").val()+"&order="+order+"&orderConditions="+orderConditions);
}
function checkCarName ()
{
	var newcarname = $ ("#newcarname").val ();
	var datas =
	{
		"newcarname" : newcarname
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/checkCarName",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    if (data == "ok")
			    {
				    $ ('#dangertext').css ('display', 'none');
				    $ ('#savebtn').removeClass ('disabled');
			    }
			    else
			    {
				    if (newcarname != null || newcarname != "")
				    {
					    $ ('#dangertext').css ('display', 'block');
					    $ ('#savebtn').addClass ('disabled');
				    }
			    }
		    }
	    }
	});
	
}
function checkCarNewName(oldname){
	var newcarname = $ ("#newcarname").val ();
    var car_id=$("#car_id").val();
	var datas =
	{
		"car_id":car_id,
		"caroldname":oldname,
		"newcarname" : newcarname
	}

	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/checkCarNewName",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    if (data == "ok")
			    {
				    $ ('#dangertext').css ('display', 'none');
				    $ ('#savebtn').removeClass ('disabled');
			    }
			    else
			    {
				    if (newcarname != null || newcarname != "")
				    {
					    $ ('#dangertext').css ('display', 'block');
					    $ ('#savebtn').addClass ('disabled');
				    }
			    }
		    }
	    }
	});
}
function saveNewCar ()
{
	var newcarname = $ ("#newcarname").val ();
	var newcarcc = $ ("#newcarcc").val ();
	var newCar_Type = $ ("#newCar_Type").val ();
	var newCar_Brand = $ ("#newCar_Brand").val ();
	if (newcarname == null || newcarname == "")
	{
		alert ("车名不能为空");
		return false;
	}
	if (newcarcc == null || newcarcc == "")
	{
		alert ("排量不能为空");
		return false;
	}
	
	var datas =
	{
	    "newcarname" : newcarname,
	    "newcarcc" : newcarcc,
	    "newCar_Type" : newCar_Type,
	    "newCar_Brand" : newCar_Brand
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/saveNewCar",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("添加成功！");
			    $ ("#newcarname").val ("");
			    
		    }
	    }
	});
}
function insertNewCarType ()
{
	var newcartype = $ ("#newcartype").val ();
	if(newcartype==null||newcartype==""){
		alert("类型名不能为空！");
		return false;
	}
	var datas =
	{
		"newcartype" : newcartype
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/insertNewCarType",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("添加成功！");
			    $ ("#page-wrapper").load ("../carmanage/carlist");
		    }
	    }
	});
	
}
function checkCarType ()
{
	var newCarTypeName = $ ("#newcartype").val ();
	var datas =
	{
		"newCarTypeName" : newCarTypeName
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/checkCarType",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    if (data == "ok")
			    {
				    $ ('#dangertypetext').css ('display', 'none');
				    $ ('#saveTypebtn').removeClass ('disabled');
			    }
			    else
			    {
				    if (newCarTypeName != null || newCarTypeName != "")
				    {
					    $ ('#dangertypetext').css ('display', 'block');
					    $ ('#saveTypebtn').addClass ('disabled');
				    }
			    }
		    }
	    }
	});
}
function insertNewCarBrand ()
{
	var newcarbrand = $ ("#newcarbrand").val ();
	var bpid = $("#newcarbrandparentname").val();
	alert(bpid);
	if(newcarbrand==null||newcarbrand==""){
		alert("大品牌名不能为空！");
		return false;
	}
	var datas =
	{
		"newcarbrand" : newcarbrand,
		"bpid" : bpid
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/insertNewCarBrand",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    alert ("添加成功！");
			    $ ("#page-wrapper").load ("../carmanage/carlist");
		    }
	    }
	});
	
}
function checkCarBrand ()
{
	var newCarBrand = $ ("#newcarbrand").val ();
	var datas =
	{
		"newCarBrand" : newCarBrand
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/checkCarBrand",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
			    if (data == "ok")
			    {
				    $ ('#dangerbrandtext').css ('display', 'none');
				    $ ('#saveBrandbtn').removeClass ('disabled');
			    }
			    else
			    {
				    if (newCarBrand != null || newCarBrand != "")
				    {
					    $ ('#dangerbrandtext').css ('display', 'block');
					    $ ('#saveBrandbtn').addClass ('disabled');
				    }
			    }
		    }
	    }
	});
}
function saveCarMessage ()
{
	var car_id = $ ("#car_id").val ();
	var car_name = $ ("#newcarname").val ();
	var car_cc = $ ("#car_cc").val ();
	var car_type = $ ("#typeselect").val ();
	var car_brand = $ ("#brandselect").val ();
	if(car_name==null||car_name==""){
		alert("摩托车名不能为空！");
		return false;
		 
	}
	if(car_cc==null||car_cc==""){
		alert("摩托车排量不能为空！");
		return false;
		 
	}
	
	
	var datas =
	{
	    "car_id" : car_id,
	    "car_name" : car_name,
	    "car_cc" : car_cc,
	    "car_type" : car_type,
	    "car_brand" : car_brand
	
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../carmanage/saveCarMessage",
	    data : datas,
	    success : function (data)
	    {
		    if (data != "" && data != null)
		    {
		    	 alert("保存成功");
				 window.opener=null;
			     window.open('','_self');
			     window.close();
//		    	 location.reload() ;
			 /*   $ ("#page-wrapper").load ("../carmanage/carMessage?motoid=" + car_id);*/
		    }else{
		    	 alert("保存成功");
		    }
	    }
	});
}
function delCarGuid(car_id){
	$("#delCarGuid").val(car_id);
}
function delCarMessage(){
	var car_id=$("#delCarGuid").val();
	var datas={
			"car_id":car_id
	}
	$.ajax (
			{
			    type : "POST",
			    url : "../carmanage/delCarMessage",
			    data : datas,
			    success : function (data)
			    {
				    if (data != "" && data != null)
				    {
					    alert ("删除成功");
					    $ ("#page-wrapper").load ("../carmanage/carlist");
				    }
			    }
			});
}
function insertbrandbp(){
	$("#newcarbrand").val("");
	$("#newcarbrandparentname").empty();
    var str="";
	$.ajax (
			{
			    type : "POST",
			    url : "../carmanage/selectCarbrandParentname",
			    success : function (data)
			    {
				    if (data != "" && data != null)
				    {
					   var jsondata =eval("("+data+")");
					   for(var i=0;i<jsondata.length;i++){
						  str+="<option value='"+jsondata[i].bpid+"'>"+jsondata[i].name+"</option>"; 
					   }
					   $("#newcarbrandparentname").append(str);
				    }
			    }
			});
}
function updateversion (sign,userGuid)
{  
	if(confirm("真的要更新车辆版本么，此操作不可恢复")){
	datas =
	{
		"sign" : sign
	}
	$.ajax (
	{
	    type : "POST",
	    url : "../dataversion/updateversion",
	    data : datas,
	    success : function (data)
	    {
	    	if(data!=null&&data!=""){
	    		alert("更新版本成功");
		    	$("#page-wrapper").load("../carmanage/carlist?userGuid="+userGuid+"&page=1&limit=20&order=0");
	    	}
	    	
	    }
	});
	}
}