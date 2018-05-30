package com.tamguo.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面视图
 */
@Controller
public class SysPageController {
	
	@RequestMapping("/sys/{url}.html")
	public String page(@PathVariable("url") String url){
		return "sys/"+url;
	}
	
	@RequestMapping("/video/{url}.html")
	public String videoPage(@PathVariable("url") String url){
		return "video/"+url;
	}
	
	@RequestMapping("/member/{url}.html")
	public String memberPage(@PathVariable("url") String url){
		return "member/"+url;
	}
	
	@RequestMapping("/tiku/{module}/{url}.html")
	public String memberPage(@PathVariable("module") String module , @PathVariable("url") String url){
		return "tiku/"+module+"/"+url;
	}
}
