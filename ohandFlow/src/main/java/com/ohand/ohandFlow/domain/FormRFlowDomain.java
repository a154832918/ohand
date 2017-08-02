package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class FormRFlowDomain extends BaseModel {
	
	private static final long serialVersionUID = -3270327799931306274L;

	private Integer id;

	private Integer formId;

	private String formName;

	private Integer formCode;

	private Integer flowId;

	private String flowName;

	private Integer flowCode;

	private Integer publishVersion;

	private Integer isPublishVersion;
	
	private Integer flowCategoryId;
	
	private String flowCategoryName;

	public FormRFlowDomain() {
	}
	
	public Integer getIsPublishVersion() {
		return isPublishVersion;
	}

	public void setIsPublishVersion(Integer isPublishVersion) {
		this.isPublishVersion = isPublishVersion;
	}

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public Integer getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Integer getPublishVersion() {
		return publishVersion;
	}

	public void setPublishVersion(Integer publishVersion) {
		this.publishVersion = publishVersion;
	}

	public Integer getFlowCategoryId() {
		return flowCategoryId;
	}

	public void setFlowCategoryId(Integer flowCategoryId) {
		this.flowCategoryId = flowCategoryId;
	}

	public String getFlowCategoryName() {
		return flowCategoryName;
	}

	public void setFlowCategoryName(String flowCategoryName) {
		this.flowCategoryName = flowCategoryName;
	}

}
