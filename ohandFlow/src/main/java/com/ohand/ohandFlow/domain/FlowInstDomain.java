package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FlowInstDomain extends BaseModel {

	private static final long serialVersionUID = -3761487290184558407L;

	private Integer id;

	private String title;
	
	private Integer rId;

	private Integer flowId;

	private Integer flowCode;

	private String flowName;

	private Integer formId;

	private Integer formCode;

	private String formName;

	private Date createDate;

	private String creator;

	private Integer creatorId;

	private Integer status;

	private String guid;

	public FlowInstDomain() {
	}

	public Integer getrId() {
		return rId;
	}

	public void setrId(Integer rId) {
		this.rId = rId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreator() {
		return creator;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public Integer getFlowCode() {
		return flowCode;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public Integer getFormCode() {
		return formCode;
	}

	public Integer getFormId() {
		return formId;
	}

	public String getFormName() {
		return formName;
	}

	public String getGuid() {
		return guid;
	}

	public Integer getId() {
		return id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
