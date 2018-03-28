package com.gym.circle.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.circle.dao.CircleDao;
import com.gym.circle.dao.CircleInfoDao;
import com.gym.circle.dao.CommentDao;
import com.gym.circle.dao.ThumbsupDao;
import com.gym.circle.entity.Circle;
import com.gym.circle.entity.CircleInfo;
import com.gym.circle.entity.Comment;
import com.gym.circle.entity.Thumbsup;
import com.gym.common.dao.ImageDao;
import com.gym.common.entity.Image;
import com.gym.user.dao.UserDao;
import com.gym.user.dto.SimpleUserDto;
import com.gym.user.entity.User;

@Service
@Transactional
public class CircleService {

	Logger logger = LoggerFactory.getLogger(CircleService.class);

	@Autowired
	private CircleDao dao;
	
	@Autowired
	private CircleInfoDao infoDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ThumbsupDao thumbsupDao;
	
	@Autowired
	private CommentDao commentDao;

	@Autowired
	private ImageDao imagedao;
	
	private static final String ImageCate = "circle";
	
	public Circle publish(int userId, String content, Double lng, Double lat, String urls) {
		try {
			Circle circle = new Circle();
			circle.setStatus(0);
			circle.setCreateTime(new Date());
			circle.setUserId(userId);
			circle.setContent(content);
			circle.setLng(lng);
			circle.setLat(lat);
			circle = dao.save(circle);
			
			if(urls != null) {
				String[] arr = urls.split(",");
				for(int i=0;i<arr.length;i++) {
					Image image = new Image();
					image.setCid(circle.getId());
					image.setUrl(arr[i]);
					image.setCate(ImageCate);
					imagedao.save(image);
				}
			}
			
			return circle;
		} catch (Exception e) {
			logger.error("发布圈子失败", e);
			throw new RuntimeException("发布圈子失败", e);
		}
	}
	
	public List<CircleInfo> list(int page, int size){
		try {
			List<CircleInfo> circles = infoDao.findByPage(new PageRequest(page, size));
			for(CircleInfo c : circles) {
				c.setImages(imagedao.getOfImages(c.getId(), ImageCate));
				SimpleUserDto userDto = this.getUser(c.getUserId());
				c.setUser(userDto);
				c.setClickNum(thumbsupDao.getCountByCircleId(c.getId()));
				c.setCommentNum(commentDao.getCountByCircleId(c.getId()));
				List<Thumbsup> thumbsups = thumbsupDao.findByCircleId(c.getId());
				for(Thumbsup thumbsup: thumbsups) {
					SimpleUserDto userDto1 = this.getUser(thumbsup.getUserId());
					thumbsup.setUser(userDto1);
				}
				List<Comment> comments = commentDao.findByCircleId(c.getId());
				for(Comment comment: comments) {
					SimpleUserDto userDto2 = this.getUser(comment.getUserId());
					comment.setUser(userDto2);
				}
				c.setThumbsups(thumbsups);
				c.setComments(comments);
			}
			return circles;
		} catch (Exception e) {
			logger.error("获取圈子列表失败", e);
			throw new RuntimeException("获取圈子列表失败", e);
		}
	}
	
	public CircleInfo detail(int id) {
		try {
			CircleInfo info = infoDao.findOne(id);
			info.setImages(imagedao.getOfImages(info.getId(), ImageCate));
			
			SimpleUserDto userDto = this.getUser(info.getUserId());
			info.setUser(userDto);
			
			info.setClickNum(thumbsupDao.getCountByCircleId(info.getId()));
			info.setCommentNum(commentDao.getCountByCircleId(info.getId()));
			List<Thumbsup> thumbsups = thumbsupDao.findByCircleId(info.getId());
			for(Thumbsup thumbsup: thumbsups) {
				SimpleUserDto userDto1 = this.getUser(thumbsup.getUserId());
				thumbsup.setUser(userDto1);
			}
			List<Comment> comments = commentDao.findByCircleId(info.getId());
			for(Comment comment: comments) {
				SimpleUserDto userDto2 = this.getUser(comment.getUserId());
				comment.setUser(userDto2);
			}
			info.setThumbsups(thumbsups);
			info.setComments(comments);
			return info;
		} catch (Exception e) {
			logger.error("获取圈子详情失败", e);
			throw new RuntimeException("获取圈子详情失败", e);
		}
	}
	
	public void delete(int id) {
		try {
			Circle circle = dao.findOne(id);
			if(circle != null) {
				dao.delete(circle);
				//删除关联图片
				List<Image> images = imagedao.getOfImages(circle.getId(), ImageCate);
				for(Image image : images) {
					imagedao.delete(image);
				}
				//删除圈子评论
				List<Comment> comments = commentDao.findByCircleId(circle.getId());
				for(Comment comment : comments) {
					commentDao.delete(comment);
				}
				//删除圈子点赞
				List<Thumbsup> thumbsups = thumbsupDao.findByCircleId(circle.getId());
				for(Thumbsup thumbsup : thumbsups) {
					thumbsupDao.delete(thumbsup);
				}
			}
		} catch (Exception e) {
			logger.error("圈子删除失败", e);
			throw new RuntimeException("圈子删除失败", e);
		}
	}
	
	public SimpleUserDto getUser(int id) {
		User user = userDao.findOne(id);
		SimpleUserDto userDto = new SimpleUserDto();
		userDto.setName(user.getName());
		userDto.setNickname(user.getNickname());
		userDto.setImages(imagedao.getOfImages(id, "user"));
		return userDto;
	}
	
	public int getCount() {
		try {
			return infoDao.getCount();
		} catch (Exception e) {
			logger.error("获取圈子数量异常", e);
			throw new RuntimeException("获取圈子数量异常", e);
		}
	}
	
}
