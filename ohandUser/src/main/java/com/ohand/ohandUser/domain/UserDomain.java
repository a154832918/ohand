package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class UserDomain extends BaseModel{
	
	private static final long serialVersionUID = -8921505542171609252L;
	public static Integer USER_STATUS_NORMAL=new Integer(1);// 正常
	public static Integer USER_STATUS_LOCK=new Integer(2);// 锁定
	public static Integer USER_STATUS_CANCEL=new Integer(3);// 注销
	
	public static Integer inOutFlag_In=new Integer(1);//内部用户
	public static Integer inOutFlag_Out=new Integer(2);//外部用户
	
	public static Integer loginType_Password=new Integer(1);//用户名、密码
	public static Integer loginType_OAuth2=new Integer(2);//OAuth2
	public static Integer loginType_Cert=new Integer(3);//证书

	private Integer id;
	
	private String userName;
	
	private Integer personId;

	private String account;

	private String password;

	private Integer loginType;

	private String certType;

	private Integer userStatus;
	
	private Integer inOutFlag;
	//TODO 用户内码数据库字段未设置
	private String identityNum;

	public UserDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getLoginType() {
		return loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public Integer getUserStatus() {
		return userStatus;
	}
 
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getInOutFlag() {
		return inOutFlag;
	}
 
	public void setInOutFlag(Integer inOutFlag) {
		this.inOutFlag = inOutFlag;
	}

	public String getIdentityNum() {
		return identityNum;
	}

	public void setIdentityNum(String identityNum) {
		this.identityNum = identityNum;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
