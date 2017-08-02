package com.ohand.ohandUser.domain;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.ohand.ohandUser.common.BaseModel;

public class TemplateConfigDomain extends BaseModel{

	private static final long serialVersionUID = 1862714572938332969L;

	private Integer id;

	private String templateUuid;

	private String templatePath;

	private Integer versionNo;
	
	private Integer isLastVersion;

	private Date uploadDate;

	private String suffix;

	private String fileName;

	public TemplateConfigDomain() {
	}
	
	public String getContent() throws IOException{
		//TODO 可以内置緩存裏面redis
//		return OhandFileUtil.readFileWrapper(this.templatePath,false);
		return null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTemplateUuid() {
		return templateUuid;
	}

	public void setTemplateUuid(String templateUuid) {
		this.templateUuid = templateUuid;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getIsLastVersion() {
		return isLastVersion;
	}

	public void setIsLastVersion(Integer isLastVersion) {
		this.isLastVersion = isLastVersion;
	}
	
}
