package com.tamguo.admin.web.tiku;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SubjectEntity;
import com.tamguo.admin.service.ISubjectService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;

@Controller(value="adminSubjectController")
public class TikuSubjectController {
	
	@Autowired
	private ISubjectService iSubjectService;
	
	@RequestMapping("subject/list")
	@RequiresPermissions("tiku:subject:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<SubjectEntity> list = iSubjectService.list(name, page, limit);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("subject/find/{subjectId}")
	@ResponseBody
	public Result find(@PathVariable String subjectId){
		return Result.result(200, iSubjectService.find(subjectId), "");
	}
	
	@RequestMapping("subject/save")
	@ResponseBody
	public Result save(@RequestBody SubjectEntity subject){
		try {
			iSubjectService.save(subject);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("新增考试", this.getClass(), e);
		}
		return Result.result(0, null, "");
	}
	
	@RequestMapping("subject/update")
	@ResponseBody
	public Result update(@RequestBody SubjectEntity subject){
		try {
			iSubjectService.update(subject);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("更新考试", this.getClass(), e);
		}
		return Result.result(0, null, "");
	}	
	
	@RequestMapping("subject/delete")
	@ResponseBody
	public Result delete(@RequestBody String[] subjectIds){
		try {
			iSubjectService.deleteBatch(subjectIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除考试", this.getClass(), e);
		}
	}
	
	@RequestMapping(value = {"subject/getSubject"}, method = RequestMethod.GET)
	@ResponseBody
	public Result getSuject(){
		List<SubjectEntity> list = iSubjectService.getSubjectTree();
		return Result.successResult(list);
	}

}
