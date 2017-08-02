package com.ohand.common.plugin.mybaits;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-20
 * @Function:
 */
public abstract class Dialect {

	public static enum Type {
		MYSQL, ORACLE, DB2
	}

	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);

}
