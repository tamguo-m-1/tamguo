package com.tamguo.web;

import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;

@Controller
public class RegisterController {
	
	@Autowired
	private IMemberService iMemberService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(ModelAndView model , HttpSession session) {
		model.setViewName("register");
		return model;
    }
	
	@RequestMapping(value = "/checkUsername", method = RequestMethod.GET)
	@ResponseBody
	public Result checkUsername(String username){
		return iMemberService.checkUsername(username);
	}
	
	@RequestMapping(value = "/checkMobile", method = RequestMethod.GET)
	@ResponseBody
	public Result checkMobile(String mobile){
		return iMemberService.checkMobile(mobile);
	}
	
	@RequestMapping(value = "/subRegister", method = RequestMethod.POST)
	@ResponseBody
	public Result subRegister(@RequestBody MemberEntity member , HttpSession session){
		Result result = iMemberService.register(member);
		if(result.getCode() == 200) {
			Subject subject = ShiroUtils.getSubject();
			MemberEntity memberEntity = (MemberEntity) result.getResult();
			UsernamePasswordToken token = new UsernamePasswordToken(memberEntity.getUsername(), member.getPassword());
			try {
				subject.login(token);
				
				session.setAttribute("currMember", ShiroUtils.getMember());
			} catch (UnknownAccountException e) {
				return Result.result(201, null, "用户名或密码有误，请重新输入或找回密码");
			} catch (IncorrectCredentialsException e) {
				return Result.result(202, null, "用户名或密码有误，请重新输入或找回密码");
			} catch (LockedAccountException e) {
				return Result.result(203, null, "账号被锁定");
			} 
		}
		return result;
	}
	
}
