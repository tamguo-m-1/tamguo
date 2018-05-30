package com.tamguo.admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tamguo.admin.dao.SysRoleMenuMapper;
import com.tamguo.admin.model.SysRoleMenuEntity;
import com.tamguo.admin.mybatis.utils.Condition;
import com.tamguo.admin.service.ISysRoleMenuService;

/**
 * 角色与菜单对应关系
 * 
 */
@Service
public class SysRoleMenuService implements ISysRoleMenuService {
	@Autowired
	private SysRoleMenuMapper sysRoleMenuMapper;

	@Override
	@Transactional
	public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
		if (menuIdList.size() == 0) {
			return;
		}
		// 先删除角色与菜单关系
		Condition<SysRoleMenuEntity> condition = new Condition<SysRoleMenuEntity>(){};
		condition.andEqualTo("roleId", roleId);
		sysRoleMenuMapper.deleteByCondition(condition);

		// 保存角色与菜单关系
		Map<String, Object> map = new HashMap<>();
		map.put("roleId", roleId);
		map.put("menuIdList", menuIdList);
		sysRoleMenuMapper.saveRoleMenu(map);
	}

	@Override
	public List<Long> queryMenuIdList(Long roleId) {
		return sysRoleMenuMapper.queryMenuIdList(roleId);
	}

}
