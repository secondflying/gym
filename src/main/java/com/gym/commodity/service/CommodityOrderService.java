package com.gym.commodity.service;

import java.util.Date;
import java.util.List;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.gym.commodity.dao.CommodityOrderDao;
import com.gym.commodity.entity.CommodityOrder;
import com.gym.common.dao.ImageDao;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.entity.User;

@Service
@Transactional
public class CommodityOrderService {

	Logger logger = LoggerFactory.getLogger(CommodityOrderService.class);
	
	@Autowired
	private CommodityOrderDao dao;
	
	@Autowired
	private ImageDao imagedao;
	
	public void addOrder(int userId, int cid, int num) {
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setUserId(userId);
			commodityOrder.setCid(cid);
			commodityOrder.setTime(new Date());
			commodityOrder.setStatus(0);
			commodityOrder.setState(5);
			commodityOrder.setNum(num);
			dao.save(commodityOrder);
		} catch (Exception e) {
			logger.error("新增商品订单失败", e);
			throw new RuntimeException("新增商品订单失败", e);
		}
	}
	
	public void commentOrder(int id, String comment, int level) {
		try {
			CommodityOrder commodityOrder = dao.findOne(id);
			commodityOrder.setComment(comment);
			commodityOrder.setLevel(level);
			dao.save(commodityOrder);
		} catch (Exception e) {
			logger.error("评价商品失败", e);
			throw new RuntimeException("评价商品失败", e);
		}
	}
	
	public Page<CommodityOrder> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<CommodityOrder> results = dao.findAll(new QuerySpecification<CommodityOrder>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
	
	public CommodityOrder getById(int id) {
		try {
			CommodityOrder commodityOrder = dao.findOne(id);
			return commodityOrder;
		} catch (Exception e) {
			logger.error("获取商品详情失败", e);
			throw new RuntimeException("获取商品详情失败", e);
		}
	}
	
	public void saveState(int id, int state){
		CommodityOrder commodityOrder = dao.findOne(id);
		commodityOrder.setState(state);
		dao.save(commodityOrder);
	}
	
	public void delete(int id) {
		try {
			CommodityOrder commodityOrder = dao.findOne(id);
			commodityOrder.setStatus(-1);
			dao.save(commodityOrder);
		} catch (Exception e) {
			logger.error("删除商品订单失败", e);
			throw new RuntimeException("删除商品订单失败", e);
		}
	}
	
	public List<CommodityOrder> payList(int userId, int page, int size, int state){
		List<CommodityOrder> list = dao.payList(new PageRequest(page, size), userId, state);
		for(CommodityOrder co : list) {
			User user = co.getUser();
			user.setImages(imagedao.getOfImages(user.getId(), "user"));
			co.setUser(user);
		}
		return list;
	}
	
	public int payCount(int userId, int state) {
		return dao.payCount(userId, state);
	}
	
	public List<CommodityOrder> unpayList(int userId, int page, int size, int state){
		List<CommodityOrder> list = dao.unpayList(new PageRequest(page, size), userId, state);
		for(CommodityOrder co : list) {
			User user = co.getUser();
			user.setImages(imagedao.getOfImages(user.getId(), "user"));
			co.setUser(user);
		}
		return list;
	}
	
	public int unpayCount(int userId, int state) {
		return dao.unpayCount(userId, state);
	}
	
	public List<CommodityOrder> commentedList(String userId, int page, int size){
		List<CommodityOrder> list = null;
		if(StringUtils.isEmpty(userId)) {
			list = dao.commentedList(new PageRequest(page, size));
		}else {
			int user = Integer.valueOf(userId);
			list = dao.commentedListByUser(new PageRequest(page, size), user);
		}
		for(CommodityOrder co : list) {
			User user = co.getUser();
			user.setImages(imagedao.getOfImages(user.getId(), "user"));
			co.setUser(user);
		}
		return list;
	}
	
	public int commentedCount(String userId){
		int count = 0;
		if(StringUtils.isEmpty(userId)) {
			count = dao.commentedCount();
		}else {
			int user = Integer.valueOf(userId);
			count = dao.commentedCountByUser(user);
		}
		return count;
	}
}
