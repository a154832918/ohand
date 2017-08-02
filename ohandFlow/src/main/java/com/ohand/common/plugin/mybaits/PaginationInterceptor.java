package com.ohand.common.plugin.mybaits;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import com.ohand.common.exception.BussinessException;
import com.ohand.ohandFlow.common.PFConstant;


/**
 * @author Richard
 * @Emial a154832918@163.com
 * @version 2013-11-20
 * @Function:
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	private final static Log log = LogFactory
			.getLog(PaginationInterceptor.class);
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	private static final String pageRegexId = PFConstant.SQL_PAGE_ENDSTRING;
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(
				statementHandler, DEFAULT_OBJECT_FACTORY,
				DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		// 分离代理对象链(由于目标类可能被多个拦截器拦截，从而形成多次代理，通过下面的两次循环可以分离出最原始的的目标类)
		while (metaStatementHandler.hasGetter("h")) {
			Object object = metaStatementHandler.getValue("h");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		}
		// 分离最后一个代理对象的目标类
		while (metaStatementHandler.hasGetter("target")) {
			Object object = metaStatementHandler.getValue("target");
			metaStatementHandler = MetaObject.forObject(object,
					DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		}
		// 获取数据库类型 DB2
		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");
		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());
		} catch (BussinessException e) {
			// TODO 异常捕获未实现
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in mybaits_configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));
		}
		// 获取数据库类型 DB2
		MappedStatement mappedStatement = (MappedStatement) metaStatementHandler
				.getValue("delegate.mappedStatement");
		
		BoundSql boundSql = (BoundSql) metaStatementHandler
				.getValue("delegate.boundSql");
		
//		System.out.println("原始SQL : "+ boundSql.getSql());
		
		if (mappedStatement.getId().matches(pageRegexId)) {

			Object parameterObject = boundSql.getParameterObject();
			if (parameterObject == null) {
				throw new NullPointerException(
						"select operate ,but parameterObject is null!");
			} else {
				String originalSql = (String) boundSql.getSql();
				Map paramMap = (HashMap) parameterObject;
				Dialect dialect = null;
				switch (databaseType) {
				case DB2:
					dialect = new Db2Dialect();
					break;
				case MYSQL:
					dialect = new MySql5Dialect();
					break;
				case ORACLE:
					dialect = new OracleDialect();
					break;
				}
				metaStatementHandler
						.setValue(
								"delegate.boundSql.sql",
								dialect.getLimitString(
										originalSql,
										((new Integer((String) paramMap
												.get("pageIndex"))).intValue() - 1)
												* (new Integer(
														(String) paramMap
																.get("pageSize"))),
										(new Integer((String) paramMap
												.get("pageSize"))).intValue()));
				// 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
				metaStatementHandler.setValue("delegate.rowBounds.offset",
						RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit",
						RowBounds.NO_ROW_LIMIT);
				
				System.out.println("ohandPaginationSql_生成分页SQL : "
						+ boundSql.getSql());	
				
				if (log.isDebugEnabled()) {
//					log.debug("ohandPaginationSql_生成分页SQL : "
//							+ boundSql.getSql());
//					System.out.println("ohandPaginationSql_传入查询参数 :"+paramMap.toString());

				}
			}
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
