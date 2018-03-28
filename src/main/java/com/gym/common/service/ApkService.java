package com.gym.common.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.common.dao.ApkDao;
import com.gym.common.entity.Apk;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;

@Service
@Transactional
public class ApkService {

	Logger logger = LoggerFactory.getLogger(ApkService.class);
	
	@Autowired
	private ApkDao dao;
	
	public void save(Apk apk) {
		try {
			apk.setTime(new Date());
			dao.save(apk);
		} catch (Exception e) {
			logger.error("apk文件更新失败", e);
			throw new RuntimeException("apk文件更新失败", e);
		}
	}
	
	public Apk detail(int id) {
		try {
			Apk apk = dao.findOne(id);
			return apk;
		} catch (Exception e) {
			logger.error("获取文件详情失败", e);
			throw new RuntimeException("获取文件详情失败", e);
		}
	}
	
	public void update(String url, String version) {
		try {
			List<Apk> apks = (List<Apk>) dao.findAll();
			if(apks.size() > 0) {
				Apk apk = apks.get(0);
				apk.setUrl(url);
				apk.setVersion(version);
				apk.setTime(new Date());
				dao.save(apk);
			}
		} catch (Exception e) {
			logger.error("apk文件更新失败", e);
			throw new RuntimeException("apk文件更新失败", e);
		}
 	}
	
	public Page<Apk> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<Apk> results = dao.findAll(new QuerySpecification<Apk>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
	
}
