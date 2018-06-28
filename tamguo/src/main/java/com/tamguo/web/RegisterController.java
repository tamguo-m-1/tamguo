package com.tamguo.web;

import javax.servlet.http.HttpSession;
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
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
		}
		return Result.successResult(result);
	}
	
}
