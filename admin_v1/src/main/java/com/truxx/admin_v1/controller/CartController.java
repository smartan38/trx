package com.truxx.admin_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.admin_v1.entity.Cart;
import com.truxx.admin_v1.feignclient.Admin_UserFeignClientInterface;


@RestController
@RequestMapping("/admin/carts")
public class CartController {
  @Autowired
  Admin_UserFeignClientInterface aufci;
	
	@GetMapping("/{userId}")
	 public ResponseEntity<Cart> getUserCart(@PathVariable int userId){
			Cart userCart = aufci.getUserCart(userId).getBody();
			return new ResponseEntity<Cart>(userCart,HttpStatus.OK);
		}
	@GetMapping("/all-carts")
	 public ResponseEntity<List<Cart>> getAllCarts(){
			List<Cart> carts = aufci.getAllCarts().getBody();
			return new ResponseEntity<List<Cart>>(carts,HttpStatus.OK);
		}
}
