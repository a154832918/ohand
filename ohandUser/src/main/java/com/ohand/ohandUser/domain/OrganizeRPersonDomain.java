package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

public class OrganizeRPersonDomain extends BaseModel{

	private static final long serialVersionUID = 6547218846255882831L;

	private Integer id;

	/**
	 * Organize_Id
	 * @label Organize_Id
	 * @code Organize_Id
	 * @type INTEGER
	 * @javaType Integer
	 * @show 
	 * @index 2
	 * @oddeven 1
	 */
	private Integer organizeId;

	/**
	 * Person_Id
	 * @label Person_Id
	 * @code Person_Id
	 * @type INTEGER
	 * @javaType Integer
	 * @show 
	 * @index 3
	 * @oddeven 2
	 * @isAfterTwo 2
	 */
	private Integer personId;

	/**
	 * Organize_Name
	 * @label Organize_Name
	 * @code Organize_Name
	 * @type VARCHAR
	 * @javaType String
	 * @show 
	 * @length 64
	 * @index 4
	 * @oddeven 1
	 * @isAfterTwo 1
	 * @faint 
	 */
	private String organizeName;

	/**
	 * Person_Name
	 * @label Person_Name
	 * @code Person_Name
	 * @type VARCHAR
	 * @javaType String
	 * @show 
	 * @isLast 
	 * @length 64
	 * @index 5
	 * @oddeven 2
	 * @isAfterTwo 2
	 * @faint 
	 */
	private String personName;
	
	//TODO 是否主部门
	private Integer isMain;
	
	/**
	 * 
	 */
	public OrganizeRPersonDomain() {
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
	 * @return the organizeId
	 */ 
	public Integer getOrganizeId() {
		return organizeId;
	}

	/** 
	 * @param organizeId the organizeId to set 
	 */ 
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}

	/** 
	 * @return the personId
	 */ 
	public Integer getPersonId() {
		return personId;
	}

	/** 
	 * @param personId the personId to set 
	 */ 
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	/** 
	 * @return the organizeName
	 */ 
	public String getOrganizeName() {
		return organizeName;
	}

	/** 
	 * @param organizeName the organizeName to set 
	 */ 
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}

	/** 
	 * @return the personName
	 */ 
	public String getPersonName() {
		return personName;
	}

	/** 
	 * @param personName the personName to set 
	 */ 
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getIsMain() {
		return isMain;
	}

	public void setIsMain(Integer isMain) {
		this.isMain = isMain;
	}

}
