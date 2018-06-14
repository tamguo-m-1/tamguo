package com.tamguo.admin.dao;

import java.util.List;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.SysRoleEntity;

public interface SysRoleMapper extends SuperMapper<SysRoleEntity>{

	List<SysRoleEntity> selectPageByName(SysRoleEntity sysRoleEntity, Pagination page);

}
