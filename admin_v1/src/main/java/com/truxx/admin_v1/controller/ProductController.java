package com.truxx.admin_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truxx.admin_v1.entity.Product;
import com.truxx.admin_v1.feignclient.Admin_SellerFeignClientInterface;



@RestController
@RequestMapping("/admin/products")
public class ProductController {

	@Autowired
	Admin_SellerFeignClientInterface asfci;
	
    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){
 	   Product productById =asfci.getProductById(id).getBody();
 	return new ResponseEntity<Product>(productById,HttpStatus.OK);   
    }
    
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
 	   List<Product> products =asfci.getAllProducts().getBody();
 	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   
    }
    
    @GetMapping("/seller/{id}")
    public ResponseEntity<List<Product>> getAllProductsOfSeller(@PathVariable int id){
 	   List<Product> products =asfci.getAllProductsOfSeller(id).getBody();
 	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   
    }
}
