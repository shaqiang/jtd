<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
String path = request.getContextPath(); String basePath =
request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>相位方案</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="js/privatecssz-fa.js"></script>

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
		<div class="formbody">
			<div id="usual1" class="usual">
				<div class="itab">
					<ul>
						<li>
							<a href="sigpublicparamAction!publicParam">公共参数</a>
						</li>
						<li>
							<a href="solutionAction!solutions" class="selected">相位方案</a>
						</li>
						<li>
							<a href="sigtimeAction!sigtimes">时间段参数</a>
						</li>
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
				                
				<div id="tab3" class="tabson">
					<div style="width: 100%; float: left;">
						<ul class="forminfo">
							<li></li>
							<li>
								<div style="float: left; line-height: 35px;">
									<label>
										选择相位方案：
									</label>
									<div class="vocation">
										<s:select list="solutions" name="solution.id"  id="solutions"
							listKey="id" listValue="soluname" value="solution.id" onchange="changeSolution()" cssClass="select1"></s:select>
									</div>
									&nbsp;&nbsp;&nbsp;&nbsp;<b style="color:red;">*注意：请按顺序操作相位.</b>
								</div>
							<div style="float: left; line-height: 35px; padding-left: 20px;">
								<ul class="toolbar">
									<li onclick="saveSolution()" style="height: 28px; line-height: 28px;">
										<span><img src="images/note-2.png" width="24"
												height="20" />
										</span>保存并发送相位方案
									</li>
								</ul>
							</div>
							</li>
						</ul>
						<div>
							<div class="xwlb">
							
							<s:iterator value="steps" var="step" status="status">
									<!--单个相位图li开始 -->
								<li>
									<!--相位图开始 -->
									<div class="picbox">
										<div style="width: 100%; float: left; height: ">
											<div class="xhu">
											</div>
											<div class="xhu" style="margin-left: 135px;">
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												<img class="${step.id}_3_3" alt="北人行道" class="l23" src="images/rod/l33${step.roads[3].rxcolor}.png" width="15" />
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
												<img class="${step.id}_3_3" alt="北人行道" class="l03" src="images/rod/l33${step.roads[3].rxcolor}.png" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
												<img  class="${step.id}_2_3" alt="西人行道"  src="images/rod/l23${step.roads[2].rxcolor}.png" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
												<img class="${step.id}_0_3" alt="东人行道" src="images/rod/l03${step.roads[0].rxcolor}.png" width="15" />
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
															<img id="${step.id}_0_2"  src="images/rod/l02${step.roads[0].rightcolor}.png" alt="东向西右拐"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${step.id}_0_1"  src="images/rod/l01${step.roads[0].linecolor}.png" alt="东向西直行"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${step.id}_0_0"   src="images/rod/l00${step.roads[0].leftcolor}.png" alt="东向西左拐"
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
															<img id="${step.id}_3_2"  src="images/rod/l32${step.roads[3].rightcolor}.png" alt="北向南右拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="${step.id}_3_1"  src="images/rod/l31${step.roads[3].linecolor}.png" alt="北向南直行" width="15"
																height="15" />
														</td>
														<td>
															<img  id="${step.id}_3_0"  src="images/rod/l30${step.roads[3].leftcolor}.png" alt="北向南左拐" width="15"
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
															<img id="${step.id}_1_0"  src="images/rod/l10${step.roads[1].leftcolor}.png" alt="南向北左拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="${step.id}_1_1"  src="images/rod/l11${step.roads[1].linecolor}.png" alt="南向北直行" width="15"
																height="15" />
														</td>
														<td>
															<img id="${step.id}_1_2"  src="images/rod/l12${step.roads[1].rightcolor}.png" alt="南向北右拐" width="15"
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
															<img  id="${step.id}_2_0"  src="images/rod/l20${step.roads[2].leftcolor}.png" alt="西向东左拐" width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${step.id}_2_1"  src="images/rod/l21${step.roads[2].linecolor}.png" alt="西向东直行"  width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${step.id}_2_2"  src="images/rod/l22${step.roads[2].rightcolor}.png" alt="西向东右拐" width="15"
																height="15" />
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
											
												<img class="${step.id}_2_3"  src="images/rod/l23${step.roads[2].rxcolor}.png" alt="西人行道" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
											
												<img class="${step.id}_0_3"  src="images/rod/l03${step.roads[0].rxcolor}.png" alt="东人行道" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												
												<img  class="${step.id}_1_3"  src="images/rod/l13${step.roads[1].rxcolor}.png" alt="南人行道" width="15" />
											
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											
												<img class="${step.id}_1_3"  src="images/rod/l13${step.roads[1].rxcolor}.png" alt="南人行道" width="15" />
											
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											</div>
										</div>
									</div>
									<!--相位图结束 -->
									<h1>
										相位<s:property value="#status.index"/>
									</h1>
									<button onclick="clearAllLight(${step.id})">清  空</button>
									<button onclick="allRed(${step.id})">全 红</button>
								</li>
							</s:iterator>
							
						
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
