package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FormObjDomain extends BaseModel {

	private static final long serialVersionUID = -5309975654641319942L;
	
	public static final Integer formType_NORMAL=new Integer(1);
	public static final Integer formType_APP=new Integer(2);
	public static final Integer formType_PORTAL=new Integer(3);
	
	private Integer id;

	private String formName;

	private Integer formCode;

	private Integer creatorId;

	private String creator;

	private Date createDate;

	private Integer formVersion;// 当前版本

	private Integer bindingVersion;// 发布版本

	private Integer lastVersion;// 最新版本

	private Integer isLastVersion;

	private Integer isBindingVersion;

	private String formPath;

	private String formDes;
	
	private Integer formType;//表单类型

	/**
	 * 
	 */
	public FormObjDomain() {
	}

	public Integer getIsBindingVersion() {
		return isBindingVersion;
	}

	public void setIsBindingVersion(Integer isBindingVersion) {
		this.isBindingVersion = isBindingVersion;
	}

	public Integer getLastVersion() {
		return lastVersion;
	}

	public void setLastVersion(Integer lastVersion) {
		this.lastVersion = lastVersion;
	}

	public Integer getBindingVersion() {
		return bindingVersion;
	}

	public void setBindingVersion(Integer bindingVersion) {
		this.bindingVersion = bindingVersion;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the formName
	 */
	public String getFormName() {
		return formName;
	}

	/**
	 * @param formName
	 *            the formName to set
	 */
	public void setFormName(String formName) {
		this.formName = formName;
	}

	/**
	 * @return the formCode
	 */
	public Integer getFormCode() {
		return formCode;
	}

	/**
	 * @param formCode
	 *            the formCode to set
	 */
	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	/**
	 * @return the creatorId
	 */
	public Integer getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            the creatorId to set
	 */
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator
	 *            the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the formVersion
	 */
	public Integer getFormVersion() {
		return formVersion;
	}

	/**
	 * @param formVersion
	 *            the formVersion to set
	 */
	public void setFormVersion(Integer formVersion) {
		this.formVersion = formVersion;
	}

	/**
	 * @return the isLastVersion
	 */
	public Integer getIsLastVersion() {
		return isLastVersion;
	}

	/**
	 * @param isLastVersion
	 *            the isLastVersion to set
	 */
	public void setIsLastVersion(Integer isLastVersion) {
		this.isLastVersion = isLastVersion;
	}

	/**
	 * @return the formPath
	 */
	public String getFormPath() {
		return formPath;
	}

	/**
	 * @param formPath
	 *            the formPath to set
	 */
	public void setFormPath(String formPath) {
		this.formPath = formPath;
	}

	/**
	 * @return the formDes
	 */
	public String getFormDes() {
		return formDes;
	}

	/**
	 * @param formDes
	 *            the formDes to set
	 */
	public void setFormDes(String formDes) {
		this.formDes = formDes;
	}

	public Integer getFormType() {
		return formType;
	}

	public void setFormType(Integer formType) {
		this.formType = formType;
	}

}
