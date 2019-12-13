package com.security.app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.security.app.service.SecureLoginService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public UserDetailsService userDetailsService() {
		return new SecureLoginService();
	}

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		//System.out.println("inside DaoAuthenticationProvider");
		logger.info("inside DaoAuthenticationProvider");
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		 authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		//System.out.println("inside AuthenticationManagerBuilder");
		logger.info("inside AuthenticationManagerBuilder");
		auth.authenticationProvider(authenticationProvider());
	}

	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/firstPage").hasAnyRole("ADMIN", "USER").and().authorizeRequests()
				.antMatchers("/login", "/resource/**").permitAll().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/doLogin")
				.usernameParameter("username").passwordParameter("password").permitAll()
				.defaultSuccessUrl("/postLogin",true)
				.successForwardUrl("/postLogin").failureUrl("/loginFailed").and()
				.logout().logoutUrl("/doLogout").logoutSuccessUrl("/logout").permitAll().and().csrf().disable();
	}

//	@Override
//	public  void configure(AuthenticationManagerBuilder auth) throws Exception {
//		System.out.println("in configure AuthenticationManagerBuilder");
//		auth.inMemoryAuthentication().withUser("Gaurav")
//				.password("{noop}Gaurav@123").roles("USER");
//	}
//	
//	@Override
//	public void configure(HttpSecurity http) throws Exception {
//		System.out.println("in configure HttpSecurity");
//		http.authorizeRequests()
//			.antMatchers("/login")
//				.permitAll()
//				.antMatchers("/**")
//				.hasAnyRole("USER")
//			.and()
//				.formLogin()
//				.loginPage("/login.jsp")
//				.defaultSuccessUrl("/welcome",true)
//				
//				.failureUrl("/login.jsp?error=1")//.loginProcessingUrl("/welcome").successForwardUrl("/welcome")
//				.permitAll()
//			.and()
//				.logout()
//				.logoutSuccessUrl("/welcome.jsp");
//	}
}
