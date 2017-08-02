package com.ohand.ohandFlow.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.ohand.ohandFlow.common.BaseModel;

public abstract  class BaseTree<T extends BaseModel> implements Tree{
	private String text;
	private String iconCls;
	private Integer id;
	private String state;
	private Integer pid;
	private List<Tree> children = new ArrayList<Tree>();
	private Integer recordId;
	private Object attributes;

	public BaseTree(T t){
	}

	public void setStateValue(String str) {
		if(str!=null&&!str.equals("")){
			this.state=str;
		}else{
			this.state="open";
		}
		
	}

	public void setIconsValue(String icons) {
		if(icons!=null&&!icons.equals("")){
			this.iconCls=icons;
		}
	}
	
	public void setChildrenValue(List children) {
		
	}
	public void setChildrenValue(Tree tree){
		children.add(tree);
	}
	public Integer getTreeId(){
		return this.id;
	}
	
	public Integer getTreeParentId(){
		return this.pid;
	}
	
	public void initValue(){
		this.setTextValue();
		this.setIdValue();
		this.setIconsValue("");
		this.setStateValue("");
		this.setParentId();
	}
	/*
	 * seters and geters
	 */

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}
	
}
