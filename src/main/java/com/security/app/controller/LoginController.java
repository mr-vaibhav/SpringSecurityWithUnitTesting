package com.security.app.controller;




import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.security.app.model.UserLogin;
import com.security.app.service.SecureLoginService;



//@Controller
@RestController
//@RequestMapping("/LoginController")
public class LoginController implements ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final String PATH = "/error";

	@Autowired
	SecureLoginService secureLoginService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	ModelAndView model = new ModelAndView();
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login() {
		logger.info("In /login controller");
		model.setViewName("login");
        return model;
    }
	
	
	@RequestMapping(value = "/postLogin", method = RequestMethod.POST)
    public ModelAndView postLogin() {
        logger.info("in to the post login controller");
        model.setViewName("welcome");
        return model;
    }
	
	
	@RequestMapping(value = "/loginFailed", method = RequestMethod.GET)
    public ModelAndView loginError() {
      	logger.info("In controller with url loginFailed");
		model.setViewName("Fail");
        return model;
    }
	
	@RequestMapping(value = "/signupUser", method = RequestMethod.POST)
	public ModelAndView SignupUser(@Valid @ModelAttribute("userLogin") UserLogin userLogin,BindingResult result ) {
		if(result.hasErrors()) {
			model.setViewName("error");
			return model;
		}
		logger.info("Value Has Been Taken From User");
		if(userLogin.getPassword().equals(userLogin.getPasswordConfirm())) {
		userLogin.setPassword(bCryptPasswordEncoder.encode(userLogin.getPassword()));
		this.secureLoginService.signupUser(userLogin);
		model.setViewName("signupSuccess");
		logger.info("Values Has Been Get set");
		return model;	
		}
		logger.info("password doesn't get match");
		this.model.setViewName("signup");
		return model;		
	}
		
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ModelAndView Signup() {
       logger.info("In controller with Sign up");
       this.model.setViewName("signup");
       return model;
    }

	@RequestMapping(value=PATH,method=RequestMethod.GET)
	public ModelAndView defaultErrorMessage() {
		model.setViewName("error");
		return model;
	}
	
	@Override
	public String getErrorPath() {
		return PATH;
	}
	
	
	

	
	
//	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
//	public ModelAndView welcomePage() {
//		System.out.println("in welcome Controller with view return");
//		return new ModelAndView("welcome");
//	}
//		

}
