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
import lombok.Data;
@Entity
@Data
public class Seller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int sellerId;
	    private String seller_Name;
	    private String contactPersonName;
	    private String email;
	    private String phoneNumber;
	    private String address;
	    private String city;
	    private String state;
	    private String country;
	    private String postalCode;
	    private String password;
	    @JsonIgnore
	    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<Product> products = new ArrayList<>();
}
