package com.gym.common.service;

import java.util.List;

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
	
	public void saveimgs(int cid, String cate, String urls) {
		try {
			String[] arr = urls.split(",");
			for(int i=0;i<arr.length;i++) {
				Image image = new Image();
				image.setCid(cid);
				image.setUrl(arr[i]);
				image.setCate(cate);
				imageDao.save(image);
			}
		} catch (Exception e) {
			logger.error("新增图片失败", e);
			throw new RuntimeException("新增图片失败", e);
		}
	}
	
	public void deleteByUrl(String url) {
		try {
			List<Image> images = imageDao.findByUrl(url);
			if(images.size() > 0) {
				imageDao.delete(images.get(0));
			}
		} catch (Exception e) {
			logger.error("删除图片失败", e);
			throw new RuntimeException("新增图片失败", e);
		}
	}
	
}
