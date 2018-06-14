package com.tamguo.model;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotations.TableName;
import com.tamguo.config.dao.SuperEntity;

/**
 * The persistent class for the tiku_ad database table.
 * 
 */
@TableName(value="tiku_ad")
public class AdEntity extends SuperEntity<AdEntity> implements Serializable {
	private static final long serialVersionUID = 1L;

	private String businessKey;

	private String name;
	
	private String adInfo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAdInfo() {
		return adInfo;
	}

	public void setAdInfo(String adInfo) {
		this.adInfo = adInfo;
	}
	
	public JSONArray getAds(){
		if(StringUtils.isEmpty(getAdInfo())){
			return null;
		}
		return JSONArray.parseArray(getAdInfo());
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

}