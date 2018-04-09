package com.gym.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.order.entity.UserOrder;

@Repository
public interface UserOrderDao  extends CrudRepository<UserOrder, Integer>, JpaSpecificationExecutor<UserOrder> {

	@Query("select u from UserOrder as u where u.coachId = ?1 and u.startTime > ?2 and u.endTime < ?3")
	public List<UserOrder> findOrderByTime(int coachId, Date start, Date end);
	
	/*时间段交集*/
	@Query("select u from UserOrder as u where u.coachId = ?1 and ((u.startTime >= ?2 and u.startTime <= ?3) or (u.startTime <= ?2 and u.endTime >= ?3) or (u.endTime >= ?2 and u.endTime <= ?3))")
	public List<UserOrder> intersectTime(int coachId, Date start, Date end);
	
	@Query("select u from UserOrder as u where u.coachId = ?1 and u.comment <> null and u.status = 0  order by createtime desc")
	public List<UserOrder> findOrderHasComment(int coachId);
	
	@Query("select u from UserOrder as u where u.coachId = ?1 and u.state = ?2 and u.status = 0  order by createtime desc")
	public List<UserOrder> findOrderByCidAndState(Pageable pageable, int coachId, int state);
	
	@Query("select count(u) from UserOrder u  where u.coachId = ?1 and u.state = ?2 and u.status = 0")
	public int countByCidAndState(int coachId, int state);
	
	@Query("select u from UserOrder as u where u.coachId = ?1 and u.status = 0  order by createtime desc")
	public List<UserOrder> findOrderByCid(Pageable pageable, int coachId);
	
	@Query("select count(u) from UserOrder u  where u.coachId = ?1 and u.status = 0")
	public int countByCid(int coachId);
	
	@Query("select u from UserOrder as u where u.userId = ?1 and u.state = ?2 and u.status = 0  order by createtime desc")
	public List<UserOrder> findOrderByUserId(Pageable pageable, int userId, int state);
	
	@Query("select count(u) from UserOrder u  where u.userId = ?1 and u.state = ?2 and u.status = 0")
	public int countByUserId(int userId, int state);
	
}
