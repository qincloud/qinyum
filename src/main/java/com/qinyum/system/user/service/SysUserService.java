package com.qinyum.system.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.service.BaseService;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.user.mapper.SysUserMapper;
import com.qinyum.system.user.mapper.SysUserRoleMapper;
import com.qinyum.system.user.model.SysUser;
import com.qinyum.system.user.model.SysUserRole;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysUserService extends BaseService<SysUser, SysUserMapper> implements UserDetailsService{

	@Autowired
	private SysUserRoleMapper urmapper;
	
	public PageInfo<SysUser> query(SysUser model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		if (model != null) {
			if (StrKit.notBlank(model.getUsername())) {
				criteria.andLike("username", "%" + model.getUsername() + "%");
			}
			if (StrKit.notBlank(model.getLogname())) {
				criteria.andLike("logname", "%" + model.getLogname() + "%");
			}
			if (StrKit.notBlank(model.getEmail())) {
				criteria.andLike("email", "%" + model.getEmail() + "%");
			}
			if (StrKit.notBlank(model.getPhone())) {
				criteria.andLike("phone", "%" + model.getPhone() + "%");
			}
		}
		example.setOrderByClause("create_time asc"); // 按创建时间排序
		return new PageInfo<SysUser>(dao.selectByExample(example));
	}

	/**
	 * 获取全部
	 */
	public List<SysUser> findAll() {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return dao.selectByExample(example);
	}

	public SysUser findById(String id) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		criteria.andEqualTo("id", id);
		if (dao.selectByExample(example) != null) {
			return dao.selectByExample(example).get(0);
		} else {
			return null;
		}
	}

	public SysUser findByLogname(String name) {
		Example example = new Example(SysUser.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		criteria.andEqualTo("logname", name);
		if (dao.selectByExample(example) != null) {
			return dao.selectByExample(example).get(0);
		} else {
			return null;
		}
	}
	
	public List<SysUserRole> findByUserid(String userid){
		return urmapper.findByUserid(userid);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = findByLogname(username);
		return user;
	}
}
