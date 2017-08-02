package com.ohand.ohandUser.common.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ohand.ohandUser.common.JsonUtil;
import com.ohand.ohandUser.common.Reflections;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-3
 */
public class BuildTreeJsonUtil<T extends TreeGrid> {

	public String buildDataGridJson(List list, int parentId ) {
		BuildTreeJsonUtil util = new BuildTreeJsonUtil();
		List sortList = util.buildDataGridList(list, parentId);
		return JsonUtil.list2Json(sortList);
	}
	
	public String buildDataGridJson_Str(List list, String parentId ) {
		BuildTreeJsonUtil util = new BuildTreeJsonUtil();
		List sortList = util.buildDataGridList_Str(list, parentId);
		return JsonUtil.list2Json(sortList);
	}
	
	public String buildTreeJson(List list, int parentId ) {
		BuildTreeJsonUtil util = new BuildTreeJsonUtil();
		List sortList = util.buildTreeList(list, parentId);
		return JsonUtil.list2Json(sortList);
	}
	
	public String buildTreeJson_Str(List list, String parentId ) {
		BuildTreeJsonUtil util = new BuildTreeJsonUtil();
		List sortList = util.buildTreeList_Str(list, parentId);
		return JsonUtil.list2Json(sortList);
	}
	
	// 通过查询数据库来判断是否有子项
	public String buildTreeJsonFindDB(List list, int parentId ,
										final Object obj,
										final String methodName) {
		BuildTreeJsonUtil util = new BuildTreeJsonUtil();
		List sortList = util.buildTreeListFindDB(list, parentId,obj,methodName);
		return JsonUtil.list2Json(sortList);
	}
	
	public List<T> buildTreeList(List<T> list, int parentId) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T domain = list.get(i);
			if (domain.getParentId() != null
					&& ((Integer)domain.getParentId()).intValue() == parentId) {
				List<T> childList = buildTreeList(list, ((Integer)domain.getId())
						.intValue());
				if (childList != null && childList.size() > 0) {
					domain.setChildren(childList);
					domain.setChildrenCount(new Integer(childList.size()));
					if(childList==null||childList.size()==0){
						domain.setState("open");
					}else{
						domain.setState("closed");
					}
				}
				result.add(domain);
			}
		}
		return result;
	}
	
	public List<T> buildTreeList_Str(List<T> list, String parentId) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T domain = list.get(i);
			if (domain.getParentId() != null
					&& (""+domain.getParentId()).equals(parentId)) {
				List<T> childList = buildTreeList_Str(list, (""+domain.getId()));
				if (childList != null && childList.size() > 0) {
					domain.setChildren(childList);
					domain.setChildrenCount(new Integer(childList.size()));
					if(childList==null||childList.size()==0){
						domain.setState("open");
					}else{
						domain.setState("closed");
					}
				}
				result.add(domain);
			}
		}
		return result;
	}
	
	public List<T> buildDataGridList(List<T> list, int parentId) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T domain = list.get(i);
			if (domain.getParentId() != null
					&& ((Integer)domain.getParentId()).intValue() == parentId) {
				List<T> childList = buildTreeList(list, ((Integer)domain.getId())
						.intValue());
				if (childList != null && childList.size() > 0) {
					domain.setChildren(childList);
					domain.setChildrenCount(new Integer(childList.size()));
					if(childList==null||childList.size()==0){
						domain.setState("open");
					}else{
						domain.setState("closed");
					}
				}
				result.add(domain);
			}
		}
		return result;
	}
	
	public List<T> buildDataGridList_Str(List<T> list, String parentId) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T domain = list.get(i);
			if (domain.getParentId() != null
					&& (domain.getParentId()).equals(parentId)) {
				List<T> childList = buildTreeList_Str(list, (""+domain.getId()));
				if (childList != null && childList.size() > 0) {
					domain.setChildren(childList);
					domain.setChildrenCount(new Integer(childList.size()));
					if(childList==null||childList.size()==0){
						domain.setState("open");
					}else{
						domain.setState("closed");
					}
				}
				result.add(domain);
			}
		}
		return result;
	}
	
	public List<T> buildTreeListFindDB(List<T> list, int parentId,
											final Object obj,
											final String methodName) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
			T domain = list.get(i);
			if (domain.getParentId() != null
					&& ((Integer)domain.getParentId()).intValue() == parentId) {
				List<T> childList = buildTreeList(list, ((Integer)domain.getId())
						.intValue());
					// 反射执行代码 ，判断是否有子项,方法执行结果集是List<Map>	
						Map map=new HashMap();
						map.put("parentId", domain.getId());
					List listCount=(List) Reflections.invokeMethodByName(obj,methodName,new Object[] { map });
					int numb = new Integer(""+ ((Map) (listCount.get(0))).get("NUMB"));
					if(numb>0){
						domain.setState("closed");
					}else{
						domain.setState("open");
					}
				result.add(domain);
			}
		}
		return result;
	}
	
	public List<T> buildTreeListFindDB_Str(List<T> list, String parentId,
			final Object obj,
			final String methodName) {
		List<T> result = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {
		T domain = list.get(i);
		if (domain.getParentId() != null
		&& (""+domain.getParentId()).equals(parentId)) {
		List<T> childList = buildTreeList_Str(list, (domain.getId()+""));
		// 反射执行代码 ，判断是否有子项,方法执行结果集是List<Map>	
		Map map=new HashMap();
		map.put("parentId", domain.getId());
		List listCount=(List) Reflections.invokeMethodByName(obj,methodName,new Object[] { map });
		int numb = new Integer(""+ ((Map) (listCount.get(0))).get("NUMB"));
		if(numb>0){
		domain.setState("closed");
		}else{
		domain.setState("open");
		}
		result.add(domain);
			}
		}
	return result;
	}

	
}
