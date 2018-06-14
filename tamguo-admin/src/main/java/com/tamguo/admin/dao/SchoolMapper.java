package com.tamguo.admin.dao;

import java.util.List;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.SchoolEntity;

public interface SchoolMapper extends SuperMapper<SchoolEntity>{

	List<SchoolEntity> findByAreaId(String beijingAreaId , Pagination page);
	
}
