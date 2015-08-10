﻿<%@  page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>特勤控制map</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="mapfiles/mapapi_3.12.9.js"></script>
<script type="text/javascript" src="js/map_helper.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<!--弹出层控件-->
<script type="text/javascript" src="js/popup_layer.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/privatetqmap.js"></script>
<script type="text/javascript" src="js/privatesig.js"></script>

<body >
 <div style="float：left;width:660px;">
 			 &nbsp;&nbsp;特勤方案 : <select id="tqid" name="select4" class="dfinputtq"">
											</select>
   <input type="button" onclick="Polyline()" id="addroad" value=" 添加特勤方案 " style="margin-left:20px;margin-top:10px;width:100px;height:20px; background:url(images/topbtn02.fw.png); color:#FFF; border-bottom:#039 1px solid; border-right:#039 1px solid;"/>
   <input type="button" onclick="saveAndUpdateLine()" id="saveroad" value="编辑特勤方案 " style="margin-left:20px;margin-top:10px;width:100px;height:20px; background:url(images/topbtn02.fw.png); color:#FFF; border-bottom:#039 1px solid; border-right:#039 1px solid;"/>&nbsp;&nbsp;&nbsp;
   <input type="button" onclick="deleteLine()" id="saveroad" value="删除特勤方案" style="margin-left:20px;margin-top:10px;width:100px;height:20px; background:url(images/topbtn02.fw.png); color:#FFF; border-bottom:#039 1px solid; border-right:#039 1px solid;"/>
											</div>
   <div id="map_canvas" style="margin:4px ;width:98%; height:760px;border:2px solid #cfdfe4">
   		地图加载失败....
   </div>
</body>
</html>
