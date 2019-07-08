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
<title>摩托邦骑行</title>
<script type="text/javascript"
	src="https://webapi.amap.com/maps?v=1.3&key=cede6d4897221dd58d89c5a713d6afa2"></script>
<script type="text/javascript"
	src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
<script type="text/javascript" src="../js/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../js/jquery.flot.js"></script>
<script type="text/javascript"
	src="../js/motorideline/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript"
	src="../js/motorideline/jquery.touchSlider.js"></script>
<link href="../css/motorideline/style.css" rel="stylesheet"
	type="text/css">
<link href="../css/motorideline/motorideline.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
.amap-e, .amap-layers, .amap-maps, .amap-tile, .amap-tile-container {
	border-radius: 8%;
}

#closemapfull .amap-e, .amap-layers, .amap-maps, .amap-tile,
	.amap-tile-container {
	border-radius: 8%;
}

#mapfull .amap-layers {
	border-radius: 0%;
}

#container {
	border-radius: 8%;
}

.amap-logo {
	right: 0 !important;
	left: auto !important;
	display: none;
}

.amap-copyright {
	right: 70px !important;
	left: auto !important;
}

.amap-copyright {
	display: none;
}

.amap-controls {
	display: none;
}

@font-face {
	font-family: 'MyNewFont';
	src: url('../css/fonts/FV_Almelo.ttf');
	/* src: url('../css/fonts/cmake_n.ttf'); */
}

body {
	height: 100%;
	max-width: 960px;
	margin: 0 auto;
	background-color: rgb(43, 45, 46);
	text-align: center;
	font-family: "Avenir Next", Avenir, "Helvetica Neue", Helvetica,
		"Nimbus Sans L", Arial, "Liberation Sans", "PingFang SC",
		"Hiragino Sans GB", "Source Han Sans CN", "Source Han Sans SC",
		"Microsoft YaHei", "Wenquanyi Micro Hei", "WenQuanYi Zen Hei",
		"ST Heiti", SimHei, "WenQuanYi Zen Hei Sharp", sans-serif;
}

em {
	color: #8c8c8c;
	font-style: normal;
	vertical-align: middle;
	margin-right: 30px;
}

