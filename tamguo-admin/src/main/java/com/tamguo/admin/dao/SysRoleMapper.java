package com.tamguo.admin.dao;

import com.tamguo.admin.model.SysRoleEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SysRoleMapper extends BaseDao<SysRoleEntity> {
	
	void save(SysRoleEntity sysRoleEntity);

}
