package com.gym.circle.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.circle.dao.CommentDao;
import com.gym.circle.entity.Comment;

@Service
@Transactional
public class CommentService {
	
	Logger logger = LoggerFactory.getLogger(CommentService.class);
	
	@Autowired
	private CommentDao dao;
	
	public void add(int userId, int circleId, String comment) {
		try {
			Comment c = new Comment();
			c.setUserId(userId);
			c.setComment(comment);
			c.setCircleId(circleId);
			c.setTime(new Date());
			dao.save(c);
		} catch (Exception e) {
			logger.error("评论失败", e);
			throw new RuntimeException("评论失败", e);
		}
	}
	
}
