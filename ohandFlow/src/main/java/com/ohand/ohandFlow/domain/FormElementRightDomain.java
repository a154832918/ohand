package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class FormElementRightDomain extends BaseModel {

	private static final long serialVersionUID = -3466410380902322213L;

	private static final Integer rightType_no=new Integer(1);//不可见
	private static final Integer rightType_nowrite=new Integer(2);//可读不可写
	private static final Integer rightType_write=new Integer(3);//可读可写
	
	
	private Integer id;

	private Integer formRFlowId;

	private String formName;

	private Integer activityId;

	private Integer elementId;

	private String elementCode;

	private String elementName;

	private Integer isEnable;

	private Integer rightType;

	public Integer getFormRFlowId() {
		return formRFlowId;
	}

	public void setFormRFlowId(Integer formRFlowId) {
		this.formRFlowId = formRFlowId;
	}

	public FormElementRightDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
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

	public String getElementName() {
		return elementName;
	}
 
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public Integer getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

	public Integer getRightType() {
		return rightType;
	}

	public void setRightType(Integer rightType) {
		this.rightType = rightType;
	}

}
