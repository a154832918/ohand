package com.ohand.ohandFlow.domain;

import com.ohand.ohandFlow.common.BaseModel;

public class FlowConnectorDomain extends BaseModel {
	private static final long serialVersionUID = -7776687500837009688L;

	private Integer Id;

	private String connName;

	private Integer connCode;

	private Integer preId;

	private Integer nextId;

	private Integer relatedFlowId;

	private String relatedFlowName;

	private String textPos;
	
	private String props;
	
	private String dots;
	
	public String getDots() {
		return dots;
	}

	public void setDots(String dots) {
		this.dots = dots;
	}

	public String getTextPos() {
		return textPos;
	}

	public void setTextPos(String textPos) {
		this.textPos = textPos;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getConnName() {
		return connName;
	}

	public void setConnName(String connName) {
		this.connName = connName;
	}

	public Integer getConnCode() {
		return connCode;
	}

	public void setConnCode(Integer connCode) {
		this.connCode = connCode;
	}

	public Integer getPreId() {
		return preId;
	}

	public void setPreId(Integer preId) {
		this.preId = preId;
	}

	public Integer getNextId() {
		return nextId;
	}

	public void setNextId(Integer nextId) {
		this.nextId = nextId;
	}

	public Integer getRelatedFlowId() {
		return relatedFlowId;
	}

	public void setRelatedFlowId(Integer relatedFlowId) {
		this.relatedFlowId = relatedFlowId;
	}

	public String getRelatedFlowName() {
		return relatedFlowName;
	}

	public void setRelatedFlowName(String relatedFlowName) {
		this.relatedFlowName = relatedFlowName;
	}
}
