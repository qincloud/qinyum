package com.qinyum.common.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import com.qinyum.system.logs.model.SysLogs;
import com.qinyum.system.logs.service.SysLogService;
import com.qinyum.system.user.model.SysUser;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SysLogService logService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		SysUser user = (SysUser) authentication.getPrincipal();
		// 输出登录提示信息
		System.out.println("用户名： " + user.getUsername());
		System.out.println("用户密码： " + authentication.getCredentials());
		System.out.println("角色列表：" + authentication.getAuthorities());
		System.out.println("IP信息 :" + ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress());
		System.out.println("是否被授权 :" + authentication.isAuthenticated());

		SysLogs logs = new SysLogs();
		logs.setLoguser(user.getUsername());
		logs.setIpaddr(((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress());
		logService.save(logs);
		
		redirectStrategy.sendRedirect(request, response, "/");
	}

}
