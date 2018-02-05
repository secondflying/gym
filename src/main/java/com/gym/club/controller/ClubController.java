package com.gym.club.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/manager/club")
public class ClubController {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model, @RequestParam(required = false) String context,
			@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size) {

		// model.addAttribute("imageUrl", PublicConfig.getImageUrl() +
		// "users/small");
		// model.addAttribute("questions", questions);
		// model.addAttribute("sumcount", sumcount);
		return "club/index";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, @RequestParam(required = false) Integer id) {
		// model.addAttribute("imageUrl", PublicConfig.getImageUrl() +
		// "users/small");
		// model.addAttribute("questions", questions);
		// model.addAttribute("sumcount", sumcount);
		return "club/edit";
	}

}
