package com.gym.order.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gym.order.service.UserOrderService;

@Controller
@RequestMapping("/rest/order")
public class UserOrderResource {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserOrderService service;
	
	
}
