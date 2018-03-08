package com.gym.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultsResponse;
import com.gym.user.entity.UserAddress;
import com.gym.user.service.UserAddressService;

@Controller
@RequestMapping("/rest/user/address")
public class UserAddressResource {
	
	@Autowired
	private UserAddressService service;
	
	@RequestMapping(value = "list", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse list(@RequestParam(required = true) int userId) {
		try {
			List<UserAddress> results = service.findByUserId(userId);
			int count = results.size();
			return new BaseResultsResponse(count, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/add", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse add(@RequestParam(required = true) int userId, 
			@RequestParam(required = true) String address){
		try {
			service.save(userId, address);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse update(@RequestParam(required = true) int id, 
			@RequestParam(required = true) String address){
		try {
			service.update(id, address);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.GET}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse delete(@RequestParam(required = true) int id ){
		try {
			service.delete(id);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
}
