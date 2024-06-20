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

import com.truxx.seller_v1.entity.Seller;
import com.truxx.seller_v1.serviceImpl.SellerServiceImpl;

@RestController
@RequestMapping("/sellers")
public class SellerController {
	@Autowired
	SellerServiceImpl sellerService;
	@PostMapping()
	public ResponseEntity<Seller> createSeller(@RequestBody Seller seller){
		Seller createdSeller =sellerService.createSeller(seller);
		return new ResponseEntity<Seller>(createdSeller,HttpStatus.CREATED);
	}
	
	@GetMapping()
	public ResponseEntity<List<Seller>> getAllSeller(){
		List<Seller> allSellers =sellerService.getAllSeller();
		return new ResponseEntity<List<Seller>>(allSellers,HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable int id){
	Seller sellerById =sellerService.getSellerById(id);
		return new ResponseEntity<Seller>(sellerById,HttpStatus.OK);
	}
	
   @PutMapping()
   public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller){
	   Seller mySeller =sellerService.updateSeller(seller);
	   return new ResponseEntity<Seller>(mySeller,HttpStatus.CREATED);
   }
}
