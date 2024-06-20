package com.truxx.admin_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminV1Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminV1Application.class, args);
	}

}
