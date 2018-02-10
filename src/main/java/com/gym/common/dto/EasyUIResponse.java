package com.gym.common.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "BaseObjectResponse")
@XmlAccessorType(XmlAccessType.NONE)
public class EasyUIResponse extends BaseResponse{

	private static final long serialVersionUID = -728228299599479308L;

	@XmlElement
	protected Long total = 0L;
	
	@XmlElement
	List<?> rows = null;
    
	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
