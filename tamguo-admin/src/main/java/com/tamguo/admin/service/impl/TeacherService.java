package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.TeacherMapper;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.model.enums.TeacherStatus;
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
	public Result pass(String teacherId) {
		// 创建管理员账号
		
		// 发送邮件
		
		// 发送短信
		return Result.result(Result.SUCCESS_CODE, null, "审核通过成功");
	}

	@Transactional(readOnly=false)
	@Override
	public Result deleteByIds(String[] teacherIds) {
		if(teacherIds != null) {
			if(!CollectionUtils.isEmpty(Arrays.asList(teacherIds))) {
				List<TeacherEntity> teacherList = teacherMapper.selectBatchIds(Arrays.asList(teacherIds));
				for(int i=0 ; i<teacherList.size() ; i++) {
					TeacherEntity teacher = teacherList.get(i);
					if(teacher.getStatus() == TeacherStatus.PASS) {
						return Result.result(Result.FAIL_CODE, null, "已经通过不能删除");
					}
				}
				// 发送短信
				
				// 发送邮件
				
				teacherMapper.deleteBatchIds(Arrays.asList(teacherIds));
				return Result.result(Result.SUCCESS_CODE, null, "删除成功");
			}
		}
		return Result.failResult("请选择要删除的记录！");
	}


}
