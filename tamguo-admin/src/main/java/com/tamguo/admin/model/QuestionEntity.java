package com.tamguo.admin.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;

import java.math.BigInteger;


/**
 * The persistent class for the tiku_question database table.
 * 
 */
@TableName(value="tiku_question")
public class QuestionEntity extends SuperEntity<QuestionEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String analysis;
	
	private BigInteger paperId;

	private String answer;

	private BigInteger chapterId;

	private String questionType;

	private String content;
	
	private String subjectId;

	private String courseId;
	
	private String reviewPoint;
	
	private String year;
	
	private Integer score;
	
	private String auditStatus;
	
	@TableField(exist=false)
	private String courseName;
	
	@TableField(exist=false)
	private String chapterName;
	
	@TableField(exist=false)
	private String subjectName;

	public QuestionEntity() {
	}

	public String getAnalysis() {
		return this.analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public BigInteger getChapterId() {
		return this.chapterId;
	}

	public void setChapterId(BigInteger chapterId) {
		this.chapterId = chapterId;
	}
	
	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReviewPoint() {
		return reviewPoint;
	}

	public void setReviewPoint(String reviewPoint) {
		this.reviewPoint = reviewPoint;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public BigInteger getPaperId() {
		return paperId;
	}

	public void setPaperId(BigInteger paperId) {
		this.paperId = paperId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
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

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}