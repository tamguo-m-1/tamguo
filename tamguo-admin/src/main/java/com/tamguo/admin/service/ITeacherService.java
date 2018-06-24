package com.tamguo.admin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.util.Result;

public interface ITeacherService {

	Page<TeacherEntity> queryPage(String mobile, Page<TeacherEntity> page);

	TeacherEntity find(String teacherId);

	Result update(TeacherEntity teacher);

	Result unpass(String teacherId);

	Result pass(String teacherId);

}
