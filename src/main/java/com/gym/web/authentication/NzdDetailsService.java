//package com.dfyy.web.authentication;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import com.dfyy.bussiness.User;
//import com.dfyy.service.UserService;
//
//@Component
//public class NzdDetailsService implements UserDetailsService {
//
//	@Autowired
//	private UserService userService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userService.getNzdByName(username);
//		if (user == null) {
//			throw new UsernameNotFoundException("用户不存在");
//		}
//		return new NzdUserDetails(user);
//	}
//
//}
