package com.gym.sysconfig.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.sysconfig.entity.Admin;

@Repository
public interface AdminDao extends CrudRepository<Admin, Integer>, JpaSpecificationExecutor<Admin> {

	@Query("select u from Admin u where u.status != -1 and u.name <> 'king' ")
	public List<Admin> getAll(Pageable pageable);
	
	@Query("select u from Admin u where u.name=?1 and u.status != -1")
	public Admin findByName(String name);
	
	@Query("select count(u) from Admin u where u.status != -1  and u.name <> 'king'")
	public int getCount();

}
