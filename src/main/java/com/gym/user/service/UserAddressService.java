package com.gym.user.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.user.dao.UserAddressDao;
import com.gym.user.entity.UserAddress;

@Service
@Transactional
public class UserAddressService {

	Logger logger = LoggerFactory.getLogger(UserAddressService.class);
	
	@Autowired
	private UserAddressDao dao;
	
	public List<UserAddress> findByUserId(int userId) {
		try {
			List<UserAddress> list = dao.findByUserId(userId);
			return list;
		} catch (Exception e) {
			logger.error("获取用户地址失败", e);
			throw new RuntimeException("获取用户地址失败", e);
		}
	}
	
	public void save(int userId, String address) {
		try {
			UserAddress userAddress = new UserAddress();
			userAddress.setUserId(userId);
			userAddress.setAddress(address);
			userAddress.setTime(new Date());
			dao.save(userAddress);
		} catch (Exception e) {
			logger.error("新增用户地址失败", e);
			throw new RuntimeException("新增用户地址失败", e);
		}
	}
	
	public void update(int id, String address) {
		try {
			UserAddress userAddress = dao.findOne(id);
			userAddress.setAddress(address);
			userAddress.setTime(new Date());
			dao.save(userAddress);
		} catch (Exception e) {
			logger.error("编辑用户地址失败", e);
			throw new RuntimeException("编辑用户地址失败", e);
		}
	}
	
	public void delete(int id) {
		try {
			dao.delete(id);
		} catch (Exception e) {
			logger.error("删除用户地址失败", e);
			throw new RuntimeException("删除用户地址失败", e);
		}
	}
}
