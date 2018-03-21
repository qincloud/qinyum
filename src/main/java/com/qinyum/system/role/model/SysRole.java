package com.qinyum.system.role.model;

import javax.persistence.Table;

import com.qinyum.common.model.BasicModel;

@Table(name = "sys_role")
public class SysRole extends BasicModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 角色名
	 * */
	private String name;
	
	/**
	 * 角色英文名
	 * */
	private String ename;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}
	
	

}
