package com.tamguo.service;

import java.util.List;
import com.tamguo.model.CourseEntity;

public interface ICourseService {

	/** 根据考试获取科目 */
	List<CourseEntity> findBySubjectId(String subjectId);
	
	/** 获取科目 */
	CourseEntity find(String uid);

}
