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
	private Integer userId;
	
	@OneToOne()
	@JoinColumn(name = "userid", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private User user;
	
	@Column(name = "cid")
	private Integer cid;
	
	@OneToOne()
	@JoinColumn(name = "cid", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Commodity commodity;
	
	@Column(name = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date time;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "level")
	private Integer level;
	
	@Column(name = "status")
	@JsonIgnore
	private Integer status;
	
	/**
	 * 订单状态
	 * 1正在发货、2已发货、3已完成、4已退货、5未支付、6已支付
	 * 
	 * */
	@Column(name = "state")
	private Integer state;
	
	@Column(name = "num")
	private Integer num;

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

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
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

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
}
