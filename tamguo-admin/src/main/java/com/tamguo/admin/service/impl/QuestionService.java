package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.dao.ChapterMapper;
import com.tamguo.admin.dao.PaperMapper;
import com.tamguo.admin.dao.QuestionMapper;
import com.tamguo.admin.model.ChapterEntity;
import com.tamguo.admin.model.PaperEntity;
import com.tamguo.admin.model.QuestionEntity;
import com.tamguo.admin.service.IQuestionService;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class QuestionService implements IQuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private ChapterMapper chapterMapper;

	@Override
	public Page<QuestionEntity> findByChapterId(String chapterId  , Integer offset ,  Integer limit) {
		Page<QuestionEntity> p = new Page<>(offset , limit);
		return p.setRecords(questionMapper.findByChapterId(chapterId , p));
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
	public Page<QuestionEntity> list(String name, Integer page, Integer limit) {
		Page<QuestionEntity> p = new Page<>(page , limit);
		return p.setRecords(questionMapper.queryPageByName(name , p));
	}

	@Override
	public QuestionEntity select(String questionId) {
		return questionMapper.selectByUid(questionId);
	}

	@Override
	public void deleteBatch(String[] questionIds) {
		questionMapper.deleteBatchIds(Arrays.asList(questionIds));
	}

	@Transactional(readOnly=false)
	@Override
	public void addQuestion(QuestionEntity question) {
		PaperEntity paper = paperMapper.selectById(question.getPaperId().toString());
		question.setCourseId(paper.getCourseId());
		questionMapper.insert(question);
	}

	@Override
	public Page<QuestionEntity> queryQuestionList(QuestionEntity question , Integer page , Integer limit) {
		Page<QuestionEntity> p = new Page<>(page , limit);
		if(!StringUtils.isEmpty(question.getReviewPoint())){
			question.setReviewPoint("%" + question.getReviewPoint() + "%");
		}
		return p.setRecords(questionMapper.queryQuestionList(question.getQuestionType(),question.getUid() ,question.getReviewPoint() , p));
	}

	@Transactional(readOnly=false)
	@Override
	public void save(QuestionEntity question) {
		question.setAuditStatus(TamguoConstant.QUESTION_NOTHING_AUDIT_STATUS);
		questionMapper.insert(question);
	}

	@Transactional(readOnly=false)
	@Override
	public void update(QuestionEntity question) {
		question.setAuditStatus(TamguoConstant.QUESTION_NOTHING_AUDIT_STATUS);
		questionMapper.updateById(question);
	}

	@Transactional(readOnly=false)
	@Override
	public void audit(String[] questionIds) {
		List<QuestionEntity> questions = questionMapper.selectBatchIds(Arrays.asList(questionIds));
		for(int i=0 ; i<questions.size() ; i++) {
			QuestionEntity question = questions.get(i);
			if(TamguoConstant.QUESTION_SUCCESS_AUDIT_STATUS.equals(question.getAuditStatus())) {
				continue;
			}
			question.setAuditStatus(TamguoConstant.QUESTION_SUCCESS_AUDIT_STATUS);
			questionMapper.updateById(question);
			
			// 章节题目数添加
			ChapterEntity chapter = chapterMapper.selectById(question.getChapterId().toString());
			if(chapter != null) {
				chapter.setQuestionNum(chapter == null ? 0 : chapter.getQuestionNum().intValue() + 1);
				chapterMapper.updateById(chapter);
			}
		}
	}

	@Transactional(readOnly=false)
	@Override
	public void notAudit(String[] questionIds) {
		List<QuestionEntity> questions = questionMapper.selectBatchIds(Arrays.asList(questionIds));
		for(int i=0 ; i<questions.size() ; i++) {
			QuestionEntity question = questions.get(i);
			if(TamguoConstant.QUESTION_FAILED_AUDIT_STATUS.equals(question.getAuditStatus())) {
				continue;
			}
			question.setAuditStatus(TamguoConstant.QUESTION_FAILED_AUDIT_STATUS);
			questionMapper.updateById(question);
			
			// 章节题目数添加
			ChapterEntity chapter = chapterMapper.selectById(question.getChapterId().toString());
			if(chapter != null) {
				chapter.setQuestionNum(chapter == null ? 0 : chapter.getQuestionNum().intValue() - 1);
				chapterMapper.updateById(chapter);
			}
		}
	}

}
