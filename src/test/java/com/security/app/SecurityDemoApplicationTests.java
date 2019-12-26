package com.security.app;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.security.app.model.UserLogin;
import com.security.app.repository.UserAuthorityRepository;
import com.security.app.repository.UserLoginRepository;
import com.security.app.service.SecureLoginService;

@SpringBootTest
class SecurityDemoApplicationTests {

//	@Test
//	void contextLoads() {
//	}
	
	@Autowired
	SecureLoginService secureLoginService;
	
	@Autowired
	UserLoginRepository userLoginRepository;
	
	@Autowired
	UserAuthorityRepository userAuthorityRepository;
	
//	@Autowired
//	UserLoginCustomRepository userLoginCustomRepository;
//	
	private static final Logger logger = LoggerFactory.getLogger(SecurityDemoApplicationTests.class);
	
//	@Test
//	public void signupUserTest() {
//		
//		
//		UserLogin userLogin = new UserLogin();
//		//UserLogin userLogin = null;
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String password = "Pranav@123";
//		userLogin.setUsername("Pranav");
//		userLogin.setPassword(encoder.encode(password));
//		userLogin.setPasswordConfirm(encoder.encode(password));
//		userLogin.setUserMail("vaibs999patil@gmail.com");
//		userLogin.setRole("ROLE_USER");
//		logger.info(userLogin.getRole());
//		
//		//Authority authority =userAuthorityRepository.findByRole("user");
//		//userLogin.setAuthorities((new HashSet<Authority>(Arrays.asList(authority))));
//		
//		//Mockito.when(userLoginRepository.save(userLogin)).thenReturn(userLogin);
//		
//		
//		secureLoginService.signupUser(userLogin);
//			
//	}
	
	@Test
	public void mailSenderTest() {
		secureLoginService.mailSender("vaibs999patil@gmail.com");
		
	}

}
