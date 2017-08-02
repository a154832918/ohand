package com.ohand.ohandFlow.common;


import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-1
 */
public final class PropertyUtils {
	//用于文件的热加载
	private static Map timeMap=new HashMap();
	
	private static PropertyUtils utils = new PropertyUtils();

	private PropertyUtils() {
	}

	public static PropertyUtils getInstance() {
		return utils;
	}

	public Map<Object, Object> Properties2Map(String propPath) {
		Properties pro = loadProperties(propPath);
		if (pro == null)
			return null;
		Map<Object, Object> map = new HashMap<Object, Object>();
		Iterator<?> iterator = pro.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<Object, Object> entry = (Entry<Object, Object>) iterator
					.next();
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	/**
	 */
	private static final Log log = LogFactory.getLog(PropertyUtils.class);

	/**
	 * 
	 */
	private static Map<String, Properties> propMap = new HashMap<String, Properties>();
	
	/**
	 * 用于加载程序代码中的properties文件
	 * @param propName
	 * @return java.util.Properties
	 */
	public static Properties loadProperties(String propName) {
		
		File passInFile = new File(propName);
		long lastModified=passInFile.lastModified();
		boolean item=passInFile.exists();
		boolean isSame=false;
		String oldTime=""+timeMap.get(propName+"_j");
		if(oldTime.equals(lastModified+"")){
			isSame=true;
		}else{
			timeMap.put(propName+"_j", lastModified);
		}
		if (propName == null)
			propName = "config/framework.properties";
		if (propMap.containsKey(propName+"_j")&&isSame)
			return (Properties) propMap.get(propName+"_j");
		
		Properties props = new Properties();
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			props.load(loader.getResourceAsStream(propName));
			propMap.put(propName+"_j", props);
		} catch (Exception e) {
			log.error("getProperties(String)", e);
		}
		return props;
	}
	
	public static String getEntryValue(String propName, String key) {
		Properties prop = loadProperties(propName);
		if (prop != null) {
			return prop.getProperty(key);
		}
		return null;
	}

	public static String getEntryValue(String key) {
		return getEntryValue(null, key);
	}

	public static int getIntEntryValue(String key) {
		String value = getEntryValue(null, key);
		int intValue = 0;
		if (value != null) {
			try {
				intValue = Integer.parseInt(value.trim());
			} catch (NumberFormatException ex) {
			}
		}
		return intValue;
	}

	public static long getLongEntryValue(String key) {
		String value = getEntryValue(null, key);
		long longValue = 0;
		if (value != null) {
			try {
				longValue = Long.parseLong(value.trim());
			} catch (NumberFormatException ex) {
			}
		}
		return longValue;
	}

	public static String getEntryValue(String propName, String key,
			String defaultValue) {
		Properties prop = loadProperties(propName);
		if (prop != null) {
			return prop.getProperty(key, defaultValue);
		}
		return null;
	}

	public static void main(String[] args) {
		String 发送到="asdfasdfsddddd";
		String ii=发送到.substring(1, 6);
		System.out.println("-----------"+ii);
		Map map= new HashMap();
			map.put(发送到, "fsdfsddd顶顶顶顶");
			
			System.out.println("-----------"+map.get(发送到));	
		
	}
	
}