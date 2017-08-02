package com.ohand.ohandUser.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.ohand.ohandUser.domain.UserInfo;

/**
 * @description:Cookie processor(cookie get , set ,delete)
 * @author:liming
 * @mail:liming@sogou-inc.com
 * @date:2014-5-6 上午10:08:11
 * @version:v1.0
 */
public class CookieManager {

	/**
	 * @description:set cookie 
	 * @date:2014-5-6 上午10:08:40
	 * @version:v1.0
	 * @param response
	 * @param name:cookie name
	 * @param value:cookie value
	 * @param maxAge:cookie max age
	 */
	public static void addCookie(HttpServletResponse response,String name,String value,int maxAge){
		Cookie cookie = new Cookie(name,value);
		cookie.setPath("/");
		if(maxAge>0)  cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * @description:delete cookie
	 * @date:2014-5-6 上午10:10:38
	 * @version:v1.0
	 * @param response
	 * @param name:cookie name
	 */
	public static void delCookie(HttpServletResponse response,String name){
		Cookie cookie = new Cookie(name,null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	/**
	 * @description:get cookie by cookie name
	 * @date:2014-5-6 上午10:10:53
	 * @version:v1.0
	 * @param request
	 * @param name:cookie name
	 * @return:cookie
	 */
	public static Cookie getCookieByName(HttpServletRequest request,String name){
		Map<String,Cookie> cookieMap = ReadCookieMap(request);
		if(cookieMap.containsKey(name)){
		  Cookie cookie = (Cookie)cookieMap.get(name);
		  return cookie;
		}else{
		  return null;
		} 
	}



	/**
	 * @description:put all cookie from request into map
	 * @date:2014-5-6 上午10:11:20
	 * @version:v1.0
	 * @param request
	 * @return:cookie map
	 */
	private static Map<String,Cookie> ReadCookieMap(HttpServletRequest request){ 
		Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
		Cookie[] cookies = request.getCookies();
		if(null!=cookies){
		  for(Cookie cookie : cookies){
		   cookieMap.put(cookie.getName(), cookie);
		  }
		}
		return cookieMap;
	}

	/** 登陆信息记录cookie*/
	public static void loginCookie( HttpServletRequest  request, HttpServletResponse response,UserInfo userInfo){
		
		//从cookie中获取sessionId，如果此次请求没有sessionId，重写为这次请求设置一个sessionId
		Cookie sidCookie = CookieManager.getCookieByName(request, PFConstant.COOKIE_USER_KEY);
		String sid="";
		if(sidCookie!=null){
			sidCookie.getValue();
		}
	    if(StringUtils.isEmpty(sid) || sid.length() != 36){
	        sid = UUIDUtil.generateUUID();
	        CookieManager.addCookie(response, PFConstant.COOKIE_USER_KEY, sid, 60 * 60);
	        //TODO /**进行加密处理*/
	        CookieManager.addCookie(response, PFConstant.COOKIE_USER_NAME, userInfo.getAccount(), 60 * 60); 
	    }
	    
	}
	
}
