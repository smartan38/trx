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

import com.truxx.admin_v1.entity.Seller;
import com.truxx.admin_v1.feignclient.Admin_SellerFeignClientInterface;


@RestController
@RequestMapping("/admin/sellers")
public class SellerController {
	@Autowired
	Admin_SellerFeignClientInterface asfci;
	@GetMapping()
	public ResponseEntity<List<Seller>> getAllSeller(){
		List<Seller> allSellers =asfci.getAllSeller().getBody();
		return new ResponseEntity<List<Seller>>(allSellers,HttpStatus.OK);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<Seller> getSellerById(@PathVariable int id){
	Seller sellerById =asfci.getSellerById(id).getBody();
		return new ResponseEntity<Seller>(sellerById,HttpStatus.OK);
	}
	
	  //error
//   @PutMapping("/update")
//   public ResponseEntity<Seller> updateSeller(@RequestBody Seller seller){
//	   Seller mySeller =asfci.updateSeller(seller).getBody();
//	   return new ResponseEntity<Seller>(mySeller,HttpStatus.CREATED);
//   }
}
