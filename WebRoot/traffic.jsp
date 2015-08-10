<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<%
String path = request.getContextPath(); String basePath =
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/select.css" rel="stylesheet" type="text/css" />

<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script src="js/stilearn-base.js"></script>
<script src="js/holder.js"></script>

<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="js/select-ui.min.js"></script>



<script type="text/javascript" src="js/privatesig.js"></script>
<script type="text/javascript">
function test()
{
	console.log("coming");
	executeCommand(5);
	location.href = "cssz-cs.html";
}
		$(function(){
				setInterval("ShowLights()",250);
				});
				function ShowLights(){
				  $.ajax({   
				            url:'realtime',//这里是你的action或者servlert的路径地址   
				            type:'get', //数据发送方式   
				            dataType:'json', //接受数据格式 (这里有很多,常用的有html,xml,js,json)   
				            error: function(msg)
				            { //失败   
				            	console.log('Error loading document');   
				            },   
				            success: function(msg)
				            { //成功   
				             if(msg!=null)
				             {
					             	 $(".l03").attr("src","images/rod/l03"+msg.l03+".png"); 
					           	 	 $(".l23").attr("src","images/rod/l03"+msg.l23+".png"); 
					           	 	 
						           	 $("#l20").attr("src","images/rod/l20"+msg.l20+".png");
						           	 $("#l21").attr("src","images/rod/l21"+msg.l21+".png");
						           	 $("#l22").attr("src","images/rod/l22"+msg.l22+".png"); 
						           	 
						           	 $("#l00").attr("src","images/rod/l00"+msg.l00+".png");
						           	 $("#l01").attr("src","images/rod/l01"+msg.l01+".png");
						           	 $("#l02").attr("src","images/rod/l02"+msg.l02+".png");
						           	 
						           	 $(".l13").attr("src","images/rod/l13"+msg.l13+".png");
						           	 $(".l33").attr("src","images/rod/l33"+msg.l33+".png");
						           	 
						           	 $("#l32").attr("src","images/rod/l32"+msg.l32+".png"); 
						           	 $("#l31").attr("src","images/rod/l31"+msg.l31+".png");
						           	 $("#l30").attr("src","images/rod/l30"+msg.l30+".png");
						           	 
						           	 $("#l11").attr("src","images/rod/l11"+msg.l11+".png"); 
						           	 $("#l10").attr("src","images/rod/l10"+msg.l10+".png");
						           	 $("#l12").attr("src","images/rod/l12"+msg.l12+".png");
						           	 
						           	 $("#dd").val(msg.dd);
						           	 $("#xd").val(msg.xd);
						           	 $("#nd").val(msg.nd);
						           	 $("#bd").val(msg.bd);
				             }
				           	 	 
					        
					           	 
				            }  
		            });        	 
		}


</script>

<script type="text/javascript">
    KE.show({
        id : 'content7',
        cssPath : './index.css'
    });
  </script>
  
<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 200			  
	});
	$(".select2").uedSelect({
		width : 130  
	});
	$(".select3").uedSelect({
		width : 100
	});
});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});



});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click2").click(function(){
  $(".tip2").fadeIn(200);
  });
  
  $(".tiptop2 a").click(function(){
  $(".tip2").fadeOut(200);
});



});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click4").click(function(){
  $(".tip4").fadeIn(200);
  });
  
  $(".tiptop4 a").click(function(){
  $(".tip4").fadeOut(200);
});



});
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click3").click(function(){
  $(".tip3").fadeIn(200);
  });
  
  $(".tiptop3 a").click(function(){
  $(".tip3").fadeOut(200);
});



});
</script>
<script type="text/javascript" src="js/jquery.tabso_yeso.js"></script>
<script type="text/javascript">

var sigNumber = <s:property value="sig.number"/>;
console.log(sigNumber);
$(document).ready(function($){
	
	
	//默认选项卡切换
	$("#normaltab").tabso({
		cntSelect:"#normalcon",
		tabEvent:"mouseover",
		tabStyle:"normal"
	});
               
   
});
</script>
<script>
function show_div(){
 var obj=document.getElementById('test_div');
var btn=document.getElementById('show_btn');
	if(obj.style.display=='none'){
		obj.style.display='block';
		btn.innerHTML='自　动';
		allRed(0);
		
	}else{
		obj.style.display='none';
		btn.innerHTML='手　动';
		executeCommand(33);
	}
}
</script>


