package com.tamguo.service;

import com.tamguo.util.Result;

public interface ITeacherService {

	/**
	 * 获取教师档案
	 * @param mobile
	 * @param verifyCode
	 * @return
	 */
	Result getTeacherByMobile(String mobile , String verifyCode);
	
}
