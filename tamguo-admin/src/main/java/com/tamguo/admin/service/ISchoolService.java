package com.tamguo.admin.service;

import java.util.List;

import com.tamguo.admin.model.SchoolEntity;

public interface ISchoolService {

	public List<SchoolEntity> findEliteSchoolPaper(String shcoolId);
	
	public List<SchoolEntity> findEliteSchool();
	
}
