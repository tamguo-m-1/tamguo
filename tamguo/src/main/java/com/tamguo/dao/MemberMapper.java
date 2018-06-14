package com.tamguo.dao;

import org.apache.ibatis.annotations.Param;

import com.tamguo.config.dao.SuperMapper;
import com.tamguo.model.MemberEntity;

public interface MemberMapper extends SuperMapper<MemberEntity>{

	MemberEntity findByUsername(String username);

	MemberEntity findByMobile(String mobile);

	MemberEntity findByUsernameOrEmailOrMobile(@Param(value="account")String account);

}
