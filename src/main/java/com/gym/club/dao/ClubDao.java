package com.gym.club.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.club.entity.Club;

@Repository
public interface ClubDao extends CrudRepository<Club, Integer>, JpaSpecificationExecutor<Club> {

	@Query("select u from Club as u  where u.status = 0  order by GETDISTANCE(?2,?1,u.y,u.x) asc")
	public List<Club> getNearAt(Pageable pageable, double x, double y);

	@Query("select u from Club as u  where u.status = 0  order by time desc")
	public List<Club> getByPage(Pageable pageable);

	@Query("select count(u) from Club u  where u.status = 0")
	public int getCount();
	
}
