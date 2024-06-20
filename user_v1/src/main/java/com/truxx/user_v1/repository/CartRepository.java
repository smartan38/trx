package com.truxx.user_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxx.user_v1.entity.Cart;
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
