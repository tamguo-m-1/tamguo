package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.SysRoleMapper;
import com.tamguo.admin.model.SysRoleEntity;
import com.tamguo.admin.service.ISysRoleService;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements ISysRoleService{
	
	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Override
	public List<SysRoleEntity> findAll() {
		return sysRoleMapper.selectList(null);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<SysRoleEntity> queryList(SysRoleEntity sysRoleEntity, Page<SysRoleEntity> page) {
		List<SysRoleEntity> list = sysRoleMapper.selectPageByName(sysRoleEntity , page);
		page.setRecords(list);
		return page;
	}

	@Override
	public SysRoleEntity select(String uid) {
		return sysRoleMapper.selectById(uid);
	}

	@Transactional(readOnly=false)
	@Override
	public void save(SysRoleEntity role) {
		role.setPerms(StringUtils.join(role.getMenuIdList(), ","));
		sysRoleMapper.insert(role);
	}

	@Transactional(readOnly=false)
	@Override
	public void update(SysRoleEntity role) {
		role.setPerms(StringUtils.join(role.getMenuIdList(), ","));
		sysRoleMapper.updateById(role);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteBatch(String[] roleIds) {
		sysRoleMapper.deleteBatchIds(Arrays.asList(roleIds));
	}

}
