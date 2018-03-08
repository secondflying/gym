package com.gym.commodity.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.commodity.service.CommodityOrderService;
import com.gym.common.dto.BaseResponse;

@Controller
@RequestMapping("/rest/commodity/order")
public class CommodityOrderResource {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommodityOrderService service;
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse add(@RequestParam(required = true) int userId, @RequestParam(required = true) int cid,
			@RequestParam(required = false) String comment){
		try {
			service.addOrder(userId, cid, comment);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
}