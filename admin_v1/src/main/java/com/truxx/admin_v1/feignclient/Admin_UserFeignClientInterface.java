package com.truxx.admin_v1.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.truxx.admin_v1.entity.Cart;
import com.truxx.admin_v1.entity.OrderItems;
import com.truxx.admin_v1.entity.User;



// user , order and cart services here

@FeignClient("USER-SERVICE")

public interface Admin_UserFeignClientInterface {
	
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser();
    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id);
    @PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user);
    
    //CART SERVICES
    @GetMapping("/carts/{userId}")
	 public ResponseEntity<Cart> getUserCart(@PathVariable int userId);
    @GetMapping("/carts/all-carts")
	 public ResponseEntity<List<Cart>> getAllCarts();
    //ORDERS SERVICES
    
    @PutMapping("/orders/{orderItemId}")
	  public ResponseEntity<OrderItems> updateOrder(@PathVariable int orderItemId);
	@GetMapping("/orders/{userId}/{orderItemId}")
	 public ResponseEntity<OrderItems> viewOrder(@PathVariable int userId,@PathVariable int orderItemId);
	@GetMapping("/orders")
	 public ResponseEntity<List<OrderItems>> viewAllOrders();
	@GetMapping("/orders/{userId}")
	 public ResponseEntity<List<OrderItems>> viewOrderByUserId(@PathVariable int userId);
	@GetMapping("/orders/seller-orders/{sellerId}")
	 public ResponseEntity<List<OrderItems>> viewSellerOrders(@PathVariable int sellerId);
}
