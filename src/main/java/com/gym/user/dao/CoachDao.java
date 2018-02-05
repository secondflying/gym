package com.gym.user.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.Coach;

@Repository
public interface CoachDao extends CrudRepository<Coach, Integer>, JpaSpecificationExecutor<Coach> {

}
