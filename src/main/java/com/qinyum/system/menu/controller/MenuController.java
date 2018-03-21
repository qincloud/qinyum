package com.qinyum.system.menu.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.security.UserUtils;
import com.qinyum.common.utils.Result;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.menu.service.SysMenuService;

@Controller
@RequestMapping("system/menu")
public class MenuController extends BaseController {
	private String prefix = "system/menu/";

	@Autowired
	private SysMenuService service;

	@RequestMapping()
	public String index(SysMenu sysmenu) {
		setAttribute("pageInfo", service.query(sysmenu));
		setAttribute("queryParam", sysmenu);
		setAttribute("page", sysmenu.getPage());
		setAttribute("rows", sysmenu.getRows());
		setAttribute("model", sysmenu);
		return prefix + "menu_list";
	}

	@GetMapping("/add")
	public String add(ModelMap model) {
		model.put("time", new Date());
		model.put("message", "this springboot hello");
		return prefix + "add";
	}

	@GetMapping("/edit")
	public String edit() {
		String id = getParameter("id");
		SysMenu model = service.findById(id);
		setAttribute("model", model);
		return prefix + "add";
	}

	@PostMapping("/delete")
	public Result delete() {
		String id = getParameter("id");
		return service.delete(service.findById(id));

	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Result saveorupdate(SysMenu menu) {
		int ok = 0;
		if (StrKit.isBlank(menu.getId())) {
			return service.save(menu);
		} else {
			return service.update(menu);
		}

	}

	@RequestMapping(value = "/child", method = RequestMethod.GET)
	@ResponseBody
	public Result childs() {
		String rank = getParameter("rank");
		List<SysMenu> menus = null;
		String username = UserUtils.getUsername();
		if (username.equals("admin")) {
			menus = service.findByChild(rank);
		} else {
			menus = service.findByChild2(rank,UserUtils.getId());
		}
		return Result.ok(menus);
	}

}
