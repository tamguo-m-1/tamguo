package com.tamguo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.QuestionEntity;

public interface QuestionMapper extends SuperMapper<QuestionEntity>{

	List<QuestionEntity> findByChapterId(@Param(value="chapterId")String chapterId , Pagination page);

	List<QuestionEntity> findByPaperId(@Param(value="paperId")String paperId);

	List<QuestionEntity> queryQuestionList(@Param(value="questionType")String questionType , @Param(value="uid")String uid , @Param(value="reviewPoint")String reviewPoint , @Param(value="paperId")String paperId , Pagination page);

	QuestionEntity selectByUid(@Param(value="uid")String uid);

	QuestionEntity findNormalQuestion(@Param(value="uid")String uid);

	List<QuestionEntity> featuredQuestion(@Param(value="subjectId")String subjectId, @Param(value="courseId")String courseId , Pagination page);

}
