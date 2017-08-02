/*add flash*/

ExFlash = {url:"images/logo.swf",width:"153",height:"52",target:"logo"};
ExFlash.getHTML = function(){
	var html = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0" width="' + ExFlash.width + '" height="' + ExFlash.height + '" id="flash" align="middle">' +
'	<param name="allowScriptAccess" value="sameDomain" />' +
'	<param name="movie" value="' + ExFlash.url + '" />' +
'	<param name="menu" value="false" />' +
'	<param name="quality" value="high" />' +
'	<param name="scale" value="noscale" />' +
'	<param name="wmode" value="transparent" />' +
'	<embed src="' + ExFlash.url + '" menu="false" quality="high" scale="noscale" wmode="transparent" width="' + ExFlash.width + '" height="' + ExFlash.height + '" name="flash" align="middle" allowScriptAccess="sameDomain" type="application/x-shockwave-flash" pluginspage="http://www.macromedia.com/go/getflashplayer" />' +
'</object>';
	return html;
}

function addFlash() {
	//Ìí¼Ólogo flash
	var obj = document.getElementById(ExFlash.target)
	var u = obj.currentStyle.backgroundImage;
	if(u.search(".swf") != -1){
		u = u.replace(/"|'/g,"");
		u = u.replace(/^url\(/i,"");
		u = u.replace(/\)$/,"");
		ExFlash.url = u;
		var w = obj.currentStyle.width,h = obj.currentStyle.height;
		if(w.search("%") != -1 || w != w.replace("px",""))ExFlash.width = w;
		if(h.search("%") != -1 || h != h.replace("px",""))ExFlash.height = h;
		obj.innerHTML = ExFlash.getHTML();
	}
	/*Ìæ»»Õû¸öbanner
	var obj = document.createElement("div");
	var banner = document.getElementById("banner");
	banner.style.display = "none";
	document.body.insertBefore(obj,banner);
	obj.innerHTML = ExFlash.getHTML();
	*/
};

window.attachEvent("onload",addFlash);