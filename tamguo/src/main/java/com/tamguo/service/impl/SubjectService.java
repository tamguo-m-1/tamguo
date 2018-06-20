package com.tamguo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.CourseMapper;
import com.tamguo.dao.SubjectMapper;
import com.tamguo.model.CourseEntity;
import com.tamguo.model.SubjectEntity;
import com.tamguo.service.ISubjectService;

@Service
public class SubjectService extends ServiceImpl<SubjectMapper, SubjectEntity> implements ISubjectService{

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

	@Override
	public JSONArray getCourseCascaderTree() {
		JSONArray courseTree = new JSONArray();
		
		@SuppressWarnings("unchecked")
		List<SubjectEntity> subjectList = subjectMapper.selectList(Condition.EMPTY);
		for(int i=0 ; i<subjectList.size() ; i++){
			SubjectEntity subject = subjectList.get(i);
			JSONObject node = new JSONObject();
			node.put("value", "s" + subject.getUid());
			node.put("label", subject.getName());
			
			
			JSONArray children = new JSONArray();
			List<CourseEntity> courseList = courseMapper.findBySubjectId(subject.getUid());
			for(int k=0 ; k<courseList.size() ; k++){
				CourseEntity course = courseList.get(k);
				
				JSONObject no = new JSONObject();
				no.put("value", course.getUid());
				no.put("label", course.getName());
				children.add(no);
			}
			node.put("children", children);
			courseTree.add(node);
		}
		return courseTree;
	}
	
}
