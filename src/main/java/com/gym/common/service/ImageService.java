package com.gym.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.common.dao.ImageDao;
import com.gym.common.entity.Image;

@Service
@Transactional
public class ImageService {
	
	Logger logger = LoggerFactory.getLogger(ImageService.class);
	
	@Autowired
	private ImageDao imageDao;
	
	public void save(Image image) {
		try {
			imageDao.save(image);
		} catch (Exception e) {
			logger.error("新增图片失败", e);
			throw new RuntimeException("新增图片失败", e);
		}
	}
}
