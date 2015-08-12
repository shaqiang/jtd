<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> <%
String path = request.getContextPath(); String basePath =
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>特勤控制</title>

</head>

  
 

   <frameset cols="*,187" frameborder="no" border="0" framespacing="0" name="pageframe" >
  
      <frame src="tqmap.jsp" name="tqmapFrame" id="tqmapFrame" title="tqmapFrame" />
      <frame src="tq_status.jsp" name="tqFrame"  noresize="noresize" id="tqFrame" title="tqFrame" />
     
   </frameset>
    


<noframes></noframes>

</html>
