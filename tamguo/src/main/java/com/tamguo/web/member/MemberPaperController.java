package com.tamguo.web.member;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.model.MemberEntity;
import com.tamguo.model.PaperEntity;
import com.tamguo.model.enums.QuestionType;
import com.tamguo.service.IPaperService;
import com.tamguo.util.ExceptionSupport;
import com.tamguo.util.Result;

@Controller
public class MemberPaperController {
	
	@Autowired
	private IPaperService iPaperService;
	

	@RequestMapping(value = "/member/paper", method = RequestMethod.GET)
	public ModelAndView paper(ModelAndView model, HttpSession session){
		model.setViewName("member/paperList");
		MemberEntity member = ((MemberEntity)session.getAttribute("currMember"));
		model.addObject("paperList", iPaperService.findByCreaterId(member.getUid()));
		return model;
	}
	
	@RequestMapping(value = "/member/findPaper", method = RequestMethod.GET)
	@ResponseBody
	public Result findPaper(String paperId) {
		return Result.successResult(iPaperService.find(paperId));
	}
	
	@RequestMapping(value = "member/paper/list" , method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> paperList(String name , Integer page , Integer limit , HttpSession session){
		MemberEntity member = ((MemberEntity)session.getAttribute("currMember"));
		Page<PaperEntity> p = new Page<>();
		p.setCurrent(page);
		p.setSize(limit);
		Page<PaperEntity> list = iPaperService.memberPaperList(name, member.getUid() , p);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), limit, page, list.getPages());
	}
	
	@RequestMapping("member/paperList/updatePaperName.html")
	@ResponseBody
	public Result updatePaperName(String paperId , String name){
		try {
			iPaperService.updatePaperName(paperId , name);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改试卷名称", this.getClass(), e);
		}
	}
	
	@RequestMapping(value="member/paperList/addPaperQuestionInfo",method=RequestMethod.POST)
	@ResponseBody
	public Result addPaperQuestionInfo(@RequestBody JSONObject data){
		try {
			String paperId ; String title ; String name ;String type;
			paperId = data.getString("uid");
			title = data.getString("title");
			type = data.getString("type");
			name = QuestionType.getQuestionType(type).getDesc();
			iPaperService.addPaperQuestionInfo(paperId , title , name , type);
			return Result.result(0, null, "修改成功");
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("添加questionInfo", this.getClass(), e);
		}
	}
	
	@RequestMapping("member/paperList/updatePaperQuestionInfo.html")
	@ResponseBody
	public Result updatePaperQuestionInfo(String paperId , String title , String name , String type , String cuid){
		try {
			iPaperService.updatePaperQuestionInfo(paperId , title , name , type , cuid);
			return Result.result(0, null, "修改成功");
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改questionInfo", this.getClass(), e);
		}
	}
	
	@RequestMapping("member/paperList/deletePaper.html")
	@ResponseBody
	public Result deletePaper(String paperId){
		try {
			iPaperService.deletePaper(paperId);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除试卷", this.getClass(), e);
		}
	}
	
	@RequestMapping("member/paperList/deletePaperQuestionInfoBtn.html")
	@ResponseBody
	public Result deletePaperQuestionInfoBtn(String paperId , String cuid){
		try {
			iPaperService.deletePaperQuestionInfoBtn(paperId , cuid);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除子卷", this.getClass(), e);
		}
	}
	
	@RequestMapping(value="member/paperList/addPaper.html",method=RequestMethod.POST)
	@ResponseBody
	public Result addPaper(@RequestBody PaperEntity paper,HttpSession session){
		try {
			MemberEntity member = (MemberEntity) session.getAttribute("currMember");
			paper.setCreaterId(member.getUid());
			iPaperService.addPaper(paper);
			return  Result.result(Result.SUCCESS_CODE, paper, "添加成功");
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("添加试卷", this.getClass(), e);
		}
	}
	
	@RequestMapping(value="member/paperList/updatePaper.html",method=RequestMethod.POST)
	@ResponseBody
	public Result updatePaper(@RequestBody PaperEntity paper){
		try {
			return iPaperService.updatePaper(paper);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改试卷", this.getClass(), e);
		}
	}
}
