package com.ohand.ohandApi;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.spring.filter.LoginFilter;

@EnableZuulProxy
@SpringCloudApplication  //整合了@SpringBootApplication、@EnableDiscoveryClient、@EnableCircuitBreaker，主要目的还是简化配置
public class OhandApiApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public LoginFilter loginFilter() {
		return new LoginFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(OhandApiApplication.class, args);
	}
}
