package com.tamguo.config.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.tamguo.model.MemberEntity;
import com.tamguo.service.IMemberService;

/**
 * 认证
 * 
 */
public class MemberRealm extends AuthorizingRealm {
	
	@Autowired
	private IMemberService iMemberService;
	
    /**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String > permsSet = null;
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
        
        MemberEntity member = iMemberService.findByUsername(username);
        if(member == null) {
        	throw new UnknownAccountException("用户名或密码有误，请重新输入或找回密码");
        }
        Integer loginFailureCount = iMemberService.getLoginFailureCount(member);		
		
		if(!new Sha256Hash(password).toHex().equals(member.getPassword())){
			loginFailureCount++;
			iMemberService.updateLoginFailureCount(member , loginFailureCount);
			throw new IncorrectCredentialsException("用户名或密码有误，请重新输入或找回密码");
		}
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(member, password, getName());
        return info;
	}

}
