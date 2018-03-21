package com.qinyum.system.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qinyum.common.security.UserUtils;
import com.qinyum.common.service.BaseService;
import com.qinyum.common.utils.StrKit;
import com.qinyum.system.menu.mapper.SysMenuDao;
import com.qinyum.system.menu.mapper.SysMenuMapper;
import com.qinyum.system.menu.model.SysMenu;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

@Component("menuService")
public class SysMenuService extends BaseService<SysMenu, SysMenuMapper> {

	public PageInfo<SysMenu> query(SysMenu model) {
		if (model.getPage() != null && model.getRows() != null) {
			PageHelper.startPage(model.getPage(), model.getRows());
		}
		Example example = new Example(model.getClass());
		Example.Criteria criteria = example.createCriteria();
		
		/**
		 * 菜单名称搜索
		 * */
		if (!StringUtil.isEmpty(model.getName())) {
			criteria.andLike("name", "%" + model.getName() + "%");
		}
		
		/**
		 * 菜单路径搜索
		 * */
		if(StrKit.notBlank(model.getHref())) {
			criteria.andLike("href", "%" + model.getHref() + "%");
		}
		
		
		criteria.andEqualTo("del_flag", "0");
		return new PageInfo<SysMenu>(dao.selectByExample(example));
	}

	/**
	 * 获取全部
	 */
	public List<SysMenu> findAll() {
		Example example = new Example(SysMenu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		return dao.selectByExample(example);
	}

	public SysMenu findById(String id) {
		Example example = new Example(SysMenu.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("del_flag", "0");
		criteria.andEqualTo("id", id);
		if (dao.selectByExample(example) != null) {
			return dao.selectByExample(example).get(0);
		} else {
			return null;
		}
	}

	public List<SysMenu> findByChild(String id) {
		return dao.findByChilds2(id);
	}
	
	public List<SysMenu> findByChild2(String parentid,String userid){
		return dao.findByChilds(parentid,userid);
	}

	public List<SysMenu> findMenuByUserid(String userid) {
		return dao.findMenuByUserid(userid);
	}

}
