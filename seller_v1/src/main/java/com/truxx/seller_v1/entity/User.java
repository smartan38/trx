package com.truxx.seller_v1.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String user_name;
    private String user_mobile;
    private String user_email;
    private String user_password;
    private String user_role;
}

