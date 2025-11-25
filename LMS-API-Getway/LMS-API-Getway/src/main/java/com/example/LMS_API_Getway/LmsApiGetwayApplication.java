package com.example.LMS_API_Getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LmsApiGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApiGetwayApplication.class, args);
	}

}
