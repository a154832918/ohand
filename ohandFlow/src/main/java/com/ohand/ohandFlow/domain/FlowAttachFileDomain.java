package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

public class FlowAttachFileDomain extends BaseModel {

	private static final long serialVersionUID = 8803827602859063682L;

	private Integer id;

	private String fileName;

	private String suffix;

	private Integer formId;

	private String filePath;

	private Integer fileVersion;

	private String uploader;

	private Integer uploaderId;

	private Date uploaderDate;

	private Integer instId;

	private Integer formCode;

	private Integer encryptType;

	private String passKey;

	public FlowAttachFileDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Integer getFormId() {
		return formId;
	}

	public void setFormId(Integer formId) {
		this.formId = formId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Integer getFileVersion() {
		return fileVersion;
	}
 
	public void setFileVersion(Integer fileVersion) {
		this.fileVersion = fileVersion;
	}
 
	public String getUploader() {
		return uploader;
	}

	public void setUploader(String uploader) {
		this.uploader = uploader;
	}

	public Integer getUploaderId() {
		return uploaderId;
	}

	public void setUploaderId(Integer uploaderId) {
		this.uploaderId = uploaderId;
	}

	public Date getUploaderDate() {
		return uploaderDate;
	}

	public void setUploaderDate(Date uploaderDate) {
		this.uploaderDate = uploaderDate;
	}

	public Integer getInstId() {
		return instId;
	}

	public void setInstId(Integer instId) {
		this.instId = instId;
	}

	public Integer getFormCode() {
		return formCode;
	}
 
	public void setFormCode(Integer formCode) {
		this.formCode = formCode;
	}

	public Integer getEncryptType() {
		return encryptType;
	}
 
	public void setEncryptType(Integer encryptType) {
		this.encryptType = encryptType;
	}

	public String getPassKey() {
		return passKey;
	}

	public void setPassKey(String passKey) {
		this.passKey = passKey;
	}

}
