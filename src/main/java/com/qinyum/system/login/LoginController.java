package com.qinyum.system.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.utils.StrKit;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private String PREFIX = "/system/login";

	@RequestMapping()
	public String index(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,Model model) {
		if(StrKit.notBlank(error)) {
			model.addAttribute("error", "登录失败用户名或密码错误");
		}
		
		if(StrKit.notBlank(logout)) {
			model.addAttribute("logout", "退出登录");
		}
		
		return PREFIX+"/login";
	}
}
