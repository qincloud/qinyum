package com.qinyum.system.dict.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qinyum.common.controller.BaseController;

@Controller
@RequestMapping("/system/dict")
public class SysDictController extends BaseController {
	private static final String PREFIX = "system/dict";

	@RequestMapping()
	public String index() {
		return PREFIX + "/dict_list";
	}
}
