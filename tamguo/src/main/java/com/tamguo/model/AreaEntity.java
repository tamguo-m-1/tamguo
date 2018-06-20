package com.tamguo.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;

/**
 * The persistent class for the tiku_chapter database table.
 * 
 */
@TableName(value="tiku_area")
public class AreaEntity extends SuperEntity<AreaEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private Integer orders;
	
	private String fullName;
	
	private Integer grade;
	
	private String treePath;
	
	private String parent;
	
	/** 下级地区 */
	@TableField(exist=false)
	private List<AreaEntity> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	public String getParent() {
		return parent;
	}
	
	public String getValue() {
		return getUid();
	}
	
	public String getLabel() {
		return getName();
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public List<AreaEntity> getChildren() {
		return children;
	}

	public void setChildren(List<AreaEntity> children) {
		this.children = children;
	}
}