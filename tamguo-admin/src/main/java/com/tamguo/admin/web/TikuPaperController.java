package com.tamguo.admin.web;

import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.tamguo.admin.model.PaperEntity;
import com.tamguo.admin.service.IPaperService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;

@Controller(value="adminPaperController")
public class TikuPaperController {
	
	@Autowired
	private IPaperService iPaperService;

	@RequestMapping("paper/list")
	@RequiresPermissions("tiku:paper:list")
	@ResponseBody
	public Map<String, Object> list(String name , Integer page , Integer limit){
		Page<PaperEntity> list = iPaperService.list(name, page, limit);
		return Result.jqGridResult(list.getResult(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("paper/info/{paperId}.html")
	@RequiresPermissions("tiku:paper:list")
	@ResponseBody
	public Result info(@PathVariable String paperId){
		try {
			return Result.result(0, iPaperService.select(paperId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询试卷", this.getClass(), e);
		}
	}
	
	@RequestMapping("paper/delete.html")
	@RequiresPermissions("tiku:paper:delete")
	@ResponseBody
	public Result delete(@RequestBody String[] paperIds){
		try {
			iPaperService.deleteByIds(paperIds);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除试卷", this.getClass(), e);
		}
	}
	
	@RequestMapping("paper/save.html")
	@RequiresPermissions("tiku:paper:save")
	@ResponseBody
	public Result save(@RequestBody PaperEntity paper){
		try {
			iPaperService.save(paper);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存试卷", this.getClass(), e);
		}
	}
	
	@RequestMapping("paper/update.html")
	@RequiresPermissions("tiku:paper:update")
	@ResponseBody
	public Result update(@RequestBody PaperEntity paper){
		try {
			iPaperService.update(paper);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改试卷", this.getClass(), e);
		}
	}
	
}
