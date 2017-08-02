package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FormElementDataDomain extends BaseModel {
	
	private static final long serialVersionUID = -7411179402909187566L;

	private Integer id;

	private Integer flowInstId;

	private Date createDate;

	private Integer flowId;

	private String flowName;

	private Integer flowCode;

	private Integer formId;

	private Integer formCode;

	private String formName;

	private Integer rid;

	private Integer elementId;

	private String elementCode;

	private String elementValue;

	public FormElementDataDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlowInstId() {
		return flowInstId;
	}

	public void setFlowInstId(Integer flowInstId) {
		this.flowInstId = flowInstId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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

	public Integer getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public Integer getElementId() {
		return elementId;
	}

	public void setElementId(Integer elementId) {
		this.elementId = elementId;
	}

	public String getElementCode() {
		return elementCode;
	}

	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	public String getElementValue() {
		return elementValue;
	}

	public void setElementValue(String elementValue) {
		this.elementValue = elementValue;
	}

}
