package com.gym.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.Coach;

@Repository
public interface CoachDao extends CrudRepository<Coach, Integer>, JpaSpecificationExecutor<Coach> {
	
	@Query("select u from Coach as u  where u.clubid = ?1")
	public List<Coach> findByClubId(int clubId);
	
	@Query("select u from Coach as u  where u.phone = ?1")
	public List<Coach> findByPhone(String phone);
	
}
