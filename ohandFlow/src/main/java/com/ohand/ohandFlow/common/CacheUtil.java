package com.ohand.ohandFlow.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-21
 * @Function:
 */
public class CacheUtil {
	/** 最大数量 **/
	private int cacheMax = PFConstant.CACHE_MAX_OBJECT;
	/** 当前数量 **/
	private int count = 0;
	/** 内容缓存 **/
	private static ConcurrentMap<String, Object> content = new ConcurrentHashMap<String, Object>();
	/** 最好访问时间 **/
	private static ConcurrentMap<String, Object> dateMap = new ConcurrentHashMap<String, Object>();
	/** **/
	private static CacheUtil cache = null;
	/** debug **/
	private boolean debug = false;

	private CacheUtil() {

	}

	public static CacheUtil getInstance() {
		if (cache == null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized ("") {
				if(cache==null){
					cache = new CacheUtil();
				}
			}
		}
		return cache;
	}

	public String toString() {
		return content.toString();
	}

	public synchronized void put(String key, Object obj) {
		if (count > cacheMax) {
			// 先清除最少访问的。
			if (dateMap.size() > 0) {
				List arrayList = new ArrayList(dateMap.entrySet());
				Collections.sort(arrayList, new Comparator() {
					public int compare(Object o1, Object o2) {
						Map.Entry obj1 = (Map.Entry) o1;
						Map.Entry obj2 = (Map.Entry) o2;
						return ((Long) obj1.getValue()).compareTo((Long) obj2
								.getValue());
					}
				});
				Map.Entry first = (Map.Entry) arrayList.get(0);
				String k = (String) first.getKey();
				this.pop(k);

			}
		}
		content.putIfAbsent(key, obj);
		dateMap.putIfAbsent(key, new Long((new Date()).getTime()));
		count++;
	}

	public synchronized Object pop(String key) {
		if (content.containsKey(key)) {
			Object obj = content.get(key);
			content.remove(key);
			dateMap.remove(key);
			count--;
			return obj;
		}
		return null;
	}

	public synchronized Object get(String key) {
		if (content.containsKey(key)) {
			dateMap.putIfAbsent(key, new Long((new Date()).getTime()));
			return content.get(key);
		} else
			return null;
	}

	public synchronized boolean exists(String key) {
		if (content.containsKey(key))
			return true;
		else
			return false;
	}

	public void clear() {
		content.clear();
		dateMap.clear();
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean isDebug) {
		this.debug = isDebug;
	}

}
