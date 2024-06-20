package com.truxx.seller_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SellerV1Application {

	public static void main(String[] args) {
		SpringApplication.run(SellerV1Application.class, args);
	}

}
