package com.gym.common.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gym.common.dto.BaseResponse;
import com.gym.common.dto.BaseResultResponse;
import com.gym.util.PublicHelper;

@Controller
@RequestMapping("/manager/file")
public class FileController {
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
}
