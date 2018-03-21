package com.qinyum.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.qinyum.common.interceptor.MenuInterceptor;

@Configuration
public class QinWebMvcConfigurer extends WebMvcConfigurerAdapter {

	@Bean
	MenuInterceptor menuInterceptor() {
		return new MenuInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(menuInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
		//
		super.addInterceptors(registry);
	}
}
