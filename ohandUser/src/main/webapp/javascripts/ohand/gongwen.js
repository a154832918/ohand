$(function(){
	//“发送-选择收件人”按钮事件绑定，Rc即Receiver
	$(".btnRc").live("click",function(){
		showReceiver();
	});
	//“返回-继续处理公文”按钮事件绑定
	$(".btnBack").live("click",function(){
		hideReceiver();
	});
	//“发送-立即发送”按钮事件绑定，RN及right now
	$(".btnRN").live("click",function(){
		$("#messager").show({
			title:'My Title',
			msg:'The message content',
			showType:'fade',
			style:{
				right:'',
				bottom:''
			}
		});
	});
	//“常用收件人>”按钮事件绑定
	$(".btnRecently").bind("click",function(){
		$(this).parent().siblings(".inputTd").children(".recentOptions").toggle();
	});
	//“收件人…”按钮事件绑定
	$(".btnAddress").bind("click",function(){	
		var thatId=(this.id).split("_")[1];
		$('#modalWin').window('body').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/address/addressPerson.jsp?activityId='+thatId+'&rdm='+Ld.common.utils.getRandom(1,10000000)+'"></iframe>');
		$('#modalWin').window('open');
	});
	//后续步骤勾选事件绑定
	$(".stepHead input").bind("click",function(){
		if(this.checked) $(this).parents(".step").addClass("stepSelected");
		else $(this).parents(".step").removeClass("stepSelected");
	});
	//工具条下拉菜单事件绑定
	$(".selectDown").bind("click",function(){
		var pre = $(this).prev();
		$('#'+$(this).attr("for")).menu('show', {
			left: pre.offset().left,
			top: pre.offset().top+pre.height()
		});		
	});
	//添加附件资料图标按钮“+”事件绑定
	$(".btnAccAdd").bind("click",function(){
		var docInstId=$("#docInstId").val();
		if(docInstId&&docInstId!="null"){
		}else{
			doSave();
		}
		var heightWin=$(window).height();
		var widthWin=$(window).width();
		var height=heightWin*0.6;
		var width=widthWin*0.6;
		var top=(heightWin-height)/2;	
		var left=(widthWin-width)/2;
		$('#attachModalWinContent').html('<iframe frameborder=\"0\" scrolling=\"no\" src=\"/flowattachfile/flowAttachFileUpload.jsp?instId='+docInstId+'\"></iframe>');
		$('#attachModalWin').window({   
		    width:width,   
		    height:height,
		    top:top,
		    left:left,
		    modal:true
		});
		$('#attachModalWin').window('open');
	});
	//删除附件资料图标按钮“-”事件绑定
	$(".btnAccRemove").bind("click",function(){
		$(this).parents(".pnlAcc").find(".list").toggleClass("delList");
	});
	//后续步骤及收件人选择区域的高度初始化设置
	resizeRc();
	//附件页面初始化
	loadAttachFile();
	//后续步骤及收件人选择区域的高度设置resize事件绑定
	$(window).bind("resize",function(){resizeRc();});
	//已选择的后续步骤的样式初始化
	$(".stepHead input:checked").parents(".step").addClass("stepSelected");
	//右边面板折叠事件绑定
	$(".pnlAcc .pnlTitle").bind("click",function(){
		var p = $(this).toggleClass("pnlTitle_closed").parents(".pnlAcc");
		$(".pnlBody",p).toggle();
	});
	//流程监控下拉框
	$("#mmJiankong").menu({   
	    onClick:function(item){  
    		var flowCode=$("#flowCode").val();
    		var docInstId=$("#docInstId").val();
	    	if(item.name&&item.name=="imageMonitor"){//图形跟踪
	    		if(!docInstId||docInstId=="null"){
	    			alert("请先保存公文...")
	    			 return false;
	    		}
	    		window.open("/ohandflow/flowObjAction!stepView.action?flowInstId="+docInstId+"&flowCode="+flowCode)
	    	}else if(item.name&&item.name=="flowMonitor"){//流程监控
	    		window.open("/ohandflow/flowObjAction!stepFlowView.action?flowInstId="+docInstId+"&flowCode="+flowCode)
	    	}
	    }   
	}); 
});

//显示后续步骤及收件人选择界面
var isFirstShowReceiver=false;
function showReceiver(){
	//先保存，在展示
	var docInstId=doSave();
	var inPageDocInstId=$("#docInstId").val();
	if(!docInstId){
	   	docInstId=inPageDocInstId;
	}
	if(!docInstId){
	   alert("表单实例存在问题...");
	   return false;
	}
	$(".receiver").css("left",document.body.clientWidth).show().animate({left:0},300);
	$(".btnRc").animate({right:100},300).removeClass("btnRc").addClass("btnBack");
	$(".btnRN").animate({right:0},300);
	if(isFirstShowReceiver==false){
		doSendDiv(docInstId);
		isFirstShowReceiver=true;
	}	
}

