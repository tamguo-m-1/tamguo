package com.tamguo.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tamguo.admin.dao.AreaMapper;
import com.tamguo.admin.model.AreaEntity;
import com.tamguo.admin.service.IAreaService;

@Service
public class AreaService implements IAreaService{
	
	@Autowired
	private AreaMapper areaMapper;
	
	@Override
	public List<AreaEntity> findAll() {
		return areaMapper.selectAll();
	}
	
}
