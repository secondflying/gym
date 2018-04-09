package com.gym.order.rest;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.gym.common.dto.BaseResponse;
import com.gym.order.service.UserOrderService;

@Controller
@RequestMapping("/rest/order")
public class UserOrderResource {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserOrderService service;
	
	@RequestMapping(value = "/neworder", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse neworder(@RequestParam(required = true) int userId, @RequestParam(required = true) int coachId,
			@RequestParam(required = true) String startTime, @RequestParam(required = true) String endTime,
			@RequestParam(required = false) String content){
		try {
			service.neworder(userId, coachId, startTime, endTime, content);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	@RequestMapping(value = "/comment", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse comment(@RequestParam(required = true) int id, @RequestParam(required = true) String comment, 
			@RequestParam(required = true, defaultValue = "1") int level){
		try {
			service.commentOrder(id, comment, level);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
	/**
	 * 通过订单id生成订单二维码
	 * 
	 * */
	@RequestMapping(value = "/getQRCode", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse getQRCode(HttpServletResponse response, @RequestParam(required = true) String id, 
			@RequestParam(required = false, defaultValue = "300") int width,
			@RequestParam(required = false, defaultValue = "300") int height) throws IOException{
		if (StringUtils.isEmpty(id))
			throw new IllegalArgumentException("参数id不得为空");
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		OutputStream stream = null;
		try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(id, BarcodeFormat.QR_CODE, width, height);
			stream = response.getOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", stream);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		} finally {
			if(stream != null) {
				stream.flush();
				stream.close();
			}
		}
	}
	
	/**
	 * 订单开始接口
	 * 
	 * */
	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST}, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public BaseResponse start(@RequestParam(required = true) int id){
		try {
			service.start(id);
			return BaseResponse.buildSuccessResponse();
		} catch (Exception e) {
			return BaseResponse.buildErrorResponse(e);
		}
	}
	
}
