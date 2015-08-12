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
		<title>特勤控制</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/lbd.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		
		<script type="text/javascript">
$(document).ready(function(e) {
    $(".select1").uedSelect({
		width : 200			  
	});
	$(".select2").uedSelect({
		width : 167  
	});
	$(".select3").uedSelect({
		width : 120
	});
	$(".zxselect").uedSelect({
		width : 120
	});
	$(".fxselect").uedSelect({
		width : 120
	});
});

var conflictVOs = <s:property value="conflictVOs" escape="false"/>;
var sigVOs = <s:property value="sigVOs" escape="false"/>;
var marklineid = <s:property value="greenroad.marklineid"/>;
</script>
<script type="text/javascript" src="js/tq.js"></script>
	</head>

	<body>

		<div id="usual1" class="usual" style="margin: 15px;">

			<div class="itab">
				<ul>
					<li>
						<a href="#tab1" class="selected">特勤控制</a>
					</li>

				</ul>
			</div>


			<div style="width: 100%; float: left; margin-top: 15px;">
				<div class="lleft lbdinfo ">

					<div style="float: left; line-height: 35px; width: 90%;">
						<label>
							特勤方案名称：
						</label>
						<div class="vocation">
							<span style="margin-right: 5px;"> 
								<s:textfield name="greenroad.name" cssClass="dfinput" id="tqname" cssStyle="width: 165px;"></s:textfield>
							</span>
						</div>
					</div>
					
					<div style="width: 100%; float: left; margin-top: 28px;">
						<table >
							<thead>
								<tr>
									<th width="4%" height="40"
										style="text-align: center; line-height: 20px; text-indent: 0px;">
										编号
									</th>
									<th width="8%"
										style="text-align: center; line-height: 20px; text-indent: 10px;">
										路口名
									</th>
									<th width="40%"
										style="text-align: center; line-height: 20px; text-indent: 0px;">
										指定相位
									</th>
									<th width="16%"
										style="text-align: left; line-height: 20px; text-indent: 0px;">
										绿灯执行时间
									</th>
									<th width="16%"
										style="text-align: left; line-height: 20px; text-indent: 0px;">
										黄灯执行时间
									</th>
									<th width="16%"
										style="text-align: left; line-height: 20px; text-indent: 0px;">
										红灯灯执行时间
									</th>
								</tr>
							</thead>
							<tbody>
								<s:iterator value="sigVOs" var="sigVO" status="status">
									<tr>
										<td style="text-align: center;">
											<s:property value="number" />
										</td>
										<td style="text-align: center;">
											<s:property value="name" />
										</td>
										<td>
											   <div class="picbox">
										<div style="width: 100%; float: left; height: ">
											<div class="xhu">
											</div>
											<div class="xhu" style="margin-left: 135px;">
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												<img class="${number}_3_3" alt="北人行道" class="l23" src="images/rod/l330.png" width="15" />
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
												<img class="${number}_3_3" alt="北人行道" class="l03" src="images/rod/l330.png" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
												<img  class="${number}_2_3" alt="西人行道"  src="images/rod/l230.png" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
												<img class="${number}_0_3" alt="东人行道" src="images/rod/l030.png" width="15" />
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
															<img id="${number}_0_2"  src="images/rod/l020.png" alt="东向西右拐"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${number}_0_1"  src="images/rod/l010.png" alt="东向西直行"
																width="15" height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${number}_0_0"   src="images/rod/l000.png" alt="东向西左拐"
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
															<img id="${number}_3_2"  src="images/rod/l320.png" alt="北向南右拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="${number}_3_1"  src="images/rod/l310.png" alt="北向南直行" width="15"
																height="15" />
														</td>
														<td>
															<img  id="${number}_3_0"  src="images/rod/l300.png" alt="北向南左拐" width="15"
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
															<img id="${number}_1_0"  src="images/rod/l100.png" alt="南向北左拐" width="15"
																height="15" />
														</td>
														<td>
															<img id="${number}_1_1"  src="images/rod/l110.png" alt="南向北直行" width="15"
																height="15" />
														</td>
														<td>
															<img id="${number}_1_2"  src="images/rod/l120.png" alt="南向北右拐" width="15"
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
															<img  id="${number}_2_0"  src="images/rod/l200.png" alt="西向东左拐" width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${number}_2_1"  src="images/rod/l210.png" alt="西向东直行"  width="15"
																height="15" />
														</td>
													</tr>
													<tr>
														<td align="center">
															<img id="${number}_2_2"  src="images/rod/l220.png" alt="西向东右拐" width="15"
																height="15" />
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhup">
											
												<img class="${number}_2_3"  src="images/rod/l230.png" alt="西人行道" width="15" />
											</div>
											<div class="xhup" style="margin-left: 165px;">
											
												<img class="${number}_0_3"  src="images/rod/l030.png" alt="东人行道" width="15" />
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
												
												<img  class="${number}_1_3"  src="images/rod/l130.png" alt="南人行道" width="15" />
											
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											
												<img class="${number}_1_3"  src="images/rod/l130.png" alt="南人行道" width="15" />
											
											</div>
										</div>
										<div style="width: 100%; float: left; height: ">
											<div class="xhu" style="margin-top: 0px;">
											</div>
											<div class="xhu" style="margin-left: 135px; margin-top: 0px;">
											</div>
										</div>
									</div>
         
										</td>
										<td>
												<input type="text" alt="绿灯执行时间"
													id="<s:property value="number"/>_gtime" class="dfinput_tqtime"  value="10"
													style="width: 120px;" placeholder="范围:1-99"/> ×10秒
										</td>
										<td>
												<input type="text" alt="黄灯执行时间"
													id="<s:property value="number"/>_ytime" class="dfinput_tqtime"  value="3"
													style="width: 120px;" placeholder="范围:3-9"/> 秒
										</td>
										<td>
												<input type="text" alt="红灯执行时间"
													id="<s:property value="number"/>_rtime" class="dfinput_tqtime"  value="5"
													style="width: 120px;"  placeholder="范围:3-9"/> 秒
										</td>
									</tr>
								</s:iterator>


							</tbody>
						</table>
					</div>
					<div
						style="width: 90%; float: left; margin-top: 20px; color: #999; text-align: center;">
						<input name="input" type="button" class="scbtn" onclick="saveControl()"
							style="margin-top: 15px;"  value="保存特勤控制" />
					</div>
					
					<p>
						&nbsp;
					</p>
				</div>
			</div>
			
			
			
	</body>
</html>
