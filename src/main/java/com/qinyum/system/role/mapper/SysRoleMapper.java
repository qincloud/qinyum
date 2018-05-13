package com.qinyum.system.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.system.role.model.SysRole;

@Repository
public interface SysRoleMapper extends BaseMapper<SysRole>{

	@Select("select * from sys_role where del_flag='0'")
	public List<SysRole> findAll();
	
	@Select("select * from sys_role r left join user_role ur on ur.roleid=r.id where r.del_flag='0' and ur.userid= #{userid}")
	public List<SysRole> findByRoleForUserid(String userid);
}
