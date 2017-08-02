package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FlowObjDomain extends BaseModel {
	private static final long serialVersionUID = 7849436501132661794L;
	/**新建状态*/
	public static final Integer  flowStatus_New=new Integer(1);
	/**编辑状态*/
	public static final Integer  flowStatus_Edit=new Integer(2);
	
	private Integer id;

	private String flowName;

	private Integer flowCode;

	private Integer creatorId;

	private String creator;

	private Date createDate;

	private Integer flowVersion;

	private Integer bindingVersion;

	private Integer lastVersion;

	private Integer isLastVersion;

	private Integer isBindingVersion;

	private String flowPath;

	private String flowDes;

	private Integer flowCategoryId;
	
	private String createTableSql;
	
	private String uuid;
	
	private Integer flowStatus;
	
	public String getCreateTableSql() {
		return createTableSql;
	}

	public void setCreateTableSql(String createTableSql) {
		this.createTableSql = createTableSql;
	}

	public Integer getBindingVersion() {
		return bindingVersion;
	}

	public void setBindingVersion(Integer bindingVersion) {
		this.bindingVersion = bindingVersion;
	}

	public Integer getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(Integer lastVersion) {
		this.lastVersion = lastVersion;
	}

	public Integer getIsBindingVersion() {
		return isBindingVersion;
	}

	public void setIsBindingVersion(Integer isBindingVersion) {
		this.isBindingVersion = isBindingVersion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Integer getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getFlowVersion() {
		return flowVersion;
	}

	public void setFlowVersion(Integer flowVersion) {
		this.flowVersion = flowVersion;
	}

	public Integer getIsLastVersion() {
		return isLastVersion;
	}

	public void setIsLastVersion(Integer isLastVersion) {
		this.isLastVersion = isLastVersion;
	}

	public String getFlowPath() {
		return flowPath;
	}

	public void setFlowPath(String flowPath) {
		this.flowPath = flowPath;
	}

	public String getFlowDes() {
		return flowDes;
	}

	public void setFlowDes(String flowDes) {
		this.flowDes = flowDes;
	}

	public Integer getFlowCategoryId() {
		return flowCategoryId;
	}

	public void setFlowCategoryId(Integer flowCategoryId) {
		this.flowCategoryId = flowCategoryId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(Integer flowStatus) {
		this.flowStatus = flowStatus;
	}
	
}
