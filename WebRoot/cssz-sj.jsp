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
		<title>升级</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />

		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
		<script type="text/javascript" src="js/select-ui.min.js"></script>
		<script type="text/javascript" src="js/privatecssz-sj.js"></script>


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
								<a href="promotionAction!promotion" class="selected">信号机升级</a>
							</li>
							<li>
								<a href="greenAction!green">绿冲突表</a>
							</li>
						</ul>
					</div>
					<div id="tab1" class="tabson">
						<div class="csbox">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td style="padding-right: 5px;">
										<input name="" type="button" onclick="check();" class="scbtn" value="升  级" />
									</td>
									<td style="padding-right: 5px;">
										<input name="" type="submit" class="scbtn"  value="自动升级" />
									</td>
									<td>
										<div class="vocation">
											<select id="zdjs" name="select4" class="select2">
												<option>
													零时
												</option>
											</select>
										</div>
									</td>
									<td>
									&nbsp;&nbsp;&nbsp;自动升级
									</td>
								</tr>
							</table>

						</div>

					
						<div style="width: 30%; padding-top: 15px; padding-bottom: 15px; float: left;">
							<div class="csbox">
								FTP服务&nbsp;&nbsp;&nbsp;IP&nbsp;&nbsp;&nbsp;&nbsp：
								<s:textfield name="centerIp" id="centerIp" cssClass="dfinput"
									cssStyle="width:150px;" readonly="true"></s:textfield>
								
							</div>

							<div class="csbox">
								FTP服务端口号&nbsp;：
								<s:textfield name="centerPort" id="centerPort" cssClass="dfinput"
									cssStyle="width:150px;"></s:textfield>
								
							</div>

					
							</div>
						</div>
					</div>

				</div>


	</body>
</html>
