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
}
