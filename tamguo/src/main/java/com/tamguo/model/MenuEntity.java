package com.tamguo.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;

import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the tiku_subject database table.
 * 
 */
@TableName(value="tiku_menu")
public class MenuEntity extends SuperEntity<MenuEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	
	private String pinyin;

	private BigInteger parentId;
	
	private String isShow;
	
	private Integer orders;

	private String url;
	
	private String reserve1;
	
	// 子类型
	@TableField(exist=false)
	private List<MenuEntity> childSubjects;

	public MenuEntity() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getParentId() {
		return this.parentId;
	}

	public void setParentId(BigInteger parentId) {
		this.parentId = parentId;
	}

	public List<MenuEntity> getChildSubjects() {
		return childSubjects;
	}

	public void setChildSubjects(List<MenuEntity> childSubjects) {
		this.childSubjects = childSubjects;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

}