package com.photoserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PhotoResourceServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoResourceServerAppApplication.class, args);
	}

}
