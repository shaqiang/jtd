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
		<title>汽车流量折线图</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
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
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
  	<script src="js/highcharts.js"></script>
	<script src="js/modules/exporting.js"></script>
  <script>
  
    var categorielist = new Array(20);
  	var dleftlist=new Array(20);
  	var dlinelist=new Array(20);
  	var drightlist=new Array(20);
  	var nleftlist=new Array(20);
  	var nlinelist=new Array(20);
  	var nrightlist=new Array(20);
  	var xleftlist=new Array(20);
  	var xlinelist=new Array(20);
  	var xrightlist=new Array(20);
  	var bleftlist=new Array(20);
  	var blinelist=new Array(20);
  	var brightlist=new Array(20);
  	<s:iterator value="flows" var="flow" status="index">
  		categorielist[<s:property value="#index.count"/>-1]="<s:date name='time' format='yyyy-MM-dd HH:mm:ss'/>";
  		////-------------------------------------------------东
  		<s:if test='dleft==null'>
  			dleftlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			dleftlist[<s:property value="#index.count"/>-1]=<s:property value="dleft"/>;
  		</s:else>
  		
  		<s:if test='dline==null'>
  			dlinelist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			dlinelist[<s:property value="#index.count"/>-1]=<s:property value="dline"/>;
  		</s:else>
  		
  		<s:if test='dright==null'>
  			drightlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			drightlist[<s:property value="#index.count"/>-1]=<s:property value="dright"/>;
  		</s:else>
  		//-------------------------------------------------南
  		<s:if test='nleft==null'>
  			nleftlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			nleftlist[<s:property value="#index.count"/>-1]=<s:property value="nleft"/>;
  		</s:else>
  		
  		<s:if test='nline==null'>
  			nlinelist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			nlinelist[<s:property value="#index.count"/>-1]=<s:property value="nline"/>;
  		</s:else>
  		
  		<s:if test='nright==null'>
  			nrightlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			nrightlist[<s:property value="#index.count"/>-1]=<s:property value="nright"/>;
  		</s:else>
  		//-------------------------------------------------西
  		<s:if test='xleft==null'>
  			xleftlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			xleftlist[<s:property value="#index.count"/>-1]=<s:property value="xleft"/>;
  		</s:else>
  		
  		<s:if test='xline==null'>
  			xlinelist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			xlinelist[<s:property value="#index.count"/>-1]=<s:property value="xline"/>;
  		</s:else>
  		
  		
  		<s:if test='xright==null'>
  			xrightlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			xrightlist[<s:property value="#index.count"/>-1]=<s:property value="xright"/>;
  		</s:else>
  		
  		//-------------------------------------------------北
  		<s:if test='bleft==null'>
  			bleftlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			bleftlist[<s:property value="#index.count"/>-1]=<s:property value="bleft"/>;
  		</s:else>
  		
  		<s:if test='bline==null'>
  			blinelist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			blinelist[<s:property value="#index.count"/>-1]=<s:property value="bline"/>;
  		</s:else>
  		
  		
  		<s:if test='bright==null'>
  			brightlist[<s:property value="#index.count"/>-1]=0;
  		</s:if>
  		<s:else>
  			brightlist[<s:property value="#index.count"/>-1]=<s:property value="bright"/>;
  		</s:else>
  		//alert(dleftlist[<s:property value="#index.count"/>-1]+dlinelist[<s:property value="#index.count"/>-1]+drightlist[<s:property value="#index.count"/>-1]+nleftlist[<s:property value="#index.count"/>-1]+nlinelist[<s:property value="#index.count"/>-1]+nrightlist[<s:property value="#index.count"/>-1]+xleftlist[<s:property value="#index.count"/>-1]+xlinelist[<s:property value="#index.count"/>-1]+xrightlist[<s:property value="#index.count"/>-1]+bleftlist[<s:property value="#index.count"/>-1]+blinelist[<s:property value="#index.count"/>-1]+brightlist[<s:property value="#index.count"/>-1]);
  		var avtage = (dleftlist[<s:property value="#index.count"/>-1]+dlinelist[<s:property value="#index.count"/>-1]+drightlist[<s:property value="#index.count"/>-1]+nleftlist[<s:property value="#index.count"/>-1]+nlinelist[<s:property value="#index.count"/>-1]+nrightlist[<s:property value="#index.count"/>-1]+xleftlist[<s:property value="#index.count"/>-1]+xlinelist[<s:property value="#index.count"/>-1]+xrightlist[<s:property value="#index.count"/>-1]+bleftlist[<s:property value="#index.count"/>-1]+blinelist[<s:property value="#index.count"/>-1]+brightlist[<s:property value="#index.count"/>-1])/12;
  		dleftlist[<s:property value="#index.count"/>-1]=dleftlist[<s:property value="#index.count"/>-1]+avtage;
  		dlinelist[<s:property value="#index.count"/>-1]=dlinelist[<s:property value="#index.count"/>-1]+avtage*2;
  		drightlist[<s:property value="#index.count"/>-1]=drightlist[<s:property value="#index.count"/>-1]+avtage*3;
  		nleftlist[<s:property value="#index.count"/>-1]=nleftlist[<s:property value="#index.count"/>-1]+avtage*4;
  		nlinelist[<s:property value="#index.count"/>-1]=nlinelist[<s:property value="#index.count"/>-1]+avtage*5;
  		nrightlist[<s:property value="#index.count"/>-1]=nrightlist[<s:property value="#index.count"/>-1]+avtage*6;
  		xleftlist[<s:property value="#index.count"/>-1]=xleftlist[<s:property value="#index.count"/>-1]+avtage*7;
  		xlinelist[<s:property value="#index.count"/>-1]=xlinelist[<s:property value="#index.count"/>-1]+avtage*8;
  		xrightlist[<s:property value="#index.count"/>-1]=xrightlist[<s:property value="#index.count"/>-1]+avtage*9;
  		bleftlist[<s:property value="#index.count"/>-1]=bleftlist[<s:property value="#index.count"/>-1]+avtage*10;
  		blinelist[<s:property value="#index.count"/>-1]=blinelist[<s:property value="#index.count"/>-1]+avtage*11;
  		brightlist[<s:property value="#index.count"/>-1]=brightlist[<s:property value="#index.count"/>-1]+avtage*12;
  	</s:iterator>
  	
    
    $(function () {
    $('#container').highcharts({
        title: {
            text: '车流量折线图',
            x: -20 //center
        },
        subtitle: {
            text: '按时间显示车流量',
            x: -20
        },
        xAxis: {
            categories: categorielist
        },
        yAxis: {
            title: {
                text: '折线趋势'
            },
             labels:{
		        enabled: false
		    },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '辆',
            enabled: false,
        },
        credits: {
                text: '',
                href: 'http://#'
            },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '东左',
            data: dleftlist
        }, {
            name: '东直',
            data: dlinelist
        }, {
            name: '东右',
            data: drightlist
        }, {
            name: '南左',
            data: nleftlist
        }, {
            name: '南直',
            data: nlinelist
        }, {
            name: '南右',
            data: nrightlist
        }, {
            name: '西左',
            data: xleftlist
        }, {
            name: '西直',
            data: xlinelist
        }, {
            name: '西右',
            data: xrightlist
        }, {
            name: '北左',
            data: bleftlist
        }, {
            name: '北直',
            data: blinelist
        }, {
            name: '北右',
            data: brightlist
        }]
    });
});
  </script>
	</head>

	<body>



		<div class="formbody">


			<div id="usual1" class="usual">

				<div class="itab">
					<ul>

						<li>
							<a href="flowAction!list?sigid=<s:property value="sigid"/>">流量信息列表</a>
						</li>
						<li>
							<a href="#tab3" class="selected">流量报表折线图</a>
						</li>
						
						<li>
							<a href="flowAction!listbar?sigid=<s:property value="sigid"/>">流量报表柱状图</a>
						</li>
						 
					</ul>
				</div>





				<div id="tab3" class="tabson">
					<form action="flowAction!listline" method="post">
					<ul class="forminfo">
						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr style="height: 35px;">
									<td>信号机：</td>
									<td><s:select list="sigs" name="sigid" cssStyle="width:150px;height:25px;border:#bbb 1px solid;" listKey="id" listValue="name"></s:select></td>
								</tr>
								<tr style="height: 35px;">
									<td>
									时间：
									</td>
									<td>
									<input name="time1" id="startdate" value="<s:property value="time1"/>" style="width: 150px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,maxDate:'#F{$dp.$D(\'enddate\')}'})" />
									至
									<input name="time2" id="enddate" value="<s:property value="time2"/>" style="width: 150px;" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startdate\')}',startDate:'#F{$dp.$D(\'startdate\',{d:+1})}'})" />
									</td>
								</tr>
								<tr style="height: 35px;">
									<td>间隔：</td>
									<td><s:select list="#{1:'1分钟',2:'1小时',3:'1天',4:'1周'}" name="interval" cssStyle="width:150px;height:25px;border:#bbb 1px solid;" listKey="key" listValue="value"></s:select></td>
								</tr>
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
					<div id="container" style="min-width:700px;height:400px"></div>
					<ul class="forminfo" style="line-height: 40px; font-size: 14px;">
						<table width="98%" border="0" align="center" cellpadding="2"
							cellspacing="1" bgcolor="#93CDF3" style="margin-top: 8px">
							<tr align="right" bgcolor="#EEF4EA">
								<td height="34" align="center" bgcolor="#FFFFFF">
									&nbsp;
								</td>
								<td height="34" colspan="6" align="center" bgcolor="#FFFFFF">
									
									<a
										href="javascript:jumpFlowLinePage('flowAction!listline',<s:property value="1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>',<s:property value="interval"/>,'');"
										target="main">首页</a>&nbsp;&nbsp;
									<a
										href="javascript:jumpFlowLinePage('flowAction!listline',<s:property value="page-1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>',<s:property value="interval"/>,'<s:property value="nextstarttime"/>');"
										target="main">上一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpFlowLinePage('flowAction!listline',<s:property value="page+1"/>,<s:property value="sigid"/>,'<s:property value="time1"/>','<s:property value="time2"/>',<s:property value="interval"/>,'<s:property value="nextstarttime"/>');"
										target="main">下一页</a>&nbsp;&nbsp;&nbsp;
									
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
