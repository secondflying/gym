package com.gym.club.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.club.dao.ClubDao;
import com.gym.club.entity.Club;
import com.gym.common.dao.ImageDao;
import com.gym.util.PublicHelper;

@Service
@Transactional
public class ClubService {

	@Autowired
	private ClubDao dao;

	@Autowired
	private ImageDao imagedao;

	private static final String ImageCate = "club";

	public List<Club> getNearAt(double x, double y, int page, Integer size) {
		List<Club> aList = dao.getNearAt(new PageRequest(page, size), x, y);
		for (Club club : aList) {
			double dis1 = PublicHelper.distance(x, y, club.getX(), club.getY());
			club.setDistance(dis1);
		}
		return aList;
	}

	public int getCount() {
		return dao.getCount();
	}

	public Club getById(int id) {
		Club club = dao.findOne(id);
		club.setImages(imagedao.getOfImages(id, ImageCate));
		return club;
	}

	public void delete(Integer id) {
		Club club = dao.findOne(id);
		club.setStatus(-1);
		dao.save(club);
	}

}