</head>

<body>   
<div class="formbody" >

     <div class="tabson" style="margin-top:0px;" ><ul class="toolbar" style="width:100%; margin-bottom:10px;"><li style="padding-left:5px;">信号机基本信息 |　编号:<s:property  value="sig.number"/>&nbsp;&nbsp;&nbsp;地址:<s:property value="sig.address"/>&nbsp;&nbsp;&nbsp;名称:<s:property value="sig.name" />&nbsp;&nbsp;&nbsp;所在区域:<s:property value="sig.userarea.uareaname" />　</li></ul>
       <div style="width:100%; float:left;">
         <div class="xwbox" >
         <li style=" margin-top: 60px; height:29px;">
         <div style="width: 29px; float:left; height: 29px; margin-left: 195px;"><img alt="北人行道" class="l33" src="images/rod/l330.png" width="29" height="29" /></div>
         <div style=" float:left; width: 29px; height: 29px; margin-left: 305px;"><img alt="北人行道" class="l33" src="images/rod/l330.png" width="29" height="29" /></div>
         </li>
           <li style="height:29px;">
             <div style="width: 29px; float:left;  margin-top: 0px; height: 29px; margin-left: 150px;"><img class="l23" alt="西人行道" src="images/rod/l230.png" width="29" height="29" /></div>
             <div style="width: 29px; float:left;  margin-top: 0px; height: 29px; margin-left: 390px;"><img class="l03" alt="东人行道" src="images/rod/l030.png" width="29" height="29" /></div>
           </li>
           <li style="height: 270px;">
             <div style="height: 270px; width: 100%; float: left;">
               <div style="width: 33px; height: 126px; float: left; margin-top: 25px; margin-left: 205px; overflow: hidden; background: url(images/rod/trabg1.png ) no-repeat center;">
                 <table width="33" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                     <td height="8"> </td>
                   </tr>
                   <tr>
                     <td height="25" align="center"><span id="dd" style="font-family: Adobe 黑体 Std R; font-size: 18px; color: #FFF;"></span></td>
                   </tr>
                   <tr>
                     <td height="28" align="center"><img id="l02" src="images/rod/l020.png" alt="东向西右拐" width="25" height="25" /></td>
                   </tr>
                   <tr>
                     <td height="27" align="center"><img id="l01" src="images/rod/l010.png"  alt="东向西直行" width="25" height="25" /></td>
                   </tr>
                   <tr>
                     <td height="27" align="center"><img id="l00" src="images/rod/l000.png" alt="东向西左拐" width="25" height="25" /></td>
                   </tr>
                 </table>
               </div>
                <div style="width: 126px; height: 33px; float: left; margin-top: 230px; margin-left: 10px; background: url(images/rod/trabg2.png) no-repeat;">
                 <table width="126" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                   	 <td width="30" align="center"><span id="bd" style="font-family: Adobe 黑体 Std R; font-size: 18px; color: #FFF; line-height: 33px;"></span></td>
                     <td width="28" align="center"><img id="l32" src="images/rod/l320.png" alt="北向南右拐" width="25" height="25" /></td>
                     <td width="28" align="center"><img  id="l31" src="images/rod/l310.png" alt="北向南直行" width="25" height="25" /></td>
                     <td width="30" align="center"><img  id="l30" src="images/rod/l300.png" alt="北向南左拐" width="25" height="25" /></td>
                   	 <td width="4" height="33"></td>
                   </tr>
                 </table>
               </div>
              <div style="width: 126px; height: 33px; float: left; margin-top: 0px; margin-left: 0px; background: url(images/rod/trabg2.png) no-repeat;">
                 <table width="126" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                     <td width="4" height="33"></td>
                     <td width="28" align="center"><img id="l10" src="images/rod/l100.png" alt="南向北左拐" width="25" height="25" /></td>
                     <td width="28" align="center"><img id="l11" src="images/rod/l110.png" alt="南向北直行" width="25" height="25" /></td>
                     <td width="30" align="center"><img id="l12" src="images/rod/l120.png" alt="南向北右拐" width="25" height="25" /></td>
                      <td width="30" align="center"><span id="nd" style="font-family: Adobe 黑体 Std R; font-size: 18px; color: #FFF; line-height: 33px;"></span></td>
                   </tr>
                 </table>
               </div>
               <div style="width: 33px; height: 126px; float: left; margin-top: 130px; margin-left: 10px; overflow: hidden; background: url(images/rod/trabg1.png ) no-repeat center;">
                 <table width="33" border="0" cellspacing="0" cellpadding="0">
                   <tr>
                     <td height="8"></td>
                   </tr>
                   <tr>
                     <td height="28" align="center"><img id="l20" src="images/rod/l200.png" alt="西向东左拐" width="25" height="25" /></td>
                   </tr>
                   <tr>
                     <td height="27" align="center"><img id="l21" src="images/rod/l210.png" alt="西向东直行" width="25" height="25" /></td>
                   </tr>
                   <tr>
                     <td height="27" align="center"><img id="l22" src="images/rod/l220.png" alt="西向东右拐" width="25" height="25" /></td>
                   </tr>
                   <tr>
                     <td height="25" align="center"><span id="xd" style="font-family: Adobe 黑体 Std R; font-size: 18px; color: #FFF;"></span></td>
                   </tr>
                 </table>
               </div>
             </div>
           </li>
           <li style="height:29px;">
             <div style="width: 29px; height: 29px; float:left; margin-left: 150px;">
             <img class="l23"  src="images/rod/l230.png" alt="西人行道" width="29" height="29" /></div>
             <div style="width: 29px; float:left;  margin-top: 0px; height: 29px; margin-left: 390px;">
             <img class="l03" src="images/rod/l030.png" alt="东人行道" width="29" height="29" /></div>
           </li>
           <ul>
             <li style="height:29px;">
               <div style="width: 29px; height: 29px; float:left; margin-left: 195px;">
               <img class="l13" src="images/rod/l130.png" alt="南人行道" width="29" height="29" /></div>
               <div style=" float:left; width: 29px; height: 29px; margin-left: 305px;">
               <img class="l13" src="images/rod/l130.png" alt="南人行道" width="29" height="29" /></div>
             </li>
           </ul>
         </div>
       </div>
       <div style="width:100%; float:left; margin-bottom:10px;">
         <ul class="toolbar" >
           <li  style="cursor: auto;"><span><img src="images/shezhi3.png" alt="" width="24" height="24" /></span><b>设 置 </b></li>
         </ul>
         <ul class="toolbar1">
           

       <li onclick="javascript:location.href='sigpublicparamAction!publicParam'">设置参数</li>
		<li  onclick="javascript:location.href='promotionAction!promotion?sigNumber=<s:property  value="sig.number"/>'">高级设置</li>

		   <li  id="manyCommands">初始化所有参数</li> 
		   <li  id="#">恢复默认参数</li> 
		   <!--
		   <li  onclick="executeCommand(0)">调阅实时状态</li> 
		     -->
		  
         </ul>
       </div>
       <div style="width:100%; float:left; margin-bottom:10px;">
         <ul class="toolbar" >
           <li style="cursor: auto;"><span><img src="images/chakan.png" alt="" width="24" height="24" /></span><b>查 看 </b></li>
         </ul>
         <ul class="toolbar1">
       <li class="click2"><a href="flowAction!list?sigid=<s:property value="sig.id"/>">流量信息</a></li>
       <li class="click3"><a href="devlogAction!plist?iserror=1">故障信息</a></li>
     
          <!-- 
            <li  onclick="clearErrorcode()">清空故障记录</li> 
	   <li  onclick="clearFlow()">清除累计流量</li> 
       <li class="click4"><a href="oplogAction!plist">操作日志</a></li>
        -->
         </ul>
         
       </div>
       
       <div style="width:100%; float:left; margin-bottom:10px;">
         <ul class="toolbar" >
           <li style="cursor: auto;"><span><img src="images/settings.png" alt="" width="24" height="23" /></span><b>控 制 </b></li>
         </ul>
         <ul class="toolbar1">
       <li id="show_btn" onclick="show_div()">手　动</li>
      
       

         </ul>
         
       </div>
       			<s:hidden id="c1" name="conflictVO.c_00"></s:hidden>
				  <s:hidden id="c2" name="conflictVO.c_01"></s:hidden>
				   <s:hidden id="c3" name="conflictVO.c_02"></s:hidden>
				    <s:hidden id="c4" name="conflictVO.c_03"></s:hidden>
				     <s:hidden id="c5" name="conflictVO.c_10"></s:hidden>
				      <s:hidden id="c6" name="conflictVO.c_11"></s:hidden>
				       <s:hidden id="c7" name="conflictVO.c_12"></s:hidden>
				        <s:hidden id="c8" name="conflictVO.c_13"></s:hidden>
				         <s:hidden id="c9" name="conflictVO.c_20"></s:hidden>
				          <s:hidden id="c10" name="conflictVO.c_21"></s:hidden>
				           <s:hidden id="c11" name="conflictVO.c_22"></s:hidden>
				            <s:hidden id="c12" name="conflictVO.c_23"></s:hidden>
				             <s:hidden id="c13" name="conflictVO.c_30"></s:hidden>
				              <s:hidden id="c14" name="conflictVO.c_31"></s:hidden>
				               <s:hidden id="c15" name="conflictVO.c_32"></s:hidden>
				                <s:hidden id="c16" name="conflictVO.c_33"></s:hidden>
       <div id="test_div" style="width:100%; float:left; display:none; margin-bottom:15px; ">
       
         <ul class="toolbar1" style="margin-left:80px; ">
           
       	 <li  onclick="executeCommand(29)">黄 闪<br /></li>
         <li  onclick="executeCommand(30)">关 灯<br /></li>
         <li  onclick="executeCommand(31)">全 红<br /></li>
           <li  onclick="runByPharse()">指定相位运行<br /></li>
         </ul>
          <div style="width:100%; float:left; margin-bottom:10px;">
         <ul class="toolbar1" style="margin-left:80px; ">
	         		<li  onclick="allRed(0)">指定相位全红<br /></li>
	         		<li  onclick="clearAllLight(0)">指定相位清空<br /></li>
	         		<li>绿灯时间&nbsp;&nbsp;<input style="border:1px dotted #CCC;;width:23%;height:100%" id="gltime" class="ptime" type="text" placeholder="范围:1-99" name="gltime" value="10"/>×10秒</li>
	         		<li>黄灯时间&nbsp;&nbsp;<input style="border:1px dotted #CCC;;width:23%;height:100%" id="yltime"  class="ptime"  type="text" placeholder="范围:3-9" name="yltime" value="3"/>秒</li>	
	         		<li>红灯时间&nbsp;&nbsp;<input style="border:1px dotted #CCC;;width:23%;height:100%" id="rltime"   class="ptime" type="text"  placeholder="范围:3-9" name="rltime" value="3"/>秒</li>
	         </ul>
         <div class="picbox">
										<div style="width: 100%; float: left; height: ">
											<div class="xhu">
											</div>
											<div class="xhu" style="margin-left: 135px;">
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												<img class="0_3_3" alt="北人行道" class="l23" src="images/rod/l330.png" width="15" />
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
												<img class="0_3_3" alt="北人行道" class="l03" src="images/rod/l330.png" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
												<img  class="0_2_3" alt="西人行道"  src="images/rod/l230.png" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
												<img class="0_0_3" alt="东人行道" src="images/rod/l030.png" width="15" />
											</div>
										</div>
										
										<div style="height: 110px; width: 100%; float: left;">
											<div class="xleftdown">
												<table width="18" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="2"></td>
													</tr>
													<tr>
														<td align="center">
															<img id="0_0_2"  src="images/rod/l020.png" alt="东向西右拐"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="0_0_1"  src="images/rod/l010.png" alt="东向西直行"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="0_0_0"   src="images/rod/l000.png" alt="东向西左拐"
																width="15" height="15" />
														</td>
													</tr>
												</table>
											</div>
											<div class="xrightdown">
												<table width="50" height="18" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="2" height="18"></td>
														<td>
															<img id="0_3_2"  src="images/rod/l320.png" alt="北向南右拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="0_3_1"  src="images/rod/l310.png" alt="北向南直行" width="15"
																height="15" />
														</td>
														<td>
															<img  id="0_3_0"  src="images/rod/l300.png" alt="北向南左拐" width="15"
																height="15" />
														</td>
													</tr>
												</table>
											</div>
											<div class="xleftup">
												<table width="50" height="18" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="2" height="18"></td>
														<td>
															<img id="0_1_0"  src="images/rod/l100.png" alt="南向北左拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="0_1_1"  src="images/rod/l110.png" alt="南向北直行" width="15"
																height="15" />
														</td>
														<td>
															<img id="0_1_2"  src="images/rod/l120.png" alt="南向北右拐" width="15"
																height="15" />
														</td>
													</tr>
												</table>
											</div>
											<div class="xrightup">
												<table width="18" border="0" align="center" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="2"></td>
													</tr>
													<tr>
														<td align="center">
															<img  id="0_2_0"  src="images/rod/l200.png" alt="西向东左拐" width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="0_2_1"  src="images/rod/l210.png" alt="西向东直行"  width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="0_2_2"  src="images/rod/l220.png" alt="西向东右拐" width="15"
																height="15" />
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
											
												<img class="0_2_3"  src="images/rod/l230.png" alt="西人行道" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
											
												<img class="0_0_3"  src="images/rod/l030.png" alt="东人行道" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												
												<img  class="0_1_3"  src="images/rod/l130.png" alt="南人行道" width="15" />
											
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											
												<img class="0_1_3"  src="images/rod/l130.png" alt="南人行道" width="15" />
											
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											</div>
										</div>
									</div>
         
       </div>
           
       </div>
        <div id="divProgressbar"></div>
     </div>
     
      
    
