package com.tamguo.admin.web.sys;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysUserEntity;
import com.tamguo.admin.service.ISysUserService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;
import com.tamguo.admin.util.ShiroUtils;

/**
 * Controller - 用户列表
 * 
 * @author candy.tam
 *
 */
@Controller
public class SysUserController {
	
	@Autowired
	private ISysUserService sysUserService;
	
	@RequestMapping(value = "sysUser/queryPage", method = RequestMethod.GET)
	@ResponseBody
	@RequiresPermissions("sys:user:list")
	public Map<String, Object> queryPage(String userName ,Page<SysUserEntity> page) {
		Page<SysUserEntity> list = sysUserService.queryPage(userName , page);
		return Result.jqGridResult(list.getRecords(), list.getTotal(), list.getSize(), list.getCurrent(), list.getPages());
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("sysUser/info/{userId}")
	public @ResponseBody Result info(@PathVariable("userId") String userId) {
		SysUserEntity user = sysUserService.selectById(userId);
		// 处理角色
		if(!org.apache.commons.lang3.StringUtils.isEmpty(user.getRoleIds())) {
			user.setRoleIdList(Arrays.asList(user.getRoleIds().split(",")));
		}
		user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
		return Result.successResult(user);
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping("sysUser/save")
	public @ResponseBody Result save(@RequestBody SysUserEntity user) {
		if (StringUtils.isEmpty(user.getUserName())) {
			return Result.failResult("用户名不能为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			return Result.failResult("密码不能为空");
		}
		try {
			sysUserService.save(user);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存用户", this.getClass(), e);
		}

	}

	/**
	 * 修改用户
	 */
	@RequestMapping("sysUser/update")
	public @ResponseBody Result update(@RequestBody SysUserEntity user) {
		if (StringUtils.isEmpty(user.getUserName())) {
			return Result.failResult("用户名不能为空");
		}
		try {
			sysUserService.update(user);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改用户", this.getClass(), e);
		}
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("sysUser/delete")
	public @ResponseBody Result delete(@RequestBody String[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return Result.failResult("系统管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, ShiroUtils.getUid())) {
			return Result.failResult("当前用户不能删除");
		}
		try {
			sysUserService.deleteBatch(userIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除用户", this.getClass(), e);
		}
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("sysUser/info")
	public @ResponseBody Result info() {
		return Result.successResult(ShiroUtils.getUser());
	}

	@RequestMapping(path="sysUser/password" , method=RequestMethod.POST)
	public @ResponseBody Result password(String password , String newPassword) {
		return sysUserService.updatePassword(password, newPassword, newPassword);
	}
}
