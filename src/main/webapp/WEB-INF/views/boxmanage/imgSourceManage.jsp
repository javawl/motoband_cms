<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("path", ctx);
%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>MotoBand CMS</title>

<!-- Bootstrap Core CSS -->
<link href="../bower_components/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../bower_components/metisMenu/dist/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../bower_components/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
	

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
 <script type="text/javascript" src="../dist/cos-js-sdk-v5.js"></script>
<style type="text/css">
.groupdiv {
	width: 90%;
	margin: 5%;
	margin-right: 12%;
	text-align: center;
	line-height: 120px;
	height: 120px;
	background-color: #8c8c8c;
	font-size: 14pt;
	text-align: center;
	color: #ffffff;
}

.groupaction {
	filter: alpha(opacity = 50);
	opacity: 0.5;
}
.progress {
   width: 100%;
    height: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    margin: 10px 0px;
    overflow: hidden;
}
/* 初始状态设置进度条宽度为0px */
.progress > div {
    width: 0px;     
    height: 100%;
    background-color: yellowgreen;
    transition:width 0.2s;
-moz-transition:width 0.2s; /* Firefox 4 */
-webkit-transition:width 0.2s; /* Safari and Chrome */
-o-transition:width 0.2s; /* Opera */
}
</style>
<script>
	$ ('#grouplist > div').on ("click", function ()
    {
	    showgroupimg ($ (this).attr ("id"));
	    $ ("#nowgroupid").val ($ (this).attr ("id"));
	    $ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
	    $ (this).addClass ('groupaction');
	    
    });
	var Bucket = 'motobox-10013836';
	var Region = 'ap-shanghai';
	// 初始化实例
	var cos = new COS({getAuthorization: function (options, callback) {
	        var url = '../boxmanage/getSignUrl';
	        var xhr = new XMLHttpRequest();
	        xhr.open('post', url, true);
	        xhr.crossDomain=true;
	        xhr.onload = function (e) {
	            try {
	                var data = JSON.parse(e.target.responseText);
// 	                $ ('#downloadRet').show ();
	            } catch (e) {
	            }
	            callback({
	                TmpSecretId: data.credentials && data.credentials.tmpSecretId,
	                TmpSecretKey: data.credentials && data.credentials.tmpSecretKey,
	                XCosSecurityToken: data.credentials && data.credentials.sessionToken,
	                ExpiredTime: data.expiredTime,
	            });
	        };
	        xhr.send();
	    }
	});

	// 监听选文件
	document.getElementById('file2').onchange = function () {

	    var file = this.files[0];
	    if (file==null) {
	    	return;	
	    }
		var filename = "", groupid = "";
		var map = {}, key="";
		filename = file.name;
		groupid = $ ("#nowgroupid").val ();
		key = Math.uuidFast ();
		map[key] = filename;
	    // 分片上传文件
	    cos.sliceUploadFile({
	        Bucket: Bucket,
	        Region: Region,
	        Key: key,
	        Body: file,
	        onHashProgress: function (progressData) {
	            console.log('校验中', JSON.stringify(progressData));
	        },
	        onProgress: function (progressData) {
	            var progressRate = (progressData.loaded / progressData.total) * 100 + '%';
                //通过设置进度条的宽度达到效果
                $('.progress > div').css('width', progressRate);
	            console.log('上传中', JSON.stringify(progressData));
                if(progressRate =="100%"){
			        console.log ("添加图片资源成功！");
                }
	        },
	    }, function (err, data) {
	    	if(err!=null){
	    		console.log(err)
	    	}else if(data!=null){
	    		//Bucket: "motobox-10013836"
// ETag: ""b84e09d66d9529350766afed77ee0e16-1""
// Key: "20150102204502_YZyY4.jpeg"
// Location: "motobox-10013836.cos.ap-shanghai.myqcloud.com/20150102204502_YZyY4.jpeg"
	    		console.log(data);	
	    		var url="https://"+data.Location;
	    		var img_guid = data.Key;
			    var img_url = url;
			    var img_opurl = url;
			    var img_name = map[data.Key];
			    var datas =
			    {
			        
			        "img_guid" : img_guid,
			        "img_url" : img_url,
			        "img_opurl" : img_opurl,
			        "img_name" : img_name,
			        "img_groupid" : groupid
			    
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
					        var json = eval ("(" + data + ")");
					        addimgdiv (json);
					        $("#back").css("display","none");
					        // $ ("#page-wrapper").load
					        // ("../boxmanage/imgSourceManage");
				        }
				        
			        },
				    error: function(XMLHttpRequest, textStatus, errorThrown) {
			            alert("上传失败，点击重试");
			            $("#back").css("display","none");
			        }
			    });
	    	}
	        
	    });

	};
