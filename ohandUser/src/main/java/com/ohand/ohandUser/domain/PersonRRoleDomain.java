package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class PersonRRoleDomain extends BaseModel{

	private static final long serialVersionUID = 1209702774700702680L;

	private Integer id;

	private Integer roleId;

	private String roleName;

	private String personName;

	private Integer personId;

	public PersonRRoleDomain() {
	}

	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPersonId() {
		return personId;
	}
 
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

}
