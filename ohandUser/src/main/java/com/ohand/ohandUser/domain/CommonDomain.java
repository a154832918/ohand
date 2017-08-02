package com.ohand.ohandUser.domain;

import java.io.Serializable;

import com.ohand.ohandUser.common.BaseModel;

/**
 * 功能：地址本
 * 	   除人员以外存在多条记录，其余的均为一条记录
 */
public class CommonDomain extends BaseModel{
	
	private static final long serialVersionUID = -1924851161592469889L;

	// 与机构的关系表
	public static final Integer SOURCE_TYPE_PERSON=new Integer(1);
	
	public static final Integer SOURCE_TYPE_ORGANIZE=new Integer(2);
	
	public static final Integer SOURCE_TYPE_GROUP=new Integer(3);
	
	public static final Integer SOURCE_TYPE_ROLE=new Integer(4);
	
	/**固定人员*/
	public static final Integer SOURCE_TYPE_FIXED=new Integer(5);
	
	private Integer id;

	private Integer recordId;

	private String name;

	private Integer sourceType;

	private Integer parentId;

	private String description;

	private Integer priority;
	
	//1:表示，主记录
	private Integer sysbol;
	
	private Object sourceTypeObj;

	public CommonDomain() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getSysbol() {
		return sysbol;
	}

	public void setSysbol(Integer sysbol) {
		this.sysbol = sysbol;
	}

	public Object getSourceTypeObj() {
		return sourceTypeObj;
	}

	public void setSourceTypeObj(Object sourceTypeObj) {
		this.sourceTypeObj = sourceTypeObj;
	}
	
}
