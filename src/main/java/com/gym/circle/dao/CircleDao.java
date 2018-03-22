package com.gym.circle.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.Circle;

@Repository
public interface CircleDao extends CrudRepository<Circle, Integer>, JpaSpecificationExecutor<Circle> {
	
	@Query("select u from Circle as u  where u.status = 0  order by createtime desc")
	public List<Circle> findByPage(Pageable pageable);
	
	@Query("select count(u) from Circle u  where u.status = 0")
	public int getCount();

}
