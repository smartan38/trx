package com.truxx.admin_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.admin_v1.entity.OrderItems;
import com.truxx.admin_v1.feignclient.Admin_UserFeignClientInterface;

@RestController
@RequestMapping("/admin/orders")
public class OrderController {
	@Autowired
	Admin_UserFeignClientInterface aufci;

	@PutMapping("/{orderItemId}")
	public ResponseEntity<OrderItems> updateOrder(@PathVariable int orderItemId) {
		OrderItems updatedOrder = aufci.updateOrder(orderItemId).getBody();
		return new ResponseEntity<OrderItems>(updatedOrder, HttpStatus.OK);
	}

	@GetMapping("/{userId}/{orderItemId}")
	public ResponseEntity<OrderItems> viewOrder(@PathVariable int userId, @PathVariable int orderItemId) {
		OrderItems orderItem = aufci.viewOrder(userId, orderItemId).getBody();
		return new ResponseEntity<OrderItems>(orderItem, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<OrderItems>> viewAllOrders() {
		List<OrderItems> orderItem = aufci.viewAllOrders().getBody();
		return new ResponseEntity<List<OrderItems>>(orderItem, HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<List<OrderItems>> viewOrderByUserId(@PathVariable int userId) {
		List<OrderItems> orderItem = aufci.viewOrderByUserId(userId).getBody();
		return new ResponseEntity<List<OrderItems>>(orderItem, HttpStatus.OK);
	}

	@GetMapping("/seller-orders/{sellerId}")
	public ResponseEntity<List<OrderItems>> viewSellerOrders(@PathVariable int sellerId) {
		List<OrderItems> orderItem = aufci.viewSellerOrders(sellerId).getBody();
		return new ResponseEntity<List<OrderItems>>(orderItem, HttpStatus.OK);
	}
}
