package com.gym.commodity.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gym.commodity.entity.Commodity;
import com.gym.commodity.entity.CommodityOrder;
import com.gym.commodity.service.CommodityOrderService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.dto.EasyUIResponse;

/**
 * 商品订单管理类
 * 
 * */
@Controller
@RequestMapping("/manager/commodityOrder")
public class CommodityOrderController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommodityOrderService service;
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/commodityorder/index");
		return view;
	}
	
	@RequestMapping(value = "/list", method = { RequestMethod.GET}, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse list(HttpServletRequest request, String page, String rows,
			String filterParam, String sortParam) {
		try {
			// 处理参数异常信息
			if (StringUtils.isEmpty(page))
				throw new IllegalArgumentException("参数page不得为空");
			if (StringUtils.isEmpty(rows))
				throw new IllegalArgumentException("参数rows不得为空");

			int start = Integer.valueOf(page) - 1;
			int limit = Integer.valueOf(rows);

			if (StringUtils.isEmpty(filterParam))
				filterParam = "Q_status_N_EQ=0";

			if (StringUtils.isEmpty(sortParam))
				sortParam = "";
			
			Page<CommodityOrder> pager = service.queryListFilter(filterParam, sortParam, start, limit);
			EasyUIResponse response = new EasyUIResponse();
			response.setTotal(pager.getTotalElements());
			response.setRows(pager.getContent());
			return response;
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
	}
	
	@RequestMapping(value = "/save", method = { RequestMethod.POST}, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse save(HttpServletRequest request, int id, int state) {
		try {
			service.saveState(id, state);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
	}
	
	@RequestMapping(value = "/detail", method = { RequestMethod.GET}, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse detail(HttpServletRequest request, int id) {
		try {
			CommodityOrder order = service.getById(id);
			BaseResultResponse response = new BaseResultResponse();
			response.setResult(order);
			return response;
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
	}
	
	@RequestMapping(value = "/delete", method = { RequestMethod.POST}, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse delete(HttpServletRequest request, int ids) {
		try {
			service.delete(ids);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
	}
}
