package com.tamguo.dao;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.MenuEntity;
import com.tamguo.model.SubjectEntity;

public interface MenuMapper extends SuperMapper<MenuEntity>{

	public List<MenuEntity> findFatherMenus();
	
	public List<MenuEntity> findMenuByParentId(String parentId);

	public List<MenuEntity> findAllFatherMenus();

	public List<MenuEntity> findLeftFatherMenus();

	public List<MenuEntity> findFooterFatherMenus();

	public Page<SubjectEntity> findByName(String name);
	
}
