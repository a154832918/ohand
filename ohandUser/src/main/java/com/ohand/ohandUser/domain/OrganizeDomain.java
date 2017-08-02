package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class OrganizeDomain extends BaseModel{

	private static final long serialVersionUID = 8405120120290843776L;

	private Integer id;

	private Integer recordId;

	private String name;

	private Integer sourceType;

	private Integer parentId;
	
	private String parentNodes;
	
	private Integer topId;

	private String description;

	private Integer priority;

	private String sysbol;

	public OrganizeDomain() {
	}
 		
	public String getParentNodes() {
		return parentNodes;
	}
	
	public void setParentNodes(String parentNodes) {
		this.parentNodes = parentNodes;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
 
	public Integer getPriority() {
		return priority;
	}
 
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getSysbol() {
		return sysbol;
	}

	public void setSysbol(String sysbol) {
		this.sysbol = sysbol;
	}

}
