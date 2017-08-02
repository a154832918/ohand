<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.ohand.ohandform.domain.FormRFlowDomain"%>
<%@ page import="com.ohand.common.common.*"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title></title>
<jsp:include page="/commonJsp/include.jsp"></jsp:include>
<link href="<%=PFConstant.JAVASCRIPT_PATH%>bootstrap/bootstrap-3.2.0-dist/css/bootstrap.min.css" rel="stylesheet">
<SCRIPT type="text/JavaScript">
	function openAddr(type) {
		if(type==1){
			
			var selectedAll=$("#ids_1").val();
			var selected="";
			if(selectedAll&&selectedAll.length>0){
				var selectedArr=selectedAll.split(",");
				for(var i=0;i<selectedArr.length;i++){
					var selectedItem=selectedArr[i];
					var commonId=selectedItem.split("-")[1];
					if(selected==""){
						selected=commonId;
					}else{
						selected=selected+","+commonId;
					}
				}
			}
			
			curAddressDealFunction=addressImpl_1;	
			$('#modalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/ohand/commonAction!address.action?addressType=1_2&activityId=&selected='+selected+'\"></iframe>');
			$('#modalWin').window({
				title:"选择收件人、父节点为机构",
			    width:800,   
			    height:600,
			    top:30,
			    left:30,
			    modal:true
			});
			$('#modalWin').window('open');
			
		}else if(type==10){
			
			var selectedAll=$("#ids_10").val();
			var selected="";
			if(selectedAll&&selectedAll.length>0){
				var selectedArr=selectedAll.split(",");
				for(var i=0;i<selectedArr.length;i++){
					var selectedItem=selectedArr[i];
					var commonId=selectedItem.split("-")[1];
					if(selected==""){
						selected=commonId;
					}else{
						selected=selected+","+commonId;
					}
				}
			}
			
			curAddressDealFunction=addressImpl_1;	
			$('#modalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/ohand/commonAction!address.action?addressType=1_3&activityId=&selected='+selected+'\"></iframe>');
			$('#modalWin').window({
				title:"选择收件人、父节点为机构",
			    width:800,   
			    height:600,
			    top:30,
			    left:30,
			    modal:true
			});
			$('#modalWin').window('open');
			
		}else if(type==2){
			
			var selectedAll=$("#ids_2").val();
			var selected="";
			if(selectedAll&&selectedAll.length>0){
				var selectedArr=selectedAll.split(",");
				for(var i=0;i<selectedArr.length;i++){
					var selectedItem=selectedArr[i];
					var commonId=selectedItem.split("-")[1];
					if(selected==""){
						selected=commonId;
					}else{
						selected=selected+","+commonId;
					}
				}
			}
			
			
			curAddressDealFunction=addressImpl_2;	
			$('#modalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/ohand/commonAction!address.action?addressType=2_2&activityId=&selected='+selected+'\"></iframe>');
			$('#modalWin').window({ 
				title:"选择机构",
			    width:800,   
			    height:600,
			    top:30,
			    left:30,
			    modal:true
			});
			$('#modalWin').window('open');
			
		}else if(type==3){
			
			var selectedAll=$("#ids_3").val();
			var selected="";
			if(selectedAll&&selectedAll.length>0){
				var selectedArr=selectedAll.split(",");
				for(var i=0;i<selectedArr.length;i++){
					var selectedItem=selectedArr[i];
					var commonId=selectedItem.split("-")[1];
					if(selected==""){
						selected=commonId;
					}else{
						selected=selected+","+commonId;
					}
				}
			}
			
			curAddressDealFunction=addressImpl_3;	
			$('#modalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/ohand/commonAction!address.action?addressType=3_2&activityId=&selected='+selected+'\"></iframe>');
			$('#modalWin').window({ 
				title:"选择组",
			    width:800,   
			    height:600,
			    top:30,
			    left:30,
			    modal:true
			});
			$('#modalWin').window('open');
			
		}else if(type==5){
			
			var fixed="";
			alert("传入人员的id或者机构的id或者组的id")
			var selectedAll=$("#ids_5").val();
			var selected="";
			if(selectedAll&&selectedAll.length>0){
				var selectedArr=selectedAll.split(",");
				for(var i=0;i<selectedArr.length;i++){
					var selectedItem=selectedArr[i];
					var commonId=selectedItem.split("-")[1];
					if(selected==""){
						selected=commonId;
					}else{
						selected=selected+","+commonId;
					}
				}
			}
			
			curAddressDealFunction=addressImpl_5;	
			$('#modalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/ohand/commonAction!address.action?addressType=5_2&activityId=&selected='+selected+'\"></iframe>');
			$('#modalWin').window({ 
				title:"选择固定人员",
			    width:800,   
			    height:600,
			    top:30,
			    left:30,
			    modal:true
			});
			$('#modalWin').window('open');
			
		}
	}

	function addressImpl_1(paramArray,activityId){
		if(paramArray!=undefined&&paramArray!=null&&paramArray.length>0){
		    var showName="";
		    var passItem="";
			for(var i=0;i<paramArray.length;i++){
				var item=paramArray[i];
				var name=item.split("-")[0];
				if(i==0){
					showName=name;
					passItem=item;
				}else{
					showName=showName+","+name;
					passItem=passItem+","+item;
				}
			}
			document.getElementById("names_1").value=showName;
			document.getElementById("ids_1").value=passItem;
		}
	}
	
	function addressImpl_2(paramArray,activityId){
		if(paramArray!=undefined&&paramArray!=null&&paramArray.length>0){
		    var showName="";
		    var passItem="";
			for(var i=0;i<paramArray.length;i++){
				var item=paramArray[i];
				var name=item.split("-")[0];
				if(i==0){
					showName=name;
					passItem=item;
				}else{
					showName=showName+","+name;
					passItem=passItem+","+item;
				}
			}
			document.getElementById("names_2").value=showName;
			document.getElementById("ids_2").value=passItem;
		}
	}	
	
	function addressImpl_3(paramArray,activityId){
		if(paramArray!=undefined&&paramArray!=null&&paramArray.length>0){
		    var showName="";
		    var passItem="";
			for(var i=0;i<paramArray.length;i++){
				var item=paramArray[i];
				var name=item.split("-")[0];
				if(i==0){
					showName=name;
					passItem=item;
				}else{
					showName=showName+","+name;
					passItem=passItem+","+item;
				}
			}
			document.getElementById("names_3").value=showName;
			document.getElementById("ids_3").value=passItem;
		}
	}	
	
	
	function addressImpl_5(paramArray,activityId){
		if(paramArray!=undefined&&paramArray!=null&&paramArray.length>0){
		    var showName="";
		    var passItem="";
			for(var i=0;i<paramArray.length;i++){
				var item=paramArray[i];
				var name=item.split("-")[0];
				if(i==0){
					showName=name;
					passItem=item;
				}else{
					showName=showName+","+name;
					passItem=passItem+","+item;
				}
			}
			document.getElementById("names_5").value=showName;
			document.getElementById("ids_5").value=passItem;
		}
	}	
		
	function  closeModalWin(){
		$('#modalWin').window('close');
	}
	
