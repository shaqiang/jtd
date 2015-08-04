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
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>
<!--[if IE 6]>
	<script src="js/IE6PNG.js"></script>
	<script type="text/javascript">
		IE6PNG.fix('.png');
	</script>
<![endif]-->
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
	
	
		
	$("#time").html(new Date().toLocaleString());
	
	
	function realtime()
	{
		$("#time").html(new Date().toLocaleString());
	}
	setInterval(realtime,1000);
})	

function logout(){
	if(confirm("你确定退出管理系统吗？")){
		parent.location.href = "useroAction!logout";
	}
	
}
</script>

</head>

<body style="background: url(img/topbg.jpg); background-color:#DEEFFF; background-repeat: repeat-x; background-position: 500px 0px;" >
<div class="topleft">管理控制平台</div>
<div class="topright">    
    <ul>
      <li><span>欢迎您：管理员 [<s:property value="#session.usero.username"/>]</span></li>
      <li><span><div id="time"></div> </span></li>
      <li><span><a href="usero_update_password.jsp" target="rightFrame">修改密码</a></span></li>
      <li><span><a href="javascript:void(0)" onClick="logout()">注销</a></span></li>
      	
    </ul>
   
    </div>
</body>
</html>
