package com.gym.circle.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.circle.entity.Comment;

@Repository
public interface CommentDao extends CrudRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

	@Query("select u from Comment as u where u.circleId = ?1 order by time desc")
	public List<Comment> findByCircleId(int circleId);
	
	@Query("select count(u) from Comment u where u.circleId = ?1")
	public int getCountByCircleId(int circleId);
	
}
