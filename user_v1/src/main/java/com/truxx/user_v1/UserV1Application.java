package com.truxx.user_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserV1Application {

	public static void main(String[] args) {
		SpringApplication.run(UserV1Application.class, args);
	}

}
