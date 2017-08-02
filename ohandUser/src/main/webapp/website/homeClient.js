
	var todo=(function todoData(){
		var todo=new Array();
		var todoData=new Array();
		var todoTotal=0;
				
		$.ajax({
			url : '/ohandFlow/missiveToDo/6',
			type : "GET",
			async:false,
			success : function(data) {
				var classes = eval(data);
					todoTotal=classes.total;
					todoData=classes.rows;
					todo[0]=todoTotal;
					todo[1]=todoData;
		        },
			error : function(request, textStatus, errorThrown) {
				 // alert("获取数据异常！")
			}
		})
		
		return todo;
		
	})();

	var personInfo=(function todoData(){
		var ret={};
		$.ajax({
			url : '/ohandUser/personInfo',
			type : "GET",
			async:false,
			success : function(data) {
				ret=data;
		    },
			error : function(request, textStatus, errorThrown) {
				 alert("获取数据异常！")
			}
		})
		
		return ret;
		
	})();
	
	
 	var  vue= new Vue({
		    el:'#app',
		    methods:{
		    	openOhandQuick:function(){
		    		alert("openOhandQuick")
		    	},
 				openWorkItem:openWorkItem,
 				alterPersonInfo:alterPersonInfo,
 				uploadImage:uploadImage,
 				alterPwd:alterPwd,
 				toHome:toHome,
 				menuClick:menuClick
		    },
		    data:{
		    	todoTotal:todo[0],
		    	todoData:todo[1],
		    	personInfo:personInfo
		    }
	});
 	
 	function alterPersonInfo(){
 		$(".modal-body").html('<iframe src="/ohand/personAction!_personCenter1.action" frameborder="0" scrolling="no" style="height:550px;width:100%;"></iframe>');
 		$('#myModal').modal({
 	        keyboard: true
 	    })
 	}
 	
 	function toHome(){
 		top.window.location.href="/portalAction!homeClient.action";
 	}
 	
 	function uploadImage(){
 		$(".modal-body").html('<iframe src="/user/personImage/uploadImage.jsp?imageType=2" frameborder="0" scrolling="no" style="height:380px;width:100%;"></iframe>');
 		$('#myModal').modal({
 	        keyboard: true
 	    })
 	}
 	
 	function menuClick(menuId){
 		// 如果没有子栏目，就modal弹出页面，打开它
 		// 如果存在子栏目，就打开显示左边栏目的界面
   		
   	
	
 		var html='<ul  class="nav"><li><a ><i class="icon-user icon-white"></i><span style="color: rgb(255, 140, 60);" class="glyphicon glyphicon-file" @click="menuClick()">测试</span></a></li></ul>';
		$("#menupage").show();
		$("#leftFrame").html(html);
		$("#homepage").hide();
		
 	}
 	
 	

 	
 	
 	function alterPwd(){
 		$(".modal-body").html('<iframe src="/user/person/alterPwd.jsp" frameborder="0" scrolling="no" style="height:280px;width:100%;"></iframe>');
 		$('#myModal').modal({
 	        keyboard: true
 	    })
 	}	
 	
 	
 	/**/
 	(function($, window, undefined) {
 	    var $allDropdowns = $();
 	    $.fn.dropdownHover = function(options) {
 	        $allDropdowns = $allDropdowns.add(this.parent());
 	        return this.each(function() {
 	            var $this = $(this).parent(),
 	                defaults = {
 	                    delay: 300,
 	                    instantlyCloseOthers: true
 	                },
 	                data = {
 	                    delay: $(this).data('delay'),
 	                    instantlyCloseOthers: $(this).data('close-others')
 	                },
 	                options = $.extend(true, {}, defaults, options, data),
 	                timeout;

 	            $this.hover(function() {
 	                if(options.instantlyCloseOthers === true)
 	                    $allDropdowns.removeClass('open');

 	                window.clearTimeout(timeout);
 	                $(this).addClass('open');
 	            }, function() {
 	                timeout = window.setTimeout(function() {
 	                    $this.removeClass('open');
 	                }, options.delay);
 	            });
 	        });
 	    };
 	    $('[data-hover="dropdown"]').dropdownHover();
 	})(jQuery, this);
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
 	
    //导航收起与展开
    $('#side-menu li span.fa,.sidebar-menus-icon').click(function () {
    	var menupageIsOpen=$("#menupageIsOpen").val();
    	$("#side-menu > li").each(function(){
    		if(menupageIsOpen==1){
    			var table=(($(this).find("table")));
        	    var menutip=(($(this).find("div")));
        	    $(table).show(500);
        	    $(menutip).hide(500);
        	    $("#menupageIsOpen").val(2);
        	}else{
    			var table=(($(this).find("table")));
        	    var menutip=(($(this).find("div")));
        	    $(table).hide(500);
        	    $(menutip).show(500);
        	    $("#menupageIsOpen").val(1);
        	}
    	  });
        return false;
    })
    
    $('#side-menu li span.menutip').click(function () {
    	alert("打開内容界面！")
    });
    
    
    
    
    
    
    
 	