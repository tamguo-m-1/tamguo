package com.tamguo.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;


/**
 * The persistent class for the tiku_teacher database table.
 * 
 */
@TableName(value="tiku_teacher")
public class TeacherEntity extends SuperEntity<TeacherEntity> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mobile;
	
	private String name;
	
	private String motto;
	
	private String areaId;
	
	private String courseId;
	
	private String schoolId;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}