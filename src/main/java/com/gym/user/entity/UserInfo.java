package com.gym.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 普通用户表
 * 
 * */
@Entity
@Table(name = "user")
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2907200933275442940L;

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

	public String getPhone() {
		return phone;
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
	
}