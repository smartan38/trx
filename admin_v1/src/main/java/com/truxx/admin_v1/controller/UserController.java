package com.truxx.admin_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.admin_v1.entity.User;
import com.truxx.admin_v1.feignclient.Admin_UserFeignClientInterface;



@RestController
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	Admin_UserFeignClientInterface afci;
    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUser(){
    	List<User> allUser = afci.getAllUser().getBody();
    	return new ResponseEntity<List<User>>(allUser,HttpStatus.OK);
    	
    }
    @GetMapping("/user-details/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id){
		User userById=afci.getUserById(id).getBody();
		return new ResponseEntity<User>(userById,HttpStatus.OK);
	}
    @PutMapping()
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User updatedUser=afci.updateUser(user).getBody();
		return new ResponseEntity<User>(updatedUser,HttpStatus.CREATED);
	}
}
