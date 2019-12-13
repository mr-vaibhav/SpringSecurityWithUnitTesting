package com.security.app.model;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class LoginUserDetails implements UserDetails {

	private UserLogin userLogin;
	
	
	
	public LoginUserDetails(UserLogin userLogin) {
		super();
		this.userLogin = userLogin;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 return userLogin.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName().toString())).collect(Collectors.toList());
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
