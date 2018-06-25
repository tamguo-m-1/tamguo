package com.tamguo.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.util.Result;

public interface ITeacherService {

	Page<TeacherEntity> queryPage(String mobile, Page<TeacherEntity> page);

	TeacherEntity find(String teacherId);

	Result update(TeacherEntity teacher);

	Result pass(HttpServletRequest req , String teacherId) throws EmailException;

	Result deleteByIds(String[] teacherIds);

}
