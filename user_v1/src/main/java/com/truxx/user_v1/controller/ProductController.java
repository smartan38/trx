package com.truxx.user_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.user_v1.entity.Product;
import com.truxx.user_v1.feignclient.FeignClientInterface;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	FeignClientInterface fcI;
	
	 @GetMapping("/id/{id}")
	    
	    public ResponseEntity<Product> getProductById(@PathVariable int id){
	    	Product product = fcI.getProductById(id).getBody();
	    	return new ResponseEntity<Product>(product,HttpStatus.OK);
	    }
	   @GetMapping()
       public ResponseEntity<List<Product>> getAllProducts(){
    	   List<Product> products =fcI.getAllProducts().getBody();
    	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   
       }
}
