$ (document).ready (function ()
{
	initUploadForm ();
});
$ ('input[name=FileContent]').change (function ()
{

	initUploadForm ();
});

function initUploadForm() { 
	$.getJSON('../boxmanage/uploadBoxImg',
		function(data) {

			var sign = data.sign,
				url = data.url + '?sign=' + encodeURIComponent(sign);

			var options = {
				type: 'post',
				url: url,
				dataType: 'json',
				success: function(ret) {
				
					var img_guid = ret.data.fileid;
					var img_url = ret.data.download_url;
					var img_opurl = ret.data.url;
					 var img_name=document.getElementById ("inputfile").files[0].name;
					 var img_groupid=$ ("#XX_nowgroupid").val ();
					 console.log(img_groupid.substring(0,1));
					if(img_groupid.substring(0,1)=="X"){
						img_groupid=img_groupid.substring(3);

					 }
					
					var str = "<div class='col-lg-3' id='XX_"
				        + img_guid
				        + "' onclick=\"javascript:XX_selectImg('XX_"
				        + img_guid
				        + "');\"><div class='panel panel-default'>"
				        + "<div class='panel-body' style='text-align: center;'><img src='"
				        + img_url
				        + "' width='100' height='100'></div>"
				        + "<div class='panel-footer' style='text-align: center; padding: 0px; font-size: 9pt; height: 60px;'>"
				        + "<textarea   rows='2' style='word-break: break-all;'   class='form-control' placeholder='图片名' name='ImgName' value='"
				        + img_name
				        + "' disabled='disabled'>"+img_name+"</textarea></div>"
				        + "<div class='panel-footer' style='text-align: center; padding: 5px 0px;'><input type='button' value='选择' class='btn btn-info' ></div></div>"
				        + "<div class='selected_mask' name='XX_selectdiv' style='display: none'><div class='selected_mask_inner'></div>"
				        + "<div class='selected_mask_icon'></div></div></div>";
					$('#XX_groupimglist').append(str);

				
					var datas = {
						    "img_guid" : img_guid,
					        "img_url" : img_url,
					        "img_opurl" : img_opurl,
					        "img_name" : img_name,
					        "img_groupid" : img_groupid 

					}
					$.ajax({
						type: "POST",
						url: "../boxmanage/addNewBoxImg",
						data: datas,
						success: function(data) {
							if (data != "" && data != null) {
								alert("添加图片资源成功！");
								 $("#back").css("display","none");
								initUploadForm();
							}
						}
					});
				},
				error: function(ret) {
					alert(ret.responseText);
					$("#back").css("display","none");
				}
			};
			
			$('#uploadForm').ajaxForm(options);
		});
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

$ ('#XX_grouplist>div').on ("click", function ()
		{
			XX_showgroupimg($ (this).attr ("id"));
			$ ("#XX_nowgroupid").val ($ (this).attr ("id"));
			$ (this).parent ("#XX_grouplist").find ("div").removeClass ('groupaction');
			$ (this).addClass ('groupaction');
			
});


function imgsubmit(){
	
	 $("#back").css("display","");
}



