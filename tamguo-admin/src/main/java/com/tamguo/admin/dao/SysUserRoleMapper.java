package com.tamguo.admin.dao;

import java.util.List;
import java.util.Map;

import com.tamguo.admin.model.SysUserRoleEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SysUserRoleMapper extends BaseDao<SysUserRoleEntity> {
	
	/**
     * 查询用户的所有权限
     * @param userId  用户ID
     */
    List<String> queryUserAllPerms(Long userId);
    
    List<Long> queryAllMenuId(Long userId);
    
    void saveUserRole(Map<String, Object> map);
    
    /**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);

}
