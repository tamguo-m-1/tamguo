package com.tamguo.service.impl;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.PaperMapper;
import com.tamguo.dao.QuestionMapper;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.QuestionEntity;
import com.tamguo.service.IQuestionService;

@Service
public class QuestionService extends ServiceImpl<QuestionMapper, QuestionEntity> implements IQuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private PaperMapper paperMapper;

	@Override
	public Page<QuestionEntity> findByChapterId(String chapterId  , Page<QuestionEntity> page) {
		return page.setRecords(questionMapper.findByChapterId(chapterId , page));
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
		PaperEntity paper = paperMapper.selectById(question.getPaperId().toString());
		question.setCourseId(paper.getCourseId());
		questionMapper.insert(question);
	}

	@Transactional(readOnly=true)
	@Override
	public Page<QuestionEntity> queryQuestionList(String questionType , String uid , String reviewPoint , String paperId ,
			Page<QuestionEntity> page) {
		if(!StringUtils.isEmpty(reviewPoint)){
			reviewPoint = "%" + reviewPoint + "%";
		}
		return page.setRecords(questionMapper.queryQuestionList(questionType,  uid , reviewPoint , paperId , page));
	}

	@Transactional(readOnly=false)
	@Override
	public void updateQuestion(QuestionEntity question) {
		questionMapper.updateById(question);
	}

	@Transactional(readOnly=false)
	@Override
	public void delete(String uid) {
		questionMapper.deleteById(uid);
	}

	@Transactional(readOnly=true)
	@Override
	public List<QuestionEntity> featuredQuestion(String subjectId, String courseId) {
		Page<QuestionEntity> page = new Page<>(1 , 5);
		return questionMapper.featuredQuestion(subjectId , courseId , page);
	}

}
