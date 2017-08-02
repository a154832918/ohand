$(document).ready(function() {
	getCookie();
	onfocus();
	$(".on_off_checkbox").iphoneStyle();
	$('.tip a ').tipsy({
		gravity : 'sw'
	});
	$('#login').show().animate({
		opacity : 1
	}, 2000);
	$('.logo').show().animate({
		opacity : 1,
		top : '32%'
	}, 800, function() {
		$('.logo').show().delay(1200).animate({
			opacity : 1,
			top : '1%'
		}, 300, function() {
			$('.formLogin').animate({
				opacity : 1,
				left : '0'
			}, 300);
			$('.userbox').animate({
				opacity : 0
			}, 200).hide();
		});
	});

});
$('.userload').click(function(e) {
	$('.formLogin').animate({
		opacity : 1,
		left : '0'
	}, 300);
	$('.userbox').animate({
		opacity : 0
	}, 200, function() {
		$('.userbox').hide();
	});
});
//update-begin--Author:zhangguoming  Date:20140226 for：添加验证码
$('#randCodeImage').click(function(){
    reloadRandCodeImage();
});
/**
 * 刷新验证码
 */
function reloadRandCodeImage() {
    var date = new Date();
    var img = document.getElementById("randCodeImage");
    img.src='randCodeImage?a=' + date.getTime();
}
//update-end--Author:zhangguoming  Date:20140226 for：添加验证码
// 重置
$('#forgetpass').click(function(e) {
	$(":input").each(function() {
	$('#'+this.name).val("");
	});
});
// 点击登录
$('#but_login').click(function(e) {
	submit();
});
//回车登录
$(document).keydown(function(e){
	if(e.keyCode == 13) {
		submit();
	}
});
//表单提交
function submit()
{
	var submit = true;
	$("input[nullmsg]").each(function() {
		if ($("#" + this.name).val() == "") {
			showError($("#" + this.name).attr("nullmsg"), 500);
			jrumble();
			setTimeout('hideTop()', 1000);
			submit = false;
			return false;
		}
	});
	if (submit) {
		hideTop();
		loading('核实中..', 1);
		setTimeout("unloading()", 1000);
		setTimeout("Login()", 1000);
	}

}
//登录处理函数
function Login() {
	setCookie();
	var actionurl=$('form').attr('action');//提交路径
	var checkurl=$('form').attr('check');//验证路径
	 var formData = new Object();
	 var vertCode=$("#randCode").val();
	var data=$(":input").each(function() {
		var tp=$("#"+this.name ).val();
		if(tp){
			 formData[this.name] =encode64(tp);
		}
	});
	
	var authImage="rrrrr";
	$.ajax({
				url : '/website/login/checkAuthImage.jsp',
				type : "GET",
				async:false,
				success : function(data) {
					authImage=data.value;
				},
				error : function(request, textStatus, errorThrown) {
				}
			});
	
	if(!vertCode || !((vertCode).toLowerCase()==(authImage).toLowerCase())){
		alert("输入验证码不正确！")
		// return false;
	}
	
	$.ajax({
		async : false,
		cache : false,
		type : 'GET',
		url : checkurl,// 请求的action路径
		data : formData,
		error : function() {// 请求失败处理函数
		},
		success : function(data) {
			var d = data;
			if (d.success) {
				 loginsuccess();
				 window.location.href=actionurl+"?s="+Math.floor(Math.random()*(10000000));
				//setTimeout("window.location.href='"+actionurl+"'", 1000);
			} else {
				if(d.msg == "a"){
					$.dialog.confirm("数据库无数据,是否初始化数据?", function(){
						window.location = "init.jsp";
					}, function(){
						//
					});
				} else
					showError(d.msg);
			}
		}
	});
}
//设置cookie
function setCookie()
{
	if ($('#on_off').val() == '1') {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name, $("#"+this.name).val(), "/",24);
			$.cookie("COOKIE_NAME","true", "/",24);
		});
	} else {
		$("input[iscookie='true']").each(function() {
			$.cookie(this.name,null);
			$.cookie("COOKIE_NAME",null);
		});
	}
}
//读取cookie
function getCookie()
{
	var COOKIE_NAME=$.cookie("COOKIE_NAME");
	if (COOKIE_NAME !=null) {
		$("input[iscookie='true']").each(function() {
			$($("#"+this.name).val( $.cookie(this.name)));
		});
		$("#on_off").attr("checked", true);
		$("#on_off").val("1");
	} 
	else
	{
		$("#on_off").attr("checked", false);
		$("#on_off").val("0");
	}
}
//点击消息关闭提示
$('#alertMessage').click(function() {
	hideTop();
});
//显示错误提示
function showError(str) {
	$('#alertMessage').addClass('error').html(str).stop(true, true).show().animate({
		opacity : 1,
		right : '0'
	}, 500);

}
//验证通过加载动画
function loginsuccess()
{
	$("#login").animate({
		opacity : 1,
		top : '49%'
	}, 200, function() {
		$('.userbox').show().animate({
			opacity : 1
		}, 500);
		$("#login").animate({
			opacity : 0,
			top : '60%'
		}, 500, function() {
			$(this).fadeOut(200, function() {
				$(".text_success").slideDown();
				$("#successLogin").animate({
					opacity : 1,
					height : "200px"
				}, 1000);
			});
		});
	});
}
function showSuccess(str) {
	$('#alertMessage').removeClass('error').html(str).stop(true, true).show().animate({
		opacity : 1,
		right : '0'
	}, 500);
}

function onfocus() {
	if ($(window).width() > 480) {
		$('.tip input').tipsy({
			trigger : 'focus',
			gravity : 'w',
			live : true
		});
	} else {
		$('.tip input').tipsy("hide");
	}
}

function hideTop() {
	$('#alertMessage').animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});
}
//加载信息
function loading(name, overlay) {
	$('body').append('<div id="overlay"></div><div id="preloader">' + name + '..</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}

function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}
// 表单晃动
function jrumble() {
	$('.inner').jrumble({
		x : 4,
		y : 0,
		rotation : 0
	});
	$('.inner').trigger('startRumble');
	setTimeout('$(".inner").trigger("stopRumble")', 500);
}



// base64加密开始  
 var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" + "ghijklmnopqrstuv"  
         + "wxyz0123456789+/" + "=";  
 function encode64(input) {  
     var output = "";  
     var chr1, chr2, chr3 = "";  
     var enc1, enc2, enc3, enc4 = "";  
     var i = 0;  
     do {  
         chr1 = input.charCodeAt(i++);  
         chr2 = input.charCodeAt(i++);  
         chr3 = input.charCodeAt(i++);  
         enc1 = chr1 >> 2;  
         enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);  
         enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);  
         enc4 = chr3 & 63;  
         if (isNaN(chr2)) {  
             enc3 = enc4 = 64;  
         } else if (isNaN(chr3)) {  
             enc4 = 64;  
         }  
         output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2)  
                 + keyStr.charAt(enc3) + keyStr.charAt(enc4);  
         chr1 = chr2 = chr3 = "";  
         enc1 = enc2 = enc3 = enc4 = "";  
     } while (i < input.length);  

     return output;  
 }  
 // base64加密结束 
