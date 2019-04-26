package com.task.miracletask.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.miracletask.dao.UserMapper;
import com.task.miracletask.entity.User;
import com.task.miracletask.service.user.UserService;


@Service
public class UserImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public List<User> getAllUsers () {
		List<User> users = userMapper.getAllUsers();
		// TODO Auto-generated method stub
		return users;
	}

	@Override
	public User findUserByPhone (String phone) {
		// TODO Auto-generated method stub
		User user = userMapper.getUserByPhone(phone);
	
		return user;
	}

}