</script>

<style type="text/css">

	a:hover{ 
		background-color:lightGreen;
		color:purple;
		padding: 10px;
	}
	
	a{
		padding: 10px;
	}

</style>

</head>
<body >
	
		<table class="table table-bordered">
		<tr>
				<td style="text-align: center" colspan="4">
					<b>测试问题方向</b>
				</td>	
			</tr>
			<tr>
				<td colspan="4">
					<ul>
						<li>1、展示问题（easyui、bootstrap2种风格）</li>
						<li>2、选择之后的回填问题</li>
						<li>3、保存到数据库的格式，统一规范</li>
					</ul>
				</td>
			</tr>	
			<tr>
				<td>
					<input  class="form-control"  type="text" id="names_1" value=""  />
				</td>
				<td>
					<a  href="#"> 
						<span style="margin: 15px;font-size: 15px;" onclick="javascript:openAddr(1)"  class="glyphicon glyphicon-pencil ">人员(父节点为机构)</span>
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input   class="form-control" type="text" id="ids_1" value="" />
				</td>
			</tr>
			<tr>
				<td>
					<input  class="form-control"  type="text" id="names_10" value=""  />
				</td>
				<td>
					<a  href="#"> 
						<span style="margin: 15px;font-size: 15px;" onclick="javascript:openAddr(10)"  class="glyphicon glyphicon-pencil ">人员(父节点为组)</span>
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input   class="form-control" type="text" id="ids_10" value="" />
				</td>
			</tr>		
			<tr>
				<td>
					<input  class="form-control"  type="text" id="names_2"  />
				</td>
				<td>
					<a  href="#"> 
						<span style="margin: 15px;font-size: 15px;" onclick="javascript:openAddr(2)"  class="glyphicon glyphicon-pencil ">机构</span>
					</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input   class="form-control" type="text" id="ids_2"  />
				</td>
			</tr>
			<tr>
				<td>
					<input  class="form-control" type="text" id="names_3"  />
				</td>
				<td>
					<a  href="#"> 
						<span style="margin: 15px;font-size: 15px;" onclick="javascript:openAddr(3)"  class="glyphicon glyphicon-pencil ">组</span>
					</a>
				</td>
			</tr>	
			<tr>
				<td colspan="3">
					<input   class="form-control" type="text" id="ids_3"  />
				</td>
			</tr>				
			<tr>
				<td>
					<input  class="form-control" type="text" id="names_5"  />
				</td>
				<td>
					<a  href="#"> 
						<span style="margin: 15px;font-size: 15px;" onclick="javascript:openAddr(5)"  class="glyphicon glyphicon-pencil ">固定人员</span>
					</a>
				</td>
			</tr>		
			<tr>
				<td colspan="3">
					<input   class="form-control" type="text" id="ids_5"  />
				</td>
			</tr>		
		</table>	
		<div id="modalWin" class="easyui-dialog" title="选择收件人"
			data-options="iconCls:'iconUser1',modal:true,closed:true" style="overflow:hidden;">
			<div id="modalWinContent" style="width:100%;height:100%;"></div>
		</div>				
</body>
</html>
