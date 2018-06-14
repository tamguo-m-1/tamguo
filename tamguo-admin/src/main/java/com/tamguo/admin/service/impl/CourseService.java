package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.dao.ChapterMapper;
import com.tamguo.admin.dao.CourseMapper;
import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.ChapterEntity;
import com.tamguo.admin.model.CourseEntity;
import com.tamguo.admin.service.ICourseService;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class CourseService implements ICourseService{
	
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ChapterMapper chapterMapper; 

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseEntity> findGaokaoArea(String subjectId) {
		List<CourseEntity> courseList = (List<CourseEntity>) cacheService.getObject(TamguoConstant.GAOKAO_COURSE_AREA);
		courseList = null;
		if(courseList == null || courseList.isEmpty()){
			courseList = courseMapper.findBySubjectId(subjectId);
			cacheService.setObject(TamguoConstant.GAOKAO_COURSE_AREA, courseList , 2 * 60 * 60);
		}
		return courseList;
	}

	@Override
	public List<CourseEntity> findBySubjectId(String subjectId) {
		return courseMapper.findBySubjectId(subjectId);
	}

	@Override
	public CourseEntity find(String uid) {
		return courseMapper.selectById(uid);
	}

	@Override
	public Page<CourseEntity> list(String name, Page<CourseEntity> page) {
		if(!StringUtils.isEmpty(name)) {
			name = "%" + name + "%";
		}
		return page.setRecords(courseMapper.queryPageByName(name , page));
	}

	@Override
	public CourseEntity select(String courseId) {
		return courseMapper.selectById(courseId);
	}

	@Transactional(readOnly=false)
	@Override
	public void deleteByIds(String[] courseIds) {
		courseMapper.deleteBatchIds(Arrays.asList(courseIds));
		
		for(int i=0 ; i<courseIds.length ; i++){
			// 删除之前的章节
			chapterMapper.deleteByCourseId(courseIds[i]);
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void save(CourseEntity course) {
		course.setUid(null);
		
		course.setSeoTitle(course.getName());
		course.setSeoKeywords(course.getName());
		course.setSeoDescription(course.getName());
		
		courseMapper.insert(course);
		
		// 更新章节
		List<ChapterEntity> chapterList = course.getChapterList();
		for(int i=0 ; i<chapterList.size() ; i++){
			ChapterEntity chapter = chapterList.get(i);
			if(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID.equals(chapter.getParentId())){
				chapter.setName(course.getName());
			}
			String uid = chapter.getUid();
			
			chapter.setUid(null);
			chapter.setParentId(chapter.getParentId());
			chapter.setCourseId(course.getUid());
			chapterMapper.insert(chapter);
			
			for(int k=0 ; k<chapterList.size() ; k++){
				ChapterEntity c = chapterList.get(k);
				if(c.getParentId().equals(uid)){
					c.setParentId(chapter.getUid());
				}
			}
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void update(CourseEntity course) {
		courseMapper.updateById(course);
		
		// 更新章节
		List<ChapterEntity> chapterList = course.getChapterList();
		for(int i=0 ; i<chapterList.size() ; i++){
			
			ChapterEntity chapter = chapterList.get(i);
			if(TamguoConstant.CHAPTER_DEFAULT_ROOT_UID.equals(chapter.getParentId())){
				chapter.setName(course.getName());
			}
			// 只支持更新和新增操作
			if(StringUtils.isEmpty(chapter.getCourseId()) 
					|| TamguoConstant.CHAPTER_DEFAULT_ROOT_UID.equals(chapter.getCourseId())) {
				String uid = chapter.getUid();
				
				chapter.setUid(null);
				chapter.setParentId(chapter.getParentId());
				chapter.setCourseId(course.getUid());
				chapterMapper.insert(chapter);
				
				for(int k=0 ; k<chapterList.size() ; k++){
					ChapterEntity c = chapterList.get(k);
					if(c.getParentId().equals(uid)){
						c.setParentId(chapter.getUid());
					}
				}
			} else {
				ChapterEntity entity = chapterMapper.selectById(chapter.getUid());
				entity.setName(chapter.getName());
				chapterMapper.updateById(entity);
			}
			
		}
	}
}
