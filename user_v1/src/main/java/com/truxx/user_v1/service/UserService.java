package com.truxx.user_v1.service;

import java.util.List;

import com.truxx.user_v1.entity.User;

public interface UserService {
   public User createUser(User user);
   public List<User> getAllUser();
   public User getUserById(int id);
   public User updateUser(User user);
}
