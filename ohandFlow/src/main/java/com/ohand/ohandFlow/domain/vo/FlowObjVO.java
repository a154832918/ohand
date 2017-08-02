package com.ohand.ohandFlow.domain.vo;

import java.util.List;

import com.ohand.ohandFlow.domain.FlowObjDomain;

public class FlowObjVO extends FlowObjDomain {
	
	private static final long serialVersionUID = 4656165363091254071L;

	private List children;

	private Integer childrenCount;

	private String state;

	private String text;

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
