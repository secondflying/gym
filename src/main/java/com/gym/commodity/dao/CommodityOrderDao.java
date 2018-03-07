package com.gym.commodity.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.commodity.entity.CommodityOrder;

@Repository
public interface CommodityOrderDao extends CrudRepository<CommodityOrder, Integer>, JpaSpecificationExecutor<CommodityOrder> {
	
	@Query("select count(u) from CommodityOrder u  where u.status = 0")
	public int getCount();
	
}
