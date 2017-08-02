package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class FormElementDomain extends BaseModel {

	private static final long serialVersionUID = -1014871190917023255L;

	private Integer id;

	private Integer formId;

	private String formName;

	private String elementCode;
	
	private Integer elementType;

	private String elementName;

	private Integer isEnable;
	
	public Integer getElementType() {
		return elementType;
	}

	public void setElementType(Integer elementType) {
		this.elementType = elementType;
	}

	/**
	 * 
	 */
	public FormElementDomain() {
	}

	/** 
	 * @return the id
	 */ 
	public Integer getId() {
		return id;
	}

	/** 
	 * @param id the id to set 
	 */ 
	public void setId(Integer id) {
		this.id = id;
	}

	/** 
	 * @return the formId
	 */ 
	public Integer getFormId() {
		return formId;
	}

	/** 
	 * @param formId the formId to set 
	 */ 
	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	/** 
	 * @return the formName
	 */ 
	public String getFormName() {
		return formName;
	}

	/** 
	 * @param formName the formName to set 
	 */ 
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/** 
	 * @return the elementCode
	 */ 
	public String getElementCode() {
		return elementCode;
	}

	/** 
	 * @param elementCode the elementCode to set 
	 */ 
	public void setElementCode(String elementCode) {
		this.elementCode = elementCode;
	}

	/** 
	 * @return the elementName
	 */ 
	public String getElementName() {
		return elementName;
	}

	/** 
	 * @param elementName the elementName to set 
	 */ 
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	/** 
	 * @return the isEnable
	 */ 
	public Integer getIsEnable() {
		return isEnable;
	}

	/** 
	 * @param isEnable the isEnable to set 
	 */ 
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}

}
