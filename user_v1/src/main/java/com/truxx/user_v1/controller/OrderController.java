package com.truxx.user_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.user_v1.entity.Cart;

import com.truxx.user_v1.entity.OrderItems;
import com.truxx.user_v1.entity.Orders;
import com.truxx.user_v1.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	OrderServiceImpl orderService;
	


	@PostMapping("/{userId}")
	  public ResponseEntity<Orders> placeOrder(@PathVariable int userId){
		Orders placedOrder = orderService.placeOrder(userId);
		return new ResponseEntity<Orders>(placedOrder,HttpStatus.CREATED);
	}
	@PutMapping("/{orderItemId}")
	  public ResponseEntity<OrderItems> updateOrder(@PathVariable int orderItemId){
		OrderItems updatedOrder = orderService.updateOrder(orderItemId);
		return new ResponseEntity<OrderItems>(updatedOrder,HttpStatus.OK);
	}
	@GetMapping("/{userId}/{orderItemId}")
	 public ResponseEntity<OrderItems> viewOrder(@PathVariable int userId,@PathVariable int orderItemId){
		OrderItems orderItem = orderService.viewOrder(userId, orderItemId);
		return new ResponseEntity<OrderItems>(orderItem,HttpStatus.OK);
	}
	@GetMapping()
	 public ResponseEntity<List<OrderItems>> viewAllOrders(){
		List<OrderItems> orderItem = orderService.viewAllOrders();
		return new ResponseEntity<List<OrderItems>>(orderItem,HttpStatus.OK);
	}
	@GetMapping("/{userId}")
	 public ResponseEntity<List<OrderItems>> viewOrderByUserId(@PathVariable int userId){
		List<OrderItems> orderItem = orderService.viewAllOrderByUserId(userId);
		return new ResponseEntity<List<OrderItems>>(orderItem,HttpStatus.OK);
	}
	@GetMapping("/seller-orders/{sellerId}")
	 public ResponseEntity<List<OrderItems>> viewSellerOrders(@PathVariable int sellerId){
		List<OrderItems> orderItem = orderService.viewAllOrderBySellerId(sellerId);
		return new ResponseEntity<List<OrderItems>>(orderItem,HttpStatus.OK);
	}
}
