package com.qinyum.common.security;

import org.springframework.security.core.GrantedAuthority;

public class QinGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 1L;
	private String url;
	private String method;

	public String MenuUrl() {
		return url;
	}

	public void setMenuUrl(String menuUrl) {
		this.url = menuUrl;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public QinGrantedAuthority(String url, String method) {
        this.url = url;
        this.method = method;
    }

	@Override
	public String getAuthority() {
		return this.url + ";" + this.method;
	}
}
