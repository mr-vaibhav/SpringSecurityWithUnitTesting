package com.security.app.controller;




import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.security.app.customexception.RecordNotFoundException;
import com.security.app.model.UserLogin;
import com.security.app.service.SecureLoginService;



@RestController
//@RequestMapping("/LoginController")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	SecureLoginService secureLoginService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
		//System.out.println("In /login controller");
		logger.info("In /login controller");
		
        return "login";
    }
	
	
	@RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public String postLogin(@Valid Model model, HttpSession session) {
       // System.out.println("in to the post login controller");
        logger.info("in to the post login controller");
        return "welcome";
    }
	
	
	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public ModelAndView loginError(@Valid Model model) {
      // System.out.println(" In controller with url /loginFailed");
		logger.info("In controller with url /loginFailed");
		try {  
		int a = 5/0;
		}
		catch(Exception e) {
			logger.error("Error while 5/0",e);
		}
        return new ModelAndView("welcome");
    }
	
	@RequestMapping(value = "/signupUser", method = RequestMethod.POST)
	public ModelAndView SignupUser(@Valid @ModelAttribute("userLogin") UserLogin userLogin) {
		logger.info("inside the signup");
		
		if(userLogin.getPassword().equals(userLogin.getPasswordConfirm())) {
		userLogin.setPassword(bCryptPasswordEncoder.encode(userLogin.getPassword()));
		UserLogin user = secureLoginService.signupUser(userLogin);
		return new ModelAndView("signupSuccess");	
		}
		return new ModelAndView("signup");
		
		
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView Signup() {
      // System.out.println(" In controller with Sign up");
       logger.info("In controller with Sign up");
        return new ModelAndView("signup");
    }
	
	
	

	
	
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
//	public ModelAndView welcomePage() {
//		System.out.println("in welcome Controller with view return");
//		return new ModelAndView("welcome");
//	}
//	
	
	

}
