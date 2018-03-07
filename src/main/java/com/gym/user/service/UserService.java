package com.gym.user.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.common.dao.ImageDao;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.UserDao;
import com.gym.user.dto.UserDto;
import com.gym.user.entity.Coach;
import com.gym.user.entity.User;

@Service
@Transactional
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private ImageDao imagedao;
	
	private static final String ImageCate = "user";
	
	public User login(UserDto dto) {
		try {
			List<User> users = dao.findByPhone(dto.getPhone());
			if(users.size() == 0) { //未登陆过
				User user = new User();
				user.setAge(dto.getAge());
				user.setBrief(dto.getBrief());
				user.setHeight(dto.getHeight());
				user.setName(dto.getName());
				user.setNickname(dto.getNickname());
				user.setPhone(dto.getPhone());
				user.setSex(dto.getSex());
				user.setStatus(0);
				user.setTime(new Date());
				dao.save(user);
				return user;
			}else {
				User user = users.get(0);
				user.setTime(new Date());
				dao.save(user);
				return user;
			}
		} catch (Exception e) {
			logger.error("用户登录异常", e);
			throw new RuntimeException("用户登录异常", e);
		}
	}
	
	public User findByPhone(String phone) {
		try {
			List<User> users = dao.findByPhone(phone);
			if(users.size() == 0) {
				return null;
			}else {
				User user = users.get(0);
				user.setImages(imagedao.getOfImages(user.getId(), ImageCate));
				return user;
			}
		} catch (Exception e) {
			logger.error("获取用户详情失败", e);
			throw new RuntimeException("获取用户详情失败", e);
		}
	}
	
	public Page<User> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<User> results = dao.findAll(new QuerySpecification<User>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
	
	public void delete(int id) {
		try {
			User user = dao.findOne(id);
			user.setStatus(-1);
			dao.save(user);
		} catch (Exception e) {
			logger.error("删除用户失败", e);
			throw new RuntimeException("删除用户失败", e);
		}
	}
}
