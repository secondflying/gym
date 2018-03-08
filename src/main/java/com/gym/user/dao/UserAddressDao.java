package com.gym.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.UserAddress;

@Repository
public interface UserAddressDao extends CrudRepository<UserAddress, Integer>, JpaSpecificationExecutor<UserAddress> {

	@Query("select u from UserAddress as u  where u.userId = ?1")
	public List<UserAddress> findByUserId(int userId);
	
}