</script>
</head>
<body>
<div id="back" style="display:none; POSITION:fixed;z-index: 1000;left:0; top:0; width:100%; height:100%; background-color:rgba(0, 0, 0, 0.6); filter:alpha(opacity=60)">
				<h3><font color="white">上传中，请耐心等待</font></h3></div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">图片管理</h1>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading" style="font-size: 16pt">
						图片资源列表 <input type="button" value="增加图片分组" class="btn btn-success"
						    data-toggle="modal" data-target="#insertImgGroupModel"
							>
							<input type="button" value="删除" class="btn btn-success"
							data-toggle="modal" data-target="#delSelectedImgModel"
							>
							<input type="button" value="更换图片组" class="btn btn-success"
							data-toggle="modal" data-target="#imgListChangeGroupModel"
							>
					</div>
					<div class="panel-body" style="overflow: auto; height: 55%">
						<div
							style="float: left; width: 24%; padding-right: 0px; height: 100%; border: 1px solid #000000; overflow: auto;"
							id="grouplist">
							<input type="text" style="display: none"
								value="${imggroups[0].group_guid}" id="nowgroupid">
							<c:forEach items="${imggroups}" var="imggroup" varStatus="ids">
								<div
									class="groupdiv <c:if test='${ids.index==0}'>groupaction</c:if>"
									id="${imggroup.group_guid}">${imggroup.group_name}</div>
							</c:forEach>
						</div>
						<div
							style="float: left; width: 75%; padding-left: 0px; border: 1px solid #000000; height: 100%; overflow: auto;"
							id="groupimglist">
							<c:forEach items="${motoimgs}" var="imgMessages" varStatus="ids">
								<div class="col-lg-3" id="${imgMessages.img_guid }">
								    <div style="z-index: 10000;float: left;">
								    <input type="checkbox" style="margin: 2px 2px;width: 20px;height: 20px;" name="imgcheck" value="${imgMessages.img_guid }">
								    </div>
									<div class="panel panel-default">
										<div class="panel-body" style="text-align: center;">
											<img src="${imgMessages.img_url }!thumb" width="100" height="100">
										</div>
										<div class="panel-footer"
											style="text-align: center; padding: 0px; font-size: 9pt; height: 60px;">
											<textarea   rows='2' style='word-break: break-all;' class="form-control" placeholder="图片名" name="ImgName"
												 disabled="disabled">${imgMessages.img_name }</textarea>
										</div>
										<div class="panel-footer"
											style="text-align: center; padding: 5px 0px;">
											<button type="button" class="btn btn-success btn-dropbox"
												onclick="javascript:editImgName('${imgMessages.img_guid }')">
												<i name="editName" class="fa fa-edit"></i>
											</button>
											<button type="button" class="btn btn-primary btn-dropbox"
												data-toggle="modal" data-target="#imgChangeGroupModel"
												onclick="javascript:showimgChangeGroup('${imgMessages.img_guid}');">
												<i class="fa fa-exchange"></i>
											</button>
											<button type="button" class="btn btn-warning btn-dropbox"
											    data-toggle="modal" data-target="#delSingleImgModel"
												onclick="javascript:delImg('${imgMessages.img_opurl}','${imgMessages.img_guid}');">
												<i class="fa fa-trash"></i>
											</button>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- body -->
				</div>
				<div class="col-lg-12" style="padding: 0; margin-top: 10px">
					<div class="well">
						<h3>图片上传</h3>
						<form id="uploadForm" style="display: none">
							<input type="file" id="file" name="FileContent"
								class="btn btn-info" multiple="multiple"></input> <input
								id="subbtn" type="submit" class="btn btn-info"
								style="margin: 10px; margin-left: 0">
						</form>

						<input type="file" id="file2" class="btn btn-info"
							multiple="multiple" accept="image/gif,image/jpg,image/jpeg,image/png,image/bmp"/>
							<div class="progress">
							    <div></div>
							</div>
