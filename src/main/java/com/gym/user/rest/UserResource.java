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
	
	@RequestMapping(value = "/addinfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public BaseResponse addinfo(@RequestBody UserDto dto) throws IllegalStateException, IOException,
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
			User result = service.addinfo(dto);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 获取验证码，应向手机发送验证码短信（暂未实现）
	 * 并将手机号和验证码存在表中
	 * 
	 * */
	@RequestMapping(value = "sendcode", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse sendcode(@RequestParam(required = true) String phone) {
		try {
			//生成验证码
			String code = "6666";
			service.saveCode(phone, code);
			return new BaseResultResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 用户登录,登录后返回用户资料
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
			User user = service.getByPhone(phone);
			return new BaseResultResponse(user);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = true) int id) {
		try {
			User result = service.detail(id);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
