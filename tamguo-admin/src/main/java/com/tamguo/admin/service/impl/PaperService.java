package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.dao.PaperMapper;
import com.tamguo.admin.dao.redis.CacheService;
import com.tamguo.admin.model.PaperEntity;
import com.tamguo.admin.service.IPaperService;
import com.tamguo.admin.util.ShiroUtils;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class PaperService implements IPaperService{
	
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private CacheService cacheService;

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findHistoryPaper() {
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.HISTORY_PAPER);
		if(paperList == null || paperList.isEmpty()){
			Page<PaperEntity> page = new Page<>(1 , 6);
			paperList = paperMapper.findByTypeAndAreaId(TamguoConstant.ZHENGTI_PAPER_ID , TamguoConstant.BEIJING_AREA_ID , page);
			cacheService.setObject(TamguoConstant.ZHENGTI_PAPER_ID, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findSimulationPaper() {
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.SIMULATION_PAPER);
		if(paperList == null || paperList.isEmpty()){
			Page<PaperEntity> page = new Page<>(1 , 6);
			paperList = paperMapper.findByTypeAndAreaId(TamguoConstant.SIMULATION_PAPER_ID , TamguoConstant.BEIJING_AREA_ID , page);
			cacheService.setObject(TamguoConstant.SIMULATION_PAPER, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PaperEntity> findHotPaper(String areaId) {
		List<PaperEntity> paperList = (List<PaperEntity>) cacheService.getObject(TamguoConstant.HOT_PAPER);
		paperList = null;
		if(paperList == null || paperList.isEmpty()){
			Page<PaperEntity> page = new Page<>(1 , 10);
			paperList = paperMapper.findByAreaId(areaId , page);
			cacheService.setObject(TamguoConstant.HOT_PAPER, paperList , 2 * 60 * 60);
		}
		return paperList;
	}

	@Override
	public Page<PaperEntity> findList(String courseId,
			String paperType, String year, String area , Integer pageNum) {
		Page<PaperEntity> page = new Page<>(pageNum , TamguoConstant.DEFAULT_PAGE_SIZE);
		return page.setRecords(paperMapper.findList(courseId , paperType , year , area , page));
	}

	@Override
	public PaperEntity find(String paperId) {
		return paperMapper.selectById(paperId);
	}

	@Override
	public List<PaperEntity> findPaperByAreaId(String areaId , String type) {
		if("n".equals(type)){
			return this.findHotPaper(areaId);
		}
		Page<PaperEntity> page = new Page<>(1 , 8);
		return paperMapper.findPaperByAreaId(areaId , type , page);
	}

	@Override
	public Long getPaperTotal() {
		return paperMapper.getPaperTotal();
	}

	@Override
	public Page<PaperEntity> list(String name, Page<PaperEntity> page) {
		if(!StringUtils.isEmpty(name)){
			name = "%" + name + "%";
		}
		return page.setRecords(paperMapper.queryPageByName(name , page));
	}

	@Override
	public PaperEntity select(String paperId) {
		return paperMapper.selectById(paperId);
	}

	@Override
	public void deleteByIds(String[] paperIds) {
		paperMapper.deleteBatchIds(Arrays.asList(paperIds));
	}

	@Override
	public void save(PaperEntity paper) {
		paper.setCreaterId(ShiroUtils.getUid());
		paperMapper.insert(paper);
	}

	@Override
	public void update(PaperEntity paper) {
		paperMapper.updateById(paper);
	}

	@Override
	public List<PaperEntity> findByCreaterId(String createrId) {
		return paperMapper.findByCreaterId(createrId);
	}

	@Transactional(readOnly=false)
	@Override
	public void updatePaperName(String paperId, String name) {
		PaperEntity paper = paperMapper.selectById(paperId);
		paper.setName(name);
		paperMapper.updateById(paper);
	}

	@Override
	public void deletePaper(String paperId) {
		paperMapper.deleteById(paperId);
	}

	@Transactional(readOnly=false)
	@Override
	public void addPaperQuestionInfo(String paperId, String title,
			String name, String type) {
		PaperEntity paper = paperMapper.selectById(paperId);
		String questionInfo = paper.getQuestionInfo();
		
		JSONArray qList = JSONArray.parseArray(questionInfo);
		JSONObject entity = new JSONObject();
		entity.put("name", name);
		entity.put("title", title);
		entity.put("type", type);
		qList.add(entity);
		
		// 处理uid 问题
		for(int i=0 ; i<qList.size(); i++){
			JSONObject q = qList.getJSONObject(i);
			q.put("uid", i+1);
		}
		
		paper.setQuestionInfo(qList.toString());
		paperMapper.updateById(paper);
	}

	@Transactional(readOnly=false)
	@Override
	public void updatePaperQuestionInfo(String paperId, String title,
			String name, String type, String cuid) {
		PaperEntity paper = paperMapper.selectById(paperId);
		String questionInfo = paper.getQuestionInfo();
		JSONArray qList = JSONArray.parseArray(questionInfo);
		for(int i =0 ; i<qList.size() ; i++){
			JSONObject q = qList.getJSONObject(i);
			if(q.getString("uid").equals(cuid)){
				q.put("name", name);
				q.put("title", title);
				q.put("type", type);
			}
		}
		
		paper.setQuestionInfo(qList.toString());
		paperMapper.updateById(paper);
	}

	@Override
	public void deletePaperQuestionInfoBtn(String paperId, String cuid) {
		PaperEntity paper = paperMapper.selectById(paperId);
		String questionInfo = paper.getQuestionInfo();
		JSONArray qList = JSONArray.parseArray(questionInfo);
		for(int i =0 ; i<qList.size() ; i++){
			JSONObject q = qList.getJSONObject(i);
			if(q.getString("uid").equals(cuid)){
				qList.remove(i);
			}
		}
		
		// 处理uid 问题
		for(int i=0 ; i<qList.size(); i++){
			JSONObject q = qList.getJSONObject(i);
			q.put("uid", i+1);
		}
				
		paper.setQuestionInfo(qList.toString());
		paperMapper.updateById(paper);
	}


	@Override
	public Page<PaperEntity> memberPaperList(String name , String memberId , Integer page,
			Integer limit) {
		if(!StringUtils.isEmpty(name)){
			name = "%" + name + "%";
		}
		Page<PaperEntity> p = new Page<>(page , limit);
		return p.setRecords(paperMapper.queryPageByNameAndCreatorId(name , memberId , p));
	}

	@Transactional(readOnly=false)
	@Override
	public void addPaper(PaperEntity paper) {
		paper.setDownHits(0);
		paper.setOpenHits(0);
		paper.setQuestionInfo("[]");
		
		// 写入seo信息
		paper.setSeoTitle(paper.getName());
		paper.setSeoKeywords(paper.getName());
		paper.setSeoDescription(paper.getName());
		paperMapper.insert(paper);
	}

}
