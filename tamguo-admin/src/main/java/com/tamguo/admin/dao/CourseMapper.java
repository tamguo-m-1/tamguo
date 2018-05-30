package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.tamguo.admin.model.CourseEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface CourseMapper extends BaseDao<CourseEntity>{

	List<CourseEntity> findBySubjectId(String subjectId);

	Page<CourseEntity> queryPageByName(@Param(value="name")String name);

}
