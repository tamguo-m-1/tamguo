package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.ChapterEntity;

public interface ChapterMapper extends SuperMapper<ChapterEntity>{

	List<ChapterEntity> findByCourseId(@Param(value="courseId") String courseId);
	
	List<ChapterEntity> findByParentId(@Param(value="parentId") String parentId);

	ChapterEntity findNextPoint(@Param(value="uid")String uid , @Param(value="orders")Integer orders);

	void deleteByCourseId(@Param(value="courseId")String courseId);

	List<ChapterEntity> queryPage(@Param(value="name")String name, Pagination page);

	ChapterEntity select(@Param(value="chapterId")String chapterId);

}
