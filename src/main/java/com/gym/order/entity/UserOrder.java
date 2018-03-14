package com.gym.order.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.user.entity.UserInfo;

@Entity
@Table(name = "userorder")
public class UserOrder implements Serializable {

	private static final long serialVersionUID = 3077990153185774448L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@XmlElement
	@Column(name = "userid")
	private Integer userId;
	
	@OneToOne()
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private UserInfo user;
	
	@Column(name = "createtime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	@Column(name = "starttime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	@Column(name = "endtime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	@XmlElement
	@Column(name = "coachid")
	private Integer coachId;
	
	@XmlElement
	@Column(name = "content")
	private String content;
	
	@XmlElement
	@Column(name = "status")
	@JsonIgnore
	private Integer status;
	
	@XmlElement
	@Column(name = "comment")
	private String comment;
	
	@XmlElement
	@Column(name = "level")
	private int level;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getCoachId() {
		return coachId;
	}

	public void setCoachId(Integer coachId) {
		this.coachId = coachId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
	
}
