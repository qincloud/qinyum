package com.qinyum.common.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.stereotype.Component;

import com.qinyum.system.user.model.SysUser;

@Component
public class UserUtils {
	
	@Bean    
	public SessionRegistry sessionRegistry(){     
	    return new SessionRegistryImpl();    
	}  
	
	@Autowired
	private SessionRegistry sessionRegistry;//这个类会自动注入 不用我们自己去手动注入

	private static UserUtils  userUtils;
	
	@PostConstruct
	public void init() {
		userUtils = this;
		userUtils.sessionRegistry = this.sessionRegistry;
	}
	
	/**
	 * 获得用户id
	 * */
	public static String getId() {
		return getUser().getId();
	}
	
	/**
	 * 获得用户名
	 * */
	public static String getUsername() {
        return getUser()==null ? "" : getUser().getUsername();
	}
	
	/**
	 * 获得用户email
	 * */
	public static String getEmail() {
		return getUser().getEmail();
	}
	
	/**
	 * 获得用户图片路径
	 * */
	public static String getPicture() {
		return getUser().getPicture();
	}
	
	/**
	 * 获得当前用户
	 * */
	public static SysUser getUser() {
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(object instanceof SysUser) {
			return (SysUser)object;	
		}else {
			SysUser user = new SysUser();
			return user;
		}
	}
	
	/**
	 * 获得所有用户
	 * */
	public static List<SysUser> getUserAll(){
		List<SysUser> lists = new ArrayList<SysUser>();
		List<Object> allPrincipals = userUtils.sessionRegistry.getAllPrincipals();
		for(Object object : allPrincipals) {
			lists.add((SysUser)object);
			System.out.println((SysUser)object);
		}
		return lists;
	}
}
