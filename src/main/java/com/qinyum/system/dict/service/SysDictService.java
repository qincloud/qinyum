package com.qinyum.system.dict.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.service.BaseService;
import com.qinyum.system.dict.mapper.SysDictMapper;
import com.qinyum.system.dict.model.SysDict;

import tk.mybatis.mapper.entity.Example;

@Service
public class SysDictService extends BaseService<SysDict,SysDictMapper>{

	public PageInfo<SysDict> query(SysDict model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		
		
		
		criteria.andEqualTo("del_flag", "0");
		return new PageInfo<SysDict>(dao.selectByExample(example));
	}

	/**
	 * 获取全部
	 */
	public List<SysDict> findAll() {
		Example example = new Example(SysDict.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return dao.selectByExample(example);
	}

	public SysDict findById(String id) {
		Example example = new Example(SysDict.class);
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
