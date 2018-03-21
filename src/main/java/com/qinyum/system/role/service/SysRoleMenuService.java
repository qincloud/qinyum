package com.qinyum.system.role.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qinyum.system.role.mapper.SysRoleUserMapper;
import com.qinyum.system.role.model.SysRoleMenu;

@Service
public class SysRoleMenuService {
	@Autowired
	private SysRoleUserMapper service;

	public void insert(SysRoleMenu ru) {
		ru.setId(UUID.randomUUID().toString());
		service.insert(ru);
	}

	public void deleteByRoleid(String roleid) {
		service.deleteByRoleid(roleid);
	}

	public List<SysRoleMenu> selectByRoleid(String roleid) {
		return service.selectByRoleid(roleid);
	}
}
