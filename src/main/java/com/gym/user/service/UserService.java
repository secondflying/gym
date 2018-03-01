package com.gym.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.user.dao.UserDao;
import com.gym.user.dto.UserDto;
import com.gym.user.entity.User;

@Service
@Transactional
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao dao;
	
	private static final String ImageCate = "user";
	
	public User login(UserDto dto) {
		
		return null;
	}
}
