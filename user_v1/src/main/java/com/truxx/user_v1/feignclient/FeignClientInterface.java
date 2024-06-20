package com.truxx.user_v1.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.truxx.user_v1.entity.Product;



@FeignClient("SELLER-SERVICE")
public interface FeignClientInterface {
	
	
    @GetMapping("/products/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id);
 	
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts();
}
