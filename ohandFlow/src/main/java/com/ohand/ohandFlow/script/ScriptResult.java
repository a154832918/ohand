package com.ohand.ohandFlow.script;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.ohand.ohandFlow.common.MapCollectionUtils;

public class ScriptResult {

	public static final String SCOPE_KEY = "scope$key";

	private Map map = new HashMap();

	public void addResult(String key, Object obj) {
		if (StringUtils.isEmpty(key) || obj == null)
			return;
		map.put(key, obj);
	}

	public Object getResultByKey(String key) {
		if (StringUtils.isEmpty(key))
			return null;
		if (map == null || MapCollectionUtils.isEmpty(map))
			return null;
		return map.get(key);
	}

	public Object removeResultByKey(String key) {
		if (StringUtils.isEmpty(key))
			return null;
		if (map == null || MapCollectionUtils.isEmpty(map))
			return null;
		return map.remove(key);
	}

	public Map getMap() {
		return map;
	}

	public Collection getAllValue() {
		if (map == null || MapCollectionUtils.isEmpty(map))
			return null;
		return map.values();
	}

	public String toString() {
		return map.toString();
	}

}
