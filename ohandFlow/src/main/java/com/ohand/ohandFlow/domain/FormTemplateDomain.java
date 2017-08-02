package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FormTemplateDomain extends BaseModel {

	private static final long serialVersionUID = 5513980146158081776L;

	private Integer id;

	private String templateName;

	private String templateDesc;

	private Integer creatorId;

	private String creator;

	private Date createDate;

	private String templateContent;

	public FormTemplateDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateDesc() {
		return templateDesc;
	}

	public void setTemplateDesc(String templateDesc) {
		this.templateDesc = templateDesc;
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

	public String getTemplateContent() {
		return templateContent==null?"":templateContent;
	}

	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}

}
