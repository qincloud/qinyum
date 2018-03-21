package com.qinyum.common.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.qinyum.system.user.model.SysUser;
import com.qinyum.system.user.service.SysUserService;

@Component
public class CustAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private SysUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		SysUser userDetials = null;
		try {
			userDetials = (SysUser) userService.loadUserByUsername(username);
		} catch (Exception e) {
			return null;
		}
		Collection<? extends GrantedAuthority> authorities = userDetials.getAuthorities();
		return new UsernamePasswordAuthenticationToken(userDetials, password, authorities);
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}