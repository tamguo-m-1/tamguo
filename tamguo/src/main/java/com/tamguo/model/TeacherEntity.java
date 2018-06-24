package com.tamguo.model;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;
import com.tamguo.model.enums.TeacherStatus;


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
	
	private String provinceId;
	
	private String cityId;
	
	private String subjectId;
	
	private String courseId;
	
	private String cardId;
	
	private String cardPhoto;
	
	private String occupationPapers;
	
	private Long createTime;
	
	@JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
	private TeacherStatus status;
	
	private String qq;

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

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardPhoto() {
		return cardPhoto;
	}

	public void setCardPhoto(String cardPhoto) {
		this.cardPhoto = cardPhoto;
	}

	public String getOccupationPapers() {
		return occupationPapers;
	}

	public void setOccupationPapers(String occupationPapers) {
		this.occupationPapers = occupationPapers;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public TeacherStatus getStatus() {
		return status;
	}

	public void setStatus(TeacherStatus status) {
		this.status = status;
	}

}