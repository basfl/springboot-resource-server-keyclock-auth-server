package com.album.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AlbumResourceServerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumResourceServerAppApplication.class, args);
	}

}
