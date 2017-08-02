$(function(){
	//输入框显示效果事件绑定
	$("input:text,textarea").bind("focus", function(){
		$(this).addClass("inputFocus");
	}).bind("blur", function(){
		$(this).removeClass("inputFocus");
	}); 
/*	$("input:text,textarea").first().focus().select();*/
	//中间区域高度设置resize事件绑定
	$(window).bind("resize",function(){
		resizeFormHeight();
	});
	//中间区域高度初始化设置
	resizeFormHeight();
	//地址本初始化
	initAddress();
	//初始化中间区域宽度
	$(window).bind("resize",function(){
		$('#layoutAddress').width(fillWidthSize(0.95));
	});
	$('#layoutAddress').width(fillWidthSize(0.95));
	
}); 
//中间区域高度设置
function resizeFormHeight(){
	var fillHeightSizeInt=parseInt(fillHeightSize(1));
	var topInt=parseInt($("div#formInput").position().top);
	var outerHeightInt=parseInt($("table.formSubmit").outerHeight());
	$("div.formInput").height(fillHeightSizeInt - topInt - outerHeightInt);
	$("div.formContent").height(fillHeightSizeInt - topInt - outerHeightInt-40);
	$("table#layoutAddress").height($("div.formContent").height()-$("#addrSearch").height()-40);
	// $("div#addrOptions").height($("div.formContent").height()-$("#addrSearch").height()-$("div.addrHead").height());
	$("div.addrContent").height($("div.formContent").height()-$("#addrSearch").height()-$("div.addrHead").height());
}
//地址本初始化
function initAddress(){
	//上次使用状态还原，主要是标签页的还原
	var id = getCookie("addrTab");	
	if(!id)id = $(".tabAddress a").first().attr("id");
	$("#"+id).addClass("tabItem_active");
	$("#a"+id.replace("tabA","")).show();
	
	//地址本标签页选择事件绑定
	$(".tabAddress a").bind("click",function(){
		if(!$(this).hasClass("tabItem_active")){
			$(this).addClass("tabItem_active").siblings(".tabItem_active").removeClass("tabItem_active");
			$("#a"+this.id.replace("tabA","")).show().siblings(":visible").hide();
			setCookie("addrTab",this.id);
		}
	});
	//地址本搜索事件绑定，包括搜索和关闭搜索
	$("#btnSearch").bind("click",function(){
		$(".tabItem_active").removeClass("tabItem_active").addClass("tabItem");
		var paramName=$("#textfield").val();
		if(!paramName){
			alert("请输入查询条件！");
			return false;
		}
		var getRoots=$('#treeUser').tree('getRoots');
		var topIds="";
		if(getRoots!=null&&getRoots!=undefined&&getRoots.length>0){
			for(var i=0;i<getRoots.length;i++){
				var root=getRoots[i];
				if(topIds==""){
					topIds=root.id;
				}else{
					topIds=topIds+","+root.id;
				}
			}
		}
		
		$("#treeSearchResult").tree({
			url:"/ohand/commonAction!searchAddressResult.action?rdm="+Ld.common.utils.getRandom(1,100000),
			queryParams: {
				name: paramName,
				topIds:topIds
			},
			onDblClick: function(node){
				addAddrItem();
			}
		});

		$("#addrSearchResult").show().siblings(":visible").hide();
		
	});
	$("#btnFinishSearch").bind("click",function(){
		var id = $(".tabItem").removeClass("tabItem").addClass("tabItem_active").attr("id");
		$("#addrSearchResult").hide();
		$("#a"+id.replace("tabA","")).show();
	});

}

function fillHeightSize(percent) {   
	 var bodyHeight = $(window).height();  	 
	 return bodyHeight * percent;
}

function fillWidthSize(percent) {   
	 var bodyWidth = $(window).width();    
	 return bodyWidth * percent;
}

function getParamFromUrl(name) {
	name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
	var regexS = "[\\?&]" + name + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.href);
	if (results == null) {
		return null;
	} else {
		return results[1];
	}
}

function reqAddressDataUrl(addressType,activityId){
	var addressUrlObj={};
		addressUrlObj._1_2='/ohand/commonAction!buildTreeJson.action?addressType=1_2&activityId='+activityId;
		addressUrlObj._1_3='/ohand/commonAction!buildTreeJson.action?addressType=1_3&activityId='+activityId;
		addressUrlObj._2_2='/ohand/commonAction!buildTreeJson.action?addressType=2_2&activityId='+activityId;
		addressUrlObj._3_2='/ohand/commonAction!buildTreeJson.action?addressType=3_2&activityId='+activityId;
		addressUrlObj._4_2='/ohand/commonAction!buildTreeJson.action?addressType=3_2&activityId='+activityId;
		addressUrlObj._5_2='/ohand/commonAction!buildTreeJson.action?addressType=5_2&activityId='+activityId;
		if(!addressUrlObj["_"+addressType]){
			return "";
		}
	return addressUrlObj["_"+addressType];
}

/**
 * @param paramStr
 * json 数据格式 {paramStr:[id;name,idname,idname]}
 * 
 */
function reqAddressSelectedDataUrl(paramStr){
	
}

function iconClsConvert(iconCls){
	if(iconCls=="icon-user8"){
		return 1;
	}else if(iconCls=="icon-dept"){
		return 2;
	}else if(iconCls=="icon-group"){
		return 3;
	}else if(iconCls=="icon-role"){
		return 4;
	}
}

