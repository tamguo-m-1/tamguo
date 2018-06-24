package com.tamguo.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.tamguo.admin.config.dao.SuperMapper;
import com.tamguo.admin.model.TeacherEntity;

public interface TeacherMapper extends SuperMapper<TeacherEntity>{

	List<TeacherEntity> queryPage(@Param(value="mobile") String mobile , Pagination page);

}
