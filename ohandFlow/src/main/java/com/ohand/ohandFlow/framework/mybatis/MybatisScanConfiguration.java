package com.ohand.ohandFlow.framework.mybatis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
@MapperScan(basePackages = { "com.ohand.ohandFlow.mapper" }, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisScanConfiguration {
	protected static Log log = LogFactory.getLog(MybatisScanConfiguration.class);

	public MybatisScanConfiguration() {
		log.info("*************************MybatisScanConfiguration***********************");
	}
}
