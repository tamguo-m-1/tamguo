package com.tamguo.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.CourseEntity;

public interface ICourseService {

	/** 高考专区*/
	List<CourseEntity> findGaokaoArea(String gaokaoSubjectId);
	
	/** 根据考试获取科目 */
	List<CourseEntity> findBySubjectId(String subjectId);
	
	/** 获取科目 */
	CourseEntity find(String uid);

	/** 获取科目*/
	Page<CourseEntity> list(String name, Page<CourseEntity> page);

	/** 获取科目*/
	CourseEntity select(String courseId);

	/** 删除*/
	void deleteByIds(String[] courseIds);

	/** 保存*/
	void save(CourseEntity course);

	/** 修改*/
	void update(CourseEntity course);

}
