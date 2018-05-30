package com.tamguo.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;
import com.tamguo.util.Result;
import com.tamguo.util.ShiroUtils;

@Controller
public class LoginController {
	
	@Autowired
	private IMemberService iMemberService;
	
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
		Result result = iMemberService.login(username, password , verifyCode);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
			response.sendRedirect("member/index.html");
			return null;
		}else{
			model.setViewName("login");	
			model.addObject("code", result.getCode());
			model.addObject("msg" , result.getMessage());
			model.addObject("isVerifyCode" , iMemberService.getLoginFailureCount((MemberEntity)result.getResult()) >=3 ? "1" : "0");
			model.addObject("username", username);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/miniLogin", method = RequestMethod.GET)
	@ResponseBody
    public Result miniLogin(String username , String password , String captcha, ModelAndView model , HttpSession session) {
		Result result = iMemberService.login(username, password , captcha);
		if(result.getCode() == 200){
			session.setAttribute("currMember", result.getResult());
		}
		return result;
    }

}
