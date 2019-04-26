package com.task.miracletask.service.user;

import java.util.List;

import com.task.miracletask.entity.User;

public interface UserService {

	public List<User> getAllUsers();
	
	public User findUserByPhone(String phone);
}
