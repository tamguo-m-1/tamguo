package com.tamguo.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.tamguo.dao.PaperMapper;
import com.tamguo.dao.QuestionMapper;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private PaperMapper paperMapper;

	@Override
	public Page<QuestionEntity> findByChapterId(String chapterId  , Integer offset ,  Integer limit) {
		PageHelper.offsetPage(offset, limit);
		return questionMapper.findByChapterId(chapterId);
	}

	@Transactional(readOnly=true)
	@Override
	public QuestionEntity findNormalQuestion(String uid) {
		return questionMapper.findNormalQuestion(uid);
	}

	@Override
	public List<QuestionEntity> findPaperQuestion(String paperId) {
		return questionMapper.findByPaperId(paperId);
	}

	@Override
	public QuestionEntity select(String questionId) {
		return questionMapper.selectByUid(questionId);
	}

	@Transactional(readOnly=false)
	@Override
	public void addQuestion(QuestionEntity question) {
		PaperEntity paper = paperMapper.select(question.getPaperId().toString());
		question.setCourseId(paper.getCourseId());
		questionMapper.insert(question);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<QuestionEntity> queryQuestionList(String questionType , String uid , String reviewPoint , String paperId ,
			Integer page , Integer limit) {
		PageHelper.startPage(page, limit);
		if(!StringUtils.isEmpty(reviewPoint)){
			reviewPoint = "%" + reviewPoint + "%";
		}
		return questionMapper.queryQuestionList(questionType,  uid , reviewPoint , paperId);
	}

	@Transactional(readOnly=false)
	@Override
	public void updateQuestion(QuestionEntity question) {
		questionMapper.update(question);
	}

}
