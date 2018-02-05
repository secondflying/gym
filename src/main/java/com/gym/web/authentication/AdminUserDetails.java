package com.gym.web.authentication;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.gym.sysconfig.entity.Admin;

public class AdminUserDetails implements UserDetails {

	private static final long serialVersionUID = 6763082771785222816L;

	private Integer id;
	private String name;
	private String password;
	private Integer status;

	public AdminUserDetails(Admin admin) {
		setId(admin.getId());
		setName(admin.getName());
		setPassword(admin.getPassword());
		setStatus(admin.getStatus());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ADMIN");
	}

	@Override
	public String getUsername() {
		return getName();
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
