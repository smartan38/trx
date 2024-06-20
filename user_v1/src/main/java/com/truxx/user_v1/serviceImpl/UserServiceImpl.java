package com.truxx.user_v1.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truxx.user_v1.entity.User;
import com.truxx.user_v1.repository.UserRepository;
import com.truxx.user_v1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	  UserRepository userRepo;
	@Override
	public User createUser(User user) {
		User createduser = userRepo.save(user);
		return createduser;
	}
	@Override
	public List<User> getAllUser() {
		List<User> allUser =userRepo.findAll();
		return allUser;
	}
	@Override
	public User getUserById(int id) {
		User user = userRepo.findById(id).get();
		return user;
	}
	@Override
	public User updateUser(User user) {
		User myUser = userRepo.findById(user.getUser_id()).get();
		if(myUser!=null) {
		myUser=	userRepo.save(user);
		}
		return myUser;
	}

}
