package com.tamguo.admin.web;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value="adminIndexController")
public class IndexController {
	
	private final String INDEX_PAGE = "index";
	
	public final String UNAUTHORIZED = "unauthorized";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexAction(ModelAndView model) {
    	model.setViewName(INDEX_PAGE);
        return model;
    }
    
    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(HttpServletRequest request,ModelMap model) throws IOException {
        return INDEX_PAGE;
    }
    
	@RequestMapping(value = "unauthorized", method = RequestMethod.GET)
	public String unauthorized() {
		return UNAUTHORIZED;
	}
   
}