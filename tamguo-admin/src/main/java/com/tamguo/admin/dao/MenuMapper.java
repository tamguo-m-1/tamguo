package com.tamguo.admin.dao;

import java.util.List;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.MenuEntity;
import com.tamguo.admin.model.SubjectEntity;

public interface MenuMapper extends SuperMapper<MenuEntity>{

	public List<MenuEntity> findFatherMenus();
	
	public List<MenuEntity> findMenuByParentId(String parentId);

	public List<MenuEntity> findAllFatherMenus();

	public List<MenuEntity> findLeftFatherMenus();

	public List<MenuEntity> findFooterFatherMenus();

	public List<SubjectEntity> findByName(String name , Pagination page);
	
}
