//显示顶部消息提醒
//参数：提醒信息（一般是具体信息的标题连接，点击后直接打开该条信息）、自动关闭时间（缺省为不自动关闭）
function showTopTips(msg,time){
	window.top.$('#modalWin').modal();                      // 以默认值初始化
	window.top.$('#modalWin').modal({ keyboard: false });   // initialized with no keyboard
	window.top.$('#modalWin').modal('show') ;              // 初始化后立即调用 show 方法
	window.top.$('#modalContent').html(msg) ; 
}

//隐藏顶部消息提醒
function hideTopTips(){
	var t = $('#topTips');
	t.animate({top:-t.height()-2},800,function(){
		$(this).hide()
	});
}

//从cookie记录中获取上次导航项展开状态，并初始化，保障刷新后的导航状态一致性
function initOI(){
	var oi = getCookie("oi");
	var items = $(".outlookItem_active,.outlookItem");
	var oiLength = items.length;
	if(oiLength == 0)return;
	if(document.getElementById("outlookBar").className == "outlookBar"){
		activedOI = items.get(0);
		if(oi.search(",") != -1){
			oi = oi.split(",").pop();
		}
		if(oi){			
			activedOI.className = "outlookItem";
			activedOI.parentNode.className = "outlookItemTd";
			
			activedOI = document.getElementById("outlookItem"+oi);
			activedOI.className = "outlookItem_active";
			activedOI.parentNode.className = "outlookItemTd_active";
		}
	}else{
		isOutlook = false;
		scrObj = document.getElementById("leftNav");//.body;
		scrObj.style.overflow = "auto";
		if(oi){
			for(var i=1;i<=oiLength;i++){
				document.getElementById("outlookItem"+i).className = "outlookItem";
				document.getElementById("outlookItem"+i).parentNode.className = "outlookItemTd";
			}
			oi = oi.split(",");
			for(var j=1;j<oi.length-1;j++){
				if(oi[j]!=null&&oi[j]!=undefined&&oi[j]!=''&&oi[j]<j){
					document.getElementById("outlookItem"+oi[j]).className = "outlookItem_active";
					document.getElementById("outlookItem"+oi[j]).parentNode.className = "outlookItemTd_active";					
				}
			}
		}else{
			var sCookie = ",";
			for(var i=1;i<=oiLength;i++){
				if(document.getElementById("outlookItem"+i).className == "outlookItem_active")sCookie += i+",";
			}
			setCookie("oi",sCookie);
		}
	}
}
//左边导航列表鼠标效果对象
//左边导航列表鼠标效果对象
var leftMenuCS = new CurrentSign();
leftMenuCS.addRelateClass("infoItem","infoItem_active","infoItem_over");
function initCS(){
	
	$(".outlookHead").bind("click",function(){
		showOI(this);
	}).bind("mouseover",function(){
		$(this).addClass("outlookHead_over");
	}).bind("mouseout",function(){
		$(this).removeClass("outlookHead_over");
	});
	$(".outlookCont .infoItem").bind("click",function(){
		leftMenuCS.itemClick(this);
		//parent.navTo($(this).text(),$(this).attr("href"),"iconTabList",true);
	}).bind("mouseover",function(){
		leftMenuCS.itemOver(this);
	}).bind("mouseout",function(){
		leftMenuCS.itemOut(this);
	});
	var ni = getCookie("ni");
	if(ni){
		var obj = document.getElementById(ni);
		leftMenuCS.itemOver(obj);
		leftMenuCS.itemClick(obj);
	}
	
}

$(function(){
	var centerUrl="";
	$(".menuItem,.menuItem_active").bind("click",function(){
		var itemId=$(this).attr("itemId");
		var id=$(this).attr("id");
		if(itemId){		
			$.ajax({
				url : '/portalAction!leftFrame.action?topId='+itemId,
				type : "POST",
				async:false,
				success : function(data) {
					$("#leftFrame").html(data);
					$(".outlookCont .infoItem").bind("click",function(){
						centerUrl=$(this).attr("href");
						var thisTabName=$(this).html();
						var isModal=$(this).attr("isModal");
						var isNewPage=$(this).attr("isNewPage");
						if(isNewPage&&isNewPage=="1"){
							if(isModal&&isModal=="1"){
								window.showModalDialog(centerUrl);
							}else{
								window.open(centerUrl);
							}
						}else{
							addTab(thisTabName,centerUrl,'iconTabList');
						}
					});
					initCS();
				},
				error : function(request, textStatus, errorThrown) {
					
				}
			});
			$("#rowHomePage").hide();
			$("#rowContent").show();
		}else{
			$("#rowHomePage").show();
			$("#rowContent").hide();
		}
	}).bind("mouseover",function(){
		if(this.className != "menuItem_active")this.className = "menuItem_over";
	}).bind("mouseout",function(){
		if(this.className != "menuItem_active")this.className = "menuItem";
	}).bind("mouseenter",function(){
		//showDropDown(this);
	}).bind("mouseleave",function(){
		//hideDropDown();
	});
	
	initCS();
	initOI();
	
}); 	



//所在表格id="outlookBar"，导航项id="outlookItem"+序号
var activedOI = false;		//当前展开的outlookItem
var isOutlook = false;		//是否为只展开
//展开收起outlook导航项
function showOI(obj){
	var oTable = getParentTag(obj,"table");
	var sId = oTable.id.replace("outlookItem","");
	if(isOutlook){
		if(obj == oTable)return;
		activedOI.className = "outlookItem";
		activedOI.parentNode.className = "outlookItemTd";
		activedOI = oTable;
		activedOI.className = "outlookItem_active";
		activedOI.parentNode.className = "outlookItemTd_active";
		setCookie("oi",sId)
	}else{
		activedOI = oTable;
		var oi = getCookie("oi");
		if(activedOI.className == "outlookItem"){
			activedOI.className = "outlookItem_active";
			activedOI.parentNode.className = "outlookItemTd_active";
			setCookie("oi",oi+sId+",");
			//scrollToView(activedOI);
		}else{
			activedOI.className = "outlookItem";
			activedOI.parentNode.className = "outlookItemTd";
			setCookie("oi",oi.replace(sId+",",""));
		}
	}
}

function addTab(title ,url , clsName){
	$("#navTitle").html(title);
	$("#home").html('<iframe homeCurrentCenterPage="homeCurrentCenterPage" src='+url+' style="width: 100%; height:96%;" frameborder="0" scrolling="no"></iframe>');
	return false;
}

function shColNav(obj){
	if(obj.className.indexOf("shColNavTOLEFT")>-1){
		obj.className="shColNavTORIGHT";
		// $("#outlookBar",$("#leftFrame")).hide();
		$("#leftFrame").addClass("col-md-offset-12");
		$("#myTabGuide").removeClass("col-md-offset-2");
		$("#myTabGuide").addClass("col-md-offset-0");
		$("#myTabGuide").removeClass("col-md-10");
		$("#myTabGuide").addClass("col-md-12");
	}else{
		obj.className="shColNavTOLEFT";
		$("#outlookBar",$("#leftFrame")).show();
		$("#leftFrame").removeClass("col-md-offset-12");
		$("#myTabGuide").removeClass("col-md-offset-0");
		$("#myTabGuide").addClass("col-md-offset-2");
		$("#myTabGuide").removeClass("col-md-12");
		$("#myTabGuide").addClass("col-md-10");
		
	}
}

//获取某个tagName的父级标签
function getParentTag(obj,tag){
	tag = tag.toLowerCase();
	do{
		obj = obj.parentNode;
	}while(obj.tagName.toLowerCase() != tag)
	return obj;
}
