package com.tamguo.admin.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;


/**
 * The persistent class for the reaps_sys_role database table.
 * 
 */
@TableName(value="sys_role")
public class SysRoleEntity extends SuperEntity<SysRoleEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String perms;

	private String name;

	@TableField(exist=false)
	private List<String> menuIdList;

	public SysRoleEntity() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMenuIdList() {
		return menuIdList;
	}

	public void setMenuIdList(List<String> menuIdList) {
		this.menuIdList = menuIdList;
	}

	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

}