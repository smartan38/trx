package com.truxx.seller_v1.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.truxx.seller_v1.entity.OrderItems;


@FeignClient("USER-SERVICE")
public interface SellerFeignClientInterface {
	@GetMapping("/orders/seller-orders/{sellerId}")
	 public ResponseEntity<List<OrderItems>> viewSellerOrders(@PathVariable int sellerId);
	@PutMapping("/orders/{orderItemId}")
	  public ResponseEntity<OrderItems> updateOrder(@PathVariable int orderItemId);
}