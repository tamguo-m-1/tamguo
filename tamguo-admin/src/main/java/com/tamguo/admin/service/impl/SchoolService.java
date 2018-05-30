package com.tamguo.admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.tamguo.admin.dao.PaperMapper;
import com.tamguo.admin.dao.SchoolMapper;
import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.PaperEntity;
import com.tamguo.admin.model.SchoolEntity;
import com.tamguo.admin.service.ISchoolService;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class SchoolService implements ISchoolService {

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
			PageHelper.startPage(1, 3);
			schoolList = schoolMapper.findByAreaId(shcoolId);
			for(SchoolEntity school : schoolList){
				PageHelper.startPage(1, 3);
				List<PaperEntity> paperList = paperMapper.findBySchoolId(school.getUid());
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
			PageHelper.startPage(1, 6);
			schoolList = schoolMapper.selectAll();
			cacheService.setObject(TamguoConstant.ELITE_PAPER, schoolList , 2 * 60 * 60);
		}
		return schoolList;
	}
	
}
