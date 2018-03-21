package com.qinyum.system.role.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "role_menu")
public class SysRoleMenu implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String roleid;

	private String menuid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
}
