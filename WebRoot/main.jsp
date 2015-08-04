<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>信息管理系统界面</title>

</head>
<frameset rows="70,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="" noresize="noresize" id="topFrame" title="topFrame" />
  
 

   <frameset cols="187,*" frameborder="no" border="0" framespacing="0" name="pageframe" >
  
   
      <frame src="left.jsp" name="leftFrame" scrolling="" noresize="noresize" id="leftFrame" title="leftFrame" />
      <frame src="smap.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
   </frameset>
    

</frameset>
<noframes></noframes>

</html>
