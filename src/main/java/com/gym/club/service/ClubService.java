package com.gym.club.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.club.dao.ClubDao;
import com.gym.club.entity.Club;
import com.gym.common.dao.ImageDao;
import com.gym.support.QueryParamUtil;
import com.gym.support.QuerySpecification;
import com.gym.user.dao.CoachDao;
import com.gym.user.dto.CoachDto;
import com.gym.user.entity.Coach;
import com.gym.util.PublicHelper;

@Service
@Transactional
public class ClubService {
	
	Logger logger = LoggerFactory.getLogger(ClubService.class);

	@Autowired
	private ClubDao dao;

	@Autowired
	private ImageDao imagedao;
	
	@Autowired
	private CoachDao coachDao;

	private static final String ImageCate = "club";

	public List<Club> getNearAt(double x, double y, int page, Integer size) {
		try {
			List<Club> aList = dao.getNearAt(new PageRequest(page, size), x, y);
			for (Club club : aList) {
				double dis1 = PublicHelper.distance(x, y, club.getX(), club.getY());
				club.setDistance(dis1);
			}
			return aList;
		} catch (Exception e) {
			logger.error("获取附近健身房失败", e);
			throw new RuntimeException("获取附近健身房失败", e);
		}
	}

	public int getCount() {
		try {
			return dao.getCount();
		} catch (Exception e) {
			logger.error("获取健身房数量异常", e);
			throw new RuntimeException("获取健身房数量异常", e);
		}
	}

	public Club getById(int id) {
		try {
			Club club = dao.findOne(id);
			club.setImages(imagedao.getOfImages(id, ImageCate));
			List<Coach> list = coachDao.findByClubId(id);
			List<CoachDto> coachs = new ArrayList<CoachDto>();
			for(Coach coach: list) {
				CoachDto coachDto = new CoachDto();
				coachDto.setAge(coach.getAge());
				coachDto.setBrief(coach.getBrief());
				coachDto.setHeight(coach.getHeight());
				coachDto.setName(coach.getName());
				coachDto.setPhone(coach.getPhone());
				coachDto.setSex(coach.getSex());
				coachDto.setWeight(coach.getWeight());
				coachs.add(coachDto);
			}
			club.setCoachs(coachs);
			return club;
		} catch (Exception e) {
			logger.error("获取健身房详情失败", e);
			throw new RuntimeException("获取健身房详情失败", e);
		}
	}

	public void delete(int id) {
		try {
			Club club = dao.findOne(id);
			club.setStatus(-1);
			dao.save(club);
		} catch (Exception e) {
			logger.error("删除健身房失败", e);
			throw new RuntimeException("删除健身房失败", e);
		}
	}

	public void save(Club club) {
		try {
			club.setStatus(0);
			dao.save(club);
		} catch (Exception e) {
			logger.error("新增健身房失败", e);
			throw new RuntimeException("新增健身房失败", e);
		}
	}
	
	public void edit(Club club) {
		try {
			club.setStatus(0);
			dao.save(club);
		} catch (Exception e) {
			logger.error("编辑健身房失败", e);
			throw new RuntimeException("编辑健身房失败", e);
		}
	}
	
	public Page<Club> queryListFilter(String filterParam, String sortParam, int start, int limit) {
		Page<Club> results = dao.findAll(new QuerySpecification<Club>(filterParam),
				new PageRequest(start, limit, QueryParamUtil.parseSortParams(sortParam)));
		return results;
	}
}
