package com.gym.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gym.club.entity.Club;
import com.gym.common.entity.Image;
import com.gym.order.entity.UserOrder;
import com.gym.user.dto.TimeSlot;

/**
 * 教练表
 * 
 * */
@Entity
@Table(name = "coach")
public class Coach implements Serializable {

	private static final long serialVersionUID = 6617897682424252818L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@XmlElement
	@Column(name = "name")
	private String name;
	
	@XmlElement
	@Column(name = "age")
	private Integer age;
	
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
	
	@Column(name = "time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date time;
	
	@XmlElement
	@Column(name = "status")
	@JsonIgnore
	private Integer status;
	
	@XmlElement
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "ctime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@JsonIgnore
	private Date ctime;
	
	@XmlElement
	@Column(name = "code")
	@JsonIgnore
	private String code;
	
	@XmlElement
	@Column(name = "level")
	private Integer level;
	
	@XmlElement
	@Column(name = "clubid")
	private Integer clubid;
	
	@ManyToOne()
	@JoinColumn(name = "clubid", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	private Club club;
	
	/**
	 * 审核状态
	 * 0未审核、1审核通过、-1审核不通过
	 * 
	 * */
	@XmlElement
	@Column(name = "state")
	private Integer state;
	
	@XmlElement
	@Column(name = "reason")
	private String reason;
	
	@XmlElement
	@Column(name = "hourcost")
	private String hourcost;
	
	@Transient
	private List<Image> images;
	
	@Transient
	private List<TimeSlot> today;
	
	@Transient
	private List<TimeSlot> tomorrow;
	
	@Transient
	private List<TimeSlot> after;
	
	@Transient
	private List<UserOrder> orders;
	
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Integer getClubid() {
		return clubid;
	}

	public void setClubid(Integer clubid) {
		this.clubid = clubid;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}
	
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public List<TimeSlot> getToday() {
		return today;
	}

	public void setToday(List<TimeSlot> today) {
		this.today = today;
	}

	public List<TimeSlot> getTomorrow() {
		return tomorrow;
	}

	public void setTomorrow(List<TimeSlot> tomorrow) {
		this.tomorrow = tomorrow;
	}

	public String getHourcost() {
		return hourcost;
	}

	public void setHourcost(String hourcost) {
		this.hourcost = hourcost;
	}

	public List<UserOrder> getOrders() {
		return orders;
	}

	public void setOrders(List<UserOrder> orders) {
		this.orders = orders;
	}

	public List<TimeSlot> getAfter() {
		return after;
	}

	public void setAfter(List<TimeSlot> after) {
		this.after = after;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
