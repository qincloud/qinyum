package com.qinyum.system.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.utils.Result;

@Controller
@RequestMapping("system/org")
public class SysOrgController extends BaseController {
	private static final String PREFIX = "system/org";


	
	@RequestMapping()
	public String index() {
		return PREFIX + "/org_list";
	}

	@RequestMapping("/add")
	public String add() {
		return PREFIX + "/org_add";
	}

	@RequestMapping("/edit")
	public String edit() {
		return PREFIX + "/";
	}

	@RequestMapping("/saveorupdate")
	public Result saveorupdate() {
		return Result.build(200, "");
	}
}
