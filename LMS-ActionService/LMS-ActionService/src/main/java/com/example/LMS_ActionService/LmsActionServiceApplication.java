package com.example.LMS_ActionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LmsActionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsActionServiceApplication.class, args);
	}

}
