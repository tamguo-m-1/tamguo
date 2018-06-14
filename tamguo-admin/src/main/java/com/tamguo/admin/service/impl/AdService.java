package com.tamguo.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.tamguo.admin.dao.AdMapper;
import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.AdEntity;
import com.tamguo.admin.service.IAdService;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class AdService implements IAdService{
	
	@Autowired
	AdMapper adMapper;
	@Autowired
	CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<AdEntity> findAll() {
		List<AdEntity> adList = (List<AdEntity>) cacheService.getObject(TamguoConstant.ALL_AD);
		adList = null;
		if(adList == null || adList.isEmpty()){
			adList = adMapper.selectList(Condition.EMPTY);
			cacheService.setObject(TamguoConstant.ALL_AD, adList , 2 * 60 * 60);
		}
		return adList;
	}
	
}