</div>

 <div class="tip">
   	 <div class="tiptop"><span>信号设置</span><a></a></div>
        
      <div class="tipinfo">
        
        <div class="tipright" style="line-height:25px;">
        <p>路口名称：<input type="text" class="dfinput2"  style="width:150px;"/></p>
        <p>路口编码：<input type="text" class="dfinput2"  style="width:150px;"/></p>
        <p>路口地址：<input type="text" class="dfinput2"  style="width:150px;"/></p>
        <p>路口端口：<input type="text" class="dfinput2"  style="width:150px;"/></p>
        <p>中心端口：<input type="text" class="dfinput2"  style="width:150px;"/></p>
        <p ><table width="50%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="20">&nbsp;</td>
    <td width="66"><btn class="forminfobtn2">保存路口信息</btn></td>
    <td width="66"><btn class="forminfobtn">连接</btn></td>
    <td width="203"><btn class="forminfobtn">断开</btn></td>
    <td >&nbsp;</td>
  </tr>
</table>
 <p style=" margin-top:5px;">工作方式：<input type="text" class="dfinput2"  style="width:150px;"/></p>
  <p>工 作 日 ：<input type="text" class="dfinput2"  style="width:150px;"/></p>
  <p><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="44%" align="left">工作方案：
      <input type="text" class="dfinput2"  style="width:30px;"/></td>
    <td width="56%">时 间 段 ：
      <input type="text" class="dfinput2"  style="width:30px;"/></td>
    </tr>
