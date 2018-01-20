package com.nightingale.web.security.user;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nightingale.app.entity.User;

public class CustomUserDetails implements UserDetails {
	
	private static final long serialVersionUID = 2158960867474820449L;
	private User user;
			
	
	private Collection<? extends GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

	public CustomUserDetails(User user,Collection<? extends GrantedAuthority> authorities){
		this.user=user;
		this.authorities=authorities;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
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
		return user.getEnabled();
	}			

	
}

