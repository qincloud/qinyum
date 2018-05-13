package com.qinyum.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.menu.service.SysMenuService;
import com.qinyum.system.user.model.SysUser;
import com.qinyum.system.user.service.SysUserService;

//@Component
//public class QinUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private SysUserService userService;
//
//	@Autowired
//	private SysMenuService menuService;
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<QinGrantedAuthority> auths = new ArrayList<QinGrantedAuthority>();
//		SysUser user = userService.findByLogname(username);
//		if (user != null) {
//			List<SysMenu> menus = menuService.findMenuByUserid(user.getId());
//			for (SysMenu menu : menus) {
//				QinGrantedAuthority qa = new QinGrantedAuthority(menu.getHref(), "ALL");
//				auths.add(qa);
//			}
//			return new User(user.getUsername(), user.getPassword(), auths);
//		}
//		else {
//			return null;
//		}
//	}
//
//}
