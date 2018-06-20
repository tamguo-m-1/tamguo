package com.tamguo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.AreaEntity;

public interface AreaMapper extends SuperMapper<AreaEntity>{

	List<AreaEntity> findRootArea();

	List<AreaEntity> findByParent(@Param(value="parentId")String parentId);

}
