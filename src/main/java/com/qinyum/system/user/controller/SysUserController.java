package com.qinyum.system.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.utils.Result;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.role.model.SysRole;
import com.qinyum.system.role.service.SysRoleService;
import com.qinyum.system.user.model.SysUser;
import com.qinyum.system.user.service.SysUserService;

@Controller
@RequestMapping(value = "/system/user")
public class SysUserController extends BaseController {
	public static final String prefix = "/system/user";

	@Autowired
	private SysUserService service;

	@Autowired
	private SysRoleService roleservice;

	@RequestMapping()
	public String index(SysUser user) {
		setAttribute("pageInfo", service.query(user));
		setAttribute("queryParam", user);
		setAttribute("page", user.getPage());
		setAttribute("rows", user.getRows());
		setAttribute("model", user);
		return prefix + "/sys_user";
	}

	@RequestMapping(value = "/add")
	public String add(Model model) {
		List<SysRole> roles = roleservice.findAll();
		model.addAttribute("roles", roles);
		return prefix + "/user_add";
	}

	@RequestMapping(value = "/edit")
	public String edit(Model model) {
		SysUser user = service.findById(getParameter("id"));
		setAttribute("model", user);
		List<SysRole> roles = roleservice.findAll();
		model.addAttribute("roles", roles);
		return prefix + "/user_add";
	}

	@RequestMapping("/saveorupdate")
	@ResponseBody
	public Result saveorupdate(SysUser user) {
		if (StrKit.notBlank(user.getId())) {
			return service.update(user);
		} else {
			return service.save(user);
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Result delete() {
		String id = getParameter("id");
		return service.delete(service.findById(id));
	}

	@RequestMapping(value = "/deleteall", method = RequestMethod.POST)
	@ResponseBody
	public Result deleteall() {
		try {
			String ids = getParameter("ids");
			String[] is = ids.split(",");
			for (String id : is) {
				service.delete(service.findById(id));
			}
		} catch (Exception e) {
			return Result.build(500, "删除失败");
		}
		return Result.build(200, "删除成功");

	}

}
