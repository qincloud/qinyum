package com.qinyum.system.user.model;

import javax.persistence.Table;

@Table(name = "user_role")
public class SysUserRole implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String userid;
	
	private String roleid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
}
