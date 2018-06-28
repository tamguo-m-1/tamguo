package com.tamguo.service;

import com.tamguo.model.MemberEntity;
import com.tamguo.util.Result;

public interface IMemberService {

	public Result login(String username , String password);
	
	public Result checkUsername(String username);

	public Result checkMobile(String mobile);
	
	public Result register(MemberEntity member);

	public Result checkAccount(String account);
	
	public Result confirmAccount(String account , String veritycode);

	public Result securityCheck(String username , String isEmail , String vcode);

	public Result resetPassword(String resetPasswordKey , String username , String password, String verifypwd);
	
	public Integer getLoginFailureCount(MemberEntity member);
	
	public void updateMember(MemberEntity member);
	
	public MemberEntity findByUid(String uid);
	
	public MemberEntity findByUsername(String username);
	
	public void updateLoginFailureCount(MemberEntity member , Integer loginFailureCount);

	public void updateLastLoginTime(String string);
}
