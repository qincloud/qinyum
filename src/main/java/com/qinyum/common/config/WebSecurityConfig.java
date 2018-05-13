package com.qinyum.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import com.qinyum.common.security.CustAuthenticationProvider;
import com.qinyum.common.security.CustomInvocationSecurityMetadataSourceService;
import com.qinyum.common.security.QinAccessDecisionManager2;
import com.qinyum.common.security.QinLoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	// @Autowired
	// private CustAuthenticationProvider custProvider;
	//
	// @Autowired
	// public void configureGlobal(AuthenticationManagerBuilder auth) throws
	// Exception {
	// auth.authenticationProvider(custProvider);
	// }

	protected void configure(HttpSecurity http) throws Exception {
		http
		.addFilter(filterSecurityInterceptor())
		.authorizeRequests().antMatchers("/login", "/tmp/**").permitAll()
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.loginProcessingUrl("/loginCheck").permitAll().failureUrl("/login?error").permitAll()
				.successHandler(loginSuccessHandler()).permitAll()
				.and().logout().logoutSuccessUrl("/login?logout")
				.permitAll()
				// 注销后使session相关信息无效
				.invalidateHttpSession(true).and()
				// 开启rememberme功能：验证，登录成功后，关闭页面，直接访问登陆后可以访问的页面
				.rememberMe()
				// 设置有效时间
				.tokenValiditySeconds(30 * 60 * 60)
				.and().exceptionHandling()
				.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
	}

	@Bean
	public LoginSuccessHandler loginSuccessHandler() {
		return new LoginSuccessHandler();
	}

	@Bean
	public AuthenticationEntryPoint qinLoginUrlAuthenticationEntryPoint() {
		return new QinLoginUrlAuthenticationEntryPoint("/login");
	}

	@Autowired
	private CustomInvocationSecurityMetadataSourceService mySecurityMetadataSource;

	@Autowired
	private QinAccessDecisionManager2 myAccessDecisionManager;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Bean
	public FilterSecurityInterceptor filterSecurityInterceptor() {
		FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
		filterSecurityInterceptor.setSecurityMetadataSource(mySecurityMetadataSource);
		filterSecurityInterceptor.setAccessDecisionManager(myAccessDecisionManager);
		filterSecurityInterceptor.setAuthenticationManager(authenticationManager);
		return filterSecurityInterceptor;
	}

}
