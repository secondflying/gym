package com.gym.common.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gym.common.entity.Image;

@Repository
public interface ImageDao extends CrudRepository<Image, Integer>, JpaSpecificationExecutor<Image> {

	@Query("select u from Image u where u.cid=?1 and u.cate=?2")
	public List<Image> getOfImages(Integer aid, String cate);

	@Query("delete from Image as u where u.cid = ?1 and u.cate=?2")
	@Modifying()
	public int deleteByClub(Integer aid, String cate);
	
	@Query("select u from Image u where u.url=?1")
	public Image findByUrl(String url);
	
}
