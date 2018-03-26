package com.gym.common.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.common.dao.ApkDao;
import com.gym.common.entity.Apk;

@Service
@Transactional
public class ApkService {

	Logger logger = LoggerFactory.getLogger(ApkService.class);
	
	@Autowired
	private ApkDao apkDao;
	
	public void update(String url, String version) {
		try {
			List<Apk> apks = (List<Apk>) apkDao.findAll();
			if(apks.size() > 0) {
				Apk apk = apks.get(0);
				apk.setUrl(url);
				apk.setVersion(version);
				apk.setTime(new Date());
				apkDao.save(apk);
			}
		} catch (Exception e) {
			logger.error("apk文件更新失败", e);
			throw new RuntimeException("apk文件更新失败", e);
		}
 	}
	
}
