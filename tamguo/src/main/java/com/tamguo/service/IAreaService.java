package com.tamguo.service;

import java.util.List;

import com.tamguo.model.AreaEntity;
import com.tamguo.util.Result;

public interface IAreaService {

	public List<AreaEntity> findAll();
	
	public List<AreaEntity> findRootArea();

	public Result findAreaTree();
	
}
