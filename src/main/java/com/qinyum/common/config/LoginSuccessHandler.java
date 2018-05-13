package com.qinyum.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.qinyum.system.user.model.SysUser;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	 private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SysUser user = (SysUser) authentication.getPrincipal();
		// 输出登录提示信息
		System.out.println("用户名： " + user.getUsername());

		System.out.println("用户密码： " + authentication.getCredentials());

		System.out.println("角色列表：" + authentication.getAuthorities());

		System.out.println("IP信息 :" + authentication.getDetails());

		System.out.println("是否被授权 :" + authentication.isAuthenticated());
		redirectStrategy.sendRedirect(request,response,"/");
	}

}
