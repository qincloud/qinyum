package com.qinyum.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.qinyum.common.security.CustAuthenticationProvider;

@Configuration 
@EnableWebSecurity 
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustAuthenticationProvider custProvider;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.authenticationProvider(custProvider);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests()
	    .antMatchers("/login","/tmp/**").permitAll()
	    .anyRequest().authenticated().and().formLogin()
	    .loginPage("/login").permitAll().loginProcessingUrl("/loginCheck").permitAll()
	    .failureUrl("/login?error").permitAll().defaultSuccessUrl("/").permitAll()
	    .and().logout().logoutSuccessUrl("/login?logout").permitAll();
	}
}
