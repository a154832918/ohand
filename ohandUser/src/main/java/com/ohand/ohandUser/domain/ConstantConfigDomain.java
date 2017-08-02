package com.ohand.ohandUser.domain;

import com.ohand.ohandUser.common.BaseModel;

/**
 * @author richard
 * 
 */
public class ConstantConfigDomain extends BaseModel{

	private static final long serialVersionUID = -6673162545515218741L;

	/**
	 * Id
	 * 
	 * @label Id
	 * @code Id
	 * @type INTEGER
	 * @javaType Integer
	 * @pk instance="Id"
	 * @required true
	 * @index 1
	 * @oddeven 0
	 */
	private Integer id;

	/**
	 * ����Id
	 * 
	 * @code Parent_Id
	 * @type INTEGER
	 * @javaType Integer
	 * @show
	 * @index 2
	 * @oddeven 1
	 */
	private Integer parentId;

	/**
	 * @code Priority
	 * @type INTEGER
	 * @javaType Integer
	 * @show
	 * @index 3
	 * @oddeven 2
	 * @isAfterTwo 2
	 */
	private Integer priority;

	/**
	 * @code Show_Name
	 * @type VARCHAR
	 * @javaType String
	 * @show
	 * @length 64
	 * @index 4
	 * @oddeven 1
	 * @isAfterTwo 1
	 * @faint
	 */
	private String showName;

	/**
	 * ֵ
	 * 
	 * @code Hidden_Value
	 * @type VARCHAR
	 * @javaType String
	 * @show
	 * @length 64
	 * @index 5
	 * @oddeven 2
	 * @isAfterTwo 2
	 * @faint
	 */
	private String hiddenValue;

	/**
	 * @code Enable_Flag
	 * @type INTEGER
	 * @javaType Integer
	 * @show
	 * @index 6
	 * @oddeven 1
	 * @isAfterTwo 1
	 */
	private Integer enableFlag;

	/**
	 * @code Description
	 * @type VARCHAR
	 * @javaType String
	 * @show
	 * @length 128
	 * @index 7
	 * @oddeven 2
	 * @isAfterTwo 2
	 * @faint
	 */
	private String description;

	/**
	 * @code Parent_Nodes
	 * @type VARCHAR
	 * @javaType String
	 * @show
	 * @length 128
	 * @index 8
	 * @oddeven 1
	 * @isAfterTwo 1
	 * @faint
	 */
	private String parentNodes;

	/**
	 * @code Top_Id
	 * @type TIMESTAMP
	 * @javaType java.util.Date
	 * @show
	 * @isLast
	 * @index 9
	 * @oddeven 2
	 * @isAfterTwo 2
	 */
	private Integer topId;

	/**
	 * 
	 */
	public ConstantConfigDomain() {
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
	 * @return the parentId
	 */
	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the priority
	 */
	public Integer getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**
	 * @return the showName
	 */
	public String getShowName() {
		return showName;
	}

	/**
	 * @param showName
	 *            the showName to set
	 */
	public void setShowName(String showName) {
		this.showName = showName;
	}

	/**
	 * @return the hiddenValue
	 */
	public String getHiddenValue() {
		return hiddenValue;
	}

	/**
	 * @param hiddenValue
	 *            the hiddenValue to set
	 */
	public void setHiddenValue(String hiddenValue) {
		this.hiddenValue = hiddenValue;
	}

	/**
	 * @return the enableFlag
	 */
	public Integer getEnableFlag() {
		return enableFlag;
	}

	/**
	 * @param enableFlag
	 *            the enableFlag to set
	 */
	public void setEnableFlag(Integer enableFlag) {
		this.enableFlag = enableFlag;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentNodes
	 */
	public String getParentNodes() {
		return parentNodes;
	}

	/**
	 * @param parentNodes
	 *            the parentNodes to set
	 */
	public void setParentNodes(String parentNodes) {
		this.parentNodes = parentNodes;
	}

	public Integer getTopId() {
		return topId;
	}

	public void setTopId(Integer topId) {
		this.topId = topId;
	}

}
