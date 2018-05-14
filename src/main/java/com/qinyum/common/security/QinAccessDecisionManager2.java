package com.qinyum.common.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.qinyum.system.menu.mapper.SysMenuMapper;
import com.qinyum.system.menu.model.SysMenu;

/**
 * Created by liaozq on 18/5/13
 *  */
@Service
public class QinAccessDecisionManager2 implements AccessDecisionManager {

	@Autowired
	private SysMenuMapper menuMapper;

	// decide 方法是判定是否拥有权限的决策方法
	@PreAuthorize("isAuthenticated()") //没有登陆不允许访问
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		System.out.println("start decide");
//		if (SecurityContextHolder.getContext().getAuthentication() == null
//				 && !SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//			
//		}
		
		if(UserUtils.getUsername().equals("admin")) { //超级管理员不用验证直接返回
			return;
		}
	
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		AntPathRequestMatcher matcher;
	
		Set<String> urls = new HashSet<String>();
		for (GrantedAuthority ga : authentication.getAuthorities()) { //迭代登陆用户所拥有的角色
			SimpleGrantedAuthority urlGrantedAuthority = (SimpleGrantedAuthority) ga;
			List<SysMenu> menus = menuMapper.findMenuByRoleEname(urlGrantedAuthority.getAuthority());
			for(SysMenu menu : menus) {
				urls.add(menu.getHref()); //将登陆用户拥有所角色全部的资源全部添加到urls
			}
		}

		String requrl = request.getRequestURI();
		for(String url : urls) {
//			matcher = new AntPathRequestMatcher(url); 
//			if (matcher.matches(request)) { //request是请求的url,判断该请求是不是登陆用户拥有的url
//				return;
//			}
			if(requrl.startsWith(url)) {
				return;
			}
		}
		throw new AccessDeniedException("您无权访问该资源");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}
