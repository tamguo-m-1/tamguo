package com.tamguo.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.TeacherMapper;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.service.ITeacherService;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, TeacherEntity> implements ITeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public Page<TeacherEntity> queryPage(String mobile, Page<TeacherEntity> page) {
		return page.setRecords(teacherMapper.queryPage(mobile , page));
	}

	@Override
	public TeacherEntity find(String teacherId) {
		return teacherMapper.selectById(teacherId);
	}


}
