package com.gym.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.club.entity.Club;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.CoachDao;
import com.gym.user.dto.CoachCreate;
import com.gym.user.entity.Coach;

@Service
@Transactional
public class CoachService {
	
	Logger logger = LoggerFactory.getLogger(CoachService.class);
	
	@Autowired
	private CoachDao dao;
	
	public Coach register(CoachCreate dto) {
		try {
			Coach coach = new Coach();
			coach.setName(dto.getName());
			coach.setStatus(0);
			return dao.save(coach);
		} catch (Exception e) {
			logger.error("注册教练失败", e);
			throw new RuntimeException("注册教练失败", e);
		}
	}
	
	public void save(Coach coach) {
		try {
			coach.setStatus(0);
			dao.save(coach);
		} catch (Exception e) {
			logger.error("注册教练失败", e);
			throw new RuntimeException("注册教练失败", e);
		}
	}
	
	public Coach detail(int id) {
		try {
			return dao.findOne(id);
		} catch (Exception e) {
			logger.error("获取教练详情失败", e);
			throw new RuntimeException("获取教练详情失败", e);
		}
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
}
