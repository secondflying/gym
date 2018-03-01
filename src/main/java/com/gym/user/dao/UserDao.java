package com.gym.user.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.user.entity.User;

@Repository
public interface UserDao extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
