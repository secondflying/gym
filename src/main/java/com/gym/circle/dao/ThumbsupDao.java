package com.gym.circle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.Thumbsup;

@Repository
public interface ThumbsupDao extends CrudRepository<Thumbsup, Integer>, JpaSpecificationExecutor<Thumbsup> {
	
	@Query("select u from Thumbsup as u where u.userId = ?1 and u.circleId = ?2 order by time desc")
	public List<Thumbsup> getThumbsup(int userId, int circleId);
	
	@Query("select u from Thumbsup as u where u.circleId = ?1 order by time desc")
	public List<Thumbsup> findByCircleId(int circleId);
	
	@Query("select count(u) from Thumbsup u where u.circleId = ?1")
	public int getCountByCircleId(int circleId);

}
