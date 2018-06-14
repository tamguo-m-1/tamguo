package com.tamguo.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.dao.PaperMapper;
import com.tamguo.dao.SchoolMapper;
import com.tamguo.dao.redis.CacheService;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.SchoolEntity;
import com.tamguo.service.ISchoolService;
import com.tamguo.util.TamguoConstant;

@Service
public class SchoolService extends ServiceImpl<SchoolMapper, SchoolEntity> implements ISchoolService {

	@Autowired
	private SchoolMapper schoolMapper;
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolEntity> findEliteSchoolPaper(String shcoolId) {
		List<SchoolEntity> schoolList = (List<SchoolEntity>) cacheService.getObject(TamguoConstant.ELITE_SCHOOL_PAPER);
		schoolList = null;
		// 获取名校试卷
		if(schoolList == null || schoolList.isEmpty()){
			Page<SchoolEntity> page = new Page<>();
			page.setCurrent(1);
			page.setSize(3);
			schoolList = schoolMapper.findByAreaId(shcoolId , page);
			for(SchoolEntity school : schoolList){
				Page<PaperEntity> p = new Page<>();
				p.setCurrent(1);
				p.setSize(3);
				List<PaperEntity> paperList = paperMapper.findBySchoolId(school.getUid() , p);
				school.setPaperList(paperList);
			}
			cacheService.setObject(TamguoConstant.ELITE_SCHOOL_PAPER, schoolList , 2 * 60 * 60);
		}
		return schoolList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SchoolEntity> findEliteSchool() {
		List<SchoolEntity> schoolList = (List<SchoolEntity>) cacheService.getObject(TamguoConstant.ELITE_PAPER);
		if(schoolList == null || schoolList.isEmpty()){
			RowBounds row = new RowBounds(1 , 6);
			schoolList = schoolMapper.selectPage(row, Condition.EMPTY);
			cacheService.setObject(TamguoConstant.ELITE_PAPER, schoolList , 2 * 60 * 60);
		}
		return schoolList;
	}
	
}
