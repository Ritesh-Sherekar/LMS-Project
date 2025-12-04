package com.example.LMS_SchedularService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LmsSchedularServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsSchedularServiceApplication.class, args);
	}

}
