package com.gym.circle.rest;

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

import com.gym.circle.entity.Circle;
import com.gym.circle.entity.CircleInfo;
import com.gym.circle.service.CircleService;
import com.gym.circle.service.CommentService;
import com.gym.circle.service.FollowService;
import com.gym.circle.service.ThumbsupService;
import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.dto.BaseResultsResponse;

@Controller
@RequestMapping("/rest/circle")
public class CircleResource {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CircleService service;
	
	@Autowired
	private ThumbsupService thumbsupService;
	
	@Autowired
	private CommentService commentService;
	
	/**
	 * 圈子发布
	 * 
	 * */
	@RequestMapping(value = "/publish", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse publish(@RequestParam(required = true) int userId, @RequestParam(required = false) String content,
			@RequestParam(required = false) Double lng, @RequestParam(required = false) Double lat){
		try {
			Circle circle = service.publish(userId, content, lng, lat);
			return new BaseResultResponse(circle);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 获取所有圈子列表
	 * 
	 * */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse list(@RequestParam(required = false, defaultValue = "0") int page,
			@RequestParam(required = false, defaultValue = "20") int size){
		try {
			List<Circle> results = service.list(page, size);
			int count = service.getCount();
			return new BaseResultsResponse(count, results);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 圈子详情
	 * 
	 * */
	@RequestMapping(value = "/detail", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse detail(@RequestParam(required = true) int id){
		try {
			CircleInfo result = service.detail(id);
			return new BaseResultResponse(result);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 圈子点赞
	 * 如果该圈子内容未点赞则新增点赞，否则取消点赞
	 * 
	 * */
	@RequestMapping(value = "/thumbsup/click", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse click(@RequestParam(required = true) int userId, @RequestParam(required = true) int circleId){
		try {
			thumbsupService.clickOf(userId, circleId);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 圈子评论
	 * 只做一级评论
	 * 
	 * */
	@RequestMapping(value = "/comment/add", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse addComment(@RequestParam(required = true) int userId, @RequestParam(required = true) int circleId,
			@RequestParam(required = true) String comment){
		try {
			if (StringUtils.isEmpty(comment))
				throw new IllegalArgumentException("参数comment不得为空");
			commentService.add(userId, circleId, comment);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}

}
