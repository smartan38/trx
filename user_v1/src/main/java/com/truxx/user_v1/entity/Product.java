package com.truxx.user_v1.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
    private String name;
    private String description;
    private double price;
    private int quantity;
//    @JsonIgnore
    @ManyToOne
    private Seller seller;
}
