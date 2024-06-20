package com.truxx.user_v1.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 private int orderItemId;
 private LocalDateTime date;
 private double orderAmount;
 private String orderStatus;
 @ManyToOne
 private Product product;
 
 @JsonIgnore
 @ManyToOne
 private Orders order;
}
