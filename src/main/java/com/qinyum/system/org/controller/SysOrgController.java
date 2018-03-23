package com.qinyum.system.org.controller;

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
import com.qinyum.system.org.model.SysOrg;
import com.qinyum.system.org.service.SysOrgService;

@Controller
@RequestMapping("system/org")
public class SysOrgController extends BaseController {
	private static final String PREFIX = "system/org/";


	
	@Autowired
	private SysOrgService service;

	@RequestMapping()
	public String index(SysOrg SysOrg) {
		setAttribute("pageInfo", service.query(SysOrg));
		setAttribute("queryParam", SysOrg);
		setAttribute("page", SysOrg.getPage());
		setAttribute("rows", SysOrg.getRows());
		setAttribute("model", SysOrg);
		return PREFIX + "org_list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		model.put("time", new Date());
		model.put("message", "this springboot hello");
		return PREFIX + "org_add";
	}

	@GetMapping("/edit")
	public String edit() {
		String id = getParameter("id");
		SysOrg model = service.findById(id);
		setAttribute("model", model);
		return PREFIX + "org_add";
	}

	@PostMapping("/delete")
	public Result delete() {
		String id = getParameter("id");
		return service.delete(service.findById(id));

	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Result saveorupdate(SysOrg menu) {
		int ok = 0;
		if (StrKit.isBlank(menu.getId())) {
			return service.save(menu);
		} else {
			return service.update(menu);
		}

	}
}
