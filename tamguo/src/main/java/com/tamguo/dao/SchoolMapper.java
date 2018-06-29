package com.tamguo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.SchoolEntity;

public interface SchoolMapper extends SuperMapper<SchoolEntity>{

	List<SchoolEntity> findByAreaId(String beijingAreaId , Pagination page);

	List<SchoolEntity> findByAreaIds(@Param(value="areaIds")List<String> areaIds);
	
}
