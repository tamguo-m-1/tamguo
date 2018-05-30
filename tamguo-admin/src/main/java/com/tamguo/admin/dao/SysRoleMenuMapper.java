package com.tamguo.admin.dao;

import java.util.List;
import java.util.Map;

import com.tamguo.admin.model.SysRoleMenuEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SysRoleMenuMapper extends BaseDao<SysRoleMenuEntity> {
	
	int saveRoleMenu(Map<String, Object> map);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

}
