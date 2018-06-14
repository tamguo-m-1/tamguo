package com.tamguo.admin.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;


/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@TableName(value="tiku_paper")
public class PaperEntity extends SuperEntity<PaperEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String subjectId;
	
	private String courseId;
	
	private String schoolId;
	
	private String areaId;
	
	private String createrId;
	
	private String name;
	
	private String questionInfo;
	
	private String type;
	
	private String year;
	
	private Integer downHits;
	
	private Integer openHits;
	
	private String seoTitle;
	
	private String seoKeywords;
	
	private String seoDescription;
	
	@TableField(exist=false)
	private String subjectName;
	
	@TableField(exist=false)
	private String courseName;
	
	@TableField(exist=false)
	private String areaName;
	
	@TableField(exist=false)
	private String schoolName;
	
	public JSONArray getQueInfo(){
		if(StringUtils.isEmpty(getQuestionInfo())){
			return null;
		}
		return JSONArray.parseArray(getQuestionInfo());
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public Integer getDownHits() {
		return downHits;
	}

	public void setDownHits(Integer downHits) {
		this.downHits = downHits;
	}

	public Integer getOpenHits() {
		return openHits;
	}

	public void setOpenHits(Integer openHits) {
		this.openHits = openHits;
	}

	public String getQuestionInfo() {
		return questionInfo;
	}

	public void setQuestionInfo(String questionInfo) {
		this.questionInfo = questionInfo;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public String getSeoTitle() {
		return seoTitle;
	}

	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}

	public String getSeoKeywords() {
		return seoKeywords;
	}

	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}

	public String getSeoDescription() {
		return seoDescription;
	}

	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}


}