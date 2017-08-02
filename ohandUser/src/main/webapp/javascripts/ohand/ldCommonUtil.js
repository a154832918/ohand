
$.fn.serializeObject = function() {
      var o = {};
      var a = this.serializeArray();
      $.each(a, function() {
          if (o[this.name] !== undefined) {
              if (!o[this.name].push) {
                  o[this.name] = [o[this.name]];
              }
              o[this.name].push(this.value || '');
          } else {
              o[this.name] = this.value || '';
          }
      });
      return o;
};

if(!Ld) {var Ld = {};}
if(!Ld.common) {Ld.common = {};}
Ld.common.utils = {		
	getParamFromUrl : function(name) {
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.location.href);
		if (results == null) {
			return "";
		} else {
			return results[1];
		}
	},
	getTopParamFromUrl: function(name) {
		name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
		var regexS = "[\\?&]" + name + "=([^&#]*)";
		var regex = new RegExp(regexS);
		var results = regex.exec(window.top.location.href);
		if (results == null) {
			return "";
		} else {
			return results[1];
		}
	},
	getRandom: function (min,max){
	    return Math.floor(Math.random()*(max-min)+min);
	},
	easyui : {
		datagrid : {
			retSelectedId : function(name,isLower,key) {
				var selectRows = $('#'+name).datagrid('getSelections');
				if(selectRows==null||selectRows==""){
					return null;
				}else{
					var retStr="";
					for(var i=0;i<selectRows.length;i++){
						if(i==0){
							if(key){
								retStr=selectRows[i][key];
							}else{
								if(isLower){
									retStr=selectRows[i].id;
								}else{
									retStr=selectRows[i].ID;
								}
							}
						}else{
							if(key){
								retStr=retStr+","+selectRows[i][key];
							}else{
								if(isLower){
									retStr=retStr+","+selectRows[i].id;
								}else{
									retStr=retStr+","+selectRows[i].ID;
								}
							}
						}
					}
					return retStr;
				}
			},
			hasChildNode : function(name) {
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
		},
		treegrid : {
			retSelectedId : function(name,isLower) {
				var selectRows = $('#'+name).treegrid('getSelections');
				if(selectRows==null||selectRows==""){
					return null;
				}else{
					var retStr="";
					for(var i=0;i<selectRows.length;i++){
						if(i==0){
							if(isLower){
								retStr=selectRows[i].id;
							}else{
								retStr=selectRows[i].ID;
							}
						}else{
							if(isLower){
								retStr=retStr+","+selectRows[i].id;
							}else{
								retStr=retStr+","+selectRows[i].ID;
							}
						}
					}
					return retStr;
				}
			},
			hasChildNode : function(name) {
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
		},
		databox:{
			formatter:function(date){
				
			}
		}
	},
	ldForm :{
		retObjCss :function(objId){//TODO
			if(objId!=undefined && objId!=null && objId.length>0){
				var type=objId.substr(0, 1); 
				// alert(type);
			}
		}
	}
};

function fillWidthSize(percent) {   
	 var bodyWidth = $(window).width();    
	 return bodyWidth * percent;
}

function fillHeightSize(percent) {   
	 var bodyHeight = $(window).height();  	 
	 return bodyHeight * percent;
}

/**
 * 对ajax进行封装
 */
function Cmp_ajaxWrapper(configObj){
	/**
	 * 对ajax进行封装--默认配置
	 */
	var ajaxWrapperConfigObj={
			url:'',
			isAsync:true,
			type:'POST',
			data:{},
			dataType: "json",
			contentType : "application/json;charset=utf-8",
			successFunc:function(data){},
			failFunc:function(response, textStatus, errorThrown){
				alert(response.responseText);
			}
	}
	if(!configObj.url){
		top.$.messager.alert("提示","没有配置请求路径...","warn");
		return false;
	}
	if(!configObj.isAsync&&configObj.isAsync!=false){
		configObj.isAsync=ajaxWrapperConfigObj.isAsync;
	}
	if(!configObj.type){
		configObj.type=ajaxWrapperConfigObj.type;
	}
	if(!configObj.successFunc){
		configObj.successFunc=ajaxWrapperConfigObj.successFunc;
	}
	if(!configObj.failFunc){
		configObj.failFunc=ajaxWrapperConfigObj.failFunc;
	}
	if(!configObj.data){
		configObj.data=ajaxWrapperConfigObj.data;
	}
	if(!configObj.contentType){
		configObj.contentType=ajaxWrapperConfigObj.contentType;
	}
	$.ajax({
		url : configObj.url,
		type : configObj.type,
		async:configObj.isAsync,
		data:configObj.data,
		contentType:configObj.contentType,
		success : function(data) {
			configObj.successFunc(data);
		},
		error : function(response, textStatus, errorThrown) {
			configObj.failFunc(response, textStatus, errorThrown);
		}
	});
}

function _inArray(val, arr) {
	for (var i = 0, len = arr.length; i < len; i++) {
		if (val === arr[i]) {
			return i;
		}
	}
	return -1;
}

function _inString(val, str, delimiter) {
	delimiter = delimiter === undefined ? ',' : delimiter;
	return (delimiter + str + delimiter).indexOf(delimiter + val + delimiter) >= 0;
}

function unixToDate_(unixTime, isFull){ 
		if(!unixTime){
			return "";
		}
        var time = new Date(unixTime);    
        var ymdhis = "";    
        ymdhis += time.getUTCFullYear() + "-";    
        ymdhis += time.getUTCMonth() + "-";    
        ymdhis += time.getUTCDate();    
        if ( isFull === true )    
        {    
               ymdhis += " " + time.getUTCHours() + ":";    
               ymdhis += time.getUTCMinutes() + ":";    
               ymdhis += time.getUTCSeconds();    
         }    
       return ymdhis;    
 } 

function convertDateDataGrid(value,row){
	return unixToDate_(value,false);
}

function replaceAll(s1,s2,s3){
	return  s1.replace(new RegExp(s2,"gm"),s3);
}

function formatterYesNo(value){
	if(value=="1"){
		return "是";
	}else{
		return "否";
	}
}

function formatterGender(value){
	if(value=="2"){
		return "男";
	}else{
		return "女";
	}
}

/**
 *  功能：获取当前系统页面主题
 */
function Cmp_SkinType(){
	var skinType= window.top.$("#modalWin").attr("skinType");
	return skinType
}
/**
 * 功能：普通页面查询按钮事件
 * @param height 查询区域高度
 */
function Cmp_BtnSearch(height){
	if(!height){
		height=90;
	}
	var isShow=$("#complexSearch").attr("isShow");
	if(isShow=="false"){
		var html=$("#complexSearchForm").html();
			$("#complexSearch").attr("isShow", "true");
			$("input:text,textarea").bind("focus", function() {
				$(this).addClass("inputFocus");
			}).bind("blur", function() {
				$(this).removeClass("inputFocus");
			});
			$("input:text,textarea").first().focus().select();
			var northPanel=$('body').layout('panel','north');
			northPanel.panel('resize',{
				height: (height+38)
			});
			$('body').layout('resize');
			$("#complexSearch").html(html);
		} else {
			$("#complexSearch").html("");
			$("#complexSearch").attr("isShow", "false");
			var northPanel=$('body').layout('panel','north');
			northPanel.panel('resize',{
				height: 38
			});
			$('body').layout('resize');
		}
}
/**
 * 功能：普通页面删除按钮事件
 * @param dataUrl 刷新datagrid的请求路径
 */
function Cmp_RemoveData(dataUrl,type){
	if(!type){
		type="POST";
	}
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.ajax({
	    		url : dataUrl,
	    		type : type,
	    		success : function() {
	    			try{
	    				top.showTopTips("删除成功！",'2000');	
	    				refreshData();
	    			}catch(e){
	    				$.messager.alert("提示","删除成功！","info");
	    				window.location.reload();
	    			}
	    		},
	    		error : function(response, textStatus, errorThrown) {
	    			top.$.messager.alert("警告",response.responseText,"warning");
	    		}
	    	});    
	    }    
	});  
}

