package com.truxx.user_v1.service;

import java.util.List;


import com.truxx.user_v1.entity.OrderItems;
import com.truxx.user_v1.entity.Orders;

public interface OrderService {
       public Orders placeOrder(int userId);
       public OrderItems updateOrder(int orderItemId);
       public OrderItems viewOrder(int userId,int orderItemId);
       public List<OrderItems> viewAllOrders();
       public List<OrderItems> viewAllOrderByUserId(int userId);
       public List<OrderItems> viewAllOrderBySellerId(int sellerId);
       
//       
} 
