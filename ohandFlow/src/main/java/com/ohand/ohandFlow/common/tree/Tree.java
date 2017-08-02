package com.ohand.ohandFlow.common.tree;

import java.util.List;

/**
 * @author Yaojh
 * @version 2014-2-13
 * @Function:构成easyUI tree 的children格式的方法
 */
public interface Tree {

	public void setChildrenValue(List children);
	
	public void setChildrenValue(Tree tree);

	public void setStateValue(String str);
	
	public void setIconsValue(String icons);
	
	public void setTextValue();
	
	public void setIdValue();
	
	
	public Integer getTreeId();
	
	public Integer getTreeParentId();
	
	public void setParentId();
	
	public void initValue();
	
}
