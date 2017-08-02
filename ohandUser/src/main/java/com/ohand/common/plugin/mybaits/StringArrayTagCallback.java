package com.ohand.common.plugin.mybaits;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeException;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-2
 */
public class StringArrayTagCallback extends BaseTypeHandler {

	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			if (jdbcType == null) {
				try {
					ps.setNull(i, JdbcType.OTHER.TYPE_CODE);
				} catch (SQLException e) {
					throw new TypeException(
							"PM_ld:Error setting null parameter.  Most JDBC drivers require that the JdbcType must be specified for all nullable parameters. Cause: "
									+ e, e);
				}
			} else {
				ps.setNull(i, jdbcType.TYPE_CODE);
			}
		} else {
			// add by liudan :String[a,b,c] to a,b,c
			StringBuffer result = new StringBuffer();
				String[] parameterArr=(String[]) parameter;
			   for (String value : parameterArr){
			    	 result.append(value).append(",");  
			   }  
			result.deleteCharAt(result.length()-1);  
			setNonNullParameter(ps, i, result, jdbcType);
		}
	}

	@Override
	public Object getNullableResult(ResultSet arg0, String arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getNullableResult(CallableStatement arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1,
			Object arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		arg0.setString(arg1, arg2.toString());  
	}
	
	public Object getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
