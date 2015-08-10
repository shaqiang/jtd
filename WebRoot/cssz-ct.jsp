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
<title>绿冲突表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />
<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script src="js/stilearn-base.js"></script>
<script src="js/holder.js"></script>

<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>
<script type="text/javascript" src="js/privatecssz-ct.js"></script>



  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 200			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
  $(".select4").uedSelect({
    width : 150
  });
});
</script>

</head>

<body bgcolor="#EAF1F7">

	
    
<div class="formbody" >
    
    
<div id="usual1" class="usual"> 
    
    <div class="itab">
  	<ul> 
    	<li>
								<a href="promotionAction!promotion">信号机升级</a>
							</li>
     
    <li><a href="greenAction!green"  class="selected">绿冲突表</a></li> 
  	</ul>
    </div>
    <div style="font-size:18px; font-family:黑体; font-weight:bold; width:100%; text-align:center; line-height:30px; margin-top:15px;">绿冲突表</div>
    <div id="tab2" class="tabson">
      <table class="tablelist">
        <tbody >
        </tbody>
        <tbody>
          <tr>
            <td>&nbsp;</td>
            <td>序号</td>
            <td>0</td>
            <td>1</td>
            <td>2</td>
            <td>3</td>
            <td>4</td>
            <td>5</td>
            <td>6</td>
            <td>7</td>
            <td>8</td>
            <td>9</td>
            <td>10</td>
            <td>11</td>
            <td>12</td>
            <td>13</td>
            <td>14</td>
            <td>15</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td>灯位置</td>
            <td>东左</td>
            <td>东直</td>
            <td>东右</td>
            <td>东人</td>
            <td>南左</td>
            <td>南直</td>
            <td>南右</td>
            <td>南人</td>
            <td>西左</td>
            <td>西直</td>
            <td>西右</td>
            <td>西人</td>
            <td>北左</td>
            <td>北直</td>
            <td>北右</td>
            <td>北人</td>
          </tr>
          
         
          <s:iterator value="greens" var="green" status="status">
          	<tr>
	            <td> <s:property value="#status.index"/></td>
	            <td> <s:property value="name"/></td>
	            <td>
			            <s:if test="l00==0">
				            <img id="${id}_00" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_00" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l01==0">
				            <img id="${id}_01" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_01" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l02==0">
				            <img id="${id}_02" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_02" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l03==0">
				            <img id="${id}_03" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_03" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l10==0">
				            <img id="${id}_10" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_10" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l11==0">
				            <img id="${id}_11" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_11" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l12==0">
				            <img id="${id}_12" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_12" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l13==0">
				            <img id="${id}_13" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_13" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l20==0">
				            <img id="${id}_20" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_20" src="img/cross.png"/>
				        </s:else>
		        </td>
	           <td>
			            <s:if test="l21==0">
				            <img id="${id}_21" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_21" src="img/cross.png"/>
				        </s:else>
		        </td>
	           <td>
			            <s:if test="l22==0">
				            <img id="${id}_22" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_22" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l23==0">
				            <img id="${id}_23" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_23" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l30==0">
				            <img id="${id}_30" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_30" src="img/cross.png"/>
				        </s:else>
		        </td>
	            <td>
			            <s:if test="l31==0">
				            <img id="${id}_31" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_31" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l32==0">
				            <img id="${id}_32" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_32" src="img/cross.png"/>
				        </s:else>
		        </td>
	             <td>
			            <s:if test="l33==0">
				            <img id="${id}_33" src="img/tick.png"/>
				        </s:if>
				        <s:else>
				            <img id="${id}_33" src="img/cross.png"/>
				        </s:else>
		        </td>
          </tr>
          
          </s:iterator>
          
        </tbody>
      </table>
    </div>
	
    <ul class="forminfo">
      <li>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="539">&nbsp;</td>
            <td width="590" height="50">
            <div style="float: center; line-height: 35px; padding-left: 20px;">
								<ul class="toolbar2">
													<li>
														<span><img src="images/note-2.png" alt="" width="24"
																height="24" /> </span>
														<input  type="submit" id="submit_cs" class="toolbarbtn" value="保存并发送绿冲突表"
															onclick="saveGreen()"  />
															</li>
													</ul>
										</div>
            </td>
          </tr>
        </table>
        
    </ul>
        <div class="formtext">
      <p>操作说明：点击图标，修改图标状态</p>
      <p>注：红色叉：设置冲突   绿色钩：不冲突</p>
    </div>
</div>
<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>
    
  <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</div>



</body>
</html>
