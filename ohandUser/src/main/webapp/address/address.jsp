<%@ page import="com.ohand.common.common.PFConstant"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>地址本</title>
<link href="<%=PFConstant.ROOT_CSSPATH%>style2/addressPage.css" rel="stylesheet" type="text/css">
<script language="JavaScript" type="text/JavaScript" src="<%=PFConstant.ROOT_CSSPATH%>style2/uiConfig.js"></script>
<script language="JavaScript" type="text/javascript" src="/javascripts/ohand/js/cookie.js"></script>
<jsp:include page="/commonJsp/include.jsp"></jsp:include>
<script language="JavaScript" type="text/JavaScript" src="/javascripts/ohand/js/common.js"></script>
<script language="JavaScript" type="text/javascript" src="/javascripts/ohand/js/address.js"></script>
<script type="text/javascript">
</script>

</head>
<body>
<!-- UI Structure Begin "当前位置" -->
<div class="addrTitle">
	<span class="icon"></span><span class="title">地址本</span>
	<div class="tabAddress">
		<a id="tabAddrUser" href="javascript:void(0)">用户</a>
		<a id="tabAddrPrivate" href="javascript:void(0)">私有组</a>
		<a id="tabAddrOften" href="javascript:void(0)">常用人员</a>
	</div>
</div>
<!-- UI Structure End "当前位置" -->
<!-- UIStructureBegin "表单输入" -->
<form name="">
	<div class="formInput" id="formInput">
		<div class="formContent">
			<!-- UIDataBegin "表单内容" -->
			<div id="addrSearch" style="float:left">
				<table border="0" cellpadding="0" cellspacing="0" class="searchtable">
					<tr>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询:</td>
						<td><input name="textfield" type="text" style="width:200px" class="input100" value=""></td>
						<td><a class="btnAct" href="javascript:void(0)"><span><span><input name="" type="button" value="" title="查询" class="icon iconFind" id="btnSearch"></span></span></a></td>
						<td class="noteText">拼音之间请以空格隔开</td>
					</tr>
				</table>	
			</div>
			<table id="layoutAddress" width="100%" border="0" cellspacing="0" cellpadding="0" class="layoutAddress">
				<tr>
					<td class="layout_L">
						<div id="addrOptions">
							<div class="address" id="addrUser">
								<div class="addrHead">用户</div>
								<div class="addrContent">
									<ul id="treeUser"></ul>
								</div>
							</div>
							<div class="address" style="display: none;" id="addrPrivate">
								<div class="addrHead">私有组</div>
								<div class="addrContent">
									<ul id="treePrivate"></ul>
								</div>
							</div>
							<div class="address" style="display: none;" id="addrOften">
								<div class="addrHead">常用人员</div>
								<div class="addrContent">
									<ul id="treeOften"></ul>
								</div>
							</div>
							<div class="address" style="display: none;"  id="addrSearchResult">
								<div class="addrHead"><a href="javascript:void(0)" id="btnFinishSearch"></a>查询结果</div>
								<div class="addrContent">
									<ul id="treeSearchResult"></ul>
								</div>
							</div>
						</div>
					</td>
					<td class="layout_C">
						<div>
							<a class="btnAct" href="javascript:void(0)"><span><span><input value="选择 ->" type="button" id="btnSelect"></span></span></a>
							<!--<a class="btnAct" href="javascript:void(0)"><span><span><input value="全选 >>" type="button">-->
							</span></span></a>
						</div>
						<div>
							<a class="btnAct" href="javascript:void(0)"><span><span><input value="删除 <-" type="button" id="btnDel"></span></span></a>
							<a class="btnAct" href="javascript:void(0)"><span><span><input value="全删 <<" type="button" id="benDelAll">
							</span></span></a>
						</div>
					</td>
					<td class="layout_R">
						<div class="address" id="addrSelected">
							<div class="addrHead">已选用户</div>
							<div class="addrContent">
								<ul id="treeSelected" class="easyui-tree"></ul>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<table border="0" cellpadding="0" cellspacing="0" class="formSubmit">
		<tr>
			<td class="formSubmit_L"></td>
			<td class="formSubmit_C">
				<div class="formToolBar">
					<div class="secondAct">
						<a class="btnAct" href="javascript:void(0)"><span><span><input value="取消" type="button" class="icon iconDel" id="btnCancel"></span></span></a>
					</div>
					<div class="firstAct">
						<a class="btnAct" href="javascript:void(0)"><span><span><input value="确定" type="button" class="icon iconOk" id="btnOk"></span></span></a>
					</div>
				</div>
			</td>
			<td class="formSubmit_O">
			</td>
			<td class="formSubmit_R"></td>
		</tr>
	</table>
</form>
</body>
</html>
