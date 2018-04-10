package com.gym.order.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.common.dao.ImageDao;
import com.gym.order.dao.UserOrderDao;
import com.gym.order.entity.UserOrder;
import com.gym.user.dao.CoachInfoDao;
import com.gym.user.dao.CoachLeaveDao;
import com.gym.user.entity.CoachInfo;
import com.gym.user.entity.CoachLeave;

@Service
@Transactional
public class UserOrderService {

	Logger logger = LoggerFactory.getLogger(UserOrderService.class);
	
	@Autowired
	private UserOrderDao dao;
	
	@Autowired
	private CoachInfoDao coachInfoDao;
	
	@Autowired
	private CoachLeaveDao leaveDao;
	
	@Autowired
	private ImageDao imagedao;
	
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
	
	/**
	 * 检查教练是否已约或者已请假
	 * 
	 * */
	public void checkCoachTime(int coachId, String startTime, String endTime) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date start = formatter.parse(startTime);
		Date end = formatter.parse(endTime);
		//检查教练在这个时间段是否请假
		List<CoachLeave> leaves = leaveDao.intersectTime(coachId, start, end);
		if(leaves.size() > 0) {
			logger.error("该时间段教练已请假");
			throw new RuntimeException("该时间段教练已请假");
		}
		//检查教练在这个时间段是否已约
		List<UserOrder> orders = dao.intersectTime(coachId, start, end);
		if(orders.size() > 0) {
			logger.error("该时间段已有预约");
			throw new RuntimeException("该时间段已有预约");
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
	
	public List<UserOrder> list(int userId, int page, int size){
		try {
			List<UserOrder> list = dao.findOrderByUserId(new PageRequest(page, size), userId);
			for(UserOrder order: list) {
				CoachInfo coach = coachInfoDao.findOne(order.getCoachId());
				coach.setImages(imagedao.getOfImages(order.getCoachId(), "coach"));
				order.setCoach(coach);
			}
			return list;
		} catch (Exception e) {
			logger.error("获取订单列表失败", e);
			throw new RuntimeException("获取订单列表失败", e);
		}
	}
	
	public int getCount(int userId) {
		return dao.countByUserId(userId);
	}
	
	public List<UserOrder> listOfState(int userId, int state, int page, int size){
		try {
			List<UserOrder> list = dao.findOrderByState(new PageRequest(page, size), userId, state);
			for(UserOrder order: list) {
				CoachInfo coach = coachInfoDao.findOne(order.getCoachId());
				coach.setImages(imagedao.getOfImages(order.getCoachId(), "coach"));
				order.setCoach(coach);
			}
			return list;
		} catch (Exception e) {
			logger.error("获取订单列表失败", e);
			throw new RuntimeException("获取订单列表失败", e);
		}
	}
	
	public int getCountOfState(int userId, int state) {
		return dao.countByState(userId, state);
	}
	
	public UserOrder detail(int id) {
		UserOrder order = dao.findOne(id);
		CoachInfo coach = coachInfoDao.findOne(order.getCoachId());
		coach.setImages(imagedao.getOfImages(order.getCoachId(), "coach"));
		order.setCoach(coach);
		return order;
	}
	
}
