package com.gym.circle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.Follow;

@Repository
public interface FollowDao extends CrudRepository<Follow, Integer>, JpaSpecificationExecutor<Follow> {

	
}
