package com.ohand.ohandFlow.domain;

import java.io.Serializable;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-28
 */
public class TableFieldIncrease implements Serializable {
	private static final long serialVersionUID = -1151251825681612007L;
	private Integer id;
	private String tableName;
	private String fieldName;
	private Integer nextVal;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Integer getNextVal() {
		return nextVal;
	}

	public void setNextVal(Integer nextVal) {
		this.nextVal = nextVal;
	}

	public TableFieldIncrease() {
	}
	
	public TableFieldIncrease(Integer id, String tableName, String fieldName,
			Integer nextVal) {
		this.id = id;
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.nextVal = nextVal;
	}

	public TableFieldIncrease(String tableName, String fieldName,Integer nextVal) {
		this.tableName = tableName;
		this.fieldName = fieldName;
		this.nextVal=nextVal;
	}

}