</table>
  <p><table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="44%" align="left">步　　序：
      <input type="text" class="dfinput2"  style="width:30px;"/></td>
    <td width="56%">剩余时间：
      <input type="text" class="dfinput2"  style="width:30px;"/></td>
    </tr>
</table>
<p>故障信息：</p>
 <div style="width:240px; height:100px; overflow:auto;"><cite>该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。该信号灯运行正常，无故障。如果是请点击确定按钮 ，否则请点取消。</cite></div>
        
        </div>
        
        <div class="tipleft">
        <p>运行状态：未连接....</p>
        <div style="width:500px; height:400px; float:left; overflow:hidden;"><img src="images/img/fw.jpg" width="500" height="400" /></div>
        </div>
     </div>
 
    
  </div>
  
  
  <!-- 
     <div class="tip2">
   	 <div class="tiptop2"><span>月车流量查询</span><a></a></div>
        
      <div class="tipinfo2">
        <div class="tipright" style="width:680px; padding-right:5px;">
        <ul class="tabbtn" id="normaltab">
		<li class="current"><a href="#">流量信息列表</a></li>
		<li><a href="#">流量报表折线图</a></li>
		<li><a href="#">流量报表柱状图</a></li>
		</ul>
	<div class="tabcon" id="normalcon">
		<div class="sublist">
			<ul>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>1</td>
  </tr>
