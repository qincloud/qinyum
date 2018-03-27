
package com.qinyum.system.role.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qinyum.common.controller.BaseController;
import com.qinyum.common.utils.Result;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.menu.service.SysMenuService;
import com.qinyum.system.role.model.SysRole;
import com.qinyum.system.role.model.SysRoleMenu;
import com.qinyum.system.role.service.SysRoleMenuService;
import com.qinyum.system.role.service.SysRoleService;

@Controller
@RequestMapping(value = "/system/role")
public class SysRoleController extends BaseController {
	public static final String prefix = "/system/role/";

	@Autowired
	private SysRoleService roleservice;

	@Autowired
	private SysRoleMenuService rmservice;

	@Autowired
	private SysMenuService menuservice;

	@RequestMapping()
	public String index(SysRole role) {
		setAttribute("pageInfo", roleservice.query(role));
		setAttribute("queryParam", role);
		setAttribute("page", role.getPage());
		setAttribute("rows", role.getRows());
		setAttribute("model", role);
		return prefix + "role_list";
	}

	/**
	 * 添加页面方法
	 */
	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("flag", "add_page");
		return prefix + "role_add";
	}

	@RequestMapping("/edit")
	public String edit(Model model) {
		String id = getParameter("id");
		setAttribute("model", roleservice.selectByPrimary(id));
		model.addAttribute("flag", "edit_page");
		model.addAttribute("roleid", id);
		return prefix + "role_add";
	}

	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
	@ResponseBody
	public Result saveorupdate(SysRole role) {
		try {
			if (StrKit.isBlank(role.getId())) {
				String uuid = roleservice.saveonly(role);
				if (StrKit.notBlank(uuid)) {
					String ids = getParameter("ids");
					String[] lists = ids.split(",");
					for (String s : lists) {
						SysRoleMenu rm = new SysRoleMenu();
						rm.setRoleid(uuid);
						rm.setMenuid(s);
						rmservice.insert(rm);
					}
				} else {
					throw new Exception();
				}
			} else {
				rmservice.deleteByRoleid(role.getId());
				String ids = getParameter("ids");
				String[] lists = ids.split(",");
				for (String s : lists) {
					SysRoleMenu rm = new SysRoleMenu();
					rm.setRoleid(role.getId());
					rm.setMenuid(s);
					rmservice.insert(rm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Result.build(500, e.getMessage());
		}
		return Result.build(200, "保存成功");
	}

	@GetMapping("/delete")
	public Result delete() {
		String id = getParameter("id");
		roleservice.delete(roleservice.selectByPrimary(id));
		rmservice.deleteByRoleid(id);
		return null;
	}

	public List<Object> rootnodes() {
		List<Object> lists = new ArrayList<Object>();
		List<SysMenu> menus = menuservice.findByChild("0");
		for (SysMenu m : menus) {
			lists.add(nodeschild(m));
		}
		return lists;
	}

	/****************************** 添加页面功能资源树 ******************************/
	@RequestMapping(value = "/nodes", method = RequestMethod.POST)
	@ResponseBody
	public Result nodes() {
		List<Object> lists = new ArrayList<Object>();
		List<SysMenu> menus = menuservice.findByChild("0");
		for (SysMenu m : menus) {
			lists.add(nodeschild(m));
		}
		return Result.build(200, "", lists);
	}

	/**
	 * 一级
	 */
	public Map<String, Object> nodeschild(SysMenu m) {
		ArrayList<Object> nodes = new ArrayList<Object>();
		Map<String, Object> map = (Map<String, Object>) putJson(m);
		List<SysMenu> tms = menuservice.findByChild(m.getId());
		if (tms != null) {
			for (SysMenu t : tms) {
				nodes.add(putJson(m));
			}
		}
		return map;
	}

	/**
	 * 二级
	 */
	public Map<String, Object> putJson(SysMenu m) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("text", m.getName());
		map.put("href", m.getId());
		statemap = new LinkedHashMap<String, Object>();
		statemap.put("expanded", "true");
		map.put("state", statemap);
		Map<String, Object> nodesmap = null;
		List<SysMenu> tms = menuservice.findByChild(m.getId());
		if (tms != null) {
			List<Object> nodes = new ArrayList<Object>();
			for (SysMenu t : tms) {
				nodesmap = new LinkedHashMap<String, Object>();
				nodesmap.put("text", t.getName());
				nodesmap.put("href", t.getId());
				LinkedHashMap<String, Object> nodesstatemap = new LinkedHashMap<String, Object>();
				nodesstatemap.put("expanded", "true");
				nodesmap.put("state", nodesstatemap);
				nodes.add(nodesmap);
			}
			map.put("nodes", nodes);
		}
		return map;
	}

	/****************************** 添加页面功能资源树 ******************************/

	/****************************** 编辑页面功能资源树 ******************************/
	@RequestMapping(value = "/nodes2", method = RequestMethod.POST)
	@ResponseBody
	public Result nodes2() {
		List<Object> lists = new ArrayList<Object>();
		List<SysMenu> menus = menuservice.findByChild("0"); //查询顶级菜单下
		String roleid = getParameter("roleid"); // 获取角色id
		/******************* 以下:将角色下的菜单全部查询出来 *******************/
		List<SysRoleMenu> rms = rmservice.selectByRoleid(roleid);
		List<String> menuids = new ArrayList<String>();
		for (SysRoleMenu rm : rms) {
			menuids.add(rm.getMenuid());
		}
		/******************* 以上:将角色下的菜单全部查询出来 *******************/
		for (SysMenu m : menus) {
			lists.add(nodeschild2(m, menuids));
		}
		return Result.build(200, "", lists);
	}

	/**
	 * 一级
	 */
	public Object nodeschild2(SysMenu m, List<String> menuids) {
		ArrayList<Object> nodes = new ArrayList<Object>();
		Map<String, Object> map = (Map<String, Object>) putJson2(m,menuids);
		List<SysMenu> tms = menuservice.findByChild(m.getId()); //查询各目录下菜单
		if (tms != null) {
			for (SysMenu t : tms) {
				nodes.add(putJson2(t, menuids));
			}
		}
		return map;
	}

	/**
	 * 二级
	 */
	public Map<String, Object> putJson2(SysMenu m, List<String> menuids) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		if (menuids.contains(m.getId())) {
			map.put("text", m.getName());
			map.put("href", m.getId());
			//map.put("selectable", "false");
			statemap = new LinkedHashMap<String, Object>();
			statemap.put("expanded", "true");
			statemap.put("checked", "true");
			//statemap.put("selected", "false");
			map.put("state", statemap);
		} else {
			map.put("text", m.getName());
			map.put("href", m.getId());
			statemap = new LinkedHashMap<String, Object>();
			statemap.put("expanded", "true");
			map.put("state", statemap);
		}
		Map<String, Object> nodesmap = null;
		List<SysMenu> tms = menuservice.findByChild(m.getId());
		if (tms != null) {
			List<Object> nodes = new ArrayList<Object>();
			for (SysMenu t : tms) {
				nodesmap = new LinkedHashMap<String, Object>();
				if (menuids.contains(t.getId())) {
					nodesmap.put("text", t.getName());
					nodesmap.put("href", t.getId());
					//map.put("selectable", "false");
					LinkedHashMap<String, Object> nodesstatemap = new LinkedHashMap<String, Object>();
					nodesstatemap.put("expanded", "true");
					nodesstatemap.put("checked", "true");
					//nodesstatemap.put("selected", "false");
					nodesmap.put("state", nodesstatemap);
				}else {
					nodesmap.put("text", t.getName());
					nodesmap.put("href", t.getId());
					LinkedHashMap<String, Object> nodesstatemap = new LinkedHashMap<String, Object>();
					nodesstatemap.put("expanded", "true");
					nodesmap.put("state", nodesstatemap);
				}
				nodes.add(nodesmap);
			}
			map.put("nodes", nodes);
		}
		return map;
	}

	/****************************** 编辑页面功能资源树 ******************************/

	private Map<String, Object> statemap = null;
}
