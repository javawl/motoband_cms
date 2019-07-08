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
<!-- jQuery -->
<script src="../bower_components/jquery/dist/jquery.min.js"></script>
<!-- <link type="text/css" rel="stylesheet" href="../css/timer/admin.css"/> -->
<!-- <link href="../css/wysi/bootstrap-combined.no-icons.min.css"
	rel="stylesheet">-->
<!-- <link href="../css/wysi/bootstrap-responsive.min.css" rel="stylesheet"> -->
<link
	href="http://netdna.bootstrapcdn.com/font-awesome/3.0.2/css/font-awesome.css"
	rel="stylesheet">
<link rel="stylesheet" href="../css/wysi/wysi.css" type="text/css">
</link>
<!-- <link rel="stylesheet" type="text/css" href="../css/timer/jquery-ui.css" /> -->
<script type="text/javascript"
	src="../js/jquery-ui-1.10.4.custom.min.js"></script>
<!-- 	<script type="text/javascript" src='../js/boxmanage/seed-min.js'></script>
		<script type="text/javascript" src='../js/boxmanage/editor.js'></script>
			<script type="text/javascript" src='../js/boxmanage/avalon.js'></script> -->
<!--<script type="text/javascript" src="../js/jquery.ui.datepicker-zh-CN.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-addon.js"></script>
<script type="text/javascript" src="../js/jquery-ui-timepicker-zh-CN.js"></script> -->


<link rel="stylesheet" type="text/css"
	href="../css/bootstrap-datetimepicker.min.css" />
<script src="../js/bootstrap-datetimepicker.js"
	type="text/javascript" charset="utf-8"></script>
<script src="../js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript" charset="utf-8"></script>

<script src="../js/wysi/bootstrap-wysiwyg.js" type="text/javascript"></script>
<script src="../js/wysi/jquery.hotkeys.js" type="text/javascript"></script>


<link rel="stylesheet" href="../css/boxmanage/default/default.css" />
<link rel="stylesheet" href="../css/boxmanage/code/prettify.css" />
<script charset="utf-8" src="/js/boxmanage/kindeditor.js"></script>
<script charset="utf-8" src="../css/boxmanage/code/prettify.js"></script>


<style type="text/css">
.k {
	display: none;
}

.ui-timepicker-div .ui-widget-header {
	margin-bottom: 8px;
}

.ui-timepicker-div dl {
	text-align: left;
}

.ui-timepicker-div dl dt {
	float: left;
	clear: left;
	padding: 0 0 0 5px;
}

.ui-timepicker-div dl dd {
	margin: 0 10px 10px 45%;
}

.ui-timepicker-div td {
	font-size: 90%;
}

.ui-tpicker-grid-label {
	background: none;
	border: none;
	margin: 0;
	padding: 0;
}

.ui-timepicker-rtl {
	direction: rtl;
}

.ui-timepicker-rtl dl {
	text-align: right;
	padding: 0 5px 0 0;
}

.ui-timepicker-rtl dl dt {
	float: right;
	clear: right;
}

.ui-timepicker-rtl dl dd {
	margin: 0 45% 10px 10px;
}

#editor {
	overflow: scroll;
	max-height: 650px
}

.selected_mask {
	padding-bottom: 20px;
	padding-left: 15px;
	padding-right: 15px;
	position: absolute;
	width: 100%;
	height: 100%;
	top: 0;
	left: 0;
	position: absolute;
}

.selected_mask_inner {
	width: 100%;
	height: 100%;
	-moz-opacity: .6;
	-khtml-opacity: .6;
	opacity: .6;
	background-color: #000;
	filter: alpha(opacity = 60);
}

.selected_mask_icon {
	position: absolute;
	top: 0;
	left: 0;
	background: transparent
		url(../css/boxmanage/icon_card_selected218877.png) no-repeat 0 0;
	width: 100%;
	height: 100%;
	vertical-align: middle;
	display: inline-block;
	background-position: 50% 50%;
}

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
</style>
<script>
	$ ('#grouplist > div').on ("click", function ()
    {
	    showgroupimg ($ (this).attr ("id"));
	    $ ("#nowgroupid").val ($ (this).attr ("id"));
	    $ (this).parent ("#grouplist").find ("div").removeClass ('groupaction');
	    $ (this).addClass ('groupaction');
	    
    });
