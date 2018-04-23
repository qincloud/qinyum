package com.qinyum.system.org.model;

import javax.persistence.Table;

import com.qinyum.common.model.BasicModel;

@Table(name = "sys_org")
public class SysOrg extends BasicModel implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 机构名称
	 */
	public String mc;

	/**
	 * 机构编码
	 */
	public String bm;

	/**
	 * 上级机构名称
	 */
	public String fmc;

	/**
	 * 上级机构编码
	 */
	public String fbm;
	
	/**
	 * 排序号
	 * */
	public String pxh;

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

	public String getFmc() {
		return fmc;
	}

	public void setFmc(String fmc) {
		this.fmc = fmc;
	}

	public String getFbm() {
		return fbm;
	}

	public void setFbm(String fbm) {
		this.fbm = fbm;
	}

	public String getPxh() {
		return pxh;
	}

	public void setPxh(String pxh) {
		this.pxh = pxh;
	}

	
}