// 选择默认自动选择上的环节 ,TODO 添加一个 defaultReceivers参数
function activityReceiveHTMLHandle(obj){
	
	var activityReceiveHTML="";	
	if(obj.length==1&&parseInt(obj[0].activityType)==4){// 子流程
		//环节、接收人html模板
		activityReceiveHTML=activityReceiveHTML+"<div class=\"step stepSelected \">";
		activityReceiveHTML=activityReceiveHTML+"<div class=\"stepHead\">";
		activityReceiveHTML=activityReceiveHTML+"<label><input name=\"nextActivity\" type=\"checkbox\"  activityType=\""+obj[i].activityType+"\"   value=\""+obj[i].id+"_"+obj[i].activityType+"\">"+obj[0].activityName+"</label>";
		activityReceiveHTML=activityReceiveHTML+"</div>";
		activityReceiveHTML=activityReceiveHTML+"</div>";	
		return activityReceiveHTML;
	}
	
	//环节、接收人html模板
	for(var i=0;i<obj.length;i++){
		
		if(obj[i].activityType!=null&&obj[i].activityType==6){
			activityReceiveHTML=activityReceiveHTML+"<div class=\"step stepSelected \">";
			activityReceiveHTML=activityReceiveHTML+"<div class=\"stepHead\">";
			activityReceiveHTML=activityReceiveHTML+"<label><input name=\"nextActivity\" type=\"checkbox\"  activityType=\""+obj[i].activityType+"\"  value=\""+obj[i].id+"_"+obj[i].activityType+"\">"+obj[i].activityName+"</label>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
		}else{
			activityReceiveHTML=activityReceiveHTML+"<div class=\"step stepSelected \">";
			activityReceiveHTML=activityReceiveHTML+"<div class=\"stepHead\">";
			activityReceiveHTML=activityReceiveHTML+"<label><input name=\"nextActivity\" type=\"checkbox\"   activityType=\""+obj[i].activityType+"\"   value=\""+obj[i].id+"_"+obj[i].activityType+"\">"+obj[i].activityName+"</label>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
			activityReceiveHTML=activityReceiveHTML+"<div class=\"stepBody\">";
			activityReceiveHTML=activityReceiveHTML+"<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">";
			activityReceiveHTML=activityReceiveHTML+"<tr>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"labelTd\"><a href=\"#\" id='a_"+obj[i].id+"_"+obj[i].activityType+"'  class=\"btnAddress\">收件人…</a></td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"inputTd\"><input type=\"hidden\" id='hidden_"+obj[i].id+"_"+obj[i].activityType+"'  /><input name=\"\" id='show_"+obj[i].id+"_"+obj[i].activityType+"' type=\"text\" class=\"inputRc\"></td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"buttonTd\"><a href=\"#\" class=\"btnRcReset\"></a></td>";
			activityReceiveHTML=activityReceiveHTML+"</tr>";
			activityReceiveHTML=activityReceiveHTML+"<tr>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"labelTd\">固定收件人：</td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"inputTd\">";
			activityReceiveHTML=activityReceiveHTML+"<div class=\"fixedOptions\">";
			activityReceiveHTML=activityReceiveHTML+"<label><input name=\"\" type=\"checkbox\" value=\"\">罗斯元</label>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
			activityReceiveHTML=activityReceiveHTML+"</td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"buttonTd\"></td>";
			activityReceiveHTML=activityReceiveHTML+"</tr>";
			activityReceiveHTML=activityReceiveHTML+"<tr>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"labelTd\"><a href=\"#\" class=\"btnRecently\">常用收件人&gt;</a></td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"inputTd\">";
			activityReceiveHTML=activityReceiveHTML+"<div class=\"recentOptions\">";
			activityReceiveHTML=activityReceiveHTML+"<label><input name=\"\" type=\"checkbox\" value=\"\">叶小明</label>";
			activityReceiveHTML=activityReceiveHTML+"<label><input name=\"\" type=\"checkbox\" value=\"\">叶小明</label>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
			activityReceiveHTML=activityReceiveHTML+"</td>";
			activityReceiveHTML=activityReceiveHTML+"<td class=\"buttonTd\"></td>";
			activityReceiveHTML=activityReceiveHTML+"</tr>";			
			activityReceiveHTML=activityReceiveHTML+"</table>";			
			activityReceiveHTML=activityReceiveHTML+"</div>";
			activityReceiveHTML=activityReceiveHTML+"</div>";
		}
		
		
	
	}
	return activityReceiveHTML;
}

//隐藏后续步骤及收件人选择界面
function hideReceiver(){
	$(".receiver").animate({left:document.body.clientWidth},300,function(){$(this).hide()});
	$(".btnBack").animate({right:0},300).removeClass("btnBack").addClass("btnRc");
	$(".btnRN").animate({right:-100},300);
}
//设置后续步骤及收件人选择区域的高度
function resizeRc(){
	$(".steps").css("height",document.body.clientHeight-uiConfig.stepsSpaceHeight);
}
//加载附件信息
function loadAttachFile(){
	var instId=$("#docInstId").val();
	if(instId&&instId!="null"){
	}else{
		return false;
	}
	$.ajax({
		url : '/ohandflow/flowAttachFileAction!getAttachFileList.action?instId='+instId,
		type : "POST",
		success : function(data) {
			if(data&&data.length>0){
				var attachFileHtml="";
				for(var i=0;i<data.length;i++){
					var item=data[i];
						attachFileHtml=attachFileHtml+'<div class="list">';
						attachFileHtml=attachFileHtml+'<a href="#" class="accTitle iconDoc">'+item.fileName+'</a>'
						attachFileHtml=attachFileHtml+'<a href="#" class="btnAccDel"></a>'
						attachFileHtml=attachFileHtml+'<span class="accInfo">'+item.uploader+','+(item.uploaderDate)+'</span>';
						attachFileHtml=attachFileHtml+'</div>';
				}
				$("#attachFileContent").html(attachFileHtml);
			}
		},
		error : function(request, textStatus, errorThrown) {
			$("#attachFileContent").html("<span style='color:red;'>&nbsp;&nbsp;&nbsp;&nbsp;附件加载异常...</span>");
		}
	});
}
//关闭模态窗口
function closeModalWin(id){
	$("#"+id).window("close");
}