</script>
<script type="text/javascript">
	function cursorControl (a)
    {
	    this.element = a;
	    this.range = !1;
	    this.start = 0;
	    this.init ();
    };
    cursorControl.prototype =
    {
        init : function ()
        {
	        var _that = this;
	        this.element.onkeyup = this.element.onmouseup = function ()
	        {
		        this.focus ();
		        if (document.all)
		        {
			        _that.range = document.selection.createRange ();
		        }
		        else
		        {
			        _that.start = _that.getStart ();
		        }
	        }
        },
        getType : function ()
        {
	        return Object.prototype.toString.call (this.element).match (/^\[object\s(.*)\]$/)[1];
        },
        getStart : function ()
        {
	        if (this.element.selectionStart || this.element.selectionStart == '0')
	        {
		        return this.element.selectionStart;
	        }
	        // else if (window.getSelection){
	        // var rng =
	        // window.getSelection().getRangeAt(0).cloneRange();
	        // rng.setStart(this.element,0);
	        // return rng.toString().length;
	        // }
        },
        inserteditorText : function (text)
        {
	        this.element.focus ();
	        if (document.all)
	        {
		        document.selection.empty ();
		        this.range.text = text;
		        this.range.collapse ();
		        this.range.select ();
	        }
	        else
	        {
		        if (this.getType () == 'HTMLDivElement')
		        {
			        // this.element.innerHTML=this.element.innerHTML.substr(0,this.start)+text+this.element.innerHTML.substr(this.start);
			        // Begain of The Content added by bedweather
			        var sel = window.getSelection ();
			        var rang = sel.rangeCount > 0 ? sel.getRangeAt (0) : null;
			        if (rang == undefined
			                || rang == null
			                || (rang.commonAncestorContainer.id != "editor" && rang.commonAncestorContainer.parentNode.id != "editor"))
			        {
				        this.element.focus ();
				        rang = document.createRange ();
				        rang = selectNode (this.element);
				        rang.setStart (range.getEndContainer, rang.endOffset);
			        }
			        rang.deleteContents ();
			        rang.insertNode (rang.createContextualFragment (text));
			        var tempRange = document.createRange ();
			        var a = document.getElementById ("editor")
			        tempRange.selectNodeContents (a);
			        if (rang.commonAncestorContainer.id == "editor")
			        {
				        tempRange.setStart (rang.endContainer, rang.endOffset + 1);
				        tempRange.setEnd (rang.endContainer, rang.endOffset + 1);
			        }
			        else
			        {
				        tempRange.setStartAfter (rang.endContainer.nextSibling);
				        tempRange.setEndAfter (rang.endContainer.nextSibling);
			        }
			        sel.removeAllRanges ();
			        sel.addRange (tempRange);
			        this.element.focus ();
			        // End of The Content added by bedweather
		        }
		        else
		        {
			        this.element.value = this.element.value.substr (0, this.start) + text
			                + this.element.value.substr (this.start);
		        }
		        ;
	        }
        },
        getText : function ()
        {
	        if (document.all)
	        {
		        var r = document.selection.createRange ();
		        document.selection.empty ();
		        return r.text;
	        }
	        else
	        {
		        if (this.element.selectionStart || this.element.selectionStart == '0')
		        {
			        var text = this.getType () == 'HTMLDivElement' ? this.element.innerHTML : this.element.value;
			        return text.substring (this.element.selectionStart, this.element.selectionEnd);
		        }
		        else if (window.getSelection)
		        {
			        return window.getSelection ().toString ()
		        }
		        ;
	        }
        }
    };
    var c2;
    function fn2 (str)
    {
	    /* c2 = new cursorControl (document.getElementById ('editor'));
	    c2.inserteditorText (str); */

    };
