package com.task.miracletask.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.task.miracletask.entity.User;

@Mapper
public interface UserMapper {

	List<User> getAllUsers();
	
	User getUserByPhone(@Param("phone") String phone);
}
