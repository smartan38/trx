package com.truxx.user_v1.service;

import java.util.List;

import com.truxx.user_v1.entity.Cart;
import com.truxx.user_v1.entity.Product;

public interface CartService {
      public Cart addToCart(int userId,Product product);
      public Cart getUserCart(int userId);
      public Cart removeProductFromCart(int userId,Product product);
      public Cart removeAllProduct(int userId);
      public Cart incrementProductQuantity(int userId,Product product);
      public Cart decrementProductQuantity(int userId,Product product); 
      public List<Cart> getAllCarts();
      
      
}