</script>
</head>
<body>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">手册管理——添加分享页</h1>
		</div>

		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-info">
					<div class="panel-heading">页面内容</div>
					<div class="panel-body">
						<div class="form-group col-lg-10">
							<p class="text-primary">标题（建议20个字以内）：</p>
							<input class="form-control" placeholder="标题" id="box_title"
								value="">
						</div>
						<div class="form-group col-lg-2">
							<p class="text-primary">手册类型：</p>
							<select class="form-control" id='box_type'>
								<c:forEach items="${boxtypelist}" var="boxtype" varStatus="ids">
									<option value="${boxtype.typeid}">${boxtype.description}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-lg-12">
							<p class="text-primary">副标题（建议30个字以内）：</p>
							<input class="form-control" placeholder="副标题" id="box_subtitle"
								value="">
						</div>
						<div class="form-group col-lg-6">
							<p class="text-primary">作者：</p>
							<input class="form-control" placeholder="作者" id="box_writer"
								value="">
						</div>
						<div class="form-group col-lg-3">
							<p class="text-primary">日期：</p>
							<div class="input-append date form_datetime"
								onclick="javascript:showTimeForm(this)">
								<input size="16" type="text" value="" readonly id="boxtime"
									style="cursor: pointer;" class="form-control"> <span
									class="add-on"><i class="icon-th"></i></span>
							</div>
						</div>

						<div class="form-group col-lg-3" style="display: none">
							<p class="text-primary">添加图片：</p>
							<input id="" type="button" value="添加图片到资源库" class="btn btn-info"
								onclick="javascript:showImgModel();">
						</div>
						<div class="col-lg-12" style="padding: 0; display: none"
							id="ImgModel">
							<div class="well">
								<h3>图片上传</h3>

								<div id="downloadRet" style="display: none">
									<h4>显示图片</h4>
									<span id="downloadUrl"></span> <input id="downloadBtn"
										type="button" value="显示" class="btn btn-info"> <br />
									<img id="downloadImg" src=""></img>
									<h3>文件ID</h3>
									<div id="fileid"></div>
									<h3>管理URL</h3>
									<span id="url"></span> <input id="queryBtn" type="button"
										value="查询" class="btn btn-info"> <span id="imgInfo"></span>
								</div>
							</div>
						</div>
						<div class="col-lg-12"
							style="background-color: whitesmoke; padding-top: 20px; height: 800px">
							<p class="text-primary">手册内容：</p>
							<div class="container" style="padding: 0; width: 100%">
								<div class="hero-unit">
									<div id="alerts"></div>
									<div class="btn-toolbar" data-role="editor-toolbar"
										data-target="#editor" style="margin-bottom: 10px">
										<!-- <div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Font"><i class="icon-font"></i><b class="caret"></b></a>
											<ul class="dropdown-menu">
											</ul>
										</div> -->
										<div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Font Size"><i class="icon-text-height"></i>&nbsp;<b
												class="caret"></b></a>
											<ul class="dropdown-menu">
												<li><a data-edit="fontSize 4"><font size="4">正文</font></a></li>
												<li><a data-edit="fontSize 5"><font size="5">小标题</font></a></li>

											</ul>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="bold" title="Bold (Ctrl/Cmd+B)"><i
												class="icon-bold"></i></a> <a class="btn" data-edit="italic"
												title="Italic (Ctrl/Cmd+I)"><i class="icon-italic"></i></a>
											<a class="btn" data-edit="strikethrough"
												title="Strikethrough"><i class="icon-strikethrough"></i></a>
											<a class="btn" data-edit="underline"
												title="Underline (Ctrl/Cmd+U)"><i class="icon-underline"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="insertunorderedlist"
												title="Bullet list"><i class="icon-list-ul"></i></a> <a
												class="btn" data-edit="insertorderedlist"
												title="Number list"><i class="icon-list-ol"></i></a> <a
												class="btn" data-edit="outdent"
												title="Reduce indent (Shift+Tab)"><i
												class="icon-indent-left"></i></a> <a class="btn"
												data-edit="indent" title="Indent (Tab)"><i
												class="icon-indent-right"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="justifyleft"
												title="Align Left (Ctrl/Cmd+L)"><i
												class="icon-align-left"></i></a> <a class="btn"
												data-edit="justifycenter" title="Center (Ctrl/Cmd+E)"><i
												class="icon-align-center"></i></a> <a class="btn"
												data-edit="justifyright" title="Align Right (Ctrl/Cmd+R)"><i
												class="icon-align-right"></i></a> <a class="btn"
												data-edit="justifyfull" title="Justify (Ctrl/Cmd+J)"><i
												class="icon-align-justify"></i></a>
										</div>
										<div class="btn-group">
											<a class="btn dropdown-toggle" data-toggle="dropdown"
												title="Hyperlink"><i class="icon-link"></i></a>
											<div class="dropdown-menu input-append">
												<input class="span2" placeholder="URL" type="text"
													data-edit="createLink" />
												<button class="btn" type="button">Add</button>
											</div>
											<a class="btn" data-edit="unlink" title="Remove Hyperlink"><i
												class="icon-cut"></i></a>
										</div>

										<div class="btn-group">
											<a class="btn" title="Insert picture (or just drag & drop)"
												id="pictureBtn" data-toggle="modal"
												data-target="#selectImgModel" onclick="fn2('#888#');"><i
												class="icon-picture"></i></a>
											<!-- <input
												type="file" data-role="magic-overlay"
												data-target="#pictureBtn" data-edit="insertImage" /> -->
										</div>
										<div class="btn-group">
											<a class="btn" data-edit="undo" title="Undo (Ctrl/Cmd+Z)"><i
												class="icon-undo"></i></a> <a class="btn" data-edit="redo"
												title="Redo (Ctrl/Cmd+Y)"><i class="icon-repeat"></i></a>
										</div>
										<input type="text" data-edit="inserttext" id="voiceBtn"
											x-webkit-speech="">
										<div class="btn-group">
											<input type="button" value="清除样式" class="btn btn-info"
												onclick="javascript:cleanFontStyle()">
										</div>
										<div class="btn-group">
											<input type="button" value="文章源码" class="btn btn-info"
												data-toggle="modal" data-target="#contentHTMLModel"
												onclick="javascript:contentHTML();">
										</div>

									</div>

									<div id="editor" contenteditable="true">
										<div>输入内容&hellip;</div>
									</div>
								</div>





								<%
									String htmlData = request.getParameter("content1") != null
											? request.getParameter("content1")
											: "";
								%>

								<script>
									KindEditor.ready (function (K)
                                    {
	                                    var editor1 = K.create ('textarea[name="content1"]',
	                                    {
	                                        cssPath : '../css/boxmanage/code/prettify.css',
	                                        uploadJson : 'upload_json.jsp',
	                                        fileManagerJson : 'file_manager_json.jsp',
	                                        allowFileManager : true,
	                                        afterCreate : function ()
	                                        {
		                                        var self = this;
		                                        K.ctrl (document, 13, function ()
		                                        {
			                                        self.sync ();
			                                        document.forms['example'].submit ();
		                                        });
		                                        K.ctrl (self.edit.doc, 13, function ()
		                                        {
			                                        self.sync ();
			                                        document.forms['example'].submit ();
		                                        });
	                                        }
	                                    });
	                                    prettyPrint ();
                                    });
									 KindEditor.ready(function(K) {
										 var options = {
											        cssPath : '../css/boxmanage/code/prettify.css',
											        filterMode : true
											};
										var editor = K.create('textarea[name="contentx"]', options);
							        });
									
								</script>
								<%-- <div class="container" style="padding: 0; width: 100%">
									<%=htmlData%>
									<form name="example" method="post" action="addNewBoxPage.jsp">
										<textarea name="content1" cols="100" rows="8"
											style="width: 700px; height: 200px; visibility: hidden;"><%=htmlspecialchars(htmlData)%></textarea>
										<br /> <input type="submit" name="button" value="提交内容" />
										(提交快捷键: Ctrl + Enter)
									</form>

									<textarea id="editor_id" name="contentx"
										style="width: 700px; height: 300px;">