</table>

			</ul>
		</div>
		<div class="sublist">
			<ul>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td>2</td>
  </tr>
</table>
                </ul>
		</div>
		<div class="sublist">
			<ul>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><img src="images/img/z.png" width="680" height="270" /></td>
  </tr>
</table>
</ul>
		</div>
		
	</div>
        </div>
        <div class="tipleft" style="width: 180px; line-height: 35px;">
					<p>
						查询条件：
					</p>
					<p>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								信号机：
							</td>
							<td>
								<select name="select" class="select2">
									<option>
										118信号服务器
									</option>
									<option>
										125信号服务器
									</option>
									<option>
										006信号服务器
									</option>
								</select>
							</td>
						</tr>
					</table>

					
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								日期：
							</td>
							<td>
								<s:select list="#

{2015:'2015',2016:'2016',2017:'2017',2018:'2018',2019:'2019',2020:'2020'}" name="year" listKey="key" listValue="value" 

cssClass="select2"></s:select>
							</td>
						</tr>
					</table>

					<p>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
								月 份：
							</td>
							<td>
								<s:select list="#

{1:'1',2:'2',3:'3',4:'4',5:'5',6:'6',7:'7',8:'8',9:'9',10:'10',11:'11',12:'12'}" name="month" listKey="key" 

