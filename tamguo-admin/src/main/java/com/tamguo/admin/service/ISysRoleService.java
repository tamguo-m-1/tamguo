package com.tamguo.admin.service;

import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysRoleEntity;

public interface ISysRoleService {

	public List<SysRoleEntity> findAll();

	public Page<SysRoleEntity> queryList(SysRoleEntity role, Page<SysRoleEntity> page);
	
	public SysRoleEntity select(String uid);

	public void save(SysRoleEntity role);

	public void update(SysRoleEntity role);

	public void deleteBatch(String[] roleIds);
	
}
