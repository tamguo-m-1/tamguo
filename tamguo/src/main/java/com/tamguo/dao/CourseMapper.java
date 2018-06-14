package com.tamguo.dao;

import java.util.List;

import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.CourseEntity;

public interface CourseMapper extends SuperMapper<CourseEntity>{

	List<CourseEntity> findBySubjectId(String uid);

}
