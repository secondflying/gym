package com.gym.common.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.common.entity.Image;
import com.gym.common.service.ImageService;
import com.gym.util.PublicHelper;

@Controller
@RequestMapping("/rest/file")
public class FileResource {
	
	@Autowired
	private ImageService service;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException, IOException, ServletException {
		try {
			String imagename = file.getOriginalFilename();
			String format = imagename.substring(imagename.lastIndexOf(".") + 1);
			String saveName = PublicHelper.saveImage1(file.getInputStream(), format);
			return new BaseResultResponse(saveName);
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/saveimg", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse saveimg(HttpServletRequest request, @RequestBody Image image){
		try {
			service.save(image);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/delimg", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse delimg(HttpServletRequest request, String url){
		try {
			if (StringUtils.isEmpty(url))
				throw new IllegalArgumentException("参数url不得为空");
			service.deleteByUrl(url);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
