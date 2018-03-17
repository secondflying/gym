package com.gym.user.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.club.dao.ClubDao;
import com.gym.common.dao.ImageDao;
import com.gym.order.dao.UserOrderDao;
import com.gym.order.entity.UserOrder;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.CoachDao;
import com.gym.user.dto.CoachDto;
import com.gym.user.dto.TimeSlot;
import com.gym.user.entity.Coach;
import com.gym.user.entity.UserInfo;
import com.gym.util.DateUtil;

@Service
@Transactional
public class CoachService {
	
	Logger logger = LoggerFactory.getLogger(CoachService.class);
	
	@Autowired
	private CoachDao dao;
	
	@Autowired
	private ImageDao imagedao;
	
	@Autowired
	private ClubDao clubDao;
	
	@Autowired
	private UserOrderDao userOrderDao;

	private static final String ImageCate = "coach";
	
	public Coach register(CoachDto dto) {
		try {
			Coach coach = new Coach();
			coach.setName(dto.getName());
			coach.setAge(dto.getAge());
			coach.setPhone(dto.getPhone());
			coach.setHeight(dto.getHeight());
			coach.setBrief(dto.getBrief());
			coach.setSex(dto.getSex());
			coach.setHourcost(dto.getHourcost());
			coach.setStatus(0);
			coach.setClubid(-1);
			coach.setState(0);
			coach.setTime(new Date());
			return dao.save(coach);
		} catch (Exception e) {
			logger.error("注册教练失败", e);
			throw new RuntimeException("注册教练失败", e);
		}
	}
	
	public void save(Coach coach) {
		try {
			coach.setStatus(0);
			coach.setState(1);
			coach.setClubid(-1);
			coach.setTime(new Date());
			dao.save(coach);
		} catch (Exception e) {
			logger.error("新增教练失败", e);
			throw new RuntimeException("新增教练失败", e);
		}
	}
	
	public Coach detail(int id) {
		try {
			Coach coach = dao.findOne(id);
			coach.setImages(imagedao.getOfImages(id, ImageCate));
			coach.setClub(clubDao.findOne(coach.getClubid()));
			List<TimeSlot> today = this.todayOrder(id);
			List<TimeSlot> tomorrow = this.tomorrowOrder(id);
			coach.setToday(today);
			coach.setTomorrow(tomorrow);
			List<UserOrder> orders = this.userOrderDao.findOrderHasComment(id);
			for(UserOrder uo : orders) {
				UserInfo user = uo.getUser();
				user.setImages(imagedao.getOfImages(user.getId(), "user"));
			}
			coach.setOrders(orders);
			return coach;
		} catch (Exception e) {
			logger.error("获取教练详情失败", e);
			throw new RuntimeException("获取教练详情失败", e);
		}
	}
	
	public List<TimeSlot> todayOrder(int id) {
		Calendar s = Calendar.getInstance();
		s.set(Calendar.HOUR_OF_DAY, 1);
        s.set(Calendar.MINUTE, 0);
        s.set(Calendar.SECOND, 0);
        Date start = s.getTime();
        Calendar e = Calendar.getInstance();
		e.set(Calendar.HOUR_OF_DAY, 23);
        e.set(Calendar.MINUTE, 0);
        e.set(Calendar.SECOND, 0);
        Date end = e.getTime();
		List<UserOrder> orders = this.userOrderDao.findOrderByTime(id, start, end);
		List<TimeSlot> list = new ArrayList<TimeSlot>();
		for(UserOrder order: orders) {
			TimeSlot timeSlot = new TimeSlot();
			Date startTime = order.getStartTime();
			timeSlot.setSlot(DateUtil.getHour(startTime));
			timeSlot.setIsbusy(true);
			list.add(timeSlot);
		}
		return list;
	}
	
	public List<TimeSlot> tomorrowOrder(int id) {
		Calendar s = Calendar.getInstance();
		s.set(Calendar.HOUR_OF_DAY, 1);
        s.set(Calendar.MINUTE, 0);
        s.set(Calendar.SECOND, 0);
        int day = s.get(Calendar.DATE); 
        s.set(Calendar.DATE, day + 1);
        Date start = s.getTime();
        Calendar e = Calendar.getInstance();
		e.set(Calendar.HOUR_OF_DAY, 23);
        e.set(Calendar.MINUTE, 0);
        e.set(Calendar.SECOND, 0);
        int day1 = e.get(Calendar.DATE); 
        e.set(Calendar.DATE, day1 + 1);
        Date end = e.getTime();
		List<UserOrder> orders = this.userOrderDao.findOrderByTime(id, start, end);
		List<TimeSlot> list = new ArrayList<TimeSlot>();
		for(UserOrder order: orders) {
			TimeSlot timeSlot = new TimeSlot();
			Date startTime = order.getStartTime();
			timeSlot.setSlot(DateUtil.getHour(startTime));
			timeSlot.setIsbusy(true);
			list.add(timeSlot);
		}
		return list;
	}
	
	
	public Page<Coach> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<Coach> results = dao.findAll(new QuerySpecification<Coach>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
	
	public void delete(int id) {
		try {
			Coach coach = dao.findOne(id);
			coach.setStatus(-1);
			dao.save(coach);
		} catch (Exception e) {
			logger.error("删除教练失败", e);
			throw new RuntimeException("删除教练失败", e);
		}
	}
	
	public void coachsToClub(int clubId, String coachIds) {
		try {
			List<Coach> list = dao.findByClubId(clubId);
			for(Coach c: list) {
				c.setClubid(-1);
				dao.save(c);
			}
			if(coachIds != "") {
				String[] arr = coachIds.split(",");
				if(arr.length > 0) {
					for(String coachId: arr) {
						Coach coach = dao.findOne(Integer.parseInt(coachId));
						coach.setClubid(clubId);
						dao.save(coach);
					}
				}
			}
		} catch (NumberFormatException e) {
			logger.error("分配教练到健身房失败", e);
			throw new RuntimeException("分配教练到健身房失败", e);
		}
	}
	
	public void check(int coachId, int state, String reason) {
		try {
			Coach coach = dao.findOne(coachId);
			coach.setState(state);
			coach.setReason(reason);
			dao.save(coach);
		} catch (Exception e) {
			logger.error("审核教练失败", e);
			throw new RuntimeException("审核教练失败", e);
		}
	}
	
}
