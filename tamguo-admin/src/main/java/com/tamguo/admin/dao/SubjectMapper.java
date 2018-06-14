package com.tamguo.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.SubjectEntity;

public interface SubjectMapper extends SuperMapper<SubjectEntity>{

	List<SubjectEntity> queryPage(@Param(value="name")String name , Pagination page);

}
