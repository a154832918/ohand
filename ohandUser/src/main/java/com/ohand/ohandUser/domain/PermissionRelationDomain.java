package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class PermissionRelationDomain extends BaseModel{

	private static final long serialVersionUID = -5306503463351613317L;

	private Integer id;

	private Integer permissionItemId;

	private String permissionItemName;

	private Integer recordId;

	private String recordName;

	private Integer userId;

	private String userName;

	private Integer cType;

	public PermissionRelationDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPermissionItemId() {
		return permissionItemId;
	}

	public void setPermissionItemId(Integer permissionItemId) {
		this.permissionItemId = permissionItemId;
	}

	public String getPermissionItemName() {
		return permissionItemName;
	}

	public void setPermissionItemName(String permissionItemName) {
		this.permissionItemName = permissionItemName;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getcType() {
		return cType;
	}

	public void setcType(Integer cType) {
		this.cType = cType;
	}
	
}
