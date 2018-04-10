package com.gym.commodity.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.commodity.entity.CommodityOrder;
import com.gym.commodity.service.CommodityOrderService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.dto.BaseResultsResponse;

@Controller
@RequestMapping("/rest/commodity/order")
public class CommodityOrderResource {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommodityOrderService service;
	
	/**
	 * 新增订单，默认未支付状态
	 * 
	 * */
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse add(@RequestParam(required = true) int userId, @RequestParam(required = true) int cid,
			@RequestParam(required = true, defaultValue = "1") int num){
		try {
			service.addOrder(userId, cid, num);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 对订单进行评论
	 * 只有订单完成后，才可进行评论
	 * 
	 * */
	@RequestMapping(value = "/comment", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse comment(@RequestParam(required = true) int id, @RequestParam(required = true) String comment, 
			@RequestParam(required = true, defaultValue = "1") int level){
		try {
			CommodityOrder co = service.getById(id);
			if(co.getState() != 3) { //3表示订单已完成，才可进行评论操作
				throw new IllegalArgumentException("订单状态未完成，不可进行评论");
			}
			service.commentOrder(id, comment, level);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 用户已支付订单
	 * 
	 * */
	@RequestMapping(value = "/ispay", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse ispay(@RequestParam(required = true) int userId, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		try {
			List<CommodityOrder> results = service.payList(userId, page, size, 5);
			int totalCount = service.payCount(userId, 6);
			return new BaseResultsResponse(totalCount, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 用户未支付订单
	 * 
	 * */
	@RequestMapping(value = "/unpay", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse unpay(@RequestParam(required = true) int userId, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		try {
			List<CommodityOrder> results = service.unpayList(userId, page, size, 5);
			int totalCount = service.unpayCount(userId, 5);
			return new BaseResultsResponse(totalCount, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 获取已评论的订单
	 * 
	 * */
	@RequestMapping(value = "/commented/list", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse commentedList(@RequestParam(required = false) String userId, 
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		try {
			List<CommodityOrder> results = service.commentedList(userId, page, size);
			int totalCount = service.commentedCount(userId);
			return new BaseResultsResponse(totalCount, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/detail", method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = false) int id){
		try {
			CommodityOrder order = service.getById(id);
			return new BaseResultResponse(order);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
