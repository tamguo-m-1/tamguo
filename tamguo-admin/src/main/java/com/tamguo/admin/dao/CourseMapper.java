package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.CourseEntity;

public interface CourseMapper extends SuperMapper<CourseEntity>{

	List<CourseEntity> findBySubjectId(String subjectId);

	List<CourseEntity> queryPageByName(@Param(value="name")String name , Pagination page);

}
