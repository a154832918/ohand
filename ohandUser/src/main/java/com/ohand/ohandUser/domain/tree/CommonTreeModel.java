package com.ohand.ohandUser.domain.tree;

import com.ohand.ohandUser.common.tree.Tree;
import com.ohand.ohandUser.domain.CommonDomain;
import com.ohand.ohandUser.common.tree.BaseTree;

public class CommonTreeModel extends BaseTree<CommonDomain> implements Tree{
	private CommonDomain common ;
	
	public CommonTreeModel(CommonDomain common){
		super(common);
		this.common= common;
		initValue();
	}

	public void setTextValue() {
		this.setText(common.getName());
	}

	public void setIdValue() {
		this.setId(common.getId());
	}

	public void setParentId() {
		this.setPid(common.getParentId());
	}
	
	public CommonDomain getCommon() {
		return common;
	}

	public void setCommon(CommonDomain common) {
		this.common = common;
	}

	
}
