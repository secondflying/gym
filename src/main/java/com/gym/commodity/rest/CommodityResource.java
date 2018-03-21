package com.gym.commodity.rest;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gym.commodity.entity.Commodity;
import com.gym.commodity.service.CommodityService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.dto.BaseResultsResponse;

@Controller
@RequestMapping("/rest/commodity")
public class CommodityResource {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommodityService service;
	
	@RequestMapping(value = "listOfType", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse listOfType(@RequestParam(required = true) String type,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		try {
			if (StringUtils.isEmpty(type))
				throw new IllegalArgumentException("参数type不得为空");
			int t = Integer.valueOf(type);
			List<Commodity> results = service.getByType(t, page, size);
			int count = service.getCount();
			return new BaseResultsResponse(count, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = true) String id) {
		try {
			if (StringUtils.isEmpty(id))
				throw new IllegalArgumentException("参数id不得为空");
			int cid = Integer.valueOf(id);
			Commodity commodity = service.detail(cid);
			return new BaseResultResponse(commodity);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
}
