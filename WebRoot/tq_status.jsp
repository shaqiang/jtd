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
<title>信号机状态</title>
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
</head>

<body style="background: #FFF; overflow-x:hidden;">
<dl class="leftmenu">
        
  <dd style="margin-left:5px;">
    <div class="title">状态</div>
    	<ul class="menuson">
    	<s:if test="%{tqsigs==null||tqsigs.size()==0}">
    		<li class="active"><cite></cite>
	    			暂无状态
    		</li>
    	</s:if>
    	<s:iterator value="tqsigs" var="tqsig" status="index">
    		<li class="active"><cite></cite>
	    		<s:if test="tqstatus==0">
		    		<a href="greenroadAction!changetqstatus?tqstatus=1&tqid=<s:property value="id"/>&marklineid=<s:property value="marklineid"/>" target="tqFrame"><s:property value="name"/>&nbsp;
		    			自动运行
		    		</a>
	    		</s:if>
	    		<s:elseif test="tqstatus==1">
	    			<a href="greenroadAction!changetqstatus?tqstatus=0&tqid=<s:property value="id"/>&marklineid=<s:property value="marklineid"/>" target="tqFrame"><s:property value="name"/>&nbsp;
		    			特勤控制
		    		</a>
	    		</s:elseif>
	    		<s:else>
	    			状态异常
	    		</s:else>
    		</li>
    	</s:iterator>
        
        

        </ul>    
    </dd>    
    </dl>

</body>
</html>
