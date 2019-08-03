package com.wctf.task.go.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wctf.task.go.dao.UserMapper;
import com.wctf.task.go.model.User;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	public User getUserByCode(String code) {
		return userMapper.getUserByCode(code);
	}
}
