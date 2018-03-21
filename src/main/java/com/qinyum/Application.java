package com.qinyum;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;

@SpringBootApplication
@ComponentScan(basePackages={"com.qinyum"},includeFilters={@Filter(org.springframework.stereotype.Controller.class)})
@MapperScan(basePackages = {"com.qinyum.*.*.mapper","com.qinyum.*.*.*.mapper","com.qinyum.*.*.*.*.mapper"})
public class Application extends SpringBootServletInitializer {
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	

}
 