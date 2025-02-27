package com.truxx.seller_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxx.seller_v1.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
