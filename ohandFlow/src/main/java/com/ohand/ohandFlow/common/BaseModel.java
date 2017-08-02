package com.ohand.ohandFlow.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseModel implements Serializable {

	private static final long serialVersionUID = -3897270223767344905L;
	
	public static int pageSize = 15;
	public static int pageIndex = 1;
	
	/**字符串转换成时间的格式*/
	public  static String  DATEFORMAT = "yyyy-MM-dd HH:mm";  
	/**扩展属性值*/
	private  Map ohandExtAttrs = new HashMap();
	
	public Object get(Object key){
		return ohandExtAttrs.get(key);
	}
	
	/**
	 * @return Returns the attrs.
	 */
	public Map getOhandExtAttrs() {
		return ohandExtAttrs;
	}

	public Object put(Object key,Object value){
		return ohandExtAttrs.put(key,value);
	}
	/**
	 * @param attrs The attrs to set.
	 */
	public void setOhandExtAttrs(Map attrs) {
		this.ohandExtAttrs = attrs;
	}

}