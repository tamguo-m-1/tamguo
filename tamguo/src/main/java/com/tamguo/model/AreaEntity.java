package com.tamguo.model;

import java.io.Serializable;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}