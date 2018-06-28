package com.tamguo.admin.config.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.tamguo.admin.model.SysMenuEntity;
import com.tamguo.admin.model.SysUserEntity;
import com.tamguo.admin.model.enums.SysUserStatusEnum;
import com.tamguo.admin.service.ISysMenuService;
import com.tamguo.admin.service.ISysUserService;

/**
 * 认证
 * 
 */
public class UserRealm extends AuthorizingRealm {
	@Autowired
    private ISysMenuService sysMenuService;
    
    @Autowired
    private ISysUserService sysUserService;
    
    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserEntity user = (SysUserEntity)principals.getPrimaryPrincipal();
		String userId = user.getUid();
		
		List<String> permsList = null;
		
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(userId);
		permsList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			permsList.add(menu.getPerms());
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(StringUtils.isEmpty(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);
		
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        
        //查询用户信息
        SysUserEntity user = sysUserService.queryByUserName(username);
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定
        if(user.getStatus() == SysUserStatusEnum.LOCKED){
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
		
        return info;
	}

}
