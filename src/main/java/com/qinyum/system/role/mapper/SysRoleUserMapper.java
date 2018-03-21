package com.qinyum.system.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.system.role.model.SysRoleMenu;

@Repository
public interface SysRoleUserMapper extends BaseMapper<SysRoleMenu> {

	@Delete("delete role_menu where roleid = #{roleid}")
	public void deleteByRoleid(String roleid);

	@Select("select * from role_menu where roleid = #{roleid}")
	public List<SysRoleMenu> selectByRoleid(String roleid);
}
