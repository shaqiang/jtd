﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> <%
String path = request.getContextPath(); String basePath =
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>地图界面</title>

</head>

  
 

   <frameset cols="*,187" frameborder="no" border="0" framespacing="0" name="pageframe" >
      <frame src="map.jsp" name="mapFrame" id="mapFrame" title="mapFrame" />
      <frame src="sig_status.jsp" name="zztFrame"  noresize="noresize" id="zztFrame" title="zztFrame" />
     
   </frameset>
    


<noframes></noframes>

</html>
