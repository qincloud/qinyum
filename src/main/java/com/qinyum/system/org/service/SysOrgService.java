package com.qinyum.system.org.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.service.BaseService;
import com.qinyum.system.org.mapper.SysOrgMapper;
import com.qinyum.system.org.model.SysOrg;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysOrgService extends BaseService<SysOrg, SysOrgMapper>{

	public PageInfo<SysOrg> query(SysOrg model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		
		
		
		criteria.andEqualTo("del_flag", "0");
		return new PageInfo<SysOrg>(dao.selectByExample(example));
	}

	/**
	 * 获取全部
	 */
	public List<SysOrg> findAll() {
		Example example = new Example(SysOrg.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return dao.selectByExample(example);
	}

	public SysOrg findById(String id) {
		Example example = new Example(SysOrg.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		criteria.andEqualTo("id", id);
		if (dao.selectByExample(example) != null) {
			return dao.selectByExample(example).get(0);
		} else {
			return null;
		}
	}
}
