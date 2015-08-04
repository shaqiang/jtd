<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<title>导航栏</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="js/jquery.js"></script>



<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav2 li a").click(function(){
		$(".nav2 li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>


<!--[if IE 6]>
	<script src="js/IE6PNG.js"></script>
	<script type="text/javascript">
		IE6PNG.fix('.png');
	</script>
<![endif]-->
<script type="text/javascript" src="js/privateleft.js"></script></head>

<body style="background: #DEEFFF;">
<dl class="leftmenu">
        
  <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>交通信号系统</div>
    	<ul class="menuson">
        <!--
<li><cite></cite><a href="cssz.html" target="rightFrame">参数设置</a></li>
        -->
        
        <li class="active"><cite></cite><a href="smap.jsp" target="rightFrame">查看地图</a></li>
        <s:if test="#session.usero.ulimit==0">
         <li><cite></cite><a href="areamap.jsp" target="rightFrame">新增区域</a></li>
         </s:if>
        </ul>    
    </dd>
        
         <s:if test="#session.usero.ulimit==0">
    <dd>
    <div class="title">
    <span><img src="images/leftico02.png" /></span>优化控制</div>
    <ul class="menuson">
        <li><cite></cite><a href="greenmap.jsp" target="rightFrame">无电缆联动</a></li>
         <li><cite></cite><a href="tqmap.jsp" target="rightFrame">特勤控制</a></li>
       <li><cite></cite><a href="areayhmap.jsp" target="rightFrame">区域优化</a></li>
        </ul>     
    </dd>
    </s:if>
    <dd>
    <div class="title"><span><img src="images/leftico03.png" /></span>系统用户管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="usero_update_password.jsp" target="rightFrame">修改密码</a><i></i></li>
        <s:if test="#session.usero.ulimit==0">
        <li><cite></cite><a href="userareaAction!alllist" target="rightFrame">片区管理</a><i></i></li>
        <li><cite></cite><a href="useroAction!list" target="rightFrame">用户管理</a><i></i></li>
        </s:if>
        <li><cite></cite><a href="oplogAction!list" target="rightFrame">日志管理</a><i></i></li>

    </ul>    
    </dd>  
     
    </dl>

</body>
</html>
