package com.tamguo.admin.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.ChapterEntity;

public interface IChapterService {

	// 获取科目章节
	public List<ChapterEntity> findCourseChapter(String courseId);
	
	// 获取当前章节
	public ChapterEntity findById(String uid);
	
	// 获取下一个知识点
	public ChapterEntity findNextPoint(String uid , Integer orders);

	// 获取树形
	public List<ChapterEntity> getChapterTree(String courseId);
	
	// 获取树形
	public List<ChapterEntity> getChapterTree();

	// 查询列表
	public Page<ChapterEntity> queryPage(String name, Page<ChapterEntity> page);

	// 根据ID查询当前章节
	public ChapterEntity select(String teacherId);

	// 删除章节
	public void deleteByIds(String[] chapterIds);

	// 保存章节
	public void save(ChapterEntity chapter);

	// 更新章节
	public void update(ChapterEntity chapter);
	
}
