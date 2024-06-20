package com.truxx.user_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.user_v1.entity.Product;
import com.truxx.user_v1.entity.User;
import com.truxx.user_v1.feignclient.FeignClientInterface;
import com.truxx.user_v1.serviceImpl.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserServiceImpl userService;
	
	@Autowired
	FeignClientInterface fcI;
	
	@PostMapping()
	public ResponseEntity<User> creteUser(@RequestBody User user){
		User createdUser=userService.createUser(user);
		return new ResponseEntity<User>(createdUser,HttpStatus.CREATED);
	}
	
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
    	List<User> allUser = userService.getAllUser();
    	return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
    	
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
		User userById=userService.getUserById(id);
		return new ResponseEntity<User>(userById,HttpStatus.OK);
	}
    @PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updatedUser=userService.updateUser(user);
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);
	}
   
    
}
