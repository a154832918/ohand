package com.ohand.common.plugin.mybaits;

import java.sql.Connection;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.ohand.common.exception.BussinessException;

/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-20
 * @Function:
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor_bak implements Interceptor {

	private final static Log log = LogFactory
			.getLog(PaginationInterceptor_bak.class);

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		BoundSql boundSql = statementHandler.getBoundSql();
		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler, null, null, null);
		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}
		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());
		} catch (BussinessException e) {
		  //TODO 异常捕获未实现
			
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));
		}
		Dialect dialect = null;
		switch (databaseType) {
			case DB2:
				dialect = new Db2Dialect();
			case MYSQL:
				dialect = new MySql5Dialect();
			case ORACLE:
				dialect = new OracleDialect();
		}

		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");
		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getLimitString(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		
		// 设置这2个参数  不需要mybaits 的内存分页了 
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
		// 设置这2个参数  不需要mybaits 的内存分页了
		
		if (log.isDebugEnabled()) {
			log.debug("ohandPaginationSql_生成分页SQL : " + boundSql.getSql());
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {  
			   return Plugin.wrap(target, this);  
		} else {  
			  return target;  
		}
	}

	public void setProperties(Properties properties) {
	}

}
