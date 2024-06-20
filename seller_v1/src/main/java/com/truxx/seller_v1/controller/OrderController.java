package com.truxx.seller_v1.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.seller_v1.entity.OrderItems;
import com.truxx.seller_v1.feignclient.SellerFeignClientInterface;

@RestController
@RequestMapping("/seller-orders")
public class OrderController {
	@Autowired
	SellerFeignClientInterface sfci;
	@GetMapping("/{sellerId}")
	 public ResponseEntity<List<OrderItems>> viewSellerOrders(@PathVariable int sellerId){
		List<OrderItems> sellerOrders = sfci.viewSellerOrders(sellerId).getBody();
		Collections.reverse(sellerOrders);
		return new ResponseEntity<List<OrderItems>>(sellerOrders,HttpStatus.OK);
	}
	
	@PutMapping("/{orderItemId}")
	  public ResponseEntity<OrderItems> updateOrder(@PathVariable int orderItemId){
		OrderItems updatedOrder = sfci.updateOrder(orderItemId).getBody();
		return new ResponseEntity<OrderItems>(updatedOrder,HttpStatus.OK);
	}
	
}
