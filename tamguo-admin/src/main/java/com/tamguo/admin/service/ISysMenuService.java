package com.tamguo.admin.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysMenuEntity;

public interface ISysMenuService {

	/**
	 * 获取用户菜单权限
	 * @param userId
	 * @return
	 */
	List<SysMenuEntity> getUserMenuList(String userId);
	
	/**
	 * 获取菜单树
	 * @param menus
	 * @return
	 */
	List<SysMenuEntity> getUserMenuTree(List<SysMenuEntity> menus);

	/**
	 * 菜单列表
	 * @param hashMap
	 * @param page
	 * @param limit
	 * @return
	 */
	Page<SysMenuEntity> queryList(Map<String, Object> hashMap, Page<SysMenuEntity> page);

	/**
	 * 查询不是按钮的菜单
	 * @return
	 */
	List<SysMenuEntity> queryNotButtonList();

	/**
	 * 根据父菜单查询菜单
	 * @param parentId
	 * @return
	 */
	SysMenuEntity select(String parentId);

	/**
	 * 保存数据
	 * @param menu
	 */
	void save(SysMenuEntity menu);

	/**
	 * 修改
	 * @param menu
	 */
	void update(SysMenuEntity menu);

	/**
	 * 删除
	 * @param menuIds
	 */
	void deleteBatch(String[] menuIds);

}
