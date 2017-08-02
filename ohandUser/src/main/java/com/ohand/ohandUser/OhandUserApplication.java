package com.ohand.ohandUser;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication
@Configuration
@EnableScheduling
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan()
@ServletComponentScan
@EnableAsync
@PropertySources({ @PropertySource("classpath:config/application-jdbc.properties") })
public class OhandUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhandUserApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
	            System.out.println("Let's inspect the beans provided by Spring Boot:");
	            String[] beanNames = ctx.getBeanDefinitionNames();
	            Arrays.sort(beanNames);
	            for (String beanName : beanNames) {
	               //  System.out.println(beanName);
	            }
	        };
	    }
	
}
