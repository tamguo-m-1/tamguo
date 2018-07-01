package com.tamguo.web.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;

@Controller
public class MemberController {
	
	@Autowired
	IMemberService iMemberService;

	@RequestMapping(value = "/member/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("member/index");
		model.addObject("member" , iMemberService.findByUid(ShiroUtils.getMember().getUid()));
		return model;
	}
	
	@RequestMapping(value = "/member/findCurrMember", method = RequestMethod.GET)
	@ResponseBody
	public Result findCurrMember() {
		return Result.successResult(iMemberService.findCurrMember());
	}
}