listValue="value" cssClass="select2"></s:select>
							</td>
						</tr>
					</table>

					<p>
						<btn class="forminfobtn">
						查 询
						</btn>
					</p>
				</div>
			</div>


		</div>

  
  
  
  
  <div class="tip3">
   	 <div class="tiptop3"><span>故障信息</span><a></a></div>
        
      <div class="tipinfo3">
       <table class="tablelist">
         <thead>
       <tr>
         <th width="5%"><input name="input" type="checkbox" value="" checked="checked"/></th>
         <th width="10%">日期<i class="sort"><img src="images/px.gif" alt="" /></i></th>
         <th width="9%">时间</th>
         <th width="11%">设备</th>
         <th width="65%">事件</th>
       </tr>
     </thead>
     <tbody>
       <tr>
         <td><input name="input" type="checkbox" value="" /></td>
         <td>2015-12-12 </td>
         <td>10:12:08</td>
         <td><div id="u5" jquery171015793810490029025="20">
           <div id="u6" jquery171015793810490029025="21">
             <p>T42</p>
           </div>
         </div></td>
         <td>信号机--联机</td>
       </tr>
       <tr>
         <td><input name="input" type="checkbox" value="" /></td>
         <td>2015-12-12 </td>
         <td>10:12:08</td>
         <td>T11</td>
         <td>&nbsp;</td>
       </tr>
       <tr>
         <td><input name="input" type="checkbox" value="" /></td>
         <td>2015-12-12 </td>
         <td>10:12:08</td>
         <td><div id="u9" jquery171015793810490029025="20">
           <div id="u10" jquery171015793810490029025="21">
             <p>T44</p>
           </div>
         </div></td>
         <td>&nbsp;</td>
       </tr>
     </tbody>
 </table>
 <ul class="forminfo" style="line-height:40px; font-size:14px;">
     <table width="98%" border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="#93CDF3" style="margin-top:8px">
       <tr align="right" bgcolor="#EEF4EA">
         <td  height="34" align="center" bgcolor="#FFFFFF">&nbsp;</td>
         <td height="34" colspan="6" align="center" bgcolor="#FFFFFF">记录数：3&nbsp;&nbsp;&nbsp; <a href="javascript:jumpPage(1)" target="main">首页</a>&nbsp;&nbsp; <a href="javascript:jumpPage(2)" target="main">上一页</a>&nbsp;&nbsp;&nbsp; <a href="javascript:jumpPage(4)" target="main">下一页</a>&nbsp;&nbsp;&nbsp; <a href="javascript:jumpPage(5)" target="main">尾页</a>&nbsp;&nbsp;&nbsp;
           <input type='button'  class="exit" onclick="" value='转到' />
           &nbsp;
           
            <select size="1" name="page">
              <option selected="selected">第1页</option>
              <option>第2页</option>
              <option>第3页</option>
              </select>
            
           当前页：
           <input onpaste="return false" onkeypress="checkPage();" id="page" type="text" name="page" value="3" size="2" style="ime-mode=disabled;width:25px; height:20px;line-height:18px; BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #cccccc 1px solid; COLOR: #000000; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff;"/>
           /共5页</td>
       </tr>
     </table>
   </ul>
        </div>
        
    
  </div>
 -->

