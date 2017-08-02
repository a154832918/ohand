package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class OfficialLevelDomain extends BaseModel{

	private static final long serialVersionUID = -3243266293493360188L;

	private Integer id;

	private String rDescription;

	private String name;

	private Integer status;

	public OfficialLevelDomain() {
	}

	public Integer getId() {
		return id;
	}
 
	public void setId(Integer id) {
		this.id = id;
	}

	public String getRDescription() {
		return rDescription;
	}

	public void setRDescription(String rDescription) {
		this.rDescription = rDescription;
	}
 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
