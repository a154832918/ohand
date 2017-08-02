package com.ohand.common.plugin.mybaits;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
/**
 * TimeStamp类型处理
 */
public class OracleDateTypeHandler extends BaseTypeHandler {
	
	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		ps.setTimestamp(i, new java.sql.Timestamp(((Date) parameter).getTime()));
	}

	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		java.sql.Timestamp sqlTimestamp = rs.getTimestamp(columnName);
		if (sqlTimestamp != null) {
			return new java.util.Date(sqlTimestamp.getTime());
		}
		return null;
	}

	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		java.sql.Timestamp sqlTimestamp = cs.getTimestamp(columnIndex);
		if (sqlTimestamp != null) {
			return new java.util.Date(sqlTimestamp.getTime());
		}
		return null;
	}

	@Override
	public Object getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
