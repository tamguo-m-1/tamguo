package com.tamguo.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysUserEntity;
import com.tamguo.admin.util.Result;

public interface ISysUserService {
	
	SysUserEntity queryByUserName(String username);
	
	SysUserEntity queryByUid(String uid);
	
	void updateUserInfo(SysUserEntity user);

	Result updatePassword(String oldPassword, String password, String repassword);

	Page<SysUserEntity> queryPage(String userName ,Page<SysUserEntity> page);

	SysUserEntity selectById(String userId);

	void save(SysUserEntity user);

	void update(SysUserEntity user);

	void deleteBatch(String[] userIds);
}
