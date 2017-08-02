package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FormPluginObjDomain extends BaseModel {
	
	private static final long serialVersionUID = 3434335316272430825L;

	private Integer id;

	private String pluginName;

	private Integer pluginCode;

	private Integer creatorId;

	private String creator;

	private Date createDate;

	private Integer pluginVersion;

	private Integer isLastVersion;

	private String pluginPath;

	private String pluginDes;

	private Integer formCode;

	public FormPluginObjDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
 
	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}

	public Integer getPluginCode() {
		return pluginCode;
	}

	public void setPluginCode(Integer pluginCode) {
		this.pluginCode = pluginCode;
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

	public Integer getPluginVersion() {
		return pluginVersion;
	}

	public void setPluginVersion(Integer pluginVersion) {
		this.pluginVersion = pluginVersion;
	}

	public Integer getIsLastVersion() {
		return isLastVersion;
	}

	public void setIsLastVersion(Integer isLastVersion) {
		this.isLastVersion = isLastVersion;
	}

	public String getPluginPath() {
		return pluginPath;
	}

	public void setPluginPath(String pluginPath) {
		this.pluginPath = pluginPath;
	}

	public String getPluginDes() {
		return pluginDes;
	}

	public void setPluginDes(String pluginDes) {
		this.pluginDes = pluginDes;
	}

	public Integer getFormCode() {
		return formCode;
	}

	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}
	
}
