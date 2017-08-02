package com.ohand.common.plugin.mybaits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.CallableStatement;
import java.sql.Clob;
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
public class ClobTypeTagCallback extends BaseTypeHandler {

	public void setParameter(PreparedStatement ps, int i, Object parameter,
			JdbcType jdbcType) throws SQLException {
		if (parameter == null) {
			if (jdbcType == null) {
				try {
					ps.setNull(i, JdbcType.OTHER.TYPE_CODE);
				} catch (SQLException e) {
					throw new TypeException(
							"Ohand_ld:Error setting null parameter.  Most JDBC drivers require that the JdbcType must be specified for all nullable parameters. Cause: "
									+ e, e);
				}
			} else {
//				CLOB clob = CLOB.getEmptyCLOB();
//		        clob.setString(1, (String)parameter);
//		        ps.setClob(i, clob);
		    }
		} else {
			parameter = "%" + parameter + "%";
			setNonNullParameter(ps, i, parameter, jdbcType);
		}
	}

	@Override
	public Object getNullableResult(ResultSet arg0, String arg1)
			throws SQLException {Clob clob=arg0.getClob(arg1);
			String reString = "";
	        Reader is = null;
	        try {
	            is = clob.getCharacterStream();
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        // 得到流
	        BufferedReader br = new BufferedReader(is);
	        String s = null;
	        try {
	            s = br.readLine();
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        StringBuffer sb = new StringBuffer();
	        while (s != null) {
	            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
	            sb.append(s);
	            try {
	                s = br.readLine();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	        reString = sb.toString();
	        return reString;}

	@Override
	public Object getNullableResult(CallableStatement arg0, int arg1)
			throws SQLException {
		
		Clob clob=arg0.getClob(arg1);
		String reString = "";
        Reader is = null;
        try {
            is = clob.getCharacterStream();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 得到流
        BufferedReader br = new BufferedReader(is);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        while (s != null) {
            //执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
            sb.append(s);
            try {
                s = br.readLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        reString = sb.toString();
        return reString;
	}

	@Override
	public void setNonNullParameter(PreparedStatement arg0, int arg1,
			Object arg2, JdbcType arg3) throws SQLException {
		// TODO Auto-generated method stub
		arg0.setString(arg1, arg2.toString());
	}

	@Override
	public Object getNullableResult(ResultSet arg0, int arg1)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
