package com.ohand.ohandUser.domain;

import java.io.Serializable;
import java.util.Date;

import com.ohand.ohandUser.common.BaseModel;

/**
 * 个人签章图片及其个人头像
 *
 */
public class PersonImageDomain extends BaseModel{
	
	private static final long serialVersionUID = -2044607013333758328L;

	/**签章*/
	public static final Integer imageType_signature=new Integer(1);
	
	/**头像*/
	public static final Integer imageType_head=new Integer(2);

	private String id;

	private String fileName;

	private String suffix;

	private String filePath;

	private Integer fileVersion;
	
	// 关联的uuid（流水编码）
	private String relatedAccount;
	
	private Integer relatedCommonId;
	
	private String relatedPersonName;

	private String uploader;

	private Integer uploaderId;

	private Date uploaderDate;

	private Integer encryptType;

	private String passKey;
	
	private Integer imageType;

	public PersonImageDomain() {
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

	public String getRelatedAccount() {
		return relatedAccount;
	}

	public void setRelatedAccount(String relatedAccount) {
		this.relatedAccount = relatedAccount;
	}

	public String getRelatedPersonName() {
		return relatedPersonName;
	}

	public void setRelatedPersonName(String relatedPersonName) {
		this.relatedPersonName = relatedPersonName;
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

	public Integer getImageType() {
		return imageType;
	}

	public void setImageType(Integer imageType) {
		this.imageType = imageType;
	}

	public Integer getRelatedCommonId() {
		return relatedCommonId;
	}

	public void setRelatedCommonId(Integer relatedCommonId) {
		this.relatedCommonId = relatedCommonId;
	}
	
}
