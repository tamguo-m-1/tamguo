package com.tamguo.admin.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;
import com.tamguo.admin.model.enums.SysUserStatusEnum;


/**
 * The persistent class for the reaps_sys_user database table.
 * 
 */
@TableName(value="sys_user")
public class SysUserEntity extends SuperEntity<SysUserEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String userName;

	private String nickName;
	
	private String mobile;
	
	private String email;

	private String password;

	private String roleIds;
	
	@JSONField(serialzeFeatures= SerializerFeature.WriteEnumUsingToString)
	private SysUserStatusEnum status;
	
	private String safeKeyValue;
	
	private Long createTime;

	@TableField(exist=false)
	private List<String> roleIdList;
	
	public SysUserEntity() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleIds() {
		return this.roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public List<String> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<String> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getSafeKeyValue() {
		return safeKeyValue;
	}

	public void setSafeKeyValue(String safeKeyValue) {
		this.safeKeyValue = safeKeyValue;
	}

	public SysUserStatusEnum getStatus() {
		return status;
	}

	public void setStatus(SysUserStatusEnum status) {
		this.status = status;
	}


}