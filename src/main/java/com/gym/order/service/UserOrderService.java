package com.gym.order.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			UserOrder userOrder = new UserOrder();
			userOrder.setUserId(userId);
			userOrder.setCoachId(coachId);
			userOrder.setContent(content);
			Date start = formatter.parse(startTime);
			Date end = formatter.parse(endTime);
			userOrder.setStartTime(start);
			userOrder.setEndTime(end);
			userOrder.setTime(new Date());
			userOrder.setStatus(0);
			userOrder.setState(0);
			dao.save(userOrder);
		} catch (Exception e) {
			logger.error("新增订单失败", e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public void commentOrder(int id, String comment, int level) {
		try {
			UserOrder userOrder = dao.findOne(id);
			userOrder.setComment(comment);
			userOrder.setLevel(level);
			dao.save(userOrder);
		} catch (Exception e) {
			logger.error("评价订单失败", e);
			throw new RuntimeException("评价订单失败", e);
		}
	}
	
	public void start(int id) {
		try {
			UserOrder userOrder = dao.findOne(id);
			userOrder.setState(1);
			dao.save(userOrder);
		} catch (Exception e) {
			logger.error("确认订单失败", e);
			throw new RuntimeException("确认订单失败", e);
		}
	}
	
	public List<UserOrder> list(int userId, int state, int page, int size){
		return dao.findOrderByUserId(new PageRequest(page, size), userId, state);
	}
	
	public int getCount(int userId, int state) {
		return dao.countByUserId(userId, state);
	}
	
}
