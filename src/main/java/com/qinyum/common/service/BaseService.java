package com.qinyum.common.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.mapper.BaseMapper;
import com.qinyum.common.model.BasicModel;
import com.qinyum.common.utils.Result;
import com.qinyum.system.menu.model.SysMenu;

import tk.mybatis.mapper.entity.Example;

/**
 * 基础服务类
 * 
 * @author: liaozq
 * @time: 2017.3.10
 */
public class BaseService<M extends BasicModel, D extends BaseMapper<M>> {
	Logger log = LoggerFactory.getLogger(this.getClass());

	protected M m;

	@Autowired
	protected D dao;

	public PageInfo<M> query(M model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return new PageInfo<M>(dao.selectByExample(example));
	}

	/**
	 * 保存方法
	 */
	public Result save(M m) {
		try {
			m.setId(UUID.randomUUID().toString());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.setCreate_time(format.parse(format.format(Calendar.getInstance().getTime())));
			m.setDel_flag("0");
			dao.insert(m);
			return Result.build(200, "保存成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.build(500, "保存失败");
		}
	}

	public String saveonly(M m) {
		try {
			String uuid = UUID.randomUUID().toString();
			m.setId(uuid);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.setCreate_time(format.parse(format.format(Calendar.getInstance().getTime())));
			m.setDel_flag("0");
			dao.insert(m);
			return m.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 逻辑删除方法,不是物理删除
	 */
	public Result delete(M m) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			m.setDelete_time(format.parse(format.format(Calendar.getInstance().getTime())));
			m.setDel_flag("1");
			dao.updateByPrimaryKey(m);
			return Result.build(200, "删除成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.build(500, "删除失败");
		}
	}

	/**
	 * 更新方法
	 */
	public Result update(M m) {
		try {
			BasicModel bm = dao.selectByPrimaryKey(m.getId());
			m.setCreate_by(bm.getCreate_by());
			m.setCreate_time(bm.getCreate_time());
			m.setDel_flag(bm.getDel_flag());
			m.setDelete_by(bm.getDelete_by());
			m.setDelete_time(bm.getDelete_time());
			dao.updateByPrimaryKey(m);
			return Result.build(200, "更新成功");
		} catch (Exception e) {
			log.error(e.getMessage());
			return Result.build(500, "更新失败");
		}
	}
	
	public M selectByPrimary(String id) {
		return dao.selectByPrimaryKey(id);
	}
}
