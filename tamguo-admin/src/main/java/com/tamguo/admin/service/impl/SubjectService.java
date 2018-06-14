package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.dao.CourseMapper;
import com.tamguo.admin.dao.SubjectMapper;
import com.tamguo.admin.model.CourseEntity;
import com.tamguo.admin.model.SubjectEntity;
import com.tamguo.admin.service.ISubjectService;

@Service
public class SubjectService implements ISubjectService{

	@Autowired
	private SubjectMapper subjectMapper;
	@Autowired
	private CourseMapper courseMapper;

	@Override
	public SubjectEntity find(String uid) {
		SubjectEntity subject = subjectMapper.selectById(uid);
		List<CourseEntity> courseList = courseMapper.findBySubjectId(uid);
		subject.setCourseList(courseList);
		return subject;
	}

	@Override
	public Page<SubjectEntity> list(String name , Integer pageNum , Integer pageSize) {
		Page<SubjectEntity> page = new Page<>(pageNum , pageSize);
		if(!StringUtils.isEmpty(name)){
			name = "%"+name+"%";
		}
		return page.setRecords(subjectMapper.queryPage(name , page));
	}

	@Override
	public void update(SubjectEntity subject) {
		SubjectEntity entity = subjectMapper.selectById(subject.getUid());
		entity.setName(subject.getName());
		if(!StringUtils.isEmpty(subject.getCourseId())){
			CourseEntity course = courseMapper.selectById(subject.getCourseId());
			entity.setCourseId(course.getUid());
			entity.setCourseName(course.getName());
		}
		subjectMapper.updateById(entity);
	}

	@Override
	public void save(SubjectEntity subject) {
		if(!StringUtils.isEmpty(subject.getCourseId())){
			CourseEntity course = courseMapper.selectById(subject.getCourseId());
			subject.setCourseId(course.getUid());
			subject.setCourseName(course.getName());
		}
		subjectMapper.insert(subject);
	}

	@Override
	public void deleteBatch(String[] subjectIds) {
		subjectMapper.deleteBatchIds(Arrays.asList(subjectIds));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubjectEntity> getSubjectTree() {
		return subjectMapper.selectList(Condition.EMPTY);
	}

	@Override
	public JSONArray getCourseTree() {
		JSONArray courseTree = new JSONArray();
		
		@SuppressWarnings("unchecked")
		List<SubjectEntity> subjectList = subjectMapper.selectList(Condition.EMPTY);
		for(int i=0 ; i<subjectList.size() ; i++){
			SubjectEntity subject = subjectList.get(i);
			JSONObject node = new JSONObject();
			node.put("uid", "s" + subject.getUid());
			node.put("name", subject.getName());
			node.put("parentId", "-1");
			courseTree.add(node);
			List<CourseEntity> courseList = courseMapper.findBySubjectId(subject.getUid());
			for(int k=0 ; k<courseList.size() ; k++){
				CourseEntity course = courseList.get(k);
				
				node = new JSONObject();
				node.put("uid", course.getUid());
				node.put("name", course.getName());
				node.put("parentId", "s" + subject.getUid());
				courseTree.add(node);
			}
		}
		return courseTree;
	}
	
}
