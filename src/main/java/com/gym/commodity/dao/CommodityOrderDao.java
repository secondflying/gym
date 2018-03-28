package com.gym.commodity.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.commodity.entity.CommodityOrder;

@Repository
public interface CommodityOrderDao extends CrudRepository<CommodityOrder, Integer>, JpaSpecificationExecutor<CommodityOrder> {
	
	@Query("select count(u) from CommodityOrder u  where u.status = 0")
	public int getCount();
	
	@Query("select u from CommodityOrder as u  where u.cid=?1 and u.status = 0  order by time desc")
	public List<CommodityOrder> findByCommodityId(int commodityId);
	
	@Query("select u from CommodityOrder as u  where u.userId=?1 and u.status = 0 and u.state <>?2  order by time desc")
	public List<CommodityOrder> payList(Pageable pageable, int userId, int state);
	
	@Query("select count(u) from CommodityOrder u where u.userId=?1 and  u.status = 0 and u.state <>?2")
	public int payCount(int userId, int state);
	
	@Query("select u from CommodityOrder as u  where u.userId=?1 and u.status = 0 and u.state =?2  order by time desc")
	public List<CommodityOrder> unpayList(Pageable pageable, int userId, int state);
	
	@Query("select count(u) from CommodityOrder u where u.userId=?1 and  u.status = 0 and u.state =?2")
	public int unpayCount(int userId, int state);
	
	@Query("select u from CommodityOrder as u  where u.status = 0 and u.comment <> null  order by time desc")
	public List<CommodityOrder> commentedList(Pageable pageable);
	
	@Query("select count(u) from CommodityOrder u where u.status = 0 and u.comment <> null")
	public int commentedCount();
	
	@Query("select u from CommodityOrder as u  where u.status = 0 and u.userId=?1 and u.comment <> null  order by time desc")
	public List<CommodityOrder> commentedListByUser(Pageable pageable, int userId);
	
	@Query("select count(u) from CommodityOrder u where u.status = 0 and u.userId=?1 and u.comment <> null")
	public int commentedCountByUser(int userId);
	
}
