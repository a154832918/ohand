package com.ohand.ohandUser.controller;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ohand.ohandUser.common.CookieManager;
import com.ohand.ohandUser.common.JsonUtil;
import com.ohand.ohandUser.common.MD5Util;
import com.ohand.ohandUser.common.PFConstant;
import com.ohand.ohandUser.domain.UserDomain;
import com.ohand.ohandUser.domain.UserInfo;
import com.ohand.ohandUser.framework.controller.BaseController;
import com.ohand.ohandUser.service.CommonService;
import com.ohand.ohandUser.service.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginAction extends BaseController {
	
	private static final long serialVersionUID = 7205800928189447653L;

	public static final String EXPECTED_USER_KEY = "username";

	public static final String EXPECTED_PWD_KEY = "password";
	
	private static char[] base64EncodeChars = new char[] { 'A', 'B', 'C', 'D',  
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',  
        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',  
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',  
        'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',  
        '4', '5', '6', '7', '8', '9', '+', '/', };  
  
private static byte[] base64DecodeChars = new byte[] { -1, -1, -1, -1, -1,  
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
        -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  
        -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59,  
        60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,  
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1,  
        -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,  
        38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1,  
        -1, -1 };  

		/** base64解密  */  
		public static byte[] decode(String str) {  
		    byte[] data = str.getBytes();  
		    int len = data.length;  
		    ByteArrayOutputStream buf = new ByteArrayOutputStream(len);  
		    int i = 0;  
		    int b1, b2, b3, b4;  
		  
		    while (i < len) {  
		        do {  
		            b1 = base64DecodeChars[data[i++]];  
		        } while (i < len && b1 == -1);  
		        if (b1 == -1) {  
		            break;  
		        }  
		  
		        do {  
		            b2 = base64DecodeChars[data[i++]];  
		        } while (i < len && b2 == -1);  
		        if (b2 == -1) {  
		            break;  
		        }  
		        buf.write((int) ((b1 << 2) | ((b2 & 0x30) >>> 4)));  
		  
		        do {  
		            b3 = data[i++];  
		            if (b3 == 61) {  
		                return buf.toByteArray();  
		            }  
		            b3 = base64DecodeChars[b3];  
		        } while (i < len && b3 == -1);  
		        if (b3 == -1) {  
		            break;  
		        }  
		        buf.write((int) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));  
		  
		        do {  
		            b4 = data[i++];  
		            if (b4 == 61) {  
		                return buf.toByteArray();  
		            }  
		            b4 = base64DecodeChars[b4];  
		        } while (i < len && b4 == -1);  
		        if (b4 == -1) {  
		            break;  
		        }  
		        buf.write((int) (((b3 & 0x03) << 6) | b4));  
		    }  
		    return buf.toByteArray();  
		}
	
		
	@Resource
	private transient UserService userService ;
	
	@Resource
	private transient CommonService commonService ;
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public Object getModel() {
		return null;
	}

	@RequestMapping(value="/website/loginAction!login.action",method=RequestMethod.GET)
	public void  login(Model model) throws Exception {
		LoginInfo loginInfo=new LoginInfo();
		String account=request.getParameter("account");
		account=new String(decode(account));
		String pwd=request.getParameter("password");
		pwd=new String(decode(pwd));
		UserDomain user=null;
		Map loginMap=new HashMap();
			loginMap.put("account", account);
		List userList=userService.getObjectVOList(loginMap);
		if(userList==null || userList.size()==0){
			loginInfo.setMsg("用户不存在...");
			loginInfo.setSuccess(false);
		}else{
			user=(UserDomain) userList.get(0);
			boolean isMatch=MD5Util.checkPassword(pwd,user.getPassword());
			if(isMatch==false){
				loginInfo.setMsg("密码错误...");
			}
			Integer commonId=commonService.findCommonIdByPersonId(user.getPersonId());
			UserInfo userInfo=new UserInfo();
					 userInfo.setCommonId(commonId);
					 userInfo.setAccount(account);
					 userInfo.setLogintime(new Date());
			if(loginInfo.getMsg()!=null){
				loginInfo.setSuccess(false);
			}else{
				loginInfo.setSuccess(true);
				loginInfo.setUserInfo(userInfo);
				BeanUtils.copyProperties(user, userInfo);
				request.getSession().setAttribute(PFConstant.SESSION_USER_KEY, userInfo);
				CookieManager.loginCookie(request , response,userInfo);
			}
		}
		initJsonResponse(response, JsonUtil.obj2Json(loginInfo),RESPONCE_TYPE_JSON);
		
	}
	
	/**
	 * Function  : 注销操作
	 */
	public String doLogout(HttpServletRequest request,Model model){
		// @TODO
		return null;
	}
	
	private void setRememberPasswordCookie(HttpServletResponse response,
			String name, String pwd, boolean remberPasswordFlag) {
		
			int maxAge = remberPasswordFlag ? 200000000 : -1;

			Cookie usernameCookie;
			usernameCookie = new Cookie(EXPECTED_USER_KEY, name);
			usernameCookie.setPath("/");
			//usernameCookie.setMaxAge(Integer.MAX_VALUE);
			usernameCookie.setMaxAge(200000000);

			Cookie pwdCookie;
			pwdCookie = new Cookie(EXPECTED_PWD_KEY, pwd);
			pwdCookie.setPath("/");
			//pwdCookie.setMaxAge(maxAge);
			pwdCookie.setMaxAge(200000000);
			response.addCookie(usernameCookie);
			response.addCookie(pwdCookie);
		}
	
	/**
	 * 登录返回信息
	 */
	public class LoginInfo {
		
		private Boolean success;
		private String msg;
		private UserInfo userInfo;
		
		public Boolean getSuccess() {
			return success;
		}
		public void setSuccess(Boolean success) {
			this.success = success;
		}
		public String getMsg() {
			return msg;
		}
		public void setMsg(String msg) {
			this.msg = msg;
		}
		public UserInfo getUserInfo() {
			return userInfo;
		}
		public void setUserInfo(UserInfo userInfo) {
			this.userInfo = userInfo;
		}
	}
	
}