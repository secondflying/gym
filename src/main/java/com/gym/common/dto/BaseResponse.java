package com.gym.common.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class BaseResponse implements Serializable {
	private static final long serialVersionUID = -2116022509436963916L;

	@XmlElement
	// 返回结果状态标识码，ok表示成功,error表示错误
	private String status = "ok";

	@XmlElement
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static BaseResponse buildErrorResponse(Exception e) {
		BaseResponse response = new BaseResponse();
		response.setStatus("error");
		response.setMessage(e.getMessage());
		return response;
	}

	public static BaseResponse buildErrorResponse(String message) {
		BaseResponse response = new BaseResponse();
		response.setStatus("error");
		response.setMessage(message);
		return response;
	}

	public static BaseResponse buildSuccessResponse() {
		BaseResponse response = new BaseResponse();
		response.setStatus("ok");
		return response;
	}

}
