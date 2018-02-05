//package com.dfyy.web.authentication;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.dfyy.bussiness.User;
//
//public final class NzdUserDetails extends User implements UserDetails {
//	private static final long serialVersionUID = -3405289086051043887L;
//	private int count;
//
//	public NzdUserDetails(User user) {
//		setId(user.getId());
//		setPhone(user.getPhone());
//		setPassword(user.getPassword());
//		setStatus(user.getStatus());
//	}
//
//	public NzdUserDetails(User user, int c) {
//		setId(user.getId());
//		setPhone(user.getPhone());
//		setPassword(user.getPassword());
//		setStatus(user.getStatus());
//		count = c;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return AuthorityUtils.createAuthorityList("NZD");
//	}
//
//	@Override
//	public String getUsername() {
//		return getPhone();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//
//	public int getCount() {
//		return count;
//	}
//
//	public void setCount(int count) {
//		this.count = count;
//	}
//}
