package com.qinyum.system.dict.model;

import javax.persistence.Table;

import com.qinyum.common.model.BasicModel;

@Table(name = "sys_dict")
public class SysDict extends BasicModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 字典名称
	 * */
	public String mc;
	
	/**
	 * 字典代码
	 * */
	public String bm;
	
	/**
	 * 字典类别
	 * */
	public String zdlb;
	
	/**
	 * 字典类别名称
	 * */
	public String lbmc;

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getZdlb() {
		return zdlb;
	}

	public void setZdlb(String zdlb) {
		this.zdlb = zdlb;
	}

	public String getLbmc() {
		return lbmc;
	}

	public void setLbmc(String lbmc) {
		this.lbmc = lbmc;
	}
}
