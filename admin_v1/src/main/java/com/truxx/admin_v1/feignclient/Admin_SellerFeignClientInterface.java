package com.truxx.admin_v1.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.truxx.admin_v1.entity.Product;
import com.truxx.admin_v1.entity.Seller;

// seller and products services here

@FeignClient("SELLER-SERVICE")
public interface Admin_SellerFeignClientInterface {
	@GetMapping("/sellers")
	public ResponseEntity<List<Seller>> getAllSeller();
	@GetMapping("/sellers/id/{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable int id);
	
	//error
//   @PutMapping("/sellers")
//   public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller);
	
	
	
	//for products
    @GetMapping("/products/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id);
    
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts();
    @GetMapping("/products/seller/{id}")
    public ResponseEntity<List<Product>> getAllProductsOfSeller(@PathVariable int id);
}
