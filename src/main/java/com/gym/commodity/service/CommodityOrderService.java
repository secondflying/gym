package com.gym.commodity.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.commodity.dao.CommodityOrderDao;
import com.gym.commodity.entity.CommodityOrder;

@Service
@Transactional
public class CommodityOrderService {

	Logger logger = LoggerFactory.getLogger(CommodityOrderService.class);
	
	@Autowired
	private CommodityOrderDao dao;
	
	public void addOrder(int userId, int cid, String comment) {
		try {
			CommodityOrder commodityOrder = new CommodityOrder();
			commodityOrder.setUserId(userId);
			commodityOrder.setCid(cid);
			commodityOrder.setComment(comment);
			commodityOrder.setTime(new Date());
			commodityOrder.setStatus(0);
			dao.save(commodityOrder);
		} catch (Exception e) {
			logger.error("新增商品订单失败", e);
			throw new RuntimeException("新增商品订单失败", e);
		}
	}
	
}
