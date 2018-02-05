package com.gym.user.rest;

import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.user.dto.CoachCreate;
import com.gym.user.entity.Coach;
import com.gym.user.service.CoachService;

@Controller
@RequestMapping("/rest/coach")
public class CoachResource {

	@Autowired
	private CoachService service;

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
	@ResponseBody
	public BaseResponse register(CoachCreate dto) throws IllegalStateException, IOException, ServletException {
		try {

			Coach result = service.register(dto);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
}
