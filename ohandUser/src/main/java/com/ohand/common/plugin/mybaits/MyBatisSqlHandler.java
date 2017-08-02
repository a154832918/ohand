package com.ohand.common.plugin.mybaits;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-2
 */
public class MyBatisSqlHandler {

	public static String likePlusPercentTagCallback(String parameter) {
		return "%" + parameter + "%";
	}

	public static String stringArrayTagCallback(String[] parameter) {
		StringBuffer result = new StringBuffer();
		for (String value : parameter) {
			result.append(value).append(",");
		}
		result.deleteCharAt(result.length() - 1);
		return result + "";

	}

}
