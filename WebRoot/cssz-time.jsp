<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>时间参数</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="js/privatecssz-time.js"></script>
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
							<a href="solutionAction!solutions">相位方案</a>
						</li>
						<li>
							<a href="sigtimeAction!sigtimes" class="selected">时间段参数</a>
						</li>
					</ul>
				</div>
	
				<div id="tab2" class="tabson">
					<ul class="forminfo">
						<li>
						</li>
						<li>
							<div style="float: left; line-height: 35px;">
								<label>
									时间段选择：
								</label>
								<div class="vocation">
									<select id="timetype" name="timetype" class="select1" onchange="changeTimeSelect()">
										<option <s:if test="timetype==0">selected</s:if> value="0">
											普通日
										</option>
										<option <s:if test="timetype==1">selected</s:if> value="1">
											周日
										</option>
										<option <s:if test="timetype==2">selected</s:if> value="2">
											特殊日
										</option>
									</select>
								</div>
								<b>*</b>
							</div>
							<div style="float: left; line-height: 35px; padding-left: 20px;">
								<label>
									时间段细分：
								</label>
								<div class="vocation">
									<select name="select3" id="orderid" class="select1" onchange="changeTimeSelect()">
						                 <option <s:if test="orderid==0">selected</s:if>  value="0">时间段0</option>
						                 <option <s:if test="orderid==1">selected</s:if>  value="1">时间段1</option>
						                 <option <s:if test="orderid==2">selected</s:if> value="2">时间段2</option>
						                 <option <s:if test="orderid==3">selected</s:if> value="3">时间段3</option>
						                 <option <s:if test="orderid==4">selected</s:if> value="4">时间段4</option>
						                 <option <s:if test="orderid==5">selected</s:if> value="5">时间段5</option>
						                 <option <s:if test="orderid==6">selected</s:if> value="6">时间段6</option>
						                 <option <s:if test="orderid==7">selected</s:if> value="7">时间段7</option>
						                 <option <s:if test="orderid==8">selected</s:if> value="8">时间段8</option>
						                 <option <s:if test="orderid==9">selected</s:if> value="9">时间段9</option>
						                 <option <s:if test="orderid==10">selected</s:if> value="10">时间段10</option>
						                 <option <s:if test="orderid==11">selected</s:if> value="11">时间段11</option>
						                 <option <s:if test="orderid==12">selected</s:if> value="12">时间段12</option>
						                 <option <s:if test="orderid==13">selected</s:if> value="13">时间段13</option>
						                 <option <s:if test="orderid==14">selected</s:if> value="14">时间段14</option>
						                 <option <s:if test="orderid==15">selected</s:if> value="15">时间段15</option>
						               </select>
								</div>
								<b>*</b>
							</div>
							<!--  
							<div style="float: left; line-height: 35px; padding-left: 20px;">
								<ul class="toolbar">
									<li onclick="updateStepTimes()" class="click" style="height: 28px; line-height: 28px;">
										<span><img src="images/time.png" alt="" width="24"
												height="24" />
										</span>保存并发送相位执行时间
									</li>
								</ul>
							</div>
							-->
							<b style="color:red;">注意：请按相位顺序设置执行时间.</b>
						</li>
					</ul>
					<div style="width: 100%; float: left;">
						<div class="csleft" style="line-height: 35px;">
						<form id="sigtimeform" name="sigtimeform" action="sigtimeAction!update"
									method="post" onkeydown="if(event.keyCode==13)return false;">
									<s:hidden name="commontime.id"></s:hidden>
									<s:hidden name="commontime.orderid"></s:hidden>
									<s:hidden name="commontime.timetype"></s:hidden>
									<s:hidden id="signid" name="commontime.sig.id"></s:hidden>
									<s:hidden name="commontime.t0"></s:hidden>
									<s:hidden name="commontime.t1"></s:hidden>
									<s:hidden name="commontime.t2"></s:hidden>
									<s:hidden name="commontime.t3"></s:hidden>
									<s:hidden name="commontime.t4"></s:hidden>
									<s:hidden name="commontime.t5"></s:hidden>
									<s:hidden name="commontime.t6"></s:hidden>
									<s:hidden name="commontime.t7"></s:hidden>
									<s:hidden name="commontime.t8"></s:hidden>
									<s:hidden name="commontime.t9"></s:hidden>
									<s:hidden name="commontime.t10"></s:hidden>
									<s:hidden name="commontime.t11"></s:hidden>
									<s:hidden name="commontime.t12"></s:hidden>
									<s:hidden name="commontime.t13"></s:hidden>
									<s:hidden name="commontime.t14"></s:hidden>
									<s:hidden name="commontime.t15"></s:hidden>
									<s:hidden name="commontime.t16"></s:hidden>
									<s:hidden name="commontime.t17"></s:hidden>
									<s:hidden name="commontime.t18"></s:hidden>
									<s:hidden name="commontime.t19"></s:hidden>
									<s:hidden name="commontime.t20"></s:hidden>
									<s:hidden name="commontime.t21"></s:hidden>
									<s:hidden name="commontime.t22"></s:hidden>
									<s:hidden name="commontime.t23"></s:hidden>
									<s:hidden name="commontime.t24"></s:hidden>
									<s:hidden name="commontime.t25"></s:hidden>
									<s:hidden name="commontime.t26"></s:hidden>
									<s:hidden name="commontime.t27"></s:hidden>
									<s:hidden name="commontime.t28"></s:hidden>
									<s:hidden name="commontime.t29"></s:hidden>
									<s:hidden name="commontime.t30"></s:hidden>
									<s:hidden name="commontime.t31"></s:hidden>
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											时
										</label>
									</td>
									<td>
										<s:textfield name="commontime.hour" id="hour" cssClass="dfinput"
									cssStyle="width:150px;"></s:textfield>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label style="margin-right: 20px"> 
											分
										</label>
									</td>
									<td>
									<s:textfield name="commontime.minute" id="minute" cssClass="dfinput"
									cssStyle="width:150px;"></s:textfield>
									</td>
								</tr>
								
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											秒
										</label>
									</td>
									
									<td>
										<s:if test="commontime.workingway==3">
										<s:textfield name="commontime.seconds" id="seconds" cssClass="dfinput"
									cssStyle="width:150px;" ></s:textfield>
									</s:if>
									<s:else>
										<s:textfield name="commontime.seconds" id="seconds" cssClass="dfinput"
									cssStyle="width:150px;" readonly="true"></s:textfield>
									</s:else>
									</td>
									
								</tr>
								
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											控制方式
										</label>
									</td>
									<td >
										<s:select list="#{0:'普通控制方式',1:'黄闪',2:'关灯',3:'协调控制',4:'感应控制',5:'中心控制',6:'未定义'}"
								name="commontime.workingway" listKey="key" listValue="value" cssClass="select4" id="control" onchange="changeControl()"></s:select>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											相位方案
										</label>
									</td>
									<td style="margin-left:10px">
								 
											<s:select list="solutions"   listValue="soluname" listKey="orderid"   id="soid"
						             name="commontime.workingprogram"   cssClass="select4"  onchange="changeSoSelect()"></s:select> 
									</td>
								</tr>
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											绿闪时间
										</label>
									</td>
									<td>
										<s:textfield name="commontime.lstime" id="lstime" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:0-9"></s:textfield>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											黄灯时间
										</label>
									</td>
									<td>
										<s:textfield name="commontime.hdtime" id="hdtime" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:3-9"></s:textfield>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label style="margin-right: 20px">
											红灯时间
										</label>
									</td>
									<td>
										<s:textfield name="commontime.qchdtime" id="qchdtime" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:0-9"></s:textfield>
									</td>
								</tr>
							</table>
								<b style="color:red;">*注意：唯有控制方式为协调控制时，才可设置秒数，秒表示相位差时间.</b>
								
								<div style="float: center; line-height: 35px; padding-left: 20px;">
								<ul class="toolbar2">
													<li>
														<span><img src="images/note-2.png" alt="" width="24"
																height="24" /> </span>
														<input  type="submit" id="submit_cs" class="toolbarbtn2" value="保存并发送时间段参数"
															onclick="updateStepTimes()"  />
															</li>
													</ul>
										</div>
							</form>
						</div>
						<div class="csright">
							<div class="xwlb">
							<s:iterator value="steptimes" var="step" status="status">
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
									<txt>
									<h1>
										相位<s:property value="#status.index"/>
									</h1>
									<s:property value="phasename"/>：
										<input value="${step.second}" name="${step.id}_${step.stepname}" class="timeinput" style="width: 70px; height: 20px;" placeholder="范围:10-90"/>
									秒	
									</txt>
								</li>
							</s:iterator>

							</div>
						</div>
					</div>

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