/**
 * 功能：编辑页面关闭事件
 * @param id:对话框id
 */
function Cmp_CloseModalWin(id){
	var skinType=Cmp_SkinType();
	if(skinType&&skinType=="bootstrap"){
		window.top.$('#modalWin').modal('hide') ;
	}else{
		top.$('#'+id).dialog("close");
	}
}
/**
 * 功能：普通页面刷新事件
 * @param id 元素Id
 * @param dataUrl 刷新datagrid的请求路径
 */
function Cmp_refreshData(id,dataUrl){
	$('#'+id).datagrid('unselectAll');
	$('#'+id).datagrid({
	    url:dataUrl,
	    loadMsg:'更新数据......'
	  });
}
/**
 * 功能：普通页面新增、修改按钮事件
 * @param centerUrl 刷新datagrid的请求路径
 * @param operateUrl 当前页面路径
 * @param width 对话框宽度
 * @param height 对话框高度
 * @param top  对话框顶部距离
 * @param left 对话框左边距离
 * 
 */
function Cmp_openEditDialog(centerUrl,operateUrl,width,height,top1,left,operateWindow){
	if(centerUrl.indexOf("?")>0){
		centerUrl=centerUrl+"&rdm="+Ld.common.utils.getRandom(1,10000000);	
	}else{
		centerUrl=centerUrl+"?rdm="+Ld.common.utils.getRandom(1,10000000);
	}
	var skinType=Cmp_SkinType();
	if(skinType&&skinType=="bootstrap"){
		window.top.$("#modalWin").data("operateUrl", operateUrl);
		$.get(centerUrl, function(data){
			
			window.top.$('#modalWin').modal();                      // 以默认值初始化
			window.top.$('#modalWin').modal({ keyboard: false });   // initialized with no keyboard
			window.top.$('#modalWin').modal('show') ;              // 初始化后立即调用 show 方法
			window.top.$('#modalContent').html(data) ; 
			for ( var i = 0; i < $.parser.plugins.length; i++) {
				var name = $.parser.plugins[i];
				window.top.$.parser.parse(".easyui-"+name,window.top.$('#modalWin'));
			}
			
		}); 
	}else{
		window.top.$("#modalWin").data("operateUrl", operateUrl);
		window.top.$("#modalWin").data("operateWindow", operateWindow);
		$.get(centerUrl, function(data){
			window.top.$('#modalWin').dialog({
				title : '编辑...',
				width : width==null?($(window.top.window).width()*0.8):width,
				height : height==null?($(window.top.window).height()*0.8):height,
				closed : false,
				top:top1==null?($(window.top.window).height()*0.1):top1,
				left:left==null?($(window.top.window).width()*0.1):left,
				cache : false,
				content:data,
				modal : true
			}); 
		}); 
	}
}

