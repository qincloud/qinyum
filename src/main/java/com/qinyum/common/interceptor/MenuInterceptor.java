package com.qinyum.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qinyum.common.security.UserUtils;
import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.menu.service.SysMenuService;

public class MenuInterceptor implements HandlerInterceptor {
	@Autowired
	private SysMenuService mservcice;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	@PreAuthorize("isAuthenticated()") //没有登陆不允许访问
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 判断是否登陆
		if (SecurityContextHolder.getContext().getAuthentication() != null
				 && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
				/*
				&& !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)*/) {
			String method = request.getMethod();
			if (method.equals("GET")) {
				String username = UserUtils.getUsername();
				// if(StrKit.isBlank(username)) {
				// redirectStrategy.sendRedirect(request,response,"/login");
				// return false;
				// }
				if (username.equals("admin")) {
					List<SysMenu> ms = mservcice.findByChild("0");
					request.setAttribute("ms", ms);
				} else {
					List<SysMenu> ms = mservcice.findMenuByUserid(UserUtils.getId());
					request.setAttribute("ms", ms);
					for (SysMenu m : ms) {
						System.out.println(m.getName());
					}
				}
				request.setAttribute("username", username);
				String email = UserUtils.getEmail();
				request.setAttribute("email", email);
			}
		}else {
			redirectStrategy.sendRedirect(request,response,"/login");
			return false;
		}

		return true;
	}
}
