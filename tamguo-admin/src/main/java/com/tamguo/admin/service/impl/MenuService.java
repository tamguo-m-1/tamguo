package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.dao.MenuMapper;
import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.MenuEntity;
import com.tamguo.admin.service.IMenuService;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class MenuService implements IMenuService{
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> findMenus() {
		List<MenuEntity> menuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.INDEX_MENU));
		menuList = null;
		if (menuList == null || menuList.isEmpty()) {
			menuList = menuMapper.findFatherMenus();
			for(MenuEntity menu : menuList){
				List<MenuEntity> childSubjects = menuMapper.findMenuByParentId(menu.getUid());
				menu.setChildSubjects(childSubjects);
			}
			cacheService.setObject(TamguoConstant.INDEX_MENU, menuList , 2 * 60 * 60);
		}
		return menuList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> findAllMenus() {
		List<MenuEntity> allMenuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.ALL_INDEX_MENU));
		allMenuList = null;
		if(allMenuList == null || allMenuList.isEmpty()){
			allMenuList = menuMapper.findAllFatherMenus();
			for(MenuEntity menu : allMenuList){
				List<MenuEntity> childSubjects = menuMapper.findMenuByParentId(menu.getUid());
				menu.setChildSubjects(childSubjects);
			}
			cacheService.setObject(TamguoConstant.ALL_INDEX_MENU, allMenuList , 2 * 60 * 60);
		}
		return allMenuList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> findLeftMenus() {
		List<MenuEntity> leftMenuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.LEFT_INDEX_MENU));
		leftMenuList = null;
		if(leftMenuList == null || leftMenuList.isEmpty()){
			leftMenuList = menuMapper.findLeftFatherMenus();
			for(MenuEntity menu : leftMenuList){
				List<MenuEntity> childSubjects = menuMapper.findMenuByParentId(menu.getUid());
				menu.setChildSubjects(childSubjects);
			}
			cacheService.setObject(TamguoConstant.LEFT_INDEX_MENU, leftMenuList , 2 * 60 * 60);
		}
		return leftMenuList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> findFooterMenus() {
		List<MenuEntity> footerMenuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.FOOTER_INDEX_MENU));
		footerMenuList = null;
		if(footerMenuList == null || footerMenuList.isEmpty()){
			footerMenuList = menuMapper.findFooterFatherMenus();
			for(MenuEntity menu : footerMenuList){
				List<MenuEntity> childSubjects = menuMapper.findMenuByParentId(menu.getUid());
				menu.setChildSubjects(childSubjects);
			}
			cacheService.setObject(TamguoConstant.FOOTER_INDEX_MENU, footerMenuList , 2 * 60 * 60);
		}
		return footerMenuList;
	}

	@Override
	public Page<MenuEntity> list(String name, Page<MenuEntity> page) {
		return page.setRecords(menuMapper.findByName(name , page));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> getMenuTree() {
		return (List<MenuEntity>) menuMapper.selectList(Condition.EMPTY);
	}

	@Override
	public MenuEntity findById(String uid) {
		return menuMapper.selectById(uid);
	}

	@Override
	public void save(MenuEntity menu) {
		menuMapper.insert(menu);
	}

	@Override
	public void update(MenuEntity menu) {
		menuMapper.updateById(menu);
	}

	@Override
	public void deleteBatch(String[] menuIds) {
		menuMapper.deleteBatchIds(Arrays.asList(menuIds));
	}

}
