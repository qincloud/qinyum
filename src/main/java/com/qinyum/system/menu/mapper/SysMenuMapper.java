package com.qinyum.system.menu.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.system.menu.model.SysMenu;

@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
	
	@Select("SELECT * FROM sys_menu m left join sys_user u on u.del_flag = '0'" 
	        + " left join user_role ur on ur.userid = u.id left join role_menu rm on rm.roleid = ur.roleid"
			+ " WHERE m.parent_id = #{parentid} and m.id = rm.menuid and u.id = #{userid} ORDER BY sort")
	public List<SysMenu> findByChilds(@Param("parentid")String parentid,@Param("userid")String userid);
	
	@Select("SELECT * FROM sys_menu m WHERE m.del_flag='0' and m.parent_id=#{parentid} ORDER BY m.sort")
	public List<SysMenu> findByChilds2(String parentid);

	@Select("select * from sys_menu m left join sys_user u on u.del_flag = '0' left join user_role ur"
			+ " on ur.userid=u.id left join role_menu rm"
			+ " on rm.roleid = ur.roleid where m.id = rm.menuid and u.id = #{userid}")
	public List<SysMenu> findMenuByUserid(String userid);

}
