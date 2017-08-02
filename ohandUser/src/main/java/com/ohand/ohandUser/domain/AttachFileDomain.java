package com.ohand.ohandUser.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.ohand.ohandUser.common.BaseModel;

public class AttachFileDomain  extends BaseModel{

	private static final long serialVersionUID = -7044433700651533355L;

	@Id
	private String id;

	private String fileName;

	private String suffix;

	private String fileUuid;

	private String filePath;

	private Integer fileVersion;

	private String uploader;

	private Integer uploaderId;

	private Date uploaderDate;

	private Integer encryptType;

	private String passKey;

	private String beyondModule;

	private String relatedId;

	private Integer fileSize;
	
	/**上传批次*/
	private String uploadNo;
	
	private Date createDate;

	public AttachFileDomain() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getFileUuid() {
		return fileUuid;
	}

	public void setFileUuid(String fileUuid) {
		this.fileUuid = fileUuid;
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

	public String getBeyondModule() {
		return beyondModule;
	}

	public void setBeyondModule(String beyondModule) {
		this.beyondModule = beyondModule;
	}

	public String getRelatedId() {
		return relatedId;
	}
 
	public void setRelatedId(String relatedId) {
		this.relatedId = relatedId;
	}

	public Integer getFileSize() {
		return fileSize;
	}
 
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public String getUploadNo() {
		return uploadNo;
	}

	public void setUploadNo(String uploadNo) {
		this.uploadNo = uploadNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
