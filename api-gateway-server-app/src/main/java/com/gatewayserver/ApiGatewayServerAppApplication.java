package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiGatewayServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayServerAppApplication.class, args);
	}

}
