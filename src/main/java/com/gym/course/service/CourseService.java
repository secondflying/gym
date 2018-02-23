package com.gym.course.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.course.dao.CourseDao;
import com.gym.course.entity.Course;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;

@Service
@Transactional
public class CourseService {
	
	Logger logger = LoggerFactory.getLogger(CourseService.class);

	@Autowired
	private CourseDao dao;
	
	public Page<Course> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<Course> results = dao.findAll(new QuerySpecification<Course>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}

}
