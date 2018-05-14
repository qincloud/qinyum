package com.qinyum.system.logs.service;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.service.BaseService;
import com.qinyum.system.logs.mapper.SysLogsMapper;
import com.qinyum.system.logs.model.SysLogs;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysLogService extends BaseService<SysLogs, SysLogsMapper>{
	
	@Override
	public PageInfo<SysLogs> query(SysLogs model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		
		example.setOrderByClause("create_time desc"); // 按创建时间排序
		return new PageInfo<SysLogs>(dao.selectByExample(example));
	}

	
	
}
