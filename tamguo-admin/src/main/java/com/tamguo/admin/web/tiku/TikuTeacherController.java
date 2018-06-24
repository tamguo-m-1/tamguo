package com.tamguo.admin.web.tiku;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.service.ITeacherService;
import com.tamguo.admin.util.Result;

@Controller
public class TikuTeacherController {

	@Autowired
	ITeacherService iTeacherService;
	
	@RequestMapping("teacher/list")
	@RequiresPermissions("tiku:teacher:list")
	@ResponseBody
	public Map<String, Object> list(String mobile , Page<TeacherEntity> page){
		Page<TeacherEntity> list = iTeacherService.queryPage(mobile, page);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), page.getSize(), page.getCurrent(), list.getPages());
	}
	
	@RequestMapping("teacher/find/{teacherId}")
	@ResponseBody
	public Result find(@PathVariable String teacherId){
		return Result.successResult(iTeacherService.find(teacherId));
	}
	
	
	@RequestMapping("teacher/update")
	@ResponseBody
	public Result update(@RequestBody TeacherEntity teacher){
		return Result.successResult(iTeacherService.update(teacher));
	}
	
	@RequestMapping("teacher/unpass/{teacherId}")
	@ResponseBody
	@RequiresPermissions("tiku:teacher:unpass")
	public Result unpass(@PathVariable String teacherId){
		return Result.successResult(iTeacherService.unpass(teacherId));
	}
	
	@RequestMapping("teacher/unpass/{teacherId}")
	@ResponseBody
	@RequiresPermissions("tiku:teacher:pass")
	public Result pass(@PathVariable String teacherId){
		return Result.successResult(iTeacherService.pass(teacherId));
	}
	
}
