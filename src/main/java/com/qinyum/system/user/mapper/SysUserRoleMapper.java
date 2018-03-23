package com.qinyum.system.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.system.user.model.SysUserRole;

@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
	@SelectProvider(type = UserRoleMapper.class, method = "selectByUserid")
	public List<SysUserRole> findByUserid(String userid);

	@SelectProvider(type = UserRoleMapper.class, method = "selectByRoleid")
	public List<SysUserRole> findByRoleid(String roleid);
	
	@Delete("delete from user_role where userid=#{userid}")
	public void deleteByUserid(String userid);

	public class UserRoleMapper {
		public String selectByUserid(String userid) {
			return "select * from user_role where userid='" + userid+"'";
		}
		
		public String selectByRoleid(String roleid) {
			return "select * from user_role where roleid='" + roleid+"'";
		}
	}
}
