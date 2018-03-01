package com.gym.order.dto;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOrderDto implements Serializable {

	private static final long serialVersionUID = 8492339437787890093L;
	
	private Integer userId;
	private Integer coachId;
	private String content;
	
}
