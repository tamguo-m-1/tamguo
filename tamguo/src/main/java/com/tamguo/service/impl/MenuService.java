package com.tamguo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.MenuMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.MenuEntity;
import com.tamguo.service.IMenuService;
import com.tamguo.util.TamguoConstant;

@Service
public class MenuService extends ServiceImpl<MenuMapper, MenuEntity> implements IMenuService{
	
	@Autowired
	private MenuMapper menuMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> findMenus() {
		List<MenuEntity> menuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.INDEX_MENU));
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
	public List<MenuEntity> findChapterMenus() {
		List<MenuEntity> chapterMenuList = ((List<MenuEntity>) cacheService.getObject(TamguoConstant.CHAPTER_INDEX_MENU));
		if(chapterMenuList == null || chapterMenuList.isEmpty()){
			chapterMenuList = menuMapper.findChapterFatherMenus();
			for(MenuEntity menu : chapterMenuList){
				List<MenuEntity> childSubjects = menuMapper.findMenuByParentId(menu.getUid());
				menu.setChildSubjects(childSubjects);
			}
			cacheService.setObject(TamguoConstant.CHAPTER_INDEX_MENU, chapterMenuList , 2 * 60 * 60);
		}
		return chapterMenuList;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuEntity> getMenuTree() {
		return menuMapper.selectList(Condition.EMPTY);
	}

	@Override
	public MenuEntity findById(String uid) {
		return menuMapper.selectById(uid);
	}

}
