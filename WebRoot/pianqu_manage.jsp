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
		<title>片区管理</title>
		<link href="css/style.css" rel="stylesheet" type="text/css" />
		<link href="css/select.css" rel="stylesheet" type="text/css" />
		<link href="css/stilearn-helper.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script src="js/stilearn-base.js"></script>
		<script src="js/holder.js"></script>

		<script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
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
							<a href="#tab4" class="selected">片区管理</a>
						</li>
						<!--
    <li><a href="#tab3">权限分配</a></li> 
  -->
					</ul>
				</div>

				<div id="tab4" class="tabson">
				<!--
					<form action="userareaAction!add" method="post">
					<ul class="forminfo">
						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										<label>
											片区名：
										</label>
									</td>
									<td width="220">
										<s:textfield name="userarea.uareaname" cssClass="dfinput"
											value="请填写新的片区名" cssStyle="width:200px;" onclick="this.value=''"></s:textfield>
										<b>*</b>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</li>

						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										<label>
											精 度：
										</label>
									</td>
									<td width="220">
										<s:textfield name="userarea.lat" cssClass="dfinput"
											cssStyle="width:200px;"></s:textfield>
										<b>*</b>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										<label>
											纬 度：
										</label>
									</td>
									<td width="220">
										<s:textfield name="userarea.lng" cssClass="dfinput"
											cssStyle="width:200px;"></s:textfield>
										<b>*</b>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</li>
						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										<label>
											放大倍数：
										</label>
									</td>
									<td width="220">
										<s:textfield name="userarea.size" cssClass="dfinput"
											cssStyle="width:200px;" value="1"></s:textfield>
										<b>*</b>
									</td>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</li>
						<li>
							<div style="float: left; line-height: 35px;">
								<label>
									所属管理员：
								</label>
								<div class="vocation">
									<s:select list="useros" listKey="id"
										listValue="username" name="userarea.usero.id" cssClass="select1"></s:select>
								</div>
								<b>*</b>
							</div>
						</li>


						<li>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="85">
										&nbsp;
									</td>
									<td>
									<s:token></s:token>
										<input value="保存" type="submit" class="scbtn" />
									</td>
								</tr>
								<tr>
									<td width="85">
										&nbsp;
									</td>
									<td>
										<font color="red" size="16"><s:property value="outinfo"/></font>
									</td>
								</tr>
							</table>
						</li>
					</ul>
					</form>
					  -->
					<div class="tools">
						<ul class="seachform">
							<li>
								<label>
									输入片区名：
								</label>
								<input name="uareaname" id="uareaname"
									value="<s:property value='uareaname'/>" type="text"
									class="scinput" />
							</li>
							<li>
								<label>
									&nbsp;
								</label>
								<input name="input2" type="button" class="scbtn" value="查询"
									onclick="jumpAllPage('userareaAction!alllist',1,document.getElementById('uareaname').value);" />
							</li>
						</ul>
					</div>
					<table class="tablelist">
						<thead>
							<tr>
								<!-- 
       <th width="5%"><input name="input" type="checkbox" value="" checked="checked"/></th>
        -->

								<th width="6%">
									序号
									<i class="sort"><img src="images/px.gif" alt="" />
									</i>
								</th>
								<th width="66%">
									片区名
								</th>
								<th width="23%">
									操作
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="userareas" var="userarea" status="index">
								<tr>
									<!-- 
     	<td><input name="input" type="checkbox" value="" /></td>
     	 -->

									<td>
										<s:property value="#index.count" />
									</td>
									<td>
										<s:property value="uareaname" />
									</td>
									
									<td>
										<a
											href="userareaAction!load?id=<s:property value="id"/>&page=<s:property value="page"/>"
											class="tablelink">编辑 </a>
										<a
											href="userareaAction!delete?id=<s:property value="id"/>&page=<s:property value="page"/>"
											class="tablelink" onclick="return confirm('你确定删除该信息吗？')"> 删除</a>
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
									记录数：
									<s:property value="totalCount" />
									&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpAllPage('userareaAction!alllist',<s:property value="1"/>,'<s:property value="uareaname"/>');"
										target="main">首页</a>&nbsp;&nbsp;
									<a
										href="javascript:jumpAllPage('userareaAction!alllist',<s:property value="page-1"/>,'<s:property value="uareaname"/>');"
										target="main">上一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpAllPage('userareaAction!alllist',<s:property value="page+1"/>,'<s:property value="uareaname"/>');"
										target="main">下一页</a>&nbsp;&nbsp;&nbsp;
									<a
										href="javascript:jumpAllPage('userareaAction!alllist',<s:property value="pageCount"/>,'<s:property value="uareaname"/>');"
										target="main">尾页</a>&nbsp;&nbsp;&nbsp;
									<input type='button' class="exit"
										onclick="jumpAllPage('userareaAction!alllist',document.getElementById('page').value,'<s:property value="uareaname"/>');"
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
