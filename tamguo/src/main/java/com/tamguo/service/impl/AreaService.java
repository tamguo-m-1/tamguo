package com.tamguo.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.AreaMapper;
import com.tamguo.model.AreaEntity;
import com.tamguo.service.IAreaService;

@Service
public class AreaService extends ServiceImpl<AreaMapper, AreaEntity> implements IAreaService{
	
	@Autowired
	private AreaMapper areaMapper;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<AreaEntity> findAll() {
		return areaMapper.selectList(Condition.EMPTY);
	}
	
}
