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
<title>默认主页面</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">     
function countDown(secs,surl){     
 //alert(surl);     
 surl = surl.replace("&amp;","&");
 var jumpTo = document.getElementById('jumpTo');
 jumpTo.innerHTML=secs;  
 if(--secs>0){
     setTimeout("countDown("+secs+",'"+surl+"')",1000);     
     }
 else{       
     self.location=document.referrer;     
     }     
 }     
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
    	<b><s:property value="#request.errorMsg"/>&nbsp;<span id="jumpTo">6</span>秒后&nbsp;自动返回</b>
    	
  		<script type="text/javascript">countDown(5,'#');</script>
    </div>
    
    <div class="xline"></div>
</div>
    
    


</body>
</html>
