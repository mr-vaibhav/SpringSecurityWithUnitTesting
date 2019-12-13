package com.security.app.service;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.app.controller.LoginController;
import com.security.app.customexception.RecordNotFoundException;
import com.security.app.model.Authority;
import com.security.app.model.LoginUserDetails;
import com.security.app.model.UserLogin;
import com.security.app.repository.UserLoginRepository;

@Service
public class SecureLoginService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(SecureLoginService.class);
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws RecordNotFoundException {
		UserLogin userLogin=userLoginRepository.getUserByUsername(username);
    	if (userLogin == null) {
            throw new RecordNotFoundException("User not found.");
        }
        
        return new LoginUserDetails(userLogin);
	}

	public UserLogin signupUser(UserLogin userLogin) {
		UserLogin user = null;
		try {
			 user = userLoginRepository.save(userLogin);
		}
		catch(RecordNotFoundException e) {
			logger.error("Enter unique name" + e);
		}
		if(user!=null) {
			SimpleMailMessage message = new SimpleMailMessage();
			if (userLogin.getUserMail() != null) {

				message.setTo(userLogin.getUserMail());
				message.setSubject("Sign up Details");
				message.setText("The Entered Details by User : "+userLogin.getUsername()+ " With Password : "+userLogin.getPasswordConfirm());
				javaMailSender.send(message);
			}
		}
		return user;
	}
}
