package com.truxx.seller_v1.service;

import java.util.List;


import com.truxx.seller_v1.entity.Seller;


public interface SellerService {
	  public Seller createSeller(Seller seller);
	   public List<Seller> getAllSeller();
	   public Seller getSellerById(int id);
	   public Seller updateSeller(Seller seller);
}
