package com.tamguo.admin.dao;

import org.apache.ibatis.annotations.Param;

import com.tamguo.admin.model.MemberEntity;
import com.tamguo.admin.mybatis.dao.BaseDao;

public interface MemberMapper extends BaseDao<MemberEntity>{

	MemberEntity findByUsername(String username);

	MemberEntity findByMobile(String mobile);

	MemberEntity findByUsernameOrEmailOrMobile(@Param(value="account")String account);

}
