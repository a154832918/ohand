package com.ohand.ohandFlow.domain.tree;

import java.util.List;

import com.ohand.ohandFlow.common.tree.TreeGrid;
import com.ohand.ohandFlow.domain.FlowCategoryDomain;

public class FlowCategoryTreeGridVO extends FlowCategoryDomain implements
		TreeGrid<FlowCategoryDomain> {

	private static final long serialVersionUID = -1923202528235483171L;

	private List children;

	private Integer childrenCount;

	private String state;

	private String text;

	public Integer getChildrenCount() {
		return childrenCount;
	}

	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
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
