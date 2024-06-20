package com.truxx.user_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.truxx.user_v1.entity.Cart;
import com.truxx.user_v1.entity.Product;
import com.truxx.user_v1.serviceImpl.CartServiceImpl;



@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	CartServiceImpl cartService;
	
	@PostMapping("/{userId}")
	  public ResponseEntity<Cart> addToCart(@RequestBody Product product,@PathVariable int userId){
		Cart createdCart = cartService.addToCart(userId, product);
		return new ResponseEntity<Cart>(createdCart,HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
		 public ResponseEntity<Cart> getUserCart(@PathVariable int userId){
				Cart userCart = cartService.getUserCart(userId);
				return new ResponseEntity<Cart>(userCart,HttpStatus.OK);
			}
	@GetMapping("/all-carts")
	 public ResponseEntity<List<Cart>> getAllCarts(){
			List<Cart> carts = cartService.getAllCarts();
			return new ResponseEntity<List<Cart>>(carts,HttpStatus.OK);
		}
	@DeleteMapping("/{userId}")
	 public ResponseEntity<Cart> removeProductFromCart(@RequestBody Product product,@PathVariable int userId){
		Cart latestCart = cartService.removeProductFromCart(userId, product);
		return new ResponseEntity<Cart>(latestCart,HttpStatus.OK);
	}
	@DeleteMapping("/cart-items/{userId}")
	 public ResponseEntity<Cart> removeAllProduct(@PathVariable int userId){
		Cart emptyCart = cartService.removeAllProduct(userId);
		return new ResponseEntity<Cart>(emptyCart,HttpStatus.OK);
	}
	@PutMapping("/increment/{userId}")
	 public ResponseEntity<Cart> incrementProductFromCart(@RequestBody Product product,@PathVariable int userId){
		Cart latestCart = cartService.incrementProductQuantity(userId, product);
		return new ResponseEntity<Cart>(latestCart,HttpStatus.OK);
	}
	@PutMapping("/decrement/{userId}")
	 public ResponseEntity<Cart> decrementProductFromCart(@RequestBody Product product,@PathVariable int userId){
		Cart latestCart = cartService.decrementProductQuantity(userId, product);
		return new ResponseEntity<Cart>(latestCart,HttpStatus.OK);
	}
}
