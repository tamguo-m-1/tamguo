package com.tamguo.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.TeacherMapper;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.service.ITeacherService;
import com.tamguo.admin.util.Result;

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

	@Transactional(readOnly=false)
	@Override
	public Result update(TeacherEntity teacher) {
		TeacherEntity entity = teacherMapper.selectById(teacher.getUid());
		entity.setMobile(teacher.getMobile());
		entity.setName(teacher.getName());
		entity.setMobile(teacher.getMobile());
		entity.setCardId(teacher.getCardId());
		entity.setQq(teacher.getQq());
		teacherMapper.updateById(entity);
		return Result.successResult("修改成功");
	}

	@Transactional(readOnly=false)
	@Override
	public Result unpass(String teacherId) {
		teacherMapper.deleteById(teacherId);
		return Result.successResult("审核不通过成功");
	}

	@Transactional(readOnly=false)
	@Override
	public Result pass(String teacherId) {
		// 创建管理员账号
		
		// 发送邮件
		
		// 发送短信
		return Result.successResult("审核通过成功");
	}


}
