package com.tamguo.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;

@Controller
public class LoginController {
	
	@Autowired
	private Producer producer;

	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");
		// 生成文字验证码
		String text = producer.createText();
		// 生成图片验证码
		BufferedImage image = producer.createImage(text);
		// 保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("login");
		model.addObject("isVerifyCode" , "0");
		return model;
	}
	
	@RequestMapping(value = "/submitLogin", method = RequestMethod.POST)
	public ModelAndView submitLogin(String  username , String password , String verifyCode , ModelAndView model , HttpSession session , HttpServletResponse response) throws IOException{
		Result result = Result.successResult(null);
		if(StringUtils.isEmpty(verifyCode)) {
			result = Result.result(202, null, "请输入验证码");
		} else if(StringUtils.isNotEmpty(verifyCode)){
			String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			if (!verifyCode.equalsIgnoreCase(kaptcha)) {
				result = Result.result(205, null, "验证码错误");
			} else {
				Subject subject = ShiroUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				try {
					subject.login(token);
					
					session.setAttribute("currMember", ShiroUtils.getMember());
					response.sendRedirect("member/index.html");
					return null;
				} catch (UnknownAccountException e) {
					result = Result.result(201, null, "用户名或密码有误，请重新输入或找回密码");
				} catch (IncorrectCredentialsException e) {
					result = Result.result(202, null, "用户名或密码有误，请重新输入或找回密码");
				} catch (LockedAccountException e) {
					result = Result.result(203, null, "账号被锁定");
				} 
			}
		} 
		model.setViewName("login");	
		model.addObject("code", result.getCode());
		model.addObject("msg" , result.getMessage());
		model.addObject("username", username);
		return model;
	}
	
	@RequestMapping(value = "/miniLogin", method = RequestMethod.GET)
	@ResponseBody
    public Result miniLogin(String username , String password , String captcha, ModelAndView model , HttpSession session) {
		Result result = null;
		if(StringUtils.isEmpty(captcha)) {
			result = Result.result(204, null, "请输入验证码");
		} else if(StringUtils.isNotEmpty(captcha)){
			String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
			if (!captcha.equalsIgnoreCase(kaptcha)) {
				result = Result.result(205, null, "验证码错误");
			}else {
				Subject subject = ShiroUtils.getSubject();
				UsernamePasswordToken token = new UsernamePasswordToken(username, password);
				try {
					subject.login(token);
					session.setAttribute("currMember", ShiroUtils.getMember());
					result = Result.successResult(ShiroUtils.getMember());
				} catch (UnknownAccountException e) {
					result = Result.result(201, null, "用户名或密码有误，请重新输入或找回密码");
				} catch (IncorrectCredentialsException e) {
					result = Result.result(202, null, "用户名或密码有误，请重新输入或找回密码");
				} catch (LockedAccountException e) {
					result = Result.result(203, null, "账号被锁定");
				} 
			}
		}
		return result;
    }

}
