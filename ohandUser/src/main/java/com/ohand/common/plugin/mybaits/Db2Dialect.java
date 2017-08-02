package com.ohand.common.plugin.mybaits;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-20
 * @Function:
 */
public class Db2Dialect extends Dialect {

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

		pagingSelect
				.append("select * from  (select rownumber()over() as db2rowid ,db2sql.*   from ( ");
		pagingSelect.append(sql);
		pagingSelect.append(" )  as db2sql) as db2page where db2page.db2rowid>"
				+ offset + " and db2page.db2rowid<" + (offset + limit));

		if (isForUpdate) {
			pagingSelect.append(" for update");
		}
		return pagingSelect.toString();
	}
}
