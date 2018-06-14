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
@TableName(value="tiku_school")
public class SchoolEntity extends SuperEntity<SchoolEntity> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String areaId;
	
	private String name;
	
	private String image;
	
	// 试卷
	@TableField(exist=false)
	private List<PaperEntity> paperList;

	public SchoolEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public List<PaperEntity> getPaperList() {
		return paperList;
	}

	public void setPaperList(List<PaperEntity> paperList) {
		this.paperList = paperList;
	}

}