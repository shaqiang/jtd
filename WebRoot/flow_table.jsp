<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
		<title>汽车流量数据表</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>

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
});
</script>
	<script type="text/javascript" src="js/pageKit.js"></script>
	</head>

	<body>



		<div class="formbody">


			<div id="usual1" class="usual">

				<div class="itab">
					<ul>

						<li>
							<a href="#tab3" class="selected">流量信息列表</a>
						</li>
						<li>
							<a href="flowAction!listline?sigid=<s:property value="sigid"/>&interval=1">流量报表折线图</a>
						</li>
						
						<li>
							<a href="flowAction!listbar?sigid=<s:property value="sigid"/>">流量报表柱状图</a>
						</li>
						
					</ul>
				</div>





				<div id="tab3" class="tabson">
					<form action="flowAction!list" method="post">
					<ul class="forminfo">
						<li>
							<div style="float: left; line-height: 35px;">
								<label>
									信号机：
								</label>
								<div class="vocation">
									<s:select list="sigs" name="sigid" cssClass="select1" listKey="id" listValue="name"></s:select>
								</div>
								<b>*</b>
							</div>
						</li>
						

						<li>
							<div style="float: left; line-height: 35px;">
								<label>
									时间：
								</label>
								<div class="vocation">
									<input name="time1" id="startdate" value="<s:property value="time1"/>" style="width: 150px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'enddate\')}'})" />
									至
									<input name="time2" id="enddate" value="<s:property value="time2"/>" style="width: 150px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}',startDate:'#F{$dp.$D(\'startdate\',{d:+1})}'})" />
								</div>
						</li>
						<!-- 
						<li>
							<div style="float: left; line-height: 35px;">
								<label>
									时间：
								</label>
								<div class="vocation">
									<s:select list="#{1:'1分钟',2:'1小时',3:'1天',4:'1周'}" name="interval" cssStyle="width:150px;height:25px;border:#bbb 1px solid;" cssClass="select1" listKey="key" listValue="value"></s:select>
								</div>
						</li>
						 -->
						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										&nbsp;
									</td>
									<td height="50">
										<input name="input2" type="submit" class="scbtn" value="查询" />

										<input name="input3" type="reset" class="scbtn" value="重置" />
									</td>
								</tr>
							</table>
						</li>

					</ul>
					</form>
					<table class="tablelist">
						<thead>
							<tr>
								<th width="5%">
									序号
								</th>
								<th width="35%">
									时间
									<i class="sort"><img src="images/px.gif" alt="" />
									</i>
								</th>
								<th width="5%">
									东左
								</th>
								<th width="5%">
									东直
								</th>
								<th width="5%">
									东右
								</th>
								<th width="5%">
									南左
								</th>
								<th width="5%">
									南直
								</th>
								<th width="5%">
									南右
								</th>
								<th width="5%">
									西左
								</th>
								<th width="5%">
									西直
								</th>
								<th width="5%">
									西右
								</th>
								<th width="5%">
									北左
								</th>
								<th width="5%">
									北直
								</th>
								<th width="5%">
									北右
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="flows" var="flow" status="index">
							<tr>
								<td>
									<s:property value="#index.count"/>
								</td>
								<td>
									<s:date name="time" format="yyyy-MM-dd HH:mm:ss"/>
								</td>
								<td>
									<s:property value="dleft"/>
								</td>
								<td>
									<s:property value="dline"/>
								</td>
								<td>
									<s:property value="dright"/>
								</td>
								<td>
									<s:property value="nleft"/>
								</td>
								<td>
									<s:property value="nline"/>
								</td>
								<td>
									<s:property value="nright"/>
								</td>
								<td>
									<s:property value="xleft"/>
								</td>
								<td>
									<s:property value="xline"/>
								</td>
								<td>
									<s:property value="xright"/>
								</td>
								<td>
									<s:property value="bleft"/>
								</td>
								<td>
									<s:property value="bline"/>
								</td>
								<td>
									<s:property value="bright"/>
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
										href="javascript:jumpFlowPage('flowAction!list',<s:property value="1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>');"
										target="main">首页</a>&nbsp;&nbsp;
									<a
										href="javascript:jumpFlowPage('flowAction!list',<s:property value="page-1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>');"
										target="main">上一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpFlowPage('flowAction!list',<s:property value="page+1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>');"
										target="main">下一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpFlowPage('flowAction!list',<s:property value="pageCount"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>');"
										target="main">尾页</a>&nbsp;&nbsp;&nbsp;
									<input type='button' class="exit"
										onclick="jumpFlowPage('flowAction!list',document.getElementById('page').value,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>');"
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



				<!-- 
				<div id="tab4" class="tabson">
					
				</div>
				 -->

				<script type="text/javascript"> 
      $("#usual1 ul").idTabs(); 
    </script>

				<script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>





			</div>

		</div>

	</body>
</html>
