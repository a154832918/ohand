package com.ohand.ohandUser.domain.tree;

import java.io.Serializable;

import com.ohand.ohandUser.common.tree.BaseTree;
import com.ohand.ohandUser.domain.ConstantConfigDomain;

public class ConstantConfigTreeModel extends BaseTree<ConstantConfigDomain> implements Serializable{
	private static final long serialVersionUID = -4827851777769113749L;
	private ConstantConfigDomain directory ;

	public ConstantConfigTreeModel(ConstantConfigDomain directory){
		super(directory);
		this.directory= directory;
		initValue();
	}

	public void setTextValue() {
		this.setText(directory.getShowName());
	}

	public void setIdValue() {
		this.setId(directory.getId());
	}

	public void setParentId() {
		this.setPid(directory.getParentId());
	}
	public Integer getParentId() {
		return directory.getParentId();
	}

	public Integer getChildrenCount() {
		return this.getChildrenCount();
	}

	public void setChildrenCount(Integer childrenCount) {
		this.setChildrenCount(childrenCount);
	}
}
