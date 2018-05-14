package com.qinyum.system.logs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qinyum.common.controller.BaseController;
import com.qinyum.system.logs.model.SysLogs;
import com.qinyum.system.logs.service.SysLogService;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("system/logs")
public class LogsController extends BaseController {
	private String PREFIX = "/system/logs";
	
	@Autowired
	private SysLogService service;

	@Secured("ROLE_ADMIN")
	@RequestMapping()
	public String index(SysLogs logs) {
		setAttribute("pageInfo", service.query(logs));
		setAttribute("queryParam", logs);
		setAttribute("page", logs.getPage());
		setAttribute("rows", logs.getRows());
		setAttribute("model", logs);
		return PREFIX+"/logs";
	}
}
