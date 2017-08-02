// Tab½Å±¾ by dyg

var gTabBar;
function TabBar(){
	this.activeTab = null;
	this.targetTab = null;
	this.tabs = new Array();
	this.timer = null;
	this.over = true;
	
	var DELAY_TIME = 150;
	
	this.show = function(obj){
		if(this.tabs.length == 0)return;
		if(!this.activeTab)this.activeTab = document.getElementById(this.tabs[0]);
		if(this.activeTab == obj)return;
		this.targetTab = obj;
		gTabBar = this;
		setTimeout("showTab()",DELAY_TIME)
	};
	
	this.addTab = function(id){
		this.tabs.push(id);
		if(this.over){
			document.getElementById(id).onmouseout = function(){if(this.className.search("_active")!=-1)return;this.className = "tabItem";}
			document.getElementById(id).onmouseover = function(){if(this.className.search("_active")!=-1)return;this.className = "tabItem_over";}
		}
	};
}
//tabÄÚÈÝid £½ tab id £« ¡°Content¡±
function showTab(){
	gTabBar.activeTab.className = "tabItem";
	document.getElementById(gTabBar.activeTab.id+"Content").style.display = "none";
	gTabBar.activeTab = gTabBar.targetTab;
	gTabBar.activeTab.className = "tabItem_active";
	document.getElementById(gTabBar.activeTab.id+"Content").style.display = "block";
}