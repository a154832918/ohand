package com.ohand.ohandUser.common.tree;

import java.util.List;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-17
 * @Function:构成easyUI treeGrid 的children格式的方法
 */
public interface TreeGrid<T> {

	public List<T> getChildren();

	public void setChildren(List<T> children);

	public Object getParentId();

	public Object getId();
	
	public Integer getChildrenCount();
	
	public void setChildrenCount(Integer childrenCount);
	
	public String getState();

	public void setState(String state);
	
	public String getText();

	public void setText(String text);
	
	
}
