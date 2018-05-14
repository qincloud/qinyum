package com.qinyum.system.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.security.UserUtils;

@Controller
@RequestMapping(value="/")
public class IndexController extends BaseController {

	@RequestMapping()
	public String index(Model model) {
		return "/index";
	}
	
	@RequestMapping("f403")
	public String f403() {
		return "/403";
	}
	
	@RequestMapping("f404")
	public String f404() {
		return "/404";
	}
	
	@RequestMapping("f500")
	public String f500() {
		return "/500";
	}
}
