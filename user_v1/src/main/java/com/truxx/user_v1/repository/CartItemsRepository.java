package com.truxx.user_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truxx.user_v1.entity.CartItems;
@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {

}
