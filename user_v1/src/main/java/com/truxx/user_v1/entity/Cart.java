package com.truxx.user_v1.entity;


import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
      private int cartId;
	
	 @OneToMany( cascade = CascadeType.ALL)
	    private List<CartItems> cartItem=new ArrayList<>();
	 
	 private double totalPrice;
	 @JsonIgnore
	 @OneToOne
	 private User user;
}
