package com.gym.commodity.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.commodity.dao.CommodityDao;
import com.gym.commodity.dao.CommodityOrderDao;
import com.gym.commodity.dto.COrderDto;
import com.gym.commodity.entity.Commodity;
import com.gym.commodity.entity.CommodityOrder;
import com.gym.common.dao.ImageDao;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.UserDao;
import com.gym.user.entity.User;

@Service
@Transactional
public class CommodityService {

	Logger logger = LoggerFactory.getLogger(CommodityService.class);
	
	@Autowired
	private CommodityDao dao;
	
	@Autowired
	private CommodityOrderDao orderDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ImageDao imagedao;
	
	private static final String ImageCate = "commodity";
	
	public void save(Commodity commodity) {
		try {
			commodity.setStatus(0);
			commodity.setCreateTime(new Date());
			dao.save(commodity);
		} catch (Exception e) {
			logger.error("新增商品失败", e);
			throw new RuntimeException("新增商品失败", e);
		}
	}
	
	public Page<Commodity> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<Commodity> results = dao.findAll(new QuerySpecification<Commodity>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
	
	public Commodity getById(int id) {
		try {
			Commodity commodity = dao.findOne(id);
			commodity.setImages(imagedao.getOfImages(id, ImageCate));
			return commodity;
		} catch (Exception e) {
			logger.error("获取商品详情失败", e);
			throw new RuntimeException("获取商品详情失败", e);
		}
	}
	
	public Commodity detail(int id) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Commodity commodity = dao.findOne(id);
			List<CommodityOrder> list = orderDao.findByCommodityId(id);
			List<COrderDto> clist = new ArrayList<COrderDto>();
			for(CommodityOrder c: list) {
				COrderDto corder = new COrderDto();
				corder.setId(c.getId());
				corder.setCid(c.getCid());
				corder.setComment(c.getComment());
				corder.setLevel(c.getLevel());
				corder.setUserId(c.getUserId());
				corder.setState(c.getState());
				corder.setTime(formatter.format(c.getTime()));
				corder.setNum(c.getNum());
				User user = userDao.findOne(c.getUserId());
				user.setImages(imagedao.getOfImages(c.getUserId(), "user"));
				corder.setUser(user);
				clist.add(corder);
			}
			commodity.setOrders(clist);
			commodity.setImages(imagedao.getOfImages(id, ImageCate));
			return commodity;
		} catch (Exception e) {
			logger.error("获取商品详情失败", e);
			throw new RuntimeException("获取商品详情失败", e);
		}
	}
	
	public int getCount() {
		try {
			return dao.getCount();
		} catch (Exception e) {
			logger.error("获取商品数量异常", e);
			throw new RuntimeException("获取商品数量异常", e);
		}
	}
	
	public List<Commodity> getByType(int type, int page, int size){
		List<Commodity> list = dao.getByType(new PageRequest(page, size), type);
		for(Commodity commodity: list) {
			commodity.setImages(imagedao.getOfImages(commodity.getId(), ImageCate));
		}
		return list;
	}
}
