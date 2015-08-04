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
		<title>汽车流量直方图</title>
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
  		
  	</s:iterator>
  	
  	/*
  	var categorielist = new Array('2015-06-26 09:24:15', '2015-06-26 09:25:15', '2015-06-26 09:26:15', '2015-06-26 09:27:15', '2015-06-26 09:28:15', '2015-06-26 09:29:15','2015-06-26 09:30:15', '2015-06-26 09:31:15', '2015-06-26 09:32:15', '2015-06-26 09:33:15', '2015-06-26 09:34:15', '2015-06-26 09:35:15'
  	, '2015-06-26 09:36:15', '2015-06-26 09:37:15', '2015-06-26 09:38:15', '2015-06-26 09:39:15', '2015-06-26 09:40:15', '2015-06-26 09:41:15', '2015-06-26 09:42:15', '2015-06-26 09:43:15'
  	);
  	var dleftlist=new Array(7, 6, 9, 10, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var dlinelist=new Array(7, 6, 9, 14, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var drightlist=new Array(7, 6, 9, 13, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var nleftlist=new Array(7, 6, 9, 11, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var nlinelist=new Array(7, 6, 9, 18, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var nrightlist=new Array(7, 6, 9, 4, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var xleftlist=new Array(7, 6, 9, 5, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var xlinelist=new Array(7, 6, 9, 7, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var xrightlist=new Array(7, 6, 9, 8, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var bleftlist=new Array(7, 6, 9, 9, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var blinelist=new Array(7, 6, 9, 20, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
  	var brightlist=new Array(7, 6, 9, 23, 18, 21, 25, 26, 23, 18, 13, 9, 9, 14, 18, 21, 25, 26, 23, 18);
    */
  
    $(function () {
    $('#container').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '车流量柱状图'
        },
        subtitle: {
            text: '按时间显示车流量'
        },
        credits: {
                text: '',
                href: 'http://#'
            },
        xAxis: {
            categories: categorielist
        },
        yAxis: {
            min: 0,
            title: {
                text: '数量(辆)'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 辆</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
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
							<a href="flowAction!listline?sigid=<s:property value="sigid"/>&interval=1">流量报表折线图</a>
						</li>
						
						<li>
							<a href="#tab3" class="selected">流量报表柱状图</a>
						</li>
						 
					</ul>
				</div>





				<div id="tab3" class="tabson">
					<form action="flowAction!listbar" method="post">
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
								<!-- 
								<tr style="height: 35px;">
									<td>间隔：</td>
									<td><s:select list="#{1:'1分钟',2:'1小时',3:'1天',4:'1周'}" name="interval" cssStyle="width:150px;height:25px;border:#bbb 1px solid;" listKey="key" listValue="value"></s:select></td>
								</tr>
								 -->
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
				
					
				</div>
				<div id="container" style="min-width:700px;height:400px"></div>


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
