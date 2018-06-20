package com.tamguo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tamguo.service.IAreaService;
import com.tamguo.util.Result;

@Controller
public class AreaController {

	@Autowired
	private IAreaService iAreaService;

	@RequestMapping(value = {"area/findAreaTree"}, method = RequestMethod.GET)
	@ResponseBody
	public Result findAreaTree() {
		return iAreaService.findAreaTree();
	}
	
	
}
