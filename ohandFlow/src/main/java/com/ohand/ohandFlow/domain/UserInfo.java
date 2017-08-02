package com.ohand.ohandFlow.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.BeanUtils;

public class UserInfo extends UserDomain implements Serializable {
	
	private static final long serialVersionUID = -7843366811655285655L;

	public UserInfo() {
		super();
	}

	public UserInfo(UserDomain domain) {
		super();
		BeanUtils.copyProperties(domain, this);
	}
	
	protected Integer commonId;
	
	protected Integer orgId;
	
	protected String orgName;
	
	protected Date logintime;
	
	protected String ip;
	
	protected String machine;
	
	protected UserDomain actualUser ;
	
	protected String unitId;
	
	protected String unitName;
	
	public Integer getCommonId() {
		return commonId;
	}

	public void setCommonId(Integer commonId) {
		this.commonId = commonId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	public UserDomain getActualUser() {
		return actualUser;
	}

	public void setActualUser(UserDomain actualUser) {
		this.actualUser = actualUser;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
