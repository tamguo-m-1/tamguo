package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.SysUserEntity;

public interface SysUserMapper extends SuperMapper<SysUserEntity>{

	SysUserEntity queryByUserName(String username);

	List<SysUserEntity> queryPage(@Param(value="userName")String userName , Page<SysUserEntity> page);
	
}
