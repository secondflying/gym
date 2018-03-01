package com.gym.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.order.dao.UserOrderDao;

@Service
@Transactional
public class UserOrderService {

	Logger logger = LoggerFactory.getLogger(UserOrderService.class);
	
	@Autowired
	private UserOrderDao dao;
	
	
}
