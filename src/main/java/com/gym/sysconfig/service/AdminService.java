package com.gym.sysconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gym.sysconfig.dao.AdminDao;
import com.gym.sysconfig.entity.Admin;

@Service
@Transactional
public class AdminService {

	@Autowired
	private AdminDao dao;


	public List<Admin> getAllAdmins(int page, int size) {
		List<Admin> admins = dao.getAll(new PageRequest(page, size));
		return admins;
	}

	public int getAllCount() {
		return dao.getCount();
	}

	public Admin getByName(String name) {
		Admin admin = dao.findByName(name);
		return admin;
	}

	public Admin getById(Integer id) {
		Admin admin = dao.findOne(id);
		return admin;
	}

	public Admin createAdmin(String name, String password) {
		Admin admin = new Admin();
		admin.setName(name);
		admin.setPassword(password);
		admin.setStatus(1);
		dao.save(admin);
		return admin;
	}

	public void delete(Integer id) {
		Admin admin = dao.findOne(id);
		admin.setStatus(-1);
		dao.save(admin);
	}

	public void updateAdmin(Admin admin) {
		dao.save(admin);
	}
}
