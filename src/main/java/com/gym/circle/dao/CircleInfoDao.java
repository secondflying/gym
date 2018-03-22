package com.gym.circle.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.CircleInfo;

@Repository
public interface CircleInfoDao extends CrudRepository<CircleInfo, Integer>, JpaSpecificationExecutor<CircleInfo> {

}
