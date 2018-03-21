package com.qinyum.system.role.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.qinyum.common.service.BaseService;
import com.qinyum.system.menu.model.SysMenu;
import com.qinyum.system.role.mapper.SysRoleMapper;
import com.qinyum.system.role.model.SysRole;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysRoleService extends BaseService<SysRole,SysRoleMapper>{

	
	/**
	 * 获取全部
	 */
	public List<SysRole> findAll() {
		Example example = new Example(SysRole.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return dao.selectByExample(example);
	}
}
