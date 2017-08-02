package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class BizConfigDomain extends BaseModel {

	private static final long serialVersionUID = -6105545142895308847L;

	private Integer id;

	private String tableName;

	private Integer flowId;

	private Integer flowCode;

	private Integer isLast;

	private Integer publishVersion;

	public BizConfigDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
 
	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public Integer getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getIsLast() {
		return isLast;
	}
 
	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}

	public Integer getPublishVersion() {
		return publishVersion;
	}

	public void setPublishVersion(Integer publishVersion) {
		this.publishVersion = publishVersion;
	}
	
}
