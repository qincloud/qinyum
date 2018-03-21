 package com.qinyum.system.menu.model;

import javax.persistence.Table;

import com.qinyum.common.model.BasicModel;


@Table(name = "sys_menu")
public class SysMenu extends BasicModel implements java.io.Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 名字
	 */
	private String name;

	/**
	 * 路径
	 */
	private String href;

	/**
	 * 菜单层级
	 * */
	private String rank;

	/**
	 * 父ID
	 * */
    private String parent_id;
    
    /**
     * 父菜单名字
     * */
    private String parent;

	/**
	 * 排序
	 * */
	private String sort;
	
	/**
	 * 图标
	 * */
	private String icon;
	
	/**
	 * 权限标志
	 * */
	private String permission;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}
	
	
	
	
	
}
