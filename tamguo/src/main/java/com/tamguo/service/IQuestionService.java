package com.tamguo.service;

import java.util.List;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.model.QuestionEntity;
import com.tamguo.util.Result;

public interface IQuestionService {

	/** 根据章节获取问题 */
	public Page<QuestionEntity> findByChapterId(String chapterId ,  Page<QuestionEntity> page);
	
	/** 获取审核通过的题目 */
	public QuestionEntity findNormalQuestion(String uid);

	public List<QuestionEntity> findPaperQuestion(String paperId);

	public QuestionEntity select(String questionId);

	public Result addQuestion(QuestionEntity question);

	public Page<QuestionEntity> queryQuestionList(String questionType , String uid , String reviewPoint , String paperId ,
			Page<QuestionEntity> page);

	public Result updateQuestion(QuestionEntity question);

	public Result delete(String uid);

	public List<QuestionEntity> featuredQuestion(String subjectId, String courseId);

}
