package com.tamguo.dao;

import org.apache.ibatis.annotations.Param;

import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.TeacherEntity;

public interface TeacherMapper extends SuperMapper<TeacherEntity>{

	TeacherEntity findByMobile(@Param(value="mobile") String mobile);

}
