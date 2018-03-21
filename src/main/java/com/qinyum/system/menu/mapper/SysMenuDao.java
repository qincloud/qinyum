package com.qinyum.system.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.qinyum.system.menu.model.SysMenu;

@Mapper
public interface SysMenuDao {
	public List<SysMenu> findByChilds(String parentid);
}