@media screen and (min-width:1200px) {
	#title {
		margin-bottom: 20px;
		font-size: 10em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
	}
	.gdmap_right_content {
		margin-top: 8.5pt;
	}
	.middle_three {
		height: 234pt;
	}
	.three_num {
		font-size: 36pt;
	}
	#three_num_top {
		height: 44%
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 15pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 41.2pt;
		height: 41.2pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 41.2pt 41.2pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 10pt;
		font-size: 17pt;
		margin-left: 1pt;
	}
	.zan {
		width: 102.7pt;
		height: 102.7pt;
		background-image: url(../images/banana_icon.png);
		background-size: 102.7pt 102.7pt;
	}
	.zan div {
		width: 73pt;
		height: 35pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 30pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 114pt;
		margin-top: 43.3pt;
		font-size: 26pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 51pt;
		width: 100%;
		margin-top: 16pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .img-2 {
		background-image: url(images/end.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .txt-1 {
		margin-left: 20pt;
		margin-top: 11pt;
		display: block;
		/*内联对象需加*/
		word-break: keep-all;
		/* 不换行 */
		white-space: nowrap;
		/* 不换行 */
		overflow: hidden;
		/* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 39pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 200pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 17pt;
		margin-bottom: 20pt;
	}
	.mileage {
		height: 52pt;
		font-size: 46pt;
	}
	.ridetime {
		height: 42pt;
		font-size: 32pt;
	}
	#mapfull, #openimg {
	/* 	margin-top: 3%;
		margin-left: 4%;
		margin-right: auto; */
		width: 100%;
		height: 100%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 45px;
		height: 45px;
	}
}

@media screen and (min-width: 960px) and (max-width: 1199px) {
	#title {
		margin-bottom: 20px;
		font-size: 8em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
	}
	.gdmap_right_content {
		margin-top: 8.5pt;
	}
	.middle_three {
		height: 234pt;
	}
	.three_num {
		font-size: 33pt;
	}
	#three_num_top {
		height: 45%;
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 15pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 41.2pt;
		height: 41.2pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 41.2pt 41.2pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 10pt;
		font-size: 17pt;
		margin-left: 1pt;
	}
	.zan {
		width: 102.7pt;
		height: 102.7pt;
		background-image: url(../images/banana_icon.png);
		background-size: 102.7pt 102.7pt;
	}
	.zan div {
		width: 73pt;
		height: 35pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 30pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 114pt;
		margin-top: 43.3pt;
		font-size: 26pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 51pt;
		width: 100%;
		margin-top: 16pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .txt-1 {
		margin-left: 20pt;
		margin-top: 11pt;
		display: block;
		/*内联对象需加*/
		word-break: keep-all;
		/* 不换行 */
		white-space: nowrap;
		/* 不换行 */
		overflow: hidden;
		/* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 39pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 200pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 17pt;
		margin-bottom: 20pt;
	}
	.mileage {
		height: 52pt;
		font-size: 46pt;
	}
	.ridetime {
		height: 42pt;
		font-size: 32pt;
	}
	#mapfull, #openimg {
		margin-top: 4%;
		margin-left: 5%;
		margin-right: auto;
		width: 90%;
		height: 93%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 43px;
		height: 43px;
	}
}

@media screen and (min-width: 750px) and (max-width: 959px) {
	#title {
		margin-bottom: 20px;
		font-size: 6em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
	}
	.gdmap_right_content {
		margin-top: 8.5pt;
	}
	.middle_three {
		height: 214pt;
	}
	.three_num {
		font-size: 30pt;
	}
	#three_num_top {
		height: 40%;
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 8pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 41.2pt;
		height: 41.2pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 41.2pt 41.2pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 10pt;
		font-size: 17pt;
		margin-left: 1pt;
	}
	.zan {
		width: 102.7pt;
		height: 102.7pt;
		background-image: url(../images/banana_icon.png);
		background-size: 102.7pt 102.7pt;
	}
	.zan div {
		width: 73pt;
		height: 35pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 30pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 114pt;
		margin-top: 43.3pt;
		font-size: 26pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		margin-top:;
		height: 51pt;
		width: 100%;
		margin-top: 16pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 20pt;
		height: 30.2pt;
		margin-left: 26pt;
		margin-top: 11pt;
		background-size: 20pt 30.2pt;
	}
	.seplace-div .txt-1 {
		margin-left: 20pt;
		margin-top: 11pt;
		display: block;
		/*内联对象需加*/
		word-break: keep-all;
		/* 不换行 */
		white-space: nowrap;
		/* 不换行 */
		overflow: hidden;
		/* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 39pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 200pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 17pt;
		margin-bottom: 20pt;
	}
	.mileage {
		height: 52pt;
		font-size: 46pt;
	}
	.ridetime {
		height: 42pt;
		font-size: 32pt;
	}
	#mapfull, #openimg {
		margin-top: 5%;
		margin-left: 8%;
		margin-right: auto;
		width: 88%;
		height: 92%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 40px;
		height: 40px;
	}
}

@media only screen and (min-width: 601px) and (max-width: 749px) {
	#title {
		margin-bottom: 20px;
		font-size: 4em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
	}
	.gdmap_right_content {
		margin-top: 8.5pt;
	}
	.middle_three {
		height: 157pt;
	}
	.three_num {
		font-size: 27pt;
	}
	#three_num_top {
		height: 40%;
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 8pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 20.6pt;
		height: 20.6pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 20.6pt 20.6pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 5pt;
		font-size: 9pt;
		margin-left: 10pt;
	}
	.zan {
		width: 51.4pt;
		height: 51.4pt;
		background-image: url(../images/banana_icon.png);
		background-size: 51.4pt 51.4pt;
	}
	.zan div {
		width: 36.5pt;
		height: 17.5pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 15pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 57pt;
		margin-top: 21.6pt;
		font-size: 13pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 25.5pt;
		width: 100%;
		margin-top: 8pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .txt-1 {
		margin-left: 10pt;
		margin-top: 5.5pt;
		display: block;
		/*内联对象需加*/
		word-break: keep-all;
		/* 不换行 */
		white-space: nowrap;
		/* 不换行 */
		overflow: hidden;
		/* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 19.5pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 100pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 9pt
	}
	.mileage {
		height: 46pt;
		font-size: 40pt;
	}
	.ridetime {
		height: 36pt;
		font-size: 26pt;
	}
	#mapfull, #openimg {
		margin-top: 5%;
		margin-left: 8%;
		margin-right: auto;
		width: 85%;
		height: 90%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 30px;
		height: 30px;
	}
}

@media only screen and (min-width: 466px) and (max-width: 600px) {
	#title {
		margin-bottom: 20px;
		font-size: 4em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
	}
	.gdmap_right_content {
		margin-top: 8.5pt;
	}
	.middle_three {
		height: 137pt;
	}
	.three_num {
		font-size: 24pt;
	}
	#three_num_top {
		height: 35%;
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 8pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 20.6pt;
		height: 20.6pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 20.6pt 20.6pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 5pt;
		font-size: 9pt;
		margin-left: 10pt;
	}
	.zan {
		width: 51.4pt;
		height: 51.4pt;
		background-image: url(../images/banana_icon.png);
		background-size: 51.4pt 51.4pt;
	}
	.zan div {
		width: 36.5pt;
		height: 17.5pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 15pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 57pt;
		margin-top: 21.6pt;
		font-size: 13pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 25.5pt;
		width: 100%;
		margin-top: 8pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .txt-1 {
		margin-left: 10pt;
		margin-top: 5.5pt;
		display: block;
		/*内联对象需加*/
		word-break: keep-all;
		/* 不换行 */
		white-space: nowrap;
		/* 不换行 */
		overflow: hidden;
		/* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 19.5pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 100pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 9pt
	}
	.mileage {
		height: 46pt;
		font-size: 40pt;
	}
	.ridetime {
		height: 36pt;
		font-size: 26pt;
	}
	#mapfull, #openimg {
		margin-top: 5%;
		margin-left: 8%;
		margin-right: auto;
		width: 85%;
		height: 90%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 30px;
		height: 30px;
	}
}

@media only screen and (min-width: 360px) and (max-width: 465px) {
	#title {
		margin-bottom: 20px;
		font-size: 2em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 13pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 4.5pt;
		height: text-align: left;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
		margin-top: 8.5pt
	}
	.gdmap_right_content {
		margin-top: 0%;
	}
	.middle_three {
		height: 90pt;
	}
	.three_num {
		font-size: 24pt;
	}
	#three_num_top {
		height: 33%
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 8pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 20.6pt;
		height: 20.6pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 20.6pt 20.6pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 5pt;
		font-size: 9pt;
		margin-left: 10pt;
	}
	.zan {
		width: 51.4pt;
		height: 51.4pt;
		background-image: url(../images/banana_icon.png);
		background-size: 51.4pt 51.4pt;
	}
	.zan div {
		width: 36.5pt;
		height: 17.5pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 15pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 57pt;
		margin-top: 21.6pt;
		font-size: 13pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 25.5pt;
		width: 100%;
		margin-top: 8pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .txt-1 {
		margin-left: 10pt;
		margin-top: 5.5pt;
		display: block;
		word-break: keep-all;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}
	.content {
		margin-top: 19.5pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 100pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 9pt
	}
	.mileage {
		height: 46pt;
		font-size: 40pt;
	}
	.ridetime {
		height: 36pt;
		font-size: 26pt;
	}
	#mapfull, #openimg {
		width: 100%;
		height: 100%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 30px;
		height: 30px;
	}
}

@media only screen and (max-width: 359px) {
	#title {
		margin-bottom: 20px;
		font-size: 2em;
		padding: 0;
	}
	.ride_title {
		float: left;
		z-index: 2;
		font-size: 14pt;
		line-height: normal;
		color: rgb(217, 217, 217);
		display: inline;
		margin-left: 8pt;
		margin-top: 2.5pt;
		text-align: left;
	}
	.ride_time {
		font-size: 13pt;
	}
	.ride_bt  p {
		font-size: 9pt;
	}
	.gdmap {
		height: 124pt;
	}
	.gdmap_right {
		height: 124pt;
		margin-top: 8pt;
	}
	.gdmap_right_content {
		padding: 0%;
	}
	.middle_three {
		height: 90pt;
	}
	.three_num {
		font-size: 22pt;
	}
	#three_num_top {
		height: 33%;
	}
	.three_num_bottom {
		font-size: 7pt;
	}
	.gdmap_right_content .bottom_title {
		width: 80%;
		margin-left: auto;
		margin-right: auto;
		margin-top: 8pt;
	}
	.gdmap_right_content .bottom_title .image {
		width: 14.6pt;
		height: 14.6pt;
		background-image: url(../images/route_icon_averagespeed@3x.png);
		background-size: 14.6pt 14.6pt;
	}
	.gdmap_right_content .bottom_title .txt {
		margin-top: 5pt;
		font-size: 9pt;
		margin-left: 10pt;
	}
	.zan {
		width: 51.4pt;
		height: 51.4pt;
		background-image: url(../images/banana_icon.png);
		background-size: 51.4pt 51.4pt;
	}
	.zan div {
		width: 36.5pt;
		height: 17.5pt;
		margin: 0 auto;
		font-family: 'MyNewFont';
		font-size: 15pt;
		color: rgb(60, 60, 60);
	}
	.seplace {
		height: 57pt;
		margin-top: 21.6pt;
		font-size: 13pt;
		color: rgb(130, 130, 130);
	}
	.seplace-div {
		height: 25.5pt;
		width: 100%;
		margin-top: 8pt;
	}
	.seplace-div .img-1 {
		background-image: url(../images/go.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .img-2 {
		background-image: url(../images/end.png);
		width: 10pt;
		height: 15.1pt;
		margin-left: 13pt;
		margin-top: 5.5pt;
		background-size: 10pt 15.1pt;
	}
	.seplace-div .txt-1 {
		margin-left: 10pt;
		margin-top: 5.5pt;
		text-align: left;
		width: 80%;
		display: block; /*内联对象需加*/
		word-break: keep-all; /* 不换行 */
		white-space: nowrap; /* 不换行 */
		overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
		text-overflow: ellipsis;
		/* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
	}
	.content {
		margin-top: 19.5pt;
		width: 92%;
		height: auto;
		font-size: 11pt;
		color: rgb(163, 163, 163);
		margin-left: auto;
		margin-right: auto;
		text-align: left;
	}
	.dataimg {
		height: 100pt;
	}
	.mileage-txt, .ridetime-txt {
		font-size: 9pt
	}
	.mileage {
		height: 46pt;
		font-size: 40pt;
	}
	.ridetime {
		height: 36pt;
		font-size: 26pt;
	}
	#mapfull, #openimg {
		width: 100%;
		height: 100%;
		background-position: center;
		background-size: 100% auto;
	}
	#closemapfull, #closeopenimg {
		width: 30px;
		height: 30px;
	}
}

.top {
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	border: 0px none rgb(68, 196, 148);
	width: 100%;
	height: 81px;
	background-color: rgb(253, 211, 19);
	margin-top: 8px;
}

.title {
	pointer-events: auto;
	border: 0px none rgb(216, 221, 228);
	z-index: 1;
	max-width: 100%;
	height: 44pt;
	background-color: rgb(38, 54, 54);
}

.ride_time {
	float: left;
	font-size: 9pt;
	width: 45%;
	height: 15pt;
	margin-top: 4.5pt;
	margin-left: 8pt;
	margin-bottom: 0px;
	font-style: normal;
	font-variant: normal;
	line-height: normal;
	color: rgb(130, 130, 130);
	text-align: left;
}

.ride_bt {
	pointer-events: auto;
	float: right;
	word-spacing: 0px;
	text-align: right;
	margin-right: 9.5pt;
	width: 45%;
	display: block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.ride_bt p {
	margin-top: 0px;
	margin-bottom: 0px;
	text-align: right;
	font-style: normal;
	font-variant: normal;
	font-size: 7pt;
	color: rgb(130, 130, 130);
}

.gdmap {
	border-radius: 5%;
	float: left;
	margin-top: 8.5pt;
	width: 48%;
	background-color: rgb(60, 60, 60);
}

.gdmap_right {
	float: right;
	width: 30%;
	vertical-align: middle;
	text-align: center;
}
</style>
<script type="text/javascript">
function AutoResizeImage(maxWidth,maxHeight,objImg){
	var img = new Image();
	img.src = objImg.src;
	var hRatio;
	var wRatio;
	var Ratio = 1;
	var w = img.width;
	var h = img.height;
	wRatio = maxWidth / w;
	hRatio = maxHeight / h;
	if (maxWidth ==0 && maxHeight==0){
	Ratio = 1;
	}else if (maxWidth==0){//
	if (hRatio<1) Ratio = hRatio;
	}else if (maxHeight==0){
	if (wRatio<1) Ratio = wRatio;
	}else if (wRatio<1 || hRatio<1){
	Ratio = (wRatio<=hRatio?wRatio:hRatio);
	}
	if (Ratio<1){
	w = w * Ratio;
	h = h * Ratio;
	}
	objImg.height = h;
	objImg.width = w;
	if(h>=w){
		objImg.style.top=-(h-w)/2;
	}else{
		objImg.style.left=-(w-h)/2;
	}
	}
			$(document).ready(function(){
			
				$(".main_visual").hover(function(){
					$("#btn_prev,#btn_next").fadeIn()
				},function(){
					$("#btn_prev,#btn_next").fadeOut()
				});
				
				$dragBln = false;
				
				$(".main_image").touchSlider({
					flexible : true,
					speed : 200,
					btn_prev : $("#btn_prev"),
					btn_next : $("#btn_next"),
					paging : $(".flicking_con")
					
				});
				
				$(".main_image").bind("mousedown", function() {
					$dragBln = false;
				});
				
				$(".main_image").bind("dragstart", function() {
					$dragBln = true;
				});
				
				$(".main_image a").click(function(){
					if($dragBln) {
						return false;
					}
				});
				
				timer = setInterval(function(){
					$("#btn_next").click();
				}, 5000);
				
				$(".main_visual").hover(function(){
					clearInterval(timer);
				},function(){
					timer = setInterval(function(){
						$("#btn_next").click();
					},5000);
				});
				
				$(".main_image").bind("touchstart",function(){
					clearInterval(timer);
				}).bind("touchend", function(){
					timer = setInterval(function(){
						$("#btn_next").click();
					}, 5000);
				});
				
			});
		</script>
</head>
<body>
	<div id="" class="title">
		<div id="" class="ride_title">
			<b>${rideline.title}</b>
		</div>
		<div style="clear: both;"></div>
		<div id="" class="ride_time">
			<span style="letter-spacing: 0.0px;"> ${titletime}</span>
		</div>
		<div id="" class="ride_bt">
			<p>${modelname}</p>
			<p>${brandname}</p>
		</div>
	</div>
	<div style="margin-left: 10%;">

		<div id="" class="middle_three"
			style="float: left; width: 25%; margin-top: 9.7pt; text-align: center;">
			<div id="" class="gdmap_right_content " style="width: 100%;">
				<div class="three_num"
					style="background-image: url(../images/pjspeed_img@2x.png); background-size: 100% 100%; width: 100%; margin-top: 10pt;">
					<div id="three_num_top"></div>
					<div id=""
						style="font-family: 'MyNewFont'; color: rgb(255, 255, 255); width: 100%; text-align: center;">
						${rideline.avgspeed}</div>
				</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">平均速度</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">(km/h)</div>
			</div>
		</div>

		<div id="" class="middle_three"
			style="float: left; width: 25%; margin-left: 8%; margin-top: 9.7pt; text-align: center;">
			<div id="" class="gdmap_right_content" style="width: 100%;">
				<div class="three_num"
					style="background-image: url(../images/maxspeed_img@2x.png); background-size: 100% 100%; width: 100%; margin-top: 10pt;">
					<div id="three_num_top"></div>
					<div id=""
						style="font-family: 'MyNewFont'; color: rgb(255, 255, 255); width: 100%; text-align: center;">
						${rideline.maxspeed}</div>
				</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">最高速度</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">(km/h)</div>
			</div>
		</div>

		<div id="" class="middle_three"
			style="float: left; width: 25%; margin-left: 8%; margin-top: 9.7pt; text-align: center;">
			<div id="" class="gdmap_right_content" style="width: 100%;">
				<div class="three_num"
					style="background-image: url(../images/maxangle_img@2x.png); background-size: 100% 100%; width: 100%; margin-top: 10pt;">
					<div id="three_num_top"></div>
					<div id=""
						style="font-family: 'MyNewFont'; color: rgb(255, 255, 255); width: 100%; text-align: center;">
						${rideline.dipangle}</div>
				</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">最大倾角</div>
				<div class='three_num_bottom'
					style="width: 100%; color: rgb(150, 150, 150); float: left;">(°)</div>
			</div>
		</div>
		<div style="clear: both;"></div>

	</div>
	<div id="">
		<div>
			<div id="container" class="gdmap"
				style="width: 66%; margin-left: 2%;"></div>

			<div id="" class="gdmap_right">
				<div class="gdmap_right_content"
					style="text-align: right; margin-right: 8%;">
					<div
						style="height: 20pt; font-family: 'MyNewFont'; font-size: 20pt; color: rgb(68, 196, 148); margin-left: auto; margin-right: auto; margin-top: 10pt">${mi}</div>
					<div
						style="margin-top: -5px; margin-left: auto; margin-right: auto; height: 11pt; font-size: 8pt; color: rgb(150, 150, 150);">里程(km/h)</div>

					<div
						style="height: 20pt; margin-top: 10pt; font-family: 'MyNewFont'; font-size: 20pt; margin-left: auto; margin-right: auto; color: rgb(68, 196, 148);">${ridetime}</div>
					<div
						style="height: 11pt; margin-top: -5px; margin-left: auto; margin-right: auto; font-size: 8pt; color: rgb(150, 150, 150);">时长(hh:mm:ss)</div>

					<div
						style="height: 20pt; margin-top: 10pt; font-family: 'MyNewFont'; font-size: 20pt; margin-left: auto; margin-right: auto; color: rgb(68, 196, 148);">${rideline.maxelevation}</div>
					<div
						style="height: 11pt; margin-top: -5px; margin-left: auto; margin-right: auto; font-size: 8pt; color: rgb(150, 150, 150);">最高海拔(m)</div>

				</div>
			</div>
		</div>
		<div id='divonmap'
			style="width: 66%; height: 124pt; border-radius: 8%; position: relative; z-index: 200; padding-top: 8.5pt; margin-left: 2%"></div>

		<div style="clear: both;"></div>
	</div>


	<div style="width: 97%; height: 123.8pt; margin: 8pt 0pt 0pt 2%;"
		class="dataimg">
		<div id="content">
			<div class="demo-container">
				<div id="placeholder" class="demo-placeholder"></div>
			</div>
		</div>
	</div>
	<div
		style="display: block; width: 97%; height: 123.8pt; margin: 8pt 0pt 0pt 2%;"
		class="dataimg">
		<div id="content">
			<div class="demo-container">
				<div id="dipangle" class="demo-placeholder"></div>
			</div>
		</div>
	</div>
	<div id="" class="content" style="text-align: justify;">
		${rideline.content}</div>
	<script type="text/javascript">
			function funa(objImg){
				  var img=new Image();
			       img.src=objImg.src;
			     
			       if(img.width>=img.height){
			    	   var y=$(".nineimg").css("width").replace("px","");
			    	   AutoResizeImage(0,y,objImg);
			       }
			       if(img.width<img.height){
			    	   var y=$(".nineimg").css("width").replace("px","");
			    	   AutoResizeImage(y,0,objImg);
			       }
			}   
			function lsl(objimg,url){
			
				scwidth=document.body.offsetWidth;
				scheight=document.body.offsetHeight;
				var img=new Image();
				img.src=url;
				if(img.height<=img.width){
					objimg.style.width=scwidth+"px";
					
					objimg.style.height="auto";
					objimg.style.top=(document.body.offsetHeight -img.height/img.width*scwidth)/2;
				}else{
				    
					objimg.style.height=scheight+"px";
					
					objimg.style.width="auto";
					 
				
				}
				
			}
			</script>

	<c:forEach items="${pics}" var="pic" varStatus="ids">
		<div id=""
			style="margin: 10pt 0pt 10pt 5pt; width: 30%; float: left; overflow: hidden;"
			class='nineimg' onclick="javascript:openimg();">

			<img src="${pic}!thumb" class='flicking_con'
				style="position: relative" width="0" height="0"
				onload="javascript:funa(this)" />

		</div>
	</c:forEach>

	<div style="height: 20px; width: 100%"></div>
	<div id="fullscreen"
		style="display: none; position: absolute; top: 0px; left: 0px; background-color: #000000; opacity: 0.6;">
	</div>
	<!-- <div id="openimg"
		style="display: none; position: fixed; top: 0px; left: 0px; background-color: #000000; background-image: url(); background-repeat: no-repeat;">
		<img alt="" src="" style="z-index:10000">
		<div id="closeopenimg"
			style="position: absolute; right: 0px; z-index: 99999;">
			<img alt="" src="../images/close_map.png"
				style="width: 100%; height: 100%">
		</div>
	</div> -->

	<div id="mapfull"
		style="display: none; position: fixed; top: 0px; left: 0px; z-index: 300">
		<div id="closemapfull"
			style="position: absolute; right: 10pt; top: 10pt; z-index: 99999;">
			<img alt="" src="../images/close_map.png"
				style="width: 100%; height: 100%">
		</div>
	</div>
	<div style="clear: both;"></div>
	<div
		style="position: fixed; top: 0px; left: 0px; visibility: hidden; width: 100%; height: 100%; margin: 0px; background-color: black;"
		id="openimg">
		<div id="closeopenimg"
			style="position: absolute; right: 5px; top: 10px; z-index: 99999;">
			<img alt="" src="../images/close_map.png"
				style="width: 100%; height: 100%">
		</div>
		<c:if test="${fn:length(pics)==1}">
			<img src="${pics[0]}" style="position: relative"
				onload="javascript:lsl(this,'${pic}');" />
		</c:if>
		<c:if test="${fn:length(pics)!=1}">
			<div class="kePublic">
				<!--效果html开始-->
				<div class="main_visual">
					<div class="main_image">
						<ul>
							<c:forEach items="${pics}" var="pic" varStatus="ids">
								<li><img src="${pic}"
									onload="javascript:lsl(this,'${pic}');" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<!--效果html结束-->
				<div class="clear"></div>
			</div>
		</c:if>
	</div>

	<script>
	
	$ (".nineimg").css("height",$(".nineimg").css("width"));
	function openimg(index){
	    
		$("#fullscreen").css("display","block");
		$("#openimg").css("visibility","visible");
		
	}
		$ ("#closemapfull").click (function ()
        {
	        $ ("#fullscreen").css ("display", "none");
	        $ ("#mapfull").css ("display", "none");
        });
		$ ("#closeopenimg").click (function ()
		        {
			        $ ("#fullscreen").css ("display", "none");
			        $ ("#openimg").css ("visibility", "hidden");
		        });
		$("#divonmap").click(function ()
		        {
		 $ ("#fullscreen").css ("display", "block");
	        $ ("#mapfull").css ("display", "block");
		        });
		
        $ ('#fullscreen').css (
        {
	        "height" : function ()
	        {
		        return $ (document).height ();
	        }
        });
        
        $ ('#fullscreen').css (
        {
	        "width" : function ()
	        {
		        return document.body.clientWidth;
	        }
        });
        
        var positiondata = '${str}';
        var obj = eval ('(' + positiondata + ")");
        var myArray = new Array ();
        var myArray2 = new Array ();
        var myArray3=new Array();
        var j=1,k=1;
        myArray2[0]=new Array(0,0);
        myArray3[0]=new Array(0,0);
        
        
        var dd = Math.ceil(${mi}) ;
        var count = 0;
        var distance = 0;
        if (dd <= 14) {
            distance = 2;
            count = Math.ceil(dd / 2.0);
        } else if (dd <= 35) {
            distance = 5;
            count = Math.ceil(dd / 5.0);
        } else {
            distance = Math.ceil(dd / 70.0) * 10;
            count = Math.ceil(1.0 * dd / distance);
        }

       
        var avg=${mi}/(obj.length+1);
        
        for (var i = 0; i < obj.length; i++)
        {
	        var myArray1 = new Array (obj[i].longitude, obj[i].latitude);
	        var speedpi=obj[i].speed * 3.6;
	        if(speedpi<0){
	        	speedpi=0;
	        }
	        var speeddata = new Array (j*avg, speedpi);
	 
	        if(obj[i].hasOwnProperty("dipangle")){
	        	
	        	 var dipangle=new Array(k*avg,obj[i].dipangle);
	        	 
	        }else{
	        	
	        	 var dipangle=new Array(k*avg,0);
	        }
	       
	        myArray[i] = myArray1;
	        myArray2[j] = speeddata;
	        myArray3[k]=dipangle;
	        j++;k++;
        }
        myArray2[j]=new Array(j*avg,0);
        myArray3[k]=new Array(k*avg,0);
       
      

        var map = new AMap.Map ('container',
        {
            
            resizeEnable : false,
            dragEnable : false,
            keyboardEnable : false,
            doubleClickZoom : false,
            scrollWheel : false,
            center : [
                    116.405467, 39.907761
            ],
            zoom : 13
        
        });
        var mapfull = new AMap.Map ('mapfull',
        {
            
            resizeEnable : true,
            scrollWheel : true,
            center : [
                    116.405467, 39.907761
            ],
            zoom : 13
        
        });
        
        
      
        
        
        var startplace = new Array (myArray[0][0], myArray[0][1]);
        map.setZoomAndCenter (14, startplace);
        mapfull.setZoomAndCenter (14, startplace);
        var pointsize = myArray.length;
        var endplace = new Array (myArray[pointsize - 1][0], myArray[pointsize - 1][1]);
        
       
        var mar=new AMap.Pixel(-7,-7);
         var maker1 = new AMap.Marker (
        {
            map : map,
            position : startplace,
            icon : new AMap.Icon (
            {
                size : new AMap.Size (24,24), //图标大小
                image : "../images/go_icon@2x.png",
                imageSize:new AMap.Size(15,15)
            
            }),
            offset:mar
         
        });
        
        var maker2 = new AMap.Marker (
        {
            map : map,
            position : endplace,
            icon : new AMap.Icon (
            {
                size : new AMap.Size (24,24), //图标大小
                image : "../images/over_icon@2x.png",
                imageSize:new AMap.Size(15,15)
            
            }),
        offset:mar
        });
       
         var maker3 = new AMap.Marker (
        {
            map : mapfull,
            position : startplace,
            icon : new AMap.Icon (
            {
                size : new AMap.Size (24,24), //图标大小
                image : "../images/go_icon@2x.png",
                imageSize:new AMap.Size(15,15)
            
            }),
        offset:mar
        }); 
        var maker4 = new AMap.Marker (
        {
            map : mapfull,
            position : endplace,
            icon : new AMap.Icon (
            {
                size : new AMap.Size (24,24), //图标大小
                image : "../images/over_icon@2x.png",
                imageSize:new AMap.Size(15,15)
            
            }),
        offset:mar
        });
        var maker = new Array ();
        maker[0] = maker1;
        maker[1] = maker2;
        map.setFitView (maker);
        var maker2 = new Array ();
        maker2[0] = maker3;
        maker2[1] = maker4;
        
        //mapfull.setFitView (maker2);
        
        map.on ('click', function (e)
        {
        
	        $ ("#fullscreen").css ("display", "block");
	        $ ("#mapfull").css ("display", "block");
        });
        map.setMapStyle("normal");
        mapfull.setMapStyle("normal");
        var polyline = new AMap.Polyline (
        {
            path : myArray, //设置线覆盖物路径
            strokeColor : "#FFFFFF", //线颜色
            strokeOpacity : 1, //线透明度
            strokeWeight : 5, //线宽
            strokeStyle : "solid", //线样式
            isOutline:true,
            strokeDasharray : [
                    10, 5
            ]
        //补充线样式
        });
        polyline.setMap (map);
        var polyline2 = new AMap.Polyline (
        {
            path : myArray, //设置线覆盖物路径
            strokeColor : "rgb(68,196,148)", //线颜色
            strokeOpacity : 1, //线透明度
            strokeWeight : 5, //线宽
            strokeStyle : "solid", //线样式
            strokeDasharray : [
                    10, 5
            ]
        //补充线样式
        });
        polyline2.setMap (mapfull);
        /* googleLayer = new AMap.TileLayer({
            // 图块取图地址
            zIndex:10,
            tileUrl: 'http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil'
        }); 
          googleLayer1 = new AMap.TileLayer({
             // 图块取图地址
             tileUrl: 'http://mt{1,2,3,0}.google.cn/vt/lyrs=m@142&hl=zh-CN&gl=cn&x=[x]&y=[y]&z=[z]&s=Galil'
         });  */
        //googleLayer.setMap(mapfull); 
                                                                                                                                                                                                                                                                                                            
      /*   var options =
        {
        		grid: {
    				hoverable: true,
    				clickable: true
    			},
            series :
            {
                lines :
                {
	                show : true,
	                lineWidth:1
                },
                 points :
                {
	                show : false
                }, 
                shadowSize : 10
            
            },
            // 在这里设置颜色
            colors : [
	            "rgb(68,196,148)"
            ],
            grid :
            {	color:"rgb(255,255,255)",
	            borderColor : "rgb(60, 60, 60)"
            },
            xaxis :
            {
            	min:0,
            	max:distance*count,
                color : "rgb(60,60,60)",
                font :
                {
                    color : "rgb(43, 45, 46)",
                    size :1/2
                   
                
                }
            },
            yaxis :
            {
            	min:0,
            	color : "rgb(60,60,60)",
                font :
                {
                	
                    color : "rgb(150, 150, 150)",
                    size : 7
                   
                
                }
            },
            legend: {
              
               font:{
            	   color : "rgb(230, 230, 230)",
                   size : 7
                  
               },
              
          
                backgroundColor: "rgb(43,45,46)" ,
                backgroundOpacity: 0
               
                
            }
           
        };
        var options2 =
        {
        		grid: {
    				hoverable: true,
    				clickable: true
    			},
            series :
            {
                lines :
                {
                	show : true,
   	                lineWidth:1
                },
                 points :
                {
	                show : false
                }, 
                shadowSize : 10
            
            },
            // 在这里设置颜色
            colors : [
	            "rgb(68,196,148)"
            ],
            grid :
            {
            
	            borderColor : "rgb(60, 60, 60)"
            },
            xaxis :
            {
            	min:0,
            	max:distance*count,
                color : "rgb(60,60,60)",
         
                font :
                {
                    color : "rgb(43, 45, 46)",
                    size :1
                   
                
                }
            },
            yaxis :
            {   ticks: [[60, "60°"], [30, "30°"], [0, "0°"],[-30, "-30°"],[-60, "-60°"]], 
            	min:-60,
            	max:60,
            	color : "rgb(60,60,60)",
            	
                font :
                {
                	
                    color : "rgb(150, 150, 150)",
                    size : 7
                 
                
                }
            },
            legend: {
              
               font:{
            	   color : "rgb(230, 230, 230)",
                   size : 7
                  
               },
              
          
                backgroundColor: "rgb(43,45,46)" ,
                backgroundOpacity: 0
               
                
            }
           
        };
       
        
        var plot=$.plot ("#placeholder", [
	        {
	        	label:"速度曲线",
		        data : myArray2
	        }
        ], options);
        var plot=$.plot ("#dipangle", [
                              	        {
                              	        	label:"倾角",
                              	        	
                              		        data : myArray3
                              	        }
                                      ], options2);
        
         $("#placeholder").bind("plotclick", function (event, pos, item) {         
        	
        	   alert("You clicked at " + pos.x + ", " + pos.y);                   
                if (item) {    
                  highlight(item.series, item.datapoint); 
                 
                    }    
         });
        
        $(".legendColorBox").css("display","none");
        $("<div id='tooltip'></div>").css({
			position: "absolute",
			display: "none",
			border: "1px solid #fdd",
			padding: "2px",
			"background-color": "#fee",
			opacity: 0.80
		}).appendTo("body");
        $("#placeholder").bind("plothover", function (event, pos, item) {

			   
				var str = "(" + pos.x.toFixed(2) + ", " + pos.y.toFixed(2) + ")";
				if (item) {
					var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);

					$("#tooltip").html(item.series.label + " of " + x + " = " + y)
						.css({top: item.pageY+5, left: item.pageX+5})
						.fadeIn(200);
				} else {
					$("#tooltip").hide();
				}
		
		}); */
	</script>



	<script>
		$('.three_num').css('height', $('.three_num').css('width').replace('px', '') * 0.88);
		$(window).resize(function() {
			$('.three_num').css('height', $('.three_num').css('width').replace('px', '') * 0.88);
		});
	</script>
<!-- 	<script type="text/javascript" src="../js/download.js"></script>
	<script type="text/javascript">
	
  $("#downloadModel").css("width",document.body.offsetWidth);
 </script> -->
</body>
</html>