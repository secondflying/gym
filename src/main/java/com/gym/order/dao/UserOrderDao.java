package com.gym.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.order.entity.UserOrder;

@Repository
public interface UserOrderDao  extends CrudRepository<UserOrder, Integer>, JpaSpecificationExecutor<UserOrder> {

	@Query("select u from UserOrder as u where u.coachId = ?1 and u.startTime > ?2 and u.startTime < ?3")
	public List<UserOrder> findOrderByTime(int coachId, Date start, Date end);
	
	@Query("select u from UserOrder as u where u.coachId = ?1 and u.comment <> null and u.status = 0  order by createtime desc")
	public List<UserOrder> findOrderHasComment(int coachId);
	
}
