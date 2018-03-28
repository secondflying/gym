package com.gym.circle.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.CircleInfo;

@Repository
public interface CircleInfoDao extends CrudRepository<CircleInfo, Integer>, JpaSpecificationExecutor<CircleInfo> {

	@Query("select u from CircleInfo as u  where u.status = 0  order by createtime desc")
	public List<CircleInfo> findByPage(Pageable pageable);
	
	@Query("select count(u) from CircleInfo u  where u.status = 0")
	public int getCount();
	
}
