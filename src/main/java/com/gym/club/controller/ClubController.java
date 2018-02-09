package com.gym.club.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

}
