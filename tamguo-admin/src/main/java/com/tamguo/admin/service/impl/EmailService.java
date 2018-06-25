package com.tamguo.admin.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.service.IEmailService;
import com.tamguo.admin.util.Setting;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	private CacheService cacheService;
	@Autowired
    private TemplateEngine templateEngine;
	@Autowired
	private Setting setting;
	
	@Override
	public Integer sendFindPasswordEmail(String email , String subject) throws EmailException {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(TamguoConstant.ALIYUN_SMTP_HOST_NAME);
		mail.setSmtpPort(TamguoConstant.ALIYUN_SMTP_HOST_PORT);
		mail.setAuthentication(TamguoConstant.ALIYUN_MAIL_ACCOUNT, TamguoConstant.ALIYUN_MAIL_PASSWORD);
		mail.setSSLOnConnect(true);
		mail.setFrom(TamguoConstant.ALIYUN_MAIL_ACCOUNT, "探果题库");
		mail.addTo(email);
		mail.setSubject(subject);
		mail.setCharset("UTF-8");
		Integer vcode = (int) ((Math.random()*9+1)*100000);  
		mail.setHtmlMsg("探果网找回密码验证码："+vcode);
		mail.send();
		
		cacheService.setObject(TamguoConstant.ALIYUN_MAIL_FIND_PASSWORD_PREFIX + email , vcode.toString() , 3 * 60);
		return 0;
	}

	@Override
	public Integer sendPassJoinusEmail(HttpServletRequest req , TeacherEntity teacher , String password) throws EmailException {
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(TamguoConstant.ALIYUN_SMTP_HOST_NAME);
		mail.setSmtpPort(TamguoConstant.ALIYUN_SMTP_HOST_PORT);
		mail.setAuthentication(TamguoConstant.ALIYUN_MAIL_ACCOUNT, TamguoConstant.ALIYUN_MAIL_PASSWORD);
		mail.setSSLOnConnect(true);
		mail.setFrom(TamguoConstant.ALIYUN_MAIL_ACCOUNT, "探果题库");
		mail.addTo(teacher.getEmail());
		mail.setSubject("恭喜您成为探果题库的成员");
		mail.setCharset("UTF-8");
		
		Map<String, Object> map = new HashMap<>();
		map.put("teacher", teacher);
		map.put("setting", setting);
		map.put("password", password);
		IContext context = new Context(req.getLocale() , map);
		String html = templateEngine.process("email/joinus", context);
		mail.setHtmlMsg(html);
		mail.send();
		return 0;
	}

}
