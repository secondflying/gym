package com.gym.user.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.dto.BaseResultsResponse;
import com.gym.order.entity.UserOrder;
import com.gym.user.dto.CoachDto;
import com.gym.user.entity.Coach;
import com.gym.user.entity.CoachInfo;
import com.gym.user.service.CoachService;

@Controller
@RequestMapping("/rest/coach")
public class CoachResource {

	@Autowired
	private CoachService service;
	
	/**
	 * 教练获取验证码
	 * 
	 * */
	@RequestMapping(value = "sendcode", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse sendcode(@RequestParam(required = true) String phone) {
		try {
			if(StringUtils.isEmpty(phone)) {
				throw new IllegalArgumentException("手机号码不得为空");
			}
			//生成验证码
			String code = "6666";
			if(!service.checkPhone(phone)) {
				throw new IllegalArgumentException("手机号码未注册或未审核");
			}
			service.saveCode(phone, code);
			return new BaseResultResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 教练登录
	 * 
	 * */
	@RequestMapping(value = "login", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse login(@RequestParam(required = true) String phone, @RequestParam(required = true) String code) {
		try {
			if (StringUtils.isEmpty(phone))
				throw new IllegalArgumentException("参数phone不得为空");
			if (StringUtils.isEmpty(phone))
				throw new IllegalArgumentException("参数code不得为空");
			if(!service.checkCode(phone, code)) {
				throw new IllegalArgumentException("输入的验证码不正确或已过期");
			}
			CoachInfo coach = service.getByPhone(phone);
			return new BaseResultResponse(coach);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	

	/**
	 * 教练注册
	 * 
	 * */
	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public BaseResponse register(@RequestBody CoachDto dto) throws IllegalStateException, IOException,
			ServletException {
		try {
			if (StringUtils.isEmpty(dto.getPhone()))
				throw new IllegalArgumentException("手机号码不得为空");
			if (StringUtils.isEmpty(dto.getName()))
				throw new IllegalArgumentException("名称不得为空");
			if(service.checkExsit(dto.getPhone())) {
				throw new IllegalArgumentException("手机号码已注册");
			}
			Coach result = service.register(dto);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 教练详情
	 * 
	 * */
	@RequestMapping(value = "detail", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = true) String id) {	
		try {
			if (StringUtils.isEmpty(id))
				throw new IllegalArgumentException("参数id不得为空");
			int cid = Integer.valueOf(id);
			Coach result = service.detail(cid);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 教练请假
	 * 
	 * */
	@RequestMapping(value = "/leave", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse leave(@RequestParam(required = true) String times, @RequestParam(required = true) int coachId) {
		try {
			service.leaveCheck(times, coachId);
			service.leave(times, coachId);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 教练获取我的订单
	 * 教练id
	 * 订单状态state
	 * */
	@RequestMapping(value = "orders", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse orders(@RequestParam(required = true) String id, @RequestParam(required = false) String state,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		try {
			if (StringUtils.isEmpty(id))
				throw new IllegalArgumentException("参数id不得为空");
			int cid = Integer.valueOf(id);
			List<UserOrder> list = service.orders(cid, state, page, size);
			int count = service.ordersCount(cid, state);
			return new BaseResultsResponse(count, list);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
