package com.tamguo.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.TeacherEntity;

public interface ITeacherService {

	Page<TeacherEntity> queryPage(String mobile, Page<TeacherEntity> page);

	TeacherEntity find(String teacherId);

}
