<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.ohand.common.common.PFConstant"%>
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

<%

	String addressType=request.getAttribute("addressType")==null?"":(request.getAttribute("addressType")+"");
	String activityId=request.getAttribute("activityId")==null?"":(request.getAttribute("activityId")+"");
	String selectedList=request.getAttribute("selectedList")==null?"":(request.getAttribute("selectedList")+"");
	
	if(addressType.equals("") || (addressType.toLowerCase()).equals("null")){
		addressType=request.getParameter("addressType")+"";
	}
	if(activityId.equals("") || (activityId.toLowerCase()).equals("null")){
		activityId=request.getParameter("activityId")+"";
	}

%>

$(function(){
	
	var addressType="<%=addressType%>";
	var activityId="<%=activityId%>";
	var url=reqAddressDataUrl(addressType,activityId);
	//各个标签页下的数据初始化
	$("#treeGroup").tree({
		url:url,
		method:'GET',
		onDblClick: function(){
			addAddrItem();
		}
	});
	
	$('#treeSelected').tree({data:<%=selectedList%>});
	
	//地址本选择按钮事件绑定
	$("#btnSelect").bind("click",function(){
		addAddrItem();
	});
	$("#btnDel").bind("click",function(){
		removeAddrItem();
	});
	$("#benDelAll").bind("click",function(){	
		removeAllAddrItem();	
	});
	
	$("#btnCancel").bind("click",function(){
		parent.closeModalWin("modalWin");
	});
	
	$("#btnOk").bind("click",function(){
		
		var activityId=getParamFromUrl("activityId");
		var paramArray=new Array();		
		var children = $('#treeSelected').tree('getChildren');
		var itemData = '';
		for(var i=0; i<children.length; i++){
			itemData=children[i].text + '-'+children[i].id+"-"+iconClsConvert(children[i].iconCls)+"-"+children[i].attributes["recordId"] ;
			paramArray.push(itemData);
		}
		if(activityId==null || activityId=="null"){
			activityId="";
		}
		parent.curAddressDealFunction.call(null,paramArray,activityId);
		parent.closeModalWin("modalWin");
		
	});
	
});
	
	

//添加一个用户到“已选用户”中
function addAddrItem(){
	
	var divisionStr=$("#addrOptions .address:visible").attr("id").substring(4);
	var selectedObj=[$('#tree'+divisionStr).tree('getSelected')];
	// 深层复制（一层一层往下复制直到最底层）
	var newObject = $.extend(true, {}, selectedObj[0]);
	var getRoots=$('#treeSelected').tree('getRoots');
	if(getRoots&&getRoots.length>0){
		for(var i=0;i<getRoots.length;i++){
			var selectedItemTemp=getRoots[i];
			if(selectedItemTemp.id==newObject.id){
				return false;
			}
		}
	}
		
	if(selectedObj[0].iconCls=="icon-group"){
		$('#treeSelected').tree('append',{
			data:newObject
		});
	}
	
}

//从“已选用户”中删除一个用户
function removeAddrItem(){
	$('#treeSelected').tree('remove', $('#treeSelected').tree('getSelected').target);
}
//清空“已选用户”
function removeAllAddrItem(){
	$($('#treeSelected').tree('getRoots')).each(function(index, element) {
		$('#treeSelected').tree('remove', this.target);
	});
}	
	
	

</script>
</head>
<body>
<div class="addrTitle">
	<span class="icon"></span><span class="title">地址本</span>
	<div class="tabAddress">
		<a id="tabAddrGroup" href="javascript:void(0)">机构</a>
	</div>
</div>
<form name="">
	<div  id="formInput">
		<div class="formContent">
			<div id="addrSearch" style="float:left">
				<table border="0" style="margin: 5px;" cellpadding="0" cellspacing="0" class="searchtable">
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
							<div class="address" id="addrGroup">
								<div class="addrHead">机构</div>
								<div class="addrContent">
									<ul id="treeGroup"></ul>
								</div>
							</div>
							<div class="address" style="display: none;"   id="addrSearchResult">
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
							<div class="addrHead">已选机构</div>
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
