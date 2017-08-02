package com.ohand.common.plugin.mybaits;

/**
 * @author Richard
 * @Emial  a154832918@163.com
 * @version 2013-11-20
 * @Function:
 */
public class MySql5Dialect extends Dialect {

	protected static final String SQL_END_DELIMITER = ";";

	public String getLimitString(String sql, boolean hasOffset) {
		return MySql5PageHepler.getLimitString(sql, -1, -1);
	}

	public String getLimitString(String sql, int offset, int limit) {
		return MySql5PageHepler.getLimitString(sql, offset, limit);
	}

	public boolean supportsLimit() {
		return true;
	}
}
