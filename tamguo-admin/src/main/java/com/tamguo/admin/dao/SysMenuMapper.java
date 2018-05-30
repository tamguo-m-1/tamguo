package com.tamguo.admin.dao;

import java.util.List;
import java.util.Map;

import com.tamguo.admin.model.SysMenuEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SysMenuMapper extends BaseDao<SysMenuEntity> {

	List<SysMenuEntity> queryList(Map<String, Object> map);

	List<SysMenuEntity> queryListParentId(Long parentId);
	
	List<SysMenuEntity> queryNotButtonList();

}
