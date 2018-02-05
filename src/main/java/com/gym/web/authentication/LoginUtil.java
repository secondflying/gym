package com.gym.web.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class LoginUtil {

	public static AdminUserDetails getLoginAdmin() {
		Authentication login = SecurityContextHolder.getContext().getAuthentication();
		if (login != null) {
			AdminUserDetails user = (AdminUserDetails) login.getPrincipal();
			return user;
		}
		return null;
	}

}
