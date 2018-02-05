package com.gym.web.authentication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.gym.sysconfig.entity.Admin;
import com.gym.sysconfig.service.AdminService;

@Component
public class AdminDetailsService implements UserDetailsService {

	@Autowired
	private AdminService adminService;

	@Autowired
	private HttpServletRequest request;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Admin admin = adminService.getByName(username);
		if (admin == null) {
			throw new UsernameNotFoundException("用户不存在");
		}

		return new AdminUserDetails(admin);

	}

}
