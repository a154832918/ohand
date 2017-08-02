package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class ResourceDomain extends BaseModel{
	
	private static final long serialVersionUID = 3925665040690431826L;

	private Integer id;

	private String resCode;

	private String name;

	private String realPath;

	private String redirectPath;

	private Integer enableFlag;

	private Integer creatorId;

	private String creatorName;

	private Integer resourceType;

	private String description;

	private Integer parentId;

	private Integer treeLevel;

	private Integer priority;

	private Integer topId;

	private String parentNodes;

	private Integer isNewPage;
	
	private Integer isModal;
	
	private String sysCode;
	
	private Integer isShow2Client;
	
	private String menuLogo;
	
	private Integer isOpenPerm;//是否所有用户访问权限
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getRedirectPath() {
		return redirectPath;
	}

	public void setRedirectPath(String redirectPath) {
		this.redirectPath = redirectPath;
	}

	public Integer getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getTreeLevel() {
		return treeLevel;
	}

	public void setTreeLevel(Integer treeLevel) {
		this.treeLevel = treeLevel;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

	public String getParentNodes() {
		return parentNodes;
	}

	public void setParentNodes(String parentNodes) {
		this.parentNodes = parentNodes;
	}

	public Integer getIsNewPage() {
		return isNewPage;
	}

	public void setIsNewPage(Integer isNewPage) {
		this.isNewPage = isNewPage;
	}

	public Integer getIsModal() {
		return isModal;
	}

	public void setIsModal(Integer isModal) {
		this.isModal = isModal;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public Integer getIsShow2Client() {
		return isShow2Client;
	}

	public void setIsShow2Client(Integer isShow2Client) {
		this.isShow2Client = isShow2Client;
	}

	public String getMenuLogo() {
		return menuLogo;
	}

	public void setMenuLogo(String menuLogo) {
		this.menuLogo = menuLogo;
	}

	public Integer getIsOpenPerm() {
		return isOpenPerm;
	}

	public void setIsOpenPerm(Integer isOpenPerm) {
		this.isOpenPerm = isOpenPerm;
	}
	
}
