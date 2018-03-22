package com.gym.circle.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gym.common.entity.Image;
import com.gym.user.dto.SimpleUserDto;

/**
 * 圈子表
 * 
 * 
 * */
@Entity
@Table(name = "circle")
@JsonInclude(Include.NON_NULL)
public class Circle implements Serializable {

	private static final long serialVersionUID = 7583345301393781401L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "userid")
	private int userId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "lng")
	private Double lng;
	
	@Column(name = "lat")
	private Double lat;
	
	@Column(name = "createtime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@Column(name = "status")
	@JsonIgnore
	private Integer status;
	
	@Transient
	private List<Image> images;
	
	@Transient
	private SimpleUserDto user;
	
	@Transient
	private int clickNum;
	
	@Transient
	private int commentNum;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public SimpleUserDto getUser() {
		return user;
	}

	public void setUser(SimpleUserDto user) {
		this.user = user;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	
}
