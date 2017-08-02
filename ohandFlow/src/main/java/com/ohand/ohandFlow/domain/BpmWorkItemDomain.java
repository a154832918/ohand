package com.ohand.ohandFlow.domain;

import java.util.Date;

import com.ohand.ohandFlow.common.BaseModel;

/*
 * TODO bpm 与 业务表的 ---关联关系表
 */
public class BpmWorkItemDomain extends BaseModel {

	private static final long serialVersionUID = 1524570185966042880L;

	private Integer id;
	
	private Integer rTableId;
	
	private String rTableName;
	
	private String title;

	private Integer flowId;
	
	private Integer flowCode;

	private String flowName;

	private Integer flowInstId;

	private Date createDate;

	private Integer status;

	private String sender;

	private Integer senderId;

	private String receiver;

	private Integer receiverId;

	private Integer sourceType;

	private Date readDate;

	private Date dealDate;
	
	private Integer activityId;
	
	private String activityName;

	public Integer getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(Integer flowCode) {
		this.flowCode = flowCode;
	}

	public Integer getActivityId() {
		return activityId;
	}

	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public BpmWorkItemDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFlowId() {
		return flowId;
	}

	public void setFlowId(Integer flowId) {
		this.flowId = flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public Integer getFlowInstId() {
		return flowInstId;
	}

	public void setFlowInstId(Integer flowInstId) {
		this.flowInstId = flowInstId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public Date getReadDate() {
		return readDate;
	}

	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}

	public Date getDealDate() {
		return dealDate;
	}

	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getrTableId() {
		return rTableId;
	}

	public void setrTableId(Integer rTableId) {
		this.rTableId = rTableId;
	}

	public String getrTableName() {
		return rTableName;
	}

	public void setrTableName(String rTableName) {
		this.rTableName = rTableName;
	}
	
}
