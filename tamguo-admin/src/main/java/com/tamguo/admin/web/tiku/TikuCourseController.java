package com.tamguo.admin.web.tiku;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.CourseEntity;
import com.tamguo.admin.service.IChapterService;
import com.tamguo.admin.service.ICourseService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;

@Controller(value="adminCourseController")
public class TikuCourseController {
	
	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private IChapterService iChapterService;
	
	@RequestMapping("course/list")
	@RequiresPermissions("tiku:course:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<CourseEntity> p = new Page<>(page , limit);
		Page<CourseEntity> list = iCourseService.list(name, p);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("course/getChapterTree/{courseId}.html")
	@ResponseBody
	public Result getChapterTree(@PathVariable String courseId){
		return Result.result(0, iChapterService.getChapterTree(courseId), null);
	}
	
	@RequestMapping("course/info/{courseId}.html")
	@ResponseBody
	public Result info(@PathVariable String courseId){
		try {
			return Result.result(0, iCourseService.select(courseId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("course/delete.html")
	@RequiresPermissions("tiku:course:delete")
	@ResponseBody
	public Result delete(@RequestBody String[] courseIds){
		try {
			iCourseService.deleteByIds(courseIds);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("course/save.html")
	@RequiresPermissions("tiku:course:save")
	@ResponseBody
	public Result save(@RequestBody CourseEntity course){
		try {
			iCourseService.save(course);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("course/update.html")
	@RequiresPermissions("tiku:course:update")
	@ResponseBody
	public Result update(@RequestBody CourseEntity course){
		try {
			iCourseService.update(course);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("course/findBySubjectId.html")
	@ResponseBody
	public Result getCourse(String subjectId) {
		try {
			List<CourseEntity> courseList = iCourseService.findBySubjectId(subjectId);
			return Result.successResult(courseList);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("获取科目树", this.getClass(), e);
		}
	}
}
