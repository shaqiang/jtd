<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>一般参数</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="editor/kindeditor.js"></script>
		<script type="text/javascript" src="js/privatecssz-cs.js"></script>
		<script type="text/javascript" src="js/privatesig.js"></script>

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
		width : 167  
	});
	$(".select3").uedSelect({
		width : 100
	});
  $(".select4").uedSelect({
    width : 150
  });
  
  $("#xyxr").click(function () {
		if($("#xyxr").val()==0)
  		{
  			$("#xyxr").val(1);
  		}else
  		{
  			$("#xyxr").val(0);
  		}
	});
	
	$("#spetime").click(function () {
		if($("#spetime").val()==0)
  		{
  			$("#spetime").val(1);
  		}else
  		{
  			$("#spetime").val(0);
  		}
	});
	
	$("#suntime").click(function () {
		if($("#suntime").val()==0)
  		{
  			$("#suntime").val(1);
  		}else
  		{
  			$("#suntime").val(0);
  		}
	});
  
 
});
</script>
	</head>

	<body onload="initPublicParam()"  bgcolor="#EAF1F7">

		<form id="publicparamform" name="publicparamform" action="sigpublicparamAction!update"
			method="post">

			<div class="formbody">
				<div id="usual1" class="usual">
					<div class="itab">
						<ul>
							<li>
								<a href="sigpublicparamAction!publicParam" class="selected">一般参数</a>
							</li>
							<li>
								<a href="solutionAction!solutions">相位方案</a>
							</li>
							<li>
								<a href="sigtimeAction!sigtimes">时间段参数</a>
							</li>
						</ul>
					</div>

					<div id="tab1" class="tabson">
						<div class="csbox">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="padding-right: 5px;">
										<input name="" type="button" class="scbtn" onclick="suretime();" value="校 时" />
									</td>
									<td style="padding-right: 5px;">
										<input name="" type="button" class="scbtn"  onclick="autojs()" value="自动校时" />
									</td>
									<td>
										<div class="vocation">
											<select id="zdjs" name="select4" class="select2">
												<option value="0">
													五分钟
												</option>
												<option value="1">
													十分钟
												</option>
												<option value="2">
													三十分钟
												</option>
												<option value="3">
													一小时
												</option>
												<option value="4">
													一天
												</option>
												<option value="5">
													一周
												</option>
											</select>
										</div>
									</td>
									<td>
									&nbsp;&nbsp;&nbsp;后自动校验
									</td>
								</tr>
							</table>

						</div>


						<div style="width: 30%; padding-top: 15px; padding-bottom: 15px; float: left;">
							<s:hidden name="sigpubparam.id"></s:hidden>
							<s:hidden name="sigpubparam.ip"></s:hidden>
							<s:hidden name="sigpubparam.number"></s:hidden>
							<s:hidden name="sigpubparam.comparam"></s:hidden>
							<s:hidden name="sigpubparam.checkflow"></s:hidden>
							<s:hidden name="sigpubparam.innermark"></s:hidden>
							<s:hidden name="sigpubparam.paramversion"></s:hidden>
							<s:hidden name="sigpubparam.countdownmode"></s:hidden>
							<s:hidden name="sigpubparam.jgkz"></s:hidden>
							<s:hidden name="sigpubparam.centerport"></s:hidden>
							<s:hidden name="sigpubparam.centerip"></s:hidden>
							<s:hidden name="sigpubparam.checklight"></s:hidden>
							<s:hidden name="sigpubparam.sbnetmastk"></s:hidden>
							<s:hidden name="sigpubparam.gateway"></s:hidden>
							<div class="csbox">
								开机黄闪：
								<s:textfield name="sigpubparam.kjhstime" id="kjhs" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:0-30"></s:textfield>
								秒
							</div>

							<div class="csbox">
								清场红灯：
								<s:textfield name="sigpubparam.qchdtime" id="qchd" cssClass="dfinput"
									cssStyle="width:150px;"  placeholder="范围:0-9"></s:textfield>
								秒
							</div>

							<div class="csbox">
								最短绿灯：
								<s:textfield name="sigpubparam.gmintime" id="gmin" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:10-90"></s:textfield>
								秒
							</div>
							<div class="csbox">
								最长绿灯：
								<s:textfield name="sigpubparam.gmaxtime" id="gmax" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:大于最短绿灯小于90"></s:textfield>
								秒
							</div>
							<div class="csbox">
								步长时间：
								<s:textfield name="sigpubparam.zdbctime" id="bc" cssClass="dfinput"
									cssStyle="width:150px;" placeholder="范围:3-15"></s:textfield>
								秒
							</div>
							<div style="height: 50px;"></div>
							<div>

								<div class="csbox">

									<input id="xyxr" name="sigpubparam.xyxr" class="checkinput"
										type="checkbox" value="${sigpubparam.xyxr}" />

									<txt>
									行人触发功能使用
									</txt>
								</div>
								<div id="xr">
									<div class="csbox">
										行人请求：
										<s:textfield  name="sigpubparam.xrfxtime" cssClass="dfinput"
											cssStyle="width:150px;" id="xrfx" placeholder="范围:30-90"></s:textfield>
										秒
									</div>
									<div class="csbox">
										请求周期：
										<s:textfield name="sigpubparam.cycle" cssClass="dfinput"
											cssStyle="width:150px;" id="cycle" placeholder="范围:0-255"></s:textfield>
										×10秒
									</div>
								</div>
							</div>
						</div>
						<div
							style="width: 70%; padding-top: 15px; padding-bottom: 15px; float: left;">
							<div>
								<span class="csbox"> <input type="checkbox"
										class="checkinput" id="suntime" name="suntimeable"
									 value="${suntimeable}" /> 周 日</span>
							</div>
							<div id="sunt">
								<table width="90%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="mon" name="sigpubparam.mon"
													value="${sigpubparam.mon}" cssClass="checkinput"></input>
												星期一</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="tue" name="sigpubparam.tue"
													value="${sigpubparam.tue}" cssClass="checkinput"></input>
												星期二</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="wes" name="sigpubparam.wes"
													value="${sigpubparam.wes}" cssClass="checkinput"></input>
												星期三</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="thir" name="sigpubparam.thir"
													value="${sigpubparam.thir}" cssClass="checkinput"></input>
												星期四</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="fra" name="sigpubparam.fra"
													value="${sigpubparam.fra}" cssClass="checkinput"></input>
												星期五</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="sata" name="sigpubparam.sata"
													value="${sigpubparam.sata}" cssClass="checkinput"></input>
												星期六</span>
										</td>
										<td>
											<span class="csbox"> <input type="checkbox"
													class="checkinput" id="sun" name="sigpubparam.sun"
													value="${sigpubparam.sun}" cssClass="checkinput"></input>
												星期天</span>
										</td>

									</tr>
									<tr>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</div>
							<div>
								<span class="csbox"> <input id="spetime"
										name="spetimeable" class="checkinput" type="checkbox"
										value="${spetimeable}" /> 特殊日</span>
							</div>
							<div id="spe">
								<table width="98%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td>
											<span class="csbox"> 01、 <s:textfield name="sigpubparam.specialmonth0"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday0" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 02、 <s:textfield name="sigpubparam.specialmonth1"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday1" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 03、 <s:textfield name="sigpubparam.specialmonth2"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday2" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 04、 <s:textfield name="sigpubparam.specialmonth3"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday3" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 05、 <s:textfield name="sigpubparam.specialmonth4"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday4" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
									</tr>
									<tr>
										<td>
											<span class="csbox"> 06、 <s:textfield name="sigpubparam.specialmonth5"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday5" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 07、 <s:textfield name="sigpubparam.specialmonth6"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday6" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 08、 <s:textfield name="sigpubparam.specialmonth7"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday7" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 09、 <s:textfield name="sigpubparam.specialmonth8"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday8" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> <label>
													10、
												</label> <s:textfield name="sigpubparam.specialmonth9" cssClass="ydfinput"
													cssStyle="width:30px;"></s:textfield> 月 <s:textfield
													name="sigpubparam.specialday9" cssClass="rdfinput" cssStyle="width:30px;"></s:textfield>
												日</span>
										</td>
									</tr>
									<tr>
										<td>
											<span class="csbox"> 11、 <s:textfield name="sigpubparam.specialmonth10"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday10" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 12、 <s:textfield name="sigpubparam.specialmonth11"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday11" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 13、 <s:textfield name="sigpubparam.specialmonth12"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday12" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 14、 <s:textfield name="sigpubparam.specialmonth13"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday13" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 15、 <s:textfield name="sigpubparam.specialmonth14"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday14" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
									</tr>
									<tr>
										<td>
											<span class="csbox"> 16、 <s:textfield name="sigpubparam.specialmonth15"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday15" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 17、 <s:textfield name="sigpubparam.specialmonth16"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday16" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 18、 <s:textfield name="sigpubparam.specialmonth17"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday17" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 19、 <s:textfield name="sigpubparam.specialmonth18"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday18" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 20、 <s:textfield name="sigpubparam.specialmonth19"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday19" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
									</tr>
									<tr>
										<td>
											<span class="csbox"> 21、 <s:textfield name="sigpubparam.specialmonth20"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday20" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 22、 <s:textfield name="sigpubparam.specialmonth21"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday21" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 23、 <s:textfield name="sigpubparam.specialmonth22"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday22" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											<span class="csbox"> 24、 <s:textfield name="sigpubparam.specialmonth23"
													cssClass="ydfinput" cssStyle="width:30px;"></s:textfield> 月
												<s:textfield name="sigpubparam.specialday23" cssClass="rdfinput"
													cssStyle="width:30px;"></s:textfield> 日</span>
										</td>
										<td>
											&nbsp;
										</td>
									</tr>
								</table>
							</div>

						</div>

						<div class="tools">

							<ul class="toolbar2">
								<li>
									<span><img src="images/settings.png" alt="" width="24"
											height="24" /> </span>
									<input name="" type="submit" class="toolbarbtn" value="设置完成"
										onclick="" />
								<li>
									<span><img src="images/recycle.png" alt="" width="24"
											height="24" /> </span>
									<input name="reset" type="reset" class="toolbarbtn" value="恢复默认"
										onclick="" />
								<li>
									<span><img src="images/clipboard.png" alt="" width="24"
											height="24" /> </span>
									<input name="5" type="button" class="toolbarbtn" value="调　阅"
										onclick="executeCommand(5)" />
								<li>
									<span><img src="images/t03.png" alt="" /> </span>
									<input name="clean" type="button" class="toolbarbtn" value="清　空" onclick="cleanAll()"/>
							</ul>

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

		</form>
	</body>
</html>
