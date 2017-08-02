package com.ohand.ohandRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class OhandRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(OhandRegistryApplication.class, args);
	}
	
}