<!-- 
<div class="tip4">
   	 <div class="tiptop4"><span>操作日志</span><a></a></div>
      <s:action name="oplogAction!plist"></s:action>  
      <div class="tipinfo3">
            <table class="tablelist">
         <thead>
       <tr>
         <th width="5%">序号</th>
         <th width="16%">时间<i class="sort"><img src="images/px.gif" alt="" /></i></th>
         <th width="11%">登录IP</th>
         <th width="45%">操作事件</th>
         <th width="23%">操作用户</th>
       </tr>
     </thead>
     <tbody>
     <s:iterator value="%{#request.oplogs}" var="oplog" status="index">
       <tr>
         <td><s:property value="#index.count"/></td>
         <td>
         	<s:date name="optime" format="yyyy-MM-dd HH:mm:ss"/>
         </td>
         <td>
			<s:property value="usero.uip"/>
		 </td>
         <td>
			<s:if test="iptype==1">
				登录
			</s:if>
			<s:else>
				其他
			</s:else>
		</td>
         <td>
			<s:property value="usero.username"/>
		</td>
       </tr>
     </s:iterator>
     </tbody>
 </table>
   <ul class="forminfo" style="line-height: 40px; font-size: 14px;">
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
							<tr align="right" bgcolor="#EEF4EA">
								<td height="34" align="center" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
									记录数：<s:property value="totalCount" />&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpOplogPage('oplogAction!list',<s:property value="1"/>,<s:property value="uid"/>,<s:property value="logtype"/>,'<s:property value="startdate"/>','<s:property value="enddate"/>');"
										target="main">首页</a>&nbsp;&nbsp;
									<a
										href="javascript:jumpOplogPage('oplogAction!list',<s:property value="page-1"/>,<s:property value="uid"/>,<s:property value="logtype"/>,'<s:property value="startdate"/>','<s:property value="enddate"/>');"
										target="main">上一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpOplogPage('oplogAction!list',<s:property value="page+1"/>,<s:property value="uid"/>,<s:property value="logtype"/>,'<s:property value="startdate"/>','<s:property value="enddate"/>');"
										target="main">下一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpOplogPage('oplogAction!list',<s:property value="pageCount"/>,<s:property value="uid"/>,<s:property value="logtype"/>,'<s:property value="startdate"/>','<s:property value="enddate"/>');"
										target="main">尾页</a>&nbsp;&nbsp;&nbsp;
									<input type='button' class="exit"
										onclick="jumpOplogPage('oplogAction!list',document.getElementById('page').value,<s:property value="uid"/>,<s:property value="logtype"/>,'<s:property value="startdate"/>','<s:property value="enddate"/>');"
										value='转到' />
									&nbsp; 当前页：
									<input onpaste="return false" onkeypress="checkPage();"
										id="page" type="text" name="page"
										value="<s:property value="page"/>" size="2"
										style="width: 25px; height: 20px; line-height: 18px; BORDER-RIGHT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; FONT-SIZE: 13px; BORDER-LEFT: #cccccc 1px solid; COLOR: #000000; BORDER-BOTTOM: #cccccc 1px solid; FONT-FAMILY: 宋体; BACKGROUND-COLOR: #ffffff;" />
									/共
									<s:property value="pageCount" />
									页
								</td>
							</tr>
						</table>
					</ul>
      </div>
        
    
  </div>
   -->
</body>
</html>
