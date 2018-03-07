package com.gym.commodity.dto;

import java.io.Serializable;

/**
 * 商品订单
 * 
 * */
public class COrderDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7573013325091365626L;
	private int userId;
	private int cid;
	private String comment;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
