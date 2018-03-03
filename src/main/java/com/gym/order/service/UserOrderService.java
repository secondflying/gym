package com.gym.order.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.order.dao.UserOrderDao;
import com.gym.order.entity.UserOrder;

@Service
@Transactional
public class UserOrderService {

	Logger logger = LoggerFactory.getLogger(UserOrderService.class);
	
	@Autowired
	private UserOrderDao dao;
	
	/**
	 * 新增订单
	 * 
	 * */
	public void neworder(int userId, int coachId, String startTime, String endTime, String content) {
		try {
			UserOrder userOrder = new UserOrder();
			userOrder.setUserId(userId);
			userOrder.setCoachId(coachId);
			userOrder.setContent(content);
			long start = Long.parseLong(startTime);
			long end = Long.parseLong(endTime);
			userOrder.setStartTime(new Date(start));
			userOrder.setEndTime(new Date(end));
			userOrder.setTime(new Date());
			userOrder.setStatus(0);
			dao.save(userOrder);
		} catch (Exception e) {
			logger.error("新增订单失败", e);
			throw new RuntimeException("新增订单失败", e);
		}
	}
	
}
