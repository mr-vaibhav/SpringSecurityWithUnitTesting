package com.security.app.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.security.app.controller.LoginController;

public class LoginUserDetails implements UserDetails {

	private UserLogin userLogin;
	private static final Logger logger = LoggerFactory.getLogger(LoginUserDetails.class);
	
	
	public LoginUserDetails(UserLogin userLogin) {
		super();
		this.userLogin = userLogin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		logger.info("Inside Grant Authority");
		 return userLogin.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getRole().toString())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return userLogin.getPassword();
	}

	@Override
	public String getUsername() {
		return userLogin.getUsername();
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
	  public UserLogin getUserDetails() {	
	        return userLogin;
	    }
	
}
