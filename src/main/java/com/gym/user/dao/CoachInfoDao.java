package com.gym.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.CoachInfo;

@Repository
public interface CoachInfoDao extends CrudRepository<CoachInfo, Integer>, JpaSpecificationExecutor<CoachInfo> {
	
	@Query("select u from CoachInfo as u  where u.phone = ?1")
	public List<CoachInfo> findByPhone(String phone);
	
}
