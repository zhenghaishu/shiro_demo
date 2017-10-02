package com.z.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.z.util.DecriptUtil;

public class ShiroRealm extends AuthorizingRealm {
	private static final String USER_NAME = "admin";  
	private static final String PASSWORD = "123456";  
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		
		if(token.getUsername().equals(USER_NAME)){
			// it's a test demo, so there is no md5 encrypt for password
			return new SimpleAuthenticationInfo(USER_NAME, PASSWORD, getName());  
		}else{
			throw new AuthenticationException();  
		}
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { 
        Set<String> roleNames = new HashSet<String>();  
        Set<String> permissions = new HashSet<String>();  
        roleNames.add("administrator");
        permissions.add("newPage.html"); 
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);  
        info.setStringPermissions(permissions);  
        
        return info;  
	}

}
