package com.gym.user.rest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.user.dto.CoachDto;
import com.gym.user.dto.UserDto;
import com.gym.user.entity.Coach;
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
			User result = service.login(dto);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
}
