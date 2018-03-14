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
	private int id;
	private int userId;
	private int cid;
	private String comment;
	private int level;
	private int state;
	private String time;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
