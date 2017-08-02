package com.ohand.ohandFlow.common;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-1
 */
public class ParameterHandle {

	public static Map getParameterMap(HttpServletRequest request) {
		Map properties = request.getParameterMap();
		// 返回值Map
		Map returnMap = new HashMap();
		Iterator entries = properties.entrySet().iterator();
		Map.Entry entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();			
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
	/*
	 * 分页时的参数处理
	 */
	public static Map handlePage(HttpServletRequest request,
			int defualtPageSize, int defualtPageIndex) {
		Map returnMap = new HashMap();
		returnMap = getParameterMap(request);
		int pageSize = defualtPageSize;
		int pageIndex = defualtPageIndex;
		// 分页参数的处理 2013年11月20日
		// 分页的来源，有如下2个
		// a、easyui 的 treegrid的分页处理
		// b、一般table列表的分页处理
		// c、是否分页参数isPagination:true,fase
		if (CollectionUtils.isEmpty(returnMap)) {
			// 1、转换easyui特有的参数------------------------------------------------------------------------------
			if (returnMap.containsKey("page")) {
				pageIndex = new Integer((String) returnMap.get("page"));
				returnMap.put("pageIndex", pageIndex + "");
			}
			if (returnMap.containsKey("rows")) {
				pageSize = new Integer((String) returnMap.get("rows"));
				returnMap.put("pageSize", pageSize + "");
			}
			// 2、判断是否有pageSize，pageIndex参数------------------------------------------------------------------
			if (!returnMap.containsKey("pageIndex")) {
				returnMap.put("pageIndex", pageIndex + "");
			}
			if (!returnMap.containsKey("pageSize")) {
				returnMap.put("pageSize", pageSize + "");
			}
			return returnMap;
		} else {
			// 1、转换easyui特有的参数------------------------------------------------------------------------------
			if (returnMap.containsKey("page")) {
				pageIndex = new Integer((String) returnMap.get("page"));
				returnMap.put("pageIndex", pageIndex + "");
			}
			if (returnMap.containsKey("rows")) {
				pageSize = new Integer((String) returnMap.get("rows"));
				returnMap.put("pageSize", pageSize + "");
			}
			// 2、判断是否有pageSize，pageIndex参数------------------------------------------------------------------
			if (!returnMap.containsKey("pageIndex")) {
				returnMap.put("pageIndex", pageIndex + "");
			}
			if (!returnMap.containsKey("pageSize")) {
				returnMap.put("pageSize", pageSize + "");
			}
			returnMap = MapCollectionUtils.removeEmptyEntry(returnMap);
			return returnMap;
		}
	}
	/*
	 * 非分页的参数处理
	 * 
	 */
	public static Map handle(HttpServletRequest request) {
		Map returnMap = new HashMap();
		returnMap = getParameterMap(request);
		if (CollectionUtils.isEmpty(returnMap)) {
			return returnMap;
		} else {
			returnMap = MapCollectionUtils.removeEmptyEntry(returnMap);
			return returnMap;
		}
	}
}
