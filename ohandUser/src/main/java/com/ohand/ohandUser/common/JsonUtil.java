package com.ohand.ohandUser.common;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-10-25
 */
public class JsonUtil {

	private static SerializeConfig mapping = new SerializeConfig(); 
	private static String dateFormat;  
	
	static{
		dateFormat = "yyyy-MM-dd HH:mm:ss";  
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));		
	}
	
	public JsonUtil() {
	}

	/**
	 * 数组转换成json格式字符串
	 */
	public static String array2Json(Object[] arr) {
		if (arr == null) {
			return null;
		}
		return JSON.toJSONString(arr,mapping);
	}

	/**
	 * json格式字符串转数组
	 * @param <T>
	 */
	public static <T> List<T> json2Array(String str,Class<T> clz) {
		if (str == null) {
			return null;
		}
		return JSON.parseArray(str,clz);
	}

	/**
	 * list集合转json格式字符串
	 */
	public static String list2Json(List list) {
		String jsonText = JSON.toJSONString(list, mapping);
		return jsonText;
	}

	/**
	 * map转json格式字符串
	 */
	public static String map2Json(Map map) {
		String jsonText = JSON.toJSONString(map, mapping);
		return jsonText;
	}

	/**
	 * 反序列化为json对象
	 */
	public static JSONObject parseStr2JsonObj(String str) {
		JSONObject json = JSON.parseObject(str);
		return json;
	}

	/**
	 * 反序列化为json对象
	 */
	public static JSONArray parseStr2JsonArray(String str) {
		JSONArray json = JSON.parseArray(str);
		return json;
	}
	
	public static JSONObject parseStr2JsonObjUnQuoted(String str) {
		JSONObject json = JSON.parseObject(str,Feature.AllowUnQuotedFieldNames);
		return json;
	}
	
	/**
	 * 将javaBean转化为json对象
	 */
	public static JSONObject bean2Json(Object obj) {
		JSONObject jsonObj = (JSONObject) JSON.toJSON(obj);
		return jsonObj;
	}
	/*
	 * 字符串转为json
	 */
	public static String str2Json(String str){
		String jsonText = JSON.toJSONString(str, mapping);
		return jsonText;
	}
	/*
	 * 字符串转为对象
	 */
	public static <T> T str2Obj(Class<T> clz,String str){
		return JSON.parseObject(str,clz);
	}
	/*
	 * 对象转换为json
	 */
	public static String obj2Json(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, mapping);
	}
	
	/*
	 * 对象转换为json
	 */
	public static String obj2JsonUseSingleQuotes(Object obj) {
		if (obj == null) {
			return null;
		}
		return JSON.toJSONString(obj, mapping,SerializerFeature.UseSingleQuotes);
	}
    
	public static String wrapperJsonWithTotalCount(String json ,int totalCount){
		StringBuffer sb=new StringBuffer();
		sb.append("{\"total\":" + totalCount + ",\"rows\":");
		sb.append(json);
		sb.append("}");
		return sb.toString();
	}
	
}