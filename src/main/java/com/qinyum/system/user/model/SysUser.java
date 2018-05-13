package com.qinyum.system.user.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.qinyum.common.model.BasicModel;
import com.qinyum.common.utils.SpringContextHolder;
import com.qinyum.system.role.mapper.SysRoleMapper;
import com.qinyum.system.role.model.SysRole;
import com.qinyum.system.role.service.SysRoleService;

@Entity
@Table(name = "sys_user")
@Component
public class SysUser extends BasicModel implements UserDetails, java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用戶名
	 */
	private String username;

	/**
	 * 登陸名
	 */
	private String logname;

	/**
	 * 密碼
	 */
	private String pass;

	/**
	 * 圖片路徑
	 */
	private String picture;

	/**
	 * email
	 */
	private String email;

	/**
	 * 電話
	 */
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLogname() {
		return logname;
	}

	public void setLogname(String logname) {
		this.logname = logname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (getLogname().equals("admin")) {
			SysRoleService roleService = SpringContextHolder.getBean(SysRoleService.class);
			for (SysRole role : roleService.findAll()) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getEname());
				authorities.add(authority);
			}
		} else {
			SysRoleMapper roleMapper = SpringContextHolder.getBean(SysRoleMapper.class);
			for (SysRole role : roleMapper.findByRoleForUserid(getId())) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getEname());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	// @Bean
	// public SysMenuService menuDao() {
	// return new SysMenuService();
	// }

	@Override
	public String getPassword() {
		return getPass();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() { // 是否可用默认为可用true
		return true;
	}

}
