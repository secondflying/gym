package com.gym.user.rest;

import java.io.IOException;

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
import com.gym.user.dto.UserDto;
import com.gym.user.entity.User;
import com.gym.user.service.UserService;

@Controller
@RequestMapping("/rest/user")
public class UserResource {

	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public BaseResponse login(@RequestBody UserDto dto) throws IllegalStateException, IOException,
			ServletException {
		try {
			if(StringUtils.isEmpty(dto.getName())) {
				throw new IllegalArgumentException("参数name不得为空");
			}
			if(StringUtils.isEmpty(dto.getSex())) {
				throw new IllegalArgumentException("参数sex不得为空");
			}
			if(StringUtils.isEmpty(dto.getPhone())) {
				throw new IllegalArgumentException("参数phone不得为空");
			}
			User result = service.login(dto);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = true) String phone) {
		try {
			if (StringUtils.isEmpty(phone))
				throw new IllegalArgumentException("参数phone不得为空");
			User result = service.findByPhone(phone);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
