package com.tamguo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.TeacherMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.TeacherEntity;
import com.tamguo.service.ITeacherService;
import com.tamguo.util.Result;
import com.tamguo.util.TamguoConstant;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, TeacherEntity> implements ITeacherService {
	
	@Autowired
	TeacherMapper teacherMapper;
	@Autowired
	CacheService cacheService;

	@Transactional(readOnly=false)
	@Override
	public Result getTeacherByMobile(String mobile, String verifyCode) {
		// 校验短信验证码
		if(!cacheService.isExist(TamguoConstant.ALIYUN_MOBILE_SMS_PREFIX + mobile)){
		//	return Result.result(201, null, "验证码错误");
		}
		TeacherEntity teacher = teacherMapper.findByMobile(mobile);
		if(teacher == null) {
			return Result.result(202, null, "欢迎");
		}
		return Result.successResult(teacher);
	}

}