/**
 * 功能：普通页面新增、修改按钮事件
 * @param centerUrl 刷新datagrid的请求路径
 * @param operateUrl 当前页面路径
 * @param width 对话框宽度
 * @param height 对话框高度
 * @param top  对话框顶部距离
 * @param left 对话框左边距离
 * 
 */
function Cmp_openEditDialogWithHTML(html,operateUrl,width,height,top1,left){
	var skinType=Cmp_SkinType();
	if(skinType&&skinType=="bootstrap"){
		window.top.$("#modalWin").data("operateUrl", operateUrl);
			window.top.$('#modalWin').modal();                      // 以默认值初始化
			window.top.$('#modalWin').modal({ keyboard: false });   // initialized with no keyboard
			window.top.$('#modalWin').modal('show') ;              // 初始化后立即调用 show 方法
			window.top.$('#modalContent').html(html) ; 
			for ( var i = 0; i < $.parser.plugins.length; i++) {
				var name = $.parser.plugins[i];
				window.top.$.parser.parse(".easyui-"+name,window.top.$('#modalWin'));
			}
	}else{
		window.top.$("#modalWin").data("operateUrl", operateUrl);
		window.top.$('#modalWin').dialog({
				title : '编辑...',
				width : width==null?($(window.top.window).width()*0.8):width,
				height : height==null?($(window.top.window).height()*0.8):height,
				closed : false,
				top:top1==null?($(window.top.window).height()*0.1):top1,
				left:left==null?($(window.top.window).width()*0.1):left,
				cache : false,
				content:html,
				modal : true
			}); 
	}
}

function openWorkItem(instId,workItemId){
	var dataUrl="/ohandflow/processAction!docInstDeal.action?docInstId="+instId+"&workItemId="+workItemId;
	window.open(dataUrl, "公文表单");
}

function getFormatJsonStrFromString(jsonStr){
        var res="";
        for(var i=0,j=0,k=0,ii,ele;i<jsonStr.length;i++)
        {//k:缩进，j:""个数
            ele=jsonStr.charAt(i);
            if(j%2==0&&ele=="}")
            {
                k--;                
                for(ii=0;ii<k;ii++) ele="    "+ele;
                ele="\n"+ele;
            }
            else if(j%2==0&&ele=="{")
            {
                ele+="\n";
                k++;            
                for(ii=0;ii<k;ii++) ele+="    ";
            }
            else if(j%2==0&&ele==",")
            {
                ele+="\n";
                for(ii=0;ii<k;ii++) ele+="    ";
            }
            else if(ele=="\"") j++;
            res+=ele;               
        }
     return res;
 }

function serializeForm(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

function Cmp_loading(msg){
	var maskDiv= $("<div style=\"top:'38px';\" class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: "100%" }).appendTo("body");
	var maskDivMsg=$("<div style=\"top:50%;right:50%; \" class=\"datagrid-mask-msg\"></div>").html(msg).appendTo("body").css({ display: "block" });
	var objArr=new Array();
	    objArr[0]=maskDiv;
	    objArr[1]=maskDivMsg;
	return  objArr
}

function Cmp_unLoading(objArr){
	objArr[0].remove();
	objArr[1].remove();
}

function fmoneyDataGrid(value,row,index){
	var money=fmoney(value,2);
	if(money&&money.indexOf("NaN")==-1){
		return money;
	}else {
		return "";
	}
	
}
//金额格式化 s:金额   n:精度
function fmoney(s, n)   
{   
   if(s==undefined){
	   return "";
   }
   n = n > 0 && n <= 20 ? n : 2;   
   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";   
   var l = s.split(".")[0].split("").reverse(),   
   r = s.split(".")[1];   
   t = "";   
   for(i = 0; i < l.length; i ++ )   
   {   
      t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");   
   }   
   return t.split("").reverse().join("") + "." + r;   
}
