package com.tamguo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.CourseMapper;
import com.tamguo.model.CourseEntity;
import com.tamguo.service.ICourseService;

@Service
public class CourseService extends ServiceImpl<CourseMapper, CourseEntity> implements ICourseService{
	
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public List<CourseEntity> findBySubjectId(String subjectId) {
		return courseMapper.findBySubjectId(subjectId);
	}

	@Override
	public CourseEntity find(String uid) {
		return courseMapper.selectById(uid);
	}

}
