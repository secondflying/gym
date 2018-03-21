package com.gym.commodity.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.commodity.entity.Commodity;

@Repository
public interface CommodityDao extends CrudRepository<Commodity, Integer>, JpaSpecificationExecutor<Commodity> {

	@Query("select u from Commodity as u  where u.type=?1 and u.status =0  order by createTime desc")
	public List<Commodity> getByType(Pageable pageable, int type);
	
	@Query("select count(u) from Commodity u  where u.status = 0")
	public int getCount();
}
