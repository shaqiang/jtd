<%@  page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>默认某页面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />
<script src="js/stilearn-base.js"></script>
<script src="js/holder.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="jconfirmaction.jquery.js"></script>

<script type="text/javascript">
	
	$(document).ready(function() {
		$('.ask').jConfirmAction();
	});
	
</script>

</head>


<body>
      
<!--
      <div > 
        
        <div class="place" >
          <span>位置：</span>
          <ul class="placeul">
            <li><a href="#">首页</a></li>
          </ul>

          <div style=" float:right; padding-top:10px; "> <a href=""  class="ask"><img src="images/f07.fw.png"/></a></div>
        </div>
        
        
</div>-->

    
<div class="mainindex">
    
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b><s:property value="#session.usero.username"/>你好，欢迎使用管理系统</b>
    </div>
    
    <div class="welinfo">
    	<b>消息显示:</b>
    	<b>区域管理升级中...</b>
    </div>
    
    <div class="xline"></div>
</div>
    
    


</body>
</html>
