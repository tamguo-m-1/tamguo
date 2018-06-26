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
import com.tamguo.admin.model.ChapterEntity;
import com.tamguo.admin.service.IChapterService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;

@Controller(value="tikuChapterController")
public class TikuChapterController {
	
	@Autowired
	private IChapterService iChapterService;

	@RequestMapping("chapter/list")
	@RequiresPermissions("tiku:chapter:list")
	@ResponseBody
	public Map<String, Object> queryPage(String name , Page<ChapterEntity> page){
		Page<ChapterEntity> list = iChapterService.queryPage(name, page);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), page.getSize(), page.getCurrent(), list.getPages());
	}
	
	@RequestMapping("chapter/info/{chapterId}.html")
	@RequiresPermissions("tiku:chapter:info")
	@ResponseBody
	public Result info(@PathVariable String chapterId){
		try {
			return Result.result(0, iChapterService.select(chapterId), null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("查询科目", this.getClass(), e);
		}
	}
	
	@RequestMapping("chapter/delete.html")
	@RequiresPermissions("tiku:chapter:delete")
	@ResponseBody
	public Result delete(@RequestBody String[] chapterIds){
		try {
			iChapterService.deleteByIds(chapterIds);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除章节", this.getClass(), e);
		}
	}
	
	@RequestMapping("chapter/save.html")
	@RequiresPermissions("tiku:chapter:save")
	@ResponseBody
	public Result save(@RequestBody ChapterEntity chapter){
		try {
			iChapterService.save(chapter);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存章节", this.getClass(), e);
		}
	}
	
	@RequestMapping("chapter/update.html")
	@RequiresPermissions("tiku:chapter:update")
	@ResponseBody
	public Result update(@RequestBody ChapterEntity chapter){
		try {
			iChapterService.update(chapter);
			return Result.result(0, null, null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("更新章节", this.getClass(), e);
		}
	}
	
	@RequestMapping("chapter/getChapterTree.html")
	@ResponseBody
	public Result getChapterTree(){
		return Result.result(0, iChapterService.getChapterTree(), null);
	}
}
