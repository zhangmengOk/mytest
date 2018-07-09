package com.itheima.realms;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 自定义Realm
 * @author lenovo
 *
 */
public class MyRealm extends AuthorizingRealm{

	//授权方法
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("授权方法....");
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		//存入授权标记
		//基于资源授权
		/*info.addStringPermission("user:add");
		info.addStringPermission("user:update");
		info.addStringPermission("user:delete");
		*/
		info.addStringPermission("user:*");
		
		//基于角色授权
		info.addRole("admin");
		
		return info;
	}

	//认证方法
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("认证方法....");
		
		//模拟数据库的账户信息
		String username = "eric";
		String password = "123456";
		Long id = 10L;
		
		
		//获取用户输入的账户信息
		UsernamePasswordToken token = (UsernamePasswordToken)arg0;
		
		//1.判断账户是否存在
		if(!token.getUsername().equals(username)){
			System.out.println("不存在....");
			//账户不存在
			//注意：开发者只需要返回null即可（Shiro底层自动会抛出UnknownAccountException）
			return null;
		}
		//2.判断密码是否正确
		/**
		 * SimpleAuthenticationInfo的参数：
		 *   参数一：principal数据。作为login方法的"返回值"
		 *   参数二：返回数据库的密码
		 *   参数三：realm的别名，只有在多个realm的情况下才会起作用    （一个realm的时候留空即可）
		 */
		/**
		 * Shiro底层会自动判断数据库密码 和 用户输入的密码 是否匹配
		 *   1）正确，则认证通过啦
		 *   2）错误，则抛出异常IncorrectCredentialsException
		 */
		return new SimpleAuthenticationInfo(id, password , "");
	}

}
