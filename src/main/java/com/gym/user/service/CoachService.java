package com.gym.user.service;

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
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.CoachDao;
import com.gym.user.dto.CoachDto;
import com.gym.user.entity.Coach;

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
			coach.setStatus(0);
			coach.setClubid(-1);
			return dao.save(coach);
		} catch (Exception e) {
			logger.error("注册教练失败", e);
			throw new RuntimeException("注册教练失败", e);
		}
	}
	
	public void save(Coach coach) {
		try {
			coach.setStatus(0);
			coach.setClubid(-1);
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
			return coach;
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
	
	public void coachsToClub(int clubId, String coachIds) {
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
	}
	
}
