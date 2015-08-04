<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>无电缆联动时距图</title>
<base href="<%=basePath%>"> 
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css"/>
<link rel="stylesheet" href="css/greenroad.css"/>
<script type="text/javascript" src="js/privategreenroad.js"></script>
 
    <style>
        #canvas{
            margin:0px;
            padding-bottom:20px;
            position: absolute;
            border:0px solid #c3c3c3;
            background-image:url(images/xybg.jpg);
            background-repeat:no-repeat;
            background-attachment:fixed;
            background-position:bottom left;
        }
    </style>
</head>
<body >
<input type="button" id="setPharseTime" value="设置相位时间"  style="height:20px;margin-left:500px;"/>
<div id="topdiv" style="position: relative;">
            <canvas id="canvas" width="1600px" height="900px" style="">
                Your browser does not support the canvas element.
            </canvas>
</div>
</body>
</html>
