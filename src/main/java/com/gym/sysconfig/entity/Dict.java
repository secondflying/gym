package com.gym.sysconfig.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;

/** 
* 字典表
* 
*/
@Entity
@Table(name = "dict")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Dict implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1878189112174576501L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@XmlElement
	private Integer id;
	
	@XmlElement
	@Column(name = "name")
	protected String name;
	
	@XmlElement
	@Column(name = "key")
	protected String key;
	
	@XmlElement
	@Column(name = "description")
	protected String description;
	
	@Column(name = "createtime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	@OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="dictid",insertable = false, updatable = false)
    @NotFound(action = NotFoundAction.IGNORE)
    protected List<DictValue>  dictValues;

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

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<DictValue> getDictValues() {
		return dictValues;
	}

	public void setDictValues(List<DictValue> dictValues) {
		this.dictValues = dictValues;
	}
	
}