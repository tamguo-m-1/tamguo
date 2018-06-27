package com.tamguo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.PaperEntity;

public interface PaperMapper extends SuperMapper<PaperEntity>{

	List<PaperEntity> findByTypeAndAreaId(@Param(value="type")String type, @Param(value="areaId")String areaId , Pagination page);
	
	List<PaperEntity> findByAreaId(@Param(value="areaId") String areaId , Pagination page);

	List<PaperEntity> findBySchoolId(@Param(value="schoolId")String schoolId , Pagination page);

	List<PaperEntity> findList(@Param(value="subjectId")String subjectId, @Param(value="courseId")String courseId, @Param(value="paperType")String paperType, 
			@Param(value="year")String year, @Param(value="area")String area , Pagination page);

	List<PaperEntity> findPaperByAreaId(@Param(value="areaId")String areaId , @Param(value="type")String type , Pagination page);

	Long getPaperTotal();

	List<PaperEntity> findByCreaterId(@Param(value="createrId")String createrId);

	List<PaperEntity> queryPageByNameAndCreatorId(@Param(value="name")String name, @Param(value="memberId")String memberId , Pagination page);

}
