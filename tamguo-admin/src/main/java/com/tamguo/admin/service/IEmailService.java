package com.tamguo.admin.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;

import com.tamguo.admin.model.TeacherEntity;

public interface IEmailService {

	/** 发送找回密码邮件*/
	public Integer sendFindPasswordEmail(String email , String subject) throws EmailException;
	
	/** 发送通过教师招募邮件*/
	public Integer sendPassJoinusEmail(HttpServletRequest req , TeacherEntity teacher , String password) throws EmailException;
}
