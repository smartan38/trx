package com.truxx.seller_v1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxx.seller_v1.entity.Seller;
import com.truxx.seller_v1.repository.SellerRepository;
import com.truxx.seller_v1.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {
    
	@Autowired
	SellerRepository sellerRepo;
	
	@Override
	public Seller createSeller(Seller seller) {
		Seller createdSeller =sellerRepo.save(seller);
		return createdSeller;
	}

	@Override
	public List<Seller> getAllSeller() {
		List<Seller> allSeller = sellerRepo.findAll();
		return allSeller;
	}

	@Override
	public Seller getSellerById(int id) {
	Seller sellerById = sellerRepo.findById(id).get();
	return sellerById;
	}

	@Override
	public Seller updateSeller(Seller seller) {
		Seller mySeller = sellerRepo.findById(seller.getSellerId()).get();
		if(mySeller!=null) {
			mySeller=sellerRepo.save(seller);
		}
		return mySeller;
	}

}