<!-- 							 <input -->
<!-- 							type="button" value="提交" class="btn btn-info" -->
<!-- 							onclick="javascript:subfile()"> -->

						<div id="downloadRet" style="display: none">
							<h4>显示图片</h4>
							<span id="downloadUrl">${noteModel.note_imgurl }</span> <input
								id="downloadBtn" type="button" value="显示" class="btn btn-info">
							<br /> <img id="downloadImg" src="${noteModel.note_imgurl }"></img>
							<h3>文件ID</h3>
							<div id="fileid">${noteModel.note_imgfileid}</div>
							<h3>管理URL</h3>
							<span id="url"></span> <input id="queryBtn" type="button"
								value="查询" class="btn btn-info"> <input id="deleteBtn"
								type="button" value="删除" class="btn btn-info"><span
								id="imgInfo"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- saveRoleConf -->
	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>
	<script src="../js/boxmanage/jquery.form.min.js"></script>


	<!-- Modal -->
	<div class="modal fade" id="insertImgGroupModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增图片组</h4>
				</div>
				<div class="modal-body">			
					<div class="form-group">
						<input class="form-control" placeholder="新图片组名称" id="groupname" value="" onblur="javascript:checkGroupName()">
						<p class="text-danger" id="dangertext" style="display: none">组名已被使用！</p>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:addImgGroup();" id="savebtn">保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- Modal -->
	<div class="modal fade" id="imgListChangeGroupModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">图片更改分组</h4>
				</div>
				<div class="modal-body">			
					<div class="form-group">
							<c:forEach items="${imggroups}" var="imggroup" varStatus="ids">
								<input type="radio" name="imglistnewgroup"  value="${imggroup.group_guid}"
							>${imggroup.group_name}
							</c:forEach>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveimgListNewGroup()" >保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<!-- Modal -->
	<div class="modal fade" id="imgChangeGroupModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">图片更改分组</h4>
				</div>
				<div class="modal-body">			
						<input class="form-control" id="changeimgguid"
							value="" style="display:none">
					<div class="form-group">
							<c:forEach items="${imggroups}" var="imggroup" varStatus="ids">
								<input type="radio" name="imgnewgroup"  value="${imggroup.group_guid}"
							>${imggroup.group_name}
							</c:forEach>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:saveimgNewGroup()" >保存</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<div class="modal fade" id="delSingleImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除这张图片</h4>
					<input class="form-control" id="del_img_opurl"
							value="" style="display:none">
					<input class="form-control" id="del_img_guid"
							value="" style="display:none">
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delBoxGuid">
					<h3>删除图片后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:delSingleImgConfirm()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<div class="modal fade" id="delSelectedImgModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>

					<h4 class="modal-title" id="myModalLabel">删除选中图片</h4>
				</div>
				<div class="modal-body">
					<input type="text" value="" style="display: none" id="delBoxGuid">
					<h3>删除图片后无法恢复</h3>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="closedelBoxModel">关闭</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						onclick="javascript:DelImgList()">确认删除</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
		<script src="../js/boxmanage/imgSourceManage.js"></script>
</body>
</html>