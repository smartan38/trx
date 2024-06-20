package com.truxx.seller_v1.service;

import java.util.List;

import com.truxx.seller_v1.entity.Product;


public interface ProductService {
	 public Product createProduct(Product product);
	   public List<Product> getAllProduct();
	   public Product getProductById(int id);
	   public Product updateProduct(Product product);
	   public List<Product> getAllProductOfSeller(int sellerId);
}

