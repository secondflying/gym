package com.gym.order.rest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.dto.BaseResponse;
import com.gym.order.service.UserOrderService;

@Controller
@RequestMapping("/rest/order")
public class UserOrderResource {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserOrderService service;
	
	@RequestMapping(value = "/neworder", method = { RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse neworder(@RequestParam(required = true) int userId, @RequestParam(required = true) int coachId,
			@RequestParam(required = true) String startTime, @RequestParam(required = true) String endTime,
			@RequestParam(required = false) String content){
		try {
			service.neworder(userId, coachId, startTime, endTime, content);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	
}
