package com.tamguo.admin.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.admin.config.dao.SuperEntity;

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

	public void setParent(String parent) {
		this.parent = parent;
	}
}