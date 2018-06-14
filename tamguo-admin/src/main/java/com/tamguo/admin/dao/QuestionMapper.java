package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.QuestionEntity;

public interface QuestionMapper extends SuperMapper<QuestionEntity>{

	List<QuestionEntity> findByChapterId(@Param(value="chapterId")String chapterId , Pagination page);

	List<QuestionEntity> findByPaperId(@Param(value="paperId")String paperId);

	List<QuestionEntity> queryPageByName(@Param(value="name")String name , Pagination page);

	List<QuestionEntity> queryQuestionList(@Param(value="questionType")String questionType , @Param(value="uid")String uid , @Param(value="reviewPoint")String reviewPoint , Pagination page);

	QuestionEntity selectByUid(@Param(value="uid")String uid);

	QuestionEntity findNormalQuestion(@Param(value="uid")String uid);

}