&lt;strong&gt;HTML内容&lt;/strong&gt;
</textarea>


								</div> --%>
								<%-- 								<%!private String htmlspecialchars(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\"", "&quot;");
		return str;
	}%> --%>





								<script>
									$ (function ()
                                    {
	                                    function initToolbarBootstrapBindings ()
	                                    {
		                                    var fonts = [
		                                            'Serif', 'Sans', 'Arial', 'Arial Black', 'Courier', 'Courier New',
		                                            'Comic Sans MS', 'Helvetica', 'Impact', 'Lucida Grande',
		                                            'Lucida Sans', 'Tahoma', 'Times', 'Times New Roman', 'Verdana'
		                                    ], fontTarget = $ ('[title=Font]').siblings ('.dropdown-menu');
		                                    $
		                                            .each (
		                                                    fonts,
		                                                    function (idx, fontName)
		                                                    {
			                                                    fontTarget
			                                                            .append ($ ('<li><a data-edit="fontName ' + fontName +'" style="font-family:\''+ fontName +'\'">'
			                                                                    + fontName + '</a></li>'));
		                                                    });
		                                    $ ('a[title]').tooltip (
		                                    {
			                                    container : 'body'
		                                    });
		                                    $ ('.dropdown-menu input').click (function ()
		                                    {
			                                    return false;
		                                    }).change (
		                                            function ()
		                                            {
			                                            $ (this).parent ('.dropdown-menu')
			                                                    .siblings ('.dropdown-toggle').dropdown ('toggle');
		                                            }).keydown ('esc', function ()
		                                    {
			                                    this.value = '';
			                                    $ (this).change ();
		                                    });
		                                    
		                                    $ ('[data-role=magic-overlay]').each (
		                                            function ()
		                                            {
			                                            var overlay = $ (this), target = $ (overlay.data ('target'));
			                                            overlay.css ('opacity', 0).css ('position', 'absolute').offset (
			                                                    target.offset ()).width (target.outerWidth ()).height (
			                                                    target.outerHeight ());
		                                            });
		                                    if ("onwebkitspeechchange" in document.createElement ("input"))
		                                    {
			                                    var editorOffset = $ ('#editor').offset ();
			                                    $ ('#voiceBtn').css ('position', 'absolute').offset (
			                                    {
			                                        top : editorOffset.top,
			                                        left : editorOffset.left + $ ('#editor').innerWidth () - 35
			                                    });
		                                    }
		                                    else
		                                    {
			                                    $ ('#voiceBtn').hide ();
		                                    }
	                                    }
	                                    ;
	                                    function showErrorAlert (reason, detail)
	                                    {
		                                    var msg = '';
		                                    if (reason === 'unsupported-file-type')
		                                    {
			                                    msg = "Unsupported format " + detail;
		                                    }
		                                    else
		                                    {
			                                    console.log ("error uploading file", reason, detail);
		                                    }
		                                    $ (
		                                            '<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'
		                                                    + '<strong>File upload error</strong> ' + msg + ' </div>')
		                                            .prependTo ('#alerts');
	                                    }
	                                    ;
	                                    initToolbarBootstrapBindings ();
	                                    $ ('#editor').wysiwyg (
	                                    {
		                                    fileUploadError : showErrorAlert
	                                    });
                                    });
								</script>
							</div>
						</div>
						<!-- body -->
					</div>
					<div class="panel-footer">
						<button type="button" class="btn btn-primary"
							onclick="javascript:saveNewBoxMessage()" id="savebtn">保存手册信息</button>
						<!-- data-toggle="modal" data-target="#insertRoleModel" -->

					</div>
				</div>
			</div>

		</div>



		<!-- saveRoleConf -->
		<!-- Metis Menu Plugin JavaScript -->
		<script src="../bower_components/metisMenu/dist/metisMenu.min.js"></script>
		<!-- Custom Theme JavaScript -->
		<script src="../dist/js/sb-admin-2.js"></script>
		<script src="../js/boxmanage/jquery.form.min.js"></script>
		<script src="../js/boxmanage/addNewBoxPage.js"></script>

		<script type="text/javascript">
			/* $ ("input[name='act_start_time'],input[name='act_stop_time']").datetimepicker (); */
            $ (".form_datetime").datetimepicker (
            {
                language : 'zh-CN',
                autoclose : 1,
                todayBtn : 1,
                minuteStep : 1,
                format : 'yyyy-mm-dd hh:ii',
                pickerPosition : "bottom"
            
            });
		</script>
		<!-- Modal -->
		<div class="modal fade" id="selectImgModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 80%">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">选择图片</h4>
					</div>
					<div class="modal-body" style="padding: 5px;">
						<div class="form-group" id="imglistdiv"
							style="height: 70%; overflow: auto; overflow-x: hidden;">
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
									<div class="col-lg-3" id="${imgMessages.img_guid }"
										onclick="javascript:selectImg('${imgMessages.img_guid }');">
										<div class="panel panel-default">
											<div class="panel-body" style="text-align: center;">
												<img src="${imgMessages.img_url }" width="100" height="100">
											</div>
											<div class="panel-footer"
												style="text-align: center; padding: 0px; font-size: 9pt; height: 30px;">
												<input class="form-control" placeholder="图片名" name="ImgName"
													value="${imgMessages.img_name }" disabled="disabled">
											</div>
											<div class="panel-footer"
												style="text-align: center; padding: 5px 0px;">
												<input type="button" value="选择" class="btn btn-info"
													onclick="">
											</div>
										</div>
										<div class="selected_mask" name="selectdiv"
											style="display: none">
											<div class="selected_mask_inner"></div>
											<div class="selected_mask_icon"></div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<form id="uploadForm" style="float: left">
							<input type="file" name="FileContent" class="btn btn-info"
								id="inputfile" style="float: left"></input> <input id="subbtn"
								type="submit" style="float: left" class="btn btn-info"
								style="margin-left: 0;">
						</form>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							id="closeModel">关闭</button>
						<button type="button" class="btn btn-primary" id="savebtn"
							data-dismiss="modal" onclick="javascript:insertImg()">插入图片</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->


		<!-- Modal -->
		<div class="modal fade" id="contentHTMLModel" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">文章源码</h4>
					</div>
					<div class="modal-body">
						<div class="form-group"
							style="height: 70%; overflow: auto; overflow-x: hidden;">
							<textarea id="contentHTML" cols="78" rows="34"></textarea>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" data-dismiss="modal"
							onclick="javascript:saveContentHTML()" id="savebtn">保存</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
</body>
</html>