package com.gym.user.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.gym.user.dao.UserAddressDao;
import com.gym.user.dao.UserDao;
import com.gym.user.dto.UserDto;
import com.gym.user.entity.User;

@Service
@Transactional
public class UserService {

	Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private UserAddressDao addressDao;
	
	@Autowired
	private ImageDao imagedao;
	
	private static final String ImageCate = "user";
	
	public void saveCode(String phone, String code) {
		List<User> users = dao.findByPhone(phone);
		if(users.size() == 0) { //未登陆过
			User user = new User();
			user.setCreateTime(new Date());
			user.setTime(new Date());
			user.setCode(code);
			user.setPhone(phone);
			dao.save(user);
		}else {
			User user = users.get(0);
			user.setTime(new Date());
			user.setCode(code);
			dao.save(user);
		}
	}
	
	/**
	 * 查验证码是否正确和过期，五分钟时间限制
	 * 
	 * */
	public boolean checkCode(String phone, String code) {
		try {
			boolean status = true;
			List<User> users = dao.findByPhone(phone);
			User user = users.get(0);
			if(!user.getCode().equals(code)) {
				status = false;
			}else{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date d = new Date();
				String currentTime = df.format(d);
				Date time = user.getTime();
				String codeTime = df.format(time);
				long time1 = df.parse(currentTime).getTime();  
				long time2 = df.parse(codeTime).getTime();
				int minutes = (int) ((time1 - time2)/(1000 * 60)); 
				if(minutes > 5) {
					status = false;
				}
			}
			return status;
		} catch (Exception e) {
			logger.error("用户登录异常", e);
			throw new RuntimeException("用户登录异常", e);
		}
	}
	
	public User getByPhone(String phone) {
		List<User> users = dao.findByPhone(phone);
		User user = users.get(0);
		user.setImages(imagedao.getOfImages(user.getId(), ImageCate));
		user.setAddresses(addressDao.findByUserId(user.getId()));
		return user;
	}
	
	public User addinfo(UserDto dto) {
		try {
			User user = dao.findOne(dto.getId());
			user.setAge(dto.getAge());
			user.setBrief(dto.getBrief());
			user.setHeight(dto.getHeight());
			user.setName(dto.getName());
			user.setNickname(dto.getNickname());
			user.setPhone(dto.getPhone());
			user.setSex(dto.getSex());
			user.setStatus(0);
			dao.save(user);
			return user;
		} catch (Exception e) {
			logger.error("保存用户资料异常", e);
			throw new RuntimeException("保存用户资料异常", e);
		}
	}
	
	public User detail(int id) {
		try {
			User user = dao.findOne(id);
			user.setImages(imagedao.getOfImages(user.getId(), ImageCate));
			user.setAddresses(addressDao.findByUserId(user.getId()));
			return user;
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
