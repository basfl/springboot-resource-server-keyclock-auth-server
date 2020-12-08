package com.resourceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



//@EnableEurekaClient
@SpringBootApplication
@EnableEurekaClient
//@EnableDiscoveryClient

public class ResourceServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResourceServerAppApplication.class, args);
	}

}
