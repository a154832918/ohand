package com.ohand.ohandFlow.common.tree;

import java.util.ArrayList;
import java.util.List;

public class BuildTreeModelUtil<T extends Tree> {

	public List<T> convertList(List<T> list, int parentId) {
		List<T> resultList = new ArrayList<T>();
		for (T tree : list) {
			if (tree.getTreeParentId() != null && tree.getTreeParentId() == parentId) {
				List<T> childList=convertList(list, tree.getTreeId().intValue());
				if (childList != null && childList.size() > 0) {
					tree.setChildrenValue(childList);
					if(childList==null||childList.size()==0){
						tree.setStateValue("open");
					}else{
						tree.setStateValue("closed");
					}
				}
				resultList.add(tree);
			} 
		}
		return resultList;
	}
	
}
