package com.truxx.seller_v1.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxx.seller_v1.entity.Product;
import com.truxx.seller_v1.entity.Seller;
import com.truxx.seller_v1.repository.ProductRepository;
import com.truxx.seller_v1.repository.SellerRepository;
import com.truxx.seller_v1.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
     
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	SellerRepository sellerRepo;
	
	@Override
	public Product createProduct(Product product) {
		
		Product createdProduct =productRepo.save(product);
//		Seller seller =sellerRepo.findById(product.getSeller().getSellerId()).get();
//		sellerRepo.save(seller);
		return createdProduct;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> products =productRepo.findAll();
		return products;
	}

	@Override
	public Product getProductById(int id) {
		Optional<Product> productById = productRepo.findById(id);
		return productById.get();
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> myProduct = productRepo.findById(product.getProductId());
		Product myPro=null;
		if(myProduct.get()!=null) {
		myPro=	productRepo.save(product);
			}
		return myPro;
	}

	@Override
	public List<Product> getAllProductOfSeller(int sellerId) {
		List<Product> allPro=getAllProduct();
		List<Product> sellerProduct=new ArrayList<Product>();
		for(Product p:allPro) {
			if(p.getSeller().getSellerId()==sellerId) {
				sellerProduct.add(p);
			}
		}
		return sellerProduct;
	}

}
