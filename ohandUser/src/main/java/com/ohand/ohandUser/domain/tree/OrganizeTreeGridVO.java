package com.ohand.ohandUser.domain.tree;

import java.util.List;

import com.ohand.ohandUser.common.tree.TreeGrid;
import com.ohand.ohandUser.domain.OrganizeDomain;

public class OrganizeTreeGridVO extends OrganizeDomain implements
		TreeGrid<OrganizeDomain> {

	private static final long serialVersionUID = 3834180120740384796L;

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
