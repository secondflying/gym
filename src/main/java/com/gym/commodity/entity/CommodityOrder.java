package com.gym.commodity.entity;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gym.user.entity.User;

/**
 * 商品订单表
 * 
 * */
@Entity
@Table(name = "commodityorder")
@JsonInclude(Include.NON_NULL)
public class CommodityOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2587140301725115360L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "userid")
	private int userId;
	
	@OneToOne()
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private User user;
	
	@Column(name = "cid")
	private int cid;
	
	@OneToOne()
	@JoinColumn(name = "cid", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Commodity commodity;
	
	@Column(name = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date time;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "level")
	private int level;
	
	@Column(name = "status")
	@JsonIgnore
	private int status;
	
	@Column(name = "state")
	private int state;
	
	@Column(name = "num")
	private int num;

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

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Commodity getCommodity() {
		return commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
