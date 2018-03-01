package com.gym.order.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.order.entity.UserOrder;

@Repository
public interface UserOrderDao  extends CrudRepository<UserOrder, Integer>, JpaSpecificationExecutor<UserOrder> {

}
