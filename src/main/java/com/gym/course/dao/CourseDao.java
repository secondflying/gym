package com.gym.course.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.course.entity.Course;

@Repository
public interface CourseDao extends CrudRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

}
