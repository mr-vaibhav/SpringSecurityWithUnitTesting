package com.security.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.security.app.model.UserLogin;

public interface UserLoginRepository extends JpaRepository<UserLogin, Integer>{

	UserLogin getUserByUsername(String username);

}
