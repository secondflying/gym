package com.gym.circle.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.circle.dao.ThumbsupDao;
import com.gym.circle.entity.Thumbsup;

@Service
@Transactional
public class ThumbsupService {
	
	Logger logger = LoggerFactory.getLogger(ThumbsupService.class);
	
	@Autowired
	private ThumbsupDao dao;
	
	public void clickOf(int userId, int circleId) {
		try {
			//查询是否点赞过
			List<Thumbsup> thumbsups = dao.getThumbsup(userId, circleId);
			if(thumbsups.size() > 0) {
				Thumbsup thumbsup = thumbsups.get(0);
				dao.delete(thumbsup);
			}else {
				Thumbsup thumbsup = new Thumbsup();
				thumbsup.setTime(new Date());
				thumbsup.setUserId(userId);
				thumbsup.setCircleId(circleId);
				dao.save(thumbsup);
			}
		} catch (Exception e) {
			logger.error("点赞失败", e);
			throw new RuntimeException("点赞失败", e);
		}
	}

}
