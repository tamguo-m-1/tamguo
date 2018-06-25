package com.tamguo.admin.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tamguo.admin.dao.SysUserMapper;
import com.tamguo.admin.dao.TeacherMapper;
import com.tamguo.admin.model.SysUserEntity;
import com.tamguo.admin.model.TeacherEntity;
import com.tamguo.admin.model.enums.SysUserStatusEnum;
import com.tamguo.admin.model.enums.TeacherStatus;
import com.tamguo.admin.service.IEmailService;
import com.tamguo.admin.service.ISmsService;
import com.tamguo.admin.service.ITeacherService;
import com.tamguo.admin.util.DateUtil;
import com.tamguo.admin.util.Result;
import com.tamguo.admin.util.TamguoConstant;

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, TeacherEntity> implements ITeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private IEmailService iEmailService;
	@Autowired
	private ISmsService iSmsService;
	@Autowired
	private SysUserMapper sysUserMapper;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public Page<TeacherEntity> queryPage(String mobile, Page<TeacherEntity> page) {
		return page.setRecords(teacherMapper.queryPage(mobile , page));
	}

	@Override
	public TeacherEntity find(String teacherId) {
		return teacherMapper.selectById(teacherId);
	}

	@Transactional(readOnly=false)
	@Override
	public Result update(TeacherEntity teacher) {
		TeacherEntity entity = teacherMapper.selectById(teacher.getUid());
		entity.setMobile(teacher.getMobile());
		entity.setName(teacher.getName());
		entity.setMobile(teacher.getMobile());
		entity.setCardId(teacher.getCardId());
		entity.setQq(teacher.getQq());
		entity.setEmail(teacher.getEmail());
		teacherMapper.updateById(entity);
		return Result.successResult("修改成功");
	}

	@Transactional(readOnly=false)
	@Override
	public Result pass(HttpServletRequest req , String teacherId) {
		TeacherEntity teacher = teacherMapper.selectById(teacherId);
		if(TeacherStatus.PASS == teacher.getStatus()) {
			return Result.result(Result.SUCCESS_CODE, null, "已经审核，不能再次审核！");
		}
		teacher.setStatus(TeacherStatus.PASS);
		teacherMapper.updateById(teacher);
		
		// 创建管理员账号
		SysUserEntity user = new SysUserEntity();
		user.setCreateTime(DateUtil.getTime());
		user.setEmail(teacher.getEmail());
		user.setMobile(teacher.getMobile());
		user.setNickName(teacher.getName());
		String password = this.generatePassword();
		user.setPassword(new Sha256Hash(password).toHex());
		user.setRoleIds(TamguoConstant.TEACHER_ROLE_ID);
		user.setStatus(SysUserStatusEnum.NORMAL);
		user.setUserName(teacher.getMobile());
		sysUserMapper.insert(user);
		
		try {
			// 发送邮件
			iEmailService.sendPassJoinusEmail(req , teacher , password);
			// 发送短信
			iSmsService.sendPassJoinusSms(teacher.getMobile());
		} catch (Exception e) {
			log.error(e.getMessage() , e);
		}
		
		return Result.result(Result.SUCCESS_CODE, null, "审核通过成功");
	}

	@Transactional(readOnly=false)
	@Override
	public Result deleteByIds(String[] teacherIds) {
		if(teacherIds != null) {
			if(!CollectionUtils.isEmpty(Arrays.asList(teacherIds))) {
				List<TeacherEntity> teacherList = teacherMapper.selectBatchIds(Arrays.asList(teacherIds));
				for(int i=0 ; i<teacherList.size() ; i++) {
					TeacherEntity teacher = teacherList.get(i);
					if(teacher.getStatus() == TeacherStatus.PASS) {
						return Result.result(Result.FAIL_CODE, null, "已经通过不能删除");
					}
				}
				// 发送短信
				
				// 发送邮件
				
				teacherMapper.deleteBatchIds(Arrays.asList(teacherIds));
				return Result.result(Result.SUCCESS_CODE, null, "删除成功");
			}
		}
		return Result.failResult("请选择要删除的记录！");
	}

	public String generatePassword() {
	    //字符源，可以根据需要删减
	    String generateSource = "0123456789abcdefghigklmnopqrstuvwxyz";
	    String rtnStr = "";
	    for (int i = 0; i < 8; i++) {
	        //循环随机获得当次字符，并移走选出的字符
	        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	        rtnStr += nowStr;
	        generateSource = generateSource.replaceAll(nowStr, "");
	    }
	    return rtnStr;
	}
}
