package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FormElementScriptDomain extends BaseModel {

	private static final long serialVersionUID = 4698053321132619133L;

	public static final Integer IS_LAST_YES=1;
	public static final Integer IS_LAST_NO=2;
	
	private String id;
	
	private String elementUuid;

	private String scriptContent;
	
	private Date createTime;
	
	private Integer isLast;
	
	private Integer formId;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getElementUuid() {
		return elementUuid;
	}

	public void setElementUuid(String elementUuid) {
		this.elementUuid = elementUuid;
	}

	public String getScriptContent() {
		return scriptContent;
	}

	public void setScriptContent(String scriptContent) {
		this.scriptContent = scriptContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsLast() {
		return isLast;
	}

	public void setIsLast(Integer isLast) {
		this.isLast = isLast;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}
	
}
