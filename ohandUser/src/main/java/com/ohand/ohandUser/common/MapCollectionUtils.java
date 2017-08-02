package com.ohand.ohandUser.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-1
 */
public class MapCollectionUtils {

	public static boolean isEmpty(Collection paramCollection) {
		return CollectionUtils.isEmpty(paramCollection);
	}

	public static boolean isEmpty(Map paramMap) {
		return CollectionUtils.isEmpty(paramMap);
	}

	public static Map removeEmptyEntry(Map paramMap) {
		return removeEntryOfEmptyValue(removeEntryOfEmptyKey(paramMap));
	}

	public static Map removeEntryOfEmptyKey(Map paramMap) {
		HashMap localHashMap;
		Set localSet;
		if (isEmpty(paramMap)) {
			return new HashMap();
		}
		(localHashMap = new HashMap()).putAll(paramMap);
		Iterator localIterator = (localSet = paramMap.entrySet()).iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry;
			Object localObject = (localEntry = (Map.Entry) localIterator.next())
					.getKey();
			if ((localObject) instanceof String) {
				String str;
				if ((!(StringUtils.isEmpty(str = (String) localObject)))
						&& (!(StringUtils.isEmpty(str.trim()))))
					continue;
				localHashMap.remove(localObject);
			} else if (localObject instanceof Collection) {
				if (!(isEmpty((Collection) localObject)))
					continue;
				localHashMap.remove(localObject);
			} else if (localObject instanceof Map) {
				if (!(isEmpty((Map) localObject)))
					continue;
				localHashMap.remove(localObject);
			} else if (localObject instanceof Object[]) {
				if (((Object[]) localObject).length != 0)
					continue;
				localHashMap.remove(localObject);
			} else {
				if (localObject != null)
					continue;
				localHashMap.remove(localObject);
			}
		}
		return localHashMap;
	}

	public static Map removeEntryOfEmptyValue(Map paramMap) {
		HashMap localHashMap;
		Set localSet;
		if (isEmpty(paramMap))
			return new HashMap();
		(localHashMap = new HashMap()).putAll(paramMap);
		Iterator localIterator = (localSet = paramMap.entrySet()).iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry;
			Object localObject = (localEntry = (Map.Entry) localIterator.next())
					.getValue();
			if ((localObject) instanceof String) {
				String str;
				if ((!(StringUtils.isEmpty(str = (String) localObject)))
						&& (!(StringUtils.isEmpty(str.trim()))))
					continue;
				localHashMap.remove(localEntry.getKey());
			} else if (localObject instanceof Collection) {
				if (!(isEmpty((Collection) localObject)))
					continue;
				localHashMap.remove(localEntry.getKey());
			} else if (localObject instanceof Map) {
				if (!(isEmpty((Map) localObject)))
					continue;
				localHashMap.remove(localEntry.getKey());
			} else if (localObject instanceof Object[]) {
				if (((Object[]) localObject).length != 0)
					continue;
				localHashMap.remove(localEntry.getKey());
			} else {
				if (localObject != null)
					continue;
				localHashMap.remove(localEntry.getKey());
			}
		}
		return localHashMap;
	}

	public static Map removeEntryOfSpecialValue(Map paramMap, Object paramObject) {
		ArrayList localArrayList;
		if (paramObject == null)
			return paramMap;
		(localArrayList = new ArrayList()).add(paramObject);
		return removeEntryOfSpecialValue(paramMap, localArrayList);
	}

	public static Map removeEntryOfSpecialValue(Map paramMap, List paramList) {
		HashMap localHashMap;
		Set localSet;
		if (CollectionUtils.isEmpty(paramList))
			return paramMap;
		if (isEmpty(paramMap))
			return new HashMap();
		(localHashMap = new HashMap()).putAll(paramMap);
		Iterator localIterator1 = (localSet = paramMap.entrySet()).iterator();
		while (localIterator1.hasNext()) {
			Map.Entry localEntry;
			Object localObject1 = (localEntry = (Map.Entry) localIterator1
					.next()).getValue();
			Iterator localIterator2 = paramList.iterator();
			while (localIterator2.hasNext()) {
				Object localObject2 = localIterator2.next();
				if (localObject1 == null)
					continue;
				if (localObject2 == null)
					continue;
				if (!(localObject1.toString().equals(localObject2.toString())))
					continue;
				localHashMap.remove(localEntry.getKey());
			}
		}
		return localHashMap;
	}
}
