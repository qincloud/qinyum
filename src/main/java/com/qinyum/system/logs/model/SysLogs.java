package com.qinyum.system.logs.model;

import javax.persistence.Table;

import com.qinyum.common.model.BasicModel;

@Table(name="sys_logs")
public class SysLogs extends BasicModel implements java.io.Serializable{
	
	private String loguser;
	
	private String ipaddr;

	public String getLoguser() {
		return loguser;
	}

	public void setLoguser(String loguser) {
		this.loguser = loguser;
	}

	public String getIpaddr() {
		return ipaddr;
	}

	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
}
