package com.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.security.app.model.Authority;

public interface UserAuthorityRepository  extends JpaRepository<Authority, Integer>{
	
	Authority findByRole(String role);

}
