package com.gym.user.dto;

/**
 * 订单时间段，每天的9点到22点
 * 
 * */
public class TimeSlot {
	
	private int slot;//时间点，9表示，9点--10点
    private boolean isbusy; //该时间段是否忙
    
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
	
}
