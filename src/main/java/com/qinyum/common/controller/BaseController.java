package com.qinyum.common.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.qinyum.common.utils.FileUtil;

public class BaseController {
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String EXCEPTION = "exception";
	
	public static final String REDIRECT = "redirect:";
	public static final String FORWARD = "forward:";
	Logger log = Logger.getLogger(BaseController.class);

	/**
	 * 获取页面传递的某一个参数值,
	 */
	public String getParameter(String key) {
		return this.getRequest().getParameter(key);
	}

	public void setAttribute(String key, Object value) {
		this.getRequest().setAttribute(key, value);
	}
	
	 /**
     * 获得session
     * */
    public HttpSession getSession() {
        return getRequest().getSession();
    }
    
    public HttpSession getSession(Boolean flag) {
        return getRequest().getSession(flag);
    }

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	
	/**
	 * 得到request对象
	 */
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			.getResponse();
		return response;
	}
	
	 /**
     * 删除cookie
     */
    public void deleteCookieByName(String cookieName) {
        Cookie[] cookies = this.getRequest().getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName)) {
                Cookie temp = new Cookie(cookie.getName(), "");
                temp.setMaxAge(0);
                this.getResponse().addCookie(temp);
            }
        }
    }
    
    /**
     * 读取文件流
     * */
    public ResponseEntity<byte[]> renderFile(String fileName, String filePath) {
        byte[] bytes = FileUtil.toByteArray(filePath);
        return renderFile(fileName, bytes);
    }
    
    public ResponseEntity<byte[]> renderFile(String fileName, byte[] fileBytes) {
        String dfileName = null;
        try {
            dfileName = new String(fileName.getBytes("gb2312"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", dfileName);
        return new ResponseEntity<byte[]>(fileBytes, headers, HttpStatus.CREATED);
    }
}