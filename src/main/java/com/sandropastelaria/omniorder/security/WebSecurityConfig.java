package com.sandropastelaria.omniorder.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/resources/**").permitAll()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("user").password("password").roles("mesa");
	}
	
}
