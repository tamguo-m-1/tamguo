package com.tamguo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.AreaMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.AreaEntity;
import com.tamguo.service.IAreaService;
import com.tamguo.util.Result;
import com.tamguo.util.TamguoConstant;

@Service
public class AreaService extends ServiceImpl<AreaMapper, AreaEntity> implements IAreaService{
	
	@Autowired
	private AreaMapper areaMapper;
	@Autowired
	private CacheService cacheService;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaEntity> findAll() {
		return areaMapper.selectList(Condition.EMPTY);
	}

	@Transactional(readOnly=true)
	@Override
	public List<AreaEntity> findRootArea() {
		return areaMapper.findRootArea();
	}

	@Transactional(readOnly=true)
	@Override
	public Result findAreaTree() {
		if(cacheService.isExist(TamguoConstant.AREA_ALL_TREE)) {
			return Result.successResult(cacheService.getObject(TamguoConstant.AREA_ALL_TREE));
		}
		List<AreaEntity> areaList = areaMapper.findRootArea();
		for(AreaEntity area : areaList) {
			List<AreaEntity> childend = areaMapper.findByParent(area.getUid());
			if(!CollectionUtils.isEmpty(childend)) {
				area.setChildren(childend);
			}
			
			for(AreaEntity a : childend) {
				List<AreaEntity> ceList = areaMapper.findByParent(a.getUid());
				if(!CollectionUtils.isEmpty(ceList)) {
					a.setChildren(ceList);
				}
			}
		}
		cacheService.setObject(TamguoConstant.AREA_ALL_TREE, areaList , 60 * 60 * 2);
		return Result.successResult(areaList);
	}
	
}
