package com.gym.user.dto;

import java.util.Date;

/**
 * 请假时间段
 * 
 * */
public class LeaveTime {
	
	private Date startTime;
	
	private Date endTime;
	
	private String reason;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
