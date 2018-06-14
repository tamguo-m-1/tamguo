package com.tamguo.admin.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;

/**
 * The persistent class for the tiku_ad database table.
 * 
 */
@TableName(value="tiku_member")
public class MemberEntity extends SuperEntity<MemberEntity> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String avatar;
	
	private String mobile;
	
	private String email;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}