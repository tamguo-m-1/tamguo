package com.tamguo.admin.service;

import com.tamguo.admin.model.MemberEntity;
import com.tamguo.admin.util.Result;

public interface IMemberService {

	public Result login(String username , String password , String captcha);
	
	public Result checkUsername(String username);

	public Result checkMobile(String mobile);
	
	public Result register(String username , String mobile , String password , String verifyCode);

	public Result checkAccount(String account);
	
	public Result confirmAccount(String account , String veritycode);

	public Result securityCheck(String username , String isEmail , String vcode);

	public Result resetPassword(String resetPasswordKey , String username , String password, String verifypwd);
	
	public Integer getLoginFailureCount(MemberEntity member);
	
	public void updateMember(MemberEntity member);
	
	public MemberEntity findByUid(String uid);
}
