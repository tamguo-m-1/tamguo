package com.tamguo.admin.service;


import com.github.pagehelper.Page;
import com.tamguo.admin.model.SysRoleEntity;


/**
 * 角色
 *
 */
public interface ISysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	Page<SysRoleEntity> queryList(SysRoleEntity sysRoleEntity,int pageNum,int pageSize);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(String[] roleIds);
}
