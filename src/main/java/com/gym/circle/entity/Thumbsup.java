package com.gym.circle.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gym.user.dto.SimpleUserDto;

/**
 * 圈子用户点赞表
 * 
 * 
 * */
@Entity
@Table(name = "thumbsup")
@JsonInclude(Include.NON_NULL)
public class Thumbsup implements Serializable {

	private static final long serialVersionUID = 8631206269350485039L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "circleid")
	private int circleId;
	
	@Column(name = "userid")
	private int userId;
	
	@Column(name = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	@Transient
	private SimpleUserDto user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCircleId() {
		return circleId;
	}

	public void setCircleId(int circleId) {
		this.circleId = circleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public SimpleUserDto getUser() {
		return user;
	}

	public void setUser(SimpleUserDto user) {
		this.user = user;
	}

}
