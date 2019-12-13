package com.security.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.security.app.model.UserLogin;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer>{

	UserLogin getUserByUsername(String username);

}
