package com.gym.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gym.club.entity.Club;
import com.gym.club.service.ClubService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.EasyUIResponse;

@Controller
@RequestMapping("/manager/club")
public class ClubController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ClubService service;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("/club/index");
		return view;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam(required = false) Integer id) {
		// model.addAttribute("imageUrl", PublicConfig.getImageUrl() +
		// "users/small");
		// model.addAttribute("questions", questions);
		// model.addAttribute("sumcount", sumcount);
		return "club/edit";
	}
	
	@RequestMapping(value = "/save", method = { RequestMethod.POST}, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public BaseResponse save(HttpServletRequest request, Club club) {
		try {
			service.save(club);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
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
				filterParam = "";

			if (StringUtils.isEmpty(sortParam))
				sortParam = "";
			
			Page<Club> pager = service.queryListFilter(filterParam, sortParam, start, limit);
			EasyUIResponse response = new EasyUIResponse();
			response.setTotal(pager.getTotalElements());
			response.setRows(pager.getContent());
			return response;
		} catch (Exception ex) {
			return BaseResponse.buildErrorResponse(ex);
		}
	}
	
	

}
