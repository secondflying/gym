package com.gym.common.dto;

public class BaseResultResponse extends BaseResponse {

	private static final long serialVersionUID = -728228299599479308L;

	private Object result;

	public BaseResultResponse() {
	}

	public BaseResultResponse(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
