package com.truxx.user_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.truxx.user_v1.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

}
