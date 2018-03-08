package com.gym.user.entity;

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
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.common.entity.Image;

/**
 * 普通用户表
 * 
 * */
@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 6617891312424233118L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@XmlElement
	@Column(name = "name")
	private String name;
	
	@XmlElement
	@Column(name = "nickname")
	private String nickname;
	
	@XmlElement
	@Column(name = "age")
	private int age;
	
	@XmlElement
	@Column(name = "sex")
	private String sex;
	
	@XmlElement
	@Column(name = "brief")
	private String brief;
	
	@XmlElement
	@Column(name = "height")
	private String height;
	
	@XmlElement
	@Column(name = "weight")
	private String weight;
	
	@XmlElement
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	@XmlElement
	@Column(name = "status")
	@JsonIgnore
	private int status;
	
	//用户头像
	@Transient
	private List<Image> images;
	
	//用户地址
	private List<UserAddress> addresses;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UserAddress> addresses) {
		this.addresses = addresses;
	}
	
}
