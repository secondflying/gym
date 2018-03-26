package com.gym.common.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.common.entity.Apk;

@Repository
public interface ApkDao  extends CrudRepository<Apk, Integer>, JpaSpecificationExecutor<Apk> {

}
