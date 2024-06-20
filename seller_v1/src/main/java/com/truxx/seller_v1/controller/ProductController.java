package com.truxx.seller_v1.controller;

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

import com.truxx.seller_v1.entity.Product;
import com.truxx.seller_v1.serviceImpl.ProductServiceImpl;

@RestController
@RequestMapping("/products")
public class ProductController {
       @Autowired
       ProductServiceImpl productService;
       
       @PostMapping()
       public ResponseEntity<Product> createProduct(@RequestBody Product product){
    	   Product createdProduct =productService.createProduct(product);
    	return new ResponseEntity<Product>(createdProduct,HttpStatus.CREATED);   
       }
       @GetMapping("/id/{id}")
       public ResponseEntity<Product> getProductById(@PathVariable int id){
    	   Product productById =productService.getProductById(id);
    	return new ResponseEntity<Product>(productById,HttpStatus.OK);   
       }
       
       @GetMapping()
       public ResponseEntity<List<Product>> getAllProducts(){
    	   List<Product> products =productService.getAllProduct();
    	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   
       }
       
       @GetMapping("/seller/{id}")
       public ResponseEntity<List<Product>> getAllProductsOfSeller(@PathVariable int id){
    	   List<Product> products =productService.getAllProductOfSeller(id);
    	return new ResponseEntity<List<Product>>(products,HttpStatus.OK);   
       }
       
       
       @PutMapping()
       public ResponseEntity<Product> updateProduct(@RequestBody Product product){
    	   Product updatedProduct =productService.updateProduct(product);
    	return new ResponseEntity<Product>(updatedProduct,HttpStatus.CREATED);   
       }
       
}
