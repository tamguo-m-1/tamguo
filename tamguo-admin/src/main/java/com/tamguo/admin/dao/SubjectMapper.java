package com.tamguo.admin.dao;

import org.apache.ibatis.annotations.Param;
import com.github.pagehelper.Page;
import com.tamguo.admin.model.SubjectEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface SubjectMapper extends BaseDao<SubjectEntity>{

	Page<SubjectEntity> queryPage(@Param(value="name")String name);

}
