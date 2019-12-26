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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.customexception.RecordNotFoundException;
import com.security.app.model.Authority;
import com.security.app.model.LoginUserDetails;
import com.security.app.model.UserLogin;
import com.security.app.repository.UserAuthorityRepository;
import com.security.app.repository.UserLoginRepository;

@Service
public class SecureLoginService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(SecureLoginService.class);
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws RecordNotFoundException {
		UserLogin userLogin=userLoginRepository.getUserByUsername(username);
		logger.info("Inside loadUserByUsername");
    	if (userLogin == null) {
            throw new RecordNotFoundException("User not found.");
        }       
        return new LoginUserDetails(userLogin);
	}

	@Transactional
	public void signupUser(UserLogin userLogin) {
		Authority authority =userAuthorityRepository.findByRole(userLogin.getRole());
		userLogin.setAuthorities((new HashSet<Authority>(Arrays.asList(authority))));
		UserLogin user = null;
		try {
			 user = userLoginRepository.save(userLogin);
		}
		catch(RecordNotFoundException e) {
			logger.error("Enter unique name" + e);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if(user!=null) {
			if (userLogin.getUserMail() != null) {
				mailSender(userLogin.getUserMail());
			}
		}
		
	}
	
	public void mailSender(String mail) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		
	
			message.setTo(mail);
			message.setSubject("Sign up Details");
			message.setText("You had logging successfully ");
			//message.setText("The Entered Details by User : "+userLogin.getUsername()+ " With Password : "+userLogin.getPasswordConfirm());
			javaMailSender.send(message);
			//return null;
		
	}
	
	
}
