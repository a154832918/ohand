package com.ohand.ohandUser.domain.tree;

import java.io.Serializable;

import com.ohand.ohandUser.common.tree.BaseTree;
import com.ohand.ohandUser.domain.PermissionItemDomain;

public class PermissionItemTreeModel extends BaseTree<PermissionItemDomain> implements Serializable{
	
	private static final long serialVersionUID = -6223506277057587857L;
	private PermissionItemDomain directory ;

	public PermissionItemTreeModel(PermissionItemDomain directory){
		super(directory);
		this.directory= directory;
		initValue();
	}

	public void setTextValue() {
		this.setText(directory.getResName());
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
	
}
