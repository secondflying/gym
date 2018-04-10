package com.gym.user.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.CoachLeave;

@Repository
public interface CoachLeaveDao extends CrudRepository<CoachLeave, Integer>, JpaSpecificationExecutor<CoachLeave> {

	@Query("select u from CoachLeave as u where u.coachId = ?1 and u.startTime > ?2 and u.endTime < ?3")
	public List<CoachLeave> findLeaveByTime(int coachId, Date start, Date end);
	
	/*时间段交集*/
	@Query("select u from CoachLeave as u where u.coachId = ?1 and ((u.startTime >= ?2 and u.startTime <= ?3) or (u.startTime <= ?2 and u.endTime >= ?3) or (u.endTime >= ?2 and u.endTime <= ?3))")
	public List<CoachLeave> intersectTime(int coachId, Date start, Date end);
}
