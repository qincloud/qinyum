package com.qinyum.system.dict.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.utils.Result;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.dict.model.SysDict;
import com.qinyum.system.dict.service.SysDictService;

/**
 * @author lzq996298643
 * @date 2017-03-23
 * @description:系统字典
 */

@Controller
@RequestMapping("/system/dict")
public class SysDictController extends BaseController {
	private static final String PREFIX = "system/dict/";

	@Autowired
	private SysDictService service;

	@RequestMapping()
	public String index(SysDict SysDict) {
		setAttribute("pageInfo", service.query(SysDict));
		setAttribute("queryParam", SysDict);
		setAttribute("page", SysDict.getPage());
		setAttribute("rows", SysDict.getRows());
		setAttribute("model", SysDict);
		return PREFIX + "dict_list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		model.put("time", new Date());
		model.put("message", "this springboot hello");
		return PREFIX + "dict_add";
	}

	@GetMapping("/edit")
	public String edit() {
		String id = getParameter("id");
		SysDict model = service.findById(id);
		setAttribute("model", model);
		return PREFIX + "dict_add";
	}

	@PostMapping("/delete")
	public Result delete() {
		String id = getParameter("id");
		return service.delete(service.findById(id));

	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Result saveorupdate(SysDict menu) {
		int ok = 0;
		if (StrKit.isBlank(menu.getId())) {
			return service.save(menu);
		} else {
			return service.update(menu);
		}

	}
}
