package com.tamguo.admin.dao;

import java.util.List;
import com.tamguo.admin.model.SchoolEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SchoolMapper extends BaseDao<SchoolEntity>{

	List<SchoolEntity> findByAreaId(String beijingAreaId);
	
}
