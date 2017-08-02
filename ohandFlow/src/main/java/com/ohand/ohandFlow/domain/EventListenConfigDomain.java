package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class EventListenConfigDomain extends BaseModel {

	private static final long serialVersionUID = -5754259317573366634L;

	private Integer id;

	private String queueName;

	private String listenerClass;

	private String queueClass;

	private Integer isUseThread;

	private Integer isOpen;

	private String eDescription;

	public EventListenConfigDomain() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getListenerClass() {
		return listenerClass;
	}

	public void setListenerClass(String listenerClass) {
		this.listenerClass = listenerClass;
	}

	public String getQueueClass() {
		return queueClass;
	}

	public void setQueueClass(String queueClass) {
		this.queueClass = queueClass;
	}

	public Integer getIsUseThread() {
		return isUseThread;
	}

	public void setIsUseThread(Integer isUseThread) {
		this.isUseThread = isUseThread;
	}

	public Integer getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(Integer isOpen) {
		this.isOpen = isOpen;
	}

	public String getEDescription() {
		return eDescription;
	}

	public void setEDescription(String eDescription) {
		this.eDescription = eDescription;
	}

}
