package com.gym.commodity.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.commodity.dao.CommodityOrderDao;
import com.gym.commodity.entity.CommodityOrder;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;

@Service
@Transactional
public class CommodityOrderService {

	Logger logger = LoggerFactory.getLogger(CommodityOrderService.class);
	
	@Autowired
	private CommodityOrderDao dao;
	
	public void addOrder(int userId, int cid, int num) {
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setUserId(userId);
			commodityOrder.setCid(cid);
			commodityOrder.setTime(new Date());
			commodityOrder.setStatus(0);
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
}
