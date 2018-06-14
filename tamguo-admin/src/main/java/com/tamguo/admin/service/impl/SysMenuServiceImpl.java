package com.tamguo.admin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.SysMenuMapper;
import com.tamguo.admin.dao.SysRoleMapper;
import com.tamguo.admin.dao.SysUserMapper;
import com.tamguo.admin.model.SysMenuEntity;
import com.tamguo.admin.model.SysRoleEntity;
import com.tamguo.admin.model.SysUserEntity;
import com.tamguo.admin.service.ISysMenuService;

@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity> implements ISysMenuService {
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	
	@Override
	public List<SysMenuEntity> getUserMenuList(String userId) {
		SysUserEntity user = sysUserMapper.selectById(userId);
		List<String> roleIds = Arrays.asList(user.getRoleIds().split(","));
		// 获取用户角色
		List<SysRoleEntity> roles = sysRoleMapper.selectBatchIds(roleIds);
		// 有权限的菜单
		List<String> menuIds = new ArrayList<>();
		if(!CollectionUtils.isEmpty(roles)) {
			for(int i=0 ; i<roles.size() ; i++) {
				SysRoleEntity role = roles.get(i);
				if(!StringUtils.isEmpty(role.getPerms())) {
					List<String> mIds = Arrays.asList(role.getPerms().split(","));
					menuIds.addAll(mIds);
				}
			}
		}
		List<SysMenuEntity> menuList = sysMenuMapper.selectBatchIds(menuIds);
		
		return menuList;
	}
	
	public List<SysMenuEntity> getUserMenuTree(List<SysMenuEntity> menus){
		List<SysMenuEntity> pMenuList = new ArrayList<>();
		for(int i=0 ; i<menus.size() ; i++) {
			SysMenuEntity menu = menus.get(i);
			if(menu.getParentId().equals("0")) {
				pMenuList.add(menu);
			}
		}
		// 支持二级菜单
		for(int i=0 ; i<pMenuList.size() ; i++) {
			SysMenuEntity pMenu = pMenuList.get(i);
			
			List<SysMenuEntity> childMenus = new ArrayList<>();
			for(int k=0 ; k<menus.size() ; k++) {
				SysMenuEntity cMenu = menus.get(k);
				if(cMenu.getParentId().toString().equals(pMenu.getUid())) {
					childMenus.add(cMenu);
				}
			}
			pMenu.setMenuList(childMenus);
		}
		
		return pMenuList;
	}

	@Transactional(readOnly=true)
	@Override
	public Page<SysMenuEntity> queryList(Map<String, Object> hashMap, Page<SysMenuEntity> page) {
        List<SysMenuEntity> pageList = (List<SysMenuEntity>) sysMenuMapper.queryList(hashMap , page);
        page.setRecords(pageList);
        return page;
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return sysMenuMapper.queryNotButtonList();
	}

	@Override
	public SysMenuEntity select(String parentId) {
		return sysMenuMapper.queryByUid(parentId);
	}

	@Transactional(readOnly=false)
	@Override
	public void save(SysMenuEntity menu) {
		sysMenuMapper.insert(menu);		
	}

	@Transactional(readOnly=false)
	@Override
	public void update(SysMenuEntity menu) {
		sysMenuMapper.updateById(menu);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteBatch(String[] menuIds) {
		sysMenuMapper.deleteBatchIds(Arrays.asList(menuIds));
	}

}
