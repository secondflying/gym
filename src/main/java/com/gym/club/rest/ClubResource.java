package com.gym.club.rest;

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

import com.gym.club.entity.Club;
import com.gym.club.service.ClubService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultsResponse;
import com.gym.common.dto.BaseResultResponse;

@Controller
@RequestMapping("/rest/club")
public class ClubResource {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClubService service;

	/**
	 * 获取附近的健身房
	 * 
	 * @param x
	 * @param y
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "near", method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse near(@RequestParam(required = true) double x, @RequestParam(required = true) double y,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {
		try {
			List<Club> results = service.getNearAt(x, y, page, size);
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
			Club result = service.getById(cid);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}

}
