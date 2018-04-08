package com.gym.user.dto;

/**
 * 订单时间段，每天的9点到21点
 * 
 * */
public class TimeSlot {
	
	private int slot;//时间点，9表示，9点--10点
    private boolean isbusy; //该时间段是否忙
    private String state;
    
	public int getSlot() {
		return slot;
	}
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public boolean isIsbusy() {
		return isbusy;
	}
	public void setIsbusy(boolean isbusy) {
		this.isbusy = isbusy;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
