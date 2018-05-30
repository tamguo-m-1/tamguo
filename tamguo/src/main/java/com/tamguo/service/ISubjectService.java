package com.tamguo.service;

import com.alibaba.fastjson.JSONArray;
import com.tamguo.model.SubjectEntity;

public interface ISubjectService {

	public SubjectEntity find(String uid);
	
	public JSONArray getCourseTree();
	
}
