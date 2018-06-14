package com.tamguo.admin.web.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.tamguo.admin.model.SysMenuEntity;
import com.tamguo.admin.service.ISysMenuService;
import com.tamguo.admin.util.CException;
import com.tamguo.admin.util.ExceptionSupport;
import com.tamguo.admin.util.Result;
import com.tamguo.admin.util.ShiroUtils;

/**
 * Controller - 用户菜单
 * 
 * @author candy.tam
 *
 */
@Controller
public class SysMenuController {
	
	@Autowired
	private ISysMenuService iSysMenuService;

	@RequestMapping("sysMenu/select")
	public @ResponseBody Result select() {
		// 查询列表数据
		List<SysMenuEntity> menuList = iSysMenuService.queryNotButtonList();

		// 添加顶级菜单
		SysMenuEntity root = new SysMenuEntity();
		root.setUid("0");
		root.setName("一级菜单");
		root.setParentId("-1");
		root.setOpen(true);
		menuList.add(root);
		return Result.successResult(menuList);
	}
	
	@RequestMapping("sysMenu/perms")
	public @ResponseBody Result perms() {
		// 查询列表数据
		Page<SysMenuEntity> page = new Page<>();
		page.setCurrent(1);
		page.setSize(10000);
		List<SysMenuEntity> menuList = iSysMenuService.queryList(null ,page).getRecords();
		return Result.successResult(menuList);
	}

	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping("sysMenu/queryPage")
	public @ResponseBody Map<String, Object> queryPage(Page<SysMenuEntity> page) {
		// 查询列表数据
		Page<SysMenuEntity> menuList = iSysMenuService.queryList(new HashMap<>(), page);
		
		List<SysMenuEntity> result = menuList.getRecords();
		return Result.jqGridResult(result, menuList.getTotal(), menuList.getLimit(), menuList.getCurrent(), menuList.getPages());
	}
	
	@RequestMapping("sysMenu/save")
	public @ResponseBody Result save(@RequestBody SysMenuEntity menu) {
		// 数据校验
		try {
			verifyForm(menu);
			iSysMenuService.save(menu);
			return Result.successResult(null);
		} catch (CException e) {
			return ExceptionSupport.resolverResult("保存菜单", this.getClass(), e);
		}
	}

	@RequestMapping("sysMenu/update")
	public @ResponseBody Result update(@RequestBody SysMenuEntity menu) {
		// 数据校验
		try {
			verifyForm(menu);
			iSysMenuService.update(menu);
			return Result.successResult(null);
		} catch (CException e) {
			return ExceptionSupport.resolverResult("修改菜单", this.getClass(), e);
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("sysMenu/delete")
	public @ResponseBody Result delete(@RequestBody String[] menuIds) {
		try {
			for (String menuId : menuIds) {
				if (Long.parseLong(menuId) <= 3) {
					return Result.failResult("系统菜单，不能删除");
				}
			}
			iSysMenuService.deleteBatch(menuIds);
			return Result.successResult(null);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("修改菜单", this.getClass(), e);
		}
	}	
	
	/**
	 * 菜单信息
	 */
	@RequestMapping("sysMenu/info/{menuId}")
	public @ResponseBody Result info(@PathVariable("menuId") String menuId) {
		SysMenuEntity menu = iSysMenuService.select(menuId);
		return Result.successResult(menu);
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu) {
		if (StringUtils.isEmpty(menu.getName())) {
			throw new CException("菜单名称不能为空");
		}

		if (menu.getParentId() == null) {
			throw new CException("上级菜单不能为空");
		}

		// 菜单
		if (menu.getType().equals(1)) {
			if (StringUtils.isEmpty(menu.getUrl())) {
				throw new CException("菜单URL不能为空");
			}
		}

		// 上级菜单类型
		Integer parentType = 0;
		if (!menu.getParentId().equals("0")) {
			SysMenuEntity parentMenu = iSysMenuService.select(menu.getParentId().toString());
			parentType = parentMenu.getType();
		}

		// 目录、菜单
		if (menu.getType().equals(0) || menu.getType().equals(1)) {
			if (!parentType.equals(0)) {
				throw new CException("上级菜单只能为目录类型");
			}
			return;
		}

		// 按钮
		if (menu.getType().equals(2)) {
			if (!parentType.equals(1)) {
				throw new CException("上级菜单只能为菜单类型");
			}
			return;
		}
	}
	
	/**
	 * 用户菜单列表
	 */
	@RequestMapping("sysMenu/user")
	public @ResponseBody Result user() {
		try {
			List<SysMenuEntity> menuList = iSysMenuService.getUserMenuList(ShiroUtils.getUid());
			menuList = iSysMenuService.getUserMenuTree(menuList);
			return Result.successResult(menuList);
		} catch (Exception e) {
			return ExceptionSupport.resolverResult("用户菜单列表", this.getClass(), e);
		}
	}
	
}
