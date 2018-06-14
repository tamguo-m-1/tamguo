package com.tamguo.admin.web.sys;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysRoleEntity;
import com.tamguo.admin.service.ISysRoleService;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;
/**
 * Controller - 用户角色
 * 
 * @author candy.tam
 *
 */
@Controller
public class SysRoleController {

	@Autowired
	public ISysRoleService iSysRoleService;
	
	@RequestMapping("sysRole/all")
	public @ResponseBody Result all() {
		List<SysRoleEntity> roleList = iSysRoleService.findAll();
		return Result.successResult(roleList);
	}
	
	@RequestMapping("sysRole/queryPage")
	public @ResponseBody Map<String, Object> queryPage(String roleName, Page<SysRoleEntity> page) {
		SysRoleEntity role = new SysRoleEntity();
		role.setName(roleName);
		// 查询列表数据
		Page<SysRoleEntity> list = iSysRoleService.queryList(role, page);
		List<SysRoleEntity> result = list.getRecords();
		return Result.jqGridResult(result, list.getTotal(), list.getSize(), list.getCurrent(), list.getPages());
	}
	
	/**
	 * 角色信息
	 */
	@RequestMapping("sysRole/info/{roleId}")
	public @ResponseBody Result info(@PathVariable("roleId") String roleId) {
		SysRoleEntity role = iSysRoleService.select(roleId);

		// 查询角色对应的菜单
		List<String> menuIdList = null;
		if(!StringUtils.isEmpty(role.getPerms())) {
			menuIdList = Arrays.asList(role.getPerms().split(","));
		}
		role.setMenuIdList(menuIdList);

		return Result.successResult(role);
	}
	

	/**
	 * 保存角色
	 */
	@RequestMapping(value = "sysRole/save", method = RequestMethod.POST)
	public @ResponseBody Result save(@RequestBody SysRoleEntity role) {
		if (StringUtils.isEmpty(role.getName())) {
			return Result.failResult("角色名称不能为空");
		}
		try {
			iSysRoleService.save(role);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("保存角色", this.getClass(), e);
		}
	}
	
	/**
	 * 修改角色
	 */
	@RequestMapping("sysRole/update")
	public @ResponseBody Result update(@RequestBody SysRoleEntity role) {
		if (StringUtils.isEmpty(role.getName())) {
			return Result.failResult("角色名称不能为空");
		}
		try {
			iSysRoleService.update(role);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改角色", this.getClass(), e);
		}
	}

	@RequestMapping("sysRole/delete")
	public @ResponseBody Result delete(@RequestBody String[] roleIds) {
		try {
			iSysRoleService.deleteBatch(roleIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("删除角色", this.getClass(), e);
		}
	}
	
}
