package com.gym.user.dto;

import java.util.List;

import com.gym.common.entity.Image;

public class SimpleUserDto {
	
	private String name;
	private String nickname;
	private List<Image> images;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	
}
