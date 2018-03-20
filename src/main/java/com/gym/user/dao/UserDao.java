package com.gym.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {
	
	@Query("select u from User as u  where u.phone = ?1 and u.status = 0  order by createtime desc")
	public List<User> findByPhone(String phone);
	
}